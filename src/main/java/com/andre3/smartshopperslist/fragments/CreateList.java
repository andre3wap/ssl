package com.andre3.smartshopperslist.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.andre3.smartshopperslist.R;
import com.andre3.smartshopperslist.adapters.ListTypeAdpr;
import com.andre3.smartshopperslist.impl.CreateListTypeImpl;
import com.andre3.smartshopperslist.models.ListTypeMdl;

/**
 * Created by andre3 on 9/29/16.
 */
public class CreateList  extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ListTypeMdl data = new ListTypeMdl(0, "List Type", 0);
        CreateListTypeImpl db = new CreateListTypeImpl(getContext(), data);
        ///db.save();

        View view =  inflater.inflate(R.layout.list_types, container, false);
        ListView lv = (ListView) view.findViewById(R.id.ListV);
        Bundle bd = getArguments();

        // Get all list type by category ID
        ListTypeAdpr adapter = new ListTypeAdpr(getContext(), db.readData(bd.getInt("cat_id")));

        lv.setAdapter(adapter);

        return view;
    }
}
