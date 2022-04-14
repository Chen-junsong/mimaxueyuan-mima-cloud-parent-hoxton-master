/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mkevin.consul.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConsulController {

	// 以下两个客户端类,用哪个都可以,DiscoveryClient抽象程度更高,是一个接口,ConsulDiscoveryClient只是他的抽象类
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	private ConsulDiscoveryClient consulDiscoveryClient;

	// consul使用展示
	@GetMapping("/consul/test")
	public String consul() {
		System.out.println("consul使用展示:/consul/get---start");

		// ----------------- 以下的代码使用DiscoveryClient-------------------
		// 查询所有注册到Consul上的服务:
		System.out.println("[discoveryClient]-查询所有注册到Consul上的服务:");
		for (String string : discoveryClient.getServices()) {
			System.out.println(string);
		}

		// 查询某一个provider的所有Service实例
		System.out.println("[discoveryClient]-查询某一个provider的所有Service实例");
		List<ServiceInstance> instances = discoveryClient.getInstances("mima-cloud-consul-producer");
		for (ServiceInstance instance : instances) {
			System.out.println("host:" + instance.getHost() + ",port:" + instance.getPort() + ",serviceId=" + instance.getServiceId() + ",uri=" + instance.getUri());
			System.out.println("metadata=" + instance.getMetadata());
		}

		// TODO 已经不使用
		//ServiceInstance localServiceInstance = discoveryClient.getLocalServiceInstance();

		// ----------------- 以下的代码使用ConsulDiscoveryClient-------------------

		// 查询所有注册到Consul上的服务:
		System.out.println("[consulDiscoveryClient]-查询所有注册到Consul上的服务:");
		for (String string : consulDiscoveryClient.getServices()) {
			System.out.println(string);
		}

		// 查询某一个provider的所有Service实例
		System.out.println("[consulDiscoveryClient]-查询某一个provider的所有Service实例");
		instances = consulDiscoveryClient.getInstances("mima-cloud-producer");
		for (ServiceInstance instance : instances) {
			System.out.println("host:" + instance.getHost() + ",port:" + instance.getPort() + ",serviceId=" + instance.getServiceId() + ",uri=" + instance.getUri());
			System.out.println("metadata=" + instance.getMetadata());
		}

		// TODO 已经不使用
		//localServiceInstance = consulDiscoveryClient.getLocalServiceInstance();

		System.out.println("consul使用展示:/consul/get---end");
		return "consul's demo, please to see console output";
	}

}
