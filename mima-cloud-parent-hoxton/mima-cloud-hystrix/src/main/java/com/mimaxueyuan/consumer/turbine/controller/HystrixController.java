/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.consumer.turbine.controller;

import com.mimaxueyuan.consumer.turbine.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;

@RestController
public class HystrixController {

	@Autowired
	private MyService myService;

	/**
	 * @HystrixCommand由名为“javanica”的Netflix contrib库提供 。 
	 * Spring Cloud在连接到Hystrix断路器的代理中使用该注解自动包装Spring bean。
	 *  断路器计算何时打开和关闭电路，以及在发生故障时应该做什么。
	 *
	 * @author Kevin
	 * @Title: get
	 * @param id
	 * @return
	 * @return: String
	 */
	@GetMapping("/get1/{id}")
	@HystrixCommand(fallbackMethod = "getError")
	public String get1(@PathVariable String id) {
		try {
			System.out.println(Thread.currentThread().getName()+":get1 before sleep 5s....");
			Thread.sleep(1000 * 5);
			System.out.println(Thread.currentThread().getName()+":get1 after sleep 5s....");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "kevin.get1." + id;
	}

	/**
	 * 测试SEMAPHORE和THREAD隔离策略
	 * 
	 * execution.isolation.strategy 默认为THREAD
	 *
	 * @author Kevin
	 * @Title: get2 
	 * @param id
	 * @return
	 * @return: String
	 */
	@GetMapping("/get2/{id}")
	@HystrixCommand(fallbackMethod = "getError", commandProperties = {
			// @HystrixProperty(name=HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value="THREAD")
			@HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "SEMAPHORE")
			})
	public String get2(@PathVariable String id) {
		try {
			System.out.println(Thread.currentThread().getName()+":get2 before sleep 5s....");
			Thread.sleep(1000 * 5);
			System.out.println(Thread.currentThread().getName()+":get2 after sleep 5s....");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "kevin.get2." + id;
	}
	
	/**
	 * execution.isolation.thread.interruptOnTimeout 参数,默认为true
	 *
	 * @author Kevin
	 * @Title: get3 
	 * @param id
	 * @return
	 * @return: String
	 */
	@GetMapping("/get3/{id}")
	@HystrixCommand(fallbackMethod = "getError",commandProperties= {
			//设置超时的时候不中断线程,默认为true
			@HystrixProperty(name=HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_INTERRUPT_ON_TIMEOUT,value="false")
	})
	public String get3(@PathVariable String id) {
		try {
			System.out.println(Thread.currentThread().getName()+":get3 before sleep 5s....");
			Thread.sleep(1000 * 5);
			System.out.println(Thread.currentThread().getName()+":get3 after sleep 5s....");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "kevin.get3." + id;
	}
	
	/**
	 * fallback.isolation.semaphore.maxConcurrentRequests默认为10
	 *
	 * @author Kevin
	 * @Title: get4 
	 * @param id
	 * @return
	 * @return: String
	 */
	@GetMapping("/get4/{id}")
	@HystrixCommand(fallbackMethod = "getError",commandProperties= {
			@HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "SEMAPHORE"),
			@HystrixProperty(name=HystrixPropertiesManager.EXECUTION_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS,value="1")
	})
	public String get4(@PathVariable String id) {
		try {
			System.out.println(Thread.currentThread().getName()+":get4 before sleep 5s....");
			Thread.sleep(1000 * 5);
			System.out.println(Thread.currentThread().getName()+":get4 after sleep 5s....");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "kevin.get4." + id;
	}
	
	
	/**
	 * execution.timeout.enabled 默认为true, 是否超时
	 *
	 * @author Kevin
	 * @Title: get5 
	 * @param id
	 * @return
	 * @return: String
	 */
	@GetMapping("/get5/{id}")
	@HystrixCommand(fallbackMethod = "getError",commandProperties= {
			@HystrixProperty(name=HystrixPropertiesManager.EXECUTION_TIMEOUT_ENABLED,value="false")
	})
	public String get5(@PathVariable String id) {
		try {
			System.out.println(Thread.currentThread().getName()+":get5 before sleep 5s....");
			Thread.sleep(1000 * 5);
			System.out.println(Thread.currentThread().getName()+":get5 after sleep 5s....");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "kevin.get5." + id;
	}

	/**
	 * execution.isolation.thread.timeoutInMilliseconds 默认为1000
	 *
	 * @author Kevin
	 * @Title: get6
	 * @param id
	 * @return
	 * @return: String
	 */
	@GetMapping("/get6/{id}")
	@HystrixCommand(fallbackMethod = "getError",commandProperties= {
			@HystrixProperty(name=HystrixPropertiesManager.EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS,value="6000")
	})
	public String get6(@PathVariable String id) {
		try {
			System.out.println(Thread.currentThread().getName()+":get6 before sleep 5s....");
			Thread.sleep(1000 * 5);
			System.out.println(Thread.currentThread().getName()+":get6 after sleep 5s....");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "kevin.get6." + id;
	}
	
	public String getError(String id) {
		System.out.println(Thread.currentThread().getName()+":getError before ....");
		myService.execute();
		System.out.println(Thread.currentThread().getName()+":getError after ....");
		return "超时错误,使用断路器返回" + id;
	}
}
