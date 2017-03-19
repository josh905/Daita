package daita.daita;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;


public class TheCatcherActivity extends AppCompatActivity{

    private Button returnBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_catcher);



        returnBtn = (Button)findViewById(R.id.returnBtn);



        returnBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                Intent i = new Intent(TheCatcherActivity.this,MainActivity.class);

                startActivity(i);
            }
        });








    }


}
