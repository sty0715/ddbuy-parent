package com.ddbuy.fastdfs;

import org.csource.fastdfs.*;

import java.io.FileOutputStream;


public class Test3 {
    //使用java程序下载图片到Fastdfs
    public static void main(String[] args) {
       //使用api操作Fastdfs
        try {
            //1.加载属性
            ClientGlobal.init("fastdfs.properties");
            //2.创建tracker服务器对象
            TrackerClient client=new TrackerClient();
            TrackerServer trackerServer=client.getConnection();
            //3.创建storage客户端
            StorageServer storageServer=null;
            StorageClient storageClient=new StorageClient(trackerServer,storageServer);
            //4.下载图片
             byte []bs=storageClient.download_file("group1","M00/00/00/wKgBHl08fciAbfTMAADwK-o9tPU095.jpg");
             //将到返回的二进制数据写入文件
             // 流Stream
             // 流的分类:输入流inputStream 读文件、输出流(outputStream) 写文件
            FileOutputStream fos=new FileOutputStream("d:\\a.jpg");
            fos.write(bs);
            fos.flush();
            fos.close();
            System.out.println("下载成功");
            //5.关闭
            trackerServer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
