package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * Service class for handling email-related operations.
 */
@Service
public class MailService {
    private final String senderEmail;
    private final String senderPassword;
    private final String smtpHost; // Replace with your university's SMTP server address
    private final int smtpPort;
    private final Properties props;
    private final Session session;

    /**
     * Constructor for MailService initializing email-related parameters.
     */
    public MailService(){
        senderEmail = "bilsyncapp@gmail.com";
        senderPassword = "cgaeqhlgtupekakf";
        smtpHost = "smtp.gmail.com";
        smtpPort=587;
        props = createMessageProp();
        session = createMailSession(props);
    }

    /**
     * Creates and configures the properties for sending email messages.
     *
     * @return Properties object with email configuration.
     */
    private Properties createMessageProp(){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        System.out.println("CheckPoint-1 passed");
        return props;
    }

    /**
     * Creates a mail session with authentication properties.
     *
     * @param props The properties for configuring the mail session.
     * @return The configured mail session.
     */
    private Session createMailSession(Properties props){
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
        return session;
    }

    /**
     * This method is for sending messages
     * @param mailTitle Mail title. Requirements:
     *          - Should not be empty
     * @param mailBody Mail content. Requirements:
     *          - Should Not be empty
     * @param recipientMail who to send mail to. Requirements:
     *          - Should not be empty
     *          - must contain '@' symbol
     * @return boolean to indicate the message's success
     */
    public boolean sendMail(String mailTitle, String mailBody, String recipientMail){
        //checking the param requirements
        if(mailTitle.isBlank() || mailBody.isBlank() ||recipientMail.isBlank()
                || !recipientMail.contains("@")){
            return false;//short circuit return
        }
        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);
            // Set the sender and recipient email addresses
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientMail));

            // Set the email subject and content
            message.setSubject(mailTitle);
            message.setText(mailBody);

            // Send the email
            Transport.send(message);
            System.out.println("CheckPoint-4 passed");
            System.out.println("Email sent successfully!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Email sending failed: " + e.getMessage());
            return false;
        }
    }
}
