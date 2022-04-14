/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.consumer.robbin.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.mimaxueyuan.consumer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonController {

	// 注入restTemplate, 这个类已经在RibbonConsumerApplication中初始化,不使用负载均衡
	@Autowired
	private RestTemplate noBalanceRestTemplate;

	// 注入restTemplate, 这个类已经在RibbonConsumerApplication中初始化,并且使用负载均衡
	@Autowired // 默认按照类型注入,如果需要按照名字注入需要使用@Qualifier注解
	//@LoadBalanced //使用带有负载均衡的RestTemplate
	@Qualifier("balanceRestTemplate")
	private RestTemplate balanceRestTemplate;

	// 以下注入负载均衡客户端LoadBalancerClient是一个接口,下面只有一个RibbonLoadBalancerClient实现类
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	@Autowired
	private RibbonLoadBalancerClient ribbonLoadBalancerClient;

	/**
	 * 不使用ribbon的旧调用方式
	 *
	 * @author Kevin
	 * @Title: old
	 * @return
	 * @return: String
	 */
	@GetMapping("/ribbon/old/get/{id}")
	public String old(@PathVariable("id") String id) {
		
		// 使用noBalanceRestTemplate是非负载均衡的,所以没问题
		String result = noBalanceRestTemplate.getForObject("http://localhost:9907/get/"+id, String.class);
		System.out.println("[hardcode1]" + result);
		
		// 由于balanceRestTemplate已经使用了Ribbon做负载均衡,所以使用硬编码方式就不允许了,会提示:No instances available for localhost
		result = balanceRestTemplate.getForObject("http://localhost:9907/get/"+id, String.class);
		System.out.println("[hardcode2]" + result);
		
		return "result";
	}

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
		// -----------------以下代码使用ribbon做客户端负载均衡
		// 使用provider的instanceName替代ip和端口的硬编码
		String result = balanceRestTemplate.getForObject("http://mima-cloud-producer/get/"+id, String.class);
		System.out.println("[ribbon]" + result);

		System.out.println("[loadBalancerClient]choose的结果,代表负载均衡之后要选择的服务实例");
		ServiceInstance instance = loadBalancerClient.choose("mima-cloud-producer");
		System.out.println("host:" + instance.getHost() + ",port:" + instance.getPort() + ",serviceId=" + instance.getServiceId() + ",uri=" + instance.getUri());

		System.out.println("[ribbonLoadBalancerClient]choose的结果,代表负载均衡之后要选择的服务实例");
		instance = ribbonLoadBalancerClient.choose("mima-cloud-producer");
		System.out.println("host:" + instance.getHost() + ",port:" + instance.getPort() + ",serviceId=" + instance.getServiceId() + ",uri=" + instance.getUri());

		try {
			// 根据负载均衡后的服务,构建一个访问url
			// 第二个参数不能为null
			System.out.println("根据负载均衡后的服务,构建一个访问url");
			URI reconstructURI = ribbonLoadBalancerClient.reconstructURI(instance, new URI(""));
			System.out.println("reconstructURI1-yes:" + reconstructURI);
			// 拼写在请求地址后边，需要注意是否需要添加/
			reconstructURI = ribbonLoadBalancerClient.reconstructURI(instance, new URI("/ribbon/get"));
			System.out.println("reconstructURI2-yes:" + reconstructURI);
			reconstructURI = ribbonLoadBalancerClient.reconstructURI(instance, new URI("http"));
			System.out.println("reconstructURI3-no:" + reconstructURI);
			reconstructURI = ribbonLoadBalancerClient.reconstructURI(instance, new URI("https"));
			System.out.println("reconstructURI4-no:" + reconstructURI);
			reconstructURI = ribbonLoadBalancerClient.reconstructURI(instance, new URI("test"));
			System.out.println("reconstructURI5-no:" + reconstructURI);
			// 使用http:/xxx、https:/xxx可以用于切换http协议还是https协议
			reconstructURI = ribbonLoadBalancerClient.reconstructURI(instance, new URI("http:/ribbin/get"));
			System.out.println("reconstructURI6-yes:" + reconstructURI);
			reconstructURI = ribbonLoadBalancerClient.reconstructURI(instance, new URI("https:/ribbin/get"));
			System.out.println("reconstructURI7-yes:" + reconstructURI);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO 该方法如何使用，还没搞懂
		// loadBalancerClient.execute()

		return "ribbon's demo,please to see console output";
	}

	@GetMapping("/nobalance/get/{id}")
	public String nobalance(@PathVariable("id") String id) {
		// -----------------以下代码使用硬编码方式调用服务
		// 如果restTemplate已经使用了Ribbon做负载均衡,也就是使用了@LoadBaleced注解,依然使用硬编码方式就不允许了,会提示:No
		// instances available for localhost

		String result = noBalanceRestTemplate.getForObject("http://localhost:9907/get/"+id, String.class);
		System.out.println("[noBalanceRestTemplate-hardcode1]" + result);
		result = noBalanceRestTemplate.getForObject("http://localhost:9908/get/"+id, String.class);
		System.out.println("[noBalanceRestTemplate-hardcode2]" + result);

		try {
			result = balanceRestTemplate.getForObject("http://localhost:9907/get/"+id, String.class);
			System.out.println("[balanceRestTemplate-hardcode1]" + result);
			result = balanceRestTemplate.getForObject("http://localhost:9908/get/"+id, String.class);
			System.out.println("[balanceRestTemplate-hardcode2]" + result);
		} catch (Exception e) {
			System.out.println("使用balanceRestTemplate同时使用地址硬编码错误：" + e.getMessage());
		}
		return "ribbon's demo,please to see console output";
	}

	@SuppressWarnings("unchecked")
	@GetMapping("listAll")
	public List<User> listAll() {
		// restTemplate怎样返回一个List对象
		List<User> list = balanceRestTemplate.getForObject("http://mima-cloud-producer/listAll", List.class);
		return list;
	}

}
