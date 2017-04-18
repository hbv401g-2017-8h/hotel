
package is.hi.hbv402g.hotel.UI;

import javax.swing.JPanel;

import is.hi.hbv402g.hotel.controllers.Search;
import is.hi.hbv402g.hotel.controllers.Search.SortBy;
import is.hi.hbv402g.hotel.models.Room;

import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class SearchResultPanel extends JPanel
{
	
	static final int STAR_MIN = 1;
    static final int STAR_MAX = 5;
	static final int PRICE_MIN = 0;
    static final int PRICE_MAX = 20000;
    static final int SINGLE_BED_MIN = 0;
    static final int SINGLE_BED_MAX = 5;
    static final int DOUBLE_BED_MIN = 0;
    static final int DOUBLE_BED_MAX = 3;

	private JPanel tablePanel;
	private JPanel filterPanel;
	private final JPanel filterAndSortPanel = new JPanel();
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
	private Boolean isBathroom = false;
	private Boolean isWiFi = false;
	private Boolean isBreakfast = false;
	private Boolean isCableTV = false;
	private Boolean isRoomService = false;
	private Boolean isServiceDesk = false;
	private RangeSlider starRangeSlider = new RangeSlider();
	private RangeSlider priceRangeSlider = new RangeSlider();
	private RangeSlider singleBedRangeSlider = new RangeSlider();
	private RangeSlider doubleBedRangeSlider = new RangeSlider();
	private Checkbox bathroomCheckBox;
	private final JLabel lblOrderBy = new JLabel("Order by:");
	private JComboBox comboBox = new JComboBox();
	private ArrayList<Checkbox> amenityCheckboxes = new ArrayList<>();

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
		searchTable.setFont(new Font("Dialog", Font.PLAIN, 12));
		searchTable.setModel( searchTableModel);
		searchTable.getTableHeader().setReorderingAllowed(false);
		
		searchTable.getColumn("Book").setCellRenderer(new ButtonTableRenderer());
		searchTable.getColumn("Book").setCellEditor(new ButtonTableEditor(new JCheckBox(), this));
		
		searchTable.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 2) {
	            	showRoom();
	            }
	         }
	      });
		
		searchScrollPane.setViewportView(searchTable);

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
		
		filterPanel = new JPanel();
		filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
		filterPanel.setMaximumSize(new Dimension(50, Integer.MAX_VALUE));
		
		// Add sub panels to searchResultPanel
		this.add(tablePanel);
		filterAndSortPanel.setMinimumSize(new Dimension(220, 300));
		filterAndSortPanel.setMaximumSize(new Dimension(220, Integer.MAX_VALUE));
		filterAndSortPanel.setPreferredSize(new Dimension(220, 300));
		add(filterAndSortPanel);
		filterAndSortPanel.setLayout(null);
		
		JLabel filterLabel = new JLabel("Filter By:");
		filterLabel.setBounds(12, 88, 103, 20);
		filterAndSortPanel.add(filterLabel);
		filterLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		filterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		filterLabel.setPreferredSize(new Dimension(20,20));
		starLabel.setBounds(12, 119, 244, 19);
		filterAndSortPanel.add(starLabel);
		starLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		starLabel.setText("Stars: Min: "+starMin.toString()+" Max: "+starMax.toString());
		starLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		starRangeSlider.setBounds(10, 149, 200, 16);
		filterAndSortPanel.add(starRangeSlider);
		starRangeSlider.setSnapToTicks(true);
		priceLabel.setBounds(10, 176, 239, 19);
		filterAndSortPanel.add(priceLabel);
		priceLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		priceLabel.setText("Price: Min:"+priceMin.toString()+" Max: "+priceMax.toString());
		priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		priceRangeSlider.setBounds(12, 206, 200, 16);
		filterAndSortPanel.add(priceRangeSlider);
		priceRangeSlider.setSnapToTicks(true);
		
		priceRangeSlider.setMinorTickSpacing(500);
		singleBedLabel.setBounds(12, 223, 286, 19);
		filterAndSortPanel.add(singleBedLabel);
		singleBedLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		singleBedLabel.setText("Single Beds: Min: "+singleBedMin.toString()+" Max: "+singleBedMax.toString());
		singleBedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		singleBedRangeSlider.setBounds(12, 253, 200, 16);
		filterAndSortPanel.add(singleBedRangeSlider);
		singleBedRangeSlider.setSnapToTicks(true);
		doubleBedLabel.setBounds(10, 280, 288, 19);
		filterAndSortPanel.add(doubleBedLabel);
		doubleBedLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		doubleBedLabel.setText("Double Beds: Min:"+doubleBedMin.toString()+" Max: "+doubleBedMax.toString());
		doubleBedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		doubleBedRangeSlider.setBounds(12, 299, 200, 16);
		filterAndSortPanel.add(doubleBedRangeSlider);
		bathroomCheckBox = new Checkbox("En Suite Bathroom");
		bathroomCheckBox.setBounds(12, 321, 198, 23);
		filterAndSortPanel.add(bathroomCheckBox);
		bathroomCheckBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblOrderBy.setFont(new Font("Dialog", Font.BOLD, 14));
		lblOrderBy.setBounds(12, 11, 166, 14);
		
		filterAndSortPanel.add(lblOrderBy);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				applyOrderAndFilters();
			}
		});
				
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"(None)", "A to Z", "Z to A", "Price: Low to high", "Price: High to low", "Stars: Low to high", "Stars: High to low"}));
		comboBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		comboBox.setBounds(12, 36, 166, 20);
		filterAndSortPanel.add(comboBox);
		
		bathroomCheckBox.addItemListener(new BathroomItemListener());
		
		doubleBedRangeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                RangeSlider slider = (RangeSlider) e.getSource();
                doubleBedMin = slider.getValue();
                doubleBedMax = slider.getUpperValue();
                doubleBedLabel.setText("Double Beds: Min:"+doubleBedMin.toString()+" Max: "+doubleBedMax.toString());
                
                applyOrderAndFilters();
            }
        });
		
		singleBedRangeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                RangeSlider slider = (RangeSlider) e.getSource();
                singleBedMin = slider.getValue();
                singleBedMax = slider.getUpperValue();
                singleBedLabel.setText("Single Beds: Min: "+singleBedMin.toString()+" Max: "+singleBedMax.toString());
                
                applyOrderAndFilters();
            }
        });
				
		priceRangeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                RangeSlider slider = (RangeSlider) e.getSource();
                priceMin = slider.getValue();
                priceMax = slider.getUpperValue();
                priceLabel.setText("Price: Min:"+priceMin.toString()+" Max: "+priceMax.toString());
                
                applyOrderAndFilters();
            }
        });
		
		// Add listener for filters.
		starRangeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                RangeSlider slider = (RangeSlider) e.getSource();
                starMin = slider.getValue();
                starMax = slider.getUpperValue();
                starLabel.setText("Stars: Min: "+starMin.toString()+" Max: "+starMax.toString());
                
                applyOrderAndFilters();
            }
        });
		this.add(filterPanel);
	}
	
	public void setSearch(Search search)
	{
		this.search = search;
		
		// Removes old checkboxes
		for(Checkbox cb : amenityCheckboxes)
		{
			filterAndSortPanel.remove(cb);
		}
		amenityCheckboxes.clear();
		
		// Makes new checkboxes for amenities
		int i = 0;
		for(String amenity : search.getListOfAmenities())
		{	
			Checkbox amenityCheckBox = new Checkbox(amenity);
			amenityCheckBox.setBounds(12, 321 + (++i)*23, 198, 23);
			amenityCheckBox.setFont(new Font("Dialog", Font.PLAIN, 12));
			amenityCheckBox.addItemListener(new ItemListener() 
			{
				public void itemStateChanged(ItemEvent e) 
				{
					if(e.getStateChange() == ItemEvent.SELECTED)
					{
						search.addAmenity(amenity);
					}
					else
					{
						search.removeAmenity(amenity);
					}
					applyOrderAndFilters();
				}
			});
			filterAndSortPanel.add(amenityCheckBox);
			amenityCheckboxes.add(amenityCheckBox);
		}
		applyOrderAndFilters();
	}
	
	private void applyOrderAndFilters()
	{
		search.setStarCount(starMin, starMax);
		search.setPriceRange(priceMin, priceMax);
		search.setNumberOfSingleBeds(singleBedMin, singleBedMax);
		search.setNumberOfDoubleBeds(doubleBedMin, doubleBedMax);
		search.setEnSuiteBathroom(isBathroom);
		search.filter();
		
		if (String.valueOf(comboBox.getSelectedItem()).equals("A to Z"))
			search.sort(SortBy.HOTEL_NAME_AZ);
		if (String.valueOf(comboBox.getSelectedItem()).equals("Z to A"))
			search.sort(SortBy.HOTEL_NAME_ZA);
		if (String.valueOf(comboBox.getSelectedItem()).equals("Price: Low to high"))
			search.sort(SortBy.PRICE_ASCENDING);
		if (String.valueOf(comboBox.getSelectedItem()).equals("Price: High to low"))
			search.sort(SortBy.PRICE_DESCENDING);
		if (String.valueOf(comboBox.getSelectedItem()).equals("Stars: Low to high"))
			search.sort(SortBy.STARCOUNT_ASCENDING);
		if (String.valueOf(comboBox.getSelectedItem()).equals("Stars: High to low"))
			search.sort(SortBy.STARCOUNT_DESCENDING);
		if (String.valueOf(comboBox.getSelectedItem()).equals("(None)"))
			search.sort(SortBy.NONE);
		
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

	public void bookRoom()
	{
        int row = searchTable.getSelectedRow();
        ArrayList<Room> rooms = search.getSearchResults();
		MainFrame mf = Utilities.findParent(this, MainFrame.class);
		mf.openBooking(rooms.get(row));
	}
	
	public void showRoom()
	{
		int row = searchTable.getSelectedRow();
        ArrayList<Room> rooms = search.getSearchResults();
        MainFrame mf = Utilities.findParent(this, MainFrame.class);
		mf.showRoom(rooms.get(row));
	}
	
	class BathroomItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			isBathroom = e.getStateChange() == ItemEvent.SELECTED;
			applyOrderAndFilters();
		}
	}
}
