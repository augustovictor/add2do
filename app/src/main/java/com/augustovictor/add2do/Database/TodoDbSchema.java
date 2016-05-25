package com.augustovictor.add2do.Database;

/**
 * Created by victoraweb on 5/25/16.
 */
public class TodoDbSchema {

    public static final class TodoTable {
        public static final String NAME = "todos";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DONE = "done";
        }
    }

}
