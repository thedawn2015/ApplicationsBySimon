package com.simon.simpleretrofit.rest.model;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;

/**
 * Created by xw on 2016/7/27.
 */
public class ProgressResponseBody extends ResponseBody {

    @Override
    public MediaType contentType() {
        return null;
    }

    @Override
    public long contentLength() {
        return 0;
    }

    @Override
    public BufferedSource source() {
        return null;
    }
}
