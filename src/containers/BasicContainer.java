


package containers;
/**
 * Class that contains fields and methods related to the Basic Container.
 * @author celik 
 *
 */
public class BasicContainer extends Container{
	/**
	 * Constructor that creates the Container object by initializing the required fields.
	 * @param ID ID of the container 
	 * @param weight Weight of the container
	 */
	public BasicContainer(int ID , int weight) {
		super(ID, weight);
		
	}
	@Override
	public double consumption() {
		return 2.50*this.getWeight() ;
	} 
	@Override
	public  String getType() {
		return "basic" ;
	};

}


