package com.b07.store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesLogImpl implements SalesLog {

  private HashMap<Integer, Sale> sales;

  @Override
  public List<Sale> getSales() {
    // TODO Auto-generated method stub
    return (List<Sale>) sales.values();
  }

  @Override
  public Map<Integer, Sale> getSalesWithId() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addSale(Sale sale, int id) {
    // TODO Auto-generated method stub
    sales.put(id, sale);
  }

  @Override
  public Sale getSalebyId(int saleId) {
    // TODO Auto-generated method stub
    return sales.get(saleId);
  }

}
