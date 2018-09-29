package co.example.takwa.routineclasshometask;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashSchedule extends AppCompatActivity {

    TextView tvText,tvMark,tvfin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_schedule);
        initView();
        initfunc();
        animate();
        textStyle();



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();



    }

    private void textStyle() {
        Typeface typeface=Typeface.createFromAsset(getAssets(),"Jennifer Lynne.ttf");
        tvText.setTypeface(typeface);
        tvText.setText("Forgetting time");

        tvMark.setTypeface(typeface);
        tvMark.setText("?");





    }

    private void animate() {
        YoYo.with(Techniques.TakingOff)
                .duration(5000)
                .repeat(0)
                .playOn(tvText);


        YoYo.with(Techniques.ZoomOutLeft)
                .duration(2000)
                .repeat(0)
                .playOn(tvMark);


        final Thread thread =  new Thread(){
            @Override
            public void run() {


                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //catch (Exception e){


            }

        };
        thread.start();

        YoYo.with(Techniques.FadeInUp)
                .duration(1000)
                .repeat(0)
                .playOn(tvfin);

    }

    private void initView() {
        tvText=findViewById(R.id.textId);
        tvMark=findViewById(R.id.exclamatoryId);
        tvfin=findViewById(R.id.textfinalId);
    }

    private void initfunc() {
        final Thread thread =  new Thread(){
            @Override
            public void run() {
               // for(int i=0;i<=3;i++){

                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //catch (Exception e){

              //  }

                startActivity(new Intent(SplashSchedule.this,MainActivity.class));
                finish();

            }

        };
        thread.start();
    }


}

