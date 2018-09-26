package com.ihere.voyage.annotation.bean;

/**
 * @author fengshibo
 * @create 2018-07-04 14:26
 * @desc ${DESCRIPTION}
 **/

import com.ihere.voyage.init.RequestBoohomeMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author:@
 * @Description:添加拦截器
 * @Date: 2017-12-23 16:35
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截请求，判断是否有 @RequestBoohome 注解
        registry.addInterceptor(requestBoohomeMethodInterceptor()).addPathPatterns("/1");
        super.addInterceptors(registry);
    }

    /**
     * 因为启用拦截器
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(responseBodyConverter());
        // 这里必须加上加载默认转换器，不然bug玩死人，并且该bug目前在网络上似乎没有解决方案
        // 百度，谷歌，各大论坛等。你可以试试去掉。
        addDefaultHttpMessageConverters(converters);
    }


    @Bean
    public RequestBoohomeMethodArgumentResolver requestBoohomeMethodInterceptor() {
        return new RequestBoohomeMethodArgumentResolver();
    }

}