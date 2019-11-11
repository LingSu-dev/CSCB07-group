package com.b07.database.helper;

import com.b07.database.DatabaseSelector;
import com.b07.exceptions.DataNotFoundException;
import com.b07.exceptions.DatabaseContainsInvalidDataException;
import com.b07.exceptions.InvalidUserParameterException;
import com.b07.inventory.Inventory;
import com.b07.inventory.InventoryImpl;
import com.b07.inventory.Item;
import com.b07.inventory.ItemImpl;
import com.b07.store.Sale;
import com.b07.store.SaleImpl;
import com.b07.store.SalesLog;
import com.b07.store.SalesLogImpl;
import com.b07.users.Admin;
import com.b07.users.Customer;
import com.b07.users.Employee;
import com.b07.users.Roles;
import com.b07.users.User;
import com.b07.inventory.ItemImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * TODO: Complete the below methods to be able to get information out of the database. TODO: The
 * given code is there to aide you in building your methods. You don't have TODO: to keep the exact
 * code that is given (for example, DELETE the System.out.println()) TODO: and decide how to handle
 * the possible exceptions
 */
public class DatabaseSelectHelper extends DatabaseSelector {

  /**
   * @return a list of role ids
   * @throws SQLException
   */
  public static List<Integer> getRoleIds() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getRoles(connection);

    List<Integer> ids = new ArrayList<>();
    while (results.next()) {
      ids.add(results.getInt("ID"));
    }

