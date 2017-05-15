package com.golfnationsmob.GolfView.NewRound;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.golfnationsmob.GolfModel.AddFriendAdaptor;
import com.golfnationsmob.GolfModel.AddFriendName;
import com.golfnationsmob.GolfModel.DividerItemDecoration;
import com.golfnationsmob.GolfModel.RecyclerTouchListener;
import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class AddFriends extends Activity {

    TextView m_titletext;
    Button m_back;

    Button addNew,m_continue,myFriend,myClub,Find;
    int id = 0;
    public ArrayList<AddFriendName> AddFriendNameList = new ArrayList<AddFriendName>();
    private RecyclerView recyclerView;
    private AddFriendAdaptor mAdapter;
    public int m_Position;
    SharedPreferences sharedpreferencesName;
    SharedPreferences sharedpreferencesMember;
    SharedPreferences sharedpreferencesProfilePic;
    SharedPreferences sharedpreferencesselectedMember;
    SharedPreferences sharedpreferencesselectedMemberonRS;
    LinearLayout backlinear;
    Boolean memberShip;
    String name="" ;
    String fullname[] ;
    boolean membershipst[] ;
    public int SelectCount  = 0 ;
    public int IndexForThreeUser[] ={-1,-1,-1};
    public int ValueForThreeUser[] ={-1,-1,-1};
    int memberCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_add_friends);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.subheader);
        m_titletext = (TextView)findViewById(R.id.titletext);
        m_back = (Button)findViewById(R.id.header_left_btn);
        addNew = (Button)findViewById(R.id.idaddnew);
        myFriend = (Button)findViewById(R.id.scorecardcourse);
        myClub = (Button)findViewById(R.id.scorecardTalon);
        Find = (Button)findViewById(R.id.idfind);
        m_continue = (Button)findViewById(R.id.addfriendContinue);
        backlinear = (LinearLayout) findViewById(R.id.backlinear);
        m_titletext.setText("Golfers");
        Typeface font = AppFont.setFont(this,AppFont.ROBOTO_REGULAR);
        Typeface font1 =  AppFont.setFont(this,AppFont.ROBOTO_MEDIUM);
        Typeface font2 = AppFont.setFont(this,AppFont.ROBOTO_LIGHT);
        m_titletext.setTypeface(font);
        addNew.setTypeface(font);
        m_continue.setTypeface(font);
        myFriend.setTypeface(font1);
        myClub.setTypeface(font1);
        Find.setTypeface(font1);

        sharedpreferencesName= getSharedPreferences("Name", MODE_PRIVATE);
        sharedpreferencesMember= getSharedPreferences("membership", MODE_PRIVATE);
        sharedpreferencesProfilePic = getSharedPreferences("profilePic_other", MODE_PRIVATE);
     //   String msg = sharedpreferences.getString("Msg", "");
     //   Log.i("","msg load*****************"+msg);

        sharedpreferencesName= getSharedPreferences("Name", MODE_PRIVATE);
        final  Map<String, ?> allEntries = sharedpreferencesName.getAll();

        fullname = new String[allEntries.size()];


        for(int i=0;i<(allEntries.size());i++)
        {

            fullname[i] =  sharedpreferencesName.getString("name"+(i+1), "");
            Log.i("ss", "fullname[i] ***"+i+"***" + fullname[i]);

        }

        Map<String, ?> allEntries1 = sharedpreferencesMember.getAll();
        membershipst = new boolean[allEntries1.size()];
        for(int i=0;i<(allEntries1.size());i++)
        {
            membershipst[i] =  sharedpreferencesMember.getBoolean("memebership"+(i+1), true);
           // Log.i("ss", " inner membershipst  ******"+i+"***********" + membershipst[i]);

        }


     if(allEntries.size()!=0) {
         for (int k = 0; k < (allEntries.size()); k++) {
           //  if(fullname[k]!= null && membershipst[k] != null)
             {

                 Log.i("ss", "k ******" + (k+1));
                 Log.i("ss", "fullname[k] ******" + fullname[k]);
                 if(fullname[k].equals(""))
                 {
                      Log.i("value","Removed string");
                 }
                 else
                 {
                     AddFriendName friendname = new AddFriendName(fullname[k], membershipst[k], k + 1);
                     AddFriendNameList.add(friendname);
                 }
             }

         }
     }


        backlinear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent PreviousScreen = new Intent(AddFriends.this,RoundSetting.class);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

        String name1="",name2="",nam3="";
        m_continue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                sharedpreferencesselectedMember= getSharedPreferences("AddFriend", MODE_PRIVATE);
                SharedPreferences.Editor editormem = sharedpreferencesselectedMember.edit();
                sharedpreferencesselectedMemberonRS= getSharedPreferences("AddFriendOnRs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferencesselectedMemberonRS.edit();
                memberCount = sharedpreferencesselectedMemberonRS.getInt("memberCount", 0);
                int memCount = 0;
                Log.i("ss", "friend id 0*****************" + sharedpreferencesselectedMember.getInt("friendid"+0, 0));
                Log.i("ss", "friend id  1 *****************" + sharedpreferencesselectedMember.getInt("friendid"+1, 0));
                Log.i("ss", "friend id  2*****************" + sharedpreferencesselectedMember.getInt("friendid"+2, 0));

                     if(sharedpreferencesselectedMember.getInt("friendid"+0, 0)!=-1)
                     {
                         if(memberCount <3) {
                             int value = sharedpreferencesselectedMember.getInt("friendid" + 0, 0);
                             String name = sharedpreferencesName.getString("name" + (value), "");
                             String profilepic =  sharedpreferencesProfilePic.getString("profile_image_player" + (value), "");

                             boolean mem = sharedpreferencesMember.getBoolean("memebership" + (value), true);
                             Log.i("ss", " name 111 *****************" + name);
                             sharedpreferencesselectedMemberonRS = getSharedPreferences("AddFriendOnRs", MODE_PRIVATE);
                             SharedPreferences.Editor editorRS = sharedpreferencesselectedMemberonRS.edit();
                             editorRS.putString("memberonRS" + memberCount, name);
                             editorRS.putString("memberonpropic" + memberCount, profilepic);
                             editorRS.putInt("usrId" + memberCount, value);
                             editorRS.putBoolean("memberonRSB" + memberCount, mem);
                             editorRS.commit();
                             memCount++;
                             memberCount = memberCount + 1;
                             editor.putInt("memberCount", memberCount);
                             Log.i("ss", " memberCount 11*****************" + memberCount);
                             editor.commit();

                             SharedPreferences.Editor editorname = sharedpreferencesName.edit();
                             editorname.putString("name" +(value),"");
                             editorname.commit();
                           //  sharedpreferencesName.edit().remove("name" + (value)).commit();

                            // sharedpreferencesMember.edit().remove("memebership" + (value)).commit();
                         }
                     }

                    if(sharedpreferencesselectedMember.getInt("friendid"+1, 0)!=-1) {
                        if(memberCount <3) {
                            int value = sharedpreferencesselectedMember.getInt("friendid" + 1, 0);
                            String name = sharedpreferencesName.getString("name" + (value), "");
                            String profilepic =  sharedpreferencesProfilePic.getString("profile_image_player" + (value), "");

                            boolean mem = sharedpreferencesMember.getBoolean("memebership" + (value), true);
                            Log.i("ss", " name 22 *****************" + name);
                            sharedpreferencesselectedMemberonRS = getSharedPreferences("AddFriendOnRs", MODE_PRIVATE);
                            SharedPreferences.Editor editorRS = sharedpreferencesselectedMemberonRS.edit();
                            editorRS.putString("memberonRS" + memberCount, name);
                            editorRS.putString("memberonpropic" + memberCount, profilepic);
                            editorRS.putInt("usrId" + memberCount, value);
                            editorRS.putBoolean("memberonRSB" + memberCount, mem);
                            editorRS.commit();
                            memCount++;
                            memberCount = memberCount + 1;
                            editor.putInt("memberCount", memberCount);
                            Log.i("ss", " memberCount 22*****************" + memberCount);
                            editor.commit();
                            SharedPreferences.Editor editorname = sharedpreferencesName.edit();
                            editorname.putString("name" +(value),"");
                            editorname.commit();
                            //  sharedpreferencesName.edit().remove("name" + (value)).commit();

                            // sharedpreferencesMember.edit().remove("memebership" + (value)).commit();
                        }
                    }

                    if(sharedpreferencesselectedMember.getInt("friendid"+2, 0)!=-1) {
                        if(memberCount <3) {
                            int value = sharedpreferencesselectedMember.getInt("friendid" + 2, 0);
                            String name = sharedpreferencesName.getString("name" + (value), "");
                            String profilepic =  sharedpreferencesProfilePic.getString("profile_image_player" + (value), "");

                            boolean mem = sharedpreferencesMember.getBoolean("memebership" + (value), true);
                            Log.i("ss", " name 333 *****************" + name);
                            sharedpreferencesselectedMemberonRS = getSharedPreferences("AddFriendOnRs", MODE_PRIVATE);
                            SharedPreferences.Editor editorRS = sharedpreferencesselectedMemberonRS.edit();
                            editorRS.putString("memberonRS" + memberCount, name);
                            editorRS.putString("memberonpropic" + memberCount, profilepic);
                            editorRS.putInt("usrId" + memberCount, value);
                            editorRS.putBoolean("memberonRSB" + memberCount, mem);
                            editorRS.commit();
                            memCount++;
                            memberCount = memberCount + 1;
                            editor.putInt("memberCount", memberCount);
                            Log.i("ss", " memberCount 33*****************" + memberCount);
                            editor.commit();
                            SharedPreferences.Editor editorname = sharedpreferencesName.edit();
                            editorname.putString("name" +(value),"");
                            editorname.commit();
                            //  sharedpreferencesName.edit().remove("name" + (value)).commit();

                            // sharedpreferencesMember.edit().remove("memebership" + (value)).commit();
                        }
                    }
/*
                    final  Map<String, ?> allEntriesx = sharedpreferencesName.getAll();
              //  Log.i("ss", " allEntriesx *****************" + allEntriesx.size());
                    String tempArray[];
                    boolean tempArray1[];
                    tempArray = new String[allEntriesx.size()-( memCount)];
                    tempArray1 = new boolean[allEntriesx.size()-(memCount)];
              //  Log.i("ss", " allEntriesx after*****************" + (allEntriesx.size()-memberCount));
                    int count = 0;
                    for(int t = 0;t<allEntriesx.size();t++)
                    {
                      //  Log.i("ss", " t xxx *****************" +t);
                        String t1 = sharedpreferencesName.getString("name"+(t+1), "");
                        String c1 = sharedpreferencesName.getString("name"+(sharedpreferencesselectedMember.getInt("friendid"+0, 0)), "");
                        String c2 = sharedpreferencesName.getString("name"+(sharedpreferencesselectedMember.getInt("friendid"+1, 0)), "");
                        String c3 = sharedpreferencesName.getString("name"+(sharedpreferencesselectedMember.getInt("friendid"+2, 0)), "");
                     //   Log.i("ss", " t1 xxx *****************" + t1);
                    //    Log.i("ss", " c1 xxx *****************" + c1);
                     //   Log.i("ss", " c2 xxx *****************" + c2);
                     //   Log.i("ss", " c3 xxx *****************" + c3);
                        if(t1.equals(c1))
                        {
                            sharedpreferencesName.edit().remove("name" + (t+1)).commit();

                            sharedpreferencesMember.edit().remove("memebership" + (t+1)).commit();
                            continue;
                        }
                        else
                        if(t1.equals(c2))
                        {
                            sharedpreferencesName.edit().remove("name" + (t+1)).commit();

                            sharedpreferencesMember.edit().remove("memebership" + (t+1)).commit();

                            continue;
                        }
                        else
                        if(t1.equals(c3))
                        {
                            sharedpreferencesName.edit().remove("name" + (t+1)).commit();

                            sharedpreferencesMember.edit().remove("memebership" + (t+1)).commit();

                            continue;
                        }
                        else
                        {
                            tempArray[count] = sharedpreferencesName.getString("name"+(t+1), "");
                            tempArray1[count]= sharedpreferencesMember.getBoolean("memebership"+(t+1), true);
                          //  Log.i("ss", " tempArray[count]  *****************" + tempArray[count] );
                            sharedpreferencesName.edit().remove("name" + (t+1)).commit();
                            sharedpreferencesMember.edit().remove("memebership" + (t+1)).commit();

                            count++;
                         //   Log.i("ss", " count  *****************" + count );
                        }
                    }
                Map<String, ?> allEntriesx1 = sharedpreferencesName.getAll();
              //  Log.i("ss", " allEntriesx1 *****************" + (allEntriesx1.size()));
                for(int t = 0;t<tempArray.length;t++)
                {
                    //Log.i("ss", " t adding *****************" + t);
                   // Log.i("ss", "  tempArray[t] adding *****************" + tempArray[t]);
                    SharedPreferences.Editor editorn1 = sharedpreferencesName.edit();
                    editorn1.putString("name"+(t+1) , tempArray[t]);
                    editorn1.commit();


                    SharedPreferences.Editor editorn11 = sharedpreferencesMember.edit();
                    editorn11.putBoolean("memebership"+(t+1) , tempArray1[t]);
                    editorn11.commit();

                }*/

               // Map<String, ?> allEntriesx12 = sharedpreferencesName.getAll();
              //  Log.i("ss", " allEntriesx1 *****after************" + (allEntriesx12.size()));

                Intent PreviousScreen = new Intent(AddFriends.this,RoundSetting.class);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

        m_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent PreviousScreen = new Intent(AddFriends.this,RoundSetting.class);
                startActivity(PreviousScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });
        addNew.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent nextScreen = new Intent(AddFriends.this,AddEditFriend.class);
                startActivity(nextScreen);
                finish();
                //m_newRound.setBackgroundResource(R.drawable.buttonselected);

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_friend);

        mAdapter = new AddFriendAdaptor((AddFriendNameList),this,this);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        // recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerTouchListener(getApplicationContext(), new RecyclerTouchListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        AddFriendName friendname = AddFriendNameList.get(position);
                        // Toast.makeText(getApplicationContext(), golfname.getCourse() + " is selected!", Toast.LENGTH_SHORT).show();
                        m_Position= position;

                        // TODO Auto-generated method stub

                        //      Intent nextScreen = new Intent(SelectCourse.this,RoundSetting.class);
                        //       startActivity(nextScreen);
                        //m_newRound.setBackgroundResource(R.drawable.buttonselected);

                    }
                })
        );


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
    public void onBackPressed() {

        Intent PreviousScreen = new Intent(AddFriends.this,RoundSetting.class);
        startActivity(PreviousScreen);
        finish();
        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }
}
