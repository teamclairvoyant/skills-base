package com.clairvoyant.services.skillmatrix.service;


import com.clairvoyant.services.skillmatrix.model.User;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${app.baseurl}")
    private String baseurl;

    @Value("${mail.invitation.subject}")
    private String subject;

    /**
     * This functionality is not in use
     */
    public void sendEmail(User user) throws MessagingException {


        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setTo(user.getEmailAddress());

        String url = baseurl + user.getId();

        helper.setSubject(subject);

        helper.setText("<html>"
            + "<body>"
            + "Invitation Link : "
            + "<a href='" + url + "'>"
            + url + "</a></body>"
            + "</html>", true);

        javaMailSender.send(mail);
    }
}
