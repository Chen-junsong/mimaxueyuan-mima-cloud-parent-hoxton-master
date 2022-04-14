/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.zuul.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class AllZuulFallbackProvider implements FallbackProvider {

	@Override
	public String getRoute() {
		//所有
		return "*";
	}

	//Hoxton中改为此方法
	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		return new ClientHttpResponse() {

			@Override
			public InputStream getBody() throws IOException {
				return new ByteArrayInputStream(("fallback"+cause.getMessage()).getBytes());
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

	//Hoxton中该方法废除
	public ClientHttpResponse fallbackResponse(Throwable cause) {
		return new ClientHttpResponse() {

			@Override
			public InputStream getBody() throws IOException {
				return new ByteArrayInputStream(("fallback"+cause.getMessage()).getBytes());
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

}
