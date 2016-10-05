package com.andre3.smartshopperslist.fragments;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.andre3.smartshopperslist.R;
import com.andre3.smartshopperslist.adapters.ItemsAdpr;
import com.andre3.smartshopperslist.adapters.ListTypeAdpr;
import com.andre3.smartshopperslist.impl.CreateItemImpl;
import com.andre3.smartshopperslist.models.AddItemMdl;
import com.andre3.smartshopperslist.views.PopupBuilder;

import java.lang.reflect.Field;

/**
 * Created by andre3 on 9/29/16.
 */
public class AddListItem extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        forceShowActionBarOverflowMenu();
        setHasOptionsMenu(true);

        AddItemMdl data = new  AddItemMdl( 0, "name", 9, "isle", (float)5.9, "", (float)12.5, 0, 8, 6);
        CreateItemImpl db = new CreateItemImpl(getContext(), data);
        ///db.save();

        final Bundle bd = getArguments();

        View view = inflater.inflate(R.layout.list_items, container, false);
        ListView lv = (ListView) view.findViewById(R.id.ListV);


        if(db.readAllData().size() < 1){
            Toast.makeText(getContext(), "You have an empty list. Click the + button to add items", Toast.LENGTH_LONG).show();
        }else{

            ItemsAdpr adapter = new ItemsAdpr(getContext(), db.readData(bd.getInt("list_typeid")));
            lv.setAdapter(adapter);
        }

        return view;
    }


    private void forceShowActionBarOverflowMenu() {
        try {
            ViewConfiguration config = ViewConfiguration.get(getContext());
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.item_menu, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        PopupBuilder dialog = new PopupBuilder(getContext(), "Add an Item", "item");
        dialog.displyItemForm().show();
        return false;
    }
}
