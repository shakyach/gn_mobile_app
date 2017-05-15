package com.golfnationsmob.GolfView.NewRound;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.Random;

/**
 * Created by shakya
 */
public class AddEditFriend extends Activity {
    TextView m_titletext,firstnametitle,lastnametitle,emailtitle;
    Button m_back,m_save;

    EditText firstname,lastname,email;
    LinearLayout lin1,lin2,lin3;
    SharedPreferences sharedpreferencesName;
    SharedPreferences sharedpreferencesMail;
    SharedPreferences sharedpreferencesMembership;
    ImageView cameraButton,profilepic;
    private static final int CAMERA_REQUEST = 1888;
    static byte[] userprofilePic = null;

    boolean Randomboolean[] = {true,false,false,true,false};
   // String prename="mypref";
    LinearLayout backlinear;
    int id = 0;
    int indexNo=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_add_edit_friend);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.subheader);
        m_titletext = (TextView)findViewById(R.id.titletext);
        cameraButton = (ImageView) findViewById(R.id.addeditcameraicon);
        profilepic = (ImageView) findViewById(R.id.idprofilepicED);
        firstname = (EditText)findViewById(R.id.editaddeditfirstname);
        lastname = (EditText)findViewById(R.id.editaddeditlastname);
        email = (EditText)findViewById(R.id.editaddeditemail1);
        firstnametitle = (TextView)findViewById(R.id.idaddeditfirstname);
        lastnametitle = (TextView)findViewById(R.id.addeditidlastname);
        emailtitle = (TextView)findViewById(R.id.idaddeditemail);
        lin1 = (LinearLayout) findViewById(R.id.linprofile1);
        lin2 = (LinearLayout) findViewById(R.id.linprofile2);
        lin3 = (LinearLayout) findViewById(R.id.linprofile3);
        m_back = (Button)findViewById(R.id.header_left_btn);
        m_save = (Button)findViewById(R.id.d2);
        m_titletext.setText("My Friend");
        backlinear = (LinearLayout) findViewById(R.id.backlinear);
        final Typeface font = AppFont.setFont(this,AppFont.ROBOTO_REGULAR);
        final Typeface font1 =  AppFont.setFont(this,AppFont.ROBOTO_LIGHT);
        final Typeface font2 = AppFont.setFont(this,AppFont.ROBOTO_MEDIUM);

        m_titletext.setTypeface(font);
        m_save.setTypeface(font);
        firstnametitle.setTypeface(font2);
        lastnametitle.setTypeface(font2);
        emailtitle.setTypeface(font2);
        firstname.setTypeface(font);
        lastname.setTypeface(font);
        email.setTypeface(font);


        lin1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {

                    firstname.requestFocus();
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(firstname, InputMethodManager.SHOW_IMPLICIT);
                }
                return false;
            }

        });

        lin2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {

                    lastname.requestFocus();
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(lastname, InputMethodManager.SHOW_IMPLICIT);
                }
                return false;
            }

        });

        lin3.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {

                    email.requestFocus();
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(email, InputMethodManager.SHOW_IMPLICIT);
                }
                return false;
            }

        });


        email .addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

              /*  if (isEmailValid(email.getText().toString()) )
                {
                    Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                    // or
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                    //or
                }*/
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // other stuffs
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // other stuffs
            }
        });

        backlinear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent PreviousScreen = new Intent(AddEditFriend.this,AddFriends.class);
          //      PreviousScreen.putExtras(bundle);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

        cameraButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

        m_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent PreviousScreen = new Intent(AddEditFriend.this,AddFriends.class);
              //  PreviousScreen.putExtras(bundle);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });
        sharedpreferencesName= getSharedPreferences("Name" , MODE_PRIVATE);
        sharedpreferencesMail= getSharedPreferences("Mail" , MODE_PRIVATE);
        sharedpreferencesMembership= getSharedPreferences("membership" , MODE_PRIVATE);

        final Map<String, ?> allEntries = sharedpreferencesName.getAll();
        final Map<String, ?> allEntries1 = sharedpreferencesMembership.getAll();
        final Map<String, ?> allEntries2 = sharedpreferencesMail.getAll();
        indexNo = (allEntries.size()+1);
        Log.i("ss", "indexNo*****************" + indexNo);
        m_save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(firstname.getText().toString().trim().equals(""))// ||lastname.getText().toString().trim().equals("")||email.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"First name is required.",Toast.LENGTH_LONG).show();
                }
                else {
                    if (!isEmailValid(email.getText().toString()) && !email.getText().toString().trim().equals(""))
                    {
                        Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();

                        return;
                    }

                    SharedPreferences.Editor editor = sharedpreferencesName.edit();
                    SharedPreferences.Editor editor1=sharedpreferencesMail.edit();
                    SharedPreferences.Editor editor2=sharedpreferencesMembership.edit();
                    Random random = new Random();
                    editor1.putString("email"+(allEntries2.size()+1), ""+email.getText());
                    editor1.commit();

                    editor.putString("name" + (allEntries.size()+1), firstname.getText() + " " + lastname.getText());
                    editor.commit();
                    boolean membership = Randomboolean[random.nextInt(4)];
                 //   Log.i("ss", "membership*****************" + membership);
                    editor2.putBoolean("memebership"+(allEntries1.size()+1),membership);
                    editor2.commit();

                    Log.i("ss", "name*****************" + (allEntries.size()+1));

                    //   editor1.putInt("id",id);
                   // Log.i("ss", "id Save*****************" + id);
                  //  Log.i("ss", "msg Save*****************" + (firstname.getText() + " " + lastname.getText()));
                    // commit/saves the values


                    Intent PreviousScreen = new Intent(AddEditFriend.this, AddFriends.class);
                   // PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }
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
                userprofilePic = stream.toByteArray();
                RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(photo);
                profilepic.setImageDrawable(drawable);

                String encodedImage = Base64.encodeToString(userprofilePic, Base64.DEFAULT);
                SharedPreferences sharedpreferencesProfilePic = getSharedPreferences("profilePic_other", MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedpreferencesProfilePic.edit();
                edit.putString("profile_image_player"+indexNo, encodedImage);
                edit.commit();
            }
            // BitmapDrawable ob = new BitmapDrawable(getResources(), getCircularBitmap(photo));
            // profilepic.setBackgroundDrawable(ob);

            //  profilepic.setImageBitmap(photo);
        }
    }

    public static boolean isEmailValid(String email) {
        return !(email == null || TextUtils.isEmpty(email)) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    @Override
    public void onBackPressed() {


        Intent PreviousScreen = new Intent(AddEditFriend.this,AddFriends.class);
      //  PreviousScreen.putExtras(bundle);
        startActivity(PreviousScreen);
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
