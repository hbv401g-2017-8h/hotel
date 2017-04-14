package is.hi.hbv402g.hotel.UI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import is.hi.hbv402g.hotel.controllers.Search;
import is.hi.hbv402g.hotel.db.DatabaseManager;

import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.JSlider;
import java.awt.Checkbox;

public class MainFrame extends JFrame
{
	
	static final int PRICE_MIN = 0;
    static final int PRICE_MAX = 30000;
    static final int PRICE_INIT = 5000;    //initial frames per second
    
    static final String hotelHintText = "Hotel";
    static final String locationHintText = "Location";
    static final String dateFromHintText = "From: DD.MM.YYYY";
    static final String dateToHintText = "To: DD.MM.YYYY";

	private JPanel masterPanel;
	private JPanel lowerPanel;
	private JPanel filterPanel;
	private SearchResultPanel searchResultPanel;
	private JTextField textFieldHotel;
	private JTextField textFieldLocation;
	private JTextField textFieldDateFrom;
	private JTextField textFieldDateTo;
	private JLabel priceLabel;
	private JLabel starLabel;
	private JLabel singleBedLabel;
	private JLabel doubleBedLabel;
	private JSlider starSlider;
	private JSlider priceSlider;
	private JSlider singleBedSlider;
	private JSlider doubleBedSlider;
	private Checkbox bathroomCheckBox;
	
	
	

	// Lætur texta hverfa í leitarglugga
    private void hotelFocusGained(java.awt.event.FocusEvent evt, JTextField tf, String text) {                                         
        if (tf.getText().equals(text))
        {
        	tf.setText("");
        }
    }                                        

    // Lætur texta birtast í leitarglugga
    private void hotelFocusLost(java.awt.event.FocusEvent evt, JTextField tf, String text) {                                       
        if (tf.getText().equals(""))
        {
        	tf.setText(text);
        }
    }
	

	/**
	 * Create the frame.
	 */
	public MainFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		masterPanel = new JPanel();
		masterPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(masterPanel);
		masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
		
		JPanel searchTextPanel = new JPanel();
		masterPanel.add(searchTextPanel);
		searchTextPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
		
		// Define search text fields with input hint text.
		textFieldHotel = new JTextField();
		searchTextPanel.add(textFieldHotel);
		textFieldHotel.setColumns(20);
		textFieldHotel.setText(hotelHintText);
		textFieldHotel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hotelFocusGained(evt, textFieldHotel, hotelHintText);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hotelFocusLost(evt, textFieldHotel, hotelHintText);
            }
        });
		
		textFieldLocation = new JTextField();
		searchTextPanel.add(textFieldLocation);
		textFieldLocation.setColumns(20);
		textFieldLocation.setText(locationHintText);
		textFieldLocation.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hotelFocusGained(evt, textFieldLocation, locationHintText);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hotelFocusLost(evt, textFieldLocation, locationHintText);
            }
        });
		
		textFieldDateFrom = new JTextField();
		searchTextPanel.add(textFieldDateFrom);
		textFieldDateFrom.setColumns(15);
		textFieldDateFrom.setText(dateFromHintText);
		textFieldDateFrom.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hotelFocusGained(evt, textFieldDateFrom, dateFromHintText);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hotelFocusLost(evt, textFieldDateFrom, dateFromHintText);
            }
        });
		
		textFieldDateTo = new JTextField();
		searchTextPanel.add(textFieldDateTo);
		textFieldDateTo.setColumns(15);
		textFieldDateTo.setText(dateToHintText);
		textFieldDateTo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hotelFocusGained(evt, textFieldDateTo, dateToHintText);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hotelFocusLost(evt, textFieldDateTo, dateToHintText);
            }
        });
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				submitSearch();
			}
		});
		searchTextPanel.add(btnSearch);
		
		lowerPanel = new JPanel();
		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.X_AXIS));
		masterPanel.add(lowerPanel);
		
		searchResultPanel = new SearchResultPanel();
		lowerPanel.add(searchResultPanel);
		
		filterPanel = new JPanel();
		filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
		filterPanel.setMaximumSize(new Dimension(50, Integer.MAX_VALUE));
		lowerPanel.add(filterPanel);
		
		// Make components to filter panel
		
		JLabel filterLabel = new JLabel("Filter By:");
		starLabel = new JLabel("Number of Stars");
		priceLabel = new JLabel("Price");
		singleBedLabel = new JLabel("Number of Single Beds");
		doubleBedLabel = new JLabel("Number of Double Beds");
		bathroomCheckBox = new Checkbox("En Suite Bathroom");
		
		starSlider = new JSlider();
		priceSlider = new JSlider(JSlider.HORIZONTAL, PRICE_MIN, PRICE_MAX, PRICE_INIT);
		singleBedSlider = new JSlider();
		doubleBedSlider = new JSlider();
		
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
		
		
		
		
	}
	
	private void submitSearch()
	{
		Search s = new Search(new DatabaseManager());
		
		
		// Parse date strings to Date objects
		String dateFromString = textFieldDateFrom.getText();
		String dateToString = textFieldDateTo.getText();
		
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
		
		Date dateFrom = null;
		Component frame = null;
		if(!dateFromString.equals(dateFromHintText))
		{
	    
			try
			{
				dateFrom = df.parse(dateFromString);
			}
			catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				//custom title, error icon
				JOptionPane.showMessageDialog(frame,
			    "Starting date should be in the format: DD.MM.YYYY",
			    "Date from error",
			    JOptionPane.ERROR_MESSAGE);
				
			}
		}
		
	    Date dateTo = null;
	    if(!dateToString.equals(dateToHintText))
		{
			try
			{
				dateTo = df.parse(dateToString);
			}
			catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				//custom title, error icon
				JOptionPane.showMessageDialog(frame,
			    "End date should be in the format: DD.MM.YYYY",
			    "Date to error",
			    JOptionPane.ERROR_MESSAGE);
			}
		}
		
	    String hotelName = null;
	    if (!textFieldHotel.getText().equals(hotelHintText))
	    	hotelName = textFieldHotel.getText();
	    
	    String location = null;
	    if (!textFieldLocation.getText().equals(locationHintText))
	    	location = textFieldLocation.getText();
		
		s.find(hotelName, 
				location,
				dateFrom,
				dateTo);
		
		searchResultPanel.setSearch(s);
	}

}
