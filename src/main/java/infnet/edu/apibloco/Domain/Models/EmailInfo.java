package infnet.edu.apibloco.Domain.Models;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Properties;

import infnet.edu.apibloco.Domain.Primitives.EntityRoot;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class EmailInfo extends EntityRoot {

    @Column(nullable = false)
    public String email;
    @Column(nullable = false)
    public String content;
    @Column
    public File attatchement;
    @Column(nullable = false)
    public LocalDateTime dateSended;
    @Column(nullable = false)
    public long userId;

    private static final String userName = "api";
    private static final String password = "5eb5b14b734b4e412d5903c6ba6a0afe";
    private static final String houseEmail = "mailtrap@javablocoemail.com";
    private static final String Email_Subject = "Api Bloco Java";
    private static final String Content_Type = "text/html; charset=utf-8";

    public EmailInfo(long id, String email, String content, File attatchement, LocalDateTime dateSended, long userId) {
        super(id);
        this.email = email;
        this.content = content;
        this.attatchement = attatchement;
        this.dateSended = dateSended;
        this.userId = userId;
    }

    public EmailInfo() {
    }

    public void SendEmailRequest(EmailInfo emailInfo) throws AddressException, MessagingException {
        
        Properties prop = GetEmailProperties();
       
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        MimeMessage message = CreateEmailMessage(session);
            
        Transport.send(message);
    }

    private MimeMessage CreateEmailMessage(Session session) throws MessagingException, AddressException {
        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(houseEmail));
        message.setRecipients(
          Message.RecipientType.TO, InternetAddress.parse(this.email));
        message.setSubject(Email_Subject);
            
        String msg = this.content;

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, Content_Type);
            
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
            
        message.setContent(multipart);
        return message;
    }



    private Properties GetEmailProperties() {
        Properties prop = new Properties();
                        prop.put("mail.smtp.auth", true);
                        prop.put("mail.smtp.starttls.enable", "true");
                        prop.put("mail.smtp.host", "live.smtp.mailtrap.io");
                        prop.put("mail.smtp.port", "587");
        return prop;
    }

    
}