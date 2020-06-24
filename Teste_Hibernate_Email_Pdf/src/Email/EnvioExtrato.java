package Email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import com.itextpdf.text.Document;

public class EnvioExtrato {
	
	public boolean sendExtratoByEmail(String emailCliente,String caminhoExtrato) {
		Boolean enviou = false;
		
		String meuEmail = "emanoelbamorin@gmail.com";
		String minhaSenha = "Emanoel01";
		
		MultiPartEmail email = new MultiPartEmail();;
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator(meuEmail, minhaSenha));
		email.setSSLOnConnect(true);
		
		try {
			email.setFrom(meuEmail);
			email.setSubject("Extrato");
			email.setMsg("Segue extrato referente a quinzena");
			email.addTo(emailCliente);
			
			
			EmailAttachment anexo = new EmailAttachment();
			anexo.setPath(caminhoExtrato);
			//anexo.setName("extrato_tal.pdf");
			email.attach(anexo);
			
			email.send();
			enviou = true;
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
			
		
		
		return enviou;
	}

}
