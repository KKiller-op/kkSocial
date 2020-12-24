package com.kk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @Description
 * @Author KK
 * @Date 2020-11-10 22:19
 */
@Controller
public class UploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, @RequestParam("sendName") String sendName, HttpServletResponse response) {
        if (Objects.equals("", sendName) && file.isEmpty()) {
            return "请先注册账户";
        }
        try {
            String path = ResourceUtils.getURL("classpath:").getPath() + "static/upload";
            String realPath = path.replace('/', '\\').substring(1, path.length());
            //用于查看路径是否正确
            System.out.println(realPath);

            sendName = sendName + ".jpg";
            //限制文件上传的类型
            String contentType = file.getContentType();
            if (Objects.equals("image/png", contentType) || Objects.equals("image/jpeg", contentType) || Objects.equals("image/jpg", contentType)) {
                File f = new File(realPath, sendName);
                //完成文件的上传
                file.transferTo(f);
                String path01 = "../upload/" + sendName;
                System.out.println("图片上传成功!" + path01);
                response.sendRedirect("/");
                return path01;

            } else {
                System.out.println("上传失败！");
                return "上传失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}