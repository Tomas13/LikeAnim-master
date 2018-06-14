package kz.growit.likeanim;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextSwitcher;

public class MainActivity extends AppCompatActivity {

    ImageButton btnComments;
    ImageButton btnLike;
    TextSwitcher tsLikesCounter;


    private   Boolean isFavorite = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        tsLikesCounter = findViewById(R.id.tsLikesCounter);
        btnLike = findViewById(R.id.btnLike);

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isFavorite) {
                    v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale_down));

                    tsLikesCounter.setText("2");
                    btnLike.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_heart_outline_grey));
                    isFavorite = false;
                } else {
                    v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale_up));
                    tsLikesCounter.setText("3");

                    btnLike.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_heart_red));
                    isFavorite = true;
                }
            }
        });

        btnComments = findViewById(R.id.btnComments);

        btnComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, CommentsActivity.class);
               // int[] startingLocation = new int[2];
                ///v.getLocationOnScreen(startingLocation);
                //intent.putExtra(CommentsActivity.ARG_DRAWING_START_LOCATION, startingLocation[1]);
                startActivity(intent);
//                v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake_error));
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
