package com.agicquel.todoapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.agicquel.todoapp.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.agicquel.todoapp.database.ConstantsDB.*;

/**
 * Created by agicquel on 31/01/18.
 * The task dao that allows to get tasks, edit or remove them
 * into the database
 */

public class TaskDAO {
    private SQLiteDatabase db;
    private TaskDataBase taskDataBase;
    SimpleDateFormat typeFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    /**
     * Create dao object
     * @param context activity context needed
     */
    public TaskDAO(Context context){
        this.taskDataBase = new TaskDataBase(context, NAME_DB, null, VERSION_DB);
    }

    /**
     * Open the dao stream
     */
    public void open(){
        db = taskDataBase.getWritableDatabase();
    }

    /**
     * Close the dao stream
     */
    public void close(){
        db.close();
    }

    /**
     * @return get the database object
     */
    public SQLiteDatabase getDB(){
        return db;
    }

    /**
     * Insert a task into the db
     * @param task the task you want to insert
     */
    public void insert(Task task){
        ContentValues values = new ContentValues();
        values.put(COL_ID, task.getId().toString());
        values.put(COL_TITLE, task.getTitle());
        values.put(COL_DESCRIPTION, task.getDescription());
        values.put(COL_DURATION, task.getDuration());
        values.put(COL_DATE, typeFormat.format(task.getDate()));
        values.put(COL_CONTEXT, task.getContext());
        values.put(COL_LINK, task.getLink());
        db.insert(TABLE_TASK, null, values);
    }

    /**
     * Update a task into the db
     * @param id the id of the task you want to edit
     * @param task the task with the new value
     */
    public void update(UUID id, Task task){
        ContentValues values = new ContentValues();
        values.put(COL_ID, task.getId().toString());
        values.put(COL_TITLE, task.getTitle());
        values.put(COL_DESCRIPTION, task.getDescription());
        values.put(COL_DURATION, task.getDuration());
        values.put(COL_DATE, typeFormat.format(task.getDate()));
        values.put(COL_CONTEXT, task.getContext());
        values.put(COL_LINK, task.getLink());
        db.update(TABLE_TASK, values, COL_ID + " = \"" + id.toString() + "\"", null);
    }

    /**
     * Remove a task from the db
     * @param id the id of the task you to remove
     */
    public void remove(UUID id){
        db.delete(TABLE_TASK, COL_ID + " = \""  + id.toString() + "\"", null);
    }

    /**
     * @param id the id of the task you want to get
     * @return get the selected task
     */
    public Task get(UUID id){
        Cursor c = db.query(
                TABLE_TASK,
                new String[] {COL_ID, COL_TITLE, COL_DESCRIPTION, COL_DURATION, COL_DATE, COL_CONTEXT, COL_LINK},
                COL_ID + " = \"" + id.toString() +"\"",
                null,
                null,
                null,
                null
        );

        c.moveToFirst();
        Task task = cursorToTask(c);
        c.close();
        return task;
    }

    /**
     * @return get all tasks
     */
    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();

        Cursor c = db.rawQuery("select * from " + TABLE_TASK,null);
        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                tasks.add(cursorToTask(c));
                c.moveToNext();
            }
        }

        c.close();
        return tasks;
    }

    private Task cursorToTask(Cursor c){
        if (c.getCount() == 0)
            return null;

        Task task = new Task();
        task.setId(UUID.fromString(c.getString(COL_ID_NUM)));
        task.setTitle(c.getString(COL_TITLE_NUM));
        task.setDescription(c.getString(COL_DESCRIPTION_NUM));
        task.setDuration(c.getInt(COL_DURATION_NUM));
        try {
            task.setDate(typeFormat.parse(c.getString(COL_DATE_NUM)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        task.setContext(c.getString(COL_CONTEXT_NUM));
        task.setLink(c.getString(COL_LINK_NUM));

        return task;
    }

}
