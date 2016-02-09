package kr.ac.skhu.drcode.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerMapperConfig {
    @Bean
    public DozerBeanMapper dozer() {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        return dozerBeanMapper;
    }
}
