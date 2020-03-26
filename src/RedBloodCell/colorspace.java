package RedBloodCell;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
public class colorspace implements ActionListener
{
	static Image img,image3;
	static int w,h,pixels[];	
	public void recieveimage(Image temp)
	{
	img=temp;
	}

	private final int clamp(int c)
	{
		return(c>255?255:(c<0?0:c));
	}
	
	public void perform2()
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
		
		 int r=00000000&(pixels[i]>>16);
		int g=00000000&(pixels[i]>>8);
		int b=0xff&(pixels[i]);
		
		pixels[i]=0xff000000|clamp(r)<<16|clamp(g)<<8|clamp(b);
	
	}
	
	
	
	BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
	img.setRGB(0,0,w,h,pixels,0,w);
	image3=Toolkit.getDefaultToolkit().createImage(img.getSource());
	
	JFrame autocont=new JFrame("Blue Scale");
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

	public void perform1()
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
		
		int r=00000000&(pixels[i]>>16);
		int g=0xff&(pixels[i]>>8);
	int	b=00000000&(pixels[i]);
		
		pixels[i]=0xff000000|clamp(r)<<16|clamp(g)<<8|clamp(b);
	
	}
	
	
	
	BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
	img.setRGB(0,0,w,h,pixels,0,w);
	image3=Toolkit.getDefaultToolkit().createImage(img.getSource());
	
	JFrame autocont=new JFrame("Green scale");
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
		
		int r=0xff&(pixels[i]>>16);
		int g=00000000&(pixels[i]>>8);
		int b=00000000&(pixels[i]);
		
		pixels[i]=0xff000000|clamp(r)<<16|clamp(g)<<8|clamp(b);
	
	}
	BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
	img.setRGB(0,0,w,h,pixels,0,w);
	image3=Toolkit.getDefaultToolkit().createImage(img.getSource());
	JFrame autocont=new JFrame("Red Scale");
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
