package br.com.crechesystem.crechesystem.domain;

import br.com.crechesystem.crechesystem.security.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Entity
@Table(name = "funcionario")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "login")
    private String login;

    @NotEmpty
    @Column(name = "password")
    private String password;

    @NotEmpty
    @Size(max = 20)
    @Column(name = "nome")
    private String name;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "sobrenome")
    private String lastName;

    @NotEmpty
    @Size(max = 50)
    @Column(name = "email")
    private String mail;

    @NotEmpty
    @Size(min = 11, max = 11)
    @Column(name = "cpf")
    private String cpf;

    @Transient
    private static final Role ROLE = Role.ROLE_USER;

    public Funcionario(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.name = funcionario.getName();
        this.lastName = funcionario.getLastName();
        this.password = funcionario.getPassword();
        this.login = funcionario.getLogin();
        this.cpf = funcionario.getCpf();
        this.mail = funcionario.getMail();
    }

    public Funcionario() {
        //DEFAULT CONSTRUCTOR
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Role getRole() {
        return ROLE;
    }

    public String getUsername() {
        return login;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mail='" + mail + '\'' +
                ", cpf='" + cpf + '\'' +
                ", ROLE=" + ROLE +
                '}';
    }
}
