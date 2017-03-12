package daita.daita;

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
    MainInterface mint = new MainActivity();

    //then check is fab clicked



    public LatLng getNCI(){
       LatLng coord = new LatLng(53.3488234,-6.2432309);
        return coord;
    }



    private void checkWhichClicked(){
        if(mint.isMainFabClicked()){
            //call in methods to display the map
        }
        else if(mint.isFindBtnClicked()){
            //call in method to find location and zoom to it
        }
        else {
            //call in error handler
        }
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



        mMap.addMarker(new MarkerOptions().position(getNCI()).title("Welcome to Daita"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(dublin));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(this.getNCI(), 16.5f));
    }
}
