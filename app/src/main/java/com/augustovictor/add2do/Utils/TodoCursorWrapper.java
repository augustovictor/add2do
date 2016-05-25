package com.augustovictor.add2do.Utils;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.augustovictor.add2do.Database.TodoDbSchema.TodoTable;
import com.augustovictor.add2do.Models.Todo;

import java.util.UUID;

/**
 * Created by victoraweb on 5/25/16.
 */
public class TodoCursorWrapper extends CursorWrapper {

    public TodoCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Todo getTodo() {
        String uuidString = getString(getColumnIndex(TodoTable.Cols.UUID));
        String title = getString(getColumnIndex(TodoTable.Cols.TITLE));
        int done = getInt(getColumnIndex(TodoTable.Cols.DONE));

        Todo todo = new Todo(UUID.fromString(uuidString));
        todo.setmTitle(title);
        todo.setmDone(done != 0);
        return todo;
    }

}
