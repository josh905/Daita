package daita.daita;

import android.content.Intent;

import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;


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
    private RelativeLayout rel;
    private TextView infoTV;


    private int file;
    private int res;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);


        Intent in = getIntent();
        place = in.getStringExtra("place");

        infoTV = (TextView)findViewById(R.id.infoTV);


        lin = (LinearLayout) findViewById(R.id.lin);

        img = (ImageView)findViewById(R.id.img);
        spinner = (Spinner) findViewById(R.id.spinner);
        list =  new ArrayList<>();
        viewBtn = (Button) findViewById(R.id.viewBtn);

        rel = (RelativeLayout)findViewById(R.id.rel);

        spinner.setVisibility(View.VISIBLE);





        display();

        handle();



    }



    public void display(){


        infoTV.setText("Data for "+place);

            list.add("Press here to select a topic");


        if(place.equals("all of Ireland")){
            img.setImageResource(R.drawable.irelandtransp);
            list.add("Crime");

        }


        if(place.equals("Central Dublin")){
            img.setImageResource(R.drawable.dubcenpic);


        }

        if(place.equals("Fingal")){
            img.setImageResource(R.drawable.fingalpic);
            list.add("Population");
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

        if(place.equals("Italia")){
            infoTV.setText("I dati per l'Italia");
            list.remove(0);
            list.add("Premi qui per selezionare un argomento");
            list.add("Elezioni Comunali 2014");
            list.add("Numeri Civici Bari");
            viewBtn.setText("Visualizzazione");
            img.setImageResource(R.drawable.italypic);
        }



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







    }

    public void handle(){





        View.OnTouchListener spinnerOnTouch = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {

                    if(list.contains("Press here to select a topic")){
                        list.remove("Choose an option");
                        list.remove("Press here to select a topic");
                        list.add(0,"Choose an option");
                    }

                    else if(list.contains("Premi qui per selezionare un argomento")){
                        list.remove("Scegliere un'opzione");
                        list.remove("Premi qui per selezionare un argomento");
                        list.add(0,"Scegliere un'opzione");
                    }


                }
                return false;
            }
        };

        View.OnKeyListener spinnerOnKey = new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {

                    if(list.contains("Choose an option")){
                        list.remove("Press here to select a topic");
                        list.remove("Choose an option");
                        list.add(0,"Press here to select a topic");
                    }

                    else if(list.contains("Scegliere un'opzione")){
                        list.remove("Premi qui per selezionare un argomento");
                        list.remove("Scegliere un'opzione");
                        list.add(0,"Premi qui per selezionare un argomento");
                    }



                    return true;
                } else {
                    return false;
                }
            }
        };


        spinner.setOnTouchListener(spinnerOnTouch);
        spinner.setOnKeyListener(spinnerOnKey);


        viewBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                chosen = spinner.getSelectedItem().toString();



                if(chosen.equals("Press here to select a topic")||chosen.equals("Choose an option")){
                    print("Please select a topic");
                }

                if(chosen.equals("Premi qui per selezionare un argomento")||chosen.equals("Scegliere un'opzione")){
                    print("Per favore selezionate un'opzione");
                }


                if(place.equals("all of Ireland")){

                    if(chosen.equals("Crime")){
                        open(R.raw.ireland_crime_by_station, R.raw.ireland_crime_by_station_res);
                    }

                }


                if(place.equals("Dublin Central")){



                }



                if(chosen.equals("Crime")){
                    open(R.raw.ireland_crime_by_station, R.raw.ireland_crime_by_station_res);
                }

                if(chosen.equals("Population")){
                    open(R.raw.fingal_population, R.raw.fingal_population_res);
                }

                if(chosen.equals("Elezioni Comunali 2014")){
                    open(R.raw.italy_electorial_one, R.raw.italy_electorial_one_res);
                }

                if(chosen.equals("Numeri Civici Bari")){
                    open(R.raw.italy_bari_house_phones, R.raw.italy_bari_house_phones_res);
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
