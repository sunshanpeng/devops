package com.sunshanpeng.devops.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeDTO {
    private Long id;

    private Long parentId;

    private Object value;

    private String label;

    private List<TreeDTO> children;

    public void addChildren(List<TreeDTO> list) {
        if (children == null) {
            children = new LinkedList<>();
        }
        children.addAll(list);
    }
}
