package com.example.cscb07_app.Controller;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import com.example.cscb07_app.R;

public class InitializationController implements View.OnClickListener {

  private Context appContext;

  public InitializationController(Context context) {
    this.appContext = context;
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.initializationCreateAdminButton:
        AlertDialog adminAlertDialog = new AlertDialog.Builder(appContext).create();
        adminAlertDialog.setTitle("Admin Details");
        adminAlertDialog.setMessage("Admin Id: 0");
        adminAlertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Continue",
            new InitializationAdminDialogController(appContext));
        adminAlertDialog.show();
        break;
      case R.id.initializationCreateEmployeeButton:
        AlertDialog employeeAlertDialog = new AlertDialog.Builder(appContext).create();
        employeeAlertDialog.setTitle("Employee Details");
        employeeAlertDialog.setMessage("Employee Id: 1");
        employeeAlertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Continue",
            new InitializationEmployeeDialogController(appContext));
        employeeAlertDialog.show();
        break;
    }
  }
}