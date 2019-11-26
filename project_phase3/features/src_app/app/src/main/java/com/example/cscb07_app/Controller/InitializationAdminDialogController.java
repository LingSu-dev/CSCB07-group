package com.example.cscb07_app.Controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import com.example.cscb07_app.Activity.Initialization.InitializationCreateFirstEmployee;

public class InitializationAdminDialogController implements DialogInterface.OnClickListener {

  private Context appContext;

  public InitializationAdminDialogController(Context context) {
    this.appContext = context;
  }

  @Override
  public void onClick(DialogInterface dialog, int which) {
    appContext.startActivity(new Intent(appContext, InitializationCreateFirstEmployee.class));
  }
}
