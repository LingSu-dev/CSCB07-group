package com.b07.store;

import java.math.BigDecimal;
import java.util.HashMap;

import com.b07.inventory.Item;
import com.b07.users.User;

public interface Sale {
  
  /**
   *  @return the id of this item
   */
  public int getId();
  
  /**
   *  sets the id of this item
   */
  public void setId(int id);

   /**
   *  @returns the user of this sale
   */
  public User getUser();
  
  /**
   *  sets the name of this item
   */
  public void setUser(User user);
  
  /**
   *  @returns the price of this sale
   */
  public BigDecimal getTotalPrice();
  
  /**
   *  sets the price of this sale
   */
  public void setTotalPrice(BigDecimal price);
  

  /**
   * get the item map
   */
  public HashMap<Item, Integer> getItemMap();

  /**
   * set the item map
   */
  public void setItemMap(HashMap<Item, Integer> itemMap);
    
}
