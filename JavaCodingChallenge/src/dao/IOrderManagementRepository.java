package dao;
import exception.UserNotFoundException;
import exception.OrderNotFoundException;
import java.util.ArrayList;

import entity.Product;
import entity.User;
public interface IOrderManagementRepository {
    
    void createProduct(User user, Product product) throws UserNotFoundException;

    /**
     createUser(User user): create user and store in database for further development
     */
    void createUser(User user);

    /**
     * getAllProducts(): return all product list from the database.
     */
    ArrayList<Product> getAllProducts();

    /**
     * getOrderByUser(User user): return all product ordered by specific user from database.
     * 
     * 
     */
    ArrayList<Product> getOrderByUser (User user) throws UserNotFoundException;
}



