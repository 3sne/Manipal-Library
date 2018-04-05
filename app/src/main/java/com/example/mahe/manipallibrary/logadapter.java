package com.example.mahe.manipallibrary;

/**
 * Created by asus on 04-04-2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class logadapter extends RecyclerView.Adapter<logadapter.ViewHolder> {


    public interface ClickListener {
        void onClick(View view, int position);


    }
    private ArrayList<String> uid;
    private ArrayList<String> idat;
    private ArrayList<String> rdat;
    private ArrayList<String> bid;
    private Context context;
    private ClickListener onItemClickListener;
    public logadapter(ArrayList<String> uid, ArrayList<String> idat,ArrayList<String> rdat,ArrayList<String> bid, Context context) {
        this.uid=uid;
        this.idat=idat;
        this.rdat=rdat;
        this.bid=bid;
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

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.loglist,parent,false);

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


        String u= uid.get(position);
        String i=idat.get(position);
        String r=rdat.get(position);
        String b=bid.get(position);

        holder.u.setText(u);
        holder.i.setText(i);
        holder.r.setText(r);
        holder.b.setText(b);

    }

    @Override
    public int getItemCount() {
        return uid.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView u;
        public TextView i;
        public TextView r;
        public TextView b;

        public Button ret;

        public ViewHolder(View itemView) {
            super(itemView);

            u=(TextView)itemView.findViewById(R.id.user);
            i=(TextView)itemView.findViewById(R.id.issue_date);
            r=(TextView)itemView.findViewById(R.id.return_date);
            b=(TextView)itemView.findViewById(R.id.book);
        }
    }


}
