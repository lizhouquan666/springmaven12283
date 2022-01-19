package com.wanxi.controller;

import com.alibaba.fastjson.JSON;
import com.wanxi.entity.ImgModel;
import com.wanxi.result.ResultModel;
import com.wanxi.tool.CommonResult;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@CrossOrigin(allowCredentials="true", allowedHeaders="*")
@RestController
@RequestMapping(value = "upload",method = RequestMethod.POST)
//method = RequestMethod.POST
public class UploadController {
    private static final long serialVersionUID = 1L;

    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "img";

    //上传文件前端静态路径C:\Users\Master\IdeaProjects\qian\out
    private static final String UPLOAD_PATH = "C:\\Users\\Master\\IdeaProjects\\qian\\qian\\web";
    //上传文件前端out路径C:\Users\Master\IdeaProjects\qian\out
    private static final String UPLOAD_OUTPATH = "C:\\Users\\Master\\IdeaProjects\\qian\\out";
    // 上传配置
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    /**
     * 上传数据及保存文件
     * doGet和doPost的区别?
     */
    @RequestMapping(method = RequestMethod.POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            PrintWriter writer = resp.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }

        // 配置上传参数，缓存区
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
//        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

//        解析文件
        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
//                                                        获取绝对路径
        //out路径C:\Users\Master\IdeaProjects\qian\out\artifacts\qian_war_exploded\img
//        String uploadPath = request.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTORY;

//        String uploadPath = request.getServletContext().getRealPath("./");
        //前端路径
        String uploadPaths = UPLOAD_PATH + File.separator + UPLOAD_DIRECTORY;
//        String webPath = UPLOAD_OUTPATH.split("out")[0];
        String path = UPLOAD_OUTPATH + "/artifacts/qian_war_exploded/" + File.separator + UPLOAD_DIRECTORY;
        String fileName = "";
        // 如果目录不存在则创建
        File uploadDirs = new File(uploadPaths);
        if (!uploadDirs.exists()) {
            uploadDirs.mkdirs();
        }
//        File uploadDir = new File(path);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdirs();
//        }
        File web = new File(path);
        if (!web.exists()) {
            web.mkdirs();
        }
        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        fileName = new File(item.getName()).getName();
//                        UUID.randomUUID() 全球统一随机数32位
                        fileName = UUID.randomUUID() + "-" + fileName;
//                        fahdsfkjashdfkajhfdksajfd-21.jpg
                        String filePath = uploadPaths + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
//                        将文件加入到web里面
                        File webFile=new File(web,fileName);
                        item.write(webFile);
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "错误信息: " + ex.getMessage());
        }
        ImgModel imgModel = new ImgModel();
        String src = "/" + UPLOAD_DIRECTORY + "/" + fileName;
        imgModel.setSrc(src);
        resp.getWriter().println(JSON.toJSONString(CommonResult.success(imgModel)));
    }
}
