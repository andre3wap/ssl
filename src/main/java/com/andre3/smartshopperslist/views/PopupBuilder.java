package com.andre3.smartshopperslist.views;

import android.app.Dialog;
import android.content.Context;
import android.text.Layout;
import android.widget.Switch;

import com.andre3.smartshopperslist.R;

/**
 * Created by ODBdddBROW on 10/4/2016.
 */

public class PopupBuilder {

    Context context;
    String dialogTitle;
    String popupType;

    public PopupBuilder(Context context, String dialogTitle, String popupType) {
        this.context = context;
        this.popupType = popupType;
        this.dialogTitle = dialogTitle;
    }

    public void loadLayout(Dialog dialog){

        switch(this.popupType)
        {
            case"store":
                dialog.setContentView(R.layout.popup_layout1);
                break;
        }
    }

    public Dialog displyDialog() {
        Dialog dialog = new Dialog(this.context);
        this.loadLayout(dialog);
        dialog.setTitle(this.dialogTitle);

        return dialog;
    }
}
