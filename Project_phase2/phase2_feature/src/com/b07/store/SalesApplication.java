package com.b07.store;

import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.exceptions.ConnectionFailedException;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.NotAuthenticatedException;
import com.b07.inventory.Inventory;
import com.b07.inventory.Item;
import com.b07.users.Admin;
import com.b07.users.Customer;
import com.b07.users.Employee;
import com.b07.users.Roles;
import com.b07.users.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class SalesApplication {
  /**
   * This is the main method to run your entire program! Follow the "Pulling it together"
   * instructions to finish this off.
   * 
   * @param argv the mode the user would like to enter.
   */
  public static void main(String[] argv) {

    Connection connection = DatabaseDriverExtender.connectOrCreateDataBase();
    if (connection == null) {
      System.out.print("Error establishing initial database connection");
    }
    try {
      String selection = "0";
      if (argv.length != 0) {
        selection = argv[0];
      }

      if (selection.equals("-1")) {
        // Setup mode
        firstTimeSetup(connection);

      } else if (selection.equals("1")) {
        // Admin mode
        adminMode(new BufferedReader(new InputStreamReader(System.in)));

      } else {

        // Customer/Employee mode
        System.out.println("Welcome to Sales Application");
        System.out.println("----------------------------");
        String[] loginOptions =
            {"1 - Employee Login", "2 - Customer Login", "0 - Exit", "Enter Selection:"};
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // input will contain the first line of input from the user
        int input = StoreHelpers.choicePrompt(loginOptions, reader);
        while (input != 0) {
          if (input == 1) {
            employeeMode(reader);
          } else if (input == 2) {
            customerMode(reader);
          } else if (input == 0) {
            System.out.println("Exiting");
          } else {
            System.out.println("Invalid selection");
          }
          input = StoreHelpers.choicePrompt(loginOptions, reader);
        }

      }
    } catch (SQLException e) {
      System.out.println("An issue occured while communicating with the database");
      System.out.println("The program will now exit.");
    } catch (DatabaseInsertException e) {
      System.out.println("An unknown issue occured while modifying the database");
      System.out.println("The program will now exit.");
    } catch (IOException e) {
      System.out.println("An unknown issue occured while obtaining user input");
      System.out.println("The program will now exit.");
    } catch (ConnectionFailedException e) {
      System.out.println("An issue occured while creating the database");
      System.out.println("The program will now exit.");
    } finally {
      try {
        connection.close();
      } catch (Exception e) {
        System.out.println("Looks like it was closed already :)");
      }
    }
  }

  /**
   * Exit the program and close the database, worst case scenario.
   * 
   * @param connection the connection to be closed.
   */
  public static void exitOnFailure(Connection connection) {
    try {
      connection.close();
    } catch (SQLException e) {
      System.out.println("Database was already closed");
      System.exit(0);
    }
  }

  /**
   * Initialize the database.
   * 
   * @param connection the connection to the database.
   * @throws DatabaseInsertException if there is an issue inserting into the database.
   * @throws SQLException if there is an issue communicating with the database.
   * @throws ConnectionFailedException if there is an issue connecting to the database.
   * @throws IOException if there is an issue obtaining user input.
   */
  public static void firstTimeSetup(Connection connection)
      throws DatabaseInsertException, SQLException, ConnectionFailedException, IOException {

    System.out.println("First run setup");
    System.out.println("---------------");
    DatabaseDriverExtender.initialize(connection);
    // Creating roles

    DatabaseInsertHelper.insertRole("ADMIN");
    DatabaseInsertHelper.insertRole("CUSTOMER");
    DatabaseInsertHelper.insertRole("EMPLOYEE");


    System.out.println("Creating administrator account");

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    boolean exit = false;
    int id = 0;
    while (!exit) {

      // getting administrator's data
      System.out.println("Enter administrator name:");
      String name = bufferedReader.readLine();
      System.out.println("Enter administrator age:");
      String age = bufferedReader.readLine();
      System.out.println("Enter administrator address:");
      String address = bufferedReader.readLine();
      System.out.println("Enter administrator password:");
      String password = bufferedReader.readLine();

      // Creating database admin
      try {
        int ageInt = Integer.parseInt(age);
        id = DatabaseInsertHelper.insertNewUser(name, ageInt, address, password);
        exit = true;
        System.out.println("Created new Admin, ID: " + id);

      } catch (NumberFormatException e) {
        System.out.println("Invalid input:");
        System.out.println("A valid number must be entered for age");
      }

    }

    int adminRoleId = DatabaseSelectHelper.getRoleIdByName("ADMIN");
    DatabaseInsertHelper.insertUserRole(id, adminRoleId);

    System.out.println("Creating employee account");

    exit = false;
    id = 0;

    while (!exit) {
      // getting employee data
      System.out.println("Enter employee name:");
      String name = bufferedReader.readLine();
      System.out.println("Enter employee age:");
      String age = bufferedReader.readLine();
      System.out.println("Enter employee address:");
      String address = bufferedReader.readLine();
      System.out.println("Enter employee password:");
      String password = bufferedReader.readLine();

      // creating first employee
      try {
        int ageInt = Integer.parseInt(age);
        id = DatabaseInsertHelper.insertNewUser(name, ageInt, address, password);
        exit = true;
        System.out.println("Created new Empolyee, ID: " + id);

      } catch (NumberFormatException e) {
        System.out.println("Invalid input:");
        System.out.println("A valid number must be entered for age");
      }

    }

    int employeeRoleId = DatabaseSelectHelper.getRoleIdByName("EMPLOYEE");
    DatabaseInsertHelper.insertUserRole(id, employeeRoleId);


    // Adding required items to database
    // Creating dummy stock
    //
    // int itemId;
    // itemId = DatabaseInsertHelper.insertItem("FISHING_ROD", new BigDecimal("10.00"));
    // DatabaseInsertHelper.insertInventory(itemId, 100);
    // itemId = DatabaseInsertHelper.insertItem("HOCKEY_STICK", new BigDecimal("10.00"));
    // DatabaseInsertHelper.insertInventory(itemId, 100);
    // itemId = DatabaseInsertHelper.insertItem("SKATES", new BigDecimal("10.00"));
    // DatabaseInsertHelper.insertInventory(itemId, 100);
    // itemId = DatabaseInsertHelper.insertItem("RUNNING_SHOES", new BigDecimal("10.00"));
    // DatabaseInsertHelper.insertInventory(itemId, 100);
    // itemId = DatabaseInsertHelper.insertItem("PROTEIN_BAR", new BigDecimal("10.00"));
    // DatabaseInsertHelper.insertInventory(itemId, 100);
    // unnecessary because this is not expected default behaviour


    System.out.println("Database creation was successfull!");

  }

  /**
   * Allow administrators to promote users to admin status.
   * 
   * @throws SQLException if there is an issue communicating with the database.
   * @throws IOException if there is an issue obtaining user input.
   */
  public static void adminMode(BufferedReader bufferedReader) throws SQLException, IOException {
    boolean exit = false;
    String input = "";
    Admin admin = null;

    System.out.println("Admin Mode: Log in");
    System.out.println("---------------");

    // Obtain a valid admin ID
    while (!exit) {
      System.out.println("Enter admin ID");
      String adminId = bufferedReader.readLine();
      try {
        int adminIdInt = Integer.parseInt(adminId);

        User user = DatabaseSelectHelper.getUserDetails(adminIdInt);
        if (user instanceof Admin) {
          admin = (Admin) user;
          exit = true;
        } else {
          System.out.println("Not an admin!");
        }
      } catch (NumberFormatException e) {
        System.out.println("A valid number must be entered");
      }
    }

    // Get password in order to authenticate admin
    exit = false;
    while (!exit) {
      System.out.println("Enter password");
      String password = bufferedReader.readLine();
      if (admin.authenticate(password) == true) {
        exit = true;
      } else {
        System.out.println("Invalid password!");
      }
    }

    System.out.println("Admin validated!");

    // Allow admin to promote employees
    exit = false;
    while (!exit) {
      System.out.println("Type '1' to promote an employee to admin");
      System.out.println("Type anything else to exit");
      input = bufferedReader.readLine();
      if (input.equals("1")) {
        System.out.println("Enter the id of the user you would like to promote:");
        input = bufferedReader.readLine();

        int employeeId = Integer.parseInt(input);
        User toPromote = DatabaseSelectHelper.getUserDetails(employeeId);
        if (toPromote instanceof Employee) {
          admin.promoteEmployee((Employee) toPromote);
          System.out.println("Employee promoted succesfully!");
        } else {
          System.out.println("Not an employee!");
        }

      } else {
        exit = true;
      }
    }
  }

  /**
   * Allows employees to perform operations on the database.
   * 
   * @throws IOException if there is an issue obtaining user input.
   * @throws SQLException if there is an issue communicating with the database.
   * @throws NotAuthenticatedException if the employee cannot be authenticated.
   * @throws DatabaseInsertException if there is an issue inserting into the database.
   */
  public static void employeeMode(BufferedReader reader)
      throws IOException, SQLException, DatabaseInsertException {

    System.out.print("Employee Login:");
    Employee employee = (Employee) StoreHelpers.loginPrompt(reader, Roles.EMPLOYEE);
    if (employee == null) {
      return;
    }
    Inventory inventory = DatabaseSelectHelper.getInventory();
    EmployeeInterface employeeInterface = new EmployeeInterface(employee, inventory);
    System.out.println("Welcome, employee");

    String[] employeeOptions =
        {"1 - authenticate new employee", "2 - Make new User", "3 - Make new account",
            "4 - Make new Employee", "5 - Restock Inventory", "6 - Exit", "Enter Selection:"};
    int input = StoreHelpers.choicePrompt(employeeOptions, reader);
    while (input != 6) {
      if (input == 1) {
        employee = (Employee) StoreHelpers.loginPrompt(reader, Roles.EMPLOYEE);
        if (employee != null) {
          employeeInterface.setCurrentEmployee(employee);
        } else {
          System.out.println("Failed to authenticate new employee");
        }
        
      } else if (input == 2 || input == 3) {
        System.out.println("Creating a new customer");
        System.out.println("Input a name");
        String name = reader.readLine();
        System.out.println("Input an age");
        int age = Integer.parseInt(reader.readLine());
        System.out.println("Input an address");
        String address = reader.readLine();
        System.out.println("Input a password");
        String password = reader.readLine();
        insertUser: try {
          int userId = employeeInterface.createEmployee(name, age, address, password);
          if (userId == -1) {
            break insertUser;
          }
          int roleId = DatabaseSelectHelper.getRoleIdByName("CUSTOMER");
          if (userId == -1) { 
            //should never run: included here due to UML-unstable EmployeeInterface change
            System.out.println("Unable to retrieve the role ID.");
            break insertUser;
          }
          DatabaseInsertHelper.insertUserRole(userId, roleId);
        } catch (DatabaseInsertException e) {
          System.out.println("Unable to create an employee with the given parameters.");
        }

      } else if (input == 4) {
        System.out.println("Creating a new Employee");
        System.out.println("Input a name");
        String name = reader.readLine();
        System.out.println("Input an age");
        int age = Integer.parseInt(reader.readLine());
        System.out.println("Input an address");
        String address = reader.readLine();
        System.out.println("Input a password");
        String password = reader.readLine();
        insertUser: try {
          int userId = employeeInterface.createEmployee(name, age, address, password);
          if (userId == -1) {
          //should never run: included here due to UML-unstable EmployeeInterface change
            break insertUser;
          }
          int roleId = DatabaseSelectHelper.getRoleIdByName("EMPLOYEE");
          if (userId == -1) {
            System.out.println("Unable to retrieve the role ID.");
            break insertUser;
          }
          DatabaseInsertHelper.insertUserRole(userId, roleId);
        } catch (DatabaseInsertException e) {
          System.out.println("Unable to create an employee with the given parameters.");
        }
      } else if (input == 5) {

        try {
          System.out.println("Input the ID of the item to restock");
          int id = Integer.parseInt(reader.readLine());
          Item item = DatabaseSelectHelper.getItem(id);
          System.out.println("Input a quantity of the item to restock");
          int quantity = Integer.parseInt(reader.readLine());
          employeeInterface.restockInventory(item, quantity);
        } catch (NumberFormatException e) {
          System.out.println("Please input a number");
        }
      }
      input = StoreHelpers.choicePrompt(employeeOptions, reader);
    }
  }


  /**
   * Allows customers to use a cart and make purchases.
   * 
   * @throws IOException if there is an issue obtaining user input.
   * @throws SQLException if there is an issue communicating with the database.
   */
  public static void customerMode(BufferedReader bufferedReader) throws IOException, SQLException {
    Customer customer = null;
    ShoppingCart cart = null;

    // Allow user to log in
    boolean exit = false;
    while (!exit) {
      System.out.println("Enter customer ID");
      String customerId = bufferedReader.readLine();
      System.out.println("Enter password");
      String password = bufferedReader.readLine();

      // Validate and authenticate user
      try {
        int customerIdInt = Integer.parseInt(customerId);
        User user = DatabaseSelectHelper.getUserDetails(customerIdInt);
        if (user instanceof Customer) {
          customer = (Customer) user;
        } else {
          System.out.println("Not a customer!");
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid ID, must be a number!");
      }
      if (customer != null && customer.authenticate(password)) {

        try {
          cart = new ShoppingCart(customer);
          exit = true;
        } catch (NotAuthenticatedException e) {
          System.out.println("Authentication failed, try again!");
        }
      } else {
        System.out.println("Authentication failed, try again!");
      }
    }

    // Allow user to interact with cart, database
    exit = false;
    while (!exit) {
      System.out.println("Welcome to the customer interface");
      System.out.println("---------------------------------");
      System.out.println("What would you like to do?");
      System.out.println("1. List current items in cart");
      System.out.println("2. Add a quantity of an item to the cart");
      System.out.println("3. Check total price of items in the cart");
      System.out.println("4. Remove a quantity of an item from the cart");
      System.out.println("5. check out ");
      System.out.println("6. Exit");

      String input = bufferedReader.readLine();
      if (input.equals("1")) {
        // List items in cart
        System.out.println("Current Cart:");
        List<Item> items = cart.getItems();
        for (int i = 0; i < items.size(); i++) {
          System.out.println(items.get(i).getName());
        }
      } else if (input.equals("2")) {
        // Allow user to add items to cart
        List<Item> items = DatabaseSelectHelper.getAllItems();
        System.out.println("The following are the current items and their " + "IDs");
        for (int i = 0; i < items.size(); i++) {
          System.out.println(items.get(i).getName());
          System.out.println("ID: " + items.get(i).getId());
        }
        System.out.println("Enter the ID of the item you would like to add");
        String toStock = bufferedReader.readLine();
        System.out.println("Enter the qauntity of the item you would like to add");
        String quantity = bufferedReader.readLine();
        try {
          int itemId = Integer.parseInt(toStock);
          int quantityInt = Integer.parseInt(quantity);
          Item item = DatabaseSelectHelper.getItem(itemId);
          cart.addItem(item, quantityInt);
          System.out.println("Successfully added!");
        } catch (NumberFormatException e) {
          System.out.println("Must enter a valid number!");
        }
      } else if (input.equals("3")) {
        // Display cart total price
        System.out.println("Your total is:");
        System.out.println(cart.getTotal());
      } else if (input.equals("4")) {
        // Allow users to remove items from cart
        List<Item> items = DatabaseSelectHelper.getAllItems();
        System.out.println("The following are the current items and their " + "IDs");
        for (int i = 0; i < items.size(); i++) {
          System.out.println(items.get(i).getName());
          System.out.println("ID: " + items.get(i).getId());
        }
        System.out.println("Enter the ID of the item you would like to remove");
        String toStock = bufferedReader.readLine();
        System.out.println("Enter the qauntity of the item you would like to remove");
        String quantity = bufferedReader.readLine();
        try {
          int itemId = Integer.parseInt(toStock);
          int quantityInt = Integer.parseInt(quantity);
          Item item = DatabaseSelectHelper.getItem(itemId);
          cart.removeItem(item, quantityInt);
          System.out.println("Successfully removed!");
        } catch (NumberFormatException e) {
          System.out.println("Must enter a valid number!");
        }
      } else if (input.equals("5")) {
        // Allow user to check out
        System.out.println("Your total is:");
        System.out.println("$" + cart.getTotal());
        System.out.println("You will also pay taxes to the order of:");
        System.out.println("$" + cart.getTotal().multiply(cart.getTaxRate()));
        boolean checkedOut = cart.checkOutCart();
        if (checkedOut) {
          // Prompt user to continue shopping
          System.out.println("Your order has been checked out!");
          System.out.println("Enter 1 if you would like to continue shopping");
          System.out.println("Enter any other value if you would like to exit");
          input = bufferedReader.readLine();
          if (!input.equals("1")) {
            exit = true;
            cart.clearCart();
          }
        } else {
          System.out.println("Sorry, your cart could not be checked out at this time");
        }
      } else if (input.equals("6")) {
        // exit
        exit = true;
      }
    }
  }
}
