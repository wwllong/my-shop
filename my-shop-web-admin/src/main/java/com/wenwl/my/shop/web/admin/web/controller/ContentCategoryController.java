package com.wenwl.my.shop.web.admin.web.controller;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.domain.entity.TbContentCategory;
import com.wenwl.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    /**
     * 回显数据
     * @param id
     * @return
     */
    @ModelAttribute
    public TbContentCategory getTbContent(Long id){
        TbContentCategory tbContentCategory = null;

        if(id != null){
            tbContentCategory = contentCategoryService.getById(id);
        }
        else {
            tbContentCategory = new TbContentCategory();
        }

        return tbContentCategory;
    }

    /**
     * 保存内容分类
     * @param tbContentCategory
     * @param redirectAttributes
     * @param model
     * @return
     */
    @PostMapping(value = "save")
    public String save(TbContentCategory tbContentCategory, RedirectAttributes redirectAttributes, Model model){
        BaseResult baseResult = contentCategoryService.save(tbContentCategory);

        if(BaseResult.STATUS_SUCCESS == baseResult.getStatus()){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/category/list";
        }else{
            model.addAttribute("baseResult",baseResult);
            return "content_category_form";
        }
    }

    /**
     * 查询列表
     * @param model
     * @return
     */
    @GetMapping("list")
    public String list(Model model){
        List<TbContentCategory> sourceList = contentCategoryService.selectAll();
        List<TbContentCategory> targetList = new ArrayList<>();
        sortList(sourceList,targetList,0L);
        model.addAttribute("tbContentCategories",targetList);

        return "content_category_list";
    }

    /**
     * 对列表进行排序-方便前端列表TreeTable显示
     * @param sourceList
     * @param targetList
     * @param parentId
     */
    private void sortList(List<TbContentCategory> sourceList, List<TbContentCategory> targetList,Long parentId){
        for(TbContentCategory contentCategory : sourceList){
            if(contentCategory.getParent() != null && contentCategory.getParent().getId().equals(parentId)){
                targetList.add(contentCategory);

                // 判断是否是父节点、继续追加子节点
                if(contentCategory.getIsParent()){
                    for(TbContentCategory contentCategory1 :sourceList){
                        if(contentCategory1.getParent() != null && contentCategory.getId().equals(contentCategory1.getParent().getId())){
                            sortList(sourceList,targetList,contentCategory.getId());
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * 根据parentId取得列表，提供给ZTree进行异步请求的接口
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("tree/data")
    public List<TbContentCategory> treeData(Long id){
        if(id == null){
            id = 0L;
        }
        return contentCategoryService.selectByPid(id);
    }

    /**
     * 跳转新增/编辑内容类目
     * @return
     */
    @GetMapping(value = "form")
    public String form(TbContentCategory tbContentCategory){
        return "content_category_form";
    }


}
