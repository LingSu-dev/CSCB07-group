package com.b07.store;

import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.inventory.Item;
import com.b07.users.Customer;
import com.b07.users.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class AccountHelper {

  protected static boolean customerHasAccount(int userId) throws SQLException {
    List<Integer> accounts = DatabaseSelectHelper.getUserAccountsById(userId);
    if (accounts == null) {
      return false;
    }
    
    return !accounts.isEmpty();
  }
  
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
  
  protected static ShoppingCart retrieveCustomerCart(int userId) throws SQLException {
    List<ShoppingCart> carts = new ArrayList<ShoppingCart>();
    List<Integer> ids = DatabaseSelectHelper.getUserAccountsById(userId);
    User user = DatabaseSelectHelper.getUserDetails(userId);
    if (ids == null || user == null || !(user instanceof Customer)) {
      return null;
    }
    
    //Populate list with the items stored in all of a user's accounts.
    for (int i = 0; i < ids.size(); i++) {
      carts.add(DatabaseSelectHelper.getAccountDetails(ids.get(i)));
    }
    //Combine all the items into one shopping cart to be returned.
    ShoppingCart combinedCart = new ShoppingCart((Customer) user);
    //For each shopping cart
    for (int j = 0; j < carts.size(); j++) {
      //Get items contained within
      if (carts.get(j) != null) {
        HashMap<Item, Integer> items = carts.get(j).getItemsWithQuantity();
        //Add each item to the combined cart.
        for (Item item : items.keySet()) {
          combinedCart.addItem(item, items.get(item));
        }
      }
    }
    
    return combinedCart;
  }
  
  protected static boolean saveCustomerCart(int userId, ShoppingCart cart) {
    
  }
}
