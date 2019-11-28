package com.example.cscb07_app.Activity.Customer;

import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cscb07_app.Controller.CustomerController;
import com.example.cscb07_app.R;

public class CustomerStore extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_customer_store);

    ImageButton logoutBtn = findViewById(R.id.customerStoreLogoutBtn);
    logoutBtn.setOnClickListener(new CustomerController(this));

    ImageButton viewCartBtn = findViewById(R.id.customerStoreViewCartBtn);
    viewCartBtn.setOnClickListener(new CustomerController(this));
  }
}
