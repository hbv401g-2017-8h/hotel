package is.hi.hbv402g.hotel.UI;

import javax.swing.JPanel;

import is.hi.hbv402g.hotel.controllers.Search;
import is.hi.hbv402g.hotel.models.Room;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;

public class SearchResultPanel extends JPanel
{

	/**
	 * Create the panel.
	 */

	private Search search;
	private MainFrame mainFrame;
	private JTable searchTable;
	private SearchTableModel searchTableModel;

	public SearchResultPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JScrollPane searchScrollPane = new JScrollPane();
		add(searchScrollPane);
		
		searchTableModel = new SearchTableModel(0, 6);
		
		searchTableModel.setColumnIdentifiers(new String[] { "Hotel", "Number of single beds", "Number of double beds", "En Suite Bathroom", "Cost per night", "Book" });
		searchTable = new JTable();
		searchTable.setModel( searchTableModel);
		searchTable.getTableHeader().setReorderingAllowed(false);
		
		searchTable.getColumn("Book").setCellRenderer(new ButtonTableRenderer());
		searchTable.getColumn("Book").setCellEditor(new ButtonTableEditor(new JCheckBox()));
		
		searchScrollPane.setViewportView(searchTable);

	}
	
	public void setSearch(Search search)
	{
		this.search = search;
		showSearchResults();
	}
	
	private void showSearchResults()
	{
		ArrayList<Room> hotelRooms = this.search.getSearchResults();
		
		searchTableModel.setRowCount(0);
		
		for(Room r : hotelRooms)
		{
			searchTableModel.addRow(new Object[] { r.getHotel().getName(), r.getNumberOfSingleBeds(), r.getNumberOfDoubleBeds(), (r.getEnSuiteBathroom()? "Yes": "No"), r.getCostPerNight(), "Book"});
		
		}
	}

}
