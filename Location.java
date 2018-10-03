 /* File: Location.java
  * Author: Jim O'Brien
  * Course: CMPT 435
  * Assignment: Project 1
  * Due Date: 2/2/18
  * 
  * This file contains the Location class which contains the coordinates of a 
  * single maze location.
  */

import java.util.Scanner;

 /*Location
  *
  * This class allows us to create Location objects, which contain the 
  * numerical coordinates of a location in the maze. Additionally, each
  * object is able to iterate over it's neighbors in a set order, allowing
  * the object to do some of the heavy lifting in our search through the maze. 
  */

class Location {
final int RIGHT = 0;
final int DOWN  = 1;
final int LEFT  = 2;
final int UP    = 3;
final int DONE  = 4;

private int row;
private int col;
int nextDirection;   // mutable

  Location() {
  // - No arguments. Default constructor for if no coordinates are provided, 
  //sets location as the mathematical point of origin.
    row = 0;
    col = 0;
  }
  
  Location(int x, int y) {
  //- Creates the Location object with specified graphical coordinates x and y. 
    row = x;
    col = y;
  }

  void start() {  // const
  // - No arguments. Initializes the iteration capabilities of the object at the beginning.
    nextDirection = RIGHT;
  }

  Location nextNeighbor() {  // const
  // - No arguments. Iterates through the searchable directions and then finds 
  //the coordinates of the next neighbor location and returns an object for it.
    int x = row;
    int y = col;
    switch(nextDirection){
      case RIGHT:
        y += 1;
        nextDirection += 1;
        break;
      case DOWN:
        x += 1;
        nextDirection += 1;
        break;
      case LEFT:
        y -= 1;
        nextDirection += 1;
        break;
      case UP:
        x -= 1;
        nextDirection += 1;
        break;
       case DONE:
        break;
    }
    return new Location(x,y);
  }

  boolean isDone() {  // const
  // - No arguments. Returns true if the location has iterated through all 
  //neighbors.
    return nextDirection == DONE;
  }

  boolean isEqual(Location loc) {  // const
  // - Returns true if the location object refers to the same graphical 
  //coordinate as the argument Loation loc. Note that iteration status is 
  //not considered.
    if((loc.row == row) && (loc.col == col)){
      return true;
    }
    else{
      return false;
    }
  }

  void streamOut() {
    // - No arguments. Prints the Location coordinates to the CLI.
    System.out.println(row + " " + col);
  }

  void streamIn(Scanner input) {
    // - Allows Location object to be streamed in via argument Scanner input.
    row = input.nextInt();
    col = input.nextInt();
    input.close();
  }
}
