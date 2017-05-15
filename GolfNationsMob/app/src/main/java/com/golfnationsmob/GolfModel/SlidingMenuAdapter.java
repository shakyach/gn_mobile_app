package com.golfnationsmob.GolfModel;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

import java.util.List;

/**
 * Created by shakya on 12/30/2016.
 */
public class SlidingMenuAdapter extends BaseAdapter {

    private Context context;
    private List<ItemSlideMenu> lstItem;

    public SlidingMenuAdapter(Context context, List<ItemSlideMenu> lstItem) {
        this.context = context;
        this.lstItem = lstItem;
    }

    @Override
    public int getCount() {
        return lstItem.size();
    }

    @Override
    public Object getItem(int position) {
        return lstItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, R.layout.item_sliding_menu, null);
        ImageView img = (ImageView)v.findViewById(R.id.item_img);
        TextView tv = (TextView)v.findViewById(R.id.item_title);
        final LinearLayout LY = (LinearLayout)v.findViewById(R.id.linearlayoutMenu);
        final Typeface font = AppFont.setFont(context,AppFont.ROBOTO_REGULAR);
        tv.setTypeface(font);

        ItemSlideMenu item = lstItem.get(position);
        img.setImageResource(item.getImgId());
        tv.setText(item.getTitle());
        if(item.getMenuType() == 0)
        {
           tv.setTextColor(Color.parseColor("#ffffff"));
            tv.setTextSize(16);
        }
        else {
            tv.setTextColor(Color.parseColor("#ffffff"));
            tv.setTextSize(15);

        }
        if(position != 0)
        LY.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    LY.setBackgroundColor(Color.parseColor("#10171f"));
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                   LY.setBackgroundColor(Color.parseColor("#666666"));
                }
                return false;
            }

        });

        return v;
    }
}
