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
import org.springframework.web.bind.annotation.*;

import java.util.List;

//定义Feign客户端,value参数为provider的serviceName。name参数实际是value的别名
//@FeignClient("mima-cloud-producer")与@FeignClient(name="mima-cloud-producer")本质相同
//@FeignClient(url="")参数已经作废，必须使用name属性
//如果设置url属性, 则name属性则只代表Feign客户端的别名,而不代表服务端的serviceName
@FeignClient(name="mima-cloud-producer")
public interface FeignTestClient {

	// 可以使用GetMapping组合注解,以前是不能使用的
	@GetMapping(value = "/get/{id}")
	// @PathVariable必须指定value,否则异常:PathVariable annotation was empty on param 0.
	public String get(@PathVariable("id") String id);

	@RequestMapping(value = "/getuser/{id}")
	public User getUser(@PathVariable("id") String id);
	
	// 调用远程的post方法,如果参数为复杂对象,就算指定了method=RequestMethod.GET,依然会使用post方式请求
	// 远程的方法是get的时候就会失败，错误消息: status 405 reading FeignTestClient#getUser2(User); content:{"timestamp":1511326531240,"status":405,"error":"Method Not Allowed","exception":"org.springframework.web.HttpRequestMethodNotSupportedException","message":"Request method 'POST' not supported","path":"/getuser2"}
	@RequestMapping(value = "/getuser2", method = RequestMethod.GET)
	public User getUser2(User user);

	// 调用远程的post方法,可以使用@RequestBody
	@RequestMapping(value = "/postuser")
	public User postUser(@RequestBody User user);

	// 调用远程的post方法,可以不使用@RequestBody
	@RequestMapping(value = "/postuser")
	public User postUser2(User user);

	// 调用远程的post方法,如果参数为复杂对象,就算指定了method=RequestMethod.GET,依然会使用post方式请求
	// 远程的方法也是post的，所以可以调用成功
	@RequestMapping(value = "/postuser", method = RequestMethod.GET)
	public User postUser3(User user);

	@GetMapping(value = "/listAll")
	List<User> listAll();
}
