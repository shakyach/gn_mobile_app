package com.golfnationsmob.GolfView.NewRound;

import android.app.Activity;
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
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.golfnationsmob.GolfView.PostScore.MainPostScore;
import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

public class TeeSelection extends Activity {
    Button m_back,m_edit,m_Continue,m_scorecard;
    TextView m_titletext,golfertext,golferteesid,textuser,teehandicap,teehandicapvalue;
    TextView scoreavg,scoreavgvalue;
    TextView Whitemantext1,Redladiestext1,Goldmantext1,Whiteladiestext1,Blackmantext1;
    CheckBox m_whitemen,m_redladies,m_goldmen,m_whiteladies,m_blackmen;

    TextView rating[] ={null,null,null,null,null};
    TextView ratingvalue[] = {null,null,null,null,null};
    TextView slope[]= {null,null,null,null,null};
    TextView slopevalue[] = {null,null,null,null,null};
    TextView yardage[]= {null,null,null,null,null};
    TextView yardagevalue[] = {null,null,null,null,null};
   // String TeesSelected= "";
    String Menbernumber= "";
    String Menbername= "";
    String fromwhere= "";
    int usrId[] = {-1,-1,-1};
    LinearLayout backlinear;
    SharedPreferences sharedpreferencesRS;
    ImageButton profilepic;
    RelativeLayout WhitemanRelativeLayout,RedladiesRelativeLayout,GoldmanRelativeLayout,WhiteladiesRelativeLayout,BlackmanRelativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_tee_selection);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.subheader);
        //#UI INITILIZTION
        m_back = (Button)findViewById(R.id.header_left_btn);
        profilepic = (ImageButton)findViewById(R.id.propic);
        m_Continue = (Button)findViewById(R.id.TeeContinue);
        m_titletext = (TextView)findViewById(R.id.titletext);
        golfertext = (TextView)findViewById(R.id.golfertextid);
        golferteesid = (TextView)findViewById(R.id.golferteesid);
        textuser = (TextView)findViewById(R.id.idyouintee);
        teehandicap = (TextView)findViewById(R.id.teehandicapid);
        teehandicapvalue = (TextView)findViewById(R.id.teehandicapidvalue);
        scoreavg = (TextView)findViewById(R.id.scoreavgid);
        scoreavgvalue = (TextView)findViewById(R.id.scoreavgidvalue);
        backlinear = (LinearLayout) findViewById(R.id.backlinear);
        m_edit = (Button)findViewById(R.id.editbutton);

        final Typeface font = AppFont.setFont(this,AppFont.ROBOTO_REGULAR);
        final Typeface font1 =  AppFont.setFont(this,AppFont.ROBOTO_LIGHT);
        final Typeface font2 = AppFont.setFont(this,AppFont.ROBOTO_MEDIUM);

        setTeesValue(font1,font2,0);
        setTeesValue(font1,font2,1);
        setTeesValue(font1,font2,2);
        setTeesValue(font1,font2,3);
        setTeesValue(font1,font2,4);


        m_titletext. setTypeface(font);
        golfertext. setTypeface(font2);
        golferteesid. setTypeface(font2);
        textuser. setTypeface(font);
        teehandicap. setTypeface(font1);
        teehandicapvalue. setTypeface(font2);
        scoreavg. setTypeface(font1);
        scoreavgvalue. setTypeface(font2);
        m_edit. setTypeface(font2);
