package com.example.cscb07_app.Activity.Login;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cscb07_app.Controller.LoginController;
import com.example.cscb07_app.R;
import com.example.cscb07_app.Src.database.helper.DatabaseAndroidHelper;
import com.example.cscb07_app.Src.database.helper.DatabaseHelperAdapter;
import com.example.cscb07_app.Src.database.helper.DatabaseMethodHelper;

public class LoginMenu extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login_menu);

    DatabaseMethodHelper methodHelper = new DatabaseMethodHelper(getApplicationContext());
    DatabaseAndroidHelper androidHelper = new DatabaseAndroidHelper();
    androidHelper.setDriver(methodHelper);
    DatabaseHelperAdapter.setPlatformHelper(androidHelper);
    methodHelper.getWritableDatabase(); //Just to create database

    Button loginBtn = findViewById(R.id.loginButton);

    loginBtn.setOnClickListener(new LoginController(this, androidHelper));
  }

  @Override
  public void onBackPressed() {
    return;
  }

}
