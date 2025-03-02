//package cn.gcsp.i18ndemo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.LocaleResolver;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
//import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
//import org.springframework.web.servlet.i18n.SessionLocaleResolver;
//
//import java.util.Locale;
//
///**
// * @author gongchengship@163.com
// */
//@Configuration
//public class I18nConfig {
//
//    /**
//     *
//     * @return
//     */
//    @Bean
//    public LocaleResolver localeResolver() {
////         AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
////         localeResolver.setDefaultLocale(Locale.US);
////         return localeResolver;
//        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//        localeResolver.setDefaultLocale(Locale.US);
//        return localeResolver;
//    }
//
////    // 语言切换拦截器（优化版）
////    @Bean
////    public WebMvcConfigurer localeConfigurer() {
////        return new WebMvcConfigurer() {
////            @Override
////            public void addInterceptors(InterceptorRegistry registry) {
////                LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
////                interceptor.setParamName("lang");
////                registry.addInterceptor(interceptor)
////                        .addPathPatterns("/**");
////            }
////        };
////    }
//}
