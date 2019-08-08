package com.ddbuy.fastdfs;

import org.csource.fastdfs.*;


public class Test1 {
    //使用java程序上传图片到Fastdfs
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
            //4.上传图片
            String [] infos=storageClient.upload_file("d:\\dog.jpg","jpg",null);
            System.out.println("上传成功,返回信息如下");
            for (String s:infos) {
                System.out.println(s);
            }
            //5.关闭
            trackerServer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
