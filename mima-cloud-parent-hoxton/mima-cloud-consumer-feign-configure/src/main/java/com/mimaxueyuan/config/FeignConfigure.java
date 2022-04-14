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

import feign.Contract;
import feign.Logger;

//自定义过FeignConfigure用于覆盖默认的FeignClientsConfigurationFeignClientsConfiguration
//为了不出现ApplicationContext的冲突问题
//不能将配置类放置在@SpringBootApplication类的同级和子集中
//也不能放在@ComponentScan扫描的路径下
@Configuration
public class FeignConfigure {

	// FeignClientsConfiguration 的默认配置如下
	// Decoder feignDecoder: ResponseEntityDecoder (which wraps a SpringDecoder)
	// Encoder feignEncoder: SpringEncoder
	// Logger feignLogger: Slf4jLogger
	// Contract feignContract: SpringMvcContract Feign.Builder
	// feignBuilder: HystrixFeign.Builder Client
	// feignClient: if Ribbon is enabled it is a LoadBalancerFeignClient, otherwise the default feign client is used.
	@Bean
	public Contract feignContract() {
		// 使用Feign自身的默认契约, 而FeignClientsConfiguration中使用的是SpringMVC的契约,所以可以使用SpringMVC的注解
		return new feign.Contract.Default();
	}

	// 设置FeignClient的日志输出级别
	// NONE，没有日志记录（默认）。
	// BASIC，只记录请求方法和URL以及响应状态代码和执行时间。
	// HEADERS，记录请求和响应头的基本信息。
	// FULL，记录请求和响应的头、正文和元数据。
	// 注意：想要让FeignClient输出日志, 除了实现feignLoggerLevel外,还要在application.yml中配置：logging.level.${FeignClient接口类的全路径}: DEBUG，并且只能设置为DEBUG级别
	// application.yml配置样例如下:
	// logging:
	// 		level:
	//		FeignTestClient: DEBUG
	// 日志输出样例如下：
	// 2017-11-23 10:57:00.337 DEBUG 12752 --- [nio-8807-exec-5] c.m.consumer.feign.FeignTestClient : [FeignTestClient#get] ---> GET http://mima-cloud-producer/get/123 HTTP/1.1
	// 2017-11-23 10:57:00.341 DEBUG 12752 --- [nio-8807-exec-5] c.m.consumer.feign.FeignTestClient : [FeignTestClient#get] ---> END HTTP (0-byte body)
	// 2017-11-23 10:57:00.369 DEBUG 12752 --- [nio-8807-exec-5] c.m.consumer.feign.FeignTestClient : [FeignTestClient#get] <--- HTTP/1.1 200 (27ms)
	// 2017-11-23 10:57:00.370 DEBUG 12752 --- [nio-8807-exec-5] c.m.consumer.feign.FeignTestClient : [FeignTestClient#get] content-length: 8
	// 2017-11-23 10:57:00.370 DEBUG 12752 --- [nio-8807-exec-5] c.m.consumer.feign.FeignTestClient : [FeignTestClient#get] content-type: text/plain;charset=UTF-8
	// 2017-11-23 10:57:00.370 DEBUG 12752 --- [nio-8807-exec-5] c.m.consumer.feign.FeignTestClient : [FeignTestClient#get] date: Thu, 23 Nov 2017 02:57:00 GMT
	// 2017-11-23 10:57:00.371 DEBUG 12752 --- [nio-8807-exec-5] c.m.consumer.feign.FeignTestClient : [FeignTestClient#get] x-application-context: mima-cloud-producer:9902
	// 2017-11-23 10:57:00.371 DEBUG 12752 --- [nio-8807-exec-5] c.m.consumer.feign.FeignTestClient : [FeignTestClient#get]
	// 2017-11-23 10:57:00.372 DEBUG 12752 --- [nio-8807-exec-5] c.m.consumer.feign.FeignTestClient : [FeignTestClient#get] kevin123
	// 2017-11-23 10:57:00.373 DEBUG 12752 --- [nio-8807-exec-5] c.m.consumer.feign.FeignTestClient : [FeignTestClient#get] <--- END HTTP (8-byte body)
	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

	// 基础验证拦截配置, 如果provider端开启了基础验证，则需要进行在此处设置用户名密码
	// @Bean
	// public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
	// return new BasicAuthRequestInterceptor("user", "password");
	// }

}
