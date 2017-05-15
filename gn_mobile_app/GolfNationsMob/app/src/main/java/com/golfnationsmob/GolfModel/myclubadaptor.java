package com.golfnationsmob.GolfModel;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.golfnationsmob.GolfView.NewRound.MyClub;
import com.golfnationsmob.R;

import java.util.List;

/**
 * Created by Miral on 3/3/2017.
 */
public class myclubadaptor extends RecyclerView.Adapter<myclubadaptor.MyViewHolder> {
private List<myclub> myclubList;
    MyClub MyClubObject;
        Context context;
public myclubadaptor(Context context)
        {
        this.context = context;
        }
public class MyViewHolder extends RecyclerView.ViewHolder {
    public Button onoff;
    public TextView myclubtype,clubdistance;
    public Button selectButton;
    SharedPreferences sharedpreferencesRS;
    public MyViewHolder(View view) {
        super(view);
        onoff = (Button)view.findViewById(R.id.ClubOnOff);
        myclubtype = (TextView)view.findViewById(R.id.Clubtypetext);
        clubdistance = (TextView)view.findViewById(R.id.textclubdist);
        selectButton = (Button)view.findViewById(R.id.setdistclub);

        selectButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // gps.setBackgroundColor(Color.parseColor("#51677e"));
                    // m_buttonGps.setTextColor(Color.WHITE);

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    MyClubObject.Idrelativemyclub.setVisibility(View.GONE);
                   Log.i("Value","Distance  == "+clubdistance.getText());
                    int temp = Integer.parseInt(""+clubdistance.getText());
                    int np1temp = temp/100;
                    int nptemp = (temp-(np1temp*100))/10;
                    int np2temp = temp-(np1temp*100)-(nptemp*10);
                    MyClubObject.np.setVisibility(View.VISIBLE);
                    MyClubObject.np1.setVisibility(View.VISIBLE);
                    MyClubObject.np2.setVisibility(View.VISIBLE);
                    MyClubObject.linearDriveDistText.setVisibility(View.VISIBLE);
                    MyClubObject.np.setValue(nptemp);
                    MyClubObject.np1.setValue(np1temp);
                    MyClubObject.np2.setValue(np2temp);
                    MyClubObject.setDistance = true;
                    MyClubObject.linearbuttonbelow.setVisibility(View.VISIBLE);
                }
                return false;
            }

        });



    }
}



    public myclubadaptor(List<myclub> myclubList, Context context, MyClub MyClubObject) {
        this.myclubList = myclubList;
        this.context = context;
        this.MyClubObject = MyClubObject;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myclubset, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final myclub myClub = myclubList.get(position);
        final int pos = position;
        holder.myclubtype.setText(myClub.getClubname());
        holder.clubdistance.setText(myClub.getClubDistance());
        if(myClub.getClubOnOff())
        {
            holder.onoff.setBackgroundResource(R.drawable.thumbon);

        }
        else
        {
            holder.onoff.setBackgroundResource(R.drawable.thumboff);
        }
        holder.onoff.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    // gps.setBackgroundColor(Color.parseColor("#51677e"));
                    // m_buttonGps.setTextColor(Color.WHITE);

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.i("value","in touchhhhhhhhhhhhhhhh");
                    if(!myClub.getClubOnOff())
                    {
                        holder.onoff.setBackgroundResource(R.drawable.thumbon);
                        myClub.setClubOnOff(true);
                        if(MyClubObject.clubType == MyClubObject.WOODS)
                        {
                            MyClubObject.woods_enable[pos] = true;
                        }
                        else if(MyClubObject.clubType == MyClubObject.HYBRID)
                        {
                            MyClubObject.hybrid_enable[pos] = true;
                        }
                        else if(MyClubObject.clubType == MyClubObject.IRON)
                        {
                            MyClubObject.iron_enable[pos] = true;
                        }

                    }
                    else
                    {
                        holder.onoff.setBackgroundResource(R.drawable.thumboff);
                        myClub.setClubOnOff(false);
                        if(MyClubObject.clubType == MyClubObject.WOODS)
                        {
                            MyClubObject.woods_enable[pos] = false;
                        }
                        else if(MyClubObject.clubType == MyClubObject.HYBRID)
                        {
                            MyClubObject.hybrid_enable[pos] = false;
                        }
                        else if(MyClubObject.clubType == MyClubObject.IRON)
                        {
                            MyClubObject.iron_enable[pos] = false;
                        }


                    }

                }
                {
                    int woodsIndex =0 ;
                    int hybridIndex = 0;
                    int ironIndex = 0;
                    SharedPreferences sharedpreferencesCLUB= context.getSharedPreferences("CLUBSELECT" , context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                    for(int i = 0;i < MyClubObject.woods_typeTemp.length;i++)
                    {
                        if(MyClubObject.woods_enable[i])
                        {
                            editor.putString("woodsSelectString"+woodsIndex ,""+MyClubObject.woods_typeTemp[i]);
                            woodsIndex++;
                            editor.commit();
                        }
                        editor.putBoolean("woods_enable_saved"+i ,MyClubObject.woods_enable[i]);

                    }

                    for(int i = 0;i < MyClubObject.hybrid_type.length;i++)
                    {
                        if(MyClubObject.hybrid_enable[i])
                        {
                            editor.putString("hybridSelectString"+hybridIndex ,""+MyClubObject.hybrid_type[i]);
                            hybridIndex++;
                            editor.commit();
                        }
                        editor.putBoolean("hybrid_enable_saved"+i ,MyClubObject.hybrid_enable[i]);

                    }

                    for(int i = 0;i < MyClubObject.iron_typeTemp.length;i++)
                    {
                        if(MyClubObject.iron_enable[i])
                        {
                            editor.putString("ironSelectString"+ironIndex ,""+MyClubObject.iron_typeTemp[i]);
                            ironIndex++;
                            editor.commit();
                        }
                        editor.putBoolean("iron_enable_saved"+i ,MyClubObject.iron_enable[i]);

                    }

                    editor.putInt("woodsindex",woodsIndex);
                    editor.putInt("hybridindex",hybridIndex);
                    editor.putInt("ironindex",ironIndex);
                    editor.putBoolean("myclubset",true);
                    editor.commit();

                   // Intent nextScreen = new Intent(MyClub.this, ShotClubSelection.class);

                   // startActivity(nextScreen);


                }
                return false;
            }

        });




    }
    public void clear() {
        int size = myclubList.size();
        myclubList.clear();
        notifyItemRangeRemoved(0, size);
    }
    @Override
    public int getItemCount() {
        return myclubList.size();
    }
}