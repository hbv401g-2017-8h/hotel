package is.hi.hbv402g.hotel.UI;

import javax.swing.JPanel;

import is.hi.hbv402g.hotel.controllers.Search;
import is.hi.hbv402g.hotel.models.Room;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

public class SearchResultPanel extends JPanel
{
	
	static final int STAR_MIN = 0;
    static final int STAR_MAX = 5;
    static final int STAR_INIT = 3;
	static final int PRICE_MIN = 0;
    static final int PRICE_MAX = 30000;
    static final int PRICE_INIT = 5000;
    static final int SINGLE_BED_MIN = 0;
    static final int SINGLE_BED_MAX = 5;
    static final int SINGLE_BED_INIT = 3;
    static final int DOUBLE_BED_MIN = 0;
    static final int DOUBLE_BED_MAX = 3;
    static final int DOUBLE_BED_INIT = 2;

	/**
	 * Create the panel.
	 */
	private JPanel tablePanel;
	private JPanel filterPanel;
	private Search search;
	private MainFrame mainFrame;
	private JTable searchTable;
	private SearchTableModel searchTableModel;
	private JLabel priceLabel;
	private JLabel starLabel;
	private JLabel singleBedLabel;
	private JLabel doubleBedLabel;
	private JSlider starSlider;
	private JSlider priceSlider;
	private JSlider singleBedSlider;
	private JSlider doubleBedSlider;
	private Checkbox bathroomCheckBox;

	public SearchResultPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		// Specify tablePanel with search results
		tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.X_AXIS)); 

		JScrollPane searchScrollPane = new JScrollPane();
		tablePanel.add(searchScrollPane);
		
		searchTableModel = new SearchTableModel(0, 6);
		
		searchTableModel.setColumnIdentifiers(new String[] { "Hotel", "Number of single beds", "Number of double beds", "En Suite Bathroom", "Cost per night", "Book" });
		searchTable = new JTable();
		searchTable.setModel( searchTableModel);
		searchTable.getTableHeader().setReorderingAllowed(false);
		
		searchTable.getColumn("Book").setCellRenderer(new ButtonTableRenderer());
		searchTable.getColumn("Book").setCellEditor(new ButtonTableEditor(new JCheckBox()));
		
		searchScrollPane.setViewportView(searchTable);
		
		filterPanel = new JPanel();
		filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
		filterPanel.setMaximumSize(new Dimension(50, Integer.MAX_VALUE));
		
		// Make components to filter panel
		
		JLabel filterLabel = new JLabel("Filter By:");
		starLabel = new JLabel("Number of Stars");
		priceLabel = new JLabel("Price");
		singleBedLabel = new JLabel("Number of Single Beds");
		doubleBedLabel = new JLabel("Number of Double Beds");
		bathroomCheckBox = new Checkbox("En Suite Bathroom");
		
		starSlider = new JSlider(JSlider.HORIZONTAL, STAR_MIN, STAR_MAX, STAR_INIT);
		priceSlider = new JSlider(JSlider.HORIZONTAL, PRICE_MIN, PRICE_MAX, PRICE_INIT);
		singleBedSlider = new JSlider(JSlider.HORIZONTAL, SINGLE_BED_MIN, SINGLE_BED_MAX, SINGLE_BED_INIT);
		doubleBedSlider = new JSlider(JSlider.HORIZONTAL, DOUBLE_BED_MIN, DOUBLE_BED_MAX, DOUBLE_BED_INIT);
		
		//Turn on labels at major tick marks.
		starSlider.setMajorTickSpacing(1);
		starSlider.setMinorTickSpacing(1);
		starSlider.setPaintTicks(true);
		starSlider.setPaintLabels(true);
		
		// Add components to filter panel
		filterPanel.add(filterLabel);
		filterPanel.add(starLabel);
		filterPanel.add(starSlider);
		filterPanel.add(priceLabel);
		filterPanel.add(priceSlider);
		filterPanel.add(singleBedLabel);
		filterPanel.add(singleBedSlider);
		filterPanel.add(doubleBedLabel);
		filterPanel.add(doubleBedSlider);
		filterPanel.add(bathroomCheckBox);
		
		// Add sub panels to searchResultPanel
		this.add(tablePanel);
		this.add(filterPanel);

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
