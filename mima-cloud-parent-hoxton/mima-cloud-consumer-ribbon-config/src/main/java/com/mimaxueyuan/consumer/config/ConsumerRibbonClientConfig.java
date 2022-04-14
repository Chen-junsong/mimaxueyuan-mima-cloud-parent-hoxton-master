/**
 * @密码学院-尹洪亮(kevin)版权所有,私自转发、售卖、传播将追究法律责任
 * @作者QQ:1562486028
 * @技术交流群:660567408 (免费课程源码下载、任何问题的交流)
 * 
 * @打开如下连接(最好在手机上)成为我们得推广员,佣金高达30%,支付宝到账结算，期待您的加入。
 *  https://m.study.163.com/promotion/invite?invitingCode=C8QOKQ
 */
package com.mimaxueyuan.consumer.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.ribbon.ZonePreferenceServerListFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ConfigurationBasedServerList;
import com.netflix.loadbalancer.DummyPing;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PollingServerListUpdater;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import com.netflix.loadbalancer.ServerListFilter;
import com.netflix.loadbalancer.ServerListUpdater;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;

//自定义ribbon策略可以覆盖RibbonClientConfiguration中的默认配置。
//RibbonClientConfiguration每个方法都可以被覆盖
//自定义的Ribbon配置不能放在SpringApplication的同级或下级目录中会覆盖所有的Ribbon客户端配置,所有的客户端都使用了相同的配置。
//自定义的Ribbon配置不能放在ComponentScan的目录中否则次配置会覆盖所有的Ribbon客户端配置,所有的客户端都使用了相同的配置。
@Configuration
public class ConsumerRibbonClientConfig {

	public static final int DEFAULT_CONNECT_TIMEOUT = 1000;
	public static final int DEFAULT_READ_TIMEOUT = 1000;
	
	@Bean
	public IRule ribbonRule(IClientConfig config) {
		ZoneAvoidanceRule rule = new ZoneAvoidanceRule();
		rule.initWithNiwsConfig(config);
		return rule;
	}
	
	// 检测服务是否存活
	@Bean
	public IPing ribbonPing(IClientConfig config) {
		return new DummyPing();
	}

	/* 服务列保, 覆盖此方法,必须在yml/properties文件中配置服务列表
	@Bean
	public ServerList<Server> ribbonServerList(IClientConfig config) {
		ConfigurationBasedServerList serverList = new ConfigurationBasedServerList();
		serverList.initWithNiwsConfig(config);
		return serverList;
	}
	*/

	//服务列表更新器
	@Bean
	public ServerListUpdater ribbonServerListUpdater(IClientConfig config) {
		return new PollingServerListUpdater(config);
	}

	//负载均衡器
	@Bean
	public ILoadBalancer ribbonLoadBalancer(IClientConfig config,
			ServerList<Server> serverList, ServerListFilter<Server> serverListFilter,
			IRule rule, IPing ping, ServerListUpdater serverListUpdater) {
		return new ZoneAwareLoadBalancer<>(config, rule, ping, serverList,
				serverListFilter, serverListUpdater);
	}


	//此接口允许过滤配置或动态获得的具有良好特性的候选服务器列表。
	@Bean
	public ServerListFilter<Server> ribbonServerListFilter(IClientConfig config) {
		ZonePreferenceServerListFilter filter = new ZonePreferenceServerListFilter();
		filter.initWithNiwsConfig(config);
		return filter;
	}
}
