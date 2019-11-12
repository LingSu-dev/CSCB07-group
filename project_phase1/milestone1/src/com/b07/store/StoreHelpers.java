package com.b07.store;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.exceptions.DataNotFoundException;
import com.b07.exceptions.DatabaseContainsInvalidDataException;
import com.b07.exceptions.UserNotFoundException;
import com.b07.users.Roles;
import com.b07.users.User;

public class StoreHelpers {
  
  /**
   * check whether password matches a user's password
   * @param role
   * @param id
   * @param password
   * @return whether the login was successful
   * @throws DatabaseContainsInvalidDataException
   * @throws SQLException
   * @throws DataNotFoundException
   * @throws UserNotFoundException
   */
  public static final User login(Roles role, int id, String password) throws DatabaseContainsInvalidDataException, SQLException, DataNotFoundException, UserNotFoundException {
    User user = DatabaseSelectHelper.getUserDetails(id);
    if(user == null) {
      return null;
    }
    int roleId = user.getRoleId();
    if (!Roles.valueOf(DatabaseSelectHelper.getRoleName(roleId)).equals(role)) {
      throw new UserNotFoundException();
    }
    if (user.authenticate(password)) {
      return user;
    }
    return null;
  }
  
  /**
   * Creates a prompt for a user to login with their ID and password, to the given role.
   * @param reader
   * @param role
   * @return
   * @throws IOException
   */
  public static final User loginPrompt(BufferedReader reader, Roles role) throws IOException {
    System.out.println("Input your ID");
    int id;
    String password;
    try {
      id = Integer.parseInt(reader.readLine());
      System.out.println("Input your password");
      password = reader.readLine();
      User user = login(role, id, password);
      if (user == null) {
        System.out.println("Incorrect password");
        return null;
      }
      return user;
    } catch (NumberFormatException e) {
      System.out.println("Unable to read ID. Please type only the number");
    } catch (DatabaseContainsInvalidDataException e) {
      System.out.println("The user with the given id is not stored properly in the database");
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (DataNotFoundException e) {
      System.out.println("The user could not be found.");
      e.printStackTrace();
    } catch (UserNotFoundException e) {
      System.out.println("User not found. Are you logging in with the correct role?");
    } 
    return null;
  }
  
  /**
   * This method presents the user with a list of choices and returns the user's choice as a int
   * @param choices an array of choices as strings that will be printed for the user
   * @param reader a reader to get the user's input from
   * @return the user's choice
   */
  public static int choiceDialog(String[] choices, BufferedReader reader) {
    for(String string: choices) {
      System.out.println(string);
    }
    int response = -1;
    try {
      response = Integer.parseInt(reader.readLine());
    } catch (NumberFormatException e) {
      System.out.println("Please choose a number.")
    }
    return response;
  }

}
