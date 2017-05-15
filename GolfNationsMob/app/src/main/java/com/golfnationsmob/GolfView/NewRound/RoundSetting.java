package com.golfnationsmob.GolfView.NewRound;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.golfnationsmob.GolfView.MainPage;
import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

public class RoundSetting extends Activity {
    Button m_back, m_scoreselect, m_selectTees, m_course,m_courseright,m_startRound,m_cancel,addbut;
    RelativeLayout m_addmore;
    boolean m_courseEnable;
    TextView m_titletext,m_nearestCourse,m_scoringText;
    String  m_nearestcourse_data = "McDowell Mountain Ranch";
    String  m_Scoringtext = "Stroke Play (Gross)";
    String  m_teeselected = "NO TEES SELECTED";
    String  m_teeselected1 = "NO TEES SELECTED";
    String  m_teeselected2 = "NO TEES SELECTED";
    String  m_teeselected3 = "NO TEES SELECTED";
    TextView coursttext,scoreText,golferText,teeselecttext,teeselecttext1,teeselecttext2,teeselecttext3,openevent,selecttext;
    TextView youtextt[] ={null,null,null,null};
    TextView handicaptext[] = {null,null,null,null};
    ImageView User1propic,User2propic,User3propic,User4propic;
    static byte[] user2profilePic = null;
    static byte[] user3profilePic = null;
    static byte[] user4profilePic = null;

    TextView handicaptextvalue[] ={null,null,null,null};
    LinearLayout backlinear;
    float x1,x2;
    SharedPreferences sharedpreferencesRS;
    SharedPreferences sharedpreferencesAddname;
    LinearLayout Courselinear,Scorelinear;
    String newMember[]= {"","",""};
    int usrId[] = {-1,-1,-1};
    String newMemberpropic[]= {"","",""};
    boolean newMemberbool[]= {false,false,false};
    RelativeLayout Teeslinear[] ={null,null,null,null};//,Teeslinear1,Teeslinear2,Teeslinear3;
    LinearLayout TeeslinearArrow[] ={null,null,null};//,Teeslinear1,Teeslinear2,Teeslinear3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_round_setting);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.subheader);
        m_back = (Button) findViewById(R.id.header_left_btn);
        m_startRound = (Button) findViewById(R.id.idstart);
        m_cancel = (Button) findViewById(R.id.d2);
        m_addmore = (RelativeLayout) findViewById(R.id.idaddmore);
        addbut = (Button) findViewById(R.id.b1);
        m_scoreselect = (Button) findViewById(R.id.scoreselect);
        m_selectTees = (Button) findViewById(R.id.selecttees);
        User1propic = (ImageView) findViewById(R.id.UserpropicPS);
        User2propic = (ImageView) findViewById(R.id.User2propic);
        User3propic = (ImageView) findViewById(R.id.User3propic);
        User4propic = (ImageView) findViewById(R.id.User4propic);
      //  m_course = (Button) findViewById(R.id.refresh);
        m_courseright = (Button) findViewById(R.id.courseselect);
        m_nearestCourse = (TextView) findViewById(R.id.nearestcourse);
        m_scoringText = (TextView) findViewById(R.id.scoringText);
       // m_teesSelected = (TextView) findViewById(R.id.teesSelected);
        Courselinear = (LinearLayout) findViewById(R.id.linearLayoutcourseselect);
        Scorelinear = (LinearLayout) findViewById(R.id.linearLayoutscoringselect);


        SharedPreferences sharedpreferencesProfilePic = getSharedPreferences("profilePic", MODE_PRIVATE);
        String decodedData = sharedpreferencesProfilePic.getString("profile_image", "");
        if(sharedpreferencesProfilePic.getString("profile_image", "").equals(""))
        {
            Profile.user1profilePic = null;
        }
        else
            Profile.user1profilePic = Base64.decode(decodedData, Base64.DEFAULT);
        if(Profile.user1profilePic != null)
        {
            Bitmap bitmap = BitmapFactory.decodeByteArray(Profile.user1profilePic, 0, Profile.user1profilePic.length);
            RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bitmap);
            User1propic.setImageDrawable(drawable);
          //  User1propic.setBackgroundDrawable(ob);

        }
        for(int i = 0;i<4;i++) {
            int resID = getResources().getIdentifier("relativeLayoutteesselect" + i, "id", getPackageName());
            Teeslinear[i] = (RelativeLayout) findViewById(resID);
            int resID1 = getResources().getIdentifier("Youid" + i, "id", getPackageName());
            youtextt[i] = (TextView) findViewById(resID1);
            int resID2 = getResources().getIdentifier("idhandicap" + i, "id", getPackageName());
            handicaptext[i] = (TextView) findViewById(resID2);
            int resID3 = getResources().getIdentifier("idhandicapvalue" + i, "id", getPackageName());
            handicaptextvalue[i] = (TextView) findViewById(resID3);
        }

        for(int i = 0;i<3;i++) {
            int resID = getResources().getIdentifier("teeslinear" + (i+1), "id", getPackageName());
            TeeslinearArrow[i] = (LinearLayout) findViewById(resID);
        }

        backlinear = (LinearLayout) findViewById(R.id.backlinear);
        m_titletext = (TextView)findViewById(R.id.titletext);
        coursttext = (TextView)findViewById(R.id.coursttext);
        scoreText = (TextView)findViewById(R.id.scoreid);
        golferText = (TextView)findViewById(R.id.idgolfer);
        teeselecttext = (TextView)findViewById(R.id.teesSelected);
        teeselecttext1 = (TextView)findViewById(R.id.teesSelected1);
        teeselecttext2 = (TextView)findViewById(R.id.teesSelected2);
        teeselecttext3 = (TextView)findViewById(R.id.teesSelected3);
        openevent = (TextView)findViewById(R.id.openevents);
        selecttext = (TextView)findViewById(R.id.idselect);

