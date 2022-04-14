/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.consumer.controller;

import com.mimaxueyuan.consumer.feign.FeignTestClient;
import com.mimaxueyuan.consumer.feign.FeignTestClient2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignTestController {
	
	@Autowired
	private FeignTestClient feignTestClient;
	
	@Autowired
	private FeignTestClient2 feignTestClient2;
	
	@GetMapping("/feign/get/{id}")
	public String get(@PathVariable String id) {
		String result = feignTestClient.get(id);
		return result;
	}
	
	@GetMapping("/feign/get2/{serviceName}")
	public String get2(@PathVariable String serviceName) {
		String result = feignTestClient2.getServiceInfo(serviceName);
		return result;
	}
}
