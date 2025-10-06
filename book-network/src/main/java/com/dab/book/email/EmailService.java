package com.dab.book.email;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final SpringTemplateEngine templateEngine;

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    @Value("${sendgrid.from.email}")
    private String fromEmail;

    @Value("${sendgrid.from.name:Book Social Network}")
    private String fromName;

    @Async
    public void sendEmail(
            String to,
            String username,
            EmailTemplateName emailTemplate,
            String confirmationUrl,
            String activationCode,
            String subject
    ) throws MessagingException {
        try {
            String templateName = (emailTemplate == null)
                    ? "confirm-email"
                    : emailTemplate.getName();

            // Construir el contenido HTML usando Thymeleaf
            Map<String, Object> properties = new HashMap<>();
            properties.put("username", username);
            properties.put("confirmationUrl", confirmationUrl);
            properties.put("activation_code", activationCode);

            Context context = new Context();
            context.setVariables(properties);
            String htmlContent = templateEngine.process(templateName, context);

            // Configurar SendGrid
            Email from = new Email(fromEmail, fromName);
            Email toEmail = new Email(to);
            Content content = new Content("text/html", htmlContent);
            Mail mail = new Mail(from, subject, toEmail, content);

            // Enviar email usando la API HTTP de SendGrid
            SendGrid sg = new SendGrid(sendGridApiKey);
            Request request = new Request();

            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());

                Response response = sg.api(request);

                if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                    log.info("✅ Email enviado exitosamente a: {} - Status: {}", to, response.getStatusCode());
                } else {
                    log.error("❌ Error al enviar email a: {}. Status: {}, Body: {}",
                            to, response.getStatusCode(), response.getBody());
                    throw new MessagingException("Error al enviar email: HTTP " + response.getStatusCode());
                }

            } catch (IOException ex) {
                log.error("❌ Error de IO al enviar correo a {}: {}", to, ex.getMessage());
                throw new MessagingException("Error de comunicación con SendGrid", ex);
            }

        } catch (MessagingException e) {
            // Re-lanzar MessagingException para mantener compatibilidad con AuthenticationService
            throw e;
        } catch (Exception e) {
            log.error("❌ Error al procesar email para {}: {}", to, e.getMessage(), e);
            throw new MessagingException("Error al enviar correo", e);
        }
    }
}
