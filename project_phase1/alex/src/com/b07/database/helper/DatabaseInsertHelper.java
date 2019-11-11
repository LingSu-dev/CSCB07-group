package com.b07.database.helper;

import com.b07.database.DatabaseInserter;
import com.b07.database.DatabaseSelector;
import com.b07.database.helper.DatabaseDriverHelper;
import com.b07.exceptions.BadValueException;
import com.b07.exceptions.DataNotFoundException;
import com.b07.exceptions.DatabaseContainsInvalidDataException;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.inventory.Item;
import com.b07.security.PasswordHelpers;
import com.b07.users.*;

import java.util.List;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;


public class DatabaseInsertHelper extends DatabaseInserter {

  private static int roleCounter = 0;
  private static int userCounter = 0;

  /**
   * @param name
   * @return
   * @throws BadValueException
   * @throws DatabaseInsertException
   * @throws SQLException
   */
  public static int insertRole(String name)
      throws BadValueException, DatabaseInsertException, SQLException {
    // TODO Implement this method as stated on the assignment sheet
    // hint: You should be using these three lines in your final code
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();

    // validate whether name is in enum
    boolean in = false;
    for (Roles role : Roles.values()) {
      if (role.toString().equals(name)) {
        in = true;
      }
    }
    if (!in) {
      throw new BadValueException(String.format("The role %s is not a role", name));
    }
    roleCounter++;
    int roleId = DatabaseInserter.insertRole(name, connection);
    connection.close();
    return roleId;
  }

  /**
   * @param name
   * @param age
   * @param address
   * @param password
   * @return
   * @throws DatabaseInsertException
   * @throws SQLException
   * @throws BadValueException
   */
  public static int insertNewUser(String name, int age, String address, String password)
      throws DatabaseInsertException, SQLException, BadValueException {
    // TODO Implement this method as stated on the assignment sheet

    if (address == null || address.length() > 100) {
      throw new BadValueException(String.format("Invalid address %s", address));
    }
    if (password == null) {
      throw new BadValueException(String.format("Invalid password %s", password));
    }
    // String hashedPassword = PasswordHelpers.passwordHash(password);
    // hint: You should be using these three lines in your final code
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int userId = DatabaseInserter.insertNewUser(name, age, address, password, connection);
    connection.close();
    return userId;
  }

  /**
   * @param userId
   * @param roleId
   * @return
   * @throws DatabaseInsertException
   * @throws SQLException
   * @throws BadValueException
   * @throws DataNotFoundException
   * @throws DatabaseContainsInvalidDataException
   */
  public static int insertUserRole(int userId, int roleId) throws DatabaseInsertException,
      SQLException, BadValueException, DataNotFoundException, DatabaseContainsInvalidDataException {
    // TODO Implement this method as stated on the assignment sheet
    // hint: You should be using these three lines in your final code
    User user = DatabaseSelectHelper.getGenericUserDetails(userId);
    if (user == null) {
      throw new BadValueException(String.format("Bad user ID %d", userId));
    }
    List<Integer> roleIds = (List<Integer>) DatabaseSelectHelper.getRoleIds();
    if (!roleIds.contains(roleId)) {
      throw new BadValueException(String.format("Bad role ID %d", roleId));
    }
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int userRoleId = DatabaseInserter.insertUserRole(userId, roleId, connection);
    connection.close();
    return userRoleId;
  }

  /**
   * @param name
   * @param price
   * @return
   * @throws DatabaseInsertException
   * @throws SQLException
   * @throws BadValueException
   */
  public static int insertItem(String name, BigDecimal price)
      throws DatabaseInsertException, SQLException, BadValueException {
    // TODO Implement this method as stated on the assignment sheet
    // hint: You should be using these three lines in your final code

    if (price.precision() < 2 || price.compareTo(BigDecimal.ZERO) < 0) {
      throw new BadValueException(String.format("Invalid price %s", price.toString()));
    }
    if (name == null || name.length() > 64) {
      throw new BadValueException(String.format("Invalid name %s", name));
    }
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int itemId = DatabaseInserter.insertItem(name, price, connection);
    connection.close();
    return itemId;
  }

  /**
   * @param itemId
   * @param quantity
   * @return
   * @throws DatabaseInsertException
   * @throws BadValueException
   * @throws SQLException
   */
  public static int insertInventory(int itemId, int quantity)
      throws DatabaseInsertException, BadValueException, SQLException {
    // TODO Implement this method as stated on the assignment sheet
    // hint: You should be using these three lines in your final code

    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();

    // A valid ID from the ITEMS table and of a valid quantity
    if (quantity < 1) {
      throw new BadValueException(String.format("The quantity %d is not allowed", quantity));
    }
    Item item = DatabaseSelectHelper.getItem(itemId);
    if (item == null) {
      throw new BadValueException(String.format("The item %d is not a valid item", itemId));
    }
    int inventoryId = DatabaseInserter.insertInventory(itemId, quantity, connection);
    connection.close();
    return inventoryId;
  }

  /**
   * @param userId
   * @param totalPrice
   * @return
   * @throws DatabaseInsertException
   * @throws SQLException
   * @throws BadValueException
   * @throws DataNotFoundException
   * @throws DatabaseContainsInvalidDataException
   */
  public static int insertSale(int userId, BigDecimal totalPrice) throws DatabaseInsertException,
      SQLException, BadValueException, DataNotFoundException, DatabaseContainsInvalidDataException {
    // TODO Implement this method as stated on the assignment sheet

    User user = DatabaseSelectHelper.getUserDetails(userId);
    if (user == null) {
      throw new BadValueException(String.format("Bad user ID %d", userId));
    }
    // TODO

    // hint: You should be using these three lines in your final code
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int saleId = DatabaseInserter.insertSale(userId, totalPrice, connection);
    connection.close();
    return saleId;
  }

  /**
   * @param saleId
   * @param itemId
   * @param quantity
   * @return
   * @throws DatabaseInsertException
   * @throws SQLException
   */
  public static int insertItemizedSale(int saleId, int itemId, int quantity)
      throws DatabaseInsertException, SQLException {
    // TODO Implement this method as stated on the assignment sheet
    // hint: You should be using these three lines in your final code
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int itemizedId = DatabaseInserter.insertItemizedSale(saleId, itemId, quantity, connection);
    connection.close();
    return itemizedId;
  }


}
