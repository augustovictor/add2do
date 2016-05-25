package com.augustovictor.add2do.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.augustovictor.add2do.Helpers.TodoHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by victoraweb on 5/23/16.
 */
public class TodoManager {
    private static TodoManager sTodoManager;

    private List<Todo> mTodos;

    private Context mContext;
    private SQLiteDatabase mDb;

    public TodoManager(Context context) {
        mContext = context.getApplicationContext();
        mDb = new TodoHelper(mContext).getWritableDatabase();
        mTodos = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Todo todo = new Todo();
            todo.setmTitle("Task " + i);
            todo.setmDone(i%2 == 0);
            mTodos.add(todo);
        }
    }

    public static TodoManager get(Context context) {
        if(sTodoManager == null) {
            sTodoManager = new TodoManager(context);
        }
        return sTodoManager;
    }

    public List<Todo> getmTodos() {
        return mTodos;
    }

    public Todo getTodo(UUID todoId) {
        for (Todo t : this.mTodos) {
            if (t.getmId().equals(todoId)) {
                return t;
            }
        }
        return null;
    }

    public void addTodo(Todo t) {
        mTodos.add(t);
    }

    public void removeTodo(Todo t) {
        this.mTodos.remove(t);
    }
}
