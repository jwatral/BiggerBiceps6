package com.example.jwatral.biggerbiceps6.greendaoexample.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jwatral.biggerbiceps6.R;

import java.util.List;

import greendao.ExerciseSet;

/**
 * Created by surecase on 19/03/14.
 */
public class DbItemsAdapter extends ArrayAdapter<ExerciseSet> {

    private LayoutInflater inflater;
    private Context context;

    public DbItemsAdapter(Context context) {
        super(context, 0);
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void updateData(List<ExerciseSet> exerciseSets) {
        this.clear();
        for (ExerciseSet exerciseSet : exerciseSets) {
            add(exerciseSet);
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_box, null);
            viewHolder = new ViewHolder();
            viewHolder.root = (LinearLayout) convertView.findViewById(R.id.boxItem);
            viewHolder.tvId = (TextView) convertView.findViewById(R.id.tvItemId);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvSize = (TextView) convertView.findViewById(R.id.tvSize);
            viewHolder.tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        editBackground(position, viewHolder);
        fillViewWithData(position, viewHolder);

        return convertView;
    }

    private void editBackground(int position, ViewHolder viewHolder) {
        if (position % 2 == 0) {
            viewHolder.root.setBackgroundColor(context.getResources().getColor(R.color.white));
        } else {
            viewHolder.root.setBackgroundColor(context.getResources().getColor(R.color.lightGray));
        }
    }

    private void fillViewWithData(int position, ViewHolder viewHolder) {
        viewHolder.tvId.setText(context.getString(R.string.tv_label_item_id) + " " + getItem(position).getId().toString());
        viewHolder.tvName.setText(context.getString(R.string.tv_label_exercise_name) + " " + getItem(position).getExercise());
        viewHolder.tvSize.setText(context.getString(R.string.tv_label_reps) + " " + getItem(position).getReps());
        viewHolder.tvDescription.setText(context.getString(R.string.tv_label_musle) + " " + getItem(position).getMuscle());
    }

    static class ViewHolder {
        LinearLayout root;
        TextView tvId;
        TextView tvName;
        TextView tvSize;
        TextView tvDescription;
    }
}
