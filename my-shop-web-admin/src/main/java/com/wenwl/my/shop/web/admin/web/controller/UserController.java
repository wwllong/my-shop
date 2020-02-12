package com.wenwl.my.shop.web.admin.web.controller;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.domain.entity.TbUser;
import com.wenwl.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
     * @param model
     * @return
     */
    @GetMapping(value = "list")
    public String getList(Model model){
        List<TbUser> tbUsers = userService.selectAll();
        model.addAttribute("tbUsers",tbUsers);
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
     * 搜索
     * @param tbUser
     * @param model
     * @return
     */
    @PostMapping(value = "search")
    public String search(TbUser tbUser,Model model){
        List<TbUser> tbUsers = userService.search(tbUser);
        model.addAttribute("tbUsers",tbUsers);
        return "user_list";
    }

}
