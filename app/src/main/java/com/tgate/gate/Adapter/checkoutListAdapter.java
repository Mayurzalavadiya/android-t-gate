package com.tgate.gate.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.tgate.gate.R;
import com.tgate.gate.apiResponse.CheckInResponse;
import com.tgate.gate.model.getCheckoutList;
import com.tgate.gate.util.PrefsManager;

import java.util.List;

import retrofit2.Callback;

public class checkoutListAdapter extends RecyclerView.Adapter<checkoutListAdapter.MyHolder> {
    Context context;
    List<CheckInResponse.Datum> getCheckoutList;

    public checkoutListAdapter(Context context, List<CheckInResponse.Datum> getCheckoutList) {
        this.context = context;
        this.getCheckoutList = getCheckoutList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_checkout_list, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txt_name.setText(getCheckoutList.get(position).getName());
        holder.txt_1.setText(getCheckoutList.get(position).getId());
        Glide.with(context).load(getCheckoutList.get(position).getProfile()).placeholder(R.drawable.profile).into(holder.img_userimg);
        String checkout_id = getCheckoutList.get(position).getId();
        Log.d("@@@_Check_Out_Id", checkout_id);
        holder.txt_checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        holder.txt_checkin.setBackground(context.getResources().getDrawable(R.drawable.bg_checkout));
                        holder.txt_checkin.setText("Check Out");








                   /* BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(v.getContext(), R.style.alertDialogThem);
                    bottomSheetDialog.setContentView(R.layout.dialog_checkout);
                    bottomSheetDialog.setCanceledOnTouchOutside(true);

                    ImageView img_close = bottomSheetDialog.findViewById(R.id.img_close);
                    img_close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bottomSheetDialog.dismiss();
                        }
                    });
                    bottomSheetDialog.show();

                    holder.txt_checkout.setText("Check In");
                    holder.txt_checkout.setBackground(context.getResources().getDrawable(R.drawable.bg_checkin));*/


            }
        });
    }



    @Override
    public int getItemCount() {
        return getCheckoutList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView img_userimg;
        AppCompatTextView txt_name, txt_checkin,txt_1;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            img_userimg = itemView.findViewById(R.id.img_userimg);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_1 = itemView.findViewById(R.id.txt_1);
            txt_checkin = itemView.findViewById(R.id.txt_checkin);

        }
    }
}
