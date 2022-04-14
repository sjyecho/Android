package com.example.echo;

import android.annotation.SuppressLint;
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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder>{

    View view;
    private Context content;
    private ArrayList<String> nameList,numberList,timeList;

    /**
     *
     * @param content
     * @param nameList
     * @param numberList
     * @param timeList
     */
    public RecyclerAdapter(Context content, ArrayList<String> nameList, ArrayList<String> numberList, ArrayList<String> timeList) {
        this.content = content;
        this.nameList = nameList;
        this.numberList = numberList;
        this.timeList = timeList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(content).inflate(R.layout.item, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(nameList.get(position));
        holder.number.setText(numberList.get(position));
        holder.time.setText(timeList.get(position));
        if (holder.getAdapterPosition()/2==1){
            //holder.item.setBackgroundColor(content.getResources().getColor(R.color.blue));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+numberList.get(holder.getAdapterPosition())));
                content.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(content);
                builder.setTitle("删除");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        nameList.remove(holder.getAdapterPosition());
                        notifyItemRemoved(position);
                        Toast.makeText(content,"",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create().show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    /**
     * 作为主类的泛型
     * 构造方法：接收由布局生成的子View，并加载子View中的控件
     */
    class MyHolder extends RecyclerView.ViewHolder{

        //LinearLayout item;
        TextView name,number,time;
        ImageView head;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            //item=itemView.findViewById(R.id.item);
            name=itemView.findViewById(R.id.name);
            number=itemView.findViewById(R.id.number);
            time=itemView.findViewById(R.id.time);
            head=itemView.findViewById(R.id.head);
        }
    }
}
