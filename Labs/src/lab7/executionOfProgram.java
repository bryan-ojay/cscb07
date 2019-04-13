package lab7;

import java.util.Hashtable;


public class executionOfProgram {

  /**
   * @param args
   */
  public static void main(String[] args) {

    // TODO  create a HashTable and in that hash table insert in three items
    Hashtable filehashtable = new Hashtable(); 
    initializeHashTableWithFileExtension(filehashtable);

    //now imagine at this point you write code to get files off your HDD
    //and you have extracted the file extension i.e. pdf or doc or txt

    // TODO Hardcode your String string to "pdf"
    String fileExtension="pdf";

    try {
      // TODO Get the appropriate class that belongs to fileExtension. 
      String classType = (String) filehashtable.get(fileExtension);          

      // TODO Use the Class.forName method to get the class for the file extension
      // i.e. 'openPDF', or 'openWord' or 'openTXT'
      // Note: the class name must be preceded by the package name. 
      // Eg. packageName.className 
      Class<?> getClass = Class.forName("lab7."+classType);        

      try {
        // TODO create an instance of the which ever the Class is from above using newInstance 
        File instanceOfFileClass = (File) getClass.newInstance();
        instanceOfFileClass.printContentsOfFile();
        instanceOfFileClass.printDateOFfile();
        instanceOfFileClass.printOwnerOfFile();             

      } 
      catch (InstantiationException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } 
      catch (IllegalAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    } 
    catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  private static void initializeHashTableWithFileExtension(Hashtable filehashtable) {
    // TODO add three items to table
    filehashtable.put("pdf", "openPDF");
    filehashtable.put("doc", "openWord");
    filehashtable.put("txt", "openTXT");
  }

}
