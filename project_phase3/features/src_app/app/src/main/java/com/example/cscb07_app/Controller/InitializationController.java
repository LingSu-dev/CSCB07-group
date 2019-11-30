package com.example.cscb07_app.Controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import com.example.cscb07_app.R;
import com.b07.database.helper.DatabaseAndroidHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.users.Roles;
import java.sql.SQLException;

/**
 * Class that controls all actions for events on initialization activities.
 */
public class InitializationController implements View.OnClickListener {

  private Context appContext;
  private DatabaseAndroidHelper androidHelper;

  /**
   * Constructor for InitializationController.
   *
   * @param context the context it is in
   * @param helper the helper to connect with database methods
   */
  public InitializationController(Context context, DatabaseAndroidHelper helper) {
    this.appContext = context;
    this.androidHelper = helper;
  }

  /**
   * Performs actions based on events.
   *
   * @param view the view of interest
   */
  @Override
  public void onClick(View view) {
    AlertDialog ageAlertDialog = new AlertDialog.Builder(appContext).create();
    ageAlertDialog.setTitle("Age Format Error");
    ageAlertDialog.setMessage("Age cannot be empty!");
    ageAlertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
        new DialogController(appContext, DialogId.AGE_EMPTY_DIALOG));

    String name;
    int age = 0;
    String address;
    String password;

    EditText nameEntry;
    EditText ageEntry;
    EditText addressEntry;
    EditText passwordEntry;

    switch (view.getId()) {
      case R.id.initializationCreateAdminButton:

        boolean adminAgeValid = true;

        ageEntry = ((Activity) appContext).findViewById(R.id.initializationAdminAgeEntry);

        try {
          age = Integer.parseInt(ageEntry.getText().toString()); //Potential Number format exception
        } catch (NumberFormatException e) {
          adminAgeValid = false;
        }

        if (adminAgeValid) {
          nameEntry = ((Activity) appContext)
              .findViewById(R.id.initializationAdminNameEntry);

          name = nameEntry.getText().toString();

          addressEntry = ((Activity) appContext)
              .findViewById(R.id.initializationAdminAddressEntry);
          address = addressEntry.getText().toString();

          passwordEntry = ((Activity) appContext)
              .findViewById(R.id.initializationAdminPassword);
          password = passwordEntry.getText().toString();

          int adminId = insertAdmin(name, age, address, password);

          AlertDialog adminAlertDialog = new AlertDialog.Builder(appContext).create();
          adminAlertDialog.setTitle("Admin Details");
          adminAlertDialog.setMessage("Admin Id: " + adminId);
          adminAlertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Continue",
              new DialogController(appContext, DialogId.CREATE_FIRST_ADMIN_DETAILS));
          adminAlertDialog.show();
        } else {
          ageAlertDialog.show();
        }
        break;
      case R.id.initializationCreateEmployeeButton:
        boolean employeeAgeValid = true;
        ageEntry = ((Activity) appContext).findViewById(R.id.initializationEmployeeAgeEntry);

        try {
          age = Integer.parseInt(ageEntry.getText().toString());
        } catch (NumberFormatException e) {
          employeeAgeValid = false;
        }

        if (employeeAgeValid) {
          nameEntry = ((Activity) appContext)
              .findViewById(R.id.initializationEmployeeNameEntry);
          name = nameEntry.getText().toString();

          addressEntry = ((Activity) appContext)
              .findViewById(R.id.initializationEmployeeAddressEntry);
          address = addressEntry.getText().toString();

          passwordEntry = ((Activity) appContext)
              .findViewById(R.id.initializationEmployeePassword);
          password = passwordEntry.getText().toString();

          int employeeId = insertEmployee(name, age, address, password);

          AlertDialog employeeAlertDialog = new AlertDialog.Builder(appContext).create();
          employeeAlertDialog.setTitle("Employee Details");
          employeeAlertDialog.setMessage("Employee Id: " + employeeId);
          employeeAlertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Continue",
              new DialogController(appContext, DialogId.CREATE_FIRST_EMPLOYEE_DETAILS));
          employeeAlertDialog.show();
        } else {
          ageAlertDialog.show();
        }
        break;
    }
  }

  /**
   * Insert admin into database.
   *
   * @param name the name of the admin
   * @param age the age of the admin
   * @param address the address of the admin
   * @param password the password of the admin
   * @return admins's id
   */
  public int insertAdmin(String name, int age, String address, String password) {

    int adminRoleId = 0;
    int adminId = -1;

    try {
      adminId = androidHelper.insertNewUser(name, age, address, password);
      adminRoleId = androidHelper.getRoleIdByName(Roles.ADMIN.name());
      androidHelper.insertUserRole(adminId, adminRoleId);
    } catch (DatabaseInsertException e) {
      //TODO: catch it later
    } catch (SQLException e) {
    }

    return adminId;
  }

  /**
   * Insert employee into database.
   *
   * @param name the name of the employee
   * @param age the age of the employee
   * @param address the address of the employee
   * @param password the password of the employee
   * @return employee's id
   */
  public int insertEmployee(String name, int age, String address, String password) {

    int employeeRoleId = 0;
    int employeeId = -1;

    try {
      employeeId = androidHelper.insertNewUser(name, age, address, password);
      employeeRoleId = androidHelper.getRoleIdByName(Roles.EMPLOYEE.name());
      androidHelper.insertUserRole(employeeId, employeeRoleId);
    } catch (DatabaseInsertException e) {
      //TODO: catch it later
    } catch (SQLException e) {
    }
    return employeeId;
  }
}