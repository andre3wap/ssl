package com.andre3.smartshopperslist.fragments;

import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.andre3.smartshopperslist.R;
import com.andre3.smartshopperslist.adapters.CategoryAdpr;
import com.andre3.smartshopperslist.impl.CreateCatImpl;
import com.andre3.smartshopperslist.models.CategoryMdl;

/**
 * Created by andre3 on 9/29/16.
 */
public class CreateCategory extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        CategoryMdl data = new CategoryMdl(0, "Cat Name");
        final CreateCatImpl db = new CreateCatImpl(getContext(), data);
        ///db.save();

       View view = inflater.inflate(R.layout.list_category, container, false);
        ListView lv = (ListView)view.findViewById(R.id.ListV);

        CategoryAdpr adapter = new CategoryAdpr(getContext(), db.readData() );
        lv.setAdapter(adapter);

        final Bundle bd = getArguments();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                CreateList cl = new CreateList();

                FragmentTransaction tran = getFragmentManager().beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putInt("store_id", bd.getInt("store_id"));
                bundle.putInt("cat_id", db.readData().get(position).getCatId());
                cl.setArguments(bundle);

                tran.replace(R.id.fragment_container, cl).addToBackStack(null).commit();


            }
        });

        return view;
    }
}