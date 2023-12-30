package com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.R;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.adapter.ShowSanPhamAdapter;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.data.DatabaseManager;
import com.nguyenquochuy26032003.doanandroid_nhom10_vanphongpham.doituong.Item;

import java.util.ArrayList;
import java.util.List;

public class DeleteActivity extends AppCompatActivity {
    EditText EditDeleteID;
    Button btnSubmitDelete;
    List<Item> list = new ArrayList<>();
    private ShowSanPhamAdapter showSanPhamAdapter;
    ListView listViewDelete;
    Toolbar toolbar;
    private DatabaseManager dbmana;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        AnhXa();
        ActionToolBar();
        dbmana = new DatabaseManager(DeleteActivity.this);
        showSanPham();
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbarDelete);
        EditDeleteID = findViewById(R.id.editDeleteIDSanPham);
        listViewDelete = findViewById(R.id.listViewDelete);
        btnSubmitDelete = findViewById(R.id.btnSubmitDelete);
        btnSubmitDelete.setOnClickListener(new MyEvent());
    }

    private class MyEvent implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.btnSubmitDelete)
            {
                deleteSanPham();
            }
        }
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Tạo một biểu đồ vector cho nút back với màu trắng
        Drawable backArrow = ContextCompat.getDrawable(this, R.drawable.baseline_arrow_back_ios_new_24);
        if (backArrow != null) {
            backArrow = DrawableCompat.wrap(backArrow);
            DrawableCompat.setTint(backArrow, ContextCompat.getColor(this, android.R.color.white));
            getSupportActionBar().setHomeAsUpIndicator(backArrow);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void deleteSanPham() {
        // Lấy ID của sản phẩm muốn xóa từ EditText hoặc bất kỳ nguồn dữ liệu nào bạn đang sử dụng
        String itemIdStr = EditDeleteID.getText().toString().trim();

        if (!itemIdStr.isEmpty()) {
            long itemId = Long.parseLong(itemIdStr);

            // Tạo hộp thoại xác nhận trước khi xóa
            AlertDialog.Builder builder = new AlertDialog.Builder(DeleteActivity.this);
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này?");
            builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Gọi phương thức xóa sản phẩm từ DatabaseManager
                    dbmana.deleteItem(itemId);

                    // Hiển thị thông báo xóa thành công
                    Toast.makeText(DeleteActivity.this, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();

                    // Làm mới danh sách sản phẩm để hiển thị danh sách mới
                    showSanPham();
                }
            });
            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Đóng hộp thoại nếu người dùng chọn hủy
                    dialog.dismiss();
                }
            });

            // Hiển thị hộp thoại xác nhận
            builder.show();
        } else {
            // Hiển thị thông báo nếu ID sản phẩm không được nhập
            Toast.makeText(DeleteActivity.this, "Vui lòng nhập ID sản phẩm cần xóa", Toast.LENGTH_SHORT).show();
        }
    }

    private void showSanPham() {
        list = dbmana.getAllItems();
        showSanPhamAdapter = new ShowSanPhamAdapter(DeleteActivity.this, list);
        listViewDelete.setAdapter(showSanPhamAdapter);
    }
}