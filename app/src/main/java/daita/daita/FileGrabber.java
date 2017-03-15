package daita.daita;



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
 * Created on 15/03/2017.
 */

public class FileGrabber extends ArrayAdapter<FileSender>{

    private Context con;
    private FileSender ob;


    public FileGrabber(Context con, int res){
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


                ob = new FileSender(array);
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

        theView.setText(getItem(pos).getList().toString());

        return theView;
    }


}
