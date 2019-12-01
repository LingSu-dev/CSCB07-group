package com.example.cscb07_app.Controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import com.example.cscb07_app.Activity.Initialization.InitializationCreateFirstEmployee;
import com.example.cscb07_app.Activity.Login.LoginMenu;

public class DialogController implements DialogInterface.OnClickListener {

  private Context appContext;
  private DialogId id;

  public DialogController(Context context, DialogId id) {
    this.appContext = context;
    this.id = id;
  }

  @Override
  public void onClick(DialogInterface dialog, int which) {
    switch(id){
      case CREATE_FIRST_ADMIN_DETAILS:
        appContext.startActivity(new Intent(appContext, InitializationCreateFirstEmployee.class));
        break;
      case CREATE_FIRST_EMPLOYEE_DETAILS:
        appContext.startActivity(new Intent(appContext, LoginMenu.class));
        break;
      case LOGIN_INCORRECT_CREDENTIALS:
        break;
      case AGE_EMPTY_DIALOG:
        break;
      case CREATE_COUPON_DIALOG:
        appContext.startActivity(new Intent(appContext, AdminController.class));
        break;
    }
  }
}
