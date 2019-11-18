package com.b07.store;

import java.math.BigDecimal;
import java.util.List;
import com.b07.inventory.Item;
import com.b07.users.Customer;
import com.b07.users.User;

/**
 * A log to keep track of all transactions.
 * 
 * @author Aidan Zorbas
 * @author Alex Efimov
 * @author Lingfeng Su
 * @author Payam Yektamaram
 */
public interface SalesLog {


  /**
   * Get the log of all the sale transactions.
   * 
   * @return list of sales
   */
  public List<Sale> getSales();


  /**
   * Add a sale to the log.
   * 
   * @param sale the sale to be added
   */
  public void addSale(Sale sale);

  /**
   * Get the total number of sales.
   * 
   * @return total number of sales
   */
  public int getTotalSalesCount();

  /**
   * Get the total value of all the sales.
   * 
   * @return total sales
   */
  public BigDecimal getTotalValueOfSales();
  
  /**
   * Get the number of item sold
   * @return the sales of the item
   */
  public int getItemSaleQuantity(Item item);

  /**
   * Get a list of sales to a given customer in the log.
   * @return the sales to the customer
   */
  public List<Sale> getSalesToCustomer(User user);

  /**
   * Get a list of customers who have purchased items
   * @return a list of customers
   */
  public List<User> getCustomers();

  /**
   * Get a list of customers who have purchased items
   * @return a list of customers
   */
  public String viewBooks();


}
