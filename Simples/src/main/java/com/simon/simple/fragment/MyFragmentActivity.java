package com.simon.simple.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.simon.baseandroid.BaseActivity;
import com.simon.simple.R;

/**
 * desc: Fragment 手动addView
 * author: xw
 * time: 2016/12/14
 */
public class MyFragmentActivity extends BaseActivity {
    public static final String TAG = MyFragmentActivity.class.getSimpleName();

    private Fragment myFragment;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, MyFragmentActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment);

        refreshFragment();
        replaceFragment();
    }

    private void refreshFragment() {
        myFragment = getSupportFragmentManager().findFragmentByTag(MyFragment.TAG);
    }

    private void replaceFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (myFragment == null) {
            myFragment = new MyFragment();
            transaction.add(R.id.fragment_content, myFragment, MyFragment.TAG);
        }
        hideAll(transaction)
                .show(myFragment)
                .commit();
    }

    private FragmentTransaction hideAll(FragmentTransaction transaction) {
        if (myFragment != null) {
            transaction.hide(myFragment);
        }
        return transaction;
    }
}
