package RedBloodCell;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;



public class gammacorrection implements ActionListener
{
	static Image img,image3;
	static int w,h,pixels[],low,high,min,max,r,g,b,k1,k2,k3,r1,g1,b1;
	static String value;
	static double value1;
	static float value2;
	static int value3,value4,value5;
	static int Max=255;
	public void recieveimage(Image temp)
	{
	img=temp;
	}
	public void getvalue()
	{
		 value=JOptionPane.showInputDialog(null,"Enter the value of gamma ");
		value1=Double.parseDouble(value);
		value2=(float)(value1);
	}
	private final int clamp(int c)
	{
		return(c>255?255:(c<0?0:c));
	}
	public void perform()
	{
	
	w=img.getWidth(null);
	h=img.getHeight(null);
	pixels=new int[w*h];
	PixelGrabber pg1 = new PixelGrabber(img, 0, 0, w, h, pixels, 0, w);
	try
	{
		pg1.grabPixels();
		
	}
	catch(InterruptedException e)
	{
		
	}
	
	for(int i=0;i<w*h;i++)
	{
		
		r=0xff&(pixels[i]>>16);
		g=0xff&(pixels[i]>>8);
		b=0xff&(pixels[i]);
		
		
		double a1=(double) r / Max;
		double a2=(double) g / Max;
		double a3=(double) b / Max;
		
		double b1= Math.pow(a1,value2);
		double b2= Math.pow(a2,value2);
		double b3= Math.pow(a3,value2);
		
		value3 = (int) Math.round(b1 * Max);
		value4 = (int) Math.round(b2 * Max);
		value5 = (int) Math.round(b3 * Max);
		pixels[i]=0xff000000|clamp(value3)<<16|clamp(value4)<<8|clamp(value5);
	
	}
	
	
	
	BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
	img.setRGB(0,0,w,h,pixels,0,w);
	image3=Toolkit.getDefaultToolkit().createImage(img.getSource());
	
	JFrame autocont=new JFrame("gamma correction");
	autocont.setLayout(new BorderLayout());
	autocont.setVisible(true);
	autocont.setSize(1000,700);
	JPanel p1=new scrollit(image3);
	autocont.add(p1,BorderLayout.CENTER);
	JButton button1=new JButton("save image");
	button1.addActionListener(this);
	autocont.add(button1,BorderLayout.SOUTH);
	
	save s=new save();
	s.recieve(image3, autocont);
	}

	public void actionPerformed(ActionEvent e) 
	{
		save s=new save();
		s.imageoperate(image3);
	}
}
