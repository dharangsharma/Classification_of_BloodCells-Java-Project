package RedBloodCell;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.*;
public class redhist {
	static int imagepixels[],redpixels[];
	static Image red,redhi;
	static int w,h;
	 void getimage(Image r)
	 {
		 red=r;
		 w=red.getWidth(null);
		h=red.getHeight(null);
	 }
void sendimage()
{
	imagepixels=new int[w*h];
	redpixels=new int[w*h];
	PixelGrabber pg=new PixelGrabber(red,0,0,w,h,imagepixels,0,w);
try
{
	pg.grabPixels();
	
}
catch(InterruptedException e)
{
	
}
int redh[]=new int[256];
for(int i=0;i<w*h;i++)
{
int p=imagepixels[i];
int r=0xff&(p>>16);
redh[r]++;
}
histogram h=new histogram();
Color c1=new Color(255,0,0);
h.rgbimage(redh,c1);

}
}
