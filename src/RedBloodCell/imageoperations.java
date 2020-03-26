package RedBloodCell;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;

public class imageoperations extends JFrame implements ActionListener
{
	JMenuItem menuitem;
	JFrame imageoperations=new JFrame("Image Operations");
	static Image image1=null,image2=null;
	String[] pics= new String[] {"gif","jpg","jpeg","tif",};
	public void recieveimage(Image temp)
	{
		image1=temp;
		//ImageIcon look=new ImageIcon("look.jpg");
		//image1=look.getImage();
	
		
	}
	public void perform()
	{
		
		imageoperations.setSize(1000,700);
		imageoperations.setVisible(true);
		JMenuBar menubar;
		JMenu menu;
		menubar=new JMenuBar();
		menu=new JMenu("File");
		menubar.add(menu);
		menuitem=new JMenuItem("Replace first image");
		menuitem.addActionListener(this);
		menuitem.setEnabled(true);
		menu.add(menuitem);
		menuitem=new JMenuItem("load second image");
		menuitem.addActionListener(this);
		menuitem.setEnabled(true);
		menu.add(menuitem);
		menuitem=new JMenuItem("Exit");
		menuitem.addActionListener(this);
		menuitem.setEnabled(true);
		menu.add(menuitem);
		JMenu submenu=new JMenu("operations");
		menuitem=new JMenuItem("add two images");
		submenu.add(menuitem);
		menuitem.addActionListener(this);
		menuitem.setEnabled(false);
		menuitem=new JMenuItem("subtract two images");
		submenu.add(menuitem);
		menuitem.addActionListener(this);
		menuitem.setEnabled(false);
		menuitem=new JMenuItem("average of two images");
		submenu.add(menuitem);
		menuitem.addActionListener(this);
		menuitem.setEnabled(false);
		menuitem=new JMenuItem("max operation");
		submenu.add(menuitem);
		menuitem.addActionListener(this);
		menuitem.setEnabled(false);
		menuitem=new JMenuItem("min operation");
		submenu.add(menuitem);
		menuitem.addActionListener(this);
		menuitem.setEnabled(false);
		menuitem=new JMenuItem("divide operation");
		submenu.add(menuitem);
		menuitem.addActionListener(this);
		menuitem.setEnabled(false);menuitem=new JMenuItem("multiply operation");
		submenu.add(menuitem);
		menuitem.addActionListener(this);
		menuitem.setEnabled(false);
		menubar.add(submenu);
		imageoperations.add(menubar,BorderLayout.NORTH);
		
		JPanel p1=new JPanel();
		p1.setLayout(new GridLayout(1,2));
		
		JComponent newContentPane=new scrollit(image1);
		newContentPane.setBorder(new TitledBorder("Image 1"));
		p1.add(newContentPane);
		
		JPanel p3 =new JPanel();
		p3.setLayout(new BorderLayout());
		JLabel label=new JLabel("LOAD SECOND IMAGE TO PROCEED .......File > Load second image   ");
		p3.setBorder(new TitledBorder("Image 2"));
		p3.add(label,BorderLayout.CENTER);
		p1.add(p3);
		imageoperations.add(p1,BorderLayout.CENTER);
		
		/*JPanel p4=new JPanel();
		imagepannel im=new imagepannel();
		p4=im.createpanel();
		imageoperations.add(p4,BorderLayout.SOUTH);*/
		
	}
	public void perform1(JFrame temp)
	{
		
		temp.setSize(1000,700);
		temp.setVisible(true);
		JMenuBar menubar;
		JMenu menu;
		menubar=new JMenuBar();
		menu=new JMenu("File");
		menubar.add(menu);
		menuitem=new JMenuItem("Replace first image");
		menuitem.addActionListener(this);
		menuitem.setEnabled(true);
		menu.add(menuitem);
		menuitem=new JMenuItem("load second image");
		menuitem.addActionListener(this);
		menuitem.setEnabled(true);
		menu.add(menuitem);
		menuitem=new JMenuItem("Exit");
		menuitem.addActionListener(this);
		menuitem.setEnabled(true);
		menu.add(menuitem);
		JMenu submenu=new JMenu("operations");
		menuitem=new JMenuItem("add two images");
		submenu.add(menuitem);
		menuitem.addActionListener(this);
		menuitem.setEnabled(true);
		menuitem=new JMenuItem("subtract two images");
		submenu.add(menuitem);
		menuitem.addActionListener(this);
		menuitem.setEnabled(true);
		menuitem=new JMenuItem("average of two images");
		submenu.add(menuitem);
		menuitem.addActionListener(this);
		menuitem.setEnabled(true);
		menuitem=new JMenuItem("max operation");
		submenu.add(menuitem);
		menuitem.addActionListener(this);
		menuitem.setEnabled(true);
		menuitem=new JMenuItem("min operation");
		submenu.add(menuitem);
		menuitem.addActionListener(this);
		menuitem.setEnabled(true);
		menuitem=new JMenuItem("divide operation");
		submenu.add(menuitem);
		menuitem.addActionListener(this);
		menuitem.setEnabled(true);
		menuitem=new JMenuItem("multiply operation");
		submenu.add(menuitem);
		menuitem.addActionListener(this);
		menuitem.setEnabled(true);
		
		menubar.add(submenu);
		
		temp.add(menubar,BorderLayout.NORTH);
		JPanel p1=new JPanel();
		p1.setLayout(new GridLayout(1,2));
		JComponent newContentPane=new scrollit(image1);
		newContentPane.setOpaque(true);
		newContentPane.setBorder(new TitledBorder("Image 1"));
		p1.add(newContentPane);
		
		
		JPanel p3 =new scrollit(image2);
		p3.setBorder(new TitledBorder("Image 2"));
		p1.add(p3);
		temp.add(p1,BorderLayout.CENTER);
		imageoperations=temp;
		
		JPanel p4=new JPanel();
		imagepannel im=new imagepannel();
		p4=im.createpanel();
		im.recieveimage(image1, image2);
		imageoperations.add(p4,BorderLayout.SOUTH);
		

	}
	
