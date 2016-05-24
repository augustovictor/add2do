package com.augustovictor.add2do.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.augustovictor.add2do.Activities.SingletonFragmentActivity;
import com.augustovictor.add2do.Activities.TodoActivity;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID todoId = (UUID) getArguments().getSerializable(ARG_TODO_ID);
        mTodo = TodoManager.get(getActivity()).getTodo(todoId);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.todo_fragment, container, false);

        mTitle = (EditText) v.findViewById(R.id.et_task_title);
        mTitle.setText(mTodo.getmTitle());

        mDone = (CheckBox) v.findViewById(R.id.ck_task_done);
        mDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTodo.setmDone(true);
            }
        });

        return v;
    }

    public static TodoFragment newInstance(UUID id) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TODO_ID, id);
        TodoFragment todoFragment = new TodoFragment();
        todoFragment.setArguments(args);
        return todoFragment;
    }
}
