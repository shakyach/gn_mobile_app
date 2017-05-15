package com.golfnationsmob.GolfModel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.golfnationsmob.GolfView.NewRound.CourseOption;
import com.golfnationsmob.GolfView.NewRound.RoundSetting;
import com.golfnationsmob.GolfView.NewRound.SelectCourse;
import com.golfnationsmob.GolfView.PostScore.MainPostScore;
import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

import java.util.List;

public class GolfCourseAdaptor extends RecyclerView.Adapter<GolfCourseAdaptor.MyViewHolder> {
    private List<GolfCourse> golfCourseList;
    SelectCourse courseObject;
    Context context;
    public GolfCourseAdaptor(Context context)
    {
         this.context = context;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView m_golfCourseName;
        public ImageView m_selector;
        public LinearLayout Adaplineararrow;
        SharedPreferences sharedpreferencesRS;
        public MyViewHolder(View view) {
            super(view);
            m_golfCourseName = (TextView) view.findViewById(R.id.golfcoursename);
            final Typeface font = AppFont.setFont(context,AppFont.ROBOTO_REGULAR);
            m_golfCourseName.setTypeface(font);
            m_selector = (ImageView) view.findViewById(R.id.arrowIm);
            Adaplineararrow = (LinearLayout) view.findViewById(R.id.golfcoursenameLLO);

            m_selector.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Log.d("","on click   m_selector@################################");
                    if(courseObject.golfCourseList.get(courseObject.m_Position).m_selector) {
                        if(courseObject.fromwhere.equals("roundsetting")) {
                            sharedpreferencesRS = context.getSharedPreferences("roundsetting", context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                            editor.putString("courseNametemp", "" + m_golfCourseName.getText());
                            editor.commit();
                        }
                        else
                        if(courseObject.fromwhere.equals("postscore")) {
                            sharedpreferencesRS = context.getSharedPreferences("MAINPOSTSCORE", context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                            editor.putString("courseNametemp", "" + m_golfCourseName.getText());
                            editor.commit();
                        }


                        Intent nextScreen = new Intent(context, CourseOption.class);
                       // nextScreen.putExtras(bundle);
                        context.startActivity(nextScreen);
                    }
                }
            });
            Adaplineararrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(courseObject.golfCourseList.get(courseObject.m_Position).m_selector) {
                        if(courseObject.fromwhere.equals("roundsetting")) {
                            sharedpreferencesRS = context.getSharedPreferences("roundsetting", context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                            editor.putString("courseNametemp", "" + m_golfCourseName.getText());
                            editor.commit();
                        }
                        else
                        if(courseObject.fromwhere.equals("postscore")) {
                            sharedpreferencesRS = context.getSharedPreferences("MAINPOSTSCORE", context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                            editor.putString("courseNametemp", "" + m_golfCourseName.getText());
                            editor.commit();
                        }


                        Intent nextScreen = new Intent(context, CourseOption.class);
                     //   nextScreen.putExtras(bundle);
                        context.startActivity(nextScreen);
                    }
                    else {

                        if(courseObject.fromwhere.equals("roundsetting")) {
                            SharedPreferences sharedpreferencesRS= context.getSharedPreferences("roundsetting" , context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                            editor.putString("courseName" , ""+m_golfCourseName.getText());
                            editor.commit();
                            SharedPreferences FB = context.getSharedPreferences("FrontBack", context.MODE_PRIVATE);
                            SharedPreferences.Editor FBEDIT = FB.edit();
                            FBEDIT.putString("FRONT","");
                            FBEDIT.putString("BACK","");
                            FBEDIT.commit();
                            Intent nextScreen = new Intent(context, RoundSetting.class);
                            // nextScreen.putExtras(bundle);
                            context.startActivity(nextScreen);
                        }
                        else
                        if(courseObject.fromwhere.equals("postscore")) {
                            sharedpreferencesRS= context.getSharedPreferences("MAINPOSTSCORE" , context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                            editor.putString("courseName" , ""+m_golfCourseName.getText());
                            editor.commit();
                            SharedPreferences FB = context.getSharedPreferences("FrontBackPS", context.MODE_PRIVATE);
                            SharedPreferences.Editor FBEDIT = FB.edit();
                            FBEDIT.putString("FRONT","");
                            FBEDIT.putString("BACK","");
                            FBEDIT.commit();
                            Intent nextScreen = new Intent(context, MainPostScore.class);
                            // nextScreen.putExtras(bundle);
                            context.startActivity(nextScreen);
                        }
                        Log.d("","on click   m_golfCourseName@################################");
                    }
                }
            });


        }
    }



    public GolfCourseAdaptor(List<GolfCourse> golfCourseList, Context context, SelectCourse courseObject) {
        this.golfCourseList = golfCourseList;
        this.context = context;
        this.courseObject = courseObject;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.golfcourse_name, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GolfCourse golfCourse = golfCourseList.get(position);
        holder.m_golfCourseName.setText(golfCourse.getCourse());
        if(golfCourse.getSelector())
        {
            holder.m_selector.setBackgroundResource(R.drawable.rightarrow);
        }
        else
        {
            holder.m_selector.setBackgroundResource(R.drawable.trpr);
        }

    }

    @Override
    public int getItemCount() {
        return golfCourseList.size();
    }
}
