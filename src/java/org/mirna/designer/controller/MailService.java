/*
* The miRNA Design Tool is based on the UPL (Universal Probe Library) probes to design primer(s) for microRNA detection. The usergets the best result by two different Tm calculating methods.  The tool designs the miRNA specific sequence of the stem-loop RT primer as well. 
*
* copyright (C) 2009-2012 Astrid Research Ltd. 
* copyright (C) November, 2012 University of Debrecen, Clinical Genomic Center, Medical and Health Science Center, Debrecen, Hungary
*
* The miRNA Design Tool is based on the UPL (Universal Probe Library) probes to design primer(s) for microRNA detection.  The usergets the best result by two different Tm calculating methods.  The tool designs the miRNA specific sequence of the stem-loop RT primer as well. 
*
*    miRNA Design Tool is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    miRNA Design Tool is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with miRNA Primer Design Tool.  If not, see <http://www.gnu.org/licenses/>.
*/
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
