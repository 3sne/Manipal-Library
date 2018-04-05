package com.example.mahe.manipallibrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by asus on 02-04-2018.
 */


public class allbookadapter extends RecyclerView.Adapter<allbookadapter.ViewHolder> {


    public interface ClickListener {
        void onClick(View view, int position);


    }
    private ArrayList<String> name;
    private ArrayList<String> aut;
    private ArrayList<String>  qty;
    private Context context;
    private ClickListener onItemClickListener;
    public allbookadapter(ArrayList<String> nm, ArrayList<String> uid,ArrayList<String> bname, Context context) {
        name=nm;
        aut=uid;
        qty=bname;
        this.context = context;
    }

    public ClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(ClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.allbooks,parent,false);

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
        String b=qty.get(position);
        holder.head.setText(n);
        holder.desc.setText(a);
        holder.ret.setText(b);
        //holder.ret.setOnClickListener(listener);

    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView head;
        public TextView desc;

        public TextView ret;

        public ViewHolder(View itemView) {
            super(itemView);

            head=(TextView)itemView.findViewById(R.id.libbname);
            desc=(TextView)itemView.findViewById(R.id.libat);
            ret=(TextView) itemView.findViewById(R.id.libqty);

        }
    }


}

