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

/**
 * Created by andre3 on 9/29/16.
 * Shopping list Main Index page
 */
public class MainMenu extends Fragment {

    Button btn, listtype_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Load main menu view and return it to screen when app starts
        View view =  inflater.inflate(R.layout.mainmenu, container, false);


        // Buttons
        btn = (Button)view.findViewById(R.id.button);
        listtype_btn = (Button)view.findViewById(R.id.button2);

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
        listtype_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogBox();

            }
        });

        return view;
    }

    public void dialogBox() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("Click on Image for tag");
        alertDialogBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });

        alertDialogBuilder.setNegativeButton("cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
