package cn.gcsp.i18ndemo.config;

import cn.gcsp.i18ndemo.filter.LocaleFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

/**
 * @author gongchengship@163.com
 */
@Configuration
public class I18nConfig {

    @Bean
    public FilterRegistrationBean<LocaleFilter> localeFilter() {
        FilterRegistrationBean<LocaleFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LocaleFilter());
        registrationBean.addUrlPatterns("/*"); // 应用到所有 URL
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE); // 设置最高优先级
        return registrationBean;
    }

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH); // Set your default locale here
        return localeResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages"); // Set your base name here
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }


    /**
     * Add a LocaleChangeInterceptor to the interceptor registry.
     * This interceptor is used to change the locale based on the "lang" parameter in the request.
     * The "lang" parameter is used to specify the language code, such as "en" for English and "zh" for Chinese.
     * The interceptor will intercept the request and change the locale based on the value of the "lang" parameter.
     *
     * @return A WebMvcConfigurer object that adds the LocaleChangeInterceptor to the interceptor registry.
     */
    @Bean
    public WebMvcConfigurer localeConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
                interceptor.setParamName("lang");
                //Used for ignoring the invalid value of parameter "lang", instead of throwing an exception.
                interceptor.setIgnoreInvalidLocale(true);
                registry.addInterceptor(interceptor)
                        .addPathPatterns("/**");
            }
        };
    }



}
