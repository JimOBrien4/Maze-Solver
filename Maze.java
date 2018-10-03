 /* File: Maze.java
  * Author: Jim O'Brien
  * Course: CMPT 435
  * Assignment: Project 1
  * Due Date: 2/2/18
  * 
  * This file contains the Maze class which contains the maze we will be trying to solve.
  */

import java.util.Scanner;
import java.lang.Integer;

 
 /*Maze
  *
  * This class allows us to create Maze objects, which represent the maze we
  * are trying to solve. A maze contains an array of locations called 
  * validLocations as well as the number of locations as validLocationCount. 
  * validLocationCount essentially serves as the length of the validLocations 
  * array but does not have to be calculated because it is streamed in.
  * The object also contains two more objects, the start and end location.
  */

class Maze {
  private Maze(Maze m) { assert(false); }

  private int validLocationCount;
  private Location[] validLocations;

  private Location startLocation;
  private Location endLocation;

  Maze() {
    // - No arguments. Default constructor. Very lonely function.
     
  }

  Location getStartLocation() {
    // - No arguments. Returns the start location of the maze.
    return startLocation;
  }
  
  boolean isValidLocation(Location loc) {
    // - Iterates through validLocations to see if argument Location loc is contained.
    boolean found = false;
    for (int i = 0; i < validLocationCount; i++){
      if(loc.isEqual(validLocations[i])){
        found = true;
      }
    }
    //System.out.println("Valid location? " + found);//debug
    return found;
  }
  
  boolean isEndLocation(Location loc) {
    // - Tests to see if argument Location loc is the end of the maze, and 
	// returns a boolean result.
    return endLocation.isEqual(loc);
  }
  
  int getValidLocationCount(){
    // - No arguments. Returns the number of valid locations.
    return validLocationCount;
  }
  
  void streamIn(Scanner input) {
    // - Utilizes Scanner input to stream a maze into the program. 
    validLocationCount = Integer.valueOf(input.next());
    if(validLocationCount <= 0){
      return;//If the maze is empty, we don't even bother with the rest!
    }
    validLocations = new Location[validLocationCount];
    String x;
    String y;
    for(int i = 0; i < validLocationCount;i++){
      x = input.next();
      y = input.next();
      validLocations[i] = new Location(Integer.valueOf(x),Integer.valueOf(y));
    }
    x = input.next();
    y = input.next();
    startLocation = new Location(Integer.valueOf(x),Integer.valueOf(y));
    x = input.next();
    y = input.next();
    endLocation = new Location(Integer.valueOf(x),Integer.valueOf(y));
  }
  
    Location[] getLocArray(){
      // - No arguments. Returns the array of valid locations.
      return validLocations;
  }
}
