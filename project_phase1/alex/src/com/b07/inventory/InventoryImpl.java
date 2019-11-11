/**
 * 
 */
package com.b07.inventory;

import java.util.HashMap;

import com.b07.exceptions.BadValueException;

/**
 * @author efimoval
 *
 */
public class InventoryImpl implements Inventory {

  private HashMap<Item, Integer> inventory;
  
  /**
   * Set the inventory so an empty map.
   */
  /**
   * 
   */
  public InventoryImpl() {
    inventory = new HashMap<Item, Integer>();
  }

  /* (non-Javadoc)
   * @see com.b07.inventory.Inventory#getItemMap()
   */
  /**
   *
   */
  @Override
  public HashMap<Item, Integer> getItemMap() {
    // TODO Auto-generated method stub
    return inventory;
  }

  /* (non-Javadoc)
   * @see com.b07.inventory.Inventory#setItemMap(java.util.HashMap)
   */
  /**
   *
   */
  @Override
  public void setItemMap(HashMap<Item, Integer> itemMap) {
    // TODO Auto-generated method stub
    if (itemMap == null) {
      inventory = new HashMap<Item, Integer>();
    } else {
      inventory = itemMap;
    }
  }

  /* (non-Javadoc)
   * @see com.b07.inventory.Inventory#updateMap(com.b07.inventory.Item, java.lang.Integer)
   */
  /**
   *
   */
  @Override
  public void updateMap(Item item, Integer value) {
    // TODO Auto-generated method stub
    inventory.put(item, value);
  }

  /* (non-Javadoc)
   * @see com.b07.inventory.Inventory#getTotalItems()
   */
  /**
   *
   */
  @Override
  public int getTotalItems() {
    // TODO Auto-generated method stub
    return inventory.size();
  }

  /* (non-Javadoc)
   * @see com.b07.inventory.Inventory#setTotalItems(int)
   */
  /**
   *
   */
  @Override
  public void setTotalItems(int total) {
    // What is this supposed to do?
   
  }

}
