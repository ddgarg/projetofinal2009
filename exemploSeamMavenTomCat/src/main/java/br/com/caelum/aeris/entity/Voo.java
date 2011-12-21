package br.com.caelum.aeris.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.NotNull;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Entity
@Table(name = "voo")
@Name("voo")
@Scope(ScopeType.EVENT)
public class Voo implements Serializable {

	private static final long serialVersionUID = 8721687440155064708L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voo_sequence_generator")
	@SequenceGenerator(name = "voo_sequence_generator", sequenceName = "voo_sequence", initialValue = 1, allocationSize = 4) 
	@NotNull
	private Long id;

	private String codigo;

	@Temporal(TemporalType.DATE)
	@NotNull(message="Informar data")
	private Date dataPartida;

	@Temporal(TemporalType.TIME)
	@NotNull(message="Informar data")
	private Date horaPartida;

	@Temporal(TemporalType.DATE)
	@NotNull(message="Informar data")
	private Date dataChegada;

	@Temporal(TemporalType.TIME)
	@NotNull(message="Informar data")
	private Date horaChegada;

	@ManyToOne(
			fetch = FetchType.LAZY,
			targetEntity = Trecho.class)
	@NotNull
	private Trecho trecho;

	public Voo() {
		super();
	}

	public Voo(final Long id) {
		super();
		this.id = id;
	}

	public Voo(final Long id, final String codigo, final Date dataPartida,
			final Date horaPartida, final Date dataChegada,
			final Date horaChegada, final Trecho trecho) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.dataPartida = dataPartida;
		this.horaPartida = horaPartida;
		this.dataChegada = dataChegada;
		this.horaChegada = horaChegada;
		this.trecho = trecho;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(final String codigo) {
		this.codigo = codigo;
	}

	public Date getDataPartida() {
		return dataPartida;
	}

	public void setDataPartida(final Date dataPartida) {
		this.dataPartida = dataPartida;
	}

	public Date getHoraPartida() {
		return horaPartida;
	}

	public void setHoraPartida(final Date horaPartida) {
		this.horaPartida = horaPartida;
	}

	public Date getDataChegada() {
		return dataChegada;
	}

	public void setDataChegada(final Date dataChegada) {
		this.dataChegada = dataChegada;
	}

	public Date getHoraChegada() {
		return horaChegada;
	}

	public void setHoraChegada(final Date horaChegada) {
		this.horaChegada = horaChegada;
	}

	public Trecho getTrecho() {
		return trecho;
	}

	public void setTrecho(final Trecho trecho) {
		this.trecho = trecho;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Voo other = (Voo) obj;
		if (codigo == null) {
			if (other.codigo != null) {
				return false;
			}
		} else if (!codigo.equals(other.codigo)) {
			return false;
		}
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
		return "Voo [id=" + id + ", codigo=" + codigo + ", dataPartida="
				+ dataPartida + ", dataChegada=" + dataChegada + ", trecho="
				+ trecho + "]";
	}

}
