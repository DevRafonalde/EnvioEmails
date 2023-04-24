package br.com.dev1risjc.envioEmail;

/**
 *
 * @author rafael.albuquerque
 */
public class Main {
    public static void main(String[] args) {
        if (args.length != 6) {
            System.out.println("Quantidade de parâmetros inválida\n Exemplo: String host, String usuario, String senha, String destinatario, String assunto, String mensagem");
        }
        String host = args[0];
        String usuario = args[1];
        String senha = args[2];
        String destinatario = args[3];
        String assunto = args[4];
        String mensagem = args[5];
        
        EnvioEmails envio = new EnvioEmails(host, usuario, senha, destinatario, assunto, mensagem);
        envio.enviarEmail();
    }
}
