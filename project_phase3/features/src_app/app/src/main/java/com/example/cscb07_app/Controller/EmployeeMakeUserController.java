package com.example.cscb07_app.Controller;

import com.b07.database.helper.DatabaseHelperAdapter;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.users.Roles;
import com.example.cscb07_app.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;

import java.sql.SQLException;

public class EmployeeMakeUserController implements View.OnClickListener {
    private Context appContext;

    public EmployeeMakeUserController(Context context){
        this.appContext = context;
    }
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
            case R.id.makeEmployeeBtn:
                boolean employeeAgeValid = true;
                ageEntry = ((Activity) appContext).findViewById(R.id.makeEmployeeAgeEntry);

                try {
                    age = Integer.parseInt(ageEntry.getText().toString());
                } catch (NumberFormatException e) {
                    employeeAgeValid = false;
                }

                if (employeeAgeValid) {
                    nameEntry = ((Activity) appContext)
                            .findViewById(R.id.makeEmployeeNameEntry);
                    name = nameEntry.getText().toString();

                    addressEntry = ((Activity) appContext)
                            .findViewById(R.id.makeEmployeeAddressEntry);
                    address = addressEntry.getText().toString();

                    passwordEntry = ((Activity) appContext)
                            .findViewById(R.id.makeEmployeePassword);
                    password = passwordEntry.getText().toString();

                    int employeeId = insertEmployee(name, age, address, password);

                    AlertDialog employeeAlertDialog = new AlertDialog.Builder(appContext).create();
                    employeeAlertDialog.setTitle("Employee Details");
                    employeeAlertDialog.setMessage("Employee Id: " + employeeId);
                    employeeAlertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Continue",
                            new DialogController(appContext, DialogId.CREATE__NEW_USER_DETAILS));
                    employeeAlertDialog.show();
                } else {
                    ageAlertDialog.show();
                }
                break;
            case R.id.makeUserBtn:
                boolean userAgeValid = true;
                ageEntry = ((Activity) appContext).findViewById(R.id.makeUserAgeEntry);

                try {
                    age = Integer.parseInt(ageEntry.getText().toString());
                } catch (NumberFormatException e) {
                    userAgeValid = false;
                }

                if (userAgeValid) {
                    nameEntry = ((Activity) appContext)
                            .findViewById(R.id.makeUserNameEntry);
                    name = nameEntry.getText().toString();

                    addressEntry = ((Activity) appContext)
                            .findViewById(R.id.makeUserAddressEntry);
                    address = addressEntry.getText().toString();

                    passwordEntry = ((Activity) appContext)
                            .findViewById(R.id.makeUserPassword);
                    password = passwordEntry.getText().toString();

                    int userId = insertCustomer(name, age, address, password);

                    AlertDialog employeeAlertDialog = new AlertDialog.Builder(appContext).create();
                    employeeAlertDialog.setTitle("Customer Details");
                    employeeAlertDialog.setMessage("Customer Id: " + userId);
                    employeeAlertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Continue",
                            new DialogController(appContext, DialogId.CREATE__NEW_USER_DETAILS));
                    employeeAlertDialog.show();
                } else {
                    ageAlertDialog.show();
                }
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
