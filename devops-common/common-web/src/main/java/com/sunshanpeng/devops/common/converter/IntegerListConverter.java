package com.sunshanpeng.devops.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Converter
public class IntegerListConverter implements AttributeConverter<List<Integer>, String> {
    private static final String SPLIT_CHAR = ",";
    @Override
    public String convertToDatabaseColumn(List<Integer> integers) {
        return integers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(SPLIT_CHAR));
    }

    @Override
    public List<Integer> convertToEntityAttribute(String string) {
        return Stream.of(string.split(SPLIT_CHAR))
                .map(Integer::valueOf).collect(Collectors.toList());
    }
}
