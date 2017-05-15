package com.golfnationsmob.GolfModel;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.golfnationsmob.GolfView.PracticeGoals.PracGoPutting;
import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

import java.util.List;

/**
 * Created by Miral on 2/15/2017.
 */
public class ResentSetsAdaptor extends RecyclerView.Adapter<ResentSetsAdaptor.MyViewHolder>{

    private List<ResentSets> resentsetsList;
    Context context;
    PracGoPutting puttingobject;
    public ResentSetsAdaptor(Context context)
    {
        this.context = context;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView RE_date,RE_distance,RE_outoftotal,RE_percentage;

        public MyViewHolder(View view) {
            super(view);
            RE_date = (TextView) view.findViewById(R.id.recentviewDate);
            RE_distance = (TextView) view.findViewById(R.id.recentviewDistance);
            RE_outoftotal = (TextView) view.findViewById(R.id.recentviewoutoftotal);
            RE_percentage = (TextView) view.findViewById(R.id.recentviewpercentage);
            final Typeface font = AppFont.setFont(context,AppFont.ROBOTO_REGULAR);

        }
    }



    public ResentSetsAdaptor(List<ResentSets> resentsetsList, Context context, PracGoPutting puttingobject) {
        this.resentsetsList = resentsetsList;
        this.context = context;
        this.puttingobject = puttingobject;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resentsets, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ResentSets resentset = resentsetsList.get(position);
        holder.RE_date.setText(resentset.getDate());
        holder.RE_distance.setText(resentset.getDistance());
        holder.RE_outoftotal.setText(resentset.getoutOFtotal());
        holder.RE_percentage.setText(resentset.getPercentage());


    }

    @Override
    public int getItemCount() {
        return resentsetsList.size();
    }
}
