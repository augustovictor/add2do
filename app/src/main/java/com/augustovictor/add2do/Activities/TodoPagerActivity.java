package com.augustovictor.add2do.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.augustovictor.add2do.Fragments.TodoFragment;
import com.augustovictor.add2do.Models.Todo;
import com.augustovictor.add2do.Models.TodoManager;
import com.augustovictor.add2do.R;

import java.util.List;
import java.util.UUID;

/**
 * Created by victoraweb on 5/23/16.
 */
public class TodoPagerActivity extends FragmentActivity {

    private static final String EXTRA_TODO_ID = "com.augustovictor.add2do.todo_id";
    private ViewPager mViewPager;
    private List<Todo> mTodos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_pager);

        UUID todoId = (UUID) getIntent().getSerializableExtra(EXTRA_TODO_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_todo_pager);
        mTodos = TodoManager.get(this).getTodos();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Todo todo = mTodos.get(position);
                return TodoFragment.newInstance(todo.getmId());
            }

            @Override
            public int getCount() {
                return mTodos.size();
            }
        });

        for(int i = 0; i < mTodos.size(); i++) {
            if (mTodos.get(i).getmId().equals(todoId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }

    public static Intent newIntent(Context packageContext, UUID todo_id) {
        Intent intent = new Intent(packageContext, TodoPagerActivity.class);
        intent.putExtra(EXTRA_TODO_ID, todo_id);
        return intent;
    }
}
