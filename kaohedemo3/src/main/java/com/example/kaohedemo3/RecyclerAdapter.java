package com.example.kaohedemo3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

@SuppressWarnings("all")
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {

    private View view;
    private Context context;
    private ArrayList<String> nameList, numberList, timeList;

    //构造函数
    public RecyclerAdapter(Context context, ArrayList<String> nameList, ArrayList<String> numberList, ArrayList<String> timeList) {
        this.context = context;
        this.nameList = nameList;
        this.numberList = numberList;
        this.timeList = timeList;
    }

    public RecyclerAdapter(Context context, String name, String number, String time) {
        this.context = context;
        nameList.add(name);
        numberList.add(number);
        timeList.add(time);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        //将数据和控件绑定
        holder.name.setText(nameList.get(position));
        holder.number.setText(numberList.get(position));
        holder.time.setText(timeList.get(position));
        if (position % 2 == 0) {
            holder.item.setBackgroundColor(context.getResources().getColor(R.color.red));
        }else {
            holder.item.setBackgroundColor(context.getResources().getColor(R.color.ching));
        }
        //点击拨号监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + numberList.get(holder.getAdapterPosition())));
                context.startActivity(intent);
            }
        });

        //添加长按删除联系人事件的监听
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("删除联系人？");

                //实现长按删除联系人条目的操作
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        nameList.remove(holder.getAdapterPosition());
                        notifyItemRemoved(position);
                        Toast.makeText(context, "联系人删除成功", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //取消删除
                    }
                });
                builder.create().show();
                return true;
            }
        });
    }

    /*
     * 控制创建item的条数
     * */
    @Override
    public int getItemCount() {
        return nameList.size();
    }

    /**
     * 作为主类的泛型
     * 构造方法：接收由布局生成的子View，并加载子View中的控件
     */
    class MyHolder extends RecyclerView.ViewHolder {

        LinearLayout item;
        TextView name, number, time;
        ImageView head;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);
            time = itemView.findViewById(R.id.time);
            head = itemView.findViewById(R.id.head);
        }
    }
}