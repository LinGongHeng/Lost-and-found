//目的是让生成的表格可以选中，但是不可以编辑
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
	   return false;//父类的方法里面是 return true的，所以就可以编辑了~~~
	}
}
