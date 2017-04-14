
package is.hi.hbv402g.hotel.UI;

import javax.swing.JPanel;

import is.hi.hbv402g.hotel.controllers.Search;
import is.hi.hbv402g.hotel.models.Room;

import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	static final int PRICE_MIN = 0;
    static final int PRICE_MAX = 30000;
    static final int SINGLE_BED_MIN = 0;
    static final int SINGLE_BED_MAX = 5;
    static final int DOUBLE_BED_MIN = 0;
    static final int DOUBLE_BED_MAX = 3;

	private JPanel tablePanel;
	private JPanel filterPanel;
	private Search search;
	private JTable searchTable;
	private SearchTableModel searchTableModel;
	private JLabel starLabel = new JLabel();
	private JLabel priceLabel = new JLabel();
	private JLabel singleBedLabel = new JLabel();
	private JLabel doubleBedLabel = new JLabel();
	private Integer starMin;
	private Integer starMax;
	private Integer priceMin;
	private Integer priceMax;
	private Integer singleBedMin;
	private Integer singleBedMax;
	private Integer doubleBedMin;
	private Integer doubleBedMax;
	private RangeSlider starRangeSlider = new RangeSlider();
	private RangeSlider priceRangeSlider = new RangeSlider();
	private RangeSlider singleBedRangeSlider = new RangeSlider();
	private RangeSlider doubleBedRangeSlider = new RangeSlider();
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
		searchTable.getColumn("Book").setCellEditor(new ButtonTableEditor(new JCheckBox(), null));
		
		searchTable.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 2) {
	               JTable target = (JTable) e.getSource();
	               int row = target.getSelectedRow();
	               ArrayList<Room> rooms = search.getSearchResults();
	               System.out.println(rooms.get(row));
	            }
	         }
	      });
		
		searchScrollPane.setViewportView(searchTable);

		filterPanel = new JPanel();
		filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
		filterPanel.setMaximumSize(new Dimension(50, Integer.MAX_VALUE));
		
		// Make components to filter panel
		// Initialize sliders
		initRangeSlider(starRangeSlider, STAR_MIN, STAR_MAX);
		initRangeSlider(priceRangeSlider, PRICE_MIN, PRICE_MAX);
		initRangeSlider(singleBedRangeSlider, SINGLE_BED_MIN, SINGLE_BED_MAX);
		initRangeSlider(doubleBedRangeSlider, DOUBLE_BED_MIN, DOUBLE_BED_MAX);
		
        
        
		starMin = starRangeSlider.getValue();
        starMax = starRangeSlider.getUpperValue();
        priceMin = priceRangeSlider.getValue();
    	priceMax = priceRangeSlider.getUpperValue();
    	singleBedMin = singleBedRangeSlider.getValue();
    	singleBedMax = singleBedRangeSlider.getUpperValue();
    	doubleBedMin = doubleBedRangeSlider.getValue();
    	doubleBedMax = doubleBedRangeSlider.getUpperValue();
		
		// Add listener for filters.
		starRangeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                RangeSlider slider = (RangeSlider) e.getSource();
                starMin = slider.getValue();
                starMax = slider.getUpperValue();
                starLabel.setText("Number of Stars: Min: "+starMin.toString()+" Max: "+starMax.toString());
            }
        });
		
		priceRangeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                RangeSlider slider = (RangeSlider) e.getSource();
                priceMin = slider.getValue();
                priceMax = slider.getUpperValue();
                priceLabel.setText("Price: Min:"+priceMin.toString()+" Max: "+priceMax.toString());
            }
        });
		
		singleBedRangeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                RangeSlider slider = (RangeSlider) e.getSource();
                singleBedMin = slider.getValue();
                singleBedMax = slider.getUpperValue();
                singleBedLabel.setText("Number of Single Beds: Min: "+singleBedMin.toString()+" Max: "+singleBedMax.toString());
            }
        });
		
		doubleBedRangeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                RangeSlider slider = (RangeSlider) e.getSource();
                doubleBedMin = slider.getValue();
                doubleBedMax = slider.getUpperValue();
                doubleBedLabel.setText("Number of Double Beds: Min:"+doubleBedMin.toString()+" Max: "+doubleBedMax.toString());
            }
        });
		
		
		JLabel filterLabel = new JLabel("Filter By:");
		starLabel.setText("Number of Stars: Min: "+starMin.toString()+" Max: "+starMax.toString());
		priceLabel.setText("Price: Min:"+priceMin.toString()+" Max: "+priceMax.toString());
		singleBedLabel.setText("Number of Single Beds: Min: "+singleBedMin.toString()+" Max: "+singleBedMax.toString());
		doubleBedLabel.setText("Number of Double Beds: Min:"+doubleBedMin.toString()+" Max: "+doubleBedMax.toString());
		bathroomCheckBox = new Checkbox("En Suite Bathroom");
		
		// Add components to filter panel
		filterPanel.add(filterLabel);
		filterPanel.add(starLabel);
		filterPanel.add(starRangeSlider);
		filterPanel.add(priceLabel);
		filterPanel.add(priceRangeSlider);
		filterPanel.add(singleBedLabel);
		filterPanel.add(singleBedRangeSlider);
		filterPanel.add(doubleBedLabel);
		filterPanel.add(doubleBedRangeSlider);
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
	
	private void initRangeSlider( RangeSlider rs, int min, int max)
	{
		rs.setPreferredSize(new Dimension(240, rs.getPreferredSize().height));
		rs.setMinimum(min);
		rs.setMaximum(max);
		rs.setValue(min);
		rs.setUpperValue(max);
	}


	public void bookRoom(Room r)
	{
		MainFrame mf = Utilities.findParent(this, MainFrame.class);
		mf.openBooking(r);
	}

}
