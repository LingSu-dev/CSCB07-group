package com.b07.store;

import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.database.helper.DatabaseUpdateHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.inventory.Item;
import com.b07.users.Customer;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * A class allowing authenticated users to make purchase from the inventory.
 * 
 * @author Aidan Zorbas
 * @author Alex Efimov
 * @author Lingfeng Su
 * @author Payam Yektamaram
 */
public class ShoppingCart {

  private HashMap<Item, Integer> items;
  private Customer customer;
  private BigDecimal total;
  private final BigDecimal TAXRATE = new BigDecimal("1.13");


  /**
   * Construct a shopping cart for a customer
   */
  public ShoppingCart(Customer customer) {
    this.customer = customer;
    total = BigDecimal.ZERO;
    items = new HashMap<Item, Integer>();
  }

  /**
   * Add the desired quantity of an item to the cart, as long as the item is not null.
   * 
   * @param item
   * @param quantity
   */
  public void addItem(Item item, int quantity) {
    if (item == null) {
      return;
    }
    if (items.containsKey(item)) {
      items.put(item, quantity + items.get(item));
    } else {
      items.put(item, quantity);
    }
    total = calculateCost();
  }

  /**
   * Remove quantity of item from the shopping cart. Quantity must be positive, and removing more
   * than the quantity of item in the cart sets the cart quantity to 0
   * 
   * @param item
   * @param quantity
   */
  public void removeItem(Item item, int quantity) {
    if (quantity < 0) {
      return;
    }
    items.put(item, Math.max(items.getOrDefault(item, 0) - quantity, 0));
    total = calculateCost();
  }

  /**
   * 
   * @return the items in the cart
   */
  public HashMap<Item, Integer> getItems() {
    return items;
  }

  /**
   * @return the taxrate, 1.13
   */
  public BigDecimal getTaxRate() {
    return TAXRATE;
  }

  /**
   * @return the total as a BigDecimal
   */
  public BigDecimal getTotal() {
    return total;
  }

  /**
   * remove all items from the cart
   */
  public void clearCart() {
    total = BigDecimal.ZERO;
    items.clear();
  }

  /**
   * @return the customer whose cart this is
   */
  public Customer getCustomer() {
    return customer;
  }

  /**
   * Check out and @return whether or not the checkout was successful
   * 
   * @throws SQLException
   * @throws DatabaseContainsInvalidDataException
   * @throws DataNotFoundException
   * @throws BadValueException
   * @throws DatabaseInsertException
   */
  public boolean checkOut() throws SQLException, DatabaseInsertException {
    if (this.customer == null) {
      return false;
    }
    BigDecimal total = getTotal().multiply(getTaxRate());
    for (Item item : items.keySet()) {
      if (DatabaseSelectHelper.getInventoryQuantity(item.getId()) < items.get(item)) {
        return false;
      }
    }
    DatabaseInsertHelper.insertSale(customer.getId(), total);
    for (Item item : items.keySet()) {
      DatabaseUpdateHelper.updateInventoryQuantity(item.getId(),
          DatabaseSelectHelper.getInventoryQuantity(item.getId()) - items.get(item));
    }
    clearCart();
    return true;
  }

  /**
   * @return the total cost of all the items in the cart
   */
  private BigDecimal calculateCost() {
    if (items == null) {
      return BigDecimal.ZERO;
    }
    BigDecimal cost = BigDecimal.ZERO;
    for (java.util.Map.Entry<Item, Integer> entry : items.entrySet()) {
      if (entry.getKey() == null) {
        continue;
      }
      BigDecimal quantity = new BigDecimal(entry.getValue().toString());
      cost.add(entry.getKey().getPrice().multiply(quantity));
    }
    return cost;
  }
}
