import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.tools.JavaFileManager;
import java.util.Properties;

public class Gmail {
    private String EmailAddress;
    private String Password;
    private Session session;

    public Gmail(String emailAddress, String password) {
        EmailAddress = emailAddress;
        Password = password;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


         session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailAddress, password);
                    }
                });




    }

    void sendMessage(String sms){
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EmailAddress));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("recepient@gmail.com"));// replace with recepient email address
            message.setSubject("ODDS SCRAPPER");
            message.setText("Dear User,"
                    + "\n\n"+ sms);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
