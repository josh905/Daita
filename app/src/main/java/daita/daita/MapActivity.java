package daita.daita;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;



import android.graphics.Color;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int MY_PERMISSION_REQUEST_LOCATION = 1;
    private LatLng myLoc = null;
    private LocationListener listen;
    private LocationManager locman;
    private CameraPosition where;
    private FileGrabValue value;
    private String theValue;

    private String choice = "";
    private Marker myLocMarker;
    private int corkDistance, dubCenDistance, galwayDistance, dubSouthDistance,
    fingalDistance, italyDistance, belfastDistance;


    private Circle overlay;
    private CircleOptions overlayOptions;

    private int nearest, furthest;


    private Marker fingal, dubCen, dubSouth, galway, cork, italy, belfast;

    private NetworkInfo netInfo;
    private ConnectivityManager conman;




    MapHandler hand = new MapHandler();


    //create instance of interface for MainActivity class
    //mint for main interface


    //then check is fab clicked




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_find);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Intent in = getIntent();
        choice = in.getStringExtra("choice");

    }



    public void openPlace(String thePlace){
        Intent i = new Intent(MapActivity.this, PlaceActivity.class);
        i.putExtra("place", thePlace);
        startActivity(i);
    }



    public int checkNearest(){

        corkDistance = (int) Math.round(hand.myCurrentRadius(myLoc, hand.corkLoc()));
        galwayDistance = (int) Math.round(hand.myCurrentRadius(myLoc, hand.galwayLoc()));
        dubCenDistance = (int) Math.round(hand.myCurrentRadius(myLoc, hand.dubCenLoc()));
        dubSouthDistance = (int) Math.round(hand.myCurrentRadius(myLoc, hand.dubSouthLoc()));
        fingalDistance = (int) Math.round(hand.myCurrentRadius(myLoc, hand.fingalLoc()));
        italyDistance = (int) Math.round(hand.myCurrentRadius(myLoc, hand.italyLoc()));
        belfastDistance = (int) Math.round(hand.myCurrentRadius(myLoc, hand.belfastLoc()));


        int myDistanceArray[] = new int[]{corkDistance,galwayDistance,dubCenDistance,dubSouthDistance,fingalDistance,italyDistance, belfastDistance};


        nearest= myDistanceArray[0];
        furthest = myDistanceArray[0];

        for(int i=0; i< myDistanceArray.length; i++)
        {
            if(myDistanceArray[i] > furthest)
                furthest = myDistanceArray[i];
            else if (myDistanceArray[i] < nearest)
                nearest = myDistanceArray[i];

        }

        return nearest;

    }


    public void handleNearest(){

        nearest = checkNearest()/1000;  //this updates the nearest location


        if(nearest > 5000){
            print("Sorry, you are "+(nearest-5000)+"km away from the closest data");

        }

        else if(nearest==dubCenDistance){

            dubCen.setTitle("Click this pin for Central Dublin stats");
            dubCen.showInfoWindow();
            hand.zoomToPlace(mMap,hand.dubCenLoc());
        }

        else if(nearest==fingalDistance){
            openPlace("Fingal");
        }

        else if(nearest==dubSouthDistance){
            openPlace("South Dublin");
        }

        else if(nearest==galwayDistance){
            openPlace("Galway");
        }

        else if(nearest==italyDistance){
            openPlace("Italia");
        }

        else if(nearest==corkDistance){
            openPlace("Cork");
        }

        else if(nearest==belfastDistance){
            openPlace("Belfast");
        }

        else{
            print ("Please ensure your location services are enabled");
        }


    }



    public void addFirstMarkers(){


        galway = mMap.addMarker(new MarkerOptions().position(hand.galwayLoc()).title("Galway"));



        cork = mMap.addMarker(new MarkerOptions().position(hand.corkLoc()).title("Cork"));



        dubCen = mMap.addMarker(new MarkerOptions().position(hand.dubCenLoc()).title("Dublin"));


        italy = mMap.addMarker(new MarkerOptions().position(hand.italyLoc()).title("Italia"));


        belfast = mMap.addMarker(new MarkerOptions().position(hand.belfastLoc()).title("Belfast"));



    }





    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        addFirstMarkers();   //add markers regardless of choice

        value = new FileGrabValue();




        if(choice.equalsIgnoreCase("find")) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.

                    requestPermissions(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.INTERNET
                    }, 10);

                    //Toast.makeText(MapActivity.this, "Please allow location for this app", Toast.LENGTH_SHORT).show();
                    return;
                }
            } else {


                checkGPS();


            }


            mMap.setMyLocationEnabled(true);
            locman = (LocationManager) getSystemService(LOCATION_SERVICE);
            listen = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    myLoc = new LatLng(location.getLatitude(), location.getLongitude());
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };

            //8000 = 8000 milliseconds
            locman.requestLocationUpdates("gps", 8000, 0, listen);


            if (ContextCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    ActivityCompat.requestPermissions(MapActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);
                } else {
                    ActivityCompat.requestPermissions(MapActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);
                }
            } else {
                LocationManager locM = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                Location loc = locM.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                try {
                    myLoc = new LatLng(loc.getLatitude(), loc.getLongitude());

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MapActivity.this, "Please ensure permissions are granted", Toast.LENGTH_SHORT).show();
                }
            }




            handleRequests();
            findLoc();
        }


        else if (choice.equalsIgnoreCase("choose")){
            chooseLoc();
        }







        //THIS MUST STAY AS LAST THING IN ONMAPREADY
        //SO DONT HAVE TO KEEP CALLING IT IN


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {


                if(marker.getTitle().equals("Click this pin to detect nearest data")){

                    handleNearest();


                    return true;
                }


                if(marker.getTitle().equals("Click this pin for Fingal stats")){
                    openPlace("Fingal");
                    return true;
                }
                if(marker.getTitle().equals("Click this pin for Central Dublin stats")){
                    openPlace("Central Dublin");
                    return true;
                }

                if(marker.getTitle().equals("Click this pin for South Dublin stats")){
                    openPlace("South Dublin");
                    return true;
                }
                if(marker.getTitle().equals("Click this pin for Galway stats")){
                    openPlace("Galway");

                    return true;
                }
                if(marker.getTitle().equals("Click this pin for Cork stats")){
                    openPlace("Cork");
                    return true;
                }
                if(marker.getTitle().equals("Clicca qui per i dati per l'Italia")){
                    openPlace("Italia");
                    return true;
                }
                if(marker.getTitle().equals("Click this pin for Belfast stats")) {
                    openPlace("Belfast");
                    return true;
                }


                if(marker.getTitle().equals("Dublin")){
                    where = new CameraPosition.Builder().target(hand.dubCenLoc()).zoom(11).tilt(80).bearing(1).build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(where));

                    //below adds the 2 new dublin sectors and updates the central one coz the dublin pin is located at dubCen by default
                    fingal = mMap.addMarker(new MarkerOptions().position(hand.fingalLoc()).title("Fingal"));


                    dubSouth = mMap.addMarker(new MarkerOptions().position(hand.dubSouthLoc()).title("South Dublin"));


                    dubCen.setTitle("Central Dublin");

                    return true;
                }

                if(marker.getTitle().equals("Fingal")){
                    hand.zoomToPlace(mMap, hand.fingalLoc());
                    fingal.setTitle("Click this pin for Fingal stats");
                    fingal.showInfoWindow();
                    return true;
                }
                if(marker.getTitle().equals("Galway")){
                    hand.zoomToPlace(mMap, hand.galwayLoc());
                    galway.setTitle("Click this pin for Galway stats");
                    galway.showInfoWindow();
                    return true;
                }
                if(marker.getTitle().equals("Cork")){
                    hand.zoomToPlace(mMap, hand.corkLoc());
                    cork.setTitle("Click this pin for Cork stats");
                    cork.showInfoWindow();
                    return true;
                }
                if(marker.getTitle().equals("Central Dublin")){
                    hand.zoomToPlace(mMap, hand.dubCenLoc());
                    dubCen.setTitle("Click this pin for Central Dublin stats");
                    dubCen.showInfoWindow();
                    return true;
                }

                if(marker.getTitle().equals("South Dublin")){
                    hand.zoomToPlace(mMap, hand.dubSouthLoc());
                    dubSouth.setTitle("Click this pin for South Dublin stats");
                    dubSouth.showInfoWindow();
                    return true;
                }
                if(marker.getTitle().equals("Italia")){
                    hand.zoomToPlace(mMap, hand.italyLoc());
                    italy.setTitle("Clicca qui per i dati per l'Italia");
                    italy.showInfoWindow();
                    return true;
                }

                if(marker.getTitle().equals("Belfast")) {
                    hand.zoomToPlace(mMap, hand.belfastLoc());
                    belfast.setTitle("Click this pin for Belfast stats");
                    belfast.showInfoWindow();
                    return true;
                }



                return false; //must be false unless user clicks something


            }


        });













    }






    public void print(String msg){
        if(msg.isEmpty()){
            msg = "N/A";
        }
        View v = findViewById(R.id.map);
            Snackbar.make(v, msg, Snackbar.LENGTH_LONG)
                .setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                //ignore the line through getColor, its "deprecated" but its best this way for us
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                .show();
    }


    public void handleRequests(){
        if(myLoc == null){
            return;
        }

        myLocMarker = mMap.addMarker(new MarkerOptions().position(myLoc).title("Click this pin to detect nearest data"));

        where = new CameraPosition.Builder().target(myLoc).zoom(18).tilt(80).bearing(10).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(where));

        myLocMarker.showInfoWindow();





    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    checkGPS();

        }
    }



    private void checkGPS() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //locman.requestLocationUpdates("gps", 8000, 0, listen);

print("CHECKED");


    }



    private void findLoc() {


        conman = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        netInfo = conman.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (!netInfo.isConnected()) {
            print("Turn on Wifi if you wish to improve location accuracy");
        }

        fingal = mMap.addMarker(new MarkerOptions().position(hand.fingalLoc()).title("Click this pin for Fingal stats"));
        dubSouth = mMap.addMarker(new MarkerOptions().position(hand.fingalLoc()).title("Click this pin for South Dublin stats"));
        dubCen.setTitle("Click this pin for Central Dublin stats");

        //hand.handleOverlays();
        addOverlays(hand.dubCenLoc());


    }


    private void chooseLoc(){

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hand.midLoc(), 6.8f));



        //hand.handleOverlays();
        addOverlays(hand.dubCenLoc());
        addOverlays(hand.corkLoc());


    }





    public void addOverlays(LatLng middle){

        overlayOptions = new CircleOptions().strokeColor(Color.RED).center(middle).strokeWidth(3).radius(2500).fillColor(0x25FF0000);

        mMap.addCircle(overlayOptions);

        theValue = value.getValue(getApplicationContext(), R.raw.overlay_info_test);
        print(theValue);



    }





}