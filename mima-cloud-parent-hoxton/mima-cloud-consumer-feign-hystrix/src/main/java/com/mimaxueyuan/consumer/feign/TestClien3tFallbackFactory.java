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
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

//FallbackFactory的优点是可以获取到异常信息
@Component
public class TestClien3tFallbackFactory implements FallbackFactory<TestClient3> {

	@Override
	public TestClient3 create(Throwable cause) {
		return new TestClient3() {
			@Override
			public String get(String id) {
				return "get trigger hystrix open! reason:"+cause.getMessage();
			}

			@Override
			public User getUser(String id) {
				User errorUser = new User();
				errorUser.setId("getUser.errorId");
				errorUser.setName("getUser.errorName");
				return errorUser;
			}
		};
	}
	
}
