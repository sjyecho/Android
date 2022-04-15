package com.example.contentprovider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

@SuppressLint("Range")
public class ContentProviderTest extends Activity {

    private final static int EXTERNAL_STORAGE_REQUEST_CODE = 1;

    //用于运行时权限申请
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case EXTERNAL_STORAGE_REQUEST_CODE:
                //用户点击权限对话框,确认后,会回调到这里,进行操作
                Toast.makeText(ContentProviderTest.this, "已完成授权,请再次添加", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /*private boolean hasAllPermissionGranted(int[] grantResults) {
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contentprovidertest);

        Button search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //定义两个List来封装系统的联系人信息、指定联系人的电话号码、email等详情
                final ArrayList<String> names = new ArrayList<>();
                final ArrayList<ArrayList<String>> details = new ArrayList<>();
                //使用ContentResolver查找联系人数据
                //ContactsContract.Contacts.CONTENT_URI : 用于管理联系人的Uri
                Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
                //遍历查询结果,获取系统中的所有联系人
                while (cursor.moveToNext()) {
                    //获取联系人ID
                    String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    //获取联系人的名字
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    names.add(name);
                    //使用ContentResolver查找联系人的电话号码
                    Cursor phones = getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + contactId,
                            null,
                            null);
                    ArrayList<String> detail = new ArrayList<>();
                    //遍历查询结果,获取该联系人的多个电话号码
                    while (phones.moveToNext()) {
                        //获取查询结果中电话号码列中的数据
                        String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        detail.add("电话号码:" + phoneNumber);
                    }
                    phones.close();

                    //使用ContentResolver查找联系人的Email地址
                    Cursor emails = getContentResolver().query(
                            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID + "=" + contactId,
                            null,
                            null);
                    while (emails.moveToNext()) {
                        //获取查询结果中的Email地址列中的数据
                        String emailAddress = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        detail.add("邮件地址:" + emailAddress);
                    }
                    emails.close();
                    details.add(detail);
                }
                cursor.close();

                //加载result.xml界面布局代表的视图
                View resultDialog = getLayoutInflater().inflate(R.layout.result,null);
                //获取resultDialog中ID为list的ExpandableListView
                ExpandableListView list=resultDialog.findViewById(R.id.list);
                //创建一个ExpandableListAdapter对象
                ExpandableListAdapter adapter=new BaseExpandableListAdapter() {

                    /**
                     * 分组个数
                     * @return
                     */
                    @Override
                    public int getGroupCount() {
                        return names.size();
                    }

                    /**
                     * 分组中子选项的个数
                     * @param i groupPosition
                     * @return
                     */
                    @Override
                    public int getChildrenCount(int i) {
                        return details.get(i).size();
                    }

                    /**
                     * 获取指定分组数据
                     * @param i groupPosition
                     * @return
                     */
                    @Override
                    public Object getGroup(int i) {
                        return names.get(i);
                    }

                    /**
                     * 获取指定组的指定子列表项处的子列表项数据
                     * @param i groupPosition
                     * @param i1 childPosition
                     * @return
                     */
                    @Override
                    public Object getChild(int i, int i1) {
                        return details.get(i).get(i1);
                    }

                    /**
                     * 获取指定分组的ID
                     * @param i groupPosition
                     * @return
                     */
                    @Override
                    public long getGroupId(int i) {
                        return i;
                    }

                    /**
                     * 获取指定组的指定子选项的ID
                     * @param i groupPosition
                     * @param i1 childPosition
                     * @return
                     */
                    @Override
                    public long getChildId(int i, int i1) {
                        return i1;
                    }

                    /**
                     * 父组和子选项是否有稳定的ID,就是说底层数据的改变会不会影响到他们
                     * @return
                     */
                    @Override
                    public boolean hasStableIds() {
                        return true;
                    }

                    private TextView getTextView(){
                        AbsListView.LayoutParams lp=new AbsListView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,64);
                        TextView textView=new TextView(ContentProviderTest.this);
                        textView.setLayoutParams(lp);
                        textView.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER);
                        textView.setPadding(36,0,0,0);
                        textView.setTextSize(20);
                        return textView;
                    }
                    /**
                     * 该方法决定每个组选项的外观(父布局)
                     * @param i groupPosition
                     * @param b isExpanded
                     * @param view convertView
                     * @param viewGroup parent
                     * @return 每个组选项外观
                     */
                    @Override
                    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
                        TextView textView=getTextView();
                        textView.setText(getGroup(i).toString());
                        textView.setBackgroundColor(getResources().getColor(R.color.c2));
                        return textView;
                    }
                    /**
                     * 该方法决定每个子选项的外观(子布局)
                     * @param i groupPosition
                     * @param i1 childPosition
                     * @param b isLastChild
                     * @param view convertView
                     * @param viewGroup parent
                     * @return 每个子选项的外观
                     */
                    @Override
                    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
                        TextView textView = getTextView();
                        textView.setText(getChild(i,i1).toString());
                        textView.setBackgroundColor(getResources().getColor(R.color.c8));
                        return textView;
                    }

                    @Override
                    public boolean isChildSelectable(int i, int i1) {
                        return true;
                    }
                };
                //为ExpandableListView设置Adapter对象
                list.setAdapter(adapter);
                //使用对话框来显示查询结果
                new AlertDialog.Builder(ContentProviderTest.this).setView(resultDialog).setPositiveButton("确定",null).show();
            }
        });

        Button add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(ContentProviderTest.this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS}, EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //获取程序界面中的三个文本框
                    String name = ((EditText) findViewById(R.id.name)).getText().toString();
                    String phone = ((EditText) findViewById(R.id.phone)).getText().toString();
                    String email = ((EditText) findViewById(R.id.email)).getText().toString();
                    //创建一个空的ContentValues
                    ContentValues values = new ContentValues();
                    //向RawContacts.CONTENT_URI执行一个空值插入
                    //目的是获取系统返回的rawContactId
                    Uri rawContactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
                    long rawContactId = ContentUris.parseId(rawContactUri);
                    values.clear();
                    values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
                    //设置内容类型
                    values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
                    //设置联系人名字
                    values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
                    //向联系人Uri添加联系人名字
                    getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
                    values.clear();
                    values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
                    values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                    //设置联系人的电话号码
                    values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phone);
                    //设置电话类型
                    values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
                    //向联系人电话号码Uri添加电话号码
                    getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
                    values.clear();
                    values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
                    values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
                    //设置联系人的Email地址
                    values.put(ContactsContract.CommonDataKinds.Email.DATA, email);
                    //设置该电子邮件的类型
                    values.put(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK);
                    //向联系人Email Uri添加Email数据
                    getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);

                    /*
                        Toast是一个包含给用户快速显示消息的视图
                        当视图显示给用户时,为浮动视图,且不会获得焦点
                    */
                    ((EditText) findViewById(R.id.name)).setText("");
                    ((EditText) findViewById(R.id.phone)).setText("");
                    ((EditText) findViewById(R.id.email)).setText("");
                    Toast.makeText(ContentProviderTest.this, "联系人数据添加成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
