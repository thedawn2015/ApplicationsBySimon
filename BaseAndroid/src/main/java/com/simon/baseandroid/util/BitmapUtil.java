package com.simon.baseandroid.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapUtil {

    /**
     * @param bitmap
     *         原图
     * @param edgeLength
     *         希望得到的正方形部分的边长
     * @return 缩放截取正中部分后的位图。
     */
    public static Bitmap centerSquareScaleBitmap(Bitmap bitmap, int edgeLength) {
        if (null == bitmap || edgeLength <= 0) {
            return null;
        }

        Bitmap result = bitmap;
        int widthOrg = bitmap.getWidth();
        int heightOrg = bitmap.getHeight();

        if (widthOrg >= edgeLength && heightOrg >= edgeLength) {
            //压缩到一个最小长度是edgeLength的bitmap
            int longerEdge = (int) (edgeLength * Math.max(widthOrg, heightOrg) / Math.min(widthOrg, heightOrg));
            int scaledWidth = widthOrg > heightOrg ? longerEdge : edgeLength;
            int scaledHeight = widthOrg > heightOrg ? edgeLength : longerEdge;
            Bitmap scaledBitmap;

            try {
                scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);
            } catch (Exception e) {
                return null;
            }

            //从图中截取正中间的正方形部分。
            int xTopLeft = (scaledWidth - edgeLength) / 2;
            int yTopLeft = (scaledHeight - edgeLength) / 2;

            try {
                result = Bitmap.createBitmap(scaledBitmap, xTopLeft, yTopLeft, edgeLength, edgeLength);
                scaledBitmap.recycle();
            } catch (Exception e) {
                return null;
            }
        }

        return result;
    }

    /**
     * 获得指定大小比例的缩略图(压缩)
     *
     * @param bm
     *         原图片位图对象
     * @return 缩略图
     */
    public static Bitmap getThumbBitmap(Bitmap bm, float width, float height) {
        if (bm != null) {
            Options opts = new Options();
            opts.inJustDecodeBounds = true;
            byte[] data = Bitmap2Bytes(bm);
            BitmapFactory.decodeByteArray(data, 0, data.length, opts);
            float x = opts.outWidth / width;
            float y = opts.outHeight / height;
            opts.inSampleSize = (int) ((x + y) / 2);
            opts.inJustDecodeBounds = false;
            bm = BitmapFactory.decodeByteArray(data, 0, data.length, opts);
        }
        return bm;
    }

    public static Bitmap getThumbBitmap(String path, float width, float height) {
        Options opts = new Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, opts);
        float x = opts.outWidth / width;
        float y = opts.outHeight / height;
        opts.inSampleSize = (int) ((x + y) / 2);
        opts.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, opts);
    }

    /**
     * 将位图转为字节数组(JEPG)
     *
     * @param bm
     *         位图对象
     * @return 图片字节数组
     */
    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    public static Bitmap Bytes2Bitamp(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

    /**
     * 获得圆角图片(ARGB_8888)
     *
     * @param bitmap
     *         原位图对象
     * @param roundPx
     *         圆角半径
     * @return 圆角位图
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        int color = 0xff424242;
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, w, h);
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx, boolean roundTL, boolean roundTR, boolean roundBL, boolean roundBR) {
        try {
            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            final RectF rectF = new RectF(rect);

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);// draw round
            // 4Corner
            if (!roundTL) {
                Rect rectTL = new Rect(0, 0, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
                canvas.drawRect(rectTL, paint);
            }
            if (!roundTR) {
                Rect rectTR = new Rect(bitmap.getWidth() / 2, 0, bitmap.getWidth(), bitmap.getHeight() / 2);
                canvas.drawRect(rectTR, paint);
            }
            if (!roundBR) {
                Rect rectBR = new Rect(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth(), bitmap.getHeight());
                canvas.drawRect(rectBR, paint);
            }
            if (!roundBL) {
                Rect rectBL = new Rect(0, bitmap.getHeight() / 2, bitmap.getWidth() / 2, bitmap.getHeight());
                canvas.drawRect(rectBL, paint);
            }

            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);

            return output;
        } catch (Exception ignored) {
        }
        return bitmap;
    }

    /**
     * 获取圆形图片
     */
    public static Bitmap getCroppedRoundBitmap(Bitmap bmp) {
        int bmpWidth = bmp.getWidth();
        int bmpHeight = bmp.getHeight();

        int squareWidth, squareHeight;

        // 截图起始点
        int x, y;

        Bitmap squareBitmap;
        if (bmpHeight > bmpWidth) {// 高大于宽
            squareHeight = squareWidth = bmpWidth;
            x = 0;
            y = (bmpHeight - bmpWidth) / 2;
            // 截取正方形图片
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth, squareHeight);
        } else if (bmpHeight < bmpWidth) {
            // 宽大于高
            squareWidth = squareHeight = bmpHeight;
            x = (bmpWidth - bmpHeight) / 2;
            y = 0;
            squareBitmap = Bitmap.createBitmap(bmp, x, y, squareWidth, squareHeight);
        } else {
            squareBitmap = bmp;
        }

        Bitmap output = Bitmap.createBitmap(squareBitmap.getWidth(),
                squareBitmap.getHeight(),
                Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);//图片颜色更加平滑和饱满，图像更加清晰

        canvas.drawCircle(squareBitmap.getWidth() / 2, squareBitmap.getHeight() / 2, squareBitmap.getWidth() / 2, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(squareBitmap, 0, 0, paint);

        return output;
    }

    /**
     * 位图质量压缩法，获得指定大小的图片，单位KB
     *
     * @param bm
     *         需要压缩的位图对象
     * @param size
     *         压缩后的大小(kb)
     * @return 压缩后的位图对象
     */
    public static Bitmap getThumbBitmapByQuality(Bitmap bm, int size) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        bm.compress(Bitmap.CompressFormat.JPEG, 90, baos);
        int options = 80;
        while (baos.toByteArray().length / 1024 > size) {
            // 循环判断如果压缩后图片是否大于size KB,大于继续压缩
            baos.reset();// 重置baos即清空baos
            bm.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 8;// 每次都减少8
        }
        byte[] b = baos.toByteArray();
        ByteArrayInputStream isBm = new ByteArrayInputStream(b);// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    public static byte[] getBitmapCompressedBytesByQuality(Bitmap bm, int size) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 90, stream);
        int currentLength = stream.size() / 1024;
        if (currentLength <= size) {
            return stream.toByteArray();
        }
        int options = (int) (((float) size / (float) currentLength) * 100);
        while (true) {
            currentLength = stream.size() / 1024;
            Log.i("compress bitmap", "current length:" + currentLength);
            if (currentLength <= size) {
                break;
            }
            if (options <= 0) {
                break;
            }
            stream.reset();
            bm.compress(Bitmap.CompressFormat.JPEG, options, stream);
            options -= 8;
        }
        return stream.toByteArray();
    }

    /**
     * Drawable对象转Bitmap对象
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable
                        .getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888
                        : Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * Drawable对象转Bitmap对象
     */
    public static Bitmap drawableToBitmapRoute(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable
                .getIntrinsicHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * Drawable对象转Bitmap对象
     */
    public static Bitmap transBitmapColor(Bitmap orgBitmap, int color) {
        int bitmap_w = orgBitmap.getWidth();
        int bitmap_h = orgBitmap.getHeight();
        int[] arrayColor = new int[bitmap_w * bitmap_h];
        int count = 0;
        for (int i = 0; i < bitmap_h; i++) {
            for (int j = 0; j < bitmap_w; j++) {
                /*int color1 = orgBitmap.getPixel(j, i);
                //这里也可以取出 R G B 可以扩展一下 做更多的处理，
                //暂时我只是要处理除了透明的颜色，改变其他的颜色
                if (color1 != 0) {
                } else {
                    color1 = color;
                }*/
                arrayColor[count] = color;
                count++;
            }
        }
        orgBitmap = Bitmap.createBitmap(arrayColor, bitmap_w, bitmap_h, orgBitmap.getConfig());
        return orgBitmap;
    }

    /**
     * Bitmap转化为drawable
     *
     * @param bitmap
     */
    public static Drawable bitmap2Drawable(Resources res, Bitmap bitmap) {
        return new BitmapDrawable(res, bitmap);
    }

    /**
     * Drawable 缩放
     */
    public static Drawable zoomDrawable(Resources res, Drawable drawable, float w, float h) {
        Bitmap oldbmp = drawableToBitmap(drawable);
        Bitmap newbmp = getThumbBitmap(oldbmp, w, h);
        return new BitmapDrawable(res, newbmp);
    }

    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static Bitmap base64ToBitmap(String base64Data) {
        try {
            byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Bitmap getBitmapFromFile(String photoPath) {
        return BitmapFactory.decodeFile(photoPath);
    }

    public static Bitmap compressImageFromFile(String photoPath) {
        Options newOpts = new Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(photoPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        //Modified By xw at 2017/3/7：1280*720
        float hh = 1280f;//这里设置高度为800f
        float ww = 720f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(photoPath, newOpts);
        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
    }

    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    public static Bitmap compressImage(Bitmap bitmap, int targetSize) {
        int oriSize = bitmap.getByteCount();
        if (oriSize <= targetSize) {
            return bitmap;
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        int quality = (int) ((1.0 / (float) (oriSize / targetSize)) * 100);
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream);

        return BitmapFactory.decodeStream(new ByteArrayInputStream(stream.toByteArray()), null, null);
    }

    /**
     * 根据传入的照片路径信息，获取该拍照拍摄的经纬度信息
     * 返回的数组，第一项为纬度，第二项为经度
     */
    public static float[] getLatlong(String photoPath) {
        float[] latlong = new float[]{0, 0,};
        if (Util.isNullOrEmpty(photoPath)) {
            return latlong;
        }

        try {
            ExifInterface exifInterface = new ExifInterface(photoPath);
            exifInterface.getLatLong(latlong);
        } finally {
            return latlong;
        }
    }

    /**
     * 设置指定照片拍摄的经纬度信息
     * 成功返回true，失败返回false
     */
    public static boolean setLatlong(String photoPath, double latitude, double longitude) {
        if (Util.isNullOrEmpty(photoPath)) {
            return false;
        }

        try {
            ExifInterface exif = new ExifInterface(photoPath);
            int num1Lat = (int) Math.floor(latitude);
            int num2Lat = (int) Math.floor((latitude - num1Lat) * 60);
            double num3Lat = (latitude - ((double) num1Lat + ((double) num2Lat / 60))) * 3600000;

            int num1Lon = (int) Math.floor(longitude);
            int num2Lon = (int) Math.floor((longitude - num1Lon) * 60);
            double num3Lon = (longitude - ((double) num1Lon + ((double) num2Lon / 60))) * 3600000;

            exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE, num1Lat + "/1," + num2Lat + "/1," + num3Lat + "/1000");
            exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE, num1Lon + "/1," + num2Lon + "/1," + num3Lon + "/1000");

            if (latitude > 0) {
                exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, "N");
            } else {
                exif.setAttribute(ExifInterface.TAG_GPS_LATITUDE_REF, "S");
            }
            if (longitude > 0) {
                exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, "E");
            } else {
                exif.setAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF, "W");
            }
            exif.saveAttributes();

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static File saveBitmapToFile(Bitmap bitmap, String path) {
        if (bitmap == null || Util.isNullOrEmpty(path)) {
            return null;
        }

        try {
            File bitmapFile = new File(path);
            if (bitmapFile.exists()) {
                bitmapFile.delete();
            } else {
                File folder = new File(bitmapFile.getParent());
                if (!folder.exists()) {
                    folder.mkdirs();
                }
            }
            FileOutputStream out = new FileOutputStream(bitmapFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

            return bitmapFile;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 按正方形裁切图片
     */
    public static Bitmap ImageCrop(Bitmap bitmap) {
        int w = bitmap.getWidth(); // 得到图片的宽，高
        int h = bitmap.getHeight();

        int wh = w > h ? h : w;// 裁切后所取的正方形区域边长

        int retX = w > h ? (w - h) / 2 : 0;//基于原图，取正方形左上角x坐标
        int retY = w > h ? 0 : (h - w) / 2;

        //下面这句是关键
        return Bitmap.createBitmap(bitmap, retX, retY, wh, wh, null, false);
    }

    /**
     * 旋转图片
     *
     * @param originMap
     * @param degree
     * @return
     */
    public static Bitmap getRotateBitmap(Bitmap originMap, int degree) {
        if (originMap == null) {
            return null;
        }
        int width = originMap.getWidth();
        int height = originMap.getHeight();

        Bitmap bitmapRotate;
        //宽<高，旋转90度
        if (width < height) {
            Matrix matrix = new Matrix();
            //默认90度旋转
            matrix.postRotate(degree);
            bitmapRotate = Bitmap.createBitmap(originMap, 0, 0, width, height, matrix, true);
        } else {
            bitmapRotate = originMap;
        }

        if (originMap != bitmapRotate) {
            originMap.recycle();
        }

        return bitmapRotate;
    }

    /**
     * 从path取图片文件
     *
     * @param filePath
     * @return
     */
    public static Bitmap getBitmapFromPath(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        Bitmap bitmap;
        //300K以下，进行缩放
        if (file.length() <= 300 * 1024) {
            bitmap = BitmapFactory.decodeFile(filePath);
        } else {
            bitmap = BitmapCompressUtil.doCommonCompress(filePath);
        }
        return bitmap;
    }

}
