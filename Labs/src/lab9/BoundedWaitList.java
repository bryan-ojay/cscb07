package lab9;

import java.util.Collection;

/**
 * A representation on a waiting list, FIFO, that is bounded with a capacity
 * @author Muhammad & Bryan
 */
public class BoundedWaitList<E> extends WaitList<E> {
  private int capacity;
  
  public BoundedWaitList() {
    super();
    this.capacity = 10;
  }
  
  public BoundedWaitList(int limit){
    super();
    this.capacity = limit;
  }
  
  public BoundedWaitList(Collection<E> coln) {
    super(coln);
    this.capacity = coln.size();
  }
  
  /**
   * Returns the capacity of the WaitList    
   * @return The capacity of the WaitList
   */
  public int getCapacity() {
    return this.capacity;
  }
  
  /**
   * Adds the specified element to this WaitList if less than capacity
   * elements are in the WaitList
   * @param element the new element to be added to this WaitList
   */
  public void add(E element){
    if (this.content.size() < this.capacity) {
      super.add(element);
    }
  }
  
  @Override
  public String toString() {
    return "BoundedWaitList:" + this.content;
  }
}
