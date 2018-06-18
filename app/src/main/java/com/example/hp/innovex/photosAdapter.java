package com.example.hp.innovex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by HP on 17-06-2018.
 */

public class photosAdapter extends RecyclerView.Adapter<photosAdapter.ViewHolder> {

    Context context;

    int images[]={R.drawable.electricity1,R.drawable.electricity2,R.drawable.electricity3,R.drawable.electricity4,R.drawable.electricity1,R.drawable.electricity6};

    public photosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row= inflater.inflate(R.layout.photo_recycler_single,null);

       ViewHolder holder = new ViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(position<7)
       holder.image.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view=itemView;
            this.image=view.findViewById(R.id.image);
        }
    }
}
