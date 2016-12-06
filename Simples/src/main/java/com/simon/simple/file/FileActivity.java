package com.simon.simple.file;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.simon.baseandroid.BaseActivity;
import com.simon.simple.R;
import com.simon.baseandroid.util.DirectoryUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FileActivity extends BaseActivity {
    public static String TAG = FileActivity.class.getSimpleName();

    @BindView (R.id.file_btn_get_sd)
    Button fileBtnGetSd;
    @BindView (R.id.file_text_cache_path)
    TextView fileTextCachePath;
    @BindView (R.id.file_text_sd_path)
    TextView fileTextSdPath;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, FileActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        ButterKnife.bind(this);

    }

    @OnClick (R.id.file_btn_get_sd)
    public void onClick() {
        String all = "";
        all += "SDCARD getExternalStorageDirectory：\n" + DirectoryUtil.getSDCardPath() + "\n\n";
        all += "Cache getDownloadCacheDirectory：\n" + DirectoryUtil.getEnDownloadCachePath() + "\n\n";
        all += "Data getDataDirectory：\n" + DirectoryUtil.getDataPath() + "\n\n";
        all += "Root getRootDirectory：\n" + DirectoryUtil.getRootDirectoryPath() + "\n\n";

        all += "App Cache getCacheDir：\n" + DirectoryUtil.getCachePath(FileActivity.this) + "\n\n";
        all += "App ExCache getExternalCacheDir：\n" + DirectoryUtil.getExCachePath(FileActivity.this) + "\n\n";
        all += "App Picture ExCache getExternalFilesDir：\n" + DirectoryUtil.getExPictureCachePath(FileActivity.this) + "\n\n";
        all += "App Download ExCache getExternalFilesDir：\n" + DirectoryUtil.getExDownloadCachePath(FileActivity.this) + "\n\n";
        fileTextCachePath.setText(all);
    }
}
