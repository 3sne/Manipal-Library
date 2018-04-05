package com.example.mahe.manipallibrary;

/**
 * Created by asus on 02-04-2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class statusadapter extends RecyclerView.Adapter<statusadapter.ViewHolder> {


    public interface ClickListener {
        void onClick(View view, int position);


    }
    private ArrayList<String> name;
    private ArrayList<String> userid;
    private ArrayList<String>  bname;
    private Context context;
    private ClickListener onItemClickListener;
    public statusadapter(ArrayList<String> nm, ArrayList<String> uid,ArrayList<String> bname, Context context) {
        name=nm;
        userid=uid;
        this.bname=bname;
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

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recfill,parent,false);

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
        String a=userid.get(position);
        String b=bname.get(position);
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

            head=(TextView)itemView.findViewById(R.id.stname);
            desc=(TextView)itemView.findViewById(R.id.stuid);
            ret=(TextView) itemView.findViewById(R.id.stbname);

        }
    }


}
