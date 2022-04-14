/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.consumer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mimaxueyuan.consumer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/c/get/{id}")
	public String get(@PathVariable String id) {
		String result = restTemplate.getForObject("http://mima-cloud-producer/get/"+id, String.class);
		return result;
	}
	
	@GetMapping("/c/getuser/{id}")
	public User getUser(@PathVariable String id) {
		User result = restTemplate.getForObject("http://mima-cloud-producer/getuser/"+id, User.class);
		return result;
	}
	
	@PostMapping("/c/postuser")
	public User postUser(@RequestBody User user) {
		User result = restTemplate.postForObject("http://mima-cloud-producer/postuser", user, User.class);
		return result;
	}
	
	@GetMapping("/c/getuser2")
	public User getUser2(User user) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", user.getId());
		params.put("name", user.getName());
		//一下两种方式均可
		User result1 = restTemplate.getForObject("http://mima-cloud-producer/getuser2?id={id}&name={name}", User.class, params);
		User result2 = restTemplate.getForObject("http://mima-cloud-producer/getuser2?id={id}&name={name}", User.class, user.getId(),user.getName());
		return result2;
	}
	
	@GetMapping("/c/listAll")
	public List<User> listAll(){
		List<User> result = restTemplate.getForObject("http://mima-cloud-producer/listAll", List.class);
		return result;
	}
	
}
