//���ɵ�½����
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
	
	JTextField jTextField ;//�����ı������
    JPasswordField password;//������������
    JLabel jLabel1,jLabel2;
    MyPanel jp1;
    JButton jb1,jb2;//������ť
    JFrame  jf1;
    JRadioButton jrb1,jrb2; //�Ա�ѡ��
    ButtonGroup bg; 
    
    public login(){
    	
    	jf1=this;
		jp1 = new MyPanel();
      
    	jTextField = new JTextField();
    	password = new JPasswordField(13);
    	jLabel1 = new JLabel("ѧ     ��");
    	jLabel2 = new JLabel("��     ��");
    	jb1 = new JButton("��½");
    	jb2 = new JButton("ע��");
    	
		this.setTitle("У԰ʧ������"); //���ñ���
		this.setLocation(500,150); //����λ��
        //������ʾ
        this.setSize(400, 300);
        this.setResizable(false);
        this.add(jp1); //�������ӵ���½������
		
     	jp1.setSize(400, 300);
		jp1.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
       
        jLabel1.setBounds(80,70,50,30);
        jp1.add(jLabel1); 
        jTextField.setBounds(130,70,140,30);//�������û������ı��� 
        jp1.add(jTextField);
        jTextField.setDocument(new NumberLenghtLimitedDmt(8,NumberLenghtLimitedDmt.inlimit));
        jTextField.setOpaque(false);
        
        jLabel2.setBounds(80,130,50,30);
        jp1.add(jLabel2);
        password.setBounds(130,130,140,30);//��������������������
        jp1.add(password);
        password.setDocument(new NumberLenghtLimitedDmt(13,NumberLenghtLimitedDmt.nolimit));
        password.setOpaque(false);
        
        jb1.setBounds(120,210,60,30); 
        jp1.add(jb1);
        jb2.setBounds(220,210,60,30);//������ȷ�Ϻ�ȡ��
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

