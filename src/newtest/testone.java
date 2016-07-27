//主界面
package newtest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

class testone extends JFrame{	
	private static final long serialVersionUID = 1L;
	
	//界面变量
	JPanel2 jp1;
	JPanel3 jp2;
	JPanel4 jp3;
	JLabel jl_ID;
	JButton jb1,jb2,jb3,jb4;
	JButton jb_editid,jb_edit;
	JTable jt=null;
	JScrollPane jsp=null;
	Vector rowData,columName;
    JRadioButton jrb1,jrb2,jrb3,jrb4,jrb5; //类别单选框
    ButtonGroup bg; 
	
	//链接数据库变量
	PreparedStatement ps1=null;
	PreparedStatement ps2=null;
	Connection ct=null;
	ResultSet rs=null,rs2=null;
	
	String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=校园失物招领系统";
	String userName="sa";
	String userPwd="571820";
    String s_ID,s_name;
    
	public testone(String login_id,String login_name)
	{     	
		s_ID=login_id;
		s_name=login_name;
		String s1="用户姓名:"+login_name+"                        ";
	    this.setTitle("校园失物招领"); //设置标题
		this.setLocation(300,10); //设置位置
		this.setResizable(false);
     
		jp1=new JPanel2();
		jp1.setPreferredSize(new Dimension(0, 80));
		jp1.setLayout(null);
		jp1.setSize(800,500);
		jl_ID=new JLabel(s1);
		jl_ID.setFont(new Font("Dialog",1,16));
		jb1=new JButton("查询");
		jb_editid=new JButton("修改个人信息");
		
		jb_editid.setBounds(150,15,120,20);
		jb1.setBounds(350,20,60,30);
		jl_ID.setBounds(10,10,200,30);
		jp1.add(jl_ID);
		jp1.add(jb1);
		jp1.add(jb_editid);
		
		jrb1=new JRadioButton("校园卡"); 
        jrb2=new JRadioButton("钱包"); 
        jrb3=new JRadioButton("书本"); 
        jrb4=new JRadioButton("手机"); 
        jrb5=new JRadioButton("其他"); 
        // 一定要把jrb1~5放入到一个ButtonGroup中管理 
        ButtonGroup bg=new ButtonGroup(); 
        bg.add(jrb1); 
        bg.add(jrb2); 
        bg.add(jrb3); 
        bg.add(jrb4); 
        bg.add(jrb5); 
        jrb1.setBounds(420,20,70,30);
        jrb2.setBounds(490,20,60,30);
        jrb3.setBounds(550,20,60,30);
        jrb4.setBounds(610,20,60,30);
        jrb5.setBounds(670,20,60,30);
        jp1.add(jrb1);
        jp1.add(jrb2);
        jp1.add(jrb3);
        jp1.add(jrb4);
        jp1.add(jrb5);
        jrb1.setContentAreaFilled(false);
        jrb2.setContentAreaFilled(false);
        jrb3.setContentAreaFilled(false);
        jrb4.setContentAreaFilled(false);
        jrb5.setContentAreaFilled(false);
        
		jp2=new JPanel3();
		jp2.setPreferredSize(new Dimension(0, 50));
		jp2.setLayout(null);
		jb2=new JButton("添加记录");
		jb3=new JButton("查询我已添加记录");
		jb4=new JButton("删除记录");
		jb_edit=new JButton("修改记录");
		JButton Jb =new JButton("显示当前拾物图片");
		
		Jb.setBounds(500,10,150,30);
		jp2.add(Jb);
		jb2.setBounds(20,10,100,30);
		jb3.setBounds(20,50,150,30);
		jb4.setBounds(260,10,100,30);
		jb_edit.setBounds(140,10,100,30);
		jp2.add(jb2);
		jp1.add(jb3);
		jp2.add(jb4);
		jp2.add(jb_edit);
		
	    jb1.addActionListener(new Control(Control.jb1,this));
	    jb2.addActionListener(new Control(Control.jb2,this));
		jb3.addActionListener(new Control(Control.jb3,this));
		jb4.addActionListener(new Control(Control.jb4,this));
		jb_edit.addActionListener(new Control(Control.jb_edit,this));
		jb_editid.addActionListener(new Control(Control.jb_editid,this));
		Jb.addActionListener(new Control(Control.jb,this));
		
	     columName = new Vector();
	     columName.add("拾物编号");
	     columName.add("拾物种类");
	     columName.add("拾物名称");
	     columName.add("拾到地点");
	     columName.add("拾到时间");
	     columName.add("拾物描述");
	     columName.add("拾到者学号");
	     columName.add("联系方式");
	     
	     rowData =new Vector();    
	         
	     jt=new JTable(rowData,columName);
	//     jt.setVisible(false);
	     Mytable jt1 = new Mytable(rowData,columName);
	     jt.setModel(jt1);
	     jt.setEnabled(false);
	     jsp=new JScrollPane(jt);
	     jt.getTableHeader().setFont(new Font("Dialog",0,14));
	     jt.getTableHeader().setBackground(Color.red);
	     jt.setGridColor(Color.blue);
	     jt.setRowHeight(70);
	     jt.setSelectionBackground(Color.lightGray);
	     jt.getTableHeader().setReorderingAllowed(false);   //不可整列移动   
	     jt.getTableHeader().setResizingAllowed(false);   //不可拉动表格
//	     jt.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());
	     
	     
	     jp3=new JPanel4();
	     jp3.setLayout(null);
	     jsp.setBounds(33,150,733,360);
	     jp3.add(jsp);
	     this.add(jp1,"North");
	     this.add(jp3);
	     this.add(jp2,"South");
	     this.setSize(800,700);
	     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     this.setVisible(true); 
	 }
}

class TableCellTextAreaRenderer extends JTextArea implements TableCellRenderer {
		
    public TableCellTextAreaRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
//        this.setFont(new Font(null,0,15));
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        // 计算当下行的最佳高度
        int maxPreferredHeight = 0;
        for (int i = 0; i < table.getColumnCount(); i++) {
            setText("" + table.getValueAt(row, i));
            setSize(table.getColumnModel().getColumn(column).getWidth(), 0);
            maxPreferredHeight = Math.max(maxPreferredHeight, getPreferredSize().height);
        }
       if(maxPreferredHeight > 70)
       {
//        if (table.getRowHeight(row) != maxPreferredHeight)  // 少了这行则处理器瞎忙
    	   table.setRowHeight(row, maxPreferredHeight);
       }
       else
           table.setRowHeight(row, 70);
        if (isSelected) 
        {
           super.setBackground(table.getSelectionBackground());
        }
        if (isSelected==false) 
        {
           super.setBackground(Color.white);
        } 
        setText(value == null ? "" : value.toString());
        return this;
    }
}
