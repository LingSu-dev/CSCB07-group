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

public class LoginController implements View.OnClickListener {

  private Context appContext;
  private DatabaseAndroidHelper androidHelper;

  public LoginController(Context context, DatabaseAndroidHelper androidHelper) {
    this.appContext = context;
    this.androidHelper = androidHelper;
  }

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

    int userId = Integer.parseInt(userIdEntry.getText().toString());
    String password = passwordEntry.getText().toString();
    boolean authenticated = false;

    if (rolePosition.equals("Admin")) {
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
    } else if (rolePosition.equals("Employee")) {
      try {
        User currentUser = androidHelper.getUserDetails(userId);

        if (userId < 1 || currentUser == null || !(currentUser instanceof Employee)) {
          authenticated = false;
        } else {
          Employee employee = (Employee) androidHelper.getUserDetails(userId);
          authenticated = employee.authenticate(password);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }

      if (authenticated) {
        appContext.startActivity(new Intent(this.appContext, EmployeeMenu.class));
      } else {
        loginIncorrectCredentialDialog
            .setMessage("Double check your id and password and make sure its an employee account!");
        loginIncorrectCredentialDialog.show();
      }
    } else if (rolePosition.equals("Customer")) {
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
            .setMessage("Double check your id and password and make sure its a customer account!");
        loginIncorrectCredentialDialog.show();
      }
    }
  }
}
