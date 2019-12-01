package com.example.cscb07_app.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
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
import java.util.List;

public class CustomerController implements View.OnClickListener {

  private Context appContext;
  private Customer customer;
  private ShoppingCart cart;

  public CustomerController(Context context, Customer customer) {
    this.appContext = context;
    this.customer = customer;
  }

  public CustomerController(Context context, ShoppingCart cart) {
    this.appContext = context;
    this.cart = cart;
  }

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
          addItemToCart(item1Name);
        }
        break;
      case R.id.plus2:
        temp = Integer.parseInt(amount2.getText().toString()) + 1;
        if (temp <= getMaxQuantity(item2Name)) {
          amount2.setText(String.valueOf(temp));
          addItemToCart(item2Name);
        }
        break;
      case R.id.plus3:
        temp = Integer.parseInt(amount3.getText().toString()) + 1;
        if (temp <= getMaxQuantity(item3Name)) {
          amount3.setText(String.valueOf(temp));
          addItemToCart(item3Name);
        }
        break;
      case R.id.plus4:
        temp = Integer.parseInt(amount4.getText().toString()) + 1;
        if (temp <= getMaxQuantity(item4Name)) {
          amount4.setText(String.valueOf(temp));
          addItemToCart(item4Name);
        }
        break;
      case R.id.plus5:
        temp = Integer.parseInt(amount5.getText().toString()) + 1;
        if (temp <= getMaxQuantity(item5Name)) {
          amount5.setText(String.valueOf(temp));
          addItemToCart(item5Name);
        }
        break;
      case R.id.minus1:
        temp = Integer.parseInt(amount1.getText().toString()) - 1;
        if (temp >= 0) {
          amount1.setText(String.valueOf(temp));
          removeItemFromCart(item1Name);
        }
        break;
      case R.id.minus2:
        temp = Integer.parseInt(amount2.getText().toString()) - 1;
        if (temp >= 0) {
          amount2.setText(String.valueOf(temp));
          removeItemFromCart(item2Name);
        }
        break;
      case R.id.minus3:
        temp = Integer.parseInt(amount3.getText().toString()) - 1;
        if (temp >= 0) {
          amount3.setText(String.valueOf(temp));
          removeItemFromCart(item3Name);
        }
        break;
      case R.id.minus4:
        temp = Integer.parseInt(amount4.getText().toString()) - 1;
        if (temp >= 0) {
          amount4.setText(String.valueOf(temp));
          removeItemFromCart(item4Name);
        }
        break;
      case R.id.minus5:
        temp = Integer.parseInt(amount5.getText().toString()) - 1;
        if (temp >= 0) {
          amount5.setText(String.valueOf(temp));
          removeItemFromCart(item5Name);
        }
        break;
      case R.id.checkOutButton:
        if (cartIsEmpty()) {
          DialogFactory
              .createAlertDialog(appContext, "Purchase Failed", "Please add items to your cart!",
                  "Ok", DialogId.NULL_DIALOG).show();
        } else {
          checkoutCart();
        }
        break;
      case R.id.customerStoreAddToCartBtn:
        break;
      case R.id.customerStoreViewCartBtn:
        appContext.startActivity(new Intent(this.appContext, CustomerCheckout.class));
        break;
      case R.id.checkOutExitButton:
        if (cartIsEmpty() || !customerHasActiveAccounts()) {
          ((CustomerCheckout) appContext).finish();
          appContext.startActivity(new Intent(this.appContext, LoginMenu.class));
        } else if (!cartIsEmpty() && customerHasActiveAccounts()) {
          DialogFactory
              .createAlertDialogYesNo(appContext, "Save Shopping Cart", "Would you like to save"
                  + "your shopping cart?", "Yes", "No", DialogId.SAVE_SHOPPING_CART).show();
        }
        break;
    }
  }

  /**
   * Check if customer has active accounts.
   *
   * @return true if yes, false otherwise
   */
  public boolean customerHasActiveAccounts() {
    List<Integer> accounts = null;

    try {
      accounts = DatabaseHelperAdapter.getUserActiveAccounts(customer.getId());
    } catch (SQLException e) {
    }

    return accounts != null && !accounts.isEmpty();
  }

  /**
   * Add a single item to the cart.
   *
   * @param itemName the item of interest
   */
  public void addItemToCart(String itemName) {
    int itemId = getItemIdByName(itemName);
    Item item = null;

    try {
      item = DatabaseHelperAdapter.getItem(itemId);
    } catch (SQLException e) {
    }

    cart.addItem(item, 1);
    updatePrice();
  }

  /**
   * Add a single item to the cart.
   *
   * @param itemName the item of interest
   */
  public void removeItemFromCart(String itemName) {
    int itemId = getItemIdByName(itemName);
    Item item = null;

    try {
      item = DatabaseHelperAdapter.getItem(itemId);
    } catch (SQLException e) {
    }

    cart.removeItem(item, 1);
    updatePrice();
  }

  /**
   * Updates the price in the cart.
   */
  public void updatePrice() {
    BigDecimal price = (cart.getTotal().multiply(cart.getTaxRate()))
        .setScale(2, RoundingMode.CEILING);

    TextView priceText = ((Activity) appContext).findViewById(R.id.priceTotal);
    priceText.setText("Total Price (with tax): $" + price.toString());
  }

  /**
   * Checks out customer's cart.
   */
  public void checkoutCart() {
    BigDecimal price = (cart.getTotal().multiply(cart.getTaxRate()))
        .setScale(2, RoundingMode.CEILING);
    cart.checkOutCart();

    DialogFactory
        .createAlertDialog(appContext, "Checkout", "You have successfully checked out with "
            + "total $" + price.toString(), "Ok", DialogId.CHECKOUT_CART).show();
  }

  /**
   * Checks if customer's cart is empty
   *
   * @return true if empty, false otherwise
   */
  public boolean cartIsEmpty() {
    return cart.getTotal().compareTo(BigDecimal.ZERO) == 0;
  }

  /**
   * Add all items to cart.
   */
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

  /**
   * Get total number of items left for an item
   *
   * @param itemName the item of interest
   * @return quantity
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

  /**
   * Get the item id of an item given it's name.
   *
   * @param itemName the item's name
   * @return id of item
   */
  public int getItemIdByName(String itemName) {
    int id = 0;

    try {
      for (int x = 1; x < 6; x++) {
        if (DatabaseHelperAdapter.getItem(x).getName().equals(itemName)) {
          return x;
        }
      }
    } catch (SQLException e) {
    }
    return id;
  }
}
