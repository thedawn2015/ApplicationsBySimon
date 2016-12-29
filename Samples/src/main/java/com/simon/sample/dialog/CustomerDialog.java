package com.simon.sample.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.simon.sample.R;

/**
 * desc: Dialog Fragment
 * author: xw
 * time: 2016/12/29
 */
public class CustomerDialog extends DialogFragment {
    public static final String TAG = CustomerDialog.class.getSimpleName();

    public static CustomerDialog newInstance() {
        Bundle args = new Bundle();
        CustomerDialog fragment = new CustomerDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_my_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setCancelable(false);
        return builder.create();

    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        //透明度，对话框外部
        windowParams.dimAmount = 0.5f;

        //宽、高
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        windowParams.width = (int) (metrics.widthPixels * 0.8);
        windowParams.height = (int) (metrics.heightPixels * 0.6);
        //        windowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        //        windowParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(windowParams);
        //透明，对话框内部
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#80FFFFFF")));
    }
}
