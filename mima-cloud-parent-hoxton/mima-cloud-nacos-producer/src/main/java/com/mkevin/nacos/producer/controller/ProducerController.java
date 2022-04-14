/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mkevin.nacos.producer.controller;

import java.util.ArrayList;
import java.util.List;

import com.mkevin.nacos.producer.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
	
	@GetMapping("/get/{id}")
	public String get(@PathVariable String id) {
		return "kevin"+id;
	}
	
	@GetMapping("/getuser/{id}")
	public User getUser(@PathVariable String id) {
		System.out.println("getUser.....");
		User user = new User();
		user.setId(id);
		return user;
	}
	
	@PostMapping("/postuser")
	public User postUser(@RequestBody User user) {
		System.out.println("postUser.....");
		return user;
	}
	
	@GetMapping("/getuser2")
	public User getUser2(User user) {
		System.out.println("getUser2.....");
		return user;
	}
	
	@GetMapping("/listAll")
	public List<User> listAll(){
		List<User> users = new ArrayList<User>();
		users.add(new User("1","kevin1"));
		users.add(new User("2","kevin2"));
		users.add(new User("3","kevin3"));
		return users;
	}
}
