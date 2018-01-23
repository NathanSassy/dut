package com.agicquel.tp2.adapter;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.agicquel.tp2.R;
import com.agicquel.tp2.model.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by agicquel on 23/01/18.
 */

public class RecyclerAdapterTask extends RecyclerView.Adapter<RecyclerAdapterTask.ViewHolder> {

    // Data

    private final List<Task> mTasks;
    private final int mItemLayout;
    private OnItemSelectedCallback mCallback;

    public RecyclerAdapterTask(List<Task> tasks, int itemLayout, OnItemSelectedCallback callback) {
        this.mTasks = tasks;
        this.mItemLayout = itemLayout;
        this.mCallback = callback;
    }


    // GUI

    static class ViewHolder extends RecyclerView.ViewHolder {
        // SurfaceView
        final CardView mCardView;
        final TextView mTitle;
        final TextView mDescription;
        final TextView mDuration;
        final TextView mDate;

        ViewHolder(View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.row_task_layout);
            mTitle = itemView.findViewById(R.id.title);
            mDescription = itemView.findViewById(R.id.description);
            mDuration = itemView.findViewById(R.id.duration);
            mDate = itemView.findViewById(R.id.date);
        }
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(mItemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Task task = mTasks.get(position);
        holder.itemView.setTag(position);
        holder.mTitle.setText(task.getTitle());
        holder.mDescription.setText(task.getDescription());
        holder.mDuration.setText(task.getDuration() + " min");
        holder.mDate.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(task.getDate()));

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
