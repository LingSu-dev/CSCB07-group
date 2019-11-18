package com.b07.store;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.b07.inventory.Item;
import com.b07.users.Customer;
import com.b07.users.User;

/**
 * A simple implementation of the salesLog interface.
 * 
 * @author Aidan Zorbas
 * @author Alex Efimov
 * @author Lingfeng Su
 * @author Payam Yektamaram
 */
public class SalesLogImpl implements SalesLog {

  private ArrayList<Sale> sales = new ArrayList<Sale>();

  /**
   * Return the sales log.
   * 
   * @return list of sales
   */
  @Override
  public List<Sale> getSales() {
    return sales;
  }

  /**
   * Add a sale to the log.
   */
  @Override
  public void addSale(Sale sale) {
    if (sale != null && !sales.contains(sale)) {
      sales.add(sale);
    }
  }

  /**
   * Get total value of sales in sale log.
   */
  @Override
  public BigDecimal getTotalValueOfSales() {
    BigDecimal value = null;
    for (Sale sale : sales) {
      value = value.add(sale.getTotalPrice());
    }

    return value;
  }

  /**
   * Returns the total number of sales.
   * 
   * @return total number of sales
   */
  @Override
  public int getTotalSalesCount() {
    return sales.size();
  }



  @Override
  public List<Sale> getSalesToCustomer(User user) {
    if (user == null) {
      return null;
    }
    List<Sale> customerSales = new ArrayList<Sale>();
    for (Sale sale : sales) {
      if (sale.getUser() == user) {
        customerSales.add(sale);
      }
    }
    return customerSales;
  }

  @Override
  public List<User> getCustomers() {
    Set<User> customers = new HashSet<User>();
    for (Sale sale : sales) {
      customers.add(sale.getUser());
    }
    return new ArrayList<User>(customers);
  }

  @Override
  public int getItemSaleQuantity(Item item) {
    if (item == null) {
      return 0;
    }
    int itemCount = 0;
    for (Sale sale : sales) {
      itemCount += sale.getItemMap().getOrDefault(item, 0);
    }
    return itemCount;
  }
}
