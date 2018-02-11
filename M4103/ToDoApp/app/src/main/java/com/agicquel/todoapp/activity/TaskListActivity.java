package com.agicquel.todoapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.agicquel.todoapp.R;
import com.agicquel.todoapp.adapter.RecyclerAdapterTask;
import com.agicquel.todoapp.database.TaskDAO;
import com.agicquel.todoapp.model.Task;

import java.util.List;

/**
 * Created by agicquel
 * Activity showing the list of tasks
 */

public class TaskListActivity extends AppCompatActivity implements RecyclerAdapterTask.OnItemSelectedCallback {

    // DATA
    static final int CREATE_TASK_REQUEST = 1;
    static final int MODIFY_TASK_REQUEST = 2;
    private TaskDAO dao;
    List<Task> mTasks;
    // GUI
    private CoordinatorLayout mCoordinatorLayout;
    private FloatingActionButton mFAB;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        getSupportActionBar().setTitle(getString(R.string.list_task));
        dao = new TaskDAO(this);
        dao.open();

        mTasks = dao.getAll();

        mFAB = findViewById(R.id.floatingActionButton);
        mRecyclerView = findViewById(R.id.recyclerView);
        mCoordinatorLayout = findViewById(R.id.layout);

        mFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskListActivity.this, TaskCreateActivity.class);
                startActivityForResult(intent, CREATE_TASK_REQUEST);
            }
        });

        RecyclerAdapterTask recyclerAdapterTask = new RecyclerAdapterTask(mTasks, R.layout.row_task, this,this);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.task_list_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ((RecyclerAdapterTask) mRecyclerView.getAdapter()).getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dao.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_CANCELED)
            return;

        if(requestCode == CREATE_TASK_REQUEST && resultCode == Activity.RESULT_OK) {
            Task task = (Task) data.getSerializableExtra(Task.class.getName());
            mTasks.add(task);
            dao.insert(task);
            showSnackbarMessage(getString(R.string.task_created));
            refreshRecyclerView();
        }
        else if(requestCode == MODIFY_TASK_REQUEST && resultCode == Activity.RESULT_OK) {
            Task task = (Task) data.getSerializableExtra(TaskCreateActivity.TASK_OLD);
            int position = mTasks.indexOf(task);


            if(data.getStringExtra(TaskCreateActivity.TASK_STATUS).equals(TaskCreateActivity.TASK_CHANGED)) {
                Task newTask = (Task) data.getSerializableExtra(Task.class.getName());
                mTasks.set(position, newTask);
                dao.update(task.getId(), newTask);
                showSnackbarMessage(getString(R.string.task_changed));
            }
            else if(data.getStringExtra(TaskCreateActivity.TASK_STATUS).equals(TaskCreateActivity.TASK_DELETED)) {
                mTasks.remove(position);
                dao.remove(task.getId());
                showSnackbarTaskDeleted(position, task);
            }

            refreshRecyclerView();
        }
    }

    private void showSnackbarMessage(String message) {
        Snackbar.make(mCoordinatorLayout,
                message,
                Snackbar.LENGTH_SHORT
        ).show();
    }

    private void showSnackbarTaskDeleted(final int position, final Task task) {
        Snackbar snackbar = Snackbar.make(mCoordinatorLayout,
                getString(R.string.task_deleted),
                Snackbar.LENGTH_SHORT
        );
        snackbar.setAction(getString(R.string.app_undo),
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mTasks.add(position, task);
                        dao.insert(task);
                        refreshRecyclerView();
                        Snackbar snackbar1 = Snackbar.make(mCoordinatorLayout, getString(R.string.task_restored), Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                    }
                });
        snackbar.show();
    }

    private void refreshRecyclerView() {
        RecyclerAdapterTask recyclerAdapterTask = new RecyclerAdapterTask(mTasks, R.layout.row_task, this, this);
        mRecyclerView.setAdapter(recyclerAdapterTask);
        mRecyclerView.invalidate();
    }

    // Callback for item in the list

    @Override
    public void onClickReceiver(int position) {
        Task task = mTasks.get(position);
        Intent intent = new Intent(this, TaskCreateActivity.class);
        intent.putExtra(Task.class.getName(), task);
        startActivityForResult(intent, MODIFY_TASK_REQUEST);
    }
}