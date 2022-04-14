/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.zuul.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这边的@RefreshScope注解不能少，否则即使调用/refresh，配置也不会刷新
 */
@RestController
@RefreshScope
public class ConfigClientController {

	@Value("${env}")
	private String env;
	
	@Value("${password}")
	private String password;
	
	@Value("${username}")
	private String username;

	@GetMapping("/config/profile")
	public String hello() {
		return this.env+","+this.password+","+this.username;
	}
}
