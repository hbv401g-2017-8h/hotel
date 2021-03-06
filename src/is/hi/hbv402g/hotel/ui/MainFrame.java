package is.hi.hbv402g.hotel.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import is.hi.hbv402g.hotel.controllers.Search;
import is.hi.hbv402g.hotel.db.DatabaseManager;
import is.hi.hbv402g.hotel.models.Room;

@SuppressWarnings("serial")
public class MainFrame extends JFrame
{
	static final String hotelHintText = "Hotel";
	static final String locationHintText = "Location";

	private JPanel masterPanel;
	private JPanel lowerPanel;
	private JTextField textFieldHotel;
	private JTextField textFieldLocation;

	private JDatePickerImpl datePickerFrom;
	private UtilDateModel dateModelFrom;
	private JDatePanelImpl datePanelFrom;
	private JDatePickerImpl datePickerTo;
	private UtilDateModel dateModelTo;
	private JDatePanelImpl datePanelTo;

	private SearchResultPanel searchResultPanel;
	private BookingPanel bookingPanel;
	private RoomPanel roomPanel;

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
		searchTextPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));

		// Define search text fields with input hint text.
		textFieldHotel = new JTextField();
		textFieldHotel.setFont(new Font("Dialog", Font.PLAIN, 12));
		textFieldHotel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				submitSearch();
			}
		});
		searchTextPanel.add(textFieldHotel);
		textFieldHotel.setColumns(20);
		textFieldHotel.setText(hotelHintText);
		textFieldHotel.addFocusListener(new java.awt.event.FocusAdapter()
		{
			public void focusGained(java.awt.event.FocusEvent evt)
			{
				hotelFocusGained(evt, textFieldHotel, hotelHintText);
			}

			public void focusLost(java.awt.event.FocusEvent evt)
			{
				hotelFocusLost(evt, textFieldHotel, hotelHintText);
			}
		});

		textFieldLocation = new JTextField();
		textFieldLocation.setFont(new Font("Dialog", Font.PLAIN, 12));
		textFieldLocation.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				submitSearch();
			}
		});
		searchTextPanel.add(textFieldLocation);
		textFieldLocation.setColumns(20);
		textFieldLocation.setText(locationHintText);
		textFieldLocation.addFocusListener(new java.awt.event.FocusAdapter()
		{
			public void focusGained(java.awt.event.FocusEvent evt)
			{
				hotelFocusGained(evt, textFieldLocation, locationHintText);
			}

			public void focusLost(java.awt.event.FocusEvent evt)
			{
				hotelFocusLost(evt, textFieldLocation, locationHintText);
			}
		});

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		dateModelFrom = new UtilDateModel();
		datePanelFrom = new JDatePanelImpl(dateModelFrom, p);
		datePickerFrom = new JDatePickerImpl(datePanelFrom,
				new DateLabelFormatter());
		// datePickerFrom.setBounds(193, 253, 245, 25);
		searchTextPanel.add(datePickerFrom);

		dateModelTo = new UtilDateModel();
		datePanelTo = new JDatePanelImpl(dateModelTo, p);
		datePickerTo = new JDatePickerImpl(datePanelTo,
				new DateLabelFormatter());
		searchTextPanel.add(datePickerTo);
		
		MainFrame mf = this;
		datePickerFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date dateFrom = dateModelFrom.getValue();
				Calendar today = Calendar.getInstance();
				today.set(Calendar.HOUR_OF_DAY, 0);
				if (dateFrom.compareTo(today.getTime()) < 0)
				{
					JOptionPane.showMessageDialog(mf,
							"Date from has to be today's date or after",
							"Date from error", JOptionPane.ERROR_MESSAGE);
					dateModelFrom.setValue(null);
				}
			}
		});
		datePickerTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date dateTo = dateModelTo.getValue();
				Date dateFrom = dateModelFrom.getValue();
				if (dateTo.before(dateFrom))
				{
					JOptionPane.showMessageDialog(mf,
							"Date to has to be after date from",
							"Date to error", JOptionPane.ERROR_MESSAGE);
					dateModelTo.setValue(null);
				}
			}
		});

		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnSearch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				submitSearch();
			}
		});
		searchTextPanel.add(btnSearch);

		lowerPanel = new JPanel();
		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.X_AXIS));
		masterPanel.add(lowerPanel);

		searchResultPanel = new SearchResultPanel();
		bookingPanel = new BookingPanel();
		roomPanel = new RoomPanel();

		lowerPanel.add(bookingPanel);
		lowerPanel.add(searchResultPanel);
		lowerPanel.add(roomPanel);

		searchResultPanel.setVisible(false);
		bookingPanel.setVisible(false);
		roomPanel.setVisible(false);
	}

	// Lætur texta hverfa í leitarglugga
	private void hotelFocusGained(java.awt.event.FocusEvent evt, JTextField tf,
			String text)
	{
		if (tf.getText().equals(text))
		{
			tf.setText("");
		}
	}

	// Lætur texta birtast í leitarglugga
	private void hotelFocusLost(java.awt.event.FocusEvent evt, JTextField tf,
			String text)
	{
		if (tf.getText().equals(""))
		{
			tf.setText(text);
		}
	}

	private void submitSearch()
	{
		showSearch();

		Search s = new Search(new DatabaseManager());

		Date dateFrom = dateModelFrom.getValue();
		Date dateTo = dateModelTo.getValue();

		String hotelName = null;
		if (!textFieldHotel.getText().equals(hotelHintText))
			hotelName = textFieldHotel.getText();

		String location = null;
		if (!textFieldLocation.getText().equals(locationHintText))
			location = textFieldLocation.getText();

		s.find(hotelName, location, dateFrom, dateTo);

		searchResultPanel.setSearch(s);
	}

	public void showSearch()
	{
		// Show only search result panel
		bookingPanel.setVisible(false);
		roomPanel.setVisible(false);
		searchResultPanel.setVisible(true);
	}

	public void openBooking(Room r)
	{
		// Show only booking panel
		roomPanel.setVisible(false);
		searchResultPanel.setVisible(false);
		bookingPanel.setVisible(true);

		bookingPanel.newBooking(r);
	}

	public void showRoom(Room r)
	{
		// Show only room panel
		searchResultPanel.setVisible(false);
		bookingPanel.setVisible(false);
		roomPanel.setVisible(true);

		roomPanel.setRoom(r);
	}
}
