package com.example.mahe.manipallibrary;




import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class issueadapter extends RecyclerView.Adapter<issueadapter.ViewHolder> {


    public interface ClickListener {
        void onClick(View view, int position);


    }
    private ArrayList<String> name;
    private ArrayList<String> aut;
    private Context context;
    private ClickListener onItemClickListener;
    public issueadapter(ArrayList<String> nm, ArrayList<String> aut, Context context) {
        name=nm;
        this.aut=aut;
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

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.issuelist,parent,false);

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
        holder.head.setText(n);
        holder.desc.setText(a);
        holder.ret.setOnClickListener(listener);

    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView head;
        public TextView desc;

        public Button ret;

        public ViewHolder(View itemView) {
            super(itemView);

            head=(TextView)itemView.findViewById(R.id.issuename);
            desc=(TextView)itemView.findViewById(R.id.issuedate);
            ret=(Button)itemView.findViewById(R.id.ret);

        }
    }


}
