/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.zuul.filter;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

//自定义zuul拦截器
//系统自带拦截器在spring-cloud-netflix-core-1.4.0.RELEASE.jar中查看
@Component
public class QueryParamRouteFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		//是否开启拦截
		return true;
	}

	@Override
	public Object run() {
		System.out.println("QueryParamRouteFilter...");
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletResponse response = ctx.getResponse();
		try {
			HttpServletRequest request = ctx.getRequest();
			String flag = request.getParameter("flag");
			if("1".equals(flag)) {
				//不进行路由
				ctx.setSendZuulResponse(false);
				ctx.setResponseStatusCode(401);
				//返回错误消息
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.print("flag=1 return error!");
				outputStream.flush();
				outputStream.close();
			}else if("2".equals(flag)) {
				//不进行路由
				ctx.setSendZuulResponse(false);
				ctx.setResponseStatusCode(402);
				ctx.set("name", "kevin");
				//返回错误消息
				ctx.setResponseBody("flag=2 return error!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ctx.setResponse(response);
		return null;
	}

	@Override
	public String filterType() {
		//路由类型：pre、route、post
		return "route";
	}

	@Override
	public int filterOrder() {
		//拦截顺序
		return 0;
	}
}

