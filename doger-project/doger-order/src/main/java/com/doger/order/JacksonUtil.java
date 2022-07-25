package com.doger.order;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class JacksonUtil {

    public static String toJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception ex) {
            log.error("convert error", ex);
            return null;
        }

    }


    public static Object toObject(String jsonStr,Class clazz){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonStr, clazz);
        }catch (Exception ex){
            log.error("convert java object error", ex);
            return null;
        }
    }
}
