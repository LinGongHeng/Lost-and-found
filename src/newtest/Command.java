//��½�����ע����水ť�ļ�������
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

//��������� 
class Command implements ActionListener{ 
static final int jb1 = 1;               //����ť������ 
static final int jb2 = 2; 
static final int jb3 = 3; 
static final int jb4 = 4; 

int curButton;                                  //��ǰ��ť 
JFrame J;
String s1,login_id,login_name;
String newID,newkey,key,sex,stunum,callnum,grade;
testtwo testtwo;

//�������ݿ����
PreparedStatement ps=null;
Connection ct=null;
ResultSet rs=null;

//���ư�ť��Ĺ��췽�� 
Command(int button,JFrame JF){ 
  curButton = button;
  this.J=JF;
} 

Command(int button,JFrame JF,testtwo testtwo){ 
  curButton = button;
  this.J=JF;
  this.testtwo=testtwo;
} 
//��ťִ�з��� 
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
      	JOptionPane.showMessageDialog(null,"�û������������","Error",JOptionPane.ERROR_MESSAGE);  //��Ϸ������������ʾ��;
      	}
      break; 
      case jb2:
     	J.setVisible(false);
      	testtwo = new testtwo(); //ע�ᴰ��
      break; 
      
      case jb3:
      	if(IDset())
         {
      	   J.setVisible(false);
      	 JOptionPane.showMessageDialog(null,"ע��ɹ�","��ʾ",JOptionPane.PLAIN_MESSAGE);  //ע��ʧ�ܣ�������ʾ��;
           new login();    
         }
         else
         JOptionPane.showMessageDialog(null,"ע��ʧ��","����",JOptionPane.WARNING_MESSAGE);  //ע��ʧ�ܣ�������ʾ��;   
      break; 
      case jb4:
      	{   
      	 JOptionPane.showMessageDialog(null,"ע��ȡ��","����",JOptionPane.WARNING_MESSAGE);  //ע��ʧ�ܣ�������ʾ��;
           J.setVisible(false);
           new login();
      	}
      break; 
  }
}

public boolean IDtest(){
	  
   String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=У԰ʧ������ϵͳ";
   String userName="sa";
   String userPwd="571820";
  
  s1=J.toString();
  try{
  ct=DriverManager.getConnection(dbURL,userName,userPwd);
  ps=ct.prepareStatement("select *from �û�");
  rs=ps.executeQuery();
  while(rs.next())
   {
    //ȥ����β�ո�  ʮ����Ҫ
    if((rs.getString("ѧ��").trim()+""+rs.getString("����").trim()).equals(s1))
    	{
    	login_id=rs.getString("ѧ��");
    	login_name=rs.getString("����");
  		return true;
    	}
    }
  ps.close();
  ct.close();
  }
  catch(Exception e)
  {
   System.out.println("���ݿ����ʧ��!");//�˴�������
  }
  finally
  {	      
  } 
	return false;
}

public boolean IDset(){	  
		String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=У԰ʧ������ϵͳ";
	    String userName="sa";
	    String userPwd="571820";
	    Statement stmt;
	   
	    newID = testtwo.jTextField1.getText().trim();
	    if(newID.equals(""))
	    {
	       JOptionPane.showMessageDialog(null,"�û�������Ϊ�գ������룡","",JOptionPane.WARNING_MESSAGE);  //������ʾ��;
	       return false;
	    }
	       
	    if(testtwo.jrb1.isSelected()==true)
	    	sex="��";
	    else if(testtwo.jrb2.isSelected()==true)
	    	sex="Ů";
	    else
	    {
	    	JOptionPane.showMessageDialog(null,"δ�����Ա������룡","",JOptionPane.WARNING_MESSAGE);  //������ʾ��;
	    	return false;
	    }
	    
	    newkey = String.valueOf(testtwo.password1.getPassword()).trim();    
	    key = String.valueOf(testtwo.password2.getPassword()).trim();
	  
	   if(newkey.equals("") || key.equals(""))
		 {
		   JOptionPane.showMessageDialog(null,"���벻��Ϊ�գ�������!","",JOptionPane.WARNING_MESSAGE);  //���벻һ�£�������ʾ��;
		   return false;
		 }
	   else if(newkey.equals(key)==false)
	    {  
	    	JOptionPane.showMessageDialog(null,"ǰ�������������벻һ��\n       ����������!","",JOptionPane.WARNING_MESSAGE);  //���벻һ�£�������ʾ��;
	    	return false;
	    }
	    
	    stunum= testtwo.jTextField2.getText().trim();
	    if(stunum.length()!=8)
	    {
	    	JOptionPane.showMessageDialog(null,"ѧ�ų���Ϊ8λ������λ������!","",JOptionPane.WARNING_MESSAGE);  //���벻һ�£�������ʾ��;
	    	return false;
	    }
	    grade= testtwo.jTextField3.getText().trim();
	    
	    callnum= testtwo.jTextField4.getText().trim();
	    if(callnum.length()==0 || callnum.length()!=11)
	    {
	    	JOptionPane.showMessageDialog(null,"��������ϵ��ʽ���ֻ���Ϊ11λ��!","",JOptionPane.WARNING_MESSAGE);  //���벻һ�£�������ʾ��;
	    	return false;
	    }    
	    try{
	    ct=DriverManager.getConnection(dbURL,userName,userPwd);
	    stmt=ct.createStatement();//����SQL�������
	    String a1="INSERT INTO �û�  VALUES('"+stunum+"','"+newID+"','"+sex+"','"+grade+"','"+callnum+"','"+newkey+"')";//��������SQL���
	    stmt.executeUpdate(a1); 
	    System.out.println("���ݿ���سɹ�!");
	    stmt.close();
	    ct.close();
	    }
	    catch(Exception e)
	    {
	     System.out.println("���ݿ����ʧ��!11");  
	     e.printStackTrace();//!!!!!!!!!!!!!
	    }
	    finally
	    {	      
	    }
	    
	    return true;
	 }
} 
