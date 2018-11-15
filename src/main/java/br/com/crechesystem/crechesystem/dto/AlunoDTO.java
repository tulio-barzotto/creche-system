package br.com.crechesystem.crechesystem.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class AlunoDTO implements Serializable {

    private String name;

    private LocalDate birthdate;

    private Long idResponsavelAluno;

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

    public Long getIdResponsavelAluno() {
        return idResponsavelAluno;
    }

    public void setIdResponsavelAluno(Long idResponsavelAluno) {
        this.idResponsavelAluno = idResponsavelAluno;
    }

    @Override
    public String toString() {
        return "AlunoDTO{" +
                "name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", idResponsavelAluno=" + idResponsavelAluno +
                '}';
    }
}
