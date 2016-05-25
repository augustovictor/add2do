package com.augustovictor.add2do.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.augustovictor.add2do.Database.TodoDbSchema.TodoTable;
import com.augustovictor.add2do.Helpers.TodoHelper;

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
//        mTodos = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Todo todo = new Todo();
            todo.setmTitle("Task " + i);
            todo.setmDone(i%2 == 0);
//            mTodos.add(todo);
        }
    }

    public static TodoManager get(Context context) {
        if(sTodoManager == null) {
            sTodoManager = new TodoManager(context);
        }
        return sTodoManager;
    }

    public List<Todo> getmTodos() {
        return new ArrayList<>();
    }

    public Todo getTodo(UUID todoId) {
//        for (Todo t : this.mTodos) {
//            if (t.getmId().equals(todoId)) {
//                return t;
//            }
//        }
        return null;
    }

    public void addTodo(Todo t) {
        ContentValues values = getContentValues(t);
        mDb.insert(TodoTable.NAME, null, values);
    }

    public void removeTodo(Todo t) {
//        this.mTodos.remove(t);
    }

    public void updateTodo(Todo t) {
        String uuidString = t.getmId().toString();
        ContentValues values = getContentValues(t);
        mDb.update(TodoTable.NAME, values, TodoTable.Cols.UUID + " = ?", new String[] { uuidString });
    }
}
