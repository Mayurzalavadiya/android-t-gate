package com.tgate.gate.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.tgate.gate.Pagination.BaseRecyclerAdapter;
import com.tgate.gate.Pagination.LoadMoreRecyclerAdapter;
import com.tgate.gate.R;
import com.tgate.gate.apiResponse.VisitorDataGuardresponse;
import com.tgate.gate.apiResponse.VisitordataResponse;
import com.tgate.gate.apiResponse.VisitordataResponse.Datum;

import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class visitordataListAdapter extends LoadMoreRecyclerAdapter<visitordataListAdapter.myVisitordataListHolder, VisitorDataGuardresponse.Datum> {
    Context context;


    public visitordataListAdapter(List<VisitorDataGuardresponse.Datum> data, Context context) {
        super(data);
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
    public myVisitordataListHolder onCreateHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_visitor_list, parent, false);
        return new myVisitordataListHolder(view);
    }

    @Override
    public void onBindHolder(myVisitordataListHolder holder, int position) {

        VisitorDataGuardresponse.Datum getVisitordataList=getItem(position);

        Glide.with(context).load(getVisitordataList.getProfile()).into(holder.img_userimg);

        holder.txt_name.setText(getVisitordataList.getName());
        holder.txt_company.setText(getVisitordataList.getContactInfo().getCompanyName());
        holder.txt_city.setText(getVisitordataList.getCity());
        holder.txt_remark.setText(getVisitordataList.getRemark());
        holder.txt_person.setText(getVisitordataList.getNumberOfPersons()+" Person");
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


    public class myVisitordataListHolder extends BaseRecyclerAdapter<myVisitordataListHolder, VisitorDataGuardresponse.Datum>.ViewHolder {

        CircleImageView img_userimg;
        AppCompatTextView txt_name, txt_poss, txt_exp, txt_accept,txt_city, txt_person, txt_company, txt_remark;

        public myVisitordataListHolder(@NonNull View itemView) {
            super(itemView);
            img_userimg = itemView.findViewById(R.id.img_userimg);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_person = itemView.findViewById(R.id.txt_person);
            txt_remark = itemView.findViewById(R.id.txt_remark);
            txt_company = itemView.findViewById(R.id.txt_company);
            txt_poss = itemView.findViewById(R.id.txt_poss);
            txt_exp = itemView.findViewById(R.id.txt_exp);
            txt_city = itemView.findViewById(R.id.txt_city);
        }
    }
}
