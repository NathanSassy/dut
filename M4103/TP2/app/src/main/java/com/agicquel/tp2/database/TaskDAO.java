package com.agicquel.tp2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.agicquel.tp2.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.agicquel.tp2.database.ConstantsDB.*;

/**
 * Created by agicquel on 31/01/18.
 */

public class TaskDAO {
    private SQLiteDatabase db;
    private TaskDataBase taskDataBase;
    SimpleDateFormat typeFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    public TaskDAO(Context context){
        this.taskDataBase = new TaskDataBase(context, NAME_DB, null, VERSION_DB);
    }

    public void open(){
        db = taskDataBase.getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    public SQLiteDatabase getDB(){
        return db;
    }

    public long insert(Task task){
        ContentValues values = new ContentValues();
        values.put(COL_ID, task.getId().toString());
        values.put(COL_TITLE, task.getTitle());
        values.put(COL_DESCRIPTION, task.getDescription());
        values.put(COL_DURATION, task.getDuration());
        values.put(COL_DATE, typeFormat.format(task.getDate()));
        return db.insert(TABLE_TASK, null, values);
    }

    public int update(UUID id, Task task){
        ContentValues values = new ContentValues();
        values.put(COL_ID, task.getId().toString());
        values.put(COL_TITLE, task.getTitle());
        values.put(COL_DESCRIPTION, task.getDescription());
        values.put(COL_DURATION, task.getDuration());
        values.put(COL_DATE, typeFormat.format(task.getDate()));
        return db.update(TABLE_TASK, values, COL_ID + " = \"" + id.toString() + "\"", null);
    }

    public int remove(UUID id){
        return db.delete(TABLE_TASK, COL_ID + " = \""  + id.toString() + "\"", null);
    }

    public Task get(UUID id){
        //Récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = db.query(
                TABLE_TASK,
                new String[] {COL_ID, COL_TITLE, COL_DESCRIPTION, COL_DURATION, COL_DATE},
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

        return task;
    }


}
