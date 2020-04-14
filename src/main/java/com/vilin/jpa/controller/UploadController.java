package com.vilin.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/upload")
public class UploadController {

    @RequestMapping("/uploadImage")
    @ResponseBody
    public String uploadImage(MultipartFile file) throws IOException {
        if(null != file){
            String imagePath = "D:\\images\\";
            String originalFileName = file.getOriginalFilename();
            String newFileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());
            File path = new File(imagePath + newFileName);
            file.transferTo(path);
            return "success";
        }else{
            return "file is not exists";
        }
    }


    @RequestMapping("/uploadImages")
    @ResponseBody
    public String uploadImages(MultipartFile image) throws IOException {
        if(null != image){
            String imagePath = "C:\\Users\\XPRO\\IdeaProjects\\springbootjpa\\src\\main\\resources\\static\\images\\";
            String originalFileName = image.getOriginalFilename();
            String newFileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf("."), originalFileName.length());
            File path = new File(imagePath + newFileName);
            image.transferTo(path);
            return "success";
        }else{
            return "file is not exists";
        }
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<String> listImageName(){
        File file = new File("C:\\Users\\XPRO\\IdeaProjects\\springbootjpa\\src\\main\\resources\\static\\images\\");
        File[] files = file.listFiles();
        List<String> fileNames = new ArrayList<String>();
        for(int i = 0; i < files.length; i++){
            fileNames.add(files[i].getName());
        }
        return fileNames;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String deleteFiles(String files){
        files = files.substring(1, files.length() - 1);
        System.out.println("files = " + files);
        String[] fileName = files.split(",");
        for(int i = 0; i < fileName.length; i++){
            String name = fileName[i].substring(1, fileName[i].length() - 1);
            System.out.println("file name = " + name);
            File file = new File("C:\\Users\\XPRO\\IdeaProjects\\springbootjpa\\src\\main\\resources\\static\\images\\" + name);
            if(file.exists()){
                file.delete();
            }
        }
        return "success";
    }
}
