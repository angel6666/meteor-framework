package cn.meteor.module.core.shiro.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.Session;

import com.beust.jcommander.internal.Lists;

public class SerializeUtils extends SerializationUtils {

    public static String serializeToString(Serializable obj) {
        try {
            byte[] value = serialize(obj);
            return Base64.encodeToString(value);
        } catch (Exception e) {
            throw new RuntimeException("serialize session error", e);
        }
    }

    public static Session deserializeFromString(String base64) {
        try {
            byte[] objectData = Base64.decode(base64);
            return deserialize(objectData);
        } catch (Exception e) {
            throw new RuntimeException("deserialize session error", e);
        }
    }

    public static <T> Collection<T> deserializeFromStringController(Collection<String> base64s) {
        try {
            List<T> list = Lists.newLinkedList();
            for (String base64 : base64s) {
                byte[] objectData = Base64.decode(base64);
                T t = deserialize(objectData);
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            throw new RuntimeException("deserialize session error", e);
        }
    }
}
