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


/**
 * Created on 17/03/2017.
 */

public class FileGrabRow extends ArrayAdapter<FileSender>{

    private Context con;
    private FileSender ob;




    public FileGrabRow(Context con, int res){
        super(con,res);

        this.con = con;

        parse();

    }


    private void parse(){

        try{
            InputStream input = con.getResources().openRawResource(R.raw.fingal_population_res);
            BufferedReader buff = new BufferedReader(new InputStreamReader(input));
            String col;

            while ((col = buff.readLine())!=null){

                String[] array = col.split(",");

                ob = new FileSender(array[0],"","","","","","","","","","","","","","","","","","","");
                this.add(ob);



            }



        }
        catch(IOException error){
            error.printStackTrace();
        }


    }






    public View getView(final int pos, View convertView, final ViewGroup parent){
        TextView theView = (TextView)convertView;

        if(theView==null){
            theView = new TextView(parent.getContext());
            theView.setTextSize(30);
        }

        theView.setText(getItem(pos).getC1());

        return theView;
    }





}
