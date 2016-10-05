package com.andre3.smartshopperslist.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.andre3.smartshopperslist.impl.CreateItemImpl;
import com.andre3.smartshopperslist.impl.CreateListTypeImpl;
import com.andre3.smartshopperslist.impl.CreateStoreImpl;
import com.andre3.smartshopperslist.models.AddItemMdl;
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
            case "item":
                dialog.setContentView(R.layout.item_popup);
                break;
        }
    }

    public Context getContext() {
        return context;
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

    public Dialog displyItemForm() {
        final Dialog dialog = new Dialog(this.context);
        this.loadLayout(dialog);
        dialog.setTitle(this.dialogTitle);

        final Spinner spinner2, spinner3, spinner4, spinner5;
        Button btn;

        btn = (Button)dialog.findViewById(R.id.button6);
        spinner2 = (Spinner) dialog.findViewById(R.id.spinner2);
        spinner3 = (Spinner) dialog.findViewById(R.id.spinner3);
        spinner4 = (Spinner) dialog.findViewById(R.id.spinner4);
        spinner5 = (Spinner) dialog.findViewById(R.id.spinner5);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, readStore());
        spinner3.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, readList());
        spinner4.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, readCat());
        spinner5.setAdapter(adapter3);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get data from form
                EditText item_txt1, item_txt2, item_txt3, item_txt4, item_txt5;
                item_txt1 = (EditText)dialog.findViewById(R.id.item_txt1);
                item_txt2 = (EditText)dialog.findViewById(R.id.item_txt2);
                item_txt3 = (EditText)dialog.findViewById(R.id.item_txt3);
                item_txt4 = (EditText)dialog.findViewById(R.id.item_txt4);
                item_txt5 = (EditText)dialog.findViewById(R.id.item_txt5);

                ////String[] spinnerSPlt2 = spinner2.getSelectedItem().toString().split("-");
                String[] spinnerSPlt3 = spinner3.getSelectedItem().toString().split("-");
                String[] spinnerSPlt4 = spinner4.getSelectedItem().toString().split("-");
                String[] spinnerSPlt5 = spinner5.getSelectedItem().toString().split("-");

                // Save data to database
                    AddItemMdl data = new  AddItemMdl( 1, item_txt1.getText().toString(), Integer.parseInt(item_txt3.getText().toString()),
                            item_txt5.getText().toString(), (float)Integer.parseInt(item_txt2.getText().toString()), "Medium",
                            (float)Integer.parseInt(item_txt4.getText().toString()), Integer.parseInt(spinnerSPlt3[0]), Integer.parseInt(spinnerSPlt4[0]), Integer.parseInt(spinnerSPlt5[0]));
                    CreateItemImpl db = new CreateItemImpl(getContext(), data);
                db.save();

                dialog.dismiss();
            }
        });

        return dialog;
    }

    public ArrayList<String> readCat(){

        CategoryMdl data = new CategoryMdl(0, null);
        final CreateCatImpl cat_db = new CreateCatImpl(context, data);

        ArrayList arr = new ArrayList();

        for (CategoryMdl temp : cat_db.readData()){
            arr.add(temp.getCatId() +"-" +temp.getName());
        }
        return arr;
    }

    public ArrayList<String> readStore(){

        StoreMdl data = new StoreMdl(null, null, null, 0);
        CreateStoreImpl dao = new CreateStoreImpl(getContext(), data);

        ArrayList arr = new ArrayList();

        for (StoreMdl temp : dao.readData()){
            arr.add(temp.getStoreId() +"-" +temp.getStoreName());
        }
        return arr;
    }
    public ArrayList<String> readList(){

        ListTypeMdl data = new ListTypeMdl(0, null, 0);
        final CreateListTypeImpl db = new CreateListTypeImpl(getContext(), data);

        ArrayList arr = new ArrayList();

        for (ListTypeMdl temp : db.readDataAll()){
            arr.add(temp.getListId() +"-" +temp.getName());
        }
        return arr;
    }
}
