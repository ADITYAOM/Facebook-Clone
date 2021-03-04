package com.aditya.facebookui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.aditya.facebookui.Adapters.PageAdapter;
import com.aditya.facebookui.Adapters.RecyclerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener fireAuthListener;

    private androidx.appcompat.widget.Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        toolbar =(androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        viewPager = findViewById(R.id.viewpager);
        PageAdapter adapter = new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        firebaseAuth = FirebaseAuth.getInstance();

        //get current user
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        fireAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user1 = firebaseAuth.getCurrentUser();

                if (user1 == null) {
                    //user not login
                    Home.this.startActivity(new Intent(Home.this, MainActivity.class));
                   Home.this.finish();
                }
            }
        };

//        List<String> list = new ArrayList<String>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("4");
//        list.add("5");
//        list.add("6");
//        list.add("7");
//        list.add("8");
//        list.add("9");


//        //Setting Layout Manager to the Recycler View
//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(Home.this);
//        mRecyclerView.setLayoutManager(layoutManager);
//
//        // Attaching adapter
//        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list);
//        mRecyclerView.setAdapter(recyclerAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

//        if (id == R.id.action_settings) {
//            startActivity(new Intent(this, SettingsActivity.class));
//            return true; }

        if (id == R.id.sign_out) {
           firebaseAuth.signOut();
           Home.this.startActivity(new Intent(Home.this,MainActivity.class));
           Home.this.finish();
           return true;
        }

        return super.onOptionsItemSelected(item);
    }
}