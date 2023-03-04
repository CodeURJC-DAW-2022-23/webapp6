package es.codeurjc.readmebookstore.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendConfirmationEmail(String to, String subject, String body) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        //SimpleMailMessage message = new SimpleMailMessage();

        helper.setFrom("dawdawdaw289@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);

       String content = "<html>"
                + "<head>"
                + "<style type=\"text/css\">"
                + "body {font-family: Arial, sans-serif; font-size: 16px; color: #444;}"
                + "h1 {color: #0E86D4;}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<h1>Bienvenido a ReadMe Bookstore</h1>"
                + "<p>" + body + "</p>"
                + "<p>Somos Readme. Gracias por registrarte en nuestra página web.</p>"
                + "<p>Un saludo,</p>"
                + "<p>Equipo Readme</p>"
                + "<img src='cid:image' alt='Logo de ReadMe Bookstore' >"                
                + "</body>"
                + "</html>";
        helper.setText(content, true);

        ClassPathResource resource = new ClassPathResource("static/img/logo.png");
        helper.addInline("image", resource);


        javaMailSender.send(message);
        System.out.println("Correo enviado existosamente");
    }
}

