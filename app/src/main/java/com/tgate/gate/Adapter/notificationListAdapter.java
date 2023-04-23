package com.tgate.gate.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.tgate.gate.Pagination.BaseRecyclerAdapter;
import com.tgate.gate.Pagination.LoadMoreRecyclerAdapter;
import com.tgate.gate.R;
import com.tgate.gate.apiResponse.getGuardNotificationResponse;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class notificationListAdapter extends LoadMoreRecyclerAdapter<notificationListAdapter.myNotificationHolder, getGuardNotificationResponse.Datum> {
    Context context;

    public notificationListAdapter(List<getGuardNotificationResponse.Datum> data,Context context) {
        super(data);
        this.context = context;
    }

    public void addAllS(List<getGuardNotificationResponse.Datum> response) {
        getList().addAll(response);
        notifyDataSetChanged();
    }

    public void replaceAll(List<getGuardNotificationResponse.Datum> response) {
        getList().clear();
        getList().addAll(response);
        notifyDataSetChanged();
    }


    @Override
    public myNotificationHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_notification_list, parent, false);
        return new myNotificationHolder(view);
    }

    @Override
    public void onBindHolder(myNotificationHolder holder, int position) {

        getGuardNotificationResponse.Datum getNotification=getItem(position);


        holder.txt_body.setText(getNotification.getBody());
        holder.txt_title.setText(getNotification.getTitle());
        Log.d("@@@_notification_id",getNotification.getId());
        String time = getNotification.getCreatedAt();
        String[] separated = time.split(" ");
        String sdate = separated[0] ;
        String stime = separated[1] ;
//        String outputPattern = "dd-MMM-yyyy";
//        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
//        Log.d("dateeeeeeeeee",sdate);
//        String str = outputFormat.format(sdate);
        holder.txt_time.setText(stime);
        holder.txt_date.setText(sdate);
       // Log.d("@@@notification_date",date);
    }



    public class myNotificationHolder extends BaseRecyclerAdapter<myNotificationHolder, getGuardNotificationResponse.Datum>.ViewHolder {
        AppCompatTextView txt_title, txt_body, txt_date, txt_time;
        public myNotificationHolder(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_body = itemView.findViewById(R.id.txt_body);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_time = itemView.findViewById(R.id.txt_time);

        }
    }
}
