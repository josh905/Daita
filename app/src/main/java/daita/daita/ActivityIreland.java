package daita.daita;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ActivityIreland extends AppCompatActivity{


    private Button crimeBtn;
    private Intent crimeIntent;
    FileChoice fc = new FileChoice();
    private int choice = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ireland);


        handleChoice();

    }

    private void display(){
        Intent i = new Intent(ActivityIreland.this,DisplayDataActivity.class);
        startActivity(i);

    }




    public void handleChoice(){
        crimeBtn = (Button)findViewById(R.id.crimeBtn);
        crimeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //choice = R.raw.crime_rates;


                //this line passes the file choice into the FileChoice class
                //fc.setFileChoice(choice);



                display();
            }
        });
    }

}
