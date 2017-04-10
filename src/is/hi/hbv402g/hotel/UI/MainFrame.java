package is.hi.hbv402g.hotel.UI;

import java.awt.BorderLayout;
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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class MainFrame extends JFrame
{

	private JPanel contentPane;
	private JTextField textFieldHotel;
	private JTextField textFieldArea;
	private JTextField textFieldDateFrom;
	private JTextField textFieldDateTo;
	private SearchResultPanel searchResultPanel;
	
	

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
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1500, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
		
		// Define search text fields with input hint text.
		textFieldHotel = new JTextField();
		panel.add(textFieldHotel);
		textFieldHotel.setColumns(20);
		textFieldHotel.setText("Hotel");
		textFieldHotel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hotelFocusGained(evt, textFieldHotel, "Hotel");
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hotelFocusLost(evt, textFieldHotel, "Hotel");
            }
        });
		
		textFieldArea = new JTextField();
		panel.add(textFieldArea);
		textFieldArea.setColumns(20);
		textFieldArea.setText("Location");
		textFieldArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hotelFocusGained(evt, textFieldArea, "Location");
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hotelFocusLost(evt, textFieldArea, "Location");
            }
        });
		
		textFieldDateFrom = new JTextField();
		panel.add(textFieldDateFrom);
		textFieldDateFrom.setColumns(15);
		textFieldDateFrom.setText("From: DD/MM/YYYY");
		textFieldDateFrom.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hotelFocusGained(evt, textFieldDateFrom, "From: DD/MM/YYYY");
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hotelFocusLost(evt, textFieldDateFrom, "From: DD/MM/YYYY");
            }
        });
		
		textFieldDateTo = new JTextField();
		panel.add(textFieldDateTo);
		textFieldDateTo.setColumns(15);
		textFieldDateTo.setText("To: DD/MM/YYYY");
		textFieldDateTo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hotelFocusGained(evt, textFieldDateTo, "To: DD/MM/YYYY");
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hotelFocusLost(evt, textFieldDateTo, "To: DD/MM/YYYY");
            }
        });
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				submitSearch();
			}
		});
		panel.add(btnSearch);
		
		searchResultPanel = new SearchResultPanel();
		contentPane.add(searchResultPanel);
		
	}
	
	private void submitSearch()
	{
		Search s = new Search(new DatabaseManager());
		
		s.find(textFieldHotel.getText(), 
				textFieldArea.getText(),
				null,
				null);
		
		searchResultPanel.setSearch(s);
	}

}
