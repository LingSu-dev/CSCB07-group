package com.b07.store;

import java.math.BigDecimal;
import java.util.HashMap;
import com.b07.inventory.Item;
import com.b07.users.User;

public class SaleImpl implements Sale {

  private int id;
  private User user;
  private BigDecimal price;
  private HashMap<Item, Integer> items;

  /**
   *
   */
  @Override
  public int getId() {
    return id;
  }

  /**
   *
   */
  @Override
  public void setId(int id) {
    this.id = id;
  }

  /**
   *
   */
  @Override
  public User getUser() {
    return user;
  }

  /**
   *
   */
  @Override
  public void setUser(User user) {
    this.user = user;
  }

  /**
   *
   */
  @Override
  public BigDecimal getTotalPrice() {
    return price;
  }

  /**
   *
   */
  @Override
  public void setTotalPrice(BigDecimal price) {
    this.price = price;
  }

  /**
   *
   */
  @Override
  public HashMap<Item, Integer> getItemMap() {
    return items;
  }

  /**
   *
   */
  @Override
  public void setItemMap(HashMap<Item, Integer> itemMap) {
    this.items = itemMap;
  }

}
