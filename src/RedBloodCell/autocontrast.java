package RedBloodCell;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
public class autocontrast 
{
	static Image img,image3,img1;
	static int w,h,pixels[],hist[];
	static int a,alow,ahigh,amin,amax,k5,r5,r6,b5,b6,g5,g6,al,ah;
	static double qlow=0.0005,qhigh=0.0005;
	private final int clamp(int c)
	{
		return(c>255?255:(c<0?0:c));
	}
	
	public void recieveimage(Image temp)
	{
	img=temp;
	}
	
	public void perform()
	{	
	w=img.getWidth(null);
	h=img.getHeight(null);
	hist=new int[256];
	pixels=new int[w*h];
	alow=(int)(w*h*qlow);
	ahigh=(int)(w*h*(1-qhigh));
	//System.out.println(ahigh);
	PixelGrabber pg1 = new PixelGrabber(img, 0, 0, w, h, pixels, 0, w);
	try
	{
		pg1.grabPixels();
		
	}
	catch(InterruptedException e)
	{
		
	}
	amin=0;
	amax=255;
	for(int i=0;i<w*h;i++)
	{
		int p=pixels[i];
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
	for(int j=0;j<256;j++)
	{
		if(alow<=hist[j])
		{
          al=j;
          j=256;
		}
		
	}
	for(int j=0;j<256;j++)
	{
		if(ahigh<=hist[j])
		{
          ah=j;
          j=256;
		}
		
	}
System.out.println(al);
System.out.println(ah);
	int div=ah-al;
	for(int i=0;i<w*h;i++)
	{
		r5=0xff&(pixels[i]>>16);
		g5=0xff&(pixels[i]>>8);
		b5=0xff&(pixels[i]);
		k5=(int)(.56*g5+.33*r5+.11*b5);
		if(k5<=al)
		{
			pixels[i]=0xff000000|clamp(0)<<16|clamp(0)<<8|clamp(0);
		}
		if((k5>=al)&&(k5<=ah))		
    	{
       if(div==0)
       {
         div=1;
       }
			a=r5;
		r6= amin + ((r5-al) * ((amax-amin) /div));
		a=g5;
		g6= amin + ((g5-al) * ((amax-amin) /div));
		a=b5;
		b6= amin + ((b5-al) * ((amax-amin) / div));
		
		pixels[i]=0xff000000|clamp(r6)<<16|clamp(g6)<<8|clamp(b6);
	}
	if(k5>ahigh)
		pixels[i]=ah;
	}
	
	BufferedImage img1=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
	img1.setRGB(0,0,w,h,pixels,0,w);
	image3=Toolkit.getDefaultToolkit().createImage(img.getSource());
	
	JFrame autocont=new JFrame("Automatic contrst adjustment");
	autocont.setLayout(new BorderLayout());
	autocont.setVisible(true);
	autocont.setSize(1000,700);
	JPanel p1=new scrollit(image3);
	autocont.add(p1,BorderLayout.CENTER);
	
	}
	
}

