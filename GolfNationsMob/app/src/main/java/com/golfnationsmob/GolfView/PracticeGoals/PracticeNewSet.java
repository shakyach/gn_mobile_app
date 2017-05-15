package com.golfnationsmob.GolfView.PracticeGoals;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

public class PracticeNewSet extends Activity {
    Button m_back,m_continue;
    TextView m_titletext,FeetText;
    LinearLayout linLayButton[] = {null,null,null};
    Button FeetButton[] ={null,null,null,null,null,null,null,null,null,null,null,null};
    boolean FeetButtonflag[] ={false,false,false,false,false,false,false,false,false,false,false,false};
    Button BelowButtons[] = {null,null,null,null,null};
    boolean BelowButtonsflag[] = {false,false,false,false,false};
    String PracticeName;
    int PracticeId;

    String appShotIron[] = {"3i","4i","5i","6i","7i","8i","9i","PW","LW","SW"};
    String offtheTreeIron[] = {"3i","4i","5i","6i","7i","8i","9i"};
    String Shortmedlong[] = {"SHORT","MED","LONG"};
    String ShorPitch[] = {"PW","LW","SW"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_practice_new_set);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.subheader);
        m_back = (Button) findViewById(R.id.header_left_btn);
        m_continue = (Button) findViewById(R.id.resentsetcontinue);
        m_titletext = (TextView)findViewById(R.id.titletext);
        FeetText = (TextView)findViewById(R.id.FeetTextId);
        m_titletext.setText("Practice & Goals");
        Typeface font = AppFont.setFont(this,AppFont.ROBOTO_REGULAR);
        m_titletext.setTypeface(font);
        for(int i = 0;i<FeetButton.length;i++) {
            int resID = getResources().getIdentifier("num" +i, "id", getPackageName());
            FeetButton[i] = (Button) findViewById(resID);
        }

        for(int i = 0;i<linLayButton.length;i++) {
            int resID = getResources().getIdentifier("lin" +i, "id", getPackageName());
            linLayButton[i] = (LinearLayout) findViewById(resID);
        }

        for(int i = 0;i<BelowButtons.length;i++) {
            int resID = getResources().getIdentifier("belowbut" +i, "id", getPackageName());
            BelowButtons[i] = (Button) findViewById(resID);
        }

        SharedPreferences sharedpreferencestemp= getSharedPreferences("PRACTICETYPE" , MODE_PRIVATE);
        PracticeName = sharedpreferencestemp.getString("PracticeName","");
        PracticeId = sharedpreferencestemp.getInt("PracticeId",0);

        if(PracticeId == 0||PracticeId == 1||PracticeId == 2)
        {
            for(int i = 0;i<FeetButton.length;i++) {
                final int val = i;

               FeetButton[i].setOnClickListener(new View.OnClickListener() {

               @Override
                public void onClick(View v) {
                        // TODO Auto-generated method stub
               {

                       if (!FeetButtonflag[val]) {
                           for(int j = 0;j<FeetButton.length;j++)
                           {
                               if(FeetButtonflag[j]) {
                                   FeetButton[j].setBackgroundResource(R.drawable.square2);
                                   FeetButtonflag[j] = false;
                               }
                           }
                           FeetButton[val].setBackgroundResource(R.drawable.greensquare);
                           FeetButtonflag[val] = true;
                           if (val < 3) {
                               SharedPreferences sharedpreferencesRS = getSharedPreferences("PRACTICETYPE", MODE_PRIVATE);
                               SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                               editor.putInt("PracticeId", 0);
                               editor.commit();
                           } else if (val > 2 && val < 6) {
                               SharedPreferences sharedpreferencesRS = getSharedPreferences("PRACTICETYPE", MODE_PRIVATE);
                               SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                               editor.putInt("PracticeId", 1);
                               editor.commit();
                           } else {
                               SharedPreferences sharedpreferencesRS = getSharedPreferences("PRACTICETYPE", MODE_PRIVATE);
                               SharedPreferences.Editor editor = sharedpreferencesRS.edit();
                               editor.putInt("PracticeId", 2);
                               editor.commit();
                           }
                       } else {
                           FeetButton[val].setBackgroundResource(R.drawable.square2);
                           FeetButtonflag[val] = false;
                       }


                            //Intent NextScreen = new Intent(PracGoPutting.this, PracticeNewSet.class);
                           // startActivity(NextScreen);
               }


                    }
                });
            }
            for(int i = 0;i<BelowButtons.length;i++) {
               final int val = i;
               BelowButtons[i].setOnClickListener(new View.OnClickListener() {

                   @Override
                   public void onClick(View v) {
                       // TODO Auto-generated method stub
                       {
                           if(!BelowButtonsflag[val])
                           {
                               for(int j = 0;j<BelowButtons.length;j++)
                               {
                                   if(BelowButtonsflag[j]) {
                                       Log.i("value","BelowButtonsflag[j]=="+BelowButtonsflag[j]);
                                       if(j == 0)
                                           BelowButtons[j].setBackgroundResource(R.drawable.pracleftdouble);
                                       else if(j == 1)
                                           BelowButtons[j].setBackgroundResource(R.drawable.pracleft);
                                       else if(j == 2)
                                           BelowButtons[j].setBackgroundResource(R.drawable.pracup);
                                       else if(j == 3)
                                           BelowButtons[j].setBackgroundResource(R.drawable.pracright);
                                       else if(j == 4)
                                           BelowButtons[j].setBackgroundResource(R.drawable.pracrightdouble);

                                       BelowButtonsflag[j] = false;
                                   }
                               }
                               if(val == 0)
                                   BelowButtons[val].setBackgroundResource(R.drawable.pracleftdoublegreen);
                               else if(val == 1)
                                   BelowButtons[val].setBackgroundResource(R.drawable.pracleftgreen);
                               else if(val == 2)
                                   BelowButtons[val].setBackgroundResource(R.drawable.upgreen);
                               else if(val == 3)
                                   BelowButtons[val].setBackgroundResource(R.drawable.pracrightgreen);
                               else if(val == 4)
                                   BelowButtons[val].setBackgroundResource(R.drawable.pracrightdoublegreen);
                               BelowButtonsflag[val] = true;
                           }
                           else
                           {
                               if(val == 0)
                                   BelowButtons[val].setBackgroundResource(R.drawable.pracleftdouble);
                               else if(val == 1)
                                   BelowButtons[val].setBackgroundResource(R.drawable.pracleft);
                               else if(val == 2)
                                   BelowButtons[val].setBackgroundResource(R.drawable.pracup);
                               else if(val == 3)
                                   BelowButtons[val].setBackgroundResource(R.drawable.pracright);
                               else if(val == 4)
                                   BelowButtons[val].setBackgroundResource(R.drawable.pracrightdouble);

                               BelowButtonsflag[val] = false;
                           }
                           //Intent NextScreen = new Intent(PracGoPutting.this, PracticeNewSet.class);
                           // startActivity(NextScreen);
                       }


                   }
               });

            }
        }
        else
        if(PracticeId == 6)
        {
            FeetText.setText("IRON");
            for(int i = 0;i<FeetButton.length;i++) {
                final int val = i;
               if(i<FeetButton.length-2) {
                   FeetButton[i].setText("" + appShotIron[i]);
                   FeetButton[i].setOnClickListener(new View.OnClickListener() {

                       @Override
                       public void onClick(View v) {
                           // TODO Auto-generated method stub
                           {
                               if(!FeetButtonflag[val])
                               {
                                   for(int j = 0;j<FeetButton.length;j++)
                                   {
                                       if(FeetButtonflag[j]) {
                                           FeetButton[j].setBackgroundResource(R.drawable.square2);
                                           FeetButtonflag[j] = false;
                                       }
                                   }
                                   FeetButton[val].setBackgroundResource(R.drawable.greensquare);
                                   FeetButtonflag[val] = true;
                               }
                               else
                               {
                                   FeetButton[val].setBackgroundResource(R.drawable.square2);
                                   FeetButtonflag[val] = false;
                               }
                               //Intent NextScreen = new Intent(PracGoPutting.this, PracticeNewSet.class);
                               // startActivity(NextScreen);
                           }


                       }
                   });
               }
               else
                   FeetButton[i].setVisibility(View.INVISIBLE);
            }
            for(int i = 0;i<BelowButtons.length;i++) {
                final int val = i;
                if(i<BelowButtons.length-2) {
                    BelowButtons[i].setText("" + Shortmedlong[i]);
                    BelowButtons[i].setBackgroundResource(R.drawable.square2);
                    BelowButtons[i].setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            {
                                if(!BelowButtonsflag[val])
                                {
                                    for(int j = 0;j<BelowButtons.length;j++)
                                    {
                                        if(BelowButtonsflag[j]) {
                                            Log.i("value","BelowButtonsflag[j]=="+BelowButtonsflag[j]);
                                            BelowButtons[j].setBackgroundResource(R.drawable.square2);
                                            BelowButtonsflag[j] = false;
                                        }
                                    }
                                    BelowButtons[val].setBackgroundResource(R.drawable.greensquare);
                                    BelowButtonsflag[val] = true;
                                }
                                else
                                {
                                    BelowButtons[val].setBackgroundResource(R.drawable.square2);
                                    BelowButtonsflag[val] = false;
                                }
                                //Intent NextScreen = new Intent(PracGoPutting.this, PracticeNewSet.class);
                                // startActivity(NextScreen);
                            }


                        }
                    });
                }
                else
                BelowButtons[i].setVisibility(View.GONE);

            }
        }
        else
        if(PracticeId == 3)
        {
            FeetText.setText("IRON");

            for(int i = 0;i<FeetButton.length;i++) {
                final int val  = i;
                if(i<3) {
                    FeetButton[i].setText("" + ShorPitch[i]);
                    FeetButton[i].setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            {
                                if(!FeetButtonflag[val])
                                {
                                    for(int j = 0;j<FeetButton.length;j++)
                                    {
                                        if(FeetButtonflag[j]) {
                                            FeetButton[j].setBackgroundResource(R.drawable.square2);
                                            FeetButtonflag[j] = false;
                                        }
                                    }
                                    FeetButton[val].setBackgroundResource(R.drawable.greensquare);
                                    FeetButtonflag[val] = true;
                                }
                                else
                                {
                                    FeetButton[val].setBackgroundResource(R.drawable.square2);
                                    FeetButtonflag[val] = false;
                                }
                                //Intent NextScreen = new Intent(PracGoPutting.this, PracticeNewSet.class);
                                // startActivity(NextScreen);
                            }


                        }
                    });
                }
                else
                    FeetButton[i].setVisibility(View.INVISIBLE);
            }
            for(int i = 0;i<BelowButtons.length;i++) {
                final int val =i;
                if(i<BelowButtons.length-2) {
                    BelowButtons[i].setText("" + Shortmedlong[i]);
                    BelowButtons[i].setBackgroundResource(R.drawable.square2);
                    BelowButtons[i].setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            {
                                if(!BelowButtonsflag[val])
                                {
                                    for(int j = 0;j<BelowButtons.length;j++)
                                    {
                                        if(BelowButtonsflag[j]) {
                                            Log.i("value","BelowButtonsflag[j]=="+BelowButtonsflag[j]);
                                            BelowButtons[j].setBackgroundResource(R.drawable.square2);
                                            BelowButtonsflag[j] = false;
                                        }
                                    }
                                    BelowButtons[val].setBackgroundResource(R.drawable.greensquare);
                                    BelowButtonsflag[val] = true;
                                }
                                else
                                {
                                    BelowButtons[val].setBackgroundResource(R.drawable.square2);
                                    BelowButtonsflag[val] = false;
                                }
                                //Intent NextScreen = new Intent(PracGoPutting.this, PracticeNewSet.class);
                                // startActivity(NextScreen);
                            }


                        }
                    });
                }
                else
                    BelowButtons[i].setVisibility(View.GONE);

            }
        }
        else
        if(PracticeId == 9)
        {
            FeetText.setText("IRON");

            for(int i = 0;i<FeetButton.length;i++) {
                final int val =i;
                if(i<FeetButton.length-5) {
                    FeetButton[i].setText("" + appShotIron[i]);
                    FeetButton[i].setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            {
                                if(!FeetButtonflag[val])
                                {
                                    for(int j = 0;j<FeetButton.length;j++)
                                    {
                                        if(FeetButtonflag[j]) {
                                            FeetButton[j].setBackgroundResource(R.drawable.square2);
                                            FeetButtonflag[j] = false;
                                        }
                                    }
                                    FeetButton[val].setBackgroundResource(R.drawable.greensquare);                                    FeetButtonflag[val] = true;
                                }
                                else
                                {
                                    FeetButton[val].setBackgroundResource(R.drawable.square2);
                                    FeetButtonflag[val] = false;
                                }
                                //Intent NextScreen = new Intent(PracGoPutting.this, PracticeNewSet.class);
                                // startActivity(NextScreen);
                            }


                        }
                    });
                }
                else
                    FeetButton[i].setVisibility(View.INVISIBLE);
            }
            for(int i = 0;i<BelowButtons.length;i++) {

                if(i<BelowButtons.length-2) {
                    final int val =i;
                    BelowButtons[i].setText("" + Shortmedlong[i]);
                    BelowButtons[i].setBackgroundResource(R.drawable.square2);
                    BelowButtons[i].setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            {
                                if(!BelowButtonsflag[val])
                                {
                                    for(int j = 0;j<BelowButtons.length;j++)
                                    {
                                        if(BelowButtonsflag[j]) {
                                            Log.i("value","BelowButtonsflag[j]=="+BelowButtonsflag[j]);
                                            BelowButtons[j].setBackgroundResource(R.drawable.square2);
                                            BelowButtonsflag[j] = false;
                                        }
                                    }
                                    BelowButtons[val].setBackgroundResource(R.drawable.greensquare);
                                    BelowButtonsflag[val] = true;
                                }
                                else
                                {
                                    BelowButtons[val].setBackgroundResource(R.drawable.square2);
                                    BelowButtonsflag[val] = false;
                                }
                                //Intent NextScreen = new Intent(PracGoPutting.this, PracticeNewSet.class);
                                // startActivity(NextScreen);
                            }


                        }
                    });
                }
                else
                    BelowButtons[i].setVisibility(View.GONE);

            }
        }


        m_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent PreviousScreen = new Intent(PracticeNewSet.this, PracGoPutting.class);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });
        m_continue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                {

                    Intent NextScreen = new Intent(PracticeNewSet.this, PracticeInputText.class);
                     startActivity(NextScreen);
                    finish();
                }


            }
        });


    }
}
