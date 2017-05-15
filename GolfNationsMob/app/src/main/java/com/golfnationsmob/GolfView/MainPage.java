package com.golfnationsmob.GolfView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.golfnationsmob.GolfModel.ItemSlideMenu;
import com.golfnationsmob.GolfModel.SlidingMenuAdapter;
import com.golfnationsmob.GolfView.NewRound.RoundSetting;
import com.golfnationsmob.GolfView.PostScore.MainPostScore;
import com.golfnationsmob.GolfView.PracticeGoals.PracticeGoalsMain;
import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends Activity {

    Button m_newRound,m_postScore,m_statNanalytics,m_practiceNgoals,m_tournaNevents;
    boolean pressed;
    Button menu;
    private List<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter adapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout  m_drawerLayout;
    boolean setSubMenu[] = {false,false,false,false,false};
    String newMember[]= {"","",""};
    int usrId[] = {-1,-1,-1};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_main_page);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.header);
        //check if custom title is supported BEFORE setting the content view!
//****To add DFB Ads in app
      //  PublisherAdView mPublisherAdView = (PublisherAdView) findViewById(R.id.publisherAdView);
     //   PublisherAdRequest adRequest = new PublisherAdRequest.Builder().build();
      //  mPublisherAdView.loadAd(adRequest);
        //******************
        menu = (Button)findViewById(R.id.header_left_btn);
        m_newRound = (Button)findViewById(R.id.newround);
        m_postScore = (Button)findViewById(R.id.postscore);
        m_statNanalytics = (Button)findViewById(R.id.stanalytic);
        m_practiceNgoals = (Button)findViewById(R.id.Practice);
        m_tournaNevents = (Button)findViewById(R.id.tounaeve);
      //  m_drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        InitSlidingMenu();
        Typeface font = AppFont.setFont(this,AppFont.ROBOTO_MEDIUM);

        m_newRound. setTypeface(font);
        m_postScore. setTypeface(font);
        m_statNanalytics. setTypeface(font);
        m_practiceNgoals. setTypeface(font);
        m_tournaNevents. setTypeface(font);

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

        m_newRound.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    m_newRound.setBackgroundResource(R.drawable.mainbutton);
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    SharedPreferences sharedpreferencesAddname= getSharedPreferences("AddFriendOnRs", MODE_PRIVATE);
                    for(int i =0 ; i<3;i++)
                    {

                        newMember[i] = sharedpreferencesAddname.getString("memberonRS"+i, "");
                        usrId[i] = sharedpreferencesAddname.getInt("usrId"+i, -1);


                    }
                    if(newMember[0]!= "null"&& newMember[0]!= "")
                    {
                        SharedPreferences sharedpreferencesName= getSharedPreferences("Name", MODE_PRIVATE);
                        SharedPreferences.Editor editor11 = sharedpreferencesName.edit();
                        //  Map<String, ?> allEntries = sharedpreferencesName.getAll();
                        // Log.i("ss", "  allEntries above ******"+allEntries.size());
                        //Log.i("ss", "  t1 ******"+t1);
                        editor11.putString("name"+usrId[0] , newMember[0]);
                        editor11.commit();
                    }
                    if(newMember[1]!= "null"&& newMember[1]!= "")
                    {
                        SharedPreferences sharedpreferencesName= getSharedPreferences("Name", MODE_PRIVATE);
                        SharedPreferences.Editor editor11 = sharedpreferencesName.edit();
                        //  Map<String, ?> allEntries = sharedpreferencesName.getAll();
                        // Log.i("ss", "  allEntries above ******"+allEntries.size());
                        //Log.i("ss", "  t1 ******"+t1);
                        editor11.putString("name"+usrId[1] , newMember[1]);
                        editor11.commit();
                    }
                    if(newMember[2]!= "null"&& newMember[2]!= "")
                    {
                        SharedPreferences sharedpreferencesName= getSharedPreferences("Name", MODE_PRIVATE);
                        SharedPreferences.Editor editor11 = sharedpreferencesName.edit();
                        //  Map<String, ?> allEntries = sharedpreferencesName.getAll();
                        // Log.i("ss", "  allEntries above ******"+allEntries.size());
                        //Log.i("ss", "  t1 ******"+t1);
                        editor11.putString("name"+usrId[2] , newMember[2]);
                        editor11.commit();
                    }

                    SharedPreferences.Editor editor = sharedpreferencesAddname.edit();
                    for(int i =0 ; i<3;i++)
                    {
                        editor.putString("memberonRS"+i, "");
                        editor.putInt("usrId"+i, -1);
                        editor.putInt("memberCount" , 0);
                        editor.commit();
                    }
                    m_newRound.setBackgroundResource(R.drawable.buttonselected);
                    Intent nextScreen = new Intent(MainPage.this,RoundSetting.class);
                    startActivity(nextScreen);
                }
                return false;
            }

        });

        m_postScore.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    m_postScore.setBackgroundResource(R.drawable.mainbutton);
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    m_postScore.setBackgroundResource(R.drawable.buttonselected);
                    SharedPreferences sharedpresetprofile= getSharedPreferences("MAINPOSTSCORE", MODE_PRIVATE);
                    sharedpresetprofile.edit().clear().commit();
                    Intent nextScreen = new Intent(MainPage.this,MainPostScore.class);
                    startActivity(nextScreen);

                }
                return false;
            }

        });


        m_statNanalytics.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    m_statNanalytics.setBackgroundResource(R.drawable.mainbutton);
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    m_statNanalytics.setBackgroundResource(R.drawable.buttonselected);
                }
                return false;
            }

        });


        m_tournaNevents.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    m_tournaNevents.setBackgroundResource(R.drawable.mainbutton);
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    m_tournaNevents.setBackgroundResource(R.drawable.buttonselected);
                }
                return false;
            }

        });

        m_practiceNgoals.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    m_practiceNgoals.setBackgroundResource(R.drawable.mainbutton);
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    m_practiceNgoals.setBackgroundResource(R.drawable.buttonselected);
                    Intent nextScreen = new Intent(MainPage.this,PracticeGoalsMain.class);
                    startActivity(nextScreen);
                }
                return false;
            }

        });





    }

    @Override
    public void onBackPressed() {
         return;
        // Otherwise defer to system default behavior.
        //super.onBackPressed();
    }

    private void InitSlidingMenu()
    {
        //Init component
        listViewSliding = (ListView) findViewById(R.id.lv_sliding_menu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //drawerLayout.setVisibility(View.GONE);
        listSliding = new ArrayList<>();
        //Add item for sliding list
        listSliding.add(new ItemSlideMenu(0, "Start a Round",0));
        listSliding.add(new ItemSlideMenu(0, "Post a Score",0));
        listSliding.add(new ItemSlideMenu(0, "Stats & Matchups",0));
        listSliding.add(new ItemSlideMenu(0, "Practice & Goals",0));
        listSliding.add(new ItemSlideMenu(0, "Profile",0));
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
       // drawerLayout.setVisibility(View.GONE);

        //Display fragment 1 when start
       // replaceFragment(0);
        //Hanlde on item click

        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                listViewSliding.setItemChecked(position, true);
                Log.i("value","position === "+position);
                Log.i("value","setSubMenu[0] === "+setSubMenu[0]);
                Log.i("value","setSubMenu[2] === "+setSubMenu[2]);
                Log.i("value","setSubMenu[3] === "+setSubMenu[3]);
                if(position == 0 && !setSubMenu[0]&&!setSubMenu[2]&&!setSubMenu[3])
                {
                        listSliding.clear();
                        listSliding.add(new ItemSlideMenu(0, "Start a Round",0));
                        listSliding.add(new ItemSlideMenu(0, "       ScoreCard",1));
                        listSliding.add(new ItemSlideMenu(0, "       Edit Setting",1));
                        listSliding.add(new ItemSlideMenu(0, "       Cancel Round",1));
                        listSliding.add(new ItemSlideMenu(0, "Post a Score",0));
                        listSliding.add(new ItemSlideMenu(0, "Stats & Matchups",0));
                        listSliding.add(new ItemSlideMenu(0, "Practice & Goals",0));
                        listSliding.add(new ItemSlideMenu(0, "Profile",0));
                        listViewSliding.invalidate();
                        setSubMenu[0] = true;


                }
                else
                if(position == 1 && !setSubMenu[0]&&!setSubMenu[2]&&!setSubMenu[3])
                {



                }
                else
                if(position == 2 && !setSubMenu[0]&&!setSubMenu[2]&&!setSubMenu[3])
                {
                    listSliding.clear();
                    listSliding.add(new ItemSlideMenu(0, "Start a Round",0));
                    listSliding.add(new ItemSlideMenu(0, "Post a Score",0));
                    listSliding.add(new ItemSlideMenu(0, "Stats & Matchups",0));
                    listSliding.add(new ItemSlideMenu(0, "       Stats",1));
                    listSliding.add(new ItemSlideMenu(0, "       Matchups",1));
                    listSliding.add(new ItemSlideMenu(0, "       Ranking",1));
                    listSliding.add(new ItemSlideMenu(0, "       Points",1));
                    listSliding.add(new ItemSlideMenu(0, "Practice & Goals",0));
                    listSliding.add(new ItemSlideMenu(0, "Profile",0));
                    listViewSliding.invalidate();
                    setSubMenu[2] = true;


                }
                else
                if(position == 3 && !setSubMenu[0]&&!setSubMenu[2]&&!setSubMenu[3])
                {
                    listSliding.clear();
                    listSliding.add(new ItemSlideMenu(0, "Start a Round",0));
                    listSliding.add(new ItemSlideMenu(0, "Post a Score",0));
                    listSliding.add(new ItemSlideMenu(0, "Stats & Matchups",0));
                    listSliding.add(new ItemSlideMenu(0, "Practice & Goals",0));
                    listSliding.add(new ItemSlideMenu(0, "       Practice",1));
                    listSliding.add(new ItemSlideMenu(0, "       Goals",1));
                    listSliding.add(new ItemSlideMenu(0, "       Instruction",1));
                    listSliding.add(new ItemSlideMenu(0, "Profile",0));
                    listViewSliding.invalidate();
                    setSubMenu[3] = true;


                }
                else
                if(setSubMenu[0])
                {
                    if(position == 0)
                    {
                        listSliding.clear();
                        listSliding.add(new ItemSlideMenu(0, "Start a Round",0));
                        listSliding.add(new ItemSlideMenu(0, "Post a Score",0));
                        listSliding.add(new ItemSlideMenu(0, "Stats & Matchups",0));
                        listSliding.add(new ItemSlideMenu(0, "Practice & Goals",0));
                        listSliding.add(new ItemSlideMenu(0, "Profile",0));
                        listViewSliding.invalidate();
                        setSubMenu[0] = false;

                    }
                    else
                    if(position == 1)
                    {

                    }
                    else
                    if(position == 2)
                    {

                    }
                    else
                    if(position == 3)
                    {

                    }
                    else
                    if(position == 4)
                    {


                    }
                    else
                    if(position == 5)
                    {
                        listSliding.clear();
                        listSliding.add(new ItemSlideMenu(0, "Start a Round",0));
                        listSliding.add(new ItemSlideMenu(0, "Post a Score",0));
                        listSliding.add(new ItemSlideMenu(0, "Stats & Matchups",0));
                        listSliding.add(new ItemSlideMenu(0, "       Stats",1));
                        listSliding.add(new ItemSlideMenu(0, "       Matchups",1));
                        listSliding.add(new ItemSlideMenu(0, "       Ranking",1));
                        listSliding.add(new ItemSlideMenu(0, "       Points",1));
                        listSliding.add(new ItemSlideMenu(0, "Practice & Goals",0));
                        listSliding.add(new ItemSlideMenu(0, "Profile",0));
                        listViewSliding.invalidate();
                        setSubMenu[2] = true;
                        setSubMenu[0] = false;
                    }
                    else
                    if(position == 6)
                    {
                        listSliding.clear();
                        listSliding.add(new ItemSlideMenu(0, "Start a Round",0));
                        listSliding.add(new ItemSlideMenu(0, "Post a Score",0));
                        listSliding.add(new ItemSlideMenu(0, "Stats & Matchups",0));
                        listSliding.add(new ItemSlideMenu(0, "Practice & Goals",0));
                        listSliding.add(new ItemSlideMenu(0, "       Practice",1));
                        listSliding.add(new ItemSlideMenu(0, "       Goals",1));
                        listSliding.add(new ItemSlideMenu(0, "       Instruction",1));
                        listSliding.add(new ItemSlideMenu(0, "Profile",0));
                        listViewSliding.invalidate();
                        setSubMenu[3] = true;
                        setSubMenu[0] = false;

                    }
                    else
                    if(position == 7)
                    {

                    }
                }
                else
                if(setSubMenu[2])
                {
                    if(position == 0)
                    {
                        listSliding.clear();
                        listSliding.add(new ItemSlideMenu(0, "Start a Round",0));
                        listSliding.add(new ItemSlideMenu(0, "       ScoreCard",1));
                        listSliding.add(new ItemSlideMenu(0, "       Edit Setting",1));
                        listSliding.add(new ItemSlideMenu(0, "       Cancel Round",1));
                        listSliding.add(new ItemSlideMenu(0, "Post a Score",0));
                        listSliding.add(new ItemSlideMenu(0, "Stats & Matchups",0));
                        listSliding.add(new ItemSlideMenu(0, "Practice & Goals",0));
                        listSliding.add(new ItemSlideMenu(0, "Profile",0));
                        listViewSliding.invalidate();
                        setSubMenu[0] = true;
                        setSubMenu[2] = false;
                    }
                    else
                    if(position == 1)
                    {

                    }
                    else
                    if(position == 2)
                    {
                        listSliding.clear();
                        listSliding.add(new ItemSlideMenu(0, "Start a Round",0));
                        listSliding.add(new ItemSlideMenu(0, "Post a Score",0));
                        listSliding.add(new ItemSlideMenu(0, "Stats & Matchups",0));
                        listSliding.add(new ItemSlideMenu(0, "Practice & Goals",0));
                        listSliding.add(new ItemSlideMenu(0, "Profile",0));
                        listViewSliding.invalidate();
                        setSubMenu[2] = false;
                    }
                    else
                    if(position == 3)
                    {

                    }
                    else
                    if(position == 4)
                    {

                    }
                    else
                    if(position == 5)
                    {

                    }
                    else
                    if(position == 6)
                    {

                    }
                    else
                    if(position == 7)
                    {
                        listSliding.clear();
                        listSliding.add(new ItemSlideMenu(0, "Start a Round",0));
                        listSliding.add(new ItemSlideMenu(0, "Post a Score",0));
                        listSliding.add(new ItemSlideMenu(0, "Stats & Matchups",0));
                        listSliding.add(new ItemSlideMenu(0, "Practice & Goals",0));
                        listSliding.add(new ItemSlideMenu(0, "       Practice",1));
                        listSliding.add(new ItemSlideMenu(0, "       Goals",1));
                        listSliding.add(new ItemSlideMenu(0, "       Instruction",1));
                        listSliding.add(new ItemSlideMenu(0, "Profile",0));
                        listViewSliding.invalidate();
                        setSubMenu[3] = true;
                        setSubMenu[2] = false;
                    }
                    else
                    if(position == 8)
                    {

                    }
                }
                else
                if(setSubMenu[3])
                {
                    if(position == 0)
                    {
                        listSliding.clear();
                        listSliding.add(new ItemSlideMenu(0, "Start a Round",0));
                        listSliding.add(new ItemSlideMenu(0, "       ScoreCard",1));
                        listSliding.add(new ItemSlideMenu(0, "       Edit Setting",1));
                        listSliding.add(new ItemSlideMenu(0, "       Cancel Round",1));
                        listSliding.add(new ItemSlideMenu(0, "Post a Score",0));
                        listSliding.add(new ItemSlideMenu(0, "Stats & Matchups",0));
                        listSliding.add(new ItemSlideMenu(0, "Practice & Goals",0));
                        listSliding.add(new ItemSlideMenu(0, "Profile",0));
                        listViewSliding.invalidate();
                        setSubMenu[0] = true;
                        setSubMenu[3] = false;
                    }
                    else
                    if(position == 1)
                    {

                    }
                    else
                    if(position == 2)
                    {
                        listSliding.clear();
                        listSliding.add(new ItemSlideMenu(0, "Start a Round",0));
                        listSliding.add(new ItemSlideMenu(0, "Post a Score",0));
                        listSliding.add(new ItemSlideMenu(0, "Stats & Matchups",0));
                        listSliding.add(new ItemSlideMenu(0, "       Stats",1));
                        listSliding.add(new ItemSlideMenu(0, "       Matchups",1));
                        listSliding.add(new ItemSlideMenu(0, "       Ranking",1));
                        listSliding.add(new ItemSlideMenu(0, "       Points",1));
                        listSliding.add(new ItemSlideMenu(0, "Practice & Goals",0));
                        listSliding.add(new ItemSlideMenu(0, "Profile",0));
                        listViewSliding.invalidate();
                        setSubMenu[2] = true;
                        setSubMenu[3] = false;
                    }
                    else
                    if(position == 3)
                    {
                        listSliding.clear();
                        listSliding.add(new ItemSlideMenu(0, "Start a Round",0));
                        listSliding.add(new ItemSlideMenu(0, "Post a Score",0));
                        listSliding.add(new ItemSlideMenu(0, "Stats & Matchups",0));
                        listSliding.add(new ItemSlideMenu(0, "Practice & Goals",0));
                        listSliding.add(new ItemSlideMenu(0, "Profile",0));
                        listViewSliding.invalidate();
                        setSubMenu[3] = false;
                    }
                    else
                    if(position == 4)
                    {

                    }
                    else
                    if(position == 5)
                    {

                    }
                    else
                    if(position == 6)
                    {

                    }
                    else
                    if(position == 7)
                    {

                    }

                }


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

}
