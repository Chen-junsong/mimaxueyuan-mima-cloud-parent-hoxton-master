/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载 、 任何问题的交流)
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。 https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mkevin.consul.config.controller;

import com.mkevin.consul.config.entity.StudentConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

    @Value("${myName}")
    private String myName;

    @Autowired
    private StudentConfig studentConfig;

    @RequestMapping("/myname")
    public String testHello() {
        System.out.println("my name is : " + myName);
        return myName;
    }

    @RequestMapping("/config")
    public String testConfig() {
        System.out.println(studentConfig.toString());
        return studentConfig.toString();
    }

}
