package com.example.hp.innovex;


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
public class HomeFragment extends Fragment {


    ArrayList<String> detail=new ArrayList<>();
    RecyclerView recyclerView;
    HomeListAdapter homeListAdapter;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       View view = inflater.inflate(R.layout.fragment_home, container, false);
       recyclerView=view.findViewById(R.id.recyclerview);
        detail.add("General Electrical work light fitting, celing, fan, installation, MCB repair, TV Installation");




        homeListAdapter=new HomeListAdapter(getContext(), detail, new HomeListAdapter.OnItemClicked() {
           @Override
           public void HomeListItemClicked(int position) {
               Toast.makeText(getContext(), "inside fragment item click called", Toast.LENGTH_SHORT).show();
           }
       });

        recyclerView .setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext() , DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(homeListAdapter);
        homeListAdapter.notifyDataSetChanged();

       return view;
    }

}
