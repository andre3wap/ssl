package com.andre3.smartshopperslist.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.andre3.smartshopperslist.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

/**
 * Created by andre3 on 10/5/16.
 */

public class ExpandableListType extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;
    TextView qty_tv;
    int i = 1;
    int pos;

     ArrayList arr = new ArrayList();
    public ExpandableListType(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }



        TextView txtListChild1 = (TextView) convertView.findViewById(R.id.lblListItem);
       final TextView txtListChild2 = (TextView) convertView.findViewById(R.id.price_tv);
       final TextView txtListChild3 = (TextView) convertView.findViewById(R.id.qty_tv);
        TextView txtListChild4 = (TextView) convertView.findViewById(R.id.isle_tv);


        ///Format: id/name/price/qty/isle
       final String childTitle[] = childText.split("/");

        if(!childTitle[1].isEmpty()) {
            txtListChild1.setText(childTitle[0] + " - " +childTitle[1]);
        }if(!childTitle[2].isEmpty()){
            Integer qtyInt = Integer.parseInt(childTitle[3]);
            Float priceInt = Float.parseFloat(childTitle[2]) * qtyInt;
            txtListChild2.setText("$" + priceInt);
        }if(!childTitle[3].isEmpty()){
            txtListChild3.setText("Quantity: " +childTitle[3]);
        }if(!childTitle[4].isEmpty()){
            txtListChild4.setText("Isle: " +childTitle[4]);
        }


        final ImageButton inQty = (ImageButton)convertView.findViewById(R.id.increaseQty);
        inQty.setTag(childTitle[0]);

        qty_tv = (TextView)convertView.findViewById(R.id.qty_tv);
        qty_tv.setTag(childTitle[0]);

        inQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Add each tagId to an array for record keeping
                arr.add(inQty.getTag());

                int index = arr.size();
                int prevIndex;

                // Get the previous index to find out which row was clicked last.
                if(index >= 2) {
                     prevIndex = index - 2;
                }else {
                     prevIndex = 0;
                }

                // Reset count when user clicks a new row.
                if((Integer.parseInt(inQty.getTag().toString()) != Integer.parseInt(arr.get(prevIndex).toString()))){
                    i = 0;
                }

                //TODO: might need later to get itemID
                String[] qty = qty_tv.getText().toString().split(":");

                // Calculating Quantity and price
                Integer qtyInt = Integer.parseInt(childTitle[3]) + i;
                Float priceInt = Float.parseFloat(childTitle[2]) * qtyInt;
                txtListChild3.setText("Quantity: " + qtyInt);
                txtListChild2.setText("$" + priceInt);

                i++;
            }
        });


        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle + " ( "+getChildrenCount(groupPosition)+" )");

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
