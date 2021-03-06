package com.augustovictor.add2do.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.augustovictor.add2do.Activities.TodoListActivity;
import com.augustovictor.add2do.Models.Todo;
import com.augustovictor.add2do.Models.TodoManager;
import com.augustovictor.add2do.R;

import java.util.UUID;

/**
 * Created by victoraweb on 5/23/16.
 */
public class TodoFragment extends Fragment {

    private static final String ARG_TODO_ID = "todo_id";

    private Todo mTodo;
    private EditText mTitle;
    private CheckBox mDone;

    public static TodoFragment newInstance(UUID id) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TODO_ID, id);
        TodoFragment todoFragment = new TodoFragment();
        todoFragment.setArguments(args);
        return todoFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID todoId = (UUID) getArguments().getSerializable(ARG_TODO_ID);
        mTodo = TodoManager.get(getActivity()).getTodo(todoId);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        TodoManager.get(getActivity()).updateTodo(mTodo);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.todo_fragment, container, false);

        mTitle = (EditText) v.findViewById(R.id.et_task_title);
        mTitle.setText(mTodo.getmTitle());

        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTodo.setmTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDone = (CheckBox) v.findViewById(R.id.ck_task_done);
        mDone.setChecked(mTodo.ismDone());

        mDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTodo.setmDone(isChecked);
            }
        });

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_todo_item, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_item_remove_todo:
                TodoManager.get(getActivity()).removeTodo(mTodo);
                Intent i = TodoListActivity.newIntent(getActivity());
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
