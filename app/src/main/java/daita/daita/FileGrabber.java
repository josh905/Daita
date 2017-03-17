package daita.daita;



import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

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
    private FileSender fs;
    private FileChoice fc = new FileChoice();


    public FileGrabber(Context con, int res){
        super(con,res);

        this.con = con;

        parse();

}



    private void parse(){

        try{
            InputStream input = con.getResources().openRawResource(R.raw.fingal_population);
            BufferedReader buff = new BufferedReader(new InputStreamReader(input));
            String col;

            while ((col = buff.readLine())!=null){
                //make an array for this data
                String[] array = col.split(",");

                if(array.length==9){
                    fs = new FileSender(array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],array[8],"","","","","","","","","","","");
                    this.add(fs);

                }
                else{
                    Toast.makeText(this.getContext(),"WRONG AMOUNT OF COLUMNS",Toast.LENGTH_LONG).show();
                }





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

        theView.setText(getItem(pos).getC2());

        return theView;
    }




}