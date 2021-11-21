package com.example.garuda20app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "yes";
    private static final String TITLE_COL = "Title";
    private static final String PAY_COL = "Pay";
    private static final String LOCATION_COL = "Location";
    private static final String TIME_COL = "Time";
    private static final String DESCRIPTION_COL = "Description";

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use for locating paths to the the database
     * @param name    of the database file, or null for an in-memory database
     * @param factory to use for creating cursor objects, or null for the default
     * @param version number of the database (starting at 1); if the database is older,
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database
     */
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TABLE_NAME
                +  " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE_COL + " TITLE, "
                + PAY_COL + " PAY, "
                + LOCATION_COL + " LOCATION, "
                + DESCRIPTION_COL + " DESCRIPTION, "
                + TIME_COL + " TIME) ";
        db.execSQL(createTableStatement);
    }

    public boolean addDatabaseEntry(Entry Entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TITLE_COL, Entry.getTitle());

        cv.put(PAY_COL, Entry.getpay());
        cv.put(LOCATION_COL, Entry.getLocation());
        cv.put(TIME_COL, Entry.getTime());
        cv.put(DESCRIPTION_COL, Entry.getDescription());

        long insert = db.insert(TITLE_COL, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Entry getEntry(String queryDate) throws Exception {
        String queryString = "SELECT " + TITLE_COL + ", " + PAY_COL + ", " + LOCATION_COL + ", " + DESCRIPTION_COL + ", " + TIME_COL + " FROM " + TABLE_NAME + " WHERE "
                + "='" + queryDate + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        // Test if database has entries
        if (!(cursor.moveToFirst())) {
            throw new Exception();
        }

        String title = cursor.getString(1);
        String pay = cursor.getString(2);
        String location = cursor.getString(3);
        String description = cursor.getString(4);
        String time = cursor.getString(5);
        Entry entry = new Entry(title, pay, location, time, description);
        cursor.close();
        db.close();
        return entry;
    }

    public SQLiteDatabase getDatabase() {
        return (this.getReadableDatabase());
    }

}