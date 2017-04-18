package is.hi.hbv402g.hotel.UI;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;
  
/**
 * @version 1.0 11/09/98
 * * Retrieved from: http://esus.com/jbutton-cell-jtable/
 */
@SuppressWarnings("serial")
public class ButtonTableRenderer extends JButton implements TableCellRenderer {
  
  public ButtonTableRenderer() {
    setOpaque(true);
  }
   
  public Component getTableCellRendererComponent(JTable table, Object value,
                   boolean isSelected, boolean hasFocus, int row, int column) {
    if (isSelected) {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
    } else{
      setForeground(table.getForeground());
      setBackground(UIManager.getColor("Button.background"));
    }
    setText( (value ==null) ? "" : value.toString() );
    return this;
  }
}