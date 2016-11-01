package ticketingMachine;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.table.AbstractTableModel;

public class VehicleListDataModel extends AbstractTableModel {

	private static final String[] CAR_LIST_HEADING = {"Licence Number", "End Time" } ;
	private static final int COLUMN_LICENCE_NBR = 0 ;
	private static final int COLUMN_END_TIME = 1 ;
	private ArrayList<Car> carList ;
	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss");
	
	public VehicleListDataModel (ArrayList<Car> carList) {
		this.carList = carList ;
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false ;
	}

	@Override
	public int getColumnCount() {
		return CAR_LIST_HEADING.length ;
	}

	@Override
	public int getRowCount() {
		return carList.size();
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (carList.isEmpty()) {
			return Object.class;
		}
		return getValueAt(0, columnIndex).getClass();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return CAR_LIST_HEADING[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Car car = carList.get(rowIndex);
		Object returnValue = null;
		
		switch (columnIndex) {
		case COLUMN_LICENCE_NBR:
			returnValue = car.getLiecencePlate()	;
			break;
		case COLUMN_END_TIME:
			returnValue = dateFormat.format(car.getEndTime()) ;
			break;
		default:
			throw new IllegalArgumentException("Invalid column index");
		}
		return returnValue;
	}
}
