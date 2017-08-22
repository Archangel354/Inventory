package com.example.android.inventory;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.inventory.data.InventoryContract;

import static com.example.android.inventory.data.InventoryContract.ProductEntry.CONTENT_URI;

/**
 * Created by e244194 on 8/11/2017.
 */

public class InventoryCursorAdapter extends CursorAdapter{

    //Context ctx;
   // private Button tagButton = null;
    //boolean btnSellPushed = false;
    //private TextView txtQuantity;



    // The default constructor
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
    public void bindView(View view, final Context context, final Cursor cursor) {

        final int newQuantity = cursor.getInt(cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_QUANTITY));
        final int newID = cursor.getInt(cursor.getColumnIndex(InventoryContract.ProductEntry.MY_PRODUCT_ID));

        // Find individual views that we want to modify or use in the list item layout
        TextView txtProductName = (TextView) view.findViewById(R.id.txtProductName);
        TextView txtQuantity = (TextView) view.findViewById(R.id.txtQuantity);
        TextView txtPrice = (TextView) view.findViewById(R.id.txtPrice);
        //TextView txtVendor = (TextView) view.findViewById(R.id.txtVendor);
        //ImageView imgProduct = (ImageView) view.findViewById(R.id.imgProduct);

        // Find the columns of product attributes that we're interested in
        int nameIDIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.MY_PRODUCT_ID);
        int nameColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_NAME);
        int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_PRICE);
        //int vendorColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_VENDOR);
        //int imageColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_IMAGE);

        // Read the product attributes from the Cursor for the current product
        //String   productID = cursor.getString(nameIDIndex);
        String productName = cursor.getString(nameColumnIndex);
        String productQuantity = cursor.getString(quantityColumnIndex);
        Float productPrice = cursor.getFloat(priceColumnIndex);
        //String productVendor = cursor.getString(vendorColumnIndex);
        //byte[] productImage = cursor.getBlob(imageColumnIndex);

        // Update the TextViews and ImageViews with the attributes for the current product
        txtProductName.setText(productName);
        txtQuantity.setText(productQuantity);
        txtPrice.setText(productPrice.toString());
        //      txtVendor.setText(productVendor);
        //Bitmap bitmap = BitmapFactory.decodeByteArray(productImage, 0, productImage.length);
        //imgProduct.setImageBitmap(bitmap);
        //txtProductName.setText(productName);


        // Setup the Onclick listener for the button to sell a specific product
        //  Don't let the product quantity go below zero.
        Button btnSell = (Button) view.findViewById(R.id.btnSell);
        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();

                if (newQuantity > 0){

                    int mQuantity = newQuantity - 1;
                    cv.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, mQuantity);
                    Uri mUri = ContentUris.withAppendedId(InventoryContract.ProductEntry.CONTENT_URI, newID);
                    context.getContentResolver().update(mUri, cv, null, null);
                }

                context.getContentResolver().notifyChange(InventoryContract.ProductEntry.CONTENT_URI, null);
            }
        });



    }
}
