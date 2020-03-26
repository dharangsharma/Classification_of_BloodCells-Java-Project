package RedBloodCell;
import java.awt.Image;
import javax.swing.*;

import java.awt.*;
public class undo {
static int i=1,a=1,k=1,redo=0;
static Image g,redoim;
	static Image un[]=new Image[100];
	void getImage(Image r)
	{
		if(i>=100)
		{
			i=a;
		}
		else if(i==0)
		{
			i=1;
			k=1;
		}
		else
		{
		un[i]=r;
		i=i+1;
		}
		
	}
	public void intialimage(Image r)
	{
		un[0]=r;
		//System.out.println(i);
	}
	public void undoimage(JFrame f) {
		if(k==1)
		{
			i=i-1;
		}
		JFrame res=new JFrame("Image Processor");
		if(i!=0)
		{
			i=i-1;
			a=i;
		    
		    if(i==0)
		    {
		    	g=un[0]; 	
		    	redo=i+1;
		    }
		    	else
		    	{ 	
		    		g=un[i];
		    		redo=i+1;
		    		i=101;
		 		    	}
		    f.dispose();
		    Finterface fi=new Finterface(res,g);
	//	    System.out.println(i);
		    	
		}
	/*	else
		{
			g=un[0];
			i=101;
		    f.dispose();
		    Finterface fi=new Finterface(res,g);
		}*/
		k++;
	}
	void redo(JFrame f)
	{
		redoim=un[redo];
		if(redoim!=null)
		{
			JFrame res=new JFrame("Image Processor");
			   f.dispose();
			    Finterface fi=new Finterface(res,redoim);

		}
	}

}
