
package newtest;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//创建注册界面
class testtwo extends JFrame{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField jTextField1,jTextField2,jTextField3,jTextField4;//定义文本框组件
    JPasswordField password1,password2;//定义密码框组件
    JLabel jLabel1,jLabel2,jLabel3,jLabel4,jLabel5,jLabel6;
    MyPanel1 jp1;
    JButton jb3,jb4;//创建按钮
    JFrame  jf1;
    JRadioButton jrb1,jrb2; //性别单选框
    ButtonGroup bg; 
    
    public testtwo(){
    	
    	jf1=this;
		jp1 = new MyPanel1();
      
    	jTextField1 = new JTextField(12);
    	jTextField2 = new JTextField(12);
    	jTextField3 = new JTextField(12);
    	jTextField4 = new JTextField(12);
    	
    	password1 = new JPasswordField(13);
    	password2 = new JPasswordField(13);
    	
    	jLabel1 = new JLabel("姓        名");
    	jLabel2 = new JLabel("密        码");
    	jLabel3 = new JLabel("确认密码");
    	jLabel4 = new JLabel("学        号");
    	jLabel5 = new JLabel("专业班级");
    	jLabel6 = new JLabel("联系方式");
    	
    	jb3 = new JButton("确 认");
    	jb4 = new JButton("取 消");
    	
		this.setTitle("校园失物招领注册界面"); //设置标题
		this.setLocation(300,50); //设置位置
        //设置显示
        this.setSize(800, 600);
        this.setResizable(false);
        this.add(jp1); //将面板添加到登陆框上面
		
     	jp1.setSize(800, 600);
		jp1.setLayout(null);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE); 
       
        jLabel1.setBounds(100,120,50,30);
        jp1.add(jLabel1); 
        jTextField1.setBounds(155,120,120,30); 
        jp1.add(jTextField1);              //面板添加用户名和文本框
        jTextField1.setDocument(new NumberLenghtLimitedDmt(12,NumberLenghtLimitedDmt.nolimit));//
        
        jLabel2.setBounds(100,190,50,30);
        jp1.add(jLabel2);
        password1.setBounds(155,190,120,30);//面板添加密码和密码输入框
        jp1.add(password1);
        password1.setDocument(new NumberLenghtLimitedDmt(13,NumberLenghtLimitedDmt.nolimit));//最大13位密码
        
        jLabel3.setBounds(100,240,60,30);
        jp1.add(jLabel3);
        password2.setBounds(155,240,120,30);//面板添加确认密码和密码输入框
        jp1.add(password2);
        password2.setDocument(new NumberLenghtLimitedDmt(13,NumberLenghtLimitedDmt.nolimit));//13位密码
        
        jLabel4.setBounds(100,290,50,30);
        jp1.add(jLabel4); 
        jTextField2.setBounds(155,290,120,30); 
        jp1.add(jTextField2);              //面板添加学号和文本框
        jTextField2.setDocument(new NumberLenghtLimitedDmt(8,NumberLenghtLimitedDmt.inlimit));//8位学号
        
        jLabel5.setBounds(100,340,60,30);
        jp1.add(jLabel5); 
        jTextField3.setBounds(155,340,120,30); 
        jp1.add(jTextField3);              //面板添加班级和文本框
        
        jLabel6.setBounds(100,390,60,30);
        jp1.add(jLabel6); 
        jTextField4.setBounds(155,390,120,30); 
        jp1.add(jTextField4);              //面板添加联系方式和文本框
        jTextField4.setDocument(new NumberLenghtLimitedDmt(11,NumberLenghtLimitedDmt.inlimit));//最多11位电话
        
        jb3.setBounds(120,450,70,40); 
        jp1.add(jb3);
        jb4.setBounds(220,450,70,40);//面板添加确认和取消
        jp1.add(jb4);
        
        jb3.addActionListener(new Command(Command.jb3,jf1,this));
        jb4.addActionListener(new Command(Command.jb4,jf1,this));
        
        
        jrb1=new JRadioButton("男"); 
        jrb2=new JRadioButton("女"); 
        jrb1.setContentAreaFilled(false);
        jrb2.setContentAreaFilled(false);
        // 一定要把jrb1 jrb2放入到一个ButtonGroup中管理 
        ButtonGroup bg=new ButtonGroup(); 
        bg.add(jrb1); 
        bg.add(jrb2); 
        jrb1.setBounds(150,160,40,20); 
        jp1.add(jrb1);
        jrb2.setBounds(200,160,40,20);//面板添加确认和取消
        jp1.add(jrb2);
        
