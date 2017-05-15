package com.golfnationsmob.GolfView.PostScore;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.golfnationsmob.GolfModel.MyKeyBoard;
import com.golfnationsmob.GolfView.NewRound.Profile;
import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

public class TotalScore extends Activity {
    Button m_back,m_save,m_cancel;
    TextView m_titletext;
    TextView CourseName,Date;
    EditText finalscore[] = {null,null,null,null};//,totalputts,fairwayHit,GIR;
    LinearLayout linearTS[] = {null,null,null,null};
    LinearLayout linearMore,linearLess;
    LinearLayout backlinear,savelinear,linearTotalscore;
    Button savebutton;
    String edittextValue[] = {"FS","TP","FH","GIR"};
    protected KeyboardView keyboardView;
    Keyboard keyboard;
    ScrollView vertScroll;
    boolean firsttime;
    ImageView userpic;
    static byte[] user1profilePic = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_total_score);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.subheader);
        m_back = (Button) findViewById(R.id.header_left_btn);
        m_cancel = (Button) findViewById(R.id.TSCANCEL);

        savebutton = (Button) findViewById(R.id.TSsaveButton);
        m_save = (Button) findViewById(R.id.TSsaveButton);
        m_titletext = (TextView)findViewById(R.id.titletext);
        userpic = (ImageView) findViewById(R.id.TSprofileIm);
        Date = (TextView)findViewById(R.id.TSdateid);
        for(int i = 0;i<4;i++) {
            int resID = getResources().getIdentifier("editText" +edittextValue[i], "id", getPackageName());
            finalscore[i] = (EditText) findViewById(resID);
        }

        SharedPreferences sharedpreferencesProfilePic = getSharedPreferences("profilePic", MODE_PRIVATE);
        String decodedData = sharedpreferencesProfilePic.getString("profile_image", "");
        if(sharedpreferencesProfilePic.getString("profile_image", "").equals(""))
        {
            user1profilePic = null;
        }
        else
            user1profilePic = Base64.decode(decodedData, Base64.DEFAULT);
        if(user1profilePic!=null)
        {
            Bitmap bitmap = BitmapFactory.decodeByteArray(user1profilePic, 0, user1profilePic.length);
            RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bitmap);
            userpic.setImageDrawable(drawable);
        }
        userpic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Bundle bundle = new Bundle();
                bundle.putString("MemberNumber","first");
                bundle.putString("Membername","You");
                bundle.putString("fromscreen","TotalScore");
                Intent next = new Intent(TotalScore.this, Profile.class);
                next.putExtras(bundle);
                startActivity(next);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

        vertScroll = (ScrollView) findViewById(R.id.scrollView6);
        CourseName = (TextView)findViewById(R.id.TScoursename);
        backlinear = (LinearLayout) findViewById(R.id.backlinear);
        savelinear = (LinearLayout) findViewById(R.id.totalsavelinear);
        linearTotalscore = (LinearLayout) findViewById(R.id.linearTotalScore);
        linearTotalscore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                hideCustomKeyboard();

            }
        });

        SharedPreferences sharedpreferencesRS= getSharedPreferences("MAINPOSTSCORE", MODE_PRIVATE);
        Date.setText(""+(sharedpreferencesRS.getString("Date", "")));
        CourseName.setText(""+(sharedpreferencesRS.getString("courseName", "")));
        Typeface font = AppFont.setFont(this,AppFont.ROBOTO_REGULAR);
        Typeface font1 =  AppFont.setFont(this,AppFont.ROBOTO_MEDIUM);
        m_titletext.setTypeface(font);
        m_save.setTypeface(font);
        for(int i = 0;i<4;i++) {
            int resID = getResources().getIdentifier("TSlinear" + (i+1), "id", getPackageName());
            linearTS[i] = (LinearLayout) findViewById(resID);
        }
        linearMore = (LinearLayout)findViewById(R.id.TSlinMorestats);
        linearLess = (LinearLayout)findViewById(R.id.TSlinLessstats);
        m_titletext.setText("Total Score");
        keyboardView = (MyKeyBoard)findViewById(R.id.keyboard_view1);

        keyboardView.setPreviewEnabled(false);
        keyboard = new Keyboard(this, R.layout.keyboards);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(keyboardActionListener);
        m_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent PreviousScreen = new Intent(TotalScore.this, MainPostScore.class);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

        m_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent PreviousScreen = new Intent(TotalScore.this, MainPostScore.class);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

        backlinear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent PreviousScreen = new Intent(TotalScore.this, MainPostScore.class);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

        savebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                keyboardView.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Score Posted.", Toast.LENGTH_SHORT).show();
                Intent PreviousScreen = new Intent(TotalScore.this, MainPostScore.class);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });
        linearMore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                linearMore.setVisibility(LinearLayout.GONE);
                linearLess.setVisibility(LinearLayout.VISIBLE);
                for(int i = 1;i<4;i++) {
                    linearTS[i].setVisibility(LinearLayout.VISIBLE);
                }


            }
        });

        linearLess.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                linearMore.setVisibility(LinearLayout.VISIBLE);
                linearLess.setVisibility(LinearLayout.GONE);
                for(int i = 1;i<4;i++) {
                    linearTS[i].setVisibility(LinearLayout.GONE);
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

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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
     //   savelinear.setVisibility(View.VISIBLE);
        keyboardView.setEnabled(false);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) vertScroll
                .getLayoutParams();

        layoutParams.bottomMargin = 0;
        // layoutParams.setMargins(0, 0, 0, 200);
        vertScroll.setLayoutParams(layoutParams);
    }

    public void showCustomKeyboard( View v) {
        keyboardView.setVisibility(View.VISIBLE);
     //   savelinear.setVisibility(View.GONE);
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
