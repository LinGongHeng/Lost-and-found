//Ŀ���������ɵı�����ѡ�У����ǲ����Ա༭
package newtest;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class Mytable extends DefaultTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Mytable(Vector<?> rowData, Vector<?> columName) {
		super(rowData,columName);
	}

	public boolean isCellEditable(int row, int column)
	{
	   return false;//����ķ��������� return true�ģ����ԾͿ��Ա༭��~~~
	}
}
