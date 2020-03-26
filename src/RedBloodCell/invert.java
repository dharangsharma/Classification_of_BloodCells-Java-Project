package RedBloodCell;
import java.awt.Image;
import java.awt.image.*;

import javax.swing.JFrame;


public class invert extends RGBImageFilter implements Pluginfilter{
	public static Image g;
	
	

	
	public int filterRGB(int x, int y, int rgb) {
		int r=0xff-(rgb>>16)&0xff;
		int g=0xff-(rgb>>8)&0xff;
		int b=0xff-(rgb)&0xff;
		
		return (0xff000000|r<<16|g<<8|b);
	}
public void filter(JFrame f, Image in) {
		
		JFrame res=new JFrame("Image Processor");	
		
		g=f.createImage((new FilteredImageSource(in.getSource(),this)));
			

		
			f.dispose();
		Finterface fi=new Finterface(res,g);
		
	}

}
