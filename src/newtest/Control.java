//��ѯ���水ť�ļ�������
package newtest;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64.Decoder;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

//��������� 
public class Control implements ActionListener{ 
static final int jb1= 1;               //����ť������ 
static final int jb2 = 2; 
static final int jb3 = 3; 
static final int jb4 = 4; 
static final int jb_edit = 5; 
static final int jb_editid = 6; 
static final int jb = 7; 
static int chaxun=0;

int curButton;                                  //��ǰ��ť 
String s1;
testone testone;

String reads0,reads1,reads2,reads3,reads4,readid,readpic;

//�������ݿ����
PreparedStatement ps=null;
PreparedStatement ps1=null;
PreparedStatement ps2=null;
Connection ct=null;
ResultSet rs=null,rs2=null;

//�������ݿ��ļ�
String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=У԰ʧ������ϵͳ";
String userName="sa";
String userPwd="571820";

//���ư�ť��Ĺ��췽�� 
Control(int button,testone testone){ 
curButton = button;
this.testone=testone;
} 
//��ťִ�з��� 
public void actionPerformed (ActionEvent e){ 
int sort;
switch(curButton)
{ 
    case jb1:
    	testone.jt.setEnabled(true);
    	sort=check();
    	if(sort!=0)
          find(sort);
    	else
    	JOptionPane.showMessageDialog(null,"δѡ���ѯ����");  //������ʾ��;
    	chaxun=0;
    break; 
    case jb2:
    	testone.jt.setEnabled(false);
    	new addlist(testone.s_ID); //���
    break; 
    case jb3:
    	testone.jt.setEnabled(true);
    	Mylist();
    	chaxun=1;
    break; 
    case jb4:
    	if(chaxun==1)
    	delete();
    	else
         JOptionPane.showMessageDialog(null,"��ѯ����ʱ����Ȩ��ɾ����¼1");  //������ʾ��;	
    break; 
    case jb_edit:
    	if(chaxun==1)
    	{
    	testone.jt.setEnabled(true);//�����ܹ�ѡ��
    	if(getmodfiline()==true)//�ҵ��޸ĵ���
          new addlist1(testone.s_ID,1,this);//�޸�ʰ���
    	}
    	else
    	JOptionPane.showMessageDialog(null,"��ѯ����ʱ����Ȩ���޸ļ�¼2");  //������ʾ��;	
    break; 
    case jb_editid:
    	  int n = JOptionPane.showConfirmDialog(null,"�Ƿ��޸����룡","��ʾ",JOptionPane.YES_NO_OPTION);
    	  if(n==JOptionPane.YES_OPTION)
            new editid(testone.s_ID);//�޸ĸ�����Ϣ
    	  else
    	    new editinformation(testone.s_ID);//�޸ĸ�����Ϣ����������  
    break;
    case jb:
    	showpicture();
    break;
}
}

//���ѡ�е����ิѡ��
public int check(){
	 int i=0;
	 if(testone.jrb1.isSelected()==true)
		 i=1;
	 if(testone.jrb2.isSelected()==true)
		 i=2;
	 if(testone.jrb3.isSelected()==true)
		 i=3;
	 if(testone.jrb4.isSelected()==true)
		 i=4;
	 if(testone.jrb5.isSelected()==true)
		 i=5;
	 return i;

}

//�ҵ���ͬ����ļ�¼
public void find(int sort)
{
	//��ȡ����
	Vector rowData =new Vector();
	
	//��ȡ����
	String ss = null;
	
	switch(sort)
	{
	   case 1:ss="У԰��";break;
	   case 2:ss="Ǯ��";break;
	   case 3:ss="�鱾";break;
	   case 4:ss="�ֻ�";break;
	   case 5:ss="����";break;
	}
	 
	 try
     {      
      ct=DriverManager.getConnection(dbURL,userName,userPwd);
      ps1=ct.prepareStatement("select *from ʰ��");      
      rs=ps1.executeQuery();
      
       while(rs.next())
       {
    	  Vector hang=new Vector();
          if(rs.getString("ʰ������").trim().equals(ss))
          {	 
           ps2=ct.prepareStatement("select *from �û�");
           rs2=ps2.executeQuery();  
            while(rs2.next())
           {
       //      if(rs2.getString("ѧ��").trim().equals(rs.getString("ѧ��").trim()))
             break;
           } 
             hang.add(rs.getString("ʰ����"));      
             hang.add(rs.getString("ʰ������"));
             hang.add(rs.getString("ʰ������"));
             hang.add(rs.getString("ʰ��ص�"));
             hang.add(rs.getString("ʰ��ʱ��"));
             hang.add(rs.getString("ʰ������"));
             hang.add(rs2.getString("����"));
             hang.add(rs2.getString("��ϵ��ʽ"));
             rowData.add(hang);
         }
       } 
     }
     catch(Exception e)
     {
      System.out.println("���ݿ����ʧ��!����");
      e.printStackTrace();
     }
     finally
     {	      
     }
     Mytable jt1 = new Mytable(rowData,testone.columName);
     testone.jt.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());
     testone.jt.setModel(jt1);
}

