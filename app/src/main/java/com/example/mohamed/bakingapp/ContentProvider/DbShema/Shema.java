package com.example.mohamed.bakingapp.ContentProvider.DbShema;

/**
 * Created by mohamed on 14/10/2017.
 */

public class Shema {

    public static class TableIngredients{
        public static String NAME="Ingredients";
        public static class COLS{
            public static String MEAL="meal";
            public static String NAME="name";
            public static String QUANTITY="quantity";
            public static String MEASURE="measure";
        }
    }
}
