package com.example.hp.innovex;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyLeadsFragment extends Fragment {


    RecyclerView MyLeadRecyclerView;
    ArrayList<String> detail = new ArrayList<>();
    MyLeadRecyclerAdapter myLeadRecyclerAdapter;

    public MyLeadsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View view=  inflater.inflate(R.layout.fragment_my_leads, container, false);


       MyLeadRecyclerView = view.findViewById(R.id.leadRecyclerview);
        detail.add("General Electrical work light fitting, celing, fan, installation, MCB repair, TV Installation");
       myLeadRecyclerAdapter = new MyLeadRecyclerAdapter(new MyLeadRecyclerAdapter.onMyLeadClick() {
           @Override
           public void onLeadClick(int position) {
               Intent intent =new Intent(getContext(),LeadDetail.class);
               intent.putStringArrayListExtra("detail",detail);
               Toast.makeText(getContext(), "inside my lead clicked", Toast.LENGTH_SHORT).show();
               startActivity(intent);

           }
       },getContext(), detail);

        MyLeadRecyclerView .setItemAnimator(new DefaultItemAnimator());
        MyLeadRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        MyLeadRecyclerView.addItemDecoration(new DividerItemDecoration(getContext() , DividerItemDecoration.VERTICAL));
        MyLeadRecyclerView.setAdapter(myLeadRecyclerAdapter);
        myLeadRecyclerAdapter.notifyDataSetChanged();

       return view;
    }

}
