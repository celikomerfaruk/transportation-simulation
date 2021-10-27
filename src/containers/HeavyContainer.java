


package containers;
/**
 * Class that contains fields and methods related to the Heavy Container.
 * @author celik 
 *
 */
public class HeavyContainer extends Container {
	/**
	 * Constructor that creates the Container object by initializing the required fields.
	 * @param ID ID of the container 
	 * @param weight Weight of the container
	 */
	public HeavyContainer(int ID , int weight) {
		super(ID, weight);
		
	}
	@Override 
	public double consumption() {
		return 3.00 * this.getWeight() ;
	} ;
	@Override
	public  String getType() {
		return "heavy" ;
	};

}





