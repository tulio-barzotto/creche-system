package br.com.crechesystem.crechesystem.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "responsavel_aluno")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsavelAluno implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="responsavel_mae_id")
    private Responsavel responsavelMae;

    @ManyToOne
    @JoinColumn(name="responsavel_pai_id")
    private Responsavel responsavelPai;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Responsavel getResponsavelMae() {
        return responsavelMae;
    }

    public void setResponsavelMae(Responsavel responsavelMae) {
        this.responsavelMae = responsavelMae;
    }

    public Responsavel getResponsavelPai() {
        return responsavelPai;
    }

    public void setResponsavelPai(Responsavel responsavelPai) {
        this.responsavelPai = responsavelPai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponsavelAluno that = (ResponsavelAluno) o;

        if (responsavelMae != null ? !responsavelMae.equals(that.responsavelMae) : that.responsavelMae != null)
            return false;
        return responsavelPai != null ? responsavelPai.equals(that.responsavelPai) : that.responsavelPai == null;
    }

    @Override
    public int hashCode() {
        int result = responsavelMae != null ? responsavelMae.hashCode() : 0;
        result = 31 * result + (responsavelPai != null ? responsavelPai.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ResponsavelAluno{" +
                "id=" + id +
                ", responsavelMae=" + responsavelMae +
                ", responsavelPai=" + responsavelPai +
                '}';
    }
}
