package esc.dc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.ModelAndView;

@Configuration
public class BeanConfig {

    @Bean
    @Scope("prototype")
    public ModelAndView getModelAndView(){
        return new ModelAndView();
    }
}
