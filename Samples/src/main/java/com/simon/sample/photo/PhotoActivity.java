package com.simon.sample.photo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.simon.baseandroid.BaseActivity;
import com.simon.sample.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * desc: 图片选择
 * author: xw
 * time: 2017/2/9
 */
public class PhotoActivity extends BaseActivity {
    public static final String TAG = PhotoActivity.class.getSimpleName();

    private static final String CACHE_IMG = Environment.getExternalStorageDirectory() + "/AAAA/";
    private static final int REQUEST_CAMERA = 100;

    @BindView(R.id.photo_btn_photos)
    Button photoBtnPhotos;
    @BindView(R.id.photo_btn_camera)
    Button photoBtnCamera;
    @BindView(R.id.photo_image_view)
    ImageView photoImageView;

    String photoName;

    public static void launch(Activity activity) {
        Intent intent = new Intent(activity, PhotoActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.photo_btn_photos, R.id.photo_btn_camera})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.photo_btn_photos:

                break;
            case R.id.photo_btn_camera:
                getCameraOld();
                break;
        }
    }

    private void getCameraOld() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            File newFile = createImageFile();
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
            startActivityForResult(intent, REQUEST_CAMERA);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * storage/emulated/0/.REAPP/._Image:SDCard路径加上.REAPP/._Image/temp.img
     *
     * @return 接收拍照返回数据位置,
     * @throws IOException
     */
    private File createImageFile() throws IOException {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhMMss", Locale.CHINA);
        String fileName = "sample_" + dateFormat.format(date) + ".png";
        File dir = new File(CACHE_IMG);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // 以上为了规避用户删除了._Image目录，报错java.io.IOException: open failed: ENOENT (No such file or directory)
        File file = new File(dir, fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        photoName = fileName;

        return file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA) {
            if (resultCode == RESULT_OK) {
                Bitmap bitmapReturn = BitmapFactory.decodeFile(CACHE_IMG + photoName);
                photoImageView.setImageBitmap(bitmapReturn);
            }
        }
    }
}
