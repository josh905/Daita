package daita.daita;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivityFind extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private int test1 = 0;

    //create instance of interface for MainActivity class
    //mint for main interface


    //then check is fab clicked


    public LatLng nciLoc() {
        LatLng coord = new LatLng(53.3488234, -6.2432309);
        return coord;
    }

    public LatLng randomLoc(){
        LatLng coord = new LatLng(22.3488234, -17.2432309);
        return coord;
    }




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


        mMap.addMarker(new MarkerOptions().position(nciLoc()).title("Welcome to Daita"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(dublin));


            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(this.randomLoc(), 16.5f));





    }
}
