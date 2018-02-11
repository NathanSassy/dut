package com.agicquel.todoapp.database;

/**
 * Created by agicquel on 31/01/18.
 * Here is stored all the database related constants
 */

class ConstantsDB {
    static final int VERSION_DB = 1;
    static final String NAME_DB = "task.db";

    static final String TABLE_TASK = "table_task";
    static final String COL_ID = "ID";
    static final String COL_TITLE = "title";
    static final String COL_DESCRIPTION = "description";
    static final String COL_DURATION = "duration";
    static final String COL_DATE = "date";
    static final String COL_CONTEXT = "context";
    static final String COL_LINK = "link";

    static final int COL_ID_NUM = 0;
    static final int COL_TITLE_NUM = 1;
    static final int COL_DESCRIPTION_NUM = 2;
    static final int COL_DURATION_NUM = 3;
    static final int COL_DATE_NUM = 4;
    static final int COL_CONTEXT_NUM = 5;
    static final int COL_LINK_NUM = 6;
}
