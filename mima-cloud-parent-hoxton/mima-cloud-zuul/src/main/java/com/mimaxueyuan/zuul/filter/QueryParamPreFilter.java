/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

//自定义zuul拦截器
//系统自带拦截器在spring-cloud-netflix-core-1.4.0.RELEASE.jar中查看
@Component
public class QueryParamPreFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		//是否开启拦截
		return true;
	}

	@Override
	public Object run() {
		System.out.println("QueryParamPreFilter...");
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String requestUrl = request.getRequestURI();
		System.out.println(requestUrl);
		return null;
	}

	@Override
	public String filterType() {
		//路由类型：pre、route、post
		return "pre";
	}

	@Override
	public int filterOrder() {
		//拦截顺序
		return 0;
	}
}

