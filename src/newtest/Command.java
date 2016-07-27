//登陆界面和注册界面按钮的监听函数
package newtest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//处理控制类 
class Command implements ActionListener{ 
static final int jb1 = 1;               //给按钮分配编号 
static final int jb2 = 2; 
static final int jb3 = 3; 
static final int jb4 = 4; 

int curButton;                                  //当前按钮 
JFrame J;
String s1,login_id,login_name;
String newID,newkey,key,sex,stunum,callnum,grade;
testtwo testtwo;

//链接数据库变量
PreparedStatement ps=null;
Connection ct=null;
ResultSet rs=null;

//控制按钮类的构造方法 
Command(int button,JFrame JF){ 
  curButton = button;
  this.J=JF;
} 

Command(int button,JFrame JF,testtwo testtwo){ 
  curButton = button;
  this.J=JF;
  this.testtwo=testtwo;
} 
//按钮执行方法 
public void actionPerformed (ActionEvent e){ 
	
 switch(curButton)
 { 
      case jb1:
      	if(IDtest())
           {
      		//new Sound();
      		J.setVisible(false);
      	    new testone(login_id,login_name);
           }
      	else
      	{
      	//new Sound1();    	
      	JOptionPane.showMessageDialog(null,"用户名或密码错误","Error",JOptionPane.ERROR_MESSAGE);  //游戏结束，弹出提示框;
      	}
      break; 
      case jb2:
     	J.setVisible(false);
      	testtwo = new testtwo(); //注册窗口
      break; 
      
      case jb3:
      	if(IDset())
         {
      	   J.setVisible(false);
      	 JOptionPane.showMessageDialog(null,"注册成功","提示",JOptionPane.PLAIN_MESSAGE);  //注册失败，弹出提示框;
           new login();    
         }
         else
         JOptionPane.showMessageDialog(null,"注册失败","警告",JOptionPane.WARNING_MESSAGE);  //注册失败，弹出提示框;   
      break; 
      case jb4:
      	{   
      	 JOptionPane.showMessageDialog(null,"注册取消","警告",JOptionPane.WARNING_MESSAGE);  //注册失败，弹出提示框;
           J.setVisible(false);
           new login();
      	}
      break; 
  }
}

public boolean IDtest(){
	  
   String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=校园失物招领系统";
   String userName="sa";
   String userPwd="571820";
  
  s1=J.toString();
  try{
  ct=DriverManager.getConnection(dbURL,userName,userPwd);
  ps=ct.prepareStatement("select *from 用户");
  rs=ps.executeQuery();
  while(rs.next())
   {
    //去除首尾空格  十分重要
    if((rs.getString("学号").trim()+""+rs.getString("密码").trim()).equals(s1))
    	{
    	login_id=rs.getString("学号");
    	login_name=rs.getString("姓名");
  		return true;
    	}
    }
  ps.close();
  ct.close();
  }
  catch(Exception e)
  {
   System.out.println("数据库加载失败!");//此处有问题
  }
  finally
  {	      
  } 
	return false;
}

public boolean IDset(){	  
		String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=校园失物招领系统";
	    String userName="sa";
	    String userPwd="571820";
	    Statement stmt;
	   
	    newID = testtwo.jTextField1.getText().trim();
	    if(newID.equals(""))
	    {
	       JOptionPane.showMessageDialog(null,"用户名不能为空，请输入！","",JOptionPane.WARNING_MESSAGE);  //弹出提示框;
	       return false;
	    }
	       
	    if(testtwo.jrb1.isSelected()==true)
	    	sex="男";
	    else if(testtwo.jrb2.isSelected()==true)
	    	sex="女";
	    else
	    {
	    	JOptionPane.showMessageDialog(null,"未输入性别，请输入！","",JOptionPane.WARNING_MESSAGE);  //弹出提示框;
	    	return false;
	    }
	    
	    newkey = String.valueOf(testtwo.password1.getPassword()).trim();    
	    key = String.valueOf(testtwo.password2.getPassword()).trim();
	  
	   if(newkey.equals("") || key.equals(""))
		 {
		   JOptionPane.showMessageDialog(null,"密码不能为空，请输入!","",JOptionPane.WARNING_MESSAGE);  //密码不一致，弹出提示框;
		   return false;
		 }
	   else if(newkey.equals(key)==false)
	    {  
	    	JOptionPane.showMessageDialog(null,"前后两次输入密码不一致\n       请重新输入!","",JOptionPane.WARNING_MESSAGE);  //密码不一致，弹出提示框;
	    	return false;
	    }
	    
	    stunum= testtwo.jTextField2.getText().trim();
	    if(stunum.length()!=8)
	    {
	    	JOptionPane.showMessageDialog(null,"学号长度为8位，输入位数不对!","",JOptionPane.WARNING_MESSAGE);  //密码不一致，弹出提示框;
	    	return false;
	    }
	    grade= testtwo.jTextField3.getText().trim();
	    
	    callnum= testtwo.jTextField4.getText().trim();
	    if(callnum.length()==0 || callnum.length()!=11)
	    {
	    	JOptionPane.showMessageDialog(null,"请输入联系方式（手机号为11位）!","",JOptionPane.WARNING_MESSAGE);  //密码不一致，弹出提示框;
	    	return false;
	    }    
	    try{
	    ct=DriverManager.getConnection(dbURL,userName,userPwd);
	    stmt=ct.createStatement();//创建SQL命令对象
	    String a1="INSERT INTO 用户  VALUES('"+stunum+"','"+newID+"','"+sex+"','"+grade+"','"+callnum+"','"+newkey+"')";//插入数据SQL语句
	    stmt.executeUpdate(a1); 
	    System.out.println("数据库加载成功!");
	    stmt.close();
	    ct.close();
	    }
	    catch(Exception e)
	    {
	     System.out.println("数据库加载失败!11");  
	     e.printStackTrace();//!!!!!!!!!!!!!
	    }
	    finally
	    {	      
	    }
	    
	    return true;
	 }
} 
