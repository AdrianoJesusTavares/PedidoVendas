package gyn.jesus.email;
import java.io.IOException;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.outjected.email.api.SessionConfig;
import com.outjected.email.impl.SimpleMailConfig;

public class MailConfigProducer {

	@Produces
	@ApplicationScoped
	public SessionConfig getMailConfig() {
		Properties props = new Properties();
		SimpleMailConfig config = new SimpleMailConfig();
		try {
			props.load(getClass().getResourceAsStream("/mail.properties"));
		
		config.setServerHost(props.getProperty("mail.server.host"));
		config.setServerPort(Integer.parseInt(props.getProperty("mail.server.port")));
		config.setEnableSsl(Boolean.parseBoolean(props.getProperty("mail.enable.ssl")));
		config.setAuth(Boolean.parseBoolean(props.getProperty("mail.auth")));
		config.setUsername(props.getProperty("mail.username"));
		config.setPassword(props.getProperty("mail.password"));
		
		
		} catch (IOException e) {
		
			e.printStackTrace();
			System.out.println("erro da msg" + e.getMessage());
		}
		return config;
		
	}
	
}