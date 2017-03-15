package daita.daita;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import daita.daita.R;


public class DisplayDataActivity extends AppCompatActivity{


    MainInterface mint;

    private ListView theLV;
    private FileGrabber adapter;
    private FileChoice fc = new FileChoice();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);



        //displayData();
        if(fc.getFileChoice()==R.raw.crime_rates){
            Toast bread = Toast.makeText(getApplicationContext(), "yes", Toast.LENGTH_LONG);
            bread.show();
        }
        else{
            Toast bread = Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_LONG);
            bread.show();
        }




    }

/*
    public void displayData(){

        theLV = (ListView)findViewById(R.id.theLV);

        adapter = new FileGrabber(this, 0); //zero is a dummy value which does nothing

        theLV.setAdapter(adapter);



    }
*/
}
