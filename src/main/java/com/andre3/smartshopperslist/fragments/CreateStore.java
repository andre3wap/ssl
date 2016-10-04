package com.andre3.smartshopperslist.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.andre3.smartshopperslist.R;
import com.andre3.smartshopperslist.impl.CreateStoreImpl;
import com.andre3.smartshopperslist.models.StoreMdl;

/**
 * Created by andre3 on 9/29/16.
 */
public class CreateStore extends Fragment {

    Button storeNxt_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.create_store, container, false);

        storeNxt_btn = (Button)view.findViewById(R.id.button5);

        storeNxt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String storeName, Float storeNo, String storeLcn, int storeId
                StoreMdl data = new StoreMdl("Test Store", "6096496891", "Willingboro, NJ", 0);

                CreateStoreImpl dao = new CreateStoreImpl(getContext(), data);
                long id = dao.save();


                System.out.println(id + "Insert it " + dao.readData().size());


                if(id >= 1) {
                    CreateCategory catNxt_frg = new CreateCategory();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, catNxt_frg).addToBackStack(null).commit();
                }

            }
        });



        //Load main menu view and return it to screen when app starts
        return view;
    }
}
