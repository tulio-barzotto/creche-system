package br.com.crechesystem.crechesystem.dto;

import java.io.Serializable;

public class ResponsavelAlunoDTO implements Serializable {

    private ResponsavelDTO responsavelMae;

    private ResponsavelDTO responsavelPai;

    public ResponsavelDTO getResponsavelMae() {
        return responsavelMae;
    }

    public void setResponsavelMae(ResponsavelDTO responsavelMae) {
        this.responsavelMae = responsavelMae;
    }

    public ResponsavelDTO getResponsavelPai() {
        return responsavelPai;
    }

    public void setResponsavelPai(ResponsavelDTO responsavelPai) {
        this.responsavelPai = responsavelPai;
    }

    @Override
    public String toString() {
        return "ResponsavelAlunoDTO{" +
                "responsavelMae=" + responsavelMae +
                ", responsavelPai=" + responsavelPai +
                '}';
    }
}
