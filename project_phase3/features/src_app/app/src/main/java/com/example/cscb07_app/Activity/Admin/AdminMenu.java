package com.example.cscb07_app.Activity.Admin;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cscb07_app.Controller.AdminController;
import com.b07.users.Admin;
import com.example.cscb07_app.R;

public class AdminMenu extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_admin_menu);

    Admin admin = (Admin)getIntent().getSerializableExtra("adminObject");

    Button promoteEmployee = findViewById(R.id.adminMenuPromoteEmployee);
    promoteEmployee.setOnClickListener(new AdminController(this, admin));

    Button viewHistoricAccounts = findViewById(R.id.adminMenuHistoricAccounts);
    viewHistoricAccounts.setOnClickListener(new AdminController(this, admin));

    Button viewActiveAccounts = findViewById(R.id.adminMenuActiveAccounts);
    viewActiveAccounts.setOnClickListener(new AdminController(this, admin));

    Button saveAppData = findViewById(R.id.adminMenuSaveData);
    saveAppData.setOnClickListener(new AdminController(this, admin));

    Button loadAppData = findViewById(R.id.adminMenuLoadData);
    loadAppData.setOnClickListener(new AdminController(this, admin));

    Button exitButton = findViewById(R.id.adminMenuExitBtn);
    exitButton.setOnClickListener(new AdminController(this, admin));

    Button viewBooks = findViewById(R.id.adminMenuViewBooks);
    viewBooks.setOnClickListener(new AdminController(this, admin));

    Button createCoupon = findViewById(R.id.adminMenuCreateCoupon);
    createCoupon.setOnClickListener(new AdminController(this, admin));
  }

  @Override
  public void onBackPressed() {
    return;
  }
}
