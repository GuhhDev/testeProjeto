package DB;

import models.Cargo;

public class Secretario extends Cargo {
    public Secretario(Double salario, Double beneficio){
        setSalario(salario);
        setBeneficio(beneficio);
    }
}
