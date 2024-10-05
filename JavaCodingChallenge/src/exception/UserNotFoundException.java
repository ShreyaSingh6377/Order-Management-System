package exception;

//Exception classes
public class UserNotFoundException extends Exception {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public UserNotFoundException(String message) {
	 System.out.print("User ID not found");
 }
}

