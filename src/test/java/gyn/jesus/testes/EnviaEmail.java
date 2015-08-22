package gyn.jesus.testes;

import java.io.IOException;
import java.util.Properties;







import com.outjected.email.api.MailMessage;
import com.outjected.email.api.SessionConfig;
import com.outjected.email.impl.MailMessageImpl;
import com.outjected.email.impl.SimpleMailConfig;




public class EnviaEmail {
	
	
	public void enviarEmail(){
		
	MailMessage message = new MailMessageImpl(this.getMailConfig());
		
		message.to("adrianojesusgyn@gmail.com")
		.subject("teste de envio")
		.bodyHtml("enviado com sucesso")
		.send();
		
	}
	
	public SessionConfig getMailConfig() {
		Properties props = new Properties();
		SimpleMailConfig config = new SimpleMailConfig();
		try {
			props.load(getClass().getResourceAsStream("/mail.properties"));
		
		config.setServerHost(props.getProperty("mail.server.host"));
		config.setServerPort(Integer.parseInt(props.getProperty("mail.server.port")));
		config.setEnableSsl(Boolean.parseBoolean(props.getProperty("mail.enable.ssl")));
		config.setAuth(Boolean.parseBoolean(props.getProperty("mail.auth")));
		config.setUsername("aryjunioradm@gmail.com");
		config.setPassword("27374757");
		
		} catch (IOException e) {
		
			e.printStackTrace();
			System.out.println("erro da msg" + e.getMessage());
		}
		return config;
		
	}

	public static void main(String[] args) {
	 EnviaEmail env = new EnviaEmail();
	 env.enviarEmail();
	}

}
