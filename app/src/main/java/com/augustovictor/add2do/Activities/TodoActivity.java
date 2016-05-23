package com.augustovictor.add2do.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.augustovictor.add2do.Fragments.TodoFragment;
import com.augustovictor.add2do.R;

public class TodoActivity extends SingletonFragmentActivity {

    @Override
    public Fragment createFragment() {
        return new TodoFragment();
    }
}
