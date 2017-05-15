package com.golfnationsmob.GolfView.NewRound;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.golfnationsmob.GolfView.NewRound.Gps.MapsActivity;
import com.golfnationsmob.R;

public class ScoreCard extends Activity {
    String ScreenName;
    TextView m_titletext;
    Button m_back,coursename;

    LinearLayout backlinear;
    TextView ParFrontText[];
    TextView ParFrontMain;
    TextView ParFrontOut;
    TextView ParBackMain;
    TextView ParBacktOut;

    TextView YardFrontText[];
    TextView YardFrontMain;
    TextView YardFrontOut;
    TextView YardBackMain;
    TextView YardBackOut;

    TextView User1FrontMain;
    TextView User1Frontout;
    TextView User1BackMain;
    TextView User1Backout;
    TextView User1Frontext[];
    FrameLayout User1Frame[];

    TextView User2FrontMain;
    TextView User2Frontout;
    TextView User2BackMain;
    TextView User2Backout;
    TextView User2Frontext[];
    FrameLayout User2Frame[];

    TextView User3FrontMain;
    TextView User3Frontout;
    TextView User3BackMain;
    TextView User3Backout;
    TextView User3Frontext[];
    FrameLayout User3Frame[];

    TextView User4FrontMain;
    TextView User4Frontout;
    TextView User4BackMain;
    TextView User4Backout;
    TextView User4Frontext[];
    FrameLayout User4Frame[];

    final int TotalHole = 18;
    int parEachHole[] = {4,5,3,4,5,4,4,4,4,5,3,4,3,4,3,4,4,4};
  //  int parEachHole[] = {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4};
    int YardageEachHole[] = {370, 360, 358, 345, 290, 330, 350, 375, 380, 390, 450, 360, 384, 354, 330, 360, 370, 389};
    String GolferName[] = {"","",""};
    int User1[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,};
    int User2[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,};
    int User3[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,};
    int User4[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,};

    int ScoreValue[] ={2,3,0,4,5,6,7,8};
    int ScoreValue3[] ={1,2,0,3,4,5,6,7};
    int ScoreValue4[] ={2,3,0,4,5,6,7,8};
    int ScoreValue5[] ={3,4,0,5,6,7,8,9};
    LinearLayout linUser1,linUser2,linUser3,linUser4;
    LinearLayout BlinUser1,BlinUser2,BlinUser3,BlinUser4;
    SharedPreferences sharedpresetprofile;
    TextView Fronttext,BackText;
    private SensorManager mSensorManager;
    private Sensor mOrientation;

