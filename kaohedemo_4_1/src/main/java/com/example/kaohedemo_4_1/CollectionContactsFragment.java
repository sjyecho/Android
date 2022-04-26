package com.example.kaohedemo_4_1;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CollectionContactsFragment extends Fragment {

    ContentResolver contentResolver;
    Cursor cursor = null;
    ListView listView;
    AlertDialog.Builder builder;
    BaseAdapter ba;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contacts = inflater.inflate(R.layout.collection_contacts_fragment, container, false);
        contentResolver = contacts.getContext().getContentResolver();
        cursor = contentResolver.query(StaticUri.TableColumns.COLLECTIONS_QUERY, null, "name like ? or phone like ?", new String[]{"%%", "%%"}, null);
        ArrayList<Map<String, String>> list = converCursorToList(cursor);
//        SimpleAdapter simpleAdapter = new SimpleAdapter(
//                getContext(),
//                list,
//                R.layout.item_collections,
//                new String[]{"name", "phone"},
//                new int[]{R.id.name, R.id.phone});
        listView = contacts.findViewById(R.id.listviewfragment_c);

        //创建一个BaseAdapter对象
        ba = new BaseAdapter() {
            @Override
            public int getCount() {
                //要绑定的条目的数目
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                //根据一个索引（位置）获得该位置的对象
                //返回指定位置的文本
                //return getResources().getText(textIds[position]);
                return list.get(position);
            }

            @Override
            public long getItemId(int position) {
                //获取条目的Id
                return position;
            }

            /**
             * 重写该方法，该方法返回的View将作为GridView的每个格子
             * @param position
             * @param view
             * @param viewGroup
             * @return
             */
            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {
                LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.item_collections, null);
                TextView nameTextView = layout.findViewById(R.id.name);
                TextView phoneTextView = layout.findViewById(R.id.phone);
                nameTextView.setText(list.get(position).get("name"));
                phoneTextView.setText(list.get(position).get("phone"));
                Button contact_collect = layout.findViewById(R.id.contact_collect);
                //取消收藏
                contact_collect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ContentValues values=new ContentValues();
                        String name = list.get(position).get("name");
                        String phone = list.get(position).get("phone");
                        values.put(StaticUri.TableColumns.COLLECTIONS_NAME, name);
                        values.put(StaticUri.TableColumns.COLLECTIONS_PHONE, phone);
                        contentResolver.delete(StaticUri.TableColumns.COLLECTIONS_DELETE, "name = ?", new String[]{name});
                    }
                });
                return layout;
            }
        };
        listView.setAdapter(ba);
        return contacts;
    }

    private ArrayList<Map<String, String>> converCursorToList(Cursor cursor) {
        ArrayList<Map<String, String>> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<>();
            map.put(StaticUri.TableColumns.COLLECTIONS_NAME, cursor.getString(1));
            map.put(StaticUri.TableColumns.COLLECTIONS_PHONE, cursor.getString(2));
            //map.put(StaticUri.TableColumns.BOOK_JG,cursor.getString(3));
            result.add(map);
        }
        return result;
    }
}
