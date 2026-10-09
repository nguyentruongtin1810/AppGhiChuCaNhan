package com.example.ghichcnhn;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private TextView tvTitle, tvId, tvCreatedAt, tvUpdatedAt, tvContent;
    private Button btnEdit, btnDelete;
    private String currentNoteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tvDetailTitle);
        tvId = findViewById(R.id.tvDetailId);
        tvCreatedAt = findViewById(R.id.tvDetailCreatedAt);
        tvUpdatedAt = findViewById(R.id.tvDetailUpdatedAt);
        tvContent = findViewById(R.id.tvDetailContent);
        btnEdit = findViewById(R.id.btnDetailEdit);
        btnDelete = findViewById(R.id.btnDetailDelete);

        currentNoteId = getIntent().getStringExtra("EXTRA_NOTE_ID");

        // Bấm nút sửa -> chuyển sang EditNoteActivity
        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(DetailActivity.this, EditNoteActivity.class);
            intent.putExtra("EXTRA_NOTE_ID", currentNoteId);
            startActivity(intent);
        });

        // Bấm nút xóa -> Hiện dialog xác nhận
        btnDelete.setOnClickListener(v -> confirmDelete());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNoteData();
    }

    private void loadNoteData() {
        Note note = NoteManager.findById(currentNoteId);
        if (note != null) {
            tvTitle.setText(note.getTitle().isEmpty() ? "(Không có tiêu đề)" : note.getTitle());
            tvId.setText("Mã ghi chú: " + note.getId());
            tvCreatedAt.setText("Thời gian tạo: " + note.getCreatedAt());
            tvUpdatedAt.setText("Thời gian cập nhật: " + note.getUpdatedAt());
            tvContent.setText(note.getContent());
        } else {
            // Trường hợp ghi chú đã bị xóa hoặc không tìm thấy
            finish();
        }
    }

    // Hiển thị hộp thoại xác nhận trước khi xóa
    private void confirmDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận xóa");
        builder.setMessage("Bạn có chắc chắn muốn xóa ghi chú này không?");
        builder.setPositiveButton("Xóa", (dialog, which) -> {
            boolean success = NoteManager.deleteById(currentNoteId);
            if (success) {
                Toast.makeText(DetailActivity.this, "Đã xóa ghi chú thành công", Toast.LENGTH_SHORT).show();
            }
            finish(); // Đóng màn hình chi tiết, trở về MainActivity
        });
        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }
}