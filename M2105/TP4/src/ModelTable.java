package view;

import javax.swing.table.*;

public class ModelTable extends DefaultTableModel
{
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}	
}