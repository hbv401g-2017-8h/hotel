package is.hi.hbv402g.hotel.UI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;

public class HotelPanel extends JPanel
{
	
	private JTable table;
	private DefaultTableModel tableModel;
	private JTable table_2;
	private DefaultTableModel tableModel_2;

	/**
	 * Create the panel.
	 */
	public HotelPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel infoPanel = new JPanel();
		add(infoPanel);
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		infoPanel.add(scrollPane);
		
		tableModel = new DefaultTableModel(0, 5);
		tableModel.setColumnIdentifiers(new String[] { "Hotel", "Star Count", "Street Address", "City Area", "Country" });
		table = new JTable();
		table.setModel( tableModel);
		
		scrollPane.setViewportView(table);
		
		JPanel reviewPanel = new JPanel();
		add(reviewPanel);
		reviewPanel.setLayout(new BoxLayout(reviewPanel, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		reviewPanel.add(scrollPane_2);
		
		tableModel_2 = new DefaultTableModel(0, 4);
		tableModel_2.setColumnIdentifiers(new String[] { "Stars", "Review", "Guest", "Date of Submission" });
		table_2 = new JTable();
		table_2.setModel( tableModel_2);
		
		scrollPane_2.setViewportView(table_2);

	}

}
