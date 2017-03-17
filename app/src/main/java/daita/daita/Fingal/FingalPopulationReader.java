package daita.daita.Fingal;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import daita.daita.R;

/**
 * Created on 14/03/2017.
 */

public class FingalPopulationReader extends ArrayAdapter<FingalPopulation>{

    private Context con;
    private FingalPopulation ob;



    public FingalPopulationReader(Context con, int res){
        super(con,res);

        this.con = con;

        parse();

    }



    private void parse(){
        int r = R.raw.fingal_population;
        try{
            InputStream input = con.getResources().openRawResource(r);
            BufferedReader buff = new BufferedReader(new InputStreamReader(input));
            String col;

            while ((col = buff.readLine())!=null){
                //make an array for this data
                String[] array = col.split(",");


                ob = new FingalPopulation();
                ob.setPlace(array[1]);
                ob.setC2011(array[2]);
                ob.setC2006(array[3]);
                ob.setC2002(array[4]);
                ob.setC1996(array[5]);
                ob.setC1991(array[6]);
                ob.setC1986(array[7]);
                ob.setC1981(array[8]);
                this.add(ob);

            }

        }
        catch(IOException error){
            error.printStackTrace();
        }
    }



    //possibly re-do this into its own class ? (a generic version suited for all data)


    public View getView(final int pos, View convertView, final ViewGroup parent){
        TextView theView = (TextView)convertView;

        if(theView==null){
            theView = new TextView(parent.getContext());
            theView.setTextSize(26);
        }

        theView.setText(getItem(pos).getPlace());

        return theView;
    }









}