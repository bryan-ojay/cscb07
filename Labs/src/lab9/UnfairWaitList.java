package lab9;

public class UnfairWaitList<E> extends WaitList<E> {
  
  /**
   * Sends the specified element to the end of the WaitList
   * @param element The element to add to the end of the WaitList
   */
  public void moveToBack(E element) {
    //Create a variable for the element we want to move to the back
    E removedElement = null;
    
    //loop through the WaitList
    int size = content.size();
    for (int i = 0; i < size; i++){
      //get the element at the start of the list
      E thisElement = content.poll();
      /* if the element is equal to the element we want, don't add it back to
       * the waitlist yet, set it as the removedElement variable*/
      if (thisElement == element) {
        removedElement = thisElement;
      }
      // else, add it back to the waitlist
      else {
        content.add(thisElement);
      }
    }
    
    //if removedElement is defined, add it to the end of the waitlist
    if (removedElement != null) {
      content.add(removedElement);
    }
  }
  
  /**
   * Removes the first instance of the specified element from the WaitList.
   * @param element The element to add to the end of the WaitList
   */
  public void remove(E element) {
    //add a variable to check if the variable has been removed
    boolean removed = false;
    //loop through the WaitList
    int size = content.size();
    for (int i = 0; i < size; i++){
      //get the element at the start of the list
      E thisElement = content.poll();
      
      /* if the element is not equal to the element we want, add it back to the
       * WaitList */
      if (thisElement.equals(element) && !removed) {
        removed = true;
      }
      else {
        content.add(thisElement);
      }
    }
  }
  
  @Override
  public String toString() {
    return "UnfairWaitList:" + this.content;
  }
  
}
