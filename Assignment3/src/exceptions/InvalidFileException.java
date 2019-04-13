package exceptions;

/**
 * Thrown when the contents of a file are invalid.
 */
public class InvalidFileException extends Exception {

  private static final long serialVersionUID = -7216550049133391890L;

  /**
   * Instantiates the exception.
   */
  public InvalidFileException() {
    super();
  }
  
  /**
   * Instantiates the exception with a given message.
   */
  public InvalidFileException(String message) {
    super(message);
  }
}
