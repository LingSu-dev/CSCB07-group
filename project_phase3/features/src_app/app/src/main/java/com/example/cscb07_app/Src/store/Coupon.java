package com.example.cscb07_app.Src.store;

import com.b07.inventory.Item;
import java.math.BigDecimal;
import com.example.cscb07_app.Src.inventory.Item;

public interface Coupon {


  /**
   * Get the item the coupon is for
   *
   * @return the item, or null if there is no item for this coupon
   */
  public Item getItem();

  /**
   * set this coupon's item to item
   */
  public void setItem(Item item);

  /**
   * Get the discount percentage as a BigDecimal
   *
   * @return the discount
   */
  public BigDecimal getDiscount();

  /**
   * set the discount of this coupon
   *
   * @param discount: the discount
   */
  public void setDiscount(BigDecimal discount);
}
