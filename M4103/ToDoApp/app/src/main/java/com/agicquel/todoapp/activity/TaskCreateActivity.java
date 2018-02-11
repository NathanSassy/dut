package com.agicquel.todoapp.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.agicquel.todoapp.R;
import com.agicquel.todoapp.model.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by agicquel
 * Activity used of creating or editing task
 */

public class TaskCreateActivity extends AppCompatActivity implements View.OnClickListener{
    // Data
    static final String TASK_STATUS = "TASK_STATUS";
    static final String TASK_OLD = "TASK_OLD";
    static final String TASK_CREATED = "TASK_CREATED";
    static final String TASK_CHANGED = "TASK_CHANGED";
    static final String TASK_DELETED = "TASK_DELETED";
    private boolean isChanged = false;
    private Calendar mCalendar;
    private int duration;
    private Task modTask = null;

    // GUI
    private Drawable originalDrawable;
    private EditText mTitle;
    private EditText mDescription;
    private EditText mDate;
    private EditText mTime;
    private EditText mDuration;
    private EditText mContext;
    private EditText mLink;
    private Button saveBtn;
    private Button cancelBtn;
    private Button deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_create);

        mCalendar = Calendar.getInstance();
        mTitle = findViewById(R.id.create_task_title);
        mDescription = findViewById(R.id.create_task_description);
        mDate = findViewById(R.id.create_task_date);
        mTime = findViewById(R.id.create_task_time);
        mDuration = findViewById(R.id.create_task_duration);
        mContext = findViewById(R.id.create_task_context);
        mLink = findViewById(R.id.create_task_link);
        saveBtn = findViewById(R.id.button_save);
        cancelBtn = findViewById(R.id.button_cancel);
        deleteBtn = findViewById(R.id.button_delete);

        deleteBtn.setVisibility(View.GONE);
        originalDrawable = mTitle.getBackground();

        final TimePickerDialog.OnTimeSetListener timeDialog = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                mCalendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                mCalendar.set(Calendar.MINUTE, selectedMinute);
                SimpleDateFormat simpleDate =  new SimpleDateFormat("HH:mm");
                mTime.setText(simpleDate.format(mCalendar.getTime()));
            }
        };

        final DatePickerDialog.OnDateSetListener dateDialog = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat simpleDate =  new SimpleDateFormat("dd/MM/yyyy");
                mDate.setText(simpleDate.format(mCalendar.getTime()));
            }
        };

        final TimePickerDialog.OnTimeSetListener durationDialog = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                duration = selectedHour * 60 + selectedMinute;
                mDuration.setText(selectedHour + "h " + selectedMinute + "min");
            }
        };

        mDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(
                        TaskCreateActivity.this,
                        dateDialog,
                        mCalendar.get(Calendar.YEAR),
                        mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)
                ).show();
            }
        });

        mTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(
                        TaskCreateActivity.this,
                        timeDialog,
                        mCalendar.get(Calendar.HOUR_OF_DAY),
                        mCalendar.get(Calendar.MINUTE),
                        true
                ).show();
            }
        });


        mDuration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(
                        TaskCreateActivity.this,
                        durationDialog,
                        0,
                        0,
                        true
                ).show();
            }
        });

        saveBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        if(getIntent().getExtras() != null) modTask = (Task) getIntent().getExtras().getSerializable(Task.class.getName());
        if(modTask != null) {
            isChanged = true;
            deleteBtn.setVisibility(View.VISIBLE);
            duration = modTask.getDuration();
            mCalendar = Calendar.getInstance();
            mCalendar.setTime(modTask.getDate());

            mTitle.setText(modTask.getTitle());
            mDescription.setText(modTask.getDescription());
            SimpleDateFormat simpleDate =  new SimpleDateFormat("dd/MM/yyyy");
            mDate.setText(simpleDate.format(modTask.getDate()));
            simpleDate = new SimpleDateFormat("HH:mm");
            mTime.setText(simpleDate.format(modTask.getDate()));;
            mDuration.setText(modTask.getDuration() / 60 + "h" + modTask.getDuration()%60 + " min");
            mContext.setText(modTask.getContext());
            mLink.setText(modTask.getLink());
        }

        if(isChanged)
            getSupportActionBar().setTitle(getString(R.string.change_task));
        else
            getSupportActionBar().setTitle(getString(R.string.create_task));

    }

    private boolean checkInput() {
        boolean valid = true;

        if(mTitle.getText().toString().isEmpty())
            mTitle.setBackgroundResource(R.drawable.edittext_bg_wrong);
        else
            mTitle.setBackgroundDrawable(originalDrawable);

        if(mDescription.getText().toString().isEmpty()) {
            mDescription.setBackgroundResource(R.drawable.edittext_bg_wrong);
            valid = false;
        }
        else
            mTitle.setBackgroundDrawable(originalDrawable);

        if(mDuration.getText().toString().isEmpty()) {
            mDuration.setBackgroundResource(R.drawable.edittext_bg_wrong);
            valid = false;
        }
        else
            mTitle.setBackgroundDrawable(originalDrawable);

        if(mDate.getText().toString().isEmpty()) {
            mDate.setBackgroundResource(R.drawable.edittext_bg_wrong);
            valid = false;
        }
        else
            mTitle.setBackgroundDrawable(originalDrawable);

        if(mTime.getText().toString().isEmpty()) {
            mTime.setBackgroundResource(R.drawable.edittext_bg_wrong);
            valid = false;
        }
        else
            mTitle.setBackgroundDrawable(originalDrawable);

        return valid;
    }

    @Override
    public void onClick(View view) {
        Intent returnIntent = new Intent();
        switch(view.getId()) {
            case R.id.button_save:
                if(!checkInput()) return;

                Task task = new Task(mTitle.getText().toString(),
                        mDescription.getText().toString(),
                        duration,
                        mCalendar.getTime(),
                        mContext.getText().toString(),
                        mLink.getText().toString());
                returnIntent.putExtra(Task.class.getName(), task);
                String status = isChanged ? TASK_CHANGED : TASK_CREATED;
                returnIntent.putExtra(TASK_STATUS, status);
                if(isChanged) returnIntent.putExtra(TASK_OLD, modTask);
                setResult(Activity.RESULT_OK,returnIntent);
                break;

            case R.id.button_cancel:
                setResult(Activity.RESULT_CANCELED, returnIntent);
                break;

            case R.id.button_delete:
                returnIntent.putExtra(TASK_OLD, modTask);
                returnIntent.putExtra(TASK_STATUS, TASK_DELETED);
                setResult(Activity.RESULT_OK,returnIntent);
                break;
        }
        finish();
    }
}
