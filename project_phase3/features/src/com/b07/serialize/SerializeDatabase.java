package com.b07.serialize;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import com.b07.database.helper.DatabaseHelperAdapter;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.DifferentEnumException;
import com.b07.inventory.Inventory;
import com.b07.inventory.Item;
import com.b07.inventory.ItemTypes;
import com.b07.serialize.DataStorage;
import com.b07.serialize.SerializeFunc;
import com.b07.store.Coupon;
import com.b07.store.CouponImpl;
import com.b07.store.DiscountTypes;
import com.b07.store.SalesLog;
import com.b07.store.ShoppingCart;
import com.b07.users.Account;
import com.b07.users.Roles;
import com.b07.users.User;
import java.util.Arrays;


public class SerializeDatabase {

  private static DataStorage getDatabaseObject() throws SQLException {

    ArrayList<Integer> roleIds = (ArrayList) DatabaseHelperAdapter.getRoleIds();
    // Below
    HashMap<Integer, String> roleIdToRoleNames = new HashMap<Integer, String>();
    for (Integer id : roleIds) {
      roleIdToRoleNames.put(id, DatabaseHelperAdapter.getRoleName(id));
    }

    ArrayList<Integer> userIds = (ArrayList) DatabaseHelperAdapter.getUserIds();

    // Below
    ArrayList<User> users = (ArrayList) DatabaseHelperAdapter.getUsersDetails();

    // Below
    HashMap<Integer, Integer> userToRole = new HashMap<Integer, Integer>();
    for (Integer id : userIds) {
      userToRole.put(id, DatabaseHelperAdapter.getUserRoleId(id));
    }

    // Below
    ArrayList<Item> items = (ArrayList) DatabaseHelperAdapter.getAllItems();

    // Below
    Inventory inventory = DatabaseHelperAdapter.getInventory();

    // Below
    SalesLog sales = DatabaseHelperAdapter.getSales();

    // Below
    SalesLog itemizedSales = DatabaseHelperAdapter.getItemizedSales();

    // Below
    ArrayList<Account> accounts = new ArrayList<Account>();
    ArrayList<Integer> accountIds = (ArrayList<Integer>) DatabaseHelperAdapter.getAllAccountIds();
    int userId;
    boolean active;
    ArrayList<Integer> activeAccounts;
    Account newAccount;
    for (Integer accountId : accountIds) {
      userId = DatabaseHelperAdapter.getUserIdByAccountId(accountId);
      activeAccounts = (ArrayList<Integer>) DatabaseHelperAdapter.getUserActiveAccounts(userId);
      active = activeAccounts.contains(accountId);
      newAccount = new Account(userId, accountId, active);
      newAccount.retrieveCustomerCart();
      accounts.add(newAccount);
    }

    // Below
    HashMap<Integer, String> userToHashedPWs = new HashMap<Integer, String>();
    for (Integer id : userIds) {
      userToHashedPWs.put(id, DatabaseHelperAdapter.getPassword(id));
    }

    ArrayList<Integer> discountTypeIds =
        (ArrayList<Integer>) DatabaseHelperAdapter.getDiscountTypeIds();
    // Below
    HashMap<Integer, String> discountTypes = new HashMap<Integer, String>();
    for (Integer discountIds : discountTypeIds) {
      discountTypes.put(discountIds, DatabaseHelperAdapter.getDiscountTypeName(discountIds));
    }

    ArrayList<Integer> couponIds = (ArrayList<Integer>) DatabaseHelperAdapter.getCouponIds();
    // Below
    HashMap<Integer, Coupon> coupons = new HashMap<Integer, Coupon>();
    Integer itemId;
    int uses;
    String type;
    BigDecimal discount;
    String code;
    for (Integer couponId : couponIds) {
      itemId = DatabaseHelperAdapter.getCouponItem(couponId);
      uses = DatabaseHelperAdapter.getCouponUses(couponId);
      type = DatabaseHelperAdapter.getDiscountTypeName(couponId);
      discount = DatabaseHelperAdapter.getDiscountAmount(couponId);
      code = null;
      coupons.put(couponId, new CouponImpl(itemId, uses, type, discount, code));
    }

    DataStorage data = new DataStorage(roleIdToRoleNames, users, userToRole, items, inventory,
        sales, itemizedSales, accounts, userToHashedPWs, discountTypes, coupons);

    return data;
  }

  public static void serializeToFile(String location) throws SQLException, IOException {

    DataStorage database = getDatabaseObject();
    SerializeFunc.serialize(database, location + "database_copy.ser");
    System.out.println("Database stored in: " + location + "database_copy.ser");
  }

  private static boolean checkEnums(DataStorage database) {
    //TODO: Check roles, Coupontype, and Items Enum, return false if any do not match
    List<String> rolesEnum = new ArrayList<String>();
    for (Roles role : Roles.values()) {
      rolesEnum.add(role.toString());
    }
    
    List<String> discountTypesEnum = new ArrayList<String>();
    for (DiscountTypes discountType : DiscountTypes.values()) {
      discountTypesEnum.add(discountType.toString());
    }
    
    List<String> itemTypesEnum = new ArrayList<String>();
    for (ItemTypes itemType : ItemTypes.values()) {
      itemTypesEnum.add(itemType.toString());
    }
    
    for (String roleDatabase: database.getRoleIdToRoleNames().values()) {
      if (!rolesEnum.contains(roleDatabase)) {
        return false;
      }
    }
    
    for (String discountDatabase : database.getDiscountTypes().values()) {
      if (!discountTypesEnum.contains(discountDatabase)) {
        return false;
      }
    }
    
    for (Item itemDatabase : database.getItems()) {
      if (!itemTypesEnum.contains(itemDatabase.getName())){
        return false;
      }
    }
    
    return true;
  }
  
  private static void insertDataStorage(DataStorage database) throws SQLException, DatabaseInsertException {
    HashMap<Integer, String> roleIdToRoleNames = ;
    ArrayList<User> users;
    HashMap<Integer, Integer> userToRole;
    ArrayList<Item> items;
    Inventory inventory;
    SalesLog sales;
    SalesLog itemizedSales;
    ArrayList<Account> accounts;
    HashMap<Integer, String> userToHashedPWs;
    HashMap<Integer, String> discountTypes;
    HashMap<Integer, Coupon> couponIdsToCoupons;
    
    
  }
  
  public static void populateFromFile(String location) throws SQLException, IOException, ClassNotFoundException, DifferentEnumException{
    DataStorage database = SerializeFunc.deserialize(location + "database_copy.ser");
    if (!checkEnums(database)) {
      throw new DifferentEnumException();
    }
    
    //TODO: Rename old database to database_backup for reverting
    try {
      // TODO: DatabaseHelperAdapter.reInitialize;
      insertDataStorage(database);
    } catch (Exception i){
      //TODO: Use OS to load the backup database
    }
    //TODO: Use OS to remove the backup database since data was inserted successfully
    //TODO: Ask if the admin who performed the populate want to be inserted if missing
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  }



}
