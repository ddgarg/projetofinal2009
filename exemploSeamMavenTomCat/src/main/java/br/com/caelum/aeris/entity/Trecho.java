package br.com.caelum.aeris.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.NotNull;

import br.com.caelum.aeris.entity.enumerator.Localidade;

@Entity
@Table(name = "trecho")
@NamedQueries(value = { @NamedQuery(name = "trechos", query = "SELECT t from Trecho t") })
public class Trecho implements Serializable {

	private static final long serialVersionUID = -7389468436897921917L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trecho_sequence_generator")
	@SequenceGenerator(name = "trecho_sequence_generator", sequenceName = "trecho_sequence", initialValue = 1, allocationSize = 3)  
	@NotNull
	private Long id;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "{notNull.localidate.origem.msg}")
	private Localidade origem;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "{notNull.localidade.destino.msg}")
	private Localidade destino;

	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "trecho")
	private List<Voo> voos = new ArrayList<Voo>();

	public Trecho() {
		super();
	}

	public Trecho(final Long id, final Localidade origem,
			final Localidade destino, final List<Voo> voos) {
		super();
		this.id = id;
		this.origem = origem;
		this.destino = destino;
		this.voos = voos;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Localidade getOrigem() {
		return origem;
	}

	public void setOrigem(final Localidade origem) {
		this.origem = origem;
	}

	public Localidade getDestino() {
		return destino;
	}

	public void setDestino(final Localidade destino) {
		this.destino = destino;
	}

	public List<Voo> getVoos() {
		return voos;
	}

	public void setVoos(final List<Voo> voos) {
		this.voos = voos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((origem == null) ? 0 : origem.hashCode());
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
		Trecho other = (Trecho) obj;
		if (destino != other.destino) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (origem != other.origem) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Trecho [id=" + id + ", origem=" + origem + ", destino="
				+ destino + "]";
	}

}
