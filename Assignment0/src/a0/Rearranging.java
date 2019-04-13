package a0;

public class Rearranging {

  public static void rearranging(int[] items)
  {
    /* create markers for the left-most and right-most unchecked values
     * in 'items'
     */
    int arrayLeft = 0;
    int arrayRight = items.length - 1;

    //scan through 'items' linearly
    for (int curr = 0; curr <= arrayRight; curr++) {

      /* if the current int is negative and is the left-most unsearched
       * element, increment the "left-most unchecked value" marker
       */
      if (items[curr] < 0 && arrayLeft == curr) arrayLeft++;

      /* if the current int is negative and is not the left-most 
       * unsearched element:
       * - swap it with the first element in 'items'
       * - increment the "left-most unchecked value" marker
       * - keep the index search at the same position
       */
      else if (items[curr] < 0 && arrayLeft != curr) {
        swap(curr, arrayLeft, items);
        arrayLeft++;
        curr--;
      }

      // if the current int is 0, ignore it
      else if (items[curr] == 0) continue;

      /* if the current int is positive and is not the right-most 
       * unsearched element:
       * - swap it with the last element in 'items'
       * - decrement the "right-most unchecked value" marker
       * - keep the index search at the same position
       */
      else if (items[curr] > 0 && arrayRight != curr){
        swap(curr, arrayRight, items);
        arrayRight--;
        curr--;
      }

      /* if the current int is positive and is the right-most unsearched 
       * element, decrement the "right-most unchecked value" marker
       */
      else if (items[curr] > 0 && arrayRight == curr) arrayRight--;
    }
  }

  private static void swap(int i,int j,int[] items)
  {
    //swap items[i] and items[j]
    int temp = items[i];
    items[i] = items[j];
    items[j] = temp;
  }

  public static void main(String[] args) {

    int[] items={9,0,0,0,-1,2,-3,0,0,0,4,-5,6,0,0,0,-1};
    /* printing the values in the items before 
     * calling the method rearranging
     */
    System.out.print("Before: ");
    for(int item:items)
    {
      System.out.print(item + " ");
    }

    //calling the rearranging method
    rearranging(items);
    /*
     * printing the values in the items after 
     * calling the method rearranging
     */

    System.out.print("\nAfter: ");
    for(int item:items)
    {
      System.out.print(item + " ");
    }
  }
}