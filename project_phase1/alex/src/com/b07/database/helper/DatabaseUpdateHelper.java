package com.b07.database.helper;

import com.b07.database.DatabaseUpdater;
import com.b07.exceptions.BadValueException;
import com.b07.exceptions.DataNotFoundException;
import com.b07.exceptions.DatabaseContainsInvalidDataException;
import com.b07.users.Roles;
import com.b07.users.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseUpdateHelper extends DatabaseUpdater {
  
  /**
   * @param name
   * @param id
   * @return
   * @throws SQLException
   * @throws BadValueException
   */
  public static boolean updateRoleName(String name, int id) throws SQLException, BadValueException {
//    TODO Implement this method as stated on the assignment sheet (Strawberry) <- what did he mean by this?
    //hint: You should be using these three lines in your final code
    
    try {
      Roles.valueOf(name);
    } catch (NullPointerException e) {
      throw new BadValueException("name cannot be null");
    } catch (IllegalArgumentException e) {
      throw new BadValueException(String.format("name %s is not a valid role", name));
    }
    
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.updateRoleName(name, id, connection);
    connection.close();
    return complete;
  } 
  
  /**
   * @param name
   * @param userId
   * @return
   * @throws SQLException
   * @throws BadValueException
   */
  public static boolean updateUserName(String name, int userId) throws SQLException, BadValueException {
    if (name == null) {
      throw new BadValueException("Name cannot be null.");
    }
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.updateUserName(name, userId, connection);
    connection.close();
    return complete;
  }
  
  /**
   * @param age
   * @param userId
   * @return
   * @throws SQLException
   * @throws BadValueException
   */
  public static boolean updateUserAge(int age, int userId) throws SQLException, BadValueException {
    if (age < 0) {
      throw new BadValueException("Age cannot be negative");
    }
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.updateUserAge(age, userId, connection);
    connection.close();
    return complete;
  }
  
  /**
   * @param address
   * @param userId
   * @return
   * @throws SQLException
   * @throws BadValueException
   */
  public static boolean updateUserAddress(String address, int userId) throws SQLException, BadValueException {
    if (address == null) {
      throw new BadValueException("Address cannot be null.");
    }
    if (address.length() > 100) {
      throw new BadValueException("Address cannot be longer than 100 characters.");
    }
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.updateUserAddress(address, userId, connection);
    connection.close();
    return complete;

  }
  
  /**
   * @param roleId
   * @param userId
   * @return
   * @throws SQLException
   * @throws DataNotFoundException
   */
  public static boolean updateUserRole(int roleId, int userId) throws SQLException, DataNotFoundException {
    ArrayList<Integer> ids = (ArrayList<Integer>) DatabaseSelectHelper.getRoleIds();
    if (!ids.contains(roleId)) {
      throw new DataNotFoundException("Unable to find the given role id");
    }
    
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.updateUserRole(roleId, userId, connection);
    connection.close();
    return complete;

  }
  
  /**
   * @param name
   * @param itemId
   * @return
   * @throws SQLException
   * @throws BadValueException
   */
  public static boolean updateItemName(String name, int itemId) throws SQLException, BadValueException {
    if (name == null) {
      throw new BadValueException("Name cannot be null.");
    }
    if (name.length() > 63) {
      throw new BadValueException("Name cannot be longer than 63 characters.");
    }
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.updateItemName(name, itemId, connection);
    connection.close();
    return complete;

  }
  
  /**
   * @param price
   * @param itemId
   * @return
   * @throws SQLException
   * @throws BadValueException
   */
  public static boolean updateItemPrice(BigDecimal price, int itemId) throws SQLException, BadValueException {
    if (price.compareTo(BigDecimal.ZERO) > 0) {
      throw new BadValueException("Price cannot be negative");
    }
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.updateItemPrice(price, itemId, connection);
    connection.close();
    return complete;
  }
  
  /**
   * @param quantity
   * @param itemId
   * @return
   * @throws SQLException
   */
  public static boolean updateInventoryQuantity(int quantity, int itemId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    boolean complete = DatabaseUpdater.updateInventoryQuantity(quantity, itemId, connection);
    connection.close();
    return complete;
  }
}
