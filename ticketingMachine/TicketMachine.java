/**  AdminTabPanelSettings.java
 *  
 *  Description	: This class creates a panel to display parking fees	-
 *                User can edit or save the parking fees
 *  @author 	: Vinayak Hegde, October 2016
 *  @version 	:	1.0 
 */
package ticketingMachine;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

public class TicketMachine {
	
	private ArrayList<Car> list = new ArrayList<Car>();
	private  double parkingRate = 0.025  ; 						// Rate at which parking fees are charged Euro/Hrs
	/**
	 * TicketMachine instance constructor
	 */
	public TicketMachine (){
		// Transaction Builder to handle user Interfaces (V)
    	new TransactionBuilder(this) ;
	}
	
	/**
	 * Finish a transaction by adding the details into ArrayList
	 * @param curTran
	 */
	protected void finishTransaction(Car curTran){
		list.add(curTran) ;
	}
	
	/**
	 * Main method to invoke Parking Ticket Application in a separate thread
	 * @param Args - no arguments
	 */
    public static void main (String[] Args){
    	
    	//Schedule a job for the event-dispatching thread:
    	SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	new TicketMachine() ;
            }
        });
    }

	protected ArrayList<Car> getList() {
		return list;
	}

	public double getParkingRate() {
		return parkingRate;
	}

	public void setParkingRate(double parkingRate) {
		this.parkingRate = parkingRate;
	}
}
