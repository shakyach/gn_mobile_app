package com.golfnationsmob.GolfView.NewRound;

import android.app.Activity;
import android.content.Context;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.golfnationsmob.GolfView.PostScore.MainPostScore;
import com.golfnationsmob.GolfView.PostScore.TotalScore;
import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

import java.io.ByteArrayOutputStream;

public class Profile extends Activity {
    Button m_back;
    TextView m_titletext;

    TextView statsText,firstName,lastName,emailText,ghinNumber,stateText,UsgaText;
    Button savebutton,cancelbutton;
    EditText Editfirstname,Editlastname,Editemail,Editghin,Editstate;
    LinearLayout lin1,lin2,lin3,lin4,lin5;

    LinearLayout backlinear;
    String Menbernumber,Membername,fromwhere;
    ImageView camerapic,profilepic;
    private static final int CAMERA_REQUEST = 1888;
    static byte[] user1profilePic = null;
    static byte[] user2profilePic = null;
    static byte[] user3profilePic = null;
    static byte[] user4profilePic = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_profile);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.subheader);

    //#UI INITILIZATION

        m_back = (Button)findViewById(R.id.header_left_btn);
       // UsgaButton = (Button)findViewById(R.id.idusgabutton);
        savebutton = (Button)findViewById(R.id.idprofilesave);
        cancelbutton = (Button)findViewById(R.id.d2);
        camerapic = (ImageView) findViewById(R.id.idcamerapic);
        profilepic = (ImageView) findViewById(R.id.idprofilepicED);
        m_titletext = (TextView)findViewById(R.id.titletext);
        statsText = (TextView)findViewById(R.id.idstats);
        firstName = (TextView)findViewById(R.id.idfirstname);
        lastName = (TextView)findViewById(R.id.idlastname);
        emailText = (TextView)findViewById(R.id.idemail);
        ghinNumber = (TextView)findViewById(R.id.idghin);
        stateText = (TextView)findViewById(R.id.idstate);
        UsgaText = (TextView)findViewById(R.id.idusgatext);
        backlinear = (LinearLayout) findViewById(R.id.backlinear);
        Editfirstname = (EditText)findViewById(R.id.editfirstname);
        Editlastname = (EditText)findViewById(R.id.editlastname);
        Editemail = (EditText)findViewById(R.id.editemail1);
        Editghin = (EditText)findViewById(R.id.editghin);
        Editstate = (EditText)findViewById(R.id.editstate);
        lin1 = (LinearLayout) findViewById(R.id.linprofile1);
        lin2 = (LinearLayout) findViewById(R.id.linprofile2);
        lin3 = (LinearLayout) findViewById(R.id.linprofile3);
        lin4 = (LinearLayout) findViewById(R.id.linprofile4);
        lin5 = (LinearLayout) findViewById(R.id.linprofile5);

        lin1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {

                    Editfirstname.requestFocus();
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(Editfirstname, InputMethodManager.SHOW_IMPLICIT);
                }
                return false;
            }

        });

        lin2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {

                    Editlastname.requestFocus();
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(Editlastname, InputMethodManager.SHOW_IMPLICIT);
                }
                return false;
            }

        });

        lin3.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {

                    Editemail.requestFocus();
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(Editemail, InputMethodManager.SHOW_IMPLICIT);
                }
                return false;
            }

        });
        lin4.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {

                    Editghin.requestFocus();
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(Editghin, InputMethodManager.SHOW_IMPLICIT);
                }
                return false;
            }

        });
        lin5.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {

                    Editstate.requestFocus();
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(Editstate, InputMethodManager.SHOW_IMPLICIT);
                }
                return false;
            }

        });


        m_titletext.setText("Profile");

        final Typeface font = AppFont.setFont(this,AppFont.ROBOTO_REGULAR);
        final Typeface font1 =  AppFont.setFont(this,AppFont.ROBOTO_LIGHT);
        final Typeface font2 = AppFont.setFont(this,AppFont.ROBOTO_MEDIUM);

        m_titletext.setTypeface(font);
        statsText.setTypeface(font1);
        firstName.setTypeface(font2);
        lastName.setTypeface(font2);
        emailText.setTypeface(font2);
        ghinNumber.setTypeface(font2);
        stateText.setTypeface(font2);
        UsgaText.setTypeface(font1);
        //UsgaButton.setTypeface(font2);
        Editfirstname.setTypeface(font);
        Editlastname.setTypeface(font);
        Editemail.setTypeface(font);
        Editghin.setTypeface(font);
        Editstate.setTypeface(font);
        savebutton.setTypeface(font);
        cancelbutton.setTypeface(font);
