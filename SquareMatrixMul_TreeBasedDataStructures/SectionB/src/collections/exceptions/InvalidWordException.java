package collections.exceptions;

public class InvalidWordException extends Exception {

static final long serialVersionUID = 1L; // To prevent Serializable warnings

  public InvalidWordException(String message) {
    super(message);
  }
}
