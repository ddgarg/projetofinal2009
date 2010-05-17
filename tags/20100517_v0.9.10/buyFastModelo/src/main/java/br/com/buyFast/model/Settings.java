package br.com.buyFast.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Classe que representa a configuração do sistema.
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Settings implements Serializable {
 
	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * O identificador.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	/**
	 * O e-mail do sistema.
	 */
	@Column(nullable=true, length=100)
	private String emailSystem;
	
	/**
	 * O e-mail do contato.
	 */
	@Column(nullable=true, length=100)
	private String emailContact;
	
	/**
	 * O texto do e-mail de contato.
	 */
	@Lob
	private String messageContact;
	
	/**
	 * O host.
	 * Exemplo: <i>smtp.gmail.com</i>.
	 */
	@Column(nullable=true, length=50)
	private String host;
	
	/**
	 * O número da porta.
	 */
	@Column(nullable=true, length=10)
	private String port;
	 
	/**
	 * O protocolo. Exemplo: <i>smtp</i>.
	 */
	@Column(nullable=true, length=10)
	private String protocol;
	
	/**
	 * O userName do e-mail de contato.
	 */
	@Column(nullable=true, length=100)
	private String userName_EmailContact;
	
	/**
	 * O password do e-mail de contato.
	 */
	@Column(nullable=true, length=100)
	private String password_EmailContact;
	
	/**
	 * Indica se é um e-mail autenticado.
	 */
	@Column(nullable=false, length=1)
	private boolean authentic;
	
	/**
	 * Indica se utiliza conexão segura.
	 */
	@Column(nullable=false, length=1)
	private boolean secureConnection;
	
	/**
	 * Número de produtos por página.
	 */
	@Column(nullable=true, length=10)
	private int numberPage;
	
	/**
	 * UserName do e-mail do sistema.
	 */
	@Column(nullable=true, length=100)
	private String userName_EmailSystem;
	
	/**
	 * Password do e-mail do sistema.
	 */
	@Column(nullable=true, length=100)
	private String password_EmailSystem;

	/**
	 * Obter o identificador.
	 * @return O identificador.
	 */
	public long getId() {
		return id;
	}

	/**
	 * Ajustar o identificador.
	 * @param id - O novo identificador.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Obter o e-mail do sistema.
	 * @return O e-amil do sistema.
	 */
	public String getEmailSystem() {
		return emailSystem;
	}

	/**
	 * Ajusta o e-mail do sistema.
	 * @param emailSystem - O novo e-mail do sistema.
	 */
	public void setEmailSystem(String emailSystem) {
		this.emailSystem = emailSystem;
	}

	/**
	 * Obter o e-mail de contato.
	 * @return O e-mail de contato.
	 */
	public String getEmailContact() {
		return emailContact;
	}

	/**
	 * Ajustar o e-mail de contato.
	 * @param emailContact - O novo e-mail de contato.
	 */
	public void setEmailContact(String emailContact) {
		this.emailContact = emailContact;
	}

	/**
	 * Obter a mensagem do contato.
	 * @return A mensagem do contato.
	 */
	public String getMessageContact() {
		return messageContact;
	}

	/**
	 * Ajustar a mensagem do contato.
	 * @param messageContact - A nova mensagem do contato.
	 */
	public void setMessageContact(String messageContact) {
		this.messageContact = messageContact;
	}

	/**
	 * Obter o host para o envio de e-mail.
	 * @return O host para o envio de e-mail.
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Ajustar o host para o envio de e-mail.
	 * @param host - O novo host.
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Obter a porta de configuração do e-mail.
	 * @return A porta de configuração do e-mail.
	 */
	public String getPort() {
		return port;
	}

	/**
	 * Ajustar a porta de configuração do e-mail.
	 * @param port - A nova porta de configuração do e-mail.
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * Obter o protocolo de envio de e-mail.
	 * @return O protocolo de envio de e-mail.
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * Ajustar o protocolo de envio de e-mail.
	 * @param protocol - novo protocolo.
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * Obter o userName do e-mail de contato.
	 * @return O userName do e-mail de contato.
	 */
	public String getUserName_EmailContact() {
		return userName_EmailContact;
	}

	/**
	 * Ajustar o userName do e-mail de contato.
	 * @param userName_EmailContact
	 */
	public void setUserName_EmailContact(String userName_EmailContact) {
		this.userName_EmailContact = userName_EmailContact;
	}

	/**
	 * Obter o password do e-mail de contato.
	 * @return O password do e-mail de contato.
	 */
	public String getPassword_EmailContact() {
		return password_EmailContact;
	}

	/**
	 * Ajustar o password do e-mail de contato.
	 * @param password_EmailContact - O novo password.
	 */
	public void setPassword_EmailContact(String password_EmailContact) {
		this.password_EmailContact = password_EmailContact;
	}

	/**
	 * Informa se é autenticado.
	 * @return <code>true</code> para e-mail autenticado. <code>False</code> caso contrário.
	 */
	public boolean isAuthentic() {
		return authentic;
	}

	/**
	 * Ajusta a infromação de autenticação de e-mail.
	 * @param authentic - <code>True</code> para e-mail autenticado. <code>False</code> caso contrário.
	 */
	public void setAuthentic(boolean authentic) {
		this.authentic = authentic;
	}

	/**
	 * Informa se é com conexão segura.
	 * @return <code>true</code> para e-mail com conexão segura. <code>False</code> caso contrário.
	 */
	public boolean isSecureConnection() {
		return secureConnection;
	}

	/**
	 * Ajusta a informação de conexão segura.
	 * @param secureConnection - <code>true</code> para e-mail com conexão segura. <code>False</code> caso contrário.
	 */
	public void setSecureConnection(boolean secureConnection) {
		this.secureConnection = secureConnection;
	}

	/**
	 * Obter o número de produtos por página.
	 * @return O número de produtos por página.
	 */
	public int getNumberPage() {
		return numberPage;
	}

	/**
	 * Ajusta o número de produtos por página.
	 * @param numberPage - O número de produtos por página.
	 */
	public void setNumberPage(int numberPage) {
		this.numberPage = numberPage;
	}

	/**
	 * Obter o e-mail do sistema.
	 * @return O e-mail do sistema.
	 */
	public String getUserName_EmailSystem() {
		return userName_EmailSystem;
	}

	/**
	 * Ajustar o e-mail do sistema.
	 * @param userName_EmailSystem - O e-mail do sistema.
	 */
	public void setUserName_EmailSystem(String userName_EmailSystem) {
		this.userName_EmailSystem = userName_EmailSystem;
	}

	/**
	 * Obter o password do e-mail do sistema.
	 * @return O password do e-mail do sistema.
	 */
	public String getPassword_EmailSystem() {
		return password_EmailSystem;
	}

	/**
	 * Ajusta o password do e-mail do sistema.
	 * @param password_EmailSystem - O password do e-mail do sistema.
	 */
	public void setPassword_EmailSystem(String password_EmailSystem) {
		this.password_EmailSystem = password_EmailSystem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Settings other = (Settings) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Settings.class.getName() + " - " + id;
	}
	
}
 
