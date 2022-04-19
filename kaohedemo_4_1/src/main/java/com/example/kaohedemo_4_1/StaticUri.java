package com.example.kaohedemo_4_1;

import android.net.Uri;
import android.provider.BaseColumns;

public class StaticUri {

    public static final String AUTHORITY = "com.example.kaohedemo_4_1.contentprovider";

    public static final class TableColumns implements BaseColumns {
        public static final String BOOK_ID = "_id";
        public static final String BOOK_SJMC = "sjmc";
        public static final String BOOK_RKSJ = "rksj";
        public static final String BOOK_JG = "jg";

        public static final String TYPE_ID = "_id";
        public static final String TYPE_SJMC = "sjmc";
        public static final String TYPE_LBMC = "rksj";

        public static final Uri BOOK_QUERY = Uri.parse("content://" + AUTHORITY + "/bookquery");
        public static final Uri BOOK_INSERT = Uri.parse("content://" + AUTHORITY + "/bookinsert");
        public static final Uri BOOK_DELETE = Uri.parse("content://" + AUTHORITY + "/bookdelete");
        public static final Uri BOOK_UPDATE = Uri.parse("content://" + AUTHORITY + "/bookupdate");

        public static final Uri TYPE_QUERY = Uri.parse("content://" + AUTHORITY + "typequery");
        public static final Uri TYPE_INSERT = Uri.parse("content://" + AUTHORITY + "typeinsert");
        public static final Uri TYPE_DELETE = Uri.parse("content://" + AUTHORITY + "typedelete");
        public static final Uri TYPE_UPDATE = Uri.parse("content://" + AUTHORITY + "typeupdate");

    }
}
