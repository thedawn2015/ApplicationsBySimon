package com.simon.sample.file;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
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

/**
 * desc: 适配7.0 照相
 * author: xw
 * time: 2017/3/29
 */
public class FileActivity extends BaseActivity {
    public static String TAG = FileActivity.class.getSimpleName();

    @BindView(R.id.file_btn_get_sd)
    Button fileBtnGetSd;
    @BindView(R.id.file_text_cache_path)
    TextView fileTextCachePath;
    @BindView(R.id.file_btn_mkdirs)
    Button fileBtnMkdirs;
    @BindView(R.id.file_btn_take_photo)
    Button fileBtnTakePhoto;
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

    @OnClick({R.id.file_btn_get_sd, R.id.file_btn_mkdirs, R.id.file_btn_take_photo, R.id.file_btn_get_photo})
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
            case R.id.file_btn_take_photo:
                takePhoto();
                break;
            case R.id.file_btn_get_photo:
                getPhoto();
                break;
        }
    }

    /**
     * 读取照片，并裁剪
     */
    private void getPhoto() {
        File file = new File(Environment.getExternalStorageDirectory(), "/aaa/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //通过FileProvider创建一个content类型的Uri
//            Uri imageUri = Uri.fromFile(new File("/storage/emulated/0/aaa/1490769124978.jpg"));
            Uri imageUri = FileProvider.getUriForFile(this, "com.simon.sample.fileprovider", new File("/storage/emulated/0/aaa/1490769124978.jpg"));
            //
            Uri outputUri = Uri.fromFile(file);
//            Uri outputUri = FileProvider.getUriForFile(this, "com.simon.sample.fileprovider", file);
            startPhotoZoom(outputUri, imageUri);
        } else {
            Uri outputUri = Uri.fromFile(file);
            Uri imageUri = Uri.fromFile(new File("/storage/emulated/0/aaa/1490769124978.jpg"));
            startPhotoZoom(outputUri, imageUri);
        }
    }

    /**
     * 裁剪
     *
     * @param outputUri
     * @param imageUri
     */
    private void startPhotoZoom(Uri outputUri, Uri imageUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        }
        intent.setDataAndType(imageUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        //you must setup this
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        startActivityForResult(intent, 1008);
    }

    /**
     * 相机拍照
     */
    private void takePhoto() {
        File file = new File(Environment.getExternalStorageDirectory(), "/aaa/" + System.currentTimeMillis() + ".jpg");

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        String authorities = "com.simon.sample.fileprovider";

        Uri imageUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //通过FileProvider创建一个content类型的Uri
            imageUri = FileProvider.getUriForFile(this, authorities, file);
        } else {
            imageUri = Uri.fromFile(file);
        }

        //设置Action为拍照
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//将拍取的照片保存到指定URI
        startActivityForResult(intent, 1006);
    }
}
