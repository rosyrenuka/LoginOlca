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
import android.util.Log;
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
   public  MyLeadsAdapter myLeadRecyclerAdapter;

    ImageView backArrow;
    ImageView filter;
    LinearLayout layout_filterClicked;
    PopupMenu popup;
    ArrayList<DataLead> listOriginal=new ArrayList<>();
    ArrayList<DataLead> list = new ArrayList<>();
    MyLeadsAdapter.MyLeadItemClicked listener;
    ImageView backArrow1;
    LinearLayout my_lead_layout;

    public static int state=0;

    public MyLeadsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view=  inflater.inflate(R.layout.fragment_my_leads, container, false);
       MyLeadRecyclerView = view.findViewById(R.id.leadRecyclerview);

       my_lead_layout=view.findViewById(R.id.my_lead_layout);

        MyLeadRecyclerView .setItemAnimator(new DefaultItemAnimator());
        MyLeadRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        MyLeadRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

       backArrow=view.findViewById(R.id.back_arrow);
       backArrow1=view.findViewById(R.id.back_arrow1);

       layout_filterClicked=view.findViewById(R.id.layout_filterClicked);
       filter=view.findViewById(R.id.filter);
        listOriginal.add(new DataLead(true));
        listOriginal.add(new DataLead(true));
        listOriginal.add(new DataLead(false));
        listOriginal.add(new DataLead(true));
        listOriginal.add(new DataLead(true));
        listOriginal.add(new DataLead(false));
        listOriginal.add(new DataLead(true));
        listOriginal.add(new DataLead(false));
        listOriginal.add(new DataLead(true));
        listOriginal.add(new DataLead(true));
        listOriginal.add(new DataLead(false));
        list.clear();
        for(int i=0;i<listOriginal.size();i++)
        {
            list.add(listOriginal.get(i));
        }
        myLeadRecyclerAdapter=new MyLeadsAdapter(list, getContext(), new MyLeadsAdapter.MyLeadItemClicked() {
            @Override
            public void LeadItemClick(int position) {
                Intent intent = new Intent(getContext(),LeadDetail.class);
                intent.putExtra("MyClass", list.get(position));
                startActivity(intent);
                //  Toast.makeText(getContext(), "inside FRAGMENT clicked", Toast.LENGTH_SHORT).show();
            }
        });
        MyLeadRecyclerView.setAdapter(myLeadRecyclerAdapter);
        myLeadRecyclerAdapter.notifyDataSetChanged();
        Log.e("TAG", "onCreateView: "+list.size() );
        // for dummy arguments

          // list_show="LOAD";

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        backArrow1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                list.clear();
                state=4;
                for(int i=0;i<listOriginal.size();i++)
                {
                    list.add(listOriginal.get(i));
                }

                myLeadRecyclerAdapter.notifyDataSetChanged();
                layout_filterClicked.setVisibility(View.GONE);
                my_lead_layout.setVisibility(View.VISIBLE);


//                getActivity().getSupportFragmentManager().popBackStack();
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

                        my_lead_layout.setVisibility(View.GONE);
                        layout_filterClicked.setVisibility(View.VISIBLE);

                        switch(item.getItemId()) {

                            case R.id.one:
                            {
                                list.clear();

                                for(int i=0;i<listOriginal.size();i++)
                                {
                                    if(listOriginal.get(i).isEnable())
                                    {
                                        list.add(listOriginal.get(i));
                                    }
                                }
                                Log.e("TAG", "onMenuItemClick:1 "+list.size() );
                                state=0;
                                myLeadRecyclerAdapter.notifyDataSetChanged();
                               return true;
                            }


                            case R.id.two: {

                                list.clear();
                                for(int i=0;i<listOriginal.size();i++)
                                {
                                    if(!listOriginal.get(i).isEnable())
                                    {
                                        list.add(listOriginal.get(i));
                                    }
                                }
                                Log.e("TAG", "onMenuItemClick:2 "+list.size() );
                                state=1;
                                myLeadRecyclerAdapter.notifyDataSetChanged();
                                return true;
                            }

                            case R.id.three: {
                                list.clear();
                                for(int i=0;i<listOriginal.size();i++)
                                {
                                    list.add(listOriginal.get(i));
                                }
                                Log.e("TAG", "onMenuItemClick:3 "+list.size() );
                                state =2;
                                myLeadRecyclerAdapter.notifyDataSetChanged();
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
