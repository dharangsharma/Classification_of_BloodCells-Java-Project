package RedBloodCell;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
public class medianfilter implements Pluginfilter {
static int spixels[],dpixels[];
static int median[]=new int[9];
static int sortmedian[]=new int[9];
static int red[]=new int[9];
static int green[]=new int[9];
static int blue[]=new int[9];
private final int clamp(int c)
{
	return(c>255?255:(c<0?0:c));
}
	public void filter(JFrame f, Image in) {
		int h=in.getHeight(null);
		int w=in.getWidth(null);
		System.out.println(w+" "+h);
		spixels=new int[w*h];
		dpixels=new int[w*h];
		 
		PixelGrabber pg=new PixelGrabber(in,0,0,w,h,spixels,0,w);
		try
		{
			pg.grabPixels();
		}
		catch(InterruptedException e)
		{
			
		}
		for(int y=1;y<h-1;y++)
		{
			for(int x=1;x<w-1;x++)
			{
				int pixelr=0;
				int pixelg=0;
				int pixelb=0;
                int s=0;
				for(int k=-1;k<=1;k++)
				{
					for(int j=-1;j<=1;j++)
					{
						int rgb=spixels[(y+k)*w+x+j];
						int r=(rgb>>16)&0xff;
						int g=(rgb>>8)&0xff;
						int b=rgb&0xff;
						int intensity=(int)(0.56*g+0.11*b+0.33*r);
						median[s]=intensity;
				red[s]=r;
				green[s]=g;
				blue[s]=b;
						s++;
					}
				}
				sortmedian=sortedarray(median);
				for(int c=0;c<9;c++) 
				{
					if(sortmedian[4]==median[c])
					{
						pixelr=red[c];
						pixelg=green[c];
						pixelb=blue[c];
					}
				}
										dpixels[y*w+x]=(0xff000000)|clamp(pixelr)<<16|clamp(pixelg)<<8|clamp(pixelb);
			}
		}
		BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		img.setRGB(0,0,w,h,dpixels,0,w);
		Image image=Toolkit.getDefaultToolkit().createImage(img.getSource());

    JFrame res=new JFrame("Image Processor");	
		 f.dispose();
		 Finterface fi=new Finterface(res,image);
	}
int[] sortedarray(int a[])
{
	int k=0;
	for(int j=0;j<a.length;j++)
	{
		int v=a[j];
	
	for(int i=j;i<a.length;i++)
	{
		if(v<a[i])
		{
			v=a[i];
			k=i;
		}
	}
	a[j]=a[k];
	a[k]=a[j];
	//System.out.println(a[j]);
	}
	return a;
}
}