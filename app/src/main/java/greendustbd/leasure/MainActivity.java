package greendustbd.leasure;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    private ImageView mv,bk,dr,mu;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();

        mv = (ImageView) findViewById(R.id.mv);
        bk = (ImageView) findViewById(R.id.bk);
        dr = (ImageView) findViewById(R.id.dr);
        mu = (ImageView) findViewById(R.id.mu);

        mv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                    startActivity(intent);

            }
        });
        bk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(MainActivity.this, BookActivity.class);
                    startActivity(intent);

            }
        });

        dr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(MainActivity.this, DramaActivity.class);
                    startActivity(intent);

            }
        });

        mu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(MainActivity.this, AppsActivity.class);
                    startActivity(intent);
            }
        });




    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        Slide slide = new Slide();
        slide.setDuration(3000);
        getWindow().setExitTransition(slide);
    }



    }

