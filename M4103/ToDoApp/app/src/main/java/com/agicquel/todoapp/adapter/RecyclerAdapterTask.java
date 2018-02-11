package com.agicquel.todoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.agicquel.todoapp.R;
import com.agicquel.todoapp.activity.WebViewActivity;
import com.agicquel.todoapp.model.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by agicquel on 23/01/18.
 * Adapter of the recycler view's task. Allows filtering.
 */

public class RecyclerAdapterTask extends RecyclerView.Adapter<RecyclerAdapterTask.ViewHolder>  implements Filterable {

    // Data
    private Context mContext;
    private List<Task> mTasks;
    private List<Task> mFilteredList;
    private final int mItemLayout;
    private OnItemSelectedCallback mCallback;

    public RecyclerAdapterTask(List<Task> tasks, int itemLayout, Context context, OnItemSelectedCallback callback) {
        this.mTasks = tasks;
        this.mFilteredList = tasks;
        this.mItemLayout = itemLayout;
        this.mContext = context;
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
        final TextView mContext;
        final TextView mLink;
        final TextView mPreLink;

        ViewHolder(View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.row_task_layout);
            mTitle = itemView.findViewById(R.id.title);
            mDescription = itemView.findViewById(R.id.description);
            mDuration = itemView.findViewById(R.id.duration);
            mDate = itemView.findViewById(R.id.date);
            mContext = itemView.findViewById(R.id.context);
            mLink = itemView.findViewById(R.id.link);
            mPreLink = itemView.findViewById(R.id.linkpre);
        }
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(mItemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Task task = mFilteredList.get(position);
        holder.itemView.setTag(position);
        holder.mTitle.setText(task.getTitle());
        holder.mDescription.setText(mContext.getString(R.string.description) + " : " + task.getDescription());
        holder.mDuration.setText(task.getDuration() / 60 + "h" + task.getDuration()%60 + "min");
        holder.mDate.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(task.getDate()));
        holder.mContext.setText(mContext.getString(R.string.context) + " : " + task.getContext());
        holder.mPreLink.setText(mContext.getString(R.string.link) + " : ");
        holder.mLink.setText(Html.fromHtml("<a href=\"https://" + task.getLink() + "/\">" + task.getLink() + "</a>"));
        holder.mLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra(WebViewActivity.URL_EXTRA, task.getLink());
                mContext.startActivity(intent);
            }
        });

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCallback != null) {
                    mCallback.onClickReceiver(position);
                }
            }
        });
    }


    // Filterable

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String constraintString = constraint.toString().toLowerCase();
                if (constraintString.isEmpty()) {
                    mFilteredList = mTasks;
                } else {

                    List<Task> filteredList = new ArrayList<>();

                    for (Task  t : mTasks) {
                        if (t.getContext().toLowerCase().contains(constraintString)) {
                            Log.w(this.getClass().getName(), "performFiltering: found task = " + t.getTitle());
                            filteredList.add(t);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mFilteredList = (ArrayList<Task>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    // CallBack

    public interface OnItemSelectedCallback {
        void onClickReceiver(int position);
    }
}
