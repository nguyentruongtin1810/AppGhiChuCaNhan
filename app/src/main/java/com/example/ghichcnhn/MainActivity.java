package com.example.ghichcnhn;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText etSearch;
    private Button btnOpenAdd;
    private ListView lvNotes;

    private ArrayList<Note> displayList = new ArrayList<>();
    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearch = findViewById(R.id.etSearch);
        btnOpenAdd = findViewById(R.id.btnOpenAdd);
        lvNotes = findViewById(R.id.lvNotes);

        adapter = new NoteAdapter(this, displayList);
        lvNotes.setAdapter(adapter);

        // Bấm nút chuyển sang màn hình tạo ghi chú mới
        btnOpenAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
            startActivity(intent);
        });

        // Bấm vào 1 dòng -> mở xem chi tiết
        lvNotes.setOnItemClickListener((parent, view, position, id) -> {
            Note selectedNote = displayList.get(position);
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("EXTRA_NOTE_ID", selectedNote.getId());
            startActivity(intent);
        });

        // Lọc tìm kiếm theo tiêu đề hoặc nội dung
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterNotes(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    // Tải lại danh sách khi quay lại màn hình chính
    @Override
    protected void onResume() {
        super.onResume();
        filterNotes(etSearch.getText().toString());
    }

    private void filterNotes(String keyword) {
        displayList.clear();
        String query = keyword.trim().toLowerCase();

        if (query.isEmpty()) {
            displayList.addAll(NoteManager.noteList);
        } else {
            for (Note n : NoteManager.noteList) {
                boolean matchTitle = n.getTitle().toLowerCase().contains(query);
                boolean matchContent = n.getContent().toLowerCase().contains(query);
                if (matchTitle || matchContent) {
                    displayList.add(n);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}