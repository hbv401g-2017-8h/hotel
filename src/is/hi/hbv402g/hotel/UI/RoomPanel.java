package is.hi.hbv402g.hotel.UI;

import javax.swing.JPanel;

import is.hi.hbv402g.hotel.models.Room;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

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
	private JButton btnBack;
	private JPanel panel;
	
	/**
	 * Create the panel.
	 */
	public RoomPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		panel = new JPanel();
		add(panel);
		panel.setLayout(null);
		
		lblName = new JLabel("Hotelname");
		lblName.setBounds(12, 12, 195, 14);
		panel.add(lblName);
		lblName.setFont(new Font("Dialog", Font.BOLD, 14));
		
		lblAddress = new JLabel("Address:");
		lblAddress.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblAddress.setBounds(12, 38, 195, 14);
		panel.add(lblAddress);
		
		lblStars = new JLabel("Stars:");
		lblStars.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblStars.setBounds(12, 138, 195, 14);
		panel.add(lblStars);
		
		lblRoom = new JLabel("About the room");
		lblRoom.setBounds(217, 12, 225, 14);
		panel.add(lblRoom);
		lblRoom.setFont(new Font("Dialog", Font.BOLD, 14));
		
		lblSetAddress = new JLabel("StreetAddress");
		lblSetAddress.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSetAddress.setBounds(12, 62, 195, 14);
		panel.add(lblSetAddress);
		
		lblEnSuiteBathroom = new JLabel("En suite bathroom");
		lblEnSuiteBathroom.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblEnSuiteBathroom.setBounds(217, 62, 225, 14);
		panel.add(lblEnSuiteBathroom);
		
		lblNumberOfSingle = new JLabel("Number of single beds");
		lblNumberOfSingle.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNumberOfSingle.setBounds(217, 88, 225, 14);
		panel.add(lblNumberOfSingle);
		
		lblNumberOfDouble = new JLabel("Number of double beds");
		lblNumberOfDouble.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNumberOfDouble.setBounds(217, 113, 225, 14);
		panel.add(lblNumberOfDouble);
		
		lblCost = new JLabel("Cost:");
		lblCost.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCost.setBounds(217, 38, 225, 14);
		panel.add(lblCost);
		
		lblCity = new JLabel("City");
		lblCity.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCity.setBounds(12, 88, 195, 14);
		panel.add(lblCity);
		
		lblCountry = new JLabel("Country");
		lblCountry.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCountry.setBounds(12, 113, 195, 14);
		panel.add(lblCountry);
		
		JButton btnBookNow = new JButton("Book now");
		btnBookNow.setBounds(300, 255, 130, 23);
		panel.add(btnBookNow);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(12, 255, 130, 23);
		panel.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backToSearch();
			}
		});
		btnBookNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame mf = Utilities.findParent((Component) e.getSource(), MainFrame.class);
				mf.openBooking(r);
			}
		});
		
		Double width = btnBookNow.getBounds().x + btnBookNow.getBounds().getWidth() + 12;
		Double height = btnBookNow.getBounds().y + btnBookNow.getBounds().getHeight() + 12;
		panel.setMaximumSize(new Dimension(width.intValue(), height.intValue() * 2));
	}
	
	public void setRoom(Room r)
	{
		this.r = r;
		
		lblName.setText(r.getHotel().getName());
		lblSetAddress.setText(r.getHotel().getStreetAddress());
		lblCity.setText(r.getHotel().getPostalCode() + " " + r.getHotel().getCity());
		lblCountry.setText(r.getHotel().getCountry());
		lblStars.setText(String.format("Stars: %d", r.getHotel().getStarCount()));
		lblCost.setText(String.format("Cost per night: %d ISK", r.getCostPerNight()));
		lblEnSuiteBathroom.setText(r.getEnSuiteBathroom() ? "Bathroom: Yes":"Bathroom: No");
		lblNumberOfSingle.setText(String.format("Number of single beds: %d", r.getNumberOfSingleBeds()));
		lblNumberOfDouble.setText(String.format("Number of double beds: %d", r.getNumberOfDoubleBeds()));
	}
	
	private void backToSearch()
	{
		MainFrame mf = Utilities.findParent(this, MainFrame.class);
		mf.showSearch();
	}
}






