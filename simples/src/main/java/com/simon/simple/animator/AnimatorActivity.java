package com.simon.simple.animator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.simon.simple.R;

public class AnimatorActivity extends AppCompatActivity {
    public static String TAG = AnimatorActivity.class.getSimpleName();

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, AnimatorActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
    }
}
