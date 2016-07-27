
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

//����ע�����
class testtwo extends JFrame{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField jTextField1,jTextField2,jTextField3,jTextField4;//�����ı������
    JPasswordField password1,password2;//������������
    JLabel jLabel1,jLabel2,jLabel3,jLabel4,jLabel5,jLabel6;
    MyPanel1 jp1;
    JButton jb3,jb4;//������ť
    JFrame  jf1;
    JRadioButton jrb1,jrb2; //�Ա�ѡ��
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
    	
    	jLabel1 = new JLabel("��        ��");
    	jLabel2 = new JLabel("��        ��");
    	jLabel3 = new JLabel("ȷ������");
    	jLabel4 = new JLabel("ѧ        ��");
    	jLabel5 = new JLabel("רҵ�༶");
    	jLabel6 = new JLabel("��ϵ��ʽ");
    	
    	jb3 = new JButton("ȷ ��");
    	jb4 = new JButton("ȡ ��");
    	
		this.setTitle("У԰ʧ������ע�����"); //���ñ���
		this.setLocation(300,50); //����λ��
        //������ʾ
        this.setSize(800, 600);
        this.setResizable(false);
        this.add(jp1); //�������ӵ���½������
		
     	jp1.setSize(800, 600);
		jp1.setLayout(null);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE); 
       
        jLabel1.setBounds(100,120,50,30);
        jp1.add(jLabel1); 
        jTextField1.setBounds(155,120,120,30); 
        jp1.add(jTextField1);              //�������û������ı���
        jTextField1.setDocument(new NumberLenghtLimitedDmt(12,NumberLenghtLimitedDmt.nolimit));//
        
        jLabel2.setBounds(100,190,50,30);
        jp1.add(jLabel2);
        password1.setBounds(155,190,120,30);//��������������������
        jp1.add(password1);
        password1.setDocument(new NumberLenghtLimitedDmt(13,NumberLenghtLimitedDmt.nolimit));//���13λ����
        
        jLabel3.setBounds(100,240,60,30);
        jp1.add(jLabel3);
        password2.setBounds(155,240,120,30);//������ȷ����������������
        jp1.add(password2);
        password2.setDocument(new NumberLenghtLimitedDmt(13,NumberLenghtLimitedDmt.nolimit));//13λ����
        
        jLabel4.setBounds(100,290,50,30);
        jp1.add(jLabel4); 
        jTextField2.setBounds(155,290,120,30); 
        jp1.add(jTextField2);              //������ѧ�ź��ı���
        jTextField2.setDocument(new NumberLenghtLimitedDmt(8,NumberLenghtLimitedDmt.inlimit));//8λѧ��
        
        jLabel5.setBounds(100,340,60,30);
        jp1.add(jLabel5); 
        jTextField3.setBounds(155,340,120,30); 
        jp1.add(jTextField3);              //�����Ӱ༶���ı���
        
        jLabel6.setBounds(100,390,60,30);
        jp1.add(jLabel6); 
        jTextField4.setBounds(155,390,120,30); 
        jp1.add(jTextField4);              //��������ϵ��ʽ���ı���
        jTextField4.setDocument(new NumberLenghtLimitedDmt(11,NumberLenghtLimitedDmt.inlimit));//���11λ�绰
        
        jb3.setBounds(120,450,70,40); 
        jp1.add(jb3);
        jb4.setBounds(220,450,70,40);//������ȷ�Ϻ�ȡ��
        jp1.add(jb4);
        
        jb3.addActionListener(new Command(Command.jb3,jf1,this));
        jb4.addActionListener(new Command(Command.jb4,jf1,this));
        
        
        jrb1=new JRadioButton("��"); 
        jrb2=new JRadioButton("Ů"); 
        jrb1.setContentAreaFilled(false);
        jrb2.setContentAreaFilled(false);
        // һ��Ҫ��jrb1 jrb2���뵽һ��ButtonGroup�й��� 
        ButtonGroup bg=new ButtonGroup(); 
        bg.add(jrb1); 
        bg.add(jrb2); 
        jrb1.setBounds(150,160,40,20); 
        jp1.add(jrb1);
        jrb2.setBounds(200,160,40,20);//������ȷ�Ϻ�ȡ��
        jp1.add(jrb2);
        
        this.setVisible(true);  
    } 
}

