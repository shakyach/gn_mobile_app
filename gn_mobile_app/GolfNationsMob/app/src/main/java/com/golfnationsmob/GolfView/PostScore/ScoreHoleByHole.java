package com.golfnationsmob.GolfView.PostScore;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.golfnationsmob.GolfModel.MyKeyBoard;
import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

public class ScoreHoleByHole extends Activity {
    Button m_back,m_cancel,m_postscore;
    TextView m_titletext;
    EditText HoleValue[]= {null,null,null,null,null,null,null,null,null
                           ,null,null,null,null,null,null,null,null,null};
    TextView inout1,inout2,Toatalscore,HBHFront,HBHBack;
    HorizontalScrollView frontScroll,backScroll;
    int parEachHole[] = {4,5,3,4,5,4,4,4,4,5,3,4,3,4,3,4,4,4};
    LinearLayout backlinear;
    protected KeyboardView keyboardView;
    Keyboard keyboard;
    ScrollView vertScroll;
    LinearLayout linearHbh,lenearButtonHBH;
    boolean firsttime;
    MyKeyBoard mykey;
    EditText finalscore[] = {null,null,null};//,totalputts,fairwayHit,GIR;
    LinearLayout linearHBH[] = {null,null,null};
    LinearLayout linearMore,linearLess;
    String edittextValue[] = {"TP","FH","GIR"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_score_hole_by_hole);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.subheader);
        m_back = (Button) findViewById(R.id.header_left_btn);
        m_cancel = (Button) findViewById(R.id.HBHCANCEL);
        m_postscore = (Button) findViewById(R.id.HBHpostscore);
        m_titletext = (TextView)findViewById(R.id.titletext);
        inout1 = (TextView)findViewById(R.id.HBH_EditText_outin1);
        inout2 = (TextView)findViewById(R.id.HBH_EditText_outin2);
        HBHFront = (TextView)findViewById(R.id.HBHFronttext);
        HBHBack = (TextView)findViewById(R.id.HBHBacktext);
        Toatalscore = (TextView)findViewById(R.id.HBHTextFS);
        backlinear = (LinearLayout) findViewById(R.id.backlinear);
        frontScroll = (HorizontalScrollView)findViewById(R.id.horizontalScrollViewFront);
        backScroll = (HorizontalScrollView)findViewById(R.id.horizontalScrollViewBack);
        vertScroll = (ScrollView) findViewById(R.id.scrollView7);
        linearHbh = (LinearLayout) findViewById(R.id.linearHBH);
        lenearButtonHBH = (LinearLayout) findViewById(R.id.linearLayoutbuttonHBH);

        for(int i = 0;i<3;i++) {
            int resID = getResources().getIdentifier("HBHeditText" +edittextValue[i], "id", getPackageName());
            finalscore[i] = (EditText) findViewById(resID);
        }
        for(int i = 0;i<3;i++) {
            int resID = getResources().getIdentifier("HBHlinear" + (i+2), "id", getPackageName());
            linearHBH[i] = (LinearLayout) findViewById(resID);
        }

        linearMore = (LinearLayout)findViewById(R.id.HBHlinMorestats);
        linearLess = (LinearLayout)findViewById(R.id.HBHlinLessstats);
        linearMore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                linearMore.setVisibility(LinearLayout.GONE);
                linearLess.setVisibility(LinearLayout.VISIBLE);
                for(int i = 0;i<3;i++) {
                    linearHBH[i].setVisibility(LinearLayout.VISIBLE);
                }


            }
        });

        linearLess.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                linearMore.setVisibility(LinearLayout.VISIBLE);
                linearLess.setVisibility(LinearLayout.GONE);
                for(int i = 0;i<3;i++) {
                    linearHBH[i].setVisibility(LinearLayout.GONE);
                }

            }
        });
        for(int i = 0;i<finalscore.length;i++)
        {
            final int t = i;
            finalscore[i].addTextChangedListener(new TextWatcher() {

                public void onTextChanged(CharSequence s, int start,int before, int count)
                {
                    // TODO Auto-generated method stub

                }
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                    // TODO Auto-generated method stub

                }

                public void afterTextChanged(Editable s) {
                    // TODO Auto-generated method stub
                }

            });

            // Make the custom keyboard appear
            finalscore[i].setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {


                    if(firsttime) {
                        if (hasFocus) showCustomKeyboard(v);
                        else hideCustomKeyboard();
                    }
                    if(!firsttime)firsttime = true;
                }
            });

            finalscore[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCustomKeyboard(v);
                }
            });
            finalscore[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    EditText edittext = (EditText) v;
                    int inType = edittext.getInputType();       // Backup the input type
                    edittext.setInputType(InputType.TYPE_NULL); // Disable standard keyboard
                    edittext.onTouchEvent(event);               // Call native handler
                    edittext.setInputType(inType);              // Restore input type
                    return true; // Consume touch event
                }
            });
        }

        m_titletext.setText("Hole by Hole");
        //Custom Font initilization
        Typeface font = AppFont.setFont(this,AppFont.ROBOTO_REGULAR);
        Typeface font1 =  AppFont.setFont(this,AppFont.ROBOTO_MEDIUM);
        m_titletext.setTypeface(font);
        m_cancel.setTypeface(font);
        m_postscore.setTypeface(font);
        HBHFront.setTypeface(font1);
        HBHBack.setTypeface(font1);

        keyboardView = (MyKeyBoard)findViewById(R.id.keyboard_view);

        keyboardView.setPreviewEnabled(false);
        keyboard = new Keyboard(this, R.layout.keyboards);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(keyboardActionListener);

        m_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent PreviousScreen = new Intent(ScoreHoleByHole.this, MainPostScore.class);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });
 m_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent PreviousScreen = new Intent(ScoreHoleByHole.this, MainPostScore.class);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

        linearHbh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                hideCustomKeyboard();

            }
        });

        backlinear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent PreviousScreen = new Intent(ScoreHoleByHole.this, MainPostScore.class);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });


        m_postscore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                keyboardView.setVisibility(View.GONE);

                int ValueEmpty = 0;
                for(int k = 0;k<HoleValue.length;k++) {
                    if (""+HoleValue[k].getText() == "")
                    {
                        Log.i("value","HoleValue[t].getText()=========="+HoleValue[k].getText());
                        ValueEmpty++;
                    }
                }
                Log.i("value","ValueEmpty=========="+ValueEmpty);
                if(ValueEmpty > 0)
                {
                    Toast.makeText(getApplicationContext(), "Some score Fields are missing", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Score Posted.", Toast.LENGTH_SHORT).show();
                    Intent PreviousScreen = new Intent(ScoreHoleByHole.this, MainPostScore.class);
                    startActivity(PreviousScreen);
                    finish();

                }

            }
        });


        for(int i = 0;i<HoleValue.length;i++) {
            int resID = getResources().getIdentifier("HBH_EditText_" + (i + 1), "id", getPackageName());
            HoleValue[i] = (EditText) findViewById(resID);
        }


        for(int i = 0;i<HoleValue.length;i++) {
           final int t = i;
            HoleValue[i].addTextChangedListener(new TextWatcher() {

                public void onTextChanged(CharSequence s, int start,int before, int count)
                {
                    // TODO Auto-generated method stub
                    int HoleValue1 = 0;
                    Log.i("value","HoleValue[t].getText()=========="+HoleValue[t].getText());

                        Log.i("value","here in 2222222222222222");
                    if( (""+HoleValue[t].getText()).equals("0"))
                    {
                        Log.i("value","HoleValue[t].getText()  inner=========="+HoleValue[t].getText());
                        HoleValue[t].setText("");
                    }
                    if( ""+HoleValue[t].getText()!="")
                    {
                       setScoreImage(HoleValue[t],parEachHole[t]);
                    }
                    else
                    {
                       HoleValue[t].setBackgroundResource(0);
                       HoleValue[t].setBackgroundColor(Color.parseColor("#ffffff"));
                       HoleValue[t].setTextColor(Color.parseColor("#10171f"));

                    }

                    if(t == HoleValue.length/4) {

                        frontScroll.post(new Runnable() {
                            @Override
                            public void run() {
                                frontScroll.scrollTo(frontScroll.getRight(),0 );
                            }
                        });

                        Log.i("ss", " t=== *****************" +t);
                    }

                    if(t == 3*HoleValue.length/4) {

                        backScroll.post(new Runnable() {
                            @Override
                            public void run() {
                                backScroll.scrollTo(backScroll.getRight(),0 );
                            }
                        });
                        backScroll.scrollTo(backScroll.getRight(),0 );
                        Log.i("ss", " t=== *****************" +t);
                    }
                    for(int k = 0;k<HoleValue.length/2;k++) {
                        if ("" + HoleValue[k].getText() != "")
                            HoleValue1 = HoleValue1 + Integer.parseInt("" + HoleValue[k].getText());
                    }
                    if(HoleValue1 != 0)
                        inout1.setText(""+HoleValue1);
                    else
                        inout1.setText("");

                    int HoleValue2 = 0;
                    for(int k = HoleValue.length/2;k<HoleValue.length;k++) {
                        if ("" + HoleValue[k].getText() != "")
                            HoleValue2 = HoleValue2 + Integer.parseInt("" + HoleValue[k].getText());
                    }
                    if(HoleValue2 != 0)
                        inout2.setText(""+HoleValue2);
                    else
                        inout2.setText("");
                    if((HoleValue2+HoleValue1) != 0)
                        Toatalscore.setText(""+(HoleValue1+HoleValue2));
                    else
                        Toatalscore.setText("");
                    if(t<17 && ""+HoleValue[t].getText()!="")
                      HoleValue[t+1].requestFocus();
                    else
                    if(t==17 && ""+HoleValue[t].getText()!="")
                    {
                        hideCustomKeyboard();
                    }
                }
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                    // TODO Auto-generated method stub

                }

                public void afterTextChanged(Editable s) {
                    // TODO Auto-generated method stub
                }

            });

            // Make the custom keyboard appear
           HoleValue[i].setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(firsttime) {
                        if (hasFocus) showCustomKeyboard(v);
                        else hideCustomKeyboard();
                    }
                    if(!firsttime)firsttime = true;
                }
            });

            HoleValue[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCustomKeyboard(v);
                }
            });
            HoleValue[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    EditText edittext = (EditText) v;
                    int inType = edittext.getInputType();       // Backup the input type
                    edittext.setInputType(InputType.TYPE_NULL); // Disable standard keyboard
                    edittext.onTouchEvent(event);               // Call native handler
                    edittext.setInputType(inType);              // Restore input type
                    return true; // Consume touch event
                }
            });
        }

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private void setScoreImage(TextView score,int valueOfpar)
    {
        if((Integer.parseInt(""+score.getText())-valueOfpar)<= -2) {
            score.setBackgroundResource(R.drawable.round1dark);
            score.setTextColor(Color.parseColor("#ffffff"));
        }
        else
        if((Integer.parseInt(""+score.getText())-valueOfpar)== -1) {
            score.setBackgroundResource(R.drawable.round2dark);
            score.setTextColor(Color.parseColor("#000000"));
        }
        else
        if((Integer.parseInt(""+score.getText())-valueOfpar)== 1) {
            score.setBackgroundResource(R.drawable.square2dark);
            score.setTextColor(Color.parseColor("#000000"));
        }
        else
        if((Integer.parseInt(""+score.getText())-valueOfpar)>=2) {
            score.setBackgroundResource(R.drawable.square1dark);
            score.setTextColor(Color.parseColor("#ffffff"));
        }
    }


    public KeyboardView.OnKeyboardActionListener keyboardActionListener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int primaryCode) { }
        @Override
        public void onRelease(int primaryCode) { }
        @Override
        public void onKey(int primaryCode, int[] keyCodes) {
            long eventTime = System.currentTimeMillis();
            KeyEvent event = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_DOWN, primaryCode, 0, 0, 0, 0, KeyEvent.FLAG_SOFT_KEYBOARD | KeyEvent.FLAG_KEEP_TOUCH_MODE);
            dispatchKeyEvent(event);
            if(primaryCode == KeyEvent.KEYCODE_BACK){
                if( isCustomKeyboardVisible() ) hideCustomKeyboard();
            }
        }
        @Override
        public void onText(CharSequence text) { }
        @Override
        public void swipeLeft() { }
        @Override
        public void swipeRight() { }
        @Override
        public void swipeDown() { }
        @Override
        public void swipeUp() { }
    };


    public void hideCustomKeyboard() {
        keyboardView.setVisibility(View.GONE);
      //  lenearButtonHBH.setVisibility(View.VISIBLE);
        keyboardView.setEnabled(false);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) vertScroll
                .getLayoutParams();

        layoutParams.bottomMargin = 0;
        // layoutParams.setMargins(0, 0, 0, 200);
        vertScroll.setLayoutParams(layoutParams);
    }
    public void showCustomKeyboard( View v) {
        keyboardView.setVisibility(View.VISIBLE);
     //   lenearButtonHBH.setVisibility(View.GONE);
        keyboardView.setEnabled(true);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) vertScroll
                .getLayoutParams();

        layoutParams.bottomMargin = 320;
       // layoutParams.setMargins(0, 0, 0, 200);
        vertScroll.setLayoutParams(layoutParams);

        if( v!=null ){
            ((InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
    public boolean isCustomKeyboardVisible() {
        return keyboardView.getVisibility() == View.VISIBLE;
    }
    @Override public void onBackPressed() {
        if( isCustomKeyboardVisible() ) hideCustomKeyboard(); else {
            hideCustomKeyboard();
            this.finish();
        }
    }

    public String removeTrailingZero(String formattingInput){
        if(!formattingInput.contains(".")){
            return formattingInput;
        }
        int dotPosition = formattingInput.indexOf(".");
        String newValue = formattingInput.substring(dotPosition, formattingInput.length());
        if(newValue.startsWith(".0")){
            return formattingInput.substring(0, dotPosition);
        }
        return formattingInput;
    }
}
