package com.mkevin.jwt.server.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.concurrent.TimeUnit;

/**
 * @author zhaibo
 * @description 认证服务器配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("permitAll()")
                //.checkTokenAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()")
                .passwordEncoder(passwordEncoder)
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenServices(this.defaultTokenServices());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //对密码进行加密
        String secret = new BCryptPasswordEncoder().encode("123456");
        //能够使用内存或者JDBC来实现客户端详情服务
        //此处使用：内存式
        clients.inMemory()
                //创建客户端id和密码
                .withClient("client")
                .secret(secret)
                //支持的oath2.0的授权模式
                .authorizedGrantTypes("authorization_code", "client_credentials", "password", "implicit", "refresh_token")
                //在authorization_code、implicit模式下的重定向地址
                .redirectUris("http://example.com")
                //作用范围,随便写
                .scopes("select")
                .accessTokenValiditySeconds(1200)
                .refreshTokenValiditySeconds(50000);
    }

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(this.redisConnectionFactory);
    }

    @Bean
    @Primary
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(this.tokenStore());
        tokenServices.setSupportRefreshToken(false);
        tokenServices.setTokenEnhancer(this.jwtAccessTokenConverter());
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.MINUTES.toSeconds(60));
        return tokenServices;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        //JWT Access Token 转换器，由加密串转为JSON
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //对称秘钥
        //converter.setSigningKey("$2a$10$vQ9xSeEV1lQ2RQnF6hLsB.0MVImwojWAPptW9SasE.4k4NeHrxhJK");
        //私钥签名、公钥验证
        converter.setSigningKey("-----BEGIN RSA PRIVATE KEY-----\n" +
                "MIIEowIBAAKCAQEAyTZHUvEeFCmD3qD3W05IEPlFCRhAp64W+YaOTSGC1+Z1uWBa\n" +
                "dujx39/cF4i1t5Prfo/F5t7tfb+EDrUIn3jxD8fmlrzC2OcoZlcsRAnLswtx7gJ4\n" +
                "z2K6nPlXvIp0zacSVxvEOxUIUNCa+6s+dA0K6g0DW5H/0gms0el+LLgRu87jdDWX\n" +
                "dzSmvIV+xr/pAM3GxgWTeNdngl9UxnevVs26L9UpFwjIDoeQqsQJN20Y7NsXNoL3\n" +
                "9sKrsxBjHY+iG52Z5i7Ds09Z5DvABW5/gKiSeF6UkhiARaq/lKBbGuRkGjvBdFOS\n" +
                "Z1ZP45KmMA8lQ1VvJnyh9A1gkwxOKMXLkYd3MQIDAQABAoIBAFIC5Z9Q18mwAifD\n" +
                "xA5bIdSJRTInyHKP6p1Z0rZtaj9Fl8YvF7BP0gZF4BdXiXmBuQKk5yzqwDH41YKJ\n" +
                "FzKowNiFyd62oVR3I2hTL5+rvnMDvzQbndIpk5ZXqwMWC/WmmzsV/i12rSUDYagi\n" +
                "bBoxFrm/BBdWssaRWvdUDDF99Pxu5YDvmXntIJlhbvXcUN4PofHMRQiRRTQeXD9Q\n" +
                "LGvhsJ1WIgdGmlZagHO6SRTLlJd9cfrqgCmGQpg/LnRPM/fXGR2B82lAeEUv10fD\n" +
                "2WPXcTl8rlSi/isUUN/twKc3GaqYrnRrCJ3MiJT8Lg1FYf/AwnNZ1rb3OppsR0Ag\n" +
                "EF1P3oECgYEA9X/oRO+4/naicRnVhN4FeR3r1FgE87Bljna1s9pqgQguBQmgdn46\n" +
                "Zj154OwPdsN1S5Yg3RDdjZmnjfCPlEFNHBGpleHhQRI7I2DDeabluLHHluCgY4Mw\n" +
                "4eZca5vaImZVtA4sQz8yaR10mX/wGUw5B5tW7FWliyh/2UbgNIoewi0CgYEA0dFx\n" +
                "8oYxari39jinKf9bM84lVbzccNrpcMU//dLjmhMeD8J+xHGp8o08FKz/i+XoqAxe\n" +
                "KljC4ihrc7H3xWi8vWv9dwHFfFwo+VczoxpivCwe2fPaOP4Z6g9+45CZizZxz6GO\n" +
                "6xhMA+TQ96qlEoJ4T24MY39zZU8wNPlw5N+xH5UCgYEAr8WrvKfS0UaJmecATIhW\n" +
                "bNhygG+g5AWZQP5XrHUmqkn8ARlabVyFXayIdfUuQT9C3SKZVw57QqYQJH1nn6N9\n" +
                "nSo8PJckm119QCBI9PH9KlcHa0xbKcTFnAg+hcFp1hVlKWy4XlGCO2aelETY2JQN\n" +
                "hRfAjafoxhDyMNQhNRzrVuUCgYBrYdsEeWNvMCyOaj47g0IlCFsZPzg+1frlST5P\n" +
                "5I+xuhkHjc4dMeL9jQTzu/ppmffxkarb12OeJXug0bNyKAF4nH0zXAe7dttNiTCX\n" +
                "SBjCH36Go4PK6VlP7jBNvSKoGewzjIa9kUjOMVw1dPNYvsdeN39FqOPhNJ8Cbas2\n" +
                "p7lZ5QKBgCMr2TjoBu5qbu5u2wujj1eyVLt8CkQUgSjUCnbaZmTGEtyAFGOXTnQO\n" +
                "gX/r2sWBkisUkYQjcb1im1ouNlilajzllrqd3t9FBwVqs6gWFkywvgHbu0m8121w\n" +
                "Cr9uYKibl8jaVGGMte878hdjK9Gzan7iwFY3Enn5dsUp0d5Hg4DQ\n" +
                "-----END RSA PRIVATE KEY-----");
        converter.setVerifierKey("-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyTZHUvEeFCmD3qD3W05I\n" +
                "EPlFCRhAp64W+YaOTSGC1+Z1uWBadujx39/cF4i1t5Prfo/F5t7tfb+EDrUIn3jx\n" +
                "D8fmlrzC2OcoZlcsRAnLswtx7gJ4z2K6nPlXvIp0zacSVxvEOxUIUNCa+6s+dA0K\n" +
                "6g0DW5H/0gms0el+LLgRu87jdDWXdzSmvIV+xr/pAM3GxgWTeNdngl9UxnevVs26\n" +
                "L9UpFwjIDoeQqsQJN20Y7NsXNoL39sKrsxBjHY+iG52Z5i7Ds09Z5DvABW5/gKiS\n" +
                "eF6UkhiARaq/lKBbGuRkGjvBdFOSZ1ZP45KmMA8lQ1VvJnyh9A1gkwxOKMXLkYd3\n" +
                "MQIDAQAB\n" +
                "-----END PUBLIC KEY-----");
        return converter;
    }

    public static void main(String[] args) {
        //生成个加密串作为签名串
        String clientSecret = "kevin";
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode(clientSecret));

        //延签解密jwt
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJzZWxlY3QiXSwiZXhwIjoxNTgyMzAxNDM1LCJqdGkiOiJlOWNiNTFjMi0wZjA5LTQ3MTktOTdhZS1iNjQ5NDk4NjM5YzYiLCJjbGllbnRfaWQiOiJjbGllbnQifQ.JXhSCuXlmspmtSCcgMdjR6PPZ4Pa7-7IXwwqJ4Um5p3bT9AUJVo8v3yjnfaZnSpFr3IpVThbvovZV-48N4AVtJoBm-cYmR4ecZINs8txr5eEUl3UU5rA7cZdCfLeNAf4dj2E0sRbB0hZlv_OnsY61eIo9A-V3gUF3gx-322qHn1SMCZCHE5zgROATNgxJA48BwBBBBY-_oLvuEyCUjaUkK68pGR6zEHW6ioKLUTtvRWSYiMl-m_BAhzFNNB7HfiWUT4OVT4PfjYdC2P9lXiulaC1sz24W4eofi7CYtWrxEcZKspQuKEStFmdvkWNRpmcyUpJokJZAmgiE4Fd6r5yqw";
        String publicKey  = "-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyTZHUvEeFCmD3qD3W05I\n" +
                "EPlFCRhAp64W+YaOTSGC1+Z1uWBadujx39/cF4i1t5Prfo/F5t7tfb+EDrUIn3jx\n" +
                "D8fmlrzC2OcoZlcsRAnLswtx7gJ4z2K6nPlXvIp0zacSVxvEOxUIUNCa+6s+dA0K\n" +
                "6g0DW5H/0gms0el+LLgRu87jdDWXdzSmvIV+xr/pAM3GxgWTeNdngl9UxnevVs26\n" +
                "L9UpFwjIDoeQqsQJN20Y7NsXNoL39sKrsxBjHY+iG52Z5i7Ds09Z5DvABW5/gKiS\n" +
                "eF6UkhiARaq/lKBbGuRkGjvBdFOSZ1ZP45KmMA8lQ1VvJnyh9A1gkwxOKMXLkYd3\n" +
                "MQIDAQAB\n" +
                "-----END PUBLIC KEY-----";
        System.out.println(JwtHelper.decodeAndVerify(token, new RsaVerifier(publicKey)));
    }
}