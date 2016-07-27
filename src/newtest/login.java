//生成登陆界面
package newtest;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class login extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JTextField jTextField ;//定义文本框组件
    JPasswordField password;//定义密码框组件
    JLabel jLabel1,jLabel2;
    MyPanel jp1;
    JButton jb1,jb2;//创建按钮
    JFrame  jf1;
    JRadioButton jrb1,jrb2; //性别单选框
    ButtonGroup bg; 
    
    public login(){
    	
    	jf1=this;
		jp1 = new MyPanel();
      
    	jTextField = new JTextField();
    	password = new JPasswordField(13);
    	jLabel1 = new JLabel("学     号");
    	jLabel2 = new JLabel("密     码");
    	jb1 = new JButton("登陆");
    	jb2 = new JButton("注册");
    	
		this.setTitle("校园失物招领"); //设置标题
		this.setLocation(500,150); //设置位置
        //设置显示
        this.setSize(400, 300);
        this.setResizable(false);
        this.add(jp1); //将面板添加到登陆框上面
		
     	jp1.setSize(400, 300);
		jp1.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
       
        jLabel1.setBounds(80,70,50,30);
        jp1.add(jLabel1); 
        jTextField.setBounds(130,70,140,30);//面板添加用户名和文本框 
        jp1.add(jTextField);
        jTextField.setDocument(new NumberLenghtLimitedDmt(8,NumberLenghtLimitedDmt.inlimit));
        jTextField.setOpaque(false);
        
        jLabel2.setBounds(80,130,50,30);
        jp1.add(jLabel2);
        password.setBounds(130,130,140,30);//面板添加密码和密码输入框
        jp1.add(password);
        password.setDocument(new NumberLenghtLimitedDmt(13,NumberLenghtLimitedDmt.nolimit));
        password.setOpaque(false);
        
        jb1.setBounds(120,210,60,30); 
        jp1.add(jb1);
        jb2.setBounds(220,210,60,30);//面板添加确认和取消
        jp1.add(jb2);
        jb1.setBackground(Color.lightGray);
        jb2.setBackground(Color.lightGray);
        
        jb1.addActionListener(new Command(Command.jb1,jf1));
        jb2.addActionListener(new Command(Command.jb2,jf1));
        
        this.setVisible(true);  
    }
    
    public String toString()
    {
      return jTextField.getText().trim()+""+String.valueOf(password.getPassword()).trim();
    }
}