        this.setVisible(true);  
    } 
}

//修改个人信息界面包含密码
class editid extends JFrame implements ActionListener{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField jTextField1,jTextField2,jTextField3,jTextField4;//定义文本框组件
  JPasswordField password1,password2,password3;//定义密码框组件
  JLabel jLabel1,jLabel2,jLabel3,jLabel4,jLabel5,jLabel6;
  JLabel jLabel_id;
  JPanel5 jp1;
  JButton jb3,jb4;//创建按钮
  JFrame  jf1;
  JRadioButton jrb1,jrb2; //性别单选框
  ButtonGroup bg;
  //连接数据库
  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=校园失物招领系统";
  String userName="sa";
  String userPwd="571820";
//链接数据库变量
PreparedStatement ps=null;
Connection ct=null;
ResultSet rs=null;

//存取密码
String code;
String s_id;
  
  public editid(String s_id){
	 
	this.s_id=s_id;
  	jf1=this;
	jp1 = new JPanel5();
    
  	jTextField1 = new JTextField(12);
  	jTextField2 = new JTextField(12);
  	jTextField3 = new JTextField(12);
  	jTextField4 = new JTextField(12);
  	
  	password1 = new JPasswordField(13);
  	password2 = new JPasswordField(13);
  	password3 = new JPasswordField(13);
  	
  	jLabel_id =  new JLabel("学        号:"+s_id);
  	jLabel_id.setFont(new Font("Dialog",1,16));
  	jLabel1 = new JLabel("姓        名");
  	jLabel2 = new JLabel("旧  密  码");
  	jLabel3 = new JLabel("新  密  码");
  	jLabel4 = new JLabel("确认密码");
  	jLabel5 = new JLabel("专业班级");
  	jLabel6 = new JLabel("联系方式");
  	
  	jb3 = new JButton("确 认 修 改");
  	jb4 = new JButton("取 消 修 改");
  	
	 this.setTitle("修改个人资料"); //设置标题
	 this.setLocation(500,100); //设置位置
      //设置显示
      this.setSize(400, 600);
      this.setResizable(false);
      this.setDefaultCloseOperation(HIDE_ON_CLOSE);
      this.add(jp1); //将面板添加到登陆框上面
		
   	  jp1.setSize(800, 600);
	  jp1.setLayout(null);
     
	  jLabel_id.setBounds(100,70,200,40);
	  jLabel_id.setFont(new Font("Dialog",1,16));
	  jp1.add(jLabel_id);
	  
      jLabel1.setBounds(100,120,50,30);
      jp1.add(jLabel1); 
      jTextField1.setBounds(155,120,120,30); 
      jp1.add(jTextField1);              //面板添加用户名和文本框
      jTextField1.setDocument(new NumberLenghtLimitedDmt(12,NumberLenghtLimitedDmt.nolimit));//
      
      jLabel2.setBounds(100,190,60,30);
      jp1.add(jLabel2);
      password1.setBounds(155,190,120,30);//面板旧密码和密码输入框
      jp1.add(password1);
      password1.setDocument(new NumberLenghtLimitedDmt(13,NumberLenghtLimitedDmt.nolimit));//最大13位密码
      
      jLabel3.setBounds(100,240,60,30);
      jp1.add(jLabel3);
      password2.setBounds(155,240,120,30);//面板添加新密码和密码输入框
      jp1.add(password2);
      password2.setDocument(new NumberLenghtLimitedDmt(13,NumberLenghtLimitedDmt.nolimit));//13位密码
      
      jLabel4.setBounds(100,290,60,30);
      jp1.add(jLabel4); 
      password3.setBounds(155,290,120,30); 
      jp1.add(password3);              //面板添加确认密码和密码输入框
      password3.setDocument(new NumberLenghtLimitedDmt(13,NumberLenghtLimitedDmt.inlimit));//8位学号
      
      jLabel5.setBounds(100,340,60,30);
      jp1.add(jLabel5); 
      jTextField3.setBounds(155,340,120,30); 
      jp1.add(jTextField3);              //面板添加班级和文本框
      
      jLabel6.setBounds(100,390,60,30);
      jp1.add(jLabel6); 
      jTextField4.setBounds(155,390,120,30); 
      jp1.add(jTextField4);              //面板添加联系方式和文本框
      jTextField4.setDocument(new NumberLenghtLimitedDmt(11,NumberLenghtLimitedDmt.inlimit));//最多11位电话
      
      jb3.setBounds(90,450,100,30); 
      jp1.add(jb3);
      jb4.setBounds(210,450,100,30);//面板添加确认和取消
      jp1.add(jb4);
      
      jb3.addActionListener(this);
      jb4.addActionListener(this);
           
      jrb1=new JRadioButton("男"); 
      jrb2=new JRadioButton("女"); 
      jrb1.setContentAreaFilled(false);
      jrb2.setContentAreaFilled(false);
      // 一定要把jrb1 jrb2放入到一个ButtonGroup中管理 
      ButtonGroup bg=new ButtonGroup(); 
      bg.add(jrb1); 
      bg.add(jrb2); 
      jrb1.setBounds(150,160,40,20); 
      jp1.add(jrb1);
      jrb2.setBounds(200,160,40,20);//面板添加确认和取消
      jp1.add(jrb2);
      
      //将当前个人信息读取出来
      try{
    	  ct=DriverManager.getConnection(dbURL,userName,userPwd);
    	  ps=ct.prepareStatement("select *from 用户");
    	  rs=ps.executeQuery();
    	  while(rs.next())
    	   {
    	    //去除首尾空格  十分重要
    	    if(rs.getString("学号").trim().equals(s_id))
    	    {
    	    	jTextField1.setText(rs.getString("姓名")) ; 
    	    	jTextField3.setText(rs.getString("班级")) ;
    	    	jTextField4.setText(rs.getString("联系方式"));
    	    	if(rs.getString("性别").trim().equals("男"))
    	    	 jrb1.setSelected(true);
    	    	if(rs.getString("性别").trim().equals("女"))
       	    	 jrb2.setSelected(true);
    	    	code=rs.getString("密码");
    	    }
    	   }
    	  ps.close();
    	  ct.close();
    	  }
    	  catch(Exception e)
    	  {
    	   e.printStackTrace();
    	  }
    	  finally
    	  {	      
    	  } 
      
      this.setVisible(true);  
  }


public void actionPerformed(ActionEvent e) {
	if(e.getSource()==jb3)
		if(updateid()==true)
        {
     	   this.setVisible(false);
     	   JOptionPane.showMessageDialog(null,"修改成功","提示",JOptionPane.WARNING_MESSAGE);  //注册失败，弹出提示框;   
        }
	if(e.getSource()==jb4)
        {
     	   this.setVisible(false);
     	   JOptionPane.showMessageDialog(null,"修改失败","提示",JOptionPane.WARNING_MESSAGE);  //注册失败，弹出提示框;   
        }	
} 

public boolean updateid(){
	
	String newID,newkey,key,sex = null,stunum,callnum,grade;
	
	newID = jTextField1.getText();
	
	if(newID.equals(""))
	{
		JOptionPane.showMessageDialog(null,"用户名不能为空，请输入！","提示",JOptionPane.WARNING_MESSAGE);  //弹出提示框;
		return false;
	}

	if(jrb1.isSelected()==true)
		sex="男";
	if(jrb2.isSelected()==true)
		sex="女";
	if(String.valueOf(password1.getPassword()).trim().equals(code))
	{
		newkey = String.valueOf(password2.getPassword()).trim();    
		key = String.valueOf(password3.getPassword()).trim();
	}
	else
	{
		JOptionPane.showMessageDialog(null,"旧密码输入错误，请重新输入！","提示",JOptionPane.WARNING_MESSAGE);  //弹出提示框;
		return false;
	}

	if(newkey.equals("") || key.equals(""))
	{
		JOptionPane.showMessageDialog(null,"新密码不能为空，请输入!","",JOptionPane.WARNING_MESSAGE);  //密码不一致，弹出提示框;
		return false;
	}
	else if(newkey.equals(key)==false)
	{  
		JOptionPane.showMessageDialog(null,"前后两次输入密码不一致\n       请重新输入!","",JOptionPane.WARNING_MESSAGE);  //密码不一致，弹出提示框;
		return false;
	}

	grade= jTextField3.getText().trim();

	callnum= jTextField4.getText().trim();
	if(callnum.length()==0 || callnum.length()!=11)
	{
		JOptionPane.showMessageDialog(null,"请输入联系方式（手机号为11位）!","",JOptionPane.WARNING_MESSAGE);  //密码不一致，弹出提示框;
		return false;
	}    
	try{
		Statement stmt=null;
		
		ct=DriverManager.getConnection(dbURL,userName,userPwd);
		stmt=ct.createStatement();//创建SQL命令对象
		String a1= "UPDATE 用户   SET 姓名='"+newID+"',性别='"+sex+"',班级='"+grade+"',联系方式='"+callnum+"',密码='"+newkey+"' where 学号='"+s_id+"';";//插入数据SQL语句
		stmt.executeUpdate(a1); 
		stmt.close();
		ct.close();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{	      
	} 
	return true;
 }
}


class editinformation extends JFrame implements ActionListener{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField jTextField1,jTextField2,jTextField3,jTextField4;//定义文本框组件
  JPasswordField password1,password2,password3;//定义密码框组件
  JLabel jLabel1,jLabel2,jLabel3,jLabel4,jLabel5,jLabel6;
  JLabel jLabel_id;
  JPanel5 jp1;
  JButton jb3,jb4;//创建按钮
  JFrame  jf1;
  JRadioButton jrb1,jrb2; //性别单选框
  ButtonGroup bg;
  //连接数据库
  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=校园失物招领系统";
  String userName="sa";
  String userPwd="571820";
//链接数据库变量
PreparedStatement ps=null;
Connection ct=null;
ResultSet rs=null;

//存取密码
String code;
String s_id;
  
