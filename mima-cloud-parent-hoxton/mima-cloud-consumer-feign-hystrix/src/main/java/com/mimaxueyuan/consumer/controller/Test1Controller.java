/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.consumer.controller;

import java.util.List;

import com.mimaxueyuan.consumer.entity.User;
import com.mimaxueyuan.consumer.feign.TestClient1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test1Controller {
	
	@Autowired
	private TestClient1 testClient1;
	
	@GetMapping("/feign1/get/{id}")
	public String get(@PathVariable String id) {
		String result = testClient1.get(id);
		return result;
	}
	
	@GetMapping("/feign1/getuser/{id}")
	public User getUser(@PathVariable String id) {
		User result = testClient1.getUser(id);
		return result;
	}
	
	@GetMapping("/feign1/getuser2")
	public User getUser2(User user) {
		User result = testClient1.getUser2(new User());
		return result;
	}
	
	@GetMapping("/feign1/listAll")
	public List<User> listAll() {
		return testClient1.listAll();
	}
	
	@PostMapping("/feign1/postuser")
	public User postUser(@RequestBody User user) {
		User result = testClient1.postUser(user);
		return result;
	}
	
	@PostMapping("/feign1/postuser2")
	public User postUser2(@RequestBody User user) {
		User result = testClient1.postUser2(user);
		return result;
	}
	
	@PostMapping("/feign1/postuser3")
	public User postUser3(@RequestBody User user) {
		User result = testClient1.postUser3(user);
		return result;
	}
	
}
