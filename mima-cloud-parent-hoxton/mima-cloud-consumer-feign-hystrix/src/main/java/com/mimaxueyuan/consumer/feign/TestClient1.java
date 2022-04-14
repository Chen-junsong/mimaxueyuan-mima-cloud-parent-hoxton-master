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
import org.springframework.web.bind.annotation.*;

import java.util.List;

//fallback 指定一个回退类，这个类必须实现@FeignClient声明的接口,并且在spring context中
@FeignClient(value="mima-cloud-producer",configuration= FeignClientsConfiguration.class,fallback=TestClient1FallBack.class)
public interface TestClient1 {
	
		@GetMapping(value = "/get/{id}")
		public String get(@PathVariable("id") String id);

		@RequestMapping(value = "/getuser/{id}")
		public User getUser(@PathVariable("id") String id);
		
		@RequestMapping(value = "/getuser2", method = RequestMethod.GET)
		public User getUser2(User user);

		@RequestMapping(value = "/postuser")
		public User postUser(@RequestBody User user);

		@RequestMapping(value = "/postuser")
		public User postUser2(User user);

		@RequestMapping(value = "/postuser", method = RequestMethod.GET)
		public User postUser3(User user);

		@GetMapping(value = "/listAll")
		List<User> listAll();
	
}
