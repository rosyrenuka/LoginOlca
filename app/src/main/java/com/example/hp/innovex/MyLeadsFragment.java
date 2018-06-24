package com.example.hp.innovex;


import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyLeadsFragment extends Fragment {


    RecyclerView MyLeadRecyclerView;
   public  MyLeadRecyclerAdapter myLeadRecyclerAdapter;
    ImageView backArrow;
    ImageView filter;
    LinearLayout layout_filterClicked;
    MyLeadRecyclerAdapter.onMyLeadClick onMyLeadClick;
    PopupMenu popup;
    ArrayList<DataLead> listOriginal=new ArrayList<>();
    ArrayList<DataLead> list = new ArrayList<>();
     String list_show="";

    public MyLeadsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view=  inflater.inflate(R.layout.fragment_my_leads, container, false);
       //data=new DataLead();

       MyLeadRecyclerView = view.findViewById(R.id.leadRecyclerview);
       backArrow=view.findViewById(R.id.back_arrow);
       layout_filterClicked=view.findViewById(R.id.layout_filterClicked);
       filter=view.findViewById(R.id.filter);

        // for dummy arguments

        for(int i=0;i<10;i++){

          DataLead d = new DataLead(true);
          String detail = d.getDetail();
          String qsn=d.getQsn();
          String video=d.getVideo();
          String upload=d.getUploadPhoto();
          if(i==3||i==4||i==8||i==6||i==9||i==0){
            d.setEnable(false);
              listOriginal.add(d);
              list.add(d);
          }
          else{
              d.setEnable(true);
              listOriginal.add(d);
              list.add(d);
          }
        }
           list_show="LOAD";

    myLeadRecyclerAdapter = new MyLeadRecyclerAdapter(new MyLeadRecyclerAdapter.onMyLeadClick() {
          @Override
           public void onLeadClick(int position) {

              DataLead data=new DataLead(true);
              data=list.get(position);
               Intent intent =new Intent(getContext(),LeadDetail.class);
             intent.putExtra("object",data);
              startActivity(intent);

           }
       },getContext(), list,list_show);

        MyLeadRecyclerView .setItemAnimator(new DefaultItemAnimator());
        MyLeadRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        MyLeadRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        MyLeadRecyclerView.setAdapter(myLeadRecyclerAdapter);
        myLeadRecyclerAdapter.notifyDataSetChanged();



        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Creating the instance of PopupMenu
                 popup = new PopupMenu(getContext(),filter);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.popup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        switch(item.getItemId()) {

                            case R.id.one:
                            {
                                list.clear();
                                list_show="OPEN";
                                for(int i=0;i<listOriginal.size();i++){
                                    if(listOriginal.get(i).isEnable()){
                                        list.add(listOriginal.get(i));
                                   }
                                }
                                myLeadRecyclerAdapter.notifyDataSetChanged();
                               return true;
                            }


                            case R.id.two: {

                                list.clear();
                                list_show="CLOSED";
                                for(int i=0;i<listOriginal.size();i++){
                                    if(!listOriginal.get(i).isEnable()){
                                        list.add(listOriginal.get(i));
                                    }
                                }
                                myLeadRecyclerAdapter.notifyDataSetChanged();
                                return true;
                            }

                            case R.id.three: {
                                Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                            }
                                return true;
                        }
                        return true;

                    }
                });
                popup.show(); //showing popup menu
            }
        });

       return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
}
