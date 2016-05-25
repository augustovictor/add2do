package com.augustovictor.add2do.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.augustovictor.add2do.Database.TodoDbSchema.TodoTable;

/**
 * Created by victoraweb on 5/25/16.
 */
public class TodoHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "todos.db";

    public TodoHelper(Context packageContext) {
        super(packageContext, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +
            TodoTable.NAME +
            "(" +
            TodoTable.Cols.UUID +
            ", " +
            TodoTable.Cols.TITLE +
            ", " +
            TodoTable.Cols.DONE +
        ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
