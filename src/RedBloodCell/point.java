package RedBloodCell;
import java.awt.image.*;
import java.awt.image.MemoryImageSource;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.util.Hashtable;
public class point implements ActionListener,ChangeListener{
	static int w,h;
	static int fps1,fps2;
	static int imagepixels[];
	static int resimage[];
	static JFrame parent;
	private JSlider slide[];
	public static JFrame jf;
	static Image image,cbimage;
	void getimage(Image temp){
		cbimage=temp;
		w=cbimage.getWidth(null);
		h=cbimage.getHeight(null);
	}
	public void recievecbimage(){
	parent=new JFrame("Contrast and Brightness");
	JComponent newContentPane=new scrollit(cbimage);
	newContentPane.setOpaque(true);
	JMenuBar menubar;
	JMenu menu;
	JMenuItem menuitem;
	slide=new JSlider[2];
	slide[0]=new JSlider(JSlider.HORIZONTAL,1,20,11);
	slide[0].setMinorTickSpacing(1);
	slide[0].setMajorTickSpacing(10);
	slide[0].setPaintTicks(true);
	slide[0].setPaintLabels(true);
	slide[0].addChangeListener(this);
	Hashtable<Integer,JLabel> hlbtable=new Hashtable<Integer,JLabel>();
	hlbtable.put(new Integer(1),new JLabel("Low"));
	hlbtable.put(new Integer(11),new JLabel("Contrast"));
	hlbtable.put(new Integer(20),new JLabel("High"));
	slide[0].setLabelTable(hlbtable);
	slide[1]=new JSlider(JSlider.VERTICAL,1,200,10);
	slide[1].setMinorTickSpacing(5);
	slide[1].setMajorTickSpacing(10);
	slide[1].setPaintTicks(true);
	slide[1].setPaintLabels(true);
	slide[1].addChangeListener(this);
	Hashtable<Integer,JLabel> vlbtable=new Hashtable<Integer,JLabel>();
	vlbtable.put(new Integer(1),new JLabel("Low"));
	vlbtable.put(new Integer(100),new JLabel("Brightness"));
	vlbtable.put(new Integer(200),new JLabel("High"));
	slide[1].setLabelTable(vlbtable);
	menubar=new JMenuBar();
	menu=new JMenu("File");
	menubar.add(menu);
	menuitem=new JMenuItem("Save Image");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	menuitem=new JMenuItem("Exit");
	menuitem.addActionListener(this);
	menu.add(menuitem);
	//parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	parent.add(menubar,BorderLayout.NORTH);
	parent.add(newContentPane,BorderLayout.CENTER);
	parent.add(slide[0],BorderLayout.SOUTH);
	parent.add(slide[1],BorderLayout.EAST);
	parent.setSize(1000, 750);
	parent.setVisible(true);
	}
	private final int clamp(int c)
	{
		return(c>255?255:(c<0?0:c));
	}
	void contrast(int x,int y,int c,int d){
		//float z=c/5;
		//float f=d/5;
		imagepixels=new int[w*h];
		resimage=new int[w*h];
		PixelGrabber pg=new PixelGrabber(cbimage,0,0,w,h,imagepixels,0,w);
		try
		{
			pg.grabPixels();
			
		}
		catch(InterruptedException e)
		{
			
		}
		for(int i=0;i<w*h;i++)
		{
		int p=imagepixels[i];
		
		
		int r=0xff&(p>>16);
		int g=0xff&(p>>8);
		int b=0xff&(p);
		//int k=(int)(0.33*r+0.56*g+0.11*b);
		//int k1=(int)((k-0.5)*c+0.5+d);
		
		int r1=(int)(((0.30*r)-0.5)*c+0.5+d);
		int g1=(int)(((0.40*g)-0.5)*c+0.5+d);
		int b1=(int)(((0.30*b)-0.5)*c+0.5+d);
		
		resimage[i]=(0xff000000|clamp(r1)<<16|clamp(g1)<<8|clamp(b1));
		}
		BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		img.setRGB(0,0,w,h,resimage,0,w);
		image=Toolkit.getDefaultToolkit().createImage(img.getSource());
		parent.dispose();
		recievecbimage1(image,x,y);
	}
	/*void bright(int d){
		imagepixels=new int[w*h];
		resimage=new int[w*h];
		PixelGrabber pg=new PixelGrabber(cbimage,0,0,w,h,imagepixels,0,w);
		try
		{
			pg.grabPixels();
			
		}
		catch(InterruptedException e)
		{
			
		}
		for(int i=0;i<w*h;i++)
		{
		int p=imagepixels[i];
		
		
		int r=0xff&(p>>16);
		int g=0xff&(p>>8);
		int b=0xff&(p);
		//int k=(int)(0.33*r+0.56*g+0.11*b);
		//int k1=(int)((k-0.5)*c+0.5+d);
		
		int r1=(int)((r)+d);
		int g1=(int)((g)+d);
		int b1=(int)((b)+d);
		resimage[i]=0xff000000|r1<<16|g1<<8|b1;
		
		}
		BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		img.setRGB(0,0,w,h,resimage,0,w);
		image=Toolkit.getDefaultToolkit().createImage(img.getSource());
		parent.dispose();
		cbimage=image;
		recievecbimage1(cbimage);
	}*/
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JMenuItem source=(JMenuItem)(e.getSource());
		 String a=source.getText();
		 if(a.equals("Exit")){
			 parent.dispose();
		 }
		 else if(a.equals("Save Image")){
			 save s=new save();
			 s.histsave(image); 
		 }
	}
	public void stateChanged(ChangeEvent e){
	JSlider s=(JSlider)(e.getSource());
			if(!s.getValueIsAdjusting()){
			int a=(int)slide[0].getValue();
			fps1=a-10;
			int b=(int)slide[1].getValue();
			fps2=b-10;
			contrast(a,b,fps1,fps2);
			}
	
	}
	void recievecbimage1(Image im,int x,int y){
		//int d=Math.abs(c);
		//int e=Math.abs(b);
		parent=new JFrame("Contrast and Brightness");
		JComponent newContentPane=new scrollit(im);
		newContentPane.setOpaque(true);
		JMenuBar menubar;
		JMenu menu;
		JMenuItem menuitem;
		slide=new JSlider[2];
		slide[0]=new JSlider(JSlider.HORIZONTAL,1,20,x);
		slide[0].setMinorTickSpacing(1);
		slide[0].setMajorTickSpacing(10);
		slide[0].setPaintTicks(true);
		slide[0].setPaintLabels(true);
		slide[0].addChangeListener(this);
		Hashtable<Integer,JLabel> hlbtable=new Hashtable<Integer,JLabel>();
		hlbtable.put(new Integer(1),new JLabel("Low"));
		hlbtable.put(new Integer(11),new JLabel("Contrast"));
		hlbtable.put(new Integer(20),new JLabel("High"));
		slide[0].setLabelTable(hlbtable);
		slide[1]=new JSlider(JSlider.VERTICAL,1,200,y);
		slide[1].setMinorTickSpacing(5);
		slide[1].setMajorTickSpacing(10);
		slide[1].setPaintTicks(true);
		slide[1].setPaintLabels(true);
		slide[1].addChangeListener(this);
		Hashtable<Integer,JLabel> vlbtable=new Hashtable<Integer,JLabel>();
		vlbtable.put(new Integer(1),new JLabel("Low"));
		vlbtable.put(new Integer(100),new JLabel("Brightness"));
		vlbtable.put(new Integer(200),new JLabel("High"));
		slide[1].setLabelTable(vlbtable);
		menubar=new JMenuBar();
		menu=new JMenu("File");
		menubar.add(menu);
		menuitem=new JMenuItem("Save Image");
		menuitem.addActionListener(this);
		menu.add(menuitem);
		menuitem=new JMenuItem("Exit");
		menuitem.addActionListener(this);
		menu.add(menuitem);
		//parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		parent.add(menubar,BorderLayout.NORTH);
		parent.add(newContentPane,BorderLayout.CENTER);
		parent.add(slide[0],BorderLayout.SOUTH);
		parent.add(slide[1],BorderLayout.EAST);
		//parent.add(function,BorderLayout.SOUTH);
		parent.setSize(1000, 750);
		parent.setVisible(true);
		}
}

