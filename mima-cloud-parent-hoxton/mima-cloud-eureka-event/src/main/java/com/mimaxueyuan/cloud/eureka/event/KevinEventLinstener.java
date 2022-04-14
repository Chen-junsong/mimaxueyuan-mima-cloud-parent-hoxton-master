/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.cloud.eureka.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.InstanceInfo;

@Component
public class KevinEventLinstener {

	private Logger logger = LoggerFactory.getLogger(KevinEventLinstener.class);

	@EventListener
	public void listen(EurekaInstanceRegisteredEvent event) {
		InstanceInfo instanceInfo = event.getInstanceInfo();
		String ip = instanceInfo.getIPAddr();
		String id = instanceInfo.getInstanceId();
		logger.info(">>>>>>>>" + id + "已经注册到Eureka,IP=" + ip);
	}

	@EventListener
	public void listen(EurekaInstanceRenewedEvent event) {
		InstanceInfo instanceInfo = event.getInstanceInfo();
		String id = instanceInfo.getInstanceId();
		logger.info(">>>>>>>>"+id+"续约事件触发...");
	}

	@EventListener
	public void listen(EurekaRegistryAvailableEvent event) {
		logger.info(">>>>>>>>注册中心启动事件触发...");
	}

	@EventListener
	public void listen(EurekaServerStartedEvent event) {
		logger.info(">>>>>>>>EurekaServer启动事件触发...");
	}
	
	@EventListener
	public void listen(EurekaInstanceCanceledEvent  event) {
		String id = event.getServerId();
		logger.info(">>>>>>>>"+id+"从Eureka下线...");
	}

}
