package com.andre3.smartshopperslist.views;

import android.app.Dialog;
import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.andre3.smartshopperslist.R;
import com.andre3.smartshopperslist.impl.CreateStoreImpl;
import com.andre3.smartshopperslist.models.StoreMdl;

/**
 * Created by ODBddddBROW on 10/4/2016.
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
            case"list":
                dialog.setContentView(R.layout.popup_layout1);
                break;

            case "cat":
                dialog.setContentView(R.layout.cat_popup);
                break;

            case "store":
                dialog.setContentView(R.layout.store_popup);
                break;
        }
    }

    public Dialog displyStoreForm() {
        final Dialog dialog = new Dialog(this.context);
        this.loadLayout(dialog);
        dialog.setTitle(this.dialogTitle);
        Button btn = (Button)dialog.findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText store_txt1, store_txt2, store_txt3;
                store_txt1 = (EditText)dialog.findViewById(R.id.store_txt1);
                store_txt2 = (EditText)dialog.findViewById(R.id.store_txt2);
                store_txt3 = (EditText)dialog.findViewById(R.id.store_txt3);

                StoreMdl data = new StoreMdl(store_txt1.getText().toString(), store_txt2.getText().toString(), store_txt3.getText().toString(), 0);

                CreateStoreImpl dao = new CreateStoreImpl(context, data);
                long id = dao.save();
                dialog.dismiss();
            }
        });
        return dialog;
    }

    public Dialog displyCatForm() {
       final Dialog dialog = new Dialog(this.context);
        this.loadLayout(dialog);
        dialog.setTitle(this.dialogTitle);

        Button btn = (Button)dialog.findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Catalog", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        return dialog;
    }

    public Dialog displyListForm() {
        final Dialog dialog = new Dialog(this.context);
        this.loadLayout(dialog);
        dialog.setTitle(this.dialogTitle);

        Button btn = (Button)dialog.findViewById(R.id.button7);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "List type", Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        return dialog;
    }
}
