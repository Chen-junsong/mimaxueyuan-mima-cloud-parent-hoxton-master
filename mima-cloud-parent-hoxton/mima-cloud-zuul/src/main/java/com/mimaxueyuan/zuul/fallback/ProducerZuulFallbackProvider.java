/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.zuul.fallback;

//Hoxton中ZuulFallbackProvider废除, 统一使用FallbackProvider接口
/*@Component
public class ProducerZuulFallbackProvider implements ZuulFallbackProvider {

	@Override
	public String getRoute() {
		//只针对一个微服务
		return "mima-cloud-producer";
	}

	@Override
	public ClientHttpResponse fallbackResponse() {
		return new ClientHttpResponse() {

			@Override
			public InputStream getBody() throws IOException {
				return new ByteArrayInputStream("fallback".getBytes());
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
			}

			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return 200;
			}

			@Override
			public String getStatusText() throws IOException {
				return "OK";
			}

			@Override
			public void close() {

			}

		};
	}

}*/

import org.springframework.stereotype.Component;

@Component
public class ProducerZuulFallbackProvider{


}
