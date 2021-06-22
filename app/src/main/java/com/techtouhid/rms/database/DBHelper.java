package com.techtouhid.rms.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.techtouhid.rms.models.OrdersModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBNAME = "rms.db";
    final static int DBVERSION = 1 ;

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Insert order
        db.execSQL(
                "create table orders" +
                        "(id integer primary key autoincrement," +
                        "name text," +
                        "phone text," +
                        "price text," +
                        "image int," +
                        "quantity int," +
                        "description text," +
                        "foodname text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists orders");
        onCreate(db);
    }

    public boolean insertOrder(String name, String phone, int price, int image, int quantity, String desc, String foodName) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("description", desc);
        values.put("foodname", foodName);
        values.put("quantity", quantity);

        long id = db.insert("orders",null,  values);
        if (id <= 0) {
            return false;
        } else {
            return true;
        }
    }
    public boolean updateOrder(String name, String phone, int price, int image, int quantity, String desc, String foodName, int id) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("description", desc);
        values.put("foodname", foodName);
        values.put("quantity", quantity);

        long row = db.update("orders", values, "id=" + id, null);
        if (row <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<OrdersModel> getOrders() {
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select id, foodname, image, price from orders", null);

        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                OrdersModel model = new OrdersModel();
                model.setOrderNumber(cursor.getInt(0) + "");
                model.setSoldItemName(cursor.getString(1));
                model.setImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3) + "");
                orders.add(model);

            }
        }
        cursor.close();
        db.close();
        return orders;
    }

    public Cursor getOrderById(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from orders where id = " + id, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
}
