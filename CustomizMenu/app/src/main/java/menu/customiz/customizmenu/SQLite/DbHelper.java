package menu.customiz.customizmenu.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ameyamore on 05/09/15.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Patient.db";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DBSchema.DbEntry.TABLE_NAME;
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + DBSchema.DbEntry.TABLE_NAME +"(" +
            DBSchema.DbEntry.COLUMN_NAME_ID + " TEXT, " +
            DBSchema.DbEntry.COLUMN_NAME_GIVEN_NAME + " TEXT, " +
            DBSchema.DbEntry.COLUMN_NAME_FAMILY_NAME + " TEXT, " +
            DBSchema.DbEntry.COLUMN_NAME_ADDRESS + " TEXT, " +
            DBSchema.DbEntry.COLUMN_NAME_DOB + " TEXT, " +
            DBSchema.DbEntry.COLUMN_NAME_PHONE + " TEXT, " +
            DBSchema.DbEntry.COLUMN_NAME_GENDER + " TEXT)";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);

    }
}
