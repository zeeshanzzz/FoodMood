package com.example.khan.foodmood;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.khan.foodmood.Adapter.MainActivity_Adapter;
import com.example.khan.foodmood.Adapter.MyHolder;
import com.example.khan.foodmood.Model.Items;
import com.example.khan.foodmood.ViewModel.MyViewModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
   // BlankFragment blankFragment;
    private RecyclerView recyclerView;
    private MyViewModel myViewModel;
    private MainActivity_Adapter mainActivity_adapter;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
     FirebaseRecyclerAdapter<Items, MyHolder> recyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView=findViewById(R.id.Main_Recycler);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
     get();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



    }
   public void get() {
       databaseReference = FirebaseDatabase.getInstance().getReference().child("Items");
        recyclerAdapter = new FirebaseRecyclerAdapter<Items, MyHolder>(Items.class, R.layout.main1_layout, MyHolder.class, databaseReference) {
           @Override
           protected void populateViewHolder(MyHolder viewHolder, Items model, int position) {
               Glide.with(getBaseContext()).load(model.getUrl()).into(viewHolder.imageView);
              viewHolder.textView.setText(model.getName());
              final Items item=model;
              viewHolder.SetItemClick(new ItemClickListner() {
                  @Override
                  public void Onclick(View view, int position, boolean isLongClick) {
                      Toast.makeText(Main2Activity.this, item.getName(), Toast.LENGTH_LONG).show();
                      Intent intent=new Intent(Main2Activity.this,Food.class);
                      intent.putExtra("FoodId",recyclerAdapter.getRef(position).getKey());
                     startActivity(intent);
                  }
              });


           }


       };
       recyclerView.setAdapter(recyclerAdapter);
       recyclerAdapter.notifyDataSetChanged();
   }

  /*  public void Init(){

        myViewModel= ViewModelProviders.of(this).get(MyViewModel.class);
        myViewModel.Init();
        try {
            Thread.sleep(3000);
            myViewModel.Getdata().observe(this, new Observer<List<Items>>() {
                @Override
                public void onChanged(@Nullable List<Items> items) {
                    mainActivity_adapter=new MainActivity_Adapter(Main2Activity.this,items);
                    recyclerView.setAdapter(mainActivity_adapter);
                    mainActivity_adapter.notifyDataSetChanged();

                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
    */

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

