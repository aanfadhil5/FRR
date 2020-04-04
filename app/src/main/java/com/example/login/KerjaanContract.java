package com.example.login;

import android.provider.BaseColumns;

public class KerjaanContract {
    private KerjaanContract(){}

        public final class EntryKerjaan implements BaseColumns {
            public static final String TABLE_NAME = "listkerjaan";
            public static final String COLUMN_PELANGGAN = "pelanggan";
            public static final String COLUMN_NOPOL = "nopol";
            public static final String COLUMN_MOTOR = "motor";
            public static final String COLUMN_KERUSAKAN = "kerusakan";
            public static final String COLUMN_TIMESTAMP = "timestamp";
        }
    }

