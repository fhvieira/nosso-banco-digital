package br.com.zup.nossobancodigital.infrasctructure.service.email;

import br.com.zup.nossobancodigital.domain.service.EnvioEmailService;
import br.com.zup.nossobancodigital.core.email.EmailProperties;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class SmtpEnvioEmailService implements EnvioEmailService {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    private Configuration freemarkerConfig;

    @Autowired
    private EmailProperties emailProperties;

    @Override
    public void enviar(EnvioEmailService.Mensagem mensagem) {
        try {
            MimeMessage mimeMessage = criarMimeMessage(mensagem);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("nao foi possivel enviar email", e);
        }
    }

    protected MimeMessage criarMimeMessage(Mensagem mensagem) throws MessagingException {
        String corpo = processarTemplate(mensagem);

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setFrom(emailProperties.getRemetente());
        helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
        helper.setSubject(mensagem.getAssunto());
        helper.setText(corpo, true);

        return mimeMessage;
    }

    protected String processarTemplate(Mensagem mensagem) {

        try {
            Template template = freemarkerConfig.getTemplate(mensagem.getCorpo());

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
        } catch (Exception e) {
            throw new EmailException("nao foi possivel montar o template do emaijl", e);
        }
    }
}