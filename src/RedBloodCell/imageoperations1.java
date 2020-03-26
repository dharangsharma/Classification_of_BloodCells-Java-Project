package RedBloodCell;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.image.*;
import java.awt.image.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.*;
import java.lang.Math;
public class imageoperations1 extends JFrame implements ActionListener 
{
	static Image image1=null,image2=null,image3=null;
	static JMenuItem menuitem;
	JFrame imageoperations=new JFrame("Image Operations");
	static int w,h,w1,h1,w2,h2;
	static int pixels1[],pixels2[],pixels[];
	static PixelGrabber pg1,pg2;
	private final int clamp(int c)
	{
		return(c>255?255:(c<0?0:c));
	}
	public void imageoperations()
	{
		
		
		imageoperations.setSize(900,600);
		imageoperations.setVisible(true);
		JComponent newContentPane=new scrollit(image3);
		newContentPane.setBorder(new TitledBorder("new Image"));
		newContentPane.setOpaque(true);
		imageoperations.add(newContentPane,BorderLayout.CENTER);
		JPanel p2= new JPanel();
		p2.setBorder(new TitledBorder("button"));
		JButton button = new JButton("save the new image");
		button.addActionListener(this);
		p2.add(button,BorderLayout.SOUTH);
		imageoperations.add(p2,BorderLayout.SOUTH);
		
	}
	public void imageop(JFrame temp)
	{
	
		
		temp.setSize(900,600);
		temp.setVisible(true);
		JComponent newContentPane=new scrollit(image3);
		newContentPane.setBorder(new TitledBorder("new Image"));
		newContentPane.setOpaque(true);
		temp.add(newContentPane,BorderLayout.CENTER);
		JPanel p2= new JPanel();
		p2.setBorder(new TitledBorder("button"));
		JButton button = new JButton("save the new image");
		button.addActionListener(this);
		p2.add(button,BorderLayout.SOUTH);
		temp.add(p2,BorderLayout.SOUTH);
		
	}
	public void operate()
	{
		
		w1=image1.getWidth(null);
		h1=image1.getHeight(null);
		w2=image2.getWidth(null);
		h2=image2.getHeight(null);
		 pixels1=new int[w1*h1];
		 pixels2=new int [w2*h2];
		pg1 = new PixelGrabber(image1, 0, 0, w1, h1, pixels1, 0, w1);
		pg2 = new PixelGrabber(image2, 0, 0, w2, h2, pixels2, 0, w2);
		try
		{
			pg1.grabPixels();
			pg2.grabPixels();
		}
		catch(InterruptedException e)
		{
			
		}
		if(w1>w2)
		{
		w=w1;
		}else
		{
			w=w2;
		}
		if(h1>h2)
		{
			h=h1;
		}else
		{
			h=h2;
		}
		pixels=new int[w*h];
	
		
	}
	public void add()
	{int x,y;
		x=Math.min(w1*h1,w2*h2);
		y=Math.max(w1*h1,w2*h2);
		for(int i=0;i<x;i++ )
		{
				int p=pixels1[i];
				int r=0xff&(p>>16);
				int g=0xff&(p>>8);
				int b=0xff&(p);
				
				int p1=pixels2[i];
				int r1=0xff&(p1>>16);
				int g1=0xff&(p1>>8);
				int b1=0xff&(p1);
				
				int r2=(int)(r+r1);
				int g2=(int)(g+g1);
				int b2=(int)(b+b1);
				
				pixels[i]=0xff000000|clamp(r2)<<16|clamp(g2)<<8|clamp(b2);
				/*if(pixels[i]>255)
				{
					pixels[i]=255;
				}
				if(pixels[i]<0)
				{
					pixels[i]=0;
				}*/
			}
		
		for(int i=x;i<y;i++)
		{
			if(pixels1.length>pixels2.length)
			{
				
				pixels[i]=pixels1[i];
			}
			else
				pixels[i]=pixels2[i];
		}
		BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		img.setRGB(0,0,w,h,pixels,0,w);
		image3=Toolkit.getDefaultToolkit().createImage(img.getSource());
		imageoperations.dispose();
		JFrame temp=new JFrame(); 
		imageop(temp);
		
	}
	public void subtract()
	{
		int x,y;
		x=Math.min(w1*h1,w2*h2);
		y=Math.max(w1*h1,w2*h2);
		for(int i=0;i<x;i++ )
		{
				int p=pixels1[i];
				int r=0xff&(p>>16);
				int g=0xff&(p>>8);
				int b=0xff&(p);
				int p1=pixels2[i];
				int r1=0xff&(p1>>16);
				int g1=0xff&(p1>>8);
				int b1=0xff&(p1);
				
				int r2=(int)(r-r1);
				int g2=(int)(g-g1);
				int b2=(int)(b-b1);
				
				pixels[i]=0xff000000|clamp(r2)<<16|clamp(g2)<<8|clamp(b2);
				/*if(pixels[i]>255)
				{
					pixels[i]=255;
				}
				if(pixels[i]<0)
				{
					pixels[i]=0;
				}*/
			}
		
		for(int i=x;i<y;i++)
		{
			if(pixels1.length>pixels2.length)
			{
				
				pixels[i]=pixels1[i];
			}
			else
				pixels[i]=pixels2[i];
		}
		BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		img.setRGB(0,0,w,h,pixels,0,w);
		image3=Toolkit.getDefaultToolkit().createImage(img.getSource());
		imageoperations.dispose();
		JFrame temp=new JFrame(); 
		imageop(temp);
	}
	public void average()
	{
		int x,y;
		x=Math.min(w1*h1,w2*h2);
		y=Math.max(w1*h1,w2*h2);
		for(int i=0;i<x;i++ )
		{
				int p=pixels1[i];
				int r=0xff&(p>>16);
				int g=0xff&(p>>8);
				int b=0xff&(p);
				int p1=pixels2[i];
				int r1=0xff&(p1>>16);
				int g1=0xff&(p1>>8);
				int b1=0xff&(p1);
				
				int r2=(int)(r+r1)/2;
				int g2=(int)(g+g1)/2;
				int b2=(int)(b+b1)/2;
				
				pixels[i]=0xff000000|clamp(r2)<<16|clamp(g2)<<8|clamp(b2);
				/*if(pixels[i]>255)
				{
					pixels[i]=255;
				}
				if(pixels[i]<0)
				{
					pixels[i]=0;
				}*/
			}
		
		for(int i=x;i<y;i++)
		{
			if(pixels1.length>pixels2.length)
			{
				
				pixels[i]=pixels1[i];
			}
			else
				pixels[i]=pixels2[i];
		}
		BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		img.setRGB(0,0,w,h,pixels,0,w);
		image3=Toolkit.getDefaultToolkit().createImage(img.getSource());
		imageoperations.dispose();
		JFrame temp=new JFrame(); 
		imageop(temp);
	}
	public void max()
	{
		int x,y;
		x=Math.min(w1*h1,w2*h2);
		y=Math.max(w1*h1,w2*h2);
		for(int i=0;i<x;i++ )
		{
				int p=pixels1[i];
				int r=0xff&(p>>16);
				int g=0xff&(p>>8);
				int b=0xff&(p);
				
				int p1=pixels2[i];
				int r1=0xff&(p1>>16);
				int g1=0xff&(p1>>8);
				int b1=0xff&(p1);
				
				int r2=(int)Math.max(r,r1);
				int g2=(int)Math.max(g,g1);
				int b2=(int)Math.max(b,b1);
				
				pixels[i]=0xff000000|clamp(r2)<<16|clamp(g2)<<8|clamp(b2);
				/*if(pixels[i]>255)
				{
					pixels[i]=255;
				}
				if(pixels[i]<0)
				{
					pixels[i]=0;
				}*/
			}
		
		for(int i=x;i<y;i++)
		{
			if(pixels1.length>pixels2.length)
			{
				
				pixels[i]=pixels1[i];
			}
			else
				pixels[i]=pixels2[i];
		}
		BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		img.setRGB(0,0,w,h,pixels,0,w);
		image3=Toolkit.getDefaultToolkit().createImage(img.getSource());
		imageoperations.dispose();
		JFrame temp=new JFrame(); 
		imageop(temp);
	}
	public void min()
	{
		int x,y;
		x=Math.min(w1*h1,w2*h2);
		y=Math.max(w1*h1,w2*h2);
		for(int i=0;i<x;i++ )
		{
				int p=pixels1[i];
				int r=0xff&(p>>16);
				int g=0xff&(p>>8);
				int b=0xff&(p);
				
				int p1=pixels2[i];
				int r1=0xff&(p1>>16);
				int g1=0xff&(p1>>8);
				int b1=0xff&(p1);
				
				int r2=(int)Math.min(r,r1);
				int g2=(int)Math.min(g,g1);
				int b2=(int)Math.min(b,b1);
				
				pixels[i]=0xff000000|clamp(r2)<<16|clamp(g2)<<8|clamp(b2);
				/*if(pixels[i]>255)
				{
					pixels[i]=255;
				}
				if(pixels[i]<0)
				{
					pixels[i]=0;
				}*/
			}
		
		for(int i=x;i<y;i++)
		{
			if(pixels1.length>pixels2.length)
			{
				
				pixels[i]=pixels1[i];
			}
			else
				pixels[i]=pixels2[i];
		}
		BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		img.setRGB(0,0,w,h,pixels,0,w);
		image3=Toolkit.getDefaultToolkit().createImage(img.getSource());
		imageoperations.dispose();
		JFrame temp=new JFrame(); 
		imageop(temp);
	}
	public void divide()
	{
		int x,y;
		x=Math.min((w1*h1),(w2*h2));
		y=Math.max(w1*h1,w2*h2);
		for(int i=0;i<x-1;i++)
		{
				
				int p=pixels1[i];
				int r=0xff&(p>>16);
				int g=0xff&(p>>8);
				int b=0xff&(p);
				
				int p1=pixels2[i];
				int r1=0xff&(p1>>16);
				int g1=0xff&(p1>>8);
				int b1=0xff&(p1);
				
				if(r1==0){r1=1;}
				if(g1==0){g1=1;}
				if(b1==0){b1=1;}
				int r2=(int)(r/r1);
				int g2=(int)(g/g1);
				int b2=(int)(b/b1);
				
				pixels[i]=0xff000000|clamp(r2)<<16|clamp(g2)<<8|clamp(b2);
				
				/*if(pixels[i]>255)
				{
					pixels[i]=255;
				}
				if(pixels[i]<0)
				{
					pixels[i]=0;
				}*/
			}
		
		for(int i=x;i<y;i++)
		{
			if(pixels1.length>pixels2.length)
			{
				
				pixels[i]=pixels1[i];
			}
			else
				pixels[i]=pixels2[i];
		}
		BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		img.setRGB(0,0,w,h,pixels,0,w);
		image3=Toolkit.getDefaultToolkit().createImage(img.getSource());
		imageoperations.dispose();
		JFrame temp=new JFrame(); 
		imageop(temp);
	}
	public void multiply()
	{
		int x,y;
		x=Math.min(w1*h1,w2*h2);
		y=Math.max(w1*h1,w2*h2);
		for(int i=0;i<x;i++ )
		{
				int p=pixels1[i];
				int r=0xff&(p>>16);
				int g=0xff&(p>>8);
				int b=0xff&(p);

				int p1=pixels2[i];
				int r1=0xff&(p1>>16);
				int g1=0xff&(p1>>8);
				int b1=0xff&(p1);
				
				int r2=(int)(r*r1);
				int g2=(int)(g*g1);
				int b2=(int)(b*b1);
				
				pixels[i]=0xff000000|clamp(r2)<<16|clamp(g2)<<8|clamp(b2);
				/*if(pixels[i]>255)
				{
					pixels[i]=255;
				}
				if(pixels[i]<0)
				{
					pixels[i]=0;
				}*/
			}
		
		for(int i=x;i<y;i++)
		{
			if(pixels1.length>pixels2.length)
			{
				
				pixels[i]=pixels1[i];
			}
			else
				pixels[i]=pixels2[i];
		}
		BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		img.setRGB(0,0,w,h,pixels,0,w);
		image3=Toolkit.getDefaultToolkit().createImage(img.getSource());
		imageoperations.dispose();
		JFrame temp=new JFrame(); 
		imageop(temp);
	}
	public void alphabending(float value2)
	{
		
		if(value2>=0&&value2<=1)
		{
		int x,y;
		x=Math.min(w1*h1,w2*h2);
		y=Math.max(w1*h1,w2*h2);
		for(int i=0;i<x;i++ )
		{
				int p=pixels1[i];
				int r=0xff&(p>>16);
				int g=0xff&(p>>8);
				int b=0xff&(p);
				
				int p1=pixels2[i];
				int r1=0xff&(p1>>16);
				int g1=0xff&(p1>>8);
				int b1=0xff&(p1);
				
				
				int r2=(int)((value2*r)+((1-value2)*r1));
				int g2=(int)((value2*g)+((1-value2)*g1));
				int b2=(int)((value2*b)+((1-value2)*b1));
				
				pixels[i]=0xff000000|clamp(r2)<<16|clamp(g2)<<8|clamp(b2);
				/*if(pixels[i]>255)
				{
					pixels[i]=255;
				}
				if(pixels[i]<0)
				{
					pixels[i]=0;
				}*/
			}
		
		for(int i=x;i<y;i++)
		{
			if(pixels1.length>pixels2.length)
			{
				
				pixels[i]=pixels1[i];
			}
			else
				pixels[i]=pixels2[i];
		}
		BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		img.setRGB(0,0,w,h,pixels,0,w);
		image3=Toolkit.getDefaultToolkit().createImage(img.getSource());
		imageoperations.dispose();
		JFrame temp=new JFrame(); 
		imageop(temp);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "enter a valid value");
			imageoperations.dispose();
		}
		
	}
	public void recieveimage(Image temp1,Image temp2)
	{
		image1=temp1;
		image2=temp2;
		operate();
	}
	public void actionPerformed(ActionEvent e) 
	{
		save s=new save();
		s.imageoperate(image3);
	}
}

