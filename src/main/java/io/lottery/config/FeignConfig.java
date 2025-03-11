package io.lottery.config;


import feign.Client;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import io.lottery.feign.CustomFeignClient;
import io.lottery.intercepor.*;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;


@Configuration
public class FeignConfig {

    @Resource
    private OkHttpClient okHttpClient;

    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    @Bean
    public Client feignClient() {
        // 配置 OkHttpClient 不支持自动重定向
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .followRedirects(true)
                .cookieJar(new InMemoryCookieJar())
                .addInterceptor(new CookieAwareRedirectInterceptor()) //添加拦截器来自定义处理重定向问题
                .followSslRedirects(false)  // 启用 SSL 重定向
                .build();
        return new feign.okhttp.OkHttpClient(okHttpClient);
        //return new CustomFeignClient(new Client.Default(null,null));
    }

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        // 强制设置字符集为 UTF-8
        return new StringHttpMessageConverter(StandardCharsets.UTF_8);
    }
    @Bean
    public Decoder feignDecoder() {
        return new GzipDecoder(new CamelCaseDecoder());  // 使用自定义的 UTF-8 解码器
    }
}