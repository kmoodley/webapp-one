package co.za.entelect.relationship.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer
{
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        //registry.addViewController("/login").setViewName("custom_login");
        registry.addViewController("/login").setViewName("auth/custombootstrap_login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
