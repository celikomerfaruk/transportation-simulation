
//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import containers.*;
import ports.Port;
import ships.Ship;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.platform.engine.support.hierarchical.ForkJoinPoolHierarchicalTestExecutorService;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		//
		// Main receives two arguments: path to input file and path to output file.
		// You can assume that they will always be provided, so no need to check them.
		// Scanner and PrintStream are already defined for you.
		// Use them to read input and write output.
		// 
		// Good Luck!
		// 
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		
		ArrayList<Port> allPorts = new ArrayList<Port>() ;
		ArrayList<Ship> allShips = new ArrayList<Ship>() ;
		ArrayList<Container> allContainers = new ArrayList<Container>()	;	
		int actionCount = in.nextInt();
		in.nextLine();
		String[] actions = new String[actionCount];
		int containerID = 0 ;
		int portID = 0 ;
		int shipID = 0;
		
		
		
		for(int i = 0; i < actionCount ; i++) {
			actions[i]  = in.nextLine();
				
			if("3".equals(actions[i].split(" ")[0])) {             //port oluþturuyoruz
			allPorts.add(new Port(portID , Double.parseDouble(actions[i].split(" ")[1]), Double.parseDouble(actions[i].split(" ")[2]) )) ;
			portID++;
			}
			
			if("2".equals(actions[i].split(" ")[0]))   {            //ship oluþturuyoruz
				allShips.add(new Ship(shipID,allPorts.get(Integer.parseInt(actions[i].split(" ")[1])),Integer.parseInt(actions[i].split(" ")[2]), Integer.parseInt(actions[i].split(" ")[3]),Integer.parseInt(actions[i].split(" ")[4]),Integer.parseInt(actions[i].split(" ")[5]),Integer.parseInt(actions[i].split(" ")[6]),Double.parseDouble(actions[i].split(" ")[7]) )) ;
				shipID++;
			}
			
			
			
			if("1".equals(actions[i].split(" ")[0])) {    //containerlarý oluþturuyoruz
				
				
				
				if(actions[i].split(" ").length == 4) {
					if ("R".equals(actions[i].split(" ")[3]))
						
					   {
						allPorts.get(Integer.parseInt(actions[i].split(" ")[1])).containers.add(new RefrigeratedContainer(containerID ,Integer.parseInt(actions[i].split(" ")[2])))  ;
						allContainers.add(allPorts.get(Integer.parseInt(actions[i].split(" ")[1])).containers.get(allPorts.get(Integer.parseInt(actions[i].split(" ")[1])).containers.size()-1));
					   }
		 
					
                    if ("L".equals(actions[i].split(" ")[3])) 
						
						{allPorts.get(Integer.parseInt(actions[i].split(" ")[1])).containers.add(new LiquidContainer(containerID ,Integer.parseInt(actions[i].split(" ")[2])))  ;
						allContainers.add(allPorts.get(Integer.parseInt(actions[i].split(" ")[1])).containers.get(allPorts.get(Integer.parseInt(actions[i].split(" ")[1])).containers.size()-1));
						}
		 
					}
				else {
					if (Integer.parseInt(actions[i].split(" ")[2])<= 3000) {
						allPorts.get(Integer.parseInt(actions[i].split(" ")[1])).containers.add(new BasicContainer(containerID ,Integer.parseInt(actions[i].split(" ")[2])));
						allContainers.add(allPorts.get(Integer.parseInt(actions[i].split(" ")[1])).containers.get(allPorts.get(Integer.parseInt(actions[i].split(" ")[1])).containers.size()-1));

					} else {
						allPorts.get(Integer.parseInt(actions[i].split(" ")[1])).containers.add(new HeavyContainer(containerID ,Integer.parseInt(actions[i].split(" ")[2])));
						allContainers.add(allPorts.get(Integer.parseInt(actions[i].split(" ")[1])).containers.get(allPorts.get(Integer.parseInt(actions[i].split(" ")[1])).containers.size()-1));

					}
				}
				containerID++;
					}
			
			if("4".equals(actions[i].split(" ")[0])) {
				allShips.get(Integer.parseInt(actions[i].split(" ")[1])).load(allContainers.get(Integer.parseInt(actions[i].split(" ")[2])));
				
			} 
			if("5".equals(actions[i].split(" ")[0])) {
				allShips.get(Integer.parseInt(actions[i].split(" ")[1])).unLoad(allContainers.get(Integer.parseInt(actions[i].split(" ")[2])));
			}
			
			if("6".equals(actions[i].split(" ")[0])) {
				allShips.get(Integer.parseInt(actions[i].split(" ")[1])).sailTo(allPorts.get(Integer.parseInt(actions[i].split(" ")[2])));
			}
			if("7".equals(actions[i].split(" ")[0])) {
				allShips.get(Integer.parseInt(actions[i].split(" ")[1])).reFuel(Double.parseDouble(actions[i].split(" ")[2]));
			}
		}      
		
		
		
		
		
		for(int i = 0 ; i< allPorts.size() ; i++) {
			if( i != 0 ) {
			   out.print("\n"); }
			out.printf("Port " + i + ": (%.2f" + ", %.2f" + ")",allPorts.get(i).getX() , allPorts.get(i).getY());
			
			for(int k = 0 ; k < allPorts.get(i).containers.size() ; k++) 
			{
				if ("basic".equals(allPorts.get(i).containers.get(k).getType())) {
					out.print("\n");
					out.print("  BasicContainer:") ;
					break;
				}
			}
			
			for(int k = 0 ; k < allPorts.get(i).containers.size() ; k++) {
				
				if ("basic".equals(allPorts.get(i).containers.get(k).getType())) {
					out.print(" ");
					out.print(  allPorts.get(i).containers.get(k).getID());
				}
				
				}
			
			for(int k = 0 ; k < allPorts.get(i).containers.size() ; k++) 
			{
				if ("heavy".equals(allPorts.get(i).containers.get(k).getType())) {
					out.print("\n");
					out.print("  HeavyContainer:") ;
					break;
				}
			}
			
			for(int k = 0 ; k < allPorts.get(i).containers.size() ; k++) {
				
				if ("heavy".equals(allPorts.get(i).containers.get(k).getType())) {
					out.print(" ");
					out.print(  allPorts.get(i).containers.get(k).getID());
				}
				
				}
			
			for(int k = 0 ; k < allPorts.get(i).containers.size() ; k++) 
			{
				if ("refrigerator".equals(allPorts.get(i).containers.get(k).getType())) {
					out.print("\n");
					out.print("  RefrigeratedContainer:") ;
					break;
				}
			}
			
			for(int k = 0 ; k < allPorts.get(i).containers.size() ; k++) {
				
				if ("refrigerator".equals(allPorts.get(i).containers.get(k).getType())) {
					out.print(" ");
					out.print(  allPorts.get(i).containers.get(k).getID());
				}
				
				}
			
			for(int k = 0 ; k < allPorts.get(i).containers.size() ; k++) 
			{
				if ("liquid".equals(allPorts.get(i).containers.get(k).getType())) {
					out.print("\n");
					out.print("  LiquidContainer:") ;
					break;
				}
			}
			
			for(int k = 0 ; k < allPorts.get(i).containers.size() ; k++) {
				
				if ("liquid".equals(allPorts.get(i).containers.get(k).getType())) {
					out.print(" ");
					out.print(  allPorts.get(i).containers.get(k).getID());
				}
				
				}
			
			for(int k = 0 ; k<allPorts.get(i).current.size() ; k++) {
	
				out.print("\n"); 
				out.printf("  Ship " + allPorts.get(i).current.get(k).getID() + ": %.2f"  ,allPorts.get(i).current.get(k).getFuel());
				
				for (int j = 0 ; j < allPorts.get(i).current.get(k).getCurrentContainers().size() ; j++ )
				{
					if("basic".equals(allPorts.get(i).current.get(k).getCurrentContainers().get(j).getType())) {
						out.print("\n");
						out.print("    BasicContainer:");
						break; 
					}
				}
				for (int j = 0 ; j < allPorts.get(i).current.get(k).getCurrentContainers().size() ; j++ ) {
					if("basic".equals(allPorts.get(i).current.get(k).getCurrentContainers().get(j).getType()))
					{
						out.print(" ");
						out.print(allPorts.get(i).current.get(k).getCurrentContainers().get(j).getID());
					
					}
				}
				
				for (int j = 0 ; j < allPorts.get(i).current.get(k).getCurrentContainers().size() ; j++ )
				{
					if("heavy".equals(allPorts.get(i).current.get(k).getCurrentContainers().get(j).getType())) {
						out.print("\n");
						out.print("    HeavyContainer:");
						break; 
					}
				}
				for (int j = 0 ; j < allPorts.get(i).current.get(k).getCurrentContainers().size() ; j++ ) {
					if("heavy".equals(allPorts.get(i).current.get(k).getCurrentContainers().get(j).getType()))
					{
						out.print(" ");
						out.print(allPorts.get(i).current.get(k).getCurrentContainers().get(j).getID());
					
					}
				}
				
				for (int j = 0 ; j < allPorts.get(i).current.get(k).getCurrentContainers().size() ; j++ )
				{
					if("refrigerator".equals(allPorts.get(i).current.get(k).getCurrentContainers().get(j).getType())) {
						out.print("\n");
						out.print("    RefrigeratedContainer:");
						break; 
					}
				}
				for (int j = 0 ; j < allPorts.get(i).current.get(k).getCurrentContainers().size() ; j++ ) {
					if("refrigerator".equals(allPorts.get(i).current.get(k).getCurrentContainers().get(j).getType()))
					{
						out.print(" ");
						out.print(allPorts.get(i).current.get(k).getCurrentContainers().get(j).getID());
					
					}
				}
				
				for (int j = 0 ; j < allPorts.get(i).current.get(k).getCurrentContainers().size() ; j++ )
				{
					if("liquid".equals(allPorts.get(i).current.get(k).getCurrentContainers().get(j).getType())) {
						out.print("\n");
						out.print("    LiquidContainer:");
						break; 
					}
				}
				for (int j = 0 ; j < allPorts.get(i).current.get(k).getCurrentContainers().size() ; j++ ) {
					if("liquid".equals(allPorts.get(i).current.get(k).getCurrentContainers().get(j).getType()))
					{
						out.print(" ");
						out.print(allPorts.get(i).current.get(k).getCurrentContainers().get(j).getID());
					
					}
				}
				
			}
		}
		
		in.close();
		out.close();
	}
}



//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE

