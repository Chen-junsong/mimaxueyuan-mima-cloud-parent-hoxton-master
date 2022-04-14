/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.consumer.feign;

import java.util.ArrayList;
import java.util.List;

import com.mimaxueyuan.consumer.entity.User;
import org.springframework.stereotype.Component;

@Component
public class TestClient1FallBack implements TestClient1 {
	@Override
	public String get(String id) {
		return "feign hystrix fallback for get method";
	}

	@Override
	public User getUser(String id) {
		User errorUser = new User();
		errorUser.setId("getUser.errorId");
		errorUser.setName("getUser.errorName");
		return errorUser;
	}

	@Override
	public User getUser2(User user) {
		User errorUser = new User();
		errorUser.setId("getUser2.errorId");
		errorUser.setName("getUser2.errorName");
		return errorUser;
	}

	@Override
	public User postUser(User user) {
		User errorUser = new User();
		errorUser.setId("postUser.errorId");
		errorUser.setName("postUser.errorName");
		return errorUser;
	}

	@Override
	public User postUser2(User user) {
		User errorUser = new User();
		errorUser.setId("postUser2.errorId");
		errorUser.setName("postUser2.errorName");
		return errorUser;
	}

	@Override
	public User postUser3(User user) {
		User errorUser = new User();
		errorUser.setId("postUser3.errorId");
		errorUser.setName("postUser3.errorName");
		return errorUser;
	}

	@Override
	public List<User> listAll() {
		User errorUser = new User();
		errorUser.setId("listAll.errorId");
		errorUser.setName("listAll.errorName");
		ArrayList<User> list = new ArrayList<User>();
		list.add(errorUser);
		return list;
	}
}
