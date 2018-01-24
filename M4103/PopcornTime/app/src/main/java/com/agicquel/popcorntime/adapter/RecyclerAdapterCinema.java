package com.agicquel.popcorntime.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agicquel.popcorntime.R;
import com.agicquel.popcorntime.model.Cinema;

import java.util.List;

/**
 * Created by agicquel on 24/01/18.
 */

public class RecyclerAdapterCinema extends RecyclerView.Adapter<RecyclerAdapterCinema.ViewHolder> {

    // Data

    private final List<Cinema> mCinemas;
    private final int mItemLayout;
    private OnItemSelectedCallback mCallback;

    public RecyclerAdapterCinema(List<Cinema> cinemas, int itemLayout, OnItemSelectedCallback callback) {
        this.mCinemas = cinemas;
        this.mItemLayout = itemLayout;
        this.mCallback = callback;
    }


    // GUI

    static class ViewHolder extends RecyclerView.ViewHolder {
        // SurfaceView
        final CardView mCardView;
        final ImageView mImage;
        final TextView mTitle;
        final TextView mDuration;
        final TextView mDirector;

        ViewHolder(View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.cinema_item_layout);
            mImage = itemView.findViewById(R.id.cinema_item_image);
            mTitle = itemView.findViewById(R.id.cinema_item_title);
            mDuration = itemView.findViewById(R.id.cinema_item_duration);
            mDirector = itemView.findViewById(R.id.cinema_item_director);
        }
    }

    @Override
    public int getItemCount() {
        return mCinemas.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(mItemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Cinema cinema = mCinemas.get(position);
        holder.itemView.setTag(position);
        holder.mImage.setImageResource(cinema.getImageSrc());
        holder.mTitle.setText(cinema.getTitle());
        holder.mDuration.setText(cinema.getDuration() + " min");
        holder.mDirector.setText(cinema.getDirector());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallback != null) {
                    mCallback.onClickReceiver(position);
                }
            }
        });
    }


    // CallBack

    public interface OnItemSelectedCallback {
        void onClickReceiver(int position);
    }

}