//get members from addFriend (Local database for now)
        sharedpreferencesAddname= getSharedPreferences("AddFriendOnRs", MODE_PRIVATE);
        for(int i =0 ; i<3;i++)
        {

            newMember[i] = sharedpreferencesAddname.getString("memberonRS"+i, "");
            newMemberpropic[i] = sharedpreferencesAddname.getString("memberonpropic"+i, "");
            usrId[i] = sharedpreferencesAddname.getInt("usrId"+i, -1);
            newMemberbool[i] = sharedpreferencesAddname.getBoolean("memberonRSB"+i, true);
            Log.i("ss", " usrId *****"+i+"************" +usrId[i]);
            Log.i("ss", " newMember before=== ********" + i + "*********" +  newMember[i]);

        }

       // Log.i("ss", " newMember=== ********" + i + "*********" +  newMember[i]);
        if(newMember[0]!= "null"&& newMember[0]!= "")
        {
            Teeslinear[1].setVisibility(View.VISIBLE);
            int index = (""+newMember[0]).indexOf(" ");
            youtextt[1].setText(""+newMember[0].substring(0,index));
            if(newMemberpropic[0].equals(""))
            {
                user2profilePic = null;
            }
            else
                user2profilePic = Base64.decode(newMemberpropic[0], Base64.DEFAULT);
            if(user2profilePic != null)
            {
                Bitmap bitmap = BitmapFactory.decodeByteArray(user2profilePic, 0, user2profilePic.length);
                RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bitmap);
                User2propic.setImageDrawable(drawable);

            }
        }
        if(newMember[1]!= "null"&& newMember[1]!= "")
        {
            int index = (""+newMember[1]).indexOf(" ");

            Teeslinear[2].setVisibility(View.VISIBLE);
            youtextt[2].setText(""+newMember[1].substring(0,index));
            if(newMemberpropic[1].equals(""))
            {
                user3profilePic = null;
            }
            else
                user3profilePic = Base64.decode(newMemberpropic[1], Base64.DEFAULT);
            if(user3profilePic != null)
            {
                Bitmap bitmap = BitmapFactory.decodeByteArray(user3profilePic, 0, user3profilePic.length);
                RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bitmap);
                User3propic.setImageDrawable(drawable);

            }
        }
        if(newMember[2]!= "null"&& newMember[2]!= "")
        {
            int index = (""+newMember[2]).indexOf(" ");

            Teeslinear[3].setVisibility(View.VISIBLE);
            youtextt[3].setText(""+newMember[2].substring(0,index));
            m_addmore.setVisibility(View.GONE);
            if(newMemberpropic[2].equals(""))
            {
                user4profilePic = null;
            }
            else
                user4profilePic = Base64.decode(newMemberpropic[2], Base64.DEFAULT);
            if(user4profilePic != null)
            {
                Bitmap bitmap = BitmapFactory.decodeByteArray(user4profilePic, 0, user4profilePic.length);
                RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bitmap);
                User4propic.setImageDrawable(drawable);

            }
        }
        User1propic.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Bundle bundle = new Bundle();
                    bundle.putString("MemberNumber","first");
                    bundle.putString("Membername","You");
                    bundle.putString("fromscreen","ROUNDSETTING");
                    Intent PreviousScreen = new Intent(RoundSetting.this,Profile.class);
                    PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
                return false;
            }

        });
        User2propic.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    int index = newMember[0].indexOf(" ");
                    int lastindex = newMember[0].length();
                    String name = newMember[0].substring(0, index);
                    String sname = newMember[0].substring(index+1, lastindex);

                    SharedPreferences sharedpreferences= getSharedPreferences("Mail" , MODE_PRIVATE);

                    SharedPreferences Edituser= getSharedPreferences("useredit", MODE_PRIVATE);
                    SharedPreferences.Editor editor = Edituser.edit();
                    editor.putString("editname",""+name);
                    editor.putString("editsurname",""+sname);
                    editor.putString("editemail",""+(sharedpreferences.getString("email"+(usrId[0]), "")));
                    editor.putInt("edit_id",(usrId[0]));
                    editor.putInt("edit_userindex",0);
                    editor.putString("fromscreen","roundsetting");
                    editor.commit();
                    Intent nextScreen = new Intent(RoundSetting.this, EditFriendInfo.class);
                    startActivity(nextScreen);
                }
                return false;
            }

        });
        User3propic.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    int index = newMember[1].indexOf(" ");
                    int lastindex = newMember[1].length();
                    String name = newMember[1].substring(0, index);
                    String sname = newMember[1].substring(index+1, lastindex);

                    SharedPreferences sharedpreferences= getSharedPreferences("Mail" , MODE_PRIVATE);

                    SharedPreferences Edituser= getSharedPreferences("useredit", MODE_PRIVATE);
                    SharedPreferences.Editor editor = Edituser.edit();
                    editor.putString("editname",""+name);
                    editor.putString("editsurname",""+sname);
                    editor.putString("editemail",""+(sharedpreferences.getString("email"+(usrId[1]), "")));
                    editor.putInt("edit_id",(usrId[1]));
                    editor.putInt("edit_userindex",1);
                    editor.putString("fromscreen","roundsetting");
                    editor.commit();
                    Intent nextScreen = new Intent(RoundSetting.this, EditFriendInfo.class);
                    startActivity(nextScreen);
                }
                return false;
            }

        });

        User4propic.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    int index = newMember[2].indexOf(" ");
                    int lastindex = newMember[2].length();
                    String name = newMember[2].substring(0, index);
                    String sname = newMember[2].substring(index+1, lastindex);

                    SharedPreferences sharedpreferences= getSharedPreferences("Mail" , MODE_PRIVATE);

                    SharedPreferences Edituser= getSharedPreferences("useredit", MODE_PRIVATE);
                    SharedPreferences.Editor editor = Edituser.edit();
                    editor.putString("editname",""+name);
                    editor.putString("editsurname",""+sname);
                    editor.putString("editemail",""+(sharedpreferences.getString("email"+(usrId[2]), "")));
                    editor.putInt("edit_id",(usrId[2]));
                    editor.putInt("edit_userindex",2);
                    editor.putString("fromscreen","roundsetting");
                    editor.commit();
                    Intent nextScreen = new Intent(RoundSetting.this, EditFriendInfo.class);
                    startActivity(nextScreen);
                }
                return false;
            }

        });


        youtextt[0].setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Bundle bundle = new Bundle();
                    bundle.putString("MemberNumber","first");
                    bundle.putString("Membername","You");
                    bundle.putString("fromscreen","ROUNDSETTING");
                    Intent PreviousScreen = new Intent(RoundSetting.this,Profile.class);
                    PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
                return false;
            }

        });
        youtextt[1].setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    int index = newMember[0].indexOf(" ");
                    int lastindex = newMember[0].length();
                    String name = newMember[0].substring(0, index);
                    String sname = newMember[0].substring(index+1, lastindex);

                    SharedPreferences sharedpreferences= getSharedPreferences("Mail" , MODE_PRIVATE);

                    SharedPreferences Edituser= getSharedPreferences("useredit", MODE_PRIVATE);
                    SharedPreferences.Editor editor = Edituser.edit();
                    editor.putString("editname",""+name);
                    editor.putString("editsurname",""+sname);
                    editor.putString("editemail",""+(sharedpreferences.getString("email"+(usrId[0]), "")));
                    editor.putInt("edit_id",(usrId[0]));
                    editor.putInt("edit_userindex",0);
                    editor.putString("fromscreen","roundsetting");
                    editor.commit();
                    Intent nextScreen = new Intent(RoundSetting.this, EditFriendInfo.class);
                    startActivity(nextScreen);
                }
                return false;
            }

        });
        youtextt[2].setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    int index = newMember[1].indexOf(" ");
                    int lastindex = newMember[1].length();
                    String name = newMember[1].substring(0, index);
                    String sname = newMember[1].substring(index+1, lastindex);

                    SharedPreferences sharedpreferences= getSharedPreferences("Mail" , MODE_PRIVATE);

                    SharedPreferences Edituser= getSharedPreferences("useredit", MODE_PRIVATE);
                    SharedPreferences.Editor editor = Edituser.edit();
                    editor.putString("editname",""+name);
                    editor.putString("editsurname",""+sname);
                    editor.putString("editemail",""+(sharedpreferences.getString("email"+(usrId[1]), "")));
                    editor.putInt("edit_id",(usrId[1]));
                    editor.putInt("edit_userindex",1);
                    editor.putString("fromscreen","roundsetting");
                    editor.commit();
                    Intent nextScreen = new Intent(RoundSetting.this, EditFriendInfo.class);
                    startActivity(nextScreen);
                }
                return false;
            }

        });
        youtextt[3].setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    int index = newMember[2].indexOf(" ");
                    int lastindex = newMember[2].length();
                    String name = newMember[2].substring(0, index);
                    String sname = newMember[2].substring(index+1, lastindex);

                    SharedPreferences sharedpreferences= getSharedPreferences("Mail" , MODE_PRIVATE);

                    SharedPreferences Edituser= getSharedPreferences("useredit", MODE_PRIVATE);
                    SharedPreferences.Editor editor = Edituser.edit();
                    editor.putString("editname",""+name);
                    editor.putString("editsurname",""+sname);
                    editor.putString("editemail",""+(sharedpreferences.getString("email"+(usrId[2]), "")));
                    editor.putInt("edit_id",(usrId[2]));
                    editor.putInt("edit_userindex",2);
                    editor.putString("fromscreen","roundsetting");
                    editor.commit();
                    Intent nextScreen = new Intent(RoundSetting.this, EditFriendInfo.class);
                    startActivity(nextScreen);
                }
                return false;
            }

        });

