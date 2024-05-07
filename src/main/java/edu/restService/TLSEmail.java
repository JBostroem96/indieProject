package edu.restService;

import edu.matc.util.PropertiesLoader;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * Source: https://www.digitalocean.com/community/tutorials/javamail-example-send-mail-in-java-smtp
 */

/**
 * This class' purpose is to get the credentials and authenticate to send the email
 */
public class TLSEmail implements PropertiesLoader {

    private Properties properties;

    /**
     * Instantiates a new Tls email.
     */
    public TLSEmail() {
        loadProperties();
    }

    /**
     * This method's purpose is to load our property file
     */
    private void loadProperties() {

        properties = loadProperties("/email.properties");
    }

    /**
     * This method's purpose is to get the credentials and authenticate to send the email
     * @param subject the subject of the email
     * @param description the description of the email
     */
    public void simpleEmailWithTLS(String subject, String description) {

        final String fromEmail = properties.getProperty("email.from");
        final String password = properties.getProperty("email.pw");
        final String toEmail = properties.getProperty("email.to");

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");


        //Authenticate the email from
        Authenticator auth = new Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        Email.sendEmail(properties, session, toEmail, subject, description);
    }
}
