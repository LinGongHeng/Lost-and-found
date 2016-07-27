//复写Panel使得界面内插入图片
package newtest;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

//重写MyPanel类,设置背景
public class MyPanel extends JPanel{ 

	private static final long serialVersionUID = 1L;
	
	protected void paintComponent(Graphics g) {
		Image iocn1=new ImageIcon("2.jpg").getImage();
		g.drawImage(iocn1,0,0,393,285,this);
	}
}

class MyPanel1 extends JPanel{ 

	private static final long serialVersionUID = 1L;
	
	protected void paintComponent(Graphics g) {
		Image iocn1=new ImageIcon("3.jpg").getImage();
		g.drawImage(iocn1,0,0,800,600,this);
	}
}

class JPanel2 extends JPanel{ 

	private static final long serialVersionUID = 1L;
	
	protected void paintComponent(Graphics g) {
		Image iocn1=new ImageIcon("5.jpg").getImage();
		g.drawImage(iocn1,0,-100,800,500,this);
	}
}

class JPanel3 extends JPanel{ 

	private static final long serialVersionUID = 1L;
	
	protected void paintComponent(Graphics g) {
		Image iocn1=new ImageIcon("5.jpg").getImage();
		g.drawImage(iocn1,-20,-400,800,520,this);
	}
}

class JPanel4 extends JPanel{ 

	private static final long serialVersionUID = 1L;
	
	protected void paintComponent(Graphics g) {
		Image iocn1=new ImageIcon("1.jpg").getImage();
		g.drawImage(iocn1,0,0,800,570,this);
	}
}

class JPanel5 extends JPanel{ 

	private static final long serialVersionUID = 1L;
	
	protected void paintComponent(Graphics g) {
		Image iocn1=new ImageIcon("4.jpg").getImage();
		g.drawImage(iocn1,0,0,400,600,this);
	}
}