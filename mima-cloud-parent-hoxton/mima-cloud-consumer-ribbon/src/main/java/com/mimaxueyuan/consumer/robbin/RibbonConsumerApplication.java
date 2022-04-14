/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.consumer.robbin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class RibbonConsumerApplication {

	@Bean
	@LoadBalanced // 需要使用负载均衡,必须与Bean一同使用
	public RestTemplate balanceRestTemplate() {
		return new RestTemplate();
	}
	
	@Primary
	@Bean //需要多个RestTemplate, 有的RestTemplate使用负载均衡，有的不使用,不使用的不增加@LoadBalanced注解
	public RestTemplate noBalanceRestTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RibbonConsumerApplication.class, args);
	}

}
