package daita.daita;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;


/**
 * Created on 13/03/2017.
 */

public class MapHandler {


    //middle location of ireland
    public LatLng midLoc(){
        return new LatLng(53.390474, -7.797227);
    }

    public LatLng galwayLoc(){
        return new LatLng(53.270179, -9.055429);
    }


    public LatLng corkLoc(){
        return new LatLng(51.901279, -8.479604);
    }

    public LatLng dubCenLoc(){
        return new LatLng(53.347334, -6.258972);
    }

    public LatLng fingalLoc(){
        return new LatLng(53.458369, -6.220434);
    }

    public LatLng dubSouthLoc(){
        return new LatLng(53.284904, -6.240142);
    }

    public LatLng italyLoc(){
        return new LatLng(41.936549, 12.471702);
    }

    public LatLng belfastLoc() { return new LatLng (54.602642, -5.919522);}

    public LatLng dubCity1(){

        return new LatLng(53.407156, -6.149158);

    }

    public LatLng dubCity2(){

        return new LatLng(53.381002, -6.120705);

    }

    public LatLng dubCity3(){

        return new LatLng(53.312958, -6.199670);

    }

    public LatLng dubCity4(){

        return new LatLng(53.329979, -6.378541);

    }

    public LatLng dubCity5(){

        return new LatLng(53.364515, -6.326452);

    }

    public LatLng dubCity6(){
        return new LatLng(53.406486, -6.347180);
    }



    public double myCurrentRadius(LatLng firstLoc, LatLng secondLoc) {

        //these values are in metres

        if (firstLoc == null || secondLoc == null) {
            return 0;
        }

        return SphericalUtil.computeDistanceBetween(firstLoc, secondLoc);


    }


    public void zoomToPlace(GoogleMap theMap, LatLng thePlace){
        CameraPosition pos = new CameraPosition.Builder().target(thePlace).zoom(18).tilt(80).bearing(10).build();
        theMap.animateCamera(CameraUpdateFactory.newCameraPosition(pos));
    }





}
