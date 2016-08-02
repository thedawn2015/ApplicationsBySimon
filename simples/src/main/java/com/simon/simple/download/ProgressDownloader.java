package com.simon.simple.download;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 带进度监听功能的辅助类
 * Created by xw on 2016/8/2.
 */
public class ProgressDownloader {
    public static String TAG = ProgressDownloader.class.getSimpleName();

    //文件路径
    public static String FILE_PATH = Environment.getExternalStorageDirectory() + File.separator + "simple";
    //文件名
    public static String FILE_NAME = "download_file9.apk";

    //缓存大小
    private static final int READ_MAX_SIZE = 1024;

    private ProgressDownloader() {
    }

    /**----------------------------------构建Builder类，更方便地调用------------------------------**/
    /**
     * Builder类
     */
    public static class Builder {
        private Context context;
        private ProgressDownloader instance;
        private ProgressListener progressListener;
        private String url;
        private OkHttpClient client;
        private File destinationFile;
        private Call call;

        public Builder(Context context) {
            if (instance == null) {
                synchronized (ProgressDownloader.class) {
                    if (instance == null) {
                        instance = new ProgressDownloader();
                    }
                }
            }
            this.context = context;
        }

        /**
         * 设置存储路径
         *
         * @param path
         */
        public Builder setStoragePath(String path) {
            FILE_PATH = path;
            return Builder.this;
        }

        /**
         * 设置存储的文件名字
         *
         * @param fileName 完整的文件名称：例file.apk
         */
        public Builder setStorageFileName(String fileName) {
            FILE_NAME = fileName;
            return Builder.this;
        }

        /**
         * 设置下载链接
         *
         * @param url
         * @return
         */
        public Builder setUrl(String url) {
            this.url = url;
            return Builder.this;
        }

        /**
         * 设置进度监听
         *
         * @param progressListener
         */
        public Builder setProgressListener(ProgressListener progressListener) {
            this.progressListener = progressListener;
            return Builder.this;
        }

        /**
         * 开始下载
         *
         * @return
         */
        public Builder startTask() {
            return Builder.this;
        }

        /**
         * 暂停下载
         *
         * @return
         */
        public Builder pauseTask() {
            return Builder.this;
        }

        /**
         * 继续下载
         *
         * @return
         */
        public Builder cotinueTask() {
            return Builder.this;
        }

        //每次下载需要新建新的Call对象
        private Call newCall(long startPoints) {
            Log.i(TAG, "newCall: startPoints=" + startPoints);

            Request request = new Request.Builder()
                    .url(url)
                    .header("RANGE", "bytes=" + startPoints + "-")//断点续传要用到的，指示下载的区间
                    .build();
            return client.newCall(request);
        }

        public OkHttpClient getProgressClient() {
            // 拦截器，用上ProgressResponseBody
            Interceptor interceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                            .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                            .build();
                }
            };

            return new OkHttpClient.Builder()
                    .addNetworkInterceptor(interceptor)
                    .build();
        }

        // startsPoint指定开始下载的点
        public void download(final long startsPoint) {
            call = newCall(startsPoint);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i(TAG, "onFailure: ");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.i(TAG, "onResponse: startsPoint=" + startsPoint);
                    save(response, startsPoint);
                }
            });
        }

        public void pause() {
            if (call != null) {
                call.cancel();
            }
        }

        private void save(Response response, long startsPoint) {
            ResponseBody body = response.body();
            InputStream inputStream = body.byteStream();
            FileChannel channelOut = null;
            // 随机访问文件，可以指定断点续传的起始位置
            RandomAccessFile randomAccessFile = null;
            try {
                randomAccessFile = new RandomAccessFile(destinationFile, "rw");
                //Chanel NIO中的用法，由于RandomAccessFile没有使用缓存策略，直接使用会使得下载速度变慢，亲测缓存下载3.3秒的文件，
                // 用普通的RandomAccessFile需要20多秒。
                channelOut = randomAccessFile.getChannel();
                // 内存映射，直接使用RandomAccessFile，是用其seek方法指定下载的起始位置，使用缓存下载，在这里指定下载位置。
                MappedByteBuffer mappedBuffer = channelOut.map(FileChannel.MapMode.READ_WRITE, startsPoint, body.contentLength());
                byte[] buffer = new byte[1024];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    mappedBuffer.put(buffer, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                    if (channelOut != null) {
                        channelOut.close();
                    }
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
