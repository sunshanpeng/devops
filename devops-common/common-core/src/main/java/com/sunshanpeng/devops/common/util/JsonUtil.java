package com.sunshanpeng.devops.common.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.time.temporal.ChronoField;
import java.util.*;

public class JsonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final DateTimeFormatter MY_DATE_TIME;
    private static final DateTimeFormatter MY_DATE;

    private JsonUtil() {
    }

    private static Optional<String> writeValue(Object object) {
        if (object == null) {
            return Optional.empty();
        } else if (object instanceof String) {
            return Optional.of((String) object);
        } else {
            try {
                return Optional.of(OBJECT_MAPPER.writeValueAsString(object));
            } catch (JsonProcessingException var2) {
                LOGGER.error("writeJsonValue error, ", var2);
                return Optional.empty();
            }
        }
    }

    private static <T> Optional<T> readValue(String json, Class<T> t) {
        if (json == null) {
            return Optional.empty();
        } else {
            try {
                return Optional.of(OBJECT_MAPPER.readValue(json, t));
            } catch (Exception var3) {
                LOGGER.error("readJsonValue error, ", var3);
                return Optional.empty();
            }
        }
    }

    private static <T> Optional<T> readValue(String json, TypeReference<T> t) {
        if (json == null) {
            return Optional.empty();
        } else {
            try {
                return Optional.of(OBJECT_MAPPER.readValue(json, t));
            } catch (Exception var3) {
                LOGGER.error("readJsonValue error, ", var3);
                return Optional.empty();
            }
        }
    }

    public static Optional<String> toJSONString(Object object) {
        return writeValue(object);
    }

    public static <T> Optional<T> toBean(String json, Class<T> t) {
        return readValue(json, t);
    }

    public static <T> Optional<T> toBean(String json, TypeReference<T> t) {
        return readValue(json, t);
    }

    public static <K, V> Map<K, V> toMap(String json) {
        Optional<Map<K, V>> optionalMap = readValue(json, new TypeReference<Map<K, V>>() {
        });
        return optionalMap.orElse(Collections.emptyMap());
    }

    static {
        MY_DATE_TIME = (new DateTimeFormatterBuilder()).appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD).appendLiteral('-').appendValue(ChronoField.MONTH_OF_YEAR, 2).appendLiteral('-').appendValue(ChronoField.DAY_OF_MONTH, 2).appendLiteral(' ').appendValue(ChronoField.HOUR_OF_DAY, 2).appendLiteral(':').appendValue(ChronoField.MINUTE_OF_HOUR, 2).optionalStart().appendLiteral(':').appendValue(ChronoField.SECOND_OF_MINUTE, 2).toFormatter();
        MY_DATE = (new DateTimeFormatterBuilder()).appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD).appendLiteral('-').appendValue(ChronoField.MONTH_OF_YEAR, 2).appendLiteral('-').appendValue(ChronoField.DAY_OF_MONTH, 2).toFormatter();
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(MY_DATE_TIME));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(MY_DATE_TIME));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(MY_DATE));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(MY_DATE));
        OBJECT_MAPPER.registerModule(javaTimeModule);
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        OBJECT_MAPPER.setSerializationInclusion(Include.NON_NULL);
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
}
