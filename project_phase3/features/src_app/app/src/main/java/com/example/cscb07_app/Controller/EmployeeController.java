package com.example.cscb07_app.Controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.b07.database.helper.DatabaseHelperAdapter;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.users.Roles;
import com.example.cscb07_app.Activity.Employee.EmployeeAuthenticateEmployee;
import com.example.cscb07_app.Activity.Employee.EmployeeMakeAccount;
import com.example.cscb07_app.Activity.Employee.EmployeeMakeEmployee;
import com.example.cscb07_app.Activity.Employee.EmployeeMakeUser;
import com.example.cscb07_app.Activity.Employee.EmployeeRestockInventory;
import com.example.cscb07_app.Activity.Login.LoginMenu;
import com.example.cscb07_app.R;

import java.sql.SQLException;

public class EmployeeController implements View.OnClickListener {

  private Context appContext;

  public EmployeeController(Context context) {
    this.appContext = context;
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.menuAuthenticateEmployeeBtn:
        //TODO
        appContext.startActivity(new Intent(this.appContext, EmployeeAuthenticateEmployee.class));
        break;
      case R.id.menuMakeNewUserBtn:
        appContext.startActivity(new Intent(this.appContext, EmployeeMakeUser.class));
        break;
      case R.id.menuMakeNewAccountBtn:
        //TODO
        appContext.startActivity(new Intent(this.appContext, EmployeeMakeAccount.class));
        break;
      case R.id.menuMakeNewEmployeeBtn:
        appContext.startActivity(new Intent(this.appContext, EmployeeMakeEmployee.class));
        break;
      case R.id.menuRestockInventoryBtn:
        //TODO
        appContext.startActivity(new Intent(this.appContext, EmployeeRestockInventory.class));
        break;
      case R.id.menuExitBtn:
        appContext.startActivity(new Intent(this.appContext, LoginMenu.class));
        break;
      case R.id.authenticateEmployeeBtn:
        //TODO: add functionality
        break;
      case R.id.makeAccountBtn:
        //TODO: add functionality
        break;
      case R.id.makeEmployeeBtn:

        AlertDialog employeeAgeAlertDialog = new AlertDialog.Builder(appContext).create();
        employeeAgeAlertDialog.setTitle("Age Format Error");
        employeeAgeAlertDialog.setMessage("Age cannot be empty!");
        employeeAgeAlertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                new DialogController(appContext, DialogId.AGE_EMPTY_DIALOG));

        String employeeName;
        int employeeAge = 0;
        String employeeAddress;
        String employeePassword;

        EditText employeeNameEntry;
        EditText employeeAgeEntry;
        EditText employeeAddressEntry;
        EditText employeePasswordEntry;

        boolean employeeAgeValid = true;
        employeeAgeEntry = ((Activity) appContext).findViewById(R.id.makeEmployeeAgeEntry);

        try {
          employeeAge = Integer.parseInt(employeeAgeEntry.getText().toString());
        } catch (NumberFormatException e) {
          employeeAgeValid = false;
        }

        if (employeeAgeValid) {
          employeeNameEntry = ((Activity) appContext)
                  .findViewById(R.id.makeEmployeeNameEntry);
          employeeName = employeeNameEntry.getText().toString();

          employeeAddressEntry = ((Activity) appContext)
                  .findViewById(R.id.makeEmployeeAddressEntry);
          employeeAddress = employeeAddressEntry.getText().toString();

          employeePasswordEntry = ((Activity) appContext)
                  .findViewById(R.id.makeEmployeePassword);
          employeePassword = employeePasswordEntry.getText().toString();

          int employeeId = insertEmployee(employeeName, employeeAge, employeeAddress, employeePassword);

          AlertDialog employeeAlertDialog = new AlertDialog.Builder(appContext).create();
          employeeAlertDialog.setTitle("Employee Details");
          employeeAlertDialog.setMessage("Employee Id: " + employeeId);
          employeeAlertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Continue",
                  new DialogController(appContext, DialogId.CREATE__NEW_USER_DETAILS));
          employeeAlertDialog.show();
        } else {
          employeeAgeAlertDialog.show();
        }

        break;
      case R.id.makeUserBtn:

        AlertDialog customerAgeAlertDialog = new AlertDialog.Builder(appContext).create();
        customerAgeAlertDialog.setTitle("Age Format Error");
        customerAgeAlertDialog.setMessage("Age cannot be empty!");
        customerAgeAlertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ok",
                new DialogController(appContext, DialogId.AGE_EMPTY_DIALOG));

        String customerName;
        int customerAge = 0;
        String customerAddress;
        String customerPassword;

        EditText customerNameEntry;
        EditText customerAgeEntry;
        EditText customerAddressEntry;
        EditText customerPasswordEntry;

        boolean userAgeValid = true;
        customerAgeEntry = ((Activity) appContext).findViewById(R.id.makeUserAgeEntry);

        try {
          customerAge = Integer.parseInt(customerAgeEntry.getText().toString());
        } catch (NumberFormatException e) {
          userAgeValid = false;
        }

        if (userAgeValid) {
          customerNameEntry = ((Activity) appContext)
                  .findViewById(R.id.makeUserNameEntry);
          customerName = customerNameEntry.getText().toString();

          customerAddressEntry = ((Activity) appContext)
                  .findViewById(R.id.makeUserAddressEntry);
          customerAddress = customerAddressEntry.getText().toString();

          customerPasswordEntry = ((Activity) appContext)
                  .findViewById(R.id.makeUserPassword);
          customerPassword = customerPasswordEntry.getText().toString();

          int userId = insertCustomer(customerName, customerAge, customerAddress, customerPassword);

          AlertDialog customerAlertDialog = new AlertDialog.Builder(appContext).create();
          customerAlertDialog.setTitle("Customer Details");
          customerAlertDialog.setMessage("Customer Id: " + userId);
          customerAlertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Continue",
                  new DialogController(appContext, DialogId.CREATE__NEW_USER_DETAILS));
          customerAlertDialog.show();
        } else {
          customerAgeAlertDialog.show();
        }
        break;
      case R.id.restockInventoryBtn:
        //TODO: add functionality
        break;
    }
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
      employeeId = DatabaseHelperAdapter.insertNewUser(name, age, address, password);
      employeeRoleId = DatabaseHelperAdapter.getRoleIdByName(Roles.EMPLOYEE.name());
      DatabaseHelperAdapter.insertUserRole(employeeId, employeeRoleId);
    } catch (DatabaseInsertException e) {
      //TODO: catch it later
    } catch (SQLException e) {
      //Will Never Occur, holdover from cross platform adapter structure.
    }
    return employeeId;
  }

  /**
   * Insert customer into database.
   *
   * @param name the name of the customer
   * @param age the age of the customer
   * @param address the address of the customer
   * @param password the password of the customer
   * @return employee's id
   */
  public int insertCustomer(String name, int age, String address, String password) {

    int customerRoleId = 0;
    int customerId = -1;

    try {
      customerId = DatabaseHelperAdapter.insertNewUser(name, age, address, password);
      customerRoleId = DatabaseHelperAdapter.getRoleIdByName(Roles.CUSTOMER.name());
      DatabaseHelperAdapter.insertUserRole(customerId, customerRoleId);
    } catch (DatabaseInsertException e) {
      //TODO: catch it later
    } catch (SQLException e) {
      //Will Never Occur, holdover from cross platform adapter structure.
    }
    return customerId;
  }
}
