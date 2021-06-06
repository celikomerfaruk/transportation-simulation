
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ports;

import java.util.ArrayList;

import containers.Container;
import interfaces.IPort;
import ships.Ship;

public class Port implements IPort {
	
	private final int ID;
	private final double X;
	private final double Y;
	public ArrayList<Container> containers;
	ArrayList<Ship> history ;
	public ArrayList<Ship> current ;
	
	public Port(int ID , double X , double Y) {
		this.ID = ID;
		this.X = X ;
		this.Y = Y ;
		containers = new ArrayList<Container>();
		history = new ArrayList<Ship>();
		current = new ArrayList<Ship>() ;
	}
	
	public double getDistance(Port other) {
		return Math.sqrt(((this.X-other.X)*(this.X-other.X))+((this.Y-other.Y)*(this.Y-other.Y)));
	}
	public void incomingShip(Ship s) {
		if (current.contains(s) == false) {
			current.add(s);
		}
	};
	
	public void outgoingShip(Ship s) {
		if (history.contains(s) == false) {
			history.add(s);
			
		}
		current.remove(s);
	}

	public int getID() {
		return ID;
	}

	public double getX() {
		return X;
	}

	public double getY() {
		return Y;
	};
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

