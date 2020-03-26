package RedBloodCell;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;
import java.awt.Image.*;

import java.io.*;
import javax.imageio.*;
public class imageshow extends JPanel {
	private static String img;
	protected Image image=null; 
	public imageshow(Image im)
	{
		super(new BorderLayout()); 
		image=im;
	}
	public imageshow(String u)
	{
		super(new BorderLayout()); 
		img=u;
		try{
			image=ImageIO.read(new File(u));
		
	
		}
		catch(IOException e )
		{
			System.out.println(e);
		}
	}
	
public Dimension getPreferredSize()
{
if(image==null)
{
return new Dimension(100,100);
}
else
	{
	return new Dimension(image.getWidth(null),image.getHeight(null));
	}
	
}
public void paint(Graphics g)
{
g.drawImage(image,0,0,null);
}
}
