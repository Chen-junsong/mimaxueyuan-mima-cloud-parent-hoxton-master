/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.consumer.robbin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Repository
public class TestService {

	@Autowired
	private RestTemplate restTemplate;

	// 设置断路器,当此方法无法应答时,调用getError方法
	@HystrixCommand(fallbackMethod = "getError")
	public String get(String id) {
		System.out.println(Thread.currentThread().getId() + ".get");
		String result = restTemplate.getForObject("http://mima-cloud-producer/get/" + id, String.class);
		return result;
	}

	// 设置断路器,当此方法无法应答时,调用getError方法
	@HystrixCommand(fallbackMethod = "getError", commandProperties = { @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE") })
	public String get2(String id) {
		System.out.println(Thread.currentThread().getId() + ".get2" );
		String result = restTemplate.getForObject("http://mima-cloud-producer/get/" + id, String.class);
		return result;
	}

	public String getError(String id) {
		return Thread.currentThread().getId() + "." + "getError";
	}
}
