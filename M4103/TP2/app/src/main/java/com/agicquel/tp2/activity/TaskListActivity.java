package com.agicquel.tp2.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.agicquel.tp2.R;
import com.agicquel.tp2.adapter.RecyclerAdapterTask;
import com.agicquel.tp2.model.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskListActivity extends AppCompatActivity implements RecyclerAdapterTask.OnItemSelectedCallback {
    // DATA
    static final int CREATE_TASK_REQUEST = 1;
    List<Task> mTasks;
    // GUI
    private FloatingActionButton mFAB;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        mTasks = new ArrayList<>();
        mTasks.add(new Task("Task 1", "Blablabla", 45, new Date()));
        mTasks.add(new Task("Task 2", "Blablabla", 10, new Date()));
        mTasks.add(new Task("Task 3", "Blablabla", 30, new Date()));
        mTasks.add(new Task("Task 4", "Blablabla", 120, new Date()));

        mFAB = findViewById(R.id.floatingActionButton);
        mRecyclerView = findViewById(R.id.recyclerView);

        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskListActivity.this, TaskCreateActivity.class);
                startActivityForResult(intent, CREATE_TASK_REQUEST);
            }
        });

        RecyclerAdapterTask recyclerAdapterTask = new RecyclerAdapterTask(mTasks, R.layout.row_task, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(recyclerAdapterTask);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setItemViewCacheSize(10);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setFocusable(false);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_CANCELED)
            return;

        if(requestCode == CREATE_TASK_REQUEST) {
            if(resultCode == Activity.RESULT_OK){
                Task task = (Task) data.getSerializableExtra(Task.class.getName());
                mTasks.add(task);
                RecyclerAdapterTask recyclerAdapterTask = new RecyclerAdapterTask(mTasks, R.layout.row_task, this);
                mRecyclerView.setAdapter(recyclerAdapterTask);
                mRecyclerView.invalidate();
            }
        }
    }

    // Callback for item in the list

    @Override
    public void onClickReceiver(int position) {
        Task task = mTasks.get(position);
    }
}