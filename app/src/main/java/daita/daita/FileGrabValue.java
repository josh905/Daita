package daita.daita;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by joshreynolds on 11/04/2017.
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


    public String getValueAtPos(Context con, int file, int colNum, int pos){

        InputStream input = con.getResources().openRawResource(file);
        BufferedReader buff = new BufferedReader(new InputStreamReader(input));


        String row;
        String[] col;
        String newValue = "";
        try {
            while ((row = buff.readLine()) != null) {
                ArrayList<String> list = new ArrayList<>();
                for (int i=0; i < pos; i++){
            list.add(row+" "+pos);
        }

        col = row.split(",");
        newValue = col[colNum];

    }
} catch (IOException inpExcep) {
            inpExcep.printStackTrace();
        }

        return newValue;

    }

}
