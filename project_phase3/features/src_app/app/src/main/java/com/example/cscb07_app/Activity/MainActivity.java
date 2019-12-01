package com.example.cscb07_app.Activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.b07.database.helper.DatabaseAndroidHelper;
import com.b07.database.helper.DatabaseHelperAdapter;
import com.b07.database.helper.DatabaseMethodHelper;
import com.example.cscb07_app.Activity.Initialization.InitializationCreateFirstAdmin;
import com.example.cscb07_app.Activity.Login.LoginMenu;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    DatabaseMethodHelper methodHelper = new DatabaseMethodHelper(getApplicationContext());
    DatabaseAndroidHelper androidHelper = new DatabaseAndroidHelper();
    androidHelper.setDriver(methodHelper);
    DatabaseHelperAdapter.setPlatformHelper(androidHelper);

    boolean firstBoot = getSharedPreferences("BOOT_PREF", MODE_PRIVATE).
        getBoolean("firstBoot", true);

    if (firstBoot) {
      getSharedPreferences("BOOT_PREF", MODE_PRIVATE)
          .edit().putBoolean("firstBoot", false);
      startActivity(new Intent(MainActivity.this, InitializationCreateFirstAdmin.class));
    } else {
      startActivity(new Intent(MainActivity.this, LoginMenu.class));
    }
  }
}
