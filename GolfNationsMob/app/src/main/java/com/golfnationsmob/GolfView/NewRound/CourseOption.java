package com.golfnationsmob.GolfView.NewRound;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.golfnationsmob.GolfView.PostScore.MainPostScore;
import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

public class CourseOption extends Activity {
    Button m_back,m_continue;
    TextView m_titletext,m_coursename,selectcoursetext,fronttext,backtext;
    CheckBox m_frontRaptor,m_frontTalan,m_backRaptor,m_backTalan;

    String tempCoursename = "";

    String Front ="";
    String Back ="";
    LinearLayout FrontraptorLayout,FronttalanLayout;
    LinearLayout BackraptorLayout,BacktalanLayout;
    LinearLayout backlinear;
    TextView Frontraptortext,Fronttalantext;
    TextView Backraptortext,Backtalantext;
    float x1,x2;
String fromwhere = "";
    SharedPreferences sharedpreferencesRS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_course_option);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.subheader);
        //#UI INITILIZATION
        m_back = (Button)findViewById(R.id.header_left_btn);
        m_continue = (Button)findViewById(R.id.courseOptionContinue);
        m_titletext = (TextView)findViewById(R.id.titletext);
        m_coursename = (TextView)findViewById(R.id.idcoursename);
        selectcoursetext = (TextView)findViewById(R.id.selectcoursetext);
        fronttext = (TextView)findViewById(R.id.Fronttextid);
        backtext = (TextView)findViewById(R.id.Backintextid);
        m_titletext.setText("Course Options");
        backlinear = (LinearLayout) findViewById(R.id.backlinear);
        final Typeface font = AppFont.setFont(this,AppFont.ROBOTO_REGULAR);
        final Typeface font1 =  AppFont.setFont(this,AppFont.ROBOTO_LIGHT);
        final Typeface font2 = AppFont.setFont(this,AppFont.ROBOTO_MEDIUM);
        final Typeface font3 = AppFont.setFont(this,AppFont.ROBOTO_BOLD);

        m_titletext.setTypeface(font);
        m_coursename.setTypeface(font3);
        selectcoursetext.setTypeface(font1);
        fronttext.setTypeface(font2);
        backtext.setTypeface(font2);

        m_frontRaptor = (CheckBox)findViewById(R.id.frontraptor);
        m_frontTalan = (CheckBox)findViewById(R.id.fronttalan);
        m_backRaptor = (CheckBox)findViewById(R.id.backraptor);
        m_backTalan = (CheckBox)findViewById(R.id.backtalan);

        FrontraptorLayout = (LinearLayout)findViewById(R.id.frontraptorLayout);
        FronttalanLayout = (LinearLayout)findViewById(R.id.fronttalanLayout);

        BackraptorLayout = (LinearLayout)findViewById(R.id.backraptorLayout);
        BacktalanLayout = (LinearLayout)findViewById(R.id.backtalanLayout);

        Frontraptortext = (TextView)findViewById(R.id.frontraptortext);
        Fronttalantext = (TextView)findViewById(R.id.fronttalantext);

        Backraptortext = (TextView)findViewById(R.id.backraptortext);
        Backtalantext = (TextView)findViewById(R.id.backtalantext);

        Frontraptortext.setTypeface(font);
        Fronttalantext.setTypeface(font);

        Backraptortext.setTypeface(font);
        Backtalantext.setTypeface(font);
        SharedPreferences sharedpreferencestemp= getSharedPreferences("SCREENNAME" , MODE_PRIVATE);
        fromwhere = sharedpreferencestemp.getString("screenname","");
