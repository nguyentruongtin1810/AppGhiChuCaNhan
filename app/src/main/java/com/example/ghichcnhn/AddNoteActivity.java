package com.example.ghichcnhn;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {
    // 1. Khai báo các thành phần giao diện
    private EditText etTitle, etContent;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        // 2. Ánh xạ View từ layout XML qua id
        etTitle = findViewById(R.id.etNewTitle);
        etContent = findViewById(R.id.etNewContent);
        btnSave = findViewById(R.id.btnSaveNew);

        // 3. Bắt sự kiện bấm nút Lưu
        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String content = etContent.getText().toString().trim();

            // Kiểm tra rỗng
            if (title.isEmpty() && content.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập tiêu đề hoặc nội dung!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Tự sinh mã ghi chú và lấy ngày giờ hiện tại
            String id = NoteManager.generateId();
            String now = NoteManager.getCurrentDateTime();

            // Tạo đối tượng Note mới và thêm vào đầu mảng tĩnh
            Note newNote = new Note(id, title, content, now, now);
            NoteManager.noteList.add(0, newNote);

            Toast.makeText(this, "Đã tạo ghi chú: " + id, Toast.LENGTH_SHORT).show();
            finish(); // Đóng Activity hiện tại để quay về MainActivity
        });
    }
}