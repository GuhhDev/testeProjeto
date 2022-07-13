package DB;

import models.Cargo;

import java.util.HashMap;
import java.util.Map;

public class Vendedor extends Cargo {

    public Vendedor(Double salario, Double beneficio, Map<String, Double> vendas){
        setSalario(salario);
        setBeneficio(beneficio);
        setVendas(vendas);
    }

    public Map<String, Double> getVendas() {
        return vendas;
    }

    public void setVendas(Map<String, Double> vendas) {
        this.vendas = vendas;
    }

    private Map<String, Double> vendas = new HashMap<>();

}
