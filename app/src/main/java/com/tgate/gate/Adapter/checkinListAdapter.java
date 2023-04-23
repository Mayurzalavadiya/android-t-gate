package com.tgate.gate.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tgate.gate.R;
import com.tgate.gate.apiResponse.VisitorpurposeResponse;
import com.tgate.gate.model.getCheckinList;
import com.tgate.gate.util.PrefsManager;

import java.util.ArrayList;
import java.util.List;

public class checkinListAdapter extends RecyclerView.Adapter<checkinListAdapter.myVisitorListHolder> {

    Context context;
    List<VisitorpurposeResponse.Datum> getVisitorLists;
    String is_login = PrefsManager.readStringPrefsVal(PrefsManager.IS_LOGIN);
    String visitor_purpose_id = PrefsManager.readStringPrefsVal(PrefsManager.VISITOR_PURPOSE_ID);

    ArrayList<String> arrayList = new ArrayList<>();

    public checkinListAdapter(Context context, List<VisitorpurposeResponse.Datum> getVisitorLists) {
        this.context = context;
        this.getVisitorLists = getVisitorLists;

    }

    @NonNull
    @Override
    public myVisitorListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_checkin_list, parent, false);
        return new myVisitorListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myVisitorListHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(getVisitorLists.get(position).getIcon()).into(holder.img);

        holder.txt.setText(getVisitorLists.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {


                if (is_login != null && is_login != "") {
                    if (getVisitorLists.get(position).isSelect()) {
                        arrayList.remove(getVisitorLists.get(position).getId());
                        String selectId = String.join(",", arrayList);
                        PrefsManager.savePrefsVal(PrefsManager.VISITOR_PURPOSE_ID, String.valueOf(selectId));
                        holder.rl_main.setBackground(context.getResources().getDrawable(R.color.white));
                        holder.rl_check.setVisibility(View.INVISIBLE);
                        holder.img.setColorFilter(context.getResources().getColor(R.color.lightly_black));
                        holder.txt.setTextColor(context.getResources().getColor(R.color.black));
                        getVisitorLists.get(position).setSelect(false);


                    } else {

                        arrayList.add(getVisitorLists.get(position).getId());
                        String selectId = String.join(",", arrayList);
                        PrefsManager.savePrefsVal(PrefsManager.VISITOR_PURPOSE_ID, String.valueOf(selectId));
                        holder.rl_main.setBackground(context.getDrawable(R.drawable.bg_check_border));
                        holder.rl_check.setVisibility(View.VISIBLE);
                        holder.img.setColorFilter(context.getResources().getColor(R.color.blue));
                        holder.txt.setTextColor(context.getResources().getColor(R.color.blue));
                        getVisitorLists.get(position).setSelect(true);


                    }
                }
            }
        });

//        holder.cv_main.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(getVisitorLists.get(position).isSelect())
//                {
//
////                            Glide.with(context).load(getVisitorLists.get(position).getVisitorName()).placeholder(R.drawable.blue_businessman);
//                            holder.rl_main.setBackground(context.getResources().getDrawable(R.color.white));
//                            holder.rl_check.setVisibility(View.INVISIBLE);
//                            holder.img1.setVisibility(View.GONE);
//                            holder.img.setVisibility(View.VISIBLE);
//                            holder.txt.setTextColor(context.getResources().getColor(R.color.black));
//                            getVisitorLists.get(position).setSelect(false);
//
//
//
//                }
//                else
//                {
//                    holder.rl_main.setBackground(context.getDrawable(R.drawable.bg_check_border));
//                    holder.rl_check.setVisibility(View.VISIBLE);
//                    holder.img1.setVisibility(View.VISIBLE);
//                    holder.img.setVisibility(View.GONE);
//                    holder.txt.setTextColor(context.getResources().getColor(R.color.blue));
//                    getVisitorLists.get(position).setSelect(true);
//                }
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return getVisitorLists.size();
    }


    class myVisitorListHolder extends RecyclerView.ViewHolder {

        ImageView img;
        AppCompatTextView txt;
        CardView cv_main;
        RelativeLayout rl_main, rl_check;


        public myVisitorListHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            txt = itemView.findViewById(R.id.txt);
            rl_main = itemView.findViewById(R.id.rl_main);
            rl_check = itemView.findViewById(R.id.rl_check);
            cv_main = itemView.findViewById(R.id.cv_main);
        }
    }
}





