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
import supply.medium.utility.ErrorMaster;

/**
 *
 * @author Intel8GB
 */
public class EmailApi {

    /* webmail code supplymedium */
//    static final String host = SmProperties.emailHost;
//    static final String from = SmProperties.emailFrom;
//    static final String pass = SmProperties.emailPassword;

    /* gmail code drone */
    static final String host = "smtp.gmail.com";
    static final String from = "erp4drone@gmail.com";
    static final String pass = "rbdc9rbdc9";

    /* gmail code supplumedium */
//    static final String from = "supplymedium@gmail.com";
//    static final String pass = "SupplyMedium!";

    public static void normalMail(String[] to, String subject, String msg, String fromMessage) {

        Properties props = System.getProperties();
        /* webmail code supplymedium */
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", 25);
//        props.put("mail.smtp.auth", "true");

        /* gmail code */
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.user", from);
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");


        try {
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from, fromMessage));

            InternetAddress toAddress = new InternetAddress(to[0]);

            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress("supplymedium@a2bbrain.com"));
            message.setContent(msg, "text/html");
            message.setSubject(subject);
            message.setSentDate(new Date());
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void attachmentMail(String[] to, String subject, String msg, String path, String file) {

        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        try {
            Session session = Session.getDefaultInstance(props, null);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from, "SM Transaction"));

            InternetAddress toAddress = new InternetAddress(to[0]);

            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.addRecipient(Message.RecipientType.BCC, new InternetAddress("supplymedium@a2bbrain.com"));
            message.setSubject(subject);
            Multipart multipart = null;

            try {
                BodyPart messageBodyPart1 = new MimeBodyPart();

                messageBodyPart1.setText(msg);

                messageBodyPart1.setContent(msg, "text/html");

                if (!(path.equals("") && file.equals(""))) {

                    MimeBodyPart messageBodyPart2 = new MimeBodyPart();

                    String filename = path + File.separator + file;// change accordingly

                    DataSource source = new FileDataSource(filename);

                    messageBodyPart2.setDataHandler(new DataHandler(source));

                    messageBodyPart2.setFileName(file);

                    messageBodyPart2.attachFile(new File(filename));

                    messageBodyPart2.setHeader("Content-Type", "text/plain; charset=\"us-ascii\"; name=\"" + file + "\"");

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

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
