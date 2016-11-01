/*  ShowTicketPanel .java
*  
*  Description: this class creates a panel to display last issued  parking ticket	-
*               the information is displayed for 5 seconds and then returned back to TransactionBuilder
*
*  Author: Vinayak H, October 2016
*/

package ticketingMachine;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.text.NumberFormatter;

public class ShowTicketPanel extends JPanel 
{
    private JTextField licencePlateField ;
	private JFormattedTextField amountField ;
	private JTextField startTimeField ;
	private JTextField endTimeField ;
	NumberFormatter displayFormatter = 
		       new NumberFormatter(new DecimalFormat("€ #,###.00"));
	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    // constructor ShowTicketPanel ()
    public ShowTicketPanel()	{ 
    	
    	JLabel headingLabel = new JLabel("Ticket Issued Successfully.!");
		JPanel subtitlePanel = new JPanel();
		subtitlePanel.add(headingLabel, "Center");
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(subtitlePanel);
		add(Box.createVerticalStrut(15));
		
		Container contentPane = new Container();
		contentPane.setLayout(new GridBagLayout()) ;
		GridBagConstraints gBC = new GridBagConstraints();
		gBC.fill = GridBagConstraints.BOTH ;
		Insets inset = new Insets (2, 10, 2, 10) ;
		gBC.insets = inset ;
		
		JLabel licencePlateLabel = new JLabel ("Vehicle Number") ;
		gBC.gridx = 0 ;
		gBC.gridy = 0 ;
		gBC.gridwidth = 1 ;
		gBC.ipadx = 25 ;
		gBC.anchor = GridBagConstraints.WEST;
		contentPane.add(licencePlateLabel, gBC) ;
		licencePlateField = new JTextField(10) ;
		licencePlateField.setEditable(false);
		gBC.gridx = 1 ;
		gBC.gridy = 0 ;
		gBC.gridwidth = 4 ;
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

		displayFormatter.setValueClass(Double.class);
		amountField = new JFormattedTextField(displayFormatter) ;
		amountField.setEditable(false) ;
		amountField.setColumns(6) ;
		gBC.gridx = 2 ;
		gBC.gridy = 1 ;
		gBC.gridwidth = 2 ;
		gBC.ipadx = 15 ;
		//gBC.anchor = GridBagConstraints.RELATIVE;
		contentPane.add(amountField, gBC) ;
		
		JLabel startTimeLabel = new JLabel ("Start Time") ;
		gBC.gridx = 0 ;
		gBC.gridy = 2 ;
		gBC.gridwidth = 1 ;
		gBC.ipadx = 5 ;
		gBC.anchor = GridBagConstraints.WEST;
		contentPane.add(startTimeLabel, gBC) ;
		startTimeField = new JTextField () ;
		startTimeField.setEditable(false) ;
		gBC.gridx = 1 ;
		gBC.gridy = 2 ;
		gBC.gridwidth = 4 ;
		gBC.ipadx = 25 ;
		//gBC.anchor = GridBagConstraints.RELATIVE;
		contentPane.add(startTimeField, gBC) ;
		
		JLabel endTimeLabel = new JLabel ("End Time") ;
		gBC.gridx = 0 ;
		gBC.gridy = 3 ;
		gBC.gridwidth = 1 ;
		gBC.ipadx = 5 ;
		gBC.anchor = GridBagConstraints.WEST;
		contentPane.add(endTimeLabel, gBC) ;
		endTimeField = new JTextField () ;
		endTimeField.setEditable(false) ;
		gBC.gridx = 1 ;
		gBC.gridy = 3 ;
		gBC.gridwidth = 4 ;
		gBC.ipadx = 25 ;
		//gBC.anchor = GridBagConstraints.RELATIVE;
		contentPane.add(endTimeField, gBC) ;
		
		add(contentPane) ;
		add(Box.createVerticalStrut(10));
		
    } // end constructor ShowTicketPanel ()
   
    /**
     * Clear display fields method
     */
    protected void clearDisplayFields()
    {
    	licencePlateField.setText( "" );
    	amountField.setValue(0);
    	endTimeField.setText("") ;
    	startTimeField.setText("");
    }
   
    /**
     * Set all the display fields corresponding to current transaction values
     * @param curTran
     */
    protected void setDisplayFields(Car curTran)
    {
    	licencePlateField.setText(curTran.getLiecencePlate());
 		amountField.setValue(curTran.getPaidAmount());
    	endTimeField.setText(curTran.getEndTime().format(dateFormatter));
    	startTimeField.setText(curTran.getStartTime().format(dateFormatter));
 
    }
    
    /**
     * Set display timer for the Show Last Issued Ticket Panel
     */
    protected void setDisplayTimer()
    {
    	/**
         * The timer is set here for issued ticket display.
         */
        Timer timer = new Timer(3600, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
         	   setVisible(false);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}
