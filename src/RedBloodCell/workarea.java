package RedBloodCell;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.imageio.*;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.Image;
import java.io.*;
public class workarea implements ActionListener {
private JPanel function;
private JButton functions[];
public static JFrame jf;
public static Image im;
public static int option=0;
//public static undo u=new undo();
public JPanel createpanel()
{

functions=new JButton[10];
function=new JPanel();
function.setLayout(new GridLayout(1,functions.length));
functions[0]=new JButton("Edge Detection");
functions[0].addActionListener(this);
functions[1]=new JButton("Contrast");
functions[1].addActionListener(this);
functions[1].addActionListener(this);
functions[2]=new JButton("GrayScale");
functions[2].addActionListener(this);
functions[3]=new JButton("Invert");
functions[3].addActionListener(this);
functions[4]=new JButton("Sharpen");
functions[4].addActionListener(this);
functions[5]=new JButton("Blur");
functions[5].addActionListener(this);
functions[6]=new JButton("Rotate");
functions[6].addActionListener(this);
functions[7]=new JButton("Scaling");
functions[7].addActionListener(this);
functions[8]=new JButton("Sepia");
functions[8].addActionListener(this);
functions[9]=new JButton("Reset");
functions[9].addActionListener(this);
function.add(functions[0]);
function.add(functions[1]);
function.add(functions[2]);
function.add(functions[3]);
function.add(functions[4]);
function.add(functions[5]);
function.add(functions[6]);
function.add(functions[7]);
function.add(functions[8]);
function.add(functions[9]);
return function;
}
public  final void recieve(JFrame f,Image image){
	
jf=f;
im=image;


}

public void actionPerformed(ActionEvent e) {
JButton b=(JButton)(e.getSource());
if(b==functions[2])
{ 	
option=option+1;
//u.roption(option);
	Grayscale gs=new Grayscale();
gs.filter(jf, im);
}
else if(b==functions[1])
{
	option=option+1;
	//u.roption(option);
	contrast c=new contrast();
	c.filter(jf, im);
}
else if(b==functions[3])
{
	option=option+1;
	//u.roption(option);
	invert i=new invert();
	i.filter(jf,im);
}
else if(functions[5]==b)
{
	option=option+1;
	//u.roption(option);
	blur bl=new blur();
	bl.filter(jf, im);
}
else if(functions[4]==b)
{option=option+1;
//u.roption(option);
		sharpen sh=new sharpen();
	sh.filter(jf, im);
}
	else if(functions[7]==b)
	{
		option=option+1;
//		u.roption(option);
		//scale sc=new scale();
		//sc.filter(jf, im);
	}
	else if(functions[6]==b)
	{
		option=option+1;
	//	u.roption(option);
		//rotate r=new rotate();
		//r.filter(jf, im);
	}
	else if(functions[0]==b)
	{
		option=option+1;
		//u.roption(option);
		edgedetection eg=new edgedetection();
		eg.filter(jf, im);
	}
	else if(functions[9]==b)
	{
	
		reset re=new reset();
		re.filter(jf, im);
	}
	else if(functions[8]==b)
	{
		option=option+1;
	//	u.roption(option);
		sepia s=new sepia();
		s.filter(jf, im);
	}
}
}

	