//�ҵ��Լ��ļ�¼
private void Mylist() {
	
	String s=null;
	String name=null;
	String contact=null;
	s=testone.s_ID;
	String ss= "00000000";
	//��ȡ����
	Vector rowData =new Vector();
	 try
     { 
      ct=DriverManager.getConnection(dbURL,userName,userPwd);
      ps1=ct.prepareStatement("select *from ʰ��");      
      rs=ps1.executeQuery();
      ps2=ct.prepareStatement("select *from �û�");
      rs2=ps2.executeQuery(); 
       while(rs.next())
       {
    	   Vector hang=new Vector();
          //if(rs.getString("ѧ��").trim().equals(s.trim()))
    	   if((rs.getString("ѧ��").trim().equals(s.trim()))||(s.trim().equals(ss.trim())))
          {	
              //ps2=ct.prepareStatement("select *from �û�");
              //rs2=ps2.executeQuery();  
         //      while(rs2.next())
            //  {
          //      if(rs2.getString("ѧ��").trim().equals(rs.getString("ѧ��").trim()))
         //       	break;
          //    } */
              hang.add(rs.getString("ʰ����"));      
              hang.add(rs.getString("ʰ������"));
              hang.add(rs.getString("ʰ������"));
              hang.add(rs.getString("ʰ��ص�"));
              hang.add(rs.getString("ʰ��ʱ��"));
              hang.add(rs.getString("ʰ������"));
              hang.add(rs.getString("ѧ��"));
              rowData.add(hang);
         }
       }
     }
     catch(Exception e)
     {
    	System.out.println("���������");
    	e.printStackTrace();
      
     }
     finally
     {	      
     }
     Mytable jt1 = new Mytable(rowData,testone.columName);
     testone.jt.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());
     testone.jt.setModel(jt1);
}

//ɾ��ѡ���еļ�¼
private void delete() {
	Statement stmt=null;
	String scode=null;

	if(testone.jt.getSelectedRow()!=-1)
	{
	  int n = JOptionPane.showConfirmDialog(null,"ȷ���Ƿ�Ҫɾ����ǰ��¼��","��ʾ",JOptionPane.YES_NO_OPTION);
	    if(n==JOptionPane.YES_OPTION)
	    {
		  scode = String.valueOf(testone.jt.getValueAt(testone.jt.getSelectedRow(),0)).trim();
		  try
		  {	
			  ct=DriverManager.getConnection(dbURL,userName,userPwd);
			  stmt=ct.createStatement();//����SQL�������
			  String a1="delete from ʰ��  where ʰ���� ="+"'"+scode+"'";
			  stmt.executeUpdate(a1);
		  }
		  catch(Exception e)
		  {
			  System.out.println("���ݿ����ʧ��!4");
			  e.printStackTrace();
		  }
		  finally
		  {	      
		  }
	      Mylist();
	  }
	}	
	else
	 JOptionPane.showMessageDialog(null,"δѡ��ɾ����¼","��ʾ",JOptionPane.WARNING_MESSAGE);  //������ʾ��;
	
}

