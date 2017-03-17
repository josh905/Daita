package daita.daita;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
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

public class MapsActivityFind extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private LatLng myLoc = null; //this is made null until we check the user is in ireland
    private LocationManager locman;
    private CameraPosition where;
    private Marker markLoc;
    private Intent yourArea = null;




    MapHandler hand = new MapHandler();


    public LatLng getMyLoc(){
        return myLoc;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_find);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }

    public void detectClosest(){
        if (yourArea!=null){
            startActivity(yourArea);
        }
    }

    public void handleLoc(){
        markLoc = mMap.addMarker(new MarkerOptions().position(myLoc).title("Click this pin for stats on your area"));
        markLoc.showInfoWindow();
    }

    public void goToLocation() {
        locman = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria cond = new Criteria(); //cond for condition

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(MapsActivityFind.this, "Please allow location services", Toast.LENGTH_LONG).show();
            return;
        }
        mMap.setMyLocationEnabled(true);
        Location curLoc = locman.getLastKnownLocation(locman.getBestProvider(cond, false));
        if (curLoc != null) {
            myLoc = new LatLng(curLoc.getLatitude(),curLoc.getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLoc, 13));
            where = new CameraPosition.Builder().target(myLoc).zoom(17).tilt(65).bearing(45).build();

            //need to set choice to dublin central

            yourArea = new Intent(MapsActivityFind.this, PlaceActivity.class);


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(where));
            handleLoc();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;
        goToLocation();



        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                if(marker.getTitle().equals("Click this pin for stats on your area")){
                    detectClosest();
                    return true;
                }

                return false;


            }


        });


    }
}