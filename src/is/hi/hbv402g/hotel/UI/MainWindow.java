package is.hi.hbv402g.hotel.UI;

import java.awt.EventQueue;

public class MainWindow
{

	private MainFrame frame;

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
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
					window.frame.requestFocusInWindow();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new MainFrame();
		frame.setBounds(100, 100, 1200, 700);
		frame.setDefaultCloseOperation(MainFrame.EXIT_ON_CLOSE);
	}

}
