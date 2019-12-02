package com.example.cscb07_app.Controller;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.b07.database.helper.DatabaseHelperAdapter;
import com.b07.inventory.Item;
import com.example.cscb07_app.R;
import java.sql.SQLException;

/**
 * Controls all the buttons in checkout
 */
public class CheckoutButtonController implements View.OnClickListener {

  private Context appContext;

  /**
   * Constructor for a checkout button, takes in current app context
   * @param context of app
   */
  public CheckoutButtonController(Context context) {
    this.appContext = context;
  }

  /**
   * onClick method for controlling a button to add/subtract from the amount
   * displayed by the textViews. Button only add/subtract if there are enough
   * of an item within inventory/>0 item in cart. 
   * 
   * @param button
   */
  @Override
  public void onClick(View view) {

    int temp = 0;

    TextView amount1 = ((Activity) appContext).findViewById(R.id.amount1);
    TextView amount2 = ((Activity) appContext).findViewById(R.id.amount2);
    TextView amount3 = ((Activity) appContext).findViewById(R.id.amount3);
    TextView amount4 = ((Activity) appContext).findViewById(R.id.amount4);
    TextView amount5 = ((Activity) appContext).findViewById(R.id.amount5);

    TextView item1 = ((Activity) appContext).findViewById(R.id.cartItem1);
    TextView item2 = ((Activity) appContext).findViewById(R.id.cartItem2);
    TextView item3 = ((Activity) appContext).findViewById(R.id.cartItem3);
    TextView item4 = ((Activity) appContext).findViewById(R.id.cartItem4);
    TextView item5 = ((Activity) appContext).findViewById(R.id.cartItem5);

    String item1Name = item1.getText().toString();
    String item2Name = item2.getText().toString();
    String item3Name = item3.getText().toString();
    String item4Name = item4.getText().toString();
    String item5Name = item5.getText().toString();

    switch (view.getId()) {
      case R.id.plus1:
        temp = Integer.parseInt(amount1.getText().toString()) + 1;
        if (temp <= getMaxQuantity(item1Name)) {
          amount1.setText(String.valueOf(temp));
        }
        break;
      case R.id.plus2:
        temp = Integer.parseInt(amount2.getText().toString()) + 1;
        if (temp <= getMaxQuantity(item2Name)) {
          amount2.setText(String.valueOf(temp));
        }
        break;
      case R.id.plus3:
        temp = Integer.parseInt(amount3.getText().toString()) + 1;
        if (temp <= getMaxQuantity(item3Name)) {
          amount3.setText(String.valueOf(temp));
        }
        break;
      case R.id.plus4:
        temp = Integer.parseInt(amount4.getText().toString()) + 1;
        if (temp <= getMaxQuantity(item4Name)) {
          amount4.setText(String.valueOf(temp));
        }
        break;
      case R.id.plus5:
        temp = Integer.parseInt(amount5.getText().toString()) + 1;
        if (temp <= getMaxQuantity(item5Name)) {
          amount5.setText(String.valueOf(temp));
        }
        break;
      case R.id.minus1:
        temp = Integer.parseInt(amount1.getText().toString()) - 1;
        if (temp >= 0) {
          amount1.setText(String.valueOf(temp));
        }
        break;
      case R.id.minus2:
        temp = Integer.parseInt(amount2.getText().toString()) - 1;
        if (temp >= 0) {
          amount2.setText(String.valueOf(temp));
        }
        break;
      case R.id.minus3:
        temp = Integer.parseInt(amount3.getText().toString()) - 1;
        if (temp >= 0) {
          amount3.setText(String.valueOf(temp));
        }
        break;
      case R.id.minus4:
        temp = Integer.parseInt(amount4.getText().toString()) - 1;
        if (temp >= 0) {
          amount4.setText(String.valueOf(temp));
        }
        break;
      case R.id.minus5:
        temp = Integer.parseInt(amount5.getText().toString()) - 1;
        if (temp >= 0) {
          amount5.setText(String.valueOf(temp));
        }
        break;
    }
  }

  /**
   * gets the max quantity of an item
   * 
   * @param item
   * @return max quantity
   */
  public int getMaxQuantity(String itemName) {
    int max = 0;
    try {
      for (int x = 1; x < 6; x++) {
        Item item = DatabaseHelperAdapter.getItem(x);
        if (item.getName().equals(itemName)) {
          max = DatabaseHelperAdapter.getInventoryQuantity(x);
        }
      }
    } catch (SQLException e) {
    }
    return max;
  }
}
