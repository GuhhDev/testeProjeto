package DB;

import models.Cargo;

public class Gerente extends Cargo {
    public Gerente(Double salario, Double beneficio){
        setSalario(salario);
        setBeneficio(beneficio);
    }
}
