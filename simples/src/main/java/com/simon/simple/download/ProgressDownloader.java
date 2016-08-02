package com.simon.simple.download;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
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
    public static String FILE_NAME = "download_file17.apk";

    //缓存大小
    private static final int READ_MAX_SIZE = 1024 * 4;

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
        private OkHttpClient okHttpClient;
        private File outputFile;
        private Call call;
        private long currentLength;

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
            init();
            download(currentLength);
            return Builder.this;
        }

        /**
         * 暂停下载
         *
         * @return
         */
        public Builder pauseTask() {
            cancle();
            return Builder.this;
        }

        /**
         * 继续下载
         *
         * @return
         */
        public Builder cotinueTask() {
            startTask();
            return Builder.this;
        }

        /**
         * 取消下载
         *
         * @return
         */
        public Builder stopTask() {
            cancle();
            return Builder.this;
        }

        /**
         * 返回对象
         *
         * @return
         */
        public ProgressDownloader build() {
            return instance;
        }

        /**
         * startsPoint指定开始下载的点
         *
         * @param startsPoint
         */
        private void download(final long startsPoint) {
            Log.i(TAG, "download: startsPoint=" + startsPoint);
            call = newCall(startsPoint);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i(TAG, "onFailure: ");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.i(TAG, "onResponse: startsPoint=" + startsPoint);
                    writeToSDCard(response, startsPoint);
                }
            });
        }

        /**
         * 取消下载
         */
        public void cancle() {
            if (call != null) {
                call.cancel();
            }
        }

        /**
         * 向文件中写入数据
         *
         * @param response
         * @param startsPoint
         */
        private void writeToSDCard(Response response, long startsPoint) {
            ResponseBody body = response.body();
            InputStream inputStream = body.byteStream();
            FileChannel channelOut = null;
            // 随机访问文件，可以指定断点续传的起始位置
            RandomAccessFile randomAccessFile = null;
            try {
                randomAccessFile = new RandomAccessFile(outputFile, "rw");
                randomAccessFile.seek(currentLength);
                //Chanel NIO中的用法，由于RandomAccessFile没有使用缓存策略，直接使用会使得下载速度变慢，
                // 亲测缓存下载3.3秒的文件，用普通的RandomAccessFile需要20多秒。
                //但是，但是，但是，重要的事情说三遍，用这种方法，File直接就变成了文件的大小，而不是已下载的大小。
                //                channelOut = randomAccessFile.getChannel();
                // 内存映射，直接使用RandomAccessFile，是用其seek方法指定下载的起始位置，使用缓存下载，在这里指定下载位置。
                //                MappedByteBuffer mappedBuffer = channelOut.map(FileChannel.MapMode.READ_WRITE, startsPoint, body.contentLength());
                byte[] buffer = new byte[READ_MAX_SIZE];
                int len;
                while (true) {
                    len = inputStream.read(buffer);
                    if (len == -1) {
                        break;
                    }
                    randomAccessFile.write(buffer, 0, len);
                    //                    mappedBuffer.put(buffer, 0, len);
                    Log.i(TAG, "writeToSDCard: outputFile.length()=" + outputFile.length());
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

        /**
         * 初始化
         */
        private void init() {
            initClient();
            initPath();
            initFile();
        }

        /**
         * 初始化存储文件，并获取已存储的长度
         */
        private void initFile() {
            outputFile = new File(FILE_PATH, FILE_NAME);
            currentLength = outputFile.length();
        }

        /**
         * 初始化文件路径
         */
        private void initPath() {
            File fileDirectory = new File(FILE_PATH);
            if (!fileDirectory.exists() || !fileDirectory.isDirectory()) {
                fileDirectory.mkdirs();
            }
        }

        /**
         * 初始化Client
         */
        private void initClient() {
            okHttpClient = getProgressClient();
        }

        /**
         * 每次下载需要新建新的Call对象
         *
         * @param startPoints
         * @return
         */
        private Call newCall(long startPoints) {
            Log.i(TAG, "newCall: startPoints=" + startPoints);

            Request request = new Request.Builder()
                    .url(url)
                    .header("RANGE", "bytes=" + startPoints + "-")//断点续传要用到的，指示下载的区间
                    .build();
            return okHttpClient.newCall(request);
        }

        /**
         * 添加了拦截器的Client
         *
         * @return
         */
        private OkHttpClient getProgressClient() {
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
    }

    /**
     * 最基本的connection可以进行断点续传。已成功
     */
    /*private void newTask(Context context, String str) {
        initFile();
        // Open connection to URL.
        HttpURLConnection connection = null;
        try {
            URL url = new URL(str);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Range", "bytes=" + fileSizeDownloaded + "-");
            // connection.setRequestMethod("GET");
            connection.setConnectTimeout(1000 * 5);
            connection.setReadTimeout(1000 * 3);
            // connection.setUseCaches(true);
            // Connect to server.
            connection.connect();

            RandomAccessFile randomFile = new RandomAccessFile(FILE_STORE_PATH + File.separator + FILE_NAME, "rw");
            inputStream = connection.getInputStream();

            randomFile.seek(fileSizeDownloaded);

            long lastTime = System.currentTimeMillis();
            long currentTime;
            int readLength;
            buffer = new byte[READ_MAX_SIZE];
            while (true) {
                readLength = inputStream.read(buffer);
                if (readLength == -1) {
                    break;
                }
                randomFile.write(buffer, 0, readLength);
                fileSizeDownloaded += readLength;
                currentTime = System.currentTimeMillis();

                //Modified By xw at 2016/7/29 Explain：每隔500ms发送一次，避免UI阻塞（不用睡眠也不会影响下载速度）
                if (currentTime - lastTime >= 1000) {
                    Log.i(TAG, "continueDownload: fileSizeDownloaded=" + fileSizeDownloaded);
                    onDownloadProgressListener.updateProgress(fileSizeDownloaded, fileSizeTotal, false);
                    lastTime = currentTime;
                }

                if (fileSizeDownloaded >= 7280997 && downloadIndex == 1) {
                    downloadIndex = downloadIndex + 1;
                    break;
                }
            }
            Log.i(TAG, "continueDownload: randomFile.length()=" + randomFile.length());
            randomFile.close();

            onDownloadProgressListener.updateProgress(fileSizeDownloaded, fileSizeTotal, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
}
