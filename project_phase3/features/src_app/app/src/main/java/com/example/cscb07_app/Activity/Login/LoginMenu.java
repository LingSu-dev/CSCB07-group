package com.example.cscb07_app.Activity.Login;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.b07.database.helper.DatabaseAndroidHelper;
import com.b07.database.helper.DatabaseHelperAdapter;
import com.b07.database.helper.DatabaseMethodHelper;
import com.b07.users.Roles;
import com.example.cscb07_app.Controller.LoginController;
import com.example.cscb07_app.R;

public class LoginMenu extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login_menu);

    DatabaseMethodHelper methodHelper = new DatabaseMethodHelper(getApplicationContext());
    DatabaseAndroidHelper androidHelper = new DatabaseAndroidHelper();
    androidHelper.setDriver(methodHelper);
    DatabaseHelperAdapter.setPlatformHelper(androidHelper);

    Button loginBtn = findViewById(R.id.loginButton);
    loginBtn.setOnClickListener(new LoginController(this));

    try {
      if (DatabaseHelperAdapter.getUserDetails(3) == null) {
        int userId = DatabaseHelperAdapter.insertNewUser("Customer", 20, "Home",
            "1234");
        int roleId = DatabaseHelperAdapter.getRoleIdByName(Roles.CUSTOMER.name());
        DatabaseHelperAdapter.insertUserRole(userId, roleId);
      }
    } catch (Exception e) {
    }
  }

  @Override
  public void onBackPressed() {
    return;
  }

}
