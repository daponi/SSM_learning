package com.atguigu.www.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.UUID;


/**
 * ResponseEntity:可以作为控制器方法的返回值，表示响应到浏览器的完整的响应报文
 *
 * 文件上传的要求：
 * 1、form表单的请求方式必须为post
 * 2、form表单必须设置属性enctype="multipart/form-data"
 *
 */
@Controller
public class FileUpAndDownController {

    @GetMapping("/test/down")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取服务器中文件的真实路径
        String realPath = servletContext.getRealPath("img");
        realPath+= File.separator+"test1.png";
        //创建输入流
        InputStream inputStream=new FileInputStream(realPath);
        //创建字节数组，is.available()获取输入流所对应文件的字节数
        byte[] bytes=new byte[inputStream.available()];
        //将流读到字节数组中,没有设置缓冲大小，一次性全部读取到内存中，当文件比较大时，下载直接报异常。
        inputStream.read(bytes);
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String,String> httpHeaders = new HttpHeaders();
        //设置要下载方式以及下载文件的名字
        httpHeaders.add("Content-Disposition", "attaachment;filename=test1.png");
        //设置响应状态码
        HttpStatus statusCode=HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, httpHeaders, statusCode);
        //关闭输入流
        inputStream.close();
        return responseEntity;
    }

    @PostMapping("/test/up")
    public String testUpFile(MultipartFile photo,HttpSession session) throws IOException {
        //获取上传的文件的文件名
        String filename = photo.getOriginalFilename();
        //获取上传的文件的后缀名
        String suffixName = filename.substring(filename.lastIndexOf("."));
        //获取uuid
        String uuid = UUID.randomUUID().toString();
        //拼接一个新的文件名，防止同名文件被重写
        filename = uuid + suffixName;
        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取当前工程下photo目录的真实路径
        String realPath = servletContext.getRealPath("photo");
        //创建photoPath所对应的File对象
        File file = new File(realPath);
        //若不存在该目录则先创建目录
        if (!file.exists()) {
            file.mkdir();
        }
        //设置上传文件路径
        String finalPath=realPath+File.separator+filename;
        //上传文件，将文件转移到指定服务器路径
        photo.transferTo(new File(finalPath));
        return "success";
    }
}
