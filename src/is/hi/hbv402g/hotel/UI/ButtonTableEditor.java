package is.hi.hbv402g.hotel.UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @version 1.0 11/09/98 Retrieved from: http://esus.com/jbutton-cell-jtable/
 */
public class ButtonTableEditor extends DefaultCellEditor
{
	protected JButton button;
	private String label;
	private boolean isPushed;
	private SearchResultPanel parentPanel;

	public ButtonTableEditor(JCheckBox checkBox, SearchResultPanel parentPanel)
	{
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				fireEditingStopped();
			}
		});
		this.parentPanel = parentPanel;
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
	{
		if (isSelected)
		{
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		}
		else
		{
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		label = (value == null) ? "" : value.toString();
		button.setText(label);
		isPushed = true;
		return button;
	}

	public Object getCellEditorValue()
	{
		if (isPushed)
		{
			this.parentPanel.bookRoom();
		}
		isPushed = false;
		return new String(label);
	}

	public boolean stopCellEditing()
	{
		isPushed = false;
		return super.stopCellEditing();
	}

	protected void fireEditingStopped()
	{
		super.fireEditingStopped();
	}
}
