package com.golfnationsmob.GolfView.PracticeGoals;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.golfnationsmob.GolfModel.DividerItemDecoration;
import com.golfnationsmob.GolfModel.PracticeType;
import com.golfnationsmob.GolfModel.PracticetypeAdaptor;
import com.golfnationsmob.GolfModel.RecyclerTouchListener;
import com.golfnationsmob.GolfView.MainPage;
import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PracticeGoalsMain extends Activity {

    Button m_back;
    TextView m_titletext,m_date;
    public List<PracticeType> PracticeTypeList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PracticetypeAdaptor mAdapter;
    ScrollView ScrollViewpracgoal;
    CompactCalendarView compactCalendarView;
    String MonthName[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_practice_goals_main);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.subheader);
        m_back = (Button) findViewById(R.id.header_left_btn);
        m_titletext = (TextView)findViewById(R.id.titletext);
        m_date = (TextView)findViewById(R.id.montnyearprac);
        m_titletext.setText("Practice & Goals");
        ScrollViewpracgoal = (ScrollView) findViewById(R.id.scrollViewpracgoal);
        Typeface font = AppFont.setFont(this,AppFont.ROBOTO_REGULAR);
        m_titletext.setTypeface(font);
        compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        // Set first day of week to Monday, defaults to Monday so calling setFirstDayOfWeek is not necessary
        // Use constants provided by Java Calendar class
        compactCalendarView.setFirstDayOfWeek(Calendar.SUNDAY);
        Calendar calender = Calendar.getInstance();
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH);
        m_date.setText(""+MonthName[month]+"  "+year);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewpractice);

        mAdapter = new PracticetypeAdaptor(PracticeTypeList,this,this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.setFocusable(false);
        recyclerView.setNestedScrollingEnabled(false);
        // recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

       // ScrollViewpracgoal.fullScroll(View.FOCUS_UP);
        // Add event 1 on Sun, 07 Jun 2015 18:20:51 GMT
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.clear();
        cal.set(2017,1,8,18,16);
        Event ev1= new Event(Color.GREEN, cal.getTimeInMillis(), ""+48+"%");
        compactCalendarView.addEvent(ev1);
        cal.clear();
        cal.set(2017,1,14,18,16);
        // Added event 2 GMT: Sun, 07 Jun 2015 19:10:51 GMT
        Event ev2 = new Event(Color.GREEN, cal.getTimeInMillis(),""+46+"%");
        compactCalendarView.addEvent(ev2);
        cal.clear();
        cal.set(2017,1,18,18,16);
        // Added event 2 GMT: Sun, 07 Jun 2015 19:10:51 GMT
        Event ev3 = new Event(Color.GREEN, cal.getTimeInMillis(),""+49+"%");
        compactCalendarView.addEvent(ev3);
        cal.clear();
        cal.set(2017,1,4,18,16);
        // Added event 2 GMT: Sun, 07 Jun 2015 19:10:51 GMT
        Event ev4 = new Event(Color.GREEN, cal.getTimeInMillis(),""+44+"%");
        compactCalendarView.addEvent(ev4);
        cal.clear();
        cal.set(2017,1,14,18,16);
        // Query for events on Sun, 07 Jun 2015 GMT.
        // Time is not relevant when querying for events, since events are returned by day.
        // So you can pass in any arbitary DateTime and you will receive all events for that day.
        List<Event> events = compactCalendarView.getEvents(cal.getTimeInMillis()); // can also take a Date object

        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendarView.getEvents(dateClicked);

                String substr ="not found";
                if(events.toString().indexOf("data=")>0)
                   substr=events.toString().substring(events.toString().indexOf("data=") + 5,events.toString().length()-2);
                Toast.makeText(PracticeGoalsMain.this,""+ dateClicked + " with Accuracy " + substr,Toast.LENGTH_LONG).show();
            //  if(substr!=null)

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Log.i("vv", "Month was scrolled to: ######################" + firstDayOfNewMonth.getMonth());
                Log.i("vv", "Month was scrolled to: ######################" + (1900+firstDayOfNewMonth.getYear()));
                m_date.setText(""+MonthName[firstDayOfNewMonth.getMonth()]+"  "+(1900+firstDayOfNewMonth.getYear()));
            }
        });
        ScrollViewpracgoal.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP ) {
                  //  Log.i("Touche", "ScrollViewpracgoal=="+isViewVisible(compactCalendarView));
                    if(!isViewVisible(compactCalendarView) ) {
                        recyclerView.setNestedScrollingEnabled(true);
                    }
                    else {
                        recyclerView.setNestedScrollingEnabled(false);
                    }
                    return true;
                }
                return false;
            }
        });

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP ) {
                //    Log.i("Touche", "recyclerView=="+isViewVisible(compactCalendarView));
                    if(isViewVisible(compactCalendarView) ) {

                        recyclerView.setNestedScrollingEnabled(false);
                        recyclerView.invalidate();
                        Log.i("Touche", "here in 2222222222222==");
                    }
                    return false;
                }
                return false;
            }
        });
        recyclerView.addOnItemTouchListener(
                new RecyclerTouchListener(getApplicationContext(), new RecyclerTouchListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        PracticeType practicetype = PracticeTypeList.get(position);
                        // Toast.makeText(getApplicationContext(), golfname.getCourse() + " is selected!", Toast.LENGTH_SHORT).show();
                        //   m_Position= position;
                        // TODO Auto-generated method stub
                        Log.i("height","y value ========================="+recyclerView.getScrollY());

                        SharedPreferences sharedpreferencesRS= getSharedPreferences("PRACTICETYPE" , MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                        editor.putString("PracticeName" , ""+practicetype.getPracticetype());
                        editor.putInt("PracticeId" , practicetype.getPracticeID());
                        editor.commit();
                        Intent NextScreen = new Intent(PracticeGoalsMain.this, PracGoPutting.class);
                        // NextScreen.putExtras(bundle);
                        startActivity(NextScreen);
                        finish();


                    }
                })
        );

        preparePracticeTypeData();

        m_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent PreviousScreen = new Intent(PracticeGoalsMain.this, MainPage.class);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

    }


    private void preparePracticeTypeData() {
        PracticeType practicetype = new PracticeType("PUTTING", 0);
        PracticeTypeList.add(practicetype);
        practicetype = new PracticeType("PITCHING (wedge shot)", 3);
        PracticeTypeList.add(practicetype);
        practicetype = new PracticeType("BUNKER", 1);
        PracticeTypeList.add(practicetype);
        practicetype = new PracticeType("APPROACH SHOTS", 6);
        PracticeTypeList.add(practicetype);
        practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);
        practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);
        practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);
        practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);
        practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);
        practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);
        practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);
        practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);   practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);   practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);   practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);   practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);   practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);   practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);   practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);   practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);   practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);   practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);   practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);   practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);   practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);   practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);   practicetype = new PracticeType("TEE SHOTS", 9);
        PracticeTypeList.add(practicetype);

    }
    private boolean isViewVisible(View view) {
        Rect scrollBounds = new Rect();
        ScrollViewpracgoal.getHitRect(scrollBounds);


        float top = view.getY();
        float bottom = top + view.getHeight();
        if (view.getLocalVisibleRect(scrollBounds)) {
            if (!view.getLocalVisibleRect(scrollBounds)
                    || scrollBounds.height() < view.getHeight()) {
                Log.i("scroll", "####################3BTN APPEAR parcial");
                return true;
            } else {
                Log.i("scroll", "##################BTN APPEAR FULLY!!!");
                return true;
            }
        } else {
            Log.i("scroll", "######################No");
            return false;
        }
       /* if (scrollBounds.top < top && scrollBounds.bottom > bottom) {
            return true;
        } else {
            return false;
        }*/

    }
}
