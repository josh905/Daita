package daita.daita.Fingal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import daita.daita.R;

public class FingalActivity extends AppCompatActivity{

    private FingalPopulationReader adapter;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingal);


        //callPopulation();

        list = (ListView)findViewById(R.id.list);

        adapter = new FingalPopulationReader(this, 0); //zero is a dummy value which does nothing

        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //add for or while loop in this front end not back end


                Toast.makeText(view.getContext(),adapter.getItem(position).getC1981(),Toast.LENGTH_SHORT).show();



            }
        });

                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }







    /*

    private List<FingalPopulation> list = new ArrayList<>();


    private void callPopulation() {

        InputStream input = getResources().openRawResource(R.raw.fingal_population);
        BufferedReader buff = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));

        String row = "";

        try{
            while((row = buff.readLine()) != null){
                String[] pr = row.split(",");



            }


        }

        catch (IOException e){
            Log.wtf("Error","Can't read file at row "+row, e);
            e.printStackTrace();
        }

    }
    */
}
