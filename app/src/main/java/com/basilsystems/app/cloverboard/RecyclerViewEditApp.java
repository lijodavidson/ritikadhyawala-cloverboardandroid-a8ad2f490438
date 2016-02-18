package com.basilsystems.app.cloverboard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.basilsystems.app.asdasdasdasdas.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by LIJO on 1/25/2016.
 */
public class RecyclerViewEditApp extends RecyclerView.Adapter<RecyclerViewEditApp.RadioViewHolder> {

    private Context context;
    private String[] state1 = { "Dimmable", "Type 1", "Type 2"};
    private String[] state2 = { "Own", "ThemeActivity", "Appliance"};
    Context thiscontext;
private Spinner spinn1,spinn2;
    private View view;
    private TextView text1,text2;
    private List<Radio> radioList;

    public RecyclerViewEditApp(Context context) {
        this.context = context;
        radioList = new ArrayList<>();
    }

    public void setRadioList(List<Radio> radioList){
        this.radioList = radioList;
        notifyDataSetChanged();
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        thiscontext = container.getContext();
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.recycler_edit_app_iteam, container, false);
        return view;
    }



    @Override
    public RadioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_edit_app_iteam, parent, false);
        RadioViewHolder viewHolder = new RadioViewHolder(view);
        spinn1= (Spinner) view.findViewById(R.id.spinner1);
        spinn2=(Spinner)view.findViewById(R.id.spinner2);
        text1=(TextView)view.findViewById(R.id.textView);
        text2=(TextView)view.findViewById(R.id.textView2);
      /*  ArrayAdapter<String> adapter_state1 = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, state1);
        adapter_state1
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinn1.setAdapter(adapter_state1);*/









        setupspinner();
        return viewHolder;




    }

    private void setupspinner() {

        final ArrayAdapter<String> ada;
        ada = new ArrayAdapter<String>(
                context, android.R.layout.simple_spinner_dropdown_item,state1);
        spinn1.setAdapter(ada);
        spinn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {


                ada.notifyDataSetChanged();
                String item = adapterView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        ArrayAdapter<String> adapter_state2 = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, state2);
        adapter_state2
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinn2.setAdapter(adapter_state2);
        spinn2.setSelection(0);
        spinn2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {

                switch (position) {
                    case 0:
                        // Your code when first option seletced
                        break;
                    case 1:
                        // Your code when 2nd  option seletced
                        AlertDialog levelDialog;

                        // Strings to Show In Dialog with Radio Buttons
                        final CharSequence[] items = {" Morning ", " Afternoon ", " Evening ", " Night "};

                        // Creating and Building the Dialog
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                        builder.setTitle("Choose ThemeActivity");
                        text1.setText("ThemeActivity");
                        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {


                                switch (item) {
                                    case 0:
                                        // Your code when first option seletced
                                        text2.setText("Morning");


                                        break;
                                    case 1:
                                        // Your code when 2nd  option seletced

                                        break;
                                    case 2:
                                        // Your code when 3rd option seletced
                                        break;
                                    case 3:
                                        // Your code when 4th  option seletced
                                        break;

                                }
                                       /* levelDialog.dismiss();*/
                            }
                        });
                        builder
                                .setCancelable(false)
                                .setPositiveButton("CHOOSE",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                                switch (position) {
                                                    case 0:
                                                        // Your code when first option seletced
                                                        text2.setText("Morning");


                                                        break;
                                                    case 1:
                                                        // Your code when 2nd  option seletced

                                                        break;
                                                    case 2:
                                                        // Your code when 3rd option seletced
                                                        break;
                                                    case 3:
                                                        // Your code when 4th  option seletced
                                                        break;

                                                }


                                            }

                                        })
                                .setNegativeButton("CANCEL",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });
                        levelDialog = builder.create();
                        levelDialog.show();
                        break;
                    case 2:
                        // Your code when 3rd option seletced
                        AlertDialog levelDialog2;

                        // Strings to Show In Dialog with Radio Buttons
                        final CharSequence[] items2 = {" Kitchen Lights ", " Hall Fan ", " Hall Lights ", " Bedroom "};

                        // Creating and Building the Dialog
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(view.getRootView().getContext());
                        builder2.setTitle("Choose Appliance");
                        builder2.setSingleChoiceItems(items2, -1, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item2) {


                                switch (item2) {
                                    case 0:
                                        // Your code when first option seletced

                                        break;
                                    case 1:
                                        // Your code when 2nd  option seletced

                                        break;
                                    case 2:
                                        // Your code when 3rd option seletced
                                        break;
                                    case 3:
                                        // Your code when 4th  option seletced
                                        break;

                                }
                                       /* levelDialog.dismiss();*/
                            }
                        });
                        builder2
                                .setCancelable(false)
                                .setPositiveButton("CHOOSE",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                            }

                                        })
                                .setNegativeButton("CANCEL",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });
                        levelDialog2 = builder2.create();
                        levelDialog2.show();
                        break;

                }




            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });









    }


    @Override
    public void onBindViewHolder(RadioViewHolder holder, final int position) {

        Radio radio = radioList.get(position);







       /* holder.textViewRadioName.setText(radio.getRadioName());*/
        /*holder.textViewRadioDial.setText("(" + radio.getRadioDial() + ")");*/
       /* Picasso.with(context).load(radio.getRadioArt()).into(holder.imageViewRadioLogo);*/
      /*  holder.textViewRadioTags.setText("#rock #pop #news");*/
    }

    @Override
    public int getItemCount() {
        return radioList.size();
    }

    public class RadioViewHolder extends RecyclerView.ViewHolder{

      /*  @Bind(R.id.textview_radio_name)
        TextView textViewRadioName;*/

       /* @Bind(R.id.textview_radio_dial)
        TextView textViewRadioDial;

        @Bind(R.id.textview_tags)
        TextView textViewRadioTags;*/

         @Bind(R.id.spinner1)
         Spinner spinn1;


        @Bind(R.id.spinner2)
        Spinner spinn2;

        /*@Bind(R.id.imageview_radio_logo)
        ImageView imageViewRadioLogo;*/



        public RadioViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}
