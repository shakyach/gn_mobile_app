package com.golfnationsmob.GolfView.NewRound;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

public class Scoring extends Activity {
    Button m_continue,m_back,m_grosshelp,m_nethelp,m_strokeplayhelp,m_matchplayhelp,m_stablefordhelp,m_scramblehelp,cancel;
    TextView m_titletext,m_scoringoption,selectfollow,scoringScore;
    CheckBox m_gross,m_net,m_scramble,m_stableford,m_matchplay,m_strokeplay;
    String grossnet = "";
    String scoring= "";

    LinearLayout grosslayout,netLayout;
    LinearLayout splayout,mplayout,sblayout,scramblelayout;
    LinearLayout backlinear;
    SharedPreferences sharedpreferencesRS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.scoring);

        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.subheader);
    //#UI INITILIZATION ####
        m_back = (Button)findViewById(R.id.header_left_btn);
        m_continue = (Button)findViewById(R.id.scoreContinue);
        cancel = (Button)findViewById(R.id.d2);
        m_grosshelp = (Button)findViewById(R.id.button_gross);
        m_nethelp = (Button)findViewById(R.id.button_net);
        m_strokeplayhelp = (Button)findViewById(R.id.button_sp);
        m_matchplayhelp = (Button)findViewById(R.id.button_mp);
        m_stablefordhelp = (Button)findViewById(R.id.button_sb);
        m_scramblehelp = (Button)findViewById(R.id.button_scramble);
        backlinear = (LinearLayout) findViewById(R.id.backlinear);
        grosslayout = (LinearLayout)findViewById(R.id.frontraptorLayout);
        netLayout = (LinearLayout)findViewById(R.id.fronttalanLayout);

        splayout = (LinearLayout)findViewById(R.id.SPlayout);
        mplayout = (LinearLayout)findViewById(R.id.MPlayout);
        sblayout = (LinearLayout)findViewById(R.id.SBlayout);
        scramblelayout = (LinearLayout)findViewById(R.id.scramblelayout);


        m_titletext = (TextView)findViewById(R.id.titletext);
        selectfollow = (TextView)findViewById(R.id.selectfollow);
        scoringScore = (TextView)findViewById(R.id.idscoringscore);
        m_gross = (CheckBox)findViewById(R.id.gross);
        m_net = (CheckBox)findViewById(R.id.net);
        m_strokeplay = (CheckBox)findViewById(R.id.strokeplay);
        m_matchplay = (CheckBox)findViewById(R.id.matchplay);
        m_stableford = (CheckBox)findViewById(R.id.stableford);
        m_scramble = (CheckBox)findViewById(R.id.scramble);
        m_titletext.setText("Scoring");

        final Typeface font = AppFont.setFont(this,AppFont.ROBOTO_REGULAR);
        final Typeface font1 =  AppFont.setFont(this,AppFont.ROBOTO_LIGHT);
        final Typeface font2 = AppFont.setFont(this,AppFont.ROBOTO_MEDIUM);


        m_titletext.setTypeface(font);
        selectfollow.setTypeface(font1);
        scoringScore.setTypeface(font2);

        m_gross.setTypeface(font);
        m_net.setTypeface(font);
        m_strokeplay.setTypeface(font2);
        m_matchplay.setTypeface(font2);
        m_stableford.setTypeface(font2);
        m_scramble.setTypeface(font2);
        m_continue.setTypeface(font);
        cancel.setTypeface(font);

