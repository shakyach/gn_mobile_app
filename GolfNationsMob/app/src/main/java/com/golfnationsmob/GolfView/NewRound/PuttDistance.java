package com.golfnationsmob.GolfView.NewRound;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

public class PuttDistance extends Activity {
    Button m_back,m_menuButton;
    TextView m_titletext;
    public NumberPicker pd_np,pd_np1;
    Button puttDisButton[];
    boolean puttDisButtonEnable[];
    int touchIndex = 0;
    int index =0;
    Button saveButton;
    boolean buttonpress;
    int profileNo = 0;
    int holeNumVar=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_putt_distance);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.subheader);
        m_titletext = (TextView)findViewById(R.id.titletext);
        m_back = (Button)findViewById(R.id.header_left_btn);
        m_menuButton = (Button)findViewById(R.id.menubuttonSR);
        saveButton = (Button)findViewById(R.id.PDSave);
        m_titletext.setText("Putt Distance");
        Typeface font = AppFont.setFont(this,AppFont.ROBOTO_REGULAR);
        m_titletext.setTypeface(font);
       // m_menuButton.setVisibility(View.VISIBLE);
        SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
        profileNo = sharedpreferencesCLUB.getInt("SRprofileNo", 0);
        holeNumVar= sharedpreferencesCLUB.getInt("SRholeNumVar", 0);
        index = sharedpreferencesCLUB.getInt("clubselectindex"+profileNo+""+holeNumVar, 0);

        pd_np = (NumberPicker) findViewById(R.id.pdnumberPicker4);
        pd_np1 = (NumberPicker) findViewById(R.id.pdnumberPicker3);

        pd_np.setMinValue(0);
        pd_np1.setMinValue(0);
        //Specify the maximum value/number of NumberPicker
        pd_np.setMaxValue(9);
        pd_np1.setMaxValue(9);
        pd_np.setValue(0);
        pd_np1.setValue(0);

        //Gets whether the selector wheel wraps when reaching the min/max value.
        pd_np.setWrapSelectorWheel(true);
        pd_np1.setWrapSelectorWheel(true);
        //Set a value change listener for NumberPicker
        pd_np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
                // tv.setText("Selected Number : " + newVal);
            }
        });
        pd_np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                //Display the newly selected number from picker
                // tv.setText("Selected Number : " + newVal);
            }
        });

        puttDisButton = new Button[12];
        puttDisButtonEnable = new boolean[12];
        for(int i = 0;i<puttDisButton.length;i++) {
            int resID = getResources().getIdentifier("pdnum" +i, "id", getPackageName());
            puttDisButton[i] = (Button) findViewById(resID);
            puttDisButtonEnable[i] = false;
        }
        for(int i =0 ; i < puttDisButton.length;i++) {
            final int val = i;
            puttDisButton[i].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View view, MotionEvent event) {

                    if (event.getAction() == MotionEvent.ACTION_UP) {

                    } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        touchIndex = val;
                        for(int i =0 ; i < puttDisButton.length;i++) {
                            if(touchIndex == i)
                            {
                                if(!puttDisButtonEnable[i]) {
                                    puttDisButton[i].setBackgroundResource(R.drawable.greensquare);
                                    puttDisButton[i].setTextColor(Color.parseColor("#ffffff"));
                                    String temp = "" + puttDisButton[i].getText();
                                    int value = Integer.parseInt(temp);
                                    int value1 = value / 10;
                                    int value2 = value % 10;
                                    if (value1 > 0) {
                                        pd_np1.setValue(value1);
                                    } else {
                                        pd_np1.setValue(0);
                                    }
                                    pd_np.setValue(value2);
                                    buttonpress = true;
                                    puttDisButtonEnable[i] = true;
                                }
                                else
                                {
                                    puttDisButtonEnable[i] = false;
                                    puttDisButton[i].setBackgroundResource(R.drawable.square2);
                                    puttDisButton[i].setTextColor(Color.parseColor("#10171f"));
                                    pd_np.setValue(0);
                                    pd_np1.setValue(0);
                                    buttonpress = false;
                                    puttDisButtonEnable[i] = false;
                                }
                            }
                            else
                            {
                                puttDisButton[i].setBackgroundResource(R.drawable.square2);
                                puttDisButton[i].setTextColor(Color.parseColor("#10171f"));
                            }
                        }
                    }
                    return false;
                }

            });
        }

       saveButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    SharedPreferences sharedpreferencesCLUB= getSharedPreferences("CLUBSELECT" , MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                    if(pd_np.getValue()==0 && pd_np1.getValue()==0)
                    {
                        editor.putString("endPoint"+profileNo+""+holeNumVar+""+index ,"End point");

                    }
                    else
                    if(pd_np1.getValue()==0)
                    {
                        editor.putString("endPoint"+profileNo+""+holeNumVar+""+index ,""+pd_np.getValue());

                    }
                    else
                    editor.putString("endPoint"+profileNo+""+holeNumVar+""+index ,""+pd_np1.getValue()+""+pd_np.getValue());
                    editor.commit();
                    Intent nextScreen = new Intent(PuttDistance.this, StartRound.class);
                    startActivity(nextScreen);
                    finish();
                }
                return false;
            }

        });

        m_back.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    Intent nextScreen = new Intent(PuttDistance.this, StartRound.class);
                    startActivity(nextScreen);
                    finish();
                }
                return false;
            }

        });



    }
    @Override
    public void onBackPressed() {

        return;

    }
}
