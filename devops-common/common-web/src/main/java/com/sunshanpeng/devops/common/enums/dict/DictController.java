package com.sunshanpeng.devops.common.enums.dict;

import com.sunshanpeng.devops.common.base.BaseResponse;
import com.sunshanpeng.devops.common.core.dto.ValueLabelDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Api(tags = "字典管理")
@RestController
@RequestMapping("/dicts")
public class DictController {

    @Resource
    private DictService dictService;

    @ApiOperation("获取所有字典")
    @GetMapping
    public BaseResponse<Map<String, String>> getAllDict() {
        return BaseResponse.createSuccessResult(dictService.getAllDict());
    }

    @ApiOperation("获取指定字典")
    @GetMapping("/{keys}")
    public BaseResponse<Map<String, List<ValueLabelDTO>>> getDict(@PathVariable List<String> keys) {
        return BaseResponse.createSuccessResult(dictService.getDicts(keys));
    }
}
