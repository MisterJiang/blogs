package com.utils;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import java.io.IOException;
import java.text.SimpleDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;

public class JsonMapper extends ObjectMapper {
    private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);
    private static JsonMapper mapper;

    public JsonMapper() {
        this((Include)null);
    }

    public JsonMapper(Include include) {
        if(include != null) {
            this.setSerializationInclusion(include);
        }

        this.enableSimple();
        this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        this.setFilters((new SimpleFilterProvider()).setFailOnUnknownId(false));
        this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static JsonMapper getInstance() {
        if(mapper == null) {
            mapper = new JsonMapper();
        }

        return mapper;
    }

    public static JsonMapper nonEmptyMapper() {
        return new JsonMapper(Include.NON_EMPTY);
    }

    public static JsonMapper nonDefaultMapper() {
        return new JsonMapper(Include.NON_DEFAULT);
    }

    public JsonMapper enableSimple() {
        this.configure(Feature.ALLOW_SINGLE_QUOTES, true);
        this.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        return this;
    }

    public JsonMapper registerHibernate4Module() {
        Hibernate4Module mod = new Hibernate4Module();
        mod.configure(Hibernate4Module.Feature.FORCE_LAZY_LOADING, true);
        this.registerModule(mod);
        return this;
    }

    public String toJson(Object object) {
        try {
            return this.writeValueAsString(object);
        } catch (IOException var3) {
            logger.warn("write to json string error:" + object, var3);
            return null;
        }
    }

    public String toJsonP(String functionName, Object object) {
        return this.toJson(new JSONPObject(functionName, object));
    }

    public String toJson(Object object, String[] properties) {
        return this.toJson(object, object.getClass(), properties);
    }

    public String toJson(Object object, Class<?> clazz, String[] properties) {
        try {
            return this.writer((new SimpleFilterProvider()).addFilter(AnnotationUtils.getValue(AnnotationUtils.findAnnotation(clazz, JsonFilter.class)).toString(), SimpleBeanPropertyFilter.filterOutAllExcept(properties))).writeValueAsString(object);
        } catch (JsonProcessingException var5) {
            logger.warn("write to json string error:" + object, var5);
            return null;
        }
    }

    public String toJsonWithExcludeProperties(Object object, String[] properties2Exclude) {
        return this.toJsonWithExcludeProperties(object, object.getClass(), properties2Exclude);
    }

    public String toJsonWithExcludeProperties(Object object, Class<?> clazz, String[] properties2Exclude) {
        try {
            return this.writer((new SimpleFilterProvider()).addFilter(AnnotationUtils.getValue(AnnotationUtils.findAnnotation(clazz, JsonFilter.class)).toString(), SimpleBeanPropertyFilter.serializeAllExcept(properties2Exclude))).writeValueAsString(object);
        } catch (JsonProcessingException var5) {
            logger.warn("write to json string error:" + object, var5);
            return null;
        }
    }

    public <T> T fromJson(String jsonString, Class<T> clazz) {
        if(StringUtils.isEmpty(jsonString)) {
            return null;
        } else {
            try {
                return this.readValue(jsonString, clazz);
            } catch (IOException var4) {
                logger.warn("parse json string error:" + jsonString, var4);
                return null;
            }
        }
    }

    public <T> T fromJson(String jsonString, Class<?> collectionClass, Class... elementClasses) throws Exception {
        if(StringUtils.isEmpty(jsonString)) {
            return null;
        } else {
            JavaType javaType = this.createCollectionType(collectionClass, elementClasses);
            return this.fromJson(jsonString, javaType);
        }
    }

    public <T> T fromJson(String jsonString, JavaType javaType) {
        if(StringUtils.isEmpty(jsonString)) {
            return null;
        } else {
            try {
                return this.readValue(jsonString, javaType);
            } catch (IOException var4) {
                logger.warn("parse json string error:" + jsonString, var4);
                return null;
            }
        }
    }

    public JavaType createCollectionType(Class<?> collectionClass, Class... elementClasses) {
        return this.getTypeFactory().constructParametrizedType(collectionClass, collectionClass, elementClasses);
    }

    public <T> T update(String jsonString, T object) {
        try {
            return this.readerForUpdating(object).readValue(jsonString);
        } catch (JsonProcessingException var4) {
            logger.warn("update json string:" + jsonString + " to object:" + object + " error.", var4);
        } catch (IOException var5) {
            logger.warn("update json string:" + jsonString + " to object:" + object + " error.", var5);
        }

        return null;
    }

    public JsonMapper enableEnumUseToString() {
        this.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        this.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        return this;
    }

    public JsonMapper enableJaxbAnnotation() {
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        this.registerModule(module);
        return this;
    }

    public ObjectMapper getMapper() {
        return this;
    }

    public static String toJsonString(Object object) {
        return getInstance().toJson(object);
    }

    public static Object fromJsonString(String jsonString, Class<?> clazz) {
        return getInstance().fromJson(jsonString, clazz);
    }
}