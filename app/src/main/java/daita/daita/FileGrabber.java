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
            String row;

            while ((row = buff.readLine())!=null){
                //make an array for this data
                String[] array = row.split(",");
                fs = new FileSender();
                for (int i = 0; i<row.length();i++){ //or array.length ?
                    fs.setVal(i,array[i]);
                }





                this.add(fs);

            }

        }
        catch(IOException error){
            error.printStackTrace();
        }
    }



    //possibly re-do this into its own class ? (a generic version suited for all data)
    public View getView(final int pos, View convertView, final ViewGroup parent){
        TextView theLV = (TextView)convertView;
        //LV stands for list view
        if(theLV==null){
            theLV = new TextView(parent.getContext());
            theLV.setTextSize(28);
        }

        theLV.append(getItem(pos).getN1());
        theLV.append("   ");
        theLV.append(getItem(pos).getN2());
        theLV.append("   ");
        theLV.append(getItem(pos).getN3());
        theLV.append("   ");
        theLV.append(getItem(pos).getN4());
        theLV.append("   ");
        theLV.append(getItem(pos).getN5());

        return theLV;
    }


}
