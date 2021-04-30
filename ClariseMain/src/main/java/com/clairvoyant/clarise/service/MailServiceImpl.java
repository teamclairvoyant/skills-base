package com.clairvoyant.clarise.service;


import com.clairvoyant.clarise.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${app.baseurl}")
    private String baseurl;

    @Value("${mail.invitation.subject}")
    private String subject;

    public void sendEmail(Employee employee) throws MessagingException {


        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setTo(employee.getEmail());

        String url = baseurl + employee.getEmpId();

        helper.setSubject(subject);

        helper.setText("<html>" +
                "<body>" +
                "Invitation Link : "+
                "<a href='"+url+"'>"+url+"</a></body>" +
                "</html>", true);
        
        javaMailSender.send(mail);
    }
}
