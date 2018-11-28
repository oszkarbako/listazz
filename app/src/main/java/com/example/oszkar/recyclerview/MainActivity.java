package com.example.oszkar.recyclerview;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import dalvik.system.BaseDexClassLoader;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mBlogList;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabase;

    private TextView mTextMessage; //buttonnal jart

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabase = mFirebaseDatabase.getReference("sapiadvertiser-8ea00");
        mDatabase.keepSynced(true);

        mBlogList = (RecyclerView) findViewById(R.id.myRecyclerView);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(Blog.class, R.layout.blog_row, BlogViewHolder.class, mDatabase) {
                    @Override
                    protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {
                        viewHolder.setTitle(model.getTitle());
                        viewHolder.setDescription(model.getDescription());
                        viewHolder.setImage(getApplicationContext(), model.getImage());
                    }
                };

        mBlogList.setAdapter(firebaseRecyclerAdapter);
    }
}