//#BUTTON IMPLEMENTATION
        m_scorecard = (Button)findViewById(R.id.scorecardtee);
        Spannable buttonLabelScore = new SpannableString(" SCORECARD");
        buttonLabelScore.setSpan(new ImageSpan(getApplicationContext(), R.drawable.scorecardbutton,
                ImageSpan.ALIGN_BOTTOM), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        m_scorecard.setText(buttonLabelScore);


        Spannable buttonLabelContinue = new SpannableString(" CONTINUE");
        buttonLabelContinue.setSpan(new ImageSpan(getApplicationContext(), R.drawable.trpr1,
                ImageSpan.ALIGN_BOTTOM), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        m_Continue.setText(buttonLabelContinue);

        m_scorecard.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Bundle bundle = new Bundle();

                    bundle.putString("SCREENNAME","FROMTEESELECTION");
                    Intent nextScreen = new Intent(TeeSelection.this, ScoreCard.class);
                    nextScreen.putExtras(bundle);
                    startActivity(nextScreen);
                    finish();
                }
                return false;
            }

        });
        SharedPreferences sharedpreferencestemp= getSharedPreferences("SCREENNAME" , MODE_PRIVATE);
        fromwhere = sharedpreferencestemp.getString("screenname","");
        Bundle bundle = getIntent().getExtras();


 //#############CHECKED UNCHECKED OPTION FOR TEE SELECTION
        m_whitemen = (CheckBox)findViewById(R.id.whitemen);
        m_redladies = (CheckBox)findViewById(R.id.redladies);
        m_goldmen = (CheckBox)findViewById(R.id.goldmen);
        m_whiteladies = (CheckBox)findViewById(R.id.whiteladies);
        m_blackmen = (CheckBox)findViewById(R.id.blackman);

        WhitemanRelativeLayout = (RelativeLayout)findViewById(R.id.whitemanRelativeLayout);
        RedladiesRelativeLayout = (RelativeLayout)findViewById(R.id.redladiesRelativeLayout);
        GoldmanRelativeLayout = (RelativeLayout)findViewById(R.id.goldmanRelativeLayout);
        WhiteladiesRelativeLayout = (RelativeLayout)findViewById(R.id.whiteladiesRelativeLayout);
        BlackmanRelativeLayout = (RelativeLayout)findViewById(R.id.blackmanRelativeLayout);

        Whitemantext1 = (TextView)findViewById(R.id.whitemantext1);
        Whitemantext1.setTypeface(font);

        Redladiestext1 = (TextView)findViewById(R.id.redladiestext1);
        Redladiestext1.setTypeface(font);

        Goldmantext1 = (TextView)findViewById(R.id.goldmantext1);
        Goldmantext1.setTypeface(font);

        Whiteladiestext1 = (TextView)findViewById(R.id.whiteladiestext1);
        Whiteladiestext1.setTypeface(font);

        Blackmantext1 = (TextView)findViewById(R.id.blackmantext1);
        Blackmantext1.setTypeface(font);
        SharedPreferences sharedpreferencesGolfername= getSharedPreferences("AddFriendOnRs", MODE_PRIVATE);
        for(int i =0 ; i<3;i++)
        {

            usrId[i] = sharedpreferencesGolfername.getInt("usrId"+i, -1);

        }

        if(fromwhere.equals("postscore"))
        {
            sharedpreferencesRS= getSharedPreferences("MAINPOSTSCORE" , MODE_PRIVATE);

        }
        else
        sharedpreferencesRS= getSharedPreferences("roundsetting" , MODE_PRIVATE);

        if(bundle != null) {
            Menbernumber = bundle.getString("MemberNumber");
            Log.i("value ","MemberNumber==="+Menbernumber);
            Menbername = bundle.getString("Membername");
            // fromwhere = bundle.getString("FROMACTIVITY_WHERE");
            if(Menbernumber.equals("first"))
            {
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
                    profilepic.setBackgroundDrawable(drawable);
                   // profilepic.setBackgroundDrawable(ob);

                }
                else
                profilepic.setBackgroundResource(R.drawable.profile_default);
              //  m_edit.setVisibility(View.VISIBLE);
            }
            else
            if(Menbernumber.equals("second"))
            {
                SharedPreferences sharedpreferencesProfilePic = getSharedPreferences("profilePic_other", MODE_PRIVATE);
                String decodedData = sharedpreferencesProfilePic.getString("profile_image_player" + (usrId[0]), "");
                if(decodedData.equals(""))
                {
                    Profile.user2profilePic = null;
                }
                else
                    Profile.user2profilePic = Base64.decode(decodedData, Base64.DEFAULT);
                if(Profile.user2profilePic != null)
                {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(Profile.user2profilePic, 0, Profile.user2profilePic.length);
                    RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bitmap);
                    profilepic.setBackgroundDrawable(drawable);

                }
                else
                    profilepic.setBackgroundResource(R.drawable.profile_default);
                m_edit.setVisibility(View.INVISIBLE);
            }
            else
            if(Menbernumber.equals("third"))
            {
                SharedPreferences sharedpreferencesProfilePic = getSharedPreferences("profilePic_other", MODE_PRIVATE);
                String decodedData = sharedpreferencesProfilePic.getString("profile_image_player" + (usrId[1]), "");
                if(decodedData.equals(""))
                {
                    Profile.user3profilePic = null;
                }
                else
                    Profile.user3profilePic = Base64.decode(decodedData, Base64.DEFAULT);
                if(Profile.user3profilePic != null)
                {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(Profile.user3profilePic, 0, Profile.user3profilePic.length);
                    RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bitmap);
                    profilepic.setBackgroundDrawable(drawable);

                }
                else
                    profilepic.setBackgroundResource(R.drawable.profile_default);
                m_edit.setVisibility(View.INVISIBLE);
            }
            else
            if(Menbernumber.equals("forth"))
            {
                SharedPreferences sharedpreferencesProfilePic = getSharedPreferences("profilePic_other", MODE_PRIVATE);
                String decodedData = sharedpreferencesProfilePic.getString("profile_image_player" + (usrId[2]), "");
                if(decodedData.equals(""))
                {
                    Profile.user4profilePic = null;
                }
                else
                    Profile.user4profilePic = Base64.decode(decodedData, Base64.DEFAULT);
                if(Profile.user4profilePic != null)
                {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(Profile.user4profilePic, 0, Profile.user4profilePic.length);
                    RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bitmap);
                    profilepic.setBackgroundDrawable(drawable);

                }
                else
                    profilepic.setBackgroundResource(R.drawable.profile_default);
                m_edit.setVisibility(View.INVISIBLE);
            }

            textuser.setText(""+Menbername);

        }
        if(Menbernumber.equals("first"))
        Init_SetCheckUncheckSelection(sharedpreferencesRS.getString("TessSelectedfirst",""));
        else
        if(Menbernumber.equals("second"))
            Init_SetCheckUncheckSelection(sharedpreferencesRS.getString("TessSelectedsecond",""));
        else
        if(Menbernumber.equals("third"))
            Init_SetCheckUncheckSelection(sharedpreferencesRS.getString("TessSelectedthird",""));
        else
        if(Menbernumber.equals("forth"))
            Init_SetCheckUncheckSelection(sharedpreferencesRS.getString("TessSelectedforth",""));


        m_titletext.setText("Select Tees");

        m_whitemen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(m_whitemen.isChecked()==true && (m_redladies.isChecked()==true||m_goldmen.isChecked()==true||m_whiteladies.isChecked()==true||m_blackmen.isChecked()==true)) {
                    m_whitemen.setChecked(true);
                    m_redladies.setChecked(false);
                    m_goldmen.setChecked(false);
                    m_whiteladies.setChecked(false);
                    m_blackmen.setChecked(false);
                }

                if(m_whitemen.isChecked()==true)
                {
                    setUIcheck(WhitemanRelativeLayout,Whitemantext1,rating[0],ratingvalue[0],slope[0],slopevalue[0],yardage[0],yardagevalue[0],m_whitemen);
                }
                else
                {
                    setUIuncheck(WhitemanRelativeLayout,Whitemantext1,rating[0],ratingvalue[0],slope[0],slopevalue[0],yardage[0],yardagevalue[0],m_whitemen);
                }

            }
        });

        m_redladies.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(m_redladies.isChecked()==true && (m_whitemen.isChecked()==true||m_goldmen.isChecked()==true||m_whiteladies.isChecked()==true||m_blackmen.isChecked()==true)) {
                    m_redladies.setChecked(true);
                    m_whitemen.setChecked(false);
                    m_goldmen.setChecked(false);
                    m_whiteladies.setChecked(false);
                    m_blackmen.setChecked(false);
                }
                if(m_redladies.isChecked()==true)
                {
                    setUIcheck(RedladiesRelativeLayout,Redladiestext1,rating[1],ratingvalue[1],slope[1],slopevalue[1],yardage[1],yardagevalue[1],m_redladies);
                }
                else
                {
                    setUIuncheck(RedladiesRelativeLayout,Redladiestext1,rating[1],ratingvalue[1],slope[1],slopevalue[1],yardage[1],yardagevalue[1],m_redladies);
                }


            }
        });

        m_goldmen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(m_goldmen.isChecked()==true && (m_whitemen.isChecked()==true||m_redladies.isChecked()==true||m_whiteladies.isChecked()==true||m_blackmen.isChecked()==true)) {
                    m_goldmen.setChecked(true);
                    m_whitemen.setChecked(false);
                    m_redladies.setChecked(false);
                    m_whiteladies.setChecked(false);
                    m_blackmen.setChecked(false);
                }

                if(m_goldmen.isChecked()==true)
                {
                    setUIcheck(GoldmanRelativeLayout,Goldmantext1,rating[2],ratingvalue[2],slope[2],slopevalue[2],yardage[2],yardagevalue[2],m_goldmen);
                }
                else
                {
                    setUIuncheck(GoldmanRelativeLayout,Goldmantext1,rating[2],ratingvalue[2],slope[2],slopevalue[2],yardage[2],yardagevalue[2],m_goldmen);
                }

            }
        });

        m_whiteladies.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(m_whiteladies.isChecked()==true && (m_whitemen.isChecked()==true||m_redladies.isChecked()==true||m_goldmen.isChecked()==true||m_blackmen.isChecked()==true)) {
                    m_whiteladies.setChecked(true);
                    m_whitemen.setChecked(false);
                    m_redladies.setChecked(false);
                    m_goldmen.setChecked(false);
                    m_blackmen.setChecked(false);
                }
                if(m_whiteladies.isChecked()==true)
                {
                    setUIcheck(WhiteladiesRelativeLayout,Whiteladiestext1,rating[3],ratingvalue[3],slope[3],slopevalue[3],yardage[3],yardagevalue[3],m_whiteladies);
                }
                else
                {
                    setUIuncheck(WhiteladiesRelativeLayout,Whiteladiestext1,rating[3],ratingvalue[3],slope[3],slopevalue[3],yardage[3],yardagevalue[3],m_whiteladies);

                }


            }
        });

        m_blackmen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(m_blackmen.isChecked()==true && (m_whitemen.isChecked()==true||m_redladies.isChecked()==true||m_goldmen.isChecked()==true||m_whiteladies.isChecked()==true)) {
                    m_blackmen.setChecked(true);
                    m_whitemen.setChecked(false);
                    m_redladies.setChecked(false);
                    m_goldmen.setChecked(false);
                    m_whiteladies.setChecked(false);
                }

                if(m_blackmen.isChecked()==true)
                {

                    setUIcheck(BlackmanRelativeLayout,Blackmantext1,rating[4],ratingvalue[4],slope[4],slopevalue[4],yardage[4],yardagevalue[4],m_blackmen);
                }
                else
                {
                    setUIuncheck(BlackmanRelativeLayout,Blackmantext1,rating[4],ratingvalue[4],slope[4],slopevalue[4],yardage[4],yardagevalue[4],m_blackmen);
                }


            }
        });


        backlinear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(fromwhere.equals("roundsetting")) {

                    Intent PreviousScreen = new Intent(TeeSelection.this, RoundSetting.class);
                    // PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
                else
                if(fromwhere.equals("postscore")) {
                    Intent PreviousScreen = new Intent(TeeSelection.this, MainPostScore.class);
                    // PreviousScreen.putExtras(bundle);
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

                    Intent PreviousScreen = new Intent(TeeSelection.this, RoundSetting.class);
                    // PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
                else
                if(fromwhere.equals("postscore")) {
                    Intent PreviousScreen = new Intent(TeeSelection.this, MainPostScore.class);
                    // PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
                else
                if(fromwhere.equals("StartRound")) {
                    Intent PreviousScreen = new Intent(TeeSelection.this, StartRound.class);
                    // PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

        m_edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Bundle bundle = new Bundle();

                bundle.putString("MemberNumber",Menbernumber);
                bundle.putString("Membername",Menbername);
                bundle.putString("fromscreen","TEESELECTION");
                Intent PreviousScreen = new Intent(TeeSelection.this,Profile.class);
                PreviousScreen.putExtras(bundle);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

        m_Continue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


              //  Bundle bundle = new Bundle();
                String temp= "";
                if(m_whitemen.isChecked())
                    temp="White (Men)";
                else
                if(m_redladies.isChecked())
                    temp="Red (Ladies)";
                else
                if(m_goldmen.isChecked())
                    temp="Gold (Men)";
                else
                if(m_whiteladies.isChecked())
                    temp="White (Ladies)";
                else
                if(m_blackmen.isChecked())
                    temp="Black (Men)";


                if(fromwhere.equals("roundsetting")||fromwhere.equals("StartRound")) {

                    sharedpreferencesRS= getSharedPreferences("roundsetting" , MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferencesRS.edit();

                    if(Menbernumber.equals("first"))
                    {


                            editor.putString("TessSelectedfirst" , ""+temp);
                        editor.commit();

                    }
                    else
                    if(Menbernumber.equals("second"))
                    {


                            editor.putString("TessSelectedsecond" , ""+temp);
                        editor.commit();

                    }
                    else
                    if(Menbernumber.equals("third"))
                    {


                            editor.putString("TessSelectedthird" , ""+temp);
                        editor.commit();

                    }
                    else
                    if(Menbernumber.equals("forth"))
                    {


                        editor.putString("TessSelectedforth" , ""+temp);
                        editor.commit();

                    }

                    if(fromwhere.equals("roundsetting")) {
                        Intent PreviousScreen = new Intent(TeeSelection.this, RoundSetting.class);
                        // PreviousScreen.putExtras(bundle);
                        startActivity(PreviousScreen);
                        finish();
                    }
                    else
                    if(fromwhere.equals("StartRound"))
                    {
                        Intent PreviousScreen = new Intent(TeeSelection.this, StartRound.class);
                        // PreviousScreen.putExtras(bundle);
                        startActivity(PreviousScreen);
                        finish();
                    }
                }
                else
                if(fromwhere.equals("postscore")) {
                    SharedPreferences sharedpreferencesRS= getSharedPreferences("MAINPOSTSCORE" , MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferencesRS.edit();

                    if(Menbernumber.equals("first"))
                    {


                        editor.putString("TessSelectedfirst" , ""+temp);
                        editor.commit();

                    }
                    else
                    if(Menbernumber.equals("second"))
                    {

                        editor.putString("TessSelectedsecond" , ""+temp);
                        editor.commit();

                    }
                    else
                    if(Menbernumber.equals("third"))
                    {

                        editor.putString("TessSelectedthird" , ""+temp);
                        editor.commit();

                    }
                    else
                    if(Menbernumber.equals("forth"))
                    {

                        editor.putString("TessSelectedforth" , ""+temp);
                        editor.commit();

                    }
                    Intent PreviousScreen = new Intent(TeeSelection.this, MainPostScore.class);
                    // PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });
    }

    private void Init_SetCheckUncheckSelection(String option)
    {

        if(option.equals("White (Men)"))
        {
            m_whitemen.setChecked(true);
            setUIcheck(WhitemanRelativeLayout,Whitemantext1,rating[0],ratingvalue[0],slope[0],slopevalue[0],yardage[0],yardagevalue[0],m_whitemen);

        }else
        if(option.equals("Red (Ladies)"))
        {
            m_redladies.setChecked(true);
            setUIcheck(RedladiesRelativeLayout,Redladiestext1,rating[1],ratingvalue[1],slope[1],slopevalue[1],yardage[1],yardagevalue[1],m_redladies);

        }else
        if(option.equals("Gold (Men)"))
        {
            m_goldmen.setChecked(true);
            setUIcheck(GoldmanRelativeLayout,Goldmantext1,rating[2],ratingvalue[2],slope[2],slopevalue[2],yardage[2],yardagevalue[2],m_goldmen);

        }else
        if(option.equals("White (Ladies)"))
        {
            m_whiteladies.setChecked(true);
            setUIcheck(WhiteladiesRelativeLayout,Whiteladiestext1,rating[3],ratingvalue[3],slope[3],slopevalue[3],yardage[3],yardagevalue[3],m_whiteladies);

        }else
        if(option.equals("Black (Men)"))
        {
            m_blackmen.setChecked(true);
            setUIcheck(BlackmanRelativeLayout,Blackmantext1,rating[4],ratingvalue[4],slope[4],slopevalue[4],yardage[4],yardagevalue[4],m_blackmen);

        }

    }

    private void setTeesValue(Typeface font1,Typeface font2,int index)
    {

        int resID1 = getResources().getIdentifier("rating"+(index+1), "id", getPackageName());
        rating[index] = (TextView)findViewById(resID1);
        rating[index].setTypeface(font1);
        int resID2 = getResources().getIdentifier("rating"+(index+1)+"value", "id", getPackageName());
        ratingvalue[index] = (TextView)findViewById(resID2);
        ratingvalue[index].setTypeface(font2);
        int resID3 = getResources().getIdentifier("slope"+(index+1), "id", getPackageName());
        slope[index] = (TextView)findViewById(resID3);
        slope[index].setTypeface(font1);
        int resID4 = getResources().getIdentifier("slope"+(index+1)+"value", "id", getPackageName());
        slopevalue[index] = (TextView)findViewById(resID4);
        slopevalue[index] .setTypeface(font2);
        int resID5 = getResources().getIdentifier("yardage"+(index+1), "id", getPackageName());
        yardage[index] = (TextView)findViewById(resID5);
        yardage[index].setTypeface(font1);
        int resID6 = getResources().getIdentifier("yardage"+(index+1)+"value", "id", getPackageName());
        yardagevalue[index] = (TextView)findViewById(resID6);
        yardagevalue[index].setTypeface(font2);

    }
    private void setUIcheck(RelativeLayout R,TextView t1,TextView t2,TextView t3,
                                   TextView t4,TextView t5,TextView t6,TextView t7,CheckBox check)
    {
        R.setBackgroundColor(Color.parseColor("#accc3f"));
        t1.setTextColor(Color.parseColor("#ffffff"));
        t2.setTextColor(Color.parseColor("#ffffff"));
        t3.setTextColor(Color.parseColor("#ffffff"));
        t4.setTextColor(Color.parseColor("#ffffff"));
        t5.setTextColor(Color.parseColor("#ffffff"));
        t6.setTextColor(Color.parseColor("#ffffff"));
        t7.setTextColor(Color.parseColor("#ffffff"));
        int resID = getResources().getIdentifier("check2", "drawable", getPackageName());
        check.setButtonDrawable(resID);
    }
    private void setUIuncheck(RelativeLayout R,TextView t1,TextView t2,TextView t3,
                                   TextView t4,TextView t5,TextView t6,TextView t7,CheckBox check)
    {
        R.setBackgroundColor(Color.parseColor("#f1f2f5"));
        t1.setTextColor(Color.parseColor("#1a2531"));
        t2.setTextColor(Color.parseColor("#afb0b2"));
        t3.setTextColor(Color.parseColor("#10171f"));
        t4.setTextColor(Color.parseColor("#afb0b2"));
        t5.setTextColor(Color.parseColor("#10171f"));
        t6.setTextColor(Color.parseColor("#afb0b2"));
        t7.setTextColor(Color.parseColor("#10171f"));
        int resID = getResources().getIdentifier("check1", "drawable", getPackageName());
        check.setButtonDrawable(resID);
    }
    @Override
    public void onBackPressed() {


        if(fromwhere.equals("roundsetting")) {

            Intent PreviousScreen = new Intent(TeeSelection.this, RoundSetting.class);
            // PreviousScreen.putExtras(bundle);
            startActivity(PreviousScreen);
            finish();
        }
        else
        if(fromwhere.equals("postscore")) {
            Intent PreviousScreen = new Intent(TeeSelection.this, MainPostScore.class);
            // PreviousScreen.putExtras(bundle);
            startActivity(PreviousScreen);
            finish();
        }
        else
        if(fromwhere.equals("StartRound"))
        {
            Intent PreviousScreen = new Intent(TeeSelection.this, StartRound.class);
            // PreviousScreen.putExtras(bundle);
            startActivity(PreviousScreen);
            finish();
        }
        // Otherwise defer to system default behavior.
        super.onBackPressed();
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
