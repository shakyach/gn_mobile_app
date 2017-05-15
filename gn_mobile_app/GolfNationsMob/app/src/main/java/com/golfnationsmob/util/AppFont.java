package com.golfnationsmob.util;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Miral on 12/9/2016.
 */
public  class  AppFont {

    public static final int ROBOTO_REGULAR = 0;
    public static final int ROBOTO_MEDIUM = 1;
    public static final int ROBOTO_LIGHT = 2;
    public static final int ROBOTO_BOLD = 3;
    public static final int ROBOTO_THIN = 4;
    public static Typeface font;
    public static Typeface setFont(Context context,int FontType)
    {

        switch(FontType)
        {
            case ROBOTO_REGULAR:

                 font = Typeface.createFromAsset(context.getAssets(), "font/Roboto-Regular_0.ttf");

            break;

            case ROBOTO_MEDIUM:

                 font = Typeface.createFromAsset(context.getAssets(), "font/Roboto-Medium_0.ttf");

            break;

            case ROBOTO_LIGHT:

                 font = Typeface.createFromAsset(context.getAssets(),  "font/Roboto-Light_0.ttf");

            break;

            case ROBOTO_BOLD:

                 font = Typeface.createFromAsset(context.getAssets(),  "font/Roboto-Bold_0.ttf");

            break;

            case ROBOTO_THIN:

                 font = Typeface.createFromAsset(context.getAssets(), "font/Roboto-Thin_0.ttf");

            break;
        }

        return font;

    }

}
