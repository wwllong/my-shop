package com.wenwl.my.shop.web.admin.web.controller;

import com.wenwl.my.shop.domain.entity.TbContentCategory;
import com.wenwl.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wenwl
 * @className TbContentCategoryController
 * @dsecription 内容分类管理
 * @data 2020/2/16
 * @vserion 1.0.0
 */
@Controller
@RequestMapping(value = "content/category")
public class ContentCategoryController {

    @Autowired
    private TbContentCategoryService contentCategoryService;

    @GetMapping("list")
    public String list(Model model){
        List<TbContentCategory> sourceList = contentCategoryService.selectAll();
        List<TbContentCategory> targetList = new ArrayList<>();
        sortList(sourceList,targetList,0L);
        model.addAttribute("tbContentCategories",targetList);

        return "content_category_list";
    }

    private void sortList(List<TbContentCategory> sourceList, List<TbContentCategory> targetList,Long parentId){
        for(TbContentCategory contentCategory : sourceList){
            if(contentCategory.getParentId().equals(parentId)){
                targetList.add(contentCategory);

                // 判断是否是父节点、继续追加子节点
                if(contentCategory.getIsParent()){
                    for(TbContentCategory contentCategory1 :sourceList){
                        if(contentCategory.getId().equals(contentCategory1.getParentId())){
                            sortList(sourceList,targetList,contentCategory.getId());
                            break;
                        }
                    }
                }
            }
        }

    }



}
