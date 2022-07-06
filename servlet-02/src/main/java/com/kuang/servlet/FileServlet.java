package com.kuang.servlet;

import org.springframework.core.io.ClassPathResource;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String filePath = "/Users/sienpeng/code/personal/kuangshen-javaweb/servlet-02/target/servlet-02-1.0-SNAPSHOT/WEB-INF/classes/qingdao.jpeg";
//        File file = new File(filePath);
//        String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);

        ClassPathResource resource = new ClassPathResource("qingdao.jpeg");
        String filePath = resource.getURL().getPath();
        String fileName = resource.getFilename();
        
        // 3. 设置想办法让浏览器能够支持(Content-Disposition)下载我们需要的东西,中文文件名URLEncoder.encode编码，否则有可能乱码 
        resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8")); 
        
        // 4. 获取下载文件的输入流
        FileInputStream in = new FileInputStream(filePath);
        
        // 5. 创建缓冲区
        int len = 0;
        byte[] buffer = new byte[1024];
        
        // 6. 获取OutputStream对象
        ServletOutputStream out = resp.getOutputStream();
        
        // 7. 将FileOutputStream流写入到buffer缓冲区,使用OutputStream将缓冲区中的数据输出到客户端!
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public static void main(String[] args) throws IOException {
//        URL url = FileServlet.class.getResource("qingdao.jpeg");
//        System.out.println(url);

        ClassPathResource resource = new ClassPathResource("qingdao.jpeg");
        System.out.println(resource.getPath());
        System.out.println(resource.getFile().getPath());
        System.out.println(resource.getFile().getAbsolutePath());
        System.out.println(resource.getURL().getPath());

//        qingdao.jpeg
//        /Users/sienpeng/code/personal/kuangshen-javaweb/servlet-02/target/classes/qingdao.jpeg
//        /Users/sienpeng/code/personal/kuangshen-javaweb/servlet-02/target/classes/qingdao.jpeg 
//        /Users/sienpeng/code/personal/kuangshen-javaweb/servlet-02/target/classes/qingdao.jpeg
    }
}
