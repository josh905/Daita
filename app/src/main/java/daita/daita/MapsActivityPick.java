package daita.daita;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
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




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.addMarker(new MarkerOptions().position(hand.galwayLoc()).title("Marker in Galway"));

        mMap.addMarker(new MarkerOptions().position(hand.limerickLoc()).title("Marker in Limerick"));

        mMap.addMarker(new MarkerOptions().position(hand.corkLoc()).title("Marker in Cork"));

        mMap.addMarker(new MarkerOptions().position(hand.dubLoc()).title("Marker in Dublin"));




        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hand.midLoc(), 6.8f));

    }
}
