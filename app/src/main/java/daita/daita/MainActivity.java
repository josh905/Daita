package daita.daita;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements MainInterface{

    public Button findBtn, chooseBtn;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        */

        //toolbar is unnecessary

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "This button views the map", Snackbar.LENGTH_LONG)
                       // .setAction("Map view action", null).show();

                //set this so we know to go to location or not
                fabClicked = true;

                    Intent findLocIntent = new Intent(MainActivity.this,MapsActivityFind.class);
                    startActivity(findLocIntent);

            }
        });

        findLoc();
        chooseLoc();


    }

    public void pullJSON(){
        findLoc();
        String grabbed = "";
        if (grabbed.isEmpty()){
            boolean notGrab = true;
        }
    }

    public void findLoc(){
        findBtn = (Button)findViewById(R.id.findBtn);
        findBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent findLocIntent = new Intent(MainActivity.this,MapsActivityFind.class);

                startActivity(findLocIntent);
            }
        });
    }



    public void chooseLoc(){
        chooseBtn = (Button)findViewById(R.id.chooseBtn);
        chooseBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent chooseLocIntent = new Intent(MainActivity.this,ActivityIreland.class);

                startActivity(chooseLocIntent);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
