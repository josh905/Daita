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
        int qaz = R.raw.fingal_population;
        try{
            InputStream input = con.getResources().openRawResource(qaz);
            BufferedReader buff = new BufferedReader(new InputStreamReader(input));
            String row;

            while ((row = buff.readLine())!=null){
                //make an array for this data
                String[] array = row.split(",");


                ob = new FingalPopulation(array[1], array[2], array[3], array[4], array[5], array[6], array[7], array[8]);
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
            theView.setTextSize(28);
        }

        theView.setText(getItem(pos).getPlace());

        return theView;
    }





}
