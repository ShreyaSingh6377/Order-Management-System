package dao;
import util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Electronics;
import entity.Clothing;
import entity.Product;
import entity.User;
import exception.OrderNotFoundException;
import exception.UserNotFoundException;
import util.DBPropertyUtil;
import util.DBUtil;

public class OrderProcessor implements IOrderManagementRepository {

  
	
    @Override
    public void createProduct(User user, Product product) throws UserNotFoundException {
       // Connection con = DBUtil.getDBConn("jdbc:mysql://localhost:3306/CodingChallenge2?user=root&password=anshu@6377");
        String connString=DBPropertyUtil.getConnString("data.Properties");
        //System.out.println(connString);
		Connection con=DBUtil.getDBConn(connString);
		System.out.print(con);
        if (con != null) {
            try {
                // Check if admin user exists in database
                PreparedStatement pstmt = con.prepareStatement("SELECT * FROM users WHERE userId = ? AND role = 'Admin'");
                pstmt.setInt(1, user.getUserId());
                ResultSet rs = pstmt.executeQuery();
                if (!rs.next()) {
                    throw new UserNotFoundException("Admin user not found");
                }

             // Create product
                pstmt = con.prepareStatement("INSERT INTO products (productId, productName, description, price, quantityInstock, type) VALUES (?, ?, ?, ?, ?, ?)");
                pstmt.setInt(1, product.getProductId());
                pstmt.setString(2, product.getProductName());
                pstmt.setString(3, product.getDescription());
                pstmt.setDouble(4, product.getPrice());
                pstmt.setInt(5, product.getQuantityInStock());
                pstmt.setString(6, product.getType());
                pstmt.executeUpdate();
                
             // Retrieve the generated productId
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    product.setProductId(generatedKeys.getInt(1));
                }

                if (product instanceof Electronics) {
                    Electronics electronics = (Electronics) product;
                    pstmt = con.prepareStatement("INSERT INTO electronics (productId, brand, warranty_period) VALUES (?, ?, ?)");
                    pstmt.setInt(1, product.getProductId());
                    pstmt.setString(2, electronics.getBrand());
                    pstmt.setInt(3, electronics.getWarrantyPeriod());
                    pstmt.executeUpdate();
                } else if (product instanceof Clothing) {
                    Clothing clothing = (Clothing) product;
                    pstmt = con.prepareStatement("INSERT INTO clothing (productId, size, color) VALUES (?, ?, ?)");
                    pstmt.setInt(1, product.getProductId());
                    pstmt.setString(2, clothing.getSize());
                    pstmt.setString(3, clothing.getColor());
                    pstmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            throw new UserNotFoundException("Failed to connect to database");
        }
    }

    @Override
    public void createUser(User user) {
    	String connString=DBPropertyUtil.getConnString("data.Properties");
        //System.out.println(connString);
		Connection con=DBUtil.getDBConn(connString);
    	//Connection con = DBUtil.getDBConn("jdbc:mysql://localhost:3306/CodingChallenge2?user=root&password=anshu @6377");
        if (con != null) {
            try {
                PreparedStatement pstmt = con.prepareStatement("INSERT INTO users (userId, username, password, role) VALUES (?, ?, ?, ?)");
                pstmt.setInt(1, user.getUserId());
                pstmt.setString(2, user.getUsername());
                pstmt.setString(3, user.getPassword());
                pstmt.setString(4, user.getRole());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        //Connection con = DBUtil.getDBConn("jdbc:mysql://localhost:3306/CodingChallenge2?user=root&password=anshu@6377");
        String connString=DBPropertyUtil.getConnString("data.Properties");
        //System.out.println(connString);
		Connection con=DBUtil.getDBConn(connString);
        if (con != null) {
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM products");

                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("productId"));
                    product.setProductName(rs.getString("productName"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setQuantityInStock(rs.getInt("quantityInStock"));
                    product.setType(rs.getString("type"));

                    products.add(product);
                }

                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return products;
    }

    @Override
    public ArrayList<Product> getOrderByUser (User user) {
        ArrayList<Product> products = new ArrayList<>();
        //Connection con = DBUtil.getDBConn("jdbc:mysql://localhost:3306/CodingChallenge2?user=root&password=anshu@6377");
        String connString=DBPropertyUtil.getConnString("data.Properties");
       // System.out.println(connString);
		Connection con=DBUtil.getDBConn(connString);
        if (con != null) {
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT p.* FROM orders o " +
                        "JOIN products p ON o.productId = p.productId " +
                        "WHERE o.userId = " + user.getUserId());

                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("productId"));
                    product.setProductName(rs.getString("productName"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    product.setQuantityInStock(rs.getInt("quantityInStock"));
                    product.setType(rs.getString("type"));

                    products.add(product);
                }

                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return products;
    }
}