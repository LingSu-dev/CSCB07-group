package com.example.cscb07_app.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Spinner;
import com.example.cscb07_app.Activity.Admin.Admin;
import com.example.cscb07_app.Activity.Customer.CustomerStore;
import com.example.cscb07_app.Activity.Employee.EmployeeMenu;
import com.example.cscb07_app.Activity.Login.LoginMenu;
import com.example.cscb07_app.R;

public class LoginController implements View.OnClickListener {

  private Context appContext;

  public LoginController(Context context) {
    this.appContext = context;
  }

  @Override
  public void onClick(View v) {
    Spinner mySpinner = ((LoginMenu) appContext).findViewById(R.id.rolePositionEntry);
    String rolePosition = mySpinner.getSelectedItem().toString();

    if (rolePosition.equals("Admin")) {
      appContext.startActivity(new Intent(this.appContext, Admin.class));
    } else if (rolePosition.equals("Employee")) {
      appContext.startActivity(new Intent(this.appContext, EmployeeMenu.class));
    } else if (rolePosition.equals("Customer")) {
      appContext.startActivity(new Intent(this.appContext, CustomerStore.class));
    }
  }
}
