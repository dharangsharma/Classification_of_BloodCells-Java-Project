package RedBloodCell;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;


public class Finterface extends JFrame implements ActionListener {
	JFrame parent=new JFrame("Image Processor");
	static Image filter; 
	JMenuItem menuitem,m1,m2;
	String[] pics= new String[] {"gif","jpg","jpeg","tif",};
public Finterface(JFrame jf,Image result)
{	
	
	parent=jf;
		filter=result;
		if(result!=null)
		{
		colorspace cp=new colorspace();
		cp.recieveimage(result);
		histequi hi=new histequi();
		hi.getimage(result);
		cumulativehist c= new cumulativehist();
		c.getimage(result);
		autocontrast a=new autocontrast();
	a.recieveimage(result);
	imageoperations i=new imageoperations();
	i.recieveimage(result);
	gammacorrection g=new gammacorrection();
	g.recieveimage(result);
	undo u=new undo();
	u.getImage(result);
	histogram h=new histogram();
	h.getimage(result);
	redhist red=new redhist();
	red.getimage(result);
	greenhist green=new greenhist();
	green.getimage(result);
	bluehist blue=new bluehist();
	blue.getimage(result);
	point pot=new point();
	pot.getimage(result);
	JComponent newContentPane=new scrollit(result);
	newContentPane.setOpaque(true);
	workarea w=new workarea();
	save s=new save();
	JMenuBar menubar=new JMenuBar();
	menubar=createmenu();
	JPanel jp=w.createpanel();
	parent.add(menubar,BorderLayout.NORTH);
	parent.add(newContentPane,BorderLayout.CENTER);
	parent.add(jp,BorderLayout.SOUTH);
	parent.setSize(2000,700);
	
	parent.setVisible(true);
	w.recieve(parent, result);
s.recieve(result, parent);
		}
	}
	public Finterface()
{
	JMenuBar menubar;
	JMenu menu;
	ImageIcon look=new ImageIcon("lake.jpg");
JLabel image=new JLabel(look);
	parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
menubar=new JMenuBar();
menu=new JMenu("File");
menu.setMnemonic(KeyEvent.VK_F);
menubar.add(menu);
menuitem=new JMenuItem("Load image",KeyEvent.VK_O);
menuitem.addActionListener(this);
menuitem.setEnabled(true);
menu.add(menuitem);
m1=new JMenuItem("Save image",KeyEvent.VK_S);
m1.setEnabled(false);
m1.addActionListener(this);
menu.add(m1);
menuitem=new JMenuItem("Exit");
menuitem.addActionListener(this);
menu.add(menuitem);
menu=new JMenu("Help");
menuitem=new JMenuItem("How to get Started");
menuitem.addActionListener(this);
menu.add(menuitem);
menuitem=new JMenuItem("What is Edge Detection");
menuitem.addActionListener(this);
menu.add(menuitem);
menuitem=new JMenuItem("About Ant Colony Optimisation");
menuitem.addActionListener(this);
menu.add(menuitem);
menuitem=new JMenuItem("Some other functions");
menuitem.addActionListener(this);
menu.add(menuitem);
menubar.add(menu);
parent.add(menubar,BorderLayout.NORTH);
parent.add(image,BorderLayout.CENTER);
parent.setSize(1000, 750);
parent.setVisible(true);
}
public JMenuBar createmenu()
{
	JMenuBar menubar;
	JMenu menu;
	parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	menubar=new JMenuBar();
	menu=new JMenu("File");
	menu.setMnemonic(KeyEvent.VK_F);
	menubar.add(menu);
	menuitem=new JMenuItem("Load image",KeyEvent.VK_O);
	menuitem.addActionListener(this);
	menuitem.setEnabled(true);
	menu.add(menuitem);
	m1=new JMenuItem("Save image",KeyEvent.VK_S);

	m1.addActionListener(this);
	menu.add(m1);
	menuitem=new JMenuItem("undo");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menuitem=new JMenuItem("redo");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menuitem=new JMenuItem("Exit");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menu=new JMenu("Histograms");
	menubar.add(menu);
	menuitem=new JMenuItem("Generate Histogram");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menuitem=new JMenuItem("red histogram");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menuitem=new JMenuItem("green histogram");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menuitem=new JMenuItem("blue histogram");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menuitem=new JMenuItem("cumulative histogram");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menuitem=new JMenuItem("histogram equilization");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menu=new JMenu("point operations");
	JMenu menu1=new JMenu("Multiple images");
	menuitem=new JMenuItem("open");
	menuitem.addActionListener(this);
	menu1.add(menuitem);
	menu.add(menu1);
	menuitem=new JMenuItem("Automatic Contrast Adjustment");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menubar.add(menu);
	
	
	menuitem=new JMenuItem("Contrast and Brightness");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menuitem=new JMenuItem("gamma correction");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menu=new JMenu("Filters");
	menubar.add(menu);
	menuitem=new JMenuItem("Minimum Filter");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menuitem=new JMenuItem("Maximum Filter");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menuitem=new JMenuItem("Median Filter");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menuitem=new JMenuItem("Weighted Median Filter");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	
	menu=new JMenu("color space conversion");
	menubar.add(menu);
	menuitem=new JMenuItem("red scale");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menuitem=new JMenuItem("green scale");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menuitem=new JMenuItem("blue scale");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	
	menu=new JMenu("Help");
	menuitem=new JMenuItem("How to get Started");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menuitem=new JMenuItem("What is Edge Detection");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menuitem=new JMenuItem("About Ant Colony Optimisation");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menuitem=new JMenuItem("Some other functions");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menubar.add(menu);
	m1.setEnabled(true);

return menubar;
}
@Override
public void actionPerformed(ActionEvent e) {
	JMenuItem source=(JMenuItem)(e.getSource());
 String a=source.getText();
JPanel button=new JPanel();
Image im=null;
if(a.equals("Load image"))
{
	JMenuBar menubar=new JMenuBar();
	menubar=createmenu();
	JFileChooser ch=new JFileChooser();
	ch.setMultiSelectionEnabled(false);
	ch.addChoosableFileFilter(new SimpleFileFilter(pics,"Images(*.gif,*.jpg,*.jpeg,*.bmp)"));
    int option=ch.showOpenDialog(parent);
    if(option==JFileChooser.APPROVE_OPTION)
    {
    	
   String imageurl=null;
try{
imageurl =ch.getSelectedFile().getAbsolutePath().toString();
reset re=new reset();

re.getimage(imageurl);
}
catch(Exception ex)
{
	JOptionPane.showMessageDialog(null, "this file can not be loaded");
}
JComponent newContentPane=new scrollit(imageurl);
newContentPane.setOpaque(true);
//parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
parent.setContentPane(newContentPane);
parent.add(menubar,BorderLayout.NORTH);
//undo u=new undo();
try{
	im=ImageIO.read(new File(imageurl));
		
}

catch(Exception er)
{
	JOptionPane.showMessageDialog(null, "this file can not be loaded");
}
  
parent.add(button,BorderLayout.SOUTH);
parent.setSize(2000,700);
parent.setVisible(true);
save s=new save();
s.recieve(im, parent);
    parent.dispose();
    JFrame temp=new JFrame("Image Processor");
    Finterface f= new Finterface(temp,im); 
    }
    else
    {
    	
    }
}

else if(a.equals("Save image"))
{
save s=new save();
s.saveimage();
}

else if(a.equals("Exit"))
{
	 System.exit(0);
}
else if(a.equals("How to get Started"))
{
	JFrame help=new JFrame("HELP Image Processor");
	JLabel step1=new JLabel("1.Click on File Menu");
	JLabel step2=new JLabel("2.Click on  load Image");
	JLabel step3=new JLabel("3.Select the Image");
	JLabel step4=new JLabel("4.A panel of buttons will be displayed.Perform the desired operation");
		help.setLayout(new GridLayout(4,1));
	help.setSize(400, 300);
	help.setVisible(true);
	help.add(step1);
	help.add(step2);
	help.add(step3);
	help.add(step4);
}
else if(a.equals("What is Edge Detection"))
{
	JFrame help1=new JFrame("HELP Image Processor");
	ImageIcon look=new ImageIcon("edge detection.jpg");
	JLabel image=new JLabel(look);
	help1.add(image);
	help1.setSize(500, 500);
	help1.setVisible(true);
}
else if(a.equals("About Ant Colony Optimisation"))
{
	JFrame help2=new JFrame("HELP Image Processor");
	ImageIcon look=new ImageIcon("aco.jpg");
	JLabel image=new JLabel(look);
	help2.add(image);
	help2.setSize(500, 500);
	help2.setVisible(true);
}
else if(a.equals("Some other functions"))
{
	JFrame help3=new JFrame("HELP Image Processor");
	ImageIcon look=new ImageIcon("op.jpg");
	JLabel image=new JLabel(look);
	help3.add(image);
	help3.setSize(500, 500);
	help3.setVisible(true);
}
else if(a.equals("undo"))
{
	undo u=new undo();
	u.undoimage(parent);
}
else if(a.equals("Generate Histogram"))
{
	histogram h=new histogram();
	h.drawhist();
}
else if(a.equals("red histogram"))
{
	redhist red=new redhist();
	red.sendimage();
}
else if(a.equals("green histogram"))
{
	greenhist green =new greenhist();
	green.sendimage();
}
else if(a.equals("blue histogram"))
{
	bluehist blue=new bluehist();
	blue.sendimage();
}
else if(a.equals("Contrast and Brightness"))
{
point pot=new point();
pot.recievecbimage();
}
else
	if(a.equals("gamma correction"))
	{
		gammacorrection g= new gammacorrection();
		g.getvalue();
		g.perform();
	}
	else 
		if(a.equals("open"))
		{
			imageoperations i=new imageoperations();
			i.perform();
		}
		else
			if(a.equals("Automatic Contrast Adjustment"))
			{
				autocontrast b=new autocontrast();
				b.perform();
			}
			else if(a.equals("Minimum Filter"))
			{
				minfilter m=new minfilter();
				m.filter(parent, filter);
			}
			else if(a.equals("Maximum Filter"))
			{
				maxfilter m=new maxfilter();
				m.filter(parent, filter);
			}
			else if(a.equals("Median Filter"))
			{
				medianfilter m=new medianfilter();
				m.filter(parent, filter);
			}
			else if(a.equals("Weighted Median Filter"))
			{
				medianfilter m=new medianfilter();
				m.filter(parent, filter);
			}

			else if(a.equals("cumulative histogram"))
			{
				cumulativehist c=new cumulativehist();
				c.drawhist();
			}
			else
				if(a.equals("histogram equilization")) {
					histequi his=new histequi();
					his.perform();
				}
				else
					if(a.equals("red scale")) {
						colorspace cp = new colorspace();
						cp.perform();
					}
					else
						if(a.equals("green scale")) {
							colorspace cp = new colorspace();
							cp.perform1();
						}else
							if(a.equals("blue scale")) {
								colorspace cp = new colorspace();
								cp.perform2();
							}
							else if(a.equals("redo"))
							{
								undo u=new undo();
								u.redo(parent);
							}
}
    public static void main(String[] args) {
        new Finterface().setVisible(true);
    }


}

