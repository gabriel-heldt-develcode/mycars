package com.example.mycars.repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mycars.constants.DatabaseConstants;
import com.example.mycars.model.VehiclesModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VehiclesRepository {

    private static VehiclesRepository INSTANCE;
    private final VehicleDatabaseHelper mHelper;         //variável q contem o banco de dados

    private VehiclesRepository(Context context) {
        this.mHelper = new VehicleDatabaseHelper(context);
    }

    public static VehiclesRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new VehiclesRepository(context);
        }
        return INSTANCE;
    }

    public List<VehiclesModel> getList() {
        List<VehiclesModel> list = new ArrayList<>();

        try {
            SQLiteDatabase db = this.mHelper.getReadableDatabase();

            String[] columns = {DatabaseConstants.VEHICLES.COLUMNS.ID,
                    DatabaseConstants.VEHICLES.COLUMNS.MODEL,
                    DatabaseConstants.VEHICLES.COLUMNS.YEAR,
                    DatabaseConstants.VEHICLES.COLUMNS.PRICE,
                    DatabaseConstants.VEHICLES.COLUMNS.IMAGE};

            Cursor cursor = db.query(DatabaseConstants.VEHICLES.TABLE_MODEL, columns, null, null, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {

                    @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.VEHICLES.COLUMNS.ID));
                    @SuppressLint("Range") String model = cursor.getString(cursor.getColumnIndex(DatabaseConstants.VEHICLES.COLUMNS.MODEL));
                    @SuppressLint("Range") int year = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.VEHICLES.COLUMNS.YEAR));
                    @SuppressLint("Range") BigDecimal price = BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(DatabaseConstants.VEHICLES.COLUMNS.PRICE)));
                    @SuppressLint("Range") String image = cursor.getString(cursor.getColumnIndex(DatabaseConstants.VEHICLES.COLUMNS.IMAGE));

                    list.add(new VehiclesModel(id, model, year, price, image));
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return list;
        } catch (Exception e) {
            return list;
        }
    }

    public VehiclesModel load(int id) {
        try {

            VehiclesModel vehicles = null;
            SQLiteDatabase db = this.mHelper.getReadableDatabase();

            String[] columns = {DatabaseConstants.VEHICLES.COLUMNS.ID,
                    DatabaseConstants.VEHICLES.COLUMNS.MODEL,
                    DatabaseConstants.VEHICLES.COLUMNS.YEAR,
                    DatabaseConstants.VEHICLES.COLUMNS.PRICE,
                    DatabaseConstants.VEHICLES.COLUMNS.IMAGE};

            String selection = DatabaseConstants.VEHICLES.COLUMNS.ID + " = ?";
            String[] selectionArgs = {String.valueOf(id)};

            Cursor cursor = db.query(DatabaseConstants.VEHICLES.TABLE_MODEL, columns, selection, selectionArgs, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                @SuppressLint("Range") String model = cursor.getString(cursor.getColumnIndex(DatabaseConstants.VEHICLES.COLUMNS.MODEL));
                @SuppressLint("Range") int year = cursor.getInt(cursor.getColumnIndex(DatabaseConstants.VEHICLES.COLUMNS.YEAR));
                @SuppressLint("Range") BigDecimal price = BigDecimal.valueOf(cursor.getDouble(cursor.getColumnIndex(DatabaseConstants.VEHICLES.COLUMNS.PRICE)));
                @SuppressLint("Range") String image = cursor.getString(cursor.getColumnIndex(DatabaseConstants.VEHICLES.COLUMNS.IMAGE));

                vehicles = new VehiclesModel(id, model, year, price, image);
            }

            if (cursor != null) {
                cursor.close();
            }
            return vehicles;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean insert(VehiclesModel vehicle) {
        try {
            SQLiteDatabase db = this.mHelper.getWritableDatabase();

            ContentValues values = new ContentValues(); // mapeia qual coluna recebe qual dado
            values.put(DatabaseConstants.VEHICLES.COLUMNS.MODEL, vehicle.getModel());
            values.put(DatabaseConstants.VEHICLES.COLUMNS.PRICE, String.valueOf(vehicle.getPrice()));
            values.put(DatabaseConstants.VEHICLES.COLUMNS.YEAR, vehicle.getYear());
            values.put(DatabaseConstants.VEHICLES.COLUMNS.IMAGE, vehicle.getImage());

            db.insert(DatabaseConstants.VEHICLES.TABLE_MODEL, null, values);
            db.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(VehiclesModel vehicle) {
        try {
            SQLiteDatabase db = this.mHelper.getWritableDatabase();

            ContentValues values = new ContentValues(); // mapeia qual coluna recebe qual dado
            values.put(DatabaseConstants.VEHICLES.COLUMNS.MODEL, vehicle.getModel());
            values.put(DatabaseConstants.VEHICLES.COLUMNS.PRICE, String.valueOf(vehicle.getPrice()));
            values.put(DatabaseConstants.VEHICLES.COLUMNS.YEAR, vehicle.getYear());
            values.put(DatabaseConstants.VEHICLES.COLUMNS.IMAGE, vehicle.getImage());

            String where = DatabaseConstants.VEHICLES.COLUMNS.ID + " = ?";
            String[] args = {String.valueOf(vehicle.getId())};

            db.update(DatabaseConstants.VEHICLES.TABLE_MODEL, values, where, args);
            db.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(int id) {
        try {
            SQLiteDatabase db = this.mHelper.getWritableDatabase();

            String where = DatabaseConstants.VEHICLES.COLUMNS.ID + " = ?";
            String[] args = {String.valueOf(id)};

            db.delete(DatabaseConstants.VEHICLES.TABLE_MODEL, where, args);

            db.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
