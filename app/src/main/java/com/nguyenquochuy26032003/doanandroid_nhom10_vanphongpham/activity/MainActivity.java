package com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.activity;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.doituong.Category;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.adapter.CategoryAdapter;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.data.DatabaseHelper;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.data.DatabaseManager;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.doituong.Item;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.adapter.ItemAdapter;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewFlipper viewFlipper;
    private NavigationView navigationView;
    private ListView listViewTrangChu;
    private DrawerLayout drawerLayout;
    private CategoryAdapter categoryAdapter;
    private ItemAdapter itemAdapter;
    private DatabaseManager dbmana;
    private DatabaseHelper dbhelper;
    private List<Category> categories;
    private List<Item> items;
    private GridView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      Ánh xạ layout
        AnhXa();
//      Gọi phương thức actionBar
        ActionBar();
        if(isConnected(this)) {
            // Gọi phương thức ActionViewFilipper(): nội dung thanh trượt
            ActionViewFilipper();
            // listview loại hàng
            ListViewCategories();
            // listview item
            ListViewItem();
            getEventClickCategory();
        } else {
            Toast.makeText(getApplicationContext(), "Không có kết nối mạng", Toast.LENGTH_LONG).show();
        }


    }

    private void getEventClickCategory() {
        listViewTrangChu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != categories.size() - 1) {
                    switch (position) {
                        case 0:
                            // Mục "Trang chủ" được chọn
                            break;
                        case 1:
                            // Mục "Sách" được chọn
                            Intent sach = new Intent(getApplicationContext(), SachActivity.class);
                            startActivity(sach);
                            break;
                        case 2:
                            // Mục "Viết" được chọn
                            Intent viet = new Intent(getApplicationContext(), VietActivity.class);
                            startActivity(viet);
                            break;
                        // Thêm các case khác tương ứng với số lượng mục từ cơ sở dữ liệu
                    }
                } else {
                    // Mục "Thoát ứng dụng" được chọn
                    ExitApp();
                }
            }
        });
    }

    private void ListViewItem() {
        dbmana = new DatabaseManager(MainActivity.this);
        dbhelper = new DatabaseHelper(MainActivity.this);

        // Lấy danh sách Category từ cơ sở dữ liệu
        items = dbmana.getAllItems();

        // Khởi tạo và đặt Adapter cho ListView
        itemAdapter = new ItemAdapter(this, items);
        recyclerview.setAdapter(itemAdapter);
    }

    private void ListViewCategories() {
        dbmana = new DatabaseManager(MainActivity.this);
        dbhelper = new DatabaseHelper(MainActivity.this);

        // Lấy danh sách Category từ cơ sở dữ liệu
        categories = dbmana.getCategories();

        Category homeCategory = new Category(0, "Trang chủ", R.drawable.home);
        categories.add(0, homeCategory); // Thêm vào đầu danh sách

        // Khởi tạo và đặt Adapter cho ListView
        categoryAdapter = new CategoryAdapter(this, categories);
        listViewTrangChu.setAdapter(categoryAdapter);

        // Thêm mục "Thoát ứng dụng"
        Category exitCategory = new Category(-1, "Thoát ứng dụng", R.drawable.baseline_exit_to_app_24);
        categories.add(exitCategory);

    }

    private void ActionViewFilipper() {
        List<Integer> arrayViewFlipper = new ArrayList<>();

        int image1Id = getResources().getIdentifier("banner_1", "drawable", getPackageName());
        int image2Id = getResources().getIdentifier("banner_2", "drawable", getPackageName());
        int image3Id = getResources().getIdentifier("banner_3", "drawable", getPackageName());

        // Thêm ID vào danh sách
        arrayViewFlipper.add(image1Id);
        arrayViewFlipper.add(image2Id);
        arrayViewFlipper.add(image3Id);

        for (int i = 0; i < arrayViewFlipper.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(arrayViewFlipper.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation silde_to = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.silde_to_right);
        Animation silde_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(silde_to);
        viewFlipper.setOutAnimation(silde_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolBarTrangChu);
        viewFlipper = findViewById(R.id.viewFlipper);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);
        listViewTrangChu= findViewById(R.id.listViewTrangChu);
        recyclerview = findViewById(R.id.recyclerview);
    }


    private void ExitApp() {
        AlertDialog.Builder exit = new AlertDialog.Builder(this);
        exit.setTitle("Thông Báo")
                .setMessage("Bạn có muốn thoát ứng dụng không?")
                .setCancelable(false)
                .setPositiveButton("Có", (dialog, which) -> {
                    finish();
                })
                .setNegativeButton("Không", (dialog, which) -> {
                    dialog.dismiss();
                });
        exit.create().show();
    }

    private boolean isConnected (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null && wifi.isConnected()) || (mobile != null && mobile.isConnected())) {
            return true;
        } else return false;
    }

    protected void onDestroy() {
        dbmana.closeDatabase();
        super.onDestroy();
    }
}