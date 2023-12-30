package com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.doituong.Category;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.doituong.Item;

import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void openDatabase() {
        database = dbHelper.getWritableDatabase();
    }

    public void closeDatabase() {
        if (database != null && database.isOpen()) {
            database.close();
        }
    }
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        Cursor cursor = dbHelper.getCategoriesCursor();
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CATEGORY_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CATEGORY_NAME));
                int imageResourceId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CATEGORY_IMAGE));

                Category category = new Category(id, name, imageResourceId);
                categories.add(category);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return categories;
    }
    public List<Item> getItems(int categoryId) {
        List<Item> items = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseHelper.COLUMN_ITEM_ID,
                DatabaseHelper.COLUMN_ITEM_CATEGORY_ID,
                DatabaseHelper.COLUMN_ITEM_NAME,
                DatabaseHelper.COLUMN_ITEM_PRICE,
                DatabaseHelper.COLUMN_ITEM_QUANTITY,
                DatabaseHelper.COLUMN_ITEM_DESCRIBE,
                DatabaseHelper.COLUMN_ITEM_IMAGE
        };

        String selection = DatabaseHelper.COLUMN_ITEM_CATEGORY_ID + " = ?";
        String[] selectionArgs = {String.valueOf(categoryId)};

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_ITEM,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ITEM_ID));
                int categoryIdFromDb = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ITEM_CATEGORY_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ITEM_NAME));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ITEM_PRICE));
                int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ITEM_QUANTITY));
                String describe = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ITEM_DESCRIBE));
                int imageResourceId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ITEM_IMAGE));

                Item item = new Item(id, categoryIdFromDb, name, (float) price, quantity, describe, imageResourceId);
                items.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return items;
    }
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_ITEM,
                null,
                null,
                null,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ITEM_ID));
                int categoryId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ITEM_CATEGORY_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ITEM_NAME));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ITEM_PRICE));
                int quantity = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ITEM_QUANTITY));
                String describe = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ITEM_DESCRIBE));
                int imageResourceId = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ITEM_IMAGE));

                Item item = new Item(id, categoryId, name, (float) price, quantity, describe, imageResourceId);
                items.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return items;
    }

    // Thêm dữ liệu của danh mục
    public long insertCategory(String name, int image) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_CATEGORY_NAME, name);
        values.put(DatabaseHelper.COLUMN_CATEGORY_IMAGE, image);
        return database.insert(DatabaseHelper.TABLE_CATEGORY, null, values);
    }
    // Hàm sửa dữ liệu Category
    public void updateCategory(long categoryId, String newName, int newImage) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_CATEGORY_NAME, newName);
        values.put(DatabaseHelper.COLUMN_CATEGORY_IMAGE, newImage);

        db.update(DatabaseHelper.TABLE_CATEGORY, values, DatabaseHelper.COLUMN_CATEGORY_ID + "=?", new String[]{String.valueOf(categoryId)});
        db.close();
    }

    // Hàm xóa dữ liệu Category
    public void deleteCategory(long categoryId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_CATEGORY, DatabaseHelper.COLUMN_CATEGORY_ID + "=?", new String[]{String.valueOf(categoryId)});
        db.close();
    }
    // Thêm dữ liệu cảu sản phầm
    public long insertItem(long categoryId, String name, double price, int quantity, String describe, String imagePath) {
        // Mở cơ sở dữ liệu
        openDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_ITEM_CATEGORY_ID, categoryId);
        values.put(DatabaseHelper.COLUMN_ITEM_NAME, name);
        values.put(DatabaseHelper.COLUMN_ITEM_PRICE, price);
        values.put(DatabaseHelper.COLUMN_ITEM_QUANTITY, quantity);
        values.put(DatabaseHelper.COLUMN_ITEM_DESCRIBE, describe);
        values.put(DatabaseHelper.COLUMN_ITEM_IMAGE, imagePath);
        long result = database.insert(DatabaseHelper.TABLE_ITEM, null, values);
        // Đóng cơ sở dữ liệu
        closeDatabase();
        return result;
    }
    // Hàm sửa dữ liệu Item
    public void updateItem(long itemId, long newCategoryId, String newName, double newPrice, int newQuantity, String newDescribe, int newImage) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_ITEM_CATEGORY_ID, newCategoryId);
        values.put(DatabaseHelper.COLUMN_ITEM_NAME, newName);
        values.put(DatabaseHelper.COLUMN_ITEM_PRICE, newPrice);
        values.put(DatabaseHelper.COLUMN_ITEM_QUANTITY, newQuantity);
        values.put(DatabaseHelper.COLUMN_ITEM_DESCRIBE, newDescribe);
        values.put(DatabaseHelper.COLUMN_ITEM_IMAGE, newImage);

        db.update(DatabaseHelper.TABLE_ITEM, values, DatabaseHelper.COLUMN_ITEM_ID + "=?", new String[]{String.valueOf(itemId)});
        db.close();
    }

    // Hàm xóa dữ liệu Item
    public void deleteItem(long itemId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_ITEM, DatabaseHelper.COLUMN_ITEM_ID + "=?", new String[]{String.valueOf(itemId)});
        db.close();
    }

    public List<String> getImageList() {
        List<String> imageList = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                true, // distinct
                DatabaseHelper.TABLE_ITEM, // table name
                new String[]{DatabaseHelper.COLUMN_ITEM_IMAGE}, // columns to return
                null, // selection
                null, // selectionArgs
                null, // groupBy
                null, // having
                null, // orderBy
                null  // limit
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ITEM_IMAGE));
                imageList.add(imagePath);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return imageList;
    }
    public String getCategoryNameById(int categoryId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                DatabaseHelper.COLUMN_CATEGORY_NAME
        };

        String selection = DatabaseHelper.COLUMN_CATEGORY_ID + " = ?";
        String[] selectionArgs = {String.valueOf(categoryId)};

        Cursor cursor = db.query(
                DatabaseHelper.TABLE_CATEGORY,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        String categoryName = "";
        if (cursor.moveToFirst()) {
            categoryName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_CATEGORY_NAME));
        }

        cursor.close();
        return categoryName;
    }
    // Thêm các phương thức khác để thực hiện các thao tác khác nếu cần thiết
}