package com.example.cscb07_app.Controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.cscb07_app.Activity.Admin.AdminMenu;
import com.example.cscb07_app.Activity.Customer.CustomerStore;
import com.example.cscb07_app.Activity.Employee.EmployeeMenu;
import com.example.cscb07_app.Activity.Login.LoginMenu;
import com.example.cscb07_app.R;
import com.example.cscb07_app.Src.database.helper.DatabaseAndroidHelper;
import com.example.cscb07_app.Src.users.Admin;
import com.example.cscb07_app.Src.users.Customer;
import com.example.cscb07_app.Src.users.Employee;
import com.example.cscb07_app.Src.users.User;
import java.sql.SQLException;

/**
 * Class that controls all actions for events on login activity
 */
public class LoginController implements View.OnClickListener {

  private Context appContext;
  private DatabaseAndroidHelper androidHelper;

  /**
   * Constructor for LoginController.
   *
   * @param context the context it is called in
   * @param androidHelper the helper to access database methods
   */
  public LoginController(Context context, DatabaseAndroidHelper androidHelper) {
    this.appContext = context;
    this.androidHelper = androidHelper;
  }

  /**
   * Performs actions based on events in login activity.
   *
   * @param v the view
   */
  @Override
  public void onClick(View v) {
    Spinner mySpinner = ((LoginMenu) appContext).findViewById(R.id.rolePositionEntry);
    String rolePosition = mySpinner.getSelectedItem().toString();

    EditText userIdEntry = ((Activity) appContext).findViewById(R.id.loginUserIdEntry);
    EditText passwordEntry = ((Activity) appContext).findViewById(R.id.loginPassword);

    AlertDialog loginIncorrectCredentialDialog = new AlertDialog.Builder(appContext).create();
    loginIncorrectCredentialDialog.setTitle("Incorrect Credentials");
    loginIncorrectCredentialDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ok",
        new DialogController(appContext, DialogId.LOGIN_INCORRECT_CREDENTIALS));

    boolean userIdValid = true;
    int userId = 0;

    try {
      userId = Integer.parseInt(userIdEntry.getText().toString());
    } catch (NumberFormatException e) {
      userIdValid = false;
    }

    if (userIdValid) {
      String password = passwordEntry.getText().toString();
      if (rolePosition.equals("Admin")) {
        adminLogin(userId, password, loginIncorrectCredentialDialog);
      } else if (rolePosition.equals("Employee")) {
        employeeLogin(userId, password, loginIncorrectCredentialDialog);
      } else if (rolePosition.equals("Customer")) {
        customerLogin(userId,password, loginIncorrectCredentialDialog);
      }
    } else {
      loginIncorrectCredentialDialog.setTitle("User Id Format Error");
      loginIncorrectCredentialDialog.setMessage("User id can't be empty!");
      loginIncorrectCredentialDialog.show();
    }
  }


  /**
   * Authenticates admin and logs them in.
   *
   * @param userId the admin's user id
   * @param password the admin's password
   * @param loginIncorrectCredentialDialog alert dialog to be displayed on failure
   */
  public void adminLogin(int userId, String password, AlertDialog loginIncorrectCredentialDialog) {
    boolean authenticated = true;

    try {
      User currentUser = androidHelper.getUserDetails(userId);

      if (userId < 1 || currentUser == null || !(currentUser instanceof Admin)) {
        authenticated = false;
      } else {
        Admin admin = (Admin) androidHelper.getUserDetails(userId);
        authenticated = admin.authenticate(password);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    if (authenticated) {
      appContext.startActivity(new Intent(this.appContext, AdminMenu.class));
    } else {
      loginIncorrectCredentialDialog
          .setMessage("Double check your id and password and make sure its an admin account!");
      loginIncorrectCredentialDialog.show();
    }
  }


  /**
   * Authenticates customer and logs them in.
   *
   * @param userId the customer's user id
   * @param password the customer's password
   * @param loginIncorrectCredentialDialog alert dialog to be displayed on failure
   */
  public void customerLogin(int userId, String password,
      AlertDialog loginIncorrectCredentialDialog) {
    boolean authenticated = true;
    try {
      User currentUser = androidHelper.getUserDetails(userId);

      if (userId < 1 || currentUser == null || !(currentUser instanceof Customer)) {
        authenticated = false;
      } else {
        Customer customer = (Customer) androidHelper.getUserDetails(userId);
        authenticated = customer.authenticate(password);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    if (authenticated) {
      appContext.startActivity(new Intent(this.appContext, CustomerStore.class));
    } else {
      loginIncorrectCredentialDialog
          .setMessage(
              "Double check your id and password and make sure its a customer account!");
      loginIncorrectCredentialDialog.show();
    }
  }

  /**
   * Authenticates employee and logs them in.
   *
   * @param userId the employee's user id
   * @param password the employee's password
   * @param loginIncorrectCredentialDialog alert dialog to be displayed on failure
   */
  public void employeeLogin(int userId, String password,
      AlertDialog loginIncorrectCredentialDialog) {
    Employee currentEmployee = null;
    boolean authenticated = false;

    try {
      User currentUser = androidHelper.getUserDetails(userId);

      if (userId < 1 || currentUser == null || !(currentUser instanceof Employee)) {
        authenticated = false;
      } else {
        currentEmployee = (Employee) androidHelper.getUserDetails(userId);
        authenticated = currentEmployee.authenticate(password);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    Intent employeeIntent = new Intent(this.appContext, EmployeeMenu.class);

    if (authenticated) {
      //employeeIntent.putExtra("employee", currentEmployee);
      appContext.startActivity(employeeIntent);
    } else {
      loginIncorrectCredentialDialog
          .setMessage(
              "Double check your id and password and make sure its an employee account!");
      loginIncorrectCredentialDialog.show();
    }
  }
}
