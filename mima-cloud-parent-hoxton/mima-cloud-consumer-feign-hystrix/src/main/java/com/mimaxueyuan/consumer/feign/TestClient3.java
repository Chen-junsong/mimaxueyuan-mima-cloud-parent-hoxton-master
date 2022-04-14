/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.consumer.feign;

import com.mimaxueyuan.consumer.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//fallbackFactory 指定一个fallback工厂,与指定fallback不同, 此工厂可以用来获取到触发断路器的异常信息,TestClientFallbackFactory需要实现FallbackFactory类
@FeignClient(value="mima-cloud-producer",configuration= FeignClientsConfiguration.class,fallbackFactory=TestClien3tFallbackFactory.class)
public interface TestClient3 {
	
	@RequestMapping(value="/get/{id}",method=RequestMethod.GET)
	public String get(@PathVariable("id") String id);
	
	@RequestMapping(value = "/getuser/{id}")
	public User getUser(@PathVariable("id") String id);
	
}
