package com.golfnationsmob.GolfModel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.golfnationsmob.GolfView.PracticeGoals.PracticeGoalsMain;
import com.golfnationsmob.R;

import java.util.List;

/**
 * Created by Miral on 2/16/2017.
 */
public class PracticetypeAdaptor extends RecyclerView.Adapter<PracticetypeAdaptor.MyViewHolder>{
    private List<PracticeType> PracticeTypeList;
    Context context;
    PracticeGoalsMain PracticeGoalsMainobject;
    public PracticetypeAdaptor(Context context)
    {
        this.context = context;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView PracticeName;

        public MyViewHolder(View view) {
            super(view);
            PracticeName = (TextView) view.findViewById(R.id.practiceTypename);


        }
    }



    public PracticetypeAdaptor(List<PracticeType> PracticeTypeList, Context context, PracticeGoalsMain PracticeGoalsMainobject) {
        this.PracticeTypeList = PracticeTypeList;
        this.context = context;
        this.PracticeGoalsMainobject = PracticeGoalsMainobject;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.practice_type, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PracticeType practicetype = PracticeTypeList.get(position);
        holder.PracticeName.setText(practicetype.getPracticetype());
    }

    @Override
    public int getItemCount() {
        return PracticeTypeList.size();
    }
}
