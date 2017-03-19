package daita.daita;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.view.View;


import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class DisplayDataActivity extends AppCompatActivity{


    private ListView theLV;
    private FileGrabber adapter1;
    private FileGrabRow adapter2;


    private String c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,
            c11,c12,c13,c14,c15,c16,c17,c18,c19,c20;

    private int numCols = 0;

    private int row = 0;
    private int col = 0;
    private int file, res; //res for resource
    private String place;

    private String visible;
    private TextView resultView;
    private ImageView img;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        Intent tent = getIntent();
        file = tent.getIntExtra("file", 0);
        res = tent.getIntExtra("res", 0);
        place = tent.getStringExtra("place");

        img = (ImageView) findViewById(R.id.resultBG);


        resultView = (TextView)findViewById(R.id.resultView);
        theLV = (ListView)findViewById(R.id.theLV);


        adapter1 = new FileGrabber(this, 0, file);
        adapter2 = new FileGrabRow(this, 0, res);



        numCols = adapter1.getItem(0).getNumCols();




        ad1();

    }



    public void print(String msg){
        if(msg.isEmpty()||msg.equals(" ")){
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


    public void ad1(){

        img.setBackgroundResource(R.drawable.verylightgrey);

        visible = "ad1";
        resultView.setVisibility(View.GONE);
        theLV.setVisibility(View.VISIBLE);

        theLV.setAdapter(adapter1);


        theLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                row = position+1;

                if(row==1){
                    return;
                    //we dont want to read the title
                }



                c1 = adapter1.getItem(position).getC1();
                c2 = adapter1.getItem(position).getC2();
                c3 = adapter1.getItem(position).getC3();
                c4 = adapter1.getItem(position).getC4();
                c5 = adapter1.getItem(position).getC5();
                c6 = adapter1.getItem(position).getC6();
                c7 = adapter1.getItem(position).getC7();
                c8 = adapter1.getItem(position).getC8();
                c9 = adapter1.getItem(position).getC9();
                c10 = adapter1.getItem(position).getC10();
                c11 = adapter1.getItem(position).getC11();
                c12 = adapter1.getItem(position).getC12();
                c13 = adapter1.getItem(position).getC13();
                c14 = adapter1.getItem(position).getC14();
                c15 = adapter1.getItem(position).getC15();
                c16 = adapter1.getItem(position).getC16();
                c17 = adapter1.getItem(position).getC17();
                c18 = adapter1.getItem(position).getC18();
                c19 = adapter1.getItem(position).getC19();
                c20 = adapter1.getItem(position).getC20();

                ad2();


            }
        });


    }

    @Override
    public void onBackPressed(){

        if(visible.equals("result")){
            ad2();

            return;

        }
        if(visible.equals("ad2")){
            ad1();

            return;
        }
        if(visible.equals("ad1")){
            super.onBackPressed();
            /*
            Intent plain = new Intent(DisplayDataActivity.this, PlaceActivity.class);
            plain.putExtra("place", place);
            startActivity(plain);
            return;
            */

        }

        //dont add else here.
    }

    public void ad2(){

        img.setBackgroundResource(R.drawable.verylightgrey);

        visible = "ad2";
        resultView.setVisibility(View.GONE);
        theLV.setVisibility(View.VISIBLE);

        theLV.setAdapter(adapter2);

        theLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                col = position + 1;



                if(col<1||col>20){
                    print("You can't choose this item");
                }

                if(col==1){
                    show(c1);
                }
                if(col==2){
                    show(c2);
                }
                if(col==3){
                    show(c3);
                }
                if(col==4){
                    show(c4);
                }
                if(col==5){
                    show(c5);
                }
                if(col==6){
                    show(c6);
                }
                if(col==7){
                    show(c7);
                }
                if(col==8){
                    show(c8);
                }
                if(col==9){
                    show(c9);
                }
                if(col==10){
                    show(c10);
                }
                if(col==11){
                    show(c11);
                }
                if(col==12){
                    show(c12);
                }
                if(col==13){
                    show(c13);
                }
                if(col==14){
                    show(c14);
                }
                if(col==15){
                    show(c15);
                }
                if(col==16){
                    show(c16);
                }
                if(col==17){
                    show(c17);
                }
                if(col==18){
                    show(c18);
                }
                if(col==19){
                    show(c19);
                }
                if(col==20){
                    show(c20);
                }


            }
        });


    }

    public void show(String result){

        img.setBackgroundResource(R.drawable.lightback);

        visible = "result"; //this is for the back button


        theLV.setVisibility(View.GONE);
        resultView.setText(result);
        resultView.setVisibility(View.VISIBLE);


        ObjectAnimator resultAnimation = ObjectAnimator.ofFloat(resultView, "alpha", 0.1f, 1.5f);
        resultAnimation.setDuration(2000);
        AnimatorSet aset = new AnimatorSet();
        aset.play(resultAnimation);
        aset.start();
    }



    private void displayFiles(){
        ArrayList<String> fileList = new ArrayList<>();
        for(int i = 0; i<numCols; i++){
            fileList.add(adapter1.getItem(i).toString());
            if(adapter1.getItem(i)==null){
                break;
            }
        }
    }





}
