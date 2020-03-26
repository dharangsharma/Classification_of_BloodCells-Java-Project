package RedBloodCell;

import com.sun.media.jai.widget.DisplayJAI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.iterator.RandomIter;
import javax.media.jai.iterator.RandomIterFactory;
import javax.swing.*;

public class NaiveSimilarityFinder extends JFrame {

    JFrame frame;

    private Color[][] signature;
    private static final int baseSize = 300;
    private static final String basePath = "finaldatabse";
    String db = "C:\\img_data\\check.jpg";

    NaiveSimilarityFinder(File reference) throws IOException {

        super("Image Search Engine");
        UserDAO ud = new UserDAO();
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        RenderedImage ref = rescale(ImageIO.read(reference));
        System.out.println("ref=" + reference);
        cp.add(new DisplayJAI(ref), BorderLayout.WEST);//this class extends Jpanel in order to support layout managment

        signature = calcSignature(ref);

        File f = new File(db);
        File[] others = getOtherImageFiles(f);
        System.out.println("others=" + others);
        JPanel otherPanel = new JPanel(new GridLayout(5, 2));//for right side panel images
        cp.add(new JScrollPane(otherPanel), BorderLayout.CENTER);

        RenderedImage[] rothers = new RenderedImage[others.length];//to convert others into raster images
        double[] distances = new double[others.length];
        for (int o = 0; o < others.length; o++) {
            rothers[o] = rescale(ImageIO.read(others[o]));
            distances[o] = calcDistance(rothers[o]);
        }

        for (int p1 = 0; p1 < others.length - 1; p1++) {
            for (int p2 = p1 + 1; p2 < others.length; p2++) {
                if (distances[p1] > distances[p2]) {
                    double tempDist = distances[p1];
                    distances[p1] = distances[p2];
                    distances[p2] = tempDist;
                    RenderedImage tempR = rothers[p1];
                    rothers[p1] = rothers[p2];
                    rothers[p2] = tempR;
                    File tempF = others[p1];
                    others[p1] = others[p2];
                    others[p2] = tempF;
                }
            }
        }

        String percent = "0%";
        try {
            for (int i = 0; i < 5; i++) {

                if (distances[i] == 0) {
                    percent = "100%";

                } else if (distances[i] >= 2000) {
                    percent = "30%";
                    continue;
                } else if (distances[i] >= 1500) {
                    percent = "40%";
                } else if (distances[i] >= 1000) {
                    percent = "50%";
                } else if (distances[i] >= 800) {
                    percent = "60%";
                } else if (distances[i] >= 600) {
                    percent = "70%";
                } else if (distances[i] >= 400) {
                    percent = "80%";
                } else if (distances[i] >= 200) {
                    percent = "90%";
                } else if (distances[i] >= 100) {
                    percent = "95%";
                } else {
                    percent = "99%";
                }
                String name = ud.retrive(others[i].getName());
                String desc = ud.retriveType(others[i].getName());
                if (name != null) {
                    otherPanel.add(new DisplayJAI(rothers[i]));
                    JLabel ldist = new JLabel("<html>" + others[i].getName() + "<br>"
                            + percent + "<br>Type: " + ud.retrive(others[i].getName()) + "<br>Description: "+desc+"</html>");
                    ldist.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));

                    otherPanel.add(ldist);
                }

            }

        } catch (Exception e) {
        }

        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private RenderedImage rescale(RenderedImage i) {
        float scaleW = ((float) baseSize) / i.getWidth();
        float scaleH = ((float) baseSize) / i.getHeight();

        ParameterBlock pb = new ParameterBlock();
        pb.addSource(i);//source image
        pb.add(scaleW);//xscale
        pb.add(scaleH);//yscale
        pb.add(0.0F);//x translation
        pb.add(0.0F);//y translation
        pb.add(new InterpolationNearest());

        return JAI.create("scale", pb);//write newly resized image to an output
    }

    private Color[][] calcSignature(RenderedImage i) {

        Color[][] sig = new Color[5][5];
        float[] prop = new float[]{1f / 10f, 3f / 10f, 5f / 10f, 7f / 10f, 9f / 10f};//float literals 
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                sig[x][y] = averageAround(i, prop[x], prop[y]);
            }
        }
        return sig;
    }

    private Color averageAround(RenderedImage i, double px, double py) {

        RandomIter iterator = RandomIterFactory.create(i, null);

        double[] pixel = new double[3];
        double[] accum = new double[3];
        int sampleSize = 15;
        int numPixels = 0;

        for (double x = px * baseSize - sampleSize; x < px * baseSize + sampleSize; x++) {
            for (double y = py * baseSize - sampleSize; y < py * baseSize + sampleSize; y++) {
                iterator.getPixel((int) x, (int) y, pixel);//returns the samples of the specified pixel from the image in an array.
                accum[0] += pixel[0];
                accum[1] += pixel[1];
                accum[2] += pixel[2];
                numPixels++;
            }
        }

        accum[0] /= numPixels;
        accum[1] /= numPixels;
        accum[2] /= numPixels;
        return new Color((int) accum[0], (int) accum[1], (int) accum[2]);
    }

    private double calcDistance(RenderedImage other) {

        Color[][] sigOther = calcSignature(other);

        double dist = 0;
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                int r1 = signature[x][y].getRed();
                int g1 = signature[x][y].getGreen();
                int b1 = signature[x][y].getBlue();
                int r2 = sigOther[x][y].getRed();
                int g2 = sigOther[x][y].getGreen();
                int b2 = sigOther[x][y].getBlue();
                double tempDist = Math.sqrt((r1 - r2) * (r1 - r2) + (g1 - g2)
                        * (g1 - g2) + (b1 - b2) * (b1 - b2));
                dist += tempDist;
            }
        }
        return dist;
    }

    private File[] getOtherImageFiles(File reference) {
        File dir = new File(reference.getParent());

        File[] others = dir.listFiles(new JPEGImageFileFilter() {
        });
        return others;
    }

}
