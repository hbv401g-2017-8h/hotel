package is.hi.hbv402g.hotel.UI;

import javax.swing.table.DefaultTableModel;

public class SearchTableModel extends DefaultTableModel 
{

   public SearchTableModel(int rowCount, int colCount) {
      super(rowCount, colCount);
   }

   public boolean isCellEditable(int row, int column) {
      if(column < 5)
      {
    	  return false;
      }
      else
      {
    	  return true;
      }
	   
   }
}