package ticketingMachine;

import java.awt.CardLayout;
import javax.swing.JPanel;

public class TicketPanel extends JPanel {

	final static String ISSUETICKETPANEL = "Ticket Issue Panel";
	final static String SHOWTICKETPANEL = "Show Issued Ticket Panel";
	private CardLayout cardLayout ;
	private ShowTicketPanel showTicketPanel ;
	private IssueTicketPanel issueTicketPanel ;
	
	private TransactionBuilder transactionBuilder ;
	
	// Constructor method for new transaction builder
	protected TicketPanel (TransactionBuilder transactionBuilder)
	{	
		// link to parent - Transaction Builder class
		this.transactionBuilder = transactionBuilder ;
		
		// Set JFrame attributes 
		setPanelAttributes() ;
				
		// Create Transaction Builder Main Layout
		createTicketPanelLayout () ;	
	}
    
	private void createTicketPanelLayout() 
	{
		// Create instance of different panels to incorporate into card layout of TicketPanel
		showTicketPanel = new ShowTicketPanel() ;
		issueTicketPanel = new IssueTicketPanel(this) ;
		
		add(issueTicketPanel, ISSUETICKETPANEL) ;
		add(showTicketPanel, SHOWTICKETPANEL) ;
	}

	/**
	 * Set the panel attributes here
	 */
	private void setPanelAttributes() 
	{
		cardLayout = new CardLayout() ;
		setLayout(cardLayout) ;
		cardLayout.first(this);
	}
	
	/**
	 * Finish Transaction by calling finishTransaction method of TransactionBuilder.
	 * @param curTran
	 */
	protected void finishTransaction(Car curTran) {
		transactionBuilder.finishTransaction (curTran) ;
	}

	/**
	 * Show the Last issued ticket for 5 seconds via ShowTicketPanel
	 * @param curTran
	 */
	protected void showLastIssuedTicket(Car curTran) 
	{
		showTicketPanel.clearDisplayFields();
		showTicketPanel.setDisplayFields(curTran);
		showTicketPanel.setDisplayTimer();
		cardLayout.next(this);
	}

	protected double getParkingRate() {
		return transactionBuilder.getParkingRate();
	}

	/**
	 * Default settings for Ticket Panel
	 */
	protected void setDefaults() {
		cardLayout.first(this);
		issueTicketPanel.resetCurrentTransaction() ;
	}

}
