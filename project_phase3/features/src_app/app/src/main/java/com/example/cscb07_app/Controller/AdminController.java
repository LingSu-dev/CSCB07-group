package com.example.cscb07_app.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.example.cscb07_app.Activity.Admin.AdminCreateCoupon;
import com.example.cscb07_app.Activity.Admin.AdminLoadAppData;
import com.example.cscb07_app.Activity.Admin.AdminMenu;
import com.example.cscb07_app.Activity.Admin.AdminPromoteEmployee;
import com.example.cscb07_app.Activity.Admin.AdminSaveAppData;
import com.example.cscb07_app.Activity.Admin.AdminViewActiveAccounts;
import com.example.cscb07_app.Activity.Admin.AdminViewBooks;
import com.example.cscb07_app.Activity.Admin.AdminViewHistoricAccounts;
import com.example.cscb07_app.R;

public class AdminController implements View.OnClickListener {

  private Context appContext;


  public AdminController(Context context) {
    this.appContext = context;
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.adminMenuExitBtn:
        ((AdminMenu) appContext).onBackPressed();
        break;
      case R.id.adminMenuViewBooks:
        appContext.startActivity(new Intent(this.appContext, AdminViewBooks.class));
        break;
      case R.id.adminMenuPromoteEmployee:
        appContext.startActivity(new Intent(this.appContext, AdminPromoteEmployee.class));
        break;
      case R.id.adminMenuHistoricAccounts:
        appContext.startActivity(new Intent(this.appContext, AdminViewHistoricAccounts.class));
        break;
      case R.id.adminMenuActiveAccounts:
        appContext.startActivity(new Intent(this.appContext, AdminViewActiveAccounts.class));
        break;
      case R.id.adminMenuSaveData:
        appContext.startActivity(new Intent(this.appContext, AdminSaveAppData.class));
        break;
      case R.id.adminMenuLoadData:
        appContext.startActivity(new Intent(this.appContext, AdminLoadAppData.class));
        break;
      case R.id.adminMenuCreateCoupon:
        appContext.startActivity(new Intent(this.appContext, AdminCreateCoupon.class));
        break;

    }
  }
}
