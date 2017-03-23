package cn.com.hong5.syncdb.handle;

import cn.com.hong5.api.entity.user.UserMessageEntity;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tibers on 17/3/21.
 */
public class RunnableUtil {
    protected Map<String, Object> collectNeedUpdateParam(UserMessageEntity userMessageEntity) throws IllegalAccessException {
        Map<String, Object> result = new HashMap<>();
        Class userCla = userMessageEntity.getClass();
        Field[] fs = userCla.getDeclaredFields();
        for (int i = 0; i < fs.length; i++) {
            Field f = fs[i];
            f.setAccessible(true); //设置些属性是可以访问的
            Object val = f.get(userMessageEntity);//得到此属性的值
            if (val != null && ! StringUtils.isEmpty(val.toString())) {
                result.put(f.getName(), val);
            }
        }
        return result;
    }

}
