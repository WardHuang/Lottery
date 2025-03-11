package io.lottery.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import feign.Response;
import feign.Util;
import feign.codec.Decoder;
import io.lottery.utils.CamelCaseConverter;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

/**
 * <p>基于jacksonDecoder自定义解码器 </p>
 * <p><b color="yellow">出于规范的原因</b>自定义修改返回值字段，仅此而已，最简单的做法是将接受返回值的Entity对应的字段改成接收到的字段，大小写敏感</p>
 */
public class CamelCaseDecoder implements Decoder {



    @Override
    public Object decode(Response response, Type type) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModules(Collections.<Module>emptyList());
        //Map<String, Object> map = objectMapper.readValue(responseBody, Map.class);

        if (response.status() == 404 || response.status() == 204)
            return Util.emptyValueOf(type);
        if (response.body() == null)
            return null;
        Reader reader = response.body().asReader(response.charset());
        if (!reader.markSupported()) {
            reader = new BufferedReader(reader, 1);
        }
        try {
            // Read the first byte to see if we have any data
            reader.mark(1);
            if (reader.read() == -1) {
                return null; // Eagerly returning null avoids "No content to map due to end-of-input"
            }
            reader.reset();
            Map<String, Object> map = objectMapper.readValue(reader, Map.class);
            List<Object> objects = (List)map.get("result");
            List<Object> newObjects = changeMapContainListName(objects,objectMapper);
            Map<String, Object> newMap = new HashMap<>();
            map.entrySet().forEach(entry->{
                if (entry.getKey().equals("result")){
                    newMap.put(entry.getKey(), newObjects);
                } else {
                    newMap.put(entry.getKey(), entry.getValue());
                }
            });
            // 将 Map 转换为 JSON 字符串
            String json = objectMapper.writeValueAsString(newMap);

            // 将 JSON 字符串转换为字节输入流
            InputStream inputStream = new ByteArrayInputStream(json.getBytes());

            byte[] responseBody = toByteArray(inputStream);
            Response newResponse = response.toBuilder().body(responseBody).build();
            Reader newReader = newResponse.body().asReader(newResponse.charset());
            return objectMapper.readValue(newReader, objectMapper.constructType(type));
        } catch (RuntimeJsonMappingException e) {
            if (e.getCause() != null && e.getCause() instanceof IOException) {
                throw IOException.class.cast(e.getCause());
            }
            throw e;
        }
    }

    private List<Object> changeMapContainListName(List<Object> objects,ObjectMapper objectMapper){
        List<Object> newObjects = new ArrayList<>();
        objects.forEach(item->{
            Map<String,Object> itemMap = objectMapper.convertValue(item,Map.class);
            Map<String,Object> newItemMap = new HashMap<>();
            itemMap.entrySet().forEach(entry->{
                Object newValue = entry.getValue();
                if(entry.getKey().equals("prizegrades")){
                    newValue = changeMapContainListName((List)itemMap.get("prizegrades"),objectMapper);
                }
                String newKey = CamelCaseConverter.toCamelCase(entry.getKey());
                newItemMap.put(newKey,newValue);
            });
            try {
                newObjects.add(newItemMap);
            }catch (Exception e) {
                e.printStackTrace();
            }
        });
        return newObjects;
    }
    private byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
