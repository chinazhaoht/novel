package com.cyyz.image;

import java.io.OutputStream;

/**
 * author: zhoaht
 * time:   2016/1/20
 */
public interface SimpleImage {

    public OutputStream ratio(float r,OutputStream outputStream);

    public OutputStream compressWidthTo400(OutputStream outputStream);

}
