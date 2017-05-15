package com.golfnationsmob.GolfView.NewRound;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.golfnationsmob.GolfModel.DividerItemDecoration;
import com.golfnationsmob.GolfModel.RecyclerTouchListener;
import com.golfnationsmob.GolfModel.myclub;
import com.golfnationsmob.GolfModel.myclubadaptor;
import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

import java.util.ArrayList;
import java.util.List;

public class MyClub extends Activity {
    Button m_back,m_menuButton;
    TextView m_titletext;
    LinearLayout backlinear;

    public List<myclub> myclubList = new ArrayList<>();
    private RecyclerView recyclerView;
    private myclubadaptor mAdapter;
    public RelativeLayout Idrelativemyclub;
    public NumberPicker np,np1,np2;
    public LinearLayout  linearDriveDistText,linearbuttonbelow ;
    Button buttonwood,buttonhybrid,buttoniron;


    public String woods_type[] ={"Driver","2 Wood","3 Wood","4 Wood","5 Wood","6 Wood","7 Wood","8 Wood","9 Wood"};
    public String woods_typeTemp[] ={"Dr","2w","3w","4w","5w","6w","7w","8w","9w"};
    public String woods_distance[] ={"280","270","260","240","230","220","210","200", "190"};
    public boolean woods_enable[]={true,false,true,false,true,false,false,false,false};


    public String hybrid_type[] ={"2H","3H","4H","5H","6H","7H","8H","9H"};
    public String hybrid_distance[] ={"280","270","260","240","230","220","210","200", "190"};
    public boolean hybrid_enable[]={true,false,true,false,true,false,false,false,false};


    public String iron_type[] ={"2 IRON","3 IRON","4 IRON","5 IRON","6 IRON","7 IRON","8 IRON","9 IRON","Pw","Sw","Lw"};
    public String iron_typeTemp[] ={"2i","3i","4i","5i","6i","7i","8i","9i","Pw","Sw","Lw"};
    public String iron_distance[] ={"280","270","260","240","230","220","210","200", "190","60","80","90"};
    public boolean iron_enable[]={false,true,true,true,true,true,true,true,false,false,false};

    myclub myclubtype;



    public boolean setDistance;
    public Button saveButton;
    public final int WOODS = 0;
    public final int HYBRID = WOODS+1;
    public final int IRON = HYBRID+1;

    public int clubType = WOODS;
    int m_Position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_my_club);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.subheader);
        m_back = (Button)findViewById(R.id.header_left_btn);
        m_menuButton = (Button)findViewById(R.id.menubuttonSR);
        buttonwood = (Button)findViewById(R.id.myclubwood);
        buttonhybrid = (Button)findViewById(R.id.myclubhybrid);
        buttoniron = (Button)findViewById(R.id.myclubiron);
        saveButton = (Button)findViewById(R.id.idDRIVEDISSsave);
        backlinear = (LinearLayout) findViewById(R.id.backlinear);

        // saveButton.setVisibility(View.INVISIBLE);
        m_titletext = (TextView)findViewById(R.id.titletext);
        Idrelativemyclub = (RelativeLayout)findViewById(R.id.idrelativemyclub);
        linearDriveDistText = (LinearLayout) findViewById(R.id.LINEARDRIVEDISTTEXT);
        linearbuttonbelow = (LinearLayout) findViewById(R.id.LINEARDRIVEDIST);
        linearbuttonbelow.setVisibility(View.GONE);
        m_titletext.setText("My Clubs");
        Typeface font = AppFont.setFont(this,AppFont.ROBOTO_REGULAR);
        m_titletext.setTypeface(font);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewmyclub);
        SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
        if(sharedpreferencesCLUB.getBoolean("myclubset", false))
        {
            for(int i = 0; i <woods_enable.length;i++ )
            {
                woods_enable[i] = sharedpreferencesCLUB.getBoolean("woods_enable_saved"+i, false);

            }

            for(int i = 0; i <hybrid_enable.length;i++ )
            {
                hybrid_enable[i] = sharedpreferencesCLUB.getBoolean("hybrid_enable_saved"+i, false);

            }

            for(int i = 0; i <iron_enable.length;i++ )
            {
                iron_enable[i] = sharedpreferencesCLUB.getBoolean("iron_enable_saved"+i, false);

            }
        }

        mAdapter = new myclubadaptor(myclubList,this,this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        // recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        buttonwood.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mAdapter.clear();
                    clubType = WOODS;
                    prepareMyclubData(clubType);
                    buttonwood.setBackgroundColor(Color.parseColor("#ebedf1"));
                    buttonhybrid.setBackgroundColor(Color.parseColor("#97b4d4"));
                    buttoniron.setBackgroundColor(Color.parseColor("#97b4d4"));
                    buttonwood.setTextColor(Color.parseColor("#10171f"));
                    buttonhybrid.setTextColor(Color.parseColor("#ffffff"));
                    buttoniron.setTextColor(Color.parseColor("#ffffff"));

                }
                return false;
            }

        });

        backlinear.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(setDistance)
                    {
                        Idrelativemyclub.setVisibility(View.VISIBLE);
                        np.setVisibility(View.GONE);
                        np1.setVisibility(View.GONE);
                        np2.setVisibility(View.GONE);
                        linearDriveDistText.setVisibility(View.GONE);
                        linearbuttonbelow.setVisibility(View.GONE);
                    }
                    else {
                        Intent nextScreen = new Intent(MyClub.this, ShotClubSelection.class);

                        startActivity(nextScreen);
                        finish();
                    }

                }
                return false;
            }

        });