//Custom Font initilization
        Typeface font = AppFont.setFont(this,AppFont.ROBOTO_REGULAR);
        Typeface font1 =  AppFont.setFont(this,AppFont.ROBOTO_MEDIUM);
        Typeface font2 = AppFont.setFont(this,AppFont.ROBOTO_LIGHT);

        m_titletext.setTypeface(font);

        coursttext.setTypeface(font1);
        m_nearestCourse.setTypeface(font);

        scoreText.setTypeface(font1);
        m_scoringText.setTypeface(font);

        golferText.setTypeface(font1);
        teeselecttext.setTypeface(font);
        teeselecttext1.setTypeface(font);
        teeselecttext2.setTypeface(font);
        teeselecttext3.setTypeface(font);


        for(int i = 0;i<4;i++) {
            youtextt[i].setTypeface(font);
            handicaptext[i].setTypeface(font2);
            handicaptextvalue[i].setTypeface(font1);
        }
        m_startRound. setTypeface(font);
        m_cancel. setTypeface(font);
        addbut.setTypeface(font);

        openevent. setTypeface(font1);
        selecttext. setTypeface(font);


//get Value From local database for start a round
        sharedpreferencesRS= getSharedPreferences("roundsetting", MODE_PRIVATE);

        m_nearestcourse_data =  sharedpreferencesRS.getString("courseName", "");
        m_Scoringtext =  sharedpreferencesRS.getString("Scoring", "");
        m_teeselected =  sharedpreferencesRS.getString("TessSelectedfirst", "");
        m_teeselected1=  sharedpreferencesRS.getString("TessSelectedsecond", "");
        m_teeselected2=  sharedpreferencesRS.getString("TessSelectedthird", "");
        m_teeselected3=  sharedpreferencesRS.getString("TessSelectedforth", "");
        SharedPreferences.Editor editor = sharedpreferencesRS.edit();
        Log.i("value ","m_teeselected==="+m_teeselected);
        Log.i("value ","m_teeselected1==="+m_teeselected1);
        Log.i("value ","m_teeselected2==="+m_teeselected2);
        Log.i("value ","m_teeselected3==="+m_teeselected3);

        Log.i("ss", " m_nearestcourse_data" + m_nearestcourse_data);

        if(m_nearestcourse_data.equals("")) {
            m_nearestCourse.setText("McDowell Mountain Ranch");
            m_nearestcourse_data= "McDowell Mountain Ranch";
        }
        else
          m_nearestCourse.setText(m_nearestcourse_data);
        if(m_Scoringtext.equals("")) {
            m_scoringText.setText("Stroke Play (Gross)");
            m_Scoringtext = "Stroke Play (Gross)";
        }
        else
            m_scoringText.setText(m_Scoringtext);
        if(m_teeselected.equals("")) {
            teeselecttext.setText("NO TEES SELECTED");
            m_teeselected = "NO TEES SELECTED";
            editor.putString("TessSelectedfirst",m_teeselected);
            editor.commit();
        }
        else
            teeselecttext.setText(m_teeselected);

        if(m_teeselected1.equals("")) {
            teeselecttext1.setText("NO TEES SELECTED");
            m_teeselected1 = "NO TEES SELECTED";
            editor.putString("TessSelectedsecond",m_teeselected1);
            editor.commit();
        }
        else
            teeselecttext1.setText(m_teeselected1);

        if(m_teeselected2.equals("")) {
            teeselecttext2.setText("NO TEES SELECTED");
            m_teeselected2 = "NO TEES SELECTED";
            editor.putString("TessSelectedthird",m_teeselected2);
            editor.commit();
        }
        else
            teeselecttext2.setText(m_teeselected2);

        if(m_teeselected3.equals("")) {
            teeselecttext3.setText("NO TEES SELECTED");
            m_teeselected3 = "NO TEES SELECTED";
            editor.putString("TessSelectedforth",m_teeselected3);
            editor.commit();
        }
        else
            teeselecttext3.setText(m_teeselected3);



        saveRoundSetting();
