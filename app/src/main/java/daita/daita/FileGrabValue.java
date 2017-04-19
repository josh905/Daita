package daita.daita;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * @author Josh Reynolds x15389521
 */



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





    public ArrayList<String> getKnimeData(Context con, int rowNum){

        ArrayList<String> knimeData = new ArrayList<>();

        InputStream input = con.getResources().openRawResource(R.raw.knime_result_output);
        BufferedReader buff = new BufferedReader(new InputStreamReader(input));

        String row;
        String[] col;
        int rowCount = 0;

        try {
            while ((row = buff.readLine()) != null) {

                rowCount++; //title row is row number 1


                if(rowCount==rowNum){
                    col = row.split(",");
                    knimeData.add(col[0]);
                    knimeData.add(col[1]);
                    knimeData.add(col[2]);
                    knimeData.add(col[3]);
                }



            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return knimeData;

    }






    public ArrayList<LatLng> getLocationList(Context con, int file){

        ArrayList<LatLng> locationList = new ArrayList<>();

        InputStream input = con.getResources().openRawResource(file);
        BufferedReader buff = new BufferedReader(new InputStreamReader(input));

        String row;
        String[] col;
        LatLng theLoc;
        double theLat;
        double theLong;
        int readLineCount = 0;

        try {
            while ((row = buff.readLine()) != null) {

                readLineCount++; //starts on row 1

                if(readLineCount>1){ //dont include titles
                    col = row.split(",");
                    theLat = Double.parseDouble(col[0]);
                    theLong = Double.parseDouble(col[1]);
                    theLoc = new LatLng(theLat,theLong);
                    locationList.add(theLoc);
                }





            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return locationList;

    }




    public ArrayList<String> getInfoLine1(Context con, int file){

        ArrayList<String> infoLine1 = new ArrayList<>();

        InputStream input = con.getResources().openRawResource(file);
        BufferedReader buff = new BufferedReader(new InputStreamReader(input));

        String row;
        String[] col;
        int rowCount = 0;

        try {
            while ((row = buff.readLine()) != null) {

                rowCount++; //title row is row number 1


                if(rowCount>1){ //skip title row
                    col = row.split(",");
                    infoLine1.add(col[2]);
                }



            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return infoLine1;

    }



    public ArrayList<String> getInfoLine2(Context con, int file){

        ArrayList<String> infoLine2 = new ArrayList<>();

        InputStream input = con.getResources().openRawResource(file);
        BufferedReader buff = new BufferedReader(new InputStreamReader(input));

        String row;
        String[] col;
        int rowCount = 0;

        try {
            while ((row = buff.readLine()) != null) {

                rowCount++; //title row is row number 1


                if(rowCount>1){ //skip title row
                    col = row.split(",");
                    infoLine2.add(col[3]);
                }



            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return infoLine2;

    }




    public ArrayList<String> getTitles(Context con, int file){

        ArrayList<String> knimeData = new ArrayList<>();

        InputStream input = con.getResources().openRawResource(file);
        BufferedReader buff = new BufferedReader(new InputStreamReader(input));

        String row;
        String[] col;
        int rowCount = 0;

        try {
            while ((row = buff.readLine()) != null) {

                rowCount++; //title row is row number 1


                if(rowCount==1){
                    col = row.split(",");
                    knimeData.add(col[0]);
                    knimeData.add(col[1]);
                    knimeData.add(col[2]);
                    knimeData.add(col[3]);
                }
                else{
                    break;
                }



            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return knimeData;

    }

}
