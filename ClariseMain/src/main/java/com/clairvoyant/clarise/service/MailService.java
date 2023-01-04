package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.entities.Employee;

import javax.mail.MessagingException;

public interface MailService {

    public void sendEmail(Employee employee) throws MessagingException;
}
