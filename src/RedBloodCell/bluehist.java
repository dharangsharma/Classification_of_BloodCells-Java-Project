package RedBloodCell;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.*;
public class bluehist {
	static int imagepixels[],bluepixels[];
	static Image blue,bluehi;
	static int w,h;
	 void getimage(Image r)
	 {
		 blue=r;
		 w=blue.getWidth(null);
			h=blue.getHeight(null);
	 }
void sendimage()
{
	imagepixels=new int[w*h];
	bluepixels=new int[w*h];
	PixelGrabber pg=new PixelGrabber(blue,0,0,w,h,imagepixels,0,w);
try
{
	pg.grabPixels();
	
}
catch(InterruptedException e)
{
	
}
int blueh[]=new int[256];
for(int i=0;i<w*h;i++)
{
int p=imagepixels[i];
int b=0xff&(p);
blueh[b]++;
}
histogram h=new histogram();
Color c1=new Color(0,0,255);
h.rgbimage(blueh,c1);

}
}

