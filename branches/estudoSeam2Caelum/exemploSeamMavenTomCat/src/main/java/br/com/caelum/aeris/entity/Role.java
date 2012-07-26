package br.com.caelum.aeris.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.NotNull;

@Entity
@Table(name = "role")
@NamedQueries(value = {
		@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r"),
		@NamedQuery(
				name = "User.findByType",
				query = "SELECT r FROM Role r WHERE r.type = :type") })
public class Role implements Serializable {

	private static final long serialVersionUID = 3406881123095128284L;

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "role_sequence_generator")
	@SequenceGenerator(
			name = "role_sequence_generator",
			sequenceName = "role_sequence",
			initialValue = 1,
			allocationSize = 4)
	@NotNull
	private Long id;

	@NotNull()
	@NotEmpty()
	@Column(name = "type", length = 50, nullable = false, unique = true)
	private String type;

	public Role() {
		super();
	}

	public Role(final String type) {
		super();
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!type.equals(other.type)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return type;
	}

}
