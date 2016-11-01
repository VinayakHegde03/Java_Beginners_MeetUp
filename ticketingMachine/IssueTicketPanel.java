/**  IssueTicketPanel.java
 *  
 *  Description	: This class creates a display panel to accept vehicle number and show appropriate end time	-
 *                User can adjust the payable amount before giving the final confirmation using available buttons
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.text.NumberFormatter;

public class IssueTicketPanel extends JPanel 
	implements ActionListener	{
	
	private final static double INITIAL_AMOUNT = 3.0 ;	// Initial paid amount to calculate end time.
	private final static double ADJUST_IN_CENTS = 0.1 ;	// Initial paid amount to calculate end time.
	private final static double ADJUST_IN_EURO = 1 ;		// Initial paid amount to calculate end time.
	private final static String SAVE_BUTTON_CMD = "saveCarDetails";		//
	private final static String DECREASE_BY_EURO_CMD = "decreaseAmountInEuro";	//
	private final static String INCREASE_BY_EURO_CMD = "increaseAmountInEuro";	//
	private final static String DECREASE_BY_CENT_CMD = "decreaseAmountInCent";	//
	private final static String INCREASE_BY_CENT_CMD = "increaseAmountInCent";	//
	private JButton saveButton = new JButton("Done");
	private BasicArrowButton decreaseBy10CentsButton = new BasicArrowButton (BasicArrowButton.WEST) ;
	private BasicArrowButton increaseBy10CentsButton = new BasicArrowButton (BasicArrowButton.EAST) ;
	private UserDefinedDoubleArrow decreaseBy1EuroButton = new UserDefinedDoubleArrow (BasicArrowButton.WEST) ;
	private UserDefinedDoubleArrow increaseBy1EuroButton = new UserDefinedDoubleArrow (BasicArrowButton.EAST) ;
	private JTextField licencePlateField ;
	private JFormattedTextField amountField ;
	private static final NumberFormatter DISPLAY_NUMBER_FORMAT = 
				new NumberFormatter(new DecimalFormat("€ #,###.00"));
	private JTextField endTimeField ;
	private static final DateTimeFormatter DISPLAY_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy	HH:mm:ss");
	
	// Instantiate new (transaction) car/vehicle 
	private Car curTran = new Car () ;
	private TicketPanel ticketPanel ;
	
	// constructor ShowTicketPanel ()
    public IssueTicketPanel(TicketPanel ticketPanel) {
    	
    	this.ticketPanel = ticketPanel ;
    	    					
    	// Create Transaction Builder Main Layout
    	createTicketPanelLayout () ;    
    	
    	// start with a fresh transaction 
    	resetCurrentTransaction() ;
    }
    
	private void createTicketPanelLayout() 
    {
    	// SubTitle for the Ticket issue screen
    	JPanel subtitlePanel = new JPanel();
    	JLabel headingLabel = new JLabel("Issue Parking Ticket");
		subtitlePanel.add(headingLabel, "Center");
		// "Done" button for the Ticket issue screen
		JPanel buttonPanel = new JPanel();
		saveButton.setActionCommand(SAVE_BUTTON_CMD);
		buttonPanel.add(saveButton, "Center");
		
		Container contentPane = new Container();
		contentPane.setLayout(new GridBagLayout()) ;
		GridBagConstraints gBC = new GridBagConstraints();
		gBC.fill = GridBagConstraints.BOTH ;
		Insets inset = new Insets (3, 2, 3, 2) ;
		gBC.insets = inset ;
		
		JLabel licencePlateLabel = new JLabel ("Vehicle Number") ;
		gBC.gridx = 0 ;
		gBC.gridy = 0 ;
		gBC.gridwidth = 1 ;
		gBC.ipadx = 25 ;
		gBC.anchor = GridBagConstraints.WEST;
		contentPane.add(licencePlateLabel, gBC) ;
		licencePlateField = new JTextField(10) ;
		gBC.gridx = 1 ;
		gBC.gridy = 0 ;
		gBC.gridwidth = 5 ;
		gBC.ipadx = 5 ;
		//gBC.anchor = GridBagConstraints.RELATIVE;
		contentPane.add(licencePlateField, gBC) ;
		
		JLabel amountLabel = new JLabel ("Amount in Euros") ;
		gBC.gridx = 0 ;
		gBC.gridy = 1 ;
		gBC.gridwidth = 1 ;
		gBC.ipadx = 5 ;
		gBC.anchor = GridBagConstraints.WEST;
		contentPane.add(amountLabel, gBC) ;
		gBC.gridx = 1 ;
		gBC.gridy = 1 ;
		gBC.gridwidth = 1 ;
		gBC.ipadx = 5 ;
		decreaseBy1EuroButton.setActionCommand(DECREASE_BY_EURO_CMD);
		contentPane.add(decreaseBy1EuroButton, gBC) ;
		gBC.gridx = 2 ;
		gBC.gridy = 1 ;
		gBC.gridwidth = 1 ;
		gBC.ipadx = 5 ;
		decreaseBy10CentsButton.setActionCommand(DECREASE_BY_CENT_CMD);
		contentPane.add(decreaseBy10CentsButton, gBC) ;
		DISPLAY_NUMBER_FORMAT.setValueClass(Double.class);
		amountField = new JFormattedTextField(DISPLAY_NUMBER_FORMAT) ;
		amountField.setEditable(false) ;
		amountField.setColumns(7) ;
		gBC.gridx = 3 ;
		gBC.gridy = 1 ;
		gBC.gridwidth = 1 ;
		gBC.ipadx = 15 ;
		//gBC.anchor = GridBagConstraints.RELATIVE;
		contentPane.add(amountField, gBC) ;
		gBC.gridx = 4 ;
		gBC.gridy = 1 ;
		gBC.gridwidth = 1 ;
		gBC.ipadx = 5 ;
		increaseBy10CentsButton.setActionCommand(INCREASE_BY_CENT_CMD);
		contentPane.add(increaseBy10CentsButton, gBC) ;
		gBC.gridx = 5 ;
		gBC.gridy = 1 ;
		gBC.gridwidth = 1 ;
		gBC.ipadx = 5 ;
		increaseBy1EuroButton.setActionCommand(INCREASE_BY_EURO_CMD);
		contentPane.add(increaseBy1EuroButton, gBC) ;
		
		JLabel endTimeLabel = new JLabel ("Valid Untill") ;
		gBC.gridx = 0 ;
		gBC.gridy = 2 ;
		gBC.gridwidth = 1 ;
		gBC.ipadx = 5 ;
		gBC.anchor = GridBagConstraints.WEST;
		contentPane.add(endTimeLabel, gBC) ;
		endTimeField = new JTextField () ;
		endTimeField.setEditable(false) ;
		gBC.gridx = 1 ;
		gBC.gridy = 2 ;
		gBC.gridwidth = 5 ;
		gBC.ipadx = 25 ;
		//gBC.anchor = GridBagConstraints.RELATIVE;
		contentPane.add(endTimeField, gBC) ;
		// Arrange the display contents on the JPanel
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(subtitlePanel, "North");
		add(Box.createVerticalStrut(15));
		add(contentPane, "Center") ;
		add(Box.createVerticalStrut(10));
		add(buttonPanel, "South");
		
		saveButton.addActionListener( this );
	    decreaseBy10CentsButton.addActionListener( this );
	    increaseBy10CentsButton.addActionListener( this );
	    decreaseBy1EuroButton.addActionListener( this );
	    increaseBy1EuroButton.addActionListener( this );
	    
	}
	/**
	 * Method to set values from current transaction to display fields
	 */
	private void setIssueTicketPanelFields() 
    {
    	licencePlateField.setText(curTran.getLiecencePlate());
 		amountField.setValue(curTran.getPaidAmount());
    	endTimeField.setText(curTran.getEndTime().format(DISPLAY_DATE_FORMAT));
	}

	protected void resetCurrentTransaction() {
		curTran.setLiecencePlate("");
		curTran.setPaidAmount(INITIAL_AMOUNT);
		curTran.setStartTime();
		updateEndTime() ;
		
		// Set the display fields to default values
    	setIssueTicketPanelFields() ;
	}

	
	// getter methods for LicencePlateNumber
 	protected String getLicencePlateField (){
 		return licencePlateField.getText() ;
 	}
 	// getter method for amountField
 	protected double getAmountField (){
 		return (double) amountField.getValue();
 	}
 	// getter method for amountField
 	protected LocalDateTime getEndTimeField (){
 		return LocalDateTime.parse(endTimeField.getText());
 	}
    
	@Override
	public void actionPerformed(ActionEvent action) 
	{
		String licencePlateNbr = getLicencePlateField() ;
		// retrieve the user-entered text and send it to the model
		curTran.setLiecencePlate(licencePlateNbr);		
		if (action.getActionCommand().equals(DECREASE_BY_CENT_CMD)){
			removeMoney (ADJUST_IN_CENTS) ;
			setIssueTicketPanelFields();
		}
		else if(action.getActionCommand().equals(INCREASE_BY_CENT_CMD)){
			addMoney (ADJUST_IN_CENTS) ;
			setIssueTicketPanelFields();
		}
		if (action.getActionCommand().equals(DECREASE_BY_EURO_CMD)){
			removeMoney (ADJUST_IN_EURO) ;
			setIssueTicketPanelFields();
		}
		else if(action.getActionCommand().equals(INCREASE_BY_EURO_CMD)){
			addMoney (ADJUST_IN_EURO) ;
			setIssueTicketPanelFields();
		}
		else if (action.getActionCommand().equals(SAVE_BUTTON_CMD)){
			if (isValidLicencePlate(licencePlateNbr)) {
				finishTransaction() ;
				ticketPanel.showLastIssuedTicket(curTran);
				
				//transactionBuild.adminPanel addListEntry();
				resetCurrentTransaction() ;
			}
		}
	}
	private boolean isValidLicencePlate(String licencePlateField) {
		boolean isValid = false;
		try {
			/**
			 * retrieve the values from the text field, and validate for NNNNAA format
			 */
			if(Pattern.matches("[0-9]{4}[A-Z]{2}", licencePlateField)){
				isValid = true ;
			} else{
				showErrorMessage(
						"An error has occured due to incorrect \"Vehicle Number\" text field data.\nVehicle Number should be NNNNAA format where NNNN is digits and AA is alphabets.",
						"Licence Number Not Valid");
			}
		}
		/*
		 * NumberFormatException would usually be thrown if the text fields
		 * contain invalid data, for example a price field containing letters.
		 */
		catch (NumberFormatException exp) {
			showErrorMessage(
					"An unknown error has occured. Please ensure your fields meet the following requirements:\n"
							+ "The \"Year\" field must contain four numeric digits only\nThe \"Price\" field must contain a valid integer with no decimal places\nThe \"Km Traveled\" field must contain a number which can have a maximum of one decimal place",
					"Licence Number Not Valid");
		}
		return isValid ;
	}

	/**
	 * AddTime/addMoney method - for corresponding user action.
	 * @param amount - total Paid amount
	 */
	private void addMoney(double amount) {
		curTran.setPaidAmount((curTran.getPaidAmount() + amount));
		updateEndTime() ;
	}
		
	/**
	 * RemoveTime/removeMoney method - for corresponding user action.
	 * @param amount - total paid amount
	 */
	private void removeMoney(double amount)
	{			
		if((curTran.getPaidAmount() - amount) < ADJUST_IN_CENTS)	{
			showErrorMessage(
				"You can not reduce the amount less than or equal to zero.",
				"Amount Should Be Greater Than Zero");
		} else {
			curTran.setPaidAmount((curTran.getPaidAmount() - amount)) ;
			updateEndTime() ;
		}
	}
		
	/**
	 * Update End time according to total paid amount for the current transaction
	 */
	private void updateEndTime(){
		LocalDateTime increasedTime = curTran.getStartTime() ;
		increasedTime = increasedTime.plusMinutes(calculateTimePerCents(curTran.getPaidAmount())) ;
		curTran.setEndTime(increasedTime)	;
	}
				
	/**
     * To calculate number of minutes to increase/decrease based upon the amount.
     * @param amount in Euro
     * @return minutes proportional to amount.
     */
    private long calculateTimePerCents(double amount){
    	// Handle divide by zero error here
    	return  (long)((1/ticketPanel.getParkingRate()) * (amount)) ;
    }
    
    /**
     * Finish transaction by sending current transaction to TicketMachine.
     * The copy of the current transaction is added to ArrayList of the TicketMachine.
     */
	private void finishTransaction() {
		ticketPanel.finishTransaction(new Car(curTran)) ;
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
}
