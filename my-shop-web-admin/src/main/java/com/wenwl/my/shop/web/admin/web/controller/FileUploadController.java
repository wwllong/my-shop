package com.wenwl.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author wenwl
 * @className FileUploadController
 * @dsecription 文件上传控制器
 * @data 2020/2/28 0:53
 * @vserion 1.0.0
 */

@Controller
public class FileUploadController {

    private final String UPLOAD_PATH = "/static/upload";

    @ResponseBody
    @PostMapping("upload")
    public Map<String,Object> upload(MultipartFile dropzoneFile, HttpServletRequest request){
        HashMap<String, Object> result = new HashMap<>();
        // 获取上传文件的原始名称
        String fileName = dropzoneFile.getOriginalFilename();
        // 获取文件后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
        // 设置文件上传路径
        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);

        // 创建上传的文件夹
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }
        // 重新命名
        file = new File(filePath, UUID.randomUUID() + fileSuffix);

        try {
            // 写入文件
            dropzoneFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 返回数据
        String url = String.format("%s:%s%s/%s", request.getServerName(), request.getServerPort(),UPLOAD_PATH,file.getName());
        result.put("url",url);
        return result;
    }
}
