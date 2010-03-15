package br.com.buyFast.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Classe que representa a categoria do produto.
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category implements Serializable {
 
	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * O identificado da categoria.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * O nome da categoria.
	 */
	@Column(nullable=false, length=100)
	private String name;

	/**
	 * Os produtos que pertencem a essa categoria.
	 */
	@OneToMany(mappedBy="category", fetch=FetchType.EAGER, targetEntity=Product.class, cascade=CascadeType.ALL)
	private Set<Product> product;
	
	/**
	 * Obter o identificador da categoria.
	 * @return o identificador da categoria.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Ajustar o identificador da categoria.
	 * @param id - O novo identificador da categoria.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Obter o nome da categoria.
	 * @return O nome da categoria.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Ajustar o nome da categoria.
	 * @param name - O novo nome da categoria.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obter os produtos desta categoria.
	 * @return O conjunto de produtos desta categoria.
	 */
	public Set<Product> getProduct() {
		return product;
	}

	/**
	 * Ajusta o conjunto de produtos desta categoria.
	 * @param product - O novo conjunto de produtos desta categoria.
	 */
	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Category other = (Category) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
}
 
