package com.augustovictor.add2do.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.augustovictor.add2do.Database.TodoDbSchema.TodoTable;
import com.augustovictor.add2do.Helpers.TodoHelper;
import com.augustovictor.add2do.Utils.TodoCursorWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by victoraweb on 5/23/16.
 */
public class TodoManager {
    private static TodoManager sTodoManager;

//    private List<Todo> mTodos;

    private Context mContext;
    private SQLiteDatabase mDb;

    private static ContentValues getContentValues(Todo todo) {
        ContentValues values = new ContentValues();

        values.put(TodoTable.Cols.UUID, todo.getmId().toString());
        values.put(TodoTable.Cols.TITLE, todo.getmTitle());
        values.put(TodoTable.Cols.DONE, todo.ismDone() ? 1 : 0);

        return values;
    }

    public TodoManager(Context context) {
        mContext = context.getApplicationContext();
        mDb = new TodoHelper(mContext).getWritableDatabase();
    }

    public static TodoManager get(Context context) {
        if(sTodoManager == null) {
            sTodoManager = new TodoManager(context);
        }
        return sTodoManager;
    }

    public List<Todo> getmTodos() {
        TodoCursorWrapper cursor = queryTodos(null, null);
        List<Todo> todos = new ArrayList<>();
        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                todos.add(cursor.getTodo());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return todos;
    }

    public Todo getTodo(UUID todoId) {
        TodoCursorWrapper cursor = queryTodos(
            TodoTable.Cols.UUID + " = ?", new String[] { todoId.toString() }
        );

        try {
            if(cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getTodo();
        } finally {
            cursor.close();
        }
    }

    public void addTodo(Todo t) {
        ContentValues values = getContentValues(t);
        mDb.insert(TodoTable.NAME, null, values);
    }

    public void removeTodo(Todo t) {
        String uuidString = t.getmId().toString();
        mDb.delete(TodoTable.NAME, TodoTable.Cols.UUID + " = ?", new String[] { uuidString });
    }

    public void updateTodo(Todo t) {
        String uuidString = t.getmId().toString();
        ContentValues values = getContentValues(t);
        mDb.update(TodoTable.NAME, values, TodoTable.Cols.UUID + " = ?", new String[] { uuidString });
    }

    private TodoCursorWrapper queryTodos(String whereClause, String[] whereArgs) {
        Cursor cursor = mDb.query(
            TodoTable.NAME,
            null,
            whereClause,
            whereArgs,
            null,
            null,
            null
        );

        return new TodoCursorWrapper(cursor);
    }
}