    results.close();
    connection.close();
    return ids;
  }

  /**
   * @param roleId the role id
   * @return the role name
   * @throws SQLException
   */
  public static String getRoleName(int roleId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    String role = DatabaseSelector.getRole(roleId, connection);
    connection.close();
    return role;
  }

  /**
   * @param userId
   * @return the user's role id
   * @throws SQLException
   */
  public static int getUserRoleId(int userId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int roleId = DatabaseSelector.getUserRole(userId, connection);
    connection.close();
    return roleId;
  }

  /**
   * @param roleId
   * @return the users in a role
   * @throws SQLException
   */
  public static List<Integer> getUsersByRole(int roleId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getUsersByRole(roleId, connection);
    List<Integer> userIds = new ArrayList<>();
    while (results.next()) {
      userIds.add(results.getInt("ROLEID"));

    }
    results.close();
    connection.close();
    return userIds;

  }

  /**
   * @return a list of users
   * @throws SQLException
   * @throws DataNotFoundException
   * @throws DatabaseContainsInvalidDataException
   */
  public static List<User> getUsersDetails()
      throws SQLException, DataNotFoundException, DatabaseContainsInvalidDataException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getUsersDetails(connection);
    List<User> users = new ArrayList<>();
    while (results.next()) {
      int userId = results.getInt("ID");
      User user = getUserDetails(userId);
      if (user != null) {
        users.add(user);
      }
    }
    results.close();
    connection.close();
    return users;
  }

  /**
   * @param userId
   * @return a user
   * @throws DatabaseContainsInvalidDataException
   * @throws SQLException
   * @throws DataNotFoundException
   */
  public static User getUserDetails(int userId)
      throws DatabaseContainsInvalidDataException, SQLException, DataNotFoundException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getUserDetails(userId, connection);
    User user = null;
    while (results.next()) {
      int id = results.getInt("ID");
      String name = results.getString("NAME");
      int age = results.getInt("AGE");
      String address = results.getString("ADDRESS");
      String roleName = getRoleName(getUserRoleId(userId));

      if (id != userId) {
        throw new DataNotFoundException(); // let the user know that the user with the given id is
                                           // not in the database
      }
      if (roleName.equals(Roles.ADMIN.toString())) {
        user = new Admin(id, name, age, address);
      } else if (roleName.equals(Roles.EMPLOYEE.toString())) {
        user = new Employee(id, name, age, address);
      } else if (roleName.equals(Roles.CUSTOMER.toString())) {
        user = new Customer(id, name, age, address);
      } else {
        throw new DatabaseContainsInvalidDataException(); // let the user know that the user in the
                                                          // database is invalid
      }
    }
    results.close();
    connection.close();
    return user;
  }

  /**
   * @param userId
   * @return a user without a role
   * @throws DatabaseContainsInvalidDataException
   * @throws SQLException
   * @throws DataNotFoundException
   */
  protected static User getGenericUserDetails(int userId)
      throws DatabaseContainsInvalidDataException, SQLException, DataNotFoundException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getUserDetails(userId, connection);
    User user = null;
    while (results.next()) {
      int id = results.getInt("ID");
      String name = results.getString("NAME");
      int age = results.getInt("AGE");
      String address = results.getString("ADDRESS");

      if (id != userId) {
        throw new DataNotFoundException(); // let the user know that the user with the given id is
                                           // not in the database
      }
      user = new Admin(id, name, age, address);
    }
    results.close();
    connection.close();
    return user;
  }

  /**
   * 
   * @param name
   * @return a role id
   * @throws SQLException
   */
  public static int getRoleId(String name) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    String sql = "SELECT ID FROM ROLES WHERE NAME = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql);
    preparedStatement.setString(1, name);
    ResultSet results = preparedStatement.executeQuery();
    int id = results.getInt("ID");
    results.close();
    return id;
  }

  /**
   * @param userId
   * @return
   * @throws SQLException
   */
  public static String getPassword(int userId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    String password = DatabaseSelector.getPassword(userId, connection);
    connection.close();
    return password;
  }

  /**
   * @return a list of items
   * @throws SQLException
   */
  public static List<Item> getAllItems() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getAllItems(connection);
    List<Item> items = new ArrayList<>();

    Item item;
    while (results.next()) {
      try {
        int id = results.getInt("ID");
        item = getItem(id);
        items.add(item); // split for easier debugging
      } catch (Exception e) {
        // TODO: handle exception
        throw e; // nice code bro. see piazza @298
      }
    }

    results.close();
    connection.close();
    return items;
  }

  /**
   * @param itemId
   * @return an item
   * @throws SQLException
   */
  public static Item getItem(int itemId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getItem(itemId, connection);
    Item item = null;
    while (results.next()) {


      int id = results.getInt("ID");
      String name = results.getString("NAME");
      BigDecimal price = new BigDecimal(results.getString("PRICE"));

      item = new ItemImpl(name);
      item.setId(id);
      item.setName(name);
      item.setPrice(price);
    }
    results.close();
    connection.close();
    return item;
  }

  /**
   * @return the inventory
   * @throws SQLException
   */
  public static Inventory getInventory() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getInventory(connection);

    Inventory inventory = new InventoryImpl();
    while (results.next()) {
      int id = results.getInt("ITEMID");
      int quantity = results.getInt("QUANTITY");

      Item item = getItem(id);
      inventory.updateMap(item, quantity);
    }
    results.close();
    connection.close();
    return null;
  }

  /**
   * @param itemId
   * @return the quantity of item in inventory
   * @throws SQLException
   */
  public static int getInventoryQuantity(int itemId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    int quantity = DatabaseSelector.getInventoryQuantity(itemId, connection);
    connection.close();
    return quantity;
  }

  /**
   * @return a list of sales
   * @throws SQLException
   */
  public static SalesLog getSales() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getSales(connection);
    String out = "";
    while (results.next()) {
      out.concat(Integer.toString(results.getInt("ID")));
      out.concat(Integer.toString(results.getInt("USERID")));
      out.concat(results.getString("TOTALPRICE"));
    }
    results.close();
    connection.close();
    return (SalesLog) null;
  }

  /**
   * @param saleId
   * @return
   * @throws SQLException
   * @throws DataNotFoundException
   * @throws DatabaseContainsInvalidDataException
   */
  public static Sale getSaleById(int saleId)
      throws SQLException, DatabaseContainsInvalidDataException, DataNotFoundException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getSaleById(saleId, connection);
    Sale sale = new SaleImpl();
    while (results.next()) {
      sale.setId(results.getInt("ID"));
      sale.setUser(getGenericUserDetails(results.getInt("USERID")));
      sale.setTotalPrice(new BigDecimal(results.getString("TOTALPRICE")));
    }
    results.close();
    connection.close();
    return sale;
  }

  /**
   * @param userId
   * @return
   * @throws SQLException
   * @throws DataNotFoundException
   * @throws DatabaseContainsInvalidDataException
   */
  public static List<Sale> getSalesToUser(int userId)
      throws SQLException, DatabaseContainsInvalidDataException, DataNotFoundException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelectHelper.getSalesToUser(userId, connection);
    List<Sale> sales = new ArrayList<>();
    while (results.next()) {
      Sale sale = new SaleImpl();
      sale.setId(results.getInt("ID"));
      sale.setUser(getGenericUserDetails(results.getInt("USERID")));
      sale.setTotalPrice(new BigDecimal(results.getString("TOTALPRICE")));
      sales.add(sale);
    }
    results.close();
    connection.close();
    return sales;
  }

  /**
   * @param saleId
   * @return
   * @throws SQLException
   */
  public static Sale getItemizedSaleById(int saleId) throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getItemizedSaleById(saleId, connection);
    Sale sale = new SaleImpl();
    HashMap<Item, Integer> items = new HashMap<>();
    while (results.next()) {
      if (results.getInt("SALEID") != saleId) {
        sale.setId(results.getInt("SALEID"));
        continue;
      }

      items.put(getItem(results.getInt("ITEMID")), results.getInt("QUANTITY"));
    }
    sale.setItemMap(items);
    results.close();
    connection.close();
    return sale;
  }

  /**
   * @return
   * @throws SQLException
   */
  public static SalesLog getItemizedSales() throws SQLException {
    Connection connection = DatabaseDriverHelper.connectOrCreateDataBase();
    ResultSet results = DatabaseSelector.getItemizedSales(connection);
    SalesLog sales = new SalesLogImpl();
    while (results.next()) {
      Sale sale = new SaleImpl();
      HashMap<Item, Integer> items = new HashMap<>();
      sale.setId(results.getInt("SALEID"));
      items.put(getItem(results.getInt("ITEMID")), results.getInt("QUANTITY"));
      sale.setItemMap(items);
      sales.addSale(sale, results.getInt("SALEID"));
    }
    results.close();
    connection.close();
    return (SalesLog) sales;
  }
}
