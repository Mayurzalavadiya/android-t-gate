package com.tgate.gate.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tgate.gate.R;
import com.tgate.gate.apiResponse.ContactlistGuardResponse;
import com.tgate.gate.util.PrefsManager;

import java.util.List;

public class hostAdapter extends RecyclerView.Adapter<hostAdapter.myHostListHolder> {

    Context context;
    List<ContactlistGuardResponse.Datum> getHostlist;
    AppCompatImageView img_close_bottom_host_list;


//    int hostimage[];
//    String hostname[], hostposs[];
//    int counter = 1;


    public hostAdapter(Context context, List<ContactlistGuardResponse.Datum> getHostlist) {
        this.context = context;
        this.getHostlist = getHostlist;
    }

    @NonNull
    @Override
    public myHostListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_host_list, parent, false);
        return new myHostListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHostListHolder holder, @SuppressLint("RecyclerView") int position) {
//
//        if (counter<hostname.length){
//            holder.txt3_number.setText(getHostlist.get(position).setText(counter));
//            counter++;
//        }
        Glide.with(context).load(getHostlist.get(position).getProfileImage()).placeholder(R.drawable.profile).into(holder.img_hostimg);

        holder.txt_hostname.setText(getHostlist.get(position).getFirstname()+" "+ getHostlist.get(position).getLastname());
        holder.txt_hostpossition.setText(getHostlist.get(position).getEmail());
        holder.txt3_number.setText(getHostlist.get(position).getId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             /*   Intent intent = new Intent();
                intent.putExtra("host_id",getHostlist.get(position).getId());
                intent.putExtra("host_first_name",getHostlist.get(position).getFirstname());
                intent.putExtra("host_last_name",getHostlist.get(position).getLastname());
                context.sendBroadcast(intent);*/

                PrefsManager.savePrefsVal(PrefsManager.HOST_USER_ID, getHostlist.get(position).getId());
                PrefsManager.savePrefsVal(PrefsManager.HOST_FIRST_NAME,  getHostlist.get(position).getFirstname());
                PrefsManager.savePrefsVal(PrefsManager.HOST_LAST_NAME,  getHostlist.get(position).getLastname());
                Log.d("@@@_Company_id", PrefsManager.readStringPrefsVal(PrefsManager.HOST_USER_ID));
            }
        });
    }

    @Override    public int getItemCount() {
        return getHostlist.size();
    }


    public class myHostListHolder extends RecyclerView.ViewHolder {

        ImageView img_hostimg;
        AppCompatTextView txt_hostname;
        AppCompatTextView txt3_number;
        CardView maincard;
        AppCompatTextView txt_hostpossition;

        public myHostListHolder(@NonNull View itemView) {
            super(itemView);
            maincard = itemView.findViewById(R.id.maincard);
            img_hostimg = itemView.findViewById(R.id.img_hostimg);
            txt_hostname = itemView.findViewById(R.id.txt_hostname);
            txt3_number = itemView.findViewById(R.id.txt3_number);
            txt_hostpossition = itemView.findViewById(R.id.txt_hostpossition);
        }
    }
}
