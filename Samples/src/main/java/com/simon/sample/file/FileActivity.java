package com.simon.sample.file;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.simon.baseandroid.BaseActivity;
import com.simon.baseandroid.util.DirectoryUtil;
import com.simon.baseandroid.util.LogUtil;
import com.simon.sample.R;
import com.simon.sample.file.util.FileUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FileActivity extends BaseActivity {
    public static String TAG = FileActivity.class.getSimpleName();

    @BindView(R.id.file_btn_get_sd)
    Button fileBtnGetSd;
    @BindView(R.id.file_text_cache_path)
    TextView fileTextCachePath;
    @BindView(R.id.file_btn_mkdirs)
    Button fileBtnMkdirs;
    @BindView(R.id.file_btn_get_photo)
    Button fileBtnGetPhoto;

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

    @OnClick({R.id.file_btn_get_sd, R.id.file_btn_mkdirs, R.id.file_btn_get_photo})
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
                all += "App Picture Cache getExPictureCachePath：\n" + DirectoryUtil.getExPictureCachePath(FileActivity.this) + "\n\n";
                all += "App Download Cache getExDownloadCachePath：\n" + DirectoryUtil.getExDownloadCachePath(FileActivity.this) + "\n\n";
                fileTextCachePath.setText(all);
                break;
            case R.id.file_btn_mkdirs:
                boolean isSuccess = FileUtil.createNewFile(DirectoryUtil.getSDCardPath() + "EngineerTools", "1.txt");
                LogUtil.i(TAG, "onClick: isSuccess=" + isSuccess);
                break;
            case R.id.file_btn_get_photo:
                getPhoto();
                break;
        }
    }

    private void getPhoto() {
        File file = new File(Environment.getExternalStorageDirectory(), "/aaa/" + System.currentTimeMillis() + ".jpg");

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        String authorities = "com.simon.sample";
        //通过FileProvider创建一个content类型的Uri
        Uri imageUri = FileProvider.getUriForFile(this, authorities, file);

        Intent intent = new Intent();
        //添加这一句表示对目标应用临时授权该Uri所代表的文件
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//设置Action为拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
        startActivityForResult(intent, 1006);
    }
}
