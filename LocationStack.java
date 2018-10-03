 /* File: LocationStack.java
  * Author: Jim O'Brien
  * Course: CMPT 435
  * Assignment: Project 1
  * Due Date: 2/2/18
  * 
  * This file contains the LocationStack class which allows us to create and 
  * manage stacks of locations, as well as the LocationSackNode class which 
  * creates an object for each node in the stack.
  */

 
 /*LocationStack
  *
  * This class allows us to create LocationStack objects, which consist of 
  * LocationStackNodes that allow us to stack locations on top of each other
  * to simulate a path through a maze. As we go father down the maze, 
  * new locations are added to our path, and if we need to double back 
  * we can pop from the stack to return to a previous position.
  */

class LocationStack {
  private LocationStack(LocationStack s) { 
    assert(false); 
  }
  private LocationStackNode top;
  private Location stackEnd = new Location(-1,-1);
  private LocationStackNode endNode = new LocationStackNode(stackEnd,null);
  
  LocationStack() {
    // - The default constructor, no arguments. Sets node endNode as the top 
	//of the stack. endNode serves as the "floor" of the stack. 
    top = endNode;
  }

  void push(Location loc) {
    // - Adds argument location loc to the top of the stack.
    top = new LocationStackNode(loc, top);
  }
  
  void pop() {
    // - No arguments. Checks to see if the stack is empty and removes a node if not.
    if(!top.getLocation().isEqual(stackEnd)){
      top = top.getNextNode();
    }
  }
  
  Location getTop() {
    // - No arguments. Returns the location at the top of the stack.
    return top.getLocation();
  }

  boolean isEmpty() {
    // - No arguments. Returns true if the stack is empty, and false otherwise.
    return (top.getLocation().isEqual(stackEnd));
  }
  
  boolean isOn(Location loc) {
    // - Iterates through the stack to see if provided location loc 
	//is on the stack, returning true if so.
    LocationStackNode currentLocation = top;
    while(currentLocation.getNextNode() != null){
      if(currentLocation.getLocation().isEqual(loc)){
        //It's this node!
        return true;
      }
      else{
        //Not this one.
        currentLocation = currentLocation.getNextNode();
      }
    }
    return false;
  }

  void streamOut(LocationStack s) {
    // - Streams out argument LocationStack s. Passes though the stack twice- 
	//once to reverse all the nodes, and once to re-reverse them and print 
	//them to the CLI. 
    LocationStack reverseStack = new LocationStack();
    Location next = null;
    while(!(s.isEmpty())){
      reverseStack.push(s.getTop());
      next = s.getTop();
      s.pop();
    }
    next = null;
    while(!(reverseStack.isEmpty())){
      s.push(reverseStack.getTop());
      next = reverseStack.getTop();
      next.streamOut();
      reverseStack.pop();
    }
  }
}

 /*LocationStackNode
  *
  * This class allows us to create the LocationStackNode object. 
  * Each node is a location and a pointer to the node "below"
  * it allowing for the computer to traverse the entire stack.
  */
 
class LocationStackNode {
  private LocationStackNode() { assert(false); }
  private LocationStackNode(LocationStackNode s) { assert(false); }

  private Location location;
  private LocationStackNode nextNode;

  LocationStackNode(Location loc, LocationStackNode next) {
    // - Only usable constructor for LocationStackNode. Takes arguments 
	//Location loc and LocationStackNode next and assigns them to the object as such.
    this.location = loc;
    this.nextNode = next;
  }

  Location getLocation() {
    // - No arguments. Returns the location that the LocationStackNode 
	//represents in the stack.
    return this.location;
  }
  
  LocationStackNode getNextNode() {
    // - No arguments. Returns the node "below" the current node.
    return this.nextNode;
  }
  
  void setNextNode(LocationStackNode next) {
    // - Allows us to manually set LocationStackNode next as the next node in the stack.
    this.nextNode = next;
  }
}
