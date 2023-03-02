package es.codeurjc.readmebookstore.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import javax.mail.MessagingException;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendConfirmationEmail(String to, String subject, String body) throws MessagingException {

        //MimeMessage message = javaMailSender.createMimeMessage();
        //MimeMessageHelper helper = new MimeMessageHelper(message, true);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("dawdawdaw289@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
        System.out.println("Correo enviado existosamente");
    }
}

