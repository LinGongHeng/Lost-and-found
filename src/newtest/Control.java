//查询界面按钮的监听函数
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

//处理控制类 
public class Control implements ActionListener{ 
static final int jb1= 1;               //给按钮分配编号 
static final int jb2 = 2; 
static final int jb3 = 3; 
static final int jb4 = 4; 
static final int jb_edit = 5; 
static final int jb_editid = 6; 
static final int jb = 7; 
static int chaxun=0;

int curButton;                                  //当前按钮 
String s1;
testone testone;

String reads0,reads1,reads2,reads3,reads4,readid,readpic;

//链接数据库变量
PreparedStatement ps=null;
PreparedStatement ps1=null;
PreparedStatement ps2=null;
Connection ct=null;
ResultSet rs=null,rs2=null;

//链接数据库文件
String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=校园失物招领系统";
String userName="sa";
String userPwd="571820";

//控制按钮类的构造方法 
Control(int button,testone testone){ 
curButton = button;
this.testone=testone;
} 
//按钮执行方法 
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
    	JOptionPane.showMessageDialog(null,"未选择查询种类");  //弹出提示框;
    	chaxun=0;
    break; 
    case jb2:
    	testone.jt.setEnabled(false);
    	new addlist(testone.s_ID); //添加
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
         JOptionPane.showMessageDialog(null,"查询种类时，无权限删除记录1");  //弹出提示框;	
    break; 
    case jb_edit:
    	if(chaxun==1)
    	{
    	testone.jt.setEnabled(true);//设置能够选中
    	if(getmodfiline()==true)//找到修改的行
          new addlist1(testone.s_ID,1,this);//修改拾物表
    	}
    	else
    	JOptionPane.showMessageDialog(null,"查询种类时，无权限修改记录2");  //弹出提示框;	
    break; 
    case jb_editid:
    	  int n = JOptionPane.showConfirmDialog(null,"是否修改密码！","提示",JOptionPane.YES_NO_OPTION);
    	  if(n==JOptionPane.YES_OPTION)
            new editid(testone.s_ID);//修改个人信息
    	  else
    	    new editinformation(testone.s_ID);//修改个人信息不包括密码  
    break;
    case jb:
    	showpicture();
    break;
}
}

//检查选中的种类复选框
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

//找到不同种类的记录
public void find(int sort)
{
	//存取变量
	Vector rowData =new Vector();
	
	//存取种类
	String ss = null;
	
	switch(sort)
	{
	   case 1:ss="校园卡";break;
	   case 2:ss="钱包";break;
	   case 3:ss="书本";break;
	   case 4:ss="手机";break;
	   case 5:ss="其他";break;
	}
	 
	 try
     {      
      ct=DriverManager.getConnection(dbURL,userName,userPwd);
      ps1=ct.prepareStatement("select *from 拾物");      
      rs=ps1.executeQuery();
      
       while(rs.next())
       {
    	  Vector hang=new Vector();
          if(rs.getString("拾物种类").trim().equals(ss))
          {	 
           ps2=ct.prepareStatement("select *from 用户");
           rs2=ps2.executeQuery();  
            while(rs2.next())
           {
       //      if(rs2.getString("学号").trim().equals(rs.getString("学号").trim()))
             break;
           } 
             hang.add(rs.getString("拾物编号"));      
             hang.add(rs.getString("拾物种类"));
             hang.add(rs.getString("拾物名称"));
             hang.add(rs.getString("拾物地点"));
             hang.add(rs.getString("拾物时间"));
             hang.add(rs.getString("拾物描述"));
             hang.add(rs2.getString("姓名"));
             hang.add(rs2.getString("联系方式"));
             rowData.add(hang);
         }
       } 
     }
     catch(Exception e)
     {
      System.out.println("数据库加载失败!这里");
      e.printStackTrace();
     }
     finally
     {	      
     }
     Mytable jt1 = new Mytable(rowData,testone.columName);
     testone.jt.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());
     testone.jt.setModel(jt1);
}

