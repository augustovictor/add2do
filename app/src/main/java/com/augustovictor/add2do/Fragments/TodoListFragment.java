package com.augustovictor.add2do.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.augustovictor.add2do.Models.Todo;
import com.augustovictor.add2do.Models.TodoManager;
import com.augustovictor.add2do.R;

import java.util.List;

/**
 * Created by victoraweb on 5/23/16.
 */
public class TodoListFragment extends Fragment{

    private RecyclerView mRecyclerView;
    private TodoAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_todo_list, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.todo_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private class TodoHolder extends RecyclerView.ViewHolder {

        private Todo mTodo;
        private TextView mTitle;
        private CheckBox mDone;

        public TodoHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.vt_list_todo_item);
            mDone = (CheckBox) itemView.findViewById(R.id.ck_list_todo_item);
        }

        public void bindTodo(Todo todo) {
            mTodo = todo;
            mTitle.setText(mTodo.getmTitle());
            mDone.setChecked(mTodo.ismDone());
        }


    }

    private class TodoAdapter extends RecyclerView.Adapter<TodoHolder> {

        private List<Todo> mTodos;

        public TodoAdapter(List<Todo> mTodos) {
            this.mTodos = mTodos;
        }

        @Override
        public TodoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_todo, parent, false);

            return new TodoHolder(view);
        }

        @Override
        public void onBindViewHolder(TodoHolder holder, int position) {
            Todo todo = mTodos.get(position);
            holder.bindTodo(todo);
        }

        @Override
        public int getItemCount() {
            return mTodos.size();
        }

        public void setTodos(List<Todo> todos) {
            this.mTodos = todos;
        }
    }

    private void updateUI() {
        TodoManager todoManager = TodoManager.get(getActivity());

        List<Todo> todos = todoManager.getTodos();

        if(mAdapter == null) {
            mAdapter = new TodoAdapter(todos);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setTodos(todos);
            mAdapter.notifyDataSetChanged();
        }

        mAdapter = new TodoAdapter(todos);
        mRecyclerView.setAdapter(mAdapter);
    }
}
