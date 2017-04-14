package is.hi.hbv402g.hotel.UI;

import javax.swing.JPanel;
import javax.swing.BoxLayout;

import is.hi.hbv402g.hotel.controllers.BookingManager;
import is.hi.hbv402g.hotel.db.DatabaseManager;
import is.hi.hbv402g.hotel.models.Guest;
import is.hi.hbv402g.hotel.models.Room;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.awt.event.ActionEvent;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import javax.swing.JFormattedTextField.AbstractFormatter;

public class BookingPanel extends JPanel
{
	private Room room;
	
	private JLabel lblHeader;
	private JLabel lblDisclaimerText;
	
	private JTextField guestNameTextField;
	private JTextField guestEmailTextField;
	private JTextField guestPhoneNumberTextField;
	private JTextField numberOfAdultsTextField;
	private JTextField numberOfChildrenTextField;

	private JDatePickerImpl datePickerFrom;
	private UtilDateModel dateModelFrom;
	private JDatePanelImpl datePanelFrom;
	private JDatePickerImpl datePickerTo;
	private UtilDateModel dateModelTo;
	private JDatePanelImpl datePanelTo;
	
	private final String headerText = "Booking a room in %s";
	private final String disclaimerText = "<html>Check-in is at 12:00 to " +
			"14:50. During check-in you will need a credit card and 5,000 " +
			"ISK will be reserved. When you check out your total bill will " +
			"be charged to your card. The total bill is %,d ISK per night " +
			"plus any additional charges accrued during your stay.</html>";

	/**
	 * Create the panel.
	 */
	public BookingPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(null);
		
		lblHeader = new JLabel(headerText);
		lblHeader.setFont(new Font("Dialog", Font.BOLD, 14));
		lblHeader.setBounds(12, 12, 426, 15);
		panel.add(lblHeader);
		
		lblDisclaimerText = new JLabel(disclaimerText);
		lblDisclaimerText.setFont(new Font("Dialog", Font.PLAIN, 10));
		lblDisclaimerText.setBounds(12, 39, 426, 47);
		panel.add(lblDisclaimerText);
		
		guestNameTextField = new JTextField();
		guestNameTextField.setBounds(193, 98, 245, 19);
		panel.add(guestNameTextField);
		guestNameTextField.setColumns(10);
		
		guestEmailTextField = new JTextField();
		guestEmailTextField.setBounds(193, 129, 245, 19);
		panel.add(guestEmailTextField);
		guestEmailTextField.setColumns(10);
		
		guestPhoneNumberTextField = new JTextField();
		guestPhoneNumberTextField.setBounds(193, 160, 245, 19);
		panel.add(guestPhoneNumberTextField);
		guestPhoneNumberTextField.setColumns(10);
		
		numberOfAdultsTextField = new JTextField();
		numberOfAdultsTextField.setBounds(193, 191, 245, 19);
		panel.add(numberOfAdultsTextField);
		numberOfAdultsTextField.setColumns(10);
		
		numberOfChildrenTextField = new JTextField();
		numberOfChildrenTextField.setBounds(193, 222, 245, 19);
		panel.add(numberOfChildrenTextField);
		numberOfChildrenTextField.setColumns(10);

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		dateModelFrom = new UtilDateModel();
		datePanelFrom = new JDatePanelImpl(dateModelFrom, p);
		datePickerFrom = new JDatePickerImpl(datePanelFrom, new DateLabelFormatter());
		datePickerFrom.setBounds(193, 253, 245, 25);
		panel.add(datePickerFrom);
		
		dateModelTo = new UtilDateModel();
		datePanelTo = new JDatePanelImpl(dateModelTo, p);
		datePickerTo = new JDatePickerImpl(datePanelTo, new DateLabelFormatter());
		datePickerTo.setBounds(193, 290, 245, 25);
		panel.add(datePickerTo);
		
		JLabel lblGuestName = new JLabel("Guest name");
		lblGuestName.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblGuestName.setBounds(12, 100, 102, 15);
		panel.add(lblGuestName);
		
		JLabel lblGuestEmail = new JLabel("Guest email");
		lblGuestEmail.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblGuestEmail.setBounds(12, 131, 163, 15);
		panel.add(lblGuestEmail);
		
		JLabel lblGuestPhoneNumber = new JLabel("Guest phone number");
		lblGuestPhoneNumber.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblGuestPhoneNumber.setBounds(12, 162, 163, 15);
		panel.add(lblGuestPhoneNumber);
		
		JLabel lblNumberOfAdults = new JLabel("Number of adults");
		lblNumberOfAdults.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNumberOfAdults.setBounds(12, 193, 163, 15);
		panel.add(lblNumberOfAdults);
		
		JLabel lblNumberOfChildren = new JLabel("Number of children");
		lblNumberOfChildren.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNumberOfChildren.setBounds(12, 224, 163, 15);
		panel.add(lblNumberOfChildren);
		
		JLabel lblBookingDateFrom = new JLabel("Booking date from");
		lblBookingDateFrom.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblBookingDateFrom.setBounds(12, 257, 163, 15);
		panel.add(lblBookingDateFrom);
		
		JLabel lblBookingDateTo = new JLabel("Booking date to");
		lblBookingDateTo.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblBookingDateTo.setBounds(12, 294, 163, 15);
		panel.add(lblBookingDateTo);
		
		JButton btnBook = new JButton("Complete booking");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				completeBooking();
			}
		});
		btnBook.setBounds(263, 327, 175, 25);
		panel.add(btnBook);
		
		JButton btnBack = new JButton("Back to search");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToSearch();
			}
		});
		btnBack.setBounds(12, 327, 175, 25);
		panel.add(btnBack);
		
		Double width = btnBook.getBounds().x + btnBook.getBounds().getWidth() + 12;
		Double height = btnBook.getBounds().y + btnBook.getBounds().getHeight() + 12;
		panel.setMaximumSize(new Dimension(width.intValue(), height.intValue() * 2));
	}
	
	public void newBooking(Room r)
	{
		this.room = r;
		
		lblHeader.setText(String.format(headerText, r.getHotel().getName()));
		lblDisclaimerText.setText(String.format(disclaimerText, r.getCostPerNight()));
	}
	
	private void backToSearch()
	{
		MainFrame mf = Utilities.findParent(this, MainFrame.class);
		mf.showSearch();
	}
	
	private void completeBooking()
	{
		Guest g = new Guest();
		g.setName(guestNameTextField.getText());
		g.setEmail(guestEmailTextField.getText());
		g.setPhoneNumber(guestPhoneNumberTextField.getText());
		g.setNumberOfAdults(Integer.valueOf(numberOfAdultsTextField.getText()));
		g.setNumberOfChildren(Integer.valueOf(numberOfChildrenTextField.getText()));
		
		Date dateFrom = dateModelFrom.getValue();
		Date dateTo = dateModelTo.getValue();
		
		DatabaseManager dm = new DatabaseManager();
		BookingManager bm = new BookingManager(dm);
		
		bm.book(g, room, null, dateFrom, dateTo);
	}
}
