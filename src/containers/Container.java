
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

public abstract class Container {
	private final int ID ;
	private final  int  weight ;
	

	public Container(int ID , int weight) {
		this.ID = ID ;
		this.weight = weight;
		
	}
	public abstract double consumption() ; 
	public abstract String getType();
	public boolean equals(Container other) {
		if(this.getType() == other.getType() && this.getID() == other.getID() && this.getWeight() == other.getWeight())
		{
			return true ;
		}
		else {
			return false;
		}
	}
	
	public int getID() {
		return ID;
	}
	public int getWeight() {
		return weight;
	}
	

}

	


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

