package com.example.hp.innovex;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by HP on 17-06-2018.
 */

public class MyLeadRecyclerAdapter extends RecyclerView.Adapter<MyLeadRecyclerAdapter.UserHolder> {


    public interface onMyLeadClick{

        void onLeadClick(int position);
    }

    onMyLeadClick leadListener;
    Context context;
   ArrayList<DataLead> leads;
   String list_show;

    public MyLeadRecyclerAdapter(onMyLeadClick leadListener, Context context, ArrayList<DataLead> leads,String list_show) {
        this.leadListener = leadListener;
        this.context = context;
        this.leads = leads;
        this.list_show = list_show;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row= inflater.inflate(R.layout.mylead_single_layout,null);

     UserHolder holder=new UserHolder(row);
     return holder;

    }

    @Override
    public void onBindViewHolder(final UserHolder holder, final int position) {


        if(list_show.equals("OPEN")){
           // holder.linearLayout.setVisibility(View.INVISIBLE);

            Toast.makeText(context, "list modified according to open", Toast.LENGTH_SHORT).show();
            DataLead data=leads.get(position);
                holder.qsn.setText(data.getQsn());
                holder.details.setText(data.getDetail());
                holder.video_details.setText(data.getVideo());
                holder.datetime_details.setText(data.getUploadPhoto());
        }

        else if(list_show.equals("CLOSED")){
           // holder.linearLayout.setVisibility(View.VISIBLE);
            Toast.makeText(context, "list modified according to open", Toast.LENGTH_SHORT).show();
            DataLead data=leads.get(position);
            holder.qsn.setText(data.getQsn());
            holder.details.setText(data.getDetail());
            holder.video_details.setText(data.getVideo());
            holder.datetime_details.setText(data.getUploadPhoto());
        }

       else  if(list_show.equals("LOAD")){

           // Toast.makeText(context, "list modified according to LOAD", Toast.LENGTH_SHORT).show();

            DataLead data=leads.get(position);
            holder.qsn.setText(data.getQsn());
            holder.details.setText(data.getDetail());
            holder.video_details.setText(data.getVideo());
            holder.datetime_details.setText(data.getUploadPhoto());


            if(!data.isEnable()){
                holder.linearLayout.setVisibility(View.VISIBLE);
                holder.datetime_details.setTextColor(Color.LTGRAY);
                holder.video_details.setTextColor(Color.LTGRAY);
                holder.details.setTextColor(Color.LTGRAY);
                holder.qsn.setTextColor(Color.LTGRAY);
                holder.image_date.setColorFilter(Color.LTGRAY);
                holder.image_video.setColorFilter(Color.LTGRAY);
              }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                leadListener.onLeadClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return leads.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {

        View view;
        TextView qsn;
        TextView details;
        TextView video_details;
        TextView datetime_details;
        ImageView image_video;
        ImageView image_date;
        TextView closeText;
        LinearLayout linearLayout;
        RelativeLayout relativeLayout;
        String enabled;


        public UserHolder(View itemView) {
            super(itemView);

            this.view=itemView;

            this.closeText=view.findViewById(R.id.closed);
            this.qsn=view.findViewById(R.id.qsn);
            this.datetime_details= view.findViewById(R.id.datetime_details);
            this.details=view.findViewById(R.id.detail);
            this.image_date=view.findViewById(R.id.datetime);
            this.video_details=view.findViewById(R.id.attach_detail);
            this.image_video=view.findViewById(R.id.attach);
            this.linearLayout=view.findViewById(R.id.close_layout);
            this.relativeLayout=view.findViewById(R.id.relative_layout);

        }
    }
}
