package com.b07.users;

import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.database.helper.DatabaseUpdateHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.inventory.Item;
import com.b07.store.ShoppingCart;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A class representing a user account.
 * @author Aidan Zorbas
 *
 */
public class Account {
  private ShoppingCart cart;
  private int accountId;
  private int userId;
  private boolean active;
  
  public Account(int userId, int accountId, boolean active) {
    this.userId = userId;
    this.accountId = accountId;
    this.active = active;
  }
  
  public boolean retrieveCustomerCart() throws SQLException {
    if (!DatabaseSelectHelper.userIdExists(userId)) {
      return false;
    }
    List<Integer> accountIds = DatabaseSelectHelper.getUserAccountsById(userId);
    if (accountIds == null || !accountIds.contains(accountId)) {
      return false;
    }
    
    cart = DatabaseSelectHelper.getAccountDetails(accountId);
    return true;
  } 
  
  /**
   * Retrieve a cart of the item's stored in ALL of the user's accounts.
   *
   * @param userId the user who's items will be retrieved.
   * @return True if successful, false if no such cart can be made.
   * @throws SQLException if there is an issue communicating with the database.
   */
  public boolean retrieveCustomerCartCombined() throws SQLException {
    List<ShoppingCart> carts = new ArrayList<ShoppingCart>();
    List<Integer> ids = DatabaseSelectHelper.getUserAccountsById(userId);
    User user = DatabaseSelectHelper.getUserDetails(userId);
    if (ids == null || user == null || !(user instanceof Customer)) {
      return false;
    }

    // Populate list with the items stored in all of a user's accounts.
    for (int i = 0; i < ids.size(); i++) {
      carts.add(DatabaseSelectHelper.getAccountDetails(ids.get(i)));
    }
    // Combine all the items into one shopping cart to be returned.
    ShoppingCart combinedCart = new ShoppingCart((Customer) user);
    // For each shopping cart
    for (int j = 0; j < carts.size(); j++) {
      // Get items contained within
      if (carts.get(j) != null) {
        HashMap<Item, Integer> items = carts.get(j).getItemsWithQuantity();
        // Add each item to the combined cart.
        for (Item item : items.keySet()) {
          combinedCart.addItem(item, items.get(item));
        }
      }
    }

    cart = combinedCart;
    return true;
  }
  
  /**
   * Saves a customer's cart to their lowest numbered account.
   *
   * @param userId the user to save the cart of.
   * @param cart the cart of items to be saved.
   * @return true if the operation succeeds, false otherwise.
   * @throws SQLException if there is an issue communicating with the database.
   */
  public boolean saveCustomerCart(ShoppingCart toAdd) throws SQLException {

    
    if (!DatabaseSelectHelper.userIdExists(userId)) {
      return false;
    }
    List<Integer> accountIds = DatabaseSelectHelper.getUserAccountsById(userId);
    if (accountIds == null || !accountIds.contains(accountId)) {
      return false;
    }
    HashMap<Item, Integer> items = toAdd.getItemsWithQuantity();
    for (Item item : items.keySet()) {
      try {
        DatabaseInsertHelper.insertAccountLine(accountId, item.getId(), items.get(item));
      } catch (DatabaseInsertException e) {
        return false;
      }
    }
    return true;
    
  }
  
  public ShoppingCart getCart() {
    return cart;
  }
  
  public int getUserId() {
    return userId;
  }
  
  public int getAccountId() {
    return accountId;
  }
  
  public boolean deactivate() throws SQLException {
    try {
      return DatabaseUpdateHelper.updateAccountStatus(userId, accountId, false);
    } catch (DatabaseInsertException e) {
      return false;
    }
  }
}
