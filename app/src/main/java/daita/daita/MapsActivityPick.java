package daita.daita;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivityPick extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    MapHandler hand = new MapHandler();
    private CameraPosition where;
    private String place;

    private Marker fingal, dubCen, dubSouth, galway, cork;





    private void fingalGO(){
        Intent i = new Intent(MapsActivityPick.this, PlaceActivity.class);
        String thePlace = "fingal";
        i.putExtra("place", thePlace);
        startActivity(i);
    }

    private void dubCenGO(){
        Intent i = new Intent(MapsActivityPick.this, PlaceActivity.class);
        String thePlace = "dubcen";
        i.putExtra("place", thePlace);
        startActivity(i);
    }

    private void galwayGO(){
        Intent i = new Intent(MapsActivityPick.this, PlaceActivity.class);
        String thePlace = "galway";
        i.putExtra("place", thePlace);
        startActivity(i);
    }

    private void dubSouthGO(){
        Intent i = new Intent(MapsActivityPick.this, PlaceActivity.class);
        String thePlace = "dubsouth";
        i.putExtra("place", thePlace);
        startActivity(i);
    }


    private void corkGO(){
        Intent i = new Intent(MapsActivityPick.this, PlaceActivity.class);
        String thePlace = "cork";
        i.putExtra("place", thePlace);
        startActivity(i);
    }


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


        galway = mMap.addMarker(new MarkerOptions().position(hand.galwayLoc()).title("Galway"));



        cork = mMap.addMarker(new MarkerOptions().position(hand.corkLoc()).title("Cork"));



        dubCen = mMap.addMarker(new MarkerOptions().position(hand.dubCenLoc()).title("Dublin"));





    }


    public void dublinZoom(){

        where = new CameraPosition.Builder().target(hand.dubCenLoc()).zoom(11).tilt(80).bearing(1).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(where));

        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hand.dubLoc(), 10.8f));

        fingal = mMap.addMarker(new MarkerOptions().position(hand.fingalLoc()).title("Fingal"));



       dubSouth = mMap.addMarker(new MarkerOptions().position(hand.dubSouthLoc()).title("Dublin South"));





        dubCen.setTitle("Dublin Central");




    }









    public void dubCenChosen(){
        where = new CameraPosition.Builder().target(hand.dubCenLoc()).zoom(18).tilt(80).bearing(10).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(where));

        dubCen.setTitle("Click this pin for Dublin Central stats");
        dubCen.showInfoWindow();
    }

    public void fingalChosen(){
        where = new CameraPosition.Builder().target(hand.fingalLoc()).zoom(18).tilt(80).bearing(10).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(where));

        fingal.setTitle("Click this pin for Fingal stats");
        fingal.showInfoWindow();
    }


    public void dubSouthChosen(){
        where = new CameraPosition.Builder().target(hand.dubSouthLoc()).zoom(18).tilt(80).bearing(10).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(where));

        dubSouth.setTitle("Click this pin for Dublin South stats");
        dubSouth.showInfoWindow();
    }

    public void galwayChosen(){
        where = new CameraPosition.Builder().target(hand.galwayLoc()).zoom(18).tilt(80).bearing(10).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(where));

        galway.setTitle("Click this pin for Galway stats");
        galway.showInfoWindow();
    }

    public void corkChosen(){
        where = new CameraPosition.Builder().target(hand.corkLoc()).zoom(18).tilt(80).bearing(10).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(where));

        cork.setTitle("Click this pin for Cork stats");
        cork.showInfoWindow();
    }









    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        this.addMarkers();


        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(hand.midLoc(), 6.8f));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {


                if(marker.getTitle().equals("Dublin")){
                    dublinZoom();
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
                if(marker.getTitle().equals("Cork")){
                    corkChosen();
                    return true;
                }
                if(marker.getTitle().equals("Dublin Central")){
                    dubCenChosen();
                    return true;
                }

                if(marker.getTitle().equals("Dublin South")){
                    dubSouthChosen();
                    return true;
                }



                if(marker.getTitle().equals("Click this pin for Fingal stats")){
                    fingalGO();
                    return true;
                }
                if(marker.getTitle().equals("Click this pin for Dublin Central stats")){
                    dubCenGO();
                    return true;
                }

                if(marker.getTitle().equals("Click this pin for Dublin South stats")){
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



                return false; //must be false unless user clicks something


            }


        });




    }


}
