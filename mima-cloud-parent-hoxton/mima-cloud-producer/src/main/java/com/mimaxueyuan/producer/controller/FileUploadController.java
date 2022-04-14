/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.producer.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 * 
 * @author: Kevin
 * @Date:2018年4月22日
 * @Project:micrioframework
 */
@RestController
public class FileUploadController {
	
	@RequestMapping("/upload")
    public String  fileUpload2(@RequestParam("file") MultipartFile file) throws IOException {
         long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+file.getOriginalFilename());
        String path="D:/"+new Date().getTime()+file.getOriginalFilename();
        File newFile=new File(path);
        file.transferTo(newFile);
        long  endTime=System.currentTimeMillis();
        System.out.println("运行时间："+String.valueOf(endTime-startTime)+"ms");
        return "success"; 
    }
	
}
