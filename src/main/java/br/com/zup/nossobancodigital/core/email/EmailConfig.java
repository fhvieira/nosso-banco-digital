package br.com.zup.nossobancodigital.core.email;

import br.com.zup.nossobancodigital.domain.service.EnvioEmailService;
import br.com.zup.nossobancodigital.infrastructure.service.email.FakeEnvioEmailService;
import br.com.zup.nossobancodigital.infrastructure.service.email.SandboxEnvioEmailService;
import br.com.zup.nossobancodigital.infrastructure.service.email.SmtpEnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EnvioEmailService envioEmailService() {

        switch (emailProperties.getImpl()) {
            case FAKE:
                return new FakeEnvioEmailService();
            case SMTP:
                return new SmtpEnvioEmailService();
            case SANDBOX:
                return new SandboxEnvioEmailService();
            default:
                return null;
        }
    }
}
