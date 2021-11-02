package com.vsb.kru13.sokoban;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LevelAdapter extends ArrayAdapter<Level> {

    private Context mContext;
    private int mResource;

    public LevelAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Level> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);

        ImageView imageView = convertView.findViewById(R.id.image);

        TextView levelName = convertView.findViewById(R.id.levelName);

        TextView highestScore = convertView.findViewById(R.id.highestScore);

        imageView.setImageResource(getItem(position).getImage());

        levelName.setText(getItem(position).getName());

        highestScore.setText(getItem(position).getHighestScoreString());

        return convertView;
    }
}