//�޸ĸ�����Ϣ�����������
class editid extends JFrame implements ActionListener{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField jTextField1,jTextField2,jTextField3,jTextField4;//�����ı������
  JPasswordField password1,password2,password3;//������������
  JLabel jLabel1,jLabel2,jLabel3,jLabel4,jLabel5,jLabel6;
  JLabel jLabel_id;
  JPanel5 jp1;
  JButton jb3,jb4;//������ť
  JFrame  jf1;
  JRadioButton jrb1,jrb2; //�Ա�ѡ��
  ButtonGroup bg;
  //�������ݿ�
  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=У԰ʧ������ϵͳ";
  String userName="sa";
  String userPwd="571820";
//�������ݿ����
PreparedStatement ps=null;
Connection ct=null;
ResultSet rs=null;

//��ȡ����
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
  	
  	jLabel_id =  new JLabel("ѧ        ��:"+s_id);
  	jLabel_id.setFont(new Font("Dialog",1,16));
  	jLabel1 = new JLabel("��        ��");
  	jLabel2 = new JLabel("��  ��  ��");
  	jLabel3 = new JLabel("��  ��  ��");
  	jLabel4 = new JLabel("ȷ������");
  	jLabel5 = new JLabel("רҵ�༶");
  	jLabel6 = new JLabel("��ϵ��ʽ");
  	
  	jb3 = new JButton("ȷ �� �� ��");
  	jb4 = new JButton("ȡ �� �� ��");
  	
	 this.setTitle("�޸ĸ�������"); //���ñ���
	 this.setLocation(500,100); //����λ��
      //������ʾ
      this.setSize(400, 600);
      this.setResizable(false);
      this.setDefaultCloseOperation(HIDE_ON_CLOSE);
      this.add(jp1); //�������ӵ���½������
		
   	  jp1.setSize(800, 600);
	  jp1.setLayout(null);
     
	  jLabel_id.setBounds(100,70,200,40);
	  jLabel_id.setFont(new Font("Dialog",1,16));
	  jp1.add(jLabel_id);
	  
      jLabel1.setBounds(100,120,50,30);
      jp1.add(jLabel1); 
      jTextField1.setBounds(155,120,120,30); 
      jp1.add(jTextField1);              //�������û������ı���
      jTextField1.setDocument(new NumberLenghtLimitedDmt(12,NumberLenghtLimitedDmt.nolimit));//
      
      jLabel2.setBounds(100,190,60,30);
      jp1.add(jLabel2);
      password1.setBounds(155,190,120,30);//������������������
      jp1.add(password1);
      password1.setDocument(new NumberLenghtLimitedDmt(13,NumberLenghtLimitedDmt.nolimit));//���13λ����
      
      jLabel3.setBounds(100,240,60,30);
      jp1.add(jLabel3);
      password2.setBounds(155,240,120,30);//����������������������
      jp1.add(password2);
      password2.setDocument(new NumberLenghtLimitedDmt(13,NumberLenghtLimitedDmt.nolimit));//13λ����
      
      jLabel4.setBounds(100,290,60,30);
      jp1.add(jLabel4); 
      password3.setBounds(155,290,120,30); 
      jp1.add(password3);              //������ȷ����������������
      password3.setDocument(new NumberLenghtLimitedDmt(13,NumberLenghtLimitedDmt.inlimit));//8λѧ��
      
      jLabel5.setBounds(100,340,60,30);
      jp1.add(jLabel5); 
      jTextField3.setBounds(155,340,120,30); 
      jp1.add(jTextField3);              //�����Ӱ༶���ı���
      
      jLabel6.setBounds(100,390,60,30);
      jp1.add(jLabel6); 
      jTextField4.setBounds(155,390,120,30); 
      jp1.add(jTextField4);              //��������ϵ��ʽ���ı���
      jTextField4.setDocument(new NumberLenghtLimitedDmt(11,NumberLenghtLimitedDmt.inlimit));//���11λ�绰
      
