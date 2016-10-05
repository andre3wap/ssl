package com.andre3.smartshopperslist.views;

import android.app.Dialog;
import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.andre3.smartshopperslist.R;
import com.andre3.smartshopperslist.impl.CreateCatImpl;
import com.andre3.smartshopperslist.impl.CreateListTypeImpl;
import com.andre3.smartshopperslist.impl.CreateStoreImpl;
import com.andre3.smartshopperslist.models.CategoryMdl;
import com.andre3.smartshopperslist.models.ListTypeMdl;
import com.andre3.smartshopperslist.models.StoreMdl;

import java.util.ArrayList;

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

                // Get data from form
                EditText store_txt1, store_txt2, store_txt3;
                store_txt1 = (EditText)dialog.findViewById(R.id.store_txt1);
                store_txt2 = (EditText)dialog.findViewById(R.id.store_txt2);
                store_txt3 = (EditText)dialog.findViewById(R.id.store_txt3);

                // Save data to database
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

                // Get data from form
                EditText cat_txt1;
                cat_txt1 = (EditText)dialog.findViewById(R.id.cat_txt1);

                // Save data to database
                CategoryMdl data = new CategoryMdl(0, cat_txt1.getText().toString());
                final CreateCatImpl db = new CreateCatImpl(context, data);
                db.save();

                dialog.dismiss();
            }
        });

        return dialog;
    }

    public Dialog displyListForm() {
        final Dialog dialog = new Dialog(this.context);
        this.loadLayout(dialog);
        dialog.setTitle(this.dialogTitle);

        final Spinner spinner;

        Button btn = (Button)dialog.findViewById(R.id.button7);

        CategoryMdl data = new CategoryMdl(0, "Cat Name");
        final CreateCatImpl db = new CreateCatImpl(context, data);


        spinner = (Spinner) dialog.findViewById(R.id.spinner);

        ArrayList arr = new ArrayList();

        for (CategoryMdl temp : db.readData())
        {
            arr.add(temp.getCatId() +"-" +temp.getName());

        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, arr);
        spinner.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get data from form
                EditText list_txt1;

                list_txt1 = (EditText)dialog.findViewById(R.id.list_txt1);

                String[] spinnerSPlt = spinner.getSelectedItem().toString().split("-");


                //TODO: Pass category ID to list type 3rd param
                // Save data to DB
                ListTypeMdl data = new ListTypeMdl(0, list_txt1.getText().toString(), Integer.parseInt(spinnerSPlt[0]));
                CreateListTypeImpl db = new CreateListTypeImpl(context, data);
                db.save();

                dialog.dismiss();
            }
        });
        return dialog;
    }
}