    float value_0 = -10000;
    float value_1 = -10000;
    private SensorEventListener mOrientationSensorListener = new SensorEventListener() {
        int orientation = -1;

        @Override
        public void onSensorChanged(SensorEvent event) {
            int value ;

            Log.i("values:", "values:0" + event.values[0]);
            Log.i("values:", "values:1" +event.values[1]);
            if (event.values[0] < 0 && event.values[1] > 0) {

              /*  if(ScreenName.equals("FROMSTARTROUND")) {

                    Intent PreviousScreen = new Intent(ScoreCard.this, StartRound.class);

                    startActivity(PreviousScreen);
                }
                else
                if(ScreenName.equals("FROMMAPACTIVITY")) {

                    Intent PreviousScreen = new Intent(ScoreCard.this, MapsActivity.class);

                    startActivity(PreviousScreen);
                }else
                if(ScreenName.equals("FROMTEESELECTION")) {

                    Intent PreviousScreen = new Intent(ScoreCard.this, TeeSelection.class);

                    startActivity(PreviousScreen);
                }*/
            }



        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if (mOrientation != null) {
            mSensorManager.registerListener(mOrientationSensorListener, mOrientation,
                    SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mOrientation != null) {
            mSensorManager.unregisterListener(mOrientationSensorListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int currentOrientation = getResources().getConfiguration().orientation;
        Log.i("value","currentOrientation==="+currentOrientation);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_score_card);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.subheader);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // Get the default sensor of specified type
        mOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        m_back = (Button) findViewById(R.id.header_left_btn);
       // coursename = (Button) findViewById(R.id.scorecardcourse);
        m_titletext = (TextView)findViewById(R.id.titletext);

        SharedPreferences sharedpreferencesRS= getSharedPreferences("roundsetting", MODE_PRIVATE);
       // coursename.setText(""+sharedpreferencesRS.getString("courseName", ""));
        Fronttext = (TextView)findViewById(R.id.scorecardfront);
        BackText = (TextView)findViewById(R.id.IDSCORECARDBACK);

        SharedPreferences FB = getSharedPreferences("FrontBack", MODE_PRIVATE);
       // SharedPreferences.Editor FBEDIT = FB.edit();
        String F = FB.getString("FRONT", "");
        String B = FB.getString("BACK", "");

        if(F.equals(""))
        {
            Fronttext.setText("FRONT");
        }
        else
        {
            Fronttext.setText("FRONT"+" - "+F);
        }

        if(B.equals(""))
        {
            BackText.setText("BACK");
        }
        else
        {
            BackText.setText("BACK"+" - "+B);
        }


        linUser1 = (LinearLayout)findViewById(R.id.linuser1);
        linUser2 = (LinearLayout)findViewById(R.id.linuser2);
        linUser3 = (LinearLayout)findViewById(R.id.linuser3);
        linUser4 = (LinearLayout)findViewById(R.id.linuser4);
        BlinUser1 = (LinearLayout)findViewById(R.id.Blinuser1);
        BlinUser2 = (LinearLayout)findViewById(R.id.Blinuser2);
        BlinUser3 = (LinearLayout)findViewById(R.id.Blinuser3);
        BlinUser4 = (LinearLayout)findViewById(R.id.Blinuser4);

        ParFrontMain = (TextView)findViewById(R.id.parFornt);
        ParFrontOut = (TextView)findViewById(R.id.Fparoutin);
        ParBackMain = (TextView)findViewById(R.id.parBack);
        ParBacktOut = (TextView)findViewById(R.id.Bparoutin);

        ParFrontText = new TextView[TotalHole];
        for(int i = 0;i<TotalHole/2;i++) {
            int resID = getResources().getIdentifier("Fpar"+(i+1), "id", getPackageName());
            ParFrontText[i] = (TextView) findViewById(resID);

        }

        for(int i = 0;i<TotalHole/2;i++) {
            int resID = getResources().getIdentifier("Bpar" + (i + 1), "id", getPackageName());
            ParFrontText[i+9] = (TextView) findViewById(resID);

        }
        int totalPar = 0;
        int totalPar1 = 0;
        for(int i = 0;i <ParFrontText.length;i++)
        {

                ParFrontText[i].setText("" + parEachHole[i]);
            if(i <9) {
                totalPar = totalPar + parEachHole[i];
            }
            else
                totalPar1 = totalPar1 + parEachHole[i];
        }
        ParFrontOut.setText(""+totalPar);
        ParBacktOut.setText(""+totalPar1);


        YardFrontMain = (TextView)findViewById(R.id.idyardage);
        YardFrontOut = (TextView)findViewById(R.id.yardtotal);
        YardBackMain = (TextView)findViewById(R.id.idyardageBACK);
        YardBackOut = (TextView)findViewById(R.id.Byardtotal);
        YardFrontText = new TextView[TotalHole];
        for(int i = 0;i<TotalHole/2;i++) {
            int resID = getResources().getIdentifier("yard" + (i + 1), "id", getPackageName());
            YardFrontText[i] =(TextView)findViewById(resID);
        }

        for(int i = 0;i<TotalHole/2;i++) {
            int resID = getResources().getIdentifier("Byard" + (i + 1), "id", getPackageName());
            YardFrontText[i+9] =(TextView)findViewById(resID);
        }

        int totalyard = 0;
        int totalyard1 = 0;
        for(int i = 0;i <ParFrontText.length;i++)
        {
            YardFrontText[i].setText(""+YardageEachHole[i]);
            if(i < 9)
               totalyard = totalyard + YardageEachHole[i];
            else
               totalyard1 = totalyard1 + YardageEachHole[i];
        }
        YardFrontOut.setText(""+totalyard);
        YardBackOut.setText(""+totalyard1);


        SharedPreferences sharedpreferencesGolfername = getSharedPreferences("AddFriendOnRs", MODE_PRIVATE);
        sharedpresetprofile= getSharedPreferences("profileset", MODE_PRIVATE);
        final SharedPreferences.Editor editorprofile = sharedpresetprofile.edit();


        for(int i =0 ; i<(TotalHole/6);i++)
        {
            GolferName[i] = sharedpreferencesGolfername.getString("memberonRS"+i, "");
            Log.i("ss", " membername *****************" +("memberonRS"+i));
            Log.i("ss", " newMember=== ********" + i + "*********" +  GolferName[i]);
        }
        SharedPreferences scoringDB= getSharedPreferences("Scoring", MODE_PRIVATE);
        for(int j=0 ; j<TotalHole;j++) {

                User1[j] = scoringDB.getInt("profileData1"+""+j+""+0, 0);

        }
        User1FrontMain = (TextView)findViewById(R.id.username1);
        User1Frontout = (TextView)findViewById(R.id.user1outin);
        User1BackMain = (TextView)findViewById(R.id.Busername1);
        User1Backout = (TextView)findViewById(R.id.Buser1outin);
        User1Frontext = new TextView[TotalHole];
        User1Frame =  new FrameLayout[TotalHole];
        for(int i = 0;i<TotalHole/2;i++) {
            int resID = getResources().getIdentifier("user1value" + (i + 1), "id", getPackageName());
            int resID1 = getResources().getIdentifier("frameuser1value" + (i + 1), "id", getPackageName());
            User1Frontext[i] =(TextView)findViewById(resID);
            User1Frame[i] =(FrameLayout) findViewById(resID1);
        }
        for(int i = 0;i<TotalHole/2;i++) {
            int resID = getResources().getIdentifier("Buser1value" + (i + 1), "id", getPackageName());
            int resID1 = getResources().getIdentifier("frameBuser1value" + (i + 1), "id", getPackageName());
            User1Frontext[i+9] =(TextView)findViewById(resID);
            User1Frame[i+9] =(FrameLayout)findViewById(resID1);
        }

        int totalUser = 0;
        int totalUser1 = 0;
        int totalUser1PAR = 0;

        for(int i = 0;i <User1Frontext.length;i++)
        {
            final int tvalue =i;
            if(parEachHole[i] == 3)
            {
                ScoreValue=ScoreValue3;
            }
            else
            if(parEachHole[i] == 4)
            {
                ScoreValue=ScoreValue4;
            }
            else
            if(parEachHole[i] == 5)
            {
                ScoreValue=ScoreValue5;
            }
            if(ScoreValue[User1[i]] == 0) {
                User1Frontext[i].setText("");

                User1Frontext[i].setBackgroundColor(Color.parseColor("#ebedf1"));
                User1Frame[i].setBackgroundColor(Color.parseColor("#ebedf1"));

                User1Frontext[i].setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     editorprofile.putInt("profileno",0);
                     editorprofile.putInt("holeno",tvalue);
                     editorprofile.commit();

                     Log.i("ss", " tvalue=== *****************" +tvalue);

                     Intent PreviousScreen = new Intent(ScoreCard.this, StartRound.class);

                     startActivity(PreviousScreen);
                     finish();
                 }
                });
            }
            else {
                User1Frontext[i].setText("" + ScoreValue[User1[i]]);
                if((ScoreValue[User1[i]]-parEachHole[i])== -2) {
                    User1Frontext[i].setBackgroundResource(R.drawable.round1dark);
                    User1Frontext[i].setTextColor(Color.parseColor("#ffffff"));
                }
                else
                if((ScoreValue[User1[i]]-parEachHole[i])== -1)
                    User1Frontext[i].setBackgroundResource(R.drawable.round2dark);
                else
                if((ScoreValue[User1[i]]-parEachHole[i])== 1)
                    User1Frontext[i].setBackgroundResource(R.drawable.square2dark);
                else
                if((ScoreValue[User1[i]]-parEachHole[i])>=2) {
                    User1Frontext[i].setBackgroundResource(R.drawable.square1dark);
                    User1Frontext[i].setTextColor(Color.parseColor("#ffffff"));
                }
              //  User1Frontext[i].getBackground().setAlpha(255);


                if(i <TotalHole/2)
                    totalUser = totalUser + ScoreValue[User1[i]];
                else
                    totalUser1 = totalUser1 + ScoreValue[User1[i]];
                totalUser1PAR = totalUser1PAR+parEachHole[i];
            }

        }
        SharedPreferences userStatus= getSharedPreferences("USERSTATUS", MODE_PRIVATE);
        SharedPreferences.Editor editorStatus = userStatus.edit();
        Log.i("dos","totalUser1PAR = "+totalUser1PAR);
        Log.i("dos","totalUser = "+totalUser);
        Log.i("dos","totalUser1 = "+totalUser1);
        Log.i("dos","USER1 = "+(totalUser1PAR-totalUser-totalUser1));
        editorStatus.putInt("USER1",(totalUser+totalUser1-totalUser1PAR));
        editorStatus.commit();
        User1Frontout.setText(""+totalUser);
        User1Backout.setText(""+totalUser1);

