package daita.daita;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class DisplayDataActivity extends AppCompatActivity implements View.OnTouchListener {


    private ListView theLV;
    private FileGrabber adapter1;
    private FileGrabRow adapter2;


    private String c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,
            c11,c12,c13,c14,c15,c16,c17,c18,c19,c20;

    private int numCols = 0;

    private String choice1, choice2;

    private int col = 0;

    private int file, res; //res for resource

    private String place, result;

    private boolean right;

    private ImageView swipeImage;

    private int swipeCount = 0;

    private CountDownTimer theTimer;

    private boolean noSwipes = false;


    private String visible;
    private TextView resultView;
    private ImageView img;
    private GestureDetector theSwiper;



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return theSwiper.onTouchEvent(event);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        Intent tent = getIntent();
        file = tent.getIntExtra("file", 0);
        res = tent.getIntExtra("res", 0);
        place = tent.getStringExtra("place");

        img = (ImageView) findViewById(R.id.resultBG);
        swipeImage = (ImageView) findViewById(R.id.swipeImage);


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
        img.setVisibility(View.VISIBLE);

        visible = "ad1";
        swipeImage.setVisibility(View.GONE);
        resultView.setVisibility(View.GONE);
        theLV.setVisibility(View.VISIBLE);

        theLV.setAdapter(adapter1);


        theLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                if(position==0){
                    print("You can't select the "+adapter1.getItem(position).getC1()+" row");
                    return;
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

                choice1 = c1;

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

            super.onBackPressed();


        //dont add else here.

    }

    public void ad2(){

        img.setBackgroundResource(R.drawable.verylightgrey);
        img.setVisibility(View.VISIBLE);

        visible = "ad2";
        swipeImage.setVisibility(View.GONE);
        resultView.setVisibility(View.GONE);
        theLV.setVisibility(View.VISIBLE);

        theLV.setAdapter(adapter2);

        theLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if(position == 0 ){ //for the title item
                    print("You can't select the "+adapter2.getItem(position).getC1()+" row");
                    return;
                }


                choice2 = adapter2.getItem(position).getC1();
                col = position + 1;
                //MUST ALWAYS GET C1 HERE COZ C1 IS TITLES.

                checkCols();

                show();


            }
        });


    }

    public void checkCols(){


        if(col==2){
            result = c2;
        }

        if(col==3){
            result = c3;
        }

        if(col==4){
            result = c4;
        }

        if(col==5){
            result = c5;
        }

        if(col==6){
            result = c6;
        }

        if(col==7){
            result = c7;
        }

        if(col==8){
            result = c8;
        }

        if(col==9){
            result = c9;
        }

        if(col==10){
            result = c10;
        }

        if(col==11){
            result = c11;
        }

        if(col==12){
            result = c12;
        }

        if(col==13){
            result = c13;
        }

        if(col==14){
            result = c14;
        }

        if(col==15){
            result = c15;
        }

        if(col==16){
            result = c16;
        }

        if(col==17){
            result = c17;
        }

        if(col==18){
            result = c18;
        }

        if(col==19){
            result = c19;
        }

        if(col==20){
            result = c20;
        }


    }



    public void show(){


        img.setBackgroundResource(R.drawable.lightback);

        visible = "result"; //this is for the back button


        theLV.setVisibility(View.GONE);


        if(result.isEmpty()||result.equals(" ")||result.equalsIgnoreCase("null")){
            result = "N/A";
        }


        handleSwipes();

    }


    public void doResult(){

        swipeCount++;
        swipeImage.setVisibility(View.GONE);
        img.setVisibility(View.VISIBLE);
        resultView.setVisibility(View.VISIBLE);

        resultView.setText("\n"+choice1 + "\n\n" + choice2 + "\n\n" + result);
        ObjectAnimator resultAnimation = ObjectAnimator.ofFloat(resultView, "alpha", 0.1f, 1.5f);


        resultAnimation.setDuration(1750); //1.75 seconds
        AnimatorSet aset = new AnimatorSet();
        aset.play(resultAnimation);
        aset.start();

    }



    public void handleSwipes(){


        if(swipeCount==0){

            resultView.setVisibility(View.GONE);
            img.setVisibility(View.GONE);
            swipeImage.setVisibility(View.VISIBLE);


            ObjectAnimator swipeAnimation = ObjectAnimator.ofFloat(swipeImage, "alpha", 0.1f, 1.5f);


            swipeAnimation.setDuration(1250); //1.25 seconds
            AnimatorSet aset = new AnimatorSet();
            aset.play(swipeAnimation);
            aset.start();

            theTimer = new CountDownTimer(1200, 1200) {

                public void onTick(long untilDone) {
                    noSwipes = true;
                }

                public void onFinish()
                {
                    noSwipes = false;
                    doResult();
                }

            };

            theTimer.start();



        }

        else{

            doResult();



        }




        img.setOnTouchListener(new Swiper(getApplicationContext()) { //or a context created in onCreate method ?

            @Override
            public void onSwipeLeft() {

                if(!noSwipes){
                    swipeCount++;
                    whenSwipeLeft();
                }



            }

            @Override
            public void onSwipeRight() {

                if(!noSwipes){
                    swipeCount++;
                    whenSwipeRight();
                }



            }



        });


    }



    public void whenSwipeLeft(){

        if(col != numCols){

            col++;
            choice2 = adapter2.getItem(col-1).getC1();


            checkCols();



            right = false;

            show();


        }

        else{

            print("You have reached the last result");

        }

    }

    public void whenSwipeRight(){

        if(col >2){
            col--;
            choice2 = adapter2.getItem(col-1).getC1();


            checkCols();



            right = true;

            show();


        }

        else{

            print("This is the first result");

        }

    }








}
