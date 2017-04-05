package is.hi.hbv402g.hotel.UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame
{

	private JPanel contentPane;
	private JTextField textFieldHotel;
	private JTextField textFieldArea;
	private JTextField textFieldDateFrom;
	private JTextField textFieldDateTo;
	

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
		setBounds(100, 100, 865, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		// Define search text fields with input hint text.
		textFieldHotel = new JTextField();
		panel.add(textFieldHotel);
		textFieldHotel.setColumns(15);
		textFieldHotel.setText("Hotel Name");
		//textFieldHotel.addFocusListener(new java.awt.event.FocusAdapter() {
        //    public void focusGained(java.awt.event.FocusEvent evt) {
                //leitargluggiFocusGained(evt);
        //    }
        //    public void focusLost(java.awt.event.FocusEvent evt) {
                //leitargluggiFocusLost(evt);
        //    }
        //});
		
		textFieldArea = new JTextField();
		panel.add(textFieldArea);
		textFieldArea.setColumns(15);
		
		textFieldDateFrom = new JTextField();
		panel.add(textFieldDateFrom);
		textFieldDateFrom.setColumns(10);
		
		textFieldDateTo = new JTextField();
		panel.add(textFieldDateTo);
		textFieldDateTo.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel.add(btnSearch);
	}

}
