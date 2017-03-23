package cn.com.hong5.syncdb.util;

import cn.com.hong5.api.entity.user.UserMessageEntity;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by Tibers on 16/11/7.
 */
public class SerializeUtil {

    public static UserMessageEntity byteToJson(byte[] data) throws UnsupportedEncodingException {
        return JSONObject.parseObject(new String(data,"UTF-8"), UserMessageEntity.class);
    }
}