//�ҵ�ѡ�е���
private boolean getmodfiline()
{
	String scode=null;

	if(testone.jt.getSelectedRow()!=-1)
	{
	  int n = JOptionPane.showConfirmDialog(null,"ȷ���Ƿ�Ҫ�޸ĵ�ǰ��¼��","��ʾ",JOptionPane.YES_NO_OPTION);
	    if(n==JOptionPane.YES_OPTION)
	    {
		  scode = String.valueOf(testone.jt.getValueAt(testone.jt.getSelectedRow(),0)).trim();
		  try
		  {	
			  ct=DriverManager.getConnection(dbURL,userName,userPwd);
			  ps=ct.prepareStatement("select *from ʰ��");
			  rs=ps.executeQuery();
			  while(rs.next())
			   {
			    if(rs.getString("ʰ����").equals(scode))
				  {
				   readid=rs.getString("ʰ����");
				   reads0=rs.getString("ʰ������");
				   reads1=rs.getString("ʰ��ʱ��");
				   reads2=rs.getString("ʰ��ص�");
				   reads3=rs.getString("ʰ������");
				   reads4=rs.getString("ʰ������");
				   readpic=rs.getString("ʰ��ͼƬ");
				  }			  
			   }
			  ps.close();
			  rs.close();
		  }
		  catch(Exception e)
		  {
			  System.out.println("���ݿ����ʧ��!");
		  }
		  finally
		  {	      
		  }
		  return true;
	  }	   
	}	
	else
	 JOptionPane.showMessageDialog(null,"δѡ���޸ļ�¼","��ʾ",JOptionPane.WARNING_MESSAGE);  //������ʾ��;
	 return false;
}

//�ҵ�ѡ�е�ͼƬ
private void showpicture()
{
	String scode=null;

	if(testone.jt.getSelectedRow()!=-1)
	{
		  scode = String.valueOf(testone.jt.getValueAt(testone.jt.getSelectedRow(),0)).trim();
		  try
		  {	
			  ct=DriverManager.getConnection(dbURL,userName,userPwd);
			  ps=ct.prepareStatement("select *from ʰ��");
			  rs=ps.executeQuery();
			  while(rs.next())
			   {
			    if(rs.getString("ʰ����").equals(scode))
				  {	  
			    	if(rs.getString("ʰ��ͼƬ")==null || rs.getString("ʰ��ͼƬ").equals(""))
			          new show("<����������>D:/Java/workspace/newtest/6.jpg");
			    	else
	                  new show(rs.getString("ʰ��ͼƬ"));
			    	break;			    	
			      }
			    }
			  ps.close();
			  rs.close();
		   }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  finally
		  {	      
		  }   
	}	
	else
		JOptionPane.showMessageDialog(null,"δѡ��鿴��ʰ��");  //������ʾ��;
}
} 


class show extends JFrame{
	JLabel jLabel1;
	ImageIcon iocn;
public show(String s)
{ 
    this.setLocation(100,200);
    this.setSize(280,310);
    this.setResizable(false);
    this.setTitle("ʰ��ͼƬ");
    
    jLabel1 =new JLabel();
    jLabel1.setBounds(0,0,250,280);
    jLabel1.setBorder(BorderFactory.createLineBorder(Color.black));
    jLabel1.setOpaque(true);
    
    this.add(jLabel1);
    this.setVisible(true);
    
    iocn=new ImageIcon(s.substring(7));
    int y=280;
	int x;
	 if(iocn.getIconWidth()/iocn.getIconHeight()>1)
		 x =(iocn.getIconWidth()/iocn.getIconHeight()*280>250)?250:iocn.getIconWidth()/iocn.getIconHeight();
	  else
		 x=(iocn.getIconWidth()/iocn.getIconHeight())*280;
      iocn=new ImageIcon(iocn.getImage().getScaledInstance(x, y, 1));
	  jLabel1.setIcon(iocn);
}
}
