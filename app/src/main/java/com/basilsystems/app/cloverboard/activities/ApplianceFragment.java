package com.basilsystems.app.cloverboard.activities;

/**
 * Created by LIJO on 11/20/2015.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basilsystems.app.cloverboard.adapter.ApplianceAdapter;
import com.basilsystems.app.cloverboard.util.DemoData;
import com.basilsystems.app.cloverboard.util.OnApplianceStatusChangeListener;
import com.basilsystems.app.asdasdasdasdas.R;
import com.demo.cloverboard.cloverbackendlibrary.DatabaseHandler;


public class ApplianceFragment extends Fragment {


    private RecyclerView applianceListView;
    private ApplianceAdapter applianceAdapter;
    private OnApplianceStatusChangeListener onApplianceStatusChangeListener;
   private String deviceId;

    public ApplianceFragment() {

    }

    public ApplianceFragment(OnApplianceStatusChangeListener onApplianceStatusChangeListener, String deviceId) {

        this.onApplianceStatusChangeListener = onApplianceStatusChangeListener;
        this.deviceId = deviceId;

    }

    public ApplianceAdapter getApplianceAdapter() {
        return applianceAdapter;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_appliance, container, false);

        applianceListView = (RecyclerView) view.findViewById(R.id.recyclerView);

        applianceListView = (RecyclerView) view.findViewById(R.id.recyclerView);
        applianceListView.setHasFixedSize(true);
        applianceListView
                .setLayoutManager(new LinearLayoutManager(getActivity()));
        applianceAdapter = new ApplianceAdapter(onApplianceStatusChangeListener);

        //TODO: get it from the databse handler
//        applianceAdapter.setData(DemoData.getApplianceList(deviceId));
        applianceAdapter.setData(DatabaseHandler.getInstance(getContext()).getAllAppliancesGivenDevice(deviceId));
        applianceListView.setAdapter(applianceAdapter);


        return view;
    }


}