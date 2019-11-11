package com.b07.inventory;

import java.math.BigDecimal;

public interface Item {

/**
   *  @returns the id of this item
   */
  public int getId();
  
  /**
   *  sets the id of this item
   */
  public void setId(int id);

   /**
   *  @returns the name of this item
   */
  public String getName();
  
  /**
   *  sets the name of this item
   */
  public void setName(String name);

   /**
   *  @returns the price of this item
   */
  public BigDecimal getPrice();
  
  /**
   *  sets the price of this item
   */
  public void setPrice(BigDecimal price);
    
}
