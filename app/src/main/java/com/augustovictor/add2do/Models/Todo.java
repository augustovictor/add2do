package com.augustovictor.add2do.Models;

import java.util.UUID;

/**
 * Created by victoraweb on 5/23/16.
 */
public class Todo {
    private UUID mId;
    private String mTitle;
    private boolean mDone;


    public Todo() {
        mId = UUID.randomUUID();
    }

    public Todo(UUID mId) {
        this.mId = mId;
    }

    public boolean ismDone() {
        return mDone;
    }

    public void setmDone(boolean mDone) {
        this.mDone = mDone;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public UUID getmId() {
        return mId;
    }

    public void setmId(UUID mId) {
        this.mId = mId;
    }
}
