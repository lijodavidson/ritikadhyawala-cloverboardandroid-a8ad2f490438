package com.basilsystems.app.cloverboard.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.basilsystems.app.asdasdasdasdas.R;

import java.util.ArrayList;

/**
 * Created by LIJO on 1/28/2016.
 */
public class DialogImagesAdapter extends ArrayAdapter<Integer> {
    Context context;
    ArrayList<Integer> dialogData = new ArrayList<>();
    private int dialogImgPos;

    public DialogImagesAdapter(Context context, ArrayList<Integer> data) {
        super(context,android.R.layout.simple_list_item_2);
        this.context = context;
        this.dialogData = data;
    }
    public int getCount() {
        return 6;
    }
    //overriding getView to set DialogImagesAdapter
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.dialog_img, parent, false);
        }
        int item = dialogData.get(position);
        ImageView imageDialog = (ImageView)convertView.findViewById(R.id.dialogImg);
        View imageView = (View)convertView.findViewById(R.id.dialogView);
        imageDialog.setImageResource(item);
        imageDialog.setTag(position);
        //set actions for DialogGridView images
        if(dialogImgPos == position) {
            imageDialog.startAnimation(AnimationUtils.loadAnimation(context, R.anim.image_click));
            imageView.setSelected(true);
        }
        else
        {
            imageView.setSelected(false);
        }
        //set onclick for DialogImagesAdapter images
        imageDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogImgPos = position;
                Log.i("lok", "pos" + dialogImgPos);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}

