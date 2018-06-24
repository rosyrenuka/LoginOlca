package com.example.hp.innovex;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by HP on 24-06-2018.
 */

public class MyLeadsAdapter extends RecyclerView.Adapter<MyLeadsAdapter.ViewHolder>{


    public interface MyLeadItemClicked{

        void LeadItemClick(int position);
    }

    ArrayList<DataLead> leadDetailArrayList;
   Context c;
   MyLeadItemClicked listener;



    public MyLeadsAdapter(ArrayList<DataLead> leadDetailArrayList, Context c,MyLeadItemClicked listener) {
        this.leadDetailArrayList = leadDetailArrayList;
        this.c = c;
        this.listener=listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater l= LayoutInflater.from(c);
        View v=l.inflate(R.layout.mylead_single_layout,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        DataLead leadDetail=leadDetailArrayList.get(position);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (leadDetailArrayList.get(position).isEnable())
                    listener.LeadItemClick(viewHolder.getAdapterPosition());

                else {
                    Toast.makeText(c, "this item is closed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if(MyLeadsFragment.state==0){

            if(!leadDetail.isEnable()){
                viewHolder.closedLayout.setVisibility(View.VISIBLE);
                viewHolder.qsn.setTextColor(Color.LTGRAY);
                viewHolder.details.setTextColor(Color.LTGRAY);
                viewHolder.upload_date.setTextColor(Color.LTGRAY);
                viewHolder.video_detail.setTextColor(Color.LTGRAY);
                viewHolder.upload.setColorFilter(Color.LTGRAY);
                viewHolder.video.setColorFilter(Color.LTGRAY);
            }
            else{

                viewHolder.closedLayout.setVisibility(View.GONE);
                viewHolder.qsn.setTextColor(Color.GRAY);
                viewHolder.details.setTextColor(Color.GRAY);
                viewHolder.upload_date.setTextColor(Color.GRAY);
                viewHolder.video_detail.setTextColor(Color.GRAY);
                viewHolder.upload.setColorFilter(Color.GRAY);
                viewHolder.video.setColorFilter(Color.GRAY);
            }
        }
        else if(MyLeadsFragment.state==1){

            viewHolder.closedLayout.setVisibility(View.GONE);

            viewHolder.qsn.setTextColor(Color.GRAY);
            viewHolder.details.setTextColor(Color.GRAY);
            viewHolder.upload_date.setTextColor(Color.GRAY);
            viewHolder.video_detail.setTextColor(Color.GRAY);
            viewHolder.upload.setColorFilter(Color.GRAY);
            viewHolder.video.setColorFilter(Color.GRAY);


        }
        else if(MyLeadsFragment.state==2){

            viewHolder.closedLayout.setVisibility(View.GONE);

            viewHolder.qsn.setTextColor(Color.GRAY);
            viewHolder.details.setTextColor(Color.GRAY);
            viewHolder.upload_date.setTextColor(Color.GRAY);
            viewHolder.video_detail.setTextColor(Color.GRAY);
            viewHolder.upload.setColorFilter(Color.GRAY);
            viewHolder.video.setColorFilter(Color.GRAY);
        }


        else if(MyLeadsFragment.state==4){

            if(!leadDetail.isEnable()){
                viewHolder.closedLayout.setVisibility(View.VISIBLE);
                viewHolder.qsn.setTextColor(Color.LTGRAY);
                viewHolder.details.setTextColor(Color.LTGRAY);
                viewHolder.upload_date.setTextColor(Color.LTGRAY);
                viewHolder.video_detail.setTextColor(Color.LTGRAY);
                viewHolder.upload.setColorFilter(Color.LTGRAY);
                viewHolder.video.setColorFilter(Color.LTGRAY);
            }
            else{

                viewHolder.closedLayout.setVisibility(View.GONE);
                viewHolder.qsn.setTextColor(Color.GRAY);
                viewHolder.details.setTextColor(Color.GRAY);
                viewHolder.upload_date.setTextColor(Color.GRAY);
                viewHolder.video_detail.setTextColor(Color.GRAY);
                viewHolder.upload.setColorFilter(Color.GRAY);
                viewHolder.video.setColorFilter(Color.GRAY);
            }


        }

    }

    @Override
    public int getItemCount() {
        return leadDetailArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout closedLayout;
        TextView qsn;
        TextView details;
        TextView upload_date;
        TextView video_detail;
        ImageView video;
        ImageView upload;

        public ViewHolder(View v) {
            super(v);
            closedLayout=(LinearLayout)v.findViewById(R.id.close_layout);
            qsn=v.findViewById(R.id.qsn);
            details=v.findViewById(R.id.detail);
            upload_date=v.findViewById(R.id.datetime_details);
            video_detail=v.findViewById(R.id.attach_detail);
            video=v.findViewById(R.id.attach);
            upload=v.findViewById(R.id.datetime);
        }
    }
}