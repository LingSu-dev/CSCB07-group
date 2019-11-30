package com.b07.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializeFunc {

  public static String serialize(Serializable x, String location) {
    try {
      FileOutputStream fileOut = new FileOutputStream(location + "database_copy.ser");
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(x);
      out.close();
      fileOut.close();
      System.out.println(location + "database_copy.ser");
    } catch (IOException i) {
      i.printStackTrace();
    }
    return x.toString();
  }


  public static DataStorage deserialize(String location) {
    try {
      FileInputStream fileIn = new FileInputStream(location + "database_copy.ser");
      ObjectInputStream in = new ObjectInputStream(fileIn);
      Object z = in.readObject();
      in.close();
      fileIn.close();
      return (DataStorage) z;
    } catch (IOException i) {
      i.printStackTrace();
      return null;
    } catch (ClassNotFoundException c) {
      System.out.println("Class not found");
      c.printStackTrace();
      return null;
    }
  }

}


