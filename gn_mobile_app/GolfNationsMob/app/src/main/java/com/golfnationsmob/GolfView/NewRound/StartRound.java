package com.golfnationsmob.GolfView.NewRound;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.golfnationsmob.GolfModel.HorizontalPicker;
import com.golfnationsmob.GolfModel.ItemSlideMenu;
import com.golfnationsmob.GolfModel.SlidingMenuAdapter;
import com.golfnationsmob.GolfView.MainPage;
import com.golfnationsmob.GolfView.NewRound.Gps.MapsActivity;
import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

import java.util.ArrayList;
import java.util.List;

public class StartRound extends Activity implements HorizontalPicker.OnItemSelected, HorizontalPicker.OnItemClicked,LocationListener {

    LocationManager locationManager ;
    String provider;

    private static final int CAMERA_REQUEST = 1888;
    ImageView mImage_user1;
    boolean profilePress;
    boolean profilePress1;
    boolean profilePress2;
    boolean profilePress3;
    public static Button m_buttonScore;
    public Button  m_buttonPutt, m_buttonOff, m_buttonTee, m_buttonSand, m_buttonPenal, headerLeft_button;
    HorizontalPicker picker, picker1, picker2, picker3, picker4, picker5;
    ImageView profile[] ={null,null,null,null};
    TextView profileText1, profileText2, profileText3, profileText4, holeNumber, previousDist, currentDist, nextDist;
    TextView Circlesmall[]={null,null,null,null};
    Button LessState, MoreStats;
    LinearLayout linmorestats, linlessstat;
    TextView scoretext, puutstext, offtheteetext, teeaccuracytext, sandtext, penaltiestext;
    FrameLayout FrameLayout[] = {null,null,null,null,null};
    boolean setprofile1, setprofile2, setprofile3, setprofile4;
    int profileNo = 0;
    ScrollView scrollView_score,scrollView_Shots;
    LinearLayout Linearscrollshot;
     Button Buttonshottrack,Buttonnewshot,ButtonPutt;
    RelativeLayout RelativeLayoutforblueteesON,RelativeLayoutforblueteesOFF;
    Button leftArrow, rightArrow;
    int holeNumVar = 0;
    int ScoreValue[] ={2,3,0,4,5,6,7,8};
    int ScoreValue3[] ={1,2,0,3,4,5,6,7};
    int ScoreValue4[] ={2,3,0,4,5,6,7,8};
    int ScoreValue5[] ={3,4,0,5,6,7,8,9};

     String GolferName[] = {"","",""};
     String GolferNametemp[] = {"","",""};
     int usrId[] = {-1,-1,-1};
    SharedPreferences sharedpreferencesGolfername;
     int pickerNumber = 0;
    int parEachHole[] = {4,4,3,5,4,4,4,4,4,5,3,4,3,4,3,4,4,4};
  // int parEachHole[] = {4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4};
    CharSequence valuesPar3[]={"1","2","","3","4","5","6","7"};
    CharSequence valuesPar4[]={"2","3","","4","5","6","7","8"};
    CharSequence valuesPar5[]={"3","4","","5","6","7","8","9"};

