package com.simon.baseandroid.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * desc: 图片压缩
 * author: xw
 * time: 2017/3/29
 */
public class BitmapCompressUtil {
    public static Bitmap doCommonCompress(String imagePath) {
        try {
            return compressImage(imagePath, 720);
//            return compressImage(imagePath, 480);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static Bitmap compressImage(String imagePath, int bitmapMaxWidth) throws Exception {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);
        int height = options.outHeight;
        int width = options.outWidth;
        int reqWidth = bitmapMaxWidth;
        int reqHeight = (reqWidth * height) / width;
        options.inSampleSize = calculateInSampleSize(options, bitmapMaxWidth, reqHeight);
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
        return compressImage(Bitmap.createScaledBitmap(bitmap, bitmapMaxWidth, reqHeight, false));
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }

    private static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 80, stream);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (stream.toByteArray().length / 1024 > 300) {
            // 循环判断如果压缩后图片是否大于300kb,大于继续压缩
            options -= 10;// 每次都减少10
            stream.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, stream);// 这里压缩options%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(stream.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }
}
