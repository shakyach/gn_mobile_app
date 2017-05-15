package com.golfnationsmob.GolfView.PracticeGoals;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.golfnationsmob.GolfModel.DividerItemDecoration;
import com.golfnationsmob.GolfModel.RecyclerTouchListener;
import com.golfnationsmob.GolfModel.ResentSets;
import com.golfnationsmob.GolfModel.ResentSetsAdaptor;
import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

import java.util.ArrayList;
import java.util.List;

public class PracGoPutting extends Activity {
    Button m_back,m_newset;
    TextView m_titletext,PracticeNameView;
    public List<ResentSets> resentsetsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ResentSetsAdaptor mAdapter;
    String PracticeName;
    int PracticeId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_prac_go_putting);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.subheader);
        m_back = (Button) findViewById(R.id.header_left_btn);
        m_newset = (Button) findViewById(R.id.Bnewset);
        m_titletext = (TextView)findViewById(R.id.titletext);
        PracticeNameView = (TextView)findViewById(R.id.practicename);
        m_titletext.setText("Practice & Goals");
        SharedPreferences sharedpreferencestemp= getSharedPreferences("PRACTICETYPE" , MODE_PRIVATE);
        PracticeName = sharedpreferencestemp.getString("PracticeName","");
        PracticeId = sharedpreferencestemp.getInt("PracticeId",0);
        PracticeNameView.setText(""+PracticeName);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewputting);
        Typeface font = AppFont.setFont(this,AppFont.ROBOTO_REGULAR);
        m_titletext.setTypeface(font);
        mAdapter = new ResentSetsAdaptor(resentsetsList,this,this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        // recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        m_newset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
               {

                    Intent NextScreen = new Intent(PracGoPutting.this, PracticeNewSet.class);
                    startActivity(NextScreen);
                   finish();
                }


            }
        });
        recyclerView.addOnItemTouchListener(
                new RecyclerTouchListener(getApplicationContext(), new RecyclerTouchListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        ResentSets resentsets = resentsetsList.get(position);
                        // Toast.makeText(getApplicationContext(), golfname.getCourse() + " is selected!", Toast.LENGTH_SHORT).show();
                       // m_Position= position;
                        // TODO Auto-generated method stub


                    }
                })
        );
        prepareResentsetsData();
        m_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent PreviousScreen = new Intent(PracGoPutting.this, PracticeGoalsMain.class);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });
    }

    private void prepareResentsetsData() {
        ResentSets resentsets = new ResentSets("11-14-16","2 Feet","4 of 10","46%");
        resentsetsList.add(resentsets);
        resentsets = new ResentSets("11-12-16","2 Feet","4 of 10","48%");
        resentsetsList.add(resentsets);
        resentsets = new ResentSets("11-07-16","8 Feet","4 of 10","47%");
        resentsetsList.add(resentsets);
        resentsets = new ResentSets("11-12-16","2 Feet","4 of 10","48%");
        resentsetsList.add(resentsets);
        resentsets = new ResentSets("11-07-16","8 Feet","4 of 10","47%");
        resentsetsList.add(resentsets);
        resentsets = new ResentSets("11-12-16","2 Feet","4 of 10","48%");
        resentsetsList.add(resentsets);
        resentsets = new ResentSets("11-07-16","8 Feet","4 of 10","47%");
        resentsetsList.add(resentsets);
        resentsets = new ResentSets("11-12-16","2 Feet","4 of 10","48%");
        resentsetsList.add(resentsets);
        resentsets = new ResentSets("11-07-16","8 Feet","4 of 10","47%");
        resentsetsList.add(resentsets);
        resentsets = new ResentSets("11-12-16","2 Feet","4 of 10","48%");
        resentsetsList.add(resentsets);
        resentsets = new ResentSets("11-07-16","8 Feet","4 of 10","47%");
        resentsetsList.add(resentsets);
        resentsets = new ResentSets("11-12-16","2 Feet","4 of 10","48%");
        resentsetsList.add(resentsets);
        resentsets = new ResentSets("11-07-16","8 Feet","4 of 10","47%");
        resentsetsList.add(resentsets);
        resentsets = new ResentSets("11-12-16","2 Feet","4 of 10","48%");
        resentsetsList.add(resentsets);
        resentsets = new ResentSets("11-07-16","8 Feet","4 of 10","47%");
        resentsetsList.add(resentsets);
    }
}
