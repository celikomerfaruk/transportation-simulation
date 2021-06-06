
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ships;

import java.util.ArrayList;
import java.util.Iterator;

import containers.Container;
import interfaces.IShip;
import ports.Port;

public class Ship implements IShip {
	private final int ID ;
	private double fuel ;
	public Port currentPort ;
	private int totalWeightCapacity;
	private int maxNumberOfAllContainers;
	private int maxNumberOfHeavyContainers;
	private int maxNumberOfRefrigeratedContainers;
	private int maxNumberOfLiquidContainers;
	private double fuelConsumptionPerKM ;
	private int currentLiftingWeight;
	private int currentLiftingAllContainers;
	private int currentLiftingHeavyContainers;
	private int currentLiftingRefrigeratedContainers;
	private int currentLiftingLiquidContainers;
	public ArrayList<Container> liftingContainers; 
	
	
	public Ship(int ID, Port p, int totalWeightCapacity, int maxNumberOfAllContainers, int maxNumberOfHeavyContainers, int maxNumberOfRefrigeratedContainers, int maxNumberOfLiquidContainers, double fuelConsumptionPerKM)
	{
		this.ID = ID ;
		this.totalWeightCapacity = totalWeightCapacity;
		this.currentPort = p;
		p.incomingShip(this);
		this.maxNumberOfAllContainers = maxNumberOfAllContainers;
		this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers ;
		this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers ;
		this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers ;
		this.fuelConsumptionPerKM = fuelConsumptionPerKM ;
		this.fuel = 0;
		this.currentLiftingWeight = 0;
		this.currentLiftingAllContainers = 0;
		this.currentLiftingHeavyContainers = 0;
		this.currentLiftingRefrigeratedContainers = 0;
		this.currentLiftingLiquidContainers = 0;
		liftingContainers = new ArrayList<Container>();
	}
	
	public ArrayList<Container> getCurrentContainers(){
		return liftingContainers;
	}
	public boolean sailTo(Port p) {
		double containerRates = 0;
		 for(int i = 0 ; i < this.liftingContainers.size() ; i++) {
			 containerRates += this.liftingContainers.get(i).consumption();
		 }
		if (fuel >= (this.currentPort.getDistance(p))*(this.fuelConsumptionPerKM + containerRates) ) {
			fuel -= (this.currentPort.getDistance(p))*(this.fuelConsumptionPerKM + containerRates) ;
			this.currentPort.outgoingShip(this);
			this.currentPort = p;
			p.incomingShip(this);
			return true;
		}
		return false ;
	};
	
	public void reFuel(double newFuel) {
		fuel+= newFuel;
	};
	
	
	
	public boolean load(Container cont) {
		if (currentPort.containers.contains(cont)) {
			if(cont.getWeight()+currentLiftingWeight <= totalWeightCapacity )
			{
				if(currentLiftingAllContainers < maxNumberOfAllContainers) {
					if ( "heavy".equals(cont.getType())    || "liquid".equals(cont.getType())   || "refrigerator".equals(cont.getType())   ) {
						if(currentLiftingHeavyContainers < maxNumberOfHeavyContainers) {
							if ( "liquid".equals(cont.getType())) {
								if (currentLiftingLiquidContainers<maxNumberOfLiquidContainers) {
									currentLiftingLiquidContainers++;
									currentLiftingHeavyContainers++;
									currentLiftingAllContainers++;
									currentLiftingWeight+= cont.getWeight();
									liftingContainers.add(cont);
									currentPort.containers.remove(cont);
									return true;
								}
							}
							if("refrigerator".equals(cont.getType())) {
								if (currentLiftingRefrigeratedContainers < maxNumberOfRefrigeratedContainers) {
									currentLiftingRefrigeratedContainers++;
									currentLiftingHeavyContainers++;
									currentLiftingAllContainers++;
									currentLiftingWeight+= cont.getWeight();
									liftingContainers.add(cont);
									currentPort.containers.remove(cont);
									return true ;
								}
							}
							if("heavy".equals(cont.getType()) ) {
								currentLiftingAllContainers++;
								currentLiftingHeavyContainers++;
								currentLiftingWeight += cont.getWeight(); 
								liftingContainers.add(cont);
								currentPort.containers.remove(cont);
								return true;
							}
						}
					}
					else {
						currentLiftingAllContainers++;
						currentLiftingWeight+= cont.getWeight();
						liftingContainers.add(cont);
						currentPort.containers.remove(cont);
						return true;
					}
				
	
					}
				}
			}
			
		
		return false;
		
	};
	
	public int getID() {
		return ID ;
	}
	
	public double getFuel() {
		return fuel ;
	}
	
	public boolean unLoad(Container cont) {
		if (liftingContainers.contains(cont)){
			if(currentPort.containers.contains(cont) == false) {
				currentPort.containers.add(cont);
			}
			if("heavy".equals(cont.getType())) {
				currentLiftingAllContainers--;
				currentLiftingHeavyContainers--;
				currentLiftingWeight-= cont.getWeight();
			}
			if("liquid".equals(cont.getType())) {
				currentLiftingAllContainers--;
				currentLiftingHeavyContainers--;
				currentLiftingLiquidContainers--;
				currentLiftingWeight-= cont.getWeight();
			}
			if("refrigerator".equals(cont.getType())) {
				currentLiftingAllContainers--;
				currentLiftingHeavyContainers--;
				currentLiftingRefrigeratedContainers--;
				currentLiftingWeight-= cont.getWeight();
			}
			if("basic".equals(cont.getType()) ) {
				currentLiftingAllContainers--;
				currentLiftingWeight-= cont.getWeight();
			}
			return true;
		}
		return false;
	};
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

