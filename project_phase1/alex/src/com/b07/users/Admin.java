/**
 * 
 */
package com.b07.users;

import java.sql.SQLException;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.database.helper.DatabaseUpdateHelper;
import com.b07.exceptions.DataNotFoundException;

/**
 * @author efimoval
 *
 */
public class Admin extends User {
  
  private int roleId;

  /**
   * 
   */
  public Admin(int id, String name, int age, String address) {
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
       this.roleId = DatabaseSelectHelper.getRoleId("ADMIN");
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * 
   */
  public Admin(int id, String name, int age, String address, boolean authenticated) {
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
       this.roleId = DatabaseSelectHelper.getRoleId("ADMIN");
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public boolean promoteEmployee(Employee employee) throws SQLException, DataNotFoundException {
    int adminId = DatabaseSelectHelper.getRoleId("ADMIN");
    return DatabaseUpdateHelper.updateUserRole(roleId, adminId);
  }
  
  @Override
  public int getRoleId() {
    return this.roleId;
  }

  

}
