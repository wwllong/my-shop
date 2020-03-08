package com.wenwl.my.shop.web.admin.commons.framework;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.commons.dto.PageInfo;
import com.wenwl.my.shop.commons.framework.BaseService;
import com.wenwl.my.shop.commons.persistence.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author wenwl
 * @className BaseController
 * @dsecription 控制层实现基类
 * @data 2020/3/8 15:46
 * @vserion 1.0.0
 */
public abstract class BaseController<S extends BaseService<T>, T extends BaseEntity> {

    @Autowired
    protected S baseService;

    /**
     * 回显数据
     * @param id
     * @return
     */
    @ModelAttribute
    public T getEntity(Long id){
        T entity = null;

        if(id != null){
            entity = baseService.getById(id);
        }
        else {
            entity = createEntity();
        }

        return entity;
    }

    /**
     * ModelAttribute-创建实体对象
     * @return
     */
    public abstract T createEntity();

    /**
     * 跳转列表页
     *
     * @return
     */
    public abstract String list();

    /**
     * 跳转到表单页
     *
     * @return
     */
    public abstract String form();

    /**
     * 保存信息
     * @param entity
     * @param redirectAttributes
     * @param model
     * @return
     */
    public abstract String save(T entity, RedirectAttributes redirectAttributes, Model model);

    /**
     * 跳转详情页
     *
     * @return
     */
    public abstract String detail();

    /**
     * 删除
     * @param ids
     * @return
     */
    public abstract BaseResult delete(String ids);

    /**
     * 分页查询
     * @param req
     * @param entity
     * @return
     */
    @ResponseBody
    @GetMapping(value = "page")
    public PageInfo<T> page(HttpServletRequest req, T entity){

        String drawStr = req.getParameter("draw");
        String startStr = req.getParameter("start");
        String lengthStr = req.getParameter("length");

        int draw = drawStr == null ? 0 : Integer.parseInt(drawStr);
        int start = startStr == null ? 0 : Integer.parseInt(startStr);
        int length = lengthStr == null ? 0 : Integer.parseInt(lengthStr);

        HashMap<String, Object> params = new HashMap<>();
        params.put("page",start);
        params.put("pageSize",length);
        params.put("pageParams",entity);
        PageInfo<T> pageInfo = baseService.page(params);
        pageInfo.setDraw(draw);
        return pageInfo;

    }

}
