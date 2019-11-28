package com.example.cscb07_app.Src.users;

import com.example.cscb07_app.Src.database.helper.DatabaseHelperAdapter;
import com.example.cscb07_app.Src.exceptions.DatabaseInsertException;
import com.example.cscb07_app.Src.inventory.Item;
import com.example.cscb07_app.Src.store.ShoppingCart;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * A class representing a user account.
 *
 * @author Aidan Zorbas
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
    if (!DatabaseHelperAdapter.userIdExists(userId)) {
      return false;
    }
    List<Integer> accountIds = DatabaseHelperAdapter.getUserAccountsById(userId);
    if (accountIds == null || !accountIds.contains(accountId)) {
      return false;
    }

    cart = DatabaseHelperAdapter.getAccountDetails(accountId);
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

    if (!DatabaseHelperAdapter.userIdExists(userId)) {
      return false;
    }
    List<Integer> accountIds = DatabaseHelperAdapter.getUserAccountsById(userId);
    if (accountIds == null || !accountIds.contains(accountId)) {
      return false;
    }
    HashMap<Item, Integer> items = toAdd.getItemsWithQuantity();
    for (Item item : items.keySet()) {
      try {
        DatabaseHelperAdapter.insertAccountLine(accountId, item.getId(), items.get(item));
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
      return DatabaseHelperAdapter.updateAccountStatus(userId, accountId, false);
    } catch (DatabaseInsertException e) {
      return false;
    }
  }
}
