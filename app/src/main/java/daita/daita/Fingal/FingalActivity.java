package daita.daita.Fingal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import daita.daita.R;

import static com.google.android.gms.R.id.center;

public class FingalActivity extends AppCompatActivity{

    private FingalPopulationReader adapter1;
    private ListView list;
    private String place,  c2011, c2006, c2002, c1996, c1991, c1986, c1981;
    private FingalPopulationTitles adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingal);




        list = (ListView)findViewById(R.id.list);



        adapter2 = new FingalPopulationTitles(this,0);
        adapter1 = new FingalPopulationReader(this, 0); //zero is a dummy value which does nothing

        ad1();




                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void ad1(){


        list.setAdapter(adapter1);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==0){
                    Toast.makeText(view.getContext(),"Cannot choose name",Toast.LENGTH_LONG).show();
                    return;
                }


                c1981 = adapter1.getItem(position).getC1981();
                c1986 = adapter1.getItem(position).getC1986();
                c1991 = adapter1.getItem(position).getC1991();
                c1996 = adapter1.getItem(position).getC1996();
                c2002 = adapter1.getItem(position).getC2002();
                c2006 = adapter1.getItem(position).getC2006();
                c2011 = adapter1.getItem(position).getC2011();

                ad2();
                //Toast.makeText(view.getContext(),adapter.getItem(position).getC1981(),Toast.LENGTH_SHORT).show();



            }
        });

    }

    private void ad2(){

        list.setAdapter(adapter2);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position<0||position>6){
                    Toast.makeText(view.getContext(),"Error",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(position==0){
                    Toast.makeText(view.getContext(),c2011,Toast.LENGTH_SHORT).show();
                }
                else if(position==1){
                    Toast.makeText(view.getContext(),c2006,Toast.LENGTH_SHORT).show();
                }
                else if(position==2){
                    Toast.makeText(view.getContext(),c2002,Toast.LENGTH_SHORT).show();
                }
                else if(position==3){
                    Toast.makeText(view.getContext(),c1996,Toast.LENGTH_SHORT).show();
                }
                else if(position==4){
                    Toast.makeText(view.getContext(),c1991,Toast.LENGTH_SHORT).show();
                }
                else if(position==5){
                    Toast.makeText(view.getContext(),c1986,Toast.LENGTH_SHORT).show();
                }
                else if(position==6){
                    Toast.makeText(view.getContext(),c1981,Toast.LENGTH_SHORT).show();
                }




            }
        });



    }






}