//#####################

     //# HELP OPTION CLICK IMPLEMANTATION
        m_grosshelp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                AlertDialog.Builder builder;


                Context mContext = Scoring.this;
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.info_window,
                        (ViewGroup) findViewById(R.id.layout_root));


                m_scoringoption = (TextView)layout.findViewById(R.id.scoringoption);
                m_scoringoption.setText("Gross");
                ImageView close_dialog = (ImageView) layout.findViewById(R.id.imageView_custom_dialog_close);



                builder = new AlertDialog.Builder(mContext);
                builder.setView(layout);
               final Dialog alertDialog = builder.create();
                close_dialog.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        alertDialog.dismiss();

                    }
                });

                alertDialog.show();

            }
        });


        m_nethelp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                AlertDialog.Builder builder;


                Context mContext = Scoring.this;
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.info_window,
                        (ViewGroup) findViewById(R.id.layout_root));


                m_scoringoption = (TextView)layout.findViewById(R.id.scoringoption);
                m_scoringoption.setText("Net");
                ImageView close_dialog = (ImageView) layout.findViewById(R.id.imageView_custom_dialog_close);



                builder = new AlertDialog.Builder(mContext);
                builder.setView(layout);
                final Dialog alertDialog = builder.create();
                close_dialog.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        alertDialog.dismiss();

                    }
                });

                alertDialog.show();

            }
        });


        m_strokeplayhelp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                AlertDialog.Builder builder;


                Context mContext = Scoring.this;
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.info_window,
                        (ViewGroup) findViewById(R.id.layout_root));


                m_scoringoption = (TextView)layout.findViewById(R.id.scoringoption);
                m_scoringoption.setText("Stroke Play");

                ImageView close_dialog = (ImageView) layout.findViewById(R.id.imageView_custom_dialog_close);



                builder = new AlertDialog.Builder(mContext);
                builder.setView(layout);
                final Dialog alertDialog = builder.create();
                close_dialog.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        alertDialog.dismiss();

                    }
                });

                alertDialog.show();

            }
        });


        m_matchplayhelp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                AlertDialog.Builder builder;


                Context mContext = Scoring.this;
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.info_window,
                        (ViewGroup) findViewById(R.id.layout_root));


                m_scoringoption = (TextView)layout.findViewById(R.id.scoringoption);
                m_scoringoption.setText("Match Play");

                ImageView close_dialog = (ImageView) layout.findViewById(R.id.imageView_custom_dialog_close);



                builder = new AlertDialog.Builder(mContext);
                builder.setView(layout);
                final Dialog alertDialog = builder.create();
                close_dialog.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        alertDialog.dismiss();

                    }
                });

                alertDialog.show();

            }
        });

        m_stablefordhelp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                AlertDialog.Builder builder;


                Context mContext = Scoring.this;
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.info_window,
                        (ViewGroup) findViewById(R.id.layout_root));


                m_scoringoption = (TextView)layout.findViewById(R.id.scoringoption);
                m_scoringoption.setText("Stableford (points)");

                ImageView close_dialog = (ImageView) layout.findViewById(R.id.imageView_custom_dialog_close);



                builder = new AlertDialog.Builder(mContext);
                builder.setView(layout);
                final Dialog alertDialog = builder.create();
                close_dialog.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        alertDialog.dismiss();

                    }
                });

                alertDialog.show();

            }
        });

        m_scramblehelp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                AlertDialog.Builder builder;


                Context mContext = Scoring.this;
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.info_window,
                        (ViewGroup) findViewById(R.id.layout_root));


                m_scoringoption = (TextView)layout.findViewById(R.id.scoringoption);
                m_scoringoption.setText("Scramble");

                ImageView close_dialog = (ImageView) layout.findViewById(R.id.imageView_custom_dialog_close);



                builder = new AlertDialog.Builder(mContext);
                builder.setView(layout);
                final Dialog alertDialog = builder.create();
                close_dialog.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        alertDialog.dismiss();

                    }
                });

                alertDialog.show();

            }
        });
