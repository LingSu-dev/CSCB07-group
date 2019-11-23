package com.example.cscb07_app.Activity.Customer;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cscb07_app.Controller.CustomerController;
import com.example.cscb07_app.R;

public class CustomerStore extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_customer_store);

    Button logoutBtn = findViewById(R.id.customerStoreLogoutBtn);
    logoutBtn.setOnClickListener(new CustomerController(this));

    Button viewCartBtn = findViewById(R.id.customerStoreViewCartBtn);
    viewCartBtn.setOnClickListener(new CustomerController(this));
  }
}
