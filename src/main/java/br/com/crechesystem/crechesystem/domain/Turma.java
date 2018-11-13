package br.com.crechesystem.crechesystem.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "nome")
    private String name;

    @Column(name = "lotacao_maxima")
    private int maximumCapacity;

    @Column(name = "minimo_meses")
    private int minimumMonths;

    @Column(name = "maximo_meses")
    private int maximumMonths;

    @OneToMany(mappedBy = "turma")
    private Set<Aluno> alunos;

    @Transient
    private Integer alunosTotal;

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

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public int getMinimumMonths() {
        return minimumMonths;
    }

    public void setMinimumMonths(int minimumMonths) {
        this.minimumMonths = minimumMonths;
    }

    public int getMaximumMonths() {
        return maximumMonths;
    }

    public void setMaximumMonths(int maximumMonths) {
        this.maximumMonths = maximumMonths;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    @JsonProperty
    public Integer getAlunosTotal() {
        return alunos != null ? alunos.size() : 0;
    }

    @Override
    public String toString() {
        return "Turma{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maximumCapacity=" + maximumCapacity +
                ", minimumMonths=" + minimumMonths +
                ", maximumMonths=" + maximumMonths +
                '}';
    }

}
