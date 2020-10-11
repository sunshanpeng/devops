package com.sunshanpeng.devops.common.utils;

import com.sunshanpeng.devops.common.dto.TreeDTO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class TreeUtil {

    /**
     * 获取直接子集
     * @param list
     * @param <T>
     * @return
     */
    public  static <T extends TreeDTO> List<T> getChildrens(Long parentId, List<T> list){
        List<T> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)){
            for (T t : list) {
                if (Objects.equals(t.getParentId(), parentId)) {
                    result.add(t);
                }
            }
        }
        return result;
    }

    /**
     * 递归获取所有子集
     * @param rootNode
     * @param list
     * @return
     */
    public static List<TreeDTO> getAllChildrens(TreeDTO rootNode, List<TreeDTO> list) {
        //存放子机构的集合
        LinkedList<TreeDTO> result = new LinkedList<>();
        result.add(rootNode);
        for (TreeDTO t: list) {
            if (Objects.equals(t.getParentId(), rootNode.getId())) {
                rootNode.addChildren(getAllChildrens(t, list));
            }
        }
        return result ;
    }
}