m_back.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(setDistance)
                    {
                        Idrelativemyclub.setVisibility(View.VISIBLE);
                        np.setVisibility(View.GONE);
                        np1.setVisibility(View.GONE);
                        np2.setVisibility(View.GONE);
                        linearDriveDistText.setVisibility(View.GONE);
                        linearbuttonbelow.setVisibility(View.GONE);
                    }
                    else {
                        Intent nextScreen = new Intent(MyClub.this, ShotClubSelection.class);

                        startActivity(nextScreen);
                        finish();
                    }

                }
                return false;
            }

        });

        saveButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                   if(setDistance)
                   {
                       Idrelativemyclub.setVisibility(View.VISIBLE);
                       np.setVisibility(View.GONE);
                       np1.setVisibility(View.GONE);
                       np2.setVisibility(View.GONE);
                       linearDriveDistText.setVisibility(View.GONE);
                       linearbuttonbelow.setVisibility(View.GONE);

                       if(clubType == WOODS)
                       {
                           woods_distance[m_Position] =""+np1.getValue()+""+np.getValue()+""+np2.getValue();
                           final myclub myClub = myclubList.get(m_Position);
                           myClub.setClubDistance(woods_distance[m_Position]);
                           mAdapter.notifyItemChanged(m_Position);

                           Log.i("value","distance set after=="+woods_distance[m_Position]);
                       }
                       else
                       if(clubType == HYBRID)
                       {
                           hybrid_distance[m_Position] =""+np1.getValue()+""+np.getValue()+""+np2.getValue();
                           final myclub myClub = myclubList.get(m_Position);
                           myClub.setClubDistance(hybrid_distance[m_Position]);
                           mAdapter.notifyItemChanged(m_Position);
                       }
                       else
                       {
                           iron_distance[m_Position] =""+np1.getValue()+""+np.getValue()+""+np2.getValue();
                           final myclub myClub = myclubList.get(m_Position);
                           myClub.setClubDistance(iron_distance[m_Position]);
                           mAdapter.notifyItemChanged(m_Position);
                       }
                       setDistance = false;
                   }
                   //else


                }
                return false;
            }

        });


        buttonhybrid.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mAdapter.clear();
                    clubType = HYBRID;
                    prepareMyclubData(clubType);
                    buttonwood.setBackgroundColor(Color.parseColor("#97b4d4"));
                    buttonhybrid.setBackgroundColor(Color.parseColor("#ebedf1"));
                    buttoniron.setBackgroundColor(Color.parseColor("#97b4d4"));
                    buttonwood.setTextColor(Color.parseColor("#ffffff"));
                    buttonhybrid.setTextColor(Color.parseColor("#10171f"));
                    buttoniron.setTextColor(Color.parseColor("#ffffff"));

                }
                return false;
            }

        });

        buttoniron.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mAdapter.clear();
                    clubType = IRON;
                    prepareMyclubData(clubType);
                    buttonwood.setBackgroundColor(Color.parseColor("#97b4d4"));
                    buttonhybrid.setBackgroundColor(Color.parseColor("#97b4d4"));
                    buttoniron.setBackgroundColor(Color.parseColor("#ebedf1"));
                    buttonwood.setTextColor(Color.parseColor("#ffffff"));
                    buttonhybrid.setTextColor(Color.parseColor("#ffffff"));
                    buttoniron.setTextColor(Color.parseColor("#10171f"));

                }
                return false;
            }

        });

        recyclerView.addOnItemTouchListener(
                new RecyclerTouchListener(getApplicationContext(), new RecyclerTouchListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        myclub mYclub = myclubList.get(position);
                        // Toast.makeText(getApplicationContext(), golfname.getCourse() + " is selected!", Toast.LENGTH_SHORT).show();
                        m_Position= position;
                        Log.i("value","m_Position ==="+m_Position);
                        // TODO Auto-generated method stub


                    }
                })
        );

        prepareMyclubData(clubType);
         np = (NumberPicker) findViewById(R.id.np);
         np1 = (NumberPicker) findViewById(R.id.numberPicker);
         np2 = (NumberPicker) findViewById(R.id.numberPicker2);

        //Set TextView text color

        //Populate NumberPicker values from minimum and maximum value range
        //Set the minimum value of NumberPicker

        np.setMinValue(0);
        np1.setMinValue(0);
        np2.setMinValue(0);
        //Specify the maximum value/number of NumberPicker
        np.setMaxValue(9);
        np1.setMaxValue(9);
        np2.setMaxValue(9);

        //Gets whether the selector wheel wraps when reaching the min/max value.
        np.setWrapSelectorWheel(true);
        np1.setWrapSelectorWheel(true);
        np2.setWrapSelectorWheel(true);


        //Set a value change listener for NumberPicker
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
               // tv.setText("Selected Number : " + newVal);
            }
        });
        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
               // tv.setText("Selected Number : " + newVal);
            }
        });
        np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
               // tv.setText("Selected Number : " + newVal);
            }
        });

    }

    private void prepareMyclubData(int type) {
        switch(type) {
            case 0:
              for(int i = 0;i < woods_type.length;i++)
              {
                  myclubtype = new myclub(woods_type[i], woods_distance[i], woods_enable[i], type);
                  myclubList.add(myclubtype);
              }
            break;

            case 1:
                for(int i = 0;i < hybrid_type.length;i++)
                {
                    myclubtype = new myclub(hybrid_type[i], hybrid_distance[i], hybrid_enable[i], type);
                    myclubList.add(myclubtype);
                }
                break;
            case 2:
                for(int i = 0;i < iron_type.length;i++)
                {
                    myclubtype = new myclub(iron_type[i], iron_distance[i], iron_enable[i], type);
                    myclubList.add(myclubtype);
                }

                break;
        }


    }

}
