package a2;

import java.util.*;

public class TestCommand extends Command {

  public String execute(String paths) {
    String[] pathSplit = paths.split(" ");
    if (pathSplit.length != 2) {
      System.out.println("The syntax of the command is incorrect.");
    }

    else {
      try {
        Object[] oldObj = verifyPath(pathSplit[0]);
        Object[] newObj = verifyPath(pathSplit[1]);
        checkObjects(oldObj, newObj);
      }
      catch(Exception exception) {
        System.out.println("One of the directories is invalid.");
      }
    }
    return null;
  }

  private Object[] verifyPath(String path) throws Exception {
    path = path.replaceFirst("/$", "");
    int dirIndex = path.lastIndexOf('/') + 1;
    String targetDir = path.substring(0, dirIndex);
    String dirName = path.substring(dirIndex);

    Object obj;
    String fullPath;
    Directory parentDir = findPath(targetDir);
    try {
      obj = findPath(path);
    }
    catch(Exception exception) {
      obj = parentDir.getContents().get(dirName);
      if (obj == null) {
        throw new Exception("The object doesn't exist.");
      }
    }
    
    if (dirName.equals("")) {
      fullPath = "";
    }
    else {
      fullPath = parentDir.getPath() + dirName;
    }
    Object[] objDetails = {obj, fullPath};
    return objDetails;
  }

  private void checkObjects(Object[] oldObj, Object[] newObj) {
    
    String oldPath = (String) oldObj[1] + "/";
    String newPath = (String) newObj[1] + "/";
    
    if (newPath.startsWith(oldPath)) {
      System.out.println("You cannot copy a directory to its subdirectory.");
    }

    else {
      String oldType = oldObj[0].getClass().getSimpleName();
      String newType = newObj[0].getClass().getSimpleName();

      if (oldType.equals("Directory") && newType.equals("File")) {
        System.out.println("Cannot copy a directory to a file.");
      }
      else if (oldType.equals("File") && newType.equals("File")) {
        ((File) newObj[0]).setContents(((File) oldObj[0]).getContents());
      }
      else if (oldType.equals("File") && newType.equals("Directory")) {
        ((Directory) newObj[0]).createFile(oldObj[0].toString(), 
            ((File) oldObj[0]).getContents());
      }
      else if (oldType.equals("Directory") && newType.equals("Directory")) {
        String dirName = oldObj[0].toString().substring(1);
        ((Directory) newObj[0]).putIn(dirName);
        copyData((Directory) oldObj[0], 
            ((Directory) newObj[0]).getDir(dirName));
      }
    }
  }

  private void copyData(Directory oldDir, Directory newDir) {
    Hashtable<String, Object> contents = oldDir.getContents();

    for (String dir:contents.keySet()) {

      Object oldChild = contents.get(dir);
      String dataType = oldChild.getClass().getSimpleName();

      if (dataType.equals("File")) {
        newDir.createFile(dir, ((File) oldChild).getContents());
      }
      
      else if (dataType.equals("Directory")) {
        ((Directory) newDir).putIn(dir);
        Directory newChild = ((Directory) newDir).getDir(dir);
        copyData((Directory) oldChild, newChild);
      }
    }
  }
}
