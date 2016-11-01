/**	AdminPanel.java
 * 	Description	:	Administration Window for the application, and belongs to View (V) of the MVC framework.
 * 					Handles all possible administrative functions of the Parking Ticket System.
 * 					It also creates instances of the other panels (list of vehivles, Adjust fee settings, etc).
 * 					This is limited to only administrative user like civil servents.
 * 
 * @author		: 	Vinayak Hegde, 26 Oct 2016
 * @version 	:	1.0 
 */
package ticketingMachine;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AdminPanel extends JPanel
	implements ChangeListener	{

	private static final long serialVersionUID = 1L;
	private JTabbedPane theAdminTab = new JTabbedPane();
	final static String PARKEDVEHICLELIST = "Parked Vehicle List";
	final static String SETTINGS = "Settings" ;
	private AdminTabPanelList listPanel ;
	private AdminTabPanelSettings settingsPanel ;
	private JLabel statusLabel ;
	
	private TransactionBuilder transactionBuilder ;
	/**
	 *  Constructor method for Administration Panel
	 * @param transactionBuilder 
	 */
	protected AdminPanel (TransactionBuilder transactionBuilder){
		
		// link to parent - Transaction Builder class
		this.transactionBuilder = transactionBuilder ;
				
		// Set JFrame attributes 
		setPanelAttributes() ;
						
		// Create Transaction Builder Main Layout
		createTicketPanelLayout () ;
		
	}

	private void setPanelAttributes() 
	{
		// Arrange the display contents on the JPanel
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	private void createTicketPanelLayout() 
	{
		// SubTitle for the Administrative Panel
		JPanel titleAdminPanel = new JPanel(new GridLayout());
		JLabel adminPanelLabel = new JLabel("System Administration", JLabel.CENTER);
		titleAdminPanel.add(adminPanelLabel);
		add(titleAdminPanel, "North");
		
		// Create Tabular Panel for handling Search/List Vehicle information and Settings
		settingsPanel = new AdminTabPanelSettings(this) ;
		listPanel = new AdminTabPanelList(this) ;
		theAdminTab.add(PARKEDVEHICLELIST, listPanel );
		theAdminTab.add(SETTINGS, settingsPanel ) ;
		
		//	Register the change listener from Administration Panel Tab
		theAdminTab.addChangeListener(this);
		
		add(Box.createVerticalStrut(15));
		add(theAdminTab,"Center") ;
		
		/*/ set on status bar label to make it look like a panel
		JPanel statusAdminPanel = new JPanel(new GridLayout());
		statusLabel = new JLabel();
		statusLabel.setBorder(new javax.swing.border.EtchedBorder());
		statusAdminPanel.add(statusLabel, "East");
		add(statusAdminPanel) ;
		*/
				
	}

	/**
	 * Method to get new parking rate chosen by the user(civil servant)
	 * @return amount in Euro/Hour.
	 */
	protected double getNewParkingRate() {
		return settingsPanel.getParkingRateField();
	}

	@Override
	public void stateChanged(ChangeEvent changeEvent) {
		if (changeEvent.getSource()== theAdminTab ){
			setStatusLabelText ("Current panel: " + theAdminTab.getTitleAt(theAdminTab.getSelectedIndex()));
			if (theAdminTab.getTitleAt(theAdminTab.getSelectedIndex()).equals( AdminPanel.SETTINGS )){
				//	Set the display field with the value from Application settings (Parking Rate)
				settingsPanel.setParkingRateField(transactionBuilder.getParkingRate());
			}
			else if (theAdminTab.getTitleAt(theAdminTab.getSelectedIndex()).equals(AdminPanel.PARKEDVEHICLELIST)) {
			}
		}
	}

	/**
	 * This method is called from Settings panel to update the new Parking rate to Application Settings
	 * @param newRate
	 */
	protected void setParkingRate(double newRate) {
		transactionBuilder.setParkingRate(newRate) ;
	}

	/**
	 * Show the status message
	 * @param status
	 */
	public void setStatusLabelText(String status) {
		transactionBuilder.setStatusText(status);
	}

	/**
	 * Get Car List from Transaction Builder
	 * @param status
	 */
	public ArrayList<Car> getCarList() {
		return transactionBuilder.getCarList();
	}

	/**
	 * Default settings for the Administration panel
	 * 1. Update Car List
	 * 2. Set List Panel to be displayed
	 */
	protected void setDefaults() {
		listPanel.updateVehicleList();
		theAdminTab.setSelectedIndex(0);
	}
}
