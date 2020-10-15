package br.com.zup.nossobancodigital.infrastructure.service.email;

import br.com.zup.nossobancodigital.core.email.EmailProperties;
import br.com.zup.nossobancodigital.domain.service.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class SandboxEnvioEmailService extends SmtpEnvioEmailService  {

    @Autowired
    private EmailProperties emailProperties;

    // Separei a criação de MimeMessage em um método na classe pai (criarMimeMessage),
    // para possibilitar a sobrescrita desse método aqui
    @Override
    protected MimeMessage criarMimeMessage(EnvioEmailService.Mensagem mensagem) throws MessagingException {
        MimeMessage mimeMessage = super.criarMimeMessage(mensagem);

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setTo(emailProperties.getSandbox().getDestinatario());

        return mimeMessage;
    }
}
