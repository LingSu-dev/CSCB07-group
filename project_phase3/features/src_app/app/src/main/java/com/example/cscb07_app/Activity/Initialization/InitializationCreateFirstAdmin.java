package com.example.cscb07_app.Activity.Initialization;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.b07.database.helper.DatabaseAndroidHelper;
import com.b07.database.helper.DatabaseHelperAdapter;
import com.b07.database.helper.DatabaseMethodHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.inventory.ItemTypes;
import com.b07.users.Roles;
import com.example.cscb07_app.Controller.InitializationController;
import com.example.cscb07_app.R;
import java.math.BigDecimal;
import java.sql.SQLException;

public class InitializationCreateFirstAdmin extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_initialization_create_first_admin);

    DatabaseMethodHelper methodHelper = new DatabaseMethodHelper(getApplicationContext());
    DatabaseAndroidHelper androidHelper = new DatabaseAndroidHelper();
    androidHelper.setDriver(methodHelper);
    DatabaseHelperAdapter.setPlatformHelper(androidHelper);

    //methodHelper.getWritableDatabase(); //Just to create database

    try {
      //To avoid duplicate initialization
      if (androidHelper.getRoleIdByName(Roles.ADMIN.name()) == -1) {
        setUpDatabase(androidHelper);
      }
    } catch (DatabaseInsertException e) {
    } catch (SQLException e) {
    }

    Button createAdmin = findViewById(R.id.initializationCreateAdminButton);
    createAdmin.setOnClickListener(new InitializationController(this));
  }

  @Override
  public void onBackPressed() {
    return;
  }

  public void setUpDatabase(DatabaseAndroidHelper androidHelper)
      throws DatabaseInsertException, SQLException {

    for (Roles role : Roles.values()) {
      androidHelper.insertRole(role.name());
    }

    for (ItemTypes item : ItemTypes.values()) {
      int itemId = androidHelper.insertItem(item.name(), new BigDecimal("10.00"));
      androidHelper.insertInventory(itemId, 0);
    }
  }
}
