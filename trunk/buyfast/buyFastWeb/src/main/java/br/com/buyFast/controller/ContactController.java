package br.com.buyFast.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.buyFast.service.EmailService;
import br.com.buyFast.service.ServiceException;
import br.com.buyFast.util.FacesUtil;

/**
 * Classe que representa o controller para a página de contatos.
 */
@Controller("contactController")
@Scope("session")
public class ContactController implements Serializable {

	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Apresenta mensagens no log da aplicação.
	 */
	private static final Log logger = LogFactory.getLog(ContactController.class);
	
	/**
	 * Responsável pelo serviço de e-mail.
	 */
	@Resource
	private EmailService emailService;
	
	/**
	 * Representa o nome de quem está enviando a mensagem.
	 */
	private String name;
	
	/**
	 * Representa o e-mail do contato.
	 */
	private String email;
	
	/**
	 * Representa o assunto do contato.
	 */
	private String subject;
	
	/**
	 * Representa a mensagem do contato.
	 */
	private String message;

	/**
	 * Construtor padrão.
	 */
	public ContactController() {
		super();
	}
	
	/**
	 * Envia a mensagem do contato.
	 * @return
	 */
	public String sendContact() {
		try {
			logger.info("Enviando e-mail para " + email);
			
			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
			
			
			StringBuilder builder = new StringBuilder();
			
			builder.append("<h2>Contato enviado pelo site BuyFast</h2><br />");
			builder.append("<b>Hora do envio:</b> " + df.format(new Date()) + "<br />");
			builder.append("<b>Nome:</b> " + name + "<br />");
			builder.append("<b>E-mail:</b> " + email + "<br />");
			builder.append("<b>Assunto:</b> " + subject + "<br />");
			builder.append("<b>Mensagem:</b> " + message);
			
			emailService.send("danielso2007@gmail.com", email, subject, builder.toString());
		
		} catch (ServiceException e) {
			logger.error("Erro ao enviar e-mail.", e);
			FacesUtil.mensErro("", FacesUtil.getMessage("contactControllerErroSendMail"));
			return null;
		}
		
		name = "";
		email = "";
		subject = "";
		message = "";
		FacesUtil.mensInfo("", FacesUtil.getMessage("contactControllerSuccessSendMail"));
		
		return null;
	}
	
	/* Getters and Setters */
	
	/**
	 * Obter o nome do contato.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Ajustar o nome do contato.
	 * @param name O nome do contato.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obter o e-mail do contato.
	 * @return O e-mail do contato.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Ajustar o e-mail do contato.
	 * @param email O e-mail do contato.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obter o assunto do contato.
	 * @return O assunto do contato.
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Ajustar o assunto do contato.
	 * @param subject O assunto do contato.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Obter a mensagem do contato.
	 * @return A mensagem do contato.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Ajustar a mensagem do contato.
	 * @param message A mensagem do contato.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Ajustar o serviço de e-mail.
	 * @param emailService O serviço de e-mail.
	 */
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

}
