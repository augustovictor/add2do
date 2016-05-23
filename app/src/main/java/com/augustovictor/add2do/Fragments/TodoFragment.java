package com.augustovictor.add2do.Fragments;

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
import com.augustovictor.add2do.R;

/**
 * Created by victoraweb on 5/23/16.
 */
public class TodoFragment extends Fragment {

    private Todo mTodo;
    private EditText mTitle;
    private CheckBox mDone;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTodo = new Todo();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.todo_fragment, container, false);

        mTitle = (EditText) v.findViewById(R.id.et_task_title);

        mDone = (CheckBox) v.findViewById(R.id.ck_task_done);
        mDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTodo.setmDone(true);
            }
        });

        return v;
    }
}
