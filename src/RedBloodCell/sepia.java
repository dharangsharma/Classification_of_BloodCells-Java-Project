package RedBloodCell;
import java.awt.Image;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

import javax.swing.JFrame;


public class sepia extends RGBImageFilter implements Pluginfilter {

	public static Image g;
	public int filterRGB(int x, int y, int rgb) {
		int r=(rgb>>16)&0xff;
		int g=(rgb>>8)&0xff;
		int b=rgb&0xff;
		int r1=(int)(r*0.393+g*0.769+b*0.189);
		int g1=(int)(r*0.349+g*0.686+b*0.168);
		int b1=(int)(r*0.272+g*0.534+b*0.131);
		if(r1>255)
		{
			r1=255;
		}
		 if(g1>255)
		{
			g1=255;
		}
		if(r1>255)
		{
			r1=255;
		}
		
		 return (0xff000000|r1<<16|g1<<8|b1);
	}

	@Override
	public void filter(JFrame f, Image in) {
		JFrame res=new JFrame("Image Processor");	
		
		g=f.createImage((new FilteredImageSource(in.getSource(),this)));
			

		
			f.dispose();
		Finterface fi=new Finterface(res,g);

	}

}
