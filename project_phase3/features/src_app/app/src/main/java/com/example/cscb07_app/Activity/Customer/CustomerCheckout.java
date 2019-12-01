package com.example.cscb07_app.Activity.Customer;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.b07.users.Customer;
import com.example.cscb07_app.Controller.CheckoutButtonController;
import com.example.cscb07_app.Controller.CustomerController;
import com.example.cscb07_app.R;

public class CustomerCheckout extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_customer_checkout);

    Customer customer = (Customer) getIntent().getSerializableExtra("customer");

    Button plusItem1 = findViewById(R.id.plus1);
    Button plusItem2 = findViewById(R.id.plus2);
    Button plusItem3 = findViewById(R.id.plus3);
    Button plusItem4 = findViewById(R.id.plus4);
    Button plusItem5 = findViewById(R.id.plus5);

    Button minusItem1 = findViewById(R.id.minus1);
    Button minusItem2 = findViewById(R.id.minus2);
    Button minusItem3 = findViewById(R.id.minus3);
    Button minusItem4 = findViewById(R.id.minus4);
    Button minusItem5 = findViewById(R.id.minus5);

    plusItem1.setOnClickListener(new CheckoutButtonController(this));
    plusItem2.setOnClickListener(new CheckoutButtonController(this));
    plusItem3.setOnClickListener(new CheckoutButtonController(this));
    plusItem4.setOnClickListener(new CheckoutButtonController(this));
    plusItem5.setOnClickListener(new CheckoutButtonController(this));

    minusItem1.setOnClickListener(new CheckoutButtonController(this));
    minusItem2.setOnClickListener(new CheckoutButtonController(this));
    minusItem3.setOnClickListener(new CheckoutButtonController(this));
    minusItem4.setOnClickListener(new CheckoutButtonController(this));
    minusItem5.setOnClickListener(new CheckoutButtonController(this));

    Button checkPrice = findViewById(R.id.checkPriceBtn);
    checkPrice.setOnClickListener(new CustomerController(this, customer));

    Button checkoutBtn = findViewById(R.id.checkOutButton);
    checkoutBtn.setOnClickListener(new CustomerController(this, customer));
  }
}
