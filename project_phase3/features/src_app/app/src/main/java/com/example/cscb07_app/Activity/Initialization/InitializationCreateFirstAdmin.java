package com.example.cscb07_app.Activity.Initialization;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cscb07_app.Controller.EmployeeController;
import com.example.cscb07_app.Controller.InitializationController;
import com.example.cscb07_app.R;

public class InitializationCreateFirstAdmin extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_initialization_create_first_admin);
    Button createAdmin = findViewById(R.id.initializationCreateAdminButton);
    createAdmin.setOnClickListener(new InitializationController(this));
  }
}
