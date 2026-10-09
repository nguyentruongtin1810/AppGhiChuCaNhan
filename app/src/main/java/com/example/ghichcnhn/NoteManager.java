package com.example.ghichcnhn;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NoteManager {
    // Mảng tĩnh lưu trữ toàn bộ danh sách ghi chú
    public static ArrayList<Note> noteList = new ArrayList<>();
    private static int autoId = 1;

    static {
        // Nạp dữ liệu mẫu ban đầu để khi mở app luôn có dữ liệu test
        addSampleNote("Kế hoạch học tập", "Ôn tập lập trình Android và cấu trúc dữ liệu.");
        addSampleNote("Danh sách mua sắm", "Mua sổ tay, bút dạ quang, nước uống.");
    }
    public static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    private static void addSampleNote(String title, String content) {
        String id = "NOTE_" + String.format(Locale.getDefault(), "%02d", autoId++);
        String time = getCurrentDateTime();
        noteList.add(new Note(id, title, content, time, time));
    }

    // Sinh mã ghi chú mới
    public static String generateId() {
        return "NOTE_" + String.format(Locale.getDefault(), "%02d", autoId++);
    }

    // Tìm một ghi chú trong mảng theo ID
    public static Note findById(String id) {
        for (Note n : noteList) {
            if (n.getId().equalsIgnoreCase(id)) {
                return n;
            }
        }
        return null;
    }

    // Xóa ghi chú khỏi mảng theo ID
    public static boolean deleteById(String id) {
        for (int i = 0; i < noteList.size(); i++) {
            if (noteList.get(i).getId().equalsIgnoreCase(id)) {
                noteList.remove(i);
                return true;
            }
        }
        return false;
    }
}