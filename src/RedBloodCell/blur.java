package RedBloodCell;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class blur implements Pluginfilter {
 
	
	public void filter(JFrame f, Image in) {
		float[] weight=new float[25];
		
		
		 in=new ImageIcon(in).getImage();
		 BufferedImage s=new BufferedImage(in.getWidth(null),in.getHeight(null),BufferedImage.TYPE_INT_RGB);
		BufferedImage  d=new BufferedImage(in.getWidth(null),in.getHeight(null),BufferedImage.TYPE_INT_RGB);
		 JFrame res=new JFrame("Image Processor");	
		Graphics rg=s.createGraphics();
		rg.drawImage(in,0,0,null);
		rg.dispose();
	
		
		 float x= 1.0f/25.0f;
		
		for(int i=0;i<25;i++)
		{
			weight[i]=x;
		}
		 Kernel mykernel=new Kernel(5,5,weight);
ConvolveOp blurimage=new ConvolveOp(mykernel);
d=blurimage.filter(s, d);

Image g=Toolkit.getDefaultToolkit().createImage(d.getSource());

f.dispose();
Finterface fi=new Finterface(res,g);
	
	}
	public static void wait(int n)
	{
		long t0,t1;
		t0=System.currentTimeMillis();
		{
			do
			{
				t1=System.currentTimeMillis();
			}
			while(t1-t0<n);
		}
	}

}
