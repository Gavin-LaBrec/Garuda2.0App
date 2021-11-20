package com.example.garuda20app;

import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelpder extends SQLLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "yes";
    private static final String TITLE_COL = "Title";
    private static final String PAY_COL = "Pay";
    private static final String LOCATION_COL = "Location";
    private static final String TIME_COL = "Time";
    private static final String DESCRIPTION_COL = "Description";


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Entries.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TABLE_NAME
                + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE_COL + " TITLE, "
                + PAY_COL + " PAY, "
                + LOCATION_COL + " LOCATION, "
                + DESCRIPTION_COL + " DESCRIPTION, "
                + TIME_COL + " TIME) ";
        db.execSQL(createTableStatement);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean addDatabaseEntry(Entry Entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DATE_CREATED, Entry.getDate());

        cv.put(COLUMN_IMPROVEMENT, Entry.getImproveText());
        cv.put(COLUMN_GRATITUDE, Entry.getGratitudeText());

        long insert = db.insert(ENTRIES_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Entry getEntry(String queryDate) throws Exception {
        String queryString = "SELECT " + COLUMN_DATE_CREATED + ", " + COLUMN_IMPROVEMENT + ", " + COLUMN_GRATITUDE + " FROM " + ENTRIES_TABLE + " WHERE "
                + COLUMN_DATE_CREATED + "='" + queryDate + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        // Test if database has entries
        if (!(cursor.moveToFirst())) {
            throw new Exception();
        }

        String improveText = cursor.getString(1);
        String gratitudeText = cursor.getString(2);
        Entry entry = new Entry(queryDate, improveText, gratitudeText);
        cursor.close();
        db.close();
        return entry;
    }

    public SQLiteDatabase getDatabase() {
        return (this.getReadableDatabase());
    }
}