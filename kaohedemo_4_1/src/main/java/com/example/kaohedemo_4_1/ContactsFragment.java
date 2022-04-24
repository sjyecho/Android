package com.example.kaohedemo_4_1;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
public class ContactsFragment extends Fragment {

    String TAG = "ECHO.SJY";
    ContentResolver contentResolver;
    AlertDialog.Builder builder;
    AlertDialog.Builder builder2;
    AlertDialog.Builder builder3;
    Cursor cursor = null;
    ListView listView;
    SimpleAdapter simpleAdapter = null;
    MyAsyncTask myAsyncTask;


    class MyAsyncTask extends AsyncTask<Cursor, Integer, SimpleAdapter> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "----------onPreExecute()方法执行----------");
        }

        @Override
        protected SimpleAdapter doInBackground(Cursor... cursors) {
            Log.d(TAG, "----------doInBackground()执行,对数据进行操作，初始化及封装----------");
            ArrayList<Map<String, String>> list = converCursorToList(cursors[0]);
            int count = 0;
            publishProgress(count);
            SimpleAdapter simpleAdapter = new SimpleAdapter(
                    getContext(),
                    list,
                    R.layout.item,
                    new String[]{"name", "phone"},
                    new int[]{R.id.name, R.id.phone});
            return simpleAdapter;
        }

        @Override
        protected void onPostExecute(SimpleAdapter simpleAdapter) {
            Log.d(TAG, "----------onPostExecute()执行,对UI进行操作，将数据添加至组件----------");
            super.onPostExecute(simpleAdapter);
            listView.setAdapter(simpleAdapter);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d(TAG, "----------onProgressUpdate()执行,用于显示进度----------");
            AlertDialog.Builder builder4 = new AlertDialog.Builder(getContext());
            builder4.setTitle("正在进行数据初始化操作，请稍后......");
            builder4.create().show();
//            builder4.setPositiveButton("暂停加载数据", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    myAsyncTask.cancel(true);
//                }
//            });
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled() {
            Log.d(TAG, "----------onCancelled()执行----------");
            super.onCancelled();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contacts = inflater.inflate(R.layout.contacts_fragment, container, false);
        contentResolver = contacts.getContext().getContentResolver();
        cursor = contentResolver.query(StaticUri.TableColumns.CONTACTS_QUERY, null, "name like ? or phone like ?", new String[]{"%%", "%%"}, null);
        //        ArrayList<Map<String, String>> list = converCursorToList(cursor);
//        SimpleAdapter simpleAdapter = new SimpleAdapter(
//                getContext(),
//                list,
//                R.layout.item,
//                new String[]{"name", "phone"},
//                new int[]{R.id.name, R.id.phone});
        listView = contacts.findViewById(R.id.listviewfragment);
//        listView.setAdapter(simpleAdapter);
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(cursor);

        builder = new AlertDialog.Builder(getContext());
        builder2 = new AlertDialog.Builder(getContext());
        builder3 = new AlertDialog.Builder(getContext());

        //点击联系人Item，弹出修改界面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

//                Intent intent = new Intent(getContext(), MyFirstReceiver.class);
//                intent.putExtra("id", "1");
//                getActivity().sendBroadcast(intent);

                TableLayout updateForm = (TableLayout) getLayoutInflater().inflate(R.layout.update_layout, null);
                builder.setView(updateForm);
                builder.setTitle("联系人信息");

                int viewIndex = listView.indexOfChild(view);
                //String index = String.valueOf(viewIndex);
                //System.out.println(index + "-------------------------------------------");

                EditText nameText = (EditText) updateForm.findViewById(R.id.old_name);
                EditText phoneText = (EditText) updateForm.findViewById(R.id.old_phone);

                String name_old = ((TextView) view.findViewById(R.id.name)).getText().toString();//要显示的旧值
                String phone_old = ((TextView) view.findViewById(R.id.phone)).getText().toString();//要显示的旧值

                phoneText.setText(phone_old);
                nameText.setText(name_old);

                builder.setPositiveButton("确认修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        ContentValues values = new ContentValues();

                        String name_new = ((EditText) updateForm.findViewById(R.id.old_name)).getText().toString();
                        String phone_new = ((EditText) updateForm.findViewById(R.id.old_phone)).getText().toString();

                        values.put(StaticUri.TableColumns.CONTACTS_NAME, name_new);
                        values.put(StaticUri.TableColumns.CONTACTS_PHONE, phone_new);

                        contentResolver.update(StaticUri.TableColumns.CONTACTS_UPDATE, values, "name = ?", new String[]{name_old});
                    }
                });
                builder.setNegativeButton("删除此联系人", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name_old = ((TextView) view.findViewById(R.id.name)).getText().toString();
                        contentResolver.delete(StaticUri.TableColumns.CONTACTS_DELETE, "name = ?", new String[]{name_old});
                        simpleAdapter.notifyDataSetChanged();
                        listView.setAdapter(simpleAdapter);
                    }
                });
                //simpleAdapter.notifyDataSetChanged();
                //listView.setAdapter(simpleAdapter);
                builder.create().show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                TableLayout insertForm = (TableLayout) getLayoutInflater().inflate(R.layout.update_layout, null);
                builder2.setView(insertForm);
                builder2.setTitle("新增联系人");
                Button set_music = insertForm.findViewById(R.id.set_music);
                Button play_music = insertForm.findViewById(R.id.play_music);
                play_music.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), R.raw.bomb);
                        mediaPlayer.start();

                    }
                });
                set_music.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        builder3.setTitle("选择你的音乐");
                        builder3.setView(R.layout.build3);
                        builder3.create().show();
                        builder3.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //
                            }
                        });
                    }
                });

                builder2.setPositiveButton("确认添加", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = ((EditText) insertForm.findViewById(R.id.old_name)).getText().toString();
                        String phone = ((EditText) insertForm.findViewById(R.id.old_phone)).getText().toString();
                        //System.out.println(name+"="+phone);
                        //添加操作
                        ContentValues values = new ContentValues();
                        values.put(StaticUri.TableColumns.CONTACTS_NAME, name);
                        values.put(StaticUri.TableColumns.CONTACTS_PHONE, phone);
                        contentResolver.insert(StaticUri.TableColumns.CONTACTS_INSERT, values);
                    }
                });
                builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //空操作
                    }
                });
                builder2.create().show();
                return true;
            }
        });
        return contacts;
    }

    private ArrayList<Map<String, String>> converCursorToList(Cursor cursor) {
        ArrayList<Map<String, String>> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<>();
            map.put(StaticUri.TableColumns.CONTACTS_NAME, cursor.getString(1));
            map.put(StaticUri.TableColumns.CONTACTS_PHONE, cursor.getString(2));
            //map.put(StaticUri.TableColumns.BOOK_JG,cursor.getString(3));
            result.add(map);
        }
        return result;
    }
}
