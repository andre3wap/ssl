package com.andre3.smartshopperslist.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.andre3.smartshopperslist.R;
import com.andre3.smartshopperslist.adapters.ExpandableListType;
import com.andre3.smartshopperslist.adapters.ListTypeAdpr;
import com.andre3.smartshopperslist.impl.CreateItemImpl;
import com.andre3.smartshopperslist.impl.CreateListTypeImpl;
import com.andre3.smartshopperslist.models.AddItemMdl;
import com.andre3.smartshopperslist.models.ListTypeMdl;
import com.andre3.smartshopperslist.views.PopupBuilder;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by andre3 on 9/29/16.
 */
public class CreateList  extends Fragment {

    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        forceShowActionBarOverflowMenu();
        setHasOptionsMenu(true);

        View view =  inflater.inflate(R.layout.list_types, container, false);
        ExpandableListView expListView = (ExpandableListView)view.findViewById(R.id.expLv);

        prepareListData();

        ExpandableListType listAdapter = new ExpandableListType(getContext(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                /// listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition)
                /// listDataHeader.get(groupPosition)

                System.out.println("Clicked" + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));

                return false;
            }
        });


        return view;
    }



    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        ListTypeMdl data = new ListTypeMdl(0, "List Type", 0);
        final CreateListTypeImpl db = new CreateListTypeImpl(getContext(), data);

        final Bundle bd = getArguments();

        List<List<String>> lists = new ArrayList<List<String>>();
        int i = 0;

        for(ListTypeMdl temp: db.readData(bd.getInt("cat_id"))) {

            AddItemMdl itemDb = new  AddItemMdl( 0, "name", 9, "isle", (float)5.9, "", (float)12.5, 0, 8, 6);
            CreateItemImpl items = new CreateItemImpl(getContext(), itemDb);


            List<String> list = new ArrayList<>();
            lists.add(list);


            for(AddItemMdl listTemp : items.readData(temp.getListId())) {
                ///Format: id/name/price/qty/isle
                String childData = listTemp.getItemId()+"/"+listTemp.getName().toString()+"/"+listTemp.getCost()+"/"+listTemp.getQty()+"/"+listTemp.getIsle();
                lists.get(i).add(childData);
            }

            listDataHeader.add(temp.getName());
            listDataChild.put(temp.getName(),  lists.get(i));
            i++;
        }

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
