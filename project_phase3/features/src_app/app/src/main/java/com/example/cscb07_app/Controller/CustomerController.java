package com.example.cscb07_app.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.example.cscb07_app.Activity.Customer.CustomerCheckout;
import com.example.cscb07_app.Activity.Login.LoginMenu;
import com.example.cscb07_app.R;

public class CustomerController implements View.OnClickListener {

  private Context appContext;

  public CustomerController(Context context) {
    this.appContext = context;
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.checkOutButton:
        //TODO: add functionality
        break;
      case R.id.customerStoreAddToCartBtn:
        //TODO: add functionality
        break;
      case R.id.customerStoreViewCartBtn:
        appContext.startActivity(new Intent(this.appContext, CustomerCheckout.class));
        break;
      case R.id.customerStoreLogoutBtn:
        appContext.startActivity(new Intent(this.appContext, LoginMenu.class));
        break;
    }
  }
}
