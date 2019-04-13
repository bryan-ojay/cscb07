package exceptions;

/**
 * Thrown when comparing 2 arrays and they are not of equal length.
 */
public class UnequalArrayException extends RuntimeException {

  private static final long serialVersionUID = -4466798114206399860L;
  
  /**
   * Instantiates the exception.
   */
  public UnequalArrayException() {
    super();
  }
  
  /**
   * Instantiates the exception with a given message.
   */
  public UnequalArrayException(String message) {
    super(message);
  }
  
}
