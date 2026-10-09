package com.example.ghichcnhn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Note> list;

    public NoteAdapter(Context context, ArrayList<Note> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        }

        Note note = list.get(position);

        TextView tvTitle = convertView.findViewById(R.id.tvItemTitle);
        TextView tvContent = convertView.findViewById(R.id.tvItemContent);
        TextView tvDate = convertView.findViewById(R.id.tvItemDate);

        tvTitle.setText(note.getTitle());
        tvContent.setText(note.getContent());
        tvDate.setText("Cập nhật: " + note.getUpdatedAt());

        return convertView;
    }
}