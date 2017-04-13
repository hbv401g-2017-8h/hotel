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
		add(scrollPane);
		
		tableModel = new DefaultTableModel(0, 5);
		tableModel.setColumnIdentifiers(new String[] { "Hotel", "Star Count", "Street Address", "City Area", "Country" });
		table = new JTable();
		table.setModel( tableModel);
		
		scrollPane.setViewportView(table);
		
		JPanel reviewPanel = new JPanel();
		add(reviewPanel);
		reviewPanel.setLayout(new BoxLayout(reviewPanel, BoxLayout.Y_AXIS));

	}

}
