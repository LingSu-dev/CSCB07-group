package com.example.cscb07_app.Activity.Admin;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.cscb07_app.Controller.AdminController;
import com.example.cscb07_app.R;

public class AdminMenu extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_admin_menu);

    Button promoteEmployee = findViewById(R.id.adminMenuPromoteEmployee);
    promoteEmployee.setOnClickListener(new AdminController(this));

    Button viewHistoricAccounts = findViewById(R.id.adminMenuHistoricAccounts);
    viewHistoricAccounts.setOnClickListener(new AdminController(this));

    Button viewActiveAccounts = findViewById(R.id.adminMenuActiveAccounts);
    viewActiveAccounts.setOnClickListener(new AdminController(this));

    Button saveAppData = findViewById(R.id.adminMenuSaveData);
    saveAppData.setOnClickListener(new AdminController(this));

    Button loadAppData = findViewById(R.id.adminMenuLoadData);
    loadAppData.setOnClickListener(new AdminController(this));

    Button exitButton = findViewById(R.id.adminMenuExitBtn);
    exitButton.setOnClickListener(new AdminController(this));

    Button viewBooks = findViewById(R.id.adminMenuViewBooks);
    viewBooks.setOnClickListener(new AdminController(this));

    Button createCoupon = findViewById(R.id.adminMenuCreateCoupon);
    createCoupon.setOnClickListener(new AdminController(this));
  }
}
