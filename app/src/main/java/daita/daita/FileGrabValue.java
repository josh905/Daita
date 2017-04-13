package daita.daita;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;



public class FileGrabValue {

    private Context con;

    public String getValue(Context con, int file){

        InputStream input = con.getResources().openRawResource(file);
        BufferedReader buff = new BufferedReader(new InputStreamReader(input));

        String row;
        String[] col;
        String theValue = "none";
        try {
            while ((row = buff.readLine()) != null) {

                col = row.split(",");
                theValue = col[1];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return theValue;

    }


    public LatLng location(Context con, int file, int rowNum){



        InputStream input = con.getResources().openRawResource(file);
        BufferedReader buff = new BufferedReader(new InputStreamReader(input));

        LatLng theFullLoc = null;
        double theLat;
        double theLong;
        String row;
        String[] col;

        String rowNumStr = Integer.toString(rowNum);
        try {
            while ((row = buff.readLine()) != null) {
                col = row.split(",");


                if(col[0].equalsIgnoreCase(rowNumStr)){
                    theLat = Double.parseDouble(col[18]);
                    theLong = Double.parseDouble(col[17]);
                    theFullLoc = new LatLng(theLat,theLong);
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return theFullLoc;

    }







}
