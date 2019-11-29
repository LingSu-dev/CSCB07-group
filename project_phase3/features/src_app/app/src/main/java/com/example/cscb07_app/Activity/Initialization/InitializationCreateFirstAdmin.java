package com.example.cscb07_app.Activity.Initialization;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cscb07_app.Controller.InitializationController;
import com.example.cscb07_app.R;
import com.example.cscb07_app.Src.database.helper.DatabaseAndroidHelper;
import com.example.cscb07_app.Src.database.helper.DatabaseHelperAdapter;
import com.example.cscb07_app.Src.database.helper.DatabaseMethodHelper;

public class InitializationCreateFirstAdmin extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_initialization_create_first_admin);
    Button createAdmin = findViewById(R.id.initializationCreateAdminButton);
    createAdmin.setOnClickListener(new InitializationController(this));

    DatabaseMethodHelper methodHelper= new DatabaseMethodHelper(getApplicationContext());
    DatabaseAndroidHelper androidHelper = new DatabaseAndroidHelper();
    androidHelper.setDriver(methodHelper);
    DatabaseHelperAdapter.setPlatformHelper(androidHelper);

    methodHelper.getWritableDatabase(); //Just to create database

    try {
      androidHelper.insertRole("ADMIN");
    }catch (Exception e)
    {
      e.printStackTrace();
    }

  }
}
