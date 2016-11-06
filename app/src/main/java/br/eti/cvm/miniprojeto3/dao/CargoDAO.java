package br.eti.cvm.miniprojeto3.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.eti.cvm.miniprojeto3.dbutil.CargoDbHelper;
import br.eti.cvm.miniprojeto3.entity.Cargo;

public class CargoDAO {
    private CargoDbHelper mDbHelper;

    public CargoDAO(Context context) {
        mDbHelper = new CargoDbHelper(context);
    }

    public Long insert(Cargo cargo) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Long newRowId = db.insert(Cargo.TABLE, null, cargo.getContentValues());
        db.close();

        return newRowId;
    }

    public Cargo findById(Long id) {
        String[] projection = { Cargo._ID, Cargo.COLUMN_NOME };

        String selection = Cargo._ID + " = ?";
        String[] selectionArgs = {id.toString()};

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                Cargo.TABLE,    // The table to query
                projection,     // The columns to return
                selection,      // The columns for the WHERE clause
                selectionArgs,  // The values for the WHERE clause
                null,           // don't group the rows
                null,           // don't filter by row groups
                null       // The sort order
        );

        Cargo cargo = new Cargo();
        cargo.setId(cursor.getLong(cursor.getColumnIndexOrThrow(Cargo._ID)));
        cargo.setNome(cursor.getString(cursor.getColumnIndexOrThrow(Cargo.COLUMN_NOME)));

        db.close();

        return cargo;
    }

    public void delete(Cargo cargo) {
        String selection = Cargo._ID + " = ?";
        String[] selectionArgs = { cargo.getId().toString() };

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        db.delete(Cargo.TABLE, selection, selectionArgs);
        db.close();
    }

    public int update(Cargo cargo) {
        ContentValues values = new ContentValues();
        values.put(Cargo.COLUMN_NOME, cargo.getNome());

        String selection = Cargo._ID + " = ?";
        String[] selectionArgs = { cargo.getId().toString() };

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        int count = db.update(
            Cargo.TABLE,
            values,
            selection,
            selectionArgs);
        db.close();

        return count;
    }

    public List<Cargo> findAll() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + Cargo.TABLE, null);

        List<Cargo> listaCargos = new ArrayList<Cargo>();
        if(cursor.moveToFirst()) {

            while (cursor.isAfterLast() == false) {
                Cargo cargo = new Cargo();
                cargo.setId(cursor.getLong(cursor.getColumnIndex(Cargo._ID)));
                cargo.setNome(cursor.getString(cursor.getColumnIndex(Cargo.COLUMN_NOME)));

                listaCargos.add(cargo);
                cursor.moveToNext();
            }
        }
        db.close();

        return listaCargos;
    }
}
