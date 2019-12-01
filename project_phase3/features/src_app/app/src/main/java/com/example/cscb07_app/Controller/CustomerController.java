package com.example.cscb07_app.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.b07.database.helper.DatabaseHelperAdapter;
import com.b07.inventory.Item;
import com.b07.store.ShoppingCart;
import com.b07.users.Customer;
import com.example.cscb07_app.Activity.Customer.CustomerCheckout;
import com.example.cscb07_app.Activity.Login.LoginMenu;
import com.example.cscb07_app.R;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

public class CustomerController implements View.OnClickListener {

  private Context appContext;
  private Customer customer;
  private ShoppingCart cart;

  public CustomerController(Context context, Customer customer) {
    this.appContext = context;
    this.customer = customer;
    cart = new ShoppingCart(customer);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.checkOutButton:
        checkoutCart();
        break;
      case R.id.customerStoreAddToCartBtn:
        addItemToCart();
        break;
      case R.id.customerStoreViewCartBtn:
        appContext.startActivity(new Intent(this.appContext, CustomerCheckout.class));
        break;
      case R.id.customerStoreLogoutBtn:
        appContext.startActivity(new Intent(this.appContext, LoginMenu.class));
        break;
      case R.id.checkPriceBtn:
        checkPrice();
        break;
    }
  }

  public void addItemToCart() {
    EditText itemIdEntry = ((Activity) appContext).findViewById(R.id.customerStoreItemId);
    EditText itemQuantityEntry = ((Activity) appContext)
        .findViewById(R.id.customerStoreItemQuantity);

    int itemId = 0;
    int itemQuantity = 0;
    boolean isInputValid = true;

    try {
      itemId = Integer.parseInt(itemIdEntry.getText().toString());
      itemQuantity = Integer.parseInt(itemQuantityEntry.getText().toString());
    } catch (NumberFormatException e) {
      isInputValid = false;
    }

    if (isInputValid) {

      Item currentItem = null;

      try {
        currentItem = DatabaseHelperAdapter.getItem(itemId);
      } catch (Exception e) {
      }

      if (currentItem == null) {
        DialogFactory.createAlertDialog(appContext, "Invalid Item", "Input a proper item id!"
            , "Ok", DialogId.NULL_DIALOG).show();
      } else {
        cart.addItem(currentItem, itemQuantity);
      }

    } else {
      DialogFactory
          .createAlertDialog(appContext, "Invalid Input", "Make sure item id and items quantity "
              + "are not empty!", "Ok", DialogId.NULL_DIALOG).show();
    }
  }

  /**
   * public void checkoutCart() { Intent intent = new Intent(this.appContext,
   * CustomerCheckout.class); intent.putExtra("cart", cart); appContext.startActivity(intent); }
   **/

  public void checkoutCart() {
    addAllItemsToCart();
    cart.checkOutCart();
  }

  public void addAllItemsToCart() {

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

    try {
      cart.addItem(DatabaseHelperAdapter.getItem(getItemIdByName(item1Name)),
          Integer.parseInt(amount1.getText().toString()));

      cart.addItem(DatabaseHelperAdapter.getItem(getItemIdByName(item2Name)),
          Integer.parseInt(amount2.getText().toString()));

      cart.addItem(DatabaseHelperAdapter.getItem(getItemIdByName(item3Name)),
          Integer.parseInt(amount3.getText().toString()));

      cart.addItem(DatabaseHelperAdapter.getItem(getItemIdByName(item4Name)),
          Integer.parseInt(amount4.getText().toString()));

      cart.addItem(DatabaseHelperAdapter.getItem(getItemIdByName(item5Name)),
          Integer.parseInt(amount5.getText().toString()));

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public int getItemIdByName(String itemName) {
    int id = 0;

    try {
      for (int x = 1; x < 6; x++) {
        Item item = DatabaseHelperAdapter.getItem(x);
        if (item.getName().equals(itemName)) {
          id = DatabaseHelperAdapter.getInventoryQuantity(x);
        }
      }
    } catch (SQLException e) {
    }
    return id;
  }

  public void checkPrice() {
    addAllItemsToCart();
    BigDecimal price = cart.getTotal().multiply(cart.getTaxRate()).setScale(2,
        RoundingMode.CEILING);
    DialogFactory.createAlertDialog(appContext, "Price Check",
            "Total Price: $" + price.toString(), "Ok",
            DialogId.NULL_DIALOG).show();
    cart.clearCart();
  }
}
