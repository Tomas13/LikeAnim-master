package kz.growit.likeanim;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CommentsActivity extends Activity{
    public static final String ARG_DRAWING_START_LOCATION = "arg_drawing_start_location";

//    private CommentsAdapter commentsAdapter;
    private int drawingStartLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
    }
}
