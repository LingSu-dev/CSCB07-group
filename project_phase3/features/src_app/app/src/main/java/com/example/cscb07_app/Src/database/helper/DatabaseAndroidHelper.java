package com.example.cscb07_app.Src.database.helper;

import com.example.cscb07_app.Src.database.DatabaseDriverAndroid;
import com.example.cscb07_app.Src.exceptions.DatabaseInsertException;
import com.example.cscb07_app.Src.inventory.Inventory;
import com.example.cscb07_app.Src.inventory.Item;
import com.example.cscb07_app.Src.store.DiscountTypes;
import com.example.cscb07_app.Src.store.Sale;
import com.example.cscb07_app.Src.store.SalesLog;
import com.example.cscb07_app.Src.store.ShoppingCart;
import com.example.cscb07_app.Src.users.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DatabaseAndroidHelper implements DatabasePlatformHelper {

    private DatabaseDriverAndroid driver;

    public void setDriver(DatabaseDriverAndroid driver){
        this.driver = driver;
    }

    //Driver Helper
    public Connection connectOrCreateDataBase(){
      return null;
    }

    //InsertHelper
    public int insertRole(String name) throws DatabaseInsertException, SQLException{
        return 0;
    }

    public int insertNewUser(String name, int age, String address, String password)
            throws DatabaseInsertException, SQLException{
        return 0;
    }

    public int insertUserRole(int userId, int roleId) throws DatabaseInsertException, SQLException{
        return 0;
    }

    public int insertItem(String name, BigDecimal price) throws DatabaseInsertException, SQLException{
        return 0;
    }

    public int insertInventory(int itemId, int quantity) throws DatabaseInsertException, SQLException{
        return 0;
    }

    public int insertSale(int userId, BigDecimal totalPrice)
            throws DatabaseInsertException, SQLException{
        return 0;
    }

    public int insertItemizedSale(int saleId, int itemId, int quantity)
            throws DatabaseInsertException, SQLException{
        return 0;
    }

    public int insertAccount(int userId, boolean active) throws DatabaseInsertException, SQLException{
        return 0;
    }

    public int insertAccountLine(int accountId, int itemId, int quantity)
            throws DatabaseInsertException, SQLException{
        return 0;
    }

    public int insertDiscountType(String name) throws DatabaseInsertException, SQLException{
        return 0;
    }

    public int insertCoupon(int itemId, int uses, String type, BigDecimal discount, String code)
            throws DatabaseInsertException, SQLException{
        return 0;
    }

    //UpdateHelper
    public boolean updateRoleName(String name, int id) throws DatabaseInsertException, SQLException{
        return true;
    }

    public boolean updateUserName(String name, int userId)
            throws DatabaseInsertException, SQLException{
        return true;
    }

    public boolean updateUserAge(int age, int userId) throws DatabaseInsertException, SQLException{
        return true;
    }

    public boolean updateUserAddress(String address, int userId)
            throws DatabaseInsertException, SQLException{
        return true;
    }

    public boolean updateUserRole(int roleId, int userId)
            throws DatabaseInsertException, SQLException{
        return true;
    }

    public boolean updateItemName(String name, int itemId)
            throws DatabaseInsertException, SQLException{
        return true;
    }

    public boolean updateItemPrice(BigDecimal price, int itemId)
            throws DatabaseInsertException, SQLException{
        return true;
    }

    public boolean updateInventoryQuantity(int quantity, int itemId)
            throws DatabaseInsertException, SQLException{
        return true;
    }

    public boolean updateCouponUses(int uses, int couponId)
            throws DatabaseInsertException, SQLException{
        return true;
    }

    public boolean updateAccountStatus(int userId, int accountId, boolean active)
            throws DatabaseInsertException, SQLException{
        return true;
    }

    //SelectHelper
    public List<Integer> getRoleIds() throws SQLException{
        return null;
    }

    public String getRoleName(int roleId) throws SQLException{
        return null;
    }

    public int getUserRoleId(int userId) throws SQLException{
        return 0;
    }

    public List<Integer> getUsersByRole(int roleId) throws SQLException{
        return null;
    }

    public List<User> getUsersDetails() throws SQLException{
        return null;
    }

    public User getUserDetails(int userId) throws SQLException{
        return null;
    }

    public String getPassword(int userId) throws SQLException{
        return null;
    }

    public List<Item> getAllItems() throws SQLException{
        return null;
    }

    public Item getItem(int itemId) throws SQLException{
        return null;
    }

    public Inventory getInventory() throws SQLException{
        return null;
    }

    public int getInventoryQuantity(int itemId) throws SQLException{
        return 0;
    }

    public SalesLog getSales() throws SQLException{
        return null;
    }

    public Sale getSaleById(int saleId) throws SQLException{
        return null;
    }

    public List<Sale> getSalesToUser(int userId) throws SQLException{
        return null;
    }

    public Sale getItemizedSaleById(int saleId) throws SQLException{
        return null;
    }

    public SalesLog getItemizedSales() throws SQLException{
        return null;
    }

    public List<Integer> getUserIds() throws SQLException{
        return null;
    }

    public boolean itemExists(int itemId) throws SQLException{
        return true;
    }

    public boolean roleIdExists(int roleID) throws SQLException{
        return true;
    }

    public boolean userIdExists(int userID) throws SQLException{
        return true;
    }

    public boolean couponIdExists(int userID) throws SQLException{
        return true;
    }

    public List<Integer> getCouponIds() throws SQLException{
        return null;
    }

    public int getRoleIdByName(String name) throws SQLException{
        return 0;
    }

    public boolean saleExists(int saleId) throws SQLException{
        return true;
    }

    public List<Integer> getUserAccountsById(int userId) throws SQLException{
        return null;
    }

    public int getUserIdByAccountId(int accountId) throws SQLException{
        return 0;
    }

    public ShoppingCart getAccountDetails(int accountId) throws SQLException{
        return null;
    }

    public List<Integer> getAllAccountIds() throws SQLException{
        return null;
    }

    public int getDiscountTypeIdByName(String type) throws SQLException{
        return 0;
    }

    public String getDiscountTypeName(int discountTypeId) throws SQLException{
        return null;
    }

    public boolean discountTypeIdExists(int discountTypeId) throws SQLException{
        return true;
    }

    public List<Integer> getDiscountTypeIds() throws SQLException{
        return null;
    }

    public int getCouponId(String code) throws SQLException{
        return 0;
    }

    public DiscountTypes getDiscountType(int couponId) throws SQLException{
        return null;
    }

    public BigDecimal getDiscountAmount(int couponId) throws SQLException{
        return null;
    }

    public int getCouponItem(int couponId) throws SQLException{
        return 0;
    }

    public int getCouponUses(int couponId) throws SQLException{
        return 0;
    }

    public boolean customerHasAccount(int userId) throws SQLException{
        return true;
    }

    public List<Integer> getUserActiveAccounts(int userId) throws SQLException{
        return null;
    }

    public List<Integer> getUserInactiveAccounts(int userId) throws SQLException{
        return null;
    }

}
