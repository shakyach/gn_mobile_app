package com.golfnationsmob.GolfView.NewRound.Gps;

import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.golfnationsmob.GolfModel.DividerItemDecoration;
import com.golfnationsmob.GolfModel.GolfHoleField;
import com.golfnationsmob.GolfModel.GolfHoleFieldAdaptor;
import com.golfnationsmob.GolfModel.ItemSlideMenu;
import com.golfnationsmob.GolfModel.RecyclerTouchListener;
import com.golfnationsmob.GolfModel.SlidingMenuAdapter;
import com.golfnationsmob.GolfView.MainPage;
import com.golfnationsmob.GolfView.NewRound.RoundSetting;
import com.golfnationsmob.GolfView.NewRound.ScoreCard;
import com.golfnationsmob.GolfView.NewRound.StartRound;
import com.golfnationsmob.R;
import com.golfnationsmob.util.AppFont;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;


public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener,GoogleMap.OnMapLoadedCallback,LocationSource,OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,LocationListener

{
     GoogleMap mMap;
    ProgressDialog dialog;
    protected GoogleApiClient mGoogleApiClient;
    protected Location mLastLocation;
    double latitude = 1;
    double longitude = 1;
    LatLng curLocation;
    LatLng Distance;
    TouchableSupportMapFragment mapFragment;
    PolylineOptions polylineOptions;
    Polyline line1,line,line2;
    MarkerOptions markerOptionsstart,markerOptions,markerOptions1,markerOptions2,markerOptions3;
    Marker markerstart, marker1,marker_2,marker_3,marker_4,marker_5;
    public boolean active2Fairway;
    private ArrayList<LatLng> arrayPoints = null;
    Button headerLeft_button;


    private List<ItemSlideMenu> listSliding;
    private SlidingMenuAdapter adapter;
    private ListView listViewSliding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout  m_drawerLayout;
    Button menu;

    TextView GolfcourseName;
    TextView  holeNumber, previousDist, currentDist, nextDist;
    Button leftArrow, rightArrow;
    float x1,x2;
    int holeNumVar = 0;
    Button buttonReset;
    LinearLayout buttonScore,buttonshot;
    float ZoomValue = 0;
    int parEachHole[] = {4,4,3,5,4,4,4,4,4,5,3,4,3,4,3,4,4,4};
    TextView parGps;
    private static final LocationRequest REQUEST = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    double HoleLatitude[];
    double HoleLongitude[];
    String sideFields[];
    double sideFieldLatitude[];
    double sideFieldLongitude[];

    int indexOffield = 0;

    String hole1[] = {"Fairway","Center Green","Front Green","Back Green"};
    String hole2[] = {"Fairway","Center Green","Front Green","Back Green"};
    String hole3[] = {"Center Green","Front Green","Back Green"};
    String hole4[] = {"Fairway","Lay Up","Center Green","Front Green","Back Green"};

    double hole1Latitude[] ={33.613446,33.612147,33.612267,33.612035};
    double hole2Latitude[] ={33.612016,33.612049,33.61203,33.612058};
    double hole3Latitude[] ={33.611054,33.611156,33.610962};
    double hole4Latitude[] ={33.60852,33.607446,33.606536,33.606672,33.606403};

    double hole1Longitude[] ={-111.852253,-111.852337,-111.852326,-111.852344};
    double hole2Longitude[] ={-111.854157,-111.855452,-111.855345,-111.855554};
    double hole3Longitude[] ={-111.857105,-111.857239,-111.856988};
    double hole4Longitude[] ={-111.855355,-111.854411,-111.854028,-111.854093,-111.853928};

    public List<GolfHoleField> golfholefieldList = new ArrayList<>();
    private RecyclerView recyclerView;
    private GolfHoleFieldAdaptor mAdapter;
    int restrictLR = 0;
    boolean deActive1fairway,deActive2fairway;
    Button Zoom_Inout;
    boolean zoominoutActive;

    public MapsActivity()
    {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        setContentView(R.layout.activity_maps);


        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.header_startround);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
      //.  Toast.makeText(this, "Hold and move cursor to specific position.", Toast.LENGTH_LONG).show();
        menu = (Button)findViewById(R.id.menubuttonSR);
        SharedPreferences sharedpresetMap= getSharedPreferences("profileset", MODE_PRIVATE);
        holeNumVar = sharedpresetMap.getInt("holeno",0);
        restrictLR = sharedpresetMap.getInt("restrictLR",0);
        InitSlidingMenu();
        menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Opens the Drawer
                if(drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.setVisibility(View.VISIBLE);
                    drawerLayout.closeDrawer(Gravity.RIGHT);

                }
                else
                {
                    drawerLayout.setVisibility(View.VISIBLE);

                    drawerLayout.openDrawer(Gravity.RIGHT);

                }
            }


        });
        mapFragment = (TouchableSupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        buildGoogleApiClient();
        ViewGroup.MarginLayoutParams params1 = (ViewGroup.MarginLayoutParams) mapFragment.getView().getLayoutParams();
        params1.setMargins(dpToPx(110),0,0,0);
        mapFragment.getView().requestLayout();
        mapFragment.getView().setLayoutParams(params1);

        final Button buttonReset = (Button) findViewById(R.id.buttonresetmap);
        headerLeft_button = (Button) findViewById(R.id.header_left_btn);
        GolfcourseName = (TextView) findViewById(R.id.idGolfCourseName);
        SharedPreferences sharedpreferencesRS= getSharedPreferences("roundsetting", MODE_PRIVATE);
        GolfcourseName.setText(""+sharedpreferencesRS.getString("courseName", ""));
        parGps = (TextView) findViewById(R.id.pargps);

        leftArrow = (Button) findViewById(R.id.mapleftarrow);
        rightArrow = (Button) findViewById(R.id.maprightarrow);
        holeNumber = (TextView) findViewById(R.id.mapholeNumber);

        buttonScore = (LinearLayout) findViewById(R.id.LINEARSCOREMAPVIEW);
        buttonshot = (LinearLayout) findViewById(R.id.LINEARSHOTSMAPVIEW);

        Zoom_Inout  = (Button) findViewById(R.id.zoomup);

        Zoom_Inout.setVisibility(View.VISIBLE);
        FrameLayout.LayoutParams paramsbutoon = new FrameLayout.LayoutParams(
                dpToPx(40),
                dpToPx(40)
        );
        paramsbutoon.setMargins(dpToPx(120), 0, 0, dpToPx(30));
        paramsbutoon.gravity = Gravity.BOTTOM |Gravity.RIGHT;
        Zoom_Inout.setLayoutParams(paramsbutoon);
        previousDist = (TextView) findViewById(R.id.mapdistprev);
        currentDist = (TextView) findViewById(R.id.mapdiscurrent);
        nextDist = (TextView) findViewById(R.id.mapdistnext);
         dialog=new ProgressDialog(MapsActivity.this,R.style.MyAlertDialogStyle);
        dialog.setProgressStyle(android.R.style.Widget_Holo_ProgressBar_Large);
      // dialog.setMessage("Loading...");

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.x = dpToPx(60);
        dialog.getWindow().setGravity(Gravity.CENTER);
       // dialog.getWindow().setAttributes(params);
        dialog.setCancelable(false);
       // dialog.setInverseBackgroundForced(false);
        dialog.show();

        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            Toast.makeText(this, "Check Internet connection!", Toast.LENGTH_LONG).show();
            dialog.hide();
        }
        holeNumber.setText("" + (holeNumVar + 1));

        parGps.setText(""+parEachHole[holeNumVar]);
        //lIST VIEW FOR GOLFCOURSES
        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewhole);

        mAdapter = new GolfHoleFieldAdaptor(golfholefieldList,this);
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
                        GolfHoleField golfHoleField = golfholefieldList.get(position);
                      indexOffield = position;
                        zoominoutActive = false;
                        mMap.getUiSettings().setScrollGesturesEnabled(false);

                        // Toast.makeText(getApplicationContext(), golfname.getCourse() + " is selected!", Toast.LENGTH_SHORT).show();
                        if(parEachHole[holeNumVar] == 5 )
                        {
                            if(position == 0)
                            {
                                setMapPointsFor2Fairway(new LatLng(HoleLatitude[2], HoleLongitude[2]),new LatLng(HoleLatitude[3], HoleLongitude[3]));

                            }
                            else
                            {
                                setMapPoints(new LatLng(sideFieldLatitude[position], sideFieldLongitude[position]));
                            }
                        }
                        else
                          setMapPoints(new LatLng(sideFieldLatitude[position], sideFieldLongitude[position]));

                        Log.i("button position","position ###############"+position);
                        // TODO Auto-generated method stub


                    }
                })
        );

        leftArrow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (holeNumVar > 0)
                    holeNumVar--;


                holeNumber.setText("" + (holeNumVar + 1));
                parGps.setText(""+parEachHole[holeNumVar]);
                Log.i("value", "holeNumVar_Left=============================" + holeNumVar);
                Log.i("value", "restrictLR=============================" + restrictLR);
                if (holeNumVar >= 0 && restrictLR != holeNumVar) {
                    Intent nextScreen = new Intent(MapsActivity.this, MapsActivity.class);
                    restrictLR = holeNumVar;
                    SharedPreferences sharedpresetMap= getSharedPreferences("profileset", MODE_PRIVATE);
                    SharedPreferences.Editor editorMap = sharedpresetMap.edit();
                    editorMap.putInt("restrictLR",restrictLR);
                    editorMap.putInt("holeno",holeNumVar);
                    editorMap.commit();
                    Log.i("value", "holeNumVar_Left inner=============================" + holeNumVar);
                    Log.i("value", "restrictLR inner=============================" + restrictLR);
                    startActivity(nextScreen);
                    finish();
                }



            }
        });

        rightArrow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (holeNumVar < 17)
                    holeNumVar++;

                holeNumber.setText("" + (holeNumVar + 1));
                parGps.setText(""+parEachHole[holeNumVar]);
                if (holeNumVar != 17 && restrictLR != holeNumVar) {
                    Intent nextScreen = new Intent(MapsActivity.this, MapsActivity.class);
                    restrictLR = holeNumVar;
                    SharedPreferences sharedpresetMap= getSharedPreferences("profileset", MODE_PRIVATE);
                    SharedPreferences.Editor editorMap = sharedpresetMap.edit();
                    editorMap.putInt("restrictLR",restrictLR);
                    editorMap.putInt("holeno",holeNumVar);
                    editorMap.commit();

                    startActivity(nextScreen);
                    finish();
                }



            }
        });


        Zoom_Inout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
              if(!zoominoutActive)
              {

                  Zoom_Inout.setBackgroundResource(R.drawable.searchout);
               //   mMap.getUiSettings().setScrollGesturesEnabled(true);
                  if(fixPoint.latitude == Distance.latitude && fixPoint.longitude == Distance.longitude)
                  {
                      LatLngBounds.Builder builder = new LatLngBounds.Builder();

                      builder.include(curLocation).include(Distance);
                      LatLngBounds bounds = builder.build();
                      recyclerView.setVisibility(View.GONE);
                      buttonReset.setVisibility(View.GONE);
                      ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mapFragment.getView().getLayoutParams();
                      params.setMargins(0,0,0,0);
                      mapFragment.getView().requestLayout();
                      mapFragment.getView().setLayoutParams(params);
                      FrameLayout.LayoutParams paramsbutoon = new FrameLayout.LayoutParams(
                              dpToPx(30),
                              dpToPx(30)
                      );
                      paramsbutoon.setMargins(dpToPx(10), 0, 0, dpToPx(30));
                      paramsbutoon.gravity = Gravity.BOTTOM |Gravity.RIGHT;
                      Zoom_Inout.setLayoutParams(paramsbutoon);
                      mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), ZoomValue+0.2f));

                  }
                  else {

                      LatLngBounds.Builder builder = new LatLngBounds.Builder();

                      if(parEachHole[holeNumVar] == 5 )
                      {
                          if(deActive1fairway == false)
                          {
                              builder.include(marker_3.getPosition()).include(Distance);
                          }
                          else
                          {
                              builder.include(fixPoint).include(Distance);
                          }
                      }
                      else {
                          builder.include(fixPoint).include(Distance);

                      }
                      LatLngBounds bounds = builder.build();
                      recyclerView.setVisibility(View.GONE);
                      buttonReset.setVisibility(View.GONE);
                      ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mapFragment.getView().getLayoutParams();
                      params.setMargins(0,0,0,0);
                      mapFragment.getView().requestLayout();
                      mapFragment.getView().setLayoutParams(params);
                      FrameLayout.LayoutParams paramsbutoon = new FrameLayout.LayoutParams(
                              dpToPx(40),
                              dpToPx(40)
                      );
                      paramsbutoon.setMargins(dpToPx(10), 0, 0, dpToPx(30));
                      paramsbutoon.gravity = Gravity.BOTTOM |Gravity.RIGHT;
                      Zoom_Inout.setLayoutParams(paramsbutoon);
                      if(parEachHole[holeNumVar] == 5 )
                      {
                          if(deActive1fairway == false)
                          {
                              marker_5.setVisible(false);
                              if((Math.round((distance(marker_3.getPosition().latitude, marker_3.getPosition().longitude, Distance.latitude, Distance.longitude)))<=
                                      Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/4)&&
                                      (Math.round((distance(marker_3.getPosition().latitude, marker_3.getPosition().longitude, Distance.latitude, Distance.longitude)))>
                                              0)) {
                                  mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), ZoomValue + 2.0f));
                              }
                              else
                              if((Math.round((distance(marker_3.getPosition().latitude, marker_3.getPosition().longitude, Distance.latitude, Distance.longitude)))<=
                                      Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/3)&&
                                      (Math.round((distance(marker_3.getPosition().latitude, marker_3.getPosition().longitude, Distance.latitude, Distance.longitude)))>
                                              Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/4)) {
                                  mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), ZoomValue + 1.7f));
                              }
                              else
                              if((Math.round((distance(marker_3.getPosition().latitude, marker_3.getPosition().longitude, Distance.latitude, Distance.longitude)))<
                                      Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/2)&&
                                      (Math.round((distance(marker_3.getPosition().latitude, marker_3.getPosition().longitude, Distance.latitude, Distance.longitude)))>
                                              Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/3)) {
                                  mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), ZoomValue + 1.2f));
                              }
                              else
                              if((Math.round((distance(marker_3.getPosition().latitude, marker_3.getPosition().longitude, Distance.latitude, Distance.longitude)))<
                                      4*Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/5)&&
                                      (Math.round((distance(marker_3.getPosition().latitude, marker_3.getPosition().longitude, Distance.latitude, Distance.longitude)))>
                                              Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/2)) {
                                  mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), ZoomValue + 0.6f));
                              }
                              else
                              if((Math.round((distance(marker_3.getPosition().latitude, marker_3.getPosition().longitude, Distance.latitude, Distance.longitude)))<
                                      Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude))))&&
                                      (Math.round((distance(marker_3.getPosition().latitude, marker_3.getPosition().longitude, Distance.latitude, Distance.longitude)))>
                                              4* Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/5)) {
                                  mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), ZoomValue + 0.3f));
                              }
                          }
                          else
                          {
                              marker_5.setVisible(false);
                              marker_2.setVisible(false);
                              if((Math.round((distance(fixPoint.latitude, fixPoint.longitude, Distance.latitude, Distance.longitude)))<=
                                      Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/4)&&
                                      (Math.round((distance(fixPoint.latitude, fixPoint.longitude, Distance.latitude, Distance.longitude)))>
                                              0)) {
                                  mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), ZoomValue + 2.0f));
                              }
                              else
                              if((Math.round((distance(fixPoint.latitude, fixPoint.longitude, Distance.latitude, Distance.longitude)))<=
                                      Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/3)&&
                                      (Math.round((distance(fixPoint.latitude, fixPoint.longitude, Distance.latitude, Distance.longitude)))>
                                              Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/4)) {
                                  mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), ZoomValue + 1.7f));
                              }
                              else
                              if((Math.round((distance(fixPoint.latitude, fixPoint.longitude, Distance.latitude, Distance.longitude)))<
                                      Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/2)&&
                                      (Math.round((distance(fixPoint.latitude, fixPoint.longitude, Distance.latitude, Distance.longitude)))>
                                              Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/3)) {
                                  mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), ZoomValue + 1.2f));
                              }
                              else
                              if((Math.round((distance(fixPoint.latitude, fixPoint.longitude, Distance.latitude, Distance.longitude)))<
                                      4*Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/5)&&
                                      (Math.round((distance(fixPoint.latitude, fixPoint.longitude, Distance.latitude, Distance.longitude)))>
                                              Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/2)) {
                                  mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), ZoomValue + 0.6f));
                              }
                              else
                              if((Math.round((distance(fixPoint.latitude, fixPoint.longitude, Distance.latitude, Distance.longitude)))<
                                      Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude))))&&
                                      (Math.round((distance(fixPoint.latitude, fixPoint.longitude, Distance.latitude, Distance.longitude)))>
                                              4* Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/5)) {
                                  mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), ZoomValue + 0.3f));
                              }
                          }
                      }
                      else {
                          marker_2.setVisible(false);
                          if((Math.round((distance(fixPoint.latitude, fixPoint.longitude, Distance.latitude, Distance.longitude)))<=
                                  Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/4)&&
                                  (Math.round((distance(fixPoint.latitude, fixPoint.longitude, Distance.latitude, Distance.longitude)))>
                                          0)) {
                              mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), ZoomValue + 2.0f));
                          }
                          else
                          if((Math.round((distance(fixPoint.latitude, fixPoint.longitude, Distance.latitude, Distance.longitude)))<=
                                  Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/3)&&
                                  (Math.round((distance(fixPoint.latitude, fixPoint.longitude, Distance.latitude, Distance.longitude)))>
                                          Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/4)) {
                              mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), ZoomValue + 1.7f));
                          }
                          else
                          if((Math.round((distance(fixPoint.latitude, fixPoint.longitude, Distance.latitude, Distance.longitude)))<
                                  Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/2)&&
                                  (Math.round((distance(fixPoint.latitude, fixPoint.longitude, Distance.latitude, Distance.longitude)))>
                                          Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/3)) {
                              mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), ZoomValue + 1.2f));
                          }
                          else
                          if((Math.round((distance(fixPoint.latitude, fixPoint.longitude, Distance.latitude, Distance.longitude)))<
                                  4*Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/5)&&
                                  (Math.round((distance(fixPoint.latitude, fixPoint.longitude, Distance.latitude, Distance.longitude)))>
                                          Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/2)) {
                              mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), ZoomValue + 0.6f));
                          }
                          else
                          if((Math.round((distance(fixPoint.latitude, fixPoint.longitude, Distance.latitude, Distance.longitude)))<
                                  Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude))))&&
                                  (Math.round((distance(fixPoint.latitude, fixPoint.longitude, Distance.latitude, Distance.longitude)))>
                                         4* Math.round((distance(curLocation.latitude, curLocation.longitude, Distance.latitude, Distance.longitude)))/5)) {
                              mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), ZoomValue + 0.3f));
                          }

                      }

                      if(line !=null)
                          line.setVisible(false);
                  }



                /*  if(line1 !=null)
                    line1.setVisible(false);
                  if(line2 !=null)
                    line2.setVisible(false);*/
                  zoominoutActive = true;
              }
              else
              {
                  Zoom_Inout.setBackgroundResource(R.drawable.search);

                  mMap.getUiSettings().setScrollGesturesEnabled(false);
                  LatLngBounds.Builder builder = new LatLngBounds.Builder();
                  builder.include(curLocation).include(Distance);
                  LatLngBounds bounds = builder.build();
                  recyclerView.setVisibility(View.VISIBLE);
                  buttonReset.setVisibility(View.VISIBLE);
                  ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mapFragment.getView().getLayoutParams();
                  params.setMargins(dpToPx(110),0,0,0);
                  mapFragment.getView().requestLayout();
                  mapFragment.getView().setLayoutParams(params);
                  FrameLayout.LayoutParams paramsbutoon = new FrameLayout.LayoutParams(
                          dpToPx(40),
                          dpToPx(40)
                  );
                  paramsbutoon.setMargins(dpToPx(120), 0, 0, dpToPx(30));
                  paramsbutoon.gravity = Gravity.BOTTOM |Gravity.RIGHT;
                  Zoom_Inout.setLayoutParams(paramsbutoon);
                  mapFragment.getView().requestLayout();
                  mapFragment.getView().setLayoutParams(params);
                  mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(),ZoomValue));
                  if(parEachHole[holeNumVar] == 5 )
                  {
                      if(deActive1fairway == false)
                      {
                          marker_5.setVisible(true);

                      }
                      else {
                          marker_5.setVisible(true);
                          marker_2.setVisible(true);
                      }

                  }
                  else {
                      marker_2.setVisible(true);

                  }



                  if(line !=null)
                      line.setVisible(true);
                  if(line1 !=null)
                      line1.setVisible(true);
                  if(line2 !=null)
                      line2.setVisible(true);

                  zoominoutActive = false;
              }

            }
        });

        headerLeft_button.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Bundle bundle = new Bundle();

                    bundle.putString("SCREENNAME","FROMMAPACTIVITY");
                    Intent nextScreen = new Intent(MapsActivity.this, ScoreCard.class);
                    nextScreen.putExtras(bundle);
                    startActivity(nextScreen);
                    finish();
                }
                return false;
            }

        });




        final Typeface font1 = AppFont.setFont(this,AppFont.ROBOTO_REGULAR);

        TextView headerText = (TextView) findViewById(R.id.idGolfCourseName);
        headerText. setTypeface(font1);


        buttonScore.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    buttonScore.setBackgroundColor(Color.parseColor("#51677e"));

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonScore.setBackgroundColor(Color.parseColor("#10171f"));
                    SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                    editor.putBoolean("scoreshot", false);
                    editor.commit();
                    Intent nextScreen = new Intent(MapsActivity.this, StartRound.class);
                    startActivity(nextScreen);
                    finish();
                }
                return false;
            }

        });
        buttonshot.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    buttonScore.setBackgroundColor(Color.parseColor("#51677e"));

                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonshot.setBackgroundColor(Color.parseColor("#10171f"));
                    SharedPreferences sharedpreferencesCLUB = getSharedPreferences("CLUBSELECT", MODE_PRIVATE);

                    SharedPreferences.Editor editor = sharedpreferencesCLUB.edit();
                    editor.putBoolean("scoreshot", true);
                    editor.commit();
                    Intent nextScreen = new Intent(MapsActivity.this, StartRound.class);
                    startActivity(nextScreen);
                    finish();
                }
                return false;
            }

        });

        mapFragment.getMapAsync(this);
        GoogleMapOptions options = new GoogleMapOptions().liteMode(true);

        buttonReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                mAdapter.setView();
                if(parEachHole[holeNumVar] == 5)
                {
                    indexOffield = 0;
                    setMapPointsFor2Fairway(new LatLng(HoleLatitude[2], HoleLongitude[2]),new LatLng(HoleLatitude[3], HoleLongitude[3]));
                }
                else
                    setMapPoints(new LatLng(sideFieldLatitude[0], sideFieldLongitude[0]));                           // setMapPoints(new LatLng(18.555160987218603,73.88961363583803));


            }
        });


       // Setting a custom info window adapter for the google map



    }


    private TouchableSupportMapFragment getMap() {
        return ((TouchableSupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map));
    }


    public void setMapPoints(LatLng point)
    {

        mMap.clear();
        fixPoint = point;
        // mMap.clear();
        marker=new MarkerOptions();
        marker.position(point);
      //  Toast.makeText(this,"Latitude update:: "+point.latitude+" /longitude update:: "+point.longitude, Toast.LENGTH_LONG).show();
        Log.d("dist","Lat #"+point.latitude);
        Log.d("dist","Long #"+point.longitude);

        // Log.d("","Lat ::::::::::"+point.latitude);
        //Log.d("","Long ::::::::::"+point.longitude);
        MarkerOptions marker2=new MarkerOptions();
        marker2.position(new LatLng((curLocation.latitude+point.latitude)/2,(curLocation.longitude+point.longitude)/2));

        mMap.addMarker(new MarkerOptions().position(Distance).title("End point").icon(BitmapDescriptorFactory.fromResource(R.drawable.flag)));
        markerOptionsstart = new MarkerOptions().position(curLocation).title("start point").icon(BitmapDescriptorFactory.fromResource(R.drawable.golfball)).anchor(0.5f,0.5f);
        markerstart = mMap.addMarker(markerOptionsstart);

        // settin polyline in the map
        marker_3 = mMap.addMarker(marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mid)).anchor(0.5f,0.6f).draggable(true));
        marker_3.setAlpha(2);
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(curLocation).include(Distance);
        LatLngBounds bounds = builder.build();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(),ZoomValue));

        double dist1 = distance(curLocation.latitude,curLocation.longitude,point.latitude,point.longitude);
        double dist2 = distance(point.latitude,point.longitude,Distance.latitude,Distance.longitude);

        Log.i("value","dist1#########################"+dist1);
        Log.i("value","dist2#########################"+dist2);
        // Long L = Math.round(dist);
        //int d = Integer.valueOf(L.intValue());

        Long L1 = Math.round(dist1);
        final int d1 = Integer.valueOf(L1.intValue());

        Long L2 = Math.round(dist2);
        final int d2 = Integer.valueOf(L2.intValue());
        //################RND##############



        markerOptions = new MarkerOptions()
                .position(new LatLng((Distance.latitude+point.latitude)/2,(Distance.longitude+point.longitude)/2))
                .icon(BitmapDescriptorFactory.fromBitmap(drawTextToBitmap(R.drawable.bluebg,""+d2,""))).anchor(0.5f,0.5f);
                //.icon(BitmapDescriptorFactory.fromBitmap(drawTextToBitmap(R.drawable.bluebg,""+d2,"7 IRON")));

           marker1 = mMap.addMarker(markerOptions);
        if(d2>20)
            marker1.setVisible(true);
        else
            marker1.setVisible(false);
        markerOptions1 = new MarkerOptions()
                .position(new LatLng((curLocation.latitude+point.latitude)/2,(curLocation.longitude+point.longitude)/2))
                .icon(BitmapDescriptorFactory.fromBitmap(drawTextToBitmap(R.drawable.greenbg,""+d1,""))).anchor(0.5f,0.5f);
                //.icon(BitmapDescriptorFactory.fromBitmap(drawTextToBitmap(R.drawable.greenbg,""+d1,"DRIVER")));
        marker_2 = mMap.addMarker(markerOptions1);

        line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(curLocation.latitude, curLocation.longitude),
                        new LatLng(point.latitude, point.longitude)).add()
                .width(4).color(Color.WHITE).geodesic(true));

        line1 = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(point.latitude, point.longitude),
                        new LatLng(Distance.latitude, Distance.longitude)).add()
                .width(4).color(Color.WHITE).geodesic(true));
    }


    public void setMapPointsFor2Fairway(LatLng point,LatLng point2)
    {

        mMap.clear();
        fixPoint = point;
        // mMap.clear();
        marker=new MarkerOptions();
        marker.position(point);
        markerOptions2=new MarkerOptions();
        markerOptions2.position(point2);


        //  Toast.makeText(this,"Latitude update:: "+point.latitude+" /longitude update:: "+point.longitude, Toast.LENGTH_LONG).show();
        Log.d("dist","Lat #"+point.latitude);
        Log.d("dist","Long #"+point.longitude);

        // Log.d("","Lat ::::::::::"+point.latitude);
        //Log.d("","Long ::::::::::"+point.longitude);

        mMap.addMarker(new MarkerOptions().position(Distance).title("End point").icon(BitmapDescriptorFactory.fromResource(R.drawable.flag)));
        markerOptionsstart = new MarkerOptions().position(curLocation).title("start point").icon(BitmapDescriptorFactory.fromResource(R.drawable.golfball)).anchor(0.5f,0.5f);
        markerstart = mMap.addMarker(markerOptionsstart);

        // settin polyline in the map
        marker_3 = mMap.addMarker(marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mid)).anchor(0.5f,0.6f).draggable(true));
        marker_3.setAlpha(2);
        marker_4 = mMap.addMarker(markerOptions2.icon(BitmapDescriptorFactory.fromResource(R.drawable.mid)).anchor(0.5f,0.6f).draggable(true));
        marker_4.setAlpha(2);
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(curLocation).include(Distance);
        LatLngBounds bounds = builder.build();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(),ZoomValue));

        double dist1 = distance(curLocation.latitude,curLocation.longitude,point.latitude,point.longitude);
        double dist2 = distance(point.latitude,point.longitude,point2.latitude,point2.longitude);
        double dist3 = distance(point2.latitude,point2.longitude,Distance.latitude,Distance.longitude);

        Log.i("value","dist1#########################"+dist1);
        Log.i("value","dist2#########################"+dist2);
        // Long L = Math.round(dist);
        //int d = Integer.valueOf(L.intValue());

        Long L1 = Math.round(dist1);
        final int d1 = Integer.valueOf(L1.intValue());

        Long L2 = Math.round(dist2);
        final int d2 = Integer.valueOf(L2.intValue());
        Long L3 = Math.round(dist3);
        final int d3 = Integer.valueOf(L3.intValue());
        //################RND##############



        markerOptions = new MarkerOptions()
                .position(new LatLng((Distance.latitude+point2.latitude)/2,(Distance.longitude+point2.longitude)/2))
                .icon(BitmapDescriptorFactory.fromBitmap(drawTextToBitmap(R.drawable.bluebg,""+d3,""))).anchor(0.5f,0.5f);
        //.icon(BitmapDescriptorFactory.fromBitmap(drawTextToBitmap(R.drawable.bluebg,""+d2,"7 IRON")));

        marker1 = mMap.addMarker(markerOptions);
        if(d3>20)
            marker1.setVisible(true);
        else
            marker1.setVisible(false);
        markerOptions1 = new MarkerOptions()
                .position(new LatLng((point.latitude+point2.latitude)/2,(point.longitude+point2.longitude)/2))
                .icon(BitmapDescriptorFactory.fromBitmap(drawTextToBitmap(R.drawable.bluebg,""+d2,""))).anchor(0.5f,0.5f);
        //.icon(BitmapDescriptorFactory.fromBitmap(drawTextToBitmap(R.drawable.greenbg,""+d1,"DRIVER")));
        marker_2 = mMap.addMarker(markerOptions1);

        markerOptions3 = new MarkerOptions()
                .position(new LatLng((curLocation.latitude+point.latitude)/2,(curLocation.longitude+point.longitude)/2))
                .icon(BitmapDescriptorFactory.fromBitmap(drawTextToBitmap(R.drawable.greenbg,""+d1,""))).anchor(0.5f,0.5f);
        //.icon(BitmapDescriptorFactory.fromBitmap(drawTextToBitmap(R.drawable.greenbg,""+d1,"DRIVER")));
        marker_5 = mMap.addMarker(markerOptions3);

        if(deActive1fairway || deActive2fairway)
        {
            marker_5.setVisible(false);
            marker_3.setVisible(false);
            marker_2.setPosition(new LatLng((curLocation.latitude+marker_4.getPosition().latitude)/2,(curLocation.longitude+marker_4.getPosition().longitude)/2));
            line2.remove();
            line = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(curLocation.latitude, curLocation.longitude),
                            new LatLng(marker_4.getPosition().latitude, marker_4.getPosition().longitude)).add()
                    .width(4).color(Color.WHITE).geodesic(true));

            line1 = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(marker_4.getPosition().latitude, marker_4.getPosition().longitude),
                            new LatLng(Distance.latitude, Distance.longitude)).add()
                    .width(4).color(Color.WHITE).geodesic(true));

        }
        else {

            line = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(curLocation.latitude, curLocation.longitude),
                            new LatLng(marker_3.getPosition().latitude, marker_3.getPosition().longitude)).add()
                    .width(4).color(Color.WHITE).geodesic(true));

            line1 = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(marker_3.getPosition().latitude, marker_3.getPosition().longitude),
                            new LatLng(marker_4.getPosition().latitude, marker_4.getPosition().longitude)).add()
                    .width(4).color(Color.WHITE).geodesic(true));

            line2 = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(point2.latitude, point2.longitude),
                            new LatLng(Distance.latitude, Distance.longitude)).add()
                    .width(4).color(Color.WHITE).geodesic(true));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setLocationSource(this);
        mMap.setOnMapLoadedCallback(this);
        mMap.setOnMarkerClickListener(this);

    if(holeNumVar == 0) {
        HoleLatitude = null;
        HoleLongitude = null;
        HoleLatitude = new double[5];
        HoleLongitude =new double[5];

      HoleLatitude[0] = 33.61568;
        HoleLatitude[1] = 33.612147;
        HoleLatitude[2] = 33.613446;
        HoleLatitude[3] = 33.612267;
        HoleLatitude[4] = 33.612035;

        HoleLongitude[0] = -111.852076;
        HoleLongitude[1] = -111.852337;
        HoleLongitude[2] = -111.852253;
        HoleLongitude[3] = -111.852326;
        HoleLongitude[4] = -111.852344;


         /* HoleLatitude[0] = 18.53508422755573;
        HoleLatitude[1] = 18.531024468987848;
        HoleLatitude[2] = 18.532460383968125;
        HoleLatitude[3] = 18.531024468987848;
        HoleLatitude[4] = 18.531024468987848;

        HoleLongitude[0] = 73.8970185443759;
        HoleLongitude[1] = 73.89663901180029;
        HoleLongitude[2] = 73.89731124043465;
        HoleLongitude[3] = 73.89663901180029;
        HoleLongitude[4] = 73.89663901180029;*/

        sideFields = hole1;
        sideFieldLatitude = hole1Latitude;
        sideFieldLongitude = hole1Longitude;

        ZoomValue = 16.8f;
    }
    else
    if(holeNumVar == 1)
    {
        HoleLatitude = null;
        HoleLongitude = null;
        HoleLatitude = new double[5];
        HoleLongitude =new double[5];
        HoleLatitude[0] = 33.61175;
        HoleLatitude[1] = 33.612049;
        HoleLatitude[2] = 33.612016;
        HoleLatitude[3] = 33.61203;
        HoleLatitude[4] = 33.612058;

        HoleLongitude[0] = -111.851903;
        HoleLongitude[1] = -111.855452;
        HoleLongitude[2] = -111.854157;
        HoleLongitude[3] = -111.855345;
        HoleLongitude[4] = -111.855554;

        sideFields = hole2;
        sideFieldLatitude = hole2Latitude;
        sideFieldLongitude = hole2Longitude;

        ZoomValue = 17.0f;
    }
    else
    if(holeNumVar == 2)
    {
        HoleLatitude = null;
        HoleLongitude = null;
        HoleLatitude = new double[5];
        HoleLongitude =new double[5];
        HoleLatitude[0] = 33.612619;
        HoleLatitude[1] = 33.611054;
        HoleLatitude[2] = 33.611054;
        HoleLatitude[3] = 33.611156;
        HoleLatitude[4] = 33.610962;

        HoleLongitude[0] = -111.858302;
        HoleLongitude[1] = -111.857105;
        HoleLongitude[2] = -111.857105;
        HoleLongitude[3] = -111.857239;
        HoleLongitude[4] = -111.856988;

        sideFields = hole3;
        sideFieldLatitude = hole3Latitude;
        sideFieldLongitude = hole3Longitude;

        ZoomValue = 17.6f;
    }
    else
    if(holeNumVar == 3)
    {
        HoleLatitude = null;
        HoleLongitude = null;
        HoleLatitude = new double[6];
        HoleLongitude =new double[6];

        HoleLatitude[0] = 33.61055;
        HoleLatitude[1] = 33.606536;
        HoleLatitude[2] = 33.60852;
        HoleLatitude[3] = 33.607446;
        HoleLatitude[4] = 33.606672;
        HoleLatitude[5] = 33.606403;

        HoleLongitude[0] = -111.856909;
        HoleLongitude[1] = -111.854028;
        HoleLongitude[2] = -111.855355;
        HoleLongitude[3] = -111.854411;
        HoleLongitude[4] = -111.854093;
        HoleLongitude[5] = -111.853928;

        sideFields = hole4;
        sideFieldLatitude = hole4Latitude;
        sideFieldLongitude = hole4Longitude;

        ZoomValue = 16.4f;
    }
    else
    {
        HoleLatitude = null;
        HoleLongitude = null;
        HoleLatitude = new double[5];
        HoleLongitude =new double[5];
        HoleLatitude[0] = 33.61055;
        HoleLatitude[1] = 33.606536;
        HoleLatitude[2] = 33.60852;
        HoleLatitude[3] = 33.606672;
        HoleLatitude[4] = 33.606403;

        HoleLongitude[0] = -111.856909;
        HoleLongitude[1] = -111.854028;
        HoleLongitude[2] = -111.855355;
        HoleLongitude[3] = -111.854093;
        HoleLongitude[4] = -111.853928;

        sideFields = hole4;
        sideFieldLatitude = hole4Latitude;
        sideFieldLongitude = hole4Longitude;

        ZoomValue = 16.4f;

    }

        curLocation = new LatLng(HoleLatitude[0], HoleLongitude[0]);
        Distance = new LatLng(HoleLatitude[1], HoleLongitude[1]);//will change updated from server db
        preparegolfHoleFieldData();
        if(parEachHole[holeNumVar] == 5)
        {
            setMapPointsFor2Fairway(new LatLng(HoleLatitude[2], HoleLongitude[2]),new LatLng(HoleLatitude[3], HoleLongitude[3]));


        }else
           setMapPoints(new LatLng(HoleLatitude[2], HoleLongitude[2]));
        if(parEachHole[holeNumVar] == 5) {
            previousDist.setText("" + Math.round((distance(curLocation.latitude, curLocation.longitude, HoleLatitude[4], HoleLongitude[4]))));
            currentDist.setText("" + Math.round((distance(curLocation.latitude, curLocation.longitude, HoleLatitude[1], HoleLongitude[1]))));
            nextDist.setText("" + Math.round((distance(curLocation.latitude, curLocation.longitude, HoleLatitude[5], HoleLongitude[5]))));
        }
        else {
            previousDist.setText("" + Math.round((distance(curLocation.latitude, curLocation.longitude, HoleLatitude[3], HoleLongitude[3]))));
            currentDist.setText("" + Math.round((distance(curLocation.latitude, curLocation.longitude, HoleLatitude[1], HoleLongitude[1]))));
            nextDist.setText("" + Math.round((distance(curLocation.latitude, curLocation.longitude, HoleLatitude[4], HoleLongitude[4]))));
        }

       /* mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                return true;
            }
        });*/
        mMap.getUiSettings().setAllGesturesEnabled(false);
        mMap.getUiSettings().setScrollGesturesEnabled(false);
        mMap.getUiSettings().setCompassEnabled(false);
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.getUiSettings().setIndoorLevelPickerEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(false);
        mMap.getUiSettings().setTiltGesturesEnabled(false);
        mapFragment.setOnDragListener(new TouchableWrapper.OnDragListener() {
            @Override
            public void onDrag(MotionEvent motionEvent) {
                Log.d("ON_DRAG", String.format("ME: %s", motionEvent));

                mAdapter.resetView();
                //float offsetX = motionEvent.getX();
                float offsetX = 0;
                float offsetY = motionEvent.getY();
                if(zoominoutActive) {
                    offsetX = motionEvent.getX();
                    if(offsetX >Zoom_Inout.getX() && offsetX <(Zoom_Inout.getX()+Zoom_Inout.getWidth())
                            && offsetY > Zoom_Inout.getY() && offsetY < (Zoom_Inout.getY()+Zoom_Inout.getHeight()))
                        return;
                }
                else {
                    offsetX = motionEvent.getX() - dpToPx(110);
                    if(offsetX >(Zoom_Inout.getX()- dpToPx(110)) && offsetX <(Zoom_Inout.getX()- dpToPx(110)+Zoom_Inout.getWidth())
                            && offsetY > Zoom_Inout.getY() && offsetY < (Zoom_Inout.getY()+Zoom_Inout.getHeight()))
                        return;
                }

                LatLng target = new LatLng(marker.getPosition().latitude,marker.getPosition().longitude);
                Projection projection = mMap.getProjection();
                Point screenLocation = projection.toScreenLocation(target);
                screenLocation.x = (int)offsetX;
                screenLocation.y = (int)offsetY;
                LatLng offsetTarget = projection.fromScreenLocation(screenLocation);
              /*  if(zoominoutActive  )
                {

                    Log.i("value","d1=="+(Math.round(distance(curLocation.latitude,curLocation.longitude,offsetTarget.latitude,offsetTarget.longitude))));
                    Log.i("value","d2=="+(Math.round(distance(Distance.latitude,Distance.longitude,offsetTarget.latitude,offsetTarget.longitude))));
                    Log.i("value","distance=="+(Math.round(distance(curLocation.latitude,curLocation.longitude,Distance.latitude,Distance.longitude))+(Math.round(distance(curLocation.latitude,curLocation.longitude,Distance.latitude,Distance.longitude))/2.5)));

                    if(((Math.round(distance(curLocation.latitude,curLocation.longitude,offsetTarget.latitude,offsetTarget.longitude))+(Math.round(distance(Distance.latitude,Distance.longitude,offsetTarget.latitude,offsetTarget.longitude))))>
                        (Math.round(distance(curLocation.latitude,curLocation.longitude,Distance.latitude,Distance.longitude))+(Math.round(distance(curLocation.latitude,curLocation.longitude,Distance.latitude,Distance.longitude))/2.5)))

                       ) {
                        mMap.getUiSettings().setScrollGesturesEnabled(false);
                        LatLngBounds.Builder builder = new LatLngBounds.Builder();
                        builder.include(fixPoint);
                        LatLngBounds bounds = builder.build();

                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(), ZoomValue+1.2f));
                        return;
                    }
                    else
                    {
                       // mMap.getUiSettings().setScrollGesturesEnabled(true);

                    }
                }*/

                if(parEachHole[holeNumVar] == 5)
                {
                    if(indexOffield == 0)
                    setPoint2Fairway(offsetTarget,active2Fairway);
                    else
                        setPoint1Fairway(offsetTarget);

                }
                else
                   setPoint1Fairway(offsetTarget);
                // Handle motion event:

            }
        });
    }


    @Override
    public boolean onMarkerClick(final Marker marker) {


        return true;
    }
    public void setPoint2Fairway(LatLng offsetTarget,boolean activeFairway)
    {
       // active2Fairway
        fixPoint  = offsetTarget;


         if(!activeFairway)
         {
             if(Math.round(distance(marker_4.getPosition().latitude,marker_4.getPosition().longitude,offsetTarget.latitude,offsetTarget.longitude))<100)
             {
                 active2Fairway = true;
                 return;
             }
                 double dist1 = distance(curLocation.latitude,curLocation.longitude,offsetTarget.latitude,offsetTarget.longitude);
             double dist2 = distance(offsetTarget.latitude,offsetTarget.longitude,marker_4.getPosition().latitude,marker_4.getPosition().longitude);
             Long L1 = Math.round(dist1);
             final int d1 = Integer.valueOf(L1.intValue());

             Long L2 = Math.round(dist2);
             final int d2 = Integer.valueOf(L2.intValue());
             marker_3.setPosition(new LatLng((offsetTarget.latitude),(offsetTarget.longitude)));
             marker_2.setPosition(new LatLng((marker_4.getPosition().latitude+offsetTarget.latitude)/2,(marker_4.getPosition().longitude+offsetTarget.longitude)/2));
             marker_5.setPosition(new LatLng((curLocation.latitude+offsetTarget.latitude)/2,(curLocation.longitude+offsetTarget.longitude)/2));
             marker_2.setIcon(BitmapDescriptorFactory.fromBitmap(drawTextToBitmap(R.drawable.bluebg,""+d2,"")));
            // marker1.setIcon(BitmapDescriptorFactory.fromBitmap(drawTextToBitmap(R.drawable.bluebg,""+d2,"7 IRON")));
             marker_5.setIcon(BitmapDescriptorFactory.fromBitmap(drawTextToBitmap(R.drawable.greenbg,""+d1,"")));
             line.remove();
             line1.remove();
             line = mMap.addPolyline(new PolylineOptions()
                   .add(new LatLng(curLocation.latitude, curLocation.longitude),
                    new LatLng(offsetTarget.latitude, offsetTarget.longitude)).add()
                   .width(4).color(Color.WHITE).geodesic(true));

             line1 = mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(offsetTarget.latitude, offsetTarget.longitude),
                    new LatLng(marker_4.getPosition().latitude, marker_4.getPosition().longitude)).add()
                    .width(4).color(Color.WHITE).geodesic(true));
             if(zoominoutActive  )
             {
                 line.setVisible(false);
                  marker_5.setVisible(false);
               //  line1.setVisible(false);
               //  line2.setVisible(false);
             }
             else
             {
                 line.setVisible(true);
                 line2.setVisible(true);
                 marker_5.setVisible(true);


             }
         }
         else
         {
             if(Math.round(distance(marker_3.getPosition().latitude,marker_3.getPosition().longitude,offsetTarget.latitude,offsetTarget.longitude))<100) {
                 active2Fairway = false;
                 return;
             }
             double dist1 = distance(marker_3.getPosition().latitude,marker_3.getPosition().longitude,offsetTarget.latitude,offsetTarget.longitude);
             double dist2 = distance(offsetTarget.latitude,offsetTarget.longitude,Distance.latitude,Distance.longitude);
             Long L1 = Math.round(dist1);
             final int d1 = Integer.valueOf(L1.intValue());

             Long L2 = Math.round(dist2);
             final int d2 = Integer.valueOf(L2.intValue());
             marker_4.setPosition(new LatLng((offsetTarget.latitude),(offsetTarget.longitude)));
             if(deActive1fairway ||deActive2fairway) {
                 marker_2.setPosition(new LatLng((curLocation.latitude + offsetTarget.latitude) / 2, (curLocation.longitude + offsetTarget.longitude) / 2));
             }
             else
             {
                 marker_2.setPosition(new LatLng((marker_3.getPosition().latitude + offsetTarget.latitude) / 2, (marker_3.getPosition().longitude + offsetTarget.longitude) / 2));

             }
             marker1.setPosition(new LatLng((Distance.latitude+offsetTarget.latitude)/2,(Distance.longitude+offsetTarget.longitude)/2));
             marker1.setIcon(BitmapDescriptorFactory.fromBitmap(drawTextToBitmap(R.drawable.bluebg,""+d2,"")));
             // marker1.setIcon(BitmapDescriptorFactory.fromBitmap(drawTextToBitmap(R.drawable.bluebg,""+d2,"7 IRON")));
             marker_2.setIcon(BitmapDescriptorFactory.fromBitmap(drawTextToBitmap(R.drawable.bluebg,""+d1,"")));
             line1.remove();
             line2.remove();

             if(deActive1fairway ||deActive2fairway)
             {
                 line1 = mMap.addPolyline(new PolylineOptions()
                         .add(new LatLng(curLocation.latitude,curLocation.longitude), new LatLng(offsetTarget.latitude, offsetTarget.longitude)
                         ).add()
                         .width(4).color(Color.WHITE).geodesic(true));

                 line2 = mMap.addPolyline(new PolylineOptions()
                         .add(new LatLng(offsetTarget.latitude, offsetTarget.longitude),
                                 new LatLng(Distance.latitude, Distance.longitude)).add()
                         .width(4).color(Color.WHITE).geodesic(true));
                 if(zoominoutActive  )
                 {

                     marker_2.setVisible(false);
                     // line1.setVisible(false);
                     //  line2.setVisible(false);
                 }
                 else
                 {
                     marker_2.setVisible(true);
                 }
             }
             else {
                 line1 = mMap.addPolyline(new PolylineOptions()
                         .add(new LatLng(marker_3.getPosition().latitude, marker_3.getPosition().longitude), new LatLng(offsetTarget.latitude, offsetTarget.longitude)
                         ).add()
                         .width(4).color(Color.WHITE).geodesic(true));

                 line2 = mMap.addPolyline(new PolylineOptions()
                         .add(new LatLng(offsetTarget.latitude, offsetTarget.longitude),
                                 new LatLng(Distance.latitude, Distance.longitude)).add()
                         .width(4).color(Color.WHITE).geodesic(true));
             }
             if(zoominoutActive  )
             {
                 line.setVisible(false);
                 marker_5.setVisible(false);
                // line1.setVisible(false);
               //  line2.setVisible(false);
             }
             else
             {
                 line.setVisible(true);
                 line1.setVisible(true);
                 line2.setVisible(true);
                 marker_5.setVisible(true);


             }

         }


    }

    public void setPoint1Fairway(LatLng offsetTarget)
    {

        fixPoint  = offsetTarget;
    double dist1 = distance(curLocation.latitude,curLocation.longitude,offsetTarget.latitude,offsetTarget.longitude);
    double dist2 = distance(offsetTarget.latitude,offsetTarget.longitude,Distance.latitude,Distance.longitude);

   // Log.i("value","offsetTarget.latitude#########################"+offsetTarget.latitude);
   // Log.i("value","offsetTarget.longitude#########################"+offsetTarget.longitude);


    // Long L = Math.round(dist);
    //int d = Integer.valueOf(L.intValue());

    Long L1 = Math.round(dist1);
    final int d1 = Integer.valueOf(L1.intValue());

    Long L2 = Math.round(dist2);
    final int d2 = Integer.valueOf(L2.intValue());
    if(d2>20)
        marker1.setVisible(true);
    else
        marker1.setVisible(false);

    marker1.setPosition(new LatLng((Distance.latitude+offsetTarget.latitude)/2,(Distance.longitude+offsetTarget.longitude)/2));
    marker_2.setPosition(new LatLng((curLocation.latitude+offsetTarget.latitude)/2,(curLocation.longitude+offsetTarget.longitude)/2));
    marker1.setIcon(BitmapDescriptorFactory.fromBitmap(drawTextToBitmap(R.drawable.bluebg,""+d2,"")));
    // marker1.setIcon(BitmapDescriptorFactory.fromBitmap(drawTextToBitmap(R.drawable.bluebg,""+d2,"7 IRON")));
    marker_2.setIcon(BitmapDescriptorFactory.fromBitmap(drawTextToBitmap(R.drawable.greenbg,""+d1,"")));
    // marker_2.setIcon(BitmapDescriptorFactory.fromBitmap(drawTextToBitmap(R.drawable.greenbg,""+d1,"DRIVER")));
    marker_3.setPosition(new LatLng((offsetTarget.latitude),(offsetTarget.longitude)));

    line.remove();
    line1.remove();
    line = mMap.addPolyline(new PolylineOptions()
            .add(new LatLng(curLocation.latitude, curLocation.longitude),
                    new LatLng(offsetTarget.latitude, offsetTarget.longitude)).add()
            .width(4).color(Color.WHITE).geodesic(true));

    line1 = mMap.addPolyline(new PolylineOptions()
            .add(new LatLng(offsetTarget.latitude, offsetTarget.longitude),
                    new LatLng(Distance.latitude, Distance.longitude)).add()
            .width(4).color(Color.WHITE).geodesic(true));
        if(zoominoutActive  )
        {
            line.setVisible(false);
            marker_2.setVisible(false);

        }
        else
        {
            line.setVisible(true);
            line1.setVisible(true);
            marker_2.setVisible(true);

        }
}

    private double distance1(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515*1093.61;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    public static float distance(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6967410; //Yards
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float dist = (float) (earthRadius * c);

        return dist;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();


    }
    public int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }
    private Bitmap drawTextToBitmap(@DrawableRes int backgroundRes, String text,String info) {
        Resources resources = getResources();
        float scale = resources.getDisplayMetrics().density;
        Bitmap bitmap = BitmapFactory.decodeResource(resources, backgroundRes);
        android.graphics.Bitmap.Config bitmapConfig = bitmap.getConfig();

        if (bitmapConfig == null) {
            bitmapConfig = android.graphics.Bitmap.Config.ARGB_8888;
        }
        bitmap = bitmap.copy(bitmapConfig, true);
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // SET FONT COLOR (e.g. WHITE -> rgb(255,255,255))
        paint.setColor(Color.rgb(255, 255, 255));
        // SET FONT SIZE (e.g. 15)
        paint.setTextSize((int) (22 * scale));
        // SET SHADOW WIDTH, POSITION AND COLOR (e.g. BLACK)

        paint.setShadowLayer(1f, 0f, 1f, Color.WHITE);

        Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        // SET FONT COLOR (e.g. WHITE -> rgb(255,255,255))
        paint1.setColor(Color.rgb(255, 255, 255));
        // SET FONT SIZE (e.g. 15)
        paint1.setTextSize((int) (10 * scale));
        // SET SHADOW WIDTH, POSITION AND COLOR (e.g. BLACK)
        paint1.setShadowLayer(1f, 0f, 1f, Color.WHITE);

        Rect bounds = new Rect();
        Rect bounds1 = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        paint1.getTextBounds(info, 0, info.length(), bounds1);
        int x = (bitmap.getWidth() - bounds.width()) / 2;
        int y = (bitmap.getHeight() + bounds.height()) / 2;
        int x1 = (bitmap.getWidth() - bounds1.width()) / 2;
        int y1 = (bitmap.getHeight() + bounds1.height()) / 2;
        canvas.drawText(text, x-bounds.width()/20, y+0.2f*paint.getTextSize()/5, paint);
        canvas.drawText(info, x1+bounds.width()/20, y1-0.2f*paint.getTextSize(), paint1);

        return bitmap;
    }
    MarkerOptions marker;
    LatLng fixPoint;



    /**
     * Runs when a GoogleApiClient object successfully connects.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        // Provides a simple way of getting a device's location and is well suited for
        // applications that do not require a fine-grained location and that do not need location
        // updates. Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient,
                REQUEST,
                this);
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {

           if (mMap != null) {
                mMap.setMyLocationEnabled(true);
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE );


            }
            else
           {
               dialog.hide();
               Toast.makeText(this,"Location not detected,Turn on location or check your network connection.", Toast.LENGTH_LONG).show();
           }


            double dist = distance(Distance.latitude,Distance.longitude,curLocation.latitude,curLocation.longitude);
            Long L = Math.round(dist);
            int d = Integer.valueOf(L.intValue());

            positionMap(mMap,curLocation,Distance);
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(curLocation).include(Distance);
            LatLngBounds bounds = builder.build();

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bounds.getCenter(),ZoomValue));

        } else {
            dialog.hide();
            Toast.makeText(this,"Location not detected,Turn on location or check your network connection.", Toast.LENGTH_LONG).show();
        }
        try{
            Thread.sleep(2500);
        }
        catch(Exception e)
        {}



    }
    @Override
    public void onMapLoaded() {
        dialog.hide();
    }
    public static void positionMap(GoogleMap map, LatLng start, LatLng end) {
        // zoom the map so both locations are visible
       // LatLngBounds bounds = new LatLngBounds(start,end);
       // map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(start).include(end);
        LatLngBounds bounds = builder.build();
        map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
     //   CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(builder.build(), 100);
      //  map.moveCamera(cameraUpdate);
        double lat1 = start.latitude;
        double lon1 = start.longitude;
        double lat2 = end.latitude;
        double lon2 = end.longitude;
        // find the bearing

        double dLon = Math.toRadians(lon2 - lon1);


        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        lon1 = Math.toRadians(lon1);

        double Bx = Math.cos(lat2) * Math.cos(dLon);
        double By = Math.cos(lat2) * Math.sin(dLon);
        double lat3 = Math.atan2(Math.sin(lat1) + Math.sin(lat2), Math.sqrt((Math.cos(lat1) + Bx) * (Math.cos(lat1) + Bx) + By * By));
        double lon3 = lon1 + Math.atan2(By, Math.cos(lat1) + Bx);

        float[] results = new float[3];
        Location.distanceBetween(
                start.latitude,
                start.longitude,
                end.latitude,
                end.longitude,
                results);
        float bearing = results[2];
        // position the map so the two markers are vertically aligned
        CameraPosition position = map.getCameraPosition();
        CameraPosition cameraPosition = new CameraPosition.Builder(position)
                .target(new LatLng(lat3,lon3))
                .bearing(bearing)//.tilt(65.5f)
                .build();
        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
       // map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
    private LocationSource.OnLocationChangedListener mMapLocationListener = null;
    double curLoc_Latitude =0;
    double curLoc_Longitude =0;
    double preLoc_Latitude=0;
    double preLoc_Longitude=0;

    @Override
    public void onLocationChanged(Location location) {
        if (mMapLocationListener != null) {
            curLoc_Latitude = location.getLatitude();
            curLoc_Longitude = location.getLongitude();

            if((Math.round((distance(curLocation.latitude,curLocation.longitude,curLoc_Latitude, curLoc_Longitude)))<100))
            {
                if(preLoc_Latitude!=curLoc_Latitude && preLoc_Longitude!=curLoc_Longitude) {
                    curLocation = new LatLng(curLoc_Latitude, curLoc_Longitude);
                    if(parEachHole[holeNumVar] == 4 && (Math.round((distance(curLocation.latitude,curLocation.longitude,HoleLatitude[0], HoleLongitude[0])))>100))
                    {
                        setMapPoints(new LatLng(HoleLatitude[1], HoleLongitude[1]));

                    }else
                    if(parEachHole[holeNumVar] == 5)
                    {
                        if((Math.round((distance(curLocation.latitude,curLocation.longitude,HoleLatitude[0], HoleLongitude[0])))>100)&&
                          (Math.round((distance(curLocation.latitude,curLocation.longitude,HoleLatitude[0], HoleLongitude[0])))<200))
                        {
                            deActive1fairway = true;
                        }
                        else
                        if((Math.round((distance(curLocation.latitude,curLocation.longitude,HoleLatitude[0], HoleLongitude[0])))>200))
                        {
                            deActive2fairway = true;
                            marker_4.setPosition(new LatLng(HoleLatitude[1], HoleLongitude[1]));
                        }
                        setMapPointsFor2Fairway(new LatLng(marker_3.getPosition().latitude,marker_3.getPosition().longitude),new LatLng(marker_4.getPosition().latitude,marker_4.getPosition().longitude));

                    }
                    else
                      setMapPoints(new LatLng(fixPoint.latitude, fixPoint.longitude));

                    preLoc_Latitude=curLoc_Latitude;
                    preLoc_Longitude=curLoc_Longitude;
                    Log.i("value","preLoc_Latitude###################"+preLoc_Latitude);
                }
              //  markerstart.setPosition(new LatLng(curLocation.latitude,curLocation.longitude));

            }
            mMapLocationListener.onLocationChanged(location);
        }

    }
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        dialog.hide();
        Toast.makeText(this,"\"Connection failed: ConnectionResult.getErrorCode() = \" + result.getErrorCode()", Toast.LENGTH_LONG).show();
       // Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }
    @Override
    public void activate(LocationSource.OnLocationChangedListener onLocationChangedListener) {
        mMapLocationListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        dialog.hide();
        mMapLocationListener = null;
    }

    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        dialog.hide();
        Toast.makeText(this,"Connection suspended", Toast.LENGTH_LONG).show();
       // Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }
    @Override
    public void onBackPressed() {

        //Intent PreviousScreen = new Intent(MapsActivity.this,StartRound.class);

       // startActivity(PreviousScreen);
        // Otherwise defer to system default behavior.
       // super.onBackPressed();
    }

    private void preparegolfHoleFieldData() {

        GolfHoleField golfHoleField = null;
        for(int i =0 ;i<sideFields.length;i++)
        {
            golfHoleField = null;
            Log.i("dist","%%%%%%%%%%%%%%%%%sideFields==="+sideFields[i]);
            Log.i("dist","%%%%%%%%%%%%%%%%%sideFieldLatitude==="+sideFieldLatitude[i]);
            Log.i("dist","%%%%%%%%%%%%%%%%%sideFieldLongitude==="+sideFieldLongitude[i]);

            golfHoleField = new GolfHoleField(sideFields[i], ""+Math.round((distance(curLocation.latitude,curLocation.longitude,sideFieldLatitude[i], sideFieldLongitude[i]))));
            golfholefieldList.add(golfHoleField);
        }

    }
    public void UserNotification()
    {
        Log.d("dist","%%%%%%%%%%%%%%%%%on clcik");
        NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify=new Notification(R.drawable.notifyicon,"",System.currentTimeMillis());
        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        PendingIntent pending= PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT);
        Notification.Builder builder = new Notification.Builder(MapsActivity.this);
        builder.setAutoCancel(false);
        builder.setTicker("Golfnation Message");
        builder.setContentTitle("Golfnation Mobile App");
        builder.setContentText("you are on golfnations gps golf point tracker");
        builder.setSmallIcon(R.drawable.notifyicon);
        builder.setContentIntent(pending);
        builder.setOngoing(true);
        builder.setSubText("");   //API level 16
        //  builder.setNumber(100);
        builder.setAutoCancel(true);
        builder.build();
        notify= builder.getNotification();
        //notif.notify(11, notify);
        notif.notify(0, notify);
    }

    private void InitSlidingMenu()
    {
        //Init component
        listViewSliding = (ListView) findViewById(R.id.lv_sliding_menumap);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layoutmap);
        //drawerLayout.setVisibility(View.GONE);
        listSliding = new ArrayList<>();
        //Add item for sliding list
        listSliding.add(new ItemSlideMenu(0, "This Round",0));
        listSliding.add(new ItemSlideMenu(0, "      Scorecard",1));
        listSliding.add(new ItemSlideMenu(0, "      Edit Settings",1));
        listSliding.add(new ItemSlideMenu(0, "      Save",1));
        listSliding.add(new ItemSlideMenu(0, "      Cancel",1));
        adapter = new SlidingMenuAdapter(this, listSliding);
        listViewSliding.setAdapter(adapter);

        //Display icon to open/ close sliding list
        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set title
        setTitle(listSliding.get(0).getTitle());
        //item selected
        listViewSliding.setItemChecked(0, true);
        //Close menu
        drawerLayout.closeDrawer(listViewSliding);
        drawerLayout.setVisibility(View.INVISIBLE);
        // invalidateOptionsMenu();

        //Display fragment 1 when start
        // replaceFragment(0);
        //Hanlde on item click
        drawerLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    //m_hotdNteet.setBackgroundResource(R.drawable.mainbutton);
                } else if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                    Log.i("Value","Here in drawlayout click");
                }
                return false;
            }

        });

        listViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Value","Here in list click");
                listViewSliding.setItemChecked(position, true);
                if(position == 1)
                {
                    Bundle bundle = new Bundle();
                    bundle.putString("SCREENNAME","FROMMAPACTIVITY");
                    Intent nextScreen = new Intent(MapsActivity.this, ScoreCard.class);

                    nextScreen.putExtras(bundle);
                    startActivity(nextScreen);
                    finish();
                }
                else
                if(position == 2)
                {
                    Intent PreviousScreen = new Intent(MapsActivity.this,RoundSetting.class);
                    startActivity(PreviousScreen);
                    finish();

                }
                else
                if(position == 4)
                {
                    drawerLayout.closeDrawer(Gravity.RIGHT);
                    ViewDialog alert = new ViewDialog();
                    alert.showDialog(MapsActivity.this, "Do you want to cancel round?");

                }
                else
                    return;
                // drawerLayout.closeDrawer(listViewSliding);

            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_opened, R.string.drawer_closed){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                float deg = menu.getRotation() + 180F;
                menu.animate().rotation(deg).setInterpolator(new AccelerateDecelerateInterpolator());
                menu.setBackgroundResource(R.drawable.backarrow);
                drawerLayout.setVisibility(View.VISIBLE);

                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                float deg = menu.getRotation() + 180F;
                menu.animate().rotation(deg).setInterpolator(new AccelerateDecelerateInterpolator());
                menu.setBackgroundResource(R.drawable.menubutton);

                drawerLayout.setVisibility(View.GONE);

                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // drawerLayout.setDrawerListener(actionBarDrawerToggle);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    //#DIALOG BOX FOR REMOVE GOLFER
    private class ViewDialog {

        public void showDialog(Activity activity, String msg) {
            // final int valuein = value;
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialogyesno);

            TextView text = (TextView) dialog.findViewById(R.id.dialogtext);
            text.setText(msg);

            Button dialogButton = (Button) dialog.findViewById(R.id.d2);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    dialog.dismiss();
                    Intent PreviousScreen = new Intent(MapsActivity.this,MainPage.class);
                    startActivity(PreviousScreen);
                    finish();

                }
            });

            Button dialogButton1 = (Button) dialog.findViewById(R.id.d1);
            dialogButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();

                }
            });

            dialog.show();

        }
    }
}
