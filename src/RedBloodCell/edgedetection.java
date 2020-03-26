package RedBloodCell;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.*;
import java.math.*;
import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class edgedetection implements Pluginfilter {

	int w,h;
	double check=0;
	int pixels[];
	double imagedata[];
	double pheromone[][];
	double heuristics[][];
	double probability[]=new double[8];
	double edge[][];
	double ml[][];
	double mu[][];
	double k,v,n1=0,n2=0,n3=0,n4=0,n5=0,n6=0,n7=0,n8=0,d,z=0,la=1.0,q,t0=0,mlo=0,mh=0,hl=0,hh=0,be=1;
	public void filter(JFrame f, Image in) {
w=in.getWidth(null);
h=in.getHeight(null);
double intensity[][]=new double[w][h];
pheromone=new double[w][h];
heuristics=new double[w][h];
pixels=new int[w*h];
imagedata=new double[w*h];
edge=new double[w][h];
ml=new double[w][h];
mu=new double[w][h];
k=2*Math.ceil((Math.sqrt(w*h)));
System.out.println(k);
//BufferedImage gs=new BufferedImage(w,h,BufferedImage.TYPE_BYTE_GRAY);
//Graphics rg=gs.createGraphics();
//rg.drawImage(in,0,0,null);
//rg.dispose();
PixelGrabber pg=new PixelGrabber(in,0,0,w,h,pixels,0,w);
try
{
	pg.grabPixels();

}
catch(InterruptedException e)
{
	
}
ColorModel cm=pg.getColorModel();
for(int i=0;i<w*h;i++)
{
	int p=pixels[i];
	double r=0xff&(p >> 16);
	double g=0xff&(p>>8);
	double b=0xff&(p);
	double y=(double)(.33*r+.56*g+.11*b);
	imagedata[i]=y;
}


//System.out.println(pixels[500]+" "+w+" "+h);
//conversion of 1-d array into 2d array
for(int i=0;i<w;i++)
{
	for(int j=0;j<h;j++)
	{
		intensity[i][j]=imagedata[i+(j*w)];
	pheromone[i][j]=0.1;
	if(i==0|j==0)
	{
		if(i==0&&j==0)
		{
			v=(Math.abs(intensity[i+2][j+1])+
					Math.abs(intensity[i+1][j+2])+
					Math.abs(intensity[i+1][j+1])+
					Math.abs(intensity[i+1][j])+
					Math.abs(intensity[i][j+1]));
		}
		else if(i==0)
		{
			if(j==1)
			{
				v=(Math.abs(intensity[i+2][j+1])+
						Math.abs(intensity[i+2][j-1])+
						Math.abs(intensity[i+1][j+2])+
						Math.abs(intensity[i+1][j+1])+
						Math.abs(intensity[i+1][j])+
						Math.abs(intensity[i][j-1]-intensity[i][j+1]));
			}
			else if(j==h-2)
			{
				v=(Math.abs(intensity[i+2][j+1])+
						Math.abs(intensity[i+2][j-1])+
						Math.abs(intensity[i+1][j+1])+
						Math.abs(intensity[i+1][j])+
						Math.abs(intensity[i][j-1]-intensity[i][j+1]));
			}
			else if(j==h-1)
			{
				v=(
						Math.abs(intensity[i+2][j-1])+
						
						Math.abs(intensity[i+1][j])+
						Math.abs(intensity[i][j-1]));
			}
			else
			{
				v=(Math.abs(intensity[i+2][j+1])+
						Math.abs(intensity[i+2][j-1])+
						Math.abs(intensity[i+1][j+2])+
						Math.abs(intensity[i+1][j+1])+
						Math.abs(intensity[i+1][j])+
						Math.abs(intensity[i][j-1]-intensity[i][j+1]));

			}
		}
		else
		{
		if(i==1)
		{
			v=(Math.abs(intensity[i+2][j+1])+
					Math.abs(intensity[i+1][j+2])+
					Math.abs(intensity[i+1][j+1])+
					Math.abs(intensity[i-1][j]-intensity[i+1][j])+
					Math.abs(intensity[i-1][j+1])+
					Math.abs(intensity[i-1][j+2])+
					Math.abs(intensity[i][j+1]));

		}
		else if(i==w-2)
		{
			v=(
					Math.abs(intensity[i-2][j+1])+
					Math.abs(intensity[i+1][j+2])+
					Math.abs(intensity[i+1][j+1])+
					Math.abs(intensity[i-1][j]-intensity[i+1][j])+
					Math.abs(intensity[i-1][j+1])+
					Math.abs(intensity[i-1][j+2])+
					Math.abs(intensity[i][j+1]));

		}
		else if(i==w-1)
		{
			v=(
					Math.abs(intensity[i-2][j+1])+
					
					
					Math.abs(intensity[i-1][j])+
					Math.abs(intensity[i-1][j+1])+
					Math.abs(intensity[i-1][j+2])+
					Math.abs(intensity[i][j+1]));

		}
		else
		{
			v=(Math.abs(intensity[i+2][j+1])+
					Math.abs(intensity[i-2][j+1])+
					Math.abs(intensity[i+1][j+2])+
					Math.abs(intensity[i+1][j+1])+
					Math.abs(intensity[i-1][j]-intensity[i+1][j])+
					Math.abs(intensity[i-1][j+1])+
					Math.abs(intensity[i-1][j+2])+
					Math.abs(intensity[i][j+1]));

		}
		
		}
	
	}
	
	else if(i==1|j==1)
	{
		if(i==1&&j==1)
		{
			v=(Math.abs(intensity[i+2][j+1])+
					Math.abs(intensity[i+2][j-1])+
					Math.abs(intensity[i+1][j+2])+
					Math.abs(intensity[i-1][j-1]-intensity[i+1][j+1])+
					Math.abs(intensity[i-1][j]-intensity[i+1][j])+
					Math.abs(intensity[i-1][j+1]-intensity[i-1][j-1])+
					Math.abs(intensity[i-1][j+2])+
					Math.abs(intensity[i][j-1]-intensity[i][j+1]));

		}
		else if(i==1)
		{
			if(j==h-2)
			{
				v=(Math.abs(intensity[i+2][j+1])+
						Math.abs(intensity[i+2][j-1])+
						Math.abs(intensity[i-1][j-2])+
						Math.abs(intensity[i-1][j-1]-intensity[i+1][j+1])+
						Math.abs(intensity[i-1][j]-intensity[i+1][j])+
						Math.abs(intensity[i-1][j+1]-intensity[i-1][j-1])+
						Math.abs(intensity[i-1][j-2])+
						Math.abs(intensity[i][j-1]-intensity[i][j+1]));

				
			}
			else if(j==h-1)
			{
				v=(
						Math.abs(intensity[i+2][j-1])+
						Math.abs(intensity[i-1][j-2])+
						Math.abs(intensity[i-1][j-1])+
						Math.abs(intensity[i-1][j]-intensity[i+1][j])+
						Math.abs(intensity[i-1][j-1])+
						Math.abs(intensity[i-1][j-2])+
						Math.abs(intensity[i][j-1]));

			}
			else
			{
				v=(Math.abs(intensity[i+2][j+1])+
						Math.abs(intensity[i+2][j-1])+
						Math.abs(intensity[i-1][j-2]-intensity[i+1][j+2])+
						Math.abs(intensity[i-1][j-1]-intensity[i+1][j+1])+
						Math.abs(intensity[i-1][j]-intensity[i+1][j])+
						Math.abs(intensity[i-1][j+1]-intensity[i-1][j-1])+
						Math.abs(intensity[i-1][j+2]-intensity[i-1][j-2])+
						Math.abs(intensity[i][j-1]-intensity[i][j+1]));

			}
		}
		else if(j==1)
		{
			if(i==w-2)
			{
				v=(Math.abs(intensity[i-2][j-1])+
						Math.abs(intensity[i-2][j+1])+
						Math.abs(intensity[i+1][j+2])+
						Math.abs(intensity[i-1][j-1]-intensity[i+1][j+1])+
						Math.abs(intensity[i-1][j]-intensity[i+1][j])+
						Math.abs(intensity[i-1][j+1]-intensity[i-1][j-1])+
						Math.abs(intensity[i-1][j+2])+
						Math.abs(intensity[i][j-1]-intensity[i][j+1]));

			}
			else if(i==w-1)
			{
				v=(Math.abs(intensity[i-2][j-1])+
						Math.abs(intensity[i-2][j+1])+
						+
						Math.abs(intensity[i-1][j-1])+
						Math.abs(intensity[i-1][j])+
						Math.abs(intensity[i-1][j+1]-intensity[i-1][j-1])+
						Math.abs(intensity[i-1][j+2])+
						Math.abs(intensity[i][j-1]-intensity[i][j+1]));

			}
			else
			{
				v=(Math.abs(intensity[i-2][j-1]-intensity[i+2][j+1])+
						Math.abs(intensity[i-2][j+1]-intensity[i+2][j-1])+
						Math.abs(intensity[i+1][j+2])+
						Math.abs(intensity[i-1][j-1]-intensity[i+1][j+1])+
						Math.abs(intensity[i-1][j]-intensity[i+1][j])+
						Math.abs(intensity[i-1][j+1]-intensity[i-1][j-1])+
						Math.abs(intensity[i-1][j+2])+
						Math.abs(intensity[i][j-1]-intensity[i][j+1]));

			}
		}
	}
	else if(i==w-2|j==h-2)
	{
		if(i==w-2&&j==h-2)
		{
			v=(Math.abs(intensity[i-2][j-1])+
					Math.abs(intensity[i-2][j+1])+
					Math.abs(intensity[i-1][j-2])+
					Math.abs(intensity[i-1][j-1]-intensity[i+1][j+1])+
					Math.abs(intensity[i-1][j]-intensity[i+1][j])+
					Math.abs(intensity[i-1][j+1]-intensity[i-1][j-1])+
					Math.abs(intensity[i-1][j-2])+
					Math.abs(intensity[i][j-1]-intensity[i][j+1]));
	
		}
		else if(i==w-2)
		{
			if(j==h-1)
			{
				v=(Math.abs(intensity[i-2][j-1])+
						
						Math.abs(intensity[i-1][j-2])+
						Math.abs(intensity[i-1][j-1])+
						Math.abs(intensity[i-1][j]-intensity[i+1][j])+
						Math.abs(intensity[i-1][j-1])+
						Math.abs(intensity[i-1][j-2])+
						Math.abs(intensity[i][j-1]));

			}
			else
			{
				v=(Math.abs(intensity[i-2][j-1])+
						Math.abs(intensity[i-2][j+1])+
						Math.abs(intensity[i-1][j-2]-intensity[i+1][j+2])+
						Math.abs(intensity[i-1][j-1]-intensity[i+1][j+1])+
						Math.abs(intensity[i-1][j]-intensity[i+1][j])+
						Math.abs(intensity[i-1][j+1]-intensity[i-1][j-1])+
						Math.abs(intensity[i-1][j+2]-intensity[i-1][j-2])+
						Math.abs(intensity[i][j-1]-intensity[i][j+1]));

			}
		}
		else if(j==h-2)
		{
			if(i==w-1)
			{
				v=(Math.abs(intensity[i-2][j-1])+
						Math.abs(intensity[i-2][j+1])+
						Math.abs(intensity[i-1][j-2])+
						Math.abs(intensity[i-1][j-1])+
						Math.abs(intensity[i-1][j])+
						Math.abs(intensity[i-1][j+1]-intensity[i-1][j-1])+
						Math.abs(intensity[i-1][j-2])+
						Math.abs(intensity[i][j-1]-intensity[i][j+1]));

			}
			else
			{
				v=(Math.abs(intensity[i-2][j-1]-intensity[i+2][j+1])+
						Math.abs(intensity[i-2][j+1]-intensity[i+2][j-1])+
						Math.abs(intensity[i-1][j-2])+
						Math.abs(intensity[i-1][j-1]-intensity[i+1][j+1])+
						Math.abs(intensity[i-1][j]-intensity[i+1][j])+
						Math.abs(intensity[i-1][j+1]-intensity[i-1][j-1])+
						Math.abs(intensity[i-1][j-2])+
						Math.abs(intensity[i][j-1]-intensity[i][j+1]));
	
			}
		}
	}
	else if(i==w-1|j==h-1)
	{
		if(i==w-1&&j==h-1)
		{
			v=(Math.abs(intensity[i-2][j-1])+
					
					Math.abs(intensity[i-1][j-2])+
					Math.abs(intensity[i-1][j-1])+
					Math.abs(intensity[i-1][j])+
					Math.abs(intensity[i-1][j-1])+
					Math.abs(intensity[i-1][j-2])+
					Math.abs(intensity[i][j-1]));

		}
		else if(j==h-1)
		{
			v=(Math.abs(intensity[i-2][j-1])+
					Math.abs(intensity[i+2][j-1])+
					Math.abs(intensity[i-1][j-2])+
					Math.abs(intensity[i-1][j-1])+
					Math.abs(intensity[i-1][j]-intensity[i+1][j])+
					Math.abs(intensity[i-1][j-1])+
					Math.abs(intensity[i-1][j-2])+
					Math.abs(intensity[i][j-1]));

		}
		else if(i==w-1)
		{
			v=(Math.abs(intensity[i-2][j-1])+
					Math.abs(intensity[i-2][j+1])+
					Math.abs(intensity[i-1][j-2])+
					Math.abs(intensity[i-1][j-1])+
					Math.abs(intensity[i-1][j])+
					Math.abs(intensity[i-1][j+1]-intensity[i-1][j-1])+
					Math.abs(intensity[i-1][j+2]-intensity[i-1][j-2])+
					Math.abs(intensity[i][j-1]-intensity[i][j+1]));
	
		}
	}
	else{
	
		v=(Math.abs(intensity[i-2][j-1]-intensity[i+2][j+1])+
				Math.abs(intensity[i-2][j+1]-intensity[i+2][j-1])+
				Math.abs(intensity[i-1][j-2]-intensity[i+1][j+2])+
				Math.abs(intensity[i-1][j-1]-intensity[i+1][j+1])+
				Math.abs(intensity[i-1][j]-intensity[i+1][j])+
				Math.abs(intensity[i-1][j+1]-intensity[i-1][j-1])+
				Math.abs(intensity[i-1][j+2]-intensity[i-1][j-2])+
				Math.abs(intensity[i][j-1]-intensity[i][j+1]));
	
	}
	heuristics[i][j]=v;
	}

	}
for(int lv=0;lv<w;lv++)
{
	for(int lv1=0;lv1<h;lv1++)
	{
		
	imagedata[lv+(lv1*w)]=heuristics[lv][lv1];
}
}
z=Math.max(imagedata[0], imagedata[1]);
for(int lv=2;lv<w*h;lv++)
{
	z=Math.max(z, imagedata[lv]);
}

int i,j;
i=w/2;
j=h/2;


//System.out.println(z);

for(int lv=0;lv<w;lv++)
{
	for(int lv1=0;lv1<h;lv1++)
	{
		heuristics[lv][lv1]=(double)(heuristics[lv][lv1]/z);
	}
}
//CHECKED......................
	



int g[]=new int[8];
for(int lv=0;lv<8;lv++)
{
	g[lv]=1;
}
int l;

//System.out.println(i);
//System.out.println(j);
for(int x=0;x<4;x++)
{
	
for(int y=0;y<k;y++)
{
		l=0;
while(l<40)
{
	
	
		if(i==0)
		{
		
			if(j==0)
			{
				n1=0;
				n2=0;
				n3=0;
				n4=0;
				n7=0;
				n5=pheromone[i][j+1]*Math.pow(heuristics[i][j+1],be)*g[4];
				n6=pheromone[i+1][j]*Math.pow(heuristics[i+1][j],be)*g[5];
				n8=pheromone[i+1][j+1]*Math.pow(heuristics[i+1][j+1],be)*g[7];
		
		d=n1+n2+n3+n4+n5+n6+n7+n8;
			}
			else if(j==h-1)
			{
				n1=0;
				n3=0;
				n4=0;
				n5=0;
				n8=0;
				n2=pheromone[i][j-1]*Math.pow(heuristics[i][j-1],be)*g[1];
				n6=pheromone[i+1][j]*Math.pow(heuristics[i+1][j],be)*g[5];	
				n7=pheromone[i+1][j-1]*Math.pow(heuristics[i+1][j-1],be)*g[6];
				d=n1+n2+n3+n4+n5+n6+n7+n8;
			}
			else
			{
				n1=0;
				n3=0;
				n4=0;
				n2=pheromone[i][j-1]*Math.pow(heuristics[i][j-1],be)*g[1];
				n5=pheromone[i][j+1]*Math.pow(heuristics[i][j+1],be)*g[4];
				n6=pheromone[i+1][j]*Math.pow(heuristics[i+1][j],be)*g[5];
				n7=pheromone[i+1][j-1]*Math.pow(heuristics[i+1][j-1],be)*g[6];
				n8=pheromone[i+1][j+1]*Math.pow(heuristics[i+1][j+1],be)*g[7];
				d=n1+n2+n3+n4+n5+n6+n7+n8;
			}
		}
		else if(i==w-1)
		{
			if(j==0)
			{
				n1=0;
				n2=0;
				n6=0;
				n7=0;
				n8=0;
				n3=pheromone[i-1][j]*Math.pow(heuristics[i-1][j],be)*g[2];
				n4=pheromone[i-1][j+1]*Math.pow(heuristics[i-1][j+1],be)*g[3];
				n5=pheromone[i][j+1]*Math.pow(heuristics[i][j+1],be)*g[4];
				d=n1+n2+n3+n4+n5+n6+n7+n8;
			}
			else if(j==h-1)
			{
				n1=pheromone[i-1][j-1]*Math.pow(heuristics[i-1][j-1],be)*g[0];
				n2=pheromone[i][j-1]*Math.pow(heuristics[i][j-1],be)*g[1];
				n3=pheromone[i-1][j]*Math.pow(heuristics[i-1][j],be)*g[2];
				n4=0;
				n5=0;
				n6=0;
				n7=0;
				n8=0;
				d=n1+n2+n3+n4+n5+n6+n7+n8;
				
			}
			else
			{
				n1=pheromone[i-1][j-1]*Math.pow(heuristics[i-1][j-1],be)*g[0];
				n2=pheromone[i][j-1]*Math.pow(heuristics[i][j-1],be)*g[1];
				n3=pheromone[i-1][j]*Math.pow(heuristics[i-1][j],be)*g[2];
				n4=pheromone[i-1][j+1]*Math.pow(heuristics[i-1][j+1],be)*g[3];
				n5=pheromone[i][j+1]*Math.pow(heuristics[i][j+1],be)*g[4];
				n6=0;
				n7=0;
				n8=0;
				d=n1+n2+n3+n4+n5+n6+n7+n8;
			}
		}
		else if(j==0)
		{
            n1=0;
			n2=0;   
			n3=pheromone[i-1][j]*Math.pow(heuristics[i-1][j],be)*g[2];
			n4=pheromone[i-1][j+1]*Math.pow(heuristics[i-1][j+1],be)*g[3];
			n5=pheromone[i][j+1]*Math.pow(heuristics[i][j+1],be)*g[4];
			n6=pheromone[i+1][j]*Math.pow(heuristics[i+1][j],be)*g[5];
			n7=pheromone[i+1][j-1]*Math.pow(heuristics[i+1][j-1],be)*g[6];
		    n8=0;
		    d=n1+n2+n3+n4+n5+n6+n7+n8;
		}
		else if(j==h-1)
		{
			n1=pheromone[i-1][j-1]*Math.pow(heuristics[i-1][j-1],be)*g[0];
			n2=pheromone[i][j-1]*Math.pow(heuristics[i][j-1],be)*g[1];
			n3=pheromone[i-1][j]*Math.pow(heuristics[i-1][j],be)*g[2];
			n6=pheromone[i+1][j]*Math.pow(heuristics[i+1][j],be)*g[5];
			n7=pheromone[i+1][j-1]*Math.pow(heuristics[i+1][j-1],be)*g[6];
			n5=0;
			n4=0;
			n8=0;
			d=n1+n2+n3+n4+n5+n6+n7+n8;
		}
			
		
						//check=check+1;
		
		else
	{   
	n1=pheromone[i-1][j-1]*Math.pow(heuristics[i-1][j-1],be)*g[0];
	n2=pheromone[i][j-1]*Math.pow(heuristics[i][j-1],be)*g[1];
	n3=pheromone[i-1][j]*Math.pow(heuristics[i-1][j],be)*g[2];
	n4=pheromone[i-1][j+1]*Math.pow(heuristics[i-1][j+1],be)*g[3];
	n5=pheromone[i][j+1]*Math.pow(heuristics[i][j+1],be)*g[4];
	n6=pheromone[i+1][j]*Math.pow(heuristics[i+1][j],be)*g[5];
	n7=pheromone[i+1][j-1]*Math.pow(heuristics[i+1][j-1],be)*g[6];
	n8=pheromone[i+1][j+1]*Math.pow(heuristics[i+1][j+1],be)*g[7];
	d=n1+n2+n3+n4+n5+n6+n7+n8;
	}
	 
probability[0]=Math.abs(n1/d);
probability[1]=Math.abs(n2/d);
probability[2]=Math.abs(n3/d);
probability[3]=Math.abs(n4/d);
probability[4]=Math.abs(n5/d);
probability[5]=Math.abs(n6/d);
probability[6]=Math.abs(n7/d);
probability[7]=Math.abs(n8/d);
q=Math.max(probability[0], probability[1]);
 int choice=0;
for(int a=2;a<8;a++)
{
	q=Math.max(q, probability[a]);
	//System.out.println(q);
}
for(int n=0;n<8;n++)
{
	if(q==probability[n])
	{
		choice=n;
	
	}
}
int  ch=choice+1;

switch(ch)
{
case 1:
{
	
	
pheromone[i-1][j-1]=((0.9)*pheromone[i-1][j-1])+(heuristics[i-1][j-1]*0.1);	
i=i-1;
j=j-1;
g[7]=0;
g[6]=1;
g[5]=1;
g[4]=1;
g[3]=1;
g[2]=1;
g[1]=1;
g[0]=1;
//check=check+1;

//check=check+1;	
break;
}
case 2:
{
	
	
	pheromone[i][j-1]=((0.9)*pheromone[i][j-1])+(heuristics[i][j-1]*0.1);
	j=j-1;
	g[4]=0;
	g[7]=1;
	g[6]=1;
	g[5]=1;
	g[3]=1;
	g[2]=1;
	g[1]=1;
	g[0]=1;
	//check=check+1;	
	
	//check=check+1;	
	break;
}
case 3:
{
	
	
	pheromone[i-1][j]=((0.9)*pheromone[i-1][j])+(heuristics[i-1][j]*0.1);
i=i-1;
g[5]=0;
g[6]=1;
g[7]=1;
g[4]=1;
g[3]=1;
g[2]=1;
g[1]=1;
g[0]=1;
	//check=check+1;
	
	//check=check+1;
	break;
}
case 4:
{
	
		pheromone[i-1][j+1]=((0.9)*pheromone[i-1][j+1])+(heuristics[i-1][j+1]*0.1);
i=i-1;
j=j+1;
g[6]=0;
g[7]=1;
g[5]=1;
g[4]=1;
g[3]=1;
g[2]=1;
g[1]=1;
g[0]=1;
//check=check+1;

	//check=check+1;	
	break;
}
case 5:
{
	//System.out.println("ok");
	pheromone[i][j+1]=((0.9)*pheromone[i][j+1])+(heuristics[i][j+1]*0.1);
	j=j+1;
	g[1]=0;
	g[6]=1;
	g[7]=1;
	g[5]=1;
	g[4]=1;
	g[3]=1;
	g[2]=1;
	g[0]=1;
//	check=check+1;
	
	//check=check+1;
	break;
}
case 6:
{

	pheromone[i+1][j]=((0.9)*pheromone[i+1][j])+(heuristics[i+1][j]*0.1);
	i=i+1;
	g[2]=0;
	g[6]=1;
	g[7]=1;
	g[5]=1;
	g[4]=1;
	g[3]=1;
	g[1]=1;
	g[0]=1;
	//check=check+1;
	
	//check=check+1;
	break;
}
case 7:
{

	pheromone[i+1][j-1]=((0.9)*pheromone[i+1][j-1])+(heuristics[i+1][j-1]*0.1);
	i=i+1;
	j=j-1;
	g[3]=0;
	g[6]=1;
	g[7]=1;
	g[5]=1;
	g[4]=1;
	g[2]=1;
	g[1]=1;
	g[0]=1;
	//check=check+1;	
	

break;
}
case 8:
{
	
	pheromone[i+1][j+1]=((0.9)*pheromone[i+1][j+1])+(heuristics[i+1][j+1]*0.1);
	i=i+1;
	j=j+1;
	g[0]=0;
	g[6]=1;
	g[7]=1;
	g[5]=1;
	g[4]=1;
	g[3]=1;
	g[2]=1;
	g[1]=1;
	g[0]=1;
	//check=check+1;
	
	
	break;
}
default :
{
	
	//check=check+1;	
	break;
}


}
	
l=l+1;
}
}
	
	for(int u=0;u<w;u++)
	{
	for(int v=0;v<h;v++)
	{
				pheromone[u][v]=((0.95)*pheromone[u][v])+(0.05*0.1);
	//check=check+1;
		}
	}
	
}

		

for(int u=0;u<w;u++)
	{
	for(int v=0;v<h;v++)
	{

t0=t0+pheromone[u][v];
	}
	}
t0=t0/(w*h);

for(int lv=0;lv<w;lv++)
{
	for(int lv1=0;lv1<h;lv1++)
	{
		if(pheromone[lv][lv1]>=t0)
		{
			mu[lv][lv1]=pheromone[lv][lv1];
		
		}
		else if(pheromone[lv][lv1]<t0)
		{
			ml[lv][lv1]=pheromone[lv][lv1];
			//check=check+1;
		}
		else{
			mu[lv][lv1]=0;
			ml[lv][lv1]=0;
		}
	}
}
for(int z=0;z<w;z++)
{
	for(int z1=0;z1<h;z1++)
	{
		if(ml[z][z1]<=t0)
		{
			mlo=mlo+ml[z][z1];
			hl=hl+1;
		}
		 if(mu[z][z1]>=t0)
		{
			mh=mh+mu[z][z1];
			hh=hh+1;
			check=check+1;
		}
	}
			
}
if(hh!=0)
{
mh=mh/hh;
}
else{
	mh=0;
}
mlo=mlo/hl;
t0=(mh+mlo)/2;



//System.out.println(t0);
//System.out.println(pheromone [156][178]);

for(int f1=0;f1<w;f1++)
{
	for(int f2=0;f2<h;f2++)
	{
		if(pheromone[f1][f2]>=t0)
		{
			edge[f1][f2]=1;
			//System.out.println(pheromone[f1][f2]);
		//check=check+1;
		}
		else{
			edge[f1][f2]=0;
		//	System.out.println(pheromone[f1][f2]);
		}
		
	}
}

//System.out.println(intensity[500][0]);
//System.out.println(heuristics[78][35]);
//System.out.println(hh+" "+mlo+" "+" "+ml[50][50]);
int ks=0;
for(int f1=0;f1<w;f1++)
{
	for(int f2=0;f2<h;f2++)
	{
		if(edge[f1][f2]==1)
		{
		
			ks=ks+1;
		}
		else{
			//System.out.println("not edges");
		}
	}
}
System.out.println(ks);
for(int f1=0;f1<w;f1++)
{
	for(int f2=0;f2<h;f2++)
	{
		pixels[f1+(f2*w)]=(int)edge[f1][f2];
	}
}
for(int f1=0;f1<w*h;f1++)
{
	if(pixels[f1]==0)
	{
		pixels[f1]=Color.BLACK.getRGB();
	}
	else{
		pixels[f1]=Color.WHITE.getRGB();;
	}
}
in=new ImageIcon(in).getImage();
BufferedImage s=new BufferedImage(in.getWidth(null),in.getHeight(null),BufferedImage.TYPE_INT_RGB);
BufferedImage  d=new BufferedImage(in.getWidth(null),in.getHeight(null),BufferedImage.TYPE_INT_RGB);
JFrame res=new JFrame("Image Processor");	
Graphics rg=s.createGraphics();
rg.drawImage(in,0,0,null);
rg.dispose();
float weight[]={0.0f,-1.0f,0.0f,
		 -1.0f,4.0f,-1.0f,
		 0.0f,-1.0f,0.0f
		 
};
Kernel mykernel=new Kernel(3,3,weight);
ConvolveOp edgeimage=new ConvolveOp(mykernel);
d=edgeimage.filter(s, d);

Image goi=Toolkit.getDefaultToolkit().createImage(d.getSource());


JFrame r=new JFrame("Image Processor");
 BufferedImage img=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
img.setRGB(0,0,w,h,pixels,0,w);
 Image go=Toolkit.getDefaultToolkit().createImage(img.getSource());
 
f.dispose();
Finterface fi=new Finterface(r,goi);
//System.out.println("verify"+(h*4*w));


	}
	}



