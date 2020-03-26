package RedBloodCell;
import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
public class reset implements Pluginfilter {
static Image i;
 static String iurl;
JFrame res=new JFrame("Image Processor");
public final void getimage(String url)
{
	iurl=url;
	undo u=new undo();
	try {
		i=ImageIO.read(new File(url));
		u.intialimage(i);
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	
}

@Override
public void filter(JFrame f, Image in) {
	Finterface fi=new Finterface(res,i);
	f.dispose();

	
}

}
