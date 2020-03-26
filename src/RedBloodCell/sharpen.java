package RedBloodCell;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
public class sharpen implements Pluginfilter {
static int spixels[],dpixels[];
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
		int r0=0,g0=0,b0=0;
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
				int rs=0;
				int gs=0;
				int bs=0;
				for(int k=-1;k<=1;k++)
				{
					for(int j=-1;j<=1;j++)
					{
						int rgb=spixels[(y+k)*w+x+j];
						int r=(rgb>>16)&0xff;
						int g=(rgb>>8)&0xff;
						int b=rgb&0xff;
						if(j==0&k==0)
						{
							r0=r;
							g0=g;
							b0=b;
						}
						else
						{
							rs=rs+r;
							gs=gs+g;
							bs=bs+b;
						}
					}
				}
				rs=rs/8;
				gs=gs/8;
				bs=bs/8;
				dpixels[y*w+x]=(0xff000000)|clamp(r0+r0-rs)<<16|clamp(g0+g0-gs)<<8|clamp(b0+b0-bs);
			}
		}
		BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		img.setRGB(0,0,w,h,dpixels,0,w);
		Image image=Toolkit.getDefaultToolkit().createImage(img.getSource());

    JFrame res=new JFrame("Image Processor");	
		 f.dispose();
		 Finterface fi=new Finterface(res,image);
	}

}
