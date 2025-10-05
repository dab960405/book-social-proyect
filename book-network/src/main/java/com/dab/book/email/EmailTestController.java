package com.dab.book.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class EmailTestController {

    private final JavaMailSender mailSender;

    @GetMapping("/email")
    public String sendTestEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            // 👇 Cambia esto por un correo real donde quieras recibir la prueba
            message.setTo("tu.correo.real@gmail.com");

            // 👇 Debe ser tu dirección de remitente verificada en SendGrid
            message.setFrom("diegobravo.c96@gmail.com");

            message.setSubject("🎯 Prueba de Envío con SendGrid desde Book Network");
            message.setText("¡Hola! Esto es una prueba simple desde tu aplicación usando SendGrid.");

            mailSender.send(message);
            return "✅ Correo de prueba enviado correctamente. Revisa tu bandeja o el Activity de SendGrid.";

        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Error al intentar enviar el correo: " + e.getMessage();
        }
    }
}
