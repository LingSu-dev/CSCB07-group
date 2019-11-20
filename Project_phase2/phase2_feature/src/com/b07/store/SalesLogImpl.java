package com.b07.store;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.inventory.Item;
import com.b07.users.User;
import com.b07.inventory.ItemTypes;
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
    BigDecimal value = new BigDecimal("0.00");
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


  /**
   * Get all the items in the sales log.
   * 
   * @return list of items.
   */
  public List<Item> getItems() {
    List<Item> items = new ArrayList<>();
   
    for (Sale singleSale : sales) {
      for (Item item : singleSale.getItemMap().keySet()) {
        System.out.println(item.getName());
        if (!items.contains(item)) {
          items.add(item);
        }
      }
    }
    return items;
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

  @Override
  public HashMap<Item, Integer> getItemsSaleQuantity() {
    HashMap<Item, Integer> quantitiesSold = new HashMap<Item, Integer>();
    for (Sale sale : sales) {
      for (Item item : sale.getItemMap().keySet()) {
        quantitiesSold.replace(item,
            quantitiesSold.getOrDefault(item, 0) + getItemSaleQuantity(item));
      }
    }
    return quantitiesSold;
  }

  @Override
  public String viewBooks() {

    if (getTotalValueOfSales() == null) {
      return "There is no data to display.";
    }

    StringBuilder outString = new StringBuilder();
    boolean firstItem;

    for (Sale sale : getSales()) {
      outString.append(String.format("Customer: %s%n", sale.getUser().getName()));
      outString.append(String.format("Purchase Number: %d%n", sale.getId()));
      outString.append(String.format("Total Purchase Price: %s%n", sale.getTotalPrice().setScale(2, RoundingMode.CEILING)));
      outString.append(String.format("Itemized breakdown:", ""));
      firstItem = true;
      
      for (Map.Entry<Item, Integer> entry : sale.getItemMap().entrySet()) {
        Item item = entry.getKey();
        int quantity = entry.getValue();
        
        if (firstItem)
        {
          outString.append(String.format("%s: %d%n", item.getName(), quantity));
          firstItem = false;
        }
        else
        {
          outString.append(String.format("                    %s: %d%n", item.getName(), quantity));
        }
      }
      
      outString.append(String.format("-------------------------------------", ""));
    }

    for (Item item : getItems()) {
      outString
          .append(String.format("Number %s Sold: %d%n", item.getName(), getItemSaleQuantity(item)));
    }

    outString.append(String.format("TOTAL SALES: %s%n", getTotalValueOfSales().setScale(2, RoundingMode.CEILING)));
    return outString.toString();
  }
  
}
