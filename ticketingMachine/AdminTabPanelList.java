/**  AdminTabPanelList.java
 *  
 *  Description	: this class creates a panel to display all the vehicle details in a columnar format	-
 *               	the information is displayed as license plate number and end time.
 *					vehicle with the lapsed end time, are show in red font to indicate a warning message.
 *  @author 	: Vinayak Hegde, October 2016
 *  @version 	:	1.0 
 */

package ticketingMachine;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class AdminTabPanelList extends JPanel 
	implements ActionListener {
	
	protected static final String SEARCH_BUTTON_CMD = "searchLiecenceNumber";
	private JScrollPane jScrollPane1;
	private JTable jTable1;
	private JButton searchButton = new JButton("Search");
    private JTextField licencePlateField ;
	private ArrayList<Car> carList = new ArrayList<Car>();
	private TableModel carListModel ;
	private AdminPanel adminPanel ;
	/**
	 *  Constructor method for Administration List Panel to show all the vehicles along with search option
	 */
    public AdminTabPanelList(AdminPanel adminPanel){ 
    	
    	// link to parent - Transaction Builder class
    	this.adminPanel = adminPanel ;
    	
    	// Prepare Data Model for the Car List
    	carListModel = new VehicleListDataModel(this.adminPanel.getCarList()) ;
    	
    	// Set JFrame attributes 
    	setPanelAttributes() ;
    							
    	// Create Transaction Builder Main Layout
    	createTicketPanelLayout () ;
    } 
    
   private void createTicketPanelLayout() {
	   JPanel buttonPanel = new JPanel(new GridBagLayout());
		licencePlateField = new JTextField(10) ;
		searchButton.setActionCommand(SEARCH_BUTTON_CMD);
		buttonPanel.add(licencePlateField) ;
		buttonPanel.add(searchButton);
				
		add(buttonPanel, "North");
		jScrollPane1 = new JScrollPane();
		jTable1 = new JTable();
		
		jTable1.setModel(carListModel);
		// use the addRowToJTable
		addRowToJTable();
		
		jScrollPane1.setViewportView(jTable1);
		
		//contentPane.add(list, "Center");
		add(jScrollPane1) ;
	}

   private void setPanelAttributes() {
	   setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
   /**
    * Method to add rows to the list table 
    */
    private void addRowToJTable() {

    	/*for(int i = 0; i < carList.size(); i++)
        {
            rowData[0] = carList.get(i).getLiecencePlate();
            rowData[1] = carList.get(i).getEndTime();
            carListModel.addRow(rowData);
        }*/
	}

    /**
     * Method to add rows to the list table and keeps it updated 
     */
    protected void updateVehicleList()
    {
    	((AbstractTableModel) carListModel).fireTableDataChanged() ;
    } 
    
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
