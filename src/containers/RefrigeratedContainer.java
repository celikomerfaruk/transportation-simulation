
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package containers;

public class RefrigeratedContainer extends HeavyContainer {
	public RefrigeratedContainer(int ID , int weight) {
		super(ID, weight);
		
	}
	public double consumption() {
		return 5.00 ;
	} 
	public  String getType() {
		return "refrigerator" ;
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

