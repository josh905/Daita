package daita.daita;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivityPick extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    MapHandler hand = new MapHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_pick);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void addMarkers(){
        mMap.addMarker(new MarkerOptions().position(hand.galwayLoc()).title("Marker in Galway"));

        mMap.addMarker(new MarkerOptions().position(hand.limerickLoc()).title("Marker in Limerick"));

        mMap.addMarker(new MarkerOptions().position(hand.corkLoc()).title("Marker in Cork"));

        mMap.addMarker(new MarkerOptions().position(hand.dubLoc()).title("dublin"));




    }


    public void dublinChosen(){
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hand.dubLoc(), 10.8f));
        mMap.addMarker(new MarkerOptions().position(hand.fingalLoc()).title("fingal"));
        mMap.addMarker(new MarkerOptions().position(hand.dubSouthLoc()).title("Marker in South Dublin"));
        mMap.addMarker(new MarkerOptions().position(hand.dubWestLoc()).title("Marker in West Dublin"));
    }

    public void fingalChosen(){
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hand.fingalLoc(), 16.8f));

    }




    public void handleClicks(){

    }






    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        this.addMarkers();


        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hand.midLoc(), 6.8f));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
              //  mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hand.midLoc(), 0.8f));
                if(marker.getTitle().equals("dublin")){
                    dublinChosen();
                }
                else if(marker.getTitle().equals("fingal")){
                    fingalChosen();
                }
                return true; //was false by default
            }
        });


        handleClicks();


    }
}
