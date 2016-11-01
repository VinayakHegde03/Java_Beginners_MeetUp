/**  UserDefinedDoubleArrow.java
 *  
 *  Description	: This class creates double arrow inside the basic arrow button of Java Swing utility	-
 *                used for increase/decrease by Euro symbol in the application
 *  @author 	: Vinayak Hegde, October 2016
 *  @version 	:	1.0 
 */
package ticketingMachine;

import java.awt.Graphics;
import javax.swing.plaf.basic.BasicArrowButton;

public class UserDefinedDoubleArrow extends BasicArrowButton {
	  private static final long serialVersionUID = 1L;

	  public UserDefinedDoubleArrow(int direction) {
	   // pass on the Arrow direction
	   super(direction);
	  }

	  @Override
	  public void paintTriangle(Graphics g, int x, int y, int size,
	    int direction, boolean isEnabled) 
	  {

	   super.paintTriangle(g, x - (size / 2), y, size, direction, isEnabled);
	   super.paintTriangle(g, x + (size / 2), y, size, direction, isEnabled);
	  }
	 }
