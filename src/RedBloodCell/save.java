package RedBloodCell;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class save {
static Image rec,histimage,temp;
static JFrame d;
public  final void recieve(Image any,JFrame win)
{
rec=any;
d=win;
}
public void histsave(Image r)
{
	histimage=r;
	histimage=new ImageIcon(histimage).getImage();
	BufferedImage s=new BufferedImage(histimage.getWidth(null),histimage.getHeight(null),BufferedImage.TYPE_INT_RGB);
	
	Graphics rg=s.createGraphics();
	rg.drawImage(histimage,0,0,null);
	rg.dispose();
		String format="JPG";
	String name="my image";
	File savefile=new File(name+"."+format);
	JFileChooser ch=new JFileChooser();
	ch.setSelectedFile(savefile);
	 int option=ch.showSaveDialog(d);
	
		if(option==JFileChooser.APPROVE_OPTION)
		{
			savefile=ch.getSelectedFile();
		
		
				try
	{
		ImageIO.write(s, format, savefile);
	
	}
	catch(IOException e)
	{
		System.out.println(e);
		JOptionPane.showMessageDialog(null, "unable to write");
	}
	
	}
			

}
public void imageoperate(Image r)
{	

	temp=r;
	temp=new ImageIcon(temp).getImage();
	BufferedImage s=new BufferedImage(temp.getWidth(null),temp.getHeight(null),BufferedImage.TYPE_INT_RGB);
	
	Graphics rg=s.createGraphics();
	rg.drawImage(temp,0,0,null);
	rg.dispose();
		String format="JPG";
	String name="my image";
	File savefile=new File(name+"."+format);
	JFileChooser ch=new JFileChooser();
	ch.setSelectedFile(savefile);
	 int option=ch.showSaveDialog(d);
	
		if(option==JFileChooser.APPROVE_OPTION)
		{
			savefile=ch.getSelectedFile();
		
		
				try
	{
		ImageIO.write(s, format, savefile);
	
	}
	catch(IOException e)
	{
		System.out.println(e);
		JOptionPane.showMessageDialog(null, "unable to write");
	}
	
	}
			
}			

	public void saveimage() 
	{
		rec=new ImageIcon(rec).getImage();
		BufferedImage s=new BufferedImage(rec.getWidth(null),rec.getHeight(null),BufferedImage.TYPE_INT_RGB);
		
		Graphics rg=s.createGraphics();
		rg.drawImage(rec,0,0,null);
		rg.dispose();
			String format="JPG";
		String name="my image";
		File savefile=new File(name+"."+format);
		JFileChooser ch=new JFileChooser();
		ch.setSelectedFile(savefile);
		 int option=ch.showSaveDialog(d);
		
			if(option==JFileChooser.APPROVE_OPTION)
			{
				savefile=ch.getSelectedFile();
			
			
					try
		{
			ImageIO.write(s, format, savefile);
		
		}
		catch(IOException e)
		{
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "unable to write");
		}
		
		}
				
			}
			
	//		System.out.println(ver);
		
	}



