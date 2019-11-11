package com.b07.store;

import java.util.List;
import java.util.Map;

public interface SalesLog {
  // ok

  /**
   * returns the list of sales
   * 
   * @return
   */
  public List<Sale> getSales();
  
  /**
   * returns the list of sales
   * 
   * @return
   */
  public Map<Integer, Sale> getSalesWithId();

  /**
   * adds a sale to the log with id id
   * 
   * @param sale
   */
  public void addSale(Sale sale, int id);

  /**
   * 
   * @param saleId
   * @return a sale with the given id
   */
  public Sale getSalebyId(int saleId);
}
