package com.example.oszkar.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BlogViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public BlogViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }
    public void setTitle(String title){
        TextView post_title=(TextView)mView.findViewById(R.id.post_title);
        post_title.setText(title);
    }
    public void setDescription(String description){
        TextView post_description=(TextView)mView.findViewById(R.id.post_desc);
        post_description.setText(description);
    }
    public void setImage(Context ctx, String image){
        ImageView post_image=(ImageView)mView.findViewById(R.id.post_image);
        Picasso.with(ctx).load(image).into(post_image);
    }
}
