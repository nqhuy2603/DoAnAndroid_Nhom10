package com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBarMenu();
    }

    private void ActionBarMenu() {
        ActionBar actbar = getSupportActionBar();
        actbar.setTitle("Xuân Cường");
        actbar.setSubtitle("Văn Phòng Phẩm");
        actbar.setDisplayHomeAsUpEnabled(true);
        actbar.setDisplayShowHomeEnabled(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Check if the selected item is the back button
        if (id == android.R.id.home) {
            ExitApp();
            return true;
        } else if (id == android.R.id.title) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
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
}