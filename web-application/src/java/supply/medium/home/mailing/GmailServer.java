/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supply.medium.home.mailing;

import java.io.File;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import supply.medium.utility.ErrorMaster;

/**
 *
 * @author Intel8GB
 */
public class GmailServer {

    static final String host = "smtp.gmail.com";
    static final String from = "erp4drone@gmail.com";
    static final String pass = "rbdc9rbdc9";

    public static void main(HttpServletRequest request) {
        String msg = "Test Message";
        String path = request.getRealPath("") + File.separator + "cropData";
        String file = "mugshot.png";
        HTMLMailComposer obj = new HTMLMailComposer();
        msg = obj.composeInvitationMail("Company Name", "Sender Name");
//        normalMail(msg);
        attachmentMail(msg, path, file);
    }

    public static void normalMail(String msg) {

        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true"); // added this line
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        String to = "kakkar.lokesh@gmail.com"; // added this line
        try {
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from, "SupplyMedium No Reply"));

            InternetAddress toAddress = new InternetAddress(to);

            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress("luckycreative1989@gmail.com"));
            message.setContent( msg, "text/html" );
            message.setSubject("Test Subject");
            message.setSentDate( new Date( ) );
//            message.setContent(msg, "text/html");
//            message.setHeader("Content-Type", "text/html; charset=\"us-ascii\"; ");
//            message.setText(msg);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    public static void attachmentMail(String msg, String path, String file) {

        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true"); // added this line
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        String to = "kakkar.lokesh@gmail.com"; // added this line
        try {
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from, "SupplyMedium No Reply"));

            InternetAddress toAddress = new InternetAddress(to);

            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress("luckycreative1989@gmail.com"));
//            message.setSubject("Test Subject");
//            message.setText(msg);
            Multipart multipart = null;

            try {
                // 3) create MimeBodyPart object and set your message content
                BodyPart messageBodyPart1 = new MimeBodyPart();

                messageBodyPart1.setText(msg);

                messageBodyPart1.setContent(msg, "text/html");

                if (!(path.equals("") && file.equals(""))) {

                    // 4) create new MimeBodyPart object and set DataHandler object
                    // to this object
                    MimeBodyPart messageBodyPart2 = new MimeBodyPart();

                    String filename = path + File.separator + file;// change accordingly

                    DataSource source = new FileDataSource(filename);

                    messageBodyPart2.setDataHandler(new DataHandler(source));

                    messageBodyPart2.setFileName(file);

                    messageBodyPart2.attachFile(new File(filename));

                    messageBodyPart2.setHeader("Content-Type", "text/plain; charset=\"us-ascii\"; name=\"" + file + "\"");
                    // 5) create Multipart object and add MimeBodyPart objects to this object
                    multipart = new MimeMultipart();

                    multipart.addBodyPart(messageBodyPart1);


                    multipart.addBodyPart(messageBodyPart2);

                }
            } catch (MessagingException ex) {
                ErrorMaster.insert("Exception MimeMultipart - MessagingException " + ex.getMessage());
            } catch (Exception ex) {
                ErrorMaster.insert("Exception MimeMultipart - Exception " + ex.getMessage());
            }

            try {
                // 6) set the multiplart object to the message object
                message.setContent(multipart);
            } catch (MessagingException ex) {
                ErrorMaster.insert("Exception message setContent - MessagingException " + ex.getMessage());
            } catch (Exception ex) {
                ErrorMaster.insert("Exception message setContent - Exception " + ex.getMessage());
            }

            Transport transport = null;

            try {
                transport = session.getTransport("smtp");

                transport.connect(host, from, pass);
            } catch (AuthenticationFailedException ex) {
                ErrorMaster.insert("Exception Transport connect - AuthenticationFailedException " + ex.getMessage());
            } catch (MessagingException ex) {
                ErrorMaster.insert("Exception Transport connect- MessagingException " + ex.getMessage());
            } catch (IllegalStateException ex) {
                ErrorMaster.insert("Exception Transport connect- IllegalStateException " + ex.getMessage());
            } catch (Exception ex) {
                ErrorMaster.insert("Exception Transport connect - Exception " + ex.getMessage());
            }

            try {
                transport.sendMessage(message, message.getAllRecipients());
            } catch (SendFailedException ex) {
                ErrorMaster.insert("Exception Transport sendMessage - SendFailedException " + ex.getMessage());

                ex.printStackTrace();
            } catch (MessagingException ex) {
                ErrorMaster.insert("Exception Transport sendMessage- MessagingException " + ex.getMessage());

                ex.printStackTrace();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception Transport sendMessage - Exception " + ex.getMessage());
            }

            try {
                transport.close();
            } catch (MessagingException ex) {
                ErrorMaster.insert("Exception Transport close- MessagingException " + ex.getMessage());
            } catch (Exception ex) {
                ErrorMaster.insert("Exception Transport close - Exception " + ex.getMessage());
            }

//            Transport transports = session.getTransport("smtp");
//            transports.connect(host, from, pass);
//            transports.sendMessage(message, message.getAllRecipients());
//            transports.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
