package com.kaishengit.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class FileUploadController {

    @GetMapping("/upload")
    public String uploadFile(@RequestHeader(name = "User-Agent") String userAgent, HttpServletRequest request,
                             HttpServletResponse response){
        System.out.println("userAgent: "+userAgent);

        Cookie cookie = new Cookie("level","vip");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(60*60*24);
        cookie.setDomain("localhost");
        response.addCookie(cookie);
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@CookieValue(name = "level") String cookieValue, String name, MultipartFile photo,
                             RedirectAttributes redirectAttributes) throws IOException {

        System.out.println("cookieValue: " + cookieValue);
        System.out.println("文件名：" + name);
        System.out.println("你好");
        if (!photo.isEmpty()){
            System.out.println(photo.getName());
            System.out.println(photo.getOriginalFilename());
            System.out.println(photo.getContentType());
            System.out.println(photo.getSize());
            /*InputStream inputStream = photo.getInputStream();
            OutputStream outputStream = new FileOutputStream("F:/upload/"+photo.getOriginalFilename());
            IOUtils.copy(inputStream,outputStream);*/

            photo.transferTo(new File("F:/upload/"+photo.getOriginalFilename()));
           /* for (byte b:photo.getBytes()){
                System.out.print(b);
            }*/
        } else {
            System.out.println("file is empty");
            redirectAttributes.addFlashAttribute("message","请选择文件");
        }



        return "redirect:/upload";
    }

    @ExceptionHandler(IOException.class)
    public String ioException(){
        return "error/500";
    }
}
