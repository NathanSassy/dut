package com.agicquel.tp2.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.agicquel.tp2.R;
import com.agicquel.tp2.model.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TaskCreateActivity extends AppCompatActivity implements View.OnClickListener{
    // Data
    Calendar mCalendar;

    // GUI
    private EditText mTitle;
    private EditText mDescription;
    private EditText mDate;
    private EditText mTime;
    private EditText mDuration;
    private Button saveBtn;
    private Button cancelBtn;

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
        saveBtn = findViewById(R.id.button_save);
        cancelBtn = findViewById(R.id.button_cancel);

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

        saveBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent returnIntent = new Intent();
        switch(view.getId()) {
            case R.id.button_save:
                Task task = new Task(mTitle.getText().toString(), mDescription.getText().toString(), 30, mCalendar.getTime());

                returnIntent.putExtra(task.getClass().getName(), task);
                setResult(Activity.RESULT_OK,returnIntent);

                break;
            case R.id.button_cancel:
                setResult(Activity.RESULT_CANCELED, returnIntent);
                break;
        }
        finish();
    }
}
