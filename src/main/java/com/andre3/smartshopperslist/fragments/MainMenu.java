package com.andre3.smartshopperslist.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.andre3.smartshopperslist.R;
import com.andre3.smartshopperslist.adapters.StoreAdpr;
import com.andre3.smartshopperslist.impl.CreateStoreImpl;
import com.andre3.smartshopperslist.models.StoreMdl;
import com.andre3.smartshopperslist.views.PopupBuilder;

/**
 * Created by andre3 on 9/29/16.
 * Shopping list Main Index page
 */
public class MainMenu extends Fragment {

    Button btn, list_btn, cat_btn, store_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Load main menu view and return it to screen when app starts
        View view =  inflater.inflate(R.layout.mainmenu, container, false);


        // Buttons
        list_btn = (Button)view.findViewById(R.id.list_btn);
        cat_btn = (Button)view.findViewById(R.id.cat_btn);
        store_btn = (Button)view.findViewById(R.id.store_btn);

        // Views
        ListView lv = (ListView)view.findViewById(R.id.ListV);

        // Objects
        StoreMdl data = new StoreMdl(null, null, null, 0);
       final CreateStoreImpl dao = new CreateStoreImpl(getContext(), data);


        // Populate adapter + Listview with data
        StoreAdpr adapter = new StoreAdpr(getContext(),dao.readData());
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                System.out.println("Position: " + dao.readData().get(position).getStoreId());

                CreateCategory csf = new CreateCategory();

                FragmentTransaction tran = getFragmentManager().beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putInt("store_id", dao.readData().get(position).getStoreId());
                csf.setArguments(bundle);
                tran.replace(R.id.fragment_container, csf).addToBackStack(null).commit();


            }
        });

        ///Launch popup for List type button
        list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupBuilder dialog = new PopupBuilder(getContext(), "Store Title", "list");
                dialog.displyListForm().show();
            }
        });

        store_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupBuilder dialog = new PopupBuilder(getContext(), "Store Title", "store");
                dialog.displyStoreForm().show();

            }
        });

        cat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupBuilder dialog = new PopupBuilder(getContext(), "Store Title", "cat");
                dialog.displyCatForm().show();

            }
        });
        return view;
    }


}
