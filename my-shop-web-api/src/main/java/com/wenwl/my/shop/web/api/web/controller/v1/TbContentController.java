package com.wenwl.my.shop.web.api.web.controller.v1;

import com.wenwl.my.shop.commons.dto.BaseResult;
import com.wenwl.my.shop.domain.entity.TbContent;
import com.wenwl.my.shop.web.api.service.TbContentService;
import com.wenwl.my.shop.web.api.web.dto.TbContentDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wenwl
 * @className TbContentController
 * @dsecription 内容接口
 * @data 2020/3/29 18:18
 * @vserion 1.0.0
 */
@RestController
@RequestMapping(value = "${api.path.v1}/contents")
public class TbContentController {

    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id) {
        TbContent tbContent = null;

        if (id == null) {
            tbContent = new TbContent();
        }

        return tbContent;
    }


    /**
     * 幻灯片接口
     * @return
     */
    @GetMapping("{category_id}")
    public BaseResult findByCategoryId(@PathVariable(value = "category_id") Long categoryId) {
        List<TbContentDTO> tbContentDTOS = null;
        List<TbContent> tbContents = tbContentService.getByCategoryId(categoryId);

        if (tbContents != null && !tbContents.isEmpty()) {
            tbContentDTOS = new ArrayList<>();
            for (TbContent tbContent : tbContents) {
                TbContentDTO dto = new TbContentDTO();
                BeanUtils.copyProperties(tbContent, dto);
                tbContentDTOS.add(dto);
            }
        }

        return BaseResult.success("成功", tbContentDTOS);
    }

}
