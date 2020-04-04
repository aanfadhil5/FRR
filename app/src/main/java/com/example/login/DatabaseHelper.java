package com.example.login;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.renderscript.Sampler;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "frr.db";
    public static final int DB_VERSION = 1;


        public class user {
            public final class EntryUser implements BaseColumns {
                public static final String TABLE_NAME = "listuser";
                public static final String COLUMN_USERNAME = "username";
                public static final String COLUMN_PASSWORD = "password";
            }
        }
            public DatabaseHelper(Context context) { super(context, DATABASE_NAME, null, DB_VERSION); }

            @Override
            public void onCreate(SQLiteDatabase db) {
                final String CREATE_TABLE_USER = "CREATE TABLE " +
                    user.EntryUser.TABLE_NAME + " (" +
                    user.EntryUser._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    user.EntryUser.COLUMN_USERNAME + " TEXT NOT NULL, " +
                    user.EntryUser.COLUMN_PASSWORD + " TEXT NOT NULL" + ");";
                final String CREATE_TABLE_KERJAAN = "CREATE TABLE " +
                    KerjaanContract.EntryKerjaan.TABLE_NAME + " (" +
                    KerjaanContract.EntryKerjaan._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    KerjaanContract.EntryKerjaan.COLUMN_PELANGGAN + " TEXT NOT NULL, " +
                    KerjaanContract.EntryKerjaan.COLUMN_NOPOL + " TEXT NOT NULL, " +
                    KerjaanContract.EntryKerjaan.COLUMN_MOTOR + " TEXT NOT NULL, " +
                    KerjaanContract.EntryKerjaan.COLUMN_KERUSAKAN + " TEXT NOT NULL," +
                    KerjaanContract.EntryKerjaan.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ");";

                db.execSQL(CREATE_TABLE_USER);
                db.execSQL(CREATE_TABLE_KERJAAN);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + user.EntryUser.TABLE_NAME);
                db.execSQL("DROP TABLE IF EXISTS " + KerjaanContract.EntryKerjaan.TABLE_NAME);
                onCreate(db);
            }
    public long addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.user.EntryUser.COLUMN_USERNAME, username);
        values.put(user.EntryUser.COLUMN_PASSWORD, password);
        long hasil = db.insert(DatabaseHelper.user.EntryUser.TABLE_NAME, null,values);
        return hasil;
    }

    public boolean checkUser(String username, String password) {
                String[] columns = {user.EntryUser._ID};
                SQLiteDatabase db = getReadableDatabase();
                String selection = user.EntryUser.COLUMN_USERNAME + "=?" + " and " + user.EntryUser.COLUMN_PASSWORD + "=?";
                String[] selectionArgs = {username, password};
                Cursor cursor = db.query(user.EntryUser.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
                int count = cursor.getCount();
                cursor.close();

                if (count > 0)
                    return true;
                else
                    return false;
            }
    }