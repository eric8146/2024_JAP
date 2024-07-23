package com.eric.a2024_teamproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodAdapter extends BaseAdapter {

    private ArrayList<FoodItem> foodList;
    private LayoutInflater inflater;

    public FoodAdapter(ArrayList<FoodItem> foodList, Context context) {
        this.foodList = foodList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_food, parent, false);
            holder = new ViewHolder();
            holder.nameTextView = convertView.findViewById(R.id.tv_name);
            holder.foodImageView = convertView.findViewById(R.id.iv_icon);
            holder.foodDateTextView = convertView.findViewById(R.id.tv_food_date);
            holder.foodEnddateTextView = convertView.findViewById(R.id.tv_food_enddate);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        FoodItem foodItem = foodList.get(position);
        holder.nameTextView.setText(foodItem.getName());
        holder.foodImageView.setImageResource(foodItem.getImageResId());
        holder.foodDateTextView.setText("등록일: " + foodItem.getFoodDate());
        holder.foodEnddateTextView.setText("소비기한: " + foodItem.getFoodEnddate());

        return convertView;
    }

    public void updateList(ArrayList<FoodItem> newList) {
        foodList = newList;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        TextView nameTextView;
        ImageView foodImageView;
        TextView foodDateTextView;
        TextView foodEnddateTextView;
    }
}
