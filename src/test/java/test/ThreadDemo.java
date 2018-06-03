package test;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ThreadDemo {

    /**
     * 多线程处理list
     *
     * @param data  数据list
     * @param threadNum  线程数
     */
    public synchronized void handleList(List<String> data, int threadNum) {
        int length = data.size();
        int tl = length % threadNum == 0 ? length / threadNum : (length
                / threadNum + 1);

        for (int i = 0; i < threadNum; i++) {
            int end = (i + 1) * tl;
            HandleThread thread = new HandleThread("线程[" + (i + 1) + "] ",  data, i * tl, end > length ? length : end);
            thread.start();
        }
    }

    class HandleThread extends Thread {
        private String threadName;
        private List<String> data;
        private int start;
        private int end;

        public HandleThread(String threadName, List<String> data, int start, int end) {
            this.threadName = threadName;
            this.data = data;
            this.start = start;
            this.end = end;
        }

        public void run() {
            List<String> subList = data.subList(start, end)/*.add("^&*")*/;
           // System.out.println("2222");
            InputStream inputStream = null;
            String endpoint = "http://oss-cn-beijing.aliyuncs.com";
            String accessKeyId = "LTAI10b9x6obGfBE";
            String accessKeySecret = "cb5S2OjiZDguYG6k3gFKTfCSxJ42p9";

            // 创建OSSClient实例。
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);


            boolean bool = ossClient.doesBucketExist("jiangyincai");
            System.out.println(bool);
            String fileName = "123";
            File file = new File("C:\\Users\\Administrator\\Desktop\\123.jpg");
            try {
                 inputStream = new FileInputStream(file);

            }catch (Exception e){
                e.printStackTrace();
            }


            ObjectMetadata objectMetadata = new ObjectMetadata();
/*        objectMetadata.setContentLength(inputStream.available());
        objectMetadata.setCacheControl("no-cache");
        objectMetadata.setHeader("Pragma", "no-cache");
        objectMetadata.setContentType("jpg");
        objectMetadata.setContentDisposition("inline;filename=" + fileName);*/
            PutObjectResult putResult = ossClient.putObject("jiangyincai", "pic/123.jpg", inputStream, objectMetadata);
            String ret = putResult.getETag();
            System.out.println(ret);

            System.out.println(threadName+"处理了"+subList.size()+"条！");
        }

    }

    public static void main(String[] args) {
        ThreadDemo test = new ThreadDemo();
        // 准备数据
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < 100000; i++) {
            data.add("item" + i);
        }
        test.handleList(data, 5);
       // System.out.println(ArrayUtils.toString(data));
    }


}
