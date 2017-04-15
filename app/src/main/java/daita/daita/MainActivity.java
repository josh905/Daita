package daita.daita;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{

    private Button findBtn, chooseBtn;

    public void goToMap(String choice){
        Intent firstIntent= new Intent(MainActivity.this,MapActivity.class);
        firstIntent.putExtra("choice",choice);
        startActivity(firstIntent);
    }






    /*
    private boolean fabClicked, findBtnClicked;

    public boolean isMainFabClicked(){
        return fabClicked;
    }

    public boolean unsetMainFabClicked(){
        return fabClicked = false;
    }

    public boolean isFindBtnClicked(){
        return findBtnClicked;
    }

    public boolean unsetFindBtnClicked(){
        return findBtnClicked = false;
    }
    */



    public void print(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent splash = new Intent(MainActivity.this,SplashScreen.class);
        splash.putExtra("duration",1500);
        startActivity(splash);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent in = new Intent(MainActivity.this,PlaceActivity.class);
                    String thePlace = "all of Ireland";
                    in.putExtra("place", thePlace);

                    startActivity(in);


                }
        });





        handleButtons();


    }




    private void handleButtons(){


        findBtn = (Button)findViewById(R.id.findBtn);
        findBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                goToMap("find");
            }
        });


        chooseBtn = (Button)findViewById(R.id.chooseBtn);
        chooseBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {



                goToMap("choose");
            }
        });




    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
