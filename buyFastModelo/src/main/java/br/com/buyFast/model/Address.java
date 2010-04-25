package br.com.buyFast.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe que representa o endereço do cliente.
 */
@Entity
public class Address implements Serializable {

    /**
     * {@link Serializable}.
     */
    private static final long serialVersionUID = 1L;

    /**
     * O identificado do endereço.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Instancia um novo endereço.
     */
    public Address() {

    }

    /**
     * Instancia um novo endereço.
     * @param street rua, avenida, estrada e etc...
     * @param number o número da casa ou apartamento.
     * @param district bairro
     * @param city cidade
     * @param state estado
     * @param country país
     */
    public Address(final String street, final String number,
            final String district, final String city, final String state,
            final String country) {
        this.street = street;
        this.number = number;
        this.district = district;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    /**
     * Representa a rua do endereço.
     */
    @Column(nullable = true, length = 100)
    private String street;

    /**
     * Representa o número do endereço.
     */
    @Column(nullable = true, length = 10)
    private String number;

    /**
     * Representa o complemento do endereço.
     */
    @Column(nullable = true, length = 100)
    private String complement;

    /**
     * Representa o bairro do endereço.
     */
    @Column(nullable = true, length = 50)
    private String district;

    /**
     * Representa a cidade do endereço.
     */
    @Column(nullable = true, length = 50)
    private String city;

    /**
     * Representa o estado do endereço.
     */
    @Column(nullable = true, length = 50)
    private String state;

    /**
     * Representa o país do endereço.
     */
    @Column(nullable = true, length = 50)
    private String country;

    /**
     * Obter o identificado do endereço.
     * @return O identificado do endereço.
     */
    public long getId() {
        return id;
    }

    /**
     * Ajusta o identificador do endereço.
     * @param id - O novo identificador do endereço.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Obter a rua do endereço.
     * @return A rua do endereço.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Ajusta a rua do endereço.
     * @param street - A nova rua do endereço.
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Obter o número do endereço.
     * @return O número do endereço.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Ajusta o número do endereço.
     * @param number - O novo número do endereço.
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Obter o complemento do endereço.
     * @return O complemento do endereço.
     */
    public String getComplement() {
        return complement;
    }

    /**
     * Ajustar o complemento do endereço.
     * @param complement - O novo complemento do endereço.
     */
    public void setComplement(String complement) {
        this.complement = complement;
    }

    /**
     * Obter o bairro do endereço.
     * @return O bairro do endereço.
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Ajustar o bairro do endereço.
     * @param district - O novo bairro do endereço.
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * Obter a cidade do endereço.
     * @return A cidade do endereço.
     */
    public String getCity() {
        return city;
    }

    /**
     * Ajustar a cidade do endereço.
     * @param city - A nova cidade do endereço.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Obter o estado do endereço.
     * @return O estado do endereço.
     */
    public String getState() {
        return state;
    }

    /**
     * Ajustar o estado do endereço.
     * @param state - O estado do endereço.
     */
    public void setState(String state) {
        this.state = state;
    }

	/**
     * Obter o país do endereço.
     * @return O país do endereço.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Ajustar o país do endereço.
     * @param country - O novo país do endereço.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        result = prime * result + ((street == null) ? 0 : street.hashCode());
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
        Address other = (Address) obj;
        if (id != other.id)
            return false;
        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;
        if (street == null) {
            if (other.street != null)
                return false;
        } else if (!street.equals(other.street))
            return false;
        return true;
	}

    @Override
    public String toString() {
        return this.street;
    }

}
