package daita.daita;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivityPick extends FragmentActivity implements OnMapReadyCallback{

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
        Marker galway = mMap.addMarker(new MarkerOptions().position(hand.galwayLoc()).title("Galway"));


        Marker limerick = mMap.addMarker(new MarkerOptions().position(hand.limerickLoc()).title("Limerick"));


        Marker cork = mMap.addMarker(new MarkerOptions().position(hand.corkLoc()).title("Cork"));


        Marker dublin = mMap.addMarker(new MarkerOptions().position(hand.dubLoc()).title("Dublin"));





    }


    public void dublinChosen(){

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hand.dubLoc(), 10.8f));

        Marker fingal = mMap.addMarker(new MarkerOptions().position(hand.fingalLoc()).title("Fingal"));


        Marker dubsouth = mMap.addMarker(new MarkerOptions().position(hand.dubSouthLoc()).title("Dublin South"));


        Marker dubwest = mMap.addMarker(new MarkerOptions().position(hand.dubWestLoc()).title("Dublin West"));


        Marker dubcen = mMap.addMarker(new MarkerOptions().position(hand.dubLoc()).title("Dublin Central"));


    }

    public void fingalChosen(){





        Intent fingalIntent = new Intent(MapsActivityPick.this,FingalActivity.class);

        startActivity(fingalIntent);
    }

    public void galwayChosen(){
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hand.galwayLoc(), 10.8f));
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
                if(marker.getTitle().equals("Dublin")){
                    dublinChosen();
                    return true;
                }
                if(marker.getTitle().equals("Fingal")){
                    fingalChosen();
                    return true;
                }
                if(marker.getTitle().equals("Galway")){
                    galwayChosen();
                    return true;
                }
                return false; //was false by default
            }


        });




    }
}
