package edu.restService;

import edu.matc.util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;


/**
 * Source: https://www.digitalocean.com/community/tutorials/javamail-example-send-mail-in-java-smtp
 */

/**
 * This class' purpose is to send the email
 */
public class Email implements PropertiesLoader {

    /**
     * Instantiates a new Email.
     */
    public Email() {

    }

    /**
     * This method's purpose is to send email
     * @param properties the properties from the property file
     * @param session the session object
     * @param toEmail the email recipient
     * @param subject the subject of the email
     * @param body the content of the email
     */
    public static void sendEmail(Properties properties, Session session, String toEmail, String subject, String body, String replyTo) {

        final Logger logger = LogManager.getLogger();

        try {

            MimeMessage msg = new MimeMessage(session);

            //set content type
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            //set format
            msg.addHeader("format", "flowed");
            //set the content transfer encoding
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            //create new internet address and set the email from
            msg.setFrom(new InternetAddress(properties.getProperty("email.from"), properties.getProperty("email.from")));
            //set the recipient
            msg.setReplyTo(InternetAddress.parse(replyTo, false));
            //set the subject of the email
            msg.setSubject(subject, "UTF-8");
            //set the content of the email
            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            //send msg
            Transport.send(msg);
        } catch (Exception e) {
            logger.error("There was an error", e);
        }
    }
}
