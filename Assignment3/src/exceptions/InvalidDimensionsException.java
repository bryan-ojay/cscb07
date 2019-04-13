package exceptions;

/**
 * Thrown when invalid dimensions are given when instantiating a 
 * matrix.
 */
public class InvalidDimensionsException extends RuntimeException {
  private static final long serialVersionUID = 6885029726934255339L;
  
  /**
   * Instantiates the exception.
   */
  public InvalidDimensionsException() {
    super();
  }
  
  /**
   * Instantiates the exception with a given message.
   */
  public InvalidDimensionsException(String message) {
    super(message);
  }

 }
