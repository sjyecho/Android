package com.example.contentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public final class Words {
    //定义该ContentProvider的Authority
    public static final String AUTHORITY="com.example.contentprovider.dictprovider";
    //定义一个静态内部类
    public static final class Word implements BaseColumns{
        //定义Content所允许操作的三个数据列
        public static final String _ID="_id";
        public static final String WORD="word";
        public static final String DETAIL="detail";
        //定义该Content提供服务的两个Uri
        public static final Uri DICT_CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/words");
        public static final Uri WORD_CONTENT_URI=Uri.parse("content://"+AUTHORITY+"/word");
    }
}
