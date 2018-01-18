package miw.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import miw.miscellaneous.TimeBasedAccessInterceptor;
import miw.resources.AdminResource;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*").allowedOrigins("*").maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TimeBasedAccessInterceptor()).addPathPatterns(AdminResource.ADMINS + AdminResource.OUT_OF_TIME + "/**")
                .excludePathPatterns("/foo/**");
    }

}
