package exceptions;

/**
 * Thrown when access to an invalid index in a matrix is attempted. 
 */
public class OutOfBoundsException extends RuntimeException {
  private static final long serialVersionUID = -3854452415157499263L;
  
  /**
   * Instantiates the exception.
   */
  public OutOfBoundsException() {
    super();
  }
  
  /**
   * Instantiates the exception with a given message.
   */
  public OutOfBoundsException(String message) {
    super(message);
  }
}
