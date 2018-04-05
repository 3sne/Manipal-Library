package com.example.mahe.manipallibrary;




import android.content.Context;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class booklistadapter extends RecyclerView.Adapter<booklistadapter.ViewHolder> {


    public interface ClickListener {
        void onClick(View view, int position);


    }
    int qty;
    ArrayList<Integer>   qt;
    private ArrayList<String> name;
    private ArrayList<String> aut;
    private Context context;
    private ClickListener onItemClickListener;
    public booklistadapter(ArrayList<String> nm, ArrayList<String> aut, ArrayList<Integer> qt, Context context) {
        name=nm;
        this.aut=aut;
        this.context = context;
        this.qt=qt;
    }

    public ClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(ClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.listfill,parent,false);

        return new ViewHolder(v);
    }

    //    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onClick(v,position);
            }
        };

        String n= name.get(position);
        String a=aut.get(position);
        int e=qt.get(position);
        holder.head.setText(n);
        holder.desc.setText(a);
        Log.d("toooi",String.valueOf(qty));
        if (e!=0) {

            holder.issue.setOnClickListener(listener);
        }else
        {
            holder.issue.setEnabled(false);
        }

    }


    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView head;
        public TextView desc;
        public Button issue;


        public ViewHolder(View itemView) {
            super(itemView);

            head=(TextView)itemView.findViewById(R.id.bookname);
            desc=(TextView)itemView.findViewById(R.id.author);
            issue=(Button)itemView.findViewById(R.id.issue);
        }
    }


}
