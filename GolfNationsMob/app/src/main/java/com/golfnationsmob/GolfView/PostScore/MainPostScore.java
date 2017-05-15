package com.golfnationsmob.GolfView.PostScore;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.golfnationsmob.GolfView.MainPage;
import com.golfnationsmob.GolfView.NewRound.Profile;
import com.golfnationsmob.GolfView.NewRound.SelectCourse;
import com.golfnationsmob.GolfView.NewRound.TeeSelection;
import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

import java.util.Calendar;

public class MainPostScore extends Activity {
    Button m_back,m_totalScore,m_holebyhole,m_cancel;
    TextView m_titletext,psTees;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView,nearestcourse,Teestext,golferName;
    private TextView coursetitletext,teestitletext,datetitletext;
    private int year, month, day;
    String  m_nearestcourse_data = "McDowell Mountain Ranch";
    String  m_teeselected = "Select...";
    CalendarView calendarView;
    LinearLayout linearLayoutDATE,linearLayoutcourseselect,linearLayouttees;
    DatePickerDialog datePickerDialog;
    LinearLayout backlinear;
    ImageView userpic;
    static byte[] user1profilePic = null;


    String MonthName[] = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main_post_score);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.subheader);
        m_back = (Button) findViewById(R.id.header_left_btn);
        m_totalScore = (Button) findViewById(R.id.TOTALSCORE);
        m_holebyhole = (Button) findViewById(R.id.HOLEBHOLE);
        m_cancel = (Button) findViewById(R.id.PScancel);
        userpic = (ImageView) findViewById(R.id.UserpropicPS);
        m_titletext = (TextView)findViewById(R.id.titletext);
        psTees = (TextView)findViewById(R.id.PSTeestext);
        nearestcourse = (TextView)findViewById(R.id.PSnearestcourse);
        Teestext = (TextView)findViewById(R.id.PSTeestext);
        golferName = (TextView)findViewById(R.id.PSgolferName);
        coursetitletext = (TextView)findViewById(R.id.PScoursttext);
        teestitletext = (TextView)findViewById(R.id.PStees);
        datetitletext = (TextView)findViewById(R.id.PSDATE);
        backlinear = (LinearLayout) findViewById(R.id.backlinear);
        m_titletext.setText("Post a Score");
        linearLayoutDATE = (LinearLayout) findViewById(R.id.PSlinearLayoutDATE);
        linearLayoutcourseselect = (LinearLayout) findViewById(R.id.PSlinearLayoutcourseselect);
        linearLayouttees = (LinearLayout) findViewById(R.id.PSlinearLayouttees);
        dateView = (TextView) findViewById(R.id.dateid);

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
        //Custom Font initilization
        Typeface font = AppFont.setFont(this,AppFont.ROBOTO_REGULAR);
        Typeface font1 =  AppFont.setFont(this,AppFont.ROBOTO_MEDIUM);
        Typeface font2 = AppFont.setFont(this,AppFont.ROBOTO_LIGHT);

        m_titletext.setTypeface(font);
        golferName.setTypeface(font);
        nearestcourse.setTypeface(font);
        Teestext.setTypeface(font);
        dateView.setTypeface(font);
        m_cancel.setTypeface(font);

        coursetitletext.setTypeface(font1);
        teestitletext.setTypeface(font1);
        datetitletext.setTypeface(font1);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        SharedPreferences sharedpreferencesRS= getSharedPreferences("MAINPOSTSCORE", MODE_PRIVATE);

        m_nearestcourse_data =  sharedpreferencesRS.getString("courseName", "");
        m_teeselected =  sharedpreferencesRS.getString("TessSelectedfirst", "");
        if(m_nearestcourse_data.equals(""))
            nearestcourse.setText("McDowell Mountain Ranch");
        else
            nearestcourse.setText(m_nearestcourse_data);

        if(m_teeselected.equals(""))
            Teestext.setText("Select...");
        else
            Teestext.setText(m_teeselected);
        linearLayoutDATE.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                showDialog(999);

                Toast.makeText(getApplicationContext(), "ca",
                        Toast.LENGTH_SHORT)
                        .show();

            }
        });

        userpic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Bundle bundle = new Bundle();
                bundle.putString("MemberNumber","first");
                bundle.putString("Membername","You");
                bundle.putString("fromscreen","PostScore");
                Intent next = new Intent(MainPostScore.this, Profile.class);
                next.putExtras(bundle);
                startActivity(next);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });


        m_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent PreviousScreen = new Intent(MainPostScore.this, MainPage.class);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

        m_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent PreviousScreen = new Intent(MainPostScore.this, MainPage.class);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

        backlinear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent PreviousScreen = new Intent(MainPostScore.this, MainPage.class);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

        linearLayoutcourseselect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                SharedPreferences sharedpreferencesRS= getSharedPreferences("SCREENNAME" , MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                editor.putString("screenname" , "postscore");
                editor.commit();
                Intent NextScreen = new Intent(MainPostScore.this, SelectCourse.class);
               // NextScreen.putExtras(bundle);
                startActivity(NextScreen);
                finish();

            }
        });

        linearLayouttees.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                SharedPreferences sharedpreferencesRS= getSharedPreferences("SCREENNAME" , MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                editor.putString("screenname" , "postscore");
                editor.commit();
                Bundle bundle = new Bundle();
                bundle.putString("MemberNumber","first");
                bundle.putString("Membername",""+golferName.getText());
               // bundle.putString("FROMACTIVITY_WHERE","POSTSCORE");
                Intent NextScreen = new Intent(MainPostScore.this, TeeSelection.class);
                NextScreen.putExtras(bundle);
                startActivity(NextScreen);
                finish();

            }
        });

        m_totalScore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                SharedPreferences sharedpreferencesRS= getSharedPreferences("MAINPOSTSCORE" , MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                editor.putString("Date" , ""+dateView.getText());
                editor.putString("courseName" , ""+nearestcourse.getText());
                editor.commit();
                Intent NextScreen = new Intent(MainPostScore.this, TotalScore.class);
                startActivity(NextScreen);
                finish();

            }
        });

        m_holebyhole.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                SharedPreferences sharedpreferencesRS= getSharedPreferences("MAINPOSTSCORE" , MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                editor.putString("Date" , ""+dateView.getText());
                editor.putString("courseName" , ""+nearestcourse.getText());
                editor.commit();
                Intent NextScreen = new Intent(MainPostScore.this, ScoreHoleByHole.class);
                startActivity(NextScreen);
                finish();

            }
        });
        m_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent PreviousScreen = new Intent(MainPostScore.this, MainPage.class);
                startActivity(PreviousScreen);
                finish();

            }
        });

    }


    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            datePickerDialog =  new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
                    myDateListener, year, month, day);
           // datePickerDialog.getDatePicker().setCalendarViewShown(true);

           datePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, null, (DialogInterface.OnClickListener) null);
        //   datePickerDialog.setButton(DatePickerDialog.BUTTON_POSITIVE, null, (DialogInterface.OnClickListener) null);
             datePickerDialog.getDatePicker().setBackgroundColor(Color.parseColor("#ffffff"));


            //datePickerDialog.getDatePicker().setSpinnersShown(false);
            return datePickerDialog;
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day

                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(MonthName[month-1]).append("  ")
                .append(day).append(" , ").append(year));
    }

    @Override
    public void onBackPressed() {

        Intent PreviousScreen = new Intent(MainPostScore.this, MainPage.class);
        startActivity(PreviousScreen);
        finish();
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
