package com.example.wumeilun.cashdiary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class ContentAdapter extends BaseAdapter {

    private List<Map<String, Object>> item_content;

    private LayoutInflater inflater;

    public ContentAdapter(Context context, List<Map<String, Object>> item_content) {
        this.item_content = item_content;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return item_content.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return item_content.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.amount = convertView.findViewById(R.id.amount);
            holder.date = convertView.findViewById(R.id.date);
            holder.comment = convertView.findViewById(R.id.comment);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.amount.setText((String) item_content.get(item_content.size() - position - 1).get("amount"));
        holder.date.setText((String) item_content.get(item_content.size() - position - 1).get("date"));
        holder.comment.setText((String) item_content.get(item_content.size() - position - 1).get("comment"));

        return convertView;
    }

    public class ViewHolder {
        TextView amount;
        TextView date;
        TextView comment;
    }
}
