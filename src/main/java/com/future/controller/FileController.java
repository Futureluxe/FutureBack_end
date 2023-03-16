package com.future.controller;

import com.future.entity.resp.RestBean;
import com.future.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/update")
public class FileController {
    private final ResourceLoader resourceLoader;

    @Autowired
    public FileController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Value("${web.upload-path}")
    private String path;

    /**
     *
     * @param file 要上传的文件
     * @return
     */
    @RequestMapping("fileUpload")
    public RestBean<String> upload(@RequestParam("fileName") MultipartFile file){

        // 要上传的目标文件存放路径
        String localPath = "../Files/Photos";
        // 上传成功或者失败的提示
        String msg = "";
        // 显示图片
        if (FileUtils.upload(file, localPath, file.getOriginalFilename())){
            // 上传成功，给出页面提示
            msg = "上传成功！";
            return new RestBean<>(200,msg,file.getOriginalFilename());
        }else {
            msg = "上传失败！";
            return new RestBean<>(404,msg);

        }
    }

    /**
     * 显示单张图片
     * @return
     */
    @RequestMapping("show")
    public ResponseEntity showPhotos(String fileName){

        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + path + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