//###############################
        Bundle bundle = getIntent().getExtras();
        Menbernumber = bundle.getString("MemberNumber");
        Membername = bundle.getString("Membername");
        fromwhere = bundle.getString("fromscreen");
        SharedPreferences sharedpreferencesUser = getSharedPreferences("USERME", MODE_PRIVATE);
        Editfirstname.setText(""+sharedpreferencesUser.getString("name",""));
        Editlastname.setText(""+sharedpreferencesUser.getString("sname",""));
        Editemail.setText(""+sharedpreferencesUser.getString("mail",""));

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
            profilepic.setImageDrawable(drawable);
        }
        backlinear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(fromwhere.equals("ROUNDSETTING")) {

                    Intent PreviousScreen = new Intent(Profile.this, RoundSetting.class);
                    // PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
                else
                if(fromwhere.equals("PostScore")) {

                    Intent PreviousScreen = new Intent(Profile.this, MainPostScore.class);
                    // PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
                else
                if(fromwhere.equals("TotalScore")) {

                    Intent PreviousScreen = new Intent(Profile.this, TotalScore.class);
                    // PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
                else
                if(fromwhere.equals("STARTROUND")) {

                    Intent PreviousScreen = new Intent(Profile.this, StartRound.class);
                    // PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
                else
                if(fromwhere.equals("TEESELECTION")) {

                    Bundle bundle = new Bundle();

                    bundle.putString("MemberNumber", Menbernumber);
                    bundle.putString("Membername", Membername);
                    Intent PreviousScreen = new Intent(Profile.this, TeeSelection.class);
                    PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

        savebutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(fromwhere.equals("ROUNDSETTING")) {

                    SharedPreferences sharedpreferencesUser = getSharedPreferences("USERME", MODE_PRIVATE);
                    SharedPreferences.Editor ed = sharedpreferencesUser.edit();

                    ed.putString("name", ""+Editfirstname.getText());
                    ed.putString("sname", ""+Editlastname.getText());
                    ed.putString("mail", ""+Editemail.getText());
                    ed.commit();
                    Intent PreviousScreen = new Intent(Profile.this, RoundSetting.class);
                    // PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
                else
                if(fromwhere.equals("STARTROUND")) {
                    SharedPreferences sharedpreferencesUser = getSharedPreferences("USERME", MODE_PRIVATE);
                    SharedPreferences.Editor ed = sharedpreferencesUser.edit();

                    ed.putString("name", ""+Editfirstname.getText());
                    ed.putString("sname", ""+Editlastname.getText());
                    ed.putString("mail", ""+Editemail.getText());
                    ed.commit();
                    Intent PreviousScreen = new Intent(Profile.this, StartRound.class);
                    // PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
                else
                if(fromwhere.equals("PostScore")) {
                    SharedPreferences sharedpreferencesUser = getSharedPreferences("USERME", MODE_PRIVATE);
                    SharedPreferences.Editor ed = sharedpreferencesUser.edit();

                    ed.putString("name", ""+Editfirstname.getText());
                    ed.putString("sname", ""+Editlastname.getText());
                    ed.putString("mail", ""+Editemail.getText());
                    ed.commit();
                    Intent PreviousScreen = new Intent(Profile.this, MainPostScore.class);
                    // PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
                else
                if(fromwhere.equals("TotalScore")) {
                    SharedPreferences sharedpreferencesUser = getSharedPreferences("USERME", MODE_PRIVATE);
                    SharedPreferences.Editor ed = sharedpreferencesUser.edit();

                    ed.putString("name", ""+Editfirstname.getText());
                    ed.putString("sname", ""+Editlastname.getText());
                    ed.putString("mail", ""+Editemail.getText());
                    ed.commit();
                    Intent PreviousScreen = new Intent(Profile.this, TotalScore.class);
                    // PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
                else
                if(fromwhere.equals("TEESELECTION")) {
                    Bundle bundle = new Bundle();

                    bundle.putString("MemberNumber",Menbernumber);
                    bundle.putString("Membername",Membername);
                    SharedPreferences sharedpreferencesUser = getSharedPreferences("USERME", MODE_PRIVATE);
                    SharedPreferences.Editor ed = sharedpreferencesUser.edit();

                    ed.putString("name", ""+Editfirstname.getText());
                    ed.putString("sname", ""+Editlastname.getText());
                    ed.putString("mail", ""+Editemail.getText());
                    ed.commit();
                    Intent PreviousScreen = new Intent(Profile.this,TeeSelection.class);
                    PreviousScreen.putExtras(bundle);
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
                if(fromwhere.equals("ROUNDSETTING")) {

                    Intent PreviousScreen = new Intent(Profile.this, RoundSetting.class);
                    // PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
                else
                if(fromwhere.equals("PostScore")) {

                    Intent PreviousScreen = new Intent(Profile.this, MainPostScore.class);
                    // PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
                else
                if(fromwhere.equals("TotalScore")) {

                    Intent PreviousScreen = new Intent(Profile.this, TotalScore.class);
                    // PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
                else
                if(fromwhere.equals("STARTROUND")) {

                    Intent PreviousScreen = new Intent(Profile.this, StartRound.class);
                    // PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
                else
                if(fromwhere.equals("TEESELECTION")) {

                    Bundle bundle = new Bundle();

                    bundle.putString("MemberNumber", Menbernumber);
                    bundle.putString("Membername", Membername);
                    Intent PreviousScreen = new Intent(Profile.this, TeeSelection.class);
                    PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });
        camerapic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            if(data != null) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.PNG, 90, stream);
                user1profilePic = stream.toByteArray();
                RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(photo);
                profilepic.setImageDrawable(drawable);

                String encodedImage = Base64.encodeToString(user1profilePic, Base64.DEFAULT);
                SharedPreferences sharedpreferencesProfilePic = getSharedPreferences("profilePic", MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedpreferencesProfilePic.edit();
                edit.putString("profile_image", encodedImage);
                edit.commit();
            }
            // BitmapDrawable ob = new BitmapDrawable(getResources(), getCircularBitmap(photo));
           // profilepic.setBackgroundDrawable(ob);

          //  profilepic.setImageBitmap(photo);
        }
    }
    @Override
    public void onBackPressed() {
        if(fromwhere.equals("ROUNDSETTING")) {

            Intent PreviousScreen = new Intent(Profile.this, RoundSetting.class);
            // PreviousScreen.putExtras(bundle);
            startActivity(PreviousScreen);
            finish();
        }
        else
        if(fromwhere.equals("PostScore")) {

            Intent PreviousScreen = new Intent(Profile.this, MainPostScore.class);
            // PreviousScreen.putExtras(bundle);
            startActivity(PreviousScreen);
            finish();
        }
        else
        if(fromwhere.equals("TotalScore")) {

            Intent PreviousScreen = new Intent(Profile.this, TotalScore.class);
            // PreviousScreen.putExtras(bundle);
            startActivity(PreviousScreen);
            finish();
        }
        else
        if(fromwhere.equals("STARTROUND")) {

            Intent PreviousScreen = new Intent(Profile.this, StartRound.class);
            // PreviousScreen.putExtras(bundle);
            startActivity(PreviousScreen);
            finish();
        }
        else
        if(fromwhere.equals("TEESELECTION")) {
            Bundle bundle = new Bundle();

            bundle.putString("MemberNumber", Menbernumber);
            bundle.putString("Membername", Membername);
            Intent PreviousScreen = new Intent(Profile.this, TeeSelection.class);
            PreviousScreen.putExtras(bundle);
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
