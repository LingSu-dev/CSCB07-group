package com.b07.inventory;

import java.util.HashMap;

public interface Inventory {

  /**
   * get the item map
   */
  public HashMap<Item, Integer> getItemMap();

  /**
   * set the item map
   */
  public void setItemMap(HashMap<Item, Integer> itemMap);

  /**
   * update the item map
   */
  public void updateMap(Item item, Integer value);

  /**
   * get the item total
   */
  public int getTotalItems();

  /**
   * set the item total
   */
  public void setTotalItems(int total);

}
