package com.example.cscb07_app.Controller;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import com.b07.database.helper.DatabaseHelperAdapter;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.serialize.SerializeDatabase;
import com.b07.users.Admin;
import com.example.cscb07_app.Activity.Admin.AdminCreateCoupon;
import com.example.cscb07_app.Activity.Admin.AdminLoadAppData;
import com.example.cscb07_app.Activity.Admin.AdminPromoteEmployee;
import com.example.cscb07_app.Activity.Admin.AdminSaveAppData;
import com.example.cscb07_app.Activity.Admin.AdminViewActiveAccounts;
import com.example.cscb07_app.Activity.Admin.AdminViewBooks;
import com.example.cscb07_app.Activity.Admin.AdminViewHistoricAccounts;
import com.example.cscb07_app.Activity.Login.LoginMenu;
import com.example.cscb07_app.R;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public class AdminController implements View.OnClickListener {

  private Context appContext;
  private static Admin admin;
  public AdminController(Context context) {
    this.appContext = context;
  }
  public AdminController(Context context, Admin a) {
    this.appContext = context;
    admin = a;
  }
  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.adminMenuExitBtn:
        appContext.startActivity(new Intent(this.appContext, LoginMenu.class));
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
      case R.id.addCouponBtn:
        Activity context = (Activity)appContext;
        EditText couponCodeEntry = context.findViewById(R.id.couponCodeEntry);
        String couponCode = couponCodeEntry.getText().toString();

        Spinner couponTypeEntry = context.findViewById(R.id.couponTypeEntry);
        String couponType = couponTypeEntry.getSelectedItem().toString();

        EditText couponDiscountEntry = context.findViewById(R.id.couponDiscountEntry);
        String couponDiscount = couponDiscountEntry.getText().toString();

        EditText couponItemIdEntry = context.findViewById(R.id.couponDiscountEntry);

        EditText couponQuantityEntry = context.findViewById(R.id.couponDiscountEntry);
        int couponItemId = -1;
        int quantity = -1;
        BigDecimal couponDiscountDecimal = BigDecimal.ZERO;
        boolean valid = false;
        try {
          couponDiscountDecimal = new BigDecimal(couponDiscount);
          couponItemId = Integer.parseInt(couponItemIdEntry.getText().toString());
          quantity = Integer.parseInt(couponQuantityEntry.getText().toString());
          valid = true;
        } catch (NumberFormatException e) {
          AlertDialog parseFailDialog = new AlertDialog.Builder(appContext)
                  .setTitle("Incorrect input")
                  .setMessage("Please enter a number")
                  .create();
          parseFailDialog.show();
        }
        if(valid){
          try {
            DatabaseHelperAdapter.insertCoupon(couponItemId, quantity, couponType, couponDiscountDecimal, couponCode);
          } catch (SQLException | DatabaseInsertException e) {
            AlertDialog insertFailDialog = new AlertDialog.Builder(appContext)
                    .setTitle("Failed to add coupon")
                    .setMessage("An error occurred when adding the coupon to the database")
                    .create();
            insertFailDialog.show();
          }
          AlertDialog successDialog = new AlertDialog.Builder(appContext)
                  .setTitle("Success!")
                  .setMessage("The coupon has been added to the database")
                  .create();
          successDialog.show();
        }
        break;
      case R.id.saveDataBtn:
        EditText saveLoc = ((Activity) appContext).findViewById(R.id.saveAppDataEntry);
        String saveLocString = saveLoc.getText().toString();
        try {
          SerializeDatabase.serializeToFile(saveLocString);
        } catch (IOException e){
          DialogFactory.createAlertDialog(appContext, "Error!",
                  "Could not save data to this location!",
                  "Ok", DialogId.NULL_DIALOG).show();
        } catch (SQLException e) {
          DialogFactory.createAlertDialog(appContext, "Error!",
                  "There was an issue with the SQL database!",
                  "Ok", DialogId.NULL_DIALOG).show();
        }

          break;
    }
  }
}
