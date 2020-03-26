package RedBloodCell;
import java.awt.event.ActionListener;
import java.awt.*;
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
import javax.swing.JPanel;


public class imagepannel implements ActionListener 
{ static Image image1=null,image2=null;

public void recieveimage(Image temp1,Image temp2)
{
image1=temp1;
image2=temp2;
}
public JPanel createpanel()
	{
		JPanel p4=new JPanel();
		p4.setLayout(new GridLayout(1,8));
		JButton button1=new JButton("Add");
		button1.addActionListener(this);
		p4.add(button1);
		button1=new JButton("Subtract");
		button1.addActionListener(this);
		p4.add(button1);
		button1=new JButton("Average");
		button1.addActionListener(this);
		p4.add(button1);
		button1=new JButton("Max Operation");
		button1.addActionListener(this);
		p4.add(button1);
		button1=new JButton("Min Operation");
		button1.addActionListener(this);
		p4.add(button1);
		button1=new JButton("Divide");
		button1.addActionListener(this);
		p4.add(button1);
		button1=new JButton("Multiply");
		button1.addActionListener(this);
		p4.add(button1);
		button1=new JButton("Alpha Bending");
		button1.addActionListener(this);
		p4.add(button1);
		return(p4);
	}

	public void actionPerformed(ActionEvent e) 
	{
		JButton source=(JButton)(e.getSource());
		 String a=source.getText();
		 if(a.equals("Add"))
		 {
			imageoperations1 i =new imageoperations1();
			i.recieveimage(image1,image2);
			i.add();
		 }
		 else
			 if(a.equals("Subtract"))
			 {
				 imageoperations1 i =new imageoperations1();
				 i.recieveimage(image1,image2);
				 i.subtract(); 
			 }
			 else
				 if(a.equals("Average"))
				 {
					 imageoperations1 i =new imageoperations1();
					 i.recieveimage(image1,image2);
					 i.average();
				 }
				 else
					 if(a.equals("Max Operation"))
					 {
						 imageoperations1 i =new imageoperations1();
						 i.recieveimage(image1,image2);
						 i.max();
					 }
					 else
						 if(a.equals("Min Operation"))
						 {
							 imageoperations1 i =new imageoperations1();
							 i.recieveimage(image1,image2);
							 i.min();
						 }
						 else
							 if(a.equals("Divide"))
							 {
								 imageoperations1 i =new imageoperations1();
								 i.recieveimage(image1,image2);
								 i.divide();
							 }
							 else
								 if(a.equals("Multiply"))
								 {
									 imageoperations1 i =new imageoperations1();
									 i.recieveimage(image1,image2);
									 i.multiply();
								 }
								 else
									 if (a.equals("Alpha Bending"))
									 {
										 imageoperations1 i =new imageoperations1();
										 i.recieveimage(image1,image2);
										 String value=JOptionPane.showInputDialog(null,"Enter the transparency value alpha");
											double value1=Double.parseDouble(value);
											float value2=(float)(value1);
										 i.alphabending(value2);
									 }
}
		 
	}