    TextView Parvaluestart;
    int profileData1[][] = {{2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
                           {2, 0, 1, 2, 0, 0}, {2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
                           {2, 0, 1, 2, 0, 0}, {2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
                           {2, 0, 1, 2, 0, 0}, {2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
                           {2, 0, 1, 2, 0, 0}, {2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
                           {2, 0, 1, 2, 0, 0}, {2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
    };
    int profileData2[][] = {{2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
                           {2, 0, 1, 2, 0, 0}, {2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
                           {2, 0, 1, 2, 0, 0}, {2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
                           {2, 0, 1, 2, 0, 0}, {2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
                           {2, 0, 1, 2, 0, 0}, {2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
                           {2, 0, 1, 2, 0, 0}, {2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
    };
    int profileData3[][] = {{2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
                           {2, 0, 1, 2, 0, 0}, {2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
                           {2, 0, 1, 2, 0, 0}, {2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
                           {2, 0, 1, 2, 0, 0}, {2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
                           {2, 0, 1, 2, 0, 0}, {2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
                           {2, 0, 1, 2, 0, 0}, {2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
    };
    int profileData4[][] = {{2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
                           {2, 0, 1, 2, 0, 0}, {2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
                           {2, 0, 1, 2, 0, 0}, {2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
                           {2, 0, 1, 2, 0, 0}, {2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
                           {2, 0, 1, 2, 0, 0}, {2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
                           {2, 0, 1, 2, 0, 0}, {2, 0, 1, 2, 0, 0},{2, 0, 1, 2, 0, 0},
    };

    String HoleNumberArrayDistance[] = {"430", "361", "226", "569", "290", "330", "350", "375", "380", "390", "450", "360", "384", "354", "330", "360", "370", "389"};
    String HoleNumberArrayDistance1[] = {"416", "350", "208", "551", "290", "330", "350", "375", "380", "390", "450", "360", "384", "354", "330", "360", "370", "389"};
    String HoleNumberArrayDistance2[] = {"444", "372", "241", "588", "290", "330", "350", "375", "380", "390", "450", "360", "384", "354", "330", "360", "370", "389"};
    TextView GolfcourseName;

    LinearLayout score,shots,gps;
    TextView scoretext1,shotstext,gpstext;
    SharedPreferences scoringDB;

    private List<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter adapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout  m_drawerLayout;
    Button menu;

    private SensorManager mSensorManager;
    private Sensor mOrientation;
    boolean enableAddshot;
    float value_0 = -10000;
    float value_1 = -10000;
    //########For shot tracker############
    TextView Textshotcount[];
    Button Buttonclubsel[];
    FrameLayout FrameButtonclubsel[];
    FrameLayout Frameendpoint[];
    FrameLayout Frameaccubutton[];
    Button Accuracy[];
    Button Accuracy1[];
    TextView accutext[];
    TextView endpoint1[];
    TextView endpoint2[];
    ImageView endpointImage[];
    LinearLayout ButtonshotDelete[];
    int index = 0;
    boolean shotenable;

    RelativeLayout SR_relativeLayoutteesselect;
    TextView SR_Youid,SR_teesSelected;
    TextView SR_teeslinear;
    ImageView SR_userImage;
    String  m_teeselected = "NO TEES SELECTED";
    String  m_teeselected1 = "NO TEES SELECTED";
    String  m_teeselected2 = "NO TEES SELECTED";
    String  m_teeselected3 = "NO TEES SELECTED";
    //###################################
    private SensorEventListener mOrientationSensorListener = new SensorEventListener() {
        int orientation = -1;

        @Override
        public void onSensorChanged(SensorEvent event) {
            int value ;
           // Log.i("orientation", "########################################3");

          /*  Log.i("values:", "values:0" + event.values[0]);
            Log.i("values:", "values:1" +event.values[1]);
            if (event.values[0] > 0 && event.values[1] < 0) {

                Bundle bundle = new Bundle();
                bundle.putString("SCREENNAME","FROMSTARTROUND");
                Intent nextScreen = new Intent(StartRound.this, ScoreCard.class);
                nextScreen.putExtras(bundle);
                startActivity(nextScreen);
            }
*/


        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
//LinearLayout Horizentalpicker1,Horizentalpicker2,Horizentalpicker3,Horizentalpicker4,Horizentalpicker5,Horizentalpicker6;
Context mContext;
    ProgressDialog dialog;
    FrameLayout FL_profpic1,FL_profpic2,FL_profpic3,FL_profpic4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_start_round);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.header_startround);
        mContext =StartRound.this;
       // Display display = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        // Get the default sensor of specified type
        mOrientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        score = (LinearLayout) findViewById(R.id.LINEARSCORESCOREPOSTING);
        shots = (LinearLayout) findViewById(R.id.LINEARSHOTSSCOREPOSTING);
        gps = (LinearLayout) findViewById(R.id.LINEARGPSSCOREPOSTING);
        FL_profpic1 = (FrameLayout) findViewById(R.id.FL_profpic1);
        FL_profpic2 = (FrameLayout) findViewById(R.id.FL_profpic2);
        FL_profpic3 = (FrameLayout) findViewById(R.id.FL_profpic3);
        FL_profpic4 = (FrameLayout) findViewById(R.id.FL_profpic4);
        Linearscrollshot = (LinearLayout) findViewById(R.id.linearscrollshot);
        RelativeLayoutforblueteesON = (RelativeLayout) findViewById(R.id.relativeLayoutforblueteesON);
        RelativeLayoutforblueteesOFF = (RelativeLayout) findViewById(R.id.relativeLayoutforblueteesOFF);

        scoretext1 = (TextView) findViewById(R.id.IDSCORESCOREPOSTING);
        shotstext = (TextView) findViewById(R.id.IDSHOTSSCOREPOSTING);
        gpstext = (TextView) findViewById(R.id.IDGPSSCOREPOSTING);
        Parvaluestart = (TextView) findViewById(R.id.parvaluestart);
       // final Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "font/Roboto-Regular_0.ttf");

        m_buttonScore = (Button) findViewById(R.id.button16);
        m_buttonPutt = (Button) findViewById(R.id.button17);
        m_buttonOff = (Button) findViewById(R.id.button18);
        m_buttonTee = (Button) findViewById(R.id.button19);
        m_buttonSand = (Button) findViewById(R.id.button20);
        Buttonnewshot = (Button) findViewById(R.id.buttonnewshot);
        ButtonPutt = (Button) findViewById(R.id.buttonSTputt);
        m_buttonPenal = (Button) findViewById(R.id.button21);
        Buttonshottrack = (Button) findViewById(R.id.buttonshottrack);
        GolfcourseName = (TextView) findViewById(R.id.idGolfCourseName);
        scrollView_score = (ScrollView) findViewById(R.id.scrollViewscore);
        scrollView_Shots = (ScrollView) findViewById(R.id.scrollViewShots);
        headerLeft_button = (Button) findViewById(R.id.header_left_btn);
        //scrollView_Shots.fullScroll(View.FOCUS_DOWN);
        SR_relativeLayoutteesselect = (RelativeLayout) findViewById(R.id.SRrelativeLayoutteesselect);
        SR_Youid = (TextView) findViewById(R.id.SRYouid);
        SR_teesSelected = (TextView) findViewById(R.id.SRteesSelected);
        SR_teeslinear = (TextView) findViewById(R.id.SRteesSelected);
        SR_userImage = (ImageView) findViewById(R.id.SRuserImage);


        SharedPreferences sharedpreferencesRS= getSharedPreferences("roundsetting", MODE_PRIVATE);


        m_teeselected =  sharedpreferencesRS.getString("TessSelectedfirst", "");
        m_teeselected1=  sharedpreferencesRS.getString("TessSelectedsecond", "");
        m_teeselected2=  sharedpreferencesRS.getString("TessSelectedthird", "");
        m_teeselected3=  sharedpreferencesRS.getString("TessSelectedforth", "");

        sharedpreferencesGolfername= getSharedPreferences("AddFriendOnRs", MODE_PRIVATE);
        for(int i =0 ; i<3;i++)
        {
            GolferName[i] = sharedpreferencesGolfername.getString("memberonRS"+i, "");
            GolferNametemp[i] = sharedpreferencesGolfername.getString("memberonRS"+i, "");

            usrId[i] = sharedpreferencesGolfername.getInt("usrId"+i, -1);
            Log.i("ss", " membername *****************" +("memberonRS"+i));
            Log.i("ss", " newMember=== ********" + i + "*********" +  GolferName[i]);
        }

        scrollView_Shots.post(new Runnable() { public void run() {
            scrollView_Shots.fullScroll(View.FOCUS_DOWN);
         //   dialog.cancel();
        } });
        menu = (Button)findViewById(R.id.menubuttonSR);
        InitSlidingMenu();
        menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Opens the Drawer
                if(drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.setVisibility(View.VISIBLE);
                    drawerLayout.closeDrawer(Gravity.RIGHT);

                }
                else
                {
                    drawerLayout.setVisibility(View.VISIBLE);

                    drawerLayout.openDrawer(Gravity.RIGHT);

                }
            }


        });
        for(int i = 0;i < 4;i++)
        {
            int resID = getResources().getIdentifier("circlesmall"+(i+1), "id", getPackageName());
            Circlesmall[i] = (TextView) findViewById(resID);
        }



        SharedPreferences userStatus= getSharedPreferences("USERSTATUS", MODE_PRIVATE);
        int User1status = userStatus.getInt("USER1",0);
        int User2status = userStatus.getInt("USER2",0);
        int User3status = userStatus.getInt("USER3",0);
        int User4status = userStatus.getInt("USER4",0);

        setInnerCircle(User1status,Circlesmall[0]);
        setInnerCircle(User2status,Circlesmall[1]);
        setInnerCircle(User3status,Circlesmall[2]);
        setInnerCircle(User4status,Circlesmall[3]);

        scoringDB= getSharedPreferences("Scoring", MODE_PRIVATE);

        Typeface font = AppFont.setFont(this,AppFont.ROBOTO_THIN);
        final Typeface font1 = AppFont.setFont(this,AppFont.ROBOTO_REGULAR);

       // SharedPreferences sharedpreferencesRS= getSharedPreferences("roundsetting", MODE_PRIVATE);
        GolfcourseName.setText(""+sharedpreferencesRS.getString("courseName", ""));
       // TextView headerText = (TextView) findViewById(R.id.idGolfCourseName);
        GolfcourseName.setTypeface(font1);
        scoretext1.setTypeface(font1);
        shotstext.setTypeface(font1);
        gpstext.setTypeface(font1);
        leftArrow = (Button) findViewById(R.id.leftarrow);
        rightArrow = (Button) findViewById(R.id.rightarrow);


        loadData();

        for(int i = 0;i<18;i++)   saveData(i);

        scoretext = (TextView) findViewById(R.id.idscore);
        puutstext = (TextView) findViewById(R.id.idputts);
        offtheteetext = (TextView) findViewById(R.id.idofthetee);
        teeaccuracytext = (TextView) findViewById(R.id.idteeaccuracy);
        sandtext = (TextView) findViewById(R.id.idsand);
        penaltiestext = (TextView) findViewById(R.id.idpenalties);


        holeNumber = (TextView) findViewById(R.id.holeNumber);

        previousDist = (TextView) findViewById(R.id.distprev);
        currentDist = (TextView) findViewById(R.id.discurrent);
        nextDist = (TextView) findViewById(R.id.distnext);
        previousDist.setText("" +HoleNumberArrayDistance1[0]);
        currentDist.setText("" + HoleNumberArrayDistance[0]);
        nextDist.setText("" + HoleNumberArrayDistance2[0]);
        for(int i = 0;i < 5;i++) {
            int resID = getResources().getIdentifier("FrameLayout"+(i + 2), "id", getPackageName());
            FrameLayout[i] = (FrameLayout) findViewById(resID);
        }

        linmorestats = (LinearLayout) findViewById(R.id.linearmorestats);
        linlessstat = (LinearLayout) findViewById(R.id.linlessstats);

        LessState = (Button) findViewById(R.id.buttonlaessstats);
        MoreStats = (Button) findViewById(R.id.BUTTONMORESTATS);
        SharedPreferences sharedpresetprofile= getSharedPreferences("profileset", MODE_PRIVATE);


        headerLeft_button.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Bundle bundle = new Bundle();
                    bundle.putString("SCREENNAME","FROMSTARTROUND");
                    Intent nextScreen = new Intent(StartRound.this, ScoreCard.class);
                    nextScreen.putExtras(bundle);
                    startActivity(nextScreen);
                    finish();

                }
                return false;
            }

        });

        gps.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    gps.setBackgroundColor(Color.parseColor("#51677e"));
                   // m_buttonGps.setTextColor(Color.WHITE);

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    gps.setBackgroundColor(Color.parseColor("#10171f"));
                   // m_buttonGps.setTextColor(Color.WHITE);

                    SharedPreferences sharedpregpshole= getSharedPreferences("HoleGps", MODE_PRIVATE);
                    sharedpregpshole.edit().clear().commit();
                    Intent nextScreen = new Intent(StartRound.this, MapsActivity.class);

                    startActivity(nextScreen);
                    finish();
                }
                return false;
            }

        });

        SR_teeslinear.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {


                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    SharedPreferences sharedpreferencesRS= getSharedPreferences("SCREENNAME" , MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                    editor.putString("screenname" , "StartRound");
                    editor.commit();
                    Bundle bundle = new Bundle();
                    // bundle.putString("FROMACTIVITY_WHERE","ROUNDSEETING");
                    if(profileNo == 0) {
                        bundle.putString("MemberNumber", "first");
                        bundle.putString("Membername","You");
                    }
                    else
                    if(profileNo == 1) {
                        bundle.putString("MemberNumber", "second");
                        bundle.putString("Membername",""+GolferName[0]);
                    }
                    else
                    if(profileNo == 2) {
                        bundle.putString("MemberNumber", "third");
                        bundle.putString("Membername",""+GolferName[1]);
                    }
                    else
                    if(profileNo == 3) {
                        bundle.putString("MemberNumber", "forth");
                        bundle.putString("Membername",""+GolferName[2]);
                    }
                    Intent NextScreen = new Intent(StartRound.this, TeeSelection.class);
                    NextScreen.putExtras(bundle);
                    startActivity(NextScreen);
                    finish();
                  //  Intent nextScreen = new Intent(StartRound.this, MapsActivity.class);

                   // startActivity(nextScreen);
                }
                return false;
            }

        });

        score.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                   // gps.setBackgroundColor(Color.parseColor("#51677e"));
                   // m_buttonGps.setTextColor(Color.WHITE);

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    score.setBackgroundColor(Color.parseColor("#51677e"));
                    shots.setBackgroundColor(Color.parseColor("#10171f"));
                   // m_buttonGps.setTextColor(Color.WHITE);
                    SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                    editor.putBoolean("scoreshot" ,false);
                    editor.commit();
                    scrollView_score.setVisibility(View.VISIBLE);
                    scrollView_Shots.setVisibility(View.GONE);
                    SR_relativeLayoutteesselect.setVisibility(View.GONE);
                    dialog=new ProgressDialog(StartRound.this,R.style.MyAlertDialogStyle);
                    dialog.setProgressStyle(android.R.style.Widget_Holo_ProgressBar_Large);
                    // dialog.setMessage("Loading...");

                    WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                    dialog.setCancelable(false);
                    // dialog.setInverseBackgroundForced(false);
                    dialog.show();
                    for(int i = 0;i<18;i++) saveData(i);
                    Intent refresh = new Intent(StartRound.this,StartRound.class);
                    dialog.cancel();
                    startActivity(refresh);
                    finish();
                }
                return false;
            }

        });
  shots.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                   // gps.setBackgroundColor(Color.parseColor("#51677e"));
                   // m_buttonGps.setTextColor(Color.WHITE);

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    if(profileNo == 0 && (m_teeselected.equals("")||m_teeselected.equals("NO TEES SELECTED")))
                    {
                        scrollView_score.setVisibility(View.GONE);
                        scrollView_Shots.setVisibility(View.GONE);
                        shots.setBackgroundColor(Color.parseColor("#51677e"));
                        score.setBackgroundColor(Color.parseColor("#10171f"));
                        // m_buttonGps.setTextColor(Color.WHITE);
                        SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                        editor.putBoolean("scoreshot", true);
                        editor.commit();
                        SR_relativeLayoutteesselect.setVisibility(View.VISIBLE);
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
                            SR_userImage.setImageDrawable(drawable);

                        }
                        else {
                            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),
                                    R.drawable.profile_default);
                            RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bitmap);
                            SR_userImage.setImageDrawable(drawable);
                           // SR_userImage.setBackgroundResource(R.drawable.profile_default);
                        }
                        SR_Youid.setText("You");
                        Toast.makeText(StartRound.this,"You must select a tee to begin Shot Tracking",Toast.LENGTH_SHORT).show();
                    }
                    else
                    if(profileNo == 1 && (m_teeselected1.equals("")||m_teeselected1.equals("NO TEES SELECTED")))
                    {
                        scrollView_score.setVisibility(View.GONE);
                        scrollView_Shots.setVisibility(View.GONE);
                        shots.setBackgroundColor(Color.parseColor("#51677e"));
                        score.setBackgroundColor(Color.parseColor("#10171f"));
                        // m_buttonGps.setTextColor(Color.WHITE);
                        SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                        editor.putBoolean("scoreshot", true);
                        editor.commit();
                        SR_relativeLayoutteesselect.setVisibility(View.VISIBLE);
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
                            SR_userImage.setImageDrawable(drawable);

                        }
                        else {
                            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),
                                    R.drawable.profile_default);
                            RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bitmap);
                            SR_userImage.setImageDrawable(drawable);
                            // SR_userImage.setBackgroundResource(R.drawable.profile_default);
                        }
                        //SR_userImage.setBackgroundResource(R.drawable.profile_pick3);
                        SR_Youid.setText(""+GolferName[0]);
                        Toast.makeText(StartRound.this,"You must select a tee to begin Shot Tracking",Toast.LENGTH_SHORT).show();


                    }
                    else
                    if(profileNo == 2 && (m_teeselected2.equals("")||m_teeselected2.equals("NO TEES SELECTED")))
                    {
                        scrollView_score.setVisibility(View.GONE);
                        scrollView_Shots.setVisibility(View.GONE);
                        shots.setBackgroundColor(Color.parseColor("#51677e"));
                        score.setBackgroundColor(Color.parseColor("#10171f"));
                        // m_buttonGps.setTextColor(Color.WHITE);
                        SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                        editor.putBoolean("scoreshot", true);
                        editor.commit();
                        SR_relativeLayoutteesselect.setVisibility(View.VISIBLE);
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
                            SR_userImage.setImageDrawable(drawable);

                        }
                        else {
                            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),
                                    R.drawable.profile_default);
                            RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bitmap);
                            SR_userImage.setImageDrawable(drawable);
                            // SR_userImage.setBackgroundResource(R.drawable.profile_default);
                        }
                        SR_Youid.setText(""+GolferName[1]);
                        Toast.makeText(StartRound.this,"You must select a tee to begin Shot Tracking",Toast.LENGTH_SHORT).show();

                    }
                    else
                    if(profileNo == 3 && (m_teeselected3.equals("")||m_teeselected3.equals("NO TEES SELECTED")))
                    {
                        scrollView_score.setVisibility(View.GONE);
                        scrollView_Shots.setVisibility(View.GONE);
                        shots.setBackgroundColor(Color.parseColor("#51677e"));
                        score.setBackgroundColor(Color.parseColor("#10171f"));
                        // m_buttonGps.setTextColor(Color.WHITE);
                        SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                        editor.putBoolean("scoreshot", true);
                        editor.commit();
                        SR_relativeLayoutteesselect.setVisibility(View.VISIBLE);
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
                            SR_userImage.setImageDrawable(drawable);

                        }
                        else {
                            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),
                                    R.drawable.profile_default);
                            RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bitmap);
                            SR_userImage.setImageDrawable(drawable);
                            // SR_userImage.setBackgroundResource(R.drawable.profile_default);
                        }
                      //  SR_userImage.setBackgroundResource(R.drawable.profile_pick1);
                        SR_Youid.setText(""+GolferName[2]);
                        Toast.makeText(StartRound.this,"You must select a tee to begin Shot Tracking",Toast.LENGTH_SHORT).show();

                    }
                    else {
                        shots.setBackgroundColor(Color.parseColor("#51677e"));
                        score.setBackgroundColor(Color.parseColor("#10171f"));
                        // m_buttonGps.setTextColor(Color.WHITE);
                        SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                        editor.putBoolean("scoreshot", true);
                        editor.commit();
                        scrollView_score.setVisibility(View.GONE);
                        scrollView_Shots.setVisibility(View.VISIBLE);
                        dialog=new ProgressDialog(StartRound.this,R.style.MyAlertDialogStyle);
                        dialog.setProgressStyle(android.R.style.Widget_Holo_ProgressBar_Large);
                        // dialog.setMessage("Loading...");

                        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                        dialog.setCancelable(false);
                        // dialog.setInverseBackgroundForced(false);
                        dialog.show();
                        for(int i = 0;i<18;i++)   saveData(i);
                        Intent refresh = new Intent(StartRound.this,StartRound.class);
                        dialog.cancel();
                        startActivity(refresh);
                        finish();
                    }
                }
                return false;
            }

        });
        Buttonshottrack.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                   // gps.setBackgroundColor(Color.parseColor("#51677e"));
                   // m_buttonGps.setTextColor(Color.WHITE);

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    RelativeLayoutforblueteesON.setVisibility(View.GONE);
                    RelativeLayoutforblueteesOFF.setVisibility(View.VISIBLE);
                   // enableAddshot = true;

                }
                return false;
            }

        });
        profileNo = sharedpresetprofile.getInt("profileno",0);
        holeNumVar = sharedpresetprofile.getInt("holeno",0);
        SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
        index = sharedpreferencesCLUB.getInt("clubselectTotalindex"+profileNo+""+holeNumVar, 0);
        shotenable = sharedpreferencesCLUB.getBoolean("scoreshot",false);
        if(shotenable) {
            shots.setBackgroundColor(Color.parseColor("#51677e"));
            score.setBackgroundColor(Color.parseColor("#10171f"));
            scrollView_score.setVisibility(View.GONE);
            if(profileNo == 0 && (m_teeselected.equals("")||m_teeselected.equals("NO TEES SELECTED")))
            {
                scrollView_score.setVisibility(View.GONE);
                scrollView_Shots.setVisibility(View.GONE);
                shots.setBackgroundColor(Color.parseColor("#51677e"));
                score.setBackgroundColor(Color.parseColor("#10171f"));
                // m_buttonGps.setTextColor(Color.WHITE);
              //  SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                editor.putBoolean("scoreshot", true);
                editor.commit();
                SR_relativeLayoutteesselect.setVisibility(View.VISIBLE);
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
                    SR_userImage.setImageDrawable(drawable);

                }
                else {
                   Bitmap bitmap= BitmapFactory.decodeResource(getResources(),
                            R.drawable.profile_default);
                    RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bitmap);
                    SR_userImage.setImageDrawable(drawable);
                   // SR_userImage.setBackgroundResource(R.drawable.profile_default);
                }
                SR_Youid.setText("You");
                Toast.makeText(StartRound.this,"You must select a tee to begin Shot Tracking",Toast.LENGTH_SHORT).show();
            }
            else
            if(profileNo == 1 && (m_teeselected1.equals("")||m_teeselected1.equals("NO TEES SELECTED")))
            {
                scrollView_score.setVisibility(View.GONE);
                scrollView_Shots.setVisibility(View.GONE);
                shots.setBackgroundColor(Color.parseColor("#51677e"));
                score.setBackgroundColor(Color.parseColor("#10171f"));
                // m_buttonGps.setTextColor(Color.WHITE);
              //  SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                editor.putBoolean("scoreshot", true);
                editor.commit();
                SR_relativeLayoutteesselect.setVisibility(View.VISIBLE);
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
                    SR_userImage.setImageDrawable(drawable);

                }
                else {
                    Bitmap bitmap= BitmapFactory.decodeResource(getResources(),
                            R.drawable.profile_default);
                    RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bitmap);
                    SR_userImage.setImageDrawable(drawable);
                    // SR_userImage.setBackgroundResource(R.drawable.profile_default);
                }
              //  SR_userImage.setBackgroundResource(R.drawable.profile_pick3);
                SR_Youid.setText(""+GolferName[0]);
                Toast.makeText(StartRound.this,"You must select a tee to begin Shot Tracking",Toast.LENGTH_SHORT).show();


            }
            else
            if(profileNo == 2 && (m_teeselected2.equals("")||m_teeselected2.equals("NO TEES SELECTED")))
            {
                scrollView_score.setVisibility(View.GONE);
                scrollView_Shots.setVisibility(View.GONE);
                shots.setBackgroundColor(Color.parseColor("#51677e"));
                score.setBackgroundColor(Color.parseColor("#10171f"));
                // m_buttonGps.setTextColor(Color.WHITE);
             //   SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                editor.putBoolean("scoreshot", true);
                editor.commit();
                SR_relativeLayoutteesselect.setVisibility(View.VISIBLE);
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
                    SR_userImage.setImageDrawable(drawable);

                }
                else {
                    Bitmap bitmap= BitmapFactory.decodeResource(getResources(),
                            R.drawable.profile_default);
                    RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bitmap);
                    SR_userImage.setImageDrawable(drawable);
                    // SR_userImage.setBackgroundResource(R.drawable.profile_default);
                }
                //SR_userImage.setBackgroundResource(R.drawable.profile_pick2);
                SR_Youid.setText(""+GolferName[1]);
                Toast.makeText(StartRound.this,"You must select a tee to begin Shot Tracking",Toast.LENGTH_SHORT).show();

            }
            else
            if(profileNo == 3 && (m_teeselected3.equals("")||m_teeselected3.equals("NO TEES SELECTED")))
            {
                scrollView_score.setVisibility(View.GONE);
                scrollView_Shots.setVisibility(View.GONE);
                shots.setBackgroundColor(Color.parseColor("#51677e"));
                score.setBackgroundColor(Color.parseColor("#10171f"));
                // m_buttonGps.setTextColor(Color.WHITE);
               // SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                editor.putBoolean("scoreshot", true);
                editor.commit();
                SR_relativeLayoutteesselect.setVisibility(View.VISIBLE);
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
                    SR_userImage.setImageDrawable(drawable);

                }
                else {
                    Bitmap bitmap= BitmapFactory.decodeResource(getResources(),
                            R.drawable.profile_default);
                    RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bitmap);
                    SR_userImage.setImageDrawable(drawable);
                    // SR_userImage.setBackgroundResource(R.drawable.profile_default);
                }
               // SR_userImage.setBackgroundResource(R.drawable.profile_pick1);
                SR_Youid.setText(""+GolferName[2]);
                Toast.makeText(StartRound.this,"You must select a tee to begin Shot Tracking",Toast.LENGTH_SHORT).show();

            }
            else
            scrollView_Shots.setVisibility(View.VISIBLE);
        }

        if(index >0)
        {


            for(int i=0;i<index;i++){
                final int val = i;
                Buttonclubsel = new Button[i+1];
                FrameButtonclubsel = new FrameLayout[i+1];
                Textshotcount = new TextView[i+1];
                Frameendpoint = new FrameLayout[i+1];
                Frameaccubutton = new FrameLayout[i+1];
                Accuracy      = new Button[i+1];
                Accuracy1      = new Button[i+1];
                accutext = new TextView[i+1];
                endpoint1 = new TextView[i+1];
                endpoint2 = new TextView[i+1];

                endpointImage = new ImageView[i+1];
                ButtonshotDelete      = new LinearLayout[i+1];
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View convertView = inflater.inflate(R.layout.addshot, null);
                final LinearLayout childln  = (LinearLayout) convertView.findViewById(R.id.shotCustomlinear);
                Textshotcount[i] = new TextView(StartRound.this);
                Textshotcount[i] = (TextView)childln.findViewById(R.id.textshotcount);
                Textshotcount[i].setText(""+(i+1));
                Buttonclubsel[i] = new Button(StartRound.this);
                FrameButtonclubsel[i] = new FrameLayout(StartRound.this);
                Buttonclubsel[i] = (Button)childln.findViewById(R.id.buttonclubsel);
                FrameButtonclubsel[i] = (FrameLayout) childln.findViewById(R.id.f1);
                Buttonclubsel[i].setText(""+sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+i,"Club"));
                if(sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+i,"Club").equals("Club"))
                {
                    Buttonclubsel[i].setBackgroundColor(Color.parseColor("#ffffff"));
                    Buttonclubsel[i].setTextColor(Color.parseColor("#a5a6a8"));
                    FrameButtonclubsel[i].setBackgroundColor(Color.parseColor("#a5a6a8"));
                }
                else
                {
                    Buttonclubsel[i].setBackgroundColor(Color.parseColor("#accb3f"));
                    FrameButtonclubsel[i].setBackgroundColor(Color.parseColor("#accb3f"));
                    Buttonclubsel[i].setTextColor(Color.parseColor("#ffffff"));

                }
                Frameaccubutton[i] = new FrameLayout(StartRound.this);
                Frameaccubutton[i] = (FrameLayout) childln.findViewById(R.id.frameaccubutton);

                Frameendpoint[i] = new FrameLayout(StartRound.this);
                Frameendpoint[i] = (FrameLayout) childln.findViewById(R.id.frameendpoint);

                endpoint1[i] = new TextView(StartRound.this);
                endpoint1[i]  = (TextView) childln.findViewById(R.id.endpointtext1);
                endpointImage[i] = new ImageView(StartRound.this);
                endpointImage[i] =  (ImageView) childln.findViewById(R.id.endpointim);
                endpoint2[i] = new TextView(StartRound.this);
                endpoint2[i]  = (TextView) childln.findViewById(R.id.endpointtext2);
                if(sharedpreferencesCLUB.getString("endPoint"+profileNo+""+holeNumVar+""+i,"End point").equals("End point"))
                {
                    endpoint1[i].setBackgroundColor(Color.parseColor("#ffffff"));

                    Frameendpoint[i].setBackgroundColor(Color.parseColor("#a5a6a8"));
                    endpoint2[i].setTextColor(Color.parseColor("#a5a6a8"));
                    endpointImage[i].setBackgroundResource(R.drawable.shots);
                    if(!sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+i,"Club").equals("P")) {
                        endpointImage[i].setVisibility(View.VISIBLE);
                        endpoint2[i].setText("End \n point");
                    }
                    else {
                        endpointImage[i].setVisibility(View.GONE);
                        endpoint2[i].setText("DISTANCE");
                        DisplayMetrics dm = getResources().getDisplayMetrics();
                        float dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, dm);
                        endpoint2[i].setPadding(0,0,(int)dpInPx,0);
                    }

                }
                else
                {
                    endpoint1[i].setBackgroundColor(Color.parseColor("#accb3f"));
                    Frameendpoint[i].setBackgroundColor(Color.parseColor("#accb3f"));
                    endpoint2[i].setTextColor(Color.parseColor("#ffffff"));
                    endpointImage[i].setBackgroundResource(R.drawable.shotsselected);
                    if(!sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+i,"Club").equals("P")) {
                        endpointImage[i].setVisibility(View.VISIBLE);
                        endpoint2[i].setText(""+sharedpreferencesCLUB.getString("endPoint"+profileNo+""+holeNumVar+""+i,"End point")+" Yards");
                        DisplayMetrics dm = getResources().getDisplayMetrics();
                        float dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, dm);
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) endpoint2[i]
                                .getLayoutParams();

                        layoutParams.setMargins((int)dpInPx, 0, 0, 0);
                        endpoint2[i].setLayoutParams(layoutParams);
                       // endpointImage[i].setPadding(0,0,(int)dpInPx,0);
                    }
                    else {
                        endpoint2[i].setText(""+sharedpreferencesCLUB.getString("endPoint"+profileNo+""+holeNumVar+""+i,"End point")+" Feet");
                        endpointImage[i].setVisibility(View.GONE);
                        DisplayMetrics dm = getResources().getDisplayMetrics();
                        float dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, dm);
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) endpoint2[i]
                                .getLayoutParams();
                        layoutParams.setMargins(0, 0, (int)dpInPx, 0);
                        layoutParams.gravity = Gravity.CENTER;
                        endpoint2[i].setLayoutParams(layoutParams);
                       // endpoint2[i].setGravity(Gravity.CENTER);
                    }

                }
                Accuracy[i]  = new Button(StartRound.this);
                Accuracy1[i] = new Button(StartRound.this);
                accutext[i]  = new TextView(StartRound.this);
                Accuracy[i]  = (Button) childln.findViewById(R.id.buttonaccuracyshot);
                Accuracy1[i] = (Button) childln.findViewById(R.id.buttonaccuracyshot1);
                accutext[i]  = (TextView) childln.findViewById(R.id.aacutext);
                if(getScreenResolution(this) == 480 )
                {
                    Accuracy[i].setTextSize(dpToPx(9));
                }
                int accuval = sharedpreferencesCLUB.getInt("accuracyselect"+profileNo+""+holeNumVar+""+i, 0);
                if(accuval == 1)
                {
                    Accuracy[i].setVisibility(View.GONE);
                   // accutext[i].setVisibility(View.GONE);
                    accutext[i].setBackgroundColor(Color.parseColor("#accb3f"));
                    Accuracy1[i].setVisibility(View.VISIBLE);
                    Accuracy1[i].setBackgroundResource(R.drawable.upleftlabel);
                    Accuracy1[i].setText("");
                    Frameaccubutton[i].setBackgroundColor(Color.parseColor("#accb3f"));
                    if(!sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+i,"Club").equals("P")) {

                        DisplayMetrics dm = getResources().getDisplayMetrics();
                        float dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, dm);
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) endpoint2[i]
                                .getLayoutParams();

                        layoutParams.setMargins((int)dpInPx, 0, 0, 0);
                        endpoint2[i].setLayoutParams(layoutParams);
                    }
                }
                else
                if(accuval == 2)
                {

                    Accuracy[i].setVisibility(View.GONE);
                  //  accutext[i].setVisibility(View.GONE);
                    accutext[i].setBackgroundColor(Color.parseColor("#accb3f"));

                    Accuracy1[i].setVisibility(View.VISIBLE);

                    Accuracy1[i].setBackgroundResource(R.drawable.upcenter);
                    Accuracy1[i].setText("");
                    Frameaccubutton[i].setBackgroundColor(Color.parseColor("#accb3f"));
                    if(!sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+i,"Club").equals("P")) {

                        DisplayMetrics dm = getResources().getDisplayMetrics();
                        float dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, dm);
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) endpoint2[i]
                                .getLayoutParams();

                        layoutParams.setMargins((int)dpInPx, 0, 0, 0);
                        endpoint2[i].setLayoutParams(layoutParams);
                    }

                }
                else
                if(accuval == 3)
                {
                    Accuracy[i].setVisibility(View.GONE);
                   // accutext[i].setVisibility(View.GONE);
                    accutext[i].setBackgroundColor(Color.parseColor("#accb3f"));

                    Accuracy1[i].setVisibility(View.VISIBLE);
                    Accuracy1[i].setBackgroundResource(R.drawable.uprightlabel);
                    Accuracy1[i].setText("");
                    Frameaccubutton[i].setBackgroundColor(Color.parseColor("#accb3f"));
                    if(!sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+i,"Club").equals("P")) {

                        DisplayMetrics dm = getResources().getDisplayMetrics();
                        float dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, dm);
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) endpoint2[i]
                                .getLayoutParams();

                        layoutParams.setMargins((int)dpInPx, 0, 0, 0);
                        endpoint2[i].setLayoutParams(layoutParams);
                    }

                }
                else
                if(accuval == 4)
                {
                    Accuracy[i].setVisibility(View.GONE);
                   // accutext[i].setVisibility(View.GONE);
                    accutext[i].setBackgroundColor(Color.parseColor("#accb3f"));

                    Accuracy1[i].setVisibility(View.VISIBLE);
                    Accuracy1[i].setBackgroundResource(R.drawable.leftcenter);
                    Accuracy1[i].setText("");
                    Frameaccubutton[i].setBackgroundColor(Color.parseColor("#accb3f"));
                    if(!sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+i,"Club").equals("P")) {

                        DisplayMetrics dm = getResources().getDisplayMetrics();
                        float dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, dm);
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) endpoint2[i]
                                .getLayoutParams();

                        layoutParams.setMargins((int)dpInPx, 0, 0, 0);
                        endpoint2[i].setLayoutParams(layoutParams);
                    }

                }
                else
                if(accuval == 5)
                {
                    Accuracy[i].setVisibility(View.GONE);
                   // accutext[i].setVisibility(View.GONE);
                    accutext[i].setBackgroundColor(Color.parseColor("#accb3f"));

                    Accuracy1[i].setVisibility(View.VISIBLE);

                    Accuracy1[i].setBackgroundResource(R.drawable.target_un);
                    Accuracy1[i].setText("");
                    Frameaccubutton[i].setBackgroundColor(Color.parseColor("#accb3f"));
                    if(!sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+i,"Club").equals("P")) {

                        DisplayMetrics dm = getResources().getDisplayMetrics();
                        float dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, dm);
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) endpoint2[i]
                                .getLayoutParams();

                        layoutParams.setMargins((int)dpInPx, 0, 0, 0);
                        endpoint2[i].setLayoutParams(layoutParams);
                    }

                }
                else
                if(accuval == 6)
                {
                    Accuracy[i].setVisibility(View.GONE);
                 //   accutext[i].setVisibility(View.GONE);
                    accutext[i].setBackgroundColor(Color.parseColor("#accb3f"));

                    Accuracy1[i].setVisibility(View.VISIBLE);
                    Accuracy1[i].setBackgroundResource(R.drawable.rightcenter);
                    Accuracy1[i].setText("");
                    Frameaccubutton[i].setBackgroundColor(Color.parseColor("#accb3f"));
                    if(!sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+i,"Club").equals("P")) {

                        DisplayMetrics dm = getResources().getDisplayMetrics();
                        float dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, dm);
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) endpoint2[i]
                                .getLayoutParams();

                        layoutParams.setMargins((int)dpInPx, 0, 0, 0);
                        endpoint2[i].setLayoutParams(layoutParams);
                    }

                }
                else
                if(accuval == 7)
                {
                    Accuracy[i].setVisibility(View.GONE);
                   // accutext[i].setVisibility(View.GONE);
                    accutext[i].setBackgroundColor(Color.parseColor("#accb3f"));

                    Accuracy1[i].setVisibility(View.VISIBLE);
                    Accuracy1[i].setBackgroundResource(R.drawable.downleftlabel);
                    Accuracy1[i].setText("");
                    Frameaccubutton[i].setBackgroundColor(Color.parseColor("#accb3f"));
                    if(!sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+i,"Dr").equals("P")) {

                        DisplayMetrics dm = getResources().getDisplayMetrics();
                        float dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, dm);
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) endpoint2[i]
                                .getLayoutParams();

                        layoutParams.setMargins((int)dpInPx, 0, 0, 0);
                        endpoint2[i].setLayoutParams(layoutParams);
                    }

                }
                else
                if(accuval == 8)
                {
                    Accuracy[i].setVisibility(View.GONE);
                  //  accutext[i].setVisibility(View.GONE);
                    accutext[i].setBackgroundColor(Color.parseColor("#accb3f"));

                    Accuracy1[i].setVisibility(View.VISIBLE);
                    Accuracy1[i].setBackgroundResource(R.drawable.downcenter);
                    Accuracy1[i].setText("");
                    Frameaccubutton[i].setBackgroundColor(Color.parseColor("#accb3f"));
                    if(!sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+i,"Dr").equals("P")) {

                        DisplayMetrics dm = getResources().getDisplayMetrics();
                        float dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, dm);
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) endpoint2[i]
                                .getLayoutParams();

                        layoutParams.setMargins((int)dpInPx, 0, 0, 0);
                        endpoint2[i].setLayoutParams(layoutParams);
                    }

                }
                else
                if(accuval == 9)
                {
                    Accuracy[i].setVisibility(View.GONE);
                   // accutext[i].setVisibility(View.GONE);
                    accutext[i].setBackgroundColor(Color.parseColor("#accb3f"));

                    Accuracy1[i].setVisibility(View.VISIBLE);
                    Accuracy1[i].setBackgroundResource(R.drawable.downrightlabel);
                    Accuracy1[i].setText("");
                    Frameaccubutton[i].setBackgroundColor(Color.parseColor("#accb3f"));
                    if(!sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+i,"Dr").equals("P")) {

                        DisplayMetrics dm = getResources().getDisplayMetrics();
                        float dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, dm);
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) endpoint2[i]
                                .getLayoutParams();

                        layoutParams.setMargins((int)dpInPx, 0, 0, 0);
                        endpoint2[i].setLayoutParams(layoutParams);
                    }

                }
                else
                {
                    Accuracy1[i].setVisibility(View.GONE);
                    //accutext[i].setVisibility(View.VISIBLE);
                    accutext[i].setBackgroundColor(Color.parseColor("#ffffff"));

                    Accuracy[i].setVisibility(View.VISIBLE);
                    Accuracy[i].setText("Accuracy");
                    Frameaccubutton[i].setBackgroundColor(Color.parseColor("#a5a6a8"));

                }

                ButtonshotDelete[i] = new LinearLayout(StartRound.this);
                ButtonshotDelete[i]   = (LinearLayout) childln.findViewById(R.id.lin1newshot);
                Linearscrollshot.addView(childln, 3+i);
                if(Buttonclubsel[i] != null)
                    Buttonclubsel[i].setOnTouchListener(new View.OnTouchListener() {

                        @Override
                        public boolean onTouch(View view, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                                if(!sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+val,"Dr").equals("P")) {
                                Log.i("value", "index clubsel===================" + val);
                              //  SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                editor.putInt("clubselectindex"+profileNo+""+holeNumVar, val);
                                editor.putInt("SRprofileNo", profileNo);
                                editor.putInt("SRholeNumVar", holeNumVar);
                                editor.commit();
                                Intent nextScreen = new Intent(StartRound.this, ShotClubSelection.class);

                                startActivity(nextScreen);
                                    finish();
                            }
                            }
                            return false;
                        }

                    });
                if(Accuracy[i] != null)
                    Accuracy[i].setOnTouchListener(new View.OnTouchListener() {

                        @Override
                        public boolean onTouch(View view, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                                Log.i("value","index  accuracy ==================="+val);
                                SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                editor.putInt("clubselectindex"+profileNo+""+holeNumVar ,val);
                                editor.putInt("SRprofileNo", profileNo);
                                editor.putInt("SRholeNumVar", holeNumVar);
                                if(sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+val,"Dr").equals("P")) {
                                    editor.putInt("accuracyFor", 1);
                                }
                                else
                                {
                                    editor.putInt("accuracyFor", 0);

                                }
                                editor.commit();
                                Intent nextScreen = new Intent(StartRound.this, ShotAccuracy.class);

                                startActivity(nextScreen);
                                finish();
                            }
                            return false;
                        }

                    });
                if(Accuracy1[i] != null)
                    Accuracy1[i].setOnTouchListener(new View.OnTouchListener() {

                        @Override
                        public boolean onTouch(View view, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                                Log.i("value","index  accuracy ==================="+val);
                                SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                editor.putInt("clubselectindex"+profileNo+""+holeNumVar ,val);
                                editor.putInt("SRprofileNo", profileNo);
                                editor.putInt("SRholeNumVar", holeNumVar);
                                editor.commit();
                                Intent nextScreen = new Intent(StartRound.this, ShotAccuracy.class);

                                startActivity(nextScreen);
                                finish();
                            }
                            return false;
                        }

                    });
                if(Frameendpoint[i] != null)
                    Frameendpoint[i].setOnTouchListener(new View.OnTouchListener() {

                        @Override
                        public boolean onTouch(View view, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                if(val > 0)
                                {
                                    boolean check = false;
                                    int shotNumber = 0;
                                    SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                    if(sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+val,"Club").equals("P"))
                                    {
                                       // SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                                      //  SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                        editor.putInt("clubselectindex"+profileNo+""+holeNumVar, val);
                                        editor.putInt("SRprofileNo", profileNo);
                                        editor.putInt("SRholeNumVar", holeNumVar);
                                        editor.commit();
                                        Intent nextScreen = new Intent(StartRound.this, PuttDistance.class);
                                        startActivity(nextScreen);
                                        finish();
                                    }
                                    else {
                                        for(int i = val;i >0;i--) {
                                            if (sharedpreferencesCLUB.getString("endPoint"+profileNo+""+holeNumVar+"" + (i - 1), "End point").equals("End point")) {
                                                check = true;
                                                shotNumber =i;
                                                Log.i("valjue ","check===="+check);
                                            }
                                        }

                                        if(!check)
                                        {
                                            // SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                                            //     SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                            // editor.putInt("clubselectindex", val);
                                            // editor.commit();
                                            // Intent nextScreen = new Intent(StartRound.this, PuttDistance.class);
                                            // startActivity(nextScreen);

                                            locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                                            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                                            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 20000, 1, StartRound.this);
                                            if(location != null) {
                                                Toast.makeText(getBaseContext(), "End Point Plotted Lat/Long" + location.getLatitude() + "/" + location.getLongitude(), Toast.LENGTH_SHORT).show();
                                                editor.putString("endPoint" + profileNo + "" + holeNumVar + "" + val, "" + Math.round(location.getLongitude()));
                                                editor.commit();
                                                dialog = new ProgressDialog(StartRound.this, R.style.MyAlertDialogStyle);
                                                dialog.setProgressStyle(android.R.style.Widget_Holo_ProgressBar_Large);
                                                // dialog.setMessage("Loading...");

                                                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                                                dialog.setCancelable(false);
                                                // dialog.setInverseBackgroundForced(false);
                                                dialog.show();
                                                Intent PreviousScreen = new Intent(StartRound.this, StartRound.class);
                                                dialog.cancel();
                                                startActivity(PreviousScreen);
                                                finish();
                                            }
                                            else {
                                                Toast.makeText(getBaseContext(), "Enable Loction Services", Toast.LENGTH_SHORT).show();

                                            }


                                        }
                                        else
                                        {
                                            Toast.makeText(StartRound.this,"You must plot shot "+(shotNumber)+" before plotting shot "+(val+1),Toast.LENGTH_LONG).show();
                                        }
                                    }

                                }
                                else {
                                    SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                    if(sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+val,"Club").equals("P"))
                                    {
                                        // SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                                        //  SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                        editor.putInt("clubselectindex", val);
                                        editor.putInt("SRprofileNo", profileNo);
                                        editor.putInt("SRholeNumVar", holeNumVar);
                                        editor.commit();
                                        Intent nextScreen = new Intent(StartRound.this, PuttDistance.class);
                                        startActivity(nextScreen);
                                        finish();
                                    }
                                    else
                                    {
                                        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                                        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 20000, 1, StartRound.this);
                                        if(location != null) {
                                            Toast.makeText(getBaseContext(), "End Point Plotted Lat/Long" + location.getLatitude() + "/" + location.getLongitude(), Toast.LENGTH_SHORT).show();
                                            editor.putString("endPoint" + profileNo + "" + holeNumVar + "" + val, "" + Math.round(location.getLongitude()));
                                            editor.commit();
                                            dialog = new ProgressDialog(StartRound.this, R.style.MyAlertDialogStyle);
                                            dialog.setProgressStyle(android.R.style.Widget_Holo_ProgressBar_Large);
                                            // dialog.setMessage("Loading...");

                                            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();

                                            dialog.setCancelable(false);
                                            // dialog.setInverseBackgroundForced(false);
                                            dialog.show();
                                            Intent PreviousScreen = new Intent(StartRound.this, StartRound.class);
                                            dialog.cancel();
                                            startActivity(PreviousScreen);
                                            finish();
                                        }
                                        else {
                                            Toast.makeText(getBaseContext(), "Enable Loction Services", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                }
                            }
                            return false;
                        }

                    });
                if(ButtonshotDelete[i] != null)
                    ButtonshotDelete[i].setOnTouchListener(new View.OnTouchListener() {

                        @Override
                        public boolean onTouch(View view, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                                Log.i("value","index delete==================="+val);
                                if(val == index-1) {
                                    Linearscrollshot.removeViewAt(3+val);

                                    index--;
                                  //  SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                                  //  SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                    SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                    editor.remove("clubselectindex"+profileNo+""+holeNumVar+""+index);
                                    editor.remove("accuracyselect"+profileNo+""+holeNumVar+""+index);
                                    editor.remove("endPoint"+profileNo+""+holeNumVar+""+index);
                                    editor.putInt("clubselectTotalindex"+profileNo+""+holeNumVar ,index);
                                    editor.commit();
                                   /* if(index == 0)
                                    {
                                        RelativeLayoutforblueteesON.setVisibility(View.VISIBLE);
                                        RelativeLayoutforblueteesOFF.setVisibility(View.GONE);
                                    }*/
                                }
                            }
                            return false;
                        }

                    });
            }
        }
        Buttonnewshot.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                   // gps.setBackgroundColor(Color.parseColor("#51677e"));
                   // m_buttonGps.setTextColor(Color.WHITE);

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                 // if(enableAddshot)
                  {
                     // RelativeLayoutforblueteesON.setVisibility(View.GONE);
                     // RelativeLayoutforblueteesOFF.setVisibility(View.VISIBLE);
                      Buttonclubsel = new Button[index+1];
                      Textshotcount = new TextView[index+1];
                      Frameendpoint = new FrameLayout[index+1];
                      Accuracy      = new Button[index+1];
                      ButtonshotDelete      = new LinearLayout[index+1];
                      endpoint2 = new TextView[index+1];
                      endpoint1 = new TextView[index+1];
                      endpointImage = new ImageView[index+1];

                      LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                      View convertView = inflater.inflate(R.layout.addshot, null);
                      final LinearLayout childln  = (LinearLayout) convertView.findViewById(R.id.shotCustomlinear);
                      Textshotcount[index] = new TextView(StartRound.this);
                      Textshotcount[index] = (TextView)childln.findViewById(R.id.textshotcount);
                      Textshotcount[index].setText(""+(index+1));
                      Buttonclubsel[index] = new Button(StartRound.this);
                      Buttonclubsel[index] = (Button)childln.findViewById(R.id.buttonclubsel);

                      endpoint1[index] = new TextView(StartRound.this);
                      endpoint1[index]  = (TextView) childln.findViewById(R.id.endpointtext1);
                      endpointImage[index] = new ImageView(StartRound.this);
                      endpointImage[index] =  (ImageView) childln.findViewById(R.id.endpointim);
                      endpoint2[index] = new TextView(StartRound.this);
                      endpoint2[index]  = (TextView) childln.findViewById(R.id.endpointtext2);
                      Log.i("value","text======================"+endpoint2[index].getText());
                      Log.i("value","index======================"+index);
                      Frameendpoint[index] = new FrameLayout(StartRound.this);
                      Frameendpoint[index] = (FrameLayout) childln.findViewById(R.id.frameendpoint);

                      Accuracy[index] = new Button(StartRound.this);
                      Accuracy[index]      = (Button) childln.findViewById(R.id.buttonaccuracyshot);
                      if(getScreenResolution(StartRound.this) == 480 )
                      {
                          Accuracy[index].setTextSize(dpToPx(9));
                      }
                      ButtonshotDelete[index] = new LinearLayout(StartRound.this);
                      ButtonshotDelete[index]   = (LinearLayout) childln.findViewById(R.id.lin1newshot);
                      Linearscrollshot.addView(childln, 3+index);

                      for(int i=0;i<index+1;i++){
                          final int val = i;
                          if(Buttonclubsel[i] != null)
                              Buttonclubsel[i].setOnTouchListener(new View.OnTouchListener() {

                                  @Override
                                  public boolean onTouch(View view, MotionEvent event) {
                                      if (event.getAction() == MotionEvent.ACTION_DOWN) {

                                          Log.i("value","index clubsel==================="+val);
                                          SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                                          SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                          editor.putInt("clubselectindex"+profileNo+""+holeNumVar ,val);
                                          editor.putInt("SRprofileNo", profileNo);
                                          editor.putInt("SRholeNumVar", holeNumVar);
                                          editor.commit();
                                          Intent nextScreen = new Intent(StartRound.this, ShotClubSelection.class);

                                          startActivity(nextScreen);
                                          finish();
                                      }
                                      return false;
                                  }

                              });
                          if(Frameendpoint[i] != null)
                              Frameendpoint[i].setOnTouchListener(new View.OnTouchListener() {

                                  @Override
                                  public boolean onTouch(View view, MotionEvent event) {
                                      if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                          if(val > 0)
                                          {
                                              boolean check = false;
                                              int shotNumber = 0;
                                              SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);

                                              if(sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+val,"Club").equals("P"))
                                              {
                                                  // SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                                                  SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                                  editor.putInt("clubselectindex", val);
                                                  editor.putInt("SRprofileNo", profileNo);
                                                  editor.putInt("SRholeNumVar", holeNumVar);
                                                  editor.commit();
                                                  Intent nextScreen = new Intent(StartRound.this, PuttDistance.class);
                                                  startActivity(nextScreen);
                                                  finish();
                                              }
                                              else {
                                                  for(int i = val;i >0;i--) {
                                                      if (sharedpreferencesCLUB.getString("endPoint"+profileNo+""+holeNumVar+"" + (i - 1), "End point").equals("End point")) {
                                                          check = true;
                                                          shotNumber = i;
                                                          Log.i("valjue ","check===="+check);
                                                      }
                                                  }

                                                  if(!check)
                                                  {
                                                      SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();

                                                      locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                                                      Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                                                      locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 20000, 1, StartRound.this);
                                                      if(location != null) {
                                                          Toast.makeText(getBaseContext(), "End Point Plotted Lat/Long" + location.getLatitude() + "/" + location.getLongitude(), Toast.LENGTH_SHORT).show();
                                                          editor.putString("endPoint" + profileNo + "" + holeNumVar + "" + val, "" + Math.round(location.getLongitude()));
                                                          editor.commit();
                                                          dialog = new ProgressDialog(StartRound.this, R.style.MyAlertDialogStyle);
                                                          dialog.setProgressStyle(android.R.style.Widget_Holo_ProgressBar_Large);
                                                          // dialog.setMessage("Loading...");

                                                          WindowManager.LayoutParams params = dialog.getWindow().getAttributes();

                                                          dialog.setCancelable(false);
                                                          // dialog.setInverseBackgroundForced(false);
                                                          dialog.show();
                                                          Intent PreviousScreen = new Intent(StartRound.this, StartRound.class);
                                                          dialog.cancel();
                                                          startActivity(PreviousScreen);
                                                          finish();
                                                      }

                                                  }
                                                  else
                                                  {
                                                      Toast.makeText(StartRound.this,"You must plot shot "+(shotNumber)+" before plotting shot "+(val+1),Toast.LENGTH_LONG).show();
                                                  }
                                              }


                                          }
                                          else {
                                              SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                                              SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                              if(sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+val,"Club").equals("P"))
                                              {
                                                  // SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                                                  //  SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                                  editor.putInt("clubselectindex", val);
                                                  editor.putInt("SRprofileNo", profileNo);
                                                  editor.putInt("SRholeNumVar", holeNumVar);
                                                  editor.commit();
                                                  Intent nextScreen = new Intent(StartRound.this, PuttDistance.class);
                                                  startActivity(nextScreen);
                                                  finish();
                                              }
                                              else
                                              {
                                                  locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                                                  Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                                                  locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 20000, 1, StartRound.this);
                                                  if(location != null) {
                                                      Toast.makeText(getBaseContext(), "End Point Plotted Lat/Long" + location.getLatitude() + "/" + location.getLongitude(), Toast.LENGTH_SHORT).show();
                                                      editor.putString("endPoint" + profileNo + "" + holeNumVar + "" + val, "" + Math.round(location.getLongitude()));
                                                      editor.commit();
                                                      dialog = new ProgressDialog(StartRound.this, R.style.MyAlertDialogStyle);
                                                      dialog.setProgressStyle(android.R.style.Widget_Holo_ProgressBar_Large);
                                                      // dialog.setMessage("Loading...");

                                                      WindowManager.LayoutParams params = dialog.getWindow().getAttributes();

                                                      dialog.setCancelable(false);
                                                      // dialog.setInverseBackgroundForced(false);
                                                      dialog.show();
                                                      Intent PreviousScreen = new Intent(StartRound.this, StartRound.class);
                                                      dialog.cancel();
                                                      startActivity(PreviousScreen);
                                                      finish();
                                                  }
                                                  else {
                                                      Toast.makeText(getBaseContext(), "Enable Loction Services", Toast.LENGTH_SHORT).show();

                                                  }
                                              }
                                          }
                                      }
                                      return false;
                                  }

                              });
                            if(Accuracy[i] != null)
                                Accuracy[i].setOnTouchListener(new View.OnTouchListener() {

                                  @Override
                                  public boolean onTouch(View view, MotionEvent event) {
                                      if (event.getAction() == MotionEvent.ACTION_DOWN) {

                                          Log.i("value","index  accuracy ==================="+val);
                                          SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                                          SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                          editor.putInt("clubselectindex"+profileNo+""+holeNumVar ,val);
                                          editor.putInt("SRprofileNo", profileNo);
                                          editor.putInt("SRholeNumVar", holeNumVar);
                                          if(sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+val,"Dr").equals("P")) {
                                              editor.putInt("accuracyFor", 1);
                                          }
                                          else
                                          {
                                              editor.putInt("accuracyFor", 0);

                                          }
                                          editor.commit();
                                          Intent nextScreen = new Intent(StartRound.this, ShotAccuracy.class);

                                          startActivity(nextScreen);
                                          finish();
                                      }
                                      return false;
                                  }

                              });
                          if(ButtonshotDelete[i] != null)
                              ButtonshotDelete[i].setOnTouchListener(new View.OnTouchListener() {

                                  @Override
                                  public boolean onTouch(View view, MotionEvent event) {
                                      if (event.getAction() == MotionEvent.ACTION_DOWN) {

                                          Log.i("value","index delete==================="+val);
                                          if(val == index-1) {
                                              Linearscrollshot.removeViewAt(3+val);

                                              index--;
                                              SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                                              SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                              editor.remove("clubselectindex"+profileNo+""+holeNumVar+""+index);
                                              editor.remove("accuracyselect"+profileNo+""+holeNumVar+""+index);
                                             // SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                                             // SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                              editor.putInt("clubselectTotalindex"+profileNo+""+holeNumVar ,index);
                                              editor.commit();
                                             /* if(index == 0)
                                              {
                                                  RelativeLayoutforblueteesON.setVisibility(View.VISIBLE);
                                                  RelativeLayoutforblueteesOFF.setVisibility(View.GONE);
                                              }*/
                                          }
                                      }
                                      return false;
                                  }

                              });

                      }

                      index++;
                      SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                      SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                      editor.putInt("clubselectTotalindex"+profileNo+""+holeNumVar ,index);
                      editor.commit();
                  }
                }
                return false;
            }

        });

        ButtonPutt.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // gps.setBackgroundColor(Color.parseColor("#51677e"));
                    // m_buttonGps.setTextColor(Color.WHITE);

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    // if(enableAddshot)
                    {
                      //  RelativeLayoutforblueteesON.setVisibility(View.GONE);
                      //  RelativeLayoutforblueteesOFF.setVisibility(View.VISIBLE);
                        Buttonclubsel = new Button[index+1];
                        FrameButtonclubsel = new FrameLayout[index+1];
                        Textshotcount = new TextView[index+1];
                        Frameendpoint = new FrameLayout[index+1];
                        Accuracy      = new Button[index+1];
                        ButtonshotDelete      = new LinearLayout[index+1];
                        endpoint2 = new TextView[index+1];
                        endpointImage = new ImageView[index+1];
                        endpoint1 = new TextView[index+1];


                        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                        View convertView = inflater.inflate(R.layout.addshot, null);
                        final LinearLayout childln  = (LinearLayout) convertView.findViewById(R.id.shotCustomlinear);
                        Textshotcount[index] = new TextView(StartRound.this);
                        Textshotcount[index] = (TextView)childln.findViewById(R.id.textshotcount);
                        Textshotcount[index].setText(""+(index+1));
                        Buttonclubsel[index] = new Button(StartRound.this);
                        FrameButtonclubsel[index] = new FrameLayout(StartRound.this);
                        Buttonclubsel[index] = (Button)childln.findViewById(R.id.buttonclubsel);
                        FrameButtonclubsel[index] = (FrameLayout) childln.findViewById(R.id.f1);
                        Buttonclubsel[index].setText("P");
                        Buttonclubsel[index].setBackgroundColor(Color.parseColor("#accb3f"));
                        FrameButtonclubsel[index].setBackgroundColor(Color.parseColor("#accb3f"));
                        Buttonclubsel[index].setTextColor(Color.parseColor("#ffffff"));
                        Buttonclubsel[index].setBackgroundResource(R.drawable.greensquare);

                        SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                        editor.putString("clubselectindex"+profileNo+""+holeNumVar+"" + index, "P");
                        editor.commit();

                        Frameendpoint[index] = new FrameLayout(StartRound.this);
                        Frameendpoint[index] = (FrameLayout) childln.findViewById(R.id.frameendpoint);
                        endpointImage[index] = new ImageView(StartRound.this);
                        endpointImage[index]  = (ImageView) childln.findViewById(R.id.endpointim);

                        endpoint1[index] = new TextView(StartRound.this);
                        endpoint1[index]  = (TextView) childln.findViewById(R.id.endpointtext1);
                        endpointImage[index].setVisibility(View.GONE);
                        endpoint2[index] = new TextView(StartRound.this);
                        endpoint2[index]  = (TextView) childln.findViewById(R.id.endpointtext2);
                        endpoint2[index].setText("DISTANCE");
                        DisplayMetrics dm = getResources().getDisplayMetrics();
                        float dpInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, dm);
                        endpoint2[index].setPadding(0,0,(int)dpInPx,0);


                        Accuracy[index] = new Button(StartRound.this);
                        Accuracy[index]      = (Button) childln.findViewById(R.id.buttonaccuracyshot);
                        if(getScreenResolution(StartRound.this) == 480 )
                        {
                            Accuracy[index].setTextSize(dpToPx(9));
                        }
                        ButtonshotDelete[index] = new LinearLayout(StartRound.this);
                        ButtonshotDelete[index]   = (LinearLayout) childln.findViewById(R.id.lin1newshot);
                        Linearscrollshot.addView(childln, 3+index);

                        for(int i=0;i<index+1;i++){
                            final int val = i;
                          /*  if(Buttonclubsel[i] != null)
                                Buttonclubsel[i].setOnTouchListener(new View.OnTouchListener() {

                                    @Override
                                    public boolean onTouch(View view, MotionEvent event) {
                                        if (event.getAction() == MotionEvent.ACTION_DOWN) {

                                            Log.i("value","index clubsel==================="+val);
                                            SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                            editor.putInt("clubselectindex" ,val);
                                            editor.commit();
                                            Intent nextScreen = new Intent(StartRound.this, ShotClubSelection.class);

                                            startActivity(nextScreen);
                                        }
                                        return false;
                                    }

                                });*/

                            if(Frameendpoint[i] != null)
                                Frameendpoint[i].setOnTouchListener(new View.OnTouchListener() {

                                    @Override
                                    public boolean onTouch(View view, MotionEvent event) {
                                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                            if(val > 0)
                                            {
                                                boolean check = false;
                                                int shotNumber =0 ;
                                                SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
Log.i("value","value of putt ========"+(sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+val,"Club")));
                                                if(sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+val,"Club").equals("P"))
                                                {
                                                    // SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                                                    //  SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                                    editor.putInt("clubselectindex"+profileNo+""+holeNumVar, val);
                                                    editor.putInt("SRprofileNo", profileNo);
                                                    editor.putInt("SRholeNumVar", holeNumVar);
                                                    editor.commit();
                                                    Intent nextScreen = new Intent(StartRound.this, PuttDistance.class);
                                                    startActivity(nextScreen);
                                                    finish();
                                                    check = false;
                                                }
                                                else {
                                                    for(int i = val;i >0;i--) {
                                                        if (sharedpreferencesCLUB.getString("endPoint"+profileNo+""+holeNumVar+"" + (i - 1), "End point").equals("End point")) {
                                                            check = true;
                                                            shotNumber =i;
                                                        }
                                                    }
                                                    if(!check)
                                                    {
                                                        // SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                                                        //SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();

                                                        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                                                        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                                                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 20000, 1, StartRound.this);
                                                        if(location != null) {
                                                            Toast.makeText(getBaseContext(), "End Point Plotted Lat/Long" + location.getLatitude() + "/" + location.getLongitude(), Toast.LENGTH_SHORT).show();
                                                            editor.putString("endPoint" + profileNo + "" + holeNumVar + "" + val, "" + Math.round(location.getLongitude()));
                                                            editor.commit();
                                                            dialog = new ProgressDialog(StartRound.this, R.style.MyAlertDialogStyle);
                                                            dialog.setProgressStyle(android.R.style.Widget_Holo_ProgressBar_Large);
                                                            // dialog.setMessage("Loading...");

                                                            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();

                                                            dialog.setCancelable(false);
                                                            // dialog.setInverseBackgroundForced(false);
                                                            dialog.show();
                                                            Intent PreviousScreen = new Intent(StartRound.this, StartRound.class);
                                                            dialog.cancel();
                                                            startActivity(PreviousScreen);
                                                            finish();
                                                        }
                                                        else {
                                                            Toast.makeText(getBaseContext(), "Enable Loction Services", Toast.LENGTH_SHORT).show();

                                                        }
                                                    }
                                                    else
                                                    {
                                                        Toast.makeText(StartRound.this,"You must plot shot "+(shotNumber)+" before plotting shot "+(val+1),Toast.LENGTH_LONG).show();
                                                    }
                                                }

                                            }
                                            else {
                                                SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                                if(sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+val,"Club").equals("P"))
                                                {
                                                    // SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                                                    //  SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                                    editor.putInt("clubselectindex"+profileNo+""+holeNumVar, val);
                                                    editor.putInt("SRprofileNo", profileNo);
                                                    editor.putInt("SRholeNumVar", holeNumVar);
                                                    editor.commit();
                                                    Intent nextScreen = new Intent(StartRound.this, PuttDistance.class);
                                                    startActivity(nextScreen);
                                                    finish();
                                                }
                                                else
                                                {
                                                    locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                                                    Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                                                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 20000, 1, StartRound.this);
                                                    if(location != null) {
                                                        Toast.makeText(getBaseContext(), "End Point Plotted Lat/Long" + location.getLatitude() + "/" + location.getLongitude(), Toast.LENGTH_SHORT).show();
                                                        editor.putString("endPoint" + profileNo + "" + holeNumVar + "" + val, "" + Math.round(location.getLongitude()));
                                                        editor.commit();
                                                        dialog = new ProgressDialog(StartRound.this, R.style.MyAlertDialogStyle);
                                                        dialog.setProgressStyle(android.R.style.Widget_Holo_ProgressBar_Large);
                                                        // dialog.setMessage("Loading...");

                                                        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();

                                                        dialog.setCancelable(false);
                                                        // dialog.setInverseBackgroundForced(false);
                                                        dialog.show();
                                                        Intent PreviousScreen = new Intent(StartRound.this, StartRound.class);
                                                        dialog.cancel();
                                                        startActivity(PreviousScreen);
                                                        finish();
                                                    }
                                                    else {
                                                        Toast.makeText(getBaseContext(), "Enable Loction Services", Toast.LENGTH_SHORT).show();

                                                    }
                                                }
                                            }
                                        }
                                        return false;
                                    }

                                });
                            if(Accuracy[i] != null)
                                Accuracy[i].setOnTouchListener(new View.OnTouchListener() {

                                    @Override
                                    public boolean onTouch(View view, MotionEvent event) {
                                        if (event.getAction() == MotionEvent.ACTION_DOWN) {

                                            Log.i("value","index  accuracy ==================="+val);
                                            SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                            editor.putInt("clubselectindex"+profileNo+""+holeNumVar ,val);
                                            editor.putString("clubselectindex"+profileNo+""+holeNumVar+"" + val, "P");
                                            editor.putInt("SRprofileNo", profileNo);
                                            editor.putInt("SRholeNumVar", holeNumVar);
                                            if(sharedpreferencesCLUB.getString("clubselectindex"+profileNo+""+holeNumVar+""+val,"Dr").equals("P")) {
                                                editor.putInt("accuracyFor", 1);
                                            }
                                            else
                                            {
                                                editor.putInt("accuracyFor", 0);

                                            }
                                            editor.commit();
                                            Intent nextScreen = new Intent(StartRound.this, ShotAccuracy.class);

                                            startActivity(nextScreen);
                                            finish();
                                        }
                                        return false;
                                    }

                                });
                            if(ButtonshotDelete[i] != null)
                                ButtonshotDelete[i].setOnTouchListener(new View.OnTouchListener() {

                                    @Override
                                    public boolean onTouch(View view, MotionEvent event) {
                                        if (event.getAction() == MotionEvent.ACTION_DOWN) {

                                            Log.i("value","index delete==================="+val);
                                            if(val == index-1) {
                                                Linearscrollshot.removeViewAt(3+val);

                                                index--;
                                                SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                                editor.remove("clubselectindex"+profileNo+""+holeNumVar+""+index);
                                                editor.remove("accuracyselect"+profileNo+""+holeNumVar+""+index);
                                                // SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                                                // SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                                                editor.putInt("clubselectTotalindex"+profileNo+""+holeNumVar ,index);
                                                editor.commit();
                                               /* if(index == 0)
                                                {
                                                    RelativeLayoutforblueteesON.setVisibility(View.VISIBLE);
                                                    RelativeLayoutforblueteesOFF.setVisibility(View.GONE);
                                                }*/
                                            }
                                        }
                                        return false;
                                    }

                                });

                        }

                        index++;
                       // SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                       // SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                        editor.putInt("clubselectTotalindex"+profileNo+""+holeNumVar ,index);
                        editor.commit();
                    }
                }
                return false;
            }

        });

        RelativeLayoutforblueteesOFF.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                   // gps.setBackgroundColor(Color.parseColor("#51677e"));
                   // m_buttonGps.setTextColor(Color.WHITE);

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                   RelativeLayoutforblueteesON.setVisibility(View.VISIBLE);
                    RelativeLayoutforblueteesOFF.setVisibility(View.GONE);
                   // enableAddshot = false;
                }
                return false;
            }

        });

        leftArrow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (holeNumVar > 0)
                    holeNumVar--;

                holeNumber.setText("" + (holeNumVar + 1));
                Log.i("value", "holeNumVar_Left=============================" + holeNumVar);
                Parvaluestart.setText("" + parEachHole[holeNumVar]);
                previousDist.setText("" + HoleNumberArrayDistance1[holeNumVar]);
                currentDist.setText("" + HoleNumberArrayDistance[holeNumVar]);
                nextDist.setText("" + HoleNumberArrayDistance2[holeNumVar]);
                if (parEachHole[holeNumVar] == 3) {
                    picker.setValues(valuesPar3);
                } else if (parEachHole[holeNumVar] == 4) {
                    picker.setValues(valuesPar4);
                } else if (parEachHole[holeNumVar] == 5) {
                    picker.setValues(valuesPar5);
                }
                SharedPreferences sharedpresetprofile = getSharedPreferences("profileset", MODE_PRIVATE);
                SharedPreferences.Editor editorprofile = sharedpresetprofile.edit();
                // editorprofile.putInt("profileno",0);
                editorprofile.putInt("holeno", holeNumVar);
                editorprofile.commit();
                SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                if(sharedpreferencesCLUB.getBoolean("scoreshot",false)) {
                    Log.i("value","here in leftttttttttt");
                    dialog=new ProgressDialog(StartRound.this,R.style.MyAlertDialogStyle);
                    dialog.setProgressStyle(android.R.style.Widget_Holo_ProgressBar_Large);
                    // dialog.setMessage("Loading...");

                    WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                    dialog.setCancelable(false);
                    // dialog.setInverseBackgroundForced(false);
                    dialog.show();
                    for(int i = 0;i<18;i++)   saveData(i);
                    Intent refresh = new Intent(StartRound.this,StartRound.class);
                    dialog.cancel();
                    startActivity(refresh);
                    finish();

                }
                else {
                    if (profileNo == 0) {

                        setData(profileData1, holeNumVar);
                    } else if (profileNo == 1) {
                        setData(profileData2, holeNumVar);
                    } else if (profileNo == 2) {
                        setData(profileData3, holeNumVar);
                    } else if (profileNo == 3) {
                        setData(profileData4, holeNumVar);
                    }

                    linlessstat.setVisibility(LinearLayout.VISIBLE);
                    linmorestats.setVisibility(LinearLayout.GONE);
                    HideAll();

                    if (profileNo == 0) setprofile1 = false;
                    else if (profileNo == 1) setprofile2 = false;
                    else if (profileNo == 2) setprofile3 = false;
                    else if (profileNo == 3) setprofile4 = false;
                }
            }


        });

        rightArrow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (holeNumVar < 17)
                    holeNumVar++;

                holeNumber.setText("" + (holeNumVar + 1));
                Log.i("value", "holeNumVar_right=============================" + holeNumVar);
                Parvaluestart.setText(""+parEachHole[holeNumVar]);
                previousDist.setText("" +HoleNumberArrayDistance1[holeNumVar]);
                currentDist.setText("" + HoleNumberArrayDistance[holeNumVar]);
                nextDist.setText("" + HoleNumberArrayDistance2[holeNumVar]);
                if(parEachHole[holeNumVar] == 3)
                {
                    picker.setValues(valuesPar3);
                }
                else
                if(parEachHole[holeNumVar] == 4)
                {
                    picker.setValues(valuesPar4);
                }
                else
                if(parEachHole[holeNumVar] == 5)
                {
                    picker.setValues(valuesPar5);
                }

                SharedPreferences sharedpresetprofile= getSharedPreferences("profileset", MODE_PRIVATE);
                SharedPreferences.Editor editorprofile = sharedpresetprofile.edit();
                //  editorprofile.putInt("profileno",0);
                editorprofile.putInt("holeno",holeNumVar);
                editorprofile.commit();
                SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                if(sharedpreferencesCLUB.getBoolean("scoreshot",false)) {
                    Log.i("value","here in leftttttttttt");
                    dialog=new ProgressDialog(StartRound.this,R.style.MyAlertDialogStyle);
                    dialog.setProgressStyle(android.R.style.Widget_Holo_ProgressBar_Large);
                    // dialog.setMessage("Loading...");

                    WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                    dialog.setCancelable(false);
                    // dialog.setInverseBackgroundForced(false);
                    dialog.show();
                    for(int i = 0;i<18;i++)   saveData(i);
                    Intent refresh = new Intent(StartRound.this,StartRound.class);
                    dialog.cancel();
                    startActivity(refresh);
                    finish();
                }
                else
                {
                if(profileNo == 0)
                {

                    setData(profileData1,holeNumVar);
                }
                else
                if(profileNo == 1)
                {
                    setData(profileData2,holeNumVar);
                }
                else
                if(profileNo == 2)
                {
                   setData(profileData3,holeNumVar);
                }
                else
                if(profileNo == 3)
                {
                    setData(profileData4,holeNumVar);
                }

                linlessstat.setVisibility(LinearLayout.VISIBLE);
                linmorestats.setVisibility(LinearLayout.GONE);

                HideAll();

                if (profileNo == 0) setprofile1 = false;
                else if (profileNo == 1) setprofile2 = false;
                else if (profileNo == 2) setprofile3 = false;
                else if (profileNo == 3) setprofile4 = false;

                }

            }
        });


        linlessstat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                linlessstat.setVisibility(LinearLayout.GONE);
                linmorestats.setVisibility(LinearLayout.VISIBLE);
                ShowAll();
                if (profileNo == 0) setprofile1 = true;
                else if (profileNo == 1) setprofile2 = true;
                else if (profileNo == 2) setprofile3 = true;
                else if (profileNo == 3) setprofile4 = true;


            }
        });
        LessState.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                linlessstat.setVisibility(LinearLayout.GONE);
                linmorestats.setVisibility(LinearLayout.VISIBLE);
                ShowAll();
                if (profileNo == 0) setprofile1 = true;
                else if (profileNo == 1) setprofile2 = true;
                else if (profileNo == 2) setprofile3 = true;
                else if (profileNo == 3) setprofile4 = true;


            }
        });

        linmorestats.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                linlessstat.setVisibility(LinearLayout.VISIBLE);
                linmorestats.setVisibility(LinearLayout.GONE);

                HideAll();

                if (profileNo == 0) setprofile1 = false;
                else if (profileNo == 1) setprofile2 = false;
                else if (profileNo == 2) setprofile3 = false;
                else if (profileNo == 3) setprofile4 = false;


            }
        });
        MoreStats.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                linlessstat.setVisibility(LinearLayout.VISIBLE);
                linmorestats.setVisibility(LinearLayout.GONE);

                HideAll();

                if (profileNo == 0) setprofile1 = false;
                else if (profileNo == 1) setprofile2 = false;
                else if (profileNo == 2) setprofile3 = false;
                else if (profileNo == 3) setprofile4 = false;


            }
        });


        for(int i = 0;i<4;i++) {
            int resID = getResources().getIdentifier("id_profile"+(i+1)+""+(i+1), "id", getPackageName());
            profile[i] = (ImageView) findViewById(resID);

        }

    

        profileText1 = (TextView) findViewById(R.id.profileText1);
        profileText2 = (TextView) findViewById(R.id.profileText2);
        profileText3 = (TextView) findViewById(R.id.profileText3);
        profileText4 = (TextView) findViewById(R.id.profileText4);





        Bitmap bm1 = null;
        Bitmap bm2= null;
        Bitmap bm3= null;
        if( GolferName[0]!= "")
        {
            if(GolferName[0].contains(" ")){
                GolferName[0]= GolferName[0].substring(0, GolferName[0].indexOf(" "));
            }
            profileText2.setText(GolferName[0]);
            bm3 = BitmapFactory.decodeResource(getResources(),
                    R.drawable.profile_default);

        }


        if(GolferName[1]!= "")
        {
            if(GolferName[1].contains(" ")){
                GolferName[1]= GolferName[1].substring(0, GolferName[1].indexOf(" "));
            }
            profileText3.setText(GolferName[1]);
            bm2 = BitmapFactory.decodeResource(getResources(),
                    R.drawable.profile_default);
        }

        if(GolferName[2]!= "")
        {
            if(GolferName[2].contains(" ")) {
                GolferName[2] = GolferName[2].substring(0, GolferName[2].indexOf(" "));
            }
            profileText4.setText(GolferName[2]);
            bm1 = BitmapFactory.decodeResource(getResources(),
                    R.drawable.profile_default);
        }

        if(bm1 == null) {
            profileText4.setText("");
            bm1 = BitmapFactory.decodeResource(getResources(),
            R.drawable.profile_default);
            ImageView mImage3 = (ImageView) findViewById(R.id.id_profile4);
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
                mImage3.setImageDrawable(drawable);

            }
            else {
                RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bm1);
                mImage3.setImageDrawable(drawable);
            }
           // RoundedBitmapDrawable drawable3 = createRoundedBitmapDrawableWithBorder(bm1);

            // Set the ImageView image as drawable object
           // mImage3.setImageDrawable(drawable3);
            mImage3.setVisibility(View.GONE);
            Circlesmall[3].setVisibility(View.GONE);


        }
        else
        {
            //   mImage2.setImageBitmap(conv_bm2);
            ImageView mImage3 = (ImageView) findViewById(R.id.id_profile4);
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
                mImage3.setImageDrawable(drawable);

            }
            else {
                RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bm1);
                mImage3.setImageDrawable(drawable);
            }
            //RoundedBitmapDrawable drawable3 = createRoundedBitmapDrawableWithBorder(bm1);

            // Set the ImageView image as drawable object
           // mImage3.setImageDrawable(drawable3);

            // mImage3.setImageBitmap(conv_bm3);

        }

        if(bm2 == null)
        {
            profileText3.setText("");
            bm2 = BitmapFactory.decodeResource(getResources(),
            R.drawable.profile_default);
            ImageView mImage2 = (ImageView) findViewById(R.id.id_profile3);
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
                mImage2.setImageDrawable(drawable);

            }
            else {
                RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bm2);
                mImage2.setImageDrawable(drawable);
            }
           // RoundedBitmapDrawable drawable2 = createRoundedBitmapDrawableWithBorder(bm2);

            // Set the ImageView image as drawable object
            //mImage2.setImageDrawable(drawable2);
            mImage2.setVisibility(View.GONE);
            Circlesmall[2].setVisibility(View.GONE);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) FL_profpic3.getLayoutParams();
            params.setMargins(0, 0, 0, 0);
            FL_profpic3.setLayoutParams(params);
        }
        else
        {
            ImageView mImage2 = (ImageView) findViewById(R.id.id_profile3);
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
                mImage2.setImageDrawable(drawable);

            }
            else {
                RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bm2);
                mImage2.setImageDrawable(drawable);
            }
           // RoundedBitmapDrawable drawable2 = createRoundedBitmapDrawableWithBorder(bm2);

            // Set the ImageView image as drawable object
          //  mImage2.setImageDrawable(drawable2);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) FL_profpic3.getLayoutParams();
            params.setMargins(0, 0, 1, 0);
            FL_profpic3.setLayoutParams(params);
        }
        if(bm3 == null)
        {
            profileText2.setText("");
            bm3 = BitmapFactory.decodeResource(getResources(),
            R.drawable.profile_default);
            ImageView mImage1 = (ImageView) findViewById(R.id.id_profile2);
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
                mImage1.setImageDrawable(drawable);

            }
            else {
                RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bm3);
                mImage1.setImageDrawable(drawable);
            }
           // RoundedBitmapDrawable drawable1 = createRoundedBitmapDrawableWithBorder(bm3);

            // Set the ImageView image as drawable object
           // mImage1.setImageDrawable(drawable1);
            mImage1.setVisibility(View.GONE);
            Circlesmall[1].setVisibility(View.GONE);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) FL_profpic2.getLayoutParams();
            params.setMargins(0, 0, 0, 0);
            FL_profpic2.setLayoutParams(params);

        }
        else
        {
            ImageView mImage1 = (ImageView) findViewById(R.id.id_profile2);
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
                mImage1.setImageDrawable(drawable);

            }
            else {
                RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bm3);
                mImage1.setImageDrawable(drawable);
            }
          //  RoundedBitmapDrawable drawable1 = createRoundedBitmapDrawableWithBorder(bm3);

            // Set the ImageView image as drawable object
          //  mImage1.setImageDrawable(drawable1);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) FL_profpic2.getLayoutParams();
            params.setMargins(0, 0, 1, 0);
            FL_profpic2.setLayoutParams(params);

        }


        Bitmap bm = BitmapFactory.decodeResource(getResources(),
                R.drawable.profile_default);
         mImage_user1 = (ImageView) findViewById(R.id.id_profile1);
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
            mImage_user1.setImageDrawable(drawable);

        }
        else {
            RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bm);
            mImage_user1.setImageDrawable(drawable);
        }

        picker = (HorizontalPicker) findViewById(R.id.picker);
        picker.setHorizontalPickerButton(m_buttonScore);
        picker.setmyPicker(0);
        picker.setOnItemClickedListener(this);
        picker.setOnItemSelectedListener(this);

        picker.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.i("ss", " pickerNumber touch *****************" +  pickerNumber);
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    pickerNumber = 1;
                    Log.i("ss", " pickerNumber touch *****************" +  pickerNumber);
                }
                return false;
            }

        });

        picker1 = (HorizontalPicker) findViewById(R.id.picker1);
        picker1.setHorizontalPickerButton(m_buttonPutt);
        picker1.setmyPicker(1);
        picker1.setOnItemClickedListener(this);
        picker1.setOnItemSelectedListener(this);

        picker1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.i("ss", " pickerNumber touch *****************" +  pickerNumber);
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    pickerNumber = 2;
                    Log.i("ss", " pickerNumber touch *****************" +  pickerNumber);
                }
                return false;
            }

        });
        picker2 = (HorizontalPicker) findViewById(R.id.picker2);
        picker2.setHorizontalPickerButton(m_buttonOff);
        picker2.setmyPicker(2);
        picker2.setOnItemClickedListener(this);
        picker2.setOnItemSelectedListener(this);
        picker2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.i("ss", " pickerNumber touch *****************" +  pickerNumber);
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    pickerNumber = 3;
                    Log.i("ss", " pickerNumber touch *****************" +  pickerNumber);
                }
                return false;
            }

        });
        picker3 = (HorizontalPicker) findViewById(R.id.picker3);
        picker3.setHorizontalPickerButton(m_buttonTee);
        picker3.setmyPicker(3);
        picker3.setOnItemClickedListener(this);
        picker3.setOnItemSelectedListener(this);
        picker3.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.i("ss", " pickerNumber touch *****************" +  pickerNumber);
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    pickerNumber = 4;
                    Log.i("ss", " pickerNumber touch *****************" +  pickerNumber);
                }
                return false;
            }

        });
        picker4 = (HorizontalPicker) findViewById(R.id.picker4);
        picker4.setHorizontalPickerButton(m_buttonSand);
        picker4.setmyPicker(4);
        picker4.setOnItemClickedListener(this);
        picker4.setOnItemSelectedListener(this);
        picker4.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.i("ss", " pickerNumber touch *****************" +  pickerNumber);
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    pickerNumber = 5;
                    Log.i("ss", " pickerNumber touch *****************" +  pickerNumber);
                }
                return false;
            }

        });
        picker5 = (HorizontalPicker) findViewById(R.id.picker5);
        picker5.setHorizontalPickerButton(m_buttonPenal);
        picker5.setmyPicker(5);
        picker5.setOnItemClickedListener(this);
        picker5.setOnItemSelectedListener(this);
        picker5.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.i("ss", " pickerNumber touch *****************" +  pickerNumber);
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    pickerNumber = 6;
                    Log.i("ss", " pickerNumber touch *****************" +  pickerNumber);
                }
                return false;
            }

        });


        loadData();

        picker.setSelectedItem(profileData1[0][0]);
        picker1.setSelectedItem(profileData1[0][1]);
        picker2.setSelectedItem(profileData1[0][2]);
        picker3.setSelectedItem(profileData1[0][3]);
        picker4.setSelectedItem(profileData1[0][4]);
        picker5.setSelectedItem(profileData1[0][5]);

        if(parEachHole[holeNumVar] == 3)
        {
            picker.setValues(valuesPar3);
        }
        else
        if(parEachHole[holeNumVar] == 4)
        {
            picker.setValues(valuesPar4);
        }
        else
        if(parEachHole[holeNumVar] == 5)
        {
            picker.setValues(valuesPar5);
        }
        Log.i("ss", " profileNo=== **top***************" +profileNo);
        Log.i("ss", " holeNumVar=== **top***************" +holeNumVar);

        if(profileNo == 0)
        {

            setUIprofile(profile[0],profile[1],profile[2],profile[3],
                    profileText1,profileText2,profileText3,profileText4,profileNo);

            setData(profileData1,holeNumVar);
        }
        else
        if(profileNo == 1)
        {

            setUIprofile(profile[1],profile[0],profile[2],profile[3],
                    profileText2,profileText1,profileText3,profileText4,profileNo);

            setData(profileData2,holeNumVar);
        }
        else
        if(profileNo == 2)
        {

            setUIprofile(profile[2],profile[1],profile[0],profile[3],
                    profileText3,profileText2,profileText1,profileText4,profileNo);

            setData(profileData3,holeNumVar);
        }
        else
        if(profileNo == 3)
        {

            setUIprofile(profile[3],profile[1],profile[2],profile[0],
                    profileText4,profileText2,profileText3,profileText1,profileNo);

            setData(profileData4,holeNumVar);
        }

        profile[0].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.i("value","click on profile");
              /*  if(!profilePress)
                {
                    profilePress = true;
                }
                else
                {
                    Bundle bundle = new Bundle();
                    bundle.putString("MemberNumber","first");
                    bundle.putString("Membername","You");
                    bundle.putString("fromscreen","STARTROUND");
                    Intent PreviousScreen = new Intent(StartRound.this,Profile.class);
                    PreviousScreen.putExtras(bundle);
                    startActivity(PreviousScreen);
                    finish();
                }*/
                profileNo = 0;

                setUIprofile(profile[0],profile[1],profile[2],profile[3],
                        profileText1,profileText2,profileText3,profileText4,profileNo);
                SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                if(sharedpreferencesCLUB.getBoolean("scoreshot",false)) {
                    if (profileNo == 0 && (m_teeselected.equals("") || m_teeselected.equals("NO TEES SELECTED"))) {
                        scrollView_score.setVisibility(View.GONE);
                        scrollView_Shots.setVisibility(View.GONE);
                        shots.setBackgroundColor(Color.parseColor("#51677e"));
                        score.setBackgroundColor(Color.parseColor("#10171f"));
                        // m_buttonGps.setTextColor(Color.WHITE);

                        SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                        editor.putBoolean("scoreshot", true);
                        editor.commit();
                        SR_relativeLayoutteesselect.setVisibility(View.VISIBLE);
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
                            SR_userImage.setImageDrawable(drawable);

                        }
                        else {
                            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),
                                    R.drawable.profile_default);
                            RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bitmap);
                            SR_userImage.setImageDrawable(drawable);
                            //SR_userImage.setBackgroundResource(R.drawable.profile_default);
                        }
                        SR_Youid.setText("You");
                        Toast.makeText(StartRound.this,"You must select a tee to begin Shot Tracking",Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        shots.setBackgroundColor(Color.parseColor("#51677e"));
                        score.setBackgroundColor(Color.parseColor("#10171f"));
                        scrollView_score.setVisibility(View.GONE);
                        scrollView_Shots.setVisibility(View.VISIBLE);
                        SR_relativeLayoutteesselect.setVisibility(View.GONE);
                    }
                }
                else {
                    if (setprofile1) {
                        linlessstat.setVisibility(LinearLayout.GONE);
                        linmorestats.setVisibility(LinearLayout.VISIBLE);
                        ShowAll();

                    } else {
                        linlessstat.setVisibility(LinearLayout.VISIBLE);
                        linmorestats.setVisibility(LinearLayout.GONE);
                        HideAll();


                    }

                    setData(profileData1, holeNumVar);

                }



            }
        });

        profile[1].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (Circlesmall[1].getVisibility() != View.GONE) {
                    profileNo = 1;
                 /*   if(!profilePress1)
                    {
                        profilePress1 = true;
                    }
                    else
                    {
                        int index = GolferNametemp[0].indexOf(" ");
                        int lastindex = GolferNametemp[0].length();
                        String name = GolferNametemp[0].substring(0, index);
                        String sname = GolferNametemp[0].substring(index+1, lastindex);

                        SharedPreferences sharedpreferences= getSharedPreferences("Mail" , MODE_PRIVATE);

                        SharedPreferences Edituser= getSharedPreferences("useredit", MODE_PRIVATE);
                        SharedPreferences.Editor editor = Edituser.edit();
                        editor.putString("editname",""+name);
                        editor.putString("editsurname",""+sname);
                        editor.putString("editemail",""+(sharedpreferences.getString("email"+(usrId[0]), "")));
                        editor.putInt("edit_id",(usrId[0]));
                        editor.putInt("edit_userindex",0);
                        editor.putString("fromscreen","startround");
                        editor.commit();
                        Intent nextScreen = new Intent(StartRound.this, EditFriendInfo.class);
                        startActivity(nextScreen);
                    }*/

                            setUIprofile(profile[1],profile[0],profile[2],profile[3],
                            profileText2,profileText1,profileText3,profileText4,profileNo);

                    SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                    if(sharedpreferencesCLUB.getBoolean("scoreshot",false)) {
                        if (profileNo == 1 && (m_teeselected1.equals("") || m_teeselected1.equals("NO TEES SELECTED"))) {
                            scrollView_score.setVisibility(View.GONE);
                            scrollView_Shots.setVisibility(View.GONE);
                            shots.setBackgroundColor(Color.parseColor("#51677e"));
                            score.setBackgroundColor(Color.parseColor("#10171f"));
                            // m_buttonGps.setTextColor(Color.WHITE);

                            SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                            editor.putBoolean("scoreshot", true);
                            editor.commit();
                            SR_relativeLayoutteesselect.setVisibility(View.VISIBLE);
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
                                SR_userImage.setImageDrawable(drawable);

                            }
                            else {
                                Bitmap bitmap= BitmapFactory.decodeResource(getResources(),
                                        R.drawable.profile_default);
                                RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bitmap);
                                SR_userImage.setImageDrawable(drawable);
                                // SR_userImage.setBackgroundResource(R.drawable.profile_default);
                            }
                            //SR_userImage.setBackgroundResource(R.drawable.profile_pick3);
                            SR_Youid.setText(""+GolferName[0]);
                            Toast.makeText(StartRound.this,"You must select a tee to begin Shot Tracking",Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            shots.setBackgroundColor(Color.parseColor("#51677e"));
                            score.setBackgroundColor(Color.parseColor("#10171f"));
                            scrollView_score.setVisibility(View.GONE);
                            scrollView_Shots.setVisibility(View.VISIBLE);
                            SR_relativeLayoutteesselect.setVisibility(View.GONE);

                        }
                    }
                    else {
                        if (setprofile2) {
                            linlessstat.setVisibility(LinearLayout.GONE);
                            linmorestats.setVisibility(LinearLayout.VISIBLE);
                            ShowAll();
                        } else {
                            linlessstat.setVisibility(LinearLayout.VISIBLE);
                            linmorestats.setVisibility(LinearLayout.GONE);
                            HideAll();


                        }
                    }

                    setData(profileData2, holeNumVar);


                }
            }
        });

        profile[2].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (Circlesmall[2].getVisibility() != View.GONE) {
                    profileNo = 2;
                  /*  if(!profilePress2)
                    {
                        profilePress2 = true;
                    }
                    else
                    {
                        int index = GolferNametemp[1].indexOf(" ");
                        int lastindex = GolferNametemp[1].length();
                        String name = GolferNametemp[1].substring(0, index);
                        String sname = GolferNametemp[1].substring(index+1, lastindex);

                        SharedPreferences sharedpreferences= getSharedPreferences("Mail" , MODE_PRIVATE);

                        SharedPreferences Edituser= getSharedPreferences("useredit", MODE_PRIVATE);
                        SharedPreferences.Editor editor = Edituser.edit();
                        editor.putString("editname",""+name);
                        editor.putString("editsurname",""+sname);
                        editor.putString("editemail",""+(sharedpreferences.getString("email"+(usrId[1]), "")));
                        editor.putInt("edit_id",(usrId[1]));
                        editor.putInt("edit_userindex",1);
                        editor.putString("fromscreen","startround");
                        editor.commit();
                        Intent nextScreen = new Intent(StartRound.this, EditFriendInfo.class);
                        startActivity(nextScreen);
                    }*/

                    setUIprofile(profile[2],profile[1],profile[0],profile[3],
                            profileText3,profileText2,profileText1,profileText4,profileNo);

                    SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                    if(sharedpreferencesCLUB.getBoolean("scoreshot",false)) {
                        if (profileNo == 2 && (m_teeselected2.equals("") || m_teeselected2.equals("NO TEES SELECTED"))) {
                            scrollView_score.setVisibility(View.GONE);
                            scrollView_Shots.setVisibility(View.GONE);
                            shots.setBackgroundColor(Color.parseColor("#51677e"));
                            score.setBackgroundColor(Color.parseColor("#10171f"));
                            // m_buttonGps.setTextColor(Color.WHITE);

                            SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                            editor.putBoolean("scoreshot", true);
                            editor.commit();
                            SR_relativeLayoutteesselect.setVisibility(View.VISIBLE);
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
                                SR_userImage.setImageDrawable(drawable);

                            }
                            else {
                                Bitmap bitmap= BitmapFactory.decodeResource(getResources(),
                                        R.drawable.profile_default);
                                RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bitmap);
                                SR_userImage.setImageDrawable(drawable);
                                // SR_userImage.setBackgroundResource(R.drawable.profile_default);
                            }
                          //  SR_userImage.setBackgroundResource(R.drawable.profile_pick2);
                            SR_Youid.setText(""+GolferName[1]);
                            Toast.makeText(StartRound.this,"You must select a tee to begin Shot Tracking",Toast.LENGTH_SHORT).show();


                        }
                        else
                        {
                            shots.setBackgroundColor(Color.parseColor("#51677e"));
                            score.setBackgroundColor(Color.parseColor("#10171f"));
                            scrollView_score.setVisibility(View.GONE);
                            scrollView_Shots.setVisibility(View.VISIBLE);
                            SR_relativeLayoutteesselect.setVisibility(View.GONE);

                        }
                    }
                    else {
                        if (setprofile3) {
                            linlessstat.setVisibility(LinearLayout.GONE);
                            linmorestats.setVisibility(LinearLayout.VISIBLE);
                            ShowAll();
                        } else {
                            linlessstat.setVisibility(LinearLayout.VISIBLE);
                            linmorestats.setVisibility(LinearLayout.GONE);
                            HideAll();


                        }
                    }
                    setData(profileData3, holeNumVar);


                }
            }
        });

        profile[3].setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (Circlesmall[3].getVisibility() != View.GONE) {
                    profileNo = 3;
                  /*  if(!profilePress3)
                    {
                        profilePress3 = true;
                    }
                    else
                    {
                        int index = GolferNametemp[2].indexOf(" ");
                        int lastindex = GolferNametemp[2].length();
                        String name = GolferNametemp[2].substring(0, index);
                        String sname = GolferNametemp[2].substring(index+1, lastindex);

                        SharedPreferences sharedpreferences= getSharedPreferences("Mail" , MODE_PRIVATE);

                        SharedPreferences Edituser= getSharedPreferences("useredit", MODE_PRIVATE);
                        SharedPreferences.Editor editor = Edituser.edit();
                        editor.putString("editname",""+name);
                        editor.putString("editsurname",""+sname);
                        editor.putString("editemail",""+(sharedpreferences.getString("email"+(usrId[2]), "")));
                        editor.putInt("edit_id",(usrId[2]));
                        editor.putInt("edit_userindex",2);
                        editor.putString("fromscreen","startround");
                        editor.commit();
                        Intent nextScreen = new Intent(StartRound.this, EditFriendInfo.class);
                        startActivity(nextScreen);
                    }*/

                    setUIprofile(profile[3],profile[1],profile[2],profile[0],
                            profileText4,profileText2,profileText3,profileText1,profileNo);
                    SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);
                    if(sharedpreferencesCLUB.getBoolean("scoreshot",false)) {
                        if (profileNo == 3 && (m_teeselected3.equals("") || m_teeselected3.equals("NO TEES SELECTED"))) {
                            scrollView_score.setVisibility(View.GONE);
                            scrollView_Shots.setVisibility(View.GONE);
                            shots.setBackgroundColor(Color.parseColor("#51677e"));
                            score.setBackgroundColor(Color.parseColor("#10171f"));
                            // m_buttonGps.setTextColor(Color.WHITE);

                            SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                            editor.putBoolean("scoreshot", true);
                            editor.commit();
                            SR_relativeLayoutteesselect.setVisibility(View.VISIBLE);
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
                                SR_userImage.setImageDrawable(drawable);

                            }
                            else {
                                Bitmap bitmap= BitmapFactory.decodeResource(getResources(),
                                        R.drawable.profile_default);
                                RoundedBitmapDrawable drawable = createRoundedBitmapDrawableWithBorder(bitmap);
                                SR_userImage.setImageDrawable(drawable);
                                // SR_userImage.setBackgroundResource(R.drawable.profile_default);
                            }
                         //   SR_userImage.setBackgroundResource(R.drawable.profile_pick1);
                            SR_Youid.setText(""+GolferName[2]);
                            Toast.makeText(StartRound.this,"You must select a tee to begin Shot Tracking",Toast.LENGTH_SHORT).show();


                        }
                        else
                        {
                            shots.setBackgroundColor(Color.parseColor("#51677e"));
                            score.setBackgroundColor(Color.parseColor("#10171f"));
                            scrollView_score.setVisibility(View.GONE);
                            scrollView_Shots.setVisibility(View.VISIBLE);
                            SR_relativeLayoutteesselect.setVisibility(View.GONE);

                        }
                    }
                    else {
                        if (setprofile4) {
                            linlessstat.setVisibility(LinearLayout.GONE);
                            linmorestats.setVisibility(LinearLayout.VISIBLE);
                            ShowAll();
                        } else {
                            linlessstat.setVisibility(LinearLayout.VISIBLE);
                            linmorestats.setVisibility(LinearLayout.GONE);
                            HideAll();
                        }
                        setData(profileData4, holeNumVar);
                    }

                }
            }
        });

    }

    @Override
    public void onItemSelected(int index) {

      //  itemselected = index;
        SharedPreferences userStatus= getSharedPreferences("USERSTATUS", MODE_PRIVATE);
        SharedPreferences.Editor editorStatus = userStatus.edit();


        editorStatus.commit();
        if(profileNo == 0)
        {
            Log.i("ss", " pickerNumber *****************" +  pickerNumber);
            if(pickerNumber == 1) {
                profileData1[holeNumVar][0] = index;
                int totalUser = 0;
                int totalUser1 = 0;
                int totalUser1PAR = 0;

                for(int i = 0;i <18;i++)
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
                    if(ScoreValue[ profileData1[i][0] ] == 0) {

                        setInnerCircle((totalUser+totalUser1-totalUser1PAR),Circlesmall[0]);
                    }
                    else {

                        if(i <9)
                            totalUser = totalUser + ScoreValue[profileData1[i][0]];
                        else
                            totalUser1 = totalUser1 + ScoreValue[profileData1[i][0]];
                        totalUser1PAR = totalUser1PAR+parEachHole[i];
                        setInnerCircle((totalUser+totalUser1-totalUser1PAR),Circlesmall[0]);



                    }

                }
                editorStatus.putInt("USER1",(totalUser+totalUser1-totalUser1PAR));
                editorStatus.commit();
                setMessage(profileData1[holeNumVar][0],parEachHole[holeNumVar]);
            }
            else
            if(pickerNumber == 2)
                profileData1[holeNumVar][1] = index;
            else
            if(pickerNumber == 3)
                profileData1[holeNumVar][2] = index;
            else
            if(pickerNumber == 4)
                profileData1[holeNumVar][3] = index;
            else
            if(pickerNumber == 5)
                profileData1[holeNumVar][4] = index;
            else
            if(pickerNumber == 6)
                profileData1[holeNumVar][5] = index;
        }
        else
        if(profileNo == 1)
        {
            if(pickerNumber == 1) {
                profileData2[holeNumVar][0] = index;
                int total2user = 0;
                int total2user1 = 0;
                int total2userPAR = 0;
                for(int i = 0;i <18;i++)
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
                    if(ScoreValue[profileData2[i][0]] == 0) {

                        setInnerCircle((total2user+total2user1-total2userPAR),Circlesmall[1]);

                    }
                    else {

                        if(i < 9)
                            total2user = total2user + ScoreValue[profileData2[i][0]];
                        else
                            total2user1 = total2user1 + ScoreValue[profileData2[i][0]];
                        total2userPAR = total2userPAR + parEachHole[i];
                        setInnerCircle((total2user+total2user1-total2userPAR),Circlesmall[1]);
                       // setMessage(ScoreValue[profileData2[i][0]],parEachHole[i]);
                    }

                }
                editorStatus.putInt("USER2",(total2user+total2user1-total2userPAR));
                editorStatus.commit();

                setMessage(profileData2[holeNumVar][0],parEachHole[holeNumVar]);
            }
            else
            if(pickerNumber == 2)
                profileData2[holeNumVar][1] = index;
            else
            if(pickerNumber == 3)
                profileData2[holeNumVar][2] = index;
            else
            if(pickerNumber == 4)
                profileData2[holeNumVar][3] = index;
            else
            if(pickerNumber == 5)
                profileData2[holeNumVar][4] = index;
            else
            if(pickerNumber == 6)
                profileData2[holeNumVar][5] = index;
        }
        else
        if(profileNo == 2)
        {
            if(pickerNumber == 1) {
                profileData3[holeNumVar][0] = index;
                int total3User= 0;
                int total3User1= 0;
                int total3UserPAR= 0;
                for(int i = 0;i <18;i++)
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
                    if(ScoreValue[profileData3[i][0] ] == 0) {


                        setInnerCircle((total3User+total3User1-total3UserPAR),Circlesmall[2]);

                    }
                    else {

                        if(i < 9)
                            total3User = total3User + ScoreValue[profileData3[i][0]];
                        else
                            total3User1 = total3User1 + ScoreValue[profileData3[i][0]];

                        total3UserPAR= total3UserPAR+parEachHole[i];
                        setInnerCircle((total3User+total3User1-total3UserPAR),Circlesmall[2]);
                        //setMessage(ScoreValue[profileData3[i][0]],parEachHole[i]);
                    }

                }
                editorStatus.putInt("USER3",(total3User+total3User1-total3UserPAR));
                editorStatus.commit();

                setMessage(profileData3[holeNumVar][0],parEachHole[holeNumVar]);
            }
            else
            if(pickerNumber == 2)
                profileData3[holeNumVar][1] = index;
            else
            if(pickerNumber == 3)
                profileData3[holeNumVar][2] = index;
            else
            if(pickerNumber == 4)
                profileData3[holeNumVar][3] = index;
            else
            if(pickerNumber == 5)
                profileData3[holeNumVar][4] = index;
            else
            if(pickerNumber == 6)
                profileData3[holeNumVar][5] = index;
        }
        else
        if(profileNo == 3)
        {
            if(pickerNumber == 1) {
                profileData4[holeNumVar][0] = index;
                int total4User= 0;
                int total4User1= 0;
                int total4UserPAR= 0;
                for(int i = 0;i <18;i++)
                {
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
                    if(ScoreValue[ profileData4[i][0]] == 0) {


                        setInnerCircle((total4User+total4User1-total4UserPAR),Circlesmall[3]);
                    }
                    else {

                        if(i < 9)
                            total4User = total4User + ScoreValue[profileData4[i][0]];
                        else
                            total4User1 = total4User1 + ScoreValue[profileData4[i][0]];

                        total4UserPAR = total4UserPAR+parEachHole[i];
                        setInnerCircle((total4User+total4User1-total4UserPAR),Circlesmall[3]);

                    }

                }
                editorStatus.putInt("USER4",(total4User+total4User1-total4UserPAR));
                editorStatus.commit();
                setMessage(profileData4[holeNumVar][0],parEachHole[holeNumVar]);
            }
            else
            if(pickerNumber == 2)
                profileData4[holeNumVar][1] = index;
            else
            if(pickerNumber == 3)
                profileData4[holeNumVar][2] = index;
            else
            if(pickerNumber == 4)
                profileData4[holeNumVar][3] = index;
            else
            if(pickerNumber == 5)
                profileData4[holeNumVar][4] = index;
            else
            if(pickerNumber == 6)
                profileData4[holeNumVar][5] = index;
        }

        saveData(holeNumVar);



    }

    public void saveData(int hole)
    {
        SharedPreferences.Editor editor = scoringDB.edit();
        for(int i=0 ; i<profileData1[0].length;i++)
        {
            editor.putInt("profileData1"+""+hole+""+i,profileData1[hole][i]) ;

        }
        for(int i=0 ; i<profileData2[0].length;i++)
        {
            editor.putInt("profileData2"+""+hole+""+i,profileData2[hole][i]) ;

        }
        for(int i=0 ; i<profileData3[0].length;i++)
        {
            editor.putInt("profileData3"+""+hole+""+i,profileData3[hole][i]) ;

        }
        for(int i=0 ; i<profileData4[0].length;i++)
        {
            editor.putInt("profileData4"+""+hole+""+i,profileData4[hole][i]) ;

        }
        editor.commit();
    }

    public void loadData()
    {
        for(int j=0 ; j<profileData1.length;j++) {
            for (int i = 0; i < profileData1[0].length; i++) {
                profileData1[j][i] = scoringDB.getInt("profileData1"+""+j+""+i, profileData1[j][i]);

            }
        }
        for(int j=0 ; j<profileData2.length;j++) {
            for (int i = 0; i < profileData2[0].length; i++) {
                profileData2[j][i] = scoringDB.getInt("profileData2"+""+j+""+i, profileData2[j][i]);

            }
        }
        for(int j=0 ; j<profileData3.length;j++) {
            for (int i = 0; i < profileData3[0].length; i++) {
                profileData3[j][i] = scoringDB.getInt("profileData3"+""+j+""+i, profileData3[j][i]);

            }
        }

        for(int j=0 ; j<profileData4.length;j++) {
            for (int i = 0; i < profileData4[0].length; i++) {
                profileData4[j][i] = scoringDB.getInt("profileData4"+""+j+""+i, profileData4[j][i]);

            }
        }

    }
    @Override
    public void onItemClicked(int index) {
       // Toast.makeText(this, "Item clicked", Toast.LENGTH_SHORT).show();
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

    private void setInnerCircle(int userstatus,TextView circle)
    {
        if(userstatus == 0)
        {
            circle.setBackgroundResource(R.drawable.roundblue);
            circle.setText("E");
        }
        else
        if(userstatus < 0)
        {
            circle.setBackgroundResource(R.drawable.roundorange);
            circle.setText(""+userstatus);
        }
        else if(userstatus > 0)
        {
            circle.setBackgroundResource(R.drawable.roundgreen);
            circle.setText("+"+userstatus);
        }

    }

    public void setData(int ArrayData[][],int holenumber)
    {
        picker.setSelectedItem(ArrayData[holenumber][0]);
        picker1.setSelectedItem(ArrayData[holenumber][1]);
        picker2.setSelectedItem(ArrayData[holenumber][2]);
        picker3.setSelectedItem(ArrayData[holenumber][3]);
        picker4.setSelectedItem(ArrayData[holenumber][4]);
        picker5.setSelectedItem(ArrayData[holenumber][5]);
    }
    private Bitmap getCircleBitmap(Bitmap bitmap, int pixels) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);
        final int color = 0xff000000;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 255);
        paint.setColor(color);

        canvas.drawCircle(100, 100, 70, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        bitmap.recycle();
        return output;
    }

    private void ShowAll()
    {
       for(int i =0;i<5;i++)
        FrameLayout[i].setVisibility(LinearLayout.VISIBLE);

        scoretext.setVisibility(LinearLayout.VISIBLE);
        puutstext.setVisibility(LinearLayout.VISIBLE);
        offtheteetext.setVisibility(LinearLayout.VISIBLE);
        teeaccuracytext.setVisibility(LinearLayout.VISIBLE);
        sandtext.setVisibility(LinearLayout.VISIBLE);
        penaltiestext.setVisibility(LinearLayout.VISIBLE);
    }

    private void HideAll()
    {
        for(int i =0;i<5;i++)
            FrameLayout[i].setVisibility(LinearLayout.GONE);

        // scoretext.setVisibility(LinearLayout.GONE);
        puutstext.setVisibility(LinearLayout.GONE);
        offtheteetext.setVisibility(LinearLayout.GONE);
        teeaccuracytext.setVisibility(LinearLayout.GONE);
        sandtext.setVisibility(LinearLayout.GONE);
        penaltiestext.setVisibility(LinearLayout.GONE);
    }

    public void setUIprofile(ImageView profileActive,ImageView profileDeactive1,ImageView profileDeactive2,ImageView profileDeactive3,
                             TextView proTextActive,TextView proTextDeactive1,TextView proTextDeactive2,TextView proTextDeactive3,int profileno)
    {
        holeNumber.setText("" + (holeNumVar + 1));
        Parvaluestart.setText(""+parEachHole[holeNumVar]);
        previousDist.setText("" +HoleNumberArrayDistance1[holeNumVar]);
        currentDist.setText("" + HoleNumberArrayDistance[holeNumVar]);
        nextDist.setText("" + HoleNumberArrayDistance2[holeNumVar]);
        SharedPreferences sharedpresetprofile= getSharedPreferences("profileset", MODE_PRIVATE);
        SharedPreferences.Editor editorprofile = sharedpresetprofile.edit();
        editorprofile.putInt("profileno",profileno);
        editorprofile.commit();
        profileActive.setBackgroundResource(R.drawable.placeholder2);
        profileDeactive1.setBackgroundResource(R.drawable.placeholder3);
        profileDeactive2.setBackgroundResource(R.drawable.placeholder3);
        profileDeactive3.setBackgroundResource(R.drawable.placeholder3);
        proTextActive.setTextColor(Color.parseColor("#000000"));
        proTextDeactive1.setTextColor(Color.parseColor("#9ab4d5"));
        proTextDeactive2.setTextColor(Color.parseColor("#9ab4d5"));
        proTextDeactive3.setTextColor(Color.parseColor("#9ab4d5"));
    }

    public void setMessage(int index ,int par)
    {
        if(par == 3)
        {
            ScoreValue=ScoreValue3;
        }
        else
        if(par == 4)
        {
            ScoreValue=ScoreValue4;
        }
        else
        if(par == 5)
        {
            ScoreValue=ScoreValue5;
        }
        int score = ScoreValue[index];
        if(score == 0) return;
        Toast toast = null;
        if((score-par) == -3)
        {
            CustomToast(toast ,"DOUBLE EAGLE");
        }
        else
        if((score-par) == -2)
        {
            CustomToast(toast ,"EAGLE");
        }
        else
        if((score-par) == -1)
        {
            CustomToast(toast ,"BIRDIE");
        }
        else
        if((score-par)== 0)
        {
            CustomToast(toast ,"PAR");
        }
        else
        if((score-par) == 1)
        {
            CustomToast(toast ,"BOGIE");
        }
        else
        if((score-par) == 2)
        {
            CustomToast(toast ,"DOUBLE BOGIE");
        }
        else
        if((score-par) == 3)
        {
            CustomToast(toast ,"TRIPLE BOGIE");
        }
        else
        if((score-par) == 4)
        {
            CustomToast(toast ,"QUADRUPLE BOGIE");
        }


    }

    public void CustomToast(Toast toast ,String str)
    {
        toast = Toast.makeText(this,str, Toast.LENGTH_LONG);
        View toastView = toast.getView(); //This'll return the default View of the Toast.

        /* And now you can get the TextView of the default View of the Toast. */
        TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
        toastMessage.setTextSize(20);
        toastMessage.setTextColor(Color.WHITE);
        toastView.setBackgroundColor(Color.parseColor("#accc3f"));
        toastMessage.setGravity(Gravity.CENTER);
        toastMessage.setPadding(20,10,20,10);
        toastMessage.setBackgroundColor(Color.parseColor("#accc3f"));
        toast.show();
    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.setVisibility(View.VISIBLE);
            drawerLayout.closeDrawer(Gravity.RIGHT);

        }
        return;
       
    }
    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onStop() {
        super.onStop();


    }
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

    private void InitSlidingMenu()
    {
        //Init component
        listViewSliding = (ListView) findViewById(R.id.lv_sliding_menuSR);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layoutSR);
        //drawerLayout.setVisibility(View.GONE);
        listSliding = new ArrayList<>();
        //Add item for sliding list
        listSliding.add(new ItemSlideMenu(0, "This Round",0));
        listSliding.add(new ItemSlideMenu(0, "      Scorecard",1));
        listSliding.add(new ItemSlideMenu(0, "      Edit Settings",1));
        listSliding.add(new ItemSlideMenu(0, "      Save",1));
        listSliding.add(new ItemSlideMenu(0, "      Cancel ",1));
        adapter = new SlidingMenuAdapter(this, listSliding);
        listViewSliding.setAdapter(adapter);

        //Display icon to open/ close sliding list
        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set title
        setTitle(listSliding.get(0).getTitle());
        //item selected
        listViewSliding.setItemChecked(0, true);
        //Close menu
        drawerLayout.closeDrawer(listViewSliding);
        drawerLayout.setVisibility(View.INVISIBLE);
       // invalidateOptionsMenu();

        //Display fragment 1 when start
        // replaceFragment(0);
        //Hanlde on item click
        drawerLayout.setOnTouchListener(new View.OnTouchListener() {

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            if(event.getAction() == MotionEvent.ACTION_UP) {
                //m_hotdNteet.setBackgroundResource(R.drawable.mainbutton);
            } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
                Log.i("Value","Here in drawlayout click");
            }
            return false;
        }

    });

        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Value","Here in list click  "+position);
                listViewSliding.setItemChecked(position, true);
                if(position == 1)
                {
                    Bundle bundle = new Bundle();
                    bundle.putString("SCREENNAME","FROMSTARTROUND");
                    Intent nextScreen = new Intent(StartRound.this, ScoreCard.class);

                    nextScreen.putExtras(bundle);
                    startActivity(nextScreen);
                    finish();
                }
                else
                if(position == 2)
                {
                    Intent PreviousScreen = new Intent(StartRound.this,RoundSetting.class);
                    startActivity(PreviousScreen);
                    finish();

                }
                else
                if(position == 4)
                {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                    ViewDialog alert = new ViewDialog();
                    alert.showDialog(StartRound.this, "Do you want to cancel round?");

                }
                else
                    return;
               // drawerLayout.closeDrawer(listViewSliding);

            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_opened, R.string.drawer_closed){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                float deg = menu.getRotation() + 180F;
                menu.animate().rotation(deg).setInterpolator(new AccelerateDecelerateInterpolator());
                menu.setBackgroundResource(R.drawable.backarrow);
                drawerLayout.setVisibility(View.VISIBLE);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                float deg = menu.getRotation() + 180F;
                menu.animate().rotation(deg).setInterpolator(new AccelerateDecelerateInterpolator());
                menu.setBackgroundResource(R.drawable.menubutton);

                drawerLayout.setVisibility(View.GONE);

                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // drawerLayout.setDrawerListener(actionBarDrawerToggle);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    //#DIALOG BOX FOR REMOVE GOLFER
    private class ViewDialog {

        public void showDialog(Activity activity, String msg) {
           // final int valuein = value;
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


                        dialog.dismiss();
                        Intent PreviousScreen = new Intent(StartRound.this,MainPage.class);
                        startActivity(PreviousScreen);
                    finish();

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

    @Override
    public void onLocationChanged(Location location) {

       // Toast.makeText(getBaseContext(), "Longitude:" + location.getLongitude(), Toast.LENGTH_SHORT).show();

        // Setting Current Longitude
     //   tvLongitude.setText("Longitude:" + location.getLongitude());

        // Setting Current Latitude
      //  tvLatitude.setText("Latitude:" + location.getLatitude() );
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }
    private static int getScreenResolution(Context context)
    {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        return width;
    }
    public int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
