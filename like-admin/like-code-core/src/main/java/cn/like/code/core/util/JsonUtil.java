package cn.like.code.core.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jodd.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author like
 * @date 2021/5/18 16:47
 */
public class JsonUtil {
    private final static Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        //静默出现未知属性时的异常
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //允许json=""的空字符换入参
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        // new JsonPropertyFilter(JsonConfig.getJsonFilterDTOMap());
    }

    /**
     * jackson 实现 - obj转json
     *
     * @param object 对象
     * @return {@link String}
     */
    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * jackson 实现 - json转obj
     *
     * @param json  json
     * @param clazz clazz
     * @return {@link T}
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return (T) objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * jackson 实现 - json转obj
     *
     * @param json json
     * @param type 类型
     * @return {@link T}
     */
    public static <T> T fromJson(String json, TypeReference<T> type) {
        try {
            return (T) objectMapper.readValue(json, type);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将Object对象转换为json字符串，若不能转换将String.valueOf(obj)
     */
    public static String toJSONString(Object obj) {
        if (BaseUtil.isNull(obj)) {
            return null;
        }
        if (obj instanceof String) {
            return obj.toString();
        }

        try {
            return JSONUtil.toJsonStr(obj);
        } catch (JSONException e) {
            return String.valueOf(obj);
        } catch (UnsupportedOperationException e) {
            return String.valueOf(obj);
        } catch (IllegalStateException e) {
            return String.valueOf(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将obj转化为class对应的泛型对象
     *
     * @param obj
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T parseObject(Object obj, Class<T> clazz) {
        if (obj == null) {
            return null;
        }
        try {
            if (obj instanceof String) {
                obj = JSONUtil.parse(obj.toString());
            }
            String jsonStr = toJSONString(obj);
            return JSONUtil.toBean(jsonStr, clazz);
        } catch (JSONException e) {
            return (T) obj;
        } catch (UnsupportedOperationException e) {
            return (T) obj;
        } catch (IllegalStateException e) {
            return (T) obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json数格式的字符串或者list转换为Class对应泛型对象的集合
     *
     * @param listOrJsonStr
     * @param clazz
     * @return List<T>
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> parseArray(Object listOrJsonStr, Class<T> clazz) {
        if (listOrJsonStr == null || clazz == null) {
            return null;
        }
        String arrStr = listOrJsonStr.toString();
        if (StringUtil.isEmpty(arrStr)) {
            return null;
        }
        try {
            if (listOrJsonStr instanceof List) {
                arrStr = JsonUtil.toJSONString(listOrJsonStr);
            }
            return JsonUtil.parseArray(arrStr, clazz);
        } catch (Exception e) {
            List<T> retList = new ArrayList<>();
            retList.add((T) arrStr);
            return retList;
        }
    }

    /**
     * <p>
     * 将fromList中的数据转换为泛型list数据
     * </p>
     * <p>
     * <pre>
     *
     * </pre>
     *
     * @param fromList : List : 任意泛型Collection
     * @param clazz    : 泛型class
     * @return 泛型list
     * @author daiqi
     * @date 2017年12月11日 下午4:46:07
     */
    public static <T> List<T> fromListToTList(Collection<?> fromList, Class<T> clazz) {
        List<T> retList = new ArrayList<>();
        if (CollectionUtil.isEmpty(fromList)) {
            return retList;
        }
        for (Object obj : fromList) {
            retList.add(JsonUtil.parseObject(obj, clazz));
        }
        return retList;
    }
}
