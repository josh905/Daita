package daita.daita;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivityFind extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private int test12 = 0;


    MapHandler hand = new MapHandler();

    public boolean grabLocation(){
        if (test12 == 2){
            if(mMap.isMyLocationEnabled()){
                mMap.setOnMarkerClickListener(null);

            }
            else if(!(mMap.isMyLocationEnabled())){
                mMap.setMapType(0);
            }
            else{
                return false;
            }
        }

        return true;
    }



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


        mMap.addMarker(new MarkerOptions().position(hand.nciLoc()).title("Welcome to Daita"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(dublin));


            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hand.nciLoc(), 16.5f));





    }
}
