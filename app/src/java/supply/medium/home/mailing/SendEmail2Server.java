/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supply.medium.home.mailing;

/**
 *
 * @author Intel8GB
 */
import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import supply.medium.utility.CreateErrorFile;

public class SendEmail2Server {

    public static void sendEmailAttachement(String to[],String sub,String msg, String path, String fileName) {

        // Sender's email ID needs to be mentioned
        String from = "noreply@supplymedium.in";
        final String username = "noreply";
        final String password = "QRasY/9@d[qDN-q8";//change accordingly
        String host = "mail.supplymedium.in";
        String port = "25";
            CreateErrorFile.create("Line no 30");



        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
            CreateErrorFile.create("Line no 39");
//      props.put("mail.smtp.auth", "true");
//      props.put("mail.smtp.starttls.enable", "true");
//      props.put("mail.smtp.host", host);
//      props.put("mail.smtp.port", "25");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
            CreateErrorFile.create("Line no 53");

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            InternetAddress[] toAdd = new InternetAddress[to.length];
                    for (int i = 0; i < to.length; i++) {
                        toAdd[i] = new InternetAddress(to[i]);
                    }
            CreateErrorFile.create("Line no 66");

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO, toAdd);

            // Set Subject: header field
            message.setSubject(sub);

            // Create the message part
            //BodyPart messageBodyPart = new MimeBodyPart();
            MimeBodyPart messageBodyParts = new MimeBodyPart();
            messageBodyParts.setContent(msg, "text/html");
            CreateErrorFile.create("Line no 78");

            // Now set the actual message
            //messageBodyPart.setText("This is message body");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyParts);
            CreateErrorFile.create("Line no 88");

            // Part two is attachment
            messageBodyParts = new MimeBodyPart();
            DataSource source = new FileDataSource(path+File.separator+fileName);
            messageBodyParts.setDataHandler(new DataHandler(source));
            messageBodyParts.setFileName(fileName);
            multipart.addBodyPart(messageBodyParts);

            // Send the complete message parts
            message.setContent(multipart);
            CreateErrorFile.create("Line no 99");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");
            CreateErrorFile.create("Line no 105");

        } catch (Exception e) {
//            CreateErrorFile.create("Line no 108 "+e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void sendEmailNormal(String to[],String sub,String msg) {

        // Sender's email ID needs to be mentioned
        String from = "noreply@supplymedium.in";
        final String username = "noreply";
        final String password = "QRasY/9@d[qDN-q8";//change accordingly
        String host = "mail.supplymedium.in";
        String port = "25";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
//      props.put("mail.smtp.auth", "true");
//      props.put("mail.smtp.starttls.enable", "true");
//      props.put("mail.smtp.host", host);
//      props.put("mail.smtp.port", "25");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            CreateErrorFile.create("Line no 144");

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            CreateErrorFile.create("Line no 148");

            InternetAddress[] toAdd = new InternetAddress[to.length];
                    for (int i = 0; i < to.length; i++) {
                        toAdd[i] = new InternetAddress(to[i]);
                    }
            CreateErrorFile.create("Line no 154");

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO, toAdd);
            CreateErrorFile.create("Line no 158");

            // Set Subject: header field
            message.setSubject(sub);
            CreateErrorFile.create("Line no 162");

            // Create the message part
            //BodyPart messageBodyPart = new MimeBodyPart();
            MimeBodyPart messageBodyParts = new MimeBodyPart();
            messageBodyParts.setContent(msg, "text/html");
            CreateErrorFile.create("Line no 168");

            // Now set the actual message
            //messageBodyPart.setText("This is message body");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();
            CreateErrorFile.create("Line no 175");

            // Set text message part
            multipart.addBodyPart(messageBodyParts);
            CreateErrorFile.create("Line no 179");

            // Send the complete message parts
            message.setContent(multipart);
            CreateErrorFile.create("Line no 183");

            // Send message
            Transport.send(message);
            CreateErrorFile.create("Line no 187");

            System.out.println("Sent message successfully....");
            CreateErrorFile.create("Line no 190");

        } catch (Exception e) {
//            CreateErrorFile.create("Line no 193 "+e.getMessage());
            throw new RuntimeException(e);
        }
    }

//    public static  void main(String args[]){
//
//        String to[]={"mini14jan@gmail.com","webkrit@gmail.com"};
//        String sub="Test Email Subject";
//        String msg="Test <b style='color:red;'>Email</b> Message";
//        String path="C:/Users/Intel8GB/Desktop/";
//        String fileName="supply-medium.txt";
//
//        sendEmailNormal(to,sub,msg);
//        sendEmailAttachement(to, sub, msg, path, fileName);
//
//    }
}
