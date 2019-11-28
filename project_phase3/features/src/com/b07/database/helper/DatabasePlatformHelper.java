package com.b07.database.helper;

import com.b07.inventory.Inventory;
import com.b07.inventory.Item;
import com.b07.store.DiscountTypes;
import com.b07.store.Sale;
import com.b07.store.SalesLog;
import com.b07.store.ShoppingCart;
import com.b07.users.User;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;


public interface DatabasePlatformHelper {
  //Driver Helper
  public Connection connectOrCreateDataBase();
  
  //InsertHelper
  public int insertRole(String name);
  
  public int insertNewUser(String name, int age, String address, String password);
  
  public int insertUserRole(int userId, int roleId);
  
  public int insertItem(String name, BigDecimal price);
  
  public int insertInventory(int itemId, int quantity);
  
  public int insertSale(int userId, BigDecimal totalPrice);
  
  public int insertItemizedSale(int saleId, int itemId, int quantity);
  
  public int insertAccount(int userId, boolean active);
  
  public int insertAccountLine(int accountId, int itemId, int quantity);
  
  public int insertDiscountType(String name);
  
  public int insertCoupon(int itemId, int uses, String type, BigDecimal discount, String code);
  
  //UpdateHelper
  public boolean updateRoleName(String name, int id);
  
  public boolean updateUserName(String name, int userId);
  
  public boolean updateUserAge(int age, int userId);
  
  public boolean updateUserAddress(String address, int userId);
  
  public boolean updateUserRole(int roleId, int userId);
  
  public boolean updateItemName(String name, int itemId);
  
  public boolean updateItemPrice(BigDecimal price, int itemId);
  
  public boolean updateInventoryQuantity(int quantity, int itemId);
  
  public boolean updateCouponUses(int uses, int couponId); 
  
  public boolean updateAccountStatus(int userId, int accountId, boolean active); 
  
  //SelectHelper
  public List<Integer> getRoleIds();
  
  public String getRoleName(int roleId);
  
  public int getUserRoleId(int userId);
  
  public List<Integer> getUsersByRole(int roleId);
  
  public List<User> getUsersDetails();
  
  public User getUserDetails(int userId);
  
  public String getPassword(int userId);
  
  public List<Item> getAllItems();
  
  public Item getItem(int itemId);
  
  public Inventory getInventory();
  
  public int getInventoryQuantity(int itemId);
  
  public SalesLog getSales();
  
  public Sale getSaleById(int saleId);
  
  public List<Sale> getSalesToUser(int userId);
  
  public Sale getItemizedSaleById(int saleId);
  
  public SalesLog getItemizedSales();
  
  public List<Integer> getUserIds();
  
  public boolean itemExists(int itemId);
  
  public boolean roleIdExists(int roleID);
  
  public boolean userIdExists(int userID);
  
  public boolean couponIdExists(int userID);
  
  public List<Integer> getCouponIds();
  
  public int getRoleIdByName(String name);
  
  public boolean saleExists(int saleId);
  
  public List<Integer> getUserAccountsById(int userId);
  
  public int getUserIdByAccountId(int accountId);
  
  public ShoppingCart getAccountDetails(int accountId);
  
  public List<Integer> getAllAccountIds();
  
  public int getDiscountTypeIdByName(String type);
  
  public String getDiscountTypeName(int discountTypeId);
  
  public boolean discountTypeIdExists(int discountTypeId);
  
  public List<Integer> getDiscountTypeIds();
  
  public int getCouponId(String code);
  
  public DiscountTypes getDiscountType(int couponId);
  
  public BigDecimal getDiscountAmount(int couponId);
  
  public int getCouponItem(int couponId);
  
  public int getCouponUses(int couponId);
  
  public boolean customerHasAccount(int userId);
  
  public List<Integer> getUserActiveAccounts(int userId);
  
  public List<Integer> getUseInactiveAccounts(int userId);
}
