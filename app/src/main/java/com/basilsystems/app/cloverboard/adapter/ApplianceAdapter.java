package com.basilsystems.app.cloverboard.adapter;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;

import com.basilsystems.app.cloverboard.ApplianceScheduler;
import com.basilsystems.app.cloverboard.util.OnApplianceStatusChangeListener;
import com.basilsystems.app.cloverboard.activities.EditApplianceActivity;
import com.demo.cloverboard.cloverboardlibrary.data.Appliance;
import com.basilsystems.app.asdasdasdasdas.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by LIJO on 1/20/2016.
 */
public class ApplianceAdapter extends RecyclerView.Adapter<ApplianceAdapter.ViewHolder> {

    public List<Appliance> applianceList;
    private boolean isSwitchedOn = false;
    private boolean isAuto = false;
    OnApplianceStatusChangeListener onApplianceStatusChangeListener;

    public ApplianceAdapter( OnApplianceStatusChangeListener onApplianceStatusChangeListener) {

        this.onApplianceStatusChangeListener = onApplianceStatusChangeListener;
    }

    public void setData(List<Appliance> data){
        this.applianceList = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView applianceName;
        Button applianceSwitch;
        ImageButton overflowButtonImage;
        SeekBar applianceSeekbar;

        public ViewHolder(View itemView) {
            super(itemView);
          /*  this.title = (TextView)itemView.findViewById(R.id.seekBar);*/
            this.applianceName = (TextView) itemView.findViewById(R.id.card_content);
            this.applianceSwitch = (Button) itemView.findViewById(R.id.powerbutton);
            this.overflowButtonImage = (ImageButton) itemView.findViewById(R.id.morevert);
            this.applianceSwitch.setBackgroundResource(R.drawable.off);
            this.applianceSeekbar = (SeekBar) itemView.findViewById(R.id.seekBar);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
     /*   holder.title.setText(applianceList.get(position).title);*/

        final Appliance appliance = applianceList.get(position);
        if(appliance.roleAssigned == Appliance.Role.THEME){
            holder.applianceName.setText(appliance.getTheme_id() + " Theme");
        }else if(appliance.roleAssigned == Appliance.Role.OTHER_APPLIANCE){
            holder.applianceName.setText(appliance.getOther_appliance_id());
        }else {
            holder.applianceName.setText(appliance.getApplianceName());
        }
        if (isSwitchedOn) {
            if (isAuto) {
                holder.applianceSwitch.setBackgroundResource(R.drawable.auto_on);
            } else {
                holder.applianceSwitch.setBackgroundResource(R.drawable.on);
            }
        } else {
            if (isAuto) {
                holder.applianceSwitch.setBackgroundResource(R.drawable.auto_off);
            } else {
                holder.applianceSwitch.setBackgroundResource(R.drawable.off);
            }
        }


        holder.applianceSwitch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (isSwitchedOn) {
                    isSwitchedOn = false;
                } else {
                    isSwitchedOn = true;
                }
                JSONObject switchDetails = new JSONObject();

                try {
                    switchDetails.put("s", position);
                    switchDetails.put("tt", "sp");

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                onApplianceStatusChangeListener.onApplianceStatusChange(switchDetails);
            }
        });

        holder.applianceSwitch.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                if (isAuto) {
                    isAuto = false;
                } else {
                    isAuto = true;
                }
                JSONObject switchDetails = new JSONObject();

                try {
                    switchDetails.put("s", position);
                    switchDetails.put("tt", "lp");

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                onApplianceStatusChangeListener.onApplianceStatusChange(switchDetails);

                return true;
            }
        });

        if(appliance.roleAssigned == Appliance.Role.THEME){
            holder.applianceSeekbar.setVisibility(View.INVISIBLE);
        }else if(appliance.applianceType == Appliance.ApplianceType.LIGHT_SOURCE_DIMMABLE || appliance.applianceType == Appliance.ApplianceType.NON_LIGHT_SOURCE_DIMMABLE){
            holder.applianceSeekbar.setVisibility(View.VISIBLE);
        }else{
            holder.applianceSeekbar.setVisibility(View.INVISIBLE);
        }

        holder.applianceSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                JSONObject switchDetails = new JSONObject();
                try {
                    switchDetails.put(
                            "s",
                            position);
                    switchDetails.put("tt", "lt");
                    switchDetails.put("l", (int) (i / 6.5));

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                onApplianceStatusChangeListener.onApplianceStatusChange(switchDetails);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        holder.overflowButtonImage.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(final View view) {

                PopupMenu popup = new PopupMenu(view.getContext(), view);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {
                            case R.id.edit_appliance:
                                Intent i = new Intent(view.getRootView().getContext(), EditApplianceActivity.class);
                                i.putExtra("appliance_id", appliance.getApplianceName());
                                i.putExtra("selected_room", appliance.getParent_id());
                                i.putExtra("appliance_position", position);
                                view.getRootView().getContext().startActivity(i);
                                return true;


                            case R.id.schedule_appliance:
                                Intent z = new Intent(view.getRootView().getContext(), ApplianceScheduler.class);
                                view.getRootView().getContext().startActivity(z);
                                return true;


                        }
                        return false;
                    }
                });
                popup.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return applianceList.size();
    }
}