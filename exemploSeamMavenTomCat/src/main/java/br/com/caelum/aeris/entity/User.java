package br.com.caelum.aeris.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

@Entity
@Table(name = "usuario")
@NamedQueries(value = {
		@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.findByLogin", query = "SELECT u FROM User u WHERE u.login = :login"),
		@NamedQuery(name = "User.login", query = "SELECT u FROM User u WHERE u.login = :login AND u.senha = :senha")})
public class User implements Serializable {

	private static final long serialVersionUID = 3643452685183146994L;

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "user_sequence_generator")
	@SequenceGenerator(
			name = "user_sequence_generator",
			sequenceName = "user_sequence",
			initialValue = 1,
			allocationSize = 4)
	@NotNull
	private Long id;

	@NotNull(message = "{notNull.login.user.msg}")
	@NotEmpty(message = "{notEmpty.login.user.msg}")
	@Length(min = 8, max = 15, message = "{length.login.user.msg}")
	private String login;

	@NotNull(message = "{notNull.senha.user.msg}")
	@NotEmpty(message = "{notEmpty.senha.user.msg}")
	@Length(min = 3, max = 15, message = "{length.senha.user.msg}")
	private String senha;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Role> roles = new ArrayList<Role>();

	public User(final String login, final String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(final String senha) {
		this.senha = senha;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(final List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		if (id == null) {
			if (other.getId() != null) {
				return false;
			}
		} else if (!id.equals(other.getId())) {
			return false;
		}
		if (login == null) {
			if (other.getLogin() != null) {
				return false;
			}
		} else if (!login.equals(other.getLogin())) {
			return false;
		}
		if (senha == null) {
			if (other.getSenha() != null) {
				return false;
			}
		} else if (!senha.equals(other.getSenha())) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", senha=" + senha + "]";
	}

}
