package com.augustovictor.add2do.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.augustovictor.add2do.Fragments.TodoFragment;
import com.augustovictor.add2do.R;

import java.util.UUID;

public class TodoActivity extends SingletonFragmentActivity {

    private static final String EXTRA_TODO_ID = "com.augustovictor.add2do.todo_id";

    public static Intent newIntent(Context packageContext, UUID todoId) {
        Intent i = new Intent(packageContext, TodoActivity.class);
        i.putExtra(EXTRA_TODO_ID, todoId);
        return i;
    }

    @Override
    public Fragment createFragment() {
        UUID todoId = (UUID) getIntent().getSerializableExtra(EXTRA_TODO_ID);
        return TodoFragment.newInstance(todoId);
    }
}
