package br.com.crechesystem.crechesystem.domain;

import br.com.crechesystem.crechesystem.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "aluno")
public class Aluno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(max = 100)
    @Column(name = "nome")
    private String name;

    @NotNull
    @Column(name = "nascimento")
    private Date birthdate;

    @JsonManagedReference
    @NotNull
    @ManyToOne
    @JoinColumn(name="turma_id")
    private Turma turma;

    @NotNull
    @ManyToOne
    @JoinColumn(name="responsavel_aluno_id")
    private ResponsavelAluno responsavelAluno;

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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAge() {
        return DateUtils.getAgeFormatted(this.birthdate);
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public ResponsavelAluno getResponsavelAluno() {
        return responsavelAluno;
    }

    public void setResponsavelAluno(ResponsavelAluno responsavelAluno) {
        this.responsavelAluno = responsavelAluno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Aluno aluno = (Aluno) o;

        if (name != null ? !name.equals(aluno.name) : aluno.name != null) return false;
        if (birthdate != null ? !birthdate.equals(aluno.birthdate) : aluno.birthdate != null) return false;
        if (turma != null ? !turma.equals(aluno.turma) : aluno.turma != null) return false;
        return responsavelAluno != null ? responsavelAluno.equals(aluno.responsavelAluno) : aluno.responsavelAluno == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (turma != null ? turma.hashCode() : 0);
        result = 31 * result + (responsavelAluno != null ? responsavelAluno.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", turma=" + turma +
                ", responsavelAluno=" + responsavelAluno +
                '}';
    }
}
