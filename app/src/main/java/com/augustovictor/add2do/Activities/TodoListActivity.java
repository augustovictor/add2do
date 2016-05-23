package com.augustovictor.add2do.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.augustovictor.add2do.Fragments.TodoListFragment;

/**
 * Created by victoraweb on 5/23/16.
 */
public class TodoListActivity extends SingletonFragmentActivity {

    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, TodoListActivity.class);
        return i;
    }

    @Override
    protected Fragment createFragment() {
        return new TodoListFragment();
    }
}
