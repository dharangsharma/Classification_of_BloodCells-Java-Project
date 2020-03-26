package RedBloodCell;
import java.awt.Image;
import java.awt.image.*;

import javax.swing.JFrame;


public class contrast extends RGBImageFilter implements Pluginfilter  {

	public static Image g;
	public void filter(JFrame f, Image in) {
		JFrame res=new JFrame("Image Processor");	
		
		g=f.createImage((new FilteredImageSource(in.getSource(),this)));
			

		
			f.dispose();
		Finterface fi=new Finterface(res,g);
		

	}
	private int multclamp(int in,double factor)
	{
		in=(int)(in*factor);
		return in>255?255:in; 
	}
	double gain=1.2;
	private int cont(int in)
	{
		return(in<128)?(int)(in/gain):multclamp(in,gain);
	}
	@Override
	public int filterRGB(int x, int y, int rgb) {
		
		int r=cont((rgb>>16)&0xff);
		int g=cont((rgb>>8)&0xff);
		int b=cont((rgb&0xff));
		return(0xff000000|r<<16|g<<8|b);
	}

}