  public editinformation(String s_id){
	 
	this.s_id=s_id;
  	jf1=this;
	jp1 = new JPanel5();
    
  	jTextField1 = new JTextField(12);
  	jTextField2 = new JTextField(12);
  	jTextField3 = new JTextField(12);
  	jTextField4 = new JTextField(12);
  	
  	password1 = new JPasswordField(13);
  	password2 = new JPasswordField(13);
  	password3 = new JPasswordField(13);
  	
  	jLabel_id =  new JLabel("学        号:"+s_id);
  	jLabel_id.setFont(new Font("Dialog",1,16));
  	jLabel1 = new JLabel("姓        名");
  	jLabel2 = new JLabel("旧  密  码");
  	jLabel3 = new JLabel("新  密  码");
  	jLabel4 = new JLabel("确认密码");
  	jLabel5 = new JLabel("专业班级");
  	jLabel6 = new JLabel("联系方式");
  	
  	jb3 = new JButton("确 认 修 改");
  	jb4 = new JButton("取 消 修 改");
  	
	 this.setTitle("修改个人资料"); //设置标题
	 this.setLocation(500,100); //设置位置
      //设置显示
      this.setSize(400, 600);
      this.setResizable(false);
      this.setDefaultCloseOperation(HIDE_ON_CLOSE);
      this.add(jp1); //将面板添加到登陆框上面
		
   	  jp1.setSize(800, 600);
	  jp1.setLayout(null);
     
	  jLabel_id.setBounds(100,70,200,40);
	  jLabel_id.setFont(new Font("Dialog",1,16));
	  jp1.add(jLabel_id);
	  
      jLabel1.setBounds(100,120,50,30);
      jp1.add(jLabel1); 
      jTextField1.setBounds(155,120,120,30); 
      jp1.add(jTextField1);              //面板添加用户名和文本框
      jTextField1.setDocument(new NumberLenghtLimitedDmt(12,NumberLenghtLimitedDmt.nolimit));//
      
      jLabel2.setBounds(100,190,60,30);
      jp1.add(jLabel2);
      password1.setBounds(155,190,120,30);//面板旧密码和密码输入框
      jp1.add(password1);
      password1.setDocument(new NumberLenghtLimitedDmt(13,NumberLenghtLimitedDmt.nolimit));//最大13位密码
      
      jLabel3.setBounds(100,240,60,30);
      jp1.add(jLabel3);
      password2.setBounds(155,240,120,30);//面板添加新密码和密码输入框
      jp1.add(password2);
      password2.setDocument(new NumberLenghtLimitedDmt(13,NumberLenghtLimitedDmt.nolimit));//13位密码
      
      jLabel4.setBounds(100,290,60,30);
      jp1.add(jLabel4); 
      password3.setBounds(155,290,120,30); 
      jp1.add(password3);              //面板添加确认密码和密码输入框
      password3.setDocument(new NumberLenghtLimitedDmt(13,NumberLenghtLimitedDmt.inlimit));//8位学号
      password1.setEditable(false);
      password2.setEditable(false);
      password3.setEditable(false);
      
      jLabel5.setBounds(100,340,60,30);
      jp1.add(jLabel5); 
      jTextField3.setBounds(155,340,120,30); 
      jp1.add(jTextField3);              //面板添加班级和文本框
      
      jLabel6.setBounds(100,390,60,30);
      jp1.add(jLabel6); 
      jTextField4.setBounds(155,390,120,30); 
      jp1.add(jTextField4);              //面板添加联系方式和文本框
      jTextField4.setDocument(new NumberLenghtLimitedDmt(11,NumberLenghtLimitedDmt.inlimit));//最多11位电话
      
      jb3.setBounds(90,450,100,30); 
      jp1.add(jb3);
      jb4.setBounds(210,450,100,30);//面板添加确认和取消
      jp1.add(jb4);
      
      jb3.addActionListener(this);
      jb4.addActionListener(this);
           
      jrb1=new JRadioButton("男"); 
      jrb2=new JRadioButton("女"); 
      jrb1.setContentAreaFilled(false);
      jrb2.setContentAreaFilled(false);
      // 一定要把jrb1 jrb2放入到一个ButtonGroup中管理 
      ButtonGroup bg=new ButtonGroup(); 
      bg.add(jrb1); 
      bg.add(jrb2); 
      jrb1.setBounds(150,160,40,20); 
      jp1.add(jrb1);
      jrb2.setBounds(200,160,40,20);//面板添加确认和取消
      jp1.add(jrb2);
      
      //将当前个人信息读取出来
      try{
    	  ct=DriverManager.getConnection(dbURL,userName,userPwd);
    	  ps=ct.prepareStatement("select *from 用户");
    	  rs=ps.executeQuery();
    	  while(rs.next())
    	   {
    	    //去除首尾空格  十分重要
    	    if(rs.getString("学号").trim().equals(s_id))
    	    {
    	    	jTextField1.setText(rs.getString("姓名")) ; 
    	    	jTextField3.setText(rs.getString("班级")) ;
    	    	jTextField4.setText(rs.getString("联系方式"));
    	    	if(rs.getString("性别").trim().equals("男"))
    	    	 jrb1.setSelected(true);
    	    	if(rs.getString("性别").trim().equals("女"))
       	    	 jrb2.setSelected(true);
    	    	password1.setText(rs.getString("密码"));
    	    	password2.setText(rs.getString("密码"));
    	    	password3.setText(rs.getString("密码"));
    	    }
    	   }
    	  ps.close();
    	  ct.close();
    	  }
    	  catch(Exception e)
    	  {
    	   e.printStackTrace();
    	  }
    	  finally
    	  {	      
    	  } 
      
      this.setVisible(true);  
  }


public void actionPerformed(ActionEvent e) {
	if(e.getSource()==jb3)
		if(updateid()==true)
        {
     	   this.setVisible(false);
     	   JOptionPane.showMessageDialog(null,"修改成功","提示",JOptionPane.WARNING_MESSAGE);  //注册失败，弹出提示框;   
        }
	if(e.getSource()==jb4)
        {
     	   this.setVisible(false);
     	   JOptionPane.showMessageDialog(null,"修改失败","提示",JOptionPane.WARNING_MESSAGE);  //注册失败，弹出提示框;   
        }	
} 

public boolean updateid(){
	
	String newID,sex = null,stunum,callnum,grade;
	
	newID = jTextField1.getText();
	
	if(newID.equals(""))
	{
		JOptionPane.showMessageDialog(null,"用户名不能为空，请输入！","提示",JOptionPane.WARNING_MESSAGE);  //弹出提示框;
		return false;
	}

	if(jrb1.isSelected()==true)
		sex="男";
	if(jrb2.isSelected()==true)
		sex="女";

	grade= jTextField3.getText().trim();

	callnum= jTextField4.getText().trim();
	if(callnum.length()==0 || callnum.length()!=11)
	{
		JOptionPane.showMessageDialog(null,"请输入联系方式（手机号为11位）!","",JOptionPane.WARNING_MESSAGE);  //密码不一致，弹出提示框;
		return false;
	}    
	try{
		Statement stmt=null;
		
		ct=DriverManager.getConnection(dbURL,userName,userPwd);
		stmt=ct.createStatement();//创建SQL命令对象
		String a1= "UPDATE 用户   SET 姓名='"+newID+"',性别='"+sex+"',班级='"+grade+"',联系方式='"+callnum+"' where 学号='"+s_id+"';";//插入数据SQL语句
		stmt.executeUpdate(a1); 
		stmt.close();
		ct.close();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	finally
	{	      
	} 
	return true;
 }
}