        if( GolferName[0]== "")
        {
            linUser2.setVisibility(View.GONE);
            BlinUser2.setVisibility(View.GONE);
        }
        else
        {
            // scoringDB= getSharedPreferences("Scoring", MODE_PRIVATE);
            if(GolferName[0].contains(" ")){
                GolferName[0]= GolferName[0].substring(0, GolferName[0].indexOf(" "));
            }
            for(int j=0 ; j<TotalHole;j++) {

                User2[j] = scoringDB.getInt("profileData2"+""+j+""+0, 0);

            }
            User2FrontMain = (TextView)findViewById(R.id.username2);
            User2Frontout = (TextView)findViewById(R.id.user2outin);
            User2BackMain = (TextView)findViewById(R.id.Busername2);
            User2Backout = (TextView)findViewById(R.id.Buser2outin);
            User2Frontext = new TextView[TotalHole];
            User2Frame = new FrameLayout[TotalHole];
            for(int i = 0;i<TotalHole/2;i++) {
                int resID = getResources().getIdentifier("user2value" + (i + 1), "id", getPackageName());
                int resID1 = getResources().getIdentifier("frameuser2value" + (i + 1), "id", getPackageName());
                User2Frontext[i] =(TextView)findViewById(resID);
                User2Frame[i] =(FrameLayout) findViewById(resID1);
            }
            for(int i = 0;i<TotalHole/2;i++) {
                int resID = getResources().getIdentifier("Buser2value" + (i + 1), "id", getPackageName());
                int resID1= getResources().getIdentifier("frameBuser2value" + (i + 1), "id", getPackageName());
                User2Frontext[i+9] =(TextView)findViewById(resID);
                User2Frame[i+9] =(FrameLayout) findViewById(resID1);
            }

            User2FrontMain.setText(""+GolferName[0]);
            User2BackMain.setText(""+GolferName[0]);
            int total2user = 0;
            int total2user1 = 0;
            int total2userPAR = 0;
            for(int i = 0;i <User2Frontext.length;i++)
            {
                final int tvalue =i;
                if(parEachHole[i] == 3)
                {
                    ScoreValue=ScoreValue3;
                }
                else
                if(parEachHole[i] == 4)
                {
                    ScoreValue=ScoreValue4;
                }
                else
                if(parEachHole[i] == 5)
                {
                    ScoreValue=ScoreValue5;
                }
                if(ScoreValue[User2[i]] == 0) {
                    User2Frontext[i].setText("");
                    User2Frontext[i].setBackgroundColor(Color.parseColor("#ebedf1"));
                    User2Frame[i].setBackgroundColor(Color.parseColor("#ebedf1"));
                    User2Frontext[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editorprofile.putInt("profileno",1);
                            editorprofile.putInt("holeno",tvalue);
                            editorprofile.commit();

                            Log.i("ss", " tvalue=== *****************" +tvalue);

                            Intent PreviousScreen = new Intent(ScoreCard.this, StartRound.class);

                            startActivity(PreviousScreen);
                            finish();
                        }
                    });

                }
                else {
                    User2Frontext[i].setText("" + ScoreValue[User2[i]]);
                    if((ScoreValue[User2[i]]-parEachHole[i])== -2) {
                        User2Frontext[i].setBackgroundResource(R.drawable.round1dark);
                        User2Frontext[i].setTextColor(Color.parseColor("#ffffff"));
                    }
                    else
                    if((ScoreValue[User2[i]]-parEachHole[i])== -1)
                        User2Frontext[i].setBackgroundResource(R.drawable.round2dark);
                    else
                    if((ScoreValue[User2[i]]-parEachHole[i])== 1)
                        User2Frontext[i].setBackgroundResource(R.drawable.square2dark);
                    else
                    if((ScoreValue[User2[i]]-parEachHole[i])>=2) {
                        User2Frontext[i].setBackgroundResource(R.drawable.square1dark);
                        User2Frontext[i].setTextColor(Color.parseColor("#ffffff"));
                    }
                    if(i <TotalHole/2 )
                        total2user = total2user + ScoreValue[User2[i]];
                    else
                        total2user1 = total2user1 + ScoreValue[User2[i]];
                    total2userPAR = total2userPAR + parEachHole[i];
                }

            }

            editorStatus.putInt("USER2",(total2user+total2user1-total2userPAR));
            Log.i("dos","USER2 = "+(total2user+total2user1-total2userPAR));
            editorStatus.commit();
            User2Frontout.setText(""+total2user);
            User2Backout.setText(""+total2user1);
        }
        if( GolferName[1]== "")
        {
            linUser3.setVisibility(View.GONE);
            BlinUser3.setVisibility(View.GONE);

        }
        else
        {
            if(GolferName[1].contains(" ")){
                GolferName[1]= GolferName[1].substring(0, GolferName[1].indexOf(" "));
            }
            for(int j=0 ; j<TotalHole;j++) {

                User3[j] = scoringDB.getInt("profileData3"+""+j+""+0, 0);

            }
            User3FrontMain = (TextView)findViewById(R.id.username3);
            User3Frontout = (TextView)findViewById(R.id.user3outin);
            User3BackMain = (TextView)findViewById(R.id.Busername3);
            User3Backout = (TextView)findViewById(R.id.Buser3outin);
            User3Frontext = new TextView[TotalHole];
            User3Frame = new FrameLayout[TotalHole];
            for(int i = 0;i<TotalHole/2;i++) {
                int resID = getResources().getIdentifier("user3value" + (i + 1), "id", getPackageName());
                int resID1 = getResources().getIdentifier("frameuser3value" + (i + 1), "id", getPackageName());
                User3Frontext[i] =(TextView)findViewById(resID);
                User3Frame[i] =(FrameLayout) findViewById(resID1);
            }
            for(int i = 0;i<TotalHole/2;i++) {
                int resID = getResources().getIdentifier("Buser3value" + (i + 1), "id", getPackageName());
                int resID1 = getResources().getIdentifier("frameBuser3value" + (i + 1), "id", getPackageName());
                User3Frontext[i+9] =(TextView)findViewById(resID);
                User3Frame[i+9] =(FrameLayout) findViewById(resID1);
            }

            User3FrontMain.setText(""+GolferName[1]);
            User3BackMain.setText(""+GolferName[1]);
            int total3User= 0;
            int total3User1= 0;
            int total3UserPAR= 0;
            for(int i = 0;i <User3Frontext.length;i++)
            {
                final int tvalue = i;
                if(parEachHole[i] == 3)
                {
                    ScoreValue=ScoreValue3;
                }
                else
                if(parEachHole[i] == 4)
                {
                    ScoreValue=ScoreValue4;
                }
                else
                if(parEachHole[i] == 5)
                {
                    ScoreValue=ScoreValue5;
                }
                if(ScoreValue[User3[i]] == 0) {
                    User3Frontext[i].setText("");
                    User3Frontext[i].setBackgroundColor(Color.parseColor("#ebedf1"));
                    User3Frame[i].setBackgroundColor(Color.parseColor("#ebedf1"));
                    User3Frontext[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editorprofile.putInt("profileno",2);
                            editorprofile.putInt("holeno",tvalue);
                            editorprofile.commit();

                            Log.i("ss", " tvalue=== *****************" +tvalue);

                            Intent PreviousScreen = new Intent(ScoreCard.this, StartRound.class);

                            startActivity(PreviousScreen);
                            finish();
                        }
                    });


                }
                else {
                    User3Frontext[i].setText("" + ScoreValue[User3[i]]);
                    if((ScoreValue[User3[i]]-parEachHole[i])== -2) {
                        User3Frontext[i].setBackgroundResource(R.drawable.round1dark);
                        User3Frontext[i].setTextColor(Color.parseColor("#ffffff"));
                    }
                    else
                    if((ScoreValue[User3[i]]-parEachHole[i])== -1)
                        User3Frontext[i].setBackgroundResource(R.drawable.round2dark);
                    else
                    if((ScoreValue[User3[i]]-parEachHole[i])== 1)
                        User3Frontext[i].setBackgroundResource(R.drawable.square2dark);
                    else
                    if((ScoreValue[User3[i]]-parEachHole[i])>=2) {
                        User3Frontext[i].setBackgroundResource(R.drawable.square1dark);
                        User3Frontext[i].setTextColor(Color.parseColor("#ffffff"));
                    }
                    if(i < TotalHole/2)
                        total3User = total3User + ScoreValue[User3[i]];
                    else
                        total3User1 = total3User1 + ScoreValue[User3[i]];

                    total3UserPAR= total3UserPAR+parEachHole[i];
                }

            }
            editorStatus.putInt("USER3",(total3User+total3User1-total3UserPAR));
            Log.i("dos","USER3 = "+(total3UserPAR-total3User-total3User1));
            editorStatus.commit();
            User3Frontout.setText(""+total3User);
            User3Backout.setText(""+total3User1);
        }
        if( GolferName[2]== "")
        {
            linUser4.setVisibility(View.GONE);
            BlinUser4.setVisibility(View.GONE);

        }else
        {
            if(GolferName[2].contains(" ")){
                GolferName[2]= GolferName[2].substring(0, GolferName[2].indexOf(" "));
            }
            for(int j=0 ; j<TotalHole;j++) {

                User4[j] = scoringDB.getInt("profileData4"+""+j+""+0, 0);

            }
            User4FrontMain = (TextView)findViewById(R.id.username4);
            User4Frontout = (TextView)findViewById(R.id.user4outin);
            User4BackMain = (TextView)findViewById(R.id.Busername4);
            User4Backout = (TextView)findViewById(R.id.Buser4outin);
            User4Frontext = new TextView[TotalHole];
            User4Frame = new FrameLayout[TotalHole];
            for(int i = 0;i<TotalHole/2;i++) {
                int resID = getResources().getIdentifier("user4value" + (i + 1), "id", getPackageName());
                int resID1 = getResources().getIdentifier("frameuser4value" + (i + 1), "id", getPackageName());
                User4Frontext[i] =(TextView)findViewById(resID);
                User4Frame[i] =(FrameLayout) findViewById(resID1);
            }
            for(int i = 0;i<TotalHole/2;i++) {
                int resID = getResources().getIdentifier("Buser4value" + (i + 1), "id", getPackageName());
                int resID1 = getResources().getIdentifier("frameBuser4value" + (i + 1), "id", getPackageName());
                User4Frontext[i+9] =(TextView)findViewById(resID);
                User4Frame[i+9] =(FrameLayout) findViewById(resID1);
            }

            User4FrontMain.setText(""+GolferName[2]);
            User4BackMain.setText(""+GolferName[2]);
            int total4User= 0;
            int total4User1= 0;
            int total4UserPAR= 0;
            for(int i = 0;i <User4Frontext.length;i++)
            {
                if(ScoreValue[User4[i]] == 0) {
                    final int tvalue = i;
                    if(parEachHole[i] == 3)
                    {
                        ScoreValue=ScoreValue3;
                    }
                    else
                    if(parEachHole[i] == 4)
                    {
                        ScoreValue=ScoreValue4;
                    }
                    else
                    if(parEachHole[i] == 5)
                    {
                        ScoreValue=ScoreValue5;
                    }
                    User4Frontext[i].setText("");
                    User4Frontext[i].setBackgroundColor(Color.parseColor("#ebedf1"));
                    User4Frame[i].setBackgroundColor(Color.parseColor("#ebedf1"));

                    User4Frontext[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            editorprofile.putInt("profileno",3);
                            editorprofile.putInt("holeno",tvalue);
                            editorprofile.commit();

                            Log.i("ss", " tvalue=== *****************" +tvalue);

                            Intent PreviousScreen = new Intent(ScoreCard.this, StartRound.class);

                            startActivity(PreviousScreen);
                            finish();
                        }
                    });


                }
                else {
                    User4Frontext[i].setText("" + ScoreValue[User4[i]]);
                    if((ScoreValue[User4[i]]-parEachHole[i])== -2) {
                        User4Frontext[i].setBackgroundResource(R.drawable.round1dark);
                        User4Frontext[i].setTextColor(Color.parseColor("#ffffff"));
                    }
                    else
                    if((ScoreValue[User4[i]]-parEachHole[i])== -1)
                        User4Frontext[i].setBackgroundResource(R.drawable.round2dark);
                    else
                    if((ScoreValue[User4[i]]-parEachHole[i])== 1)
                        User4Frontext[i].setBackgroundResource(R.drawable.square2dark);
                    else
                    if((ScoreValue[User4[i]]-parEachHole[i])>=2) {
                        User4Frontext[i].setBackgroundResource(R.drawable.square1dark);
                        User4Frontext[i].setTextColor(Color.parseColor("#ffffff"));
                    }
                    if(i < TotalHole/2)
                        total4User = total4User + ScoreValue[User4[i]];
                    else
                        total4User1 = total4User1 + ScoreValue[User4[i]];

                    total4UserPAR = total4UserPAR+parEachHole[i];
                }

            }
            editorStatus.putInt("USER4",(total4User+total4User1-total4UserPAR));
            Log.i("dos","USER4 = "+(total4UserPAR-total4User-total4User1));
            editorStatus.commit();
            User4Frontout.setText(""+total4User);
            User4Backout.setText(""+total4User1);
        }

        backlinear = (LinearLayout) findViewById(R.id.backlinear);
        m_titletext.setText(""+sharedpreferencesRS.getString("courseName", ""));
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            ScreenName = bundle.getString("SCREENNAME");

        }

        backlinear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(ScreenName.equals("FROMSTARTROUND")) {

                    Intent PreviousScreen = new Intent(ScoreCard.this, StartRound.class);

                    startActivity(PreviousScreen);
                    finish();
                }
                else
                if(ScreenName.equals("FROMMAPACTIVITY")) {

                    Intent PreviousScreen = new Intent(ScoreCard.this, MapsActivity.class);

                    startActivity(PreviousScreen);
                    finish();
                }else
                if(ScreenName.equals("FROMTEESELECTION")) {

                    Intent PreviousScreen = new Intent(ScoreCard.this, TeeSelection.class);

                    startActivity(PreviousScreen);
                    finish();
                }


            }
        });

        m_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(ScreenName.equals("FROMSTARTROUND")) {


                    Intent PreviousScreen = new Intent(ScoreCard.this, StartRound.class);

                    startActivity(PreviousScreen);
                    finish();
                }
                else
                if(ScreenName.equals("FROMMAPACTIVITY")) {

                    Intent PreviousScreen = new Intent(ScoreCard.this, MapsActivity.class);

                    startActivity(PreviousScreen);
                    finish();
                }else
                if(ScreenName.equals("FROMTEESELECTION")) {

                    Intent PreviousScreen = new Intent(ScoreCard.this, TeeSelection.class);

                    startActivity(PreviousScreen);
                    finish();
                }
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });
    }
    @Override
    public void onBackPressed() {
        if(ScreenName.equals("FROMSTARTROUND")) {
           // SharedPreferences.Editor editorprofile = sharedpresetprofile.edit();
          //  editorprofile.putInt("profileno",0);
           // editorprofile.putInt("holeno",0);
           // editorprofile.commit();
            Intent PreviousScreen = new Intent(ScoreCard.this, StartRound.class);

            startActivity(PreviousScreen);
            finish();
        }
        else
        if(ScreenName.equals("FROMMAPACTIVITY")) {

            Intent PreviousScreen = new Intent(ScoreCard.this, MapsActivity.class);

            startActivity(PreviousScreen);
            finish();
        }else
        if(ScreenName.equals("FROMTEESELECTION")) {

            Intent PreviousScreen = new Intent(ScoreCard.this, TeeSelection.class);

            startActivity(PreviousScreen);
            finish();
        }

        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(ScreenName.equals("FROMSTARTROUND")) {


            Intent PreviousScreen = new Intent(ScoreCard.this, StartRound.class);

            startActivity(PreviousScreen);
            finish();
        }
        else
        if(ScreenName.equals("FROMMAPACTIVITY")) {

            Intent PreviousScreen = new Intent(ScoreCard.this, MapsActivity.class);

            startActivity(PreviousScreen);
            finish();
        }else
        if(ScreenName.equals("FROMTEESELECTION")) {

            Intent PreviousScreen = new Intent(ScoreCard.this, TeeSelection.class);

            startActivity(PreviousScreen);
            finish();
        }
    }

}
