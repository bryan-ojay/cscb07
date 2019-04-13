package a1;
import java.util.ArrayList;
import java.util.LinkedList;
// **********************************************************
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************

/*
 * the provided starter code may contain warnings of raw type. 
 * This is OK, because we have'nt yet learned generics. After having
 * learnt generics, we will revisit this code, and remove the warnings out. 
 */
public class BinaryTree {

  private Node root;	

  /* DO NOT MODIFY THIS METHOD
   * WE HAVE ALREADY COMPLETED THIS METHOD
   * FOR YOU.
   * adds data inside a binary
   * tree level by level starting
   * from left to right. 
   */
  public void addData(int d)
  {
    Node toAdd=new Node(d);
    if (root == null) {root = toAdd;}
    else {
      LinkedList ll=new LinkedList();
      ll.add(root);
      
      while(!(ll.isEmpty())) {
        Node currentNode=(Node)ll.poll();
        if (currentNode.getLeftNode()==null) {
          currentNode.setLeftNode(toAdd);
          break;
        }
        else if (currentNode.getRightNode()==null) {
          currentNode.setRightNode(toAdd);
          break;
        }
        else {
          /*
           * remember, the queue is FIFO, and due to this
           * we add first the left node followed by the right
           * node.
           */
          ll.add(currentNode.getLeftNode());
          ll.add(currentNode.getRightNode());
        }
      }
    }
  }

  /**
   * Returns a textual representation of the binary tree, with all the ints of
   * the binary tree are outputted in order of breadth-first search. 
   * @see java.lang.Object#toString()
   */
  public String toString() {  
    //create a linked list to traverse the nodes using breadth-first search
    LinkedList ll=new LinkedList();
    //add the root to the linkedlist if the tree is nonempty
    if (root != null) ll.add(root);

    String btString = ""; //create empty string to store the node values

    //run a while loop until there are no more nodes to search through
    while(!(ll.isEmpty())) {

      /* 
       * get and remove the head node in the LinkedList
       * then add that node's data to the string
       */
      Node thisNode = (Node)ll.poll();
      btString += thisNode.toString() + " ";

      /*
       * add the left node of the head node to the linked list, if it exists
       * then add the right node of the head node, if it exists
       */
      if (thisNode.getLeftNode()!=null) {
        ll.add(thisNode.getLeftNode());
      }
      if (thisNode.getRightNode()!=null) {
        ll.add(thisNode.getRightNode());
      }
    }
    return btString.trim();
  }

  
  /**
   * Returns an ArrayList that contains the data of all Nodes in the BinaryTree
   * with in-order traversal.
   */
  public ArrayList toList()
  {
    ArrayList btList = new ArrayList();
    //call the addSubTree helper function if tree is nonempty
    if (root != null) addSubTree(root, btList); 
    return btList; //return the list with the populated values
  }

  /**
   * Uses recursion to populate the ArrayList in toList using inOrder traversal 
   * of the tree.
   * @param temp The 'root' or head node of the subtree
   * @param values The ArrayList to populate
   */
  private void addSubTree(Node temp, ArrayList values) {
    
    //call the function on the left subtree
    if (temp.getLeftNode() != null) {addSubTree(temp.getLeftNode(), values);}
    
    /* 
     * once all the nodes in the left subtree have been traversed, add the 
     * root node to the list
     */
    values.add(temp.getData());
    
    //call the function on the right subtree
    if (temp.getRightNode() != null) {addSubTree(temp.getRightNode(), values);}
    
  }

  /*
   * You can modify the main function in any way you like
   * we will not mark your main function. We have provided some
   * sample code inside the main function that may make it easier 
   * for you to debug your code and know what the expected output 
   * is.  
   */
  public static void main(String[] args)
  {
    BinaryTree bt = new BinaryTree();
    /*
     * adding the following ints in a binary tree. Remember, the addData
     * adds the ints level by level and from left to right. I will first 
     * ask you to run the starter code and debug out the addData so that 
     * you are familiar with how it works and trace the creation of the 
     * tree using pen and paper. 
     */
    bt.addData(1);
    bt.addData(2);
    bt.addData(3);
    bt.addData(4);
    bt.addData(5);
    bt.addData(6);
    bt.addData(7);


    System.out.println(bt); //must print 1 2 3 4 5 6 7 


    // testing output of toList()
    for (Object d:bt.toList()) {
    	System.out.println((int)d);
    }
    /*
     * the above loop will print the following:
     *     4
		   2
		   5
           1
           6
           3
           7
     */
  }
}
