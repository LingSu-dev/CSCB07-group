package com.b07.store;

import java.awt.peer.ChoicePeer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.database.helper.DatabaseUpdateHelper;
import com.b07.exceptions.BadValueException;
import com.b07.exceptions.DataNotFoundException;
import com.b07.exceptions.DatabaseContainsInvalidDataException;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.InvalidUserParameterException;
import com.b07.exceptions.UserNotFoundException;
import com.b07.inventory.Inventory;
import com.b07.inventory.Item;
import com.b07.users.Customer;
import com.b07.users.Employee;
import com.b07.users.Roles;
import com.b07.users.User;
import com.b07.store.StoreHelpers;


public class SalesApplication {



  /**
   * employee interface
   * 
   * @param reader
   * @throws UserNotFoundException
   */
  private static void employee(BufferedReader reader) throws UserNotFoundException {

    // If the user entered 1
    /*
     * TODO Create a context menu for the Employee interface Prompt the employee for their id and
     * password Attempt to authenticate them. If the Id is not that of an employee or password is
     * incorrect, end the session If the Id is an employee, and the password is correct, create an
     * EmployeeInterface object then give them the following options: 1. authenticate new employee
     * 2. Make new User 3. Make new account (customer) 4. Make new Employee 5. Restock Inventory 6.
     * Exit
     * 
     * Continue to loop through as appropriate, ending once you get an exit code (9)
     */
    try {
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
      int input = StoreHelpers.choiceDialog(employeeOptions, reader);
      while (input != 6) {
        if (input == 1) {
          employee = (Employee) StoreHelpers.loginPrompt(reader, Roles.EMPLOYEE);
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
          try {
            employeeInterface.createCustomer(name, age, address, password);
          } catch (InvalidUserParameterException | BadValueException e) {
            System.out.println("One of the provided values is invalid.");
          } catch (DatabaseInsertException e) {
            e.printStackTrace();
          } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          } catch (DatabaseContainsInvalidDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
          try {
            employeeInterface.createEmployee(name, age, address, password);
          } catch (InvalidUserParameterException e) {
            System.out.println("One of the provided values is invalid.");
          } catch (DatabaseInsertException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          } catch (BadValueException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          } catch (DataNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          } catch (DatabaseContainsInvalidDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        } else if (input == 5) {

          try {
            System.out.println("Input an Item id");
            // String itemName = reader.readLine();
            int id = Integer.parseInt(reader.readLine());
            Item item = DatabaseSelectHelper.getItem(id);
            System.out.println("Input a quantity");
            int quantity = Integer.parseInt(reader.readLine());
            employeeInterface.restockInventory(item, quantity);
          } catch (NumberFormatException e) {
            System.out.println("please input a number");
          }
        }

        input = StoreHelpers.choiceDialog(employeeOptions, reader);
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SQLException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }


  }

  /**
   * customer interface
   * 
   * @param reader
   */
  private static void customer(BufferedReader reader) {
    // If the user entered 2
    /*
     * TODO create a context menu for the customer Shopping cart Prompt the customer for their id
     * and password Attempt to authenticate them If the authentication fails or they are not a
     * customer, repeat If they get authenticated and are a customer, give them this menu: 1. List
     * current items in cart 2. Add a quantity of an item to the cart 3. Check total price of items
     * in the cart 4. Remove a quantity of an item from the cart 5 . check out 6. Exit
     * 
     * When checking out, be sure to display the customers total, and ask them if they wish to
     * continue shopping for a new order
     * 
     * For each of these, loop through and continue prompting for the information needed Continue
     * showing the context menu, until the user gives a 6 as input.
     */

    try {
      System.out.print("Customer Login:");
      Customer customer = (Customer) StoreHelpers.loginPrompt(reader, Roles.CUSTOMER);
      if (customer == null) {
        return;
      }
      ShoppingCart shoppingCart = new ShoppingCart(customer);
     System.out.println("Welcome, customer");
     String[] customerOptions = {"1 - List items in cart",
     "2 - Add item to cart",
     "3 - Check price",
     "4 - Remove item from cart",
     "5 - Check out",
     "6 - Exit",
     "Enter Selection:"}
     int input = StoreHelpers.choicePrompt(customerOptions, reader);
      while (input != 6) {
        if (input == 1) {
          System.out.println(shoppingCart.getItems());
        } else if (input == 2) {
          System.out.println("Input a quantity");
          int quantity = Integer.parseInt(reader.readLine());
          System.out.println("Input an item id");
          int itemId = Integer.parseInt(reader.readLine());
          shoppingCart.addItem(DatabaseSelectHelper.getItem(itemId), quantity);
        } else if (input == 3) {
          System.out.println(shoppingCart.getTotal());
        } else if (input == 4) {
          System.out.println("Input a quantity");
          int quantity = Integer.parseInt(reader.readLine());
          System.out.println("Input an item id");
          int itemId = Integer.parseInt(reader.readLine());
          shoppingCart.removeItem(DatabaseSelectHelper.getItem(itemId), quantity);
        } else if (input == 5) {
          if (shoppingCart.checkOut()) {
            System.out.println("Success");
          }else {
            System.out.println("an error occurred");
          }
        } else if (input == 6) {
          return;
        } else if (input == -1) {
          System.out.println("Please make a valid choice");
        }
        input = StoreHelpers.choicePrompt(customerOptions, reader);

      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SQLException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    } catch (DatabaseInsertException e) {
      System.out.println("There was an error inserting into the database");
      e.printStackTrace();
    } catch (ConnectionFailedException e) {
       System.out.println("Failed to connect");
      e.printStackTrace();
    } catch (NotAuthenticatedException e) {
      System.out.println("User not authenticated");
      e.printStackTrace();
    }

  }

  /**
   * This is the main method to run your entire program! Follow the "Pulling it together"
   * instructions to finish this off.
   * 
   * @param argv unused.
   */
  public static void main(String[] argv) {

    Connection connection = DatabaseDriverExtender.connectOrCreateDataBase();
    if (connection == null) {
      System.out.print("NOOO");
    }
    try {
      // TODO Check what is in argv
      // If it is -1
      /*
       * TODO This is for the first run only! Add this code:
       * DatabaseDriverExtender.initialize(connection); Then add code to create your first account,
       * an administrator with a password Once this is done, create an employee account as well.
       * 
       */
      if (argv.length > 0 && argv[0].equals("-1")) {
        System.out.println("admin mode!");
        try {
          DatabaseDriverExtender.initialize(connection);
        } catch (Exception e) {
          System.out.println("error connecting to the database");
          e.printStackTrace();
        }
        int adminId = DatabaseInsertHelper.insertNewUser("Admin", 100, "address of admin", "admin");
        int employeeId =
            DatabaseInsertHelper.insertNewUser("Employee Name", 10, "employee address", "hunter2");
        try {
          int adminRoleId = DatabaseInsertHelper.insertRole("ADMIN");
          int employeeRoleId = DatabaseInsertHelper.insertRole("EMPLOYEE");
          DatabaseInsertHelper.insertUserRole(adminId, adminRoleId);
          DatabaseInsertHelper.insertUserRole(employeeId, employeeRoleId);
        } catch (IndexOutOfBoundsException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
      // If it is 1
      /*
       * TODO In admin mode, the user must first login with a valid admin account This will allow
       * the user to promote employees to admins. Currently, this is all an admin can do.
       */
      if (argv.length > 0 && argv[0].equals("1")) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        User admin = StoreHelpers.loginPrompt(reader, Roles.ADMIN);
        System.out.println("Enter an employee id to promote to admin");
        int id = Integer.parseInt(reader.readLine());
        DatabaseUpdateHelper.updateUserRole(DatabaseSelectHelper.getUserRoleId(admin.getId()), id);
      }
      // If anything else - including nothing
      /*
       * TODO Create a context menu, where the user is prompted with: 1 - Employee Login 2 -
       * Customer Login 0 - Exit Enter Selection:
       */
      else {
        String[] loginOptions =
            {"1 - Employee Login", "2 - Customer Login", "0 - Exit", "Enter Selection:"};
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // input will contain the first line of input from the user
        int input = StoreHelpers.choicePrompt(loginOptions, bufferedReader);
        while (input != 0) {
          if (input == 1) {
            employee(bufferedReader);
          } else if (input == 2) {
            customer(bufferedReader);
          } else {
            System.out.println("Invalid selection");
          }
          input = StoreHelpers.choicePrompt(loginOptions, bufferedReader);
        }
      }
      // If the user entered 0
      /*
       * TODO Exit condition
       */
      // If the user entered anything else:
      /*
       * TODO Re-prompt the user
       */


    } catch (Exception e) {
      // TODO Improve this!
      System.out.println("Something went wrong :(");
      e.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (Exception e) {
        System.out.println("Looks like it was closed already :)");
      }
    }

  }

}
