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
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



import android.graphics.Color;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int MY_PERMISSION_REQUEST_LOCATION = 1;
    private LatLng myLoc = null;
    private LocationListener listen;
    private LocationManager locman;
    private CameraPosition where;
    private FileGrabValue grab;
    private String theValue;
    private CountDownTimer theTimer;
    private ArrayList<String> knimeData;

    private String placePick = "";
    private String showPick = "";

    private String choice = "";
    private Marker myLocMarker;
    private int corkDistance, dubCenDistance, galwayDistance, dubSouthDistance,
    fingalDistance, italyDistance, belfastDistance, londonDistance, sydneyDistance;


    private Circle overlay;
    private CircleOptions overlayOptions;

    private int nearest, furthest;
    private boolean tooFar = false;


    private Marker fingal, dubCen, dubSouth, galway, cork, italy, belfast, sydney;

    private LatLng droppedLocation;

    private ArrayList<String> titleList, infoLine1List, infoLine2List;


    /**
     * MARKERS ARE ADDED WHEN USER CHOOSES TO GO TO A PLACE FROM THE DROPDOWN
     *
     * AND REMOVED WHEN THEY CHOOSE TO SHOW ON MAP (FILTER BY PRIMARY SCHOOLS, ETC.)
     *
     */





    private NetworkInfo netInfo;
    private ConnectivityManager conman;



    private PolygonOptions polyOp;
    private Polygon poly;




    private Spinner placeSpinner, showSpinner;
    private ImageView loadingpic;
    private ProgressBar theBar;
    private RelativeLayout mapSplashLayout;
    private String whatToDisplay = "";
    private TextView titleView;
    private ArrayList<String> placeSpinnerList, showSpinnerList;
    private ArrayAdapter<String> placeAdapter,showAdapter;


    private boolean timerOn = false;

    private ArrayList<String> shownList;
    private Swiper timeCheck;
    private Marker droppedMarker;
    private int dropCount = 0;
    private ArrayList<LatLng> locationList;
    private int closestCircleDistance;
    private int circlePositionInList;



    MapHandler hand = new MapHandler();







    //create instance of interface for MainActivity class
    //mint for main interface


    //then check is fab clicked






    /**
     *
     *         ____________         ON CREATE   __________________
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_find);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent getIn = getIntent();
        choice = getIn.getStringExtra("choice");



        placeSpinner = (Spinner) findViewById(R.id.placeSpinner);
        showSpinner = (Spinner) findViewById(R.id.showSpinner);


        placeSpinner.setBackgroundColor(Color.LTGRAY);
        showSpinner.setBackgroundColor(Color.LTGRAY);

        placeSpinnerList = new ArrayList<>();
        showSpinnerList = new ArrayList<>();


        setPlaceSpinner(placeSpinnerList);
        setShowSpinner(showSpinnerList);


        shownList = new ArrayList<>(); //TO SEE WHATS ALREADY BEEN OVERLAYED ON MAP

        boolean created = true;


    }


    /**
     * **************************************** ON CREATE ^^^  *********************8
     *
     */



    public void showSplashFor(int duration){

        //DURATION MUST BE IN MILLISECONDS

        Intent splash = new Intent(MapActivity.this,SplashScreen.class);
        splash.putExtra("duration", duration);
        startActivity(splash);

    }



    public void setPlaceSpinner(ArrayList<String> list){


        list.add("Go to place");

        if(choice.equals("find")){
            list.add("My Location");
        }

        list.add("Belfast");
        list.add("Central Dublin");
        list.add("Cork");
        list.add("Fingal");
        list.add("Galway");
        list.add("Italy");
        list.add("South Dublin");
        list.add("Sydney");




        placeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);

        placeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        placeSpinner.setAdapter(placeAdapter);
    }




    public void setShowSpinner(ArrayList<String> list){


        list.add("Show on map");
        list.add("Dublin primary schools");
        list.add("Northern Ireland street crime");


        showAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);

        showAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        showSpinner.setAdapter(showAdapter);
    }


    public void setInfoWindow(final int theWindow){

        if (mMap != null) {
            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(final Marker marker) {
                    View v = getLayoutInflater().inflate(theWindow, null);
                    titleView = (TextView) v.findViewById(R.id.titleView);


                    int nearestDistance = checkNearest(marker.getPosition());

                    String theMessage = "No data found for";
                    String thePlace = "this area";



                    if(nearestDistance<100000){
                        theMessage = "Click for data on";
                        if(nearestDistance==dubCenDistance){
                            thePlace = "Central Dublin";
                        }
                        if(nearestDistance==belfastDistance){
                            thePlace = "Belfast";
                        }
                        if(nearestDistance==corkDistance){
                            thePlace = "Cork";
                        }
                        if(nearestDistance==fingalDistance){
                            thePlace = "Fingal";
                        }
                        if(nearestDistance==galwayDistance){
                            thePlace = "Galway";
                        }
                        if(nearestDistance==italyDistance){
                            thePlace = "Italy";
                        }
                        if(nearestDistance==dubSouthDistance){
                            thePlace = "South Dublin";
                        }
                        if(nearestDistance==sydneyDistance){
                            thePlace = "Sydney";
                        }


                    }




                    String theTitle = marker.getTitle();


                    String theSnippet = "\n\n"+marker.getSnippet()+"\n"+theMessage + " " + thePlace;

                    if(nearestDistance>100000){
                        theTitle="No data in this area\n\n";
                        theSnippet="This pin is "+nearestDistance/1000+"km away from the nearest data point\nClick \"Go to place\" to find data";
                    }


                    titleView.setText(theTitle);
                    titleView.append(theSnippet);





                    return v;
                }


            });




        }



    }





    public void openPlace(String thePlace){
        Intent i = new Intent(MapActivity.this, PlaceActivity.class);
        i.putExtra("place", thePlace);
        startActivity(i);
    }






    public int checkNearest(LatLng theLoc){

        //THESE VALUES ARE IN KILOMETRES BECAUSE THE ORIGINALS ARE DIVIDED BY 1000

        corkDistance = (int) Math.round(hand.myCurrentRadius(theLoc, hand.corkLoc()));
        galwayDistance = (int) Math.round(hand.myCurrentRadius(theLoc, hand.galwayLoc()));
        dubCenDistance = (int) Math.round(hand.myCurrentRadius(theLoc, hand.dubCenLoc()));
        dubSouthDistance = (int) Math.round(hand.myCurrentRadius(theLoc, hand.dubSouthLoc()));
        fingalDistance = (int) Math.round(hand.myCurrentRadius(theLoc, hand.fingalLoc()));
        italyDistance = (int) Math.round(hand.myCurrentRadius(theLoc, hand.italyLoc()));
        belfastDistance = (int) Math.round(hand.myCurrentRadius(theLoc, hand.belfastLoc()));
        sydneyDistance = (int) Math.round(hand.myCurrentRadius(theLoc, hand.sydneyLoc()));


        int myDistanceArray[] = new int[]{corkDistance,galwayDistance,dubCenDistance,dubSouthDistance,fingalDistance,italyDistance, belfastDistance, sydneyDistance};


        nearest= myDistanceArray[0];
        furthest = myDistanceArray[0];

        for(int i=0; i< myDistanceArray.length; i++)
        {
            if(myDistanceArray[i] > furthest)
                furthest = myDistanceArray[i];
            else if (myDistanceArray[i] < nearest)
                nearest = myDistanceArray[i];

        }

        return nearest; //THIS VALUE IS IN METRES


    }


    public void handleMyLocation(){

        nearest = checkNearest(myLoc);  //this updates the nearest location
        int nearestKm = nearest/1000;



        if(nearest > 100000){
            myLocMarker.setTitle("No data here");
            myLocMarker.setSnippet("There is no data available for this area yet\nYou are "+nearestKm+" away from the closest data point");
            print("Sorry, you are "+nearestKm+"km away from the closest data point");
            return;
        }

        else if(nearest==belfastDistance){
            knimeData = grab.getKnimeData(getApplicationContext(),2);
            if(nearestKm<1){
                myLocMarker.setTitle("In Central Belfast");
            }
            else{
                myLocMarker.setTitle(nearestKm+"km from Central Belfast");
            }

        }

        else if(nearest==dubCenDistance){
            knimeData = grab.getKnimeData(getApplicationContext(),3);
            if(nearestKm<1){
                myLocMarker.setTitle("In Central Dublin");
            }
            else{
                myLocMarker.setTitle(nearestKm+"km from Central Dublin");
            }
        }

        else if(nearest==corkDistance){
            knimeData = grab.getKnimeData(getApplicationContext(),4);
            if(nearestKm<1){
                myLocMarker.setTitle("In Central Cork");
            }
            else{
                myLocMarker.setTitle(nearestKm+"km from Central Cork");
            }
        }

        else if(nearest==fingalDistance){
            knimeData = grab.getKnimeData(getApplicationContext(),5);
            if(nearestKm<1){
                myLocMarker.setTitle("In Swords Central, Fingal");
            }
            else{
                myLocMarker.setTitle(nearestKm+"km from Swords Central, Fingal");
            }
        }

        else if(nearest==galwayDistance){
            knimeData = grab.getKnimeData(getApplicationContext(),6);
            if(nearestKm<1){
                myLocMarker.setTitle("In Central Galway");
            }
            else{
                myLocMarker.setTitle(nearestKm+"km from Central Galway");
            }
        }

        else if(nearest==italyDistance){
            knimeData = grab.getKnimeData(getApplicationContext(),7);
            if(nearestKm<1){
                myLocMarker.setTitle("In Central Rome");
            }
            else{
                myLocMarker.setTitle(nearestKm+"km from Central Rome");
            }
        }

        else if(nearest==dubSouthDistance){
            knimeData = grab.getKnimeData(getApplicationContext(),8);
            if(nearestKm<1){
                myLocMarker.setTitle("In Dundrum, South Dublin");
            }
            else{
                myLocMarker.setTitle(nearestKm+"km from Dundrum, South Dublin");
            }
        }

        else if(nearest==sydneyDistance){
            knimeData = grab.getKnimeData(getApplicationContext(),9);
            if(nearestKm<1){
                myLocMarker.setTitle("In Central Sydney");
            }
            else{
                myLocMarker.setTitle(nearestKm+"km from Central Sydney");
            }
        }
        else{
            print("Please ensure location services are enabled and permissions for Daita are granted");
        }

        hand.addDataToMarker(myLocMarker,knimeData);
        myLocMarker.showInfoWindow();



    }



    public void addMarkers(){


        galway = mMap.addMarker(new MarkerOptions().position(hand.galwayLoc()).title("Galway"));




        cork = mMap.addMarker(new MarkerOptions().position(hand.corkLoc()).title("Cork"));


        dubCen = mMap.addMarker(new MarkerOptions().position(hand.dubCenLoc()).title("Central Dublin"));

        fingal = mMap.addMarker(new MarkerOptions().position(hand.fingalLoc()).title("Fingal"));


        dubSouth = mMap.addMarker(new MarkerOptions().position(hand.dubSouthLoc()).title("South Dublin"));


        italy = mMap.addMarker(new MarkerOptions().position(hand.italyLoc()).title("Italy"));


        belfast = mMap.addMarker(new MarkerOptions().position(hand.belfastLoc()).title("Belfast"));


        sydney = mMap.addMarker(new MarkerOptions().position(hand.sydneyLoc()).title("Sydney"));






    }




    public void showHandler(){

        mMap.clear();



        if(showPick.equals("Dublin primary schools")){
            showSplashFor(1000);
            addCircles(R.raw.map_dublin_primary_schools,Color.BLUE);
            hand.zoomToPlace(mMap,hand.dubCenLoc(),11);
            shownList.clear();
        }

        if(showPick.equals("Northern Ireland street crime")){
            showSplashFor(2000);
            addCircles(R.raw.map_northern_ireland_streetcrime, Color.RED);
            hand.zoomToPlace(mMap,hand.belfastLoc(),11);
        }

        shownList.clear();
        shownList.add(showPick);




    }



    public void placeHandler(){



        if(placePick.equals("Central Dublin")){
            dubCen = mMap.addMarker(new MarkerOptions().position(hand.dubCenLoc()).title("Central Dublin"));
            knimeData = grab.getKnimeData(getApplicationContext(),3);
            hand.addDataToMarker(dubCen,knimeData);

            hand.zoomToPlace(mMap,hand.dubCenLoc(), 18);
            dubCen.showInfoWindow();
        }

        if(placePick.equals("South Dublin")){
            dubSouth = mMap.addMarker(new MarkerOptions().position(hand.dubSouthLoc()).title("South Dublin"));
            knimeData = grab.getKnimeData(getApplicationContext(),8);
            hand.addDataToMarker(dubSouth,knimeData);
            hand.zoomToPlace(mMap,hand.dubSouthLoc(),18);
            dubSouth.showInfoWindow();
        }

        if(placePick.equals("Fingal")){
            fingal = mMap.addMarker(new MarkerOptions().position(hand.fingalLoc()).title("Fingal"));
            knimeData = grab.getKnimeData(getApplicationContext(),5);
            hand.addDataToMarker(fingal,knimeData);
            hand.zoomToPlace(mMap,hand.fingalLoc(),18);
            fingal.showInfoWindow();
        }

        if(placePick.equals("Galway")){
            galway = mMap.addMarker(new MarkerOptions().position(hand.galwayLoc()).title("Galway"));
            hand.zoomToPlace(mMap,hand.galwayLoc(),18);
            knimeData = grab.getKnimeData(getApplicationContext(),6);
            hand.addDataToMarker(galway,knimeData);
            galway.showInfoWindow();
        }

        if(placePick.equals("Cork")){
            cork = mMap.addMarker(new MarkerOptions().position(hand.corkLoc()).title("Cork"));
            hand.zoomToPlace(mMap,hand.corkLoc(),18);
            knimeData = grab.getKnimeData(getApplicationContext(),4);
            hand.addDataToMarker(cork,knimeData);
            cork.showInfoWindow();
        }

        if(placePick.equals("Sydney")){
            sydney = mMap.addMarker(new MarkerOptions().position(hand.sydneyLoc()).title("Sydney"));
            hand.zoomToPlace(mMap,hand.sydneyLoc(),18);
            knimeData = grab.getKnimeData(getApplicationContext(),9);
            hand.addDataToMarker(sydney,knimeData);
            sydney.showInfoWindow();

        }

        if(placePick.equals("Italy")){
            italy = mMap.addMarker(new MarkerOptions().position(hand.italyLoc()).title("Italy"));
            hand.zoomToPlace(mMap,hand.italyLoc(),18);
            knimeData = grab.getKnimeData(getApplicationContext(),7);
            hand.addDataToMarker(italy,knimeData);
            italy.showInfoWindow();
        }

        if(placePick.equals("Belfast")){
            belfast = mMap.addMarker(new MarkerOptions().position(hand.belfastLoc()).title("Belfast"));
            hand.zoomToPlace(mMap,hand.belfastLoc(),18);
            knimeData = grab.getKnimeData(getApplicationContext(),2);
            hand.addDataToMarker(belfast,knimeData);
            belfast.showInfoWindow();
        }

        if(placePick.equals("My Location")){
            myLocMarker = mMap.addMarker(new MarkerOptions().position(myLoc).title("Your area"));
            myLocMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
            hand.zoomToPlace(mMap,myLoc,18);
            myLocMarker.showInfoWindow();
        }


    }


    /**
     *
     *        *******************   ON MAP READY  *************************
     */



    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;



        grab = new FileGrabValue();


        addMarkers();



        placeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                placePick = placeSpinner.getSelectedItem().toString();


                if(placeSpinner.getSelectedItem()==placeSpinner.getItemAtPosition(0)){
                    placeSpinner.setSelection(0);
                    return;
                }


                if(timerOn){
                    print("Please wait a few more seconds");
                    placeSpinner.setSelection(0);
                    return;
                }


                placeHandler();

                placeSpinner.setSelection(0);



            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }




        });






        showSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                showPick = showSpinner.getSelectedItem().toString();


                if(showPick.equals(showSpinner.getItemAtPosition(0).toString())){
                    showSpinner.setSelection(0);
                    return;
                }

                if(shownList.contains(showPick)){
                    print(showPick +" data is already displayed");
                    showSpinner.setSelection(0);
                    return;
                }


                if(timerOn){
                    print("Please wait a few more seconds");
                    showSpinner.setSelection(0);
                    return;
                }




                showHandler();

                showSpinner.setSelection(0);

                CountDownTimer buttonTimer = new CountDownTimer(10000,10000) { //8 seconds
                    @Override
                    public void onTick(long millisUntilFinished) {
                        timerOn = true;




                    }

                    @Override
                    public void onFinish() {

                        timerOn = false;
                        print("Drop a pin in a circle for data");
                    }
                };

                buttonTimer.start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });







        setInfoWindow(R.layout.custom_info_window);









        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                String title = marker.getTitle();


                if(title.contains("Belfast")){
                    openPlace("Belfast");
                }

                if(title.contains("Central Dublin")){
                    openPlace("Central Dublin");
                }

                if(title.contains("Cork")){
                    openPlace("Cork");
                }

                if(title.contains("Fingal")){
                    openPlace("Fingal");
                }

                if(title.contains("Galway")){
                    openPlace("Galway");
                }

                if(title.contains("Italy")){
                    openPlace("Italia");        //must be italia
                    //because italy page is in italian
                }

                if(title.contains("South Dublin")){
                    openPlace("South Dublin");
                }

                if(title.contains("Sydney")){
                    openPlace("Sydney");
                }


            }
        });




        if(choice.equalsIgnoreCase("find")) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {



                    requestPermissions(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.INTERNET
                    }, 10);

                    //Toast.makeText(MapActivity.this, "Please allow location for this app", Toast.LENGTH_SHORT).show();
                    return;
                }
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



                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));

                hand.zoomToPlace(mMap,marker.getPosition(),18);



                if(marker.getTitle().equals("Belfast")){
                    knimeData = grab.getKnimeData(getApplicationContext(),2);
                }

                else if(marker.getTitle().equals("Central Dublin")){
                    knimeData = grab.getKnimeData(getApplicationContext(),3);
                }

                else if(marker.getTitle().equals("Cork")){
                    knimeData = grab.getKnimeData(getApplicationContext(),4);
                }

                else if(marker.getTitle().equals("Fingal")){
                    knimeData = grab.getKnimeData(getApplicationContext(),5);
                }

                else if(marker.getTitle().equals("Galway")){
                    knimeData = grab.getKnimeData(getApplicationContext(),6);
                }

                else if(marker.getTitle().equals("Italy")){
                    knimeData = grab.getKnimeData(getApplicationContext(),7);
                }

                else if(marker.getTitle().equals("South Dublin")){
                    knimeData = grab.getKnimeData(getApplicationContext(),8);
                }

                else if(marker.getTitle().equals("Sydney")){
                    knimeData = grab.getKnimeData(getApplicationContext(),9);
                }

                else{
                    marker.showInfoWindow();
                    return true;
                }




                hand.addDataToMarker(marker,knimeData);
                marker.showInfoWindow();
                return true;





            }


        });




        if(choice.equals("find")){
            hand.zoomToPlace(mMap,myLoc,18);
            myLocMarker.showInfoWindow();
        }

        if(choice.equals("choose")){
            print("Click a pin or \"Go to place\"");
        }



        if(choice.equals("find")){
            handleMyLocation();
        }

        theMapListener();


    }


    /**
     *
 *          ****************************  END OF ONMAPREADY  *************************************
     */




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
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light )).show();
    }




    public void handleRequests(){
        if(myLoc == null){
            return;
        }


        if(choice.equals("find")){
            myLocMarker = mMap.addMarker(new MarkerOptions().position(myLoc).title("Your area"));
            myLocMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        }


    }







    private void findLoc() {


        conman = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        netInfo = conman.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (!netInfo.isConnected()) {
            print("Turn on Wifi if you wish to improve location accuracy");
        }



    }

    private void chooseLoc(){

        hand.zoomToPlace(mMap,hand.dubCenLoc(),11);

    }



    public void theMapListener(){

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                droppedLocation = point;
                clickedOrDragged();



            }
        });

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

                droppedLocation = marker.getPosition();
                clickedOrDragged();

            }
        });

    }



    public void clickedOrDragged() {

        int theDistance;

        if (droppedMarker == null) {
            print("Hold and drag marker to move it");
        } else {
            droppedMarker.remove();
        }


        droppedMarker = mMap.addMarker(new MarkerOptions().position(droppedLocation));
        droppedMarker.setDraggable(true);
        droppedMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        droppedMarker.setTitle("Near place");
        droppedMarker.setSnippet("line1\nline2\ndaita rating 7.5");



        if (!shownList.isEmpty()) {
            closestCircleDistance = checkClosestCircle();
            if (closestCircleDistance != 0) {
                circleHandler();
            } else {
                theDistance = checkNearest(droppedLocation); //in metres
                int distanceKm = theDistance / 1000; //in KM
                if (theDistance < 100000 && theDistance > 1000) {
                    if (theDistance == belfastDistance) {
                        droppedMarker.setTitle(distanceKm + "km from Central Belfast");
                    }
                    if (theDistance == dubCenDistance) {
                        droppedMarker.setTitle(distanceKm + "km from Central Dublin");
                    }
                    if (theDistance == corkDistance) {
                        droppedMarker.setTitle(distanceKm + "km from Central Cork");
                    }
                    if (theDistance == fingalDistance) {
                        droppedMarker.setTitle(distanceKm + "km from Swords Central, Fingal");
                    }
                    if (theDistance == galwayDistance) {
                        droppedMarker.setTitle(distanceKm + "km from Central Galway");
                    }
                    if (theDistance == italyDistance) {
                        droppedMarker.setTitle(distanceKm + "km from Central Rome");
                    }
                    if (theDistance == dubSouthDistance) {
                        droppedMarker.setTitle(distanceKm + "km from Dundrum, South Dublin");
                    }
                    if (theDistance == sydneyDistance) {
                        droppedMarker.setTitle(distanceKm + "km from Central Sydney");
                    }
                    droppedMarker.setSnippet("No data at this location for " + shownList.get(0)+"\n");
                } else if (theDistance < 1000) {
                    if (theDistance == belfastDistance) {
                        droppedMarker.setTitle("In Central Belfast");
                    }
                    if (theDistance == dubCenDistance) {
                        droppedMarker.setTitle("In Central Dublin");
                    }
                    if (theDistance == corkDistance) {
                        droppedMarker.setTitle("In Central Cork");
                    }
                    if (theDistance == fingalDistance) {
                        droppedMarker.setTitle("In Swords Central, Fingal");
                    }
                    if (theDistance == galwayDistance) {
                        droppedMarker.setTitle("In Central Galway");
                    }
                    if (theDistance == italyDistance) {
                        droppedMarker.setTitle("In Central Rome");
                    }
                    if (theDistance == dubSouthDistance) {
                        droppedMarker.setTitle("In Dundrum, South Dublin");
                    }
                    if (theDistance == sydneyDistance) {
                        droppedMarker.setTitle("In Central Sydney");
                    }
                    droppedMarker.setSnippet("No data at this location for " + shownList.get(0)+"\n");
                } else {
                    droppedMarker.setTitle("No data available here");
                    droppedMarker.setSnippet("Click the \"Go to place\" button to find data");
                }


            }
        }














        else {   // if SHOWN LIST IS EMPTY


                theDistance = checkNearest(droppedLocation); //in metres
                int distanceKm = theDistance / 1000; //in KM
                if (theDistance < 100000 && theDistance > 1000) {
                    if (theDistance == belfastDistance) {
                        droppedMarker.setTitle(distanceKm + "km from Central Belfast");
                        knimeData = grab.getKnimeData(getApplicationContext(),2);
                        hand.addDataToMarker(droppedMarker,knimeData);
                    }
                    if (theDistance == dubCenDistance) {
                        droppedMarker.setTitle(distanceKm + "km from Central Dublin");
                        knimeData = grab.getKnimeData(getApplicationContext(),3);
                        hand.addDataToMarker(droppedMarker,knimeData);
                    }
                    if (theDistance == corkDistance) {
                        droppedMarker.setTitle(distanceKm + "km from Central Cork");
                        knimeData = grab.getKnimeData(getApplicationContext(),4);
                        hand.addDataToMarker(droppedMarker,knimeData);
                    }
                    if (theDistance == fingalDistance) {
                        droppedMarker.setTitle(distanceKm + "km from Swords Central, Fingal");
                        knimeData = grab.getKnimeData(getApplicationContext(),5);
                        hand.addDataToMarker(droppedMarker,knimeData);
                    }
                    if (theDistance == galwayDistance) {
                        droppedMarker.setTitle(distanceKm + "km from Central Galway");
                        knimeData = grab.getKnimeData(getApplicationContext(),6);
                        hand.addDataToMarker(droppedMarker,knimeData);
                    }
                    if (theDistance == italyDistance) {
                        droppedMarker.setTitle(distanceKm + "km from Central Rome");
                        knimeData = grab.getKnimeData(getApplicationContext(),7);
                        hand.addDataToMarker(droppedMarker,knimeData);
                    }
                    if (theDistance == dubSouthDistance) {
                        droppedMarker.setTitle(distanceKm + "km from Dundrum, South Dublin");
                        knimeData = grab.getKnimeData(getApplicationContext(),8);
                        hand.addDataToMarker(droppedMarker,knimeData);
                    }
                    if (theDistance == sydneyDistance) {
                        droppedMarker.setTitle(distanceKm + "km from Central Sydney");
                        knimeData = grab.getKnimeData(getApplicationContext(),9);
                        hand.addDataToMarker(droppedMarker,knimeData);
                    }

                } else if (theDistance < 1000) {
                    if (theDistance == belfastDistance) {
                        droppedMarker.setTitle("In Central Belfast");
                        knimeData = grab.getKnimeData(getApplicationContext(),2);
                        hand.addDataToMarker(droppedMarker,knimeData);
                    }
                    if (theDistance == dubCenDistance) {
                        droppedMarker.setTitle("In Central Dublin");
                        knimeData = grab.getKnimeData(getApplicationContext(),3);
                        hand.addDataToMarker(droppedMarker,knimeData);
                    }
                    if (theDistance == corkDistance) {
                        droppedMarker.setTitle("In Central Cork");
                        knimeData = grab.getKnimeData(getApplicationContext(),4);
                        hand.addDataToMarker(droppedMarker,knimeData);
                    }
                    if (theDistance == fingalDistance) {
                        droppedMarker.setTitle("In Swords Central, Fingal");
                        knimeData = grab.getKnimeData(getApplicationContext(),5);
                        hand.addDataToMarker(droppedMarker,knimeData);
                    }
                    if (theDistance == galwayDistance) {
                        droppedMarker.setTitle("In Central Galway");
                        knimeData = grab.getKnimeData(getApplicationContext(),6);
                        hand.addDataToMarker(droppedMarker,knimeData);
                    }
                    if (theDistance == italyDistance) {
                        droppedMarker.setTitle("In Central Rome");
                        knimeData = grab.getKnimeData(getApplicationContext(),7);
                        hand.addDataToMarker(droppedMarker,knimeData);
                    }
                    if (theDistance == dubSouthDistance) {
                        droppedMarker.setTitle("In Dundrum, South Dublin");
                        knimeData = grab.getKnimeData(getApplicationContext(),8);
                        hand.addDataToMarker(droppedMarker,knimeData);
                    }
                    if (theDistance == sydneyDistance) {
                        droppedMarker.setTitle("In Central Sydney");
                        knimeData = grab.getKnimeData(getApplicationContext(),9);
                        hand.addDataToMarker(droppedMarker,knimeData);
                    }

                } else {
                    droppedMarker.setTitle("No data available here");
                    droppedMarker.setSnippet("Click the \"Go to place\" button to find data");
                }







        }


        /**
         * THE BELOW LINE IS CRUCIAL
         */

        droppedMarker.showInfoWindow();

    }


    public int checkClosestCircle(){


        if(locationList.isEmpty()){
            return 0;
        }

        int theDistance;

        for(int i=0;i<locationList.size();i++){
            theDistance = (int) Math.round(hand.myCurrentRadius(droppedLocation, locationList.get(i)));
            if(theDistance<=75){
                circlePositionInList = i;
                return theDistance;
            }

        }

        return 0;

    }


    public void circleHandler(){

        droppedMarker.setTitle(titleList.get(2)+": "+infoLine1List.get(circlePositionInList));
        droppedMarker.setSnippet(titleList.get(3)+": "+infoLine2List.get(circlePositionInList));

    }





    public void addCircles(int file, int color){



        int fill;

        if(color==Color.RED){
            fill = 0x18ff0000;
        }
        else  if(color==Color.BLUE){
            fill = 0x180000ff;
        }
        else if(color==Color.GREEN){
            fill = 0x18008000;
        }
        else{
            fill = 0x180000ff;
        }

        LatLng theLoc;



        locationList = grab.getLocationList(getApplicationContext(), file);
        titleList = grab.getTitles(getApplicationContext(),file);
        infoLine1List = grab.getInfoLine1(getApplicationContext(),file);
        infoLine2List = grab.getInfoLine2(getApplicationContext(),file);


        for(int i=0;i<locationList.size();i++){

            theLoc = locationList.get(i);

            overlayOptions = new CircleOptions().strokeColor(color).center(theLoc).strokeWidth(3).radius(75).fillColor(fill);
            mMap.addCircle(overlayOptions);


        }

    }










}