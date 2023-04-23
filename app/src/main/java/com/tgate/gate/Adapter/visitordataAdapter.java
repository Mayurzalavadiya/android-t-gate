package com.tgate.gate.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.tgate.gate.Pagination.BaseRecyclerAdapter;
import com.tgate.gate.Pagination.LoadMoreRecyclerAdapter;
import com.tgate.gate.R;
import com.tgate.gate.apiResponse.VisitorDataGuardresponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class visitordataAdapter extends LoadMoreRecyclerAdapter<visitordataAdapter.myTodayListHolder,VisitorDataGuardresponse.Datum>  {
    Context context;
    List<String> namelistall;


    public visitordataAdapter(List<VisitorDataGuardresponse.Datum> data, Context context) {
        super(data);
        this.namelistall = new ArrayList<>();
        this.context = context;
    }

    public void addAllS(List<VisitorDataGuardresponse.Datum> response) {
        getList().addAll(response);
        notifyDataSetChanged();
    }

    public void replaceAll(List<VisitorDataGuardresponse.Datum> response) {
        getList().clear();
        getList().addAll(response);
        notifyDataSetChanged();
    }

    @Override
    public myTodayListHolder onCreateHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_today_list, parent, false);
            return new myTodayListHolder(view);
        }

    @Override
    public void onBindHolder(myTodayListHolder holder, int position) {

        VisitorDataGuardresponse.Datum getVisitorList=getItem(position);

        Glide.with(context).load(getVisitorList.getProfile()).into(holder.img_userimg);
        namelistall.add(getVisitorList.getName());
        holder.txt_name.setText(getVisitorList.getName());
        holder.txt_company.setText(getVisitorList.getContactInfo().getCompanyName());
        holder.txt_city.setText(getVisitorList.getCity());
        holder.txt_person.setText(getVisitorList.getNumberOfPersons() + " Person");

        holder.txt_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(v.getContext(), R.style.alertDialogThem);
                bottomSheetDialog.setContentView(R.layout.bottomsheetdialog_mettingguard);
                bottomSheetDialog.setCanceledOnTouchOutside(true);

                ImageView img_close = bottomSheetDialog.findViewById(R.id.img_close);
                img_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.show();

                holder.txt_accept.setText("Rejected");
                holder.txt_accept.setBackground(context.getResources().getDrawable(R.drawable.bg_rejected));
            }
        });
/*
        holder.txt_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog dialog_filter = new BottomSheetDialog(v.getContext(), R.style.alertDialogThem);
                dialog_filter.setContentView(R.layout.bottomsheetdialog_mettingowner);
                dialog_filter.setCanceledOnTouchOutside(true);

                ImageView img_close = dialog_filter.findViewById(R.id.img_close);
                img_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_filter.dismiss();
                    }
                });
                dialog_filter.show();
            }
        });
*/
    }

    /*@Override
    public Filter getFilter() {
        return null;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<String> filterList = new ArrayList<>();

            if (constraint.toString().isEmpty()){
                filterList.addAll(namelistall);
            }else {
                for (String name : namelistall){
                    if (name.toLowerCase().contains(constraint.toString().toLowerCase())){
                        filterList.add(name);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filterList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

        }
    };
*/

    public class myTodayListHolder extends BaseRecyclerAdapter<myTodayListHolder, VisitorDataGuardresponse.Datum>.ViewHolder {
        CircleImageView img_userimg;
        AppCompatTextView txt_name, txt_accept,txt_city, txt_person, txt_company;

        public myTodayListHolder(@NonNull View itemView) {
            super(itemView);
            img_userimg = itemView.findViewById(R.id.img_userimg);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_accept = itemView.findViewById(R.id.txt_accept);
            txt_person = itemView.findViewById(R.id.txt_person);
            txt_company = itemView.findViewById(R.id.txt_company);
            txt_city = itemView.findViewById(R.id.txt_city);
        }
    }
}
