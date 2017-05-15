package com.golfnationsmob.GolfModel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.golfnationsmob.GolfView.NewRound.AddFriends;
import com.golfnationsmob.GolfView.NewRound.EditFriendInfo;
import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

/**
 * Created by shakya
 */
public class AddFriendAdaptor extends RecyclerView.Adapter<AddFriendAdaptor.MyViewHolder> {
    private ArrayList<AddFriendName> AddFriendNameList;
    AddFriends addObject;
    Context context;
    SharedPreferences sharedpreferences;
    public AddFriendAdaptor(Context context)
    {
        this.context = context;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView m_friendName,m_stats;
        public Button m_Edit;
        public int id;

        CardView cv;
        public CheckBox listCheckbox;
        SharedPreferences sharedpreferencesAddname;
        SharedPreferences sharedpreferencesAddname1;
        SharedPreferences sharedpreferencesselectedMemberonRS;
        int incr =0;
        public RelativeLayout AddFriendRelativeLO;

        public MyViewHolder(View view) {
            super(view);
            m_friendName = (TextView) view.findViewById(R.id.friendname);
            m_stats = (TextView) view.findViewById(R.id.addfriendstatus);
            cv = (CardView) itemView.findViewById(R.id.cardView);
            final Typeface font = AppFont.setFont(context,AppFont.ROBOTO_REGULAR);
            m_friendName.setTypeface(font);
            m_Edit = (Button) view.findViewById(R.id.addfriendeditbutton);
            listCheckbox = (CheckBox) view.findViewById(R.id.checkBoxaddfriend);
            AddFriendRelativeLO = (RelativeLayout) view.findViewById(R.id.addFriendRelativeLO);
            sharedpreferencesselectedMemberonRS= context.getSharedPreferences("AddFriendOnRs", context.MODE_PRIVATE);
            SharedPreferences.Editor editor1 = sharedpreferencesselectedMemberonRS.edit();
            incr = sharedpreferencesselectedMemberonRS.getInt("memberCount", 0);
            sharedpreferencesAddname= context.getSharedPreferences("AddFriend" , context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = sharedpreferencesAddname.edit();
            for(int i =0 ; i<3;i++)
            {

                editor.putInt("friendid"+i , -1);
                editor.commit();
            }


            listCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    Log.i("ss", " listCheckbox.isChecked()*****************" +  listCheckbox.isChecked());

                    if(listCheckbox.isChecked() == true) {
                        if(addObject.SelectCount < 3-incr) {
                           // listCheckbox.setChecked(true);
                            Log.i("ss", " incr ****************" +incr);
                            Log.i("ss", " addObject.SelectCount above ****************" +addObject.SelectCount);

                            addObject.IndexForThreeUser[addObject.SelectCount] = addObject.m_Position;
                            for(int i =0 ; i<3;i++)
                            {
                                if(addObject.ValueForThreeUser[i] == -1) {
                                    addObject.ValueForThreeUser[i] = id;
                                  //  sharedpreferencesAddname1= context.getSharedPreferences("Name", context.MODE_PRIVATE);
                                  //  String name = sharedpreferencesAddname1.getString("name" +(addObject.ValueForThreeUser[i]), "");

                                    editor.putInt("friendid"+i , id);
                                    Log.i("ss", "  addObject.ValueForThreeUser[i] *******"+i+"**********" + addObject.ValueForThreeUser[i]);
                               //     Log.i("ss", " addObject.ValueForThreeUser[i] checked *****************" + addObject.ValueForThreeUser[i]);
                                    editor.commit();
                                    AddFriendRelativeLO.setBackgroundColor(Color.parseColor("#accc3f"));
                                    m_Edit.setBackgroundColor(Color.parseColor("#accc3f"));
                                    m_friendName.setTextColor(Color.parseColor("#ffffff"));
                                    m_Edit.setTextColor(Color.parseColor("#ffffff"));
                                    m_stats.setTextColor(Color.parseColor("#ffffff"));
                                    listCheckbox.setButtonDrawable(R.drawable.checkbox_checked1);
                                    addObject.SelectCount++;
                                    final Map<String, ?> allEntries = sharedpreferencesAddname.getAll();


                                    for(int r=0;r<allEntries.size();r++)
                                    {
                                        Log.i("ss", " checked  ******"+r+"***********" + sharedpreferencesAddname.getInt("friendid"+r,0));
                                    }

                                    break;
                                }
                            }


                        }
                        else
                        {

                              //  Log.i("ss", " id ****************" +id);
                                if(addObject.ValueForThreeUser[0] == id)
                                {

                                    if(addObject.SelectCount > 0) {
                                        AddFriendRelativeLO.setBackgroundColor(Color.parseColor("#ecedf1"));
                                        m_Edit.setBackgroundColor(Color.parseColor("#ecedf1"));
                                        m_friendName.setTextColor(Color.parseColor("#1b2531"));
                                        m_stats.setTextColor(Color.parseColor("#62768b"));
                                        m_Edit.setTextColor(Color.parseColor("#62778c"));
                                        listCheckbox.setButtonDrawable(R.drawable.checkbox_empty1);
                                        addObject.SelectCount--;
                                        addObject.ValueForThreeUser[0] = -1;
                                        editor.putInt("friendid" + 0, addObject.ValueForThreeUser[0]);
                                        Log.i("ss", "remove  addObject.ValueForThreeUser[i] *******"+0+"**********" + addObject.ValueForThreeUser[0]);

                                        editor.commit();
                                        listCheckbox.setChecked(false);
                                    }
                                    return;
                                }
                                else
                                if(addObject.ValueForThreeUser[1] == id)
                                {

                                    if(addObject.SelectCount > 0) {
                                        AddFriendRelativeLO.setBackgroundColor(Color.parseColor("#ecedf1"));
                                        m_Edit.setBackgroundColor(Color.parseColor("#ecedf1"));
                                        m_friendName.setTextColor(Color.parseColor("#1b2531"));
                                        m_stats.setTextColor(Color.parseColor("#62768b"));
                                        m_Edit.setTextColor(Color.parseColor("#62778c"));
                                        listCheckbox.setButtonDrawable(R.drawable.checkbox_empty1);
                                        addObject.SelectCount--;
                                        addObject.ValueForThreeUser[1] = -1;
                                        editor.putInt("friendid" + 1, addObject.ValueForThreeUser[1]);
                                        Log.i("ss", "remove  addObject.ValueForThreeUser[i] *******"+1+"**********" + addObject.ValueForThreeUser[1]);

                                        editor.commit();
                                        listCheckbox.setChecked(false);
                                    }
                                    return;
                                }
                                else
                                if(addObject.ValueForThreeUser[2] == id) {

                                    if(addObject.SelectCount > 0) {
                                        AddFriendRelativeLO.setBackgroundColor(Color.parseColor("#ecedf1"));
                                        m_Edit.setBackgroundColor(Color.parseColor("#ecedf1"));
                                        m_friendName.setTextColor(Color.parseColor("#1b2531"));
                                        m_stats.setTextColor(Color.parseColor("#62768b"));
                                        m_Edit.setTextColor(Color.parseColor("#62778c"));
                                        listCheckbox.setButtonDrawable(R.drawable.checkbox_empty1);
                                        addObject.SelectCount--;
                                        addObject.ValueForThreeUser[2] = -1;
                                        editor.putInt("friendid" + 2, addObject.ValueForThreeUser[2]);
                                        Log.i("ss", "remove  addObject.ValueForThreeUser[i] *******"+2+"**********" + addObject.ValueForThreeUser[2]);

                                        editor.commit();
                                        listCheckbox.setChecked(false);
                                    }
                                    return;
                                }
                                else {
                                    listCheckbox.setChecked(false);
                                    Toast.makeText(context, " You can select only three Golfers.", Toast.LENGTH_SHORT).show();
                                }
                        }

                        Log.i("ss", " addObject.SelectCount checked *****************" +  addObject.SelectCount);
                    }
                    else
                    {
                        //listCheckbox.setChecked(false);

                        for(int i =0 ; i<3;i++)
                        {
                            if(addObject.m_Position == addObject.IndexForThreeUser[i]) {

                                if(addObject.SelectCount > 0) {
                                    AddFriendRelativeLO.setBackgroundColor(Color.parseColor("#ecedf1"));
                                    m_Edit.setBackgroundColor(Color.parseColor("#ecedf1"));
                                    m_friendName.setTextColor(Color.parseColor("#1b2531"));
                                    m_stats.setTextColor(Color.parseColor("#62768b"));
                                    m_Edit.setTextColor(Color.parseColor("#62778c"));
                                    listCheckbox.setButtonDrawable(R.drawable.checkbox_empty1);
                                    addObject.SelectCount--;
                                    addObject.ValueForThreeUser[i] = -1;
                                    //  sharedpreferencesAddname = context.getSharedPreferences("AddFriend" , context.MODE_PRIVATE);
                                    Log.i("ss", " friendid UNchecked ********" + i + "*********" + "friendid" + i);
                                    Log.i("ss", " addObject.ValueForThreeUser[i] UNchecked *****************" + addObject.ValueForThreeUser[i]);
                                    // sharedpreferencesAddname.edit().remove("friendid"+i).commit();
                                    editor.putInt("friendid" + i, addObject.ValueForThreeUser[i]);
                                    editor.commit();
                                    final Map<String, ?> allEntries = sharedpreferencesAddname.getAll();


                                    for (int r = 0; r < allEntries.size(); r++) {
                                        Log.i("ss", "  unchecked******" + r + "***********" + sharedpreferencesAddname.getInt("friendid" + r, 0));
                                    }
                                }

                                break;
                            }
                        }
                        Log.i("ss", " addObject.SelectCount Unchecked *****************" +  addObject.SelectCount);
                    }


                }
            });

