package com.example.oszkar.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.WordViewHolder>{
    private final ArrayList<Blog> mWordList;
    private LayoutInflater mInflater;

    public RecyclerViewAdapter(Context context,
                           ArrayList<Blog> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }

    class WordViewHolder extends RecyclerView.ViewHolder {
        public final TextView wordItemView;
        public final TextView description;
        public final ImageView mImage;
        final RecyclerViewAdapter mAdapter;
        public WordViewHolder(View itemView, RecyclerViewAdapter adapter) {
            super(itemView);

            wordItemView = itemView.findViewById(R.id.post_title);
            description=itemView.findViewById(R.id.post_desc);
            mImage = itemView.findViewById(R.id.post_image);

            this.mAdapter = adapter;
        }
    }
    @NonNull
    @Override
    public RecyclerViewAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.recycler_item,
                parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.WordViewHolder holder, int position) {
        Blog mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent.getTitle());
        holder.description.setText(mCurrent.getDescription());

    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }
}
