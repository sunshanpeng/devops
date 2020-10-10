package com.sunshanpeng.devops.common.enums.dict;

import com.sunshanpeng.devops.common.base.BaseEnum;
import com.sunshanpeng.devops.common.base.Dict;
import com.sunshanpeng.devops.common.base.ValueLabelDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class DictService {
    private Map<String, String> dictDesc = new HashMap<>();

    private Map<String, List<BaseEnum>> dictMap = new HashMap<>();

    public void registerDict(Class<? extends BaseEnum> dictClass) {
        Dict dict = dictClass.getAnnotation(Dict.class);
        if (dict == null) {
            return;
        }
        dictDesc.put(dict.key(), dict.desc());
        dictMap.put(dict.key(), Arrays.asList(dictClass.getEnumConstants()));
    }

    public Map<String, String> getAllDict() {
        return dictDesc;
    }


    private List<ValueLabelDTO> getDict(String key) {
        List<BaseEnum> dictEnums = dictMap.get(key);
        if (dictEnums != null) {
            return dictEnums.stream().map(dict -> new ValueLabelDTO(dict.getValue(), dict.getLabel())).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public Map<String, List<ValueLabelDTO>> getDicts(List<String> keys) {
        return keys.stream().collect(Collectors.toMap(
                Function.identity(),
                this::getDict
        ));
    }
}
