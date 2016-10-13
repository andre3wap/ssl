package com.andre3.smartshopperslist.views;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.andre3.smartshopperslist.R;
import com.andre3.smartshopperslist.adapters.CategoryAdpr;
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
    EditText item_txt1, item_txt2, item_txt3, item_txt4, item_txt5;
    CreateItemImpl item_db;
    AddItemMdl item_data;

    public PopupBuilder(Context context, String dialogTitle, String popupType) {
        this.context = context;
        this.popupType = popupType;
        this.dialogTitle = dialogTitle;
    }


    public void loadLayout(Dialog dialog){

        switch(this.popupType)
        {
            case"list":
                dialog.setContentView(R.layout.create_list_type);
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

    public Dialog displyStoreForm(final boolean saveData, final int storeId) {
        final Dialog dialog = new Dialog(this.context);
        this.loadLayout(dialog);
        dialog.setTitle(this.dialogTitle);
        Button btn = (Button)dialog.findViewById(R.id.button);

        // Get data from form
       final EditText store_txt1, store_txt2;
        store_txt1 = (EditText)dialog.findViewById(R.id.store_txt1);
        store_txt2 = (EditText)dialog.findViewById(R.id.store_txt2);


        if(saveData) {

            StoreMdl data = new StoreMdl("", "", "", -0);
            final CreateStoreImpl db = new CreateStoreImpl(context, data);
            store_txt1.setText(db.readDataById(storeId).get(0).getStoreName().toString());
            store_txt2.setText(db.readDataById(storeId).get(0).getStoreLcn().toString());
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Save data to database
                StoreMdl data = new StoreMdl(store_txt1.getText().toString(), store_txt2.getText().toString(), "", storeId);
                CreateStoreImpl dao = new CreateStoreImpl(context, data);

                if(saveData){
                    dao.update();
                }else{
                    long id = dao.save();
                }

                dialog.dismiss();
            }
        });
        return dialog;
    }

    //TODO: add the ability to select previously created "Sections"
    public Dialog displyCatForm(final Boolean saveData, final int catId) {
       final Dialog dialog = new Dialog(this.context);
        this.loadLayout(dialog);
        dialog.setTitle(this.dialogTitle);

        // Get data from form
        final EditText cat_txt1;
        cat_txt1 = (EditText)dialog.findViewById(R.id.cat_txt1);

        Button btn = (Button)dialog.findViewById(R.id.button2);

        if(saveData) {

            CategoryMdl data = new CategoryMdl(0, "");
            final CreateCatImpl db = new CreateCatImpl(context, data);
            cat_txt1.setText(db.readDataById(catId).get(0).getName().toString());
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cat_txt1.getText().toString().isEmpty()){
                    Toast.makeText(context, "The Field should not be left blank", Toast.LENGTH_LONG).show();
                }else {

                    // Save data to database
                    CategoryMdl data = new CategoryMdl(catId, cat_txt1.getText().toString());
                    final CreateCatImpl db = new CreateCatImpl(context, data);

                    if (saveData) {
                        db.update();
                    } else {
                        db.save();
                    }
                }

                dialog.dismiss();
            }
        });

        return dialog;
    }
    //TODO: add the ability to select previusly created list type from spinner
    public Dialog displyListForm(final Boolean saveData, final int typeId) {
        final Dialog dialog = new Dialog(this.context);
        this.loadLayout(dialog);
        dialog.setTitle(this.dialogTitle);

        final Spinner spinner;
        final EditText list_txt1;
        Button btn;

        // Get data from form
        list_txt1 = (EditText)dialog.findViewById(R.id.list_txt1);
        btn  = (Button)dialog.findViewById(R.id.button7);
        spinner = (Spinner) dialog.findViewById(R.id.spinner);

        CategoryMdl data = new CategoryMdl(0, "");
        final CreateCatImpl db = new CreateCatImpl(context, data);

        ArrayList arr = new ArrayList();

        if(saveData){

            ListTypeMdl data2 = new ListTypeMdl(0, "", -0);
            CreateListTypeImpl db2 = new CreateListTypeImpl(context, data2);
            list_txt1.setText(db2.readData(typeId).get(0).getName().toString());

            arr.add(db2.readData(typeId).get(0).getCatId() +"-" +db.readDataById(db2.readData(typeId).get(0).getCatId()));
        }

        for (CategoryMdl temp : db.readData()) {
            arr.add(temp.getCatId() +"-" +temp.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, arr);
        spinner.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] spinnerSPlt = spinner.getSelectedItem().toString().split("-");

                // Save data to DB
                ListTypeMdl data = new ListTypeMdl(typeId, list_txt1.getText().toString(), Integer.parseInt(spinnerSPlt[0]));
                CreateListTypeImpl db = new CreateListTypeImpl(context, data);

                if(saveData){
                    db.update();
                }else{
                    db.save();
                }

                dialog.dismiss();
            }
        });
        return dialog;
    }

    public Dialog displyItemForm(final Boolean saveData, final int itemId) {
        final Dialog dialog = new Dialog(this.context);
        this.loadLayout(dialog);
        dialog.setTitle(this.dialogTitle);

        final Spinner spinner2, spinner3, spinner4, spinner5;
        Button btn;


        btn = (Button)dialog.findViewById(R.id.button6);
        spinner3 = (Spinner) dialog.findViewById(R.id.spinner3);
        spinner4 = (Spinner) dialog.findViewById(R.id.spinner4);
        spinner5 = (Spinner) dialog.findViewById(R.id.spinner5);

        item_txt1 = (EditText)dialog.findViewById(R.id.item_txt1);
        item_txt2 = (EditText)dialog.findViewById(R.id.item_txt2);
        item_txt3 = (EditText)dialog.findViewById(R.id.item_txt3);
        item_txt5 = (EditText)dialog.findViewById(R.id.item_txt5);

        // If saveData returns true then populate form fields with values from DB, if not, proceed with regular data collection.
        if(saveData){

            AddItemMdl data = new  AddItemMdl( 0, "name", 9, "isle", (float)5.9, "", "", 0, 8, 6);
            CreateItemImpl db = new CreateItemImpl(getContext(), data);

            item_txt1.setText(db.readItemData(itemId).get(0).getName());
            item_txt2.setText(db.readItemData(itemId).get(0).getCost().toString());
            item_txt3.setText(db.readItemData(itemId).get(0).getQty().toString());
            item_txt5.setText(db.readItemData(itemId).get(0).getIsle());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, readStore());
        spinner3.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, readList());
        spinner4.setAdapter(adapter2);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, readCat());
        spinner5.setAdapter(adapter3);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ////String[] spinnerSPlt2 = spinner2.getSelectedItem().toString().split("-");
                String[] spinnerSPlt3 = spinner3.getSelectedItem().toString().split("-");
                String[] spinnerSPlt4 = spinner4.getSelectedItem().toString().split("-");
                String[] spinnerSPlt5 = spinner5.getSelectedItem().toString().split("-");

                // Save data to database
                item_data = new  AddItemMdl( itemId, item_txt1.getText().toString(), Integer.parseInt(item_txt3.getText().toString()),
                            item_txt5.getText().toString(), (float)Float.parseFloat(item_txt2.getText().toString()), "",
                            "", Integer.parseInt(spinnerSPlt3[0]), Integer.parseInt(spinnerSPlt4[0]), Integer.parseInt(spinnerSPlt5[0]));
                item_db = new CreateItemImpl(getContext(), item_data);

                if(saveData) {
                    item_db.update();
                }else {
                    item_db.save();
                }

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
