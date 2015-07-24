/*
 * To change this template, choose Tools | Templates
 * and open the template instream the editor.
 */
package vijay.functestfrmwrk.utility;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author dhanya
 */
public final class SimpleMailSender {

    private static final SimpleMailSender mailSender = new SimpleMailSender();

    private SimpleMailSender() {
    }

    public static SimpleMailSender getInstance() {
        return mailSender;
    }

    public void send(String senderId, String recipients, String smtpMailHost, String smtpMailPort, String smtpMailUser, String smtpMailPassword, String subject, String body) {
        try {
            Message message = new MimeMessage(getSession(smtpMailHost, smtpMailPort, smtpMailUser, smtpMailPassword));

            message.addRecipients(RecipientType.TO, getRecipientAddress(recipients));

            message.addFrom(new InternetAddress[]{new InternetAddress(
                senderId)});

            message.setSubject(subject);

            message.setContent(body, "text/html");

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Address[] getRecipientAddress(String recipients) throws AddressException {
        Address[] recipientIds = null;
        if (StringUtils.contains(recipients, ',')) {
            String[] ids = recipients.split(",");
            recipientIds = new Address[ids.length];
            for (int i = 0; i < ids.length; i++) {
                recipientIds[i] = new InternetAddress(ids[i]);
            }
        } else {
            recipientIds = new Address[1];
            recipientIds[0] = new InternetAddress(recipients);
        }
        return recipientIds;

    }

    private Session getSession(String smtpMailHost, String smtpMailPort, String smtpMailUser, String smtpMailPassword) {
        Authenticator authenticator = new Authenticator(smtpMailUser, smtpMailPassword);

        String smtpHost = smtpMailHost; //getPropertyValue("smtp.host");
        String port = smtpMailPort;// getPropertyValue("port");

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());
        properties.setProperty("mail.smtp.auth", "true");

        properties.setProperty("mail.smtp.host", smtpHost);
        properties.setProperty("mail.smtp.port", port);

        return Session.getInstance(properties, authenticator);
    }

    private class Authenticator extends javax.mail.Authenticator {

        private final PasswordAuthentication authentication;

        public Authenticator(String smtpMailUser, String smtpMailPassword) {
            String username = smtpMailUser;
            String password = smtpMailPassword;
            authentication = new PasswordAuthentication(username, password);
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
        }
    }
}
