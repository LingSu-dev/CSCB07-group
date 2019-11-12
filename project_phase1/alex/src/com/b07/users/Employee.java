/**
 * 
 */
package com.b07.users;

import java.sql.SQLException;
import com.b07.database.helper.DatabaseSelectHelper;

/**
 * @author efimoval
 *
 */
public class Employee extends User {

  private int roleId;

  /**
   * 
   */
  public Employee(int id, String name, int age, String address) {

    super.setId(id);
    super.setAge(age);
    super.setName(name);
    super.setAddress(address);

    if (!address.equals(null)) {
      super.setAddress(address);
    } else {
      super.setAddress("");
    }
    try {
      this.roleId = DatabaseSelectHelper.getRoleId("EMPLOYEE");
   } catch (SQLException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
   }
  }

  /**
   * 
   */
  public Employee(int id, String name, int age, String address, boolean authenticated) {
    super.setId(id);
    super.setAge(age);
    super.setName(name);
    super.setAddress(address);

    if (!address.equals(null)) {
      super.setAddress(address);
    } else {
      super.setAddress("");
    }
    try {
      this.roleId = DatabaseSelectHelper.getRoleId("EMPLOYEE");
   } catch (SQLException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
   }
  }

  
  @Override
  public int getRoleId() {
    return this.roleId;
  }

}