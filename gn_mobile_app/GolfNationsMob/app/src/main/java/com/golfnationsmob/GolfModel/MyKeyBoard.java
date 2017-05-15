package com.golfnationsmob.GolfModel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.util.AttributeSet;

import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

import java.util.List;

/**
 * Created by Miral on 12/23/2016.
 */
public class MyKeyBoard extends android.inputmethodservice.KeyboardView {

    Context context;

    public MyKeyBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        this.context = context;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        Typeface font =  AppFont.setFont(getContext(),AppFont.ROBOTO_MEDIUM);
        paint.setTypeface(font);
        paint.setTextSize(26);
        paint.setColor(Color.parseColor("#ffffff"));

        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for (Keyboard.Key key : keys) { // int i = 0 ; switch(i) and implement your logic

      /*      if (key.codes[0] == 4) {
                Drawable dr = (Drawable) context.getResources().getDrawable(R.drawable.buttongrey);
                dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                dr.draw(canvas);
                if(key.label != null){
                    canvas.drawText(key.label.toString(), key.x + (key.width*0.30f), key.y +(key.height*0.65f), paint);
                }
                //drawKeyBackground(R.drawable.bg_keyboardview_yes, canvas, key);
               // drawText(canvas, key);
            }*/

            if (key.codes[0] == 67) {
                Drawable dr = (Drawable) context.getResources().getDrawable(R.drawable.buttongrey);
                dr.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
                dr.draw(canvas);
                if(key.icon != null){
                    Bitmap bm1 = BitmapFactory.decodeResource(getResources(),
                            R.drawable.keypad_del);
                    canvas.drawBitmap(bm1,key.x + (key.width*0.44f), key.y +(key.height*0.3f), paint);
                    //canvas.drawBitmap(key.label.toString(), key.x + (key.width*0.3f), key.y +(key.height*0.65f), paint);
                }
                //drawKeyBackground(R.drawable.bg_keyboardview_yes, canvas, key);
                // drawText(canvas, key);
            }


          //  break;
        }
    }
}