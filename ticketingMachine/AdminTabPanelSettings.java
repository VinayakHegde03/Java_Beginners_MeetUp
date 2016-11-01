/**  AdminTabPanelSettings.java
 *  
 *  Description	: This class creates a panel to display parking fees	-
 *                User can edit or save the parking fees
 *  @author 	: Vinayak Hegde, October 2016
 *  @version 	:	1.0 
 */

package ticketingMachine;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.NumberFormatter;

public class AdminTabPanelSettings extends JPanel 
	implements ActionListener	{
	
	protected final static String EDIT_BUTTON_CMD = "editRate";	//
	private JFormattedTextField parkingRateField ;
	private static final NumberFormatter DISPLAY_NUMBER_FORMAT = 
			new NumberFormatter(new DecimalFormat("€ #,###.00"));
	private JButton editButton = new JButton("Edit/Save");
	
	private AdminPanel adminPanel ;
    // constructor ShowTicketPanel ()
    public AdminTabPanelSettings(AdminPanel adminPanel){ // TransactionBuilder transactionBuild) {
    			
    	// link to parent - Transaction Builder class
		this.adminPanel = adminPanel ;
				
		// Set JFrame attributes 
		setPanelAttributes() ;
						
		// Create Transaction Builder Main Layout
		createTicketPanelLayout () ;
    }
   
    private void createTicketPanelLayout() 
    {
		Container contentPane = new Container();
		contentPane.setLayout(new GridBagLayout()) ;
		GridBagConstraints gBC = new GridBagConstraints();
		gBC.fill = GridBagConstraints.BOTH ;
		Insets inset = new Insets (2, 10, 2, 10) ;
		gBC.insets = inset ;
		
		JLabel licencePlateLabel = new JLabel ("Parking Rate (Euro/Hrs)") ;
		gBC.gridx = 0 ;
		gBC.gridy = 0 ;
		gBC.gridwidth = 1 ;
		gBC.ipadx = 25 ;
		gBC.anchor = GridBagConstraints.WEST;
		contentPane.add(licencePlateLabel, gBC) ;
		parkingRateField = new JFormattedTextField(DISPLAY_NUMBER_FORMAT) ;
		parkingRateField.setColumns(6);
		gBC.gridx = 1 ;
		gBC.gridy = 0 ;
		gBC.gridwidth = 4 ;
		gBC.ipadx = 5 ;
		//gBC.anchor = GridBagConstraints.RELATIVE;
		contentPane.add(parkingRateField, gBC) ;
		add(contentPane) ;
		add(Box.createVerticalStrut(10));
		
		// "Edit/Save" button for the Settings screen
		JPanel buttonPanel = new JPanel();
		editButton.setActionCommand(EDIT_BUTTON_CMD);
		buttonPanel.add(editButton, "Center");
		add(buttonPanel, "South");

		editButton.addActionListener( this );
	}

	private void setPanelAttributes() {
    	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

    protected double getParkingRateField()	{
    	return (double) parkingRateField.getValue();
    }

	protected void setParkingRateField(double parkingRate) {
		parkingRateField.setValue(parkingRate * 60);
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		if(action.getActionCommand().equals(EDIT_BUTTON_CMD)){
			adminPanel.setParkingRate((getParkingRateField()/60)) ; 
			adminPanel.setStatusLabelText("New Parking Rate is updated into the system.");
		}
	}
}
