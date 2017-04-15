
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

public class SearchResultPanel extends JPanel
{
	
	static final int STAR_MIN = 1;
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
	private final Component verticalGlue = Box.createVerticalGlue();
	private final Component rigidArea = Box.createRigidArea(new Dimension(0, 40));
	private final Component rigidArea_1 = Box.createRigidArea(new Dimension(0, 40));
	private final Component rigidArea_2 = Box.createRigidArea(new Dimension(0, 40));
	private final Component rigidArea_3 = Box.createRigidArea(new Dimension(0, 20));
	private final Component rigidArea_4 = Box.createRigidArea(new Dimension(0, 40));

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

		filterPanel = new JPanel();
		filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
		filterPanel.setMaximumSize(new Dimension(50, Integer.MAX_VALUE));
		
		// Make components to filter panel
		// Initialize sliders
		initRangeSlider(starRangeSlider, STAR_MIN, STAR_MAX);
		initRangeSlider(priceRangeSlider, PRICE_MIN, PRICE_MAX);
		initRangeSlider(singleBedRangeSlider, SINGLE_BED_MIN, SINGLE_BED_MAX);
		initRangeSlider(doubleBedRangeSlider, DOUBLE_BED_MIN, DOUBLE_BED_MAX);
		priceRangeSlider.setSnapToTicks(true);
		
		priceRangeSlider.setMinorTickSpacing(500);
        
		starMin = starRangeSlider.getValue();
        starMax = starRangeSlider.getUpperValue();
        priceMin = priceRangeSlider.getValue();
    	priceMax = priceRangeSlider.getUpperValue();
    	singleBedMin = singleBedRangeSlider.getValue();
    	singleBedMax = singleBedRangeSlider.getUpperValue();
    	doubleBedMin = doubleBedRangeSlider.getValue();
    	doubleBedMax = doubleBedRangeSlider.getUpperValue();
		starRangeSlider.setSnapToTicks(true);
		
		// Add listener for filters.
		starRangeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                RangeSlider slider = (RangeSlider) e.getSource();
                starMin = slider.getValue();
                starMax = slider.getUpperValue();
                starLabel.setText("Stars: Min: "+starMin.toString()+" Max: "+starMax.toString());
                
                setSearch(search);
            }
        });
		
		priceRangeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                RangeSlider slider = (RangeSlider) e.getSource();
                priceMin = slider.getValue();
                priceMax = slider.getUpperValue();
                priceLabel.setText("Price: Min:"+priceMin.toString()+" Max: "+priceMax.toString());
                
                setSearch(search);
            }
        });
		singleBedRangeSlider.setSnapToTicks(true);
		
		singleBedRangeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                RangeSlider slider = (RangeSlider) e.getSource();
                singleBedMin = slider.getValue();
                singleBedMax = slider.getUpperValue();
                singleBedLabel.setText("Single Beds: Min: "+singleBedMin.toString()+" Max: "+singleBedMax.toString());
                
                setSearch(search);
            }
        });
		doubleBedRangeSlider.setSnapToTicks(true);
		
		doubleBedRangeSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                RangeSlider slider = (RangeSlider) e.getSource();
                doubleBedMin = slider.getValue();
                doubleBedMax = slider.getUpperValue();
                doubleBedLabel.setText("Double Beds: Min:"+doubleBedMin.toString()+" Max: "+doubleBedMax.toString());
                
                setSearch(search);
            }
        });
		
		
		JLabel filterLabel = new JLabel("Filter By:");
		filterLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		filterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		filterLabel.setPreferredSize(new Dimension(20,20));
		starLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		starLabel.setText("Stars: Min: "+starMin.toString()+" Max: "+starMax.toString());
		starLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		priceLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		priceLabel.setText("Price: Min:"+priceMin.toString()+" Max: "+priceMax.toString());
		priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		singleBedLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		singleBedLabel.setText("Single Beds: Min: "+singleBedMin.toString()+" Max: "+singleBedMax.toString());
		singleBedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		doubleBedLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		doubleBedLabel.setText("Double Beds: Min:"+doubleBedMin.toString()+" Max: "+doubleBedMax.toString());
		doubleBedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		bathroomCheckBox = new Checkbox("En Suite Bathroom");
		bathroomCheckBox.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		
		bathroomCheckBox.addItemListener(new BathroomItemListener());
		
		
		filterPanel.add(rigidArea_4);
		
		// Add components to filter panel
		filterPanel.add(filterLabel);
		
		filterPanel.add(rigidArea_3);
		filterPanel.add(new JSeparator());
		filterPanel.add(starLabel);
		filterPanel.add(starRangeSlider);
		
		filterPanel.add(rigidArea);
		filterPanel.add(priceLabel);
		filterPanel.add(priceRangeSlider);
		
		filterPanel.add(rigidArea_1);
		filterPanel.add(singleBedLabel);
		filterPanel.add(singleBedRangeSlider);
		
		filterPanel.add(rigidArea_2);
		filterPanel.add(doubleBedLabel);
		filterPanel.add(doubleBedRangeSlider);
		filterPanel.add(bathroomCheckBox);
		
		// Add sub panels to searchResultPanel
		this.add(tablePanel);
		this.add(filterPanel);
		
		filterPanel.add(verticalGlue);
	}
	
	public void setSearch(Search search)
	{
		search.setStarCount(starMin, starMax);
		search.setPriceRange(priceMin, priceMax);
		search.setNumberOfSingleBeds(singleBedMin, singleBedMax);
		search.setNumberOfDoubleBeds(doubleBedMin, doubleBedMax);
		
		search.filter();
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
		    if (e.getStateChange() == ItemEvent.SELECTED)
		    {
		    	System.out.println("Selected");
		    }
		    else 
		    {
		    	System.out.println("UnSelected");
		    }
		}
	}
}
