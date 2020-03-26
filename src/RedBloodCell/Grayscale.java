package RedBloodCell;
import java.awt.Image;
import java.awt.image.*;
import javax.swing.JFrame;

public class Grayscale extends RGBImageFilter implements Pluginfilter {

	public static Image g;
	static int c=1;
	public int filterRGB(int x, int y, int rgb) {
		int r=(rgb>>16)&0xff;
		int g=(rgb>>8)&0xff;
		int b=rgb&0xff;
		int k=(int)(.56*g+.33*r+.11*b);
		//if(k>255)
		//{
			//c=2;
		//}
		return (0xff000000|k<<16|k<<8|k);
	}

	
	public  void filter(JFrame f, Image in) {
	JFrame res=new JFrame("Image Processor");	
		
	g=f.createImage((new FilteredImageSource(in.getSource(),this)));
		//System.out.println(c);

	
		f.dispose();
	Finterface fi=new Finterface(res,g);
	
}
}