	public void actionPerformed(ActionEvent e) 
	{
		
		JMenuItem source=(JMenuItem)(e.getSource());
		String a=source.getText();
		 if(a.equals("Replace first image"))
		 {
			 	JFileChooser ch=new JFileChooser();
				ch.setMultiSelectionEnabled(false);
				ch.addChoosableFileFilter(new SimpleFileFilter(pics,"Images(*.gif,*.jpg,*.jpeg,*.bmp)"));
			    int option=ch.showOpenDialog(imageoperations);
			    if(option==JFileChooser.APPROVE_OPTION)
			    {
			    	
			    	String imageurl=null;
			    	try{
			    		imageurl =ch.getSelectedFile().getAbsolutePath().toString();
			    		}
			    	catch(Exception ex)
			    	{
			    		JOptionPane.showMessageDialog(null, "this file can not be loaded");
			    	}
			    	try{
			    		image1=ImageIO.read(new File(imageurl));
			    			
			    	}

			    	catch(Exception er)
			    	{
			    		JOptionPane.showMessageDialog(null, "this file can not be loaded");
			    	}
			    }
			imageoperations.dispose();
			JFrame temp=new JFrame("image operations");
			perform1(temp);
		 }
		else 
		if(a.equals("load second image"))
		 {
			 	JFileChooser ch=new JFileChooser();
				ch.setMultiSelectionEnabled(false);
				ch.addChoosableFileFilter(new SimpleFileFilter(pics,"Images(*.gif,*.jpg,*.jpeg,*.bmp)"));
			    int option=ch.showOpenDialog(imageoperations);
			    if(option==JFileChooser.APPROVE_OPTION)
			    {
			    	
			    	String imageurl=null;
			    	try{
			    		imageurl =ch.getSelectedFile().getAbsolutePath().toString();
			    		}
			    	catch(Exception ex)
			    	{
			    		JOptionPane.showMessageDialog(null, "this file can not be loaded");
			    	}
			    	try{
			    		image2=ImageIO.read(new File(imageurl));
			    			
			    	}

			    	catch(Exception er)
			    	{
			    		JOptionPane.showMessageDialog(null, "this file can not be loaded");
			    	}
			    }
			imageoperations.dispose();
			JFrame temp=new JFrame("image operations");
			perform1(temp);
		 }
		 else
			 if(a.equals("add two images"))
			 {
				imageoperations1 i =new imageoperations1();
				i.recieveimage(image1,image2);
				i.add();
			 }
			 else
				 if(a.equals("subtract two images"))
				 {
					 imageoperations1 i =new imageoperations1();
					 i.recieveimage(image1,image2);
					 i.subtract(); 
				 }
				 else
					 if(a.equals("average of two images"))
					 {
						 imageoperations1 i =new imageoperations1();
						 i.recieveimage(image1,image2);
						 i.average();
					 }
					 else
						 if(a.equals("max operation"))
						 {
							 imageoperations1 i =new imageoperations1();
							 i.recieveimage(image1,image2);
							 i.max();
						 }
						 else
							 if(a.equals("min operation"))
							 {
								 imageoperations1 i =new imageoperations1();
								 i.recieveimage(image1,image2);
								 i.min();
							 }
							 else
								 if(a.equals("divide operation"))
								 {
									 imageoperations1 i =new imageoperations1();
									 i.recieveimage(image1,image2);
									 i.divide();
								 }
								 else
									 if(a.equals("multiply operation"))
									 {
										 imageoperations1 i =new imageoperations1();
										 i.recieveimage(image1,image2);
										 i.multiply();
									 }
	}
	
	
}
