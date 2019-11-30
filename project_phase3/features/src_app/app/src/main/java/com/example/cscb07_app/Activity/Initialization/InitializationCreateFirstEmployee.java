package com.example.cscb07_app.Activity.Initialization;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cscb07_app.Controller.InitializationController;
import com.example.cscb07_app.R;
import com.b07.database.helper.DatabaseAndroidHelper;
import com.b07.database.helper.DatabaseHelperAdapter;
import com.b07.database.helper.DatabaseMethodHelper;

public class InitializationCreateFirstEmployee extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_initialization_create_first_employee);

    DatabaseMethodHelper methodHelper = new DatabaseMethodHelper(getApplicationContext());
    DatabaseAndroidHelper androidHelper = new DatabaseAndroidHelper();
    androidHelper.setDriver(methodHelper);
    DatabaseHelperAdapter.setPlatformHelper(androidHelper);
    methodHelper.getWritableDatabase(); //Just to create database

    Button createAdmin = findViewById(R.id.initializationCreateEmployeeButton);
    createAdmin.setOnClickListener(new InitializationController(this, androidHelper));
  }

  @Override
  public void onBackPressed() {
    return;
  }
}
