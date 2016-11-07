package org.fengzheng.syncdb.util;

import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by Tibers on 16/11/7.
 */
public class SerializeUtil {

    public static JSONObject byteToJson(byte[] data) throws UnsupportedEncodingException {
        return JSONObject.parseObject(new String(data,"UTF-8"));
    }
}
