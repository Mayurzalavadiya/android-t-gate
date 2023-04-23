package com.tgate.gate.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.tgate.gate.R;
import com.tgate.gate.model.getTimeList;

import java.util.List;

public class timeListAdapter extends RecyclerView.Adapter<timeListAdapter.myTimeListHolder> {
    Context context;
    List<getTimeList> getTimeList;

    public timeListAdapter(Context context, List<com.tgate.gate.model.getTimeList> getTimeList) {
        this.context = context;
        this.getTimeList = getTimeList;
    }

    @NonNull
    @Override
    public myTimeListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_time_list, parent,false);
        return new myTimeListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myTimeListHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txt.setText(getTimeList.get(position).getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getTimeList.get(position).isSelect() && getTimeList.get(position).isUnelect())
                {
                    holder.txt.setTextColor(context.getResources().getColor(R.color.blue));
                    holder.rl_main.setBackground(context.getResources().getDrawable(R.drawable.bg_timeunselect));
                    getTimeList.get(position).setUnelect(false);
                    getTimeList.get(position).setSelect(false);


                }
                else
                {
                    holder.rl_main.setBackground(context.getDrawable(R.drawable.bg_timeselect));
                    holder.txt.setTextColor(context.getResources().getColor(R.color.white));
                    getTimeList.get(position).setUnelect(true);
                    getTimeList.get(position).setSelect(true);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return getTimeList.size();
    }

    public class myTimeListHolder extends RecyclerView.ViewHolder {
        RelativeLayout rl_main;
        AppCompatTextView txt;
        public myTimeListHolder(@NonNull View itemView) {
            super(itemView);
            rl_main = itemView.findViewById(R.id.rl_main);
            txt = itemView.findViewById(R.id.txt);
        }
    }
}
