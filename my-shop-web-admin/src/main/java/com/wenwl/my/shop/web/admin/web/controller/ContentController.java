package com.wenwl.my.shop.web.admin.web.controller;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.domain.entity.TbContent;
import com.wenwl.my.shop.web.admin.commons.framework.BaseController;
import com.wenwl.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author wenwl
 * @className ContentController
 * @data 2020/1/30
 * @vserion 1.0.0
 */
@Controller
@RequestMapping(value = "content")
public class ContentController extends BaseController<TbContentService, TbContent>{

    /**
     * ModelAttribute-创建实体对象
     *
     * @return
     */
    @Override
    public TbContent createEntity() {
        return new TbContent();
    }


    /**
     * 跳转内容列表
     * @return
     */
    @Override
    @GetMapping(value = "list")
    public String list(){
        return "content_list";
    }


     /**
     * 跳转新增内容
     * @return
     */
    @Override
    @GetMapping(value = "form")
    public String form(){
        return "content_form";
    }

    /**
     * 保存内容
     * @param tbContent
     * @param redirectAttributes
     * @param model
     * @return
     */
    @Override
    @PostMapping(value = "save")
    public String save(TbContent tbContent, RedirectAttributes redirectAttributes,Model model){
        BaseResult baseResult = baseService.save(tbContent);

        if(BaseResult.STATUS_SUCCESS == baseResult.getStatus()){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/list";
        }else{
            model.addAttribute("baseResult",baseResult);
            return "content_form";
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    @ResponseBody
    @PostMapping(value = "delete")
    public BaseResult delete(String ids){
        BaseResult baseResult = null;
        if(StringUtils.isNotBlank(ids)){
            String[] idArray = ids.split(",");
            baseService.batchDelete(idArray);
            baseResult = BaseResult.success("删除成功");
        }else{
            baseResult = BaseResult.fail("删除失败");
        }
        return baseResult;
    }


    /**
     * 返回内容详情
     * @return
     */
    @Override
    @GetMapping(value = "detail")
    public String detail(){
        return "content_detail";
    }
}