      jb3.setBounds(90,450,100,30); 
      jp1.add(jb3);
      jb4.setBounds(210,450,100,30);//������ȷ�Ϻ�ȡ��
      jp1.add(jb4);
      
      jb3.addActionListener(this);
      jb4.addActionListener(this);
           
      jrb1=new JRadioButton("��"); 
      jrb2=new JRadioButton("Ů"); 
      jrb1.setContentAreaFilled(false);
      jrb2.setContentAreaFilled(false);
      // һ��Ҫ��jrb1 jrb2���뵽һ��ButtonGroup�й��� 
      ButtonGroup bg=new ButtonGroup(); 
      bg.add(jrb1); 
      bg.add(jrb2); 
      jrb1.setBounds(150,160,40,20); 
      jp1.add(jrb1);
      jrb2.setBounds(200,160,40,20);//������ȷ�Ϻ�ȡ��
      jp1.add(jrb2);
      
      //����ǰ������Ϣ��ȡ����
      try{
    	  ct=DriverManager.getConnection(dbURL,userName,userPwd);
    	  ps=ct.prepareStatement("select *from �û�");
    	  rs=ps.executeQuery();
    	  while(rs.next())
    	   {
    	    //ȥ����β�ո�  ʮ����Ҫ
    	    if(rs.getString("ѧ��").trim().equals(s_id))
    	    {
    	    	jTextField1.setText(rs.getString("����")) ; 
    	    	jTextField3.setText(rs.getString("�༶")) ;
    	    	jTextField4.setText(rs.getString("��ϵ��ʽ"));
    	    	if(rs.getString("�Ա�").trim().equals("��"))
    	    	 jrb1.setSelected(true);
    	    	if(rs.getString("�Ա�").trim().equals("Ů"))
       	    	 jrb2.setSelected(true);
    	    	code=rs.getString("����");
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
     	   JOptionPane.showMessageDialog(null,"�޸ĳɹ�","��ʾ",JOptionPane.WARNING_MESSAGE);  //ע��ʧ�ܣ�������ʾ��;   
        }
	if(e.getSource()==jb4)
        {
     	   this.setVisible(false);
     	   JOptionPane.showMessageDialog(null,"�޸�ʧ��","��ʾ",JOptionPane.WARNING_MESSAGE);  //ע��ʧ�ܣ�������ʾ��;   
        }	
} 

public boolean updateid(){
	
	String newID,newkey,key,sex = null,stunum,callnum,grade;
	
	newID = jTextField1.getText();
	
	if(newID.equals(""))
	{
		JOptionPane.showMessageDialog(null,"�û�������Ϊ�գ������룡","��ʾ",JOptionPane.WARNING_MESSAGE);  //������ʾ��;
		return false;
	}

	if(jrb1.isSelected()==true)
		sex="��";
	if(jrb2.isSelected()==true)
		sex="Ů";
	if(String.valueOf(password1.getPassword()).trim().equals(code))
	{
		newkey = String.valueOf(password2.getPassword()).trim();    
		key = String.valueOf(password3.getPassword()).trim();
	}
	else
	{
		JOptionPane.showMessageDialog(null,"����������������������룡","��ʾ",JOptionPane.WARNING_MESSAGE);  //������ʾ��;
		return false;
	}

	if(newkey.equals("") || key.equals(""))
	{
		JOptionPane.showMessageDialog(null,"�����벻��Ϊ�գ�������!","",JOptionPane.WARNING_MESSAGE);  //���벻һ�£�������ʾ��;
		return false;
	}
	else if(newkey.equals(key)==false)
	{  
		JOptionPane.showMessageDialog(null,"ǰ�������������벻һ��\n       ����������!","",JOptionPane.WARNING_MESSAGE);  //���벻һ�£�������ʾ��;
		return false;
	}

	grade= jTextField3.getText().trim();

	callnum= jTextField4.getText().trim();
	if(callnum.length()==0 || callnum.length()!=11)
	{
		JOptionPane.showMessageDialog(null,"��������ϵ��ʽ���ֻ���Ϊ11λ��!","",JOptionPane.WARNING_MESSAGE);  //���벻һ�£�������ʾ��;
		return false;
	}    
	try{
		Statement stmt=null;
		
		ct=DriverManager.getConnection(dbURL,userName,userPwd);
		stmt=ct.createStatement();//����SQL�������
		String a1= "UPDATE �û�   SET ����='"+newID+"',�Ա�='"+sex+"',�༶='"+grade+"',��ϵ��ʽ='"+callnum+"',����='"+newkey+"' where ѧ��='"+s_id+"';";//��������SQL���
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
	
	JTextField jTextField1,jTextField2,jTextField3,jTextField4;//�����ı������
  JPasswordField password1,password2,password3;//������������
  JLabel jLabel1,jLabel2,jLabel3,jLabel4,jLabel5,jLabel6;
  JLabel jLabel_id;
  JPanel5 jp1;
  JButton jb3,jb4;//������ť
  JFrame  jf1;
  JRadioButton jrb1,jrb2; //�Ա�ѡ��
  ButtonGroup bg;
  //�������ݿ�
  String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=У԰ʧ������ϵͳ";
  String userName="sa";
  String userPwd="571820";
//�������ݿ����
PreparedStatement ps=null;
Connection ct=null;
ResultSet rs=null;

//��ȡ����
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
  	
  	jLabel_id =  new JLabel("ѧ        ��:"+s_id);
  	jLabel_id.setFont(new Font("Dialog",1,16));
  	jLabel1 = new JLabel("��        ��");
  	jLabel2 = new JLabel("��  ��  ��");
  	jLabel3 = new JLabel("��  ��  ��");
  	jLabel4 = new JLabel("ȷ������");
  	jLabel5 = new JLabel("רҵ�༶");
  	jLabel6 = new JLabel("��ϵ��ʽ");
  	
  	jb3 = new JButton("ȷ �� �� ��");
  	jb4 = new JButton("ȡ �� �� ��");
  	
	 this.setTitle("�޸ĸ�������"); //���ñ���
	 this.setLocation(500,100); //����λ��
      //������ʾ
      this.setSize(400, 600);
      this.setResizable(false);
      this.setDefaultCloseOperation(HIDE_ON_CLOSE);
      this.add(jp1); //�������ӵ���½������
		
   	  jp1.setSize(800, 600);
	  jp1.setLayout(null);
     
	  jLabel_id.setBounds(100,70,200,40);
	  jLabel_id.setFont(new Font("Dialog",1,16));
	  jp1.add(jLabel_id);
	  
      jLabel1.setBounds(100,120,50,30);
      jp1.add(jLabel1); 
      jTextField1.setBounds(155,120,120,30); 
      jp1.add(jTextField1);              //�������û������ı���
      jTextField1.setDocument(new NumberLenghtLimitedDmt(12,NumberLenghtLimitedDmt.nolimit));//
      
      jLabel2.setBounds(100,190,60,30);
      jp1.add(jLabel2);
      password1.setBounds(155,190,120,30);//������������������
      jp1.add(password1);
      password1.setDocument(new NumberLenghtLimitedDmt(13,NumberLenghtLimitedDmt.nolimit));//���13λ����
      
      jLabel3.setBounds(100,240,60,30);
      jp1.add(jLabel3);
      password2.setBounds(155,240,120,30);//����������������������
      jp1.add(password2);
      password2.setDocument(new NumberLenghtLimitedDmt(13,NumberLenghtLimitedDmt.nolimit));//13λ����
      
      jLabel4.setBounds(100,290,60,30);
      jp1.add(jLabel4); 
      password3.setBounds(155,290,120,30); 
      jp1.add(password3);              //������ȷ����������������
      password3.setDocument(new NumberLenghtLimitedDmt(13,NumberLenghtLimitedDmt.inlimit));//8λѧ��
      password1.setEditable(false);
      password2.setEditable(false);
      password3.setEditable(false);
      
      jLabel5.setBounds(100,340,60,30);
      jp1.add(jLabel5); 
      jTextField3.setBounds(155,340,120,30); 
      jp1.add(jTextField3);              //�����Ӱ༶���ı���
      
      jLabel6.setBounds(100,390,60,30);
      jp1.add(jLabel6); 
      jTextField4.setBounds(155,390,120,30); 
      jp1.add(jTextField4);              //��������ϵ��ʽ���ı���
      jTextField4.setDocument(new NumberLenghtLimitedDmt(11,NumberLenghtLimitedDmt.inlimit));//���11λ�绰
      
      jb3.setBounds(90,450,100,30); 
      jp1.add(jb3);
      jb4.setBounds(210,450,100,30);//������ȷ�Ϻ�ȡ��
      jp1.add(jb4);
      
      jb3.addActionListener(this);
      jb4.addActionListener(this);
           
      jrb1=new JRadioButton("��"); 
      jrb2=new JRadioButton("Ů"); 
      jrb1.setContentAreaFilled(false);
      jrb2.setContentAreaFilled(false);
      // һ��Ҫ��jrb1 jrb2���뵽һ��ButtonGroup�й��� 
      ButtonGroup bg=new ButtonGroup(); 
      bg.add(jrb1); 
      bg.add(jrb2); 
      jrb1.setBounds(150,160,40,20); 
      jp1.add(jrb1);
      jrb2.setBounds(200,160,40,20);//������ȷ�Ϻ�ȡ��
      jp1.add(jrb2);
      
      //����ǰ������Ϣ��ȡ����
      try{
    	  ct=DriverManager.getConnection(dbURL,userName,userPwd);
    	  ps=ct.prepareStatement("select *from �û�");
    	  rs=ps.executeQuery();
    	  while(rs.next())
    	   {
    	    //ȥ����β�ո�  ʮ����Ҫ
    	    if(rs.getString("ѧ��").trim().equals(s_id))
    	    {
    	    	jTextField1.setText(rs.getString("����")) ; 
    	    	jTextField3.setText(rs.getString("�༶")) ;
    	    	jTextField4.setText(rs.getString("��ϵ��ʽ"));
    	    	if(rs.getString("�Ա�").trim().equals("��"))
    	    	 jrb1.setSelected(true);
    	    	if(rs.getString("�Ա�").trim().equals("Ů"))
       	    	 jrb2.setSelected(true);
    	    	password1.setText(rs.getString("����"));
    	    	password2.setText(rs.getString("����"));
    	    	password3.setText(rs.getString("����"));
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
     	   JOptionPane.showMessageDialog(null,"�޸ĳɹ�","��ʾ",JOptionPane.WARNING_MESSAGE);  //ע��ʧ�ܣ�������ʾ��;   
        }
	if(e.getSource()==jb4)
        {
     	   this.setVisible(false);
     	   JOptionPane.showMessageDialog(null,"�޸�ʧ��","��ʾ",JOptionPane.WARNING_MESSAGE);  //ע��ʧ�ܣ�������ʾ��;   
        }	
} 

public boolean updateid(){
	
	String newID,sex = null,stunum,callnum,grade;
	
	newID = jTextField1.getText();
	
	if(newID.equals(""))
	{
		JOptionPane.showMessageDialog(null,"�û�������Ϊ�գ������룡","��ʾ",JOptionPane.WARNING_MESSAGE);  //������ʾ��;
		return false;
	}

	if(jrb1.isSelected()==true)
		sex="��";
	if(jrb2.isSelected()==true)
		sex="Ů";

	grade= jTextField3.getText().trim();

	callnum= jTextField4.getText().trim();
	if(callnum.length()==0 || callnum.length()!=11)
	{
		JOptionPane.showMessageDialog(null,"��������ϵ��ʽ���ֻ���Ϊ11λ��!","",JOptionPane.WARNING_MESSAGE);  //���벻һ�£�������ʾ��;
		return false;
	}    
	try{
		Statement stmt=null;
		
		ct=DriverManager.getConnection(dbURL,userName,userPwd);
		stmt=ct.createStatement();//����SQL�������
		String a1= "UPDATE �û�   SET ����='"+newID+"',�Ա�='"+sex+"',�༶='"+grade+"',��ϵ��ʽ='"+callnum+"' where ѧ��='"+s_id+"';";//��������SQL���
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
