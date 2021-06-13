
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;
/**
 * Class that contains fields and methods related to the Liquid Container.
 * @author celik 
 *
 */
public class LiquidContainer extends HeavyContainer {
	/**
	 * Constructor that creates the Container object by initializing the required fields.
	 * @param ID ID of the container 
	 * @param weight Weight of the container
	 */
	public LiquidContainer(int ID , int weight) {
		super(ID, weight);
		
	}
	@Override
	public double consumption() {
		return 4.00 * this.getWeight() ;
	} 
	@Override
	public  String getType() {
		return "liquid" ;
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

