package com.example.kaohedemo_4_1;

import android.net.Uri;
import android.provider.BaseColumns;

public class StaticUri {

    public static final String AUTHORITY = "com.example.kaohedemo_4_1.contentprovider";

    public static final class TableColumns implements BaseColumns {
        public static final String CONTACTS_ID = "_id";
        public static final String COLLECTIONS_ID = "_id";
        public static final String CONTACTS_NAME = "name";
        public static final String COLLECTIONS_NAME = "name";
        public static final String CONTACTS_PHONE = "phone";
        public static final String COLLECTIONS_PHONE = "phone";
//        public static final String BOOK_JG = "jg";

//        public static final String TYPE_ID = "_id";
//        public static final String TYPE_SJMC = "sjmc";
//        public static final String TYPE_LBMC = "rksj";

        public static final Uri CONTACTS_QUERY = Uri.parse("content://" + AUTHORITY + "/query");
        public static final Uri CONTACTS_INSERT = Uri.parse("content://" + AUTHORITY + "/insert");
        public static final Uri CONTACTS_DELETE = Uri.parse("content://" + AUTHORITY + "/delete");
        public static final Uri CONTACTS_UPDATE = Uri.parse("content://" + AUTHORITY + "/update");

        public static final Uri COLLECTIONS_QUERY = Uri.parse("content://" + AUTHORITY + "/collection_query");
        public static final Uri COLLECTIONS_INSERT = Uri.parse("content://" + AUTHORITY + "/collection_insert");
        public static final Uri COLLECTIONS_DELETE = Uri.parse("content://" + AUTHORITY + "/collection_delete");
        public static final Uri COLLECTIONS_UPDATE = Uri.parse("content://" + AUTHORITY + "/collection_update");

//        public static final Uri TYPE_QUERY = Uri.parse("content://" + AUTHORITY + "typequery");
//        public static final Uri TYPE_INSERT = Uri.parse("content://" + AUTHORITY + "typeinsert");
//        public static final Uri TYPE_DELETE = Uri.parse("content://" + AUTHORITY + "typedelete");
//        public static final Uri TYPE_UPDATE = Uri.parse("content://" + AUTHORITY + "typeupdate");

    }
}
