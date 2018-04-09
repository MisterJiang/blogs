package com.modules.blogs.controller;

import com.qiniu.storage.model.DefaultPutRet;
import com.utils.QiNiuUploadUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;

import static com.utils.QiNiuUploadUtil.QinNiuUpload;


@Controller
@RequestMapping(value = {"upload"})
public class UploadController {


    @RequestMapping(value = "")
    public String upload(Model uiModel){
        return "modules/blogs/upload";
    }

    @RequestMapping(value = "uploadToqiNiu")
    @ResponseBody
    public String uploadToQiNiu(HttpServletRequest request, HttpServletResponse response){
        try {

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
