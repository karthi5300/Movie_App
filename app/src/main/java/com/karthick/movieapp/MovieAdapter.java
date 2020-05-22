package com.karthick.movieapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<String> mId;
    private ArrayList<String> mPosterPath;
    private ArrayList<String> mRating;

    public MovieAdapter(ArrayList<String> posterPath, ArrayList<String> rating, ArrayList<String> id, Context context) {
        mPosterPath = posterPath;
        mRating = rating;
        mId = id;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ItemViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return mRating.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView poster;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.app_logo);
            itemView.setOnClickListener(this);
        }

        public void bindView(int position) {
            Picasso.get().load(mPosterPath.get(position)).into(poster);
            poster.setClipToOutline(true);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, MovieDetailsActivity.class);
            intent.putExtra("id", mId.get(getAbsoluteAdapterPosition()));
            intent.putExtra("poster_path", mPosterPath.get(getAbsoluteAdapterPosition()));
            mContext.startActivity(intent);
        }
    }
}
