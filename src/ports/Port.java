


package ports;

import java.util.ArrayList;
import java.util.Iterator;

import containers.Container;
import interfaces.IPort;
import ships.Ship;

/**
 * Class that contains fields and methods related to the Port.
 * @author celik
 *
 */
public class Port implements IPort {
	
	/**
	 * ID of the port.
	 */
	private final int ID;
	/**
	 * X component of location.
	 */
	private final double X;
	/**
	 * Y component of location.
	 */
	private final double Y;
	/**
	 * ArrayList of containers located in the port.
	 */
	public ArrayList<Container> containers;
	/**
	 * ArrayList of ships that have left the port.
	 */
	ArrayList<Ship> history ;
	/**
	 * ArrayList of ships currently located in the port.
	 */
	public ArrayList<Ship> current ;
	
	/**
	 * Constructor that creates the port object by initializing the required fields.
	 * @param ID ID of the port.
	 * @param X X component of location.
	 * @param Y Y component of location.
	 */
	public Port(int ID , double X , double Y) {
		this.ID = ID;
		this.X = X ;
		this.Y = Y ;
		containers = new ArrayList<Container>();
		history = new ArrayList<Ship>();
		current = new ArrayList<Ship>() ;
	}
	
	/**
	 * Calculates the distance between two ports.
	 * @param other Other port.
	 * @return   The distance between two ports.
	 */
	public double getDistance(Port other) {
		return Math.sqrt(((this.X-other.X)*(this.X-other.X))+((this.Y-other.Y)*(this.Y-other.Y)));
	}
	
	/**
	 * Adds incoming ship to current ArrayList.
	 * @param s Ship
	 */
	public void incomingShip(Ship s) {
		if (current.contains(s) == false) {
				if (current.size()>0) {
					for(int i = 0 ; i < current.size() ; i++) {
						if (s.getID()<current.get(i).getID()) {
							current.add(i, s);
							break;
						}
					}
				}
				if (current.contains(s) == false) {
					current.add(s);
				}
					
			
				
			}
		
		
	};
	
	
	/**
	 * Removes the outgoing ship from the current ArrayList and adds it to the history ArrayList.
	 * @param s Ship 
	 */
	public void outgoingShip(Ship s) {
		if (history.contains(s) == false) {
			history.add(s);
			
		}
		current.remove(s);
	}
	
	/**
	 * Allows you to access the id.
	 * @return ID of port.
	 */

	public int getID() {
		return ID;
	}
	
	/**
	 * Allows you to access the X component of location.
	 * @return X component of location.
	 */
	public double getX() {
		return X;
	}
	/**
	 * Allows you to access the Y component of location.
	 * @return Y component of location.
	 */

	public double getY() {
		return Y;
	};
}





