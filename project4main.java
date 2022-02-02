import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
public class project4main {

	public static void main(String[] args) throws FileNotFoundException {
		
		
	      Scanner in = new Scanner(new File (args[0]));
	      PrintStream out = new PrintStream(new File (args[1]));
	      
	      
	      ///////////////////////////////////////////////////// Reading trains Going to the Green Region
	      int dummy = 0;
	      int trainsGreen = in.nextInt();
	      ArrayList<Integer> greenTrains = new ArrayList<Integer>();
	      
	      if (trainsGreen == 0) {
	    	  in.nextLine();
	      }
	      else {
	    	  dummy = trainsGreen;
	    	  for (int counter = 0; counter< trainsGreen; counter++) {
	    		  int entry = in.nextInt();
	    		  if (entry!=0) {
		    		  greenTrains.add(entry);
	    		  }
	    		  else {
	    			  dummy --;
	    		  }
	    	  }	 
		      trainsGreen = dummy;
	      }
	      ////////////////////////////////////////////////// Reading trains Going to the Red Region
	      int trainsRed = in.nextInt();
	      ArrayList<Integer> redTrains = new ArrayList<Integer>();
	      
	      if (trainsRed == 0) {
	    	  in.nextLine();
	      }
	      else {
	    	  dummy = trainsRed;
	    	  for (int counter = 0; counter< trainsRed; counter++) {
	    		  int entry = in.nextInt();
	    		  if (entry!=0) {
	    			  redTrains.add(entry);
	    		  }
	    		  else {
	    			  dummy --;
	    		  }
	    	  }
	    	   trainsRed = dummy ;
	      }
	      //////////////////////////////////////////////// Reading reindeers going to the Green Region
	      
	      int reindeersGreen = in.nextInt();
	      ArrayList<Integer> greenReindeers = new ArrayList<Integer>();
	      
	      if (reindeersGreen == 0) {
	    	  in.nextLine();
	      }
	      else {
	    	  dummy = reindeersGreen;
	    	  for (int counter = 0; counter< reindeersGreen; counter++) {
	    		  int entry = in.nextInt();
	    		  if (entry!=0) {
	    			  greenReindeers.add(entry);
	    		  }
	    		  else {
	    			  dummy --;
	    		  }
	      }
		     reindeersGreen = dummy;
	      }
	      //////////////////////////////////////////////////////////// Reading reindeers going to the Red Region
        
	      
	      int reindeersRed = in.nextInt();
	      ArrayList<Integer> redReindeers = new ArrayList<Integer>();
	      
	      if (reindeersRed == 0) {
	    	  in.nextLine();
	      }
	      else {
	    	  dummy = reindeersRed;
	    	  for (int counter = 0; counter< reindeersRed; counter++) {
	    		  int entry = in.nextInt();
	    		  if (entry!=0) {
	    			  redReindeers.add(entry);
	    		  }
	    		  else {
	    			  dummy --;
	    		  }
	    	  }
		      reindeersRed = dummy;
	      }
	      ///////////////////////////////////////////////////////////// Reading the gifts while combining gift sacks with the same type except for those which include "a" in their definition
	      int totalGifts = 0;
	      int bagType = in.nextInt();
	      ArrayList<String> types = new  ArrayList<String>();
	      ArrayList<Integer> gifts = new ArrayList<Integer>();
	      HashMap<String,Integer> gift = new HashMap<String,Integer>();
	      int tmp = 0;
    	  for (int counter = 0; counter< bagType; counter++) {
    		  String type = in.next();
    		  tmp = in.nextInt();
    		  if(tmp !=0) {
    			  totalGifts += tmp;
    			  if (!type.contains("a")) {
    			  if (gift.containsKey(type)) {
    				  int val = gift.get(type);
    	    		  gift.put(type, val + tmp);
    			  }
    			  else {
    				  gift.put(type,tmp);
    			  }
    			  }
    			  else {
     				 types.add(type);
     				 gifts.add(tmp);
     				  
     			  }
    		  }
    	  }
    	  for (String key: gift.keySet()) {
    		  gifts.add(gift.get(key));
    		  types.add(key);
    	  }
    	  bagType = types.size();
    	 ////////// 
         ArrayList<Integer> totalElements = new ArrayList<Integer>();
         totalElements.add(0);
         totalElements.addAll(gifts);
         totalElements.addAll(greenTrains);
         totalElements.addAll(redTrains);
         totalElements.addAll(greenReindeers);
         totalElements.addAll(redReindeers);
         totalElements.add(1);
         int[] checkpoints = new int[4];
   	     int checkpoint2 = 1+bagType;
   	     //////////////////////////////////Checkpoints array to facilitate matching proper elements with each other
   	  
   	      if (trainsGreen==0) {
   		  checkpoints[0] = -1;
   	      }
   	      else {
   		  checkpoints[0] = checkpoint2;
   		  checkpoint2 += trainsGreen;
   	      }
   	      ///////////
   	      if (trainsRed==0) {
   		  checkpoints[1] = -1;
   	      }
   	      else {
   		  checkpoints[1] = checkpoint2;
   		  checkpoint2 += trainsRed;
   	      }
   	      /////////////
   	      if (reindeersGreen==0) {
   		  checkpoints[2] = -1;
   	      }
   	      else {
   		  checkpoints[2] = checkpoint2;
   		  checkpoint2 += reindeersGreen;
   	      }
   	      /////////////
   	      if (reindeersRed==0) {
   		  checkpoints[3] = -1;
   	      }
   	      else {
   		  checkpoints[3] = checkpoint2;
   	      }
   	      int [] temp = new int[totalElements.size()];
   	      for (int counter = 0 ; counter <temp.length;counter ++) {
   	    	  temp[counter] = totalElements.get(counter);
   	      }
   	      //////////// Our Bipartite Graph
    	 Graph map = new Graph(temp,checkpoints,trainsGreen,trainsRed,reindeersGreen,reindeersRed, types);
    	 ///////Initiating the process of finding the maximum number of gifts to be delivered by Dinic's Algorithm
    	 map.findMaxFlow();
    	 out.print(totalGifts - map.getTotalFlow());
	     in.close();
	     out.close();
	     
	}
	}




