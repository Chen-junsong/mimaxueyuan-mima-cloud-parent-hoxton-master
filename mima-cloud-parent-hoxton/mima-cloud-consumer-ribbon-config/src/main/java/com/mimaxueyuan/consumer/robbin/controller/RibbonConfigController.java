/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.consumer.robbin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonConfigController {

	// 注入restTemplate, 这个类已经在RibbonConsumerApplication中初始化,并且使用负载均衡
	@Autowired //默认按照类型注入,如果需要按照名字注入需要使用@Qualifier注解
	@LoadBalanced //使用带有负载均衡的RestTemplate
	private RestTemplate balanceRestTemplate;

	// 以下注入负载均衡客户端LoadBalancerClient是一个接口,下面只有一个RibbonLoadBalancerClient实现类
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	@Autowired
	private RibbonLoadBalancerClient ribbonLoadBalancerClient;

	/**
	 * ribbon使用
	 *
	 * @author Kevin
	 * @Title: ribbon 
	 * @param id
	 * @return
	 * @return: String
	 */
	@GetMapping("/ribbon/get/{id}")
	public String ribbon(@PathVariable("id") String id) {
		ServiceInstance instance = loadBalancerClient.choose("mima-cloud-producer");
		System.out.println("host:" + instance.getHost() + ",port:" + instance.getPort() + ",serviceId=" + instance.getServiceId() + ",uri=" + instance.getUri());
		instance = ribbonLoadBalancerClient.choose("mima-cloud-producer2");
		System.out.println("host:" + instance.getHost() + ",port:" + instance.getPort() + ",serviceId=" + instance.getServiceId() + ",uri=" + instance.getUri());
		return "ribbon's demo,please to see console output";
	}
	
}
