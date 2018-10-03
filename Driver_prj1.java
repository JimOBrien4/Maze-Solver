 /* File: Driver_prj1.java
  * Author: Jim O'Brien
  * Course: CMPT 435
  * Assignment: Project 1
  * Due Date: 2/2/18
  * 
  * This file contains the Driver class which contains the our main method
  */

import java.util.Scanner;
import java.util.Arrays;

 /*Driver_prj1
  *
  *This class contains the main method, which is our logic to find our way 
  *through the maze.
  *  
  */

public class Driver_prj1 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Maze maze = new Maze();
    maze.streamIn(input);
    if(maze.getValidLocationCount() <= 0){
      System.out.println("No solution found");//Cannot navigate empty maze
      return;
    }
	if(maze.isEndLocation(maze.getStartLocation())){
      System.out.println("Solution found:");//we started at the end, that was easy!
      maze.getStartLocation().streamOut();
	  return;
    }
    LocationStack mazeStack = new LocationStack();
    mazeStack.push(maze.getStartLocation());
    Location current;
    Location next;
    boolean found = false;
    boolean popBypass;
    while(!(mazeStack.isEmpty())){
      popBypass = false;
      current = mazeStack.getTop();
      next = current.nextNeighbor();
      if(maze.isEndLocation(next) && maze.isValidLocation(next)){
        found = true;
        mazeStack.push(next);
        System.out.println("Solution found:");
        mazeStack.streamOut(mazeStack);
        break;
      }
      else if(maze.isEndLocation(next) && !(maze.isValidLocation(next))){
        //We found the end... but it's not a valid location!
        break;
      }
      if(maze.isValidLocation(next)){
        //We found one! Is it on the stack?
        if(mazeStack.isOn(next)){
          //We're already working on that one!
        }
        else{
          //OK it's new let's push it.
          mazeStack.push(next);
          popBypass = true;
		  //This prevents us from adding a location to the stack and then 
		  //immediately popping it because current has not yet updated and 
		  //has finished iterating while the new top of the stack hasn't even 
		  //started iterating yet.
        }
      }
      if(current.isDone() && !(popBypass)){
        //let's turn around, that location had no valid neighbors
        mazeStack.pop();
      }
    }
    if(found == false){
      System.out.println("No solution found");
    }
  }
}  
