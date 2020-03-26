package RedBloodCell;
import javax.swing.*;
import java.awt.*;

public class scrollit extends JPanel {
	private static String p;
	public scrollit(Image ima)
	{
		super(new BorderLayout());
		imageshow img=new imageshow(ima);
		JScrollPane scroller= new JScrollPane(img);
		scroller.setPreferredSize(new Dimension(200,200));
		add(scroller,BorderLayout.CENTER);
	}
	public scrollit(String path)
	{
		super(new BorderLayout());
		p=path;
		imageshow imgshow=new imageshow(p);
		
		JScrollPane scroller= new JScrollPane(imgshow);
		scroller.setPreferredSize(new Dimension(200,200));
		add(scroller,BorderLayout.CENTER);
	}
	
	
	
}

