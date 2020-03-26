package RedBloodCell;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.*;
public class greenhist {
	static int imagepixels[],greenpixels[];
	static Image green,greenhi;
	static int w,h;
	 void getimage(Image r)
	 {
		 green=r;
		 w=green.getWidth(null);
			h=green.getHeight(null);
	 }
void sendimage()
{
	imagepixels=new int[w*h];
	greenpixels=new int[w*h];
	PixelGrabber pg=new PixelGrabber(green,0,0,w,h,imagepixels,0,w);
try
{
	pg.grabPixels();
	
}
catch(InterruptedException e)
{
	
}
int greenh[]=new int[256];
for(int i=0;i<w*h;i++)
{
int p=imagepixels[i];
int g=0xff&(p>>8);
greenh[g]++;
}
histogram h=new histogram();
Color c1=new Color(0,255,0);

h.rgbimage(greenh,c1);

}
}
