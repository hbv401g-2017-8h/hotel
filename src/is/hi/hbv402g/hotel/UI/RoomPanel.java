package is.hi.hbv402g.hotel.UI;

import javax.swing.JPanel;

import is.hi.hbv402g.hotel.models.Room;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RoomPanel extends JPanel
{
	private Room r;
	private JLabel lblName;
	private JLabel lblAddress;
	private JLabel lblStars;
	private JLabel lblRoom;
	private JLabel lblSetAddress;
	private JLabel lblEnSuiteBathroom;
	private JLabel lblNumberOfSingle;
	private JLabel lblNumberOfDouble;
	private JLabel lblCost;
	private JLabel lblCity;
	private JLabel lblCountry;
	
	/**
	 * Create the panel.
	 */
	public RoomPanel()
	{
		setLayout(null);
		
		lblName = new JLabel("Hotelname");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblName.setBounds(10, 11, 195, 14);
		add(lblName);
		
		lblAddress = new JLabel("Address:");
		lblAddress.setBounds(10, 36, 195, 14);
		add(lblAddress);
		
		lblStars = new JLabel("Stars:");
		lblStars.setBounds(10, 138, 195, 14);
		add(lblStars);
		
		lblRoom = new JLabel("About the room");
		lblRoom.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRoom.setBounds(215, 12, 225, 14);
		add(lblRoom);
		
		lblSetAddress = new JLabel("StreetAddress");
		lblSetAddress.setBounds(10, 61, 195, 14);
		add(lblSetAddress);
		
		lblEnSuiteBathroom = new JLabel("En suite bathroom");
		lblEnSuiteBathroom.setBounds(215, 61, 225, 14);
		add(lblEnSuiteBathroom);
		
		lblNumberOfSingle = new JLabel("Number of single beds");
		lblNumberOfSingle.setBounds(215, 87, 225, 14);
		add(lblNumberOfSingle);
		
		lblNumberOfDouble = new JLabel("Number of double beds");
		lblNumberOfDouble.setBounds(215, 113, 225, 14);
		add(lblNumberOfDouble);
		
		lblCost = new JLabel("Cost:");
		lblCost.setBounds(215, 36, 225, 14);
		add(lblCost);
		
		lblCity = new JLabel("City");
		lblCity.setBounds(10, 86, 195, 14);
		add(lblCity);
		
		lblCountry = new JLabel("Country");
		lblCountry.setBounds(10, 113, 195, 14);
		add(lblCountry);
		
		JButton btnBookNow = new JButton("Book now");
		btnBookNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame mf = Utilities.findParent((Component) e.getSource(), MainFrame.class);
				mf.openBooking(r);
			}
		});
		btnBookNow.setBounds(351, 266, 89, 23);
		add(btnBookNow);
	}
	
	public void setRoom(Room r)
	{
		this.r = r;
		
		lblName.setText(r.getHotel().getName());
		lblSetAddress.setText(r.getHotel().getStreetAddress());
		lblCity.setText(r.getHotel().getPostalCode() + " " + r.getHotel().getCity());
		lblCountry.setText(r.getHotel().getCountry());
		lblStars.setText(String.format("Stars: %d", r.getHotel().getStarCount()));
		lblCost.setText(String.format("Cost per night: %d isk.", r.getCostPerNight()));
		lblEnSuiteBathroom.setText(r.getEnSuiteBathroom() ? "Bathroom: Yes":"Bathroom: No");
		lblNumberOfSingle.setText(String.format("Number of single beds: %d", r.getNumberOfSingleBeds()));
		lblNumberOfDouble.setText(String.format("Number of double beds: %d", r.getNumberOfDoubleBeds()));
	}
}