//###############
        //#DATA FROM LOCAL DB
        sharedpreferencesRS= getSharedPreferences("roundsetting", MODE_PRIVATE);
        m_coursename.setText(""+sharedpreferencesRS.getString("courseNametemp", ""));

        //#CHECKED UNCHECKED FOR OPTION WITH UI CHANGE
        m_frontRaptor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(m_frontRaptor.isChecked()==true && m_frontTalan.isChecked()==true) {
                   m_frontRaptor.setChecked(true);
                   m_frontTalan.setChecked(false);
                }

                if(m_frontRaptor.isChecked()==true)
                {
                    FrontraptorLayout.setBackgroundColor(Color.parseColor("#accc3f"));
                    Frontraptortext.setTypeface(font2);
                    Frontraptortext.setTextColor(Color.parseColor("#ffffff"));
                    m_frontRaptor.setButtonDrawable(R.drawable.check2);
                    Front ="RAPTOR";


                }
                else
                {
                    FrontraptorLayout.setBackgroundColor(Color.parseColor("#f1f2f5"));
                    Frontraptortext.setTypeface(font);
                    Frontraptortext.setTextColor(Color.parseColor("#1a2531"));
                    m_frontRaptor.setButtonDrawable(R.drawable.check1);

                }
            }
        });

        m_frontTalan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(m_frontRaptor.isChecked()==true && m_frontTalan.isChecked()==true) {
                    m_frontTalan.setChecked(true);
                    m_frontRaptor.setChecked(false);
                }

                if(m_frontTalan.isChecked()==true)
                {
                    FronttalanLayout.setBackgroundColor(Color.parseColor("#accc3f"));
                    Fronttalantext.setTypeface(font2);
                    Fronttalantext.setTextColor(Color.parseColor("#ffffff"));
                    m_frontTalan.setButtonDrawable(R.drawable.check2);

                    Front ="TALON";
                }
                else
                {
                    FronttalanLayout.setBackgroundColor(Color.parseColor("#f1f2f5"));
                    Fronttalantext.setTypeface(font);
                    Fronttalantext.setTextColor(Color.parseColor("#1a2531"));
                    m_frontTalan.setButtonDrawable(R.drawable.check1);

                }
            }
        });

        m_backRaptor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(m_backRaptor.isChecked()==true && m_backTalan.isChecked()==true) {
                    m_backRaptor.setChecked(true);
                    m_backTalan.setChecked(false);
                }

                if(m_backRaptor.isChecked()==true)
                {
                    BackraptorLayout.setBackgroundColor(Color.parseColor("#accc3f"));
                    Backraptortext.setTypeface(font2);
                    Backraptortext.setTextColor(Color.parseColor("#ffffff"));
                    m_backRaptor.setButtonDrawable(R.drawable.check2);
                    Back ="RAPTOR";

                }
                else
                {
                    BackraptorLayout.setBackgroundColor(Color.parseColor("#f1f2f5"));
                    Backraptortext.setTypeface(font);
                    Backraptortext.setTextColor(Color.parseColor("#000000"));
                    m_backRaptor.setButtonDrawable(R.drawable.check1);

                }
            }
        });


        m_backTalan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(m_backRaptor.isChecked()==true && m_backTalan.isChecked()==true) {
                    m_backTalan.setChecked(true);
                    m_backRaptor.setChecked(false);
                }

                if(m_backTalan.isChecked()==true)
                {
                    BacktalanLayout.setBackgroundColor(Color.parseColor("#accc3f"));
                    Backtalantext.setTypeface(font2);
                    Backtalantext.setTextColor(Color.parseColor("#ffffff"));
                    m_backTalan.setButtonDrawable(R.drawable.check2);
                    Back ="TALON";

                }
                else
                {
                    BacktalanLayout.setBackgroundColor(Color.parseColor("#f1f2f5"));
                    Backtalantext.setTypeface(font);
                    Backtalantext.setTextColor(Color.parseColor("#000000"));
                    m_backTalan.setButtonDrawable(R.drawable.check1);

                }
            }
        });

        backlinear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent PreviousScreen = new Intent(CourseOption.this,SelectCourse.class);
                //PreviousScreen.putExtras(bundle);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });
        m_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent PreviousScreen = new Intent(CourseOption.this,SelectCourse.class);
                //PreviousScreen.putExtras(bundle);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });


//#CONTINUE OPTION AFTER SETTING THE COURSE OPTION

    m_continue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if((m_frontRaptor.isChecked()==false && m_frontTalan.isChecked()==false)||(m_backRaptor.isChecked()==false && m_backTalan.isChecked()==false))
                {
                    Toast.makeText(getApplicationContext(), "Please Select Front and Back options.", Toast.LENGTH_LONG).show();

                }
                else {

                    if(fromwhere.equals("roundsetting")) {
                        sharedpreferencesRS= getSharedPreferences("roundsetting" , MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                        editor.putString("courseName" , ""+m_coursename.getText());
                        editor.commit();

                        SharedPreferences FB = getSharedPreferences("FrontBack", MODE_PRIVATE);
                        SharedPreferences.Editor FBEDIT = FB.edit();
                        FBEDIT.putString("FRONT",""+Front);
                        FBEDIT.putString("BACK",""+Back);
                        FBEDIT.commit();
                        Intent PreviousScreen = new Intent(CourseOption.this, RoundSetting.class);
                        startActivity(PreviousScreen);
                        finish();
                    }
                    else
                    if(fromwhere.equals("postscore")) {
                        SharedPreferences sharedpreferencesRS= getSharedPreferences("MAINPOSTSCORE" , MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                        editor.putString("courseName" , ""+m_coursename.getText());
                        editor.commit();

                        SharedPreferences FB = getSharedPreferences("FrontBack", MODE_PRIVATE);
                        SharedPreferences.Editor FBEDIT = FB.edit();
                        FBEDIT.putString("FRONT",""+Front);
                        FBEDIT.putString("BACK",""+Back);
                        FBEDIT.commit();
                        Intent PreviousScreen = new Intent(CourseOption.this, MainPostScore.class);
                        startActivity(PreviousScreen);
                        finish();
                    }
                }
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });
    }
    @Override
    public void onBackPressed() {


        Intent PreviousScreen = new Intent(CourseOption.this,SelectCourse.class);
    //    PreviousScreen.putExtras(bundle);
        startActivity(PreviousScreen);
        finish();
        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }
}
