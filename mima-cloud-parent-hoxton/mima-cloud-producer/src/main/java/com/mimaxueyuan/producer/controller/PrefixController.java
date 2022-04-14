/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.producer.controller;

import com.mimaxueyuan.producer.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/prefix")
public class PrefixController {
	
	@GetMapping("/get/{id}")
	public String get(@PathVariable String id) {
		return "kevin"+id;
	}

	@GetMapping("/get/user/{id}")
	public String getuser(@PathVariable String id) {
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

	@GetMapping("/getheader")
	public Map<String, String> getheader(HttpServletRequest request) {
		Map<String,String> map = new HashMap<String,String>();
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()){
			String name = headerNames.nextElement();
			String value = request.getHeader(name);
			map.put(name,value);
		}
		return map;
	}

	@GetMapping("/getheader/{param}")
	public Map<String, String> getheader2(HttpServletRequest request) {
		Map<String,String> map = new HashMap<String,String>();
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()){
			String name = headerNames.nextElement();
			String value = request.getHeader(name);
			map.put(name,value);
		}
		return map;
	}

	@GetMapping("/getparam")
	public Map<String, String[]> getparam1(HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		return parameterMap;
	}

	@GetMapping("/getparam/{seq}")
	public Map<String, String[]> getparam2(HttpServletRequest request) {
		Map<String, String[]> parameterMap = request.getParameterMap();
		return parameterMap;
	}

	@GetMapping("/getheaderdupe")
	public Map<String, String> getheader3(HttpServletResponse response) {
		response.setHeader("rep-id","123456");
		response.setHeader("rep-author","yin.hl");

		Map<String,String> map = new HashMap<String,String>();
		Collection<String> headerNames = response.getHeaderNames();
		for(String name:headerNames){
			String value = response.getHeader(name);
			map.put(name,value);
		}
		return map;
	}

	@GetMapping("/timeout")
	public String getheader3() throws InterruptedException {
		TimeUnit.MINUTES.sleep(2);
		return "timeout";
	}

	// 断路器fallback
	public String returnError() {
		System.out.println(Thread.currentThread().getName()+":getError before ....");
		System.out.println(Thread.currentThread().getName()+":getError after ....");
		return "断路器返回";
	}
}
