package com.dab.book.email;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendEmail(
            String to,
            String username,
            EmailTemplateName emailTemplate,
            String confirmationUrl,
            String activationCode,
            String subject
    ) {
        try {
            String templateName = (emailTemplate == null)
                    ? "confirm-email"
                    : emailTemplate.getName();

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                    mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED,
                    StandardCharsets.UTF_8.name()
            );

            Map<String, Object> properties = new HashMap<>();
            properties.put("username", username);
            properties.put("confirmationUrl", confirmationUrl);
            properties.put("activation_code", activationCode);

            Context context = new Context();
            context.setVariables(properties);
            String template = templateEngine.process(templateName, context);

            // ✅ Usa tu remitente verificado de SendGrid
            helper.setFrom("diegobravo.c96@gmail.com");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(template, true);

            mailSender.send(mimeMessage);
            log.info("✅ Email enviado correctamente a {}", to);

        } catch (Exception e) {
            // 🔥 Captura y muestra cualquier excepción oculta del hilo @Async
            log.error("❌ Error al enviar correo a {}: {}", to, e.getMessage(), e);
        }
    }
}
