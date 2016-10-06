package com.andre3.smartshopperslist.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.andre3.smartshopperslist.R;
import com.andre3.smartshopperslist.adapters.ExpandableListType;
import com.andre3.smartshopperslist.adapters.ListTypeAdpr;
import com.andre3.smartshopperslist.impl.CreateListTypeImpl;
import com.andre3.smartshopperslist.models.ListTypeMdl;

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


        View view =  inflater.inflate(R.layout.list_types, container, false);
        ListView lv = (ListView) view.findViewById(R.id.ListV);

        ExpandableListView expListView = (ExpandableListView)view.findViewById(R.id.lvExp);

        prepareListData();

        ExpandableListType listAdapter = new ExpandableListType(getContext(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);


           /*
        ListTypeMdl data = new ListTypeMdl(0, "List Type", 0);
       final CreateListTypeImpl db = new CreateListTypeImpl(getContext(), data);
        ///db.save();

       final Bundle bd = getArguments();

        // Get all list type by category ID
        ListTypeAdpr adapter = new ListTypeAdpr(getContext(), db.readData(bd.getInt("cat_id")));

        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                AddListItem cl = new AddListItem();

                FragmentTransaction tran = getFragmentManager().beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putInt("list_typeid", db.readData(bd.getInt("cat_id")).get(0).getListId());
                cl.setArguments(bundle);

                tran.replace(R.id.fragment_container, cl).addToBackStack(null).commit();


            }
        });
*/

        return view;
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();


        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");


        ListTypeMdl data = new ListTypeMdl(0, "List Type", 0);
        final CreateListTypeImpl db = new CreateListTypeImpl(getContext(), data);
        ///db.save();

        final Bundle bd = getArguments();


        for(ListTypeMdl temp: db.readData(bd.getInt("cat_id")))
        {
            listDataHeader.add(temp.getName());
            listDataChild.put(temp.getName(), comingSoon);
        }



    }
}
