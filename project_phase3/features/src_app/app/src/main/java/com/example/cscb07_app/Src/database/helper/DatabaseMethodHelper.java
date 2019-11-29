package com.example.cscb07_app.Src.database.helper;

import android.content.Context;
import com.example.cscb07_app.Src.database.DatabaseDriverAndroid;

public class DatabaseMethodHelper extends DatabaseDriverAndroid {

  public DatabaseMethodHelper(Context context) {
    super(context);
  }

  public long insertRole(String role) {
    return super.insertRole(role);
  }
}
