package com.example.cscb07_app.Controller;

import android.content.Context;
import android.view.View;
import com.example.cscb07_app.Activity.Admin.Admin;
import com.example.cscb07_app.R;

public class AdminController implements View.OnClickListener {

  private Context appContext;


  public AdminController(Context context) {
    this.appContext = context;
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.adminExitBtn:
        ((Admin) appContext).onBackPressed();
        break;
    }
  }
}
