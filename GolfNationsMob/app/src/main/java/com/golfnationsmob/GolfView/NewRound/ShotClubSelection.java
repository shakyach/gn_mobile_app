package com.golfnationsmob.GolfView.NewRound;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

public class ShotClubSelection extends Activity {
    Button m_back,m_menuButton;
    TextView m_titletext;
    LinearLayout backlinear;

    Button MyClubButton,SaveButton;
    String woodsButtonText[] ={"Dr","2w","3w"};
    String ironButtonText[] ={"3i","4i","5i","6i","7i","8i","9i"};
    String hybridButtonText[] ={"2H","4H","6H"};
    Button Woods[],Iron[],hybrid[];
    boolean Woodsenable[],Ironenable[],hybridenable[];
     int touchindex;
    int index=0;
    int profileNo = 0;
    int holeNumVar=0;
    boolean buttonpress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_shot_club_selection);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.subheader);
        m_back = (Button)findViewById(R.id.header_left_btn);
        m_menuButton = (Button)findViewById(R.id.menubuttonSR);
        MyClubButton = (Button)findViewById(R.id.idmyclub);
        SaveButton = (Button)findViewById(R.id.idSCSsave);
        backlinear = (LinearLayout) findViewById(R.id.backlinear);

        // m_menuButton.setVisibility(View.VISIBLE);
        m_titletext = (TextView)findViewById(R.id.titletext);
        m_titletext.setText("Club Selection");
        Typeface font = AppFont.setFont(this,AppFont.ROBOTO_REGULAR);
        m_titletext.setTypeface(font);
        SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);

        profileNo = sharedpreferencesCLUB.getInt("SRprofileNo", 0);
        holeNumVar= sharedpreferencesCLUB.getInt("SRholeNumVar", 0);
        index = sharedpreferencesCLUB.getInt("clubselectindex"+profileNo+""+holeNumVar, 0);
        int woodIndex = sharedpreferencesCLUB.getInt("woodsindex", 0);
        int hybridIndex = sharedpreferencesCLUB.getInt("hybridindex", 0);
        int ironIndex = sharedpreferencesCLUB.getInt("ironindex", 0);
        if( (sharedpreferencesCLUB.getBoolean("myclubset", false)))
        {
            if(woodIndex > 0)
            {
                Woods = new Button[woodIndex];
                Woodsenable = new boolean[woodIndex];
                woodsButtonText = new String[woodIndex];
                for(int i = 0; i<woodIndex;i++)
                {
                    woodsButtonText[i] = sharedpreferencesCLUB.getString("woodsSelectString"+i, "");
                    Woodsenable[i] = false;
                }
            }

            if(hybridIndex > 0)
            {
                hybrid = new Button[hybridIndex];
                hybridenable = new boolean[hybridIndex];
                hybridButtonText = new String[hybridIndex];
                for(int i = 0; i<hybridIndex;i++)
                {
                    hybridButtonText[i] = sharedpreferencesCLUB.getString("hybridSelectString"+i, "");
                    hybridenable[i] = false;
                }
            }
            if(ironIndex > 0)
            {
                Iron = new Button[ironIndex];
                Ironenable = new boolean[ironIndex];
                ironButtonText = new String[ironIndex];
                for(int i = 0; i<ironIndex;i++)
                {
                    ironButtonText[i] = sharedpreferencesCLUB.getString("ironSelectString"+i, "");
                    Ironenable[i] = false;
                }
            }
          //  Iron = new Button[ironButtonText.length];
           // hybrid = new Button[hybridButtonText.length];
        }
        else
        {
            Woods = new Button[woodsButtonText.length];
            Woodsenable = new boolean[woodsButtonText.length];
            Iron = new Button[ironButtonText.length];
            Ironenable = new boolean[ironButtonText.length];
            hybrid = new Button[hybridButtonText.length];
            hybridenable = new boolean[hybridButtonText.length];
        }

        RelativeLayout woodsLayout = (RelativeLayout) findViewById(R.id.relativeSC1);
        for(int i = 0;i < woodsButtonText.length;i++)
        {
            Woods[i] = setButton(i,woodsButtonText[i]);
            woodsLayout.addView(Woods[i]);

        }

        RelativeLayout hybridLayout = (RelativeLayout) findViewById(R.id.relativeSC2);
        for(int i = 0;i < hybridButtonText.length;i++)
        {
            hybrid[i] = setButton(i,hybridButtonText[i]);
            hybridLayout.addView(hybrid[i]);

        }

        RelativeLayout ironLayout = (RelativeLayout) findViewById(R.id.relativeSC3);
        for(int i = 0;i < ironButtonText.length;i++)
        {
            Iron[i] = setButton(i,ironButtonText[i]);
            ironLayout.addView(Iron[i]);

        }


      /*  for(int i = 0;i<Woods.length;i++) {
            int resID = getResources().getIdentifier("buttonCSw" + (i), "id", getPackageName());
            Woods[i] = (Button) findViewById(resID);
        }*/
        /*for(int i = 0;i<Iron.length;i++) {
            int resID = getResources().getIdentifier("buttonCSi" + (i), "id", getPackageName());
            Iron[i] = (Button) findViewById(resID);
        }

        for(int i = 0;i<hybrid.length;i++) {
            int resID = getResources().getIdentifier("buttonCSwe" + (i), "id", getPackageName());
            hybrid[i] = (Button) findViewById(resID);
        }*/
        for(int i = 0;i<Woods.length;i++) {
            final int val = i;
            Woods[i].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                    } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        //Woods[val].setBackgroundColor(Color.parseColor("#00ff00"));
                        //return true;
                        touchindex = val;
                        Log.i("value", "touchindex===============" + touchindex);
                        for (int i = 0; i < Woods.length; i++) {
                            if (touchindex == i) {
                                if(!Woodsenable[i]) {
                                    Woods[i].setBackgroundColor(Color.parseColor("#accb3f"));
                                    Woods[i].setTextColor(Color.parseColor("#ffffff"));
                                    SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                    editor.putString("clubselectindex"+profileNo+""+holeNumVar+"" + index, "" + Woods[i].getText());
                                    editor.commit();
                                    buttonpress = true;
                                    Woodsenable[i] = true;
                                }
                                else
                                {
                                    Woods[i].setBackgroundResource(R.drawable.whitebox);
                                    Woods[i].setTextColor(Color.parseColor("#a5a6a7"));
                                    buttonpress = false;
                                    Woodsenable[i] = false;
                                }
                            } else {
                                Woods[i].setBackgroundResource(R.drawable.whitebox);
                                Woods[i].setTextColor(Color.parseColor("#a5a6a7"));

                            }

                        }

                        for (int i = 0; i < Iron.length; i++) {
                           Iron[i].setBackgroundResource(R.drawable.whitebox);
                           Iron[i].setTextColor(Color.parseColor("#a5a6a7"));


                        }

                        for (int i = 0; i < hybrid.length; i++) {
                            hybrid[i].setBackgroundResource(R.drawable.whitebox);
                            hybrid[i].setTextColor(Color.parseColor("#a5a6a7"));


                        }
                    }
                    return false;
                }

            });
            Log.i("value", "i=====================" + i);
        }

        for(int j = 0;j<Iron.length;j++) {
            final int val1 = j;
            Iron[j].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                    } else if (event.getAction() == MotionEvent.ACTION_DOWN) {


                        touchindex = val1;

                        for(int i = 0;i<Iron.length;i++)
                        {
                            if(touchindex == i)
                            {
                                if(!Ironenable[i]) {
                                    Iron[i].setBackgroundColor(Color.parseColor("#accb3f"));
                                    Iron[i].setTextColor(Color.parseColor("#ffffff"));
                                    SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                    editor.putString("clubselectindex"+profileNo+""+holeNumVar+"" + index, "" + Iron[i].getText());
                                    editor.commit();
                                    buttonpress = true;
                                    Ironenable[i] = true;
                                }
                                else
                                {
                                    Iron[i].setBackgroundResource(R.drawable.whitebox);
                                    Iron[i].setTextColor(Color.parseColor("#a5a6a7"));
                                    buttonpress = false;
                                    Ironenable[i] = false;

                                }


                            }
                            else {
                                Iron[i].setBackgroundResource(R.drawable.whitebox);
                                Iron[i].setTextColor(Color.parseColor("#a5a6a7"));

                            }

                        }

                        for (int i = 0; i < Woods.length; i++) {
                            Woods[i].setBackgroundResource(R.drawable.whitebox);
                            Woods[i].setTextColor(Color.parseColor("#a5a6a7"));


                        }

                        for (int i = 0; i < hybrid.length; i++) {
                            hybrid[i].setBackgroundResource(R.drawable.whitebox);
                            hybrid[i].setTextColor(Color.parseColor("#a5a6a7"));


                        }

                    }
                    return false;
                }

            });


        }

        for(int k = 0;k<hybrid.length;k++) {
            final int val2 = k;
            hybrid[k].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View view, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                    } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                        touchindex = val2;

                        for(int i = 0;i<hybrid.length;i++)
                        {
                            if(touchindex == i)
                            {
                                if(!hybridenable[i]) {
                                    hybrid[i].setBackgroundColor(Color.parseColor("#accb3f"));
                                    hybrid[i].setTextColor(Color.parseColor("#ffffff"));
                                    SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                    editor.putString("clubselectindex"+profileNo+""+holeNumVar+"" + index, "" + hybrid[i].getText());
                                    editor.commit();
                                    buttonpress = true;
                                    hybridenable[i] = true;
                                }
                                else
                                {
                                    hybrid[i].setBackgroundResource(R.drawable.whitebox);
                                    hybrid[i].setTextColor(Color.parseColor("#a5a6a7"));
                                    buttonpress = false;
                                    hybridenable[i] = false;
                                }


                            }
                            else {
                                hybrid[i].setBackgroundResource(R.drawable.whitebox);
                                hybrid[i].setTextColor(Color.parseColor("#a5a6a7"));

                            }

                        }

                        for (int i = 0; i < Iron.length; i++) {
                            Iron[i].setBackgroundResource(R.drawable.whitebox);
                            Iron[i].setTextColor(Color.parseColor("#a5a6a7"));


                        }

                        for (int i = 0; i < Woods.length; i++) {
                            Woods[i].setBackgroundResource(R.drawable.whitebox);
                            Woods[i].setTextColor(Color.parseColor("#a5a6a7"));

                        }


                    }
                    return false;
                }

            });


        }


        MyClubButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // gps.setBackgroundColor(Color.parseColor("#51677e"));
                    // m_buttonGps.setTextColor(Color.WHITE);

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    Intent nextScreen = new Intent(ShotClubSelection.this, MyClub.class);

                    startActivity(nextScreen);
                    finish();
                }
                return false;
            }

        });

        SaveButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // gps.setBackgroundColor(Color.parseColor("#51677e"));
                    // m_buttonGps.setTextColor(Color.WHITE);

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(!buttonpress) {
                        SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                        editor.putString("clubselectindex"+profileNo+""+holeNumVar+"" + index, "Club");
                        editor.commit();
                        Log.i("value","here in saveeeeeeeeeeeeeeeeee");
                    }
                    Intent nextScreen = new Intent(ShotClubSelection.this, StartRound.class);

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

                    Intent nextScreen = new Intent(ShotClubSelection.this, StartRound.class);
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

                    Intent nextScreen = new Intent(ShotClubSelection.this, StartRound.class);
                    startActivity(nextScreen);
                    finish();
                }
                return false;
            }

        });
    }

    public Button setButton(int index,String text)
    {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, dm);
        RelativeLayout.LayoutParams lprams = new RelativeLayout.LayoutParams(
                (int)dpInPx,
                (int)dpInPx);
        if(index <4)
        lprams.setMargins((int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, dm)),0,0,0);
        else
            lprams.setMargins((int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, dm)),(int)(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, dm)),0,0);

        Button tv1 = new Button(this);
        tv1.setText(""+text);
        tv1.setTextColor(Color.parseColor("#a5a6a7"));
        tv1.setGravity(Gravity.CENTER);
        tv1.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 14, dm));
        tv1.setLayoutParams(lprams);
        tv1.setBackgroundResource(R.drawable.whitebox);
        tv1.setId(1000+index);
        if(index > 0) {
            if(index > 3)
            {
                lprams.addRule(RelativeLayout.BELOW, (1000 + index - 4));
                if(index %4 != 0)
                lprams.addRule(RelativeLayout.RIGHT_OF, (1000 + index - 1));

            }
            else
               lprams.addRule(RelativeLayout.RIGHT_OF, (1000 + index - 1));
        }
        return tv1;

    }
    @Override
    public void onBackPressed() {

        return;

    }
}
