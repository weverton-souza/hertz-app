package com.rent.hertz.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static String LOG = "DataBaseHelper";

    // DataBase Version.
    private static final int DATABASE_VERSION = 1;

    //Database name.
    private static final String DATABASE_NAME = "hertzdb-mobile";

    //Table names.
    protected static final String TABLE_CATEGORY = "category";
    protected static final String TABLE_MANUFACTURE = "manufacturer";
    protected static final String TABLE_MODEL = "model";
    protected static final String TABLE_VEHICLE = "vehicle";

    //Table Category column names.
    protected static final String ID_CATEGORY = "id";
    protected static final String DESCRIPTION_CATEGORY = "description";
    protected static final String PRICE_CATEGORY = "price";

    //Table Manufacture column names.
    protected static final String ID_MANUFACTURE = "id";
    protected static final String NAME_MANUFACTURE = "name";
    protected static final String DESCRIPTION_MANUFACTURE = "description";

    //Table Model column names.
    private static final String ID_MODEL = "id";
    private static final String NAME_MODEL = "name";
    private static final String NAME_MODEL_YEAR = "model_year";
    private static final String DESCRIPTION_MODEL = "description";

    //Table Vehicle column names.
    private static final String ID_VEHICLE = "id";
    private static final String VEHICLE_ID_CATEGORY = "category";
    private static final String VEHICLE_ID_MODEL = "model";
    private static final String VEHICLE_ID_MANUFACTURE = "manufacturer";


    // Table Create Statements.
    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE category ( " +
            ID_CATEGORY + " CHAR(36) PRIMARY KEY, " +
            DESCRIPTION_CATEGORY + "description TEXT DEFAULT NULL, " +
            PRICE_CATEGORY + "price REAL DEFAULT NULL )";

    // Table Create Statements.
    private static final String CREATE_TABLE_MANUFACTURE= "CREATE TABLE manufacturer ( " +
            ID_MANUFACTURE + " CHAR(36) PRIMARY KEY, " +
            NAME_MANUFACTURE + " TEXT DEFAULT NULL, " +
            DESCRIPTION_MANUFACTURE + " TEXT DEFAULT NULL )";

    public DataBaseHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_MANUFACTURE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_MANUFACTURE);
        onCreate(db);
    }
}