//on click event setting navigation to diffenent screens

        //#SELECT COURSE OPTION
        Courselinear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
              //  if (m_courseEnable)
                {
                    SharedPreferences sharedpreferencesRS= getSharedPreferences("SCREENNAME" , MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                    editor.putString("screenname" , "roundsetting");
                    editor.commit();
                    Intent NextScreen = new Intent(RoundSetting.this, SelectCourse.class);
                   // NextScreen.putExtras(bundle);
                    startActivity(NextScreen);
                }


            }
        });

        m_courseright.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
              //  if (m_courseEnable)
                {
                    saveRoundSetting();
                    SharedPreferences sharedpreferencesRS= getSharedPreferences("SCREENNAME" , MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                    editor.putString("screenname" , "roundsetting");
                    editor.commit();
                    Intent NextScreen = new Intent(RoundSetting.this, SelectCourse.class);
                  // NextScreen.putExtras(bundle);
                    startActivity(NextScreen);
                    finish();
                }


            }
        });
//#BACK BUTTON ABOVE
      backlinear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent PreviousScreen = new Intent(RoundSetting.this, MainPage.class);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });
        m_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent PreviousScreen = new Intent(RoundSetting.this, MainPage.class);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

        m_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent PreviousScreen = new Intent(RoundSetting.this, MainPage.class);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

