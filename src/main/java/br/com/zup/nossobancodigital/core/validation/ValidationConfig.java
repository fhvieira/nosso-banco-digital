package br.com.zup.nossobancodigital.core.validation;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {

    @Bean
    public LocalValidatorFactoryBean validator(MessageSource MessageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(MessageSource);

        return bean;
    }
}
