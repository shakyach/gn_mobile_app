package com.golfnationsmob.GolfView.NewRound;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

public class ShotAccuracy extends Activity {
    Button m_back,m_menuButton;
    TextView m_titletext;
    FrameLayout Accuracy[];
    Button ButtonAccuracy[];
    boolean ButtonAccuracyenable[],ButtonLandingenable[];
    FrameLayout Landing[];
    LinearLayout backlinear;

    Button ButtonLanding[],savebutton;
    int touchindex;
    int index=0;
    boolean buttonpress;
    int profileNo = 0;
    int holeNumVar=0;
    LinearLayout Linearlanding,Linearlandingoption;
    LinearLayout Linearpenalties,Linearpenaltiesoption1,Linearpenaltiesoption2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_shot_accuracy);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.subheader);
        m_back = (Button)findViewById(R.id.header_left_btn);
        m_menuButton = (Button)findViewById(R.id.menubuttonSR);
        savebutton = (Button)findViewById(R.id.saveshotaccu);
        Linearlanding = (LinearLayout)findViewById(R.id.Linearlanding);
        backlinear = (LinearLayout) findViewById(R.id.backlinear);

        Linearlandingoption = (LinearLayout)findViewById(R.id.Linearlandingoption);
        Linearpenalties = (LinearLayout)findViewById(R.id.Linearpenalties);
        Linearpenaltiesoption1 = (LinearLayout)findViewById(R.id.Linearpenaltiesoption1);
        Linearpenaltiesoption2 = (LinearLayout)findViewById(R.id.Linearpenaltiesoption2);

       // m_menuButton.setVisibility(View.VISIBLE);
        m_titletext = (TextView)findViewById(R.id.titletext);
        m_titletext.setText("Shot Accuracy");
        Typeface font = AppFont.setFont(this,AppFont.ROBOTO_REGULAR);
        m_titletext.setTypeface(font);
        SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
        profileNo = sharedpreferencesCLUB.getInt("SRprofileNo", 0);
        holeNumVar= sharedpreferencesCLUB.getInt("SRholeNumVar", 0);
        index = sharedpreferencesCLUB.getInt("clubselectindex"+profileNo+""+holeNumVar, 0);

        int from =  sharedpreferencesCLUB.getInt("accuracyFor", 0);
        if(from == 0)
        {
            Linearlanding.setVisibility(View.VISIBLE);
            Linearlandingoption.setVisibility(View.VISIBLE);
            Linearpenalties.setVisibility(View.VISIBLE);
            Linearpenaltiesoption1.setVisibility(View.VISIBLE);
            Linearpenaltiesoption2.setVisibility(View.VISIBLE);
        }
        else
        {
            Linearlanding.setVisibility(View.GONE);
            Linearlandingoption.setVisibility(View.GONE);
            Linearpenalties.setVisibility(View.GONE);
            Linearpenaltiesoption1.setVisibility(View.GONE);
            Linearpenaltiesoption2.setVisibility(View.GONE);
        }
        Accuracy= new FrameLayout[9];
        ButtonAccuracy= new Button[9];
        ButtonAccuracyenable = new boolean[9];
        Landing= new FrameLayout[2];
        ButtonLanding= new Button[2];
        ButtonLandingenable = new boolean[2];
        for(int i = 0;i<Accuracy.length;i++) {
            int resID = getResources().getIdentifier("frameSA" + (i), "id", getPackageName());
            int resID1 = getResources().getIdentifier("buttonSA" + (i+1), "id", getPackageName());
            Accuracy[i] = (FrameLayout) findViewById(resID);
            ButtonAccuracy[i] = (Button) findViewById(resID1);
            ButtonAccuracyenable[i] = false;
        }

        for(int i = 0;i<Landing.length;i++) {
            int resID = getResources().getIdentifier("frameSAlanding" + (i), "id", getPackageName());
            int resID1 = getResources().getIdentifier("buttonBunker" + (i), "id", getPackageName());
            Landing[i] = (FrameLayout) findViewById(resID);
            ButtonLanding[i] = (Button) findViewById(resID1);
            ButtonLandingenable[i] = false;
        }

        for(int i = 0;i<ButtonAccuracy.length;i++) {
            final int val = i;
            ButtonAccuracy[i].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                    } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        //Woods[val].setBackgroundColor(Color.parseColor("#00ff00"));
                        //return true;
                        touchindex = val;
                        Log.i("value", "touchindex===============" + touchindex);
                        for (int i = 0; i < Accuracy.length; i++) {
                            if (touchindex == i) {
                                if(!ButtonAccuracyenable[i]) {
                                    Accuracy[i].setBackgroundColor(Color.parseColor("#accb3f"));
                                    SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                    editor.putInt("accuracyselect"+profileNo+""+holeNumVar+"" + index, (i + 1));
                                    editor.commit();
                                    buttonpress = true;
                                    ButtonAccuracyenable[i] = true;
                                }
                                else
                                {
                                    Accuracy[i].setBackgroundResource(R.drawable.whitebox);
                                    SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                    editor.putInt("accuracyselect"+profileNo+""+holeNumVar+"" + index,0);
                                    editor.commit();
                                    buttonpress = false;
                                    ButtonAccuracyenable[i] = false;
                                }
                            } else {
                                Accuracy[i].setBackgroundResource(R.drawable.whitebox);
                            }

                        }


                    }
                    return false;
                }

            });
            Log.i("value", "i=====================" + i);
        }

        for(int i = 0;i<ButtonLanding.length;i++) {
            final int val = i;
            ButtonLanding[i].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                    } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        //Woods[val].setBackgroundColor(Color.parseColor("#00ff00"));
                        //return true;
                        touchindex = val;
                        Log.i("value", "touchindex===============" + touchindex);
                        for (int i = 0; i < ButtonLanding.length; i++) {
                            if (touchindex == i) {
                                if(!ButtonLandingenable[i]) {
                                    Landing[i].setBackgroundColor(Color.parseColor("#accb3f"));
                                    ButtonLanding[i].setBackgroundColor(Color.parseColor("#accb3f"));
                                    ButtonLanding[i].setTextColor(Color.parseColor("#ffffff"));
                                    ButtonLandingenable[i] = true;
                                }
                                else
                                {
                                    Landing[i].setBackgroundColor(Color.parseColor("#a5a6a8"));
                                    ButtonLanding[i].setBackgroundColor(Color.parseColor("#ffffff"));
                                    ButtonLanding[i].setTextColor(Color.parseColor("#a5a6a7"));
                                    ButtonLandingenable[i] = false;
                                }

                            } else {
                                Landing[i].setBackgroundColor(Color.parseColor("#a5a6a8"));
                                ButtonLanding[i].setBackgroundColor(Color.parseColor("#ffffff"));
                                ButtonLanding[i].setTextColor(Color.parseColor("#a5a6a7"));

                            }

                        }


                    }
                    return false;
                }

            });
            Log.i("value", "i=====================" + i);
        }
        savebutton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // gps.setBackgroundColor(Color.parseColor("#51677e"));
                    // m_buttonGps.setTextColor(Color.WHITE);

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(!buttonpress)
                    {
                        SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                        editor.putInt("accuracyselect"+profileNo+""+holeNumVar+""+index ,0);
                        editor.commit();
                        Log.i("value","here in saveeeeeeeeeeeeeeeeee");
                    }
                    Intent nextScreen = new Intent(ShotAccuracy.this, StartRound.class);

                    startActivity(nextScreen);
                    finish();
                }
                return false;
            }

        });

        backlinear.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    Intent nextScreen = new Intent(ShotAccuracy.this, StartRound.class);
                    startActivity(nextScreen);
                    finish();
                }
                return false;
            }

        });

        m_back.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    Intent nextScreen = new Intent(ShotAccuracy.this, StartRound.class);
                    startActivity(nextScreen);
                    finish();
                }
                return false;
            }

        });
    }
    @Override
    public void onBackPressed() {

        return;

    }
}
