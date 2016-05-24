package com.augustovictor.add2do.Models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by victoraweb on 5/23/16.
 */
public class TodoManager {
    private static TodoManager sTodoManager;

    private Context mContext;

    private List<Todo> todos;

    public TodoManager(Context context) {
        mContext = context.getApplicationContext();
        todos = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Todo todo = new Todo();
            todo.setmTitle("Task " + i);
            todo.setmDone(i%2 == 0);
            todos.add(todo);
        }
    }

    public static TodoManager get(Context context) {
        if(sTodoManager == null) {
            sTodoManager = new TodoManager(context);
        }
        return sTodoManager;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public Todo getTodo(UUID todoId) {
        for (Todo t : this.todos) {
            if (t.getmId().equals(todoId)) {
                return t;
            }
        }
        return null;
    }
}
