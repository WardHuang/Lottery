package io.lottery.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class EntityMapper {

    public static <T> T mapperToEntity(Map<String,Object> source,Class<T> entityClass) throws Exception {
        T entity = entityClass.getDeclaredConstructor().newInstance();
        Field[] fields = entity.getClass().getDeclaredFields();

        for (Field field : fields){
            field.setAccessible(true);
            for (Map.Entry<String,Object> entry : source.entrySet()){
                String fieldName = field.getName();
                String mapKey = entry.getKey();

                if(fieldName.equalsIgnoreCase(mapKey)){
                    field.set(entity,entry.getValue());
                    break;
                }
            }
        }
        return entity;
    }

    public static Map<String, Object> convertToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // 设置为可访问
            map.put(field.getName(), field.get(obj)); // 将字段名和对应值放入 Map
        }

        return map;
    }
}
