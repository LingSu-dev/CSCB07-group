package com.example.cscb07_app.Activity.Customer;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.b07.database.helper.DatabaseAndroidHelper;
import com.b07.database.helper.DatabaseHelperAdapter;
import com.b07.database.helper.DatabaseMethodHelper;
import com.example.cscb07_app.Controller.CustomerController;
import com.example.cscb07_app.R;

public class CustomerStore extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_customer_store);

    DatabaseMethodHelper methodHelper = new DatabaseMethodHelper(getApplicationContext());
    DatabaseAndroidHelper androidHelper = new DatabaseAndroidHelper();
    androidHelper.setDriver(methodHelper);
    DatabaseHelperAdapter.setPlatformHelper(androidHelper);

    //TODO: helper to get item id given item name

    TextView itemId1 = findViewById(R.id.item1ID);
    TextView itemId2 = findViewById(R.id.item2ID);
    TextView itemId3 = findViewById(R.id.item3ID);
    TextView itemId4 = findViewById(R.id.item4ID);
    TextView itemId5 = findViewById(R.id.item5ID);

    ImageButton logoutBtn = findViewById(R.id.customerStoreLogoutBtn);
    logoutBtn.setOnClickListener(new CustomerController(this));

    ImageButton viewCartBtn = findViewById(R.id.customerStoreViewCartBtn);
    viewCartBtn.setOnClickListener(new CustomerController(this));
  }
}
