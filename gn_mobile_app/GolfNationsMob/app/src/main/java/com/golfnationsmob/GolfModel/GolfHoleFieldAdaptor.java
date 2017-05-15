package com.golfnationsmob.GolfModel;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

import java.util.List;

/**
 * Created by shakya on 1/18/2017.
 */
public class GolfHoleFieldAdaptor extends RecyclerView.Adapter<GolfHoleFieldAdaptor.MyViewHolder>{
    private List<GolfHoleField> golfholefieldList;
public int selectedItem = 0;
    boolean resetColor;
    Context context;
    public GolfHoleFieldAdaptor(Context context)
    {
        this.context = context;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView m_golfholefield;
        public TextView m_golfholedistane;
        public RelativeLayout m_relativeViewHolder;

        public MyViewHolder(View view) {
            super(view);
            m_golfholefield = (TextView) view.findViewById(R.id.golfholefield);
            m_golfholedistane = (TextView) view.findViewById(R.id.golfholedistance);
            m_relativeViewHolder = (RelativeLayout) view.findViewById(R.id.golfholeviewRelative);
            final Typeface font = AppFont.setFont(context,AppFont.ROBOTO_REGULAR);
            final Typeface font1 = AppFont.setFont(context,AppFont.ROBOTO_BOLD);
            m_golfholefield.setTypeface(font);
            m_golfholedistane.setTypeface(font1);

        }



    }



    public GolfHoleFieldAdaptor(List<GolfHoleField> golfholefieldList, Context context) {
        this.golfholefieldList = golfholefieldList;
        this.context = context;

    }
    MyViewHolder myviewH;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.golfholefield, parent, false);
        myviewH = new MyViewHolder(itemView);
        return myviewH;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GolfHoleField golfHoleField = golfholefieldList.get(position);
       // Log.i("button position","position viewholder###############"+position);
        final int c_position = position;

        if(selectedItem == position && !resetColor) {
            holder.m_golfholefield.setTextColor(Color.parseColor("#ffffff"));
            holder.m_golfholedistane.setTextColor(Color.parseColor("#ffffff"));
            holder.m_relativeViewHolder.setBackgroundColor(Color.parseColor("#39b6e6"));
        }
        else
        {
            holder.m_golfholefield.setTextColor(Color.parseColor("#000000"));
            holder.m_golfholedistane.setTextColor(Color.parseColor("#000000"));
            holder.m_relativeViewHolder.setBackgroundColor(Color.parseColor("#eeeeee"));
            resetColor = false;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Updating old as well as new positions
                notifyItemChanged(selectedItem);
                selectedItem = c_position;
                notifyItemChanged(selectedItem);

                // Do your another stuff for your onClick
            }
        });

        holder.m_golfholefield.setText(golfHoleField.getGolfHoleField());
        holder.m_golfholedistane.setText(golfHoleField.getGolfHoleFieldDistance());

    }
    public  void resetView()
    {
        resetColor  = true;
        notifyItemChanged(selectedItem);
    }

    public  void setView()
    {
        resetColor  = false;
        notifyItemChanged(selectedItem);
        selectedItem = 0;
        notifyItemChanged(selectedItem);
    }
    @Override
    public int getItemCount() {
        return golfholefieldList.size();
    }
}
