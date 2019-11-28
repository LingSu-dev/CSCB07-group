package com.b07.store;

import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.inventory.Item;
import com.b07.users.Customer;
import com.b07.users.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A class used by the SalesApplication to verify certain account related information.
 *
 * @author Aidan Zorbas
 */
public class AccountHelper {
  

  /**
   * Check if there are any items stored in a customer's accounts.
   *
   * @param userId the id of the user to check the accounts of.
   * @return true if the user has accounts, and has items in them, false otherwise.
   * @throws SQLException if there is an issue communicating with the database.
   */
  protected static boolean customerHasShoppingCarts(int userId) throws SQLException {
    List<ShoppingCart> carts = new ArrayList<ShoppingCart>();
    List<Integer> ids = DatabaseSelectHelper.getUserAccountsById(userId);
    User user = DatabaseSelectHelper.getUserDetails(userId);
    if (ids == null || user == null || !(user instanceof Customer)) {
      return false;
    }
    for (int i = 0; i < ids.size(); i++) {
      carts.add(DatabaseSelectHelper.getAccountDetails(ids.get(i)));
    }
    for (int j = 0; j < carts.size(); j++) {
      if (carts.get(j) != null) {
        List<Item> items = carts.get(j).getItems();
        if (!items.isEmpty()) {
          return true;
        }
      }
    }
    return false;
  }
  
  protected static List<Integer> getNonemptyAccounts(List<Integer> accountIds){
    
  }

  
  

  
  
  
}
