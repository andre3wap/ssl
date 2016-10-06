package com.andre3.smartshopperslist.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.andre3.smartshopperslist.R;
import com.andre3.smartshopperslist.dao.CreateListDao;
import com.andre3.smartshopperslist.dao.CreateStoreDao;
import com.andre3.smartshopperslist.impl.CreateStoreImpl;
import com.andre3.smartshopperslist.models.StoreMdl;

/**
 * Created by andre3 on 10/1/16.
 */
public class ScreenOptions extends Fragment {

    Button shoppingList_btn, reminderList_btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Load fragment for user to choose which option they want to use (Shopping List/ Reminders)
        View view =  inflater.inflate(R.layout.screen_options, container, false);

        shoppingList_btn = (Button)view.findViewById(R.id.button3);
        reminderList_btn = (Button)view.findViewById(R.id.button4);

        ///Load Shopping list Index fragment
        shoppingList_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainMenu shoppingListfrg = new MainMenu();

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, shoppingListfrg).addToBackStack(null).commit();


            }
        });

        ///Load Reminder list index fragment
        reminderList_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ///CreateCategory ccf = new CreateCategory();
                ///getFragmentManager().beginTransaction().replace(R.id.fragment_container, ccf).addToBackStack(null).commit();

            }
        });


        return view;
    }
}