package com.example.control_listener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

@SuppressWarnings("all")
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {

    private View view;
    private Context context;
    private ArrayList<String> nameList,newsList,timeList;

    //构造函数
    public RecyclerAdapter(Context context, ArrayList<String> nameList, ArrayList<String> newsList, ArrayList<String> timeList) {
        this.context = context;
        this.nameList = nameList;
        this.newsList = newsList;
        this.timeList = timeList;
    }

    /*
    * 创建ViewHolder时的回调函数
    * 传入ViewGroup parent, int viewType
    * 返回MyHolder
    * MyHolder是list中每个item
    * */
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
        * public View inflate(int resource, @Nullable ViewGroup root, boolean attachToRoot)
        *   参数1：要加载的布局对应的资源的id
        *   参数2：为该布局的外部再嵌套一层父布局，如果不需要，则填null
        *   参数3：是否为加载的布局文件的最外层套一层root布局（是否为加载的布局添加一个root的外层容器）
        * */
        view= LayoutInflater.from(context).inflate(R.layout.recyclerview_list,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    /*
    * 绑定ViewHolder时的回调函数
    * 传入自定义内部类的 holder 和 int position
    * */
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        //将数据和控件绑定
        holder.name.setText(nameList.get(position));
        holder.news.setText(newsList.get(position));
        holder.time.setText(timeList.get(position));
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
     */
    class MyHolder extends RecyclerView.ViewHolder {

        TextView name, news, time;
        ImageView head;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            news=itemView.findViewById(R.id.news);
            time=itemView.findViewById(R.id.time);
            head=itemView.findViewById(R.id.head);
        }
    }
}