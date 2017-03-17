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
    private int numCols;


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

            numCols = 0;
            while ((col = buff.readLine())!=null){




                /**
                 * LOOPS ARE NOT COMPATIBLE WITH THIS CONTEXT OF THE ARRAY ADAPTER
                 * THESE IF STATEMENTS TURNED OUT TO BE THE FASTEST WAY FOR THE SYSTEM TO CHECK FOR NUMBER OF COLUMNS
                 * AND ADD THEM TO THE FILESENDER OBJECT
                 */



                String[] array = col.split(",");

                if(array.length<=0||array.length>=21){
                    Toast.makeText(this.getContext(),"INVALID AMOUNT OF COLUMNS",Toast.LENGTH_LONG).show();
                    numCols=0;
                    return;
                }

                if(array.length==1){
                    numCols=1;
                    fs = new FileSender(array[0],"","","","","","","","","","","","","","","","","","","");
                }
                if(array.length==2){
                    numCols=2;
                    fs = new FileSender(array[0],array[1],"","","","","","","","","","","","","","","","","","");
                }
                if(array.length==3){
                    numCols=3;
                    fs = new FileSender(array[0],array[1],array[2],"","","","","","","","","","","","","","","","","");
                }
                if(array.length==4){
                    numCols=4;
                    fs = new FileSender(array[0],array[1],array[2],array[3],"","","","","","","","","","","","","","","","");
                }
                if(array.length==5){
                    numCols=5;
                    fs = new FileSender(array[0],array[1],array[2],array[3],array[4],"","","","","","","","","","","","","","","");
                }
                if(array.length==6){
                    numCols=6;
                    fs = new FileSender(array[0],array[1],array[2],array[3],array[4],array[5],"","","","","","","","","","","","","","");
                }
                if(array.length==7){
                    numCols=7;
                    fs = new FileSender(array[0],array[1],array[2],array[3],array[4],array[5],array[6],"","","","","","","","","","","","","");
                }
                if(array.length==8){
                    numCols=8;
                    fs = new FileSender(array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],"","","","","","","","","","","","");
                }
                if(array.length==9){
                    numCols=9;
                    fs = new FileSender(array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],array[8],"","","","","","","","","","","");
                }
                if(array.length==10){
                    numCols=10;
                    fs = new FileSender(array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],array[8],array[9],"","","","","","","","","","");
                }
                if(array.length==11){
                    numCols=11;
                    fs = new FileSender(array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],array[8],array[9],array[10],"","","","","","","","","");
                }
                if(array.length==12){
                    numCols=12;
                    fs = new FileSender(array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],array[8],array[9],array[10],array[11],"","","","","","","","");
                }
                if(array.length==13){
                    numCols=13;
                    fs = new FileSender(array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],array[8],array[9],array[10],array[11],array[12],"","","","","","","");
                }
                if(array.length==14){
                    numCols=14;
                    fs = new FileSender(array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],array[8],array[9],array[10],array[11],array[12],array[13],"","","","","","");
                }
                if(array.length==15){
                    numCols=15;
                    fs = new FileSender(array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],array[8],array[9],array[10],array[11],array[12],array[13],array[14],"","","","","");
                }
                if(array.length==16){
                    numCols=16;
                    fs = new FileSender(array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],array[8],array[9],array[10],array[11],array[12],array[13],array[14],array[15],"","","","");
                }
                if(array.length==17){
                    numCols=17;
                    fs = new FileSender(array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],array[8],array[9],array[10],array[11],array[12],array[13],array[14],array[15],array[16],"","","");
                }
                if(array.length==18){
                    numCols=18;
                    fs = new FileSender(array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],array[8],array[9],array[10],array[11],array[12],array[13],array[14],array[15],array[16],array[17],"","");
                }
                if(array.length==19){
                    numCols=19;
                    fs = new FileSender(array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],array[8],array[9],array[10],array[11],array[12],array[13],array[14],array[15],array[16],array[17],array[18],"");
                }
                if(array.length==20){
                    numCols=20;
                    fs = new FileSender(array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],array[8],array[9],array[10],array[11],array[12],array[13],array[14],array[15],array[16],array[17],array[18],array[19]);
                }

                fs.setNumCols(numCols);
                this.add(fs);

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