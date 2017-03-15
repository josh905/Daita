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
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivityFind extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int MY_PERMISSION_REQUEST_LOCATION = 1;
    private LatLng myLoc = null;
    private LocationListener listen;
    private LocationManager locman;


    MapHandler hand = new MapHandler();

    private int sourceDecoder = -1;






    //create instance of interface for MainActivity class
    //mint for main interface


    //then check is fab clicked


    //public void onBackPressed(){
    //mint.unsetMainFabClicked();
    // mint.unsetFindBtnClicked();
    // }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_find);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //checkWhichClicked();
        //zoomToNCI();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;






        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
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
                        },10);

                //Toast.makeText(MapsActivityFind.this, "Please allow location for this app", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        else{
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





        /*

        if(ContextCompat.checkSelfPermission(MapsActivityFind.this, Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MapsActivityFind.this, Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(MapsActivityFind.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);
                }
            else{
                ActivityCompat.requestPermissions(MapsActivityFind.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_REQUEST_LOCATION);
            }

        }
        else{
            LocationManager locM = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location loc = locM.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            try{
                myLoc = new LatLng(loc.getLatitude(), loc.getLongitude());
            }
            catch (Exception e){
                e.printStackTrace();
                Toast.makeText(MapsActivityFind.this, "Cannot find location", Toast.LENGTH_SHORT).show();
            }
        }

        */




        //mMap.addMarker(new MarkerOptions().position(hand.nciLoc()).title("Welcome to Daita"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(dublin));


            //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hand.nciLoc(), 16.5f));

        if(myLoc!=null){
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLoc, 16.5f));
        }



    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        switch (requestCode){
            case 10:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    doLoc();
                return;
        }
    }

    private void doLoc() {

        locman.requestLocationUpdates("gps", 8000, 0, listen);
    }


}
