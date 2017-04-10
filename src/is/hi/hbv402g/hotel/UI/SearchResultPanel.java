package is.hi.hbv402g.hotel.UI;

import javax.swing.JPanel;

import is.hi.hbv402g.hotel.controllers.Search;
import is.hi.hbv402g.hotel.models.Room;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;

public class SearchResultPanel extends JPanel
{

	/**
	 * Create the panel.
	 */

	private Search search;
	private MainFrame mainFrame;
	private JTable table;
	private DefaultTableModel tableModel;

	public SearchResultPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		tableModel = new DefaultTableModel(0, 2);// new Object[][] { { null, null }, }, new String[] { "Hotel", "Room" }
		tableModel.setColumnIdentifiers(new String[] { "Hotel", "Room" });
		table = new JTable();
		table.setModel( tableModel);// new String[] { "Hotel", "Room" });
		
		scrollPane.setViewportView(table);

	}
	
	public void setSearch(Search search)
	{
		this.search = search;
		showSearchResults();
	}
	
	private void showSearchResults()
	{
		ArrayList<Room> hotelRooms = this.search.getSearchResults();
		
		tableModel.setRowCount(0);
		
		for(Room r : hotelRooms)
		{
			tableModel.addRow(new Object[] { r.getHotel().toString(), r.toString()});
		}
	}

}
