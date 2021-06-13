
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

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
		return 2.50 ;
	} 
	@Override
	public  String getType() {
		return "basic" ;
	};

}


//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

