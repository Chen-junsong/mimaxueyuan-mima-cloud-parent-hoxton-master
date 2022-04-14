/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
//开启Eureka客户端
@EnableEurekaClient
//开启Zuul代理,此注解内部已开启断路器@EnableCircuitBreaker
@EnableZuulProxy
public class ZuulApplication {
	
	//使用正则表达式映射，它能够从serviceId中提取变量，用于路由路径中
	/*@Bean
	public PatternServiceRouteMapper serviceRouteMapper() {
		//下面的意思是serviceId=name-version, 将被映射为version/name
	    //例如:user-v1 => v1/user
		//例如:order-v2 => v2/order
		return new PatternServiceRouteMapper(
	        "(?<name>^.+)-(?<version>v.+$)",
	        "${version}/${name}");
	}*/
	
	public static void main(String[] args) {
		//new SpringApplicationBuilder(ZuulApplication.class).web(true).run(args);
		SpringApplication.run(ZuulApplication.class, args);
	}
	
}
