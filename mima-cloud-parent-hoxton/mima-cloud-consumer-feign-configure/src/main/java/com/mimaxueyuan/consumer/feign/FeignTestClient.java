/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.consumer.feign;


import com.mimaxueyuan.config.FeignConfigure;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

//定义Feign客户端,value参数为provider的serviceName。name参数实际是value的别名
//@FeignClient("mima-cloud-producer")与@FeignClient(name="mima-cloud-producer")本质相同
//@FeignClient(url="")参数已经作废，必须使用name属性
//如果设置url属性, 则name属性则只代表Feign客户端的别名,而不代表服务端的serviceName
@FeignClient(name="mima-cloud-producer",configuration=FeignConfigure.class)
public interface FeignTestClient {

	//由于使用了FeignConfigure的配置,使用了Feign自身的默认契约,所以这里只能使用Feign自己的注解
	//如果使用SpringMVC的契约,则可以使用SpringMVC的注解
	@RequestLine("GET /get/{id}")
	public String get(@Param("id") String id);

}
