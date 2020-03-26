package RedBloodCell;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
public class histogram implements ActionListener {
static Image histimage,image,imager;
static int w,h;
static int hist[]=new int[256];
static int imagepixels[];
static double  check=0;
static int scalingfactor=1;
static double max=0;
static int nop[]=new int[256];
int pixels[]=new int[256*256];
static int w1=256;
static int h1=256;
void getimage(Image m)
{
	histimage=m;
	w=histimage.getWidth(null);
	h=histimage.getHeight(null);
	//redhist red=new redhist();
	//red.getimage(histimage);
	System.out.println(w);
	System.out.println(h);
}

void rgbimage(int rh[],Color c1)
{
	 double  check1=0;
	 int scalingfactor1=1;
	 double max1=0;
	 int nop1[]=new int[256];
	int pixels1[]=new int[256*256];
	 int w1=256;
	 int h1=256;
	for(int i=0;i<256;i++)
{
if(max1>rh[i])
{
	
}
else
{
max1=rh[i];	
}
}
check1=max1/256;
if(check1>1)
{
	scalingfactor1=(int)max1/256+1;
}
for(int i=0;i<256;i++)
{
	nop1[i]=rh[i]/(int)scalingfactor1;
}
for(int i=0;i<256*256;i++)
{
pixels1[i]=Color.WHITE.getRGB();
}
	for(int j=0;j<256;j++)
	{
		pixels1[j*256]=c1.getRGB();
		pixels1[j+255*256]=c1.getRGB();
	}
	for(int i=0;i<256;i++)
	{
		pixels1[i+(255-nop1[i])*256]=c1.getRGB();
		for(int j=255;j>=(255-nop1[i]);j--)
		{
			pixels1[i+j*256]=c1.getRGB();
		}
	}
	BufferedImage img=new BufferedImage(w1,h1,BufferedImage.TYPE_INT_RGB);
	img.setRGB(0,0,w1,h1,pixels1,0,w1);
	 image=Toolkit.getDefaultToolkit().createImage(img.getSource());
	 JFrame parent=new JFrame("Histogram");
	 parent.setLayout(new BorderLayout());
	 JComponent newContentPane=new scrollit(image);
	 newContentPane.setOpaque(true);
	 JPanel panel1=new JPanel();
	 panel1.setLayout(new BorderLayout());
	 panel1.add(newContentPane,BorderLayout.CENTER);
	 parent.add(panel1,BorderLayout.CENTER);
	 parent.add(panel1,BorderLayout.CENTER);
	 parent.setSize(500,500);
		parent.setVisible(true);
		JButton save=new JButton("save");
		save.addActionListener(this);
		save.setBounds(500, 500, 20, 20);
		parent.add(save,BorderLayout.SOUTH);
//System.out.println(redc);	

}
void drawhist()
{
		imagepixels=new int[w*h];
	PixelGrabber pg=new PixelGrabber(histimage,0,0,w,h,imagepixels,0,w);
try
{
	pg.grabPixels();
	
}
catch(InterruptedException e)
{
	
}
for(int i=0;i<w*h;i++)
{
int p=imagepixels[i];
int r=0xff&(p>>16);
int g=0xff&(p>>8);
int b=0xff&(p);
int k=(int)(0.33*r+0.56*g+0.11*b);
hist[k]++;
}for(int i=0;i<256;i++)
{
if(max>hist[i])
{
	
}
else
{
max=hist[i];	
}
}
check=max/256;
if(check>1)
{
	scalingfactor=(int)max/256+1;
}
for(int i=0;i<256;i++)
{
	nop[i]=hist[i]/(int)scalingfactor;
}
for(int i=0;i<256*256;i++)
{
pixels[i]=Color.WHITE.getRGB();
}
	for(int j=0;j<256;j++)
	{
		pixels[j*256]=Color.BLACK.getRGB();
		pixels[j+255*256]=Color.BLACK.getRGB();
	}
	for(int i=0;i<256;i++)
	{
		pixels[i+(255-nop[i])*256]=Color.BLACK.getRGB();
		for(int j=255;j>=(255-nop[i]);j--)
		{
			pixels[i+j*256]=Color.BLACK.getRGB();
		}
	}
	
	BufferedImage img=new BufferedImage(w1,h1,BufferedImage.TYPE_INT_RGB);
	img.setRGB(0,0,w1,h1,pixels,0,w1);
	 image=Toolkit.getDefaultToolkit().createImage(img.getSource());
	 JFrame parent=new JFrame("Histogram");
	 JComponent newContentPane=new scrollit(image);
	 newContentPane.setOpaque(true);
	 JPanel panel1=new JPanel();
	 panel1.setLayout(new BorderLayout());
	 panel1.add(newContentPane,BorderLayout.CENTER);
	 parent.add(panel1,BorderLayout.CENTER);
	 parent.add(panel1,BorderLayout.CENTER);
	 parent.setSize(500,500);
		parent.setVisible(true);
		JButton save=new JButton("save");
		save.addActionListener(this);
		save.setBounds(500, 500, 20, 20);
		parent.add(save,BorderLayout.SOUTH);

//System.out.println("ok");
}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	save s=new save();
	s.histsave(image);
	
}
}
