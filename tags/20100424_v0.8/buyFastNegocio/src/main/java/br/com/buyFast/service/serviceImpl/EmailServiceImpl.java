package br.com.buyFast.service.serviceImpl;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import br.com.buyFast.service.EmailService;
import br.com.buyFast.service.ServiceException;

/**
 * Implementa a classe responsável pelo envio de e-mail.
 */
public class EmailServiceImpl implements EmailService {

	/**
	 * Responsável pela apresentação do log na aplicação.
	 */
	private static final Log logger = LogFactory.getLog(EmailServiceImpl.class);
	
	/**
	 * @see JavaMailSender
	 */
	private JavaMailSender enviarEmail;
	
	@Override
	public boolean send(final String to, final String from, final String subject, final String text) 
			throws ServiceException {
		
		logger.info("Preparando envio de e-mail...");
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper MMhelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				MMhelper.setTo(to);
				MMhelper.setFrom(from);
				MMhelper.setSubject(subject);
				MMhelper.setText(text, true);
			}
		};
		
		try {
			logger.info("Enviando e-mail...");
			this.enviarEmail.send(preparator);
		} catch (MailException ex) {
			String error = "Erro ao enviar e-mail.";
			logger.error(error, ex);
			throw new ServiceException(error, ex);
		}
		
		logger.info("E-mail enviado com sucesso.");
		return true;
	}

	/**
	 * Obter JavaMailSender.
	 * @return {@link JavaMailSender}
	 */
	public JavaMailSender getEnviarEmail() {
		return enviarEmail;
	}

	/**
	 * Ajustar JavaMailSender.
	 * @param enviarEmail {@link JavaMailSender}
	 */
	public void setEnviarEmail(JavaMailSender enviarEmail) {
		this.enviarEmail = enviarEmail;
	}

}
