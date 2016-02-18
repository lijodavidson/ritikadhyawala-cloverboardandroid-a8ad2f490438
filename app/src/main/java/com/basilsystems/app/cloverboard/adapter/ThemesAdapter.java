package com.basilsystems.app.cloverboard.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.basilsystems.app.cloverboard.activities.EditThemeActivity;
import com.demo.cloverboard.cloverboardlibrary.data.Theme;
import com.basilsystems.app.asdasdasdasdas.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LIJO on 1/28/2016.
 */
public class ThemesAdapter extends ArrayAdapter<Theme> {
    Context context;
    List<Theme> themeData = new ArrayList<>();
    private int themePos;

    public ThemesAdapter(Context context, List<Theme> data) {
        super(context, R.layout.raw_grid,data);
        this.context = context;
        this.themeData = data;
    }
    //overriding getView to set ThemesAdapter
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final LinearLayout themeHolder;

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            themeHolder = (LinearLayout) inflater.inflate( R.layout.raw_grid, parent, false);
        } else {
            themeHolder = (LinearLayout) convertView;
        }
        TextView textView = (TextView) themeHolder.findViewById(R.id.themeText);
        ImageView imageView = (ImageView) themeHolder.findViewById(R.id.img_main);
       /* LinearLayout linearLayout = (LinearLayout) themeHolder.findViewById(R.id.theme_layout);*/
        ImageButton imageButton = (ImageButton) themeHolder.findViewById(R.id.editImageButton);
        textView.setTag(position);


        //set onclick action
        if(themePos == position){
            imageView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.image_click));
            textView.setBackgroundColor(Color.GREEN);
        }else {
            textView.setBackgroundColor(Color.GRAY);
        }
        //set onclick for MainGridView images
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themePos = position;
                Log.i("lok", "pos" + themePos);
                notifyDataSetChanged();
            }
        });
        //set onclick for MainGridView TextView
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themePos = position;
                Log.i("lok", "pos" + themePos);
                notifyDataSetChanged();
            }
        });
        //set onclick for MainGridView ImageButton
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("lok", "image button click" );
                Intent intent = new Intent(getContext(), EditThemeActivity.class);
                v.getContext().startActivity(intent);
            }
        });
        Theme item = themeData.get(position);
        textView.setText(item.getThemeName());
        imageView.setImageResource(item.getThemeImageResource());
     /*   themeHolder.setBackgroundResource(item.getImage());*/
        return themeHolder;
    }
}
