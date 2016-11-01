/**  TransactionBuilder.java
 *  
 *  Description	: 	Main JFrame for the application, and belongs to View (V) of the MVC framework.
 *               	Handles all possible transactions within Parking Ticket System.
 *                	It also creates instances of the other panels (issue ticket, administration, etc) and incorporates them into main frame.
 * 				  	This is hierarchically, the highest class for the view (V) layer.
 *  @author 	: 	Vinayak Hegde, October 2016
 *  @version 	:	1.0 
 */
package ticketingMachine;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TransactionBuilder extends JFrame 
	implements ChangeListener	{

	private static final long serialVersionUID = 1L;
	private JTabbedPane theMainTab = new JTabbedPane(JTabbedPane.LEFT);
	private JLabel statusLabel ;
	
	final static String PARKING_TICKET = "Parking Ticket";
	final static String ADMINISTRATION = "Administration";
	private AdminPanel adminPanel ;
	private TicketPanel ticketPanel ;
	private TicketMachine ticketMachine ;
	
	/** 
	 * 	Constructor method for new transaction builder (The Main Window)
	 */
	protected TransactionBuilder (TicketMachine ticketMachine){
		
		// Link to the main Ticket Machine class
		this.ticketMachine = ticketMachine ;
		
		// create instances of all the Panels to incorporate into Main Frame
		adminPanel = new AdminPanel(this) ;
		ticketPanel = new TicketPanel(this) ;
		
		// Create Transaction Builder Main Layout
		createMainLayout () ;	
		
		// Set JFrame attributes 
		setFrameAttributes() ;
	}

	/**
     * This method to create Main Layout for Transaction Builder
     */
	private void createMainLayout() 
	{
		JPanel titlePanel = new JPanel(new GridLayout());
		JLabel appLabel = new JLabel("Parking Ticket Machine", JLabel.CENTER);
		
		String currentFont = appLabel.getFont().getName();
		appLabel.setFont(new Font(currentFont, Font.BOLD, 26));
		titlePanel.add(appLabel);
		
		Container mainContainer = getContentPane() ;
		mainContainer.setLayout(new BorderLayout());
		mainContainer.add(titlePanel, "North");
		
		theMainTab.add(PARKING_TICKET, ticketPanel);
		theMainTab.add(ADMINISTRATION, adminPanel);
		theMainTab.setSelectedIndex(0);
		mainContainer.add(theMainTab,"Center") ;
		
		// set border on status bar label to make it look like a panel
		statusLabel = new JLabel();
		statusLabel.setBorder(new javax.swing.border.EtchedBorder());
		setStatusText("Current panel: " + theMainTab.getTitleAt(theMainTab.getSelectedIndex()));
		mainContainer.add(statusLabel, "South");
		
		// register the change listener of the Main Tab
		theMainTab.addChangeListener(this);
	}

	/**
	 * The method to set all the attributes of the Transaction Builder (Main) JFrame or window.
	 * 
	 */
	private void setFrameAttributes() {
		setDefaultLookAndFeelDecorated(true);
		setSize(620, 480);
		//pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 *	Show error Message in a separate Message Dialog Box.
	 *	@param MessageDesription
	 *	@param MessageTitle  
	 */
	protected void showErrorMessage(String msgDescription, String msgTitle ) 
	{
		JOptionPane.showMessageDialog(	this,
										msgDescription,
										msgTitle, 
										JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * The changes from the Main Tab is handled here.
	 * @param ce
	 */
	@Override
	public void stateChanged(ChangeEvent ce) {
		if (ce.getSource() == theMainTab){
			setStatusText("Current panel: " + theMainTab.getTitleAt(theMainTab.getSelectedIndex()));

			if (theMainTab.getTitleAt(theMainTab.getSelectedIndex()).equals(ADMINISTRATION )){
				adminPanel.setDefaults() ;
			}
			else if (theMainTab.getTitleAt(theMainTab.getSelectedIndex()).equals(PARKING_TICKET )){
				ticketPanel.setDefaults() ;
			}
		}
	}
	
	/**
	 * Finish Transaction by calling finishTransaction method of ticketMachine.
	 * Also update the Car List on Admin Panel
	 * @param curTran
	 */
	protected void finishTransaction(Car curTran) {
		ticketMachine.finishTransaction(curTran);
	}

	/**
	 * This method sets the Parking rate of the Application settings.
	 * @param newRate
	 */
	protected void setParkingRate(double newRate) {
		ticketMachine.setParkingRate(newRate);
	}
	
	/**
	 * This method returns Parking rate from the Application settings.
	 * @return Parking Rate from the Application Settings
	 */
	protected double getParkingRate () {
		return ticketMachine.getParkingRate();
	}

	protected void setStatusText(String status) {
		statusLabel.setText(status) ;
	}

	protected ArrayList<Car> getCarList() {
		return ticketMachine.getList() ;
	}
}
