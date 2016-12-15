package com.simon.sample.dialog.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.simon.baseandroid.util.ToastUtil;
import com.simon.sample.R;

/**
 * desc: 对话框
 * author: xiao
 * time: 2016/11/22
 */
public class DialogUtil {

    /**
     * 基本的对话框
     *
     * @param context
     */
    public static void showNormalDialog(final Context context) {
        new AlertDialog.Builder(context)
                .setTitle("标题")
                .setMessage("这是对话框的内容，随便写点啥")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ToastUtil.showShort(context, "取消");
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ToastUtil.showShort(context, "确定");
                        dialogInterface.dismiss();
                    }
                })
                .create()
                .show();
    }

    /**
     * 自定义的
     *
     * @param context
     */
    public static void showCustomerDialog(final Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_customer_dialog, null);

        final Dialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCancelable(false);
        dialog.show();

        Window dialogWindow = dialog.getWindow();

        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay(); // 获取屏幕宽、高用

        WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
        layoutParams.width = (int) (display.getWidth() * 0.9);
        layoutParams.height = (int) (display.getHeight() * 0.4);
        dialogWindow.setAttributes(layoutParams);

        dialogWindow.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        dialogWindow.setContentView(layout);

        //        WindowManager m = getWindowManager();
        //        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        //        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        //        p.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.6
        //        p.width = (int) (d.getWidth() * 0.65); // 宽度设置为屏幕的0.65
        //        dialogWindow.setAttributes(p);

        Button confirmBtn = (Button) layout.findViewById(R.id.dialog_btn_confirm);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showShort(context, "点击了确认按钮");
                dialog.dismiss();
            }
        });

        /*new AlertDialog.Builder(context)
                .setTitle("标题")
                .setMessage("这是对话框的内容，随便写点啥")
                .setView(R.layout.fragment_customer_dialog)
                .create()
                .show();*/
    }

    /**
     * popupWindow
     *
     * @param context
     * @param parent
     */
    public static void showUnbindPopupWindow(final Context context, View parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_unbind, null);

        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);

        popupWindow.setContentView(view);
        //        Window dialogWindow = popupWindow.getWindow();

        //设置宽高
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay(); // 获取屏幕宽、高用

        int totalHeight = display.getHeight();

        int windowHeight = popupWindow.getHeight();

        popupWindow.setWidth((int) (display.getWidth() * 0.95));

        WindowManager.LayoutParams layoutParams = ((Activity) context).getWindow().getAttributes();
        layoutParams.alpha = 0.5f;
        ((Activity) context).getWindow().setAttributes(layoutParams);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams layoutParams = ((Activity) context).getWindow().getAttributes();
                layoutParams.alpha = 1f;
                ((Activity) context).getWindow().setAttributes(layoutParams);
            }
        });
        //显示位置
        popupWindow.showAtLocation(parent, Gravity.NO_GRAVITY, 0, 100);
        //        popupWindow.showAtLocation();
        //        popupWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        //                popupWindow.showAsDropDown(parent);
    }
}
