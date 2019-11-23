package com.example.cscb07_app.Activity.Login;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cscb07_app.Controller.LoginController;
import com.example.cscb07_app.R;

public class LoginMenu extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login_menu);
    Button loginBtn = findViewById(R.id.loginButton);
    loginBtn.setOnClickListener(new LoginController(this));
  }
}
