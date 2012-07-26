package br.com.caelum.aeris.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.NotNull;

@Entity
@Table(name = "passagem")
@NamedQueries(value = { @NamedQuery(name = "passagens", query = "select p from Passagem p") })
public class Passagem implements Serializable {

	private static final long serialVersionUID = -6718896140264232383L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,	generator = "passagem_sequence_generator")
	@SequenceGenerator(name = "passagem_sequence_generator", sequenceName = "passagem_sequence", initialValue = 1, allocationSize = 3)
	@NotNull
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Voo.class)
	@NotNull
	private Voo voo;

	@NotNull
	private String cliente;

	public Passagem(final Long id, final Voo voo, final String cliente) {
		super();
		this.id = id;
		this.voo = voo;
		this.cliente = cliente;
	}

	public Passagem(final Voo voo, final String cliente) {
		super();
		this.voo = voo;
		this.cliente = cliente;
	}

	public Passagem(final Long id) {
		super();
		this.id = id;
	}

	public Passagem() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Voo getVoo() {
		return voo;
	}

	public void setVoo(final Voo voo) {
		this.voo = voo;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(final String cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Passagem other = (Passagem) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Passagem [id=" + id + ", voo=" + voo + ", cliente=" + cliente
				+ "]";
	}

}
