package br.com.buyFast.service;

import org.springframework.mail.MailException;


/**
 * Classe responsável pelo envio de e-mail.
 */
public interface EmailService {
	
	/**
	 * Envia o e-mail.
	 * @param to - Para onde será enviado o E-mail.
	 * @param from - Quem está enviando E-mail (Caso não seja autenticado).
	 * @param subject - Assunto do E-mail.
	 * @param text - Corpo do E-mail.
	 * @return <code>True</code> no sucesso do envio do e-mail. <code>False</code> caso contrário.
	 * @throws ServiceException
	 */
	public boolean send(final String to, final String from, final String subject, final String text) 
			throws ServiceException;

}
