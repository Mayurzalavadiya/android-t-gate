package com.tgate.gate.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.tgate.gate.Pagination.BaseRecyclerAdapter;
import com.tgate.gate.Pagination.LoadMoreRecyclerAdapter;
import com.tgate.gate.R;
import com.tgate.gate.apiResponse.VisitorDataGuardresponse;

import java.util.Collections;
import java.util.List;

public class visitorListAdapter extends LoadMoreRecyclerAdapter<visitorListAdapter.myVisitorListHolder, List<VisitorDataGuardresponse.Datum>> {
    Context context;
    List<VisitorDataGuardresponse.Datum> getVisitorList;

    public visitorListAdapter(Context context, List<VisitorDataGuardresponse.Datum> getVisitorList) {
        super(Collections.singletonList(getVisitorList));
        this.context = context;
        this.getVisitorList = getVisitorList;
    }

    @Override
    public myVisitorListHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_visitor_list, parent, false);
        return new myVisitorListHolder(view);
    }


    @Override
    public void onBindHolder(myVisitorListHolder holder, int position) {
        Glide.with(context).load(getVisitorList.get(position).getProfile()).into(holder.img_userimg);

        holder.txt_name.setText(getVisitorList.get(position).getName());
        holder.txt_company.setText(getVisitorList.get(position).getContactInfo().getCompanyName());
        holder.txt_city.setText(getVisitorList.get(position).getCity());
        holder.txt_remark.setText(getVisitorList.get(position).getRemark());
        holder.txt_person.setText(getVisitorList.get(position).getNumberOfPersons() + " Person");
//        holder.txt_poss.setText(getVisitorList.get(position).g());
//        holder.txt_exp.setText(getVisitorList.get(position).getVisitorexp());


/*        holder.txt_accept.setOnClickListener(new View.OnClickListener() {
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
        });*/


    }


    @Override
    public int getItemCount() {
        return getVisitorList.size();
    }

    public class myVisitorListHolder extends BaseRecyclerAdapter<myVisitorListHolder, VisitorDataGuardresponse.Datum>.ViewHolder {
        ImageView img_userimg;
        AppCompatTextView txt_name, txt_city, txt_person, txt_company, txt_remark;

        public myVisitorListHolder(@NonNull View itemView) {
            super(itemView);
            img_userimg = itemView.findViewById(R.id.img_userimg);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_person = itemView.findViewById(R.id.txt_person);
            txt_remark = itemView.findViewById(R.id.txt_remark);
            txt_company = itemView.findViewById(R.id.txt_company);
            txt_city = itemView.findViewById(R.id.txt_city);
        }
    }
}
