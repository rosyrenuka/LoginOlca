package com.example.hp.innovex;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HP on 17-06-2018.
 */

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.UserViewHolder> {


   public interface OnItemClicked{

       void HomeListItemClicked(int position);
    }
    OnItemClicked listener;

    Context context;
    ArrayList<String> details;


    public HomeListAdapter(Context context, ArrayList<String> details , OnItemClicked listener) {
        this.context = context;
        this.details = details;
        this.listener=listener;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row= inflater.inflate(R.layout.home_list_single_layout,null);

        UserViewHolder holder=new UserViewHolder(row);
        return holder;

    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {

        holder.details.setText(details.get(0));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.HomeListItemClicked(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 15;                    // change later
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        View view;
        TextView QSN;
        TextView details;
        TextView videos;
        TextView date;
        ImageView image_video;
        ImageView image_date;

        public UserViewHolder(View itemView) {
            super(itemView);

           this.view=itemView;
           this.QSN=view.findViewById(R.id.qsn);
           this.date= view.findViewById(R.id.datetime_details);
           this.details=view.findViewById(R.id.detail);
           this.image_date=view.findViewById(R.id.datetime);
           this.videos=view.findViewById(R.id.attach_detail);
           this.image_video=view.findViewById(R.id.attach);
        }
    }
}
