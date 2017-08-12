package com.example.android.inventory;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by e244194 on 8/11/2017.
 */

public class InventoryCursorAdapter extends CursorAdapter{


    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }



//    This method binds the product data (in the current row pointed to by cursor) to the given
//    ist item layout. For example, the name for the current product can be set on the name TextView
//    in the list item layout.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find individual views that we want to modify or use in the list item layout
        TextView txtProductName = (TextView) view.findViewById(R.id.txtProductName);
        TextView txtQuantity = (TextView) view.findViewById(R.id.txtQuantity);
        TextView txtPrice = (TextView) view.findViewById(R.id.txtPrice);
        Button btnSell = (Button) view.findViewById(R.id.btnSell);




    }
}
