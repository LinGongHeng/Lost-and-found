package newtest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Image;

public class addlist extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
   
	//���ھ������޸Ļ������
	int style=0;
	Control one;
	
	//�������ݿ����
	PreparedStatement ps=null;
	ResultSet rs=null;
	//�������ݿ��ļ�
	String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=У԰ʧ������ϵͳ";
	String userName="sa";
	String userPwd="571820";
	
	JButton jb1,jb2,jb_image;
	JTable jt=null;
	JScrollPane jsp;
	JPanel jp;
	JLabel jl1,jl2,jl3,jl4,jl5;
	JTextField jt1,jt2,jt3;
	JTextArea jt4;
	JComboBox xx;
	String SelectedItem;
	String s_id;
	JLabel jLabel1;
	ImageIcon iocn;
	String stores;
	
	public addlist(String s){
		s_id=s;
		
        this.setLocation(320,200);
        this.setSize(755,400);
        this.setResizable(false);
        this.setTitle("��Ӽ�¼");
        
        jp=new JPanel();
        jp.setLayout(null);
        this.add(jp);
        
        jLabel1 =new JLabel();
        jLabel1.setBounds(110,85,250,280);
        jLabel1.setBorder(BorderFactory.createLineBorder(Color.black));
        jLabel1.setOpaque(true);
        jp.add(jLabel1);
        
        jl1=new JLabel("ʰ������",JLabel.CENTER);
        jl1.setBorder(BorderFactory.createLineBorder(Color.red));
        jl1.setOpaque(true);
        jl1.setBackground(Color.LIGHT_GRAY); 
        jl1.setBounds(0,0,150,30);
        jp.add(jl1);
        
        jl2=new JLabel("ʰ��ʱ��",JLabel.CENTER);
        jl2.setBorder(BorderFactory.createLineBorder(Color.red));
        jl2.setOpaque(true);
        jl2.setBackground(Color.LIGHT_GRAY); 
        jl2.setBounds(150,0,150,30);
        jp.add(jl2);
        
        jl3=new JLabel("ʰ��ص�",JLabel.CENTER);
        jl3.setBorder(BorderFactory.createLineBorder(Color.red));
        jl3.setOpaque(true);
        jl3.setBackground(Color.LIGHT_GRAY); 
        jl3.setBounds(300,0,150,30);
        jp.add(jl3);
        
        jl4=new JLabel("ʰ������",JLabel.CENTER);
        jl4.setBorder(BorderFactory.createLineBorder(Color.red));
        jl4.setOpaque(true);
        jl4.setBackground(Color.LIGHT_GRAY); 
        jl4.setBounds(450,0,150,30);
        jp.add(jl4);
        
        jl5=new JLabel("ʰ������",JLabel.CENTER);
        jl5.setBorder(BorderFactory.createLineBorder(Color.red));
        jl5.setOpaque(true);
        jl5.setBackground(Color.LIGHT_GRAY); 
        jl5.setBounds(600,0,150,30);
        jp.add(jl5);
       
        jt1=new JTextField();
        jt1.setDocument(new NumberLenghtLimitedDmt(20,NumberLenghtLimitedDmt.nolimit));
        jt1.setBounds(0,30,151,50);
        jp.add(jt1);
        
        jt2=new JTextField();
        jt2.setDocument(new NumberLenghtLimitedDmt(8,NumberLenghtLimitedDmt.nolimit));        
        jt2.setBounds(150,30,151,50);
        jp.add(jt2);
        
        jt3=new JTextField();
        jt3.setBounds(300,30,151,50);
        jp.add(jt3);
        
        jt4=new JTextArea();
        jt4.setBorder (BorderFactory.createLineBorder(Color.gray));
        jt4.setLineWrap(true);        //�����Զ����й��� 
        JScrollPane scroll = new JScrollPane(jt4);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        scroll.setBounds(450,30,152,50);
        jp.add(scroll);
        
        xx = new JComboBox();//����
        xx.addItem("У԰��");//���ѡ��
        xx.addItem("Ǯ��");//���ѡ��
        xx.addItem("�鱾");//���ѡ��
        xx.addItem("�ֻ�");//���ѡ��
        xx.addItem("����");//���ѡ��
        xx.addActionListener(this);
        xx.setBounds(600,29,150,50);
        jp.add(xx);
       
	    jb1=new JButton("ȷ�����");       
	    jb1.setBounds(450,235,90,30);
	    jp.add(jb1);
	    
 	    jb2=new JButton("ȡ�����");      
	    jb2.setBounds(600,235,90,30);
	    jp.add(jb2);
	    
	    jb_image=new JButton("�ϴ�ͼƬ");
	    jb_image.setBounds(10,85,90,30);
	    jp.add(jb_image);
	    
	    jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb_image.addActionListener(this);
        
	    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	    this.setVisible(true);  
	}

	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==jb1)
		{

			if(update()==true)
			{
			   this.setVisible(false);
			   JOptionPane.showMessageDialog(null,"��ӳɹ�","��Ϣ",JOptionPane.WARNING_MESSAGE);  //��Ϸ������������ʾ��;
			}
		}
		if(e.getSource()==jb2)
		{
			 this.setVisible(false);
			  JOptionPane.showMessageDialog(null,"���ʧ��","��Ϣ",JOptionPane.WARNING_MESSAGE);  //��Ϸ������������ʾ��;
			
		}
		if(e.getSource()==xx)
		{
			SelectedItem=String.valueOf(xx.getSelectedItem()).trim();
		}
		
		if(e.getSource()==jb_image)
			getimage();
			
	}
	
	public boolean update()
	{
		if(jt1.getText().equals(""))
		 {
			JOptionPane.showMessageDialog(null,"ʰ�����Ʋ���Ϊ��","",JOptionPane.ERROR_MESSAGE);  //��Ϸ������������ʾ��;
		     return false;
		 }
		if(jt2.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null,"ʰ��ʱ�䲻��Ϊ��","",JOptionPane.ERROR_MESSAGE);  //��Ϸ������������ʾ��;
			return false;
		}
		if(jt2.getText().length()<8)
		{
			JOptionPane.showMessageDialog(null,"ʰ��ʱ���ʽ����\n  �����ʽ�磺20000203","",JOptionPane.ERROR_MESSAGE);//��Ϸ������������ʾ��;
			return false;
		}
		if(jt3.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null,"�������һ��ʰ�����ɫ�����ӵ�","",JOptionPane.ERROR_MESSAGE);  //��Ϸ������������ʾ��;
			return false;
		}
		if(jt3.getText().length()>120)
		{
			JOptionPane.showMessageDialog(null,"ʰ������̫���������Щ","",JOptionPane.ERROR_MESSAGE);  //��Ϸ������������ʾ��;
			return false;
		}
		if(SelectedItem==null)
		{
			JOptionPane.showMessageDialog(null,"δѡ��ʰ������","",JOptionPane.ERROR_MESSAGE);  //��Ϸ������������ʾ��;
			return false;
		}
		
		try
	     { 
			Connection ct=null;
			Statement stmt=null;
			UUID uuid = UUID.randomUUID(); 
			String a1="";

	      ct=DriverManager.getConnection(dbURL,userName,userPwd);
	      stmt=ct.createStatement();//����SQL�������
	      a1 ="INSERT INTO ʰ��  VALUES('"+uuid.toString().substring(0,8)+"','"+jt1.getText().trim()+"','"+jt2.getText().trim()+"','"+jt3.getText().trim()+"','"+SelectedItem.trim()+"','"+jt4.getText().trim()+"','"+s_id.trim()+"','"+stores+"');";//��������SQL��      	      
	      stmt.executeUpdate(a1);
	      System.out.println(uuid.toString().substring(0,8)); 
		  stmt.close();
	      ct.close();
	     }
	     catch(Exception e)
	     {
	    	System.out.println("���ݿ�����ʧ��");
	    	e.printStackTrace();
	     }
	     finally
	     {	      
	     }

		return true;
	}

	public void getimage()
	{	  
	  JFileChooser jfc = new JFileChooser();
	  if(jfc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION )
	  {
		  iocn =new ImageIcon(jfc.getSelectedFile().getAbsolutePath());
		  int y=280;
		  int x;
	      x =(iocn.getIconWidth()/iocn.getIconHeight()*280>250)?250:iocn.getIconWidth()/iocn.getIconHeight()*280;

		  iocn=new ImageIcon(new ImageIcon(jfc.getSelectedFile().getAbsolutePath()).getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
		  stores= "<����������>"+String.valueOf(jfc.getSelectedFile().getAbsolutePath());
		  jLabel1.setIcon(iocn);
	  }	      
	}
}