//找到自己的记录
private void Mylist() {
	
	String s=null;
	String name=null;
	String contact=null;
	s=testone.s_ID;
	String ss= "00000000";
	//存取变量
	Vector rowData =new Vector();
	 try
     { 
      ct=DriverManager.getConnection(dbURL,userName,userPwd);
      ps1=ct.prepareStatement("select *from 拾物");      
      rs=ps1.executeQuery();
      ps2=ct.prepareStatement("select *from 用户");
      rs2=ps2.executeQuery(); 
       while(rs.next())
       {
    	   Vector hang=new Vector();
          //if(rs.getString("学号").trim().equals(s.trim()))
    	   if((rs.getString("学号").trim().equals(s.trim()))||(s.trim().equals(ss.trim())))
          {	
              //ps2=ct.prepareStatement("select *from 用户");
              //rs2=ps2.executeQuery();  
         //      while(rs2.next())
            //  {
          //      if(rs2.getString("学号").trim().equals(rs.getString("学号").trim()))
         //       	break;
          //    } */
              hang.add(rs.getString("拾物编号"));      
              hang.add(rs.getString("拾物种类"));
              hang.add(rs.getString("拾物名称"));
              hang.add(rs.getString("拾物地点"));
              hang.add(rs.getString("拾物时间"));
              hang.add(rs.getString("拾物描述"));
              hang.add(rs.getString("学号"));
              rowData.add(hang);
         }
       }
     }
     catch(Exception e)
     {
    	System.out.println("我这里出错");
    	e.printStackTrace();
      
     }
     finally
     {	      
     }
     Mytable jt1 = new Mytable(rowData,testone.columName);
     testone.jt.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());
     testone.jt.setModel(jt1);
}

//删除选中行的记录
private void delete() {
	Statement stmt=null;
	String scode=null;

	if(testone.jt.getSelectedRow()!=-1)
	{
	  int n = JOptionPane.showConfirmDialog(null,"确定是否要删除当前记录！","提示",JOptionPane.YES_NO_OPTION);
	    if(n==JOptionPane.YES_OPTION)
	    {
		  scode = String.valueOf(testone.jt.getValueAt(testone.jt.getSelectedRow(),0)).trim();
		  try
		  {	
			  ct=DriverManager.getConnection(dbURL,userName,userPwd);
			  stmt=ct.createStatement();//创建SQL命令对象
			  String a1="delete from 拾物  where 拾物编号 ="+"'"+scode+"'";
			  stmt.executeUpdate(a1);
		  }
		  catch(Exception e)
		  {
			  System.out.println("数据库加载失败!4");
			  e.printStackTrace();
		  }
		  finally
		  {	      
		  }
	      Mylist();
	  }
	}	
	else
	 JOptionPane.showMessageDialog(null,"未选中删除记录","提示",JOptionPane.WARNING_MESSAGE);  //弹出提示框;
	
}

//找到选中的行
private boolean getmodfiline()
{
	String scode=null;

	if(testone.jt.getSelectedRow()!=-1)
	{
	  int n = JOptionPane.showConfirmDialog(null,"确定是否要修改当前记录！","提示",JOptionPane.YES_NO_OPTION);
	    if(n==JOptionPane.YES_OPTION)
	    {
		  scode = String.valueOf(testone.jt.getValueAt(testone.jt.getSelectedRow(),0)).trim();
		  try
		  {	
			  ct=DriverManager.getConnection(dbURL,userName,userPwd);
			  ps=ct.prepareStatement("select *from 拾物");
			  rs=ps.executeQuery();
			  while(rs.next())
			   {
			    if(rs.getString("拾物编号").equals(scode))
				  {
				   readid=rs.getString("拾物编号");
				   reads0=rs.getString("拾物名称");
				   reads1=rs.getString("拾物时间");
				   reads2=rs.getString("拾物地点");
				   reads3=rs.getString("拾物描述");
				   reads4=rs.getString("拾物种类");
				   readpic=rs.getString("拾物图片");
				  }			  
			   }
			  ps.close();
			  rs.close();
		  }
		  catch(Exception e)
		  {
			  System.out.println("数据库加载失败!");
		  }
		  finally
		  {	      
		  }
		  return true;
	  }	   
	}	
	else
	 JOptionPane.showMessageDialog(null,"未选中修改记录","提示",JOptionPane.WARNING_MESSAGE);  //弹出提示框;
	 return false;
}

//找到选中的图片
private void showpicture()
{
	String scode=null;

	if(testone.jt.getSelectedRow()!=-1)
	{
		  scode = String.valueOf(testone.jt.getValueAt(testone.jt.getSelectedRow(),0)).trim();
		  try
		  {	
			  ct=DriverManager.getConnection(dbURL,userName,userPwd);
			  ps=ct.prepareStatement("select *from 拾物");
			  rs=ps.executeQuery();
			  while(rs.next())
			   {
			    if(rs.getString("拾物编号").equals(scode))
				  {	  
			    	if(rs.getString("拾物图片")==null || rs.getString("拾物图片").equals(""))
			          new show("<二进制数据>D:/Java/workspace/newtest/6.jpg");
			    	else
	                  new show(rs.getString("拾物图片"));
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
		JOptionPane.showMessageDialog(null,"未选择查看的拾物");  //弹出提示框;
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
    this.setTitle("拾物图片");
    
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
