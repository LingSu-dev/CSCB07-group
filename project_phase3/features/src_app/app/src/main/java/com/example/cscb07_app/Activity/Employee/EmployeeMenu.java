package com.example.cscb07_app.Activity.Employee;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cscb07_app.Controller.EmployeeController;
import com.example.cscb07_app.R;

public class EmployeeMenu extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_employee_menu);

    Button authenticateEmployee = findViewById(R.id.menuAuthenticateEmployeeBtn);
    authenticateEmployee.setOnClickListener(new EmployeeController(this));

    Button makeUser = findViewById(R.id.menuMakeNewUserBtn);
    makeUser.setOnClickListener(new EmployeeController(this));

    Button makeAccount = findViewById(R.id.menuMakeNewAccountBtn);
    makeAccount.setOnClickListener(new EmployeeController(this));

    Button makeEmployee = findViewById(R.id.menuMakeNewEmployeeBtn);
    makeEmployee.setOnClickListener(new EmployeeController(this));

    Button restockInventory = findViewById(R.id.menuRestockInventoryBtn);
    restockInventory.setOnClickListener(new EmployeeController(this));

    Button exitButton = findViewById(R.id.menuExitBtn);
    exitButton.setOnClickListener(new EmployeeController(this));
  }
}