//########################

   //#CHECKED UNCHECKED OPTION IMPLEMENTATION  WITH RESPECT TO UI

        m_strokeplay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(m_strokeplay.isChecked()==true && (m_scramble.isChecked()==true||m_stableford.isChecked()==true||m_matchplay.isChecked()==true)) {
                    m_strokeplay.setChecked(true);
                    m_scramble.setChecked(false);
                    m_stableford.setChecked(false);
                    m_matchplay.setChecked(false);

                }

                if(m_strokeplay.isChecked()==true)
                {
                    splayout.setBackgroundColor(Color.parseColor("#accc3f"));

                    m_strokeplay.setTextColor(Color.parseColor("#ffffff"));
                    m_strokeplay.setButtonDrawable(R.drawable.check2);
                    m_strokeplayhelp.setBackgroundResource(R.drawable.helpselect);
                }
                else
                {
                    splayout.setBackgroundColor(Color.parseColor("#f1f2f5"));

                    m_strokeplay.setTextColor(Color.parseColor("#000000"));
                    m_strokeplay.setButtonDrawable(R.drawable.check1);
                    m_strokeplayhelp.setBackgroundResource(R.drawable.help);
                }

            }
        });

        m_matchplay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(m_matchplay.isChecked()==true && (m_scramble.isChecked()==true||m_stableford.isChecked()==true||m_strokeplay.isChecked()==true)) {
                    m_matchplay.setChecked(true);
                    m_scramble.setChecked(false);
                    m_stableford.setChecked(false);
                    m_strokeplay.setChecked(false);
                }

                if(m_matchplay.isChecked()==true)
                {
                    mplayout.setBackgroundColor(Color.parseColor("#accc3f"));

                    m_matchplay.setTextColor(Color.parseColor("#ffffff"));
                    m_matchplay.setButtonDrawable(R.drawable.check2);
                    m_matchplayhelp.setBackgroundResource(R.drawable.helpselect);
                }
                else
                {
                    mplayout.setBackgroundColor(Color.parseColor("#f1f2f5"));

                    m_matchplay.setTextColor(Color.parseColor("#000000"));
                    m_matchplay.setButtonDrawable(R.drawable.check1);
                    m_matchplayhelp.setBackgroundResource(R.drawable.help);
                }

            }
        });

        m_stableford.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(m_stableford.isChecked()==true && (m_scramble.isChecked()==true||m_matchplay.isChecked()==true||m_strokeplay.isChecked()==true)) {
                    m_stableford.setChecked(true);
                    m_scramble.setChecked(false);
                    m_matchplay.setChecked(false);
                    m_strokeplay.setChecked(false);
                }

                if(m_stableford.isChecked()==true)
                {
                    sblayout.setBackgroundColor(Color.parseColor("#accc3f"));

                    m_stableford.setTextColor(Color.parseColor("#ffffff"));
                    m_stableford.setButtonDrawable(R.drawable.check2);
                    m_stablefordhelp.setBackgroundResource(R.drawable.helpselect);
                }
                else
                {
                    sblayout.setBackgroundColor(Color.parseColor("#f1f2f5"));

                    m_stableford.setTextColor(Color.parseColor("#000000"));
                    m_stableford.setButtonDrawable(R.drawable.check1);
                    m_stablefordhelp.setBackgroundResource(R.drawable.help);
                }


            }
        });

        m_scramble.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (m_scramble.isChecked() == true && (m_stableford.isChecked() == true || m_matchplay.isChecked() == true || m_strokeplay.isChecked() == true)) {
                    m_scramble.setChecked(true);
                    m_stableford.setChecked(false);
                    m_matchplay.setChecked(false);
                    m_strokeplay.setChecked(false);
                }

                if (m_scramble.isChecked() == true) {
                    scramblelayout.setBackgroundColor(Color.parseColor("#accc3f"));

                    m_scramble.setTextColor(Color.parseColor("#ffffff"));
                    m_scramble.setButtonDrawable(R.drawable.check2);
                    m_scramblehelp.setBackgroundResource(R.drawable.helpselect);

                } else {
                    scramblelayout.setBackgroundColor(Color.parseColor("#f1f2f5"));

                    m_scramble.setTextColor(Color.parseColor("#000000"));
                    m_scramble.setButtonDrawable(R.drawable.check1);
                    m_scramblehelp.setBackgroundResource(R.drawable.help);
                }
            }
        });


        m_gross.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(m_gross.isChecked()==true && m_net.isChecked()==true) {
                    m_gross.setChecked(true);
                    m_net.setChecked(false);

                }

                if(m_gross.isChecked()==true)
                {
                    grosslayout.setBackgroundColor(Color.parseColor("#accc3f"));
                    m_gross.setTypeface(font2);
                    m_gross.setTextColor(Color.parseColor("#ffffff"));
                    m_gross.setButtonDrawable(R.drawable.check2);
                    m_grosshelp.setBackgroundResource(R.drawable.helpselect);

                }
                else
                {
                    grosslayout.setBackgroundColor(Color.parseColor("#f1f2f5"));
                    m_gross.setTypeface(font);
                    m_gross.setTextColor(Color.parseColor("#000000"));
                    m_gross.setButtonDrawable(R.drawable.check1);
                    m_grosshelp.setBackgroundResource(R.drawable.help);
                }

            }
        });

        m_net.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(m_net.isChecked() == true && m_gross.isChecked()== true) {
                    m_gross.setChecked(false);
                    m_net.setChecked(true);
                }
                if(m_net.isChecked()==true)
                {
                    netLayout.setBackgroundColor(Color.parseColor("#accc3f"));
                    m_net.setTypeface(font2);
                    m_net.setTextColor(Color.parseColor("#ffffff"));
                    m_net.setButtonDrawable(R.drawable.check2);
                    m_nethelp.setBackgroundResource(R.drawable.helpselect);
                }
                else
                {
                    netLayout.setBackgroundColor(Color.parseColor("#f1f2f5"));
                    m_net.setTypeface(font);
                    m_net.setTextColor(Color.parseColor("#000000"));
                    m_net.setButtonDrawable(R.drawable.check1);
                    m_nethelp.setBackgroundResource(R.drawable.help);
                }

            }
        });
//#####################

        //#BACK OPTION STATUS BAR

        backlinear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent PreviousScreen = new Intent(Scoring.this,RoundSetting.class);
           //     PreviousScreen.putExtras(bundle);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });
        m_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent PreviousScreen = new Intent(Scoring.this,RoundSetting.class);
            //    PreviousScreen.putExtras(bundle);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

     //# CONTINUE OPTION AFTER SETTING VALUE
        m_continue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent PreviousScreen = new Intent(Scoring.this,RoundSetting.class);

              // Bundle bundle = new Bundle();
                if(m_net.isChecked())
                    grossnet="(Net)";
                else
                if(m_gross.isChecked())
                    grossnet="(Gross)";
                  //  bundle.putString("gross_net", " (Gross)");



                if(m_strokeplay.isChecked())
                    scoring = "Stroke Play";
                else
                if(m_matchplay.isChecked())
                    scoring = "Match Play";
                else
                if(m_scramble.isChecked())
                    scoring ="Scramble";
                else
                if(m_stableford.isChecked())
                    scoring ="StableFord";

                sharedpreferencesRS= getSharedPreferences("roundsetting" , MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferencesRS.edit();
               if(scoring.equals("")||grossnet.equals(""))
                {
                   // editor.putString("Scoring" , "");
                }
                else
                  editor.putString("Scoring" , ""+scoring+" "+grossnet);
                editor.commit();


                //PreviousScreen.putExtras(bundle);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });
    }
    @Override
    public void onBackPressed() {


        Intent PreviousScreen = new Intent(Scoring.this,RoundSetting.class);
       // PreviousScreen.putExtras(bundle);
        startActivity(PreviousScreen);
        finish();
        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }
}
