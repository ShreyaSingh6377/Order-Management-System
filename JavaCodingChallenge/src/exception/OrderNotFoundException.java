package exception;

public class OrderNotFoundException extends Exception {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(String message) {
    	System.out.print("Order ID not found");
        
    }
}