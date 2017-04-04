package daita.daita;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.SphericalUtil;

public class MapsActivityFind extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int MY_PERMISSION_REQUEST_LOCATION = 1;
    private LatLng myLoc = null;
    private LocationListener listen;
    private LocationManager locman;
    private CameraPosition where;
    private String place;
    private Marker myLocMarker;
    private int corkDistance, dubCenDistance, galwayDistance, dubSouthDistance,
    fingalDistance, italyDistance;

    private int nearest, furthest;


    private Marker fingal, dubCen, dubSouth, galway, cork, italy;




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



    }

    public double myCurrentRadius(LatLng firstLoc, LatLng secondLoc) {

        //these values are in metres

        if (firstLoc == null || secondLoc == null) {
            return 0;
        }

        return SphericalUtil.computeDistanceBetween(firstLoc, secondLoc);


    }



    public int checkNearest(){

        corkDistance = (int) Math.round(myCurrentRadius(myLoc, hand.corkLoc()));
        galwayDistance = (int) Math.round(myCurrentRadius(myLoc, hand.galwayLoc()));
        dubCenDistance = (int) Math.round(myCurrentRadius(myLoc, hand.dubCenLoc()));
        dubSouthDistance = (int) Math.round(myCurrentRadius(myLoc, hand.dubSouthLoc()));
        fingalDistance = (int) Math.round(myCurrentRadius(myLoc, hand.fingalLoc()));
        italyDistance = (int) Math.round(myCurrentRadius(myLoc, hand.italyLoc()));


        int myDistanceArray[] = new int[]{corkDistance,galwayDistance,dubCenDistance,dubSouthDistance,fingalDistance,italyDistance};


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

        nearest = checkNearest();  //this updates the nearest location

        if(nearest==dubCenDistance){
            dubCenHandler();
        }

        else if(nearest==fingalDistance){
            fingalHandler();
        }

        else if(nearest==dubSouthDistance){
            dubSouthHandler();
        }

        else if(nearest==galwayDistance){
            galwayHandler();
        }

        else if(nearest==italyDistance){
            italyHandler();
        }

        else if(nearest==corkDistance){
            corkHandler();
        }

        else{
            print ("Please ensure your location services are enabled");
        }


    }





    public boolean locationFound(LatLng theNewLoc){

        if (theNewLoc==null)  return false;
        else if(theNewLoc==myLoc) return true;
        return false;

    }




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



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

                //Toast.makeText(MapsActivityFind.this, "Please allow location for this app", Toast.LENGTH_SHORT).show();
                return;
            }
        } else {


            doLoc();



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
                Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(settingsIntent);
            }
        };

        //8000 = 8000 milliseconds
        locman.requestLocationUpdates("gps", 8000, 0, listen);


        if (ContextCompat.checkSelfPermission(MapsActivityFind.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MapsActivityFind.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(MapsActivityFind.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(MapsActivityFind.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);
            }
        } else {
            LocationManager locM = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location loc = locM.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            try {
                myLoc = new LatLng(loc.getLatitude(), loc.getLongitude());

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(MapsActivityFind.this, "Cannot find location", Toast.LENGTH_SHORT).show();
            }
        }


        //mMap.addMarker(new MarkerOptions().position(hand.nciLoc()).title("Welcome to Daita"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(dublin));


        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hand.nciLoc(), 16.5f));


        handleRequests();

    }


    public void dubCenHandler(){
        dubCen = mMap.addMarker(new MarkerOptions().position(hand.dubCenLoc()));
        where = new CameraPosition.Builder().target(hand.dubCenLoc()).zoom(18).tilt(80).bearing(10).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(where));

        dubCen.setTitle("Click this pin for Central Dublin stats");
        dubCen.showInfoWindow();
    }

    public void fingalHandler(){
       fingal = mMap.addMarker(new MarkerOptions().position(hand.fingalLoc()));
        where = new CameraPosition.Builder().target(hand.fingalLoc()).zoom(18).tilt(80).bearing(10).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(where));

        fingal.setTitle("Click this pin for Fingal stats");
        fingal.showInfoWindow();
    }


    public void dubSouthHandler(){

        dubSouth = mMap.addMarker(new MarkerOptions().position(hand.dubSouthLoc()));

        where = new CameraPosition.Builder().target(hand.dubSouthLoc()).zoom(18).tilt(80).bearing(10).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(where));

        dubSouth.setTitle("Click this pin for South Dublin stats");
        dubSouth.showInfoWindow();
    }

    public void galwayHandler(){
        galway = mMap.addMarker(new MarkerOptions().position(hand.galwayLoc()));
        where = new CameraPosition.Builder().target(hand.galwayLoc()).zoom(18).tilt(80).bearing(10).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(where));

        galway.setTitle("Click this pin for Galway stats");
        galway.showInfoWindow();
    }

    public void corkHandler(){
        cork = mMap.addMarker(new MarkerOptions().position(hand.corkLoc()));
        where = new CameraPosition.Builder().target(hand.corkLoc()).zoom(18).tilt(80).bearing(10).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(where));

        cork.setTitle("Click this pin for Cork stats");
        cork.showInfoWindow();
    }



    public void italyHandler(){
        italy = mMap.addMarker(new MarkerOptions().position(hand.italyLoc()));
        where = new CameraPosition.Builder().target(hand.italyLoc()).zoom(18).tilt(80).bearing(10).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(where));

        italy.setTitle("Clicca qui per i dati per l'Italia");
        italy.showInfoWindow();
    }



    public void whenClicked(){

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {


                if(marker.getTitle().equals("Click this pin to detect nearest data")){

                    handleNearest();

                    return true;
                }


                if(marker.getTitle().equals("Click this pin for Fingal stats")){
                    fingalGO();
                    return true;
                }
                if(marker.getTitle().equals("Click this pin for Central Dublin stats")){
                    dubCenGO();
                    return true;
                }

                if(marker.getTitle().equals("Click this pin for South Dublin stats")){
                    dubSouthGO();
                    return true;
                }
                if(marker.getTitle().equals("Click this pin for Galway stats")){
                    galwayGO();
                    return true;
                }
                if(marker.getTitle().equals("Click this pin for Cork stats")){
                    corkGO();
                    return true;
                }
                if(marker.getTitle().equals("Clicca qui per i dati per l'Italia")){
                    italyGO();
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

        whenClicked();



    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    doLoc();
                return;
        }
    }



    private void doLoc() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        locman.requestLocationUpdates("gps", 8000, 0, listen);



    }


    private void italyGO(){
        Intent i = new Intent(MapsActivityFind.this, PlaceActivity.class);
        String thePlace = "Italia";
        i.putExtra("place", thePlace);
        startActivity(i);
    }


    private void fingalGO(){
        Intent i = new Intent(MapsActivityFind.this, PlaceActivity.class);
        String thePlace = "Fingal";
        i.putExtra("place", thePlace);
        startActivity(i);
    }

    private void dubCenGO(){
        Intent i = new Intent(MapsActivityFind.this, PlaceActivity.class);
        String thePlace = "Central Dublin";
        i.putExtra("place", thePlace);
        startActivity(i);
    }

    private void galwayGO(){
        Intent i = new Intent(MapsActivityFind.this, TheCatcherActivity.class);
        String thePlace = "Galway";
        i.putExtra("place", thePlace);
        startActivity(i);
    }

    private void dubSouthGO(){
        Intent i = new Intent(MapsActivityFind.this, TheCatcherActivity.class);
        String thePlace = "South Dublin";
        i.putExtra("place", thePlace);
        startActivity(i);
    }


    private void corkGO(){
        Intent i = new Intent(MapsActivityFind.this, TheCatcherActivity.class);
        String thePlace = "Cork";
        i.putExtra("place", thePlace);
        startActivity(i);
    }



}