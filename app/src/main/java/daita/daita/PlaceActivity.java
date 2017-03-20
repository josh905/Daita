package daita.daita;

import android.content.Intent;

import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;


import java.util.ArrayList;
import java.util.List;

public class PlaceActivity extends AppCompatActivity {

    private String place = "";
    private LinearLayout lin;
    private ImageView img;
    private Button viewBtn;
    private String chosen = "";
    private Spinner spinner;
    private List<String> list;
    private ArrayAdapter<String> adapter;

    private int file;
    private int res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);


        Intent in = getIntent();
        place = in.getStringExtra("place");



        lin = (LinearLayout) findViewById(R.id.lin);
        img = (ImageView)findViewById(R.id.img);
        spinner = (Spinner) findViewById(R.id.spinner);
        list =  new ArrayList<>();
        viewBtn = (Button) findViewById(R.id.viewBtn);

        spinner.setVisibility(View.VISIBLE);


        display();

        handle();



    }



    public void display(){


        if(place.equals("All of Ireland")){
            img.setImageResource(R.drawable.irelandtransp);
        }


        if(place.equals("Central Dublin")){
            img.setImageResource(R.drawable.dubcenpic);
        }

        if(place.equals("Fingal")){
            img.setImageResource(R.drawable.fingalpic);
        }

        if(place.equals("South Dublin")){
            img.setImageResource(R.drawable.dubsouthpic);
        }

        if(place.equals("Galway")){
            img.setImageResource(R.drawable.galwaypic);
        }

        if(place.equals("Cork")){
            img.setImageResource(R.drawable.corkpic);
        }

        if(place.equals("Italia"))



        //now populate list
        list.add("Press here to select a topic");
        list.add("Population");
        list.add("Crime");


        //now do this
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);



        /*

        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("item1");
        spinnerArray.add("item2");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        sItems.setAdapter(adapter);

        */




        /**
        String chosen = spinner.getSelectedItem().toString();
        if (chosen.equals("what ever the option was")) {
            print("aaaaaaaaaaa");
        }
         */



    }

    public void handle(){



        viewBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                chosen = spinner.getSelectedItem().toString();


                //create nested if statements for place and chosen topic

                if(chosen.equals("Crime")){
                    open(R.raw.crime_by_station, R.raw.crime_by_station_res);
                }

                if(chosen.equals("Population")){
                    open(R.raw.fingal_population, R.raw.fingal_population_res);
                }

            }

        });


    }


    public void print(String msg){
        if(msg.isEmpty()){
            msg = "N/A";
        }
        View v = findViewById(R.id.wrap);
        Snackbar.make(v, msg, Snackbar.LENGTH_LONG)
                .setAction("CLOSE", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                //ignore the line through getColor, its "deprecated" but its best this way for us
                .setActionTextColor(getResources().getColor(android.R.color.holo_red_light ))
                .show();
    }


    public void open(int file, int res){

        Intent dispin = new Intent(PlaceActivity.this, DisplayDataActivity.class);
        dispin.putExtra("file", file);
        dispin.putExtra("res", res);
        dispin.putExtra("place", place);
        startActivity(dispin);

    }






}
