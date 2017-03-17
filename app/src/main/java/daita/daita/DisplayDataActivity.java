package daita.daita;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class DisplayDataActivity extends AppCompatActivity{


    private ListView theLV;
    private FileGrabber adapter1;
    private FileGrabRow adapter2;


    private String c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,
            c11,c12,c13,c14,c15,c16,c17,c18,c19,c20;

    private int numCols = 0;

    private int row = 0;
    private int col = 0;






    /*
    public int endsAt(){


        //get item 0 means the title of column

        if(adapter1.getItem(0).getC1().equals("")){
            return 1;
        }
        if(adapter1.getItem(0).getC2().equals("")){
            return 2;
        }
        if(adapter1.getItem(0).getC3().equals("")){
            return 3;
        }
        if(adapter1.getItem(0).getC4().equals("")){
            return 4;
        }
        if(adapter1.getItem(0).getC5().equals("")){
            return 5;
        }
        if(adapter1.getItem(0).getC6().equals("")){
            return 6;
        }
        if(adapter1.getItem(0).getC7().equals("")){
            return 7;
        }
        if(adapter1.getItem(0).getC8().equals("")){
            return 8;
        }
        if(adapter1.getItem(0).getC9().equals("")){
            return 9;
        }
        if(adapter1.getItem(0).getC10().equals("")){
            return 10;
        }
        if(adapter1.getItem(0).getC11().equals("")){
            return 11;
        }
        if(adapter1.getItem(0).getC12().equals("")){
            return 12;
        }
        if(adapter1.getItem(0).getC13().equals("")){
            return 13;
        }
        if(adapter1.getItem(0).getC14().equals("")){
            return 14;
        }
        if(adapter1.getItem(0).getC15().equals("")){
            return 15;
        }
        if(adapter1.getItem(0).getC16().equals("")){
            return 16;
        }
        if(adapter1.getItem(0).getC17().equals("")){
            return 17;
        }
        if(adapter1.getItem(0).getC18().equals("")){
            return 18;
        }
        if(adapter1.getItem(0).getC19().equals("")){
            return 19;
        }
        if(adapter1.getItem(0).getC20().equals("")){
            return 20;
        }

        //otherwise...
        return 0;

    }
    */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        theLV = (ListView)findViewById(R.id.theLV);

        adapter1 = new FileGrabber(this, 0);
        adapter2 = new FileGrabRow(this, 0);


        numCols = adapter1.getItem(0).getNumCols();

        ad1();



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


    public void ad1(){

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

    public void ad2(){

        theLV.setAdapter(adapter2);

        theLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                col = position + 1;



                if(col<1||col>20){
                    print("You can't choose this item");
                }

                if(col==1){
                    print(c1);
                }
                if(col==2){
                    print(c2);
                }
                if(col==3){
                    print(c3);
                }
                if(col==4){
                    print(c4);
                }
                if(col==5){
                    print(c5);
                }
                if(col==6){
                    print(c6);
                }
                if(col==7){
                    print(c7);
                }
                if(col==8){
                    print(c8);
                }
                if(col==9){
                    print(c9);
                }
                if(col==10){
                    print(c10);
                }
                if(col==11){
                    print(c11);
                }
                if(col==12){
                    print(c12);
                }
                if(col==13){
                    print(c13);
                }
                if(col==14){
                    print(c14);
                }
                if(col==15){
                    print(c15);
                }
                if(col==16){
                    print(c16);
                }
                if(col==17){
                    print(c17);
                }
                if(col==18){
                    print(c18);
                }
                if(col==19){
                    print(c19);
                }
                if(col==20){
                    print(c20);
                }


            }
        });


    }



}
