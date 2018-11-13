package br.com.crechesystem.crechesystem.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "nome")
    private String name;

    @NotEmpty
    @Column(name = "nascimento")
    private LocalDate birthdate;

    @ManyToOne
    @JoinColumn(name="turma_id")
    private Turma turma;

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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aluno aluno = (Aluno) o;

        return id != null ? id.equals(aluno.id) : aluno.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", turma=" + turma +
                '}';
    }
}
