package com.example.cscb07_app.Controller;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Factory to create alert dialog boxes.
 */
public class DialogFactory {

  /**
   * Creates an alery dialog.
   *
   * @param appContext the context of interest
   * @param title the dialog's title
   * @param message the dialog's message
   * @param btnText the dialog's button's text
   * @param id the dialog's id
   * @return the alert dialog
   */
  public static AlertDialog createAlertDialog(Context appContext, String title, String message,
      String btnText, DialogId id) {

    AlertDialog dialog = new AlertDialog.Builder(appContext).create();
    dialog.setTitle(title);
    dialog.setMessage(message);
    dialog.setButton(AlertDialog.BUTTON_NEUTRAL, btnText,
        new DialogController(appContext, id));
    return dialog;
  }
}
