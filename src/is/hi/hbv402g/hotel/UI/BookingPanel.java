package is.hi.hbv402g.hotel.UI;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class BookingPanel extends JPanel
{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Create the panel.
	 */
	public BookingPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(193, 12, 114, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(193, 43, 114, 19);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(193, 74, 114, 19);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(193, 105, 114, 19);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(193, 136, 114, 19);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(193, 167, 114, 19);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(193, 198, 114, 19);
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblGuestName = new JLabel("Guest name");
		lblGuestName.setBounds(12, 14, 102, 15);
		panel.add(lblGuestName);
		
		JLabel lblGuestEmail = new JLabel("Guest email");
		lblGuestEmail.setBounds(12, 45, 163, 15);
		panel.add(lblGuestEmail);
		
		JLabel lblGuest = new JLabel("Guest phone number");
		lblGuest.setBounds(12, 76, 163, 15);
		panel.add(lblGuest);
		
		JLabel lblNumberOfAdults = new JLabel("Number of adults");
		lblNumberOfAdults.setBounds(12, 107, 163, 15);
		panel.add(lblNumberOfAdults);
		
		JLabel lblNumberOfChildren = new JLabel("Number of children");
		lblNumberOfChildren.setBounds(12, 138, 163, 15);
		panel.add(lblNumberOfChildren);
		
		JLabel lblBookingDateFrom = new JLabel("Booking date from");
		lblBookingDateFrom.setBounds(12, 169, 163, 15);
		panel.add(lblBookingDateFrom);
		
		JLabel lblBookingDateTo = new JLabel("Booking date To");
		lblBookingDateTo.setBounds(12, 200, 163, 15);
		panel.add(lblBookingDateTo);
		
		JButton btnBook = new JButton("Book");
		btnBook.setBounds(193, 229, 114, 25);
		panel.add(btnBook);

	}
}
