/*
 * Send a mail!
 */

package org.mirna.designer.controller;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author avarga
 */
public class MailService {

    static final String FROM = "MiRNADesignTool-Registration";
    static final String SMTP_ADDRESS = "smtp.gmail.com";
    static final String SMTP_PORT = "587";
    static final String SMTP_LOGIN = "mirnadesigntool";
    static final String SMTP_PASSWD = "m1Rnad3signt00l";
    
    public MailService() {}

    public void sendMail( String mailTo, String message, String subject ) throws Exception {
        
        Properties prop = new Properties();
        prop.setProperty( "mail.host", SMTP_ADDRESS );
        prop.setProperty( "mail.smtp.port", SMTP_PORT );
        prop.setProperty( "mail.smtp.auth", "true" );
        prop.setProperty( "mail.smtp.starttls.enable", "true" );

        Authenticator auth = new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication( SMTP_LOGIN, SMTP_PASSWD );

            }

        };

        Session session = Session.getInstance( prop, auth );

        MimeMessage msg = new MimeMessage( session );

        msg.addRecipient( Message.RecipientType.TO, new InternetAddress( mailTo ) );
        msg.setFrom( new InternetAddress( FROM ) );
        msg.setSubject( subject );

        msg.setText( message );

        Transport.send( msg );

    }

}
