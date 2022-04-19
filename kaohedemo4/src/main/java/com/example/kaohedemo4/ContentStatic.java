package com.example.kaohedemo4;

import android.net.Uri;
import android.provider.BaseColumns;

public class ContentStatic {

    //定义该ContentProvider的Authority
    public static final String AUTHORITY="com.example.kaohedemo4.contentproviderdemo";

    //定义一个静态内部类
    public static final class Word implements BaseColumns {
        //定义Content所允许操作的三个数据列
        public static final String _ID="_id";
        public static final String NAME="name";
        public static final String PHONENUMBER="phonenumber";
        //定义该Content提供服务的Uri
        public static final Uri CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/name");

    }
}
