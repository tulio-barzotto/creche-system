package br.com.crechesystem.crechesystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "responsavel")
public class Responsavel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "nome")
    private String name;

    @NotEmpty
    @Size(max = 2)
    @Column(name = "ddd_telefone")
    private String phoneDdd;

    @NotEmpty
    @Size(max = 9)
    @Column(name = "telefone")
    private String phoneNumber;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "endereco")
    private String address;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "municipio")
    private String city;

    @NotEmpty
    @Size(max = 2)
    @Column(name = "uf")
    private String uf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneDdd() {
        return phoneDdd;
    }

    public void setPhoneDdd(String phoneDdd) {
        this.phoneDdd = phoneDdd;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Responsavel that = (Responsavel) o;

        if (!name.equals(that.name)) return false;
        if (!phoneDdd.equals(that.phoneDdd)) return false;
        if (!phoneNumber.equals(that.phoneNumber)) return false;
        if (!address.equals(that.address)) return false;
        if (!city.equals(that.city)) return false;
        return uf.equals(that.uf);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + phoneDdd.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + uf.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Responsavel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneDdd='" + phoneDdd + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }
}
