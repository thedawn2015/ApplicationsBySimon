package com.simon.applicationsbysimon.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.simon.applicationsbysimon.R;

/**
 * 对话框
 * Created by xw on 2016/7/12.
 */
public class CustomerDialog {
    /**
     * 单选对话框
     *
     * @param context
     * @param typeStrings
     */
    public static void showSingleChoiceDialog(Context context, final CharSequence[] typeStrings) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("设备切换")
                .setSingleChoiceItems(typeStrings, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
/*.setPositiveButton(R.string.button_confirm, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //                            view.setEnabled(true);

                    finalResult.setIndex(localResult.getIndex());
                    finalResult.setId(localResult.getId());
                    finalResult.setName(localResult.getName());

                    notifyItemChanged(getAdapterPosition());
                }
            })*/
            /*.setNegativeButton(R.string.button_clear, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //                            view.setEnabled(true);

                    finalResult.setIndex(-1);
                    //                            finalResult.setId(-1);
                    finalResult.setId(null);
                    finalResult.setName(null);
                    notifyItemChanged(getAdapterPosition());
                }
            })*/
            /*.setNeutralButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //                            view.setEnabled(true);
                }
            })*/
        dialog.show();

    }
}