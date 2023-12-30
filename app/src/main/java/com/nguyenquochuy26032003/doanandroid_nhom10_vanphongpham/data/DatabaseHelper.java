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
            + COLUMN_CATEGORY_IMAGE + " TEXT)";

    // Câu lệnh SQL để tạo bảng Item
    private static final String CREATE_TABLE_ITEM = "CREATE TABLE " + TABLE_ITEM + " ("
            + COLUMN_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ITEM_CATEGORY_ID + " INTEGER, "
            + COLUMN_ITEM_NAME + " TEXT, "
            + COLUMN_ITEM_PRICE + " REAL, "
            + COLUMN_ITEM_QUANTITY + " INTEGER, "
            + COLUMN_ITEM_DESCRIBE + " TEXT, "
            + COLUMN_ITEM_IMAGE + " TEXT, "
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
        ContentValues values = new ContentValues();

        // Item Sách
        values.put(COLUMN_ITEM_CATEGORY_ID, 1); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Sách Tiếng Anh Lớp 1");
        values.put(COLUMN_ITEM_PRICE, 37800);
        values.put(COLUMN_ITEM_QUANTITY, 73);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Sách Tiếng Anh Lớp 1");
        values.put(COLUMN_ITEM_IMAGE,R.drawable.sach_english_1);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        values.put(COLUMN_ITEM_CATEGORY_ID, 1); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Sách Tiếng Anh Lớp 2");
        values.put(COLUMN_ITEM_PRICE, 43200);
        values.put(COLUMN_ITEM_QUANTITY, 92);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Sách Tiếng Anh Lớp 2");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.sach_english_2);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        values.put(COLUMN_ITEM_CATEGORY_ID, 1); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Sách Tiếng Anh Lớp 3");
        values.put(COLUMN_ITEM_PRICE, 51300);
        values.put(COLUMN_ITEM_QUANTITY, 55);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Sách Tiếng Anh Lớp 3");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.sach_english_3);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        values.put(COLUMN_ITEM_CATEGORY_ID, 1); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Sách Tiếng Anh Lớp 4");
        values.put(COLUMN_ITEM_PRICE, 80100);
        values.put(COLUMN_ITEM_QUANTITY, 47);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Sách Tiếng Anh Lớp 4");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.sach_english_4);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        values.put(COLUMN_ITEM_CATEGORY_ID, 1); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Biết đúng biết sai thiên tài logic");
        values.put(COLUMN_ITEM_PRICE, 124200);
        values.put(COLUMN_ITEM_QUANTITY, 13);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Biết đúng biết sai thiên tài logic");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.sach_social_1);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        // Item Viết

        values.put(COLUMN_ITEM_CATEGORY_ID, 2); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Viết Bi Thiên Long TL-079");
        values.put(COLUMN_ITEM_PRICE, 3675);
        values.put(COLUMN_ITEM_QUANTITY, 1780);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Viết Bi Thiên Long TL-079");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.viet_tl_079);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        values.put(COLUMN_ITEM_CATEGORY_ID, 2); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Viết Bi Thiên Long TL-093");
        values.put(COLUMN_ITEM_PRICE, 3200);
        values.put(COLUMN_ITEM_QUANTITY, 2000);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Viết Bi Thiên Long TL-093");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.viet_tl_093);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        values.put(COLUMN_ITEM_CATEGORY_ID, 2); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Viết Bi Thiên Long TL-027");
        values.put(COLUMN_ITEM_PRICE, 4675);
        values.put(COLUMN_ITEM_QUANTITY, 371);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for BÚT BI THIÊN LONG TL-027");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.viet_tl_027);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        values.put(COLUMN_ITEM_CATEGORY_ID, 2); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Viết chì gỗ Thiên Long GP-018");
        values.put(COLUMN_ITEM_PRICE, 2200);
        values.put(COLUMN_ITEM_QUANTITY, 2000);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Bút chì gỗ Thiên Long GP-018");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.viet_gp_018);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        values.put(COLUMN_ITEM_CATEGORY_ID, 2); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Bút xóa Thiên Long CP-02");
        values.put(COLUMN_ITEM_PRICE, 25500);
        values.put(COLUMN_ITEM_QUANTITY, 200);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Bút xóa Thiên Long CP-02");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.viet_cp_02);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        // Item Giấy In

        values.put(COLUMN_ITEM_CATEGORY_ID, 3); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Giấy A4 70 gsm IK");
        values.put(COLUMN_ITEM_PRICE, 74700);
        values.put(COLUMN_ITEM_QUANTITY, 100);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Giấy A4 70 gsm IK");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.giayin_a4_70_ik);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        values.put(COLUMN_ITEM_CATEGORY_ID, 3); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Giấy A4 80 gsm IK");
        values.put(COLUMN_ITEM_PRICE, 76500);
        values.put(COLUMN_ITEM_QUANTITY, 100);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Giấy A4 80 gsm IK");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.giayin_a4_70_ik);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        values.put(COLUMN_ITEM_CATEGORY_ID, 3); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Giấy A4 80 gsm IK Plus");
        values.put(COLUMN_ITEM_PRICE, 76500);
        values.put(COLUMN_ITEM_QUANTITY, 100);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Giấy A4 80 gsm IK");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.giayin_a4_70_ikyellow);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        values.put(COLUMN_ITEM_CATEGORY_ID, 3); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Giấy IK Yellow đa năng A4");
        values.put(COLUMN_ITEM_PRICE, 77400);
        values.put(COLUMN_ITEM_QUANTITY, 100);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Giấy IK Yellow đa năng A4");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.giayin_a4_80_ikplus);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        // Item Dụng Cụ Học Tập

        values.put(COLUMN_ITEM_CATEGORY_ID, 4); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Ba lô chống gù");
        values.put(COLUMN_ITEM_PRICE, 599000);
        values.put(COLUMN_ITEM_QUANTITY, 3);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Ba lô chống gù");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.dungcuhoctap_balo);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        values.put(COLUMN_ITEM_CATEGORY_ID, 4); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Bảng đen học sinh");
        values.put(COLUMN_ITEM_PRICE, 246500);
        values.put(COLUMN_ITEM_QUANTITY, 12);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Bảng đen học sinh");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.dungcuhoctap_bangden);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        values.put(COLUMN_ITEM_CATEGORY_ID, 4); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Hộp đựng bút");
        values.put(COLUMN_ITEM_PRICE, 129500);
        values.put(COLUMN_ITEM_QUANTITY, 3);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Hộp đựng bút");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.dungcuhoctap_hopbut);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        // Item Mỹ thuật

        values.put(COLUMN_ITEM_CATEGORY_ID, 5); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Bộ dụng cụ mỹ thuật");
        values.put(COLUMN_ITEM_PRICE, 296800);
        values.put(COLUMN_ITEM_QUANTITY, 5);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Bộ dụng cụ mỹ thuật");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.mythuat_mau);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        values.put(COLUMN_ITEM_CATEGORY_ID, 5); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Bút chì màu");
        values.put(COLUMN_ITEM_PRICE, 34850);
        values.put(COLUMN_ITEM_QUANTITY, 20);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Bộ dụng cụ mỹ thuật");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.mythuat_butchimau);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        values.put(COLUMN_ITEM_CATEGORY_ID, 5); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Màu nước");
        values.put(COLUMN_ITEM_PRICE, 35100);
        values.put(COLUMN_ITEM_QUANTITY, 17);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Bộ dụng cụ mỹ thuật");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.mythuat_maunuoc);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        // Item Văn Phòng Phẩm

        values.put(COLUMN_ITEM_CATEGORY_ID, 6); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Máy tính tay");
        values.put(COLUMN_ITEM_PRICE, 129800);
        values.put(COLUMN_ITEM_QUANTITY, 12);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Máy tính tay");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.vanphongpham_maytinh);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        values.put(COLUMN_ITEM_CATEGORY_ID, 6); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Bìa Còng - Bìa Hồ Sơ");
        values.put(COLUMN_ITEM_PRICE, 129800);
        values.put(COLUMN_ITEM_QUANTITY, 12);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Bìa Còng - Bìa Hồ Sơ");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.vanphongpham_biahoso);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        values.put(COLUMN_ITEM_CATEGORY_ID, 6); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Bộ dụng cụ văn phòng");
        values.put(COLUMN_ITEM_PRICE, 84100);
        values.put(COLUMN_ITEM_QUANTITY, 12);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Bộ dụng cụ văn phòng");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.vanphongpham_bodungcuvanphong);
        db.insert(TABLE_ITEM, null, values);
        values.clear();
        // Item Rạng Đông

        values.put(COLUMN_ITEM_CATEGORY_ID, 7); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Đèn học chống cận");
        values.put(COLUMN_ITEM_PRICE, 249000);
        values.put(COLUMN_ITEM_QUANTITY, 7);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Đèn học chống cận");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.rangdong_den);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

        values.put(COLUMN_ITEM_CATEGORY_ID, 7); // ID của Category tương ứng
        values.put(COLUMN_ITEM_NAME, "Bình nước");
        values.put(COLUMN_ITEM_PRICE, 135400);
        values.put(COLUMN_ITEM_QUANTITY, 7);
        values.put(COLUMN_ITEM_DESCRIBE, "Description for Bình nước");
        values.put(COLUMN_ITEM_IMAGE, R.drawable.rangdong_binhnuoc);
        db.insert(TABLE_ITEM, null, values);
        values.clear();

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

