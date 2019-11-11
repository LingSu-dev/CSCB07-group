package com.b07.store;

import java.sql.SQLException; 
import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.database.helper.DatabaseUpdateHelper;
import com.b07.exceptions.BadValueException;
import com.b07.exceptions.DataNotFoundException;
import com.b07.exceptions.DatabaseContainsInvalidDataException;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.InvalidUserParameterException;
import com.b07.inventory.Inventory;
import com.b07.inventory.Item;
import com.b07.users.Employee;
import com.b07.users.Roles;

/**
 * @author alex
 *
 */
public class EmployeeInterface {

  private Employee currentEmployee = null;
  private Inventory inventory = null;

  /**
   * @param inventory
   */
  public EmployeeInterface(Inventory inventory) {
    // TODO Auto-generated constructor stub
    // if (employee.authenticate(password)) {
    if (true) {
      this.inventory = inventory;
    }
  }

  /**
   * @param employee
   * @param inventory
   */
  public EmployeeInterface(Employee employee, Inventory inventory) {
    // TODO Auto-generated constructor stub
    this.currentEmployee = employee;
    this.inventory = inventory;
  }


  /**
   * @param currentEmployee
   */
  public void setCurrentEmployee(Employee currentEmployee) {
    this.currentEmployee = currentEmployee;
  }

  /**
   * @return
   */
  public boolean hasCurrentEmployee() {
    return currentEmployee != null;
  }

  /**
   * @param name
   * @param age
   * @param address
   * @param password
   * @return
   * @throws InvalidUserParameterException
   * @throws BadValueException
   * @throws SQLException
   * @throws DatabaseInsertException
   * @throws DatabaseContainsInvalidDataException
   * @throws DataNotFoundException
   */
  public int createCustomer(String name, int age, String address, String password)
      throws InvalidUserParameterException, DatabaseInsertException, SQLException,
      BadValueException, DataNotFoundException, DatabaseContainsInvalidDataException {
    int id = DatabaseInsertHelper.insertNewUser(name, age, address, password);
    DatabaseInsertHelper.insertUserRole(id, DatabaseSelectHelper.getRoleIds().get(2));
    return id;
  }

  /**
   * @param name
   * @param age
   * @param address
   * @param password
   * @return
   * @throws InvalidUserParameterException
   * @throws BadValueException
   * @throws SQLException
   * @throws DatabaseInsertException
   * @throws DatabaseContainsInvalidDataException
   * @throws DataNotFoundException
   */
  public int createEmployee(String name, int age, String address, String password)
      throws InvalidUserParameterException, DatabaseInsertException, SQLException,
      BadValueException, DataNotFoundException, DatabaseContainsInvalidDataException {
    int id = DatabaseInsertHelper.insertNewUser(name, age, address, password);
    DatabaseInsertHelper.insertUserRole(id, DatabaseSelectHelper.getRoleIds().get(1));
    return id;
  }

  /**
   * @param item
   * @param quantity
   * @return
   * @throws SQLException 
   */
  public boolean restockInventory(Item item, int quantity) throws SQLException {
    inventory.updateMap(item, quantity);
    return DatabaseUpdateHelper.updateInventoryQuantity(quantity, item.getId());
  }


}
