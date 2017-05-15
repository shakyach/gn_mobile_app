package com.golfnationsmob.GolfView.NewRound;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.golfnationsmob.GolfModel.DividerItemDecoration;
import com.golfnationsmob.GolfModel.GolfCourse;
import com.golfnationsmob.GolfModel.GolfCourseAdaptor;
import com.golfnationsmob.GolfModel.RecyclerTouchListener;
import com.golfnationsmob.GolfView.PostScore.MainPostScore;
import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

import java.util.ArrayList;
import java.util.List;

public class SelectCourse extends Activity {
    Button m_back;
    TextView m_titletext,m_coursetext;
    public List<GolfCourse> golfCourseList = new ArrayList<>();
    private RecyclerView recyclerView;
    private GolfCourseAdaptor mAdapter;
    ImageView m_arrowButton;

    public String tempCoursename = "";

    public  GolfCourse golfcourse;
    public int m_Position;
    Button m_nearby,m_recent,m_search;
    LinearLayout backlinear;
    public String fromwhere = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_select_course);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.subheader);

        golfcourse = new GolfCourse();
        m_back = (Button)findViewById(R.id.header_left_btn);
        m_nearby = (Button)findViewById(R.id.scorecardcourse);
        m_recent = (Button)findViewById(R.id.scorecardTalon);
        m_search = (Button)findViewById(R.id.idsearch);
        m_titletext = (TextView)findViewById(R.id.titletext);
        m_coursetext = (TextView)findViewById(R.id.golfcoursename);
        m_arrowButton = (ImageView)findViewById(R.id.arrowIm);
        backlinear = (LinearLayout) findViewById(R.id.backlinear);
        m_titletext.setText("Select a Course");
        Bundle bundle = getIntent().getExtras();
        SharedPreferences sharedpreferencesRS= getSharedPreferences("SCREENNAME" , MODE_PRIVATE);
        fromwhere = sharedpreferencesRS.getString("screenname","");
//#CUSTOM FONT INITILIZATION
        final Typeface font = AppFont.setFont(this,AppFont.ROBOTO_REGULAR);
        final Typeface font1 = AppFont.setFont(this,AppFont.ROBOTO_MEDIUM);

        m_titletext.setTypeface(font);
        m_nearby.setTypeface(font1);
        m_recent.setTypeface(font1);
        m_search.setTypeface(font1);

        //#BACK BUTTON ABOVE ON BAR
        backlinear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(fromwhere.equals("roundsetting")) {
                    Intent PreviousScreen = new Intent(SelectCourse.this,RoundSetting.class);

                    startActivity(PreviousScreen);
                    finish();
                }
                else
                if(fromwhere.equals("postscore")) {
                    Intent PreviousScreen = new Intent(SelectCourse.this,MainPostScore.class);

                    startActivity(PreviousScreen);
                    finish();
                }

                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

        m_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(fromwhere.equals("roundsetting")) {
                    Intent PreviousScreen = new Intent(SelectCourse.this,RoundSetting.class);

                    startActivity(PreviousScreen);
                    finish();
                }
                else
                if(fromwhere.equals("postscore")) {
                    Intent PreviousScreen = new Intent(SelectCourse.this,MainPostScore.class);

                    startActivity(PreviousScreen);
                    finish();
                }

                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

//lIST VIEW FOR GOLFCOURSES
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new GolfCourseAdaptor(golfCourseList,this,this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
       // recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerTouchListener(getApplicationContext(), new RecyclerTouchListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        GolfCourse golfname = golfCourseList.get(position);
                       // Toast.makeText(getApplicationContext(), golfname.getCourse() + " is selected!", Toast.LENGTH_SHORT).show();
                        m_Position= position;
                               // TODO Auto-generated method stub


                    }
                })
        );
        prepareGolfCourseData();
    }

    @Override
    public void onBackPressed() {

        if(fromwhere.equals("roundsetting")) {
            Intent PreviousScreen = new Intent(SelectCourse.this,RoundSetting.class);

            startActivity(PreviousScreen);
            finish();
        }
        else
        if(fromwhere.equals("postscore")) {
            Intent PreviousScreen = new Intent(SelectCourse.this,MainPostScore.class);

            startActivity(PreviousScreen);
            finish();
        }
        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }

    public GolfCourse getGolfCourseObj()
    {
       return golfcourse;
    }
//#DUMMY DATA FOR GOLFCOURSE (WILL REPLACE WITH ACTUAL DATA)
    private void prepareGolfCourseData() {
        GolfCourse coursename = new GolfCourse("McDowell Mountain Ranch",false);
        golfCourseList.add(coursename);
        coursename = new GolfCourse("Starfire Resort",true);
        golfCourseList.add(coursename);
        coursename = new GolfCourse("Greyhawk",true);
        golfCourseList.add(coursename);
        coursename = new GolfCourse("Desert Ridge",false);
        golfCourseList.add(coursename);
        coursename = new GolfCourse("Gold Canyon",false);
        golfCourseList.add(coursename);
        coursename = new GolfCourse("Silver Leaf",false);
        golfCourseList.add(coursename);
        coursename = new GolfCourse("Desert Mountain",true);
        golfCourseList.add(coursename);
        coursename = new GolfCourse("Kierland Resort",false);
        golfCourseList.add(coursename);
        coursename = new GolfCourse("Desert Trail",true);
        golfCourseList.add(coursename);
        coursename = new GolfCourse("Golf Course x1",false);
        golfCourseList.add(coursename);
        coursename = new GolfCourse("Golf Course x2",true);
        golfCourseList.add(coursename);
        coursename = new GolfCourse("Golf Course x3",false);
        golfCourseList.add(coursename);
        coursename = new GolfCourse("Golf Course x4",false);
        golfCourseList.add(coursename);
        coursename = new GolfCourse("Golf Course x5",true);
        golfCourseList.add(coursename);
        coursename = new GolfCourse("Golf Course x6",false);
        golfCourseList.add(coursename);
        coursename = new GolfCourse("Golf Course x7",false);
        golfCourseList.add(coursename);
        coursename = new GolfCourse("Golf Course x8",false);
        golfCourseList.add(coursename);
        coursename = new GolfCourse("Golf Course x9",false);
        golfCourseList.add(coursename);
        coursename = new GolfCourse("Golf Course x10",false);
        golfCourseList.add(coursename);




    }
}
