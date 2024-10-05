package main;

import java.util.ArrayList;
import java.util.Scanner;

import dao.OrderProcessor;
import entity.Clothing;
import entity.Electronics;
import entity.Product;
import entity.User;
import exception.UserNotFoundException;

public class MainModule{
	
	
public static void main(String[] args) 
{
        OrderProcessor orderProcessor = new OrderProcessor();
        Scanner scanner = new Scanner(System.in);
        //User user = null;
        //Product product = null;

        while (true) {
            System.out.println("Order Management System");
            System.out.println("1. CreateUser");
            System.out.println("2. CreateProduct");
            System.out.println("3. GetAllProducts");
            System.out.println("4. GetOrderByUser ");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
            case 1:
            	OrderProcessor orderProcessor2 = new OrderProcessor();
                

                System.out.print("Enter user id: ");
                int userId2 = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                System.out.print("Enter role (Admin/User): ");
                String role = scanner.nextLine();

                User  user2 = new User ();
                user2.setUserId(userId2);
                user2.setUsername(username);
                user2.setPassword(password);
                user2.setRole(role);

                orderProcessor.createUser(user2);
                System.out.println("User created successfully!");
                break;

                case 2:
                    
                	OrderProcessor orderProcessor1 = new OrderProcessor();
                    
                    System.out.print("Enter user id: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    System.out.print("Enter product id: ");
                    int productId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline left-over
                    System.out.print("Enter quantity in stock: ");
                    int quantityInStock = scanner.nextInt();
                    scanner.nextLine(); // Consume newline left-over
                    System.out.print("Enter type (Electronics/Clothing): ");
                    String type = scanner.nextLine();

                    Product product1 = null;
                    if (type.equals("Electronics")) {
                        product1 = new Electronics();
                        System.out.print("Enter brand: ");
                        String brand = scanner.nextLine();
                        System.out.print("Enter warranty period: ");
                        int warrantyPeriod = scanner.nextInt();
                        scanner.nextLine(); // Consume newline left-over
                        ((Electronics) product1).setBrand(brand);
                        ((Electronics) product1).setWarrantyPeriod(warrantyPeriod);
                    } else if (type.equals("Clothing")) {
                        product1 = new Clothing();
                        System.out.print("Enter size: ");
                        String size = scanner.nextLine();
                        System.out.print("Enter color: ");
                        String color = scanner.nextLine();
                        ((Clothing) product1).setSize(size);
                        ((Clothing) product1).setColor(color);
                    }

                    product1.setProductId(productId);
                    product1.setProductName(productName);
                    product1.setDescription(description);
                    product1.setPrice(price);
                    product1.setQuantityInStock(quantityInStock);
                    product1.setType(type);

                    User  user1 = new User (1,"Alex","passalex","Admin");
                    user1.setUserId(userId);
                    user1.setRole("Admin");

                    try {
                        orderProcessor.createProduct(user1, product1);
                        System.out.println("Product created successfully!");
                    } catch (UserNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                    case 3:
                	OrderProcessor productDAO = new OrderProcessor();
                    ArrayList<Product> products = productDAO.getAllProducts();

                    if (products != null && !products.isEmpty()) {
                        System.out.println("Products:");
                        for (Product product3 : products) {
                            System.out.println("Product ID: " + product3.getProductId());
                            System.out.println("Product Name: " + product3.getProductName());
                            System.out.println("Description: " + product3.getDescription());
                            System.out.println("Price: " + product3.getPrice());
                            System.out.println("Quantity in Stock: " + product3.getQuantityInStock());
                            System.out.println("Type: " + product3.getType());
                            System.out.println();
                        }
                    } else {
                        System.out.println("No products found.");
                    }
                    break;
                case 4:
                   
                	User user4 = new User();
                	System.out.println("Enter User Id:");
                	int id = scanner.nextInt();
                    user4.setUserId(id); // Replace with the actual user ID

                    OrderProcessor orderDAO = new OrderProcessor();
                    ArrayList<Product> products4 = orderDAO.getOrderByUser (user4);

                    if (products4 != null && !products4.isEmpty()) {
                        System.out.println("Products ordered by user " + user4.getUserId() + ":");
                        for (Product product4 : products4) {
                            System.out.println("Product ID: " + product4.getProductId());
                            System.out.println("Product Name: " + product4.getProductName());
                            System.out.println("Description: " + product4.getDescription());
                            System.out.println("Price: " + product4.getPrice());
                            System.out.println("Quantity in Stock: " + product4.getQuantityInStock());
                            System.out.println("Type: " + product4.getType());
                            System.out.println();
                        }
                    } else {
                        System.out.println("No products found for user " + user4.getUserId());
                    }
                    break;
                case 5:
                    System.out.println("Exiting the system...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    
    
    	
    }
        }
