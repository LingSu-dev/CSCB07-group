package com.b07.users;
import com.b07.security.PasswordHelpers;

import java.sql.SQLException;

import com.b07.database.helper.DatabaseSelectHelper;

public abstract class User {

  private int id;
  private int age;
  private int roleId;
  private String name;
  private String address;
  private boolean authenticated;

  /**
   *  @returns the id of this user
   */
  public int getId() {
    return this.id;
  }
  
  /**
   *  sets the id of this user
   */
  public void setId(int id) {
    this.id = id;
  }
  
  /**
   *  @returns the id of this user
   */
  public int getAge() {
    return this.age;
  }
    
  /**
   *  @returns the role id of this user
   */
  public int getRoleId() {
    return this.roleId;
  }

  /**
   *  sets the id of this user
   */
  public void setAge(int age) {
    this.age = age;
  }
  
  /**
   *  @returns the name of this user
   */
  public String getName() {
    return this.name;
  }
  
  /**
   *  sets the name of this user
   */
  public void setName(String name) {
    this.name = name;
  }
    
  /**
   *  @returns the address of this user
   */
  public String getAddress() {
    return this.address;
  }
  
  /**
   *  sets the address of this user
   */
  public void setAddress(String address) {
    this.address = address;
  }
  
  /**
   * This method takes in a password in plain text, 
   * and validates if it matches the password found in the database for the given user
   * @throws SQLException 
   */
  public final boolean authenticate(String password) throws SQLException {
    this.authenticated = PasswordHelpers.comparePassword(DatabaseSelectHelper.getPassword(this.id), password);
    return this.authenticated;
  }
}
