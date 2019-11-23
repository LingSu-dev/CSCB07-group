package com.example.cscb07_app.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.example.cscb07_app.Activity.Employee.EmployeeAuthenticateEmployee;
import com.example.cscb07_app.Activity.Employee.EmployeeMakeAccount;
import com.example.cscb07_app.Activity.Employee.EmployeeMakeEmployee;
import com.example.cscb07_app.Activity.Employee.EmployeeMakeUser;
import com.example.cscb07_app.Activity.Employee.EmployeeRestockInventory;
import com.example.cscb07_app.Activity.Login.LoginMenu;
import com.example.cscb07_app.R;

public class EmployeeController implements View.OnClickListener {

  private Context appContext;

  public EmployeeController(Context context) {
    this.appContext = context;
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.menuAuthenticateEmployeeBtn:
        appContext.startActivity(new Intent(this.appContext, EmployeeAuthenticateEmployee.class));
        break;
      case R.id.menuMakeNewUserBtn:
        appContext.startActivity(new Intent(this.appContext, EmployeeMakeUser.class));
        break;
      case R.id.menuMakeNewAccountBtn:
        appContext.startActivity(new Intent(this.appContext, EmployeeMakeAccount.class));
        break;
      case R.id.menuMakeNewEmployeeBtn:
        appContext.startActivity(new Intent(this.appContext, EmployeeMakeEmployee.class));
        break;
      case R.id.menuRestockInventoryBtn:
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
        //TODO: add functionality
        break;
      case R.id.makeUserBtn:
        //TODO: add functionality
        break;
      case R.id.restockInventoryBtn:
        //TODO: add functionality
        break;
    }
  }
}
