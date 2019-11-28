package com.example.cscb07_app.Controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import com.example.cscb07_app.Activity.Login.LoginMenu;

public class InitializationEmployeeDialogController implements DialogInterface.OnClickListener {

  private Context appContext;

  public InitializationEmployeeDialogController(Context context) {
    this.appContext = context;
  }

  @Override
  public void onClick(DialogInterface dialog, int which) {
    appContext.startActivity(new Intent(appContext, LoginMenu.class));
  }
}
