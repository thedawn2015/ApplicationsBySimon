package com.simon.simple.file;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.simon.baseandroid.BaseActivity;
import com.simon.baseandroid.util.DirectoryUtil;
import com.simon.baseandroid.util.LogUtil;
import com.simon.simple.R;
import com.simon.simple.file.util.FileUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FileActivity extends BaseActivity {
    public static String TAG = FileActivity.class.getSimpleName();

    @BindView (R.id.file_btn_get_sd)
    Button fileBtnGetSd;
    @BindView (R.id.file_text_cache_path)
    TextView fileTextCachePath;
    @BindView (R.id.file_btn_mkdirs)
    Button fileBtnMkdirs;

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

    @OnClick ({R.id.file_btn_get_sd, R.id.file_btn_mkdirs})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.file_btn_get_sd:
                String all = "";
                all += "SDCard getExternalStorageDirectory：\n" + DirectoryUtil.getSDCardPath() + "\n\n";
                all += "Cache getDownloadCacheDirectory：\n" + DirectoryUtil.getEnDownloadCachePath() + "\n\n";
                all += "Data getDataDirectory：\n" + DirectoryUtil.getDataPath() + "\n\n";
                all += "Root getRootDirectory：\n" + DirectoryUtil.getRootDirectoryPath() + "\n\n";

                all += "App Cache getCacheDir：\n" + DirectoryUtil.getCachePath(FileActivity.this) + "\n\n";
                all += "App Cache getExternalCacheDir：\n" + DirectoryUtil.getExCachePath(FileActivity.this) + "\n\n";
                all += "App Picture Cache getExternalFilesDir：\n" + DirectoryUtil.getExPictureCachePath(FileActivity.this) + "\n\n";
                all += "App Download Cache getExternalFilesDir：\n" + DirectoryUtil.getExDownloadCachePath(FileActivity.this) + "\n\n";
                fileTextCachePath.setText(all);
                break;
            case R.id.file_btn_mkdirs:
                boolean isSuccess = FileUtil.createFilePath(DirectoryUtil.getSDCardPath() + "EngineerTools");
                LogUtil.i(TAG, "onClick: isSuccess=" + isSuccess);
                break;
        }
    }
}
