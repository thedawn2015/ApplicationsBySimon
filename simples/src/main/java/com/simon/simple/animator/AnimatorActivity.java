package com.simon.simple.animator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.simon.base.util.LogUtil;
import com.simon.simple.R;
import com.simon.simple.animator.util.AnimatorUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimatorActivity extends AppCompatActivity {
    public static String TAG = AnimatorActivity.class.getSimpleName();
    @BindView (R.id.animator_button_show)
    Button animatorButtonShow;
    @BindView (R.id.animator_button_hide)
    Button animatorButtonHide;
    @BindView (R.id.animator_linear)
    LinearLayout animatorLinear;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, AnimatorActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        ButterKnife.bind(this);

        animatorLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.i(TAG, "onClick: animatorLinear");
            }
        });
    }

    @OnClick ({R.id.animator_button_show, R.id.animator_button_hide})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.animator_button_show:
                AnimatorUtil.showViewWithAnimator(animatorLinear);
                break;
            case R.id.animator_button_hide:
                AnimatorUtil.hideViewWithAnimator(animatorLinear);
                break;
        }
    }
}
