package com.example.mycars.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mycars.constants.DatabaseConstants;

public class VehicleDatabaseHelper extends SQLiteOpenHelper {

    private static final String MODEL = "modelo.bd";
    private static final int VERSION = 1;

    private static final String CREATE_TABLE_VEHICLE = "create table " + DatabaseConstants.VEHICLES.TABLE_MODEL + " ("
            + DatabaseConstants.VEHICLES.COLUMNS.ID + " integer primary key autoincrement, "
            + DatabaseConstants.VEHICLES.COLUMNS.MODEL + " text, "
            + DatabaseConstants.VEHICLES.COLUMNS.YEAR + " int, "
            + DatabaseConstants.VEHICLES.COLUMNS.PRICE + " double,"
            + DatabaseConstants.VEHICLES.COLUMNS.IMAGE + " text )";

    public VehicleDatabaseHelper(Context context) {
        super(context, MODEL, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_VEHICLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String s = "";
    }
}