//#ADD MORE FRIENDS
        m_addmore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                saveRoundSetting();
                saveMembers();
                sharedpreferencesAddname= getSharedPreferences("AddFriend" , MODE_PRIVATE);
                final SharedPreferences.Editor editorFR = sharedpreferencesAddname.edit();
                for(int i =0 ; i<3;i++)
                {
                    editorFR.putInt("friendid"+i , -1);
                    editorFR.commit();
                }


                Intent NextScreen = new Intent(RoundSetting.this, AddFriends.class);
            //    NextScreen.putExtras(bundle);
                startActivity(NextScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });
        addbut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                saveRoundSetting();
                saveMembers();
                sharedpreferencesAddname= getSharedPreferences("AddFriend" , MODE_PRIVATE);
                final SharedPreferences.Editor editorFR = sharedpreferencesAddname.edit();
                for(int i =0 ; i<3;i++)
                {
                    editorFR.putInt("friendid"+i , -1);
                    editorFR.commit();
                }


                Intent NextScreen = new Intent(RoundSetting.this, AddFriends.class);
            //    NextScreen.putExtras(bundle);
                startActivity(NextScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

        //#TEE SELECTION
        teeselecttext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //sharedpreferencesRS= getSharedPreferences("roundsetting" , MODE_PRIVATE);
                saveRoundSetting();
                saveMembers();
                SharedPreferences sharedpreferencesRS= getSharedPreferences("SCREENNAME" , MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                editor.putString("screenname" , "roundsetting");
                editor.commit();
                Bundle bundle = new Bundle();
               // bundle.putString("FROMACTIVITY_WHERE","ROUNDSEETING");
                bundle.putString("MemberNumber","first");
                bundle.putString("Membername",""+ youtextt[0].getText());
                Intent NextScreen = new Intent(RoundSetting.this, TeeSelection.class);
                NextScreen.putExtras(bundle);
                startActivity(NextScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });


        TeeslinearArrow[2].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ViewDialog alert = new ViewDialog();
                alert.showDialog(RoundSetting.this, "Remove Golfer ?",3);

            }
        });

        teeselecttext3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                saveRoundSetting();
                saveMembers();
                SharedPreferences sharedpreferencesRS= getSharedPreferences("SCREENNAME" , MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                editor.putString("screenname" , "roundsetting");
                editor.commit();
                Bundle bundle = new Bundle();
                // bundle.putString("FROMACTIVITY_WHERE","ROUNDSEETING");
                bundle.putString("MemberNumber","forth");
                bundle.putString("Membername",""+ youtextt[3].getText());
                Intent NextScreen = new Intent(RoundSetting.this, TeeSelection.class);
                NextScreen.putExtras(bundle);
                startActivity(NextScreen);
                finish();

            }
        });


        m_selectTees.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                saveRoundSetting();
                saveMembers();
                SharedPreferences sharedpreferencesRS= getSharedPreferences("SCREENNAME" , MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                editor.putString("screenname" , "roundsetting");
                editor.commit();
                Bundle bundle = new Bundle();
                //bundle.putString("FROMACTIVITY_WHERE","ROUNDSEETING");
                bundle.putString("MemberNumber","first");
                bundle.putString("Membername",""+ youtextt[0].getText());
                Intent NextScreen = new Intent(RoundSetting.this, TeeSelection.class);
                NextScreen.putExtras(bundle);
                startActivity(NextScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

        //# REMOVE GOLFER FROM ROUND LIST
        TeeslinearArrow[0].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ViewDialog alert = new ViewDialog();
                alert.showDialog(RoundSetting.this, "Remove Golfer ?",1);

            }
        });

        teeselecttext1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                saveRoundSetting();
                saveMembers();
                SharedPreferences sharedpreferencesRS= getSharedPreferences("SCREENNAME" , MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                editor.putString("screenname" , "roundsetting");
                editor.commit();
                Bundle bundle = new Bundle();
                // bundle.putString("FROMACTIVITY_WHERE","ROUNDSEETING");
                bundle.putString("MemberNumber","second");
                bundle.putString("Membername",""+ youtextt[1].getText());
                Intent NextScreen = new Intent(RoundSetting.this, TeeSelection.class);
                NextScreen.putExtras(bundle);
                startActivity(NextScreen);
                finish();

            }
        });


        TeeslinearArrow[1].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ViewDialog alert = new ViewDialog();
                alert.showDialog(RoundSetting.this, "Remove Golfer ?",2);
            }
        });

        teeselecttext2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                saveRoundSetting();
                saveMembers();
                SharedPreferences sharedpreferencesRS= getSharedPreferences("SCREENNAME" , MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                editor.putString("screenname" , "roundsetting");
                editor.commit();
                Bundle bundle = new Bundle();
                // bundle.putString("FROMACTIVITY_WHERE","ROUNDSEETING");
                bundle.putString("MemberNumber","third");
                bundle.putString("Membername",""+ youtextt[2].getText());
                Intent NextScreen = new Intent(RoundSetting.this, TeeSelection.class);
                NextScreen.putExtras(bundle);
                startActivity(NextScreen);
                finish();

            }
        });

        //#START A NEW ROUND
        m_startRound.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    m_startRound.setBackgroundColor(Color.parseColor("#51677e"));
                   // m_startRound.setTextColor(Color.parseColor("#98b5d3"));
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    m_startRound.setBackgroundColor(Color.parseColor("#10171f"));
                    m_startRound.setTextColor(Color.WHITE);
                    saveRoundSetting();
                    saveMembers();
                    SharedPreferences sharedpresetprofile= getSharedPreferences("profileset", MODE_PRIVATE);
                    sharedpresetprofile.edit().clear().commit();
                    SharedPreferences userStatus= getSharedPreferences("USERSTATUS", MODE_PRIVATE);
                    userStatus.edit().clear().commit();
                    SharedPreferences scoringDB= getSharedPreferences("Scoring", MODE_PRIVATE);
                    scoringDB.edit().clear().commit();

                    SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                    sharedpreferencesCLUB.edit().clear().commit();
                    Intent NextScreen = new Intent(RoundSetting.this, StartRound.class);
               //     NextScreen.putExtras(bundle);
                    startActivity(NextScreen);
                    finish();
                }
                return false;
            }

        });

        //#SCORING OPTION
        Scorelinear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                saveRoundSetting();
                Intent NextScreen = new Intent(RoundSetting.this, Scoring.class);
             //   NextScreen.putExtras(bundle);
                startActivity(NextScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

        m_scoreselect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                saveRoundSetting();
                Intent NextScreen = new Intent(RoundSetting.this, Scoring.class);
                //NextScreen.putExtras(bundle);
                startActivity(NextScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });



    }

    @Override
    public void onBackPressed() {

        Intent PreviousScreen = new Intent(RoundSetting.this, MainPage.class);
        startActivity(PreviousScreen);
        finish();
        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }
    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();


    }


    private void saveRoundSetting()
    {
        sharedpreferencesRS= getSharedPreferences("roundsetting" , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferencesRS.edit();
        editor.putString("TessSelectedfirst" , ""+m_teeselected);
        editor.putString("TessSelectedsecond" , ""+m_teeselected1);
        editor.putString("TessSelectedthird" , ""+m_teeselected2);
        editor.putString("TessSelectedforth" , ""+m_teeselected3);
        editor.putString("Scoring" , ""+m_Scoringtext);
        editor.putString("courseName" , ""+m_nearestcourse_data);
        editor.commit();
    }

    private void saveMembers()
    {
        SharedPreferences sharedpreferencesselectedMemberonRS;
        sharedpreferencesselectedMemberonRS= getSharedPreferences("AddFriendOnRs", MODE_PRIVATE);
        SharedPreferences.Editor editorRS = sharedpreferencesselectedMemberonRS.edit();
        if(newMember[0] == "" && newMember[1] != "" && newMember[2] != "")
        {
            editorRS.putString("memberonRS"+0 , newMember[1]);
            editorRS.putString("memberonRS"+1 , newMember[2]);
            editorRS.putString("memberonRS"+2 , "");
            editorRS.putBoolean("memberonRSB"+0 , newMemberbool[1]);
            editorRS.putBoolean("memberonRSB"+1 , newMemberbool[2]);
            editorRS.putBoolean("memberonRSB"+2 , false);

            editorRS.commit();
        }
        else
        if(newMember[0] != "" && newMember[1] == "" && newMember[2] != "")
        {
            editorRS.putString("memberonRS"+0 , newMember[0]);
            editorRS.putString("memberonRS"+1 , newMember[2]);
            editorRS.putString("memberonRS"+2 , "");
            editorRS.putBoolean("memberonRSB"+0 , newMemberbool[0]);
            editorRS.putBoolean("memberonRSB"+1 , newMemberbool[2]);
            editorRS.putBoolean("memberonRSB"+2 , false);
            editorRS.commit();
        }
        else
        if(newMember[0] != "" && newMember[1] != "" && newMember[2] == "")
        {
            editorRS.putString("memberonRS"+0 , newMember[0]);
            editorRS.putString("memberonRS"+1 , newMember[1]);
            editorRS.putString("memberonRS"+2 , "");
            editorRS.putBoolean("memberonRSB"+0 , newMemberbool[0]);
            editorRS.putBoolean("memberonRSB"+1 , newMemberbool[1]);
            editorRS.putBoolean("memberonRSB"+2 , false);
            editorRS.commit();
        }
        else
        if(newMember[0] == "" && newMember[1] == "" && newMember[2] != "")
        {
            editorRS.putString("memberonRS"+0 , newMember[2]);
            editorRS.putString("memberonRS"+1 , "");
            editorRS.putString("memberonRS"+2 , "");
            editorRS.putBoolean("memberonRSB"+0 , newMemberbool[2]);
            editorRS.putBoolean("memberonRSB"+1 ,false);
            editorRS.putBoolean("memberonRSB"+2 , false);
            editorRS.commit();
        }
        else
        if(newMember[0] == "" && newMember[1] != "" && newMember[2] == "")
        {
            editorRS.putString("memberonRS"+0 , newMember[1]);
            editorRS.putString("memberonRS"+1 , "");
            editorRS.putString("memberonRS"+2 , "");
            editorRS.putBoolean("memberonRSB"+0 , newMemberbool[1]);
            editorRS.putBoolean("memberonRSB"+1 , false);
            editorRS.putBoolean("memberonRSB"+2 , false);
            editorRS.commit();
        }
        else
        if(newMember[0] != "" && newMember[1]== "" && newMember[2] == "")
        {
            editorRS.putString("memberonRS"+0 , newMember[0]);
            editorRS.putString("memberonRS"+1 , "");
            editorRS.putString("memberonRS"+2 , "");
            editorRS.putBoolean("memberonRSB"+0 , newMemberbool[0]);
            editorRS.putBoolean("memberonRSB"+1 , false);
            editorRS.putBoolean("memberonRSB"+2 , false);
            editorRS.commit();
        }
        else
        if(newMember[0] == "" && newMember[1] == "" && newMember[2] == "")
        {
            editorRS.putString("memberonRS"+0 , "");
            editorRS.putString("memberonRS"+1 , "");
            editorRS.putString("memberonRS"+2 , "");
            editorRS.putBoolean("memberonRSB"+0 , false);
            editorRS.putBoolean("memberonRSB"+1 , false);
            editorRS.putBoolean("memberonRSB"+2 , false);
            editorRS.commit();
        }
    }


    //#DIALOG BOX FOR REMOVE GOLFER
    private class ViewDialog {

        public void showDialog(Activity activity, String msg,int value){
            final int valuein = value;
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialogyesno);

            TextView text = (TextView) dialog.findViewById(R.id.dialogtext);
            text.setText(msg);

            Button dialogButton = (Button) dialog.findViewById(R.id.d2);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(valuein == 1)
                    {
                        Teeslinear[1].setVisibility(View.GONE);
                        SharedPreferences sharedpreferencesselectedMemberonRS;
                        sharedpreferencesselectedMemberonRS= getSharedPreferences("AddFriendOnRs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferencesselectedMemberonRS.edit();
                        int count = sharedpreferencesselectedMemberonRS.getInt("memberCount", 0);
                        count--;
                        if(count <3)
                            m_addmore.setVisibility(View.VISIBLE);
                        editor.putInt("memberCount" , count);
                        Log.i("ss", " count 11*****************" +count);
                        editor.commit();
                        String t1= newMember[0];
                        String t2= newMember[1];
                        String t3= newMember[2];

                        newMember[0] = "";

                        SharedPreferences sharedpreferencesName;
                        sharedpreferencesName= getSharedPreferences("Name", MODE_PRIVATE);
                        SharedPreferences.Editor editor11 = sharedpreferencesName.edit();
                       //  Map<String, ?> allEntries = sharedpreferencesName.getAll();
                       // Log.i("ss", "  allEntries above ******"+allEntries.size());
                        //Log.i("ss", "  t1 ******"+t1);
                        editor11.putString("name"+usrId[0] , t1);
                        editor11.commit();
                        sharedpreferencesselectedMemberonRS = getSharedPreferences("AddFriendOnRs", MODE_PRIVATE);
                        SharedPreferences.Editor editorRS = sharedpreferencesselectedMemberonRS.edit();
                        editorRS.putInt("usrId0", usrId[1]);
                        editorRS.putInt("usrId1", usrId[2]);
                        editorRS.putString("memberonpropic0",newMemberpropic[1]);
                        editorRS.putString("memberonpropic1",newMemberpropic[2]);
                        editorRS.commit();
                     //   Log.i("ss", "  allEntries above ***222***"+allEntries.size());

                       /* SharedPreferences sharedpreferencesMember;
                        sharedpreferencesMember= getSharedPreferences("membership", MODE_PRIVATE);
                        SharedPreferences.Editor editor12 = sharedpreferencesMember.edit();
                         Map<String, ?> allEntries11 = sharedpreferencesMember.getAll();
                        editor12.putBoolean("memebership"+(allEntries11.size()+1) , sharedpreferencesselectedMemberonRS.getBoolean("memberonRSB"+0,true));
                        editor12.commit();*/



                    }
                    else
                    if(valuein == 2)
                    {
                        Teeslinear[2].setVisibility(View.GONE);
                        SharedPreferences sharedpreferencesselectedMemberonRS;
                        sharedpreferencesselectedMemberonRS= getSharedPreferences("AddFriendOnRs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferencesselectedMemberonRS.edit();
                        int count = sharedpreferencesselectedMemberonRS.getInt("memberCount", 0);
                        count--;
                        if(count <3)
                            m_addmore.setVisibility(View.VISIBLE);
                        editor.putInt("memberCount" , count);
                        Log.i("ss", " count 22*****************" +count);
                        editor.commit();
                        String t1= newMember[0];
                        String t2= newMember[1];
                        String t3= newMember[2];
                        newMember[1] = "";

                        SharedPreferences sharedpreferencesName;
                        sharedpreferencesName= getSharedPreferences("Name", MODE_PRIVATE);
                        SharedPreferences.Editor editor11 = sharedpreferencesName.edit();
                       //  Map<String, ?> allEntries = sharedpreferencesName.getAll();

                        editor11.putString("name"+usrId[1] , t2);

                        editor11.commit();
                        sharedpreferencesselectedMemberonRS = getSharedPreferences("AddFriendOnRs", MODE_PRIVATE);
                        SharedPreferences.Editor editorRS = sharedpreferencesselectedMemberonRS.edit();
                        editorRS.putInt("usrId1", usrId[2]);
                        editorRS.putString("memberonpropic1",newMemberpropic[2]);
                        editorRS.commit();
                       /* SharedPreferences sharedpreferencesMember;
                        sharedpreferencesMember= getSharedPreferences("membership", MODE_PRIVATE);
                        SharedPreferences.Editor editor12 = sharedpreferencesMember.edit();
                         Map<String, ?> allEntries11 = sharedpreferencesMember.getAll();
                        editor12.putBoolean("memebership"+(allEntries11.size()+1) , sharedpreferencesselectedMemberonRS.getBoolean("memberonRSB"+1,true));
                        editor12.commit();*/


                    }
                    else
                    if(valuein == 3)
                    {
                        Teeslinear[3].setVisibility(View.GONE);
                        SharedPreferences sharedpreferencesselectedMemberonRS;
                        sharedpreferencesselectedMemberonRS= getSharedPreferences("AddFriendOnRs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferencesselectedMemberonRS.edit();
                        int count = sharedpreferencesselectedMemberonRS.getInt("memberCount", 0);
                        count--;
                        if(count <3)
                            m_addmore.setVisibility(View.VISIBLE);
                        editor.putInt("memberCount" , count);
                        Log.i("ss", " count 33*****************" +count);
                        editor.commit();
                        String t1= newMember[0];
                        String t2= newMember[1];
                        String t3= newMember[2];

                        newMember[2] = "";

                        SharedPreferences sharedpreferencesName;
                        sharedpreferencesName= getSharedPreferences("Name", MODE_PRIVATE);
                        SharedPreferences.Editor editor11 = sharedpreferencesName.edit();
                      //  Map<String, ?> allEntries = sharedpreferencesName.getAll();
                        editor11.putString("name"+usrId[2], t3);
                      //  Log.i("ss", "  allEntries above ***222***"+allEntries.size());
                        editor11.commit();
                       /* SharedPreferences sharedpreferencesMember;
                        sharedpreferencesMember= getSharedPreferences("membership", MODE_PRIVATE);
                        SharedPreferences.Editor editor12 = sharedpreferencesMember.edit();
                         Map<String, ?> allEntries11 = sharedpreferencesMember.getAll();
                        editor12.putBoolean("memebership"+(allEntries11.size()+1) , sharedpreferencesselectedMemberonRS.getBoolean("memberonRSB"+2,true));
                        editor12.commit();*/

                    }
                    dialog.dismiss();
                }
            });

            Button dialogButton1 = (Button) dialog.findViewById(R.id.d1);
            dialogButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            dialog.show();

        }
    }

    private RoundedBitmapDrawable createRoundedBitmapDrawableWithBorder(Bitmap bitmap){
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        int borderWidthHalf = 4; // In pixels
        //Toast.makeText(mContext,""+bitmapWidth+"|"+bitmapHeight,Toast.LENGTH_SHORT).show();

        // Calculate the bitmap radius
        int bitmapRadius = Math.min(bitmapWidth,bitmapHeight)/2;

        int bitmapSquareWidth = Math.min(bitmapWidth,bitmapHeight);
        //Toast.makeText(mContext,""+bitmapMin,Toast.LENGTH_SHORT).show();

        int newBitmapSquareWidth = bitmapSquareWidth+borderWidthHalf;
        //Toast.makeText(mContext,""+newBitmapMin,Toast.LENGTH_SHORT).show();

        /*
            Initializing a new empty bitmap.
            Set the bitmap size from source bitmap
            Also add the border space to new bitmap
        */
        Bitmap roundedBitmap = Bitmap.createBitmap(newBitmapSquareWidth,newBitmapSquareWidth,Bitmap.Config.ARGB_8888);

        /*
            Canvas
                The Canvas class holds the "draw" calls. To draw something, you need 4 basic
                components: A Bitmap to hold the pixels, a Canvas to host the draw calls (writing
                into the bitmap), a drawing primitive (e.g. Rect, Path, text, Bitmap), and a paint
                (to describe the colors and styles for the drawing).

            Canvas(Bitmap bitmap)
                Construct a canvas with the specified bitmap to draw into.
        */
        // Initialize a new Canvas to draw empty bitmap
        Canvas canvas = new Canvas(roundedBitmap);

        /*
            drawColor(int color)
                Fill the entire canvas' bitmap (restricted to the current clip) with the specified
                color, using srcover porterduff mode.
        */
        // Draw a solid color to canvas
        //   canvas.drawColor(Color.RED);

        // Calculation to draw bitmap at the circular bitmap center position
        int x = borderWidthHalf + bitmapSquareWidth - bitmapWidth;
        int y = borderWidthHalf + bitmapSquareWidth - bitmapHeight;

        /*
            drawBitmap(Bitmap bitmap, float left, float top, Paint paint)
                Draw the specified bitmap, with its top/left corner at (x,y), using the specified
                paint, transformed by the current matrix.
        */
        /*
            Now draw the bitmap to canvas.
            Bitmap will draw its center to circular bitmap center by keeping border spaces
        */
        canvas.drawBitmap(bitmap, x-3, y-3, null);

        // Initializing a new Paint instance to draw circular border
        Paint borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(borderWidthHalf*2);
        borderPaint.setColor(Color.parseColor("#99b5d5"));

        /*
            drawCircle(float cx, float cy, float radius, Paint paint)
                Draw the specified circle using the specified paint.
        */
        /*
            Draw the circular border to bitmap.
            Draw the circle at the center of canvas.
         */
        canvas.drawCircle(canvas.getWidth()/2, canvas.getWidth()/2, newBitmapSquareWidth/2, borderPaint);

        /*
            RoundedBitmapDrawable
                A Drawable that wraps a bitmap and can be drawn with rounded corners. You can create
                a RoundedBitmapDrawable from a file path, an input stream, or from a Bitmap object.
        */
        /*
            public static RoundedBitmapDrawable create (Resources res, Bitmap bitmap)
                Returns a new drawable by creating it from a bitmap, setting initial target density
                based on the display metrics of the resources.
        */
        /*
            RoundedBitmapDrawableFactory
                Constructs RoundedBitmapDrawable objects, either from Bitmaps directly, or from
                streams and files.
        */
        // Create a new RoundedBitmapDrawable
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(),roundedBitmap);

        /*
            setCornerRadius(float cornerRadius)
                Sets the corner radius to be applied when drawing the bitmap.
        */
        // Set the corner radius of the bitmap drawable
        roundedBitmapDrawable.setCornerRadius(bitmapRadius);

        /*
            setAntiAlias(boolean aa)
                Enables or disables anti-aliasing for this drawable.
        */
        roundedBitmapDrawable.setAntiAlias(true);

        // Return the RoundedBitmapDrawable
        return roundedBitmapDrawable;
    }

}
