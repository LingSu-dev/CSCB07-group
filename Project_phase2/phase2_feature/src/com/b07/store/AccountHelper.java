package com.b07.store;

import com.b07.database.helper.DatabaseSelectHelper;
import java.sql.SQLException;
import java.util.List;


public class AccountHelper {

  protected static boolean customerHasAccount(int userId) throws SQLException {
    List<Integer> accounts = DatabaseSelectHelper.getUserAccountsById(userId);
    if (accounts == null) {
      return false;
    }
    
    return !accounts.isEmpty();
  }
  
  protected static boolean customerHasShoppingCarts(int userId) {
    
  }
  
  protected static ShoppingCart retrieveCustomerCart(int userId) {
    
  }
  
  protected static boolean saveCustomerCart(int userId, ShoppingCart cart) {
    
  }
}
