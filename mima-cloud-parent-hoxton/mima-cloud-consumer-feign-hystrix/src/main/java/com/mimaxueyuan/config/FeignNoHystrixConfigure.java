/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import feign.Feign;
import feign.Retryer;

//此配置类为了关闭特定Feign客户端的Hystrix支持
@Configuration
public class FeignNoHystrixConfigure {

	// FeignClientsConfiguration 的默认配置如下
	// Decoder feignDecoder: ResponseEntityDecoder (which wraps a SpringDecoder)
	// Encoder feignEncoder: SpringEncoder
	// Logger feignLogger: Slf4jLogger
	// Contract feignContract: SpringMvcContract Feign.Builder
	// feignBuilder: HystrixFeign.Builder Client
	// feignClient: if Ribbon is enabled it is a LoadBalancerFeignClient, otherwise the default feign client is used.
	
	@Bean
	@Scope("prototype")
	public Feign.Builder feignBuilder(Retryer retryer) {
		return Feign.builder();
	}
	

}
