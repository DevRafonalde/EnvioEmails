package br.com.dev1risjc.envioEmail;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author rafael.albuquerque
 */
public class EnvioEmails implements IEnviaEmail{

    /**
     * @param args the command line arguments
     */
    String host;
    String usuario;
    String senha;
    String destinatario;
    Address[] destinatarios;
    
    String assunto;
    String mensagem;

    public EnvioEmails(String host, String usuario, String senha, String destinatario, String assunto, String mensagem) {
        this.host = host;
        this.usuario = usuario;
        this.senha = senha;
        this.destinatario = destinatario;
        this.assunto = assunto;
        this.mensagem = mensagem;
    }

//    public EnvioEmails(String host, String usuario, String senha, Address[] destinatarios, String assunto, String mensagem) {
//        this.host = host;
//        this.usuario = usuario;
//        this.senha = senha;
//        this.destinatarios = destinatarios;
//        this.assunto = assunto;
//        this.mensagem = mensagem;
//    }

    @Override
    public void enviarEmail() {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", true);

        Session sessao = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, senha);
            }
        });

        try {
            MimeMessage email = new MimeMessage(sessao);
            email.setFrom(new InternetAddress(usuario));
            if (!"".equals(destinatario) || destinatario == null) {
                email.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            } else {
                email.addRecipients(Message.RecipientType.TO, destinatarios);
            }
            email.setSubject(assunto);
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(mensagem, "text/html; charset=utf-8");
            
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            email.setContent(multipart);

            Transport.send(email);
            System.out.println("foi");
        } catch (MessagingException e) {
            Logger.getLogger(EnvioEmails.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
