package daita.daita;

/**
 * Created on 14/03/2017.
 */

public class PlaceHandler{

    private String locationChosen;

    public PlaceHandler(){
        locationChosen = "none";
    }

    public void setLocationChosen(String newLocation){
        this.locationChosen = newLocation;
    }

    public String getLocationChosen(){
        return locationChosen;
    }



}
