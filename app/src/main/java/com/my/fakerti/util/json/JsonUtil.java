package com.my.fakerti.util.json;
/**
 * Created by Administrator on 2017/3/31 0031.
 */

import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Json工具类
 * 第三方库(FastJson)
 * created by Mr.C at2017年3月31日 17:30:19
 **/
public class JsonUtil {

    private static  String TAG ="com.my.fakerti.util.json.JsonUtil";


        public static String convertObjectToJson(Object object){
            SerializerFeature features[] = { SerializerFeature.QuoteFieldNames,
                    SerializerFeature.WriteNullListAsEmpty,
                    SerializerFeature.WriteNullStringAsEmpty,
                    SerializerFeature.WriteNullNumberAsZero,
                    SerializerFeature.WriteNullBooleanAsFalse,
                    SerializerFeature.WriteSlashAsSpecial,
                    SerializerFeature.BrowserCompatible,
                    SerializerFeature.DisableCircularReferenceDetect,
                    SerializerFeature.WriteDateUseDateFormat };
            try {
                return JSON.toJSONString(object, features);
            } catch (Exception e) {
                Log.e(TAG, e.toString());
                return "";
            }
        }


    public static <T> T convertJsonToObject(String json, Class<T> clazz) {
        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return null;
        }
    }
}
