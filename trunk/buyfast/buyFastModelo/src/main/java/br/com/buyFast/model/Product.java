package br.com.buyFast.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Classe que representa o produto.
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product implements Serializable {
 
	/**
	 * {@link Serializable}.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * O identificador do produto.
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	 
	/**
	 * O nome do produto.
	 */
	private String name;
	 
	/**
	 * O preço do produto.
	 */
	private double price;
	 
	/**
	 * O desconto do produto.
	 */
	private Integer discount;
	 
	/**
	 * A quantidade do estoque.
	 */
	private int quantityStock;
	 
	/**
	 * Representa a pequena descrição.
	 */
	private String smallDescription;
	 
	/**
	 * Representa a descrição do produto.
	 */
	@Lob
	private String description;
	 
	/**
	 * O caminho da imagem do produto.
	 */
	private String image;
	
	/**
	 * O caminho da imagem de apresentação do produto.
	 */
	private String smallImage;
	
	/**
	 * O conjunto de ítens de pedido que contém este produto.
	 */
	@OneToMany(mappedBy="product", fetch=FetchType.EAGER, targetEntity=ItemsOrder.class, cascade=CascadeType.ALL)
	private Set<ItemsOrder> itemsOrder;
	
	/**
	 * A categoria ao qual pertence este produto.
	 */
	@ManyToOne(fetch=FetchType.EAGER, targetEntity=Category.class)
	private Category category;

	/**
	 * O identificador do produto.
	 * @return O identificador do produto.
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Ajustar o identificador do produto.
	 * @param id - O novo identificador do produto.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * O nome do produto.
	 * @return O nome do produto.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Ajustar o nome do produto.
	 * @param name - O novo nome do produto.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obter o preço do produto.
	 * @return O preço do produto.
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Ajustar o preço do produto.
	 * @param price - O novo preço do produto.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Obter o desconto do produto.
	 * @return O desconto do produto.
	 */
	public Integer getDiscount() {
		return discount;
	}

	/**
	 * Ajustar o desconto do produto.
	 * @param discount - O novo desconto do produto.
	 */
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	/**
	 * Obter a quantidade de estoque de produto.
	 * @return A quantidade de estoque de produto.
	 */
	public int getQuantityStock() {
		return quantityStock;
	}

	/**
	 * Ajustar a quantidade de estoque de produto.
	 * @param quantityStock - A nova quantidade de estoque de produto.
	 */
	public void setQuantityStock(int quantityStock) {
		this.quantityStock = quantityStock;
	}

	/**
	 * Obter a descrição.
	 * @return A descrição.
	 */
	public String getSmallDescription() {
		return smallDescription;
	}

	/**
	 * Ajustar a descrição.
	 * @param smallDescription - A nova descrição.
	 */
	public void setSmallDescription(String smallDescription) {
		this.smallDescription = smallDescription;
	}

	/**
	 * Obter a descrição do produto.
	 * @return A descrição do produto.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Ajustar a descrição do produto.
	 * @param description - A nova descrição do produto.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Obter o caminho da imagem do produto.
	 * @return O caminha da imagem do produto.
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Ajustar o caminho da imagem do produto.
	 * @param image - O novo caminho da imagem do produto.
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Obter o caminho da imagem de apresentação do produto.
	 * @return O caminho da imagem de apresentação do Produto.
	 */
	public String getSmallImage() {
		return smallImage;
	}

	/**
	 * Ajustar o caminho da imagem de apresentação do Produto.
	 * @param smallImage - O caminho da imagem de apresentação do Produto.
	 */
	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}

	/**
	 * Obter o conjunto de ítens de pedido ao qual este produto está.
	 * @return O conjunto de ítens de pedido.
	 */
	public Set<ItemsOrder> getItemsOrder() {
		return itemsOrder;
	}

	/**
	 * Ajustar o conjunto de ítens de pedido.
	 * @param itemsOrder - O novo conjunto de ítens de pedido.
	 */
	public void setItemsOrder(Set<ItemsOrder> itemsOrder) {
		this.itemsOrder = itemsOrder;
	}

	/**
	 * Obter a categoria do produto.
	 * @return A categoria do produto.
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Ajustar a categoria do produto.
	 * @param category - A nova categoria do produto.
	 */
	public void setCategory(Category category) {
		this.category = category;
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
		Product other = (Product) obj;
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
 
