package com.example.android.inventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
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

    Context ctx;
    private Button tagButton = null;
    boolean btnSellPushed = false;



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
    public void bindView(View view, final Context context, Cursor cursor) {
        // Find individual views that we want to modify or use in the list item layout
        TextView txtProductName = (TextView) view.findViewById(R.id.txtProductName);
        TextView txtQuantity = (TextView) view.findViewById(R.id.txtQuantity);
        TextView txtPrice = (TextView) view.findViewById(R.id.txtPrice);
        //ImageView imgProduct = (ImageView) view.findViewById(R.id.imgProduct);

        // Find the columns of product attributes that we're interested in
        int nameColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_NAME);
        int quantityColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_PRICE);
        //int imageColumnIndex = cursor.getColumnIndex(InventoryContract.ProductEntry.COLUMN_PRODUCT_IMAGE);

        // Read the product attributes from the Cursor for the current product
        String productName = cursor.getString(nameColumnIndex);
        final Integer productQuantity = cursor.getInt(quantityColumnIndex);
        Float productPrice = cursor.getFloat(priceColumnIndex);
        //byte[] productImage = cursor.getBlob(imageColumnIndex);

        // Update the TextViews and ImageViews with the attributes for the current product
        txtProductName.setText(productName);
        txtQuantity.setText(productQuantity.toString());
        txtPrice.setText(productPrice.toString());
        //Bitmap bitmap = BitmapFactory.decodeByteArray(productImage, 0, productImage.length);
        //imgProduct.setImageBitmap(bitmap);
        txtProductName.setText(productName);


        Button btnSell = (Button) view.findViewById(R.id.btnSell);
        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSellPushed = true;
                Log.i("Inside btnSellPushed: ", String.valueOf(btnSellPushed));
                if (btnSellPushed == true){
                    ContentValues cv = new ContentValues();
                    cv.put(InventoryContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, productQuantity - 1);
                    Uri mCurrentProductUri = CONTENT_URI;
                    Uri.Builder newUri = mCurrentProductUri.buildUpon().appendPath("3");
                    int rowsAffected = context.getContentResolver().update(Uri.parse(newUri.toString()), cv, null, null);
                    Log.i("Inside if btn: ", String.valueOf(btnSellPushed));
                }
            }
        }


        );


    }
}
