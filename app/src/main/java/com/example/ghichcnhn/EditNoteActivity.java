package com.example.ghichcnhn;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditNoteActivity extends AppCompatActivity {
    private EditText etTitle, etContent;
    private Button btnSave;
    private String currentNoteId;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        etTitle = findViewById(R.id.etEditTitle);
        etContent = findViewById(R.id.etEditContent);
        btnSave = findViewById(R.id.btnUpdateSave);

        currentNoteId = getIntent().getStringExtra("EXTRA_NOTE_ID");
        note = NoteManager.findById(currentNoteId);

        if (note != null) {
            etTitle.setText(note.getTitle());
            etContent.setText(note.getContent());
        } else {
            Toast.makeText(this, "Không tìm thấy ghi chú!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        btnSave.setOnClickListener(v -> {
            String newTitle = etTitle.getText().toString().trim();
            String newContent = etContent.getText().toString().trim();

            if (newTitle.isEmpty() && newContent.isEmpty()) {
                Toast.makeText(this, "Nội dung ghi chú không được để trống!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Cập nhật thông tin trực tiếp vào phần tử trong mảng
            note.setTitle(newTitle);
            note.setContent(newContent);
            note.setUpdatedAt(NoteManager.getCurrentDateTime());

            Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
            finish(); // Quay lại màn hình Detail
        });
    }
}