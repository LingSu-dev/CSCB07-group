package com.example.cscb07_app.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.example.cscb07_app.Activity.Admin.Admin;
import com.example.cscb07_app.Activity.Admin.AdminViewBooks;
import com.example.cscb07_app.R;

public class AdminController implements View.OnClickListener {

  private Context appContext;


  public AdminController(Context context) {
    this.appContext = context;
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.adminLogoutBtn:
        ((Admin) appContext).onBackPressed();
        break;
      case R.id.viewBooksBtn:
        appContext.startActivity(new Intent(this.appContext, AdminViewBooks.class));
    }
  }
}
