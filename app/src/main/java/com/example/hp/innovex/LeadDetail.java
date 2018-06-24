package com.example.hp.innovex;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class LeadDetail extends AppCompatActivity {

    RecyclerView photosRecyclerView;
    TextView detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        DataLead dene = (DataLead) intent.getSerializableExtra("object");


        setContentView(R.layout.activity_lead_detail);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setTitle(dene.getQsn()+"");


        Drawable dr = getResources().getDrawable(R.drawable.ic_backarrow);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        Drawable d = new BitmapDrawable(getResources(),
                Bitmap.createScaledBitmap(bitmap, 60, 40, true));

        //myToolbar.setLogo(d);

       myToolbar.setNavigationIcon(d);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);


        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                finish();
               // Toast.makeText(LeadDetail.this, "clicked clicked clikced", Toast.LENGTH_SHORT).show();
            }
        });



        detail=findViewById(R.id.detail);
        photosRecyclerView=findViewById(R.id.photoRecyclerview);
   detail.setText("General Electrical work light fitting, celing, fan, installation, MCB repair, TV Installation");
        photosAdapter adapter = new photosAdapter(this);

        photosRecyclerView .setItemAnimator(new DefaultItemAnimator());
        photosRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
       // photosRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        photosRecyclerView.addItemDecoration(new DividerItemDecoration(this , DividerItemDecoration.VERTICAL));
        photosRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
