package com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.R;

import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "doAn_VPPXuanCuong.db";
    private static final int DATABASE_VERSION = 1;

    // Tên bảng và các cột cho Category
    public static final String TABLE_CATEGORY = "category";
    public static final String COLUMN_CATEGORY_ID = "id";
    public static final String COLUMN_CATEGORY_NAME = "name";
    public static final String COLUMN_CATEGORY_IMAGE = "image";

    // Tên bảng và các cột cho Item
    public static final String TABLE_ITEM = "item";
    public static final String COLUMN_ITEM_ID = "id";
    public static final String COLUMN_ITEM_CATEGORY_ID = "idc";
    public static final String COLUMN_ITEM_NAME = "name";
    public static final String COLUMN_ITEM_PRICE = "price";
    public static final String COLUMN_ITEM_QUANTITY = "quantity";
    public static final String COLUMN_ITEM_DESCRIBE = "describe";
    public static final String COLUMN_ITEM_IMAGE = "image";

    // Câu lệnh SQL để tạo bảng Category
    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY + " ("
            + COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CATEGORY_NAME + " TEXT, "
            + COLUMN_CATEGORY_IMAGE + " INTEGER)";

    // Câu lệnh SQL để tạo bảng Item
    private static final String CREATE_TABLE_ITEM = "CREATE TABLE " + TABLE_ITEM + " ("
            + COLUMN_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ITEM_CATEGORY_ID + " INTEGER, "
            + COLUMN_ITEM_NAME + " TEXT, "
            + COLUMN_ITEM_PRICE + " REAL, "
            + COLUMN_ITEM_QUANTITY + " INTEGER, "
            + COLUMN_ITEM_DESCRIBE + " TEXT, "
            + COLUMN_ITEM_IMAGE + " INTEGER, "
            + "FOREIGN KEY(" + COLUMN_ITEM_CATEGORY_ID + ") REFERENCES " + TABLE_CATEGORY + "(" + COLUMN_CATEGORY_ID + "))";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_ITEM);

        addDefaultCategories(db);

        // Thêm dữ liệu mặc định vào bảng Item
        addDefaultItems(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion!=oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY );
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM );
        }
    }
    private void addDefaultCategories(SQLiteDatabase db) {
        // Thêm dữ liệu mặc định cho bảng Category
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY_NAME, "Sách");
        values.put(COLUMN_CATEGORY_IMAGE, R.drawable.category_sach);
        db.insert(TABLE_CATEGORY, null, values);

        values.clear();

        values.put(COLUMN_CATEGORY_NAME, "Viết");
        values.put(COLUMN_CATEGORY_IMAGE, R.drawable.category_viet);
        db.insert(TABLE_CATEGORY, null, values);
        values.clear();

        values.put(COLUMN_CATEGORY_NAME, "Giấy In");
        values.put(COLUMN_CATEGORY_IMAGE, R.drawable.category_giayin);
        db.insert(TABLE_CATEGORY, null, values);
        values.clear();

        values.put(COLUMN_CATEGORY_NAME, "Dụng Cụ Học Tập");
        values.put(COLUMN_CATEGORY_IMAGE, R.drawable.category_dungcuhoctap);
        db.insert(TABLE_CATEGORY, null, values);
        values.clear();

        values.put(COLUMN_CATEGORY_NAME, "Mỹ Thuật");
        values.put(COLUMN_CATEGORY_IMAGE, R.drawable.category_mythuat);
        db.insert(TABLE_CATEGORY, null, values);
        values.clear();

        values.put(COLUMN_CATEGORY_NAME, "Văn Phòng Phẩm");
        values.put(COLUMN_CATEGORY_IMAGE, R.drawable.category_vanphongpham);
        db.insert(TABLE_CATEGORY, null, values);
        values.clear();

        values.put(COLUMN_CATEGORY_NAME, "Rạng Đông");
        values.put(COLUMN_CATEGORY_IMAGE, R.drawable.category_rangdong);
        db.insert(TABLE_CATEGORY, null, values);

        // Thêm các dòng dữ liệu khác nếu cần
    }

    private void addDefaultItems(SQLiteDatabase db) {
        // Thêm dữ liệu mặc định cho bảng Item
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_CATEGORY_ID, 1); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Viết Bi Thiên Long TL-079");
        values.put(COLUMN_ITEM_PRICE, 3675);
        values.put(COLUMN_ITEM_QUANTITY, 1780);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Viết Bi Thiên Long TL-079");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.viet_tl_079);
        db.insert(TABLE_ITEM, null, values);

        values.clear();

        values.put(COLUMN_ITEM_CATEGORY_ID, 1); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Viết Bi Thiên Long TL-093");
        values.put(COLUMN_ITEM_PRICE, 3200);
        values.put(COLUMN_ITEM_QUANTITY, 2000);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Viết Bi Thiên Long TL-093");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.viet_tl_093);
        db.insert(TABLE_ITEM, null, values);

        // Thêm các dòng dữ liệu khác nếu cần
    }

    Cursor getCategoriesCursor() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                COLUMN_CATEGORY_ID,
                COLUMN_CATEGORY_NAME,
                COLUMN_CATEGORY_IMAGE
        };
        return db.query(
                TABLE_CATEGORY,
                projection,
                null,
                null,
                null,
                null,
                null
        );
    }
    Cursor getItemsCursor() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                COLUMN_ITEM_ID,
                COLUMN_ITEM_CATEGORY_ID,
                COLUMN_ITEM_NAME,
                COLUMN_ITEM_PRICE,
                COLUMN_ITEM_QUANTITY,
                COLUMN_ITEM_DESCRIBE,
                COLUMN_ITEM_IMAGE
        };
        return db.query(
                TABLE_ITEM,
                projection,
                null,
                null,
                null,
                null,
                null
        );
    }


}

