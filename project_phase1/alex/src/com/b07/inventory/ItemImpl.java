/**
 * 
 */
package com.b07.inventory;

import java.math.BigDecimal;

/**
 * @author efimoval
 *
 */
public class ItemImpl implements Item {

  private int id;
  private String name;
  private BigDecimal price;
  private ItemTypes itemType;
  
  /**
   * @param itemType: the type of item, as a string
   */
  public ItemImpl(String itemType) {
    // TODO Auto-generated constructor stub
    if(itemType.equals("FISHING_ROD")){
      this.itemType = ItemTypes.FISHING_ROD;
    } else if(itemType.equals("HOCKEY_STICK")){
      this.itemType = ItemTypes.HOCKEY_STICK;
    } else if(itemType.equals("SKATES")){
      this.itemType = ItemTypes.SKATES;
    } else if(itemType.equals("RUNNING_SHOES")){
      this.itemType = ItemTypes.RUNNING_SHOES;
    } else if(itemType.equals("PROTEIN_BAR")){
      this.itemType = ItemTypes.PROTEIN_BAR;
    } else {
      //be sad
    }
    this.name = "";
    this.price = BigDecimal.ZERO;
  } 
  
  /**
   * @param itemType: the type of item, as a string
   */
  public ItemImpl(String itemType, int id, String name, BigDecimal price) {
    // TODO Auto-generated constructor stub
    if(itemType.equals("FISHING_ROD")){
      this.itemType = ItemTypes.FISHING_ROD;
    } else if(itemType.equals("HOCKEY_STICK")){
      this.itemType = ItemTypes.HOCKEY_STICK;
    } else if(itemType.equals("SKATES")){
      this.itemType = ItemTypes.SKATES;
    } else if(itemType.equals("RUNNING_SHOES")){
      this.itemType = ItemTypes.RUNNING_SHOES;
    } else if(itemType.equals("PROTEIN_BAR")){
      this.itemType = ItemTypes.PROTEIN_BAR;
    } else {
      //be sad
    }
    this.name = name;
    this.price = price;
    this.id = id;
  }

  /* (non-Javadoc)
   * @see com.b07.inventory.Item#getId()
   */
  @Override
  public int getId() {
    // TODO Auto-generated method stub
    return id;
  }

  /* (non-Javadoc)
   * @see com.b07.inventory.Item#setId(int)
   */
  @Override
  public void setId(int id) {
    // TODO Auto-generated method stub
    this.id = id;
  }

  /* (non-Javadoc)
   * @see com.b07.inventory.Item#getName()
   */
  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return name;
  }

  /* (non-Javadoc)
   * @see com.b07.inventory.Item#setName(java.lang.String)
   */
  @Override
  public void setName(String name) {
    // TODO Auto-generated method stub
    this.name = name;

  }

  /* (non-Javadoc)
   * @see com.b07.inventory.Item#getPrice()
   */
  @Override
  public BigDecimal getPrice() {
    // TODO Auto-generated method stub
    return price;
  }

  /* (non-Javadoc)
   * @see com.b07.inventory.Item#setPrice(java.math.BigDecimal)
   */
  @Override
  public void setPrice(BigDecimal price) {
    // TODO Auto-generated method stub
    this.price = price;
  }

}
