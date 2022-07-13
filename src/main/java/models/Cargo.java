package models;

import Exceptions.ValorNaoInformadoException;

import java.util.Objects;

public class Cargo {
    private Double salario;
    private Double beneficio;

    public Double getSalario() throws ValorNaoInformadoException  {
        return salario;
    }

    public void setSalario(Double salario) {
        if (Objects.nonNull(salario))
            this.salario = salario;
    }

    public Double getBeneficio() {
        return beneficio == null ? beneficio = 0.0 : beneficio;
    }

    public void setBeneficio(Double beneficio) throws ValorNaoInformadoException {
        if (beneficio > 0)
            this.beneficio = beneficio;
    }
}