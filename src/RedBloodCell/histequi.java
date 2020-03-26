package RedBloodCell;
import java.awt.image.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.*;
public class histequi implements ActionListener{
JFrame parent;
static int w,h,w2,h2;
static int p,k,k1,r,g,b,r1,g1,b1;
static Image histimage,image,imager;
static int hist[]=new int[256];
static int imagepixels[],imagepixels1[];
static int resimage[],resimage1[];
static double  check=0;
static int scalingfactor=1;
static double max=0;
static int nop[]=new int[256];
int pixels[]=new int[256*256];
static int w1=256;
static int h1=256;

void getimage(Image temp){
	histimage=temp;
	w=histimage.getWidth(null);
	h=histimage.getHeight(null);
}

private final int clamp(int c)
{
	return(c>255?255:(c<0?0:c));
}

void perform(){
	imagepixels=new int[w*h];
	resimage=new int[w*h];
	for(int j=0;j<256;j++)
		hist[j]=0;
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
p=imagepixels[i];
r=0xff&(p>>16);
g=0xff&(p>>8);
b=0xff&(p);
k=(int)(0.33*r+0.56*g+0.11*b);
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
	for(int j=1;j<255;j++){
		hist[j] = hist[j-1] + hist[j];
	}
	for(int i=0;i<w*h;i++){
		
			p=imagepixels[i];
			r=0xff&(p>>16);
			g=0xff&(p>>8);
			b=0xff&(p);
			k=(int)(0.33*r+0.56*g+0.11*b);
			//k1=(int)((hist[clamp(k)]*255)/(w*h));
			r1=(int)((hist[k]*255/(w*h)));
			g1=(int)((hist[k]*255/(w*h)));
			b1=(int)((hist[k]*255/(w*h)));
			resimage[i]=0xff000000|clamp(r1)<<16|clamp(g1)<<8|clamp(b1);
	}
	BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
	img.setRGB(0,0,w,h,resimage,0,w);
	image=Toolkit.getDefaultToolkit().createImage(img.getSource());
	parent=new JFrame("Histogram Equilisation");
	JComponent newContentPane=new scrollit(image);
	newContentPane.setOpaque(true);
	JButton hist=new JButton("Get Histogram");
	hist.addActionListener(this);
	JButton cumu=new  JButton("Get Cumulative Histogram");
	cumu.addActionListener(this);
	parent.add(newContentPane,BorderLayout.CENTER);
	JPanel jp=new JPanel();
	jp.setLayout(new GridLayout(1,2));
	jp.add(hist);
	jp.add(cumu);
	parent.add(jp,BorderLayout.SOUTH);
	parent.setSize(1000, 750);
	parent.setVisible(true);
	/*histogram his=new histogram();
	his.getimage(image);
	cumulativehist cum=new cumulativehist();
	cum.getimage(image);*/
}
@Override
public void actionPerformed(ActionEvent e) {
	JButton a=(JButton)(e.getSource());
	String b=a.getText();
	if(b.equals("Get Histogram")){
		drawhist();
	}
	else if(b.equals("Get Cumulative Histogram")){
		drawhist1();
	}
}
void drawhist()
{	for(int j=0;j<256;j++)
	hist[j]=0;
	imagepixels=new int[w*h];
	PixelGrabber pg=new PixelGrabber(image,0,0,w,h,imagepixels,0,w);
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
	 imager=Toolkit.getDefaultToolkit().createImage(img.getSource());
	 JFrame parent=new JFrame("Histogram");
	 JComponent newContentPane=new scrollit(imager);
	 newContentPane.setOpaque(true);
	 JPanel panel1=new JPanel();
	 panel1.setLayout(new BorderLayout());
	 panel1.add(newContentPane,BorderLayout.CENTER);
	 parent.add(panel1,BorderLayout.CENTER);
	 parent.add(panel1,BorderLayout.CENTER);
	 parent.setSize(285,295);
		parent.setVisible(true);

//System.out.println("ok");
}
void drawhist1()
{
	imagepixels=new int[w*h];
	for(int j=0;j<256;j++)
	hist[j]=0;
	
	PixelGrabber pg=new PixelGrabber(image,0,0,w,h,imagepixels,0,w);
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
}
for(int i=1;i<256;i++)
{
	hist[i]=hist[i-1]+hist[i];
} 

for(int i=0;i<256;i++)
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
	 imager=Toolkit.getDefaultToolkit().createImage(img.getSource());
	 JFrame parent=new JFrame("Histogram");
	 JComponent newContentPane=new scrollit(imager);
	 newContentPane.setOpaque(true);
	 parent.add(newContentPane,BorderLayout.CENTER);
	 parent.setSize(285,295);
		parent.setVisible(true);

//System.out.println("ok");
}
}
