
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package ships;

import java.util.ArrayList;
import java.util.Iterator;

import containers.Container;
import interfaces.IShip;
import ports.Port;
/**
 * Class that contains fields and methods related to the Ship.
 * @author celik
 *
 */
public class Ship implements IShip {
	/**
	 * ID of the Ship.
	 */
	private final int ID ;
	/**
	 * The amount of fuel the Ship has.
	 */
	private double fuel ;
	/**
	 * Port that the ship has located.
	 */
	public Port currentPort ;
	/**
	 * Total weight capacity of the Ship.
	 */
	final private int totalWeightCapacity;
	/**
	 * Maximum number of containers the ship can carry.
	 */
	final private int maxNumberOfAllContainers;
	/**
	 * Maximum number of heavy containers the ship can carry.
	 */
	final private int maxNumberOfHeavyContainers;
	/**
	 * Maximum number of refrigerated containers the ship can carry.
	 */
	final private int maxNumberOfRefrigeratedContainers;
	/**
	 * Maximum number of liquid containers the ship can carry.
	 */
	final private int maxNumberOfLiquidContainers;
	/**
	 * Fuel consumption of Ship(per KM)
	 */
	final private double fuelConsumptionPerKM ;
	/**
	 * The amount of weight the ship is currently carrying
	 */
	private int currentLiftingWeight;
	/**
	 * Number of containers the ship is currently carrying
	 */
	private int currentLiftingAllContainers;
	/**
	 * Number of heavy containers the ship is currently carrying
	 */
	private int currentLiftingHeavyContainers;
	/**
	 * Number of refrigerated containers the ship is currently carrying
	 */
	private int currentLiftingRefrigeratedContainers;
	/**
	 * Number of liquid containers the ship is currently carrying
	 */
	private int currentLiftingLiquidContainers;
	/**
	 * ArrayList of containers the ship is currently carrying
	 */
	public ArrayList<Container> liftingContainers; 
	
	/**
	 * Constructor that creates the Ship object by initializing the required fields.
	 * @param ID ID of the Ship.
	 * @param p Port that the ship has located.
	 * @param totalWeightCapacity Total weight capacity of the Ship.
	 * @param maxNumberOfAllContainers Maximum number of containers the ship can carry.
	 * @param maxNumberOfHeavyContainers Maximum number of heavy containers the ship can carry.
	 * @param maxNumberOfRefrigeratedContainers Maximum number of refrigerated containers the ship can carry.
	 * @param maxNumberOfLiquidContainers  Maximum number of liquid containers the ship can carry
	 * @param fuelConsumptionPerKM Fuel consumption of Ship(per KM)
	 */
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
	/**
	 * Returns the ArrayList of containers the ship is currently carrying.
	 * @return ArrayList of containers the ship is currently carrying
	 */
	
	public ArrayList<Container> getCurrentContainers(){
		
		return liftingContainers;
	}
	/**
	 * Checks if the ship can go to the specified port and takes the necessary actions.
	 * @param p Destination Port.
	 * @return True if it satisfies the necessary conditions, false if it doesn't
	 */
	 
	public boolean sailTo(Port p) {
		double containerRates = 0.0;
		 for(int i = 0 ; i < this.liftingContainers.size() ; i++) {
			 containerRates += ((this.liftingContainers.get(i).consumption()));
		 }
		if (fuel >= ((this.currentPort.getDistance(p))*(this.fuelConsumptionPerKM + containerRates)) ) {
			fuel -= ((this.currentPort.getDistance(p))*(this.fuelConsumptionPerKM + containerRates)) ;
			this.currentPort.outgoingShip(this);
			this.currentPort = p;
			p.incomingShip(this);
			return true;
		}
		return false ;
	};
	/**
	 * Increases the amount of fuel by the given value
	 * @param newFuel Amount to be added
	 */
	public void reFuel(double newFuel) {
		fuel+= newFuel;
	};
	
	
	/**
	 * Checks if the container can be loaded on the ship and takes the necessary actions.
	 * @param cont Container
	 * @return True if it satisfies the necessary conditions, false if it doesn't
	 */
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
									if (liftingContainers.contains(cont) == false) {
										if (liftingContainers.size()>0) {
											for(int i = 0 ; i <liftingContainers.size() ; i++) {
												if (cont.getID()<liftingContainers.get(i).getID()) {
													liftingContainers.add(i, cont);
													break;
												}
											}
										}
									}
										if (liftingContainers.contains(cont) == false) {
											liftingContainers.add(cont);
										}
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
									
									if (liftingContainers.contains(cont) == false) {
										if (liftingContainers.size()>0) {
											for(int i = 0 ; i <liftingContainers.size() ; i++) {
												if (cont.getID()<liftingContainers.get(i).getID()) {
													liftingContainers.add(i, cont);
													break;
												}
											}
										}
									}
										if (liftingContainers.contains(cont) == false) {
											liftingContainers.add(cont);
										}
									
									
									currentPort.containers.remove(cont);
									return true ;
								}
							}
							if("heavy".equals(cont.getType()) ) {
								currentLiftingAllContainers++;
								currentLiftingHeavyContainers++;
								currentLiftingWeight += cont.getWeight(); 
								if (liftingContainers.contains(cont) == false) {
									if (liftingContainers.size()>0) {
										for(int i = 0 ; i <liftingContainers.size() ; i++) {
											if (cont.getID()<liftingContainers.get(i).getID()) {
												liftingContainers.add(i, cont);
												break;
											}
										}
									}
								}
									if (liftingContainers.contains(cont) == false) {
										liftingContainers.add(cont);
									}
								currentPort.containers.remove(cont);
								return true;
							}
						}
					}
					else {
						currentLiftingAllContainers++;
						currentLiftingWeight+= cont.getWeight();
						if (liftingContainers.contains(cont) == false) {
							if (liftingContainers.size()>0) {
								for(int i = 0 ; i <liftingContainers.size() ; i++) {
									if (cont.getID()<liftingContainers.get(i).getID()) {
										liftingContainers.add(i, cont);
										break;
									}
								}
							}
						}
							if (liftingContainers.contains(cont) == false) {
								liftingContainers.add(cont);
							}
						currentPort.containers.remove(cont);
						return true;
					}
				
	
					}
				}
			};
			
		
		return false;
		
	}
	/**
	 * Allows you to access the id.
	 * @return ID of the ship.
	 */
	
	public int getID() {
		return ID ;
	}
	/**
	 * Allows you to access the fuel.
	 * @return Fuel of the ship.
	 */
	public double getFuel() {
		return fuel ;
	}
	/**
	 * It checks whether the container can be unloaded from the ship and takes the necessary actions.
	 * @param cont Container.
	 * @return True if it satisfies the necessary conditions, false if it doesn't
	 */
	public boolean unLoad(Container cont) {
		if (liftingContainers.contains(cont)){
			if(currentPort.containers.contains(cont) == false) {

				if (currentPort.containers.size()>0) {
					for(int i = 0 ; i < currentPort.containers.size() ; i++) {
						if (cont.getID()<currentPort.containers.get(i).getID()) {
							currentPort.containers.add(i, cont);
							break;
						}
					}
				}
				if (currentPort.containers.contains(cont) == false) {
					currentPort.containers.add(cont);
				}
			}
			liftingContainers.remove(cont);
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

