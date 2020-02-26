package com.wenwl.my.shop.web.admin.web.controller;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.commons.dto.PageInfo;
import com.wenwl.my.shop.domain.entity.TbUser;
import com.wenwl.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @author wenwl
 * @className UserController
 * @data 2020/1/30
 * @vserion 1.0.0
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private TbUserService userService;

    /**
     * 回显数据
     * @param id
     * @return
     */
    @ModelAttribute
    public TbUser getTbUser(Long id){
        TbUser tbUser = null;

        if(id != null){
            tbUser = userService.getById(id);
        }
        else {
            tbUser = new TbUser();
        }

        return tbUser;
    }

    /**
     * 跳转用户列表
     * @return
     */
    @GetMapping(value = "list")
    public String getList(){
        return "user_list";
    }

    /**
     * 跳转新增用户
     * @return
     */
    @GetMapping(value = "form")
    public String form(){
        return "user_form";
    }

    /**
     * 保存用户
     * @param tbUser
     * @param redirectAttributes
     * @param model
     * @return
     */
    @PostMapping(value = "save")
    public String save(TbUser tbUser, RedirectAttributes redirectAttributes,Model model){
        BaseResult baseResult = userService.save(tbUser);

        if(BaseResult.STATUS_SUCCESS == baseResult.getStatus()){
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }else{
            model.addAttribute("baseResult",baseResult);
            return "user_form";
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @ResponseBody
    @PostMapping(value = "delete")
    public BaseResult delete(String ids){
        BaseResult baseResult = null;
        if(StringUtils.isNotBlank(ids)){
            String[] idArray = ids.split(",");
            userService.deleteMulti(idArray);
            baseResult = BaseResult.success("删除成功");
        }else{
            baseResult = BaseResult.fail("删除失败");
        }
        return baseResult;
    }

    /**
     * 分页查询
     * @param req
     * @param tbUser
     * @return
     */
    @ResponseBody
    @GetMapping(value = "page")
    public PageInfo<TbUser> page(HttpServletRequest req,TbUser tbUser){

        String drawStr = req.getParameter("draw");
        String startStr = req.getParameter("start");
        String lengthStr = req.getParameter("length");

        int draw = drawStr == null ? 0 : Integer.parseInt(drawStr);
        int start = startStr == null ? 0 : Integer.parseInt(startStr);
        int length = lengthStr == null ? 0 : Integer.parseInt(lengthStr);

        HashMap<String, Object> params = new HashMap<>();
        params.put("page",start);
        params.put("pageSize",length);
        params.put("tbUser",tbUser);
        PageInfo<TbUser> pageInfo = userService.page(params);
        pageInfo.setDraw(draw);
        return pageInfo;

    }

    /**
     * 返回用户详情
     * @return
     */
    @GetMapping(value = "detail")
    public String detail(){
        return "user_detail";
    }

}
