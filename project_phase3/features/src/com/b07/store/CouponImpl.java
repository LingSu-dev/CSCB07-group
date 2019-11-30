package com.b07.store;

import java.math.BigDecimal;

public class CouponImpl implements Coupon {
  
  int item;
  int uses;
  String type;
  BigDecimal discount;
  String code;
  
  public CouponImpl(int item, int uses, String type, BigDecimal discount, String code) {
    this.item = item;
    this.uses = uses;
    this.type = type;
    this.discount = discount;
    this.code = code;
  }

  @Override
  public int getItemId() {
    // TODO Auto-generated method stub
    return item;
  }

  @Override
  public void setItemId(int item) {
    // TODO Auto-generated method stub
    this.item = item;
  }

  @Override
  public BigDecimal getDiscount() {
    // TODO Auto-generated method stub
    return this.discount;
  }

  @Override
  public void setDiscount(BigDecimal discount) {
    // TODO Auto-generated method stub
    this.discount = discount;
  }

  @Override
  public String getCode() {
    // TODO Auto-generated method stub
    return this.code;
  }

  @Override
  public void setCode(String code) {
    // TODO Auto-generated method stub
    this.code = code;
  }

  @Override
  public String getType() {
    // TODO Auto-generated method stub
    return this.type;
  }

  @Override
  public void setType(String type) {
    // TODO Auto-generated method stub
    this.type = type;
  }

  @Override
  public int getUses() {
    // TODO Auto-generated method stub
    return this.uses;
  }

  @Override
  public void setUses(int uses) {
    // TODO Auto-generated method stub
    this.uses = uses;
  }

}
