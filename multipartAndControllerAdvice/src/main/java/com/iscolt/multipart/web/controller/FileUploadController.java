package com.iscolt.multipart.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 文件上传接口
 *
 * @Auther: MaWenyi
 * @Date: 2019/6/24
 * @Description: com.iscolt.multipart.web.controller
 * @version: 1.0
 */
@RestController
public class FileUploadController {

    // 格式化日期
    SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy/MM/dd");

    /**
     * 单文件上传
     *
     * @param uploadFile
     * @param request
     * @return
     */
    @PostMapping("upload")
    public String upload(MultipartFile uploadFile, HttpServletRequest request) {
        String realPath = request.getServletContext().getRealPath("/uploadFile/");
        //String realPath = "src/main/resources/" + IMG_PATH_PREFIX + "/";
        System.out.println(realPath);
        String format = simpleFormatter.format(new Date());
        File folder = new File(realPath + format);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString()
                + oldName.substring(oldName.lastIndexOf("."), oldName.length());
        try {
            uploadFile.transferTo(new File(folder, newName));
            String filePath = request.getScheme()
                    + "://"
                    + request.getServerName()
                    + ":"
                    + request.getServerPort()
                    + "/uploadFile/"
                    + format
                    + "/"
                    + newName;
            return "<a href='" + filePath + "'>"+ filePath +"</a>";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败!";
    }

    /**
     * 多文件上传
     *
     * @param uploadFiles
     * @param request
     * @return
     */
    @PostMapping("uploads")
    public String upload(MultipartFile[] uploadFiles, HttpServletRequest request) {
        String realPath = request.getServletContext().getRealPath("/uploadFile/");
        //String realPath = "src/main/resources/" + IMG_PATH_PREFIX + "/";
        System.out.println(realPath);
        String format = simpleFormatter.format(new Date());
        File folder = new File(realPath + format);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        String filePath = "";
        if (uploadFiles.length > 0) {
            for (int i=0; i<uploadFiles.length; i++){
                String oldName = uploadFiles[i].getOriginalFilename();
                String newName = UUID.randomUUID().toString()
                        + oldName.substring(oldName.lastIndexOf("."), oldName.length());
                try {
                    uploadFiles[i].transferTo(new File(folder, newName));
                    filePath = request.getScheme()
                            + "://"
                            + request.getServerName()
                            + ":"
                            + request.getServerPort()
                            + "/uploadFile/"
                            + format
                            + "/"
                            + newName
                            + "<br>"
                            + filePath;
                    System.out.println(filePath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return filePath;
        }
        return "上传失败!";
    }
}
