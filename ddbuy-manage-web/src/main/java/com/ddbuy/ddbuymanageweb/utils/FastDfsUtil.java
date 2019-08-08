package com.ddbuy.ddbuymanageweb.utils;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class FastDfsUtil {
    @Value(value = "${nginx.fastdfs.address}")
    private String nginx_fastdfs_address;
    @Value(value = "${nginx.fastdfs.port}")
    private String nginx_fastdfs_port;


     public String uploadFile(byte[] bs,String expname){
         String filePath=null;
         try {
             ClientGlobal.init("fastdfs.properties");
             //2.创建tracker服务器对象
             TrackerClient client=new TrackerClient();
             TrackerServer trackerServer=client.getConnection();
             //3.创建storage客户端
             StorageServer storageServer=null;
             StorageClient storageClient=new StorageClient(trackerServer,storageServer);
             //4.上传图片
             String [] infos=storageClient.upload_file(bs,expname,null);
             filePath=nginx_fastdfs_address+":"+nginx_fastdfs_port+"/"+infos[0]+"/"+infos[1];
         } catch (Exception e) {
             e.printStackTrace();
         }
         return filePath;

     }
}