class addlist1 extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
   
	//���ھ������޸Ļ������
	int style=0;
	Control one;
	
	//�������ݿ����
	PreparedStatement ps=null;
	
	//�������ݿ��ļ�
	String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=У԰ʧ������ϵͳ";
	String userName="sa";
	String userPwd="571820";
	
	JButton jb1,jb2,jb_image;
	JTable jt=null;
	JScrollPane jsp;
	JPanel jp;
	JLabel jl1,jl2,jl3,jl4,jl5;
	JTextField jt1,jt2,jt3;
	JTextArea jt4;
	JComboBox xx;
	String SelectedItem;
	String s_id;
	JLabel jLabel1;
	ImageIcon iocn;
	String stores;
	
	public addlist1(String s,int i, Control j){
		s_id=s;
		this.style=i;
		this.one=j;
		
        this.setLocation(320,200);
        this.setSize(755,400);
        this.setResizable(false);
        if(style==1)
           this.setTitle("�޸ļ�¼");
        else
           this.setTitle("��Ӽ�¼");
        
        jp=new JPanel();
        jp.setLayout(null);
        this.add(jp);
        
        jLabel1 =new JLabel();
        jLabel1.setBounds(110,85,250,280);
        jLabel1.setBorder(BorderFactory.createLineBorder(Color.black));
        jLabel1.setOpaque(true);
        jp.add(jLabel1);
        
        
        jl1=new JLabel("ʰ������",JLabel.CENTER);
        jl1.setBorder(BorderFactory.createLineBorder(Color.red));
        jl1.setOpaque(true);
        jl1.setBackground(Color.LIGHT_GRAY); 
        jl1.setBounds(0,0,150,30);
        jp.add(jl1);
        
        jl2=new JLabel("ʰ��ʱ��",JLabel.CENTER);
        jl2.setBorder(BorderFactory.createLineBorder(Color.red));
        jl2.setOpaque(true);
        jl2.setBackground(Color.LIGHT_GRAY); 
        jl2.setBounds(150,0,150,30);
        jp.add(jl2);
        
        jl3=new JLabel("ʰ��ص�",JLabel.CENTER);
        jl3.setBorder(BorderFactory.createLineBorder(Color.red));
        jl3.setOpaque(true);
        jl3.setBackground(Color.LIGHT_GRAY); 
        jl3.setBounds(300,0,150,30);
        jp.add(jl3);
        
        jl4=new JLabel("ʰ������",JLabel.CENTER);
        jl4.setBorder(BorderFactory.createLineBorder(Color.red));
        jl4.setOpaque(true);
        jl4.setBackground(Color.LIGHT_GRAY); 
        jl4.setBounds(450,0,150,30);
        jp.add(jl4);
        
        jl5=new JLabel("ʰ������",JLabel.CENTER);
        jl5.setBorder(BorderFactory.createLineBorder(Color.red));
        jl5.setOpaque(true);
        jl5.setBackground(Color.LIGHT_GRAY); 
        jl5.setBounds(600,0,150,30);
        jp.add(jl5);
       
        jt1=new JTextField();
        jt1.setDocument(new NumberLenghtLimitedDmt(20,NumberLenghtLimitedDmt.nolimit));
        jt1.setBounds(0,30,151,50);
        jp.add(jt1);
        
        jt2=new JTextField();
        jt2.setDocument(new NumberLenghtLimitedDmt(8,NumberLenghtLimitedDmt.nolimit));        
        jt2.setBounds(150,30,151,50);
        jp.add(jt2);
        
        jt3=new JTextField();
        jt3.setBounds(300,30,151,50);
        jp.add(jt3);
        
        jt4=new JTextArea();
        jt4.setBorder (BorderFactory.createLineBorder(Color.gray));
        jt4.setLineWrap(true);        //�����Զ����й��� 
        JScrollPane scroll = new JScrollPane(jt4);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        scroll.setBounds(450,30,152,50);
        jp.add(scroll);
        
        xx = new JComboBox();//����
        xx.addItem("У԰��");//���ѡ��
        xx.addItem("Ǯ��");//���ѡ��
        xx.addItem("�鱾");//���ѡ��
        xx.addItem("�ֻ�");//���ѡ��
        xx.addItem("����");//���ѡ��
        xx.addActionListener(this);
        xx.setBounds(600,29,150,50);
        jp.add(xx);
        
        if(style==1)
           jb1=new JButton("ȷ���޸�");
        else
	       jb1=new JButton("ȷ�����");
        
	    jb1.setBounds(450,235,90,30);
	    jp.add(jb1);
	    
        if(style==1)
           jb2=new JButton("ȡ���޸�");
         else
 	       jb2=new JButton("ȡ�����");
        
	    jb2.setBounds(600,235,90,30);
	    jp.add(jb2);
	    
	    jb_image=new JButton("����ͼƬ");
	    jb_image.setBounds(10,85,90,30);
	    jp.add(jb_image);
	    
	    jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb_image.addActionListener(this);
        
       //�����޸�ǰ�ĳ�ʼֵ
        if(style==1)
        {
          jt1.setText(one.reads0);
          jt2.setText(one.reads1);
          jt3.setText(one.reads2);
          jt4.setText(one.reads3);
          stores=one.readpic;
          xx.setSelectedItem(one.reads4);
        }
        
	    this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	    this.setVisible(true);  
	}


	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==jb1)
		{ 
			if(update()==true)
			{
			   this.setVisible(false);
			   JOptionPane.showMessageDialog(null,"�޸ļ�¼�ɹ�","��Ϣ",JOptionPane.WARNING_MESSAGE);  //��Ϸ������������ʾ��;
			}
		 }
		if(e.getSource()==jb2)
		{
			  this.setVisible(false);
			  JOptionPane.showMessageDialog(null,"�޸ļ�¼ʧ��","��Ϣ",JOptionPane.WARNING_MESSAGE);  //��Ϸ������������ʾ��;
		}

		if(e.getSource()==xx)
		{
			SelectedItem=String.valueOf(xx.getSelectedItem()).trim();
		}
		
		if(e.getSource()==jb_image)
			getimage();
	}
	
	public boolean update()
	{
		if(jt1.getText().equals(""))
		 {
			JOptionPane.showMessageDialog(null,"ʰ�����Ʋ���Ϊ��","",JOptionPane.ERROR_MESSAGE);  //��Ϸ������������ʾ��;
		     return false;
		 }
		if(jt2.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null,"ʰ��ʱ�䲻��Ϊ��","",JOptionPane.ERROR_MESSAGE);  //��Ϸ������������ʾ��;
			return false;
		}
		if(jt2.getText().length()<8)
		{
			JOptionPane.showMessageDialog(null,"ʰ��ʱ���ʽ����\n  �����ʽ�磺20000203","",JOptionPane.ERROR_MESSAGE);//��Ϸ������������ʾ��;
			return false;
		}
		if(jt4.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null,"�������һ��ʰ�����ɫ�����ӵ�","",JOptionPane.ERROR_MESSAGE);  //��Ϸ������������ʾ��;
			return false;
		}
		if(jt4.getText().length()>120)
		{
			JOptionPane.showMessageDialog(null,"ʰ������̫���������Щ","",JOptionPane.ERROR_MESSAGE);  //��Ϸ������������ʾ��;
			return false;
		}
		if(SelectedItem==null)
		{
			JOptionPane.showMessageDialog(null,"δѡ��ʰ������","",JOptionPane.ERROR_MESSAGE);  //��Ϸ������������ʾ��;
			return false;
		}
		
		try
	     { 
			Connection ct=null;
			Statement stmt=null;
			UUID uuid = UUID.randomUUID(); 
			String a1="";
			
	      ct=DriverManager.getConnection(dbURL,userName,userPwd);
	      stmt=ct.createStatement();//����SQL�������
	      a1 = "UPDATE ʰ��   SET ʰ������='"+jt1.getText().trim()+"',ʰ��ʱ��='"+jt2.getText().trim()+"',ʰ��ص�='"+jt3.getText().trim()+"',ʰ������='"+jt4.getText().trim()+"',ʰ��ͼƬ='"+stores+"' where ʰ����='"+one.readid.trim()+"';"; 
//	      System.out.println(jt4.getText().trim());
	      stmt.executeUpdate(a1);
		  stmt.close();
	      ct.close();
	     }
	     catch(Exception e)
	     {
	    	e.getStackTrace();
	     }
	     finally
	     {	      
	     }

		return true;
	}
	
	public void getimage()
	{	  
	  JFileChooser jfc = new JFileChooser();
	  if(jfc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION )
	  {
		  iocn =new ImageIcon(jfc.getSelectedFile().getAbsolutePath());
		  int y=280;
		  int x;
          x =(iocn.getIconWidth()/iocn.getIconHeight()*280>250)?250:iocn.getIconWidth()/iocn.getIconHeight()*280;

		  iocn=new ImageIcon(new ImageIcon(jfc.getSelectedFile().getAbsolutePath()).getImage().getScaledInstance(x,y, Image.SCALE_DEFAULT));
		  stores= "<����������>"+String.valueOf(jfc.getSelectedFile().getAbsolutePath());
		  jLabel1.setIcon(iocn);
	  }	      
	}
}