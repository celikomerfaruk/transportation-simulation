
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * Class that contains fields and methods related to the Container.
 * @author celik
 *
 */
public abstract class Container {
	/**
	 * ID of the Container.
	 */
	private final int ID ;
	/**
	 * Weight of the container.
	 */
	private final  int  weight ;
	
	/**
	 * Constructor that creates the Container object by initializing the required fields.
	 * @param ID ID of the Container.
	 * @param weight Weight of the Container.
	 */
	public Container(int ID , int weight) {
		this.ID = ID ;
		this.weight = weight;
		
	}
	/**
	 * Returns fuel consumption required by the container.
	 * @return  Fuel consumption required by the container.
	 */
	public abstract double consumption() ; 
	/**
	 * Returns the type of the container.
	 * @return The type of the container.
	 */
	public abstract String getType();
	/**
	 * Checks if two containers are the same.
	 * @param other Other Container.
	 * @return If they are the same, return true, otherwise return false.
	 */
	public boolean equals(Container other) {
		if(this.getType() == other.getType() && this.getID() == other.getID() && this.getWeight() == other.getWeight())
		{
			return true ;
		}
		else {
			return false;
		}
	}
	/**
	 * Allows you to access the id.
	 * @return ID of the Container.
	 */
	
	public int getID() {
		return ID;
	}
	/**
	 * Allows you to access the weight of Container.
	 * @return Weight of Container.
	 */
	public int getWeight() {
		return weight;
	}
	

}

	


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

