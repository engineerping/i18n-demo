package cn.gcsp.i18ndemo.controller;

/**
 * @author gongchengship@163.com
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Locale;

@RestController
public class GreetingController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    @GetMapping("/greet")
    public String greet(
            @RequestHeader("Accept-Language") String acceptLanguage,
            @RequestParam(required = false) String name,
            HttpServletRequest request) {
        Locale locale = localeResolver.resolveLocale(request);

        String greeting = messageSource.getMessage("greeting", null, locale);
        String welcome = messageSource.getMessage("welcome", new Object[]{name != null ? name : "Guest"}, locale);

        return greeting + "\n" + welcome;
    }
}