package com.example.cscb07_app.Activity.Admin;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cscb07_app.Controller.AdminController;
import com.example.cscb07_app.R;

public class Admin extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_admin);
    Button exitButton = findViewById(R.id.adminExitBtn);
    exitButton.setOnClickListener(new AdminController(this));
  }
}
