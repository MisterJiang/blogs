package com.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 七牛存储
 */
public class QiNiuUploadUtil {

    public static DefaultPutRet QinNiu(HttpServletRequest request, String type) throws IOException, FileUploadException {
        // 判断enctype属性是否为multipart/form-data
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart)
            throw new IllegalArgumentException(
                    "上传内容不是有效的multipart/form-data类型.");

        //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
        //使用Apache文件上传组件处理文件上传步骤：
        //1、创建一个DiskFileItemFactory工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024 * 100);//设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
            /*//设置上传时生成的临时文件的保存目录
            factory.setRepository(tmpFile);*/
        //2、创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        //监听文件上传进度
        upload.setProgressListener(new ProgressListener() {
            public void update(long pBytesRead, long pContentLength, int arg2) {
                System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
            }
        });
        //解决上传文件名的中文乱码
        upload.setHeaderEncoding("UTF-8");
        //3、判断提交上来的数据是否是上传表单的数据
        if (!ServletFileUpload.isMultipartContent(request)) {
            //按照传统方式获取数据
            System.out.println("按照传统方式获取数据");
        }

        //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
        upload.setFileSizeMax(1024 * 1024 * 10);
        //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
        upload.setSizeMax(1024 * 1024 * 30);
        //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
        List<FileItem> list = upload.parseRequest(request);
        for (FileItem item : list) {
            if (item.isFormField()) {
                // 如果是普通表单字段
                String name = item.getFieldName();
                String value = item.getString();
                // ...
            } else {
                // 如果是文件字段
                String fieldName = item.getFieldName();
                String fileName = item.getName();
                String contentType = item.getContentType();
                boolean isInMemory = item.isInMemory();
                long sizeInBytes = item.getSize();
                // ...

                // 上传到远程服务器
                HashMap<String, FileItem> files = new HashMap<String, FileItem>();
                files.put(fileName, item);
                DefaultPutRet putRet = QinNiuUpload(files, type);
                return putRet;
            }
        }
        return null;
    }

    public static DefaultPutRet QinNiu2(HttpServletRequest request, String type) throws IOException, FileUploadException {
        // 创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile multipartFile = multiRequest.getFile(iter.next());
                if (multipartFile != null /* && file.getSize()< 1024*1024*2*/) {
                    // 取得当前上传文件的文件名称
                    String myFileName = multipartFile.getOriginalFilename();
                    CommonsMultipartFile cf = (CommonsMultipartFile)multipartFile;
                    //这个myfile是MultipartFile的
                    DiskFileItem item = (DiskFileItem) cf.getFileItem();
                    // 上传到远程服务器
                    HashMap<String, FileItem> files = new HashMap<String, FileItem>();
                    files.put(myFileName, item);
                    DefaultPutRet putRet = QinNiuUpload(files, type);
                    return putRet;
                }
            }
        }
        return null;
    }

    public static DefaultPutRet QinNiuUpload(HashMap<String, FileItem> files, String type) throws IOException {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
//      华东  Zone.zone0()
//      华北  Zone.zone1()
//      华南  Zone.zone2()
//      北美  Zone.zoneNa0()

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "mQFWYwENpTPmzuJE029oc4wnLEu6dhwmq8QNNYIw";//这里请替换成自己的AK
        String secretKey = "QrHy3YE5U7MzJWKja5Lri5JCWWJ3_W_YMgNE074B";//这里请替换成自己的SK
        String bucket = "itdaimauploadimage";//这里请替换成自己的bucket--空间名 默认 itdaimauploadimage
        if (type.equals("photos")) {
            bucket = "photos";
        }

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Iterator iter = files.entrySet().iterator();
        int i=0;
        while (iter.hasNext()) {
            i++;
            Map.Entry entry = (Map.Entry) iter.next();
            String fileName = (String) entry.getKey();
            FileItem val = (FileItem) entry.getValue();
            InputStream inputStream=val.getInputStream();
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[600]; //buff用于存放循环读取的临时数据
            int rc = 0;
            while ((rc = inputStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            byte[] uploadBytes  = swapStream.toByteArray(); //uploadBytes 为转换之后的结果
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(uploadBytes, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return putRet;
               // System.out.println(putRet.key);
                //System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        }
        return null;
    }
}
