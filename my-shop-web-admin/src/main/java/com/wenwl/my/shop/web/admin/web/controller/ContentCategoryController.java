package com.wenwl.my.shop.web.admin.web.controller;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.domain.entity.TbContentCategory;
import com.wenwl.my.shop.web.admin.commons.framework.BaseTreeController;
import com.wenwl.my.shop.web.admin.service.TbContentCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
public class ContentCategoryController extends BaseTreeController<TbContentCategoryService, TbContentCategory> {

    /**
     * ModelAttribute-创建实体对象
     *
     * @return
     */
    @Override
    public TbContentCategory createEntity() {
        return new TbContentCategory();
    }

    /**
     * 保存内容分类
     * @param tbContentCategory
     * @param redirectAttributes
     * @param model
     * @return
     */
    @Override
    @PostMapping(value = "save")
    public String save(TbContentCategory tbContentCategory, RedirectAttributes redirectAttributes, Model model){
        BaseResult baseResult = baseService.save(tbContentCategory);

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
    @Override
    @GetMapping("list")
    public String list(Model model){
        List<TbContentCategory> sourceList = baseService.selectAll();
        List<TbContentCategory> targetList = new ArrayList<>();
        sortList(sourceList,targetList,0L);
        model.addAttribute("tbContentCategories",targetList);

        return "content_category_list";
    }

    /**
     * 根据parentId取得列表，提供给ZTree进行异步请求的接口
     * @param id
     * @return
     */
    @Override
    @ResponseBody
    @PostMapping("tree/data")
    public List<TbContentCategory> treeData(Long id){
        if(id == null){
            id = 0L;
        }
        return baseService.selectByPid(id);
    }

    /**
     * 跳转新增/编辑内容类目
     * @return
     */
    @Override
    @GetMapping(value = "form")
    public String form(TbContentCategory tbContentCategory){
        return "content_category_form";
    }

    /**
     * 删除节点
     * @param ids
     * @return
     */
    @Override
    @ResponseBody
    @PostMapping(value = "delete")
    public BaseResult delete(String ids){
        BaseResult baseResult = null;
        if(StringUtils.isNotBlank(ids)){
            baseService.delete(Long.parseLong(ids));

            baseResult = BaseResult.success("删除分类及其子类及其全部内容成功");
        }else{
            baseResult = BaseResult.fail("删除失败");
        }
        return baseResult;
    }


}
