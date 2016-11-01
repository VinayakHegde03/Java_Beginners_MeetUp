/**  Car.java
 *  
 *  Description	: This class belongs to model part of the MVC which holds details about a Car	-
 *                data model for the vehicle
 *  @author 	: Vinayak Hegde, October 2016
 *  @version 	:	1.0 
 */
package ticketingMachine;

import java.time.LocalDateTime;

public class Car {

	private String liecencePlate ;
	private LocalDateTime startTime ;
	private LocalDateTime endTime ;
	private double paidAmount ;

	
	// Constructor for the Car
	protected Car() {
		// Do nothing.
	}
	
	/**
	 *  Constructor for the Copy Car
	 */
		protected Car( Car car) {
			this.liecencePlate = car.getLiecencePlate() ;
			this.paidAmount = car.getPaidAmount() ;
			this.startTime = car.getStartTime() ;
			this.endTime = car.getEndTime() ;
		}
	
	// Getter and Setter methods
	protected void setLiecencePlate (String liecencePlate){
		this.liecencePlate = liecencePlate ;
	}
	protected String getLiecencePlate (){
		return liecencePlate ;
	}
	protected LocalDateTime getStartTime (){
			return startTime ;
	}
	protected void setStartTime (){
		startTime = LocalDateTime.now() ;
}
	protected LocalDateTime getEndTime (){
			return endTime ;
	}
	protected double getPaidAmount(){
			return paidAmount ;
	}
	protected void setEndTime(LocalDateTime endTime){
			this.endTime = endTime ;
	}
	protected void setPaidAmount(double amount){
			this.paidAmount = amount ;
	}
}
