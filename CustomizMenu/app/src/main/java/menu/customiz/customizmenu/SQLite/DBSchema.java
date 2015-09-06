package menu.customiz.customizmenu.SQLite;

import android.provider.BaseColumns;

/**
 * Created by ameyamore on 05/09/15.
 */
public final class DBSchema {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public DBSchema() {}

    /* Inner class that defines the table contents */
    public static abstract class DbEntry implements BaseColumns {
        public static final String TABLE_NAME = "Patient";
        public static final String COLUMN_NAME_ID = "patientId";
        public static final String COLUMN_NAME_GIVEN_NAME = "givenName";
        public static final String COLUMN_NAME_FAMILY_NAME = "familyName";
        public static final String COLUMN_NAME_DOB = "dob";
        public static final String COLUMN_NAME_ADDRESS = "address";
        public static final String COLUMN_NAME_PHONE = "phone";
    }
}