            m_Edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  //  if(addObject.AddFriendNameList.get(addObject.m_Position).m_selector)
                    {
                        SharedPreferences sharedpreferencesName= context.getSharedPreferences("Name", context.MODE_PRIVATE);
                        sharedpreferences= context.getSharedPreferences("Mail" , context.MODE_PRIVATE);

                        Log.i("ff","name selected==="+(sharedpreferencesName.getString("name"+(id), "")));
                        int index = (sharedpreferencesName.getString("name"+(id), "")).indexOf(" ");
                        int lastindex = (sharedpreferencesName.getString("name"+(id), "")).length();
                        String name = (sharedpreferencesName.getString("name"+(id), "")).substring(0, index);
                        String sname = (sharedpreferencesName.getString("name"+(id), "")).substring(index+1, lastindex);


                        SharedPreferences Edituser= context.getSharedPreferences("useredit", context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = Edituser.edit();
                        editor.putString("editname",""+name);
                        editor.putString("editsurname",""+sname);
                        editor.putString("fromscreen","addfriendedit");
                        editor.putString("editemail",""+(sharedpreferences.getString("email"+(id), "")));
                        editor.putInt("edit_id",id);
                        editor.commit();

                       // sharedpreferences= context.getSharedPreferences("Mail" , context.MODE_PRIVATE);
                     //   final String msg = sharedpreferences.getString("email"+name, "");
                      //  Log.i("","msg load*****************"+msg);
                        Intent nextScreen = new Intent(context, EditFriendInfo.class);
                        context.startActivity(nextScreen);
                    }
                }
            });


        }
    }



    public AddFriendAdaptor(ArrayList<AddFriendName> AddFriendNameList, Context context, AddFriends addObject) {
        this.AddFriendNameList = sortList(AddFriendNameList);
        this.context = context;
        this.addObject = addObject;
    }
    ArrayList<AddFriendName> sortList(ArrayList<AddFriendName> list) {
        Collections.sort(list, new Comparator<AddFriendName>() {
            @Override
            public int compare(AddFriendName teamMember1, AddFriendName teamMember2) {
                return teamMember1.getName().compareTo(teamMember2.getName());
            }
        });
        return list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.addfriend_name, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AddFriendName AddFriendName = AddFriendNameList.get(position);
        holder.m_friendName.setText(AddFriendName.getName());
      //  holder.membership = AddFriendName.getboolean();
        //Log.i("ss", "membership adaptor*****************" + AddFriendName.getboolean());
        if(AddFriendName.getboolean())
        {
            holder.m_Edit.setVisibility(RelativeLayout.GONE);
            holder.m_stats.setText("Handicap : 13 | Score Average : 85");//Temp String -will change updated from server db
        }

        holder.id = AddFriendName.getId();
        Log.i("ss", "  holder.id**********" + holder.id);


    }

    @Override
    public int getItemCount() {
        return AddFriendNameList.size();
    }
}
