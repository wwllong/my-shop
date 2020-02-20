package com.wenwl.my.shop.web.admin.web.controller;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.commons.dto.PageInfo;
import com.wenwl.my.shop.domain.entity.TbContent;
import com.wenwl.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author wenwl
 * @className ContentController
 * @data 2020/1/30
 * @vserion 1.0.0
 */
@Controller
@RequestMapping(value = "content")
public class ContentController {

    @Autowired
    private TbContentService contentService;

    @ModelAttribute
    public TbContent getTbContent(Long id){
        TbContent tbContent = null;

        if(id != null){
            tbContent = contentService.getById(id);
        }
        else {
            tbContent = new TbContent();
        }

        return tbContent;
    }

    /**
     * 跳转用户列表
     * @return
     */
    @GetMapping(value = "list")
    public String getList(){
        return "content_list";
    }

    /**
     * 跳转新增用户
     * @return
     */
    @GetMapping(value = "form")
    public String form(){
        return "content_form";
    }

    @PostMapping(value = "save")
    public String save(TbContent tbContent, RedirectAttributes redirectAttributes,Model model){
        BaseResult baseResult = contentService.save(tbContent);

        if(BaseResult.STATUS_SUCCESS == baseResult.getStatus()){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/list";
        }else{
            model.addAttribute("baseResult",baseResult);
            return "content_form";
        }
    }

    @ResponseBody
    @PostMapping(value = "delete")
    public BaseResult delete(String ids){
        BaseResult baseResult = null;
        if(StringUtils.isNotBlank(ids)){
            String[] idArray = ids.split(",");
            contentService.deleteMulti(idArray);
            baseResult = BaseResult.success("删除成功");
        }else{
            baseResult = BaseResult.fail("删除失败");
        }
        return baseResult;
    }

    @ResponseBody
    @GetMapping(value = "page")
    public PageInfo<TbContent> page(HttpServletRequest req,TbContent tbContent){

        String drawStr = req.getParameter("draw");
        String startStr = req.getParameter("start");
        String lengthStr = req.getParameter("length");

        int draw = drawStr == null ? 0 : Integer.parseInt(drawStr);
        int start = startStr == null ? 0 : Integer.parseInt(startStr);
        int length = lengthStr == null ? 0 : Integer.parseInt(lengthStr);

        HashMap<String, Object> params = new HashMap<>();
        params.put("page",start);
        params.put("pageSize",length);
        params.put("tbContent",tbContent);
        PageInfo<TbContent> pageInfo = contentService.page(params);
        pageInfo.setDraw(draw);
        return pageInfo;

    }

    /**
     * 返回用户详情
     * @return
     */
    @GetMapping(value = "detail")
    public String detail(){
        return "content_detail";
    }

}
