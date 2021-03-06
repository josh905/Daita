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
 *
 * @author Sean Barrett x15561177
 * @author Josh Reynolds x15389521

 */





public class FileGrabRow extends ArrayAdapter<FileSender>{

    private Context con;
    private FileSender ob;


    private int res;


    public FileGrabRow(Context con, int def, int res){
        super(con,def);

        this.con = con;
        this.res = res;

        parse();

    }


    private void parse(){

        try{
            InputStream input = con.getResources().openRawResource(res);
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
