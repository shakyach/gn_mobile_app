package com.golfnationsmob.GolfView.PracticeGoals;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.golfnationsmob.GolfModel.MyKeyBoard;
import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

public class PracticeInputText extends Activity {
    Button m_back,save;
    TextView m_titletext;
    TextView m_accurateText1,m_accurateText2,m_accurateText3;
    EditText practiceAccuED1,practiceAccuED2,practiceAccuED3;
    LinearLayout accuLinear1,accuLinear2,accuLinear3;
    String PracticeName;
    int PracticeId;
    protected KeyboardView keyboardView;
    Keyboard keyboard;
    ScrollView vertScroll;
    LinearLayout LinearPracIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_practice_input_text);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.subheader);
        m_back = (Button) findViewById(R.id.header_left_btn);
        save = (Button) findViewById(R.id.practiceInsave);
        m_titletext = (TextView)findViewById(R.id.titletext);
        m_titletext.setText("Practice & Goals");
        Typeface font = AppFont.setFont(this,AppFont.ROBOTO_REGULAR);
        m_titletext.setTypeface(font);
        SharedPreferences sharedpreferencestemp= getSharedPreferences("PRACTICETYPE" , MODE_PRIVATE);
        PracticeName = sharedpreferencestemp.getString("PracticeName","");
        PracticeId = sharedpreferencestemp.getInt("PracticeId",0);

        vertScroll = (ScrollView) findViewById(R.id.scrollViewPracIn);


        m_accurateText1 = (TextView)findViewById(R.id.accuracyText1);
        m_accurateText2 = (TextView)findViewById(R.id.practiceInfeet);
        m_accurateText3 = (TextView)findViewById(R.id.practiceIntext2);

        practiceAccuED1 = (EditText)findViewById(R.id.practiceINeditText1);
        practiceAccuED2 = (EditText)findViewById(R.id.practiceINeditText2);
        practiceAccuED3 = (EditText)findViewById(R.id.practiceINeditText3);
        setEditTextKeyBoard(practiceAccuED1);
        setEditTextKeyBoard(practiceAccuED2);
        setEditTextKeyBoard(practiceAccuED3);

        accuLinear1 = (LinearLayout) findViewById(R.id.practiceInLinear1);
        accuLinear2 = (LinearLayout) findViewById(R.id.practiceInLinear2);
        accuLinear3 = (LinearLayout) findViewById(R.id.practiceInLinear3);

        LinearPracIn = (LinearLayout) findViewById(R.id.linearPracIn);
        LinearPracIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                hideCustomKeyboard();

            }
        });
        if(PracticeId == 0)
        {
            m_accurateText1.setText("I made");
            accuLinear1.setVisibility(View.GONE);
            accuLinear2.setVisibility(View.GONE);
            accuLinear3.setVisibility(View.GONE);
        }
        else
        if(PracticeId == 1)
        {
            m_accurateText1.setText("I placed");
            m_accurateText2.setText("2");
            m_accurateText3.setText("the putts I made");

        }
        else
        if(PracticeId == 2)
        {
            m_accurateText1.setText("I placed");
            m_accurateText2.setText("4");
            m_accurateText3.setText("the putts I made");
        }
        else
        if(PracticeId == 6)
        {
            m_accurateText1.setText("I placed");
            m_accurateText2.setText("6");
            m_accurateText3.setText("the chips I made");
        }
        else
        if(PracticeId == 3)
        {
            m_accurateText1.setText("I placed");
            m_accurateText2.setText("6");
            m_accurateText3.setText("the chips I made");
        }
        else
        if(PracticeId == 9)
        {
            m_accurateText1.setText("I placed");
            m_accurateText2.setText("6");
            m_accurateText3.setText("the chips I made");
        }

        keyboardView = (MyKeyBoard)findViewById(R.id.keyboard_view1);

        keyboardView.setPreviewEnabled(false);
        keyboard = new Keyboard(this, R.layout.keyboards);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(keyboardActionListener);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        m_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent PreviousScreen = new Intent(PracticeInputText.this, PracticeNewSet.class);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent PreviousScreen = new Intent(PracticeInputText.this, PracGoPutting.class);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

    }

    public void setEditTextKeyBoard(EditText edit)
    {
        // Make the custom keyboard appear
        edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) showCustomKeyboard(v);
                else hideCustomKeyboard();

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomKeyboard(v);
            }
        });
        edit.setOnTouchListener(new View.OnTouchListener() {
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
        //lenearButtonHBH.setVisibility(View.VISIBLE);
        keyboardView.setEnabled(false);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) vertScroll
                .getLayoutParams();

        layoutParams.bottomMargin = 0;
        // layoutParams.setMargins(0, 0, 0, 200);
        vertScroll.setLayoutParams(layoutParams);
    }
    public void showCustomKeyboard( View v) {
        keyboardView.setVisibility(View.VISIBLE);
       // lenearButtonHBH.setVisibility(View.GONE);
        keyboardView.setEnabled(true);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) vertScroll
                .getLayoutParams();

        layoutParams.bottomMargin = 325;
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